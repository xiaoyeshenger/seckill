package com.jsxa.vapp.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.jsxa.vapp.common.bo.vo.PageVo;
import com.jsxa.vapp.common.redis.RedisKey;
import com.jsxa.vapp.common.redis.RedisService;
import com.jsxa.vapp.inventory.bo.po.VaccinationPerson;
import com.jsxa.vapp.inventory.bo.po.VaccinationSite;
import com.jsxa.vapp.inventory.bo.po.Vaccine;
import com.jsxa.vapp.inventory.bo.po.VaccineRelease;
import com.jsxa.vapp.order.bo.dto.VaccinationRecordReqDto;
import com.jsxa.vapp.order.bo.dto.VaccinationRecordPageReqDto;
import com.jsxa.vapp.order.bo.po.VaccinationRecord;
import com.jsxa.vapp.order.mapper.VaccinationRecordMapper;
import com.jsxa.vapp.order.mapper.VaccinationRecordDynamicSqlSupport;
import com.jsxa.vapp.order.excel.VaccinationRecordExcelListener;
import com.jsxa.vapp.order.excel.VaccinationRecordExcelVo;
import com.jsxa.vapp.common.utils.*;
import com.jsxa.vapp.common.redis.RedisService;
import com.jsxa.vapp.common.redis.RedisKey;
import com.jsxa.vapp.common.validator.FieldDupValidator;
import com.jsxa.vapp.order.rocksDB.RocksDBUtil;
import com.jsxa.vapp.order.service.OrderService;
import com.jsxa.vapp.order.service.VaccinationRecordService;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.rocksdb.RocksDBException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.mybatis.dynamic.sql.select.MyBatis3SelectModelAdapter;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;


import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

