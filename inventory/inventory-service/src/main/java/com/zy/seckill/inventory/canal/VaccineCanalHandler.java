package com.zy.seckill.inventory.canal;

import com.alibaba.fastjson.JSONObject;
import com.zy.seckill.common.redis.RedisService;
import com.zy.seckill.inventory.bo.po.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.javatool.canal.client.annotation.CanalTable;
import top.javatool.canal.client.handler.EntryHandler;

@CanalTable("vaccine")
@Component
@Slf4j
@RequiredArgsConstructor
public class VaccineCanalHandler implements EntryHandler<Product> {

    private final RedisService redisService;

    @Override
    public void insert(Product product) {
        log.info("监听到新增疫苗数据，更新到redis缓存");
        redisService.hmSet("Vaccine", String.valueOf(product.getId()), JSONObject.toJSONString(product));
    }

    @Override
    public void update(Product before, Product after) {
        log.info("监听到更新疫苗数据，更新到redis缓存");
        redisService.hmSet("Vaccine", String.valueOf(after.getId()), JSONObject.toJSONString(after));
    }

    @Override
    public void delete(Product product) {
        log.info("监听到删除疫苗数据，更新到redis缓存");
        redisService.hmDelete("Vaccine", String.valueOf(product.getId()));
    }
}

