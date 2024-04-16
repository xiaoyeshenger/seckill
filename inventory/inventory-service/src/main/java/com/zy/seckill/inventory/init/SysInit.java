package com.zy.seckill.inventory.init;

import com.alibaba.fastjson.JSONObject;
import com.zy.seckill.common.redis.RedisService;
import com.zy.seckill.inventory.bo.po.Person;
import com.zy.seckill.inventory.bo.po.Site;
import com.zy.seckill.inventory.bo.po.Product;
import com.zy.seckill.inventory.mapper.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

/*
 * @Author: zhangyong
 * description: 系统初始化
 * @Date: xxxx-02-01 10:24
 * @Param:
 * @Return:
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class SysInit {


    private final RedisService redisService;

    private final PersonMapper personMapper;

    private final SiteMapper siteMapper;

    private final ProductMapper productMapper;

    private final ProductReleaseMapper productReleaseMapper;



    //(1).系统启动后，加载系统配置
    @EventListener(ContextRefreshedEvent.class)
    @Transactional(rollbackFor = Exception.class)
    public void initSysConfig() {

        log.info("step1 ---> 开始同步接种人员信息到Redis");
        List<Person> personList = personMapper.selectByExample()
                .build()
                .execute();
        personList.forEach(e -> {
            redisService.hmSet("VaccinationPerson", String.valueOf(e.getOpenId()), JSONObject.toJSONString(e));
        });

        log.info("step2 ---> 开始同步接种地点信息到Redis");
        List<Site> siteList = siteMapper.selectByExample()
                .build()
                .execute();
        siteList.forEach(e -> {
            redisService.hmSet("VaccinationSite", String.valueOf(e.getId()), JSONObject.toJSONString(e));
        });


        log.info("step3 ---> 开始同步疫苗信息到Redis");
        List<Product> productList = productMapper.selectByExample()
                .build()
                .execute();
        productList.forEach(e -> {
            redisService.hmSet("Vaccine", String.valueOf(e.getId()), JSONObject.toJSONString(e));
        });
    }
}
