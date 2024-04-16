package com.zy.seckill.inventory.service.impl;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.zy.seckill.common.bo.vo.PageVo;
import com.zy.seckill.common.redis.RedisService;
import com.zy.seckill.inventory.bo.dto.UpdateProductReleaseStatusReqDto;
import com.zy.seckill.inventory.bo.dto.ProductReleaseReqDto;
import com.zy.seckill.inventory.bo.dto.ProductReleasePageReqDto;
import com.zy.seckill.inventory.bo.po.ProductRelease;
import com.zy.seckill.inventory.bo.po.TimeTask;
import com.zy.seckill.inventory.mapper.TimeTaskDynamicSqlSupport;
import com.zy.seckill.inventory.mapper.TimeTaskMapper;
import com.zy.seckill.inventory.mapper.ProductReleaseMapper;
import com.zy.seckill.inventory.mapper.ProductReleaseDynamicSqlSupport;
import com.zy.seckill.inventory.excel.ProductReleaseExcelListener;
import com.zy.seckill.inventory.excel.ProductReleaseExcelVo;
import com.zy.seckill.common.utils.*;
import com.zy.seckill.common.validator.FieldDupValidator;
import com.zy.seckill.inventory.service.ProductReleaseService;
import com.zy.seckill.inventory.util.XxlJobUtil;
import io.seata.core.context.RootContext;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
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


import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

