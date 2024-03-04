package com.jsxa.vapp.inventory.service.impl;

import com.github.pagehelper.PageHelper;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.jsxa.vapp.common.bo.vo.PageVo;
import com.jsxa.vapp.common.redis.RedisKey;
import com.jsxa.vapp.common.redis.RedisService;
import com.jsxa.vapp.inventory.bo.dto.OrganizationReqDto;
import com.jsxa.vapp.inventory.bo.dto.OrganizationPageReqDto;
import com.jsxa.vapp.inventory.bo.po.Organization;
import com.jsxa.vapp.inventory.mapper.OrganizationMapper;
import com.jsxa.vapp.inventory.mapper.OrganizationDynamicSqlSupport;
import com.jsxa.vapp.inventory.excel.OrganizationExcelListener;
import com.jsxa.vapp.inventory.excel.OrganizationExcelVo;
import com.jsxa.vapp.common.utils.*;
import com.jsxa.vapp.common.redis.RedisService;
import com.jsxa.vapp.common.redis.RedisKey;
import com.jsxa.vapp.common.validator.FieldDupValidator;
import com.jsxa.vapp.inventory.service.OrganizationService;
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
 * @Description //OrganizationService接口实现类
 * @Date 2024/02/02 15:01
 * @Param
 * @return
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final MinioUtil minioUtil;

    private final Validator validator;

    private final FieldDupValidator fieldDupValidator;

    private final OrganizationMapper organizationMapper;


    @Override
    @Transactional
    public Map<String, Object> addOrganization(OrganizationReqDto organizationReqDto) {

        //1.参数校验

        //2.设置参数
        //(1)复制OrganizationReqDto中的请求参数到Organization
        Organization organization = VoPoConverterUtil.copyProperties(organizationReqDto, Organization.class);

        //(2)设置其他属性
        organization.setId(new IdWorker().nextId()).setStatus((byte)1).setCreateTime(System.currentTimeMillis());

        //3.保存
        organizationMapper.insert(organization);

        //4.返回结果
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("msg","添加组织机构信息成功");
        return resultMap;
    }

    @Override
    @Transactional
    public Map<String, Object> deleteOrganizationById(Long id){
        Organization organization = organizationMapper.selectByPrimaryKey(id);
        if(organization == null){
            throw new IllegalArgumentException("id为:"+id+"的组织机构信息不存在");
        }

        organizationMapper.deleteByExample()
                    .where(OrganizationDynamicSqlSupport.id, isEqualTo(id))
                    .build()
                    .execute();

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg","删除组织机构成功");
        return resultMap;
    }

    @Override
    @Transactional
    public Map<String, Object> updateOrganization(OrganizationReqDto organizationReqDto) {
        //1.参数校验

        //2.判断organization是否存在
        Organization organization = organizationMapper.selectByPrimaryKey(organizationReqDto.getId());
        if(ObjUtil.isEmpty(organization)){
            throw new IllegalArgumentException("id为:"+organizationReqDto.getId()+"的组织机构不存在");
        }

        //3.更新Organization
        //(1)复制OrganizationDto中的请求参数到Organization
        VoPoConverterUtil.beanConverterNotNull(organizationReqDto, organization);

        //4.保存
        organizationMapper.updateByPrimaryKey(organization);

        //5.返回结果
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("msg","更新组织机构信息成功");
        return resultMap;
    }

        @Override
        @Transactional
        public Map<String, Object> insertOrUpdateOrganization(OrganizationReqDto organizationReqDto) {
            //1.参数校验

            //2.判断organization是否存在
            Organization organization = organizationMapper.selectByPrimaryKey(organizationReqDto.getId());
            if(ObjUtil.isEmpty(organization)){
                throw new IllegalArgumentException("id为:"+organizationReqDto.getId()+"的组织机构不存在");
            }

            //3.更新Organization
            //(1)复制OrganizationDto中的请求参数到Organization
            VoPoConverterUtil.beanConverterNotNull(organizationReqDto, organization);

            //4.保存
            organizationMapper.updateByPrimaryKey(organization);

            //5.返回结果
            Map<String,Object> resultMap = new HashMap<>();
            resultMap.put("msg","更新组织机构信息成功");
            return resultMap;
        }

    @Override
    public Map<String, Object> getOrganizationById(Long id){
        Organization e = organizationMapper.selectByPrimaryKey(id);
        if(ObjUtil.isEmpty(e)){
            throw new IllegalArgumentException("id为:"+id+"的组织机构信息不存在");
        }

        Map<String, Object> attr = new HashMap<>();
        attr.put("id", e.getId());
        attr.put("addressCode", e.getAddressCode());
        attr.put("name", e.getName());
        attr.put("contactName", e.getContactName());
        attr.put("contactMobile", e.getContactMobile());
        attr.put("status", e.getStatus());
        attr.put("orderNum", e.getOrderNum());
        attr.put("createTime", e.getCreateTime());
        return attr;
    }

    @Override
    public PageVo<Map<String, Object>> getOrganizationListPageVo(OrganizationPageReqDto organizationPageReqDto){

        //1.查询列表
        List<Organization> list = queryListByPageReqDto(organizationPageReqDto,true);

        //2.构建pageVo
        PageVo<Organization> pageVo = new PageVo<>(list);

        //3.对象转为map分页输出
        return PageVo.of(
                pageVo,
                e -> {
                    Map<String, Object> attr = new HashMap<>();
                    attr.put("id", e.getId());
                    attr.put("addressCode", e.getAddressCode());
                    attr.put("name", e.getName());
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
        /*Organization organization = organizationMapper.selectByExampleOne()
                .where(OrganizationDynamicSqlSupport.fileKey, isEqualTo("organizationTemplateExcel"))
                .build()
                .execute();
        if(ObjUtil.isEmpty(organization)){
            throw new IllegalArgumentException("模板文件暂未上传,请联系管理员");
        }
        //2.响应文件到页面
        minioUtil.download(organization.getStorePath(),organization.getName(),response);*/
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
        OrganizationExcelListener organizationExcelListener = new OrganizationExcelListener(this,validator,fieldDupValidator);
        try {
            in = excelFile.getInputStream();
            EasyExcel.read(in, OrganizationExcelVo.class, organizationExcelListener).sheet().doRead();
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
    public void exportToExcel(OrganizationPageReqDto organizationPageReqDto,HttpServletResponse response){
        //1.查询列表
        List<Organization> organizationList = queryListByPageReqDto(organizationPageReqDto,false);

        //2.将原始列表转为导出对象列表
        List<OrganizationExcelVo> organizationExcelVoList = organizationList.stream().map(e -> {
            OrganizationExcelVo organizationExcelVo = VoPoConverterUtil.copyProperties(e, OrganizationExcelVo.class);
            return organizationExcelVo;
        }).collect(Collectors.toList());

        //3.获取到输出流
        OutputStream outputStream = null;
        try {
            //(1).文件名，表名，表头，格式
            String sheetName = "组织机构";
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
                .head(OrganizationExcelVo.class)
                .sheet("Sheet1")
                .doWrite(organizationExcelVoList);
    }

    private List<Organization> queryListByPageReqDto(OrganizationPageReqDto organizationPageReqDto,Boolean needPaging){
        //1.查询语句构建
        QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Organization>>>.QueryExpressionWhereBuilder builder = organizationMapper.selectByExample().where();

        //2.根据查询条件封装查询命令
        //(1).关键字查询
        /*String name = organizationPageReqDto.getName();
        if(!ObjUtil.isEmpty(name)){
        builder.and(OrganizationDynamicSqlSupport.name, isLike("%"+name+"%"));
        }

        Long type = organizationPageReqDto.getType();
        if(!ObjUtil.isEmpty(type)){
        builder.and(OrganizationDynamicSqlSupport.type, isEqualTo(type));
        }

        Long startTime = organizationPageReqDto.getStartTime();
        Long endTime = organizationPageReqDto.getEndTime();
        if (startTime != null && endTime != null) {
        builder.and(OrganizationDynamicSqlSupport.startTime, isGreaterThanOrEqualTo(startTime));
        builder.and(OrganizationDynamicSqlSupport.endTime, isLessThanOrEqualTo(endTime));
        } else {
            if (startTime != null) {
            builder.and(OrganizationDynamicSqlSupport.startTime, isGreaterThanOrEqualTo(startTime));
            }
            if (endTime != null) {
            builder.and(OrganizationDynamicSqlSupport.endTime, isLessThanOrEqualTo(endTime));
            }
        }*/

        //3.排序
        builder.orderBy(OrganizationDynamicSqlSupport.createTime.descending());

        //4.查询(不需要分页即列表)
        if(needPaging){
        PageHelper.startPage(organizationPageReqDto.getPageNum(), organizationPageReqDto.getPageSize());
        }

        List<Organization> list = builder.build().execute();
        return list;
    }
}