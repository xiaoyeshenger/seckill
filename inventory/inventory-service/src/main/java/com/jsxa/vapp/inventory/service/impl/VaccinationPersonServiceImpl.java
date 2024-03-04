package com.jsxa.vapp.inventory.service.impl;

import com.github.pagehelper.PageHelper;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.jsxa.vapp.common.bo.vo.PageVo;
import com.jsxa.vapp.common.redis.RedisKey;
import com.jsxa.vapp.common.redis.RedisService;
import com.jsxa.vapp.inventory.bo.dto.VaccinationPersonReqDto;
import com.jsxa.vapp.inventory.bo.dto.VaccinationPersonPageReqDto;
import com.jsxa.vapp.inventory.bo.po.VaccinationPerson;
import com.jsxa.vapp.inventory.mapper.VaccinationPersonMapper;
import com.jsxa.vapp.inventory.mapper.VaccinationPersonDynamicSqlSupport;
import com.jsxa.vapp.inventory.excel.VaccinationPersonExcelListener;
import com.jsxa.vapp.inventory.excel.VaccinationPersonExcelVo;
import com.jsxa.vapp.common.utils.*;
import com.jsxa.vapp.common.redis.RedisService;
import com.jsxa.vapp.common.redis.RedisKey;
import com.jsxa.vapp.common.validator.FieldDupValidator;
import com.jsxa.vapp.inventory.service.VaccinationPersonService;
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


import javax.annotation.Resource;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

