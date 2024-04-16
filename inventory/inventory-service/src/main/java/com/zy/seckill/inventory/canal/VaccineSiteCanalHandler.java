package com.zy.seckill.inventory.canal;

import com.alibaba.fastjson.JSONObject;
import com.zy.seckill.common.redis.RedisService;
import com.zy.seckill.inventory.bo.po.Site;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.javatool.canal.client.annotation.CanalTable;
import top.javatool.canal.client.handler.EntryHandler;

@CanalTable("vaccination_site")
@Component
@Slf4j
@RequiredArgsConstructor
public class VaccineSiteCanalHandler implements EntryHandler<Site> {

    private final RedisService redisService;

    @Override
    public void insert(Site site) {
        log.info("监听到新增接种点数据，更新到redis缓存");
        redisService.hmSet("VaccinationSite", String.valueOf(site.getId()), JSONObject.toJSONString(site));
    }

    @Override
    public void update(Site before, Site after) {
        log.info("监听到更新接种点数据，更新到redis缓存");
        redisService.hmSet("VaccinationSite", String.valueOf(after.getId()), JSONObject.toJSONString(after));
    }

    @Override
    public void delete(Site vaccine) {
        log.info("监听到删除接种点数据，更新到redis缓存");
        redisService.hmDelete("VaccinationSite", String.valueOf(vaccine.getId()));
    }
}

