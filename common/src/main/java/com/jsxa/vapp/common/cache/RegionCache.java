package com.jsxa.vapp.common.cache;

import com.alibaba.fastjson.JSON;
import com.jsxa.vapp.common.bo.po.DataDict;
import com.jsxa.vapp.common.bo.po.Region;
import com.jsxa.vapp.common.mapper.DataDictMapper;
import com.jsxa.vapp.common.mapper.RegionDynamicSqlSupport;
import com.jsxa.vapp.common.mapper.RegionMapper;
import com.jsxa.vapp.common.redis.RedisKey;
import com.jsxa.vapp.common.redis.RedisService;
import com.jsxa.vapp.common.utils.ChineseCharacterUtil;
import com.jsxa.vapp.common.utils.CommonUtils;
import com.jsxa.vapp.common.utils.ObjUtil;
import com.jsxa.vapp.common.utils.VoPoConverterUtil;
import com.jsxa.vapp.common.bo.vo.RegionVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.dynamic.sql.where.condition.IsEqualTo;
import org.mybatis.dynamic.sql.where.condition.IsNotEqualTo;
import org.mybatis.dynamic.sql.where.condition.IsNotIn;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

/*
 * @Author: zhangyong
 * description: 区域信息本地缓存
 * @Date: 2021-02-01 10:24
 * @Param:
 * @Return:
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class RegionCache {

    private final RedisService redisService;

    private final RegionMapper regionMapper;

    private final DataDictMapper dataDictMapper;

    private List<RegionVo> sourceRegionVoList = new ArrayList<>();


    //(1).从源数据中，获取区域数据所有根节点(父code为0)
    private List<RegionVo> getRootNode() {
        List<RegionVo> rootRegionVoList = new ArrayList<>();
        for (RegionVo regionVo : sourceRegionVoList) {
            if (regionVo.getParentCode().equals("0")) {
                rootRegionVoList.add(regionVo);
            }
        }
        return rootRegionVoList;
    }

    //(2).给每个根节点建立树形结构(相当于构建所有的区域信息为树形结构)
    public List<RegionVo> buildTree(List<RegionVo> rootRegionVoList) {
        List<RegionVo> treeRegionVoList = new ArrayList<>();
        for (RegionVo regionVo : rootRegionVoList) {
            regionVo = buildChildTree(regionVo);
            treeRegionVoList.add(regionVo);
        }
        return treeRegionVoList;
    }

    //(3).通过父级区域信息，递归建立自己子集树形结构(直到没有子级)
    public RegionVo buildChildTree(RegionVo parentRegionVo) {
        List<RegionVo> childRegionVoList = new ArrayList<>();
        for (RegionVo regionVo : sourceRegionVoList) {
            if (regionVo.getType() == 403 || regionVo.getType() == 404 || regionVo.getType() == 405 || regionVo.getType() == 406 || regionVo.getType() == 407 || regionVo.getType() == 408 || regionVo.getType() == 410) {
                if (regionVo.getParentCode().equals(parentRegionVo.getCode())) {
                    childRegionVoList.add(buildChildTree(regionVo));
                }
            }
        }
        parentRegionVo.setChildList(childRegionVoList);
        return parentRegionVo;
    }

    //(3).通过父级区域信息，递归建立自己子集树形结构(直到没有子级)
    public RegionVo buildChildTree(RegionVo parentRegionVo, Integer dataType) {
        List<RegionVo> childRegionVoList = new ArrayList<>();
        for (RegionVo regionVo : sourceRegionVoList) {
            if (regionVo.getType() == 403 || regionVo.getType() == 404 || regionVo.getType() == 405 || regionVo.getType() == 406 || regionVo.getType() == 407 || regionVo.getType() == 408 || regionVo.getType() == 410) {
                if (regionVo.getParentCode().equals(parentRegionVo.getCode()) && Objects.equals(regionVo.getDataType(), dataType)) {
                    childRegionVoList.add(buildChildTree(regionVo));
                }
            }
        }
        parentRegionVo.setChildList(childRegionVoList);
        return parentRegionVo;
    }

    //(4).通过父级code构建所有的子级树形结构
    public RegionVo buildChildTreeByCode(String code, Integer dataType) {
        RegionVo rv = getRegionVoByCode(code);
        if (ObjUtil.isEmpty(rv)) {
            throw new IllegalArgumentException("code为:" + code + "的区域信息不存在");
        }
        List<RegionVo> childRegionVoList = new ArrayList<>();
        for (RegionVo regionVo : sourceRegionVoList) {
            if (regionVo.getType() == 403 || regionVo.getType() == 404 || regionVo.getType() == 405 || regionVo.getType() == 406 || regionVo.getType() == 407 || regionVo.getType() == 408 || regionVo.getType() == 410) {
                if (regionVo.getParentCode().equals(rv.getCode()) && Objects.equals(regionVo.getDataType(), dataType)) {
                    childRegionVoList.add(buildChildTree(regionVo, dataType));
                }
            }
        }
        rv.setChildList(childRegionVoList);
        return rv;
    }


    //(4).通过父级code构建所有的子级树形结构
    public RegionVo buildChildTreeByCode(String code) {
        RegionVo rv = getRegionVoByCode(code);
        if (ObjUtil.isEmpty(rv)) {
            throw new IllegalArgumentException("code为:" + code + "的区域信息不存在");
        }
        List<RegionVo> childRegionVoList = new ArrayList<>();
        for (RegionVo regionVo : sourceRegionVoList) {
            if (regionVo.getType() == 403 || regionVo.getType() == 404 || regionVo.getType() == 405 || regionVo.getType() == 406 || regionVo.getType() == 407 || regionVo.getType() == 408 || regionVo.getType() == 410) {
                if (regionVo.getParentCode().equals(rv.getCode())) {
                    childRegionVoList.add(buildChildTree(regionVo));
                }
            }
        }
        rv.setChildList(childRegionVoList);
        return rv;
    }

    //(5).通过父级value构建所有的子级树形结构
    public RegionVo buildChildTreeByValue(String value) {
        RegionVo rv = getRegionVoByValue(value);
        if (ObjUtil.isEmpty(rv)) {
            throw new IllegalArgumentException("value为:" + value + "的区域信息不存在");
        }
        List<RegionVo> childRegionVoList = new ArrayList<>();
        for (RegionVo regionVo : sourceRegionVoList) {
            if (regionVo.getParentCode().equals(rv.getCode())) {
                childRegionVoList.add(buildChildTree(regionVo));
            }
        }
        rv.setChildList(childRegionVoList);
        return rv;
    }

    //(6).通过父级name构建所有的子级树形结构
    public RegionVo buildChildTreeByName(String name) {
        RegionVo rv = getRegionVoByName(name);
        if (ObjUtil.isEmpty(rv)) {
            throw new IllegalArgumentException("name为:" + name + "的区域信息不存在");
        }
        List<RegionVo> childRegionVoList = new ArrayList<>();
        for (RegionVo regionVo : sourceRegionVoList) {
            if (regionVo.getParentCode().equals(rv.getCode())) {
                childRegionVoList.add(buildChildTree(regionVo));
            }
        }
        rv.setChildList(childRegionVoList);
        return rv;
    }

    //(7).通过code查询区域信息
    public RegionVo getRegionVoByCode(String code) {
        RegionVo resultRegionVo = null;
        for (RegionVo regionVo : sourceRegionVoList) {
            if (regionVo.getCode().equals(code)) {
                resultRegionVo = regionVo;
                return resultRegionVo;
            }
        }
        return resultRegionVo;
    }

    //(8).通过value查询区域信息
    public RegionVo getRegionVoByValue(String value) {
        RegionVo resultRegionVo = null;
        for (RegionVo regionVo : sourceRegionVoList) {
            if (regionVo.getValue().equals(value)) {
                resultRegionVo = regionVo;
            }
        }
        return resultRegionVo;
    }


    //(9).通过code查询区域信息
    public RegionVo getRegionVoByName(String name) {
        RegionVo resultRegionVo = null;
        for (RegionVo regionVo : sourceRegionVoList) {
            if (regionVo.getName().equals(name)) {
                resultRegionVo = regionVo;
            }
        }
        return resultRegionVo;
    }

    public RegionVo getRegionVoByNameAndParentName(String name, String parentName) {
        RegionVo resultRegionVo = null;
        for (RegionVo regionVo : sourceRegionVoList) {
            if (regionVo.getName().equals(name)) {
                String parentCode = regionVo.getParentCode();
                RegionVo parentRegionVo = getRegionVoByCode(parentCode);
                if (parentRegionVo.getName().equals(parentName)) {
                    resultRegionVo = regionVo;
                }
            }
        }
        return resultRegionVo;
    }

    //(10).获取所有的区域信息
    public List<RegionVo> getRegionVo() {
        List<RegionVo> dictVoList = getRootNode();
        List<RegionVo> voList = buildTree(dictVoList);
        return voList;
    }

    //(11).获取所有的区域信息非树状
    public List<RegionVo> getRegionVoNoTree() {
        List<Region> regionList = regionMapper.selectByExample().where()
                .and(RegionDynamicSqlSupport.type, isNotEqualTo(409L))
                .build()
                .execute();
        List<RegionVo> regionVoList = regionList.stream()
                .map(r -> {
                    DataDict dataDict = dataDictMapper.selectByPrimaryKey(r.getType());
                    RegionVo regionVo = VoPoConverterUtil.copyProperties(r, RegionVo.class);
                    regionVo.setTypeCn(CommonUtils.isNotEmpty(dataDict) ? dataDict.getValue() : "");
                    return regionVo;
                }).collect(Collectors.toList());
        return regionVoList;
    }

    //(11).查找指定类型的区域信息
    public List<RegionVo> getChildList(RegionVo regionVo, Long childType) {
        //(1).是指定的类型,添加到结果列表
        List<RegionVo> childRegionVoList = null;

        if (CommonUtils.isEmpty(childRegionVoList)) {
            childRegionVoList = new ArrayList<>();
        }

        Long type = regionVo.getType();
        if (childType.equals(type)) {
            childRegionVoList.add(regionVo);
        }

        //(2).继续遍历子集查找指定类型的数据
        List<RegionVo> childList = regionVo.getChildList();
        if (!ObjUtil.isEmpty(childList)) {
            for (RegionVo rv : childList) {
                getChildList(rv, childType);
            }
        }
        return childRegionVoList;
    }

    //(12).通过父级code查找指定类型的子集(比如 父级code为乡镇,可以指定查询所有的小区,也可指定查询所有的社区)
    public List<RegionVo> getChildListByParentCodeAndChildType(String parentCode, Long childType) {
        //(1).判断该父级是否存在
        RegionVo regionVoByCode = getRegionVoByCode(parentCode);
        if (ObjUtil.isEmpty(regionVoByCode)) {
            throw new IllegalArgumentException("code为:" + parentCode + "的区域信息不存在");
        }

        //(2).获取该父级所有的子集
        RegionVo regionVo = buildChildTreeByCode(parentCode, 1);

        //(3).从所有子集中找出类型为childType(比如COMMUNITY/VILLAGE)的RegionVo，并加入列表
        List<RegionVo> childList = new ArrayList<>();
        getChildList(regionVo,childType,childList);

        if (CommonUtils.isEmpty(childList)) {
            childList = new ArrayList<>();
        }
        if (regionVoByCode.getType() == childType) {
            childList.add(regionVoByCode);
        }
        return childList;
    }


    //(11).查找指定类型的区域信息
    public void getChildList(RegionVo regionVo, Long childType, List<RegionVo> childRegionVoList) {
        //(1).是指定的类型,添加到结果列表
        Long type = regionVo.getType();
        if (childType.equals(type)) {
            childRegionVoList.add(regionVo);
        }

        //(2).继续遍历子集查找指定类型的数据
        List<RegionVo> childList = regionVo.getChildList();
        if (!ObjUtil.isEmpty(childList)) {
            for (RegionVo rv : childList) {
                getChildList(rv, childType, childRegionVoList);
            }
        }
    }

    //(12).通过父级code查找指定类型的子集(比如 父级code为乡镇,可以指定查询所有的小区,也可指定查询所有的社区)
    public Set<String> getCodeListByParentCodeAndChildType(String parentCode, Long childType) {
        //(1).判断该父级是否存在
        RegionVo regionVoByCode = getRegionVoByCode(parentCode);
        if (ObjUtil.isEmpty(regionVoByCode)) {
            throw new IllegalArgumentException("code为:" + parentCode + "的区域信息不存在");
        }

        //(2).获取该父级所有的子集
        RegionVo regionVo = buildChildTreeByCode(parentCode, 1);

        //(3).从所有子集中找出类型为childType(比如COMMUNITY/VILLAGE)的RegionVo，并加入列表
        List<RegionVo> childList = new ArrayList<>();

        getChildList(regionVo, childType, childList);

        if (CommonUtils.isEmpty(childList)) {
            childList = new ArrayList<>();
        }
        if (Objects.equals(regionVoByCode.getType(), childType)) {
            childList.add(regionVoByCode);
        }
        return childList.stream().map(RegionVo::getCode).collect(Collectors.toSet());
    }


    //(13).通过code查询所有的上级区域信息
    public List<RegionVo> getParentListVoByCode(String code) {
        //(1).区域信息存在，加入到结果列表,并继续查找上级区域
        List<RegionVo> parentRegionVoList = new ArrayList<>();
        RegionVo regionVoByCode = getRegionVoByCode(code);
        if (!ObjUtil.isEmpty(regionVoByCode)) {
            parentRegionVoList.add(regionVoByCode);
            getParentListVoByCode(regionVoByCode.getParentCode());
        }
        return parentRegionVoList;
    }


    public void getParentListVoByCode(String code, Set<String> parentCodeList) {
        //(1).区域信息存在，加入到结果列表,并继续查找上级区域
        RegionVo regionVoByCode = getRegionVoByCode(code);
        if (!ObjUtil.isEmpty(regionVoByCode)) {
            parentCodeList.add(regionVoByCode.getCode());
            getParentListVoByCode(regionVoByCode.getParentCode(), parentCodeList);
        }
    }

    //(14).通过parentCode查询子级区域列表
    public List<RegionVo> getChildListByParentCode(String parentCode) {
        List<RegionVo> childList = new ArrayList<>();
        for (RegionVo regionVo : sourceRegionVoList) {
            if (regionVo.getParentCode().equals(parentCode)) {
                childList.add(regionVo);
            }
        }
        return childList;
    }

    public void getChildListByCode(RegionVo regionVo, List<RegionVo> childRegionVoList) {
        if (regionVo.getType() != 409L) {
            childRegionVoList.add(regionVo);
        }

        //(2).继续遍历子集查找指定类型的数据
        List<RegionVo> childList = regionVo.getChildList();
        if (!ObjUtil.isEmpty(childList)) {
            for (RegionVo rv : childList) {
                getChildListByCode(rv, childRegionVoList);
            }
        }
    }

    //(15).通过父级code查找所有的子级
    public Set<String> getCodeListByParentCode(String parentCode) {
        //(1).判断该父级是否存在
        RegionVo regionVoByCode = getRegionVoByCode(parentCode);
        if (ObjUtil.isEmpty(regionVoByCode)) {
            throw new IllegalArgumentException("code为:" + parentCode + "的区域信息不存在");
        }

        //(2).获取该父级所有的子集
        RegionVo regionVo = buildChildTreeByCode(parentCode);

        //(3).从所有子集中找出类型为childType(比如COMMUNITY/VILLAGE)的RegionVo，并加入列表
        List<RegionVo> childList = new ArrayList<>();

        getChildListByCode(regionVo, childList);

        if (CommonUtils.isEmpty(childList)) {
            childList = new ArrayList<>();
        }
        childList.add(regionVoByCode);
        return childList.stream().map(RegionVo::getCode).collect(Collectors.toSet());
    }


    //(16).通过父级code查找所有的子级 委办单位
    public Set<String> getCodeListByParentManageCode(String parentCode) {
        //(1).判断该父级是否存在
        RegionVo regionVoByCode = getRegionVoByCode(parentCode);
        if (ObjUtil.isEmpty(regionVoByCode)) {
            throw new IllegalArgumentException("code为:" + parentCode + "的区域信息不存在");
        }

        //(2).获取该父级所有的子集
        RegionVo regionVo = buildChildTreeByCode(parentCode,2);

        //(3).从所有子集中找出类型为childType(比如COMMUNITY/VILLAGE)的RegionVo，并加入列表
        List<RegionVo> childList = new ArrayList<>();

        getChildListByCode(regionVo, childList);

        if (CommonUtils.isEmpty(childList)) {
            childList = new ArrayList<>();
        }
        childList.add(regionVoByCode);
        return childList.stream().map(RegionVo::getCode).collect(Collectors.toSet());
    }

    //(15).从redis/mysql获取所有区域信息
    private List<RegionVo> getSourceRegionVoList() {

        List<RegionVo> regionVoList;
        String regionStr = (String) redisService.hmGet(RedisKey.SYS_DATA, RedisKey.REGION);
        if (ObjUtil.isEmpty(regionStr)) {
            List<Region> regionList = regionMapper.selectByExample()
                    .build()
                    .execute();
            regionVoList = regionList.stream()
                    .map(r -> {
                        RegionVo regionVo = VoPoConverterUtil.copyProperties(r, RegionVo.class);
                        return regionVo;
                    }).collect(Collectors.toList());
            redisService.hmSet(RedisKey.SYS_DATA, RedisKey.REGION, JSON.toJSONString(regionVoList));
        } else {
            regionVoList = JSON.parseArray(regionStr, RegionVo.class);
        }

        return regionVoList;
    }

    //(16).同步区域信息到redis
    @EventListener(ContextRefreshedEvent.class)
    @Transactional
    public void reloadRegion() {
        List<Region> regionList = regionMapper.selectByExample()
                .build()
                .execute();
        sourceRegionVoList = regionList.stream()
                .map(r -> {
                    if (CommonUtils.isEmpty(r.getValue())) {
                        String upperCase = ChineseCharacterUtil.getUpperCase(r.getName(), false);
                        r.setValue(upperCase);
                        r.setCreateTime(System.currentTimeMillis());
                        regionMapper.updateByPrimaryKey(r);
                    }
                    DataDict dataDict = dataDictMapper.selectByPrimaryKey(r.getType());
                    RegionVo regionVo = VoPoConverterUtil.copyProperties(r, RegionVo.class);
                    regionVo.setTypeCn(CommonUtils.isNotEmpty(dataDict) ? dataDict.getValue() : "");
                    return regionVo;
                }).collect(Collectors.toList());
        redisService.hmSet(RedisKey.SYS_DATA, RedisKey.REGION, JSON.toJSONString(sourceRegionVoList));
    }
}
