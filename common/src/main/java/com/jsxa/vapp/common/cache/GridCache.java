package com.jsxa.vapp.common.cache;

import com.alibaba.fastjson.JSON;
import com.jsxa.vapp.common.bo.po.GridInfo;
import com.jsxa.vapp.common.mapper.GridInfoMapper;
import com.jsxa.vapp.common.redis.RedisKey;
import com.jsxa.vapp.common.redis.RedisService;
import com.jsxa.vapp.common.utils.ObjUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/*
 * @Author wangchao
 * @Description  网格信息缓存信息
 * @Date 2023/4/18 11:07
 * @Param
 * @return
 **/
@Component
@Slf4j
@RequiredArgsConstructor
public class GridCache {

    private final RedisService redisService;

    private final GridInfoMapper gridInfoMapper;


    private List<GridInfo> sourceGridVoList = new ArrayList<>();


    //(9).通过code查询区域信息
    public GridInfo getRegionVoByCode(String gridCode) {
        GridInfo resultGridInfo = null;
        for (GridInfo gridInfo : sourceGridVoList) {
            if (gridInfo.getGridCode().equals(gridCode)) {
                resultGridInfo = gridInfo;
            }
        }
        return resultGridInfo;
    }

    public GridInfo getGridByNameAndCommunityCode(String gridName, String communityCode) {
        GridInfo resultGridInfo = null;
        for (GridInfo gridInfo : sourceGridVoList) {
            if (gridInfo.getGridName().equals(gridName) && gridInfo.getCommunity().equals(communityCode)) {
                resultGridInfo = gridInfo;
            }
        }
        return resultGridInfo;
    }


    //(14).通过parentCode查询子级区域列表
    public List<GridInfo> getChildListByParentCode(String parentCode) {
        List<GridInfo> childList = new ArrayList<>();
        for (GridInfo gridInfo : sourceGridVoList) {
            if (gridInfo.getCommunity().equals(parentCode)) {
                childList.add(gridInfo);
            }
        }
        return childList;
    }


    //(15).从redis/mysql获取所有区域信息
    private List<GridInfo> getSourceGridVoList() {

        List<GridInfo> gridList;
        String regionStr = (String) redisService.hmGet(RedisKey.SYS_DATA, RedisKey.GRID);
        if (ObjUtil.isEmpty(regionStr)) {
            gridList = gridInfoMapper.selectByExample()
                    .build()
                    .execute();
            redisService.hmSet(RedisKey.SYS_DATA, RedisKey.GRID, JSON.toJSONString(gridList));
        } else {
            gridList = JSON.parseArray(regionStr, GridInfo.class);
        }
        return gridList;
    }

    //(16).同步区域信息到redis
    @EventListener(ContextRefreshedEvent.class)
    @Transactional
    public void reloadRegion() {
        sourceGridVoList = gridInfoMapper.selectByExample()
                .build()
                .execute();
        redisService.hmSet(RedisKey.SYS_DATA, RedisKey.GRID, JSON.toJSONString(sourceGridVoList));
    }
}