/**
 * @Author zhangyong
 * @Description //VaccinationPersonService接口实现类
 * @Date 2024/02/02 15:06
 * @Param
 * @return
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class VaccinationPersonServiceImpl implements VaccinationPersonService {

    private final MinioUtil minioUtil;

    private final Validator validator;

    private final FieldDupValidator fieldDupValidator;

    private final VaccinationPersonMapper vaccinationPersonMapper;


    @Override
    @Transactional
    public Map<String, Object> addVaccinationPerson(VaccinationPersonReqDto vaccinationPersonReqDto) {

        //1.参数校验

        //2.设置参数
        //(1)复制VaccinationPersonReqDto中的请求参数到VaccinationPerson
        VaccinationPerson vaccinationPerson = VoPoConverterUtil.copyProperties(vaccinationPersonReqDto, VaccinationPerson.class);

        //(2)设置其他属性
        vaccinationPerson.setId(new IdWorker().nextId()).setCreateTime(System.currentTimeMillis());

        //3.保存
        vaccinationPersonMapper.insert(vaccinationPerson);

        //4.返回结果
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("msg","添加接种人员信息成功");
        return resultMap;
    }

    @Override
    @Transactional
    public Map<String, Object> deleteVaccinationPersonById(Long id){
        VaccinationPerson vaccinationPerson = vaccinationPersonMapper.selectByPrimaryKey(id);
        if(vaccinationPerson == null){
            throw new IllegalArgumentException("id为:"+id+"的接种人员信息不存在");
        }

        vaccinationPersonMapper.deleteByExample()
                    .where(VaccinationPersonDynamicSqlSupport.id, isEqualTo(id))
                    .build()
                    .execute();

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg","删除接种人员成功");
        return resultMap;
    }

    @Override
    @Transactional
    public Map<String, Object> updateVaccinationPerson(VaccinationPersonReqDto vaccinationPersonReqDto) {
        //1.参数校验

        //2.判断vaccinationPerson是否存在
        VaccinationPerson vaccinationPerson = vaccinationPersonMapper.selectByPrimaryKey(vaccinationPersonReqDto.getId());
        if(ObjUtil.isEmpty(vaccinationPerson)){
            throw new IllegalArgumentException("id为:"+vaccinationPersonReqDto.getId()+"的接种人员不存在");
        }

        //3.更新VaccinationPerson
        //(1)复制VaccinationPersonDto中的请求参数到VaccinationPerson
        VoPoConverterUtil.beanConverterNotNull(vaccinationPersonReqDto, vaccinationPerson);

        //4.保存
        vaccinationPersonMapper.updateByPrimaryKey(vaccinationPerson);

        //5.返回结果
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("msg","更新接种人员信息成功");
        return resultMap;
    }

        @Override
        @Transactional
        public Map<String, Object> insertOrUpdateVaccinationPerson(VaccinationPersonReqDto vaccinationPersonReqDto) {
            //1.参数校验

            //2.判断vaccinationPerson是否存在
            VaccinationPerson vaccinationPerson = vaccinationPersonMapper.selectByPrimaryKey(vaccinationPersonReqDto.getId());
            if(ObjUtil.isEmpty(vaccinationPerson)){
                throw new IllegalArgumentException("id为:"+vaccinationPersonReqDto.getId()+"的接种人员不存在");
            }

            //3.更新VaccinationPerson
            //(1)复制VaccinationPersonDto中的请求参数到VaccinationPerson
            VoPoConverterUtil.beanConverterNotNull(vaccinationPersonReqDto, vaccinationPerson);

            //4.保存
            vaccinationPersonMapper.updateByPrimaryKey(vaccinationPerson);

            //5.返回结果
            Map<String,Object> resultMap = new HashMap<>();
            resultMap.put("msg","更新接种人员信息成功");
            return resultMap;
        }

    @Override
    public Map<String, Object> getVaccinationPersonById(Long id){
        VaccinationPerson e = vaccinationPersonMapper.selectByPrimaryKey(id);
        if(ObjUtil.isEmpty(e)){
            throw new IllegalArgumentException("id为:"+id+"的接种人员信息不存在");
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
        attr.put("workUnit", e.getWorkUnit());
        attr.put("personType", e.getPersonType());
        attr.put("doseStatus", e.getDoseStatus());
        attr.put("firstDoseId", e.getFirstDoseId());
        attr.put("firstDoseName", e.getFirstDoseName());
        attr.put("firstDoseUnit", e.getFirstDoseUnit());
        attr.put("firstDoseTime", e.getFirstDoseTime());
        attr.put("latestDoseId", e.getLatestDoseId());
        attr.put("latestDoseName", e.getLatestDoseName());
        attr.put("latestDoseUnit", e.getLatestDoseUnit());
        attr.put("latestDoseTime", e.getLatestDoseTime());
        attr.put("createTime", e.getCreateTime());
        attr.put("updateTime", e.getUpdateTime());
        return attr;
    }

    @Override
    public PageVo<Map<String, Object>> getVaccinationPersonListPageVo(VaccinationPersonPageReqDto vaccinationPersonPageReqDto){

        //1.查询列表
        List<VaccinationPerson> list = queryListByPageReqDto(vaccinationPersonPageReqDto,true);

        //2.构建pageVo
        PageVo<VaccinationPerson> pageVo = new PageVo<>(list);

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
                    attr.put("workUnit", e.getWorkUnit());
                    attr.put("personType", e.getPersonType());
                    attr.put("doseStatus", e.getDoseStatus());
                    attr.put("firstDoseId", e.getFirstDoseId());
                    attr.put("firstDoseName", e.getFirstDoseName());
                    attr.put("firstDoseUnit", e.getFirstDoseUnit());
                    attr.put("firstDoseTime", e.getFirstDoseTime());
                    attr.put("latestDoseId", e.getLatestDoseId());
                    attr.put("latestDoseName", e.getLatestDoseName());
                    attr.put("latestDoseUnit", e.getLatestDoseUnit());
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
        /*VaccinationPerson vaccinationPerson = vaccinationPersonMapper.selectByExampleOne()
                .where(VaccinationPersonDynamicSqlSupport.fileKey, isEqualTo("vaccinationPersonTemplateExcel"))
                .build()
                .execute();
        if(ObjUtil.isEmpty(vaccinationPerson)){
            throw new IllegalArgumentException("模板文件暂未上传,请联系管理员");
        }
        //2.响应文件到页面
        minioUtil.download(vaccinationPerson.getStorePath(),vaccinationPerson.getName(),response);*/
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
        VaccinationPersonExcelListener vaccinationPersonExcelListener = new VaccinationPersonExcelListener(this,validator,fieldDupValidator);
        try {
            in = excelFile.getInputStream();
            EasyExcel.read(in, VaccinationPersonExcelVo.class, vaccinationPersonExcelListener).sheet().doRead();
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
    public void exportToExcel(VaccinationPersonPageReqDto vaccinationPersonPageReqDto,HttpServletResponse response){
        //1.查询列表
        List<VaccinationPerson> vaccinationPersonList = queryListByPageReqDto(vaccinationPersonPageReqDto,false);

        //2.将原始列表转为导出对象列表
        List<VaccinationPersonExcelVo> vaccinationPersonExcelVoList = vaccinationPersonList.stream().map(e -> {
            VaccinationPersonExcelVo vaccinationPersonExcelVo = VoPoConverterUtil.copyProperties(e, VaccinationPersonExcelVo.class);
            return vaccinationPersonExcelVo;
        }).collect(Collectors.toList());

        //3.获取到输出流
        OutputStream outputStream = null;
        try {
            //(1).文件名，表名，表头，格式
            String sheetName = "接种人员";
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
                .head(VaccinationPersonExcelVo.class)
                .sheet("Sheet1")
                .doWrite(vaccinationPersonExcelVoList);
    }

    private List<VaccinationPerson> queryListByPageReqDto(VaccinationPersonPageReqDto vaccinationPersonPageReqDto,Boolean needPaging){
        //1.查询语句构建
        QueryExpressionDSL<MyBatis3SelectModelAdapter<List<VaccinationPerson>>>.QueryExpressionWhereBuilder builder = vaccinationPersonMapper.selectByExample().where();

        //2.根据查询条件封装查询命令
        //(1).关键字查询
        /*String name = vaccinationPersonPageReqDto.getName();
        if(!ObjUtil.isEmpty(name)){
        builder.and(VaccinationPersonDynamicSqlSupport.name, isLike("%"+name+"%"));
        }

        Long type = vaccinationPersonPageReqDto.getType();
        if(!ObjUtil.isEmpty(type)){
        builder.and(VaccinationPersonDynamicSqlSupport.type, isEqualTo(type));
        }

        Long startTime = vaccinationPersonPageReqDto.getStartTime();
        Long endTime = vaccinationPersonPageReqDto.getEndTime();
        if (startTime != null && endTime != null) {
        builder.and(VaccinationPersonDynamicSqlSupport.startTime, isGreaterThanOrEqualTo(startTime));
        builder.and(VaccinationPersonDynamicSqlSupport.endTime, isLessThanOrEqualTo(endTime));
        } else {
            if (startTime != null) {
            builder.and(VaccinationPersonDynamicSqlSupport.startTime, isGreaterThanOrEqualTo(startTime));
            }
            if (endTime != null) {
            builder.and(VaccinationPersonDynamicSqlSupport.endTime, isLessThanOrEqualTo(endTime));
            }
        }*/

        //3.排序
        builder.orderBy(VaccinationPersonDynamicSqlSupport.createTime.descending());

        //4.查询(不需要分页即列表)
        if(needPaging){
        PageHelper.startPage(vaccinationPersonPageReqDto.getPageNum(), vaccinationPersonPageReqDto.getPageSize());
        }

        List<VaccinationPerson> list = builder.build().execute();
        return list;
    }
}