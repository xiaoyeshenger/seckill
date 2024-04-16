package com.zy.seckill.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.zy.seckill.common.bo.po.SysFile;
import com.zy.seckill.common.bo.vo.PageVo;
import com.zy.seckill.common.utils.*;
import com.zy.seckill.sys.bo.dto.PageTemplateReqDto;
import com.zy.seckill.sys.bo.dto.PageTemplatePageReqDto;
import com.zy.seckill.sys.bo.po.PageTemplate;
import com.zy.seckill.sys.mapper.PageTemplateMapper;
import com.zy.seckill.sys.mapper.PageTemplateDynamicSqlSupport;
import com.zy.seckill.common.redis.RedisService;

import com.zy.seckill.sys.mapper.PostDynamicSqlSupport;
import com.zy.seckill.sys.service.PageTemplateService;
import com.zy.seckill.sys.service.SysFileService;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.springframework.stereotype.Service;
import org.mybatis.dynamic.sql.select.MyBatis3SelectModelAdapter;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.*;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

/*
 * @Author zhangyong
 * @Description //PageTemplateService接口实现类
 * @Date 2022/04/26 11:24
 * @Param
 * @return
 **/
@Service
@Slf4j
@RequiredArgsConstructor
public class PageTemplateServiceImpl implements PageTemplateService {

    private final MinioUtil minioUtil;

    private final CacheUtil cacheUtil;

    private final RedisService redisService;

    private final SysFileService sysFileService;

    private final PageTemplateMapper pageTemplateMapper;



    @Override
    @Transactional
    public Map<String, Object> addPageTemplate(PageTemplateReqDto pageTemplateReqDto,MultipartHttpServletRequest request) {
        //1.参数校验
        //园区是否存在
        cacheUtil.getParkName(pageTemplateReqDto.getParkId());

        //字典是否存在
        cacheUtil.getDataDictName(pageTemplateReqDto.getPageType());
        cacheUtil.getDataDictName(pageTemplateReqDto.getTemplateType());

        PageTemplate pt = pageTemplateMapper.selectByExampleOne()
                .where(PageTemplateDynamicSqlSupport.templateType, isEqualTo(pageTemplateReqDto.getTemplateType()))
                .and(PageTemplateDynamicSqlSupport.pageKey, isEqualTo(pageTemplateReqDto.getPageKey()))
                .and(PageTemplateDynamicSqlSupport.parkId, isEqualTo(pageTemplateReqDto.getParkId()))
                .build()
                .execute();
        if (!ObjUtil.isEmpty(pt)) {
            throw new IllegalArgumentException("模板类型(templateType)为"+pageTemplateReqDto.getTemplateType()+"，页面主键(pageKey)为"+pageTemplateReqDto.getPageKey()+"的模板信息已存在,请确认后再提交");
        }

        //2.设置参数
        //(1)复制PageTemplateReqDto中的请求参数到PageTemplate
        PageTemplate pageTemplate = VoPoConverterUtil.copyProperties(pageTemplateReqDto, PageTemplate.class);

        //获取到缩略图文件
        MultipartFile imgFile = request.getFile("imgFile");
        if (!ObjUtil.isEmpty(imgFile)) {
            String suffix = FileUtil.getSuffix(imgFile.getOriginalFilename());
            String bucketName = "pageTemplate".toLowerCase();
            if (!minioUtil.existsBucket(bucketName)) {
                minioUtil.createBucket(bucketName);
            }
            String fileName = UUIDUtil.getUUID(28) + suffix;
            minioUtil.upload(bucketName,imgFile,fileName);
            pageTemplate.setPicUrl("/"+ bucketName +"/" + fileName);
        }

        //获取到geoJson文件
        MultipartFile geoJsonFile = request.getFile("geoJsonFile");
        if(pageTemplateReqDto.getTemplateType() == 803){
            if (ObjUtil.isEmpty(geoJsonFile)) {
                throw new IllegalArgumentException("GeoJson类型的模板,geoJson文件不能为空");
            }
            String suffix = FileUtil.getSuffix(geoJsonFile.getOriginalFilename());
            String bucketName = "pageTemplate".toLowerCase();
            if (!minioUtil.existsBucket(bucketName)) {
                minioUtil.createBucket(bucketName);
            }
            String fileName = UUIDUtil.getUUID(28) + suffix;
            minioUtil.upload(bucketName,geoJsonFile,fileName);
            pageTemplate.setGeoJsonUrl("/"+ bucketName +"/" + fileName);
        }

        //设置其他属性
        pageTemplate.setId(new IdWorker().nextId())
                .setCreateTime(System.currentTimeMillis())
                .setActiveFlag((byte)0);

        //3.保存
        pageTemplateMapper.insert(pageTemplate);

        //4.返回结果
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("msg","添加页面模板信息成功");
        return resultMap;
    }

