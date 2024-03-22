package com.jsxa.vapp.inventory.service.impl;

import com.github.pagehelper.PageHelper;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.jsxa.vapp.common.bo.vo.PageVo;
import com.jsxa.vapp.common.redis.RedisKey;
import com.jsxa.vapp.common.redis.RedisService;
import com.jsxa.vapp.inventory.bo.dto.VaccineReqDto;
import com.jsxa.vapp.inventory.bo.dto.VaccinePageReqDto;
import com.jsxa.vapp.inventory.bo.po.Vaccine;
import com.jsxa.vapp.inventory.mapper.VaccineMapper;
import com.jsxa.vapp.inventory.mapper.VaccineDynamicSqlSupport;
import com.jsxa.vapp.inventory.excel.VaccineExcelListener;
import com.jsxa.vapp.inventory.excel.VaccineExcelVo;
import com.jsxa.vapp.common.utils.*;
import com.jsxa.vapp.common.redis.RedisService;
import com.jsxa.vapp.common.redis.RedisKey;
import com.jsxa.vapp.common.validator.FieldDupValidator;
import com.jsxa.vapp.inventory.service.VaccineService;
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
 * @Description //VaccineService接口实现类
 * @Date 2021/02/27 14:20
 * @Param
 * @return
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class VaccineServiceImpl implements VaccineService {

    private final MinioUtil minioUtil;

    private final Validator validator;

    private final FieldDupValidator fieldDupValidator;

    private final VaccineMapper vaccineMapper;


    @Override
    @Transactional
    public Map<String, Object> addVaccine(VaccineReqDto vaccineReqDto) {

        //1.参数校验

        //2.设置参数
        //(1)复制VaccineReqDto中的请求参数到Vaccine
        Vaccine vaccine = VoPoConverterUtil.copyProperties(vaccineReqDto, Vaccine.class);

        //(2)设置其他属性
        vaccine.setId(new IdWorker().nextId()).setStatus((byte)1).setCreateTime(System.currentTimeMillis());

        //3.保存
        vaccineMapper.insert(vaccine);

        //4.返回结果
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("msg","添加疫苗信息成功");
        return resultMap;
    }

    @Override
    @Transactional
    public Map<String, Object> deleteVaccineById(Long id){
        Vaccine vaccine = vaccineMapper.selectByPrimaryKey(id);
        if(vaccine == null){
            throw new IllegalArgumentException("id为:"+id+"的疫苗信息不存在");
        }

        vaccineMapper.deleteByExample()
                    .where(VaccineDynamicSqlSupport.id, isEqualTo(id))
                    .build()
                    .execute();

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg","删除疫苗成功");
        return resultMap;
    }

    @Override
    @Transactional
    public Map<String, Object> updateVaccine(VaccineReqDto vaccineReqDto) {
        //1.参数校验

        //2.判断vaccine是否存在
        Vaccine vaccine = vaccineMapper.selectByPrimaryKey(vaccineReqDto.getId());
        if(ObjUtil.isEmpty(vaccine)){
            throw new IllegalArgumentException("id为:"+vaccineReqDto.getId()+"的疫苗不存在");
        }

        //3.更新Vaccine
        //(1)复制VaccineDto中的请求参数到Vaccine
        VoPoConverterUtil.beanConverterNotNull(vaccineReqDto, vaccine);

        //4.保存
        vaccineMapper.updateByPrimaryKey(vaccine);

        //5.返回结果
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("msg","更新疫苗信息成功");
        return resultMap;
    }

        @Override
        @Transactional
        public Map<String, Object> insertOrUpdateVaccine(VaccineReqDto vaccineReqDto) {
            //1.参数校验

            //2.判断vaccine是否存在
            Vaccine vaccine = vaccineMapper.selectByPrimaryKey(vaccineReqDto.getId());
            if(ObjUtil.isEmpty(vaccine)){
                throw new IllegalArgumentException("id为:"+vaccineReqDto.getId()+"的疫苗不存在");
            }

            //3.更新Vaccine
            //(1)复制VaccineDto中的请求参数到Vaccine
            VoPoConverterUtil.beanConverterNotNull(vaccineReqDto, vaccine);

            //4.保存
            vaccineMapper.updateByPrimaryKey(vaccine);

            //5.返回结果
            Map<String,Object> resultMap = new HashMap<>();
            resultMap.put("msg","更新疫苗信息成功");
            return resultMap;
        }

    @Override
    public Map<String, Object> getVaccineById(Long id){
        Vaccine e = vaccineMapper.selectByPrimaryKey(id);
        if(ObjUtil.isEmpty(e)){
            throw new IllegalArgumentException("id为:"+id+"的疫苗信息不存在");
        }

        Map<String, Object> attr = new HashMap<>();
        attr.put("id", e.getId());
        attr.put("orgId", e.getOrgId());
        attr.put("orgName", e.getOrgName());
        attr.put("name", e.getName());
        attr.put("manufacturer", e.getManufacturer());
        attr.put("batchNumber", e.getBatchNumber());
        attr.put("totalDose", e.getTotalDose());
        attr.put("doseInterval", e.getDoseInterval());
        attr.put("stock", e.getStock());
        attr.put("oosUrl", e.getOosUrl());
        attr.put("orderNum", e.getOrderNum());
        attr.put("status", e.getStatus());
        attr.put("createTime", e.getCreateTime());
        return attr;
    }

    @Override
    public PageVo<Map<String, Object>> getVaccineListPageVo(VaccinePageReqDto vaccinePageReqDto){

        //1.查询列表
        List<Vaccine> list = queryListByPageReqDto(vaccinePageReqDto,true);

        //2.构建pageVo
        PageVo<Vaccine> pageVo = new PageVo<>(list);

        //3.对象转为map分页输出
        return PageVo.of(
                pageVo,
                e -> {
                    Map<String, Object> attr = new HashMap<>();
                    attr.put("id", e.getId());
                    attr.put("orgId", e.getOrgId());
                    attr.put("orgName", e.getOrgName());
                    attr.put("name", e.getName());
                    attr.put("manufacturer", e.getManufacturer());
                    attr.put("batchNumber", e.getBatchNumber());
                    attr.put("totalDose", e.getTotalDose());
                    attr.put("doseInterval", e.getDoseInterval());
                    attr.put("stock", e.getStock());
                    attr.put("oosUrl", e.getOosUrl());
                    attr.put("orderNum", e.getOrderNum());
                    attr.put("status", e.getStatus());
                    attr.put("createTime", e.getCreateTime());
                    return attr;
                }
        );
    }

    @Override
    public void downloadTemplateExcel(HttpServletResponse response){
        //1.查询到该对象的导入模板
        /*Vaccine vaccine = vaccineMapper.selectByExampleOne()
                .where(VaccineDynamicSqlSupport.fileKey, isEqualTo("vaccineTemplateExcel"))
                .build()
                .execute();
        if(ObjUtil.isEmpty(vaccine)){
            throw new IllegalArgumentException("模板文件暂未上传,请联系管理员");
        }
        //2.响应文件到页面
        minioUtil.download(vaccine.getStorePath(),vaccine.getName(),response);*/
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
        VaccineExcelListener vaccineExcelListener = new VaccineExcelListener(this,validator,fieldDupValidator);
        try {
            in = excelFile.getInputStream();
            EasyExcel.read(in, VaccineExcelVo.class, vaccineExcelListener).sheet().doRead();
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
    public void exportToExcel(VaccinePageReqDto vaccinePageReqDto,HttpServletResponse response){
        //1.查询列表
        List<Vaccine> vaccineList = queryListByPageReqDto(vaccinePageReqDto,false);

        //2.将原始列表转为导出对象列表
        List<VaccineExcelVo> vaccineExcelVoList = vaccineList.stream().map(e -> {
            VaccineExcelVo vaccineExcelVo = VoPoConverterUtil.copyProperties(e, VaccineExcelVo.class);
            return vaccineExcelVo;
        }).collect(Collectors.toList());

        //3.获取到输出流
        OutputStream outputStream = null;
        try {
            //(1).文件名，表名，表头，格式
            String sheetName = "疫苗";
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
                .head(VaccineExcelVo.class)
                .sheet("Sheet1")
                .doWrite(vaccineExcelVoList);
    }

    private List<Vaccine> queryListByPageReqDto(VaccinePageReqDto vaccinePageReqDto,Boolean needPaging){
        //1.查询语句构建
        QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Vaccine>>>.QueryExpressionWhereBuilder builder = vaccineMapper.selectByExample().where();

        //2.根据查询条件封装查询命令
        //(1).关键字查询
        /*String name = vaccinePageReqDto.getName();
        if(!ObjUtil.isEmpty(name)){
        builder.and(VaccineDynamicSqlSupport.name, isLike("%"+name+"%"));
        }

        Long type = vaccinePageReqDto.getType();
        if(!ObjUtil.isEmpty(type)){
        builder.and(VaccineDynamicSqlSupport.type, isEqualTo(type));
        }

        Long startTime = vaccinePageReqDto.getStartTime();
        Long endTime = vaccinePageReqDto.getEndTime();
        if (startTime != null && endTime != null) {
        builder.and(VaccineDynamicSqlSupport.startTime, isGreaterThanOrEqualTo(startTime));
        builder.and(VaccineDynamicSqlSupport.endTime, isLessThanOrEqualTo(endTime));
        } else {
            if (startTime != null) {
            builder.and(VaccineDynamicSqlSupport.startTime, isGreaterThanOrEqualTo(startTime));
            }
            if (endTime != null) {
            builder.and(VaccineDynamicSqlSupport.endTime, isLessThanOrEqualTo(endTime));
            }
        }*/

        //3.排序
        builder.orderBy(VaccineDynamicSqlSupport.createTime.descending());

        //4.查询(不需要分页即列表)
        if(needPaging){
        PageHelper.startPage(vaccinePageReqDto.getPageNum(), vaccinePageReqDto.getPageSize());
        }

        List<Vaccine> list = builder.build().execute();
        return list;
    }
}