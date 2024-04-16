package com.zy.seckill.gateway.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class JwtTokenUtilTest {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Test
    public void createJwt() {
        Map<String, Object> map = new HashMap<>();
        map.put("foo","bar");
        String jwt = jwtTokenUtil.generateTokenByMap(map);
        System.out.println("Jwt:"+jwt);
    }
}