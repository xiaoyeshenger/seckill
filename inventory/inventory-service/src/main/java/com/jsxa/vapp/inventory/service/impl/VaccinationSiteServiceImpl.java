package com.jsxa.vapp.inventory.service.impl;

import com.github.pagehelper.PageHelper;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.jsxa.vapp.common.bo.vo.PageVo;
import com.jsxa.vapp.common.redis.RedisKey;
import com.jsxa.vapp.common.redis.RedisService;
import com.jsxa.vapp.inventory.bo.dto.VaccinationSiteReqDto;
import com.jsxa.vapp.inventory.bo.dto.VaccinationSitePageReqDto;
import com.jsxa.vapp.inventory.bo.po.VaccinationSite;
import com.jsxa.vapp.inventory.mapper.VaccinationSiteMapper;
import com.jsxa.vapp.inventory.mapper.VaccinationSiteDynamicSqlSupport;
import com.jsxa.vapp.inventory.excel.VaccinationSiteExcelListener;
import com.jsxa.vapp.inventory.excel.VaccinationSiteExcelVo;
import com.jsxa.vapp.common.utils.*;
import com.jsxa.vapp.common.redis.RedisService;
import com.jsxa.vapp.common.redis.RedisKey;
import com.jsxa.vapp.common.validator.FieldDupValidator;
import com.jsxa.vapp.inventory.service.VaccinationSiteService;
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
 * @Description //VaccinationSiteService接口实现类
 * @Date 2021/02/27 14:32
 * @Param
 * @return
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class VaccinationSiteServiceImpl implements VaccinationSiteService {

    private final MinioUtil minioUtil;

    private final Validator validator;

    private final FieldDupValidator fieldDupValidator;

    private final VaccinationSiteMapper vaccinationSiteMapper;


    @Override
    @Transactional
    public Map<String, Object> addVaccinationSite(VaccinationSiteReqDto vaccinationSiteReqDto) {

        //1.参数校验

        //2.设置参数
        //(1)复制VaccinationSiteReqDto中的请求参数到VaccinationSite
        VaccinationSite vaccinationSite = VoPoConverterUtil.copyProperties(vaccinationSiteReqDto, VaccinationSite.class);

        //(2)设置其他属性
        vaccinationSite.setId(new IdWorker().nextId()).setStatus((byte)1).setCreateTime(System.currentTimeMillis());

        //3.保存
        vaccinationSiteMapper.insert(vaccinationSite);

        //4.返回结果
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("msg","添加接种点信息成功");
        return resultMap;
    }

    @Override
    @Transactional
    public Map<String, Object> deleteVaccinationSiteById(Long id){
        VaccinationSite vaccinationSite = vaccinationSiteMapper.selectByPrimaryKey(id);
        if(vaccinationSite == null){
            throw new IllegalArgumentException("id为:"+id+"的接种点信息不存在");
        }

        vaccinationSiteMapper.deleteByExample()
                    .where(VaccinationSiteDynamicSqlSupport.id, isEqualTo(id))
                    .build()
                    .execute();

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg","删除接种点成功");
        return resultMap;
    }

    @Override
    @Transactional
    public Map<String, Object> updateVaccinationSite(VaccinationSiteReqDto vaccinationSiteReqDto) {
        //1.参数校验

        //2.判断vaccinationSite是否存在
        VaccinationSite vaccinationSite = vaccinationSiteMapper.selectByPrimaryKey(vaccinationSiteReqDto.getId());
        if(ObjUtil.isEmpty(vaccinationSite)){
            throw new IllegalArgumentException("id为:"+vaccinationSiteReqDto.getId()+"的接种点不存在");
        }

        //3.更新VaccinationSite
        //(1)复制VaccinationSiteDto中的请求参数到VaccinationSite
        VoPoConverterUtil.beanConverterNotNull(vaccinationSiteReqDto, vaccinationSite);

        //4.保存
        vaccinationSiteMapper.updateByPrimaryKey(vaccinationSite);

        //5.返回结果
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("msg","更新接种点信息成功");
        return resultMap;
    }

        @Override
        @Transactional
        public Map<String, Object> insertOrUpdateVaccinationSite(VaccinationSiteReqDto vaccinationSiteReqDto) {
            //1.参数校验

            //2.判断vaccinationSite是否存在
            VaccinationSite vaccinationSite = vaccinationSiteMapper.selectByPrimaryKey(vaccinationSiteReqDto.getId());
            if(ObjUtil.isEmpty(vaccinationSite)){
                throw new IllegalArgumentException("id为:"+vaccinationSiteReqDto.getId()+"的接种点不存在");
            }

            //3.更新VaccinationSite
            //(1)复制VaccinationSiteDto中的请求参数到VaccinationSite
            VoPoConverterUtil.beanConverterNotNull(vaccinationSiteReqDto, vaccinationSite);

            //4.保存
            vaccinationSiteMapper.updateByPrimaryKey(vaccinationSite);

            //5.返回结果
            Map<String,Object> resultMap = new HashMap<>();
            resultMap.put("msg","更新接种点信息成功");
            return resultMap;
        }

    @Override
    public Map<String, Object> getVaccinationSiteById(Long id){
        VaccinationSite e = vaccinationSiteMapper.selectByPrimaryKey(id);
        if(ObjUtil.isEmpty(e)){
            throw new IllegalArgumentException("id为:"+id+"的接种点信息不存在");
        }

        Map<String, Object> attr = new HashMap<>();
        attr.put("id", e.getId());
        attr.put("name", e.getName());
        attr.put("addressCode", e.getAddressCode());
        attr.put("orgId", e.getOrgId());
        attr.put("orgName", e.getOrgName());
        attr.put("contactName", e.getContactName());
        attr.put("contactMobile", e.getContactMobile());
        attr.put("status", e.getStatus());
        attr.put("orderNum", e.getOrderNum());
        attr.put("createTime", e.getCreateTime());
        return attr;
    }

    @Override
    public PageVo<Map<String, Object>> getVaccinationSiteListPageVo(VaccinationSitePageReqDto vaccinationSitePageReqDto){

        //1.查询列表
        List<VaccinationSite> list = queryListByPageReqDto(vaccinationSitePageReqDto,true);

        //2.构建pageVo
        PageVo<VaccinationSite> pageVo = new PageVo<>(list);

        //3.对象转为map分页输出
        return PageVo.of(
                pageVo,
                e -> {
                    Map<String, Object> attr = new HashMap<>();
                    attr.put("id", e.getId());
                    attr.put("name", e.getName());
                    attr.put("addressCode", e.getAddressCode());
                    attr.put("orgId", e.getOrgId());
                    attr.put("orgName", e.getOrgName());
                    attr.put("contactName", e.getContactName());
                    attr.put("contactMobile", e.getContactMobile());
                    attr.put("status", e.getStatus());
                    attr.put("orderNum", e.getOrderNum());
                    attr.put("createTime", e.getCreateTime());
                    return attr;
                }
        );
    }

    @Override
    public void downloadTemplateExcel(HttpServletResponse response){
        //1.查询到该对象的导入模板
        /*VaccinationSite vaccinationSite = vaccinationSiteMapper.selectByExampleOne()
                .where(VaccinationSiteDynamicSqlSupport.fileKey, isEqualTo("vaccinationSiteTemplateExcel"))
                .build()
                .execute();
        if(ObjUtil.isEmpty(vaccinationSite)){
            throw new IllegalArgumentException("模板文件暂未上传,请联系管理员");
        }
        //2.响应文件到页面
        minioUtil.download(vaccinationSite.getStorePath(),vaccinationSite.getName(),response);*/
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
        VaccinationSiteExcelListener vaccinationSiteExcelListener = new VaccinationSiteExcelListener(this,validator,fieldDupValidator);
        try {
            in = excelFile.getInputStream();
            EasyExcel.read(in, VaccinationSiteExcelVo.class, vaccinationSiteExcelListener).sheet().doRead();
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
    public void exportToExcel(VaccinationSitePageReqDto vaccinationSitePageReqDto,HttpServletResponse response){
        //1.查询列表
        List<VaccinationSite> vaccinationSiteList = queryListByPageReqDto(vaccinationSitePageReqDto,false);

        //2.将原始列表转为导出对象列表
        List<VaccinationSiteExcelVo> vaccinationSiteExcelVoList = vaccinationSiteList.stream().map(e -> {
            VaccinationSiteExcelVo vaccinationSiteExcelVo = VoPoConverterUtil.copyProperties(e, VaccinationSiteExcelVo.class);
            return vaccinationSiteExcelVo;
        }).collect(Collectors.toList());

        //3.获取到输出流
        OutputStream outputStream = null;
        try {
            //(1).文件名，表名，表头，格式
            String sheetName = "接种点";
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
                .head(VaccinationSiteExcelVo.class)
                .sheet("Sheet1")
                .doWrite(vaccinationSiteExcelVoList);
    }

    private List<VaccinationSite> queryListByPageReqDto(VaccinationSitePageReqDto vaccinationSitePageReqDto,Boolean needPaging){
        //1.查询语句构建
        QueryExpressionDSL<MyBatis3SelectModelAdapter<List<VaccinationSite>>>.QueryExpressionWhereBuilder builder = vaccinationSiteMapper.selectByExample().where();

        //2.根据查询条件封装查询命令
        //(1).关键字查询
        /*String name = vaccinationSitePageReqDto.getName();
        if(!ObjUtil.isEmpty(name)){
        builder.and(VaccinationSiteDynamicSqlSupport.name, isLike("%"+name+"%"));
        }

        Long type = vaccinationSitePageReqDto.getType();
        if(!ObjUtil.isEmpty(type)){
        builder.and(VaccinationSiteDynamicSqlSupport.type, isEqualTo(type));
        }

        Long startTime = vaccinationSitePageReqDto.getStartTime();
        Long endTime = vaccinationSitePageReqDto.getEndTime();
        if (startTime != null && endTime != null) {
        builder.and(VaccinationSiteDynamicSqlSupport.startTime, isGreaterThanOrEqualTo(startTime));
        builder.and(VaccinationSiteDynamicSqlSupport.endTime, isLessThanOrEqualTo(endTime));
        } else {
            if (startTime != null) {
            builder.and(VaccinationSiteDynamicSqlSupport.startTime, isGreaterThanOrEqualTo(startTime));
            }
            if (endTime != null) {
            builder.and(VaccinationSiteDynamicSqlSupport.endTime, isLessThanOrEqualTo(endTime));
            }
        }*/

        //3.排序
        builder.orderBy(VaccinationSiteDynamicSqlSupport.createTime.descending());

        //4.查询(不需要分页即列表)
        if(needPaging){
        PageHelper.startPage(vaccinationSitePageReqDto.getPageNum(), vaccinationSitePageReqDto.getPageSize());
        }

        List<VaccinationSite> list = builder.build().execute();
        return list;
    }
}