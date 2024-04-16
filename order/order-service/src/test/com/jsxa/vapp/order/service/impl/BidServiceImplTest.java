package com.zy.seckill.bid.service.impl;

import com.zy.seckill.common.redis.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BidServiceImplTest {

    @Autowired
    private RedisService redisService;

    @Test
    public void getBidById() {
        Long aLong = redisService.hmSize("ProjectAuction:1745005746197983232");
        System.out.println("HASH item总数量:"+aLong);
    }
}