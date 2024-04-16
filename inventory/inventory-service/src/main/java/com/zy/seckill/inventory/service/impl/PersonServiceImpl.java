package com.zy.seckill.inventory.service.impl;

import com.github.pagehelper.PageHelper;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.zy.seckill.common.bo.vo.PageVo;
import com.zy.seckill.inventory.bo.dto.PersonReqDto;
import com.zy.seckill.inventory.bo.dto.PersonPageReqDto;
import com.zy.seckill.inventory.bo.po.Person;
import com.zy.seckill.inventory.mapper.PersonMapper;
import com.zy.seckill.inventory.mapper.PersonDynamicSqlSupport;
import com.zy.seckill.inventory.excel.PersonExcelListener;
import com.zy.seckill.inventory.excel.PersonExcelVo;
import com.zy.seckill.common.utils.*;
import com.zy.seckill.common.validator.FieldDupValidator;
import com.zy.seckill.inventory.service.PersonService;
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
 * @Description //PersonService接口实现类
 * @Date 2024/02/02 15:06
 * @Param
 * @return
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final MinioUtil minioUtil;

    private final Validator validator;

    private final FieldDupValidator fieldDupValidator;

    private final PersonMapper personMapper;


    @Override
    @Transactional
    public Map<String, Object> addPerson(PersonReqDto personReqDto) {

        //1.参数校验

        //2.设置参数
        //(1)复制PersonReqDto中的请求参数到Person
        Person person = VoPoConverterUtil.copyProperties(personReqDto, Person.class);

        //(2)设置其他属性
        person.setId(new IdWorker().nextId()).setCreateTime(System.currentTimeMillis());

        //3.保存
        personMapper.insert(person);

        //4.返回结果
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("msg","添加顾客信息成功");
        return resultMap;
    }

    @Override
    @Transactional
    public Map<String, Object> deletePersonById(Long id){
        Person person = personMapper.selectByPrimaryKey(id);
        if(person == null){
            throw new IllegalArgumentException("id为:"+id+"的顾客信息不存在");
        }

        personMapper.deleteByExample()
                    .where(PersonDynamicSqlSupport.id, isEqualTo(id))
                    .build()
                    .execute();

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg","删除顾客成功");
        return resultMap;
    }

    @Override
    @Transactional
    public Map<String, Object> updatePerson(PersonReqDto personReqDto) {
        //1.参数校验

        //2.判断Person是否存在
        Person person = personMapper.selectByPrimaryKey(personReqDto.getId());
        if(ObjUtil.isEmpty(person)){
            throw new IllegalArgumentException("id为:"+ personReqDto.getId()+"的顾客不存在");
        }

        //3.更新Person
        //(1)复制PersonDto中的请求参数到Person
        VoPoConverterUtil.beanConverterNotNull(personReqDto, person);

        //4.保存
        personMapper.updateByPrimaryKey(person);

        //5.返回结果
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("msg","更新顾客信息成功");
        return resultMap;
    }

        @Override
        @Transactional
        public Map<String, Object> insertOrUpdatePerson(PersonReqDto personReqDto) {
            //1.参数校验

            //2.判断Person是否存在
            Person person = personMapper.selectByPrimaryKey(personReqDto.getId());
            if(ObjUtil.isEmpty(person)){
                throw new IllegalArgumentException("id为:"+ personReqDto.getId()+"的顾客不存在");
            }

            //3.更新Person
            //(1)复制PersonDto中的请求参数到Person
            VoPoConverterUtil.beanConverterNotNull(personReqDto, person);

            //4.保存
            personMapper.updateByPrimaryKey(person);

            //5.返回结果
            Map<String,Object> resultMap = new HashMap<>();
            resultMap.put("msg","更新顾客信息成功");
            return resultMap;
        }

    @Override
    public Map<String, Object> getPersonById(Long id){
        Person e = personMapper.selectByPrimaryKey(id);
        if(ObjUtil.isEmpty(e)){
            throw new IllegalArgumentException("id为:"+id+"的顾客信息不存在");
        }

        Map<String, Object> attr = new HashMap<>();
        attr.put("id", e.getId());
        attr.put("name", e.getName());
        attr.put("sex", e.getSex());
        attr.put("age", e.getAge());
        attr.put("mobile", e.getMobile());
        attr.put("idNumber", e.getIdNumber());
        attr.put("openId", e.getOpenId());
        attr.put("address", e.getAddress());
        attr.put("personType", e.getPersonType());
        attr.put("doseStatus", e.getDoseStatus());
        attr.put("firstDoseId", e.getFirstDoseId());
        attr.put("firstDoseName", e.getFirstDoseName());
        attr.put("firstDoseTime", e.getFirstDoseTime());
        attr.put("latestDoseId", e.getLatestDoseId());
        attr.put("latestDoseName", e.getLatestDoseName());
        attr.put("latestDoseTime", e.getLatestDoseTime());
        attr.put("createTime", e.getCreateTime());
        attr.put("updateTime", e.getUpdateTime());
        return attr;
    }

    @Override
    public PageVo<Map<String, Object>> getPersonListPageVo(PersonPageReqDto personPageReqDto){

        //1.查询列表
        List<Person> list = queryListByPageReqDto(personPageReqDto,true);

        //2.构建pageVo
        PageVo<Person> pageVo = new PageVo<>(list);

        //3.对象转为map分页输出
        return PageVo.of(
                pageVo,
                e -> {
                    Map<String, Object> attr = new HashMap<>();
                    attr.put("id", e.getId());
                    attr.put("name", e.getName());
                    attr.put("sex", e.getSex());
                    attr.put("age", e.getAge());
                    attr.put("mobile", e.getMobile());
                    attr.put("idNumber", e.getIdNumber());
                    attr.put("openId", e.getOpenId());
                    attr.put("address", e.getAddress());
                    attr.put("personType", e.getPersonType());
                    attr.put("doseStatus", e.getDoseStatus());
                    attr.put("firstDoseId", e.getFirstDoseId());
                    attr.put("firstDoseName", e.getFirstDoseName());
                    attr.put("firstDoseTime", e.getFirstDoseTime());
                    attr.put("latestDoseId", e.getLatestDoseId());
                    attr.put("latestDoseName", e.getLatestDoseName());
                    attr.put("latestDoseTime", e.getLatestDoseTime());
                    attr.put("createTime", e.getCreateTime());
                    attr.put("updateTime", e.getUpdateTime());
                    return attr;
                }
        );
    }

    @Override
    public void downloadTemplateExcel(HttpServletResponse response){
        //1.查询到该对象的导入模板
        /*Person Person = PersonMapper.selectByExampleOne()
                .where(PersonDynamicSqlSupport.fileKey, isEqualTo("PersonTemplateExcel"))
                .build()
                .execute();
        if(ObjUtil.isEmpty(Person)){
            throw new IllegalArgumentException("模板文件暂未上传,请联系管理员");
        }
        //2.响应文件到页面
        minioUtil.download(Person.getStorePath(),Person.getName(),response);*/
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
        PersonExcelListener PersonExcelListener = new PersonExcelListener(this,validator,fieldDupValidator);
        try {
            in = excelFile.getInputStream();
            EasyExcel.read(in, PersonExcelVo.class, PersonExcelListener).sheet().doRead();
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
    public void exportToExcel(PersonPageReqDto personPageReqDto, HttpServletResponse response){
        //1.查询列表
        List<Person> personList = queryListByPageReqDto(personPageReqDto,false);

        //2.将原始列表转为导出对象列表
        List<PersonExcelVo> PersonExcelVoList = personList.stream().map(e -> {
            PersonExcelVo PersonExcelVo = VoPoConverterUtil.copyProperties(e, PersonExcelVo.class);
            return PersonExcelVo;
        }).collect(Collectors.toList());

        //3.获取到输出流
        OutputStream outputStream = null;
        try {
            //(1).文件名，表名，表头，格式
            String sheetName = "顾客";
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
                .head(PersonExcelVo.class)
                .sheet("Sheet1")
                .doWrite(PersonExcelVoList);
    }

    private List<Person> queryListByPageReqDto(PersonPageReqDto personPageReqDto, Boolean needPaging){
        //1.查询语句构建
        QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Person>>>.QueryExpressionWhereBuilder builder = personMapper.selectByExample().where();

        //2.根据查询条件封装查询命令
        //(1).关键字查询
        /*String name = PersonPageReqDto.getName();
        if(!ObjUtil.isEmpty(name)){
        builder.and(PersonDynamicSqlSupport.name, isLike("%"+name+"%"));
        }

        Long type = PersonPageReqDto.getType();
        if(!ObjUtil.isEmpty(type)){
        builder.and(PersonDynamicSqlSupport.type, isEqualTo(type));
        }

        Long startTime = PersonPageReqDto.getStartTime();
        Long endTime = PersonPageReqDto.getEndTime();
        if (startTime != null && endTime != null) {
        builder.and(PersonDynamicSqlSupport.startTime, isGreaterThanOrEqualTo(startTime));
        builder.and(PersonDynamicSqlSupport.endTime, isLessThanOrEqualTo(endTime));
        } else {
            if (startTime != null) {
            builder.and(PersonDynamicSqlSupport.startTime, isGreaterThanOrEqualTo(startTime));
            }
            if (endTime != null) {
            builder.and(PersonDynamicSqlSupport.endTime, isLessThanOrEqualTo(endTime));
            }
        }*/

        //3.排序
        builder.orderBy(PersonDynamicSqlSupport.createTime.descending());

        //4.查询(不需要分页即列表)
        if(needPaging){
        PageHelper.startPage(personPageReqDto.getPageNum(), personPageReqDto.getPageSize());
        }

        List<Person> list = builder.build().execute();
        return list;
    }
}