    @Override
    @Transactional
    public Map<String, Object> deletePageTemplateById(Long id){
        PageTemplate pageTemplate = pageTemplateMapper.selectByPrimaryKey(id);
        if(pageTemplate == null){
            throw new IllegalArgumentException("id为:"+id+"的页面模板信息不存在");
        }

        //使用中不能删除
        if(pageTemplate.getActiveFlag() == 1){
            throw new IllegalArgumentException("该页面模板正在启用中,不能删除");
        }


        //类型为普通模板,并且为基础模板(M1),不能删除
        if(pageTemplate.getTemplateType() == 802 && "M1".equals(pageTemplate.getPageKey())){
            throw new IllegalArgumentException("该页面模板为基础模板,不能删除");
        }

        pageTemplateMapper.deleteByExample()
                    .where(PageTemplateDynamicSqlSupport.id, isEqualTo(id))
                    .build()
                    .execute();

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg","删除页面模板成功");
        return resultMap;
    }

    @Override
    @Transactional
    public Map<String, Object> updatePageTemplate(PageTemplateReqDto pageTemplateReqDto,MultipartHttpServletRequest request) {

        //1.参数校验
        //园区是否存在
        cacheUtil.getParkName(pageTemplateReqDto.getParkId());

        //字典是否存在
        cacheUtil.getDataDictName(pageTemplateReqDto.getPageType());
        cacheUtil.getDataDictName(pageTemplateReqDto.getTemplateType());

        //pageTemplate是否存在
        PageTemplate pageTemplate = pageTemplateMapper.selectByPrimaryKey(pageTemplateReqDto.getId());
        if(ObjUtil.isEmpty(pageTemplate)){
            throw new IllegalArgumentException("id为:"+pageTemplateReqDto.getId()+"的页面模板不存在");
        }

        if(!(pageTemplateReqDto.getTemplateType().equals(pageTemplate.getTemplateType()) || pageTemplateReqDto.getPageKey().equals(pageTemplate.getPageKey()))){
            PageTemplate pt = pageTemplateMapper.selectByExampleOne()
                    .where(PageTemplateDynamicSqlSupport.templateType, isEqualTo(pageTemplateReqDto.getTemplateType()))
                    .and(PageTemplateDynamicSqlSupport.pageKey, isEqualTo(pageTemplateReqDto.getPageKey()))
                    .and(PageTemplateDynamicSqlSupport.parkId, isEqualTo(pageTemplateReqDto.getParkId()))
                    .build()
                    .execute();
            if (!ObjUtil.isEmpty(pt)) {
                throw new IllegalArgumentException("模板类型(templateType)为"+pageTemplateReqDto.getTemplateType()+"，页面主键(pageKey)为"+pageTemplateReqDto.getPageKey()+"的模板信息已存在,请确认后再提交");
            }
        }

        //2.如果文件不为空,更新文件
        MultipartFile imgFile = request.getFile("imgFile");
        if (!ObjUtil.isEmpty(imgFile)) {
            String suffix = FileUtil.getSuffix(imgFile.getOriginalFilename());
            String bucketName = "pageTemplate".toLowerCase();
            if (!minioUtil.existsBucket(bucketName)) {
                minioUtil.createBucket(bucketName);
            }
            String fileName = UUIDUtil.getUUID(28) + suffix;
            minioUtil.upload(bucketName,imgFile,fileName);
            pageTemplate.setPicUrl("/"+ bucketName +"/" + fileName);
        }

        //获取到geoJson文件
        MultipartFile geoJsonFile = request.getFile("geoJsonFile");
        if (!ObjUtil.isEmpty(geoJsonFile)) {
            FileUtil.checkSuffix(geoJsonFile.getOriginalFilename(),".geojson");
            String suffix = FileUtil.getSuffix(geoJsonFile.getOriginalFilename());
            String bucketName = "pageTemplate".toLowerCase();
            if (!minioUtil.existsBucket(bucketName)) {
                minioUtil.createBucket(bucketName);
            }
            String fileName = UUIDUtil.getUUID(28) + suffix;
            minioUtil.upload(bucketName,geoJsonFile,fileName);
            pageTemplate.setGeoJsonUrl("/"+ bucketName +"/" + fileName);
        }


        //3.更新PageTemplate
        //(1)复制PageTemplateDto中的请求参数到PageTemplate
        VoPoConverterUtil.beanConverterNotNull(pageTemplateReqDto, pageTemplate);


        //4.保存
        pageTemplateMapper.updateByPrimaryKey(pageTemplate);

        //5.返回结果
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("msg","更新页面模板信息成功");
        return resultMap;
    }

