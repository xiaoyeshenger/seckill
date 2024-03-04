package com.jsxa.vapp.gateway.config;

import com.jsxa.vapp.gateway.redis.RedisKey;
import com.jsxa.vapp.gateway.redis.RedisService;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

import javax.annotation.Resource;

/*
 * @Author: zhangyong
 * description: 映射本地文件
 * @Date: 2021-05-21 12:50
 * @Param:
 * @Return:
 */
@Configuration
public class WebConfig implements WebFluxConfigurer {

    @Resource
    private RedisService redisService;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        //registry.addResourceHandler("/api/sr/**").addResourceLocations("file:E:/staticResource/");
        registry.addResourceHandler("/api/sr/**").addResourceLocations("file:"+redisService.hmGet(RedisKey.SYS_SETTING_KEY, RedisKey.FILE_UPLOAD_PREFIX));
    }
}