/**
 * @Author zhangyong
 * @Description //VaccinationRecordService接口实现类
 * @Date 2021/02/27 15:20
 * @Param
 * @return
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class VaccinationRecordServiceImpl implements VaccinationRecordService {


    //1.本机内存保存抢苗活动是否启用
    public static Map<String, Object> runtimeVaccineStockMap = new HashMap<String, Object>(){{put("start", 0);}};

    private final RedisService redisService;

    private final CacheUtil cacheUtil;

    private final Validator validator;

    private final FieldDupValidator fieldDupValidator;

    private final VaccinationRecordMapper vaccinationRecordMapper;

    private final DefaultRedisScript defaultRedisScript;

    private final RedisTemplate redisTemplate;

    private final RocketMQTemplate rocketMQTemplate;


    @Override
    public Map<String, Object> addVaccinationRecord(VaccinationRecordReqDto vaccinationRecordReqDto) {
        //1.判断是否能够开始预约抢苗(未开始/已结束(库存被抢空))
        Integer start = (Integer) runtimeVaccineStockMap.get("start");
        if(start == 0){
            throw new IllegalArgumentException("暂不能预约，预约未开始或预约已结束");
        }

        //2.参数校验
        //(1).查询疫苗发放活动
        Long vaccineReleaseId = vaccinationRecordReqDto.getVaccineReleaseId();
        VaccineRelease vaccineRelease = JSONObject.parseObject((String) redisService.hmGet("VaccineRelease", String.valueOf(vaccineReleaseId)), VaccineRelease.class);
        if(ObjUtil.isEmpty(vaccineRelease)){
            throw new IllegalArgumentException("id为:"+vaccineReleaseId+"的疫苗发放活动不存在");
        }
        Long recordType = vaccinationRecordReqDto.getRecordType();
        cacheUtil.getDataDictName(recordType);

        Long timePeriod = vaccineRelease.getTimePeriod();
        cacheUtil.getDataDictName(timePeriod);

        //(2).用户是否存在
        String openId = vaccinationRecordReqDto.getOpenId();
        VaccinationPerson vaccinationPerson = JSONObject.parseObject((String) redisService.hmGet("VaccinationPerson", String.valueOf(openId)), VaccinationPerson.class);
        if(ObjUtil.isEmpty(vaccinationPerson)){
            throw new IllegalArgumentException("openId为:"+openId+"的接种人员不存在");
        }

        //(3).疫苗是否存在
        Long vaccineId = vaccineRelease.getVaccineId();
        Vaccine vaccine = JSONObject.parseObject((String) redisService.hmGet("Vaccine", String.valueOf(vaccineId)), Vaccine.class);
        if(ObjUtil.isEmpty(vaccine)){
            throw new IllegalArgumentException("id为:"+vaccineId+"的疫苗不存在");
        }

        //(4).接种地点是否存在
        Long siteId = vaccineRelease.getSiteId();
        VaccinationSite vaccinationSite = JSONObject.parseObject((String) redisService.hmGet("VaccinationSite", String.valueOf(siteId)), VaccinationSite.class);
        if(ObjUtil.isEmpty(vaccinationSite)){
            throw new IllegalArgumentException("id为:"+siteId+"的接种地点不存在");
        }

        //(5).是否已预约该疫苗及剂次(重复预约)
        String idNumber = vaccinationPerson.getIdNumber();
        Integer dose = vaccineRelease.getDose();
        String itemKey = idNumber + "_" + vaccineId + "_" + dose;
        Object rushVaccineRecordTime = redisService.hmGet("RushVaccineRecord", itemKey);
        if(!ObjUtil.isEmpty(rushVaccineRecordTime)){
            throw new IllegalArgumentException("身份证号:" + idNumber + "已预约"+vaccine.getName()+"第 " + dose + " 剂次的疫苗,请勿重复预约!");
        }

        //(6).是否已接种该疫苗及剂次(重复接种)
        Object historyVaccineRecordTime = redisService.hmGet("HistoryVaccineRecord", itemKey);
        if(!ObjUtil.isEmpty(historyVaccineRecordTime)){
            throw new IllegalArgumentException("身份证号:" + idNumber + "已接种"+vaccine.getName()+"第 " + dose + " 剂次的疫苗,请勿重复预约!");
        }

        //(7).如果剂次n大于1，则判断n-1次(前一次)是否有接种记录以及时间间隔是否符合要求
        if(dose > 1){
            //--1.判断n-1次(前一次)是否有接种记录,即如果要接种第2剂次，首先判断是否正常完成了第1剂次的接种
            Integer previousDose = dose-1;
            String previousItemKey = idNumber + "_" + vaccineId + "_" + previousDose;
            Object previousHistoryVaccineRecordTime = redisService.hmGet("HistoryVaccineRecord", previousItemKey);
            if(ObjUtil.isEmpty(previousHistoryVaccineRecordTime)){
                throw new IllegalArgumentException("身份证号:" + idNumber + "未接种"+vaccine.getName()+"第 " + previousDose + " 剂次的疫苗,请先接种后再预约!");
            }

            //--2.判断剂次接种间隔时间是否达到要求,每种疫苗的接种次数和间隔时间不一样(新增疫苗时根据厂家不同存储不同的时间间隔doseInterval)，保存在疫苗(Vaccine)信息中
            //a.查看第一次接种的时间
            String firstItemKey = idNumber + "_" + vaccineId + "_" + 1;
            Object firstHistoryVaccineRecordTime = redisService.hmGet("HistoryVaccineRecord", firstItemKey);
            Long firstDoseTime = Long.valueOf((String) firstHistoryVaccineRecordTime);

            //b.查看该疫苗间隔的时间列表(剂次间隔时间(比如:21,60)(21代表第二剂与第一剂之间的时间间隔为21天，60代表第三剂与第一剂之间的时间间隔为60天)"))
            String doseInterval = vaccine.getDoseInterval();
            String[] doseIntervalArr = doseInterval.split(",");

            //c.获取到该疫苗当前剂次与第一次接种需要间隔的时间(疫苗的间隔时间无论第几剂次时间上都是和第一次接种时间做比较)
            String doseIntervalStr = doseIntervalArr[dose - 2];
            Integer doseIntervalDay = Integer.parseInt(doseIntervalStr);

            //d.获取到可以进行该剂次接种的正确时间
            long rightDoseTime = firstDoseTime + doseIntervalDay * 24 * 60 * 60 * 1000L;

            //e.判断当前时间是否大于接种的正确时间，大于才能进行预约接种
            Long currentTime = System.currentTimeMillis();
            if(currentTime < rightDoseTime){
                throw new IllegalArgumentException("身份证号:" + idNumber + "接种"+vaccine.getName()+"第 " + previousDose + " 剂次的疫苗,与第一次接种时间还未超过 "+doseIntervalDay+" 天,请到时间后再预约!");
            }
        }


        //3.库存查询和预扣减(此处是对redis进行扣减所以是库存预扣减，真正的库存扣减是对mysql进行扣减)
        //库存扣减的核心是 查询和扣减 2步必须是原子操作，为了高并发使用redis+lua脚本实现库存的原子性无锁扣减以防止超卖（lua脚本在redis可以保证原子性）,
        //速度比分布式锁快，并且不用考虑锁超时时间设置等问题，而悲观锁和乐观锁都会阻塞其他请求，所以redis+lua是无锁操作，速度更快
        Long reduseResult = (Long) redisTemplate.execute(defaultRedisScript, Collections.singletonList("RuntimeVaccineStock"), 1);
        if(reduseResult == -1){
            runtimeVaccineStockMap.put("start",0);
            throw new IllegalArgumentException("疫苗已被约满,请到稍后再约!");
        }

        //4.在本地持久化扣减记录，便于后期查错和同步数据(因为存在着Redis宕机和本服务同时宕机的可能，这样会造成数据的丢失，所以需要快速持久化扣减记录，采用WAL机制实现，保存到本地RocksDB数据库)
        //(1).列族名称(类似于OOS的桶)
        String cfName = "vaccineReleaseId:" + vaccineReleaseId;
        //(2).key = itemKey = idNumber + "_" + vaccineId + "_" + dose;
        String key = itemKey;
        //(3).value = 扣减的库存数量
        String value = "1";
        try {
            RocksDBUtil.put(cfName,key,value);
        } catch (RocksDBException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("服务异常，请稍后再试!");
        }

        //5.扣减redis中的库存成功后，将订单信息即预约记录同步发送到rocketmq,订单消费者监听到消息后再做订单创建和库存扣减
        //(1).构建预约记录
        VaccinationRecord vaccinationRecord = VoPoConverterUtil.copyProperties(vaccinationRecordReqDto, VaccinationRecord.class);
        Long currentTime = System.currentTimeMillis();
        vaccinationRecord
                .setId(new IdWorker().nextId())
                .setRecordType(509L)
                .setVaild((byte)1)
                .setVaccineReleaseId(vaccineId)
                .setPersonId(vaccinationPerson.getId())
                .setPersonName(vaccinationPerson.getName())
                .setSex(vaccinationPerson.getSex())
                .setAge(vaccinationPerson.getAge())
                .setMobile(vaccinationPerson.getMobile())
                .setIdNumber(vaccinationPerson.getIdNumber())
                .setOpenId(openId)
                .setSiteId(siteId)
                .setSiteName(vaccinationSite.getName())
                .setRecordType(2032L)
                .setVaccineId(vaccineId)
                .setVaccineName(vaccine.getName())
                .setVaccineBatch(vaccine.getBatchNumber())
                .setManufacturer(vaccine.getManufacturer())
                .setDose(dose)
                .setDoseUnit("成都市青白江区卫健局")
                .setAppointmentTime(currentTime)
                .setTimePeriod(vaccineRelease.getTimePeriod())
                .setTimePeriodName(cacheUtil.getDataDictName(vaccineRelease.getTimePeriod()))
                .setCreateTime(currentTime);

        //(2).发送订单信息到rocketmq(同步发送syncSend的方式)，rocketmq消费者(OrderConsumer)监听到对应topic的消息，执行订单创建和Mysql库存扣减，Mysql库存扣减采用乐观锁即版本(version)的方式再次防止超卖
        //a.如果发送成功，由于创建订单还需要一定时间，前端则隐藏用户的抢购页面而是轮训调用订单查询页面(比如5秒钟查询一次)，订单未查询出来时，页面显示订单正在创建中即可
        //b.如果发送失败或出现异常,由于上面通过redis+lua已经扣减了redis的库存，所以需要还原redis库存
        try {
            SendResult sendResult = rocketMQTemplate.syncSend("vaccination_record", vaccinationRecord);
            if(!sendResult.getSendStatus().equals(SendStatus.SEND_OK)){
                redisService.incrByDelta("RuntimeVaccineStock",1);
                throw new IllegalArgumentException("服务异常，请稍后再试!!");
            }
        }catch (Exception e){
            redisService.incrByDelta("RuntimeVaccineStock",1);
            e.printStackTrace();
            throw new IllegalArgumentException("服务异常，请稍后再试!!!");
        }

        //6.预约成功，将该用户的预约信息存入redis，避免重复预约
        //redisService.hmSet("RushVaccineRecord", itemKey,String.valueOf(System.currentTimeMillis()));

        //7.返回结果
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("msg","预约成功,正在创建订单");
        return resultMap;
    }

    /**
     * 如果同步发送mq速度过慢，也可异步发送
     */
    private void asyncSendToMq(VaccinationRecord vaccinationRecord) {

        rocketMQTemplate.asyncSend("vaccination_record",vaccinationRecord, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("预约订单入库消息写入rocketmq成功，消息ID:{}", sendResult.getMsgId());

            }
            @Override
            public void onException(Throwable e) {
                //如果与mq通信故障了，可以从日志文件里找到该条预约记录，手工执行写入mysql
                log.error("预约订单写入rocketmq失败:{}, exception detail:{}" , vaccinationRecord.toString(), e.getMessage());
            }

        });
    }



    @Override
    @Transactional
    public Map<String, Object> deleteVaccinationRecordById(Long id){
        VaccinationRecord vaccinationRecord = vaccinationRecordMapper.selectByPrimaryKey(id);
        if(vaccinationRecord == null){
            throw new IllegalArgumentException("id为:"+id+"的疫苗发放信息不存在");
        }

        vaccinationRecordMapper.deleteByExample()
                    .where(VaccinationRecordDynamicSqlSupport.id, isEqualTo(id))
                    .build()
                    .execute();

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg","删除疫苗发放成功");
        return resultMap;
    }

    @Override
    @Transactional
    public Map<String, Object> insertOrUpdateVaccinationRecord(VaccinationRecordReqDto vaccinationRecordReqDto) {
        //1.参数校验

        //2.判断vaccinationRecord是否存在
        VaccinationRecord vaccinationRecord = vaccinationRecordMapper.selectByPrimaryKey(vaccinationRecordReqDto.getId());
        if(ObjUtil.isEmpty(vaccinationRecord)){
            throw new IllegalArgumentException("id为:"+vaccinationRecordReqDto.getId()+"的疫苗发放不存在");
        }

        //3.更新VaccinationRecord
        //(1)复制VaccinationRecordDto中的请求参数到VaccinationRecord
        VoPoConverterUtil.beanConverterNotNull(vaccinationRecordReqDto, vaccinationRecord);

        //4.保存
        vaccinationRecordMapper.updateByPrimaryKey(vaccinationRecord);

        //5.返回结果
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("msg","更新疫苗发放信息成功");
        return resultMap;
    }

    @Override
    public Map<String, Object> getVaccinationRecordById(Long id){
        VaccinationRecord e = vaccinationRecordMapper.selectByPrimaryKey(id);
        if(ObjUtil.isEmpty(e)){
            throw new IllegalArgumentException("id为:"+id+"的疫苗发放信息不存在");
        }

        Map<String, Object> attr = new HashMap<>();
        attr.put("id", e.getId());
        attr.put("recordType", e.getRecordType());
        attr.put("vaild", e.getVaild());
        attr.put("personId", e.getPersonId());
        attr.put("personName", e.getPersonName());
        attr.put("sex", e.getSex());
        attr.put("age", e.getAge());
        attr.put("mobile", e.getMobile());
        attr.put("idNumber", e.getIdNumber());
        attr.put("openId", e.getOpenId());
        attr.put("siteId", e.getSiteId());
        attr.put("siteName", e.getSiteName());
        attr.put("recordStatus", e.getRecordStatus());
        attr.put("vaccineId", e.getVaccineId());
        attr.put("vaccineName", e.getVaccineName());
        attr.put("vaccineBatch", e.getVaccineBatch());
        attr.put("manufacturer", e.getManufacturer());
        attr.put("dose", e.getDose());
        attr.put("doseUnit", e.getDoseUnit());
        attr.put("appointmentTime", e.getAppointmentTime());
        attr.put("timePeriod", e.getTimePeriod());
        attr.put("timePeriodName", e.getTimePeriodName());
        attr.put("doseTime", e.getDoseTime());
        attr.put("imageUrl", e.getImageUrl());
        attr.put("city", e.getCity());
        attr.put("county", e.getCounty());
        attr.put("town", e.getTown());
        attr.put("msg", e.getMsg());
        attr.put("createTime", e.getCreateTime());
        return attr;
    }

    @Override
    public PageVo<Map<String, Object>> getVaccinationRecordListPageVo(VaccinationRecordPageReqDto vaccinationRecordPageReqDto){

        //1.查询列表
        List<VaccinationRecord> list = queryListByPageReqDto(vaccinationRecordPageReqDto,true);

        //2.构建pageVo
        PageVo<VaccinationRecord> pageVo = new PageVo<>(list);

        //3.对象转为map分页输出
        return PageVo.of(
                pageVo,
                e -> {
                    Map<String, Object> attr = new HashMap<>();
                    attr.put("id", e.getId());
                    attr.put("recordType", e.getRecordType());
                    attr.put("vaild", e.getVaild());
                    attr.put("personId", e.getPersonId());
                    attr.put("personName", e.getPersonName());
                    attr.put("sex", e.getSex());
                    attr.put("age", e.getAge());
                    attr.put("mobile", e.getMobile());
                    attr.put("idNumber", e.getIdNumber());
                    attr.put("openId", e.getOpenId());
                    attr.put("siteId", e.getSiteId());
                    attr.put("siteName", e.getSiteName());
                    attr.put("recordStatus", e.getRecordStatus());
                    attr.put("vaccineId", e.getVaccineId());
                    attr.put("vaccineName", e.getVaccineName());
                    attr.put("vaccineBatch", e.getVaccineBatch());
                    attr.put("manufacturer", e.getManufacturer());
                    attr.put("dose", e.getDose());
                    attr.put("doseUnit", e.getDoseUnit());
                    attr.put("appointmentTime", e.getAppointmentTime());
                    attr.put("timePeriod", e.getTimePeriod());
                    attr.put("timePeriodName", e.getTimePeriodName());
                    attr.put("doseTime", e.getDoseTime());
                    attr.put("imageUrl", e.getImageUrl());
                    attr.put("city", e.getCity());
                    attr.put("county", e.getCounty());
                    attr.put("town", e.getTown());
                    attr.put("msg", e.getMsg());
                    attr.put("createTime", e.getCreateTime());
                    return attr;
                }
        );
    }

    @Override
    public void downloadTemplateExcel(HttpServletResponse response){
        //1.查询到该对象的导入模板
        /*VaccinationRecord vaccinationRecord = vaccinationRecordMapper.selectByExampleOne()
                .where(VaccinationRecordDynamicSqlSupport.fileKey, isEqualTo("vaccinationRecordTemplateExcel"))
                .build()
                .execute();
        if(ObjUtil.isEmpty(vaccinationRecord)){
            throw new IllegalArgumentException("模板文件暂未上传,请联系管理员");
        }
        //2.响应文件到页面
        minioUtil.download(vaccinationRecord.getStorePath(),vaccinationRecord.getName(),response);*/
    }

    @Override
    public Map<String, Object> importByExcel(MultipartHttpServletRequest request){

        //1.文件是否为空
        MultipartFile excelFile = request.getFile("xls");
        if (ObjUtil.isEmpty(excelFile)) {
            throw new IllegalArgumentException("文件不能为空");
        }

        //2.清空-->字段重复校验map
        fieldDupValidator.resetFieldSetMap();

        //3.获取excel表格每行的内容
        ExcelReader excelReader = null;
        InputStream in = null;
        VaccinationRecordExcelListener vaccinationRecordExcelListener = new VaccinationRecordExcelListener(this,validator,fieldDupValidator);
        try {
            in = excelFile.getInputStream();
            EasyExcel.read(in, VaccinationRecordExcelVo.class, vaccinationRecordExcelListener).sheet().doRead();
        } catch (IOException ex) {
            ex.getStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (excelReader != null) {
                excelReader.finish();
            }
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg", "导入系统文件成功");
        return resultMap;
    }

    @Override
    public void exportToExcel(VaccinationRecordPageReqDto vaccinationRecordPageReqDto,HttpServletResponse response){
        //1.查询列表
        List<VaccinationRecord> vaccinationRecordList = queryListByPageReqDto(vaccinationRecordPageReqDto,false);

        //2.将原始列表转为导出对象列表
        List<VaccinationRecordExcelVo> vaccinationRecordExcelVoList = vaccinationRecordList.stream().map(e -> {
            VaccinationRecordExcelVo vaccinationRecordExcelVo = VoPoConverterUtil.copyProperties(e, VaccinationRecordExcelVo.class);
            return vaccinationRecordExcelVo;
        }).collect(Collectors.toList());

        //3.获取到输出流
        OutputStream outputStream = null;
        try {
            //(1).文件名，表名，表头，格式
            String sheetName = "疫苗发放";
            String fileName = sheetName.concat(String.valueOf(System.currentTimeMillis())).concat(".xlsx");

            //(2).response输出文件流格式
            response.setContentType("APPLICATION/OCTET-STREAM");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("gb2312"), "ISO8859-1"));

            //(3).获取到输出流
            outputStream = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //4.将输出流写入到response,直接响应给浏览器
        EasyExcel.write(outputStream)
                .head(VaccinationRecordExcelVo.class)
                .sheet("Sheet1")
                .doWrite(vaccinationRecordExcelVoList);
    }

    private List<VaccinationRecord> queryListByPageReqDto(VaccinationRecordPageReqDto vaccinationRecordPageReqDto,Boolean needPaging){
        //1.查询语句构建
        QueryExpressionDSL<MyBatis3SelectModelAdapter<List<VaccinationRecord>>>.QueryExpressionWhereBuilder builder = vaccinationRecordMapper.selectByExample().where();

        //2.根据查询条件封装查询命令
        //(1).关键字查询
        /*String name = vaccinationRecordPageReqDto.getName();
        if(!ObjUtil.isEmpty(name)){
        builder.and(VaccinationRecordDynamicSqlSupport.name, isLike("%"+name+"%"));
        }

        Long type = vaccinationRecordPageReqDto.getType();
        if(!ObjUtil.isEmpty(type)){
        builder.and(VaccinationRecordDynamicSqlSupport.type, isEqualTo(type));
        }

        Long startTime = vaccinationRecordPageReqDto.getStartTime();
        Long endTime = vaccinationRecordPageReqDto.getEndTime();
        if (startTime != null && endTime != null) {
        builder.and(VaccinationRecordDynamicSqlSupport.startTime, isGreaterThanOrEqualTo(startTime));
        builder.and(VaccinationRecordDynamicSqlSupport.endTime, isLessThanOrEqualTo(endTime));
        } else {
            if (startTime != null) {
            builder.and(VaccinationRecordDynamicSqlSupport.startTime, isGreaterThanOrEqualTo(startTime));
            }
            if (endTime != null) {
            builder.and(VaccinationRecordDynamicSqlSupport.endTime, isLessThanOrEqualTo(endTime));
            }
        }*/

        //3.排序
        builder.orderBy(VaccinationRecordDynamicSqlSupport.createTime.descending());

        //4.查询(不需要分页即列表)
        if(needPaging){
        PageHelper.startPage(vaccinationRecordPageReqDto.getPageNum(), vaccinationRecordPageReqDto.getPageSize());
        }

        List<VaccinationRecord> list = builder.build().execute();
        return list;
    }
}