    @Override
    public Map<String, Object> getPageTemplateById(Long id){
        PageTemplate e = pageTemplateMapper.selectByPrimaryKey(id);
        if(ObjUtil.isEmpty(e)){
            throw new IllegalArgumentException("id为:"+id+"的页面模板信息不存在");
        }

        Map<String, Object> attr = new HashMap<>();
        attr.put("id", e.getId());
        attr.put("name", e.getName());
        attr.put("pageType", e.getPageType());
        attr.put("pageKey", e.getPageKey());
        attr.put("templateType", e.getTemplateType());
        attr.put("description", e.getDescription());
        attr.put("picUrl", e.getPicUrl()==null?null:minioUtil.getEndpointUrl() + e.getPicUrl());
        attr.put("geoJsonUrl", e.getGeoJsonUrl()==null?null:minioUtil.getEndpointUrl() + e.getGeoJsonUrl());
        attr.put("orderNum", e.getOrderNum());
        attr.put("activeFlag", e.getActiveFlag());
        attr.put("parkId", e.getParkId());
        attr.put("path", e.getPath());
        attr.put("createTime", e.getCreateTime());
        return attr;
    }

    @Override
    public PageVo<Map<String, Object>> getPageTemplateListPageVo(PageTemplatePageReqDto pageTemplatePageReqDto){

        //1.设置分页条件
        PageHelper.startPage(pageTemplatePageReqDto.getPageNum(), pageTemplatePageReqDto.getPageSize());

        //2.查询语句构建
        QueryExpressionDSL<MyBatis3SelectModelAdapter<List<PageTemplate>>>.QueryExpressionWhereBuilder builder = pageTemplateMapper.selectByExample().where();

        //3.根据查询条件封装查询命令
        builder.and(PostDynamicSqlSupport.parkId, isEqualTo(pageTemplatePageReqDto.getParkId()));
        String name = pageTemplatePageReqDto.getName();
        if(!ObjUtil.isEmpty(name)){
            builder.and(PageTemplateDynamicSqlSupport.name, isLike("%"+name+"%"));
        }

        Long pageType = pageTemplatePageReqDto.getPageType();
        if(!ObjUtil.isEmpty(pageType)){
            builder.and(PageTemplateDynamicSqlSupport.pageType, isEqualTo(pageType));
        }

        String pageKey = pageTemplatePageReqDto.getPageKey();
        if(!ObjUtil.isEmpty(pageKey)){
            builder.and(PageTemplateDynamicSqlSupport.pageKey, isEqualTo(pageKey));
        }

        Long templateType = pageTemplatePageReqDto.getTemplateType();
        if(!ObjUtil.isEmpty(templateType)){
            builder.and(PageTemplateDynamicSqlSupport.templateType, isEqualTo(templateType));
        }

        Byte activeFlag = pageTemplatePageReqDto.getActiveFlag();
        if(!ObjUtil.isEmpty(activeFlag)){
            builder.and(PageTemplateDynamicSqlSupport.activeFlag, isEqualTo(activeFlag));
        }

        //(2).排序
        builder.orderBy(PageTemplateDynamicSqlSupport.orderNum.descending());
        //(3).查询
        List<PageTemplate> list = builder.build().execute();

        //4.构建pageVo
        PageVo<PageTemplate> pageVo = new PageVo<>(list);

        //5.对象转为map分页输出
        return PageVo.of(
                pageVo,
                e -> {
                    Map<String, Object> attr = new HashMap<>();
                    attr.put("id", e.getId());
                    attr.put("name", e.getName());
                    attr.put("pageType", e.getPageType());
                    attr.put("pageKey", e.getPageKey());
                    attr.put("templateType", e.getTemplateType());
                    attr.put("description", e.getDescription());
                    attr.put("picUrl", e.getPicUrl()==null?null:minioUtil.getEndpointUrl() + e.getPicUrl());
                    attr.put("geoJsonUrl", e.getGeoJsonUrl()==null?null:minioUtil.getEndpointUrl() + e.getGeoJsonUrl());
                    attr.put("orderNum", e.getOrderNum());
                    attr.put("activeFlag", e.getActiveFlag());
                    attr.put("parkId", e.getParkId());
                    attr.put("path", e.getPath());
                    attr.put("createTime", e.getCreateTime());
                    return attr;
                }
        );
    }

