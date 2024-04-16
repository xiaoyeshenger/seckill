package com.zy.seckill.inventory.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.github.pagehelper.PageHelper;
import com.zy.seckill.inventory.bo.dto.TimeTaskPageReqDto;
import com.zy.seckill.inventory.bo.dto.TimeTaskReqDto;
import com.zy.seckill.inventory.bo.po.TimeTask;
import com.zy.seckill.common.bo.vo.PageVo;
import com.zy.seckill.common.utils.IdWorker;
import com.zy.seckill.common.utils.MinioUtil;
import com.zy.seckill.common.utils.ObjUtil;
import com.zy.seckill.common.utils.VoPoConverterUtil;
import com.zy.seckill.common.validator.FieldDupValidator;
import com.zy.seckill.inventory.excel.TimeTaskExcelListener;
import com.zy.seckill.inventory.excel.TimeTaskExcelVo;
import com.zy.seckill.inventory.mapper.TimeTaskDynamicSqlSupport;
import com.zy.seckill.inventory.mapper.TimeTaskMapper;
import com.zy.seckill.inventory.service.TimeTaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.dynamic.sql.select.MyBatis3SelectModelAdapter;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

/**
 * @Author zhangyong
 * @Description //TimeTaskService接口实现类
 * @Date 2023/12/27 17:02
 * @Param
 * @return
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TimeTaskServiceImpl implements TimeTaskService {

    private final MinioUtil minioUtil;

    private final Validator validator;

    private final FieldDupValidator fieldDupValidator;

    private final TimeTaskMapper timeTaskMapper;


    @Override
    @Transactional
    public Map<String, Object> addTimeTask(TimeTaskReqDto timeTaskReqDto) {

        //1.参数校验

        //2.设置参数
        //(1)复制TimeTaskReqDto中的请求参数到TimeTask
        TimeTask timeTask = VoPoConverterUtil.copyProperties(timeTaskReqDto, TimeTask.class);

        //(2)设置其他属性
        timeTask.setId(new IdWorker().nextId()).setCreateTime(System.currentTimeMillis());

        //3.保存
        timeTaskMapper.insert(timeTask);

        //4.返回结果
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("msg","添加定时任务信息成功");
        return resultMap;
    }

    @Override
    @Transactional
    public Map<String, Object> deleteTimeTaskById(Long id){
        TimeTask timeTask = timeTaskMapper.selectByPrimaryKey(id);
        if(timeTask == null){
            throw new IllegalArgumentException("id为:"+id+"的定时任务信息不存在");
        }

        timeTaskMapper.deleteByExample()
                    .where(TimeTaskDynamicSqlSupport.id, isEqualTo(id))
                    .build()
                    .execute();

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg","删除定时任务成功");
        return resultMap;
    }

    @Override
    @Transactional
    public Map<String, Object> updateTimeTask(TimeTaskReqDto timeTaskReqDto) {
        //1.参数校验

        //2.判断timeTask是否存在
        TimeTask timeTask = timeTaskMapper.selectByPrimaryKey(timeTaskReqDto.getId());
        if(ObjUtil.isEmpty(timeTask)){
            throw new IllegalArgumentException("id为:"+timeTaskReqDto.getId()+"的定时任务不存在");
        }

        //3.更新TimeTask
        //(1)复制TimeTaskDto中的请求参数到TimeTask
        VoPoConverterUtil.beanConverterNotNull(timeTaskReqDto, timeTask);

        //4.保存
        timeTaskMapper.updateByPrimaryKey(timeTask);

        //5.返回结果
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("msg","更新定时任务信息成功");
        return resultMap;
    }

        @Override
        @Transactional
        public Map<String, Object> insertOrUpdateTimeTask(TimeTaskReqDto timeTaskReqDto) {
            //1.参数校验

            //2.判断timeTask是否存在
            TimeTask timeTask = timeTaskMapper.selectByPrimaryKey(timeTaskReqDto.getId());
            if(ObjUtil.isEmpty(timeTask)){
                throw new IllegalArgumentException("id为:"+timeTaskReqDto.getId()+"的定时任务不存在");
            }

            //3.更新TimeTask
            //(1)复制TimeTaskDto中的请求参数到TimeTask
            VoPoConverterUtil.beanConverterNotNull(timeTaskReqDto, timeTask);

            //4.保存
            timeTaskMapper.updateByPrimaryKey(timeTask);

            //5.返回结果
            Map<String,Object> resultMap = new HashMap<>();
            resultMap.put("msg","更新定时任务信息成功");
            return resultMap;
        }

    @Override
    public Map<String, Object> getTimeTaskById(Long id){
        TimeTask e = timeTaskMapper.selectByPrimaryKey(id);
        if(ObjUtil.isEmpty(e)){
            throw new IllegalArgumentException("id为:"+id+"的定时任务信息不存在");
        }

        Map<String, Object> attr = new HashMap<>();
        attr.put("id", e.getId());
        attr.put("projectId", e.getProjectId());
        attr.put("projectName", e.getProjectName());
        attr.put("jobId", e.getJobId());
        attr.put("xxlJobAdminAddress", e.getXxlJobAdminAddress());
        attr.put("corn", e.getCorn());
        attr.put("handler", e.getHandler());
        attr.put("appName", e.getAppName());
        attr.put("createTime", e.getCreateTime());
        return attr;
    }

    @Override
    public PageVo<Map<String, Object>> getTimeTaskListPageVo(TimeTaskPageReqDto timeTaskPageReqDto){

        //1.查询列表
        List<TimeTask> list = queryListByPageReqDto(timeTaskPageReqDto,true);

        //2.构建pageVo
        PageVo<TimeTask> pageVo = new PageVo<>(list);

        //3.对象转为map分页输出
        return PageVo.of(
                pageVo,
                e -> {
                    Map<String, Object> attr = new HashMap<>();
                    attr.put("id", e.getId());
                    attr.put("projectId", e.getProjectId());
                    attr.put("projectName", e.getProjectName());
                    attr.put("jobId", e.getJobId());
                    attr.put("xxlJobAdminAddress", e.getXxlJobAdminAddress());
                    attr.put("corn", e.getCorn());
                    attr.put("handler", e.getHandler());
                    attr.put("appName", e.getAppName());
                    attr.put("createTime", e.getCreateTime());
                    return attr;
                }
        );
    }

    @Override
    public void downloadTemplateExcel(HttpServletResponse response){
        //1.查询到该对象的导入模板
        /*TimeTask timeTask = timeTaskMapper.selectByExampleOne()
                .where(TimeTaskDynamicSqlSupport.fileKey, isEqualTo("timeTaskTemplateExcel"))
                .build()
                .execute();
        if(ObjUtil.isEmpty(timeTask)){
            throw new IllegalArgumentException("模板文件暂未上传,请联系管理员");
        }
        //2.响应文件到页面
        minioUtil.download(timeTask.getStorePath(),timeTask.getName(),response);*/
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
        TimeTaskExcelListener timeTaskExcelListener = new TimeTaskExcelListener(this,validator,fieldDupValidator);
        try {
            in = excelFile.getInputStream();
            EasyExcel.read(in, TimeTaskExcelVo.class, timeTaskExcelListener).sheet().doRead();
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
    public void exportToExcel(TimeTaskPageReqDto timeTaskPageReqDto,HttpServletResponse response){
        //1.查询列表
        List<TimeTask> timeTaskList = queryListByPageReqDto(timeTaskPageReqDto,false);

        //2.将原始列表转为导出对象列表
        List<TimeTaskExcelVo> timeTaskExcelVoList = timeTaskList.stream().map(e -> {
            TimeTaskExcelVo timeTaskExcelVo = VoPoConverterUtil.copyProperties(e, TimeTaskExcelVo.class);
            return timeTaskExcelVo;
        }).collect(Collectors.toList());

        //3.获取到输出流
        OutputStream outputStream = null;
        try {
            //(1).文件名，表名，表头，格式
            String sheetName = "定时任务";
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
                .head(TimeTaskExcelVo.class)
                .sheet("Sheet1")
                .doWrite(timeTaskExcelVoList);
    }

    private List<TimeTask> queryListByPageReqDto(TimeTaskPageReqDto timeTaskPageReqDto,Boolean needPaging){
        //1.查询语句构建
        QueryExpressionDSL<MyBatis3SelectModelAdapter<List<TimeTask>>>.QueryExpressionWhereBuilder builder = timeTaskMapper.selectByExample().where();

        //2.根据查询条件封装查询命令
        //(1).关键字查询
        /*String name = timeTaskPageReqDto.getName();
        if(!ObjUtil.isEmpty(name)){
        builder.and(TimeTaskDynamicSqlSupport.name, isLike("%"+name+"%"));
        }

        Long type = timeTaskPageReqDto.getType();
        if(!ObjUtil.isEmpty(type)){
        builder.and(TimeTaskDynamicSqlSupport.type, isEqualTo(type));
        }

        Long startTime = timeTaskPageReqDto.getStartTime();
        Long endTime = timeTaskPageReqDto.getEndTime();
        if (startTime != null && endTime != null) {
        builder.and(TimeTaskDynamicSqlSupport.startTime, isGreaterThanOrEqualTo(startTime));
        builder.and(TimeTaskDynamicSqlSupport.endTime, isLessThanOrEqualTo(endTime));
        } else {
            if (startTime != null) {
            builder.and(TimeTaskDynamicSqlSupport.startTime, isGreaterThanOrEqualTo(startTime));
            }
            if (endTime != null) {
            builder.and(TimeTaskDynamicSqlSupport.endTime, isLessThanOrEqualTo(endTime));
            }
        }*/

        //3.排序
        builder.orderBy(TimeTaskDynamicSqlSupport.createTime.descending());

        //4.查询(不需要分页即列表)
        if(needPaging){
        PageHelper.startPage(timeTaskPageReqDto.getPageNum(), timeTaskPageReqDto.getPageSize());
        }

        List<TimeTask> list = builder.build().execute();
        return list;
    }
}