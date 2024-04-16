package com.zy.seckill.inventory.service.impl;

import com.github.pagehelper.PageHelper;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.zy.seckill.common.bo.vo.PageVo;
import com.zy.seckill.inventory.bo.dto.SiteReqDto;
import com.zy.seckill.inventory.bo.dto.SitePageReqDto;
import com.zy.seckill.inventory.bo.po.Site;
import com.zy.seckill.inventory.mapper.SiteMapper;
import com.zy.seckill.inventory.mapper.SiteDynamicSqlSupport;
import com.zy.seckill.inventory.excel.SiteExcelListener;
import com.zy.seckill.inventory.excel.SiteExcelVo;
import com.zy.seckill.common.utils.*;
import com.zy.seckill.common.validator.FieldDupValidator;
import com.zy.seckill.inventory.service.SiteService;
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
 * @Description //SiteService接口实现类
 * @Date xxxx/02/27 14:32
 * @Param
 * @return
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class SiteServiceImpl implements SiteService {

    private final MinioUtil minioUtil;

    private final Validator validator;

    private final FieldDupValidator fieldDupValidator;

    private final SiteMapper siteMapper;


    @Override
    @Transactional
    public Map<String, Object> addSite(SiteReqDto siteReqDto) {

        //1.参数校验

        //2.设置参数
        //(1)复制SiteReqDto中的请求参数到Site
        Site site = VoPoConverterUtil.copyProperties(siteReqDto, Site.class);

        //(2)设置其他属性
        site.setId(new IdWorker().nextId()).setStatus((byte)1).setCreateTime(System.currentTimeMillis());

        //3.保存
        siteMapper.insert(site);

        //4.返回结果
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("msg","添加接种点信息成功");
        return resultMap;
    }

    @Override
    @Transactional
    public Map<String, Object> deleteSiteById(Long id){
        Site site = siteMapper.selectByPrimaryKey(id);
        if(site == null){
            throw new IllegalArgumentException("id为:"+id+"的接种点信息不存在");
        }

        siteMapper.deleteByExample()
                    .where(SiteDynamicSqlSupport.id, isEqualTo(id))
                    .build()
                    .execute();

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg","删除接种点成功");
        return resultMap;
    }

    @Override
    @Transactional
    public Map<String, Object> updateSite(SiteReqDto siteReqDto) {
        //1.参数校验

        //2.判断Site是否存在
        Site site = siteMapper.selectByPrimaryKey(siteReqDto.getId());
        if(ObjUtil.isEmpty(site)){
            throw new IllegalArgumentException("id为:"+ siteReqDto.getId()+"的接种点不存在");
        }

        //3.更新Site
        //(1)复制SiteDto中的请求参数到Site
        VoPoConverterUtil.beanConverterNotNull(siteReqDto, site);

        //4.保存
        siteMapper.updateByPrimaryKey(site);

        //5.返回结果
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("msg","更新接种点信息成功");
        return resultMap;
    }

        @Override
        @Transactional
        public Map<String, Object> insertOrUpdateSite(SiteReqDto siteReqDto) {
            //1.参数校验

            //2.判断Site是否存在
            Site site = siteMapper.selectByPrimaryKey(siteReqDto.getId());
            if(ObjUtil.isEmpty(site)){
                throw new IllegalArgumentException("id为:"+ siteReqDto.getId()+"的接种点不存在");
            }

            //3.更新Site
            //(1)复制SiteDto中的请求参数到Site
            VoPoConverterUtil.beanConverterNotNull(siteReqDto, site);

            //4.保存
            siteMapper.updateByPrimaryKey(site);

            //5.返回结果
            Map<String,Object> resultMap = new HashMap<>();
            resultMap.put("msg","更新接种点信息成功");
            return resultMap;
        }

    @Override
    public Map<String, Object> getSiteById(Long id){
        Site e = siteMapper.selectByPrimaryKey(id);
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
    public PageVo<Map<String, Object>> getSiteListPageVo(SitePageReqDto sitePageReqDto){

        //1.查询列表
        List<Site> list = queryListByPageReqDto(sitePageReqDto,true);

        //2.构建pageVo
        PageVo<Site> pageVo = new PageVo<>(list);

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
        /*Site Site = SiteMapper.selectByExampleOne()
                .where(SiteDynamicSqlSupport.fileKey, isEqualTo("SiteTemplateExcel"))
                .build()
                .execute();
        if(ObjUtil.isEmpty(Site)){
            throw new IllegalArgumentException("模板文件暂未上传,请联系管理员");
        }
        //2.响应文件到页面
        minioUtil.download(Site.getStorePath(),Site.getName(),response);*/
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
        SiteExcelListener siteExcelListener = new SiteExcelListener(this,validator,fieldDupValidator);
        try {
            in = excelFile.getInputStream();
            EasyExcel.read(in, SiteExcelVo.class, siteExcelListener).sheet().doRead();
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
    public void exportToExcel(SitePageReqDto sitePageReqDto, HttpServletResponse response){
        //1.查询列表
        List<Site> siteList = queryListByPageReqDto(sitePageReqDto,false);

        //2.将原始列表转为导出对象列表
        List<SiteExcelVo> siteExcelVoList = siteList.stream().map(e -> {
            SiteExcelVo siteExcelVo = VoPoConverterUtil.copyProperties(e, SiteExcelVo.class);
            return siteExcelVo;
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
                .head(SiteExcelVo.class)
                .sheet("Sheet1")
                .doWrite(siteExcelVoList);
    }

    private List<Site> queryListByPageReqDto(SitePageReqDto sitePageReqDto, Boolean needPaging){
        //1.查询语句构建
        QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Site>>>.QueryExpressionWhereBuilder builder = siteMapper.selectByExample().where();

        //2.根据查询条件封装查询命令
        //(1).关键字查询
        /*String name = SitePageReqDto.getName();
        if(!ObjUtil.isEmpty(name)){
        builder.and(SiteDynamicSqlSupport.name, isLike("%"+name+"%"));
        }

        Long type = SitePageReqDto.getType();
        if(!ObjUtil.isEmpty(type)){
        builder.and(SiteDynamicSqlSupport.type, isEqualTo(type));
        }

        Long startTime = SitePageReqDto.getStartTime();
        Long endTime = SitePageReqDto.getEndTime();
        if (startTime != null && endTime != null) {
        builder.and(SiteDynamicSqlSupport.startTime, isGreaterThanOrEqualTo(startTime));
        builder.and(SiteDynamicSqlSupport.endTime, isLessThanOrEqualTo(endTime));
        } else {
            if (startTime != null) {
            builder.and(SiteDynamicSqlSupport.startTime, isGreaterThanOrEqualTo(startTime));
            }
            if (endTime != null) {
            builder.and(SiteDynamicSqlSupport.endTime, isLessThanOrEqualTo(endTime));
            }
        }*/

        //3.排序
        builder.orderBy(SiteDynamicSqlSupport.createTime.descending());

        //4.查询(不需要分页即列表)
        if(needPaging){
        PageHelper.startPage(sitePageReqDto.getPageNum(), sitePageReqDto.getPageSize());
        }

        List<Site> list = builder.build().execute();
        return list;
    }
}