    @Override
    public Map<String, Object> enablePageTemplateById(Long id) {
        PageTemplate e = pageTemplateMapper.selectByPrimaryKey(id);
        if(ObjUtil.isEmpty(e)){
            throw new IllegalArgumentException("id为:"+id+"的页面模板信息不存在");
        }

        //当前状态为0再执行启用
        if(e.getActiveFlag() == 0){
            //将该页面类型(首页/统计页)的所有配置停用
            pageTemplateMapper.update(update(PageTemplateDynamicSqlSupport.pageTemplate)
                    .set(PageTemplateDynamicSqlSupport.activeFlag).equalToWhenPresent((byte)0)
                    .where(PageTemplateDynamicSqlSupport.pageType,isEqualTo(e.getPageType()))
                    .and(PageTemplateDynamicSqlSupport.parkId,isEqualTo(e.getParkId()))
                    .build()
                    .render(RenderingStrategies.MYBATIS3));
            //将当前这条配置信息启用
            pageTemplateMapper.update(update(PageTemplateDynamicSqlSupport.pageTemplate)
                    .set(PageTemplateDynamicSqlSupport.activeFlag).equalToWhenPresent((byte)1)
                    .where(PageTemplateDynamicSqlSupport.id,isEqualTo(id))
                    .build()
                    .render(RenderingStrategies.MYBATIS3));
        }
        Map<String, Object> attr = new HashMap<>();
        attr.put("msg", "启动配置成功");
        return attr;
    }

    @Override
    public PageTemplate getPageTemplateByParkId(Long parkId) {
        PageTemplate pageTemplate = pageTemplateMapper.selectByExampleOne()
                .where(PageTemplateDynamicSqlSupport.parkId, isEqualTo(parkId))
                .and(PageTemplateDynamicSqlSupport.activeFlag, isEqualTo((byte) 1))
                .build()
                .execute();
        return pageTemplate;
    }

    @Override
    public Map<String, Object> queryPageTemplateByParkId(Long parkId) {
        PageTemplate e = pageTemplateMapper.selectByExampleOne()
                .where(PageTemplateDynamicSqlSupport.parkId, isEqualTo(parkId))
                .and(PageTemplateDynamicSqlSupport.activeFlag, isEqualTo((byte) 1))
                .build()
                .execute();
        if(ObjUtil.isEmpty(e)){
            throw new IllegalArgumentException("园区ID为:"+parkId+"的页面模板信息不存在");
        }

        Map<String, Object> attr = new HashMap<>();
        attr.put("id", e.getId());
        attr.put("name", e.getName());
        attr.put("pageType", e.getPageType());
        attr.put("pageKey", e.getPageKey());
        attr.put("templateType", e.getTemplateType());
        attr.put("path", e.getPath());
        attr.put("description", e.getDescription());
        //attr.put("picUrl", e.getPicUrl()==null?null:redisService.hmGet(RedisKey.SYS_SETTING_KEY, RedisKey.FILE_ACCESS_PREFIX) + e.getPicUrl());
        //attr.put("geoJsonUrl", e.getGeoJsonUrl()==null?null:redisService.hmGet(RedisKey.SYS_SETTING_KEY, RedisKey.FILE_ACCESS_PREFIX) + e.getGeoJsonUrl());
        attr.put("picUrl", minioUtil.getEndpointUrl() + e.getPicUrl());
        attr.put("geoJsonUrl", minioUtil.getEndpointUrl() + e.getGeoJsonUrl());
        attr.put("orderNum", e.getOrderNum());
        attr.put("activeFlag", e.getActiveFlag());
//        attr.put("parkId", park.getId());
//        attr.put("parkName", park.getName());
//        attr.put("longitude", park.getLongitude());
//        attr.put("latitude", park.getLatitude());
//        attr.put("parentId", park.getParentId());
//        attr.put("parkType", park.getParkType());

        //List<Map<String, Object>> parkMapList = parkService.getParkMapListByParkId(user.getParkId());
        //userVo.setParkList(parkMapList);
        SysFile sysFile = sysFileService.getSysFileByFileKey(String.valueOf(parkId)+"logo");
        if(!ObjUtil.isEmpty(sysFile)){
            attr.put("parkLogo", minioUtil.getEndpointUrl() + sysFile.getAccessPath());
        }
        
        return attr;
    }
}