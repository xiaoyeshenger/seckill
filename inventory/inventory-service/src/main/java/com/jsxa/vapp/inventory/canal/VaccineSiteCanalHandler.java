package com.jsxa.vapp.inventory.canal;

import com.alibaba.fastjson.JSONObject;
import com.jsxa.vapp.common.redis.RedisService;
import com.jsxa.vapp.inventory.bo.po.VaccinationSite;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.javatool.canal.client.annotation.CanalTable;
import top.javatool.canal.client.handler.EntryHandler;

@CanalTable("vaccination_site")
@Component
@Slf4j
@RequiredArgsConstructor
public class VaccineSiteCanalHandler implements EntryHandler<VaccinationSite> {

    private final RedisService redisService;

    @Override
    public void insert(VaccinationSite vaccinationSite) {
        log.info("监听到新增接种点数据，更新到redis缓存");
        redisService.hmSet("VaccinationSite", String.valueOf(vaccinationSite.getId()), JSONObject.toJSONString(vaccinationSite));
    }

    @Override
    public void update(VaccinationSite before, VaccinationSite after) {
        log.info("监听到更新接种点数据，更新到redis缓存");
        redisService.hmSet("VaccinationSite", String.valueOf(after.getId()), JSONObject.toJSONString(after));
    }

    @Override
    public void delete(VaccinationSite vaccine) {
        log.info("监听到删除接种点数据，更新到redis缓存");
        redisService.hmDelete("VaccinationSite", String.valueOf(vaccine.getId()));
    }
}

