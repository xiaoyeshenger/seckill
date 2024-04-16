package com.zy.seckill.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.zy.seckill.common.bo.vo.PageVo;
import com.zy.seckill.sys.bo.dto.SysSettingReqDto;
import com.zy.seckill.sys.bo.dto.SysSettingPageReqDto;
import com.zy.seckill.sys.bo.po.SysSetting;
import com.zy.seckill.sys.mapper.ParamSettingDynamicSqlSupport;
import com.zy.seckill.sys.mapper.PostDynamicSqlSupport;
import com.zy.seckill.sys.mapper.SysSettingMapper;
import com.zy.seckill.sys.mapper.SysSettingDynamicSqlSupport;
import com.zy.seckill.common.utils.ObjUtil;
import com.zy.seckill.common.utils.VoPoConverterUtil;
import com.zy.seckill.sys.service.SysSettingService;
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
 * @Description //SysSettingService接口实现类
 * @Date 2022/03/09 09:38
 * @Param
 * @return
 **/
@Service
@Slf4j
@RequiredArgsConstructor
public class SysSettingServiceImpl implements SysSettingService {


    private final SysSettingMapper sysSettingMapper;


    @Override
    @Transactional
    public Map<String, Object> addSysSetting(Map<String, Object> headerMap,SysSettingReqDto sysSettingReqDto) {

        //1.设置参数
        //(1)复制SysSettingReqDto中的请求参数到SysSetting
        SysSetting sysSetting = VoPoConverterUtil.copyProperties(sysSettingReqDto, SysSetting.class);

        //(2)设置其他属性
        sysSetting.setCreateTime(System.currentTimeMillis());

        //3.保存
        sysSettingMapper.insert(sysSetting);

        //3.返回结果
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("msg","添加系统设置信息成功");
        return resultMap;
    }

    @Override
    @Transactional
    public Map<String, Object> deleteSysSettingById(Map<String, Object> headerMap,Long id){
        SysSetting sysSetting = sysSettingMapper.selectByPrimaryKey(id);
        if(sysSetting == null){
            throw new IllegalArgumentException("id为:"+id+"的系统设置信息不存在");
        }

        sysSettingMapper.deleteByExample()
                    .where(SysSettingDynamicSqlSupport.id, isEqualTo(id))
                    .build()
                    .execute();

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg","删除系统设置成功");
        return resultMap;
    }

    @Override
    @Transactional
    public Map<String, Object> updateSysSetting(Map<String, Object> headerMap,SysSettingReqDto sysSettingReqDto) {

        //1.判断sysSettingReqDto是否存在
        SysSetting sysSetting = sysSettingMapper.selectByPrimaryKey(sysSettingReqDto.getId());
        if(ObjUtil.isEmpty(sysSetting)){
            throw new IllegalArgumentException("id为:"+sysSettingReqDto.getId()+"的系统设置不存在");
        }

        //2.更新SysSetting
        //(1)复制SysSettingDto中的请求参数到SysSetting
        VoPoConverterUtil.beanConverterNotNull(sysSettingReqDto, sysSetting);

        //3.保存
        sysSettingMapper.updateByPrimaryKey(sysSetting);

        //4.返回结果
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("msg","更新系统设置信息成功");
        return resultMap;
    }

    @Override
    public Map<String, Object> getSysSettingById(Map<String, Object> headerMap,Long id){
        SysSetting e = sysSettingMapper.selectByPrimaryKey(id);
        if(ObjUtil.isEmpty(e)){
            throw new IllegalArgumentException("id为:"+id+"的系统设置信息不存在");
        }

        Map<String, Object> attr = new HashMap<>();
        attr.put("id", e.getId());
        attr.put("name", e.getName());
        attr.put("settingKey", e.getSettingKey());
        attr.put("settingValue", e.getSettingValue());
        attr.put("type", e.getType());
        attr.put("description", e.getDescription());
        attr.put("orderNum", e.getOrderNum());
        attr.put("createTime", e.getCreateTime());
        return attr;
    }

    @Override
    public PageVo<Map<String, Object>> getSysSettingListPageVo(Map<String, Object> headerMap,SysSettingPageReqDto sysSettingPageReqDto){

        //1.设置分页条件
        PageHelper.startPage(sysSettingPageReqDto.getPageNum(), sysSettingPageReqDto.getPageSize());

        //2.查询语句构建
        QueryExpressionDSL<MyBatis3SelectModelAdapter<List<SysSetting>>>.QueryExpressionWhereBuilder builder = sysSettingMapper.selectByExample().where();

        //3.根据查询条件封装查询命令
        //(1).关键字查询
        String name = sysSettingPageReqDto.getName();
        if(!ObjUtil.isEmpty(name)){
            builder.and(SysSettingDynamicSqlSupport.name, isLike("%"+name+"%"));
        }

        String settingKey = sysSettingPageReqDto.getSettingKey();
        if(!ObjUtil.isEmpty(settingKey)){
            builder.and(SysSettingDynamicSqlSupport.settingKey, isLike("%"+settingKey+"%"));
        }

        Long type = sysSettingPageReqDto.getType();
        if(!ObjUtil.isEmpty(type)){
            builder.and(SysSettingDynamicSqlSupport.type, isEqualTo(type));
        }

        //(2).排序
        builder.orderBy(SysSettingDynamicSqlSupport.orderNum.descending());

        //(3).查询
        List<SysSetting> list = builder.build().execute();

        //4.构建pageVo
        PageVo<SysSetting> pageVo = new PageVo<>(list);

        //5.对象转为map分页输出
        return PageVo.of(
                pageVo,
                e -> {
                    Map<String, Object> attr = new HashMap<>();
                    attr.put("id", e.getId());
                    attr.put("name", e.getName());
                    attr.put("settingKey", e.getSettingKey());
                    attr.put("settingValue", e.getSettingValue());
                    attr.put("type", e.getType());
                    attr.put("description", e.getDescription());
                    attr.put("orderNum", e.getOrderNum());
                    attr.put("createTime", e.getCreateTime());
                    return attr;
                }
        );
    }
}