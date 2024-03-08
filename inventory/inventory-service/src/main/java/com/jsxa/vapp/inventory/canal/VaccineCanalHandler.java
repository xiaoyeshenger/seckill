package com.jsxa.vapp.inventory.canal;

import com.alibaba.fastjson.JSONObject;
import com.jsxa.vapp.common.redis.RedisService;
import com.jsxa.vapp.inventory.bo.po.Vaccine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.javatool.canal.client.annotation.CanalTable;
import top.javatool.canal.client.handler.EntryHandler;

@CanalTable("vaccine")
@Component
@Slf4j
@RequiredArgsConstructor
public class VaccineCanalHandler implements EntryHandler<Vaccine> {

    private final RedisService redisService;

    @Override
    public void insert(Vaccine vaccine) {
        log.info("监听到新增疫苗数据，更新到redis缓存");
        redisService.hmSet("Vaccine", String.valueOf(vaccine.getId()), JSONObject.toJSONString(vaccine));
    }

    @Override
    public void update(Vaccine before, Vaccine after) {
        log.info("监听到更新疫苗数据，更新到redis缓存");
        redisService.hmSet("Vaccine", String.valueOf(after.getId()), JSONObject.toJSONString(after));
    }

    @Override
    public void delete(Vaccine vaccine) {
        log.info("监听到删除疫苗数据，更新到redis缓存");
        redisService.hmDelete("Vaccine", String.valueOf(vaccine.getId()));
    }
}

