package com.jsxa.vapp.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.jsxa.vapp.common.bo.po.DataDict;
import com.jsxa.vapp.common.bo.vo.DataDictVo;
import com.jsxa.vapp.common.bo.vo.PageVo;
import com.jsxa.vapp.common.cache.DictCache;
import com.jsxa.vapp.common.mapper.DataDictDynamicSqlSupport;
import com.jsxa.vapp.common.mapper.DataDictMapper;
import com.jsxa.vapp.common.mapper.UserDynamicSqlSupport;
import com.jsxa.vapp.common.redis.RedisKey;
import com.jsxa.vapp.common.redis.RedisService;
import com.jsxa.vapp.common.utils.CacheUtil;
import com.jsxa.vapp.common.utils.ObjUtil;
import com.jsxa.vapp.common.utils.PinYinUtil;
import com.jsxa.vapp.common.utils.VoPoConverterUtil;
import com.jsxa.vapp.sys.bo.dto.DataDictPageReqDto;
import com.jsxa.vapp.sys.bo.dto.DataDictReqDto;
import com.jsxa.vapp.sys.service.DataDictService;
import lombok.RequiredArgsConstructor;
import org.mybatis.dynamic.sql.select.MyBatis3SelectModelAdapter;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mybatis.dynamic.sql.SqlBuilder.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isGreaterThanOrEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isLessThanOrEqualTo;

@Service
@RequiredArgsConstructor
public class DataDictServiceImpl implements DataDictService {

    private final CacheUtil cacheUtil;

    private final RedisService redisService;

    private final DictCache dictCache;

    private final DataDictMapper dataDictMapper;

    @Override
    public Map<String, Object> getDataDictTreeById(Long id) {
        DataDictVo dataDictVo = dictCache.buildChildTreeById(id);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("data",dataDictVo.getChildList());
        resultMap.put("msg","查询字典信息成功");
        return resultMap;
    }

    @Override
    public Map<String, Object> getDataDict() {
        List<DataDictVo> voList = dictCache.getDataDictVo();
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("data",voList);
        resultMap.put("msg","查询字典信息成功");
        return resultMap;
    }

    @Override
    public Map<String, Object> addDataDict(DataDictReqDto dataDictReqDto) {
        //1.基本参数校验
        //(1).父级字典是否存在
        Long parentId = dataDictReqDto.getParentId();
        if(parentId != 0){
            cacheUtil.getDataDictName(parentId);
        }

        //2.设置参数
        //(1)复制DeptReqDto中的请求参数到Dept
        DataDict dataDict = VoPoConverterUtil.copyProperties(dataDictReqDto, DataDict.class);

        //(2)设置其他属性
        String value = PinYinUtil.getPinYinHeadChar(dataDictReqDto.getName());
        dataDict.setValue(value);
        dataDict.setMutex(1);
        dataDict.setMultiple(true);
        dataDict.setSelected(false);
        dataDict.setAlias(dataDict.getName());
        dataDict.setCreateTime(System.currentTimeMillis());

        //3.保存
        dataDictMapper.insert(dataDict);

        //4.同步字典信息
        dictCache.reloadDataDict();
        redisService.hmSet(RedisKey.SYS_DATADICT_KEY, String.valueOf(dataDict.getId()), dataDict.getName());

        //5.返回结果
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("msg","添加字典成功");
        return resultMap;
    }

    @Override
    public Map<String, Object> deleteDataDictById(Long id) {
        dataDictMapper.deleteByExample()
                .where(UserDynamicSqlSupport.id, isEqualTo(id))
                .build()
                .execute();
        //同步字典信息
        dictCache.reloadDataDict();
        redisService.hmDelete(RedisKey.SYS_DATADICT_KEY, String.valueOf(id));
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg","删除字典成功");
        return resultMap;
    }

