package com.zy.seckill.common.cache;

import com.alibaba.fastjson.JSON;
import com.zy.seckill.common.bo.po.DataDict;
import com.zy.seckill.common.bo.vo.DataDictVo;
import com.zy.seckill.common.bo.vo.RegionVo;
import com.zy.seckill.common.mapper.DataDictMapper;
import com.zy.seckill.common.redis.RedisKey;
import com.zy.seckill.common.redis.RedisService;
import com.zy.seckill.common.utils.CommonUtils;
import com.zy.seckill.common.utils.ObjUtil;
import com.zy.seckill.common.utils.VoPoConverterUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/*
 * @Author: zhangyong
 * description: 数据字典本地缓存
 * @Date: xxxx-02-01 10:24
 * @Param:
 * @Return:
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class DictCache {

    private final DataDictMapper dataDictMapper;

    private final RedisService redisService;


    //(1).从源数据中，获取数据字典所有根节点(父ID为0)
    private List<DataDictVo> getRootNode() {
        List<DataDictVo> rootDataDictVoList = new ArrayList<>();
             for(DataDictVo dataDictVo : getSourceDataDictVoList()) {
                if(dataDictVo.getParentId().equals(0L)) {
                    rootDataDictVoList.add(dataDictVo);
                }
            }
        return rootDataDictVoList;
    }

    //(2).给每个根节点建立树形结构(相当于构建所有的数据字典为树形结构)
    public  List<DataDictVo> buildTree(List<DataDictVo> rootDataDictVoList){
    List<DataDictVo> treeDataDictVoList = new ArrayList<>();
        for(DataDictVo dataDictVo : rootDataDictVoList){
            dataDictVo = buildChildTree(dataDictVo);
            treeDataDictVoList.add(dataDictVo);
        }
        return treeDataDictVoList;
    }

    //(3).通过父级数据字典，递归建立子树形结构(直到没有子级)
    public DataDictVo buildChildTree(DataDictVo parentDataDictVo){
        List<DataDictVo> childDataDictVoList = new ArrayList<>();
        for(DataDictVo dataDictVo : getSourceDataDictVoList()) {
            if(dataDictVo.getParentId().equals(parentDataDictVo.getId())) {
                childDataDictVoList.add(buildChildTree(dataDictVo));
            }
        }
        parentDataDictVo.setChildList(childDataDictVoList);
        return parentDataDictVo;
    }

    //(4).通过父级Id构建所有的子级树形结构
    public DataDictVo buildChildTreeById(Long parentId){
        DataDictVo dataDictVoByCode = getDataDictVoById(parentId);
        if(ObjUtil.isEmpty(dataDictVoByCode)){
            throw new IllegalArgumentException("id为:"+parentId+"的数据字典不存在");
        }
        List<DataDictVo> childDataDictVoList = new ArrayList<>();
        for(DataDictVo dataDictVo : getSourceDataDictVoList()) {
            if(dataDictVo.getParentId().equals(dataDictVoByCode.getId())) {
                childDataDictVoList.add(buildChildTree(dataDictVo));
            }
        }
        dataDictVoByCode.setChildList(childDataDictVoList);
        return dataDictVoByCode;
    }

    //(5).通过父级value构建所有的子级树形结构
    public DataDictVo buildChildTreeByValue(String value){
        DataDictVo dataDictVoByCode = getDataDictVoByValue(value);
        if(ObjUtil.isEmpty(dataDictVoByCode)){
            throw new IllegalArgumentException("value为:"+value+"的数据字典不存在");
        }
        List<DataDictVo> childDataDictVoList = new ArrayList<>();
        for(DataDictVo dataDictVo : getSourceDataDictVoList()) {
            if(dataDictVo.getParentId().equals(dataDictVoByCode.getId())) {
                childDataDictVoList.add(buildChildTree(dataDictVo));
            }
        }
        dataDictVoByCode.setChildList(childDataDictVoList);
        return dataDictVoByCode;
    }

    //(6).通过父级name构建所有的子级树形结构
    public DataDictVo buildChildTreeByName(String name){
        DataDictVo dataDictVoByCode = getDataDictVoByName(name);
        if(ObjUtil.isEmpty(dataDictVoByCode)){
            throw new IllegalArgumentException("name为:"+name+"的数据字典不存在");
        }
        List<DataDictVo> childDataDictVoList = new ArrayList<>();
        for(DataDictVo dataDictVo : getSourceDataDictVoList()) {
            if(dataDictVo.getParentId().equals(dataDictVoByCode.getId())) {
                childDataDictVoList.add(buildChildTree(dataDictVo));
            }
        }
        dataDictVoByCode.setChildList(childDataDictVoList);
        return dataDictVoByCode;
    }

    //(7).通过name查询数据字典
    public DataDictVo getDataDictVoByName(String name){
        DataDictVo resultDataDictVo = null;
        for(DataDictVo dataDictVo : getSourceDataDictVoList()) {
            if(dataDictVo.getName().equals(name)) {
                resultDataDictVo = dataDictVo;
            }
        }
        return resultDataDictVo;
    }

    //(8).通过value查询数据字典
    public DataDictVo getDataDictVoByValue(String value){
        DataDictVo resultDataDictVo  = null;
        for(DataDictVo dataDictVo : getSourceDataDictVoList()) {
            if(dataDictVo.getValue().equals(value)) {
                resultDataDictVo = dataDictVo;
            }
        }
        return resultDataDictVo;
    }

    //(9).通过ID查询数据字典
    public DataDictVo getDataDictVoById(Long id){
        DataDictVo resultDataDictVo  = null;
        for(DataDictVo dataDictVo : getSourceDataDictVoList()) {
            if(dataDictVo.getId().equals(id)) {
                resultDataDictVo = dataDictVo;
            }
        }
        return resultDataDictVo;
    }

    //(10).获取所有的数据字典
    public  List<DataDictVo> getDataDictVo(){
        List<DataDictVo> dictVoList = getRootNode();
        List<DataDictVo> voList = buildTree(dictVoList);
        return voList;
    }


    public void getChildListByCode(DataDictVo dataDictVo, List<DataDictVo> childDictVoList) {
        childDictVoList.add(dataDictVo);

        //(2).继续遍历子集查找指定类型的数据
        List<DataDictVo> childList = dataDictVo.getChildList();
        if (!ObjUtil.isEmpty(childList)) {
            for (DataDictVo rv : childList) {
                getChildListByCode(rv, childDictVoList);
            }
        }
    }

    //(15).通过父级code查找所有的子级
    public Set<Long> getCodeListByParentId(Long parentId) {
        //(1).判断该父级是否存在
        DataDictVo dataDictVo = getDataDictVoById(parentId);
        if (ObjUtil.isEmpty(dataDictVo)) {
            throw new IllegalArgumentException("id为:" + dataDictVo + "的字典信息不存在");
        }

        //(2).获取该父级所有的子集
        DataDictVo buildChildTree = buildChildTree(dataDictVo);

        //(3).从所有子集中，并加入列表
        List<DataDictVo> childList = new ArrayList<>();

        getChildListByCode(buildChildTree, childList);

        if (CommonUtils.isEmpty(childList)) {
            childList = new ArrayList<>();
        }
        childList.add(dataDictVo);
        return childList.stream().map(DataDictVo::getId).collect(Collectors.toSet());
    }

    //(11).从redis/mysql获取所有数据字段
    private List<DataDictVo> getSourceDataDictVoList() {

        List<DataDictVo> dataDictVoList;
        String dataDictStr = (String) redisService.hmGet(RedisKey.SYS_DATA, RedisKey.DATA_DICT);
        if(ObjUtil.isEmpty(dataDictStr)){
            List<DataDict> dataDicList = dataDictMapper.selectByExample()
                    .build()
                    .execute();
            dataDictVoList = dataDicList.stream()
                    .map(dataDic -> {
                        return VoPoConverterUtil.copyProperties(dataDic, DataDictVo.class);
                    }).collect(Collectors.toList());
            redisService.hmSet(RedisKey.SYS_DATA, RedisKey.DATA_DICT,JSON.toJSONString(dataDictVoList));
        }else {
            dataDictVoList= JSON.parseArray(dataDictStr,DataDictVo.class);
        }

        return dataDictVoList;
    }

    //(12).同步数据字典到redis
    public void reloadDataDict() {
        List<DataDict> dataDictList = dataDictMapper.selectByExample()
                .build()
                .execute();
        List<DataDictVo> dataDictVoList = dataDictList.stream()
                .map(dataDict -> {
                    return VoPoConverterUtil.copyProperties(dataDict, DataDictVo.class);
                }).collect(Collectors.toList());
        redisService.hmSet(RedisKey.SYS_DATA, RedisKey.DATA_DICT,JSON.toJSONString(dataDictVoList));
    }
}
