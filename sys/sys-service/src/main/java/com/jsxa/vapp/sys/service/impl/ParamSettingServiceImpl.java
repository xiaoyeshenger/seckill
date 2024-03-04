package com.jsxa.vapp.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.jsxa.vapp.common.bo.vo.PageVo;
import com.jsxa.vapp.common.redis.RedisKey;
import com.jsxa.vapp.common.redis.RedisService;
import com.jsxa.vapp.common.utils.CacheUtil;
import com.jsxa.vapp.common.utils.IdWorker;
import com.jsxa.vapp.sys.bo.dto.ParamSettingReqDto;
import com.jsxa.vapp.sys.bo.dto.ParamSettingPageReqDto;
import com.jsxa.vapp.sys.bo.po.ParamSetting;
import com.jsxa.vapp.sys.mapper.ParamSettingMapper;
import com.jsxa.vapp.sys.mapper.ParamSettingDynamicSqlSupport;
import com.jsxa.vapp.common.utils.ObjUtil;
import com.jsxa.vapp.common.utils.VoPoConverterUtil;
import com.jsxa.vapp.sys.service.ParamSettingService;
import org.springframework.stereotype.Service;
import org.mybatis.dynamic.sql.select.MyBatis3SelectModelAdapter;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;


import javax.annotation.Resource;
import java.io.*;
import java.util.*;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

/*
 * @Author zhangyong
 * @Description //ParamSettingService接口实现类
 * @Date 2022/03/22 14:08
 * @Param
 * @return
 **/
@Service
@Slf4j
@RequiredArgsConstructor
public class ParamSettingServiceImpl implements ParamSettingService {

    private final CacheUtil cacheUtil;

    private final ParamSettingMapper paramSettingMapper;


    @Override
    @Transactional
    public Map<String, Object> addParamSetting(Map<String, Object> headerMap,ParamSettingReqDto paramSettingReqDto) {
        //1.参数校验
        //园区是否存在
        cacheUtil.getParkName(paramSettingReqDto.getParkId());

        //数据字典是否存在
        cacheUtil.getDataDictName(paramSettingReqDto.getType());


        //2.设置参数
        //(1)复制ParamSettingReqDto中的请求参数到ParamSetting
        ParamSetting paramSetting = VoPoConverterUtil.copyProperties(paramSettingReqDto, ParamSetting.class);

        //(2)设置其他属性
        paramSetting.setId(new IdWorker().nextId()).setCreateTime(System.currentTimeMillis());

        //3.保存
        paramSettingMapper.insert(paramSetting);

        //4.返回结果
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("msg","添加参数设置信息成功");
        return resultMap;
    }

    @Override
    @Transactional
    public Map<String, Object> deleteParamSettingById(Map<String, Object> headerMap,Long id){
        ParamSetting paramSetting = paramSettingMapper.selectByPrimaryKey(id);
        if(paramSetting == null){
            throw new IllegalArgumentException("id为:"+id+"的参数设置信息不存在");
        }

        paramSettingMapper.deleteByExample()
                    .where(ParamSettingDynamicSqlSupport.id, isEqualTo(id))
                    .build()
                    .execute();

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg","删除参数设置成功");
        return resultMap;
    }

    @Override
    @Transactional
    public Map<String, Object> updateParamSetting(Map<String, Object> headerMap,ParamSettingReqDto paramSettingReqDto) {

        //园区是否存在
        cacheUtil.getParkName(paramSettingReqDto.getParkId());

        //数据字典是否存在
        cacheUtil.getDataDictName(paramSettingReqDto.getType());

        //1.判断paramSettingReqDto是否存在
        ParamSetting paramSetting = paramSettingMapper.selectByPrimaryKey(paramSettingReqDto.getId());
        if(ObjUtil.isEmpty(paramSetting)){
            throw new IllegalArgumentException("id为:"+paramSettingReqDto.getId()+"的参数设置不存在");
        }

        //2.更新ParamSetting
        //(1)复制ParamSettingDto中的请求参数到ParamSetting
        VoPoConverterUtil.beanConverterNotNull(paramSettingReqDto, paramSetting);

        //3.保存
        paramSettingMapper.updateByPrimaryKey(paramSetting);

        //4.返回结果
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("msg","更新参数设置信息成功");
        return resultMap;
    }

    @Override
    public Map<String, Object> getParamSettingById(Map<String, Object> headerMap,Long id){
        ParamSetting e = paramSettingMapper.selectByPrimaryKey(id);
        if(ObjUtil.isEmpty(e)){
            throw new IllegalArgumentException("id为:"+id+"的参数设置信息不存在");
        }

        Map<String, Object> attr = new HashMap<>();
        attr.put("id", e.getId());
        attr.put("name", e.getName());
        attr.put("paramKey", e.getParamKey());
        attr.put("paramValue", e.getParamValue());
        attr.put("type", e.getType());
        attr.put("description", e.getDescription());
        attr.put("orderNum", e.getOrderNum());
        attr.put("parkId", e.getParkId());
        attr.put("createTime", e.getCreateTime());
        return attr;
    }

    @Override
    public PageVo<Map<String, Object>> getParamSettingListPageVo(Map<String, Object> headerMap,ParamSettingPageReqDto paramSettingPageReqDto){

        //1.设置分页条件
        PageHelper.startPage(paramSettingPageReqDto.getPageNum(), paramSettingPageReqDto.getPageSize());

        //2.查询语句构建
        QueryExpressionDSL<MyBatis3SelectModelAdapter<List<ParamSetting>>>.QueryExpressionWhereBuilder builder = paramSettingMapper.selectByExample().where();

        //3.根据查询条件封装查询命令
        //(1).关键字查询
        builder.and(ParamSettingDynamicSqlSupport.parkId, isEqualTo(paramSettingPageReqDto.getParkId()));
        String name = paramSettingPageReqDto.getName();
        if(!ObjUtil.isEmpty(name)){
            builder.and(ParamSettingDynamicSqlSupport.name, isLike("%"+name+"%"));
        }

        String paramKey = paramSettingPageReqDto.getParamKey();
        if(!ObjUtil.isEmpty(paramKey)){
            builder.and(ParamSettingDynamicSqlSupport.paramKey, isLike("%"+paramKey+"%"));
        }

        Long type = paramSettingPageReqDto.getType();
        if(!ObjUtil.isEmpty(type)){
            builder.and(ParamSettingDynamicSqlSupport.type, isEqualTo(type));
        }

        //(2).排序
        builder.orderBy(ParamSettingDynamicSqlSupport.orderNum.descending());

        //(3).查询
        List<ParamSetting> list = builder.build().execute();

        //4.构建pageVo
        PageVo<ParamSetting> pageVo = new PageVo<>(list);

        //5.对象转为map分页输出
        return PageVo.of(
                pageVo,
                e -> {
                    Map<String, Object> attr = new HashMap<>();
                    attr.put("id", e.getId());
                    attr.put("name", e.getName());
                    attr.put("paramKey", e.getParamKey());
                    attr.put("paramValue", e.getParamValue());
                    attr.put("type", e.getType());
                    attr.put("description", e.getDescription());
                    attr.put("orderNum", e.getOrderNum());
                    attr.put("parkId", e.getParkId());
                    attr.put("createTime", e.getCreateTime());
                    return attr;
                }
        );
    }
}