    @Override
    public Map<String, Object> updateDataDict(DataDictReqDto dataDictReqDto) {

        //1.基本参数校验
        //(1).字典是否存在
        Long id = dataDictReqDto.getId();
        DataDict dataDict = dataDictMapper.selectByPrimaryKey(id);
        if(ObjUtil.isEmpty(dataDict)){
            throw new IllegalArgumentException("id为:"+id+"的字典不存在");
        }

        //(2).父级字典是否存在
        Long parentId = dataDictReqDto.getParentId();
        if(parentId != 0){
            cacheUtil.getDataDictName(parentId);
        }

        //2.其他参数
        VoPoConverterUtil.beanConverterNotNull(dataDictReqDto, dataDict);
        String value = PinYinUtil.getPinYinHeadChar(dataDictReqDto.getName());
        dataDict.setValue(value);
        dataDict.setAlias(dataDict.getName());
        dataDictMapper.updateByPrimaryKey(dataDict);

        //3.同步字典信息
        dictCache.reloadDataDict();
        redisService.hmSet(RedisKey.SYS_DATADICT_KEY, String.valueOf(dataDict.getId()), dataDict.getName());

        //4.返回结果
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("msg","更新字典成功");
        return resultMap;
    }

    @Override
    public Map<String, Object> getDataDictById(Long id) {
        DataDict e = dataDictMapper.selectByPrimaryKey(id);
        if(ObjUtil.isEmpty(e)){
            throw new IllegalArgumentException("id为:"+id+"的字典不存在");
        }

        Map<String, Object> attr = new HashMap<>();
        attr.put("id", e.getId());
        attr.put("name", e.getName());
        attr.put("value", e.getValue());
        attr.put("parentId", e.getParentId());
        attr.put("status", e.getStatus());
        attr.put("orderNum", e.getOrderNum());
        attr.put("createTime", e.getCreateTime());
        return attr;
    }

    @Override
    public PageVo<Map<String, Object>> getDataDictListPageVo(String userInfo, DataDictPageReqDto dataDictPageReqDto){

        //1.设置分页条件
        PageHelper.startPage(dataDictPageReqDto.getPageNum(), dataDictPageReqDto.getPageSize());

        //2.查询语句构建
        QueryExpressionDSL<MyBatis3SelectModelAdapter<List<DataDict>>>.QueryExpressionWhereBuilder builder = dataDictMapper.selectByExample().where();

        //3.根据查询条件封装查询命令
        //(1).关键字查询
        String name = dataDictPageReqDto.getName();
        if(!ObjUtil.isEmpty(name)){
            builder.and(DataDictDynamicSqlSupport.name, isLike("%"+name+"%"));
        }

        Byte status = dataDictPageReqDto.getStatus();
        if(!ObjUtil.isEmpty(status)){
            builder.and(DataDictDynamicSqlSupport.status, isEqualTo(status));
        }

        Long startTime = dataDictPageReqDto.getStartTime();
        Long endTime = dataDictPageReqDto.getEndTime();
        if (startTime != null && endTime != null) {
            builder.and(DataDictDynamicSqlSupport.createTime, isGreaterThanOrEqualTo(startTime));
            builder.and(DataDictDynamicSqlSupport.createTime, isLessThanOrEqualTo(endTime));
        } else {
            if (startTime != null) {
                builder.and(DataDictDynamicSqlSupport.createTime, isGreaterThanOrEqualTo(startTime));
            }
            if (endTime != null) {
                builder.and(DataDictDynamicSqlSupport.createTime, isLessThanOrEqualTo(endTime));
            }
        }

        //(2).排序
        builder.orderBy(DataDictDynamicSqlSupport.orderNum.descending());

        //(3).查询
        List<DataDict> list = builder.build().execute();

        //4.构建pageVo
        PageVo<DataDict> pageVo = new PageVo<>(list);

        //5.对象转为map分页输出
        return PageVo.of(
                pageVo,
                e -> {
                    Map<String, Object> attr = new HashMap<>();
                    attr.put("id", e.getId());
                    attr.put("name", e.getName());
                    attr.put("value", e.getValue());
                    attr.put("parentId", e.getParentId());
                    attr.put("status", e.getStatus());
                    attr.put("orderNum", e.getOrderNum());
                    attr.put("createTime", e.getCreateTime());
                    return attr;
                }
        );
    }

}