/**
 * @Author zhangyong
 * @Description //ProductReleaseService接口实现类
 * @Date xxxx/02/27 15:03
 * @Param
 * @return
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ProductReleaseServiceImpl implements ProductReleaseService {

    @Value("${xxl.job.admin.addresses}")
    private String adminAddresses;

    private final RedisService redisService;

    private final Validator validator;

    private final FieldDupValidator fieldDupValidator;

    private final ProductReleaseMapper productReleaseMapper;

    private final TimeTaskMapper timeTaskMapper;

    private final RocketMQTemplate rocketMQTemplate;


    @Override
    @Transactional
    public Map<String, Object> addProductRelease(ProductReleaseReqDto productReleaseReqDto) {

        //1.参数校验

        //2.设置参数
        //(1)复制ProductReleaseReqDto中的请求参数到ProductRelease
        ProductRelease productRelease = VoPoConverterUtil.copyProperties(productReleaseReqDto, ProductRelease.class);

        //(2)设置其他属性
        productRelease
                .setId(new IdWorker().nextId())
                .setDockAmount(productReleaseReqDto.getAmount())
                .setVersion(0)
                .setStatus((byte)1)
                .setCreateTime(System.currentTimeMillis());

        //3.保存
        productReleaseMapper.insert(productRelease);

        //4.返回结果
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("msg","添加疫苗发放信息成功");
        return resultMap;
    }

    @Override
    @Transactional
    public Map<String, Object> deleteProductReleaseById(Long id){
        ProductRelease productRelease = productReleaseMapper.selectByPrimaryKey(id);
        if(productRelease == null){
            throw new IllegalArgumentException("id为:"+id+"的疫苗发放信息不存在");
        }

        productReleaseMapper.deleteByExample()
                    .where(ProductReleaseDynamicSqlSupport.id, isEqualTo(id))
                    .build()
                    .execute();

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg","删除疫苗发放成功");
        return resultMap;
    }

    @Override
    @Transactional
    public Map<String, Object> updateProductRelease(ProductReleaseReqDto productReleaseReqDto) {
        //1.参数校验

        //2.判断ProductRelease是否存在
        ProductRelease productRelease = productReleaseMapper.selectByPrimaryKey(productReleaseReqDto.getId());
        if(ObjUtil.isEmpty(productRelease)){
            throw new IllegalArgumentException("id为:"+ productReleaseReqDto.getId()+"的疫苗发放不存在");
        }

        //3.更新ProductRelease
        //(1)复制ProductReleaseDto中的请求参数到ProductRelease
        VoPoConverterUtil.beanConverterNotNull(productReleaseReqDto, productRelease);

        //4.保存
        productReleaseMapper.updateByPrimaryKey(productRelease);

        //5.返回结果
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("msg","更新疫苗发放信息成功");
        return resultMap;
    }

        @Override
        @Transactional
        public Map<String, Object> insertOrUpdateProductRelease(ProductReleaseReqDto productReleaseReqDto) {
            //1.参数校验

            //2.判断ProductRelease是否存在
            ProductRelease productRelease = productReleaseMapper.selectByPrimaryKey(productReleaseReqDto.getId());
            if(ObjUtil.isEmpty(productRelease)){
                throw new IllegalArgumentException("id为:"+ productReleaseReqDto.getId()+"的疫苗发放不存在");
            }

            //3.更新ProductRelease
            //(1)复制ProductReleaseDto中的请求参数到ProductRelease
            VoPoConverterUtil.beanConverterNotNull(productReleaseReqDto, productRelease);

            //4.保存
            productReleaseMapper.updateByPrimaryKey(productRelease);

            //5.返回结果
            Map<String,Object> resultMap = new HashMap<>();
            resultMap.put("msg","更新疫苗发放信息成功");
            return resultMap;
        }

    @Override
    public Map<String, Object> getProductReleaseById(Long id){
        ProductRelease e = productReleaseMapper.selectByPrimaryKey(id);
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
        attr.put("contactName", e.getContactName());
        attr.put("contactMobile", e.getContactMobile());
        attr.put("status", e.getStatus());
        attr.put("createTime", e.getCreateTime());
        return attr;
    }

    @Override
    public PageVo<Map<String, Object>> getProductReleaseListPageVo(ProductReleasePageReqDto productReleasePageReqDto){

        //1.查询列表
        List<ProductRelease> list = queryListByPageReqDto(productReleasePageReqDto,true);

        //2.构建pageVo
        PageVo<ProductRelease> pageVo = new PageVo<>(list);

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
        /*ProductRelease ProductRelease = ProductReleaseMapper.selectByExampleOne()
                .where(ProductReleaseDynamicSqlSupport.fileKey, isEqualTo("ProductReleaseTemplateExcel"))
                .build()
                .execute();
        if(ObjUtil.isEmpty(ProductRelease)){
            throw new IllegalArgumentException("模板文件暂未上传,请联系管理员");
        }
        //2.响应文件到页面
        minioUtil.download(ProductRelease.getStorePath(),ProductRelease.getName(),response);*/
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
        ProductReleaseExcelListener productReleaseExcelListener = new ProductReleaseExcelListener(this,validator,fieldDupValidator);
        try {
            in = excelFile.getInputStream();
            EasyExcel.read(in, ProductReleaseExcelVo.class, productReleaseExcelListener).sheet().doRead();
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
    public void exportToExcel(ProductReleasePageReqDto productReleasePageReqDto, HttpServletResponse response){
        //1.查询列表
        List<ProductRelease> productReleaseList = queryListByPageReqDto(productReleasePageReqDto,false);

        //2.将原始列表转为导出对象列表
        List<ProductReleaseExcelVo> productReleaseExcelVoList = productReleaseList.stream().map(e -> {
            ProductReleaseExcelVo productReleaseExcelVo = VoPoConverterUtil.copyProperties(e, ProductReleaseExcelVo.class);
            return productReleaseExcelVo;
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
                .head(ProductReleaseExcelVo.class)
                .sheet("Sheet1")
                .doWrite(productReleaseExcelVoList);
    }

    @Override
    public Map<String, Object> updateStatus(UpdateProductReleaseStatusReqDto updateProductReleaseStatusReqDto) {
        Long id = updateProductReleaseStatusReqDto.getId();
        ProductRelease e = productReleaseMapper.selectByPrimaryKey(id);
        if(ObjUtil.isEmpty(e)){
            throw new IllegalArgumentException("id为:"+id+"的疫苗发放信息不存在");
        }


        Byte curStatus = e.getStatus();
        Byte status = updateProductReleaseStatusReqDto.getStatus();
        if(!status.equals(curStatus)){
            if(status == 1){
                //1.每次只允许有一个放苗活动开启(status=1)，直到完成(status变为2)或停止(status变为0)后才能启用新的放苗活动
                ProductRelease release = productReleaseMapper.selectByExampleOne()
                        .where(ProductReleaseDynamicSqlSupport.status, isEqualTo(status))
                        .build()
                        .execute();
                if(!ObjUtil.isEmpty(release)){
                    throw new IllegalArgumentException("名称为:"+release.getName()+"的疫苗发放信息正在抢苗中，不能启用新的放苗活动");
                }

                //1.向xxl-job-admin新增定时任务-执行修改状态为 启用
                Map<String, Object> paramMap = new HashMap<>();
                paramMap.put("ProductReleaseId", id);
                String paramJsonStr = JSONUtil.toJsonStr(paramMap);
                String startTimeDateStr = DateUtil.timeStamp2dateStr(e.getStartTime());
                String rtCron = DateUtil.dateStrConvertCronExpression(startTimeDateStr, null);

                //--2.执行下发
                TimeTask exitstProductReleaseTimeTask = timeTaskMapper.selectByExampleOne()
                        .where(TimeTaskDynamicSqlSupport.projectId, isEqualTo(id))
                        .and(TimeTaskDynamicSqlSupport.type, isEqualTo(1902L))
                        .build()
                        .execute();
                if(ObjUtil.isEmpty(exitstProductReleaseTimeTask)){
                    //--1.执行下发
                    String jobId = XxlJobUtil.addJob(adminAddresses, rtCron, "updateProductReleaseStatusByRtJobHandler", paramJsonStr,e.getName()+"("+id+")_开启放苗后抢苗开始定时任务","inventory-service");
                    //--2.构建TimeTask
                    TimeTask ProductReleaseTimeTask = TimeTask.builder()
                            .type(1902L)
                            .projectId(id)
                            .projectName(e.getName())
                            .jobId(jobId)
                            .xxlJobAdminAddress(adminAddresses)
                            .corn(rtCron)
                            .handler("ProductRelease")
                            .appName("inventory-service")
                            .createTime(System.currentTimeMillis())
                            .build();
                    timeTaskMapper.insert(ProductReleaseTimeTask);
                }

                //(1).更新状态
                productReleaseMapper.update(update(ProductReleaseDynamicSqlSupport.productRelease)
                        .set(ProductReleaseDynamicSqlSupport.status).equalToWhenPresent(status)
                        .where(ProductReleaseDynamicSqlSupport.id, isEqualTo(id))
                        .build()
                        .render(RenderingStrategies.MYBATIS3));

                //(2).将上线的放苗活动存入redis
                e.setStatus((byte)1);
                redisService.hmSet("ProductRelease", String.valueOf(e.getId()), JSONObject.toJSONString(e));

            }
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg", "放苗成功");
        return resultMap;
    }


    /**
     * 库存扣减
     * 库存扣减和orderServer中的创建订单是一组全局事务,要使用shardingjdbc+seata全局事务需要做以下2步:
     * 1.被调用的事务方法加本地事务注解@Transactional(rollbackFor = Exception.class)
     * 2.被调用的事务出现异常以下二选一(此处选择方式(2)):
     * (1).在reduceDock方法上打上自定义注解@SeataExp，代码量少但逻辑更隐蔽(具体原因可参见com.zy.seckill.common.aop.SeataGlobalTransactionalAspect)
     * (2).在事务发起方即OrderService中处理微服务调用的结果，代码量增多但逻辑更清晰(具体原因可参见com.zy.seckill.common.aop.SeataGlobalTransactionalAspect)
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Map<String, Object> reduceDock(Long productReleaseId) {
        //获取当前事务的XID
        String xid = RootContext.getXID();
        log.info("Inventory--------------> threadId:{},threadName:{},Seata_XID:{}",Thread.currentThread().getId(),Thread.currentThread().getName(),xid);


        //1.获取到扣减库存前的版本号
        int version = productReleaseMapper.selectVersionNum(productReleaseId);


        //2.采用乐观锁即版本(version)的方式再次防止超卖(sql详见reduceDock()方法)，使用版本version机制，需要在乐观锁控制的数据库表中增加一个字段 versison，字段类型使用int,
        //  原理是在提交更新的时候检查当前数据库中数据的版本号与自己更新前第一次获取到的版本号进行对比，如果一致则OK，
        //  否则就是版本冲突，此时采用重试机制保证最终更新成功(重试机制步骤:重新查询版本号执行一次，如果还是失败则放入rocketmq延迟队列去更新，直到最终更新成功)
        //   其实上面的乐观锁算法也叫CAS（比较与交换，Compare and swap）是一种无锁算法，比独占锁如Mysql中的lock或者Java中的synchronized性能更高
        int result = productReleaseMapper.reduceDock(1,productReleaseId, version);
        if(result == 0){
            //(1).更新不成功，记录的版本号有问题，说明有其他线程修改了版本好，重新查询一次
            int versionAgain = productReleaseMapper.selectVersionNum(productReleaseId);
            //(2).再次执行扣减
            int resultAgain = productReleaseMapper.reduceDock(1,productReleaseId, versionAgain);
            //(3).如果还不成功,发送给mq处理(mq自身有重试机制),防止更新丢失
            if(resultAgain == 0){
                Map<String,Object> stockMap = new HashMap<>();
                stockMap.put("productReleaseId",productReleaseId);
                stockMap.put("amount",1);
                try {
                    SendResult sendResult = rocketMQTemplate.syncSend("product_stock", stockMap);
                    if(!sendResult.getSendStatus().equals(SendStatus.SEND_OK)){
                        throw new IllegalArgumentException("服务异常，请稍后再试!!");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    throw new IllegalArgumentException("服务异常，请稍后再试!!!");
                }
            }
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("code", 200);
        resultMap.put("msg", "库存扣减成功");
        return resultMap;
    }

    private List<ProductRelease> queryListByPageReqDto(ProductReleasePageReqDto productReleasePageReqDto, Boolean needPaging){
        //1.查询语句构建
        QueryExpressionDSL<MyBatis3SelectModelAdapter<List<ProductRelease>>>.QueryExpressionWhereBuilder builder = productReleaseMapper.selectByExample().where();

        //2.根据查询条件封装查询命令
        //(1).关键字查询
        /*String name = ProductReleasePageReqDto.getName();
        if(!ObjUtil.isEmpty(name)){
        builder.and(ProductReleaseDynamicSqlSupport.name, isLike("%"+name+"%"));
        }

        Long type = ProductReleasePageReqDto.getType();
        if(!ObjUtil.isEmpty(type)){
        builder.and(ProductReleaseDynamicSqlSupport.type, isEqualTo(type));
        }

        Long startTime = ProductReleasePageReqDto.getStartTime();
        Long endTime = ProductReleasePageReqDto.getEndTime();
        if (startTime != null && endTime != null) {
        builder.and(ProductReleaseDynamicSqlSupport.startTime, isGreaterThanOrEqualTo(startTime));
        builder.and(ProductReleaseDynamicSqlSupport.endTime, isLessThanOrEqualTo(endTime));
        } else {
            if (startTime != null) {
            builder.and(ProductReleaseDynamicSqlSupport.startTime, isGreaterThanOrEqualTo(startTime));
            }
            if (endTime != null) {
            builder.and(ProductReleaseDynamicSqlSupport.endTime, isLessThanOrEqualTo(endTime));
            }
        }*/

        //3.排序
        builder.orderBy(ProductReleaseDynamicSqlSupport.createTime.descending());

        //4.查询(不需要分页即列表)
        if(needPaging){
        PageHelper.startPage(productReleasePageReqDto.getPageNum(), productReleasePageReqDto.getPageSize());
        }

        List<ProductRelease> list = builder.build().execute();
        return list;
    }
}