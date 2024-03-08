package com.jsxa.vapp.inventory.service.impl;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.jsxa.vapp.common.annotation.SeataExp;
import com.jsxa.vapp.common.bo.vo.PageVo;
import com.jsxa.vapp.common.redis.RedisKey;
import com.jsxa.vapp.common.redis.RedisService;
import com.jsxa.vapp.inventory.bo.dto.UpdateVaccineReleaseStatusReqDto;
import com.jsxa.vapp.inventory.bo.dto.VaccineReleaseReqDto;
import com.jsxa.vapp.inventory.bo.dto.VaccineReleasePageReqDto;
import com.jsxa.vapp.inventory.bo.po.TimeTask;
import com.jsxa.vapp.inventory.bo.po.VaccineRelease;
import com.jsxa.vapp.inventory.mapper.TimeTaskDynamicSqlSupport;
import com.jsxa.vapp.inventory.mapper.TimeTaskMapper;
import com.jsxa.vapp.inventory.mapper.VaccineReleaseMapper;
import com.jsxa.vapp.inventory.mapper.VaccineReleaseDynamicSqlSupport;
import com.jsxa.vapp.inventory.excel.VaccineReleaseExcelListener;
import com.jsxa.vapp.inventory.excel.VaccineReleaseExcelVo;
import com.jsxa.vapp.common.utils.*;
import com.jsxa.vapp.common.redis.RedisService;
import com.jsxa.vapp.common.redis.RedisKey;
import com.jsxa.vapp.common.validator.FieldDupValidator;
import com.jsxa.vapp.inventory.service.VaccineReleaseService;
import com.jsxa.vapp.inventory.util.XxlJobUtil;
import io.seata.core.context.RootContext;
import io.seata.core.model.BranchType;
import io.seata.spring.annotation.GlobalTransactional;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.springframework.beans.factory.annotation.Value;
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
import org.apache.shardingsphere.sql.parser.mysql.parser.MySQLLexer;


import javax.annotation.Resource;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

