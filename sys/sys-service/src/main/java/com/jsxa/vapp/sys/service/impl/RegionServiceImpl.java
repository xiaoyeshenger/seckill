package com.jsxa.vapp.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.jsxa.vapp.common.bo.po.Region;
import com.jsxa.vapp.common.bo.vo.PageVo;
import com.jsxa.vapp.common.bo.vo.RegionVo;
import com.jsxa.vapp.common.cache.RegionCache;
import com.jsxa.vapp.common.mapper.RegionDynamicSqlSupport;
import com.jsxa.vapp.common.mapper.RegionMapper;
import com.jsxa.vapp.common.utils.ObjUtil;
import com.jsxa.vapp.common.utils.VoPoConverterUtil;
import com.jsxa.vapp.sys.bo.dto.RegionPageReqDto;
import com.jsxa.vapp.sys.bo.dto.RegionReqDto;
import com.jsxa.vapp.sys.service.RegionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.dynamic.sql.select.MyBatis3SelectModelAdapter;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isLike;

/*
 * @Author zhangyong
 * @Description //RegionService接口实现类
 * @Date 2021/09/16 16:43
 * @Param
 * @return
 **/
@Service
@Slf4j
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {

    private final RegionCache regionCache;

    private final RegionMapper regionMapper;


    @Override
    @Transactional
    public Map<String, Object> addRegion(RegionReqDto regionReqDto) {

        //1.设置参数
        //(1)复制RegionReqDto中的请求参数到Region
        Region region = VoPoConverterUtil.copyProperties(regionReqDto, Region.class);

        //3.保存
        regionMapper.insert(region);

        regionCache.reloadRegion();

        //3.返回结果
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg", "添加区域信息成功");
        return resultMap;
    }

    @Override
    @Transactional
    public Map<String, Object> deleteRegionById(Long id) {
        Region region = regionMapper.selectByPrimaryKey(id);
        if (region == null) {
            throw new IllegalArgumentException("id为:" + id + "的区域信息不存在");
        }

        regionMapper.deleteByExample()
                .where(RegionDynamicSqlSupport.id, isEqualTo(id))
                .build()
                .execute();

        regionCache.reloadRegion();

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg", "删除区域成功");
        return resultMap;
    }

    @Override
    @Transactional
    public Map<String, Object> updateRegion(RegionReqDto regionReqDto) {

        //1.判断regionReqDto是否存在
        Region region = regionMapper.selectByPrimaryKey(regionReqDto.getId());
        if (ObjUtil.isEmpty(region)) {
            throw new IllegalArgumentException("id为:" + regionReqDto.getId() + "的区域不存在");
        }

        //2.更新Region
        //(1)复制RegionDto中的请求参数到Region
        VoPoConverterUtil.beanConverterNotNull(regionReqDto, region);

        //3.保存
        regionMapper.updateByPrimaryKey(region);

        regionCache.reloadRegion();

        //4.返回结果
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg", "更新区域信息成功");
        return resultMap;
    }

    @Override
    public Map<String, Object> getRegionById(Long id) {
        Region e = regionMapper.selectByPrimaryKey(id);
        if (ObjUtil.isEmpty(e)) {
            throw new IllegalArgumentException("id为:" + id + "的区域信息不存在");
        }

        Map<String, Object> attr = new HashMap<>();
        attr.put("id", e.getId());
        attr.put("name", e.getName());
        attr.put("value", e.getValue());
        attr.put("code", e.getCode());
        attr.put("type", e.getType());
        attr.put("address", e.getAddress());
        attr.put("longitude", e.getLongitude());
        attr.put("latitude", e.getLatitude());
        attr.put("parentCode", e.getParentCode());
        attr.put("createTime", e.getCreateTime());
        return attr;
    }

    @Override
    public PageVo<Map<String, Object>> getRegionListPageVo(RegionPageReqDto regionPageReqDto) {

        //1.设置分页条件
        PageHelper.startPage(regionPageReqDto.getPageNum(), regionPageReqDto.getPageSize());

        //2.查询语句构建
        QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Region>>>.QueryExpressionWhereBuilder builder = regionMapper.selectByExample().where();

        //3.根据查询条件封装查询命令
        //(1).关键字查询
        String searchKey = regionPageReqDto.getSearchKey();
        if (!ObjUtil.isEmpty(searchKey)) {

            builder.and(RegionDynamicSqlSupport.name, isLike("%" + searchKey.trim() + "%"));
        }

        //(2).类型
        Long type = regionPageReqDto.getType();
        if (!ObjUtil.isEmpty(type)) {
            builder.and(RegionDynamicSqlSupport.type, isEqualTo(type));
        }

        //(3).编码
        String code = regionPageReqDto.getCode();
        if (!ObjUtil.isEmpty(code)) {
            builder.and(RegionDynamicSqlSupport.code, isEqualTo(code));
        }

        //2.排序
        builder.orderBy(RegionDynamicSqlSupport.createTime);

        //3.查询
        List<Region> list = builder.build().execute();

        //4.构建PageBean
        PageVo<Region> pageBean = new PageVo<>(list);

        //5.对象转为map分页输出
        return PageVo.of(
                pageBean,
                e -> {
                    Map<String, Object> attr = new HashMap<>();
                    attr.put("id", e.getId());
                    attr.put("name", e.getName());
                    attr.put("value", e.getValue());
                    attr.put("code", e.getCode());
                    attr.put("type", e.getType());
                    attr.put("address", e.getAddress());
                    attr.put("longitude", e.getLongitude());
                    attr.put("latitude", e.getLatitude());
                    attr.put("parentCode", e.getParentCode());
                    attr.put("createTime", e.getCreateTime());
                    return attr;
                }
        );
    }

    @Override
    public RegionVo getRegionByCode(String code) {
        return regionCache.getRegionVoByCode(code);
    }

    @Override
    public RegionVo getAllChildListByParentCode(String parentCode) {
        return regionCache.buildChildTreeByCode(parentCode,1);
    }

    @Override
    public RegionVo getAssignChildListByParentCode(String parentCode) {
        return regionCache.buildChildTreeByCode(parentCode,2);
    }

    public RegionVo getChildListByParentCode(String parentCode) {
        return regionCache.buildChildTreeByCode(parentCode);
    }

    @Override
    public Map<String, Object> getRegionVo() {
        List<RegionVo> voList = regionCache.getRegionVo();
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("data",voList);
        resultMap.put("msg","查询区域信息成功");
        return resultMap;
    }


    @Override
    public List<Map<String, Object>> getParentListByChildCode(String childCode) {
        List<RegionVo> regionList = regionCache.getParentListVoByCode(childCode);
        List<Map<String, Object>> mapList = new ArrayList<>();
        if (!ObjUtil.isEmpty(regionList)) {
            mapList = regionList.stream().map(e -> {
                Map<String, Object> attr = new HashMap<>();
                attr.put("name", e.getName());
                attr.put("type", e.getType());
                attr.put("code", e.getCode());
                attr.put("parentCode", e.getParentCode());
                return attr;
            }).collect(Collectors.toList());
        }
        return mapList;
    }

    @Override
    public List<Map<String, Object>> getCountyListByParentCode(String parentCode) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        List<RegionVo> childList = regionCache.getChildListByParentCodeAndChildType(parentCode, 406L);
        if (!ObjUtil.isEmpty(childList)) {
            mapList = childList.stream().map(e -> {
                Map<String, Object> attr = new HashMap<>();
                attr.put("name", e.getName());
                attr.put("type", e.getType());
                attr.put("code", e.getCode());
                attr.put("parentCode", e.getParentCode());
                return attr;
            }).collect(Collectors.toList());
        }
        return mapList;
    }
}