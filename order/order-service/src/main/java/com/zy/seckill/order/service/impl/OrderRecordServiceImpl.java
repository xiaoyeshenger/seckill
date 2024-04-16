package com.zy.seckill.order.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.zy.seckill.common.bo.vo.PageVo;
import com.zy.seckill.common.redis.RedisService;
import com.zy.seckill.inventory.bo.po.Person;
import com.zy.seckill.inventory.bo.po.Product;
import com.zy.seckill.inventory.bo.po.Site;
import com.zy.seckill.inventory.bo.po.ProductRelease;
import com.zy.seckill.order.bo.dto.OrderRecordReqDto;
import com.zy.seckill.order.bo.dto.OrderRecordPageReqDto;
import com.zy.seckill.order.mapper.OrderRecordMapper;
import com.zy.seckill.order.mapper.OrderRecordDynamicSqlSupport;
import com.zy.seckill.order.excel.OrderRecordExcelListener;
import com.zy.seckill.order.excel.OrderRecordExcelVo;
import com.zy.seckill.common.utils.*;
import com.zy.seckill.common.validator.FieldDupValidator;
import com.zy.seckill.order.rocksDB.RocksDBUtil;
import com.zy.seckill.order.service.OrderRecordService;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.rocksdb.RocksDBException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.mybatis.dynamic.sql.select.MyBatis3SelectModelAdapter;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.zy.seckill.order.bo.po.OrderRecord;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;


import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

/**
 * @Author zhangyong
 * @Description //OrderRecordService接口实现类
 * @Date xxxx/02/27 15:20
 * @Param
 * @return
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class OrderRecordServiceImpl implements OrderRecordService {


    //1.本机内存保存抢苗活动是否启用
    public static Map<String, Object> runtimeVaccineStockMap = new HashMap<String, Object>(){{put("start", 0);}};

    private final RedisService redisService;

    private final CacheUtil cacheUtil;

    private final Validator validator;

    private final FieldDupValidator fieldDupValidator;

    private final OrderRecordMapper orderRecordMapper;

    private final DefaultRedisScript defaultRedisScript;

    private final RedisTemplate redisTemplate;

    private final RocketMQTemplate rocketMQTemplate;


    @Override
    public Map<String, Object> addOrderRecord(OrderRecordReqDto orderRecordReqDto) {
        //1.判断是否能够开始预约抢苗(未开始/已结束(库存被抢空))
        Integer start = (Integer) runtimeVaccineStockMap.get("start");
        if(start == 0){
            throw new IllegalArgumentException("暂不能预约，预约未开始或预约已结束");
        }

        //2.参数校验
        //(1).查询商品秒杀活动和数据字典是否存在
        Long productReleaseId = orderRecordReqDto.getProductReleaseId();
        ProductRelease productRelease = JSONObject.parseObject((String) redisService.hmGet("ProductRelease", String.valueOf(productReleaseId)), ProductRelease.class);
        if(ObjUtil.isEmpty(productRelease)){
            throw new IllegalArgumentException("id为:"+productReleaseId+"的商品秒杀活动不存在");
        }
        Long recordType = orderRecordReqDto.getRecordType();
        cacheUtil.getDataDictName(recordType);


        //(2).用户是否存在
        String openId = orderRecordReqDto.getOpenId();
        Person person = JSONObject.parseObject((String) redisService.hmGet("Person", String.valueOf(openId)), Person.class);
        if(ObjUtil.isEmpty(person)){
            throw new IllegalArgumentException("openId为:"+openId+"的接种人员不存在");
        }

        //(3).商品是否存在
        Long productId = productRelease.getProductId();
        Product product = JSONObject.parseObject((String) redisService.hmGet("Product", String.valueOf(productId)), Product.class);
        if(ObjUtil.isEmpty(product)){
            throw new IllegalArgumentException("id为:"+productId+"的商品不存在");
        }

        //(4)商铺是否存在
        Long siteId = productRelease.getSiteId();
        Site site = JSONObject.parseObject((String) redisService.hmGet("Site", String.valueOf(siteId)), Site.class);
        if(ObjUtil.isEmpty(site)){
            throw new IllegalArgumentException("id为:"+siteId+"的商铺不存在");
        }

        //(5).是否已秒杀过本次活动的该商品(重复预约)
        String mobile = person.getMobile();
        String itemKey = mobile + "_" + productReleaseId + "_" + productId;
        Object rushVaccineRecordTime = redisService.hmGet("RushProductRecord", itemKey);
        if(!ObjUtil.isEmpty(rushVaccineRecordTime)){
            throw new IllegalArgumentException("手机号:" + mobile + "已抢购"+ product.getName()+",请勿重复抢购!");
        }

        //3.库存查询和预扣减(此处是对redis进行扣减所以是库存预扣减，真正的库存扣减是对mysql进行扣减)
        //库存扣减的核心是 查询和扣减 2步必须是原子操作，为了高并发使用redis+lua脚本实现库存的原子性无锁扣减以防止超卖（lua脚本在redis可以保证原子性）,
        //速度比分布式锁快，并且不用考虑锁超时时间设置等问题，而悲观锁和乐观锁都会阻塞其他请求，所以redis+lua是无锁操作，速度更快
        Long reduseResult = (Long) redisTemplate.execute(defaultRedisScript, Collections.singletonList("RuntimeProductStock"), 1);
        if(reduseResult == -1){
            runtimeVaccineStockMap.put("start",0);
            throw new IllegalArgumentException("商品已售罄,请到稍后再抢购!");
        }

        //4.在本地持久化扣减记录，便于后期查错和同步数据(因为存在着Redis宕机和本服务同时宕机的可能，这样会造成数据的丢失，所以需要快速持久化扣减记录，采用WAL机制实现，保存到本地RocksDB数据库)
        //(1).列族名称(类似于OOS的桶)
        String cfName = "productReleaseId:" + productReleaseId;
        //(2).key = itemKey = mobile + "_" + productReleaseId + "_" + productId;
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
        OrderRecord orderRecord = VoPoConverterUtil.copyProperties(orderRecordReqDto, OrderRecord.class);
        Long currentTime = System.currentTimeMillis();
        orderRecord
                .setId(new IdWorker().nextId())
                .setRecordType(509L)
                .setProductReleaseId(productReleaseId)
                .setPersonId(person.getId())
                .setPersonName(person.getName())
                .setSex(person.getSex())
                .setAge(person.getAge())
                .setMobile(person.getMobile())
                .setOpenId(openId)
                .setSiteId(siteId)
                .setSiteName(site.getName())
                .setRecordType(2032L)
                .setProductId(productId)
                .setProductName(product.getName())
                .setProductBatch(product.getBatchNumber())
                .setManufacturer(product.getManufacturer())
                .setAppointmentTime(currentTime)
                .setCreateTime(currentTime);

        //(2).发送订单信息到rocketmq(同步发送syncSend的方式)，rocketmq消费者(OrderConsumer)监听到对应topic的消息，执行订单创建和Mysql库存扣减，Mysql库存扣减采用乐观锁即版本(version)的方式再次防止超卖
        //a.如果发送成功，由于创建订单还需要一定时间，前端则隐藏用户的抢购页面而是轮训调用订单查询页面(比如5秒钟查询一次)，订单未查询出来时，页面显示订单正在创建中即可
        //b.如果发送失败或出现异常,由于上面通过redis+lua已经扣减了redis的库存，所以需要还原redis库存
        try {
            SendResult sendResult = rocketMQTemplate.syncSend("order_record", orderRecord);
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
    private void asyncSendToMq(OrderRecord orderRecord) {

        rocketMQTemplate.asyncSend("order_record", orderRecord, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("预约订单入库消息写入rocketmq成功，消息ID:{}", sendResult.getMsgId());

            }
            @Override
            public void onException(Throwable e) {
                //如果与mq通信故障了，可以从日志文件里找到该条预约记录，手工执行写入mysql
                log.error("预约订单写入rocketmq失败:{}, exception detail:{}" , orderRecord.toString(), e.getMessage());
            }

        });
    }



    @Override
    @Transactional
    public Map<String, Object> deleteOrderRecordById(Long id){
        OrderRecord orderRecord = orderRecordMapper.selectByPrimaryKey(id);
        if(orderRecord == null){
            throw new IllegalArgumentException("id为:"+id+"的商品秒杀信息不存在");
        }

        orderRecordMapper.deleteByExample()
                    .where(OrderRecordDynamicSqlSupport.id, isEqualTo(id))
                    .build()
                    .execute();

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg","删除商品秒杀成功");
        return resultMap;
    }

    @Override
    @Transactional
    public Map<String, Object> insertOrUpdateOrderRecord(OrderRecordReqDto orderRecordReqDto) {
        //1.参数校验

        //2.判断orderRecord是否存在
        OrderRecord orderRecord = orderRecordMapper.selectByPrimaryKey(orderRecordReqDto.getId());
        if(ObjUtil.isEmpty(orderRecord)){
            throw new IllegalArgumentException("id为:"+ orderRecordReqDto.getId()+"的商品秒杀不存在");
        }

        //3.更新OrderRecord
        //(1)复制OrderRecordDto中的请求参数到OrderRecord
        VoPoConverterUtil.beanConverterNotNull(orderRecordReqDto, orderRecord);

        //4.保存
        orderRecordMapper.updateByPrimaryKey(orderRecord);

        //5.返回结果
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("msg","更新商品秒杀信息成功");
        return resultMap;
    }

    @Override
    public Map<String, Object> getOrderRecordById(Long id){
        OrderRecord e = orderRecordMapper.selectByPrimaryKey(id);
        if(ObjUtil.isEmpty(e)){
            throw new IllegalArgumentException("id为:"+id+"的商品秒杀信息不存在");
        }

        Map<String, Object> attr = new HashMap<>();
        attr.put("id", e.getId());
        attr.put("recordType", e.getRecordType());
        attr.put("personId", e.getPersonId());
        attr.put("personName", e.getPersonName());
        attr.put("sex", e.getSex());
        attr.put("age", e.getAge());
        attr.put("mobile", e.getMobile());
        attr.put("openId", e.getOpenId());
        attr.put("siteId", e.getSiteId());
        attr.put("siteName", e.getSiteName());
        attr.put("recordStatus", e.getRecordStatus());
        attr.put("productId", e.getProductId());
        attr.put("productName", e.getProductName());
        attr.put("productBatch", e.getProductBatch());
        attr.put("manufacturer", e.getManufacturer());
        attr.put("appointmentTime", e.getAppointmentTime());
        attr.put("imageUrl", e.getImageUrl());
        attr.put("msg", e.getMsg());
        attr.put("createTime", e.getCreateTime());
        return attr;
    }

    @Override
    public PageVo<Map<String, Object>> getOrderRecordListPageVo(OrderRecordPageReqDto orderRecordPageReqDto){

        //1.查询列表
        List<OrderRecord> list = queryListByPageReqDto(orderRecordPageReqDto,true);

        //2.构建pageVo
        PageVo<OrderRecord> pageVo = new PageVo<>(list);

        //3.对象转为map分页输出
        return PageVo.of(
                pageVo,
                e -> {
                    Map<String, Object> attr = new HashMap<>();
                    attr.put("id", e.getId());
                    attr.put("recordType", e.getRecordType());
                    attr.put("personId", e.getPersonId());
                    attr.put("personName", e.getPersonName());
                    attr.put("sex", e.getSex());
                    attr.put("age", e.getAge());
                    attr.put("mobile", e.getMobile());
                    attr.put("openId", e.getOpenId());
                    attr.put("siteId", e.getSiteId());
                    attr.put("siteName", e.getSiteName());
                    attr.put("recordStatus", e.getRecordStatus());
                    attr.put("productId", e.getProductId());
                    attr.put("productName", e.getProductName());
                    attr.put("productBatch", e.getProductBatch());
                    attr.put("manufacturer", e.getManufacturer());
                    attr.put("appointmentTime", e.getAppointmentTime());
                    attr.put("imageUrl", e.getImageUrl());
                    attr.put("msg", e.getMsg());
                    attr.put("createTime", e.getCreateTime());
                    return attr;
                }
        );
    }

    @Override
    public void downloadTemplateExcel(HttpServletResponse response){
        //1.查询到该对象的导入模板
        /*OrderRecord orderRecord = orderRecordMapper.selectByExampleOne()
                .where(OrderRecordDynamicSqlSupport.fileKey, isEqualTo("orderRecordTemplateExcel"))
                .build()
                .execute();
        if(ObjUtil.isEmpty(orderRecord)){
            throw new IllegalArgumentException("模板文件暂未上传,请联系管理员");
        }
        //2.响应文件到页面
        minioUtil.download(orderRecord.getStorePath(),orderRecord.getName(),response);*/
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
        OrderRecordExcelListener orderRecordExcelListener = new OrderRecordExcelListener(this,validator,fieldDupValidator);
        try {
            in = excelFile.getInputStream();
            EasyExcel.read(in, OrderRecordExcelVo.class, orderRecordExcelListener).sheet().doRead();
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
    public void exportToExcel(OrderRecordPageReqDto orderRecordPageReqDto, HttpServletResponse response){
        //1.查询列表
        List<OrderRecord> orderRecordList = queryListByPageReqDto(orderRecordPageReqDto,false);

        //2.将原始列表转为导出对象列表
        List<OrderRecordExcelVo> orderRecordExcelVoList = orderRecordList.stream().map(e -> {
            OrderRecordExcelVo orderRecordExcelVo = VoPoConverterUtil.copyProperties(e, OrderRecordExcelVo.class);
            return orderRecordExcelVo;
        }).collect(Collectors.toList());

        //3.获取到输出流
        OutputStream outputStream = null;
        try {
            //(1).文件名，表名，表头，格式
            String sheetName = "商品秒杀";
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
                .head(OrderRecordExcelVo.class)
                .sheet("Sheet1")
                .doWrite(orderRecordExcelVoList);
    }

    private List<OrderRecord> queryListByPageReqDto(OrderRecordPageReqDto orderRecordPageReqDto, Boolean needPaging){
        //1.查询语句构建
        QueryExpressionDSL<MyBatis3SelectModelAdapter<List<OrderRecord>>>.QueryExpressionWhereBuilder builder = orderRecordMapper.selectByExample().where();

        //2.根据查询条件封装查询命令
        //(1).关键字查询
        /*String name = orderRecordPageReqDto.getName();
        if(!ObjUtil.isEmpty(name)){
        builder.and(OrderRecordDynamicSqlSupport.name, isLike("%"+name+"%"));
        }

        Long type = orderRecordPageReqDto.getType();
        if(!ObjUtil.isEmpty(type)){
        builder.and(OrderRecordDynamicSqlSupport.type, isEqualTo(type));
        }

        Long startTime = orderRecordPageReqDto.getStartTime();
        Long endTime = orderRecordPageReqDto.getEndTime();
        if (startTime != null && endTime != null) {
        builder.and(OrderRecordDynamicSqlSupport.startTime, isGreaterThanOrEqualTo(startTime));
        builder.and(OrderRecordDynamicSqlSupport.endTime, isLessThanOrEqualTo(endTime));
        } else {
            if (startTime != null) {
            builder.and(OrderRecordDynamicSqlSupport.startTime, isGreaterThanOrEqualTo(startTime));
            }
            if (endTime != null) {
            builder.and(OrderRecordDynamicSqlSupport.endTime, isLessThanOrEqualTo(endTime));
            }
        }*/

        //3.排序
        builder.orderBy(OrderRecordDynamicSqlSupport.createTime.descending());

        //4.查询(不需要分页即列表)
        if(needPaging){
        PageHelper.startPage(orderRecordPageReqDto.getPageNum(), orderRecordPageReqDto.getPageSize());
        }

        List<OrderRecord> list = builder.build().execute();
        return list;
    }
}