/**
 * @Author zhangyong
 * @Description //VaccineReleaseService接口实现类
 * @Date 2024/02/27 15:03
 * @Param
 * @return
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class VaccineReleaseServiceImpl implements VaccineReleaseService {

    @Value("${xxl.job.admin.addresses}")
    private String adminAddresses;

    private final RedisService redisService;

    private final Validator validator;

    private final FieldDupValidator fieldDupValidator;

    private final VaccineReleaseMapper vaccineReleaseMapper;

    private final TimeTaskMapper timeTaskMapper;


    @Override
    @Transactional
    public Map<String, Object> addVaccineRelease(VaccineReleaseReqDto vaccineReleaseReqDto) {

        //1.参数校验

        //2.设置参数
        //(1)复制VaccineReleaseReqDto中的请求参数到VaccineRelease
        VaccineRelease vaccineRelease = VoPoConverterUtil.copyProperties(vaccineReleaseReqDto, VaccineRelease.class);

        //(2)设置其他属性
        vaccineRelease
                .setId(new IdWorker().nextId())
                .setDockAmount(vaccineReleaseReqDto.getAmount())
                .setVersion(0)
                .setStatus((byte)1)
                .setCreateTime(System.currentTimeMillis());

        //3.保存
        vaccineReleaseMapper.insert(vaccineRelease);

        //4.返回结果
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("msg","添加疫苗发放信息成功");
        return resultMap;
    }

    @Override
    @Transactional
    public Map<String, Object> deleteVaccineReleaseById(Long id){
        VaccineRelease vaccineRelease = vaccineReleaseMapper.selectByPrimaryKey(id);
        if(vaccineRelease == null){
            throw new IllegalArgumentException("id为:"+id+"的疫苗发放信息不存在");
        }

        vaccineReleaseMapper.deleteByExample()
                    .where(VaccineReleaseDynamicSqlSupport.id, isEqualTo(id))
                    .build()
                    .execute();

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg","删除疫苗发放成功");
        return resultMap;
    }

    @Override
    @Transactional
    public Map<String, Object> updateVaccineRelease(VaccineReleaseReqDto vaccineReleaseReqDto) {
        //1.参数校验

        //2.判断vaccineRelease是否存在
        VaccineRelease vaccineRelease = vaccineReleaseMapper.selectByPrimaryKey(vaccineReleaseReqDto.getId());
        if(ObjUtil.isEmpty(vaccineRelease)){
            throw new IllegalArgumentException("id为:"+vaccineReleaseReqDto.getId()+"的疫苗发放不存在");
        }

        //3.更新VaccineRelease
        //(1)复制VaccineReleaseDto中的请求参数到VaccineRelease
        VoPoConverterUtil.beanConverterNotNull(vaccineReleaseReqDto, vaccineRelease);

        //4.保存
        vaccineReleaseMapper.updateByPrimaryKey(vaccineRelease);

        //5.返回结果
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("msg","更新疫苗发放信息成功");
        return resultMap;
    }

        @Override
        @Transactional
        public Map<String, Object> insertOrUpdateVaccineRelease(VaccineReleaseReqDto vaccineReleaseReqDto) {
            //1.参数校验

            //2.判断vaccineRelease是否存在
            VaccineRelease vaccineRelease = vaccineReleaseMapper.selectByPrimaryKey(vaccineReleaseReqDto.getId());
            if(ObjUtil.isEmpty(vaccineRelease)){
                throw new IllegalArgumentException("id为:"+vaccineReleaseReqDto.getId()+"的疫苗发放不存在");
            }

            //3.更新VaccineRelease
            //(1)复制VaccineReleaseDto中的请求参数到VaccineRelease
            VoPoConverterUtil.beanConverterNotNull(vaccineReleaseReqDto, vaccineRelease);

            //4.保存
            vaccineReleaseMapper.updateByPrimaryKey(vaccineRelease);

            //5.返回结果
            Map<String,Object> resultMap = new HashMap<>();
            resultMap.put("msg","更新疫苗发放信息成功");
            return resultMap;
        }

    @Override
    public Map<String, Object> getVaccineReleaseById(Long id){
        VaccineRelease e = vaccineReleaseMapper.selectByPrimaryKey(id);
        if(ObjUtil.isEmpty(e)){
            throw new IllegalArgumentException("id为:"+id+"的疫苗发放信息不存在");
        }

        Map<String, Object> attr = new HashMap<>();
        attr.put("id", e.getId());
        attr.put("name", e.getName());
        attr.put("siteId", e.getSiteId());
        attr.put("siteName", e.getSiteName());
        attr.put("dose", e.getDose());
        attr.put("amount", e.getAmount());
        attr.put("startTime", e.getStartTime());
        attr.put("timePeriod", e.getTimePeriod());
        attr.put("timePeriodName", e.getTimePeriodName());
        attr.put("contactName", e.getContactName());
        attr.put("contactMobile", e.getContactMobile());
        attr.put("status", e.getStatus());
        attr.put("createTime", e.getCreateTime());
        return attr;
    }

    @Override
    public PageVo<Map<String, Object>> getVaccineReleaseListPageVo(VaccineReleasePageReqDto vaccineReleasePageReqDto){

        //1.查询列表
        List<VaccineRelease> list = queryListByPageReqDto(vaccineReleasePageReqDto,true);

        //2.构建pageVo
        PageVo<VaccineRelease> pageVo = new PageVo<>(list);

        //3.对象转为map分页输出
        return PageVo.of(
                pageVo,
                e -> {
                    Map<String, Object> attr = new HashMap<>();
                    attr.put("id", e.getId());
                    attr.put("name", e.getName());
                    attr.put("siteId", e.getSiteId());
                    attr.put("siteName", e.getSiteName());
                    attr.put("dose", e.getDose());
                    attr.put("amount", e.getAmount());
                    attr.put("startTime", e.getStartTime());
                    attr.put("timePeriod", e.getTimePeriod());
                    attr.put("timePeriodName", e.getTimePeriodName());
                    attr.put("contactName", e.getContactName());
                    attr.put("contactMobile", e.getContactMobile());
                    attr.put("status", e.getStatus());
                    attr.put("createTime", e.getCreateTime());
                    return attr;
                }
        );
    }

    @Override
    public void downloadTemplateExcel(HttpServletResponse response){
        //1.查询到该对象的导入模板
        /*VaccineRelease vaccineRelease = vaccineReleaseMapper.selectByExampleOne()
                .where(VaccineReleaseDynamicSqlSupport.fileKey, isEqualTo("vaccineReleaseTemplateExcel"))
                .build()
                .execute();
        if(ObjUtil.isEmpty(vaccineRelease)){
            throw new IllegalArgumentException("模板文件暂未上传,请联系管理员");
        }
        //2.响应文件到页面
        minioUtil.download(vaccineRelease.getStorePath(),vaccineRelease.getName(),response);*/
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
        VaccineReleaseExcelListener vaccineReleaseExcelListener = new VaccineReleaseExcelListener(this,validator,fieldDupValidator);
        try {
            in = excelFile.getInputStream();
            EasyExcel.read(in, VaccineReleaseExcelVo.class, vaccineReleaseExcelListener).sheet().doRead();
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
    public void exportToExcel(VaccineReleasePageReqDto vaccineReleasePageReqDto,HttpServletResponse response){
        //1.查询列表
        List<VaccineRelease> vaccineReleaseList = queryListByPageReqDto(vaccineReleasePageReqDto,false);

        //2.将原始列表转为导出对象列表
        List<VaccineReleaseExcelVo> vaccineReleaseExcelVoList = vaccineReleaseList.stream().map(e -> {
            VaccineReleaseExcelVo vaccineReleaseExcelVo = VoPoConverterUtil.copyProperties(e, VaccineReleaseExcelVo.class);
            return vaccineReleaseExcelVo;
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
                .head(VaccineReleaseExcelVo.class)
                .sheet("Sheet1")
                .doWrite(vaccineReleaseExcelVoList);
    }

    @Override
    public Map<String, Object> updateStatus(UpdateVaccineReleaseStatusReqDto updateVaccineReleaseStatusReqDto) {
        Long id = updateVaccineReleaseStatusReqDto.getId();
        VaccineRelease e = vaccineReleaseMapper.selectByPrimaryKey(id);
        if(ObjUtil.isEmpty(e)){
            throw new IllegalArgumentException("id为:"+id+"的疫苗发放信息不存在");
        }


        Byte curStatus = e.getStatus();
        Byte status = updateVaccineReleaseStatusReqDto.getStatus();
        if(!status.equals(curStatus)){
            if(status == 1){
                //1.每次只允许有一个放苗活动开启(status=1)，直到完成(status变为2)或停止(status变为0)后才能启用新的放苗活动
                VaccineRelease release = vaccineReleaseMapper.selectByExampleOne()
                        .where(VaccineReleaseDynamicSqlSupport.status, isEqualTo(status))
                        .build()
                        .execute();
                if(!ObjUtil.isEmpty(release)){
                    throw new IllegalArgumentException("名称为:"+release.getName()+"的疫苗发放信息正在抢苗中，不能启用新的放苗活动");
                }

                //1.向xxl-job-admin新增定时任务-执行修改状态为 启用
                Map<String, Object> paramMap = new HashMap<>();
                paramMap.put("vaccineReleaseId", id);
                String paramJsonStr = JSONUtil.toJsonStr(paramMap);
                String startTimeDateStr = DateUtil.timeStamp2dateStr(e.getStartTime());
                String rtCron = DateUtil.dateStrConvertCronExpression(startTimeDateStr, null);

                //--2.执行下发
                TimeTask exitstVaccineReleaseTimeTask = timeTaskMapper.selectByExampleOne()
                        .where(TimeTaskDynamicSqlSupport.projectId, isEqualTo(id))
                        .and(TimeTaskDynamicSqlSupport.type, isEqualTo(1902L))
                        .build()
                        .execute();
                if(ObjUtil.isEmpty(exitstVaccineReleaseTimeTask)){
                    //--1.执行下发
                    String jobId = XxlJobUtil.addJob(adminAddresses, rtCron, "updateVaccineReleaseStatusByRtJobHandler", paramJsonStr,e.getName()+"("+id+")_开启放苗后抢苗开始定时任务","inventory-service");
                    //--2.构建TimeTask
                    TimeTask vaccineReleaseTimeTask = TimeTask.builder()
                            .type(1902L)
                            .projectId(id)
                            .projectName(e.getName())
                            .jobId(jobId)
                            .xxlJobAdminAddress(adminAddresses)
                            .corn(rtCron)
                            .handler("vaccineRelease")
                            .appName("inventory-service")
                            .createTime(System.currentTimeMillis())
                            .build();
                    timeTaskMapper.insert(vaccineReleaseTimeTask);
                }

                //(1).更新状态
                vaccineReleaseMapper.update(update(VaccineReleaseDynamicSqlSupport.vaccineRelease)
                        .set(VaccineReleaseDynamicSqlSupport.status).equalToWhenPresent(status)
                        .where(VaccineReleaseDynamicSqlSupport.id, isEqualTo(id))
                        .build()
                        .render(RenderingStrategies.MYBATIS3));

                //(2).将上线的放苗活动存入redis
                e.setStatus((byte)1);
                redisService.hmSet("VaccineRelease", String.valueOf(e.getId()), JSONObject.toJSONString(e));

            }
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg", "放苗成功");
        return resultMap;
    }


    /**
     * 库存扣减
     * 库存扣减和orderServer中的创建订单是一组全局事务,要使用seata全局事务需要做以下二选一(此处选择方式(2)):
     * (1).在reduceDock方法上打上自定义注解@SeataExp，代码量少但逻辑更隐蔽(具体原因可参见com.jsxa.vapp.common.aop.SeataGlobalTransactionalAspect)
     * (2).在事务发起方即OrderService中处理微服务调用的结果，代码量增多但逻辑更清晰(具体原因可参见com.jsxa.vapp.common.aop.SeataGlobalTransactionalAspect)
     */
    //@SeataExp
    @Override
    public Map<String, Object> reduceDock(Long vaccineReleaseId) {
        //获取当前事务的XID
        String xid = RootContext.getXID();
        if(ObjUtil.isEmpty(xid)){
            log.info("xid为空");
        }
        //BranchType branchType = RootContext.getBranchType();
        //String branchTypeName = RootContext.getBranchType();
        log.info("Inventory--------------> threadId:{},threadName:{},Seata_XID:{}",Thread.currentThread().getId(),Thread.currentThread().getName(),xid);

        //1.获取到疫苗发放活动信息
        VaccineRelease vaccineRelease = vaccineReleaseMapper.selectByPrimaryKey(vaccineReleaseId);
        if(ObjUtil.isEmpty(vaccineRelease)){
            throw new IllegalArgumentException("id为:"+vaccineRelease+"的疫苗发放信息不存在");
        }

        //2.获取到扣减库存前的版本号
        Integer version = vaccineRelease.getVersion();

        //3.采用乐观锁即版本(version)的方式再次防止超卖(sql详见reduceDock()方法)，使用版本version机制，需要在乐观锁控制的数据库表中增加一个字段 versison，字段类型使用int,
        //  原理是在提交更新的时候检查当前数据库中数据的版本号与自己更新前第一次获取到的版本号进行对比，如果一致则OK，
        //  否则就是版本冲突，此时采用重试机制保证最终更新成功(重试机制步骤:间隔1秒更新3次，如果还是失败则放入rocketmq延迟队列去更新，直到最终更新成功)
        vaccineReleaseMapper.reduceDock(vaccineReleaseId, 1, version);
        //vaccineReleaseMapper.reduceDockWithNoVersion(vaccineReleaseId, 1);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("code", 200);
        resultMap.put("msg", "库存扣减成功");
        return resultMap;
    }

    private List<VaccineRelease> queryListByPageReqDto(VaccineReleasePageReqDto vaccineReleasePageReqDto,Boolean needPaging){
        //1.查询语句构建
        QueryExpressionDSL<MyBatis3SelectModelAdapter<List<VaccineRelease>>>.QueryExpressionWhereBuilder builder = vaccineReleaseMapper.selectByExample().where();

        //2.根据查询条件封装查询命令
        //(1).关键字查询
        /*String name = vaccineReleasePageReqDto.getName();
        if(!ObjUtil.isEmpty(name)){
        builder.and(VaccineReleaseDynamicSqlSupport.name, isLike("%"+name+"%"));
        }

        Long type = vaccineReleasePageReqDto.getType();
        if(!ObjUtil.isEmpty(type)){
        builder.and(VaccineReleaseDynamicSqlSupport.type, isEqualTo(type));
        }

        Long startTime = vaccineReleasePageReqDto.getStartTime();
        Long endTime = vaccineReleasePageReqDto.getEndTime();
        if (startTime != null && endTime != null) {
        builder.and(VaccineReleaseDynamicSqlSupport.startTime, isGreaterThanOrEqualTo(startTime));
        builder.and(VaccineReleaseDynamicSqlSupport.endTime, isLessThanOrEqualTo(endTime));
        } else {
            if (startTime != null) {
            builder.and(VaccineReleaseDynamicSqlSupport.startTime, isGreaterThanOrEqualTo(startTime));
            }
            if (endTime != null) {
            builder.and(VaccineReleaseDynamicSqlSupport.endTime, isLessThanOrEqualTo(endTime));
            }
        }*/

        //3.排序
        builder.orderBy(VaccineReleaseDynamicSqlSupport.createTime.descending());

        //4.查询(不需要分页即列表)
        if(needPaging){
        PageHelper.startPage(vaccineReleasePageReqDto.getPageNum(), vaccineReleasePageReqDto.getPageSize());
        }

        List<VaccineRelease> list = builder.build().execute();
        return list;
    }
}