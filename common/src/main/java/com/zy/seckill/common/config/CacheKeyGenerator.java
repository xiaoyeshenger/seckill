package com.zy.seckill.common.config;

import com.alibaba.fastjson.JSON;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class CacheKeyGenerator implements KeyGenerator {

    @Override
    public Object generate(Object target, Method method, Object... params) {
        //return target.getClass().getSimpleName() + "#" + method.getName() + "(" + JSON.toJSONString(params) + ")";
        return JSON.toJSONString(params);
    }
}
