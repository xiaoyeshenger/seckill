package com.jsxa.vapp.common.feign;

import feign.codec.Encoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description feign-SpringMVC 多文件上传配置
 *
 * @author Songxudong
 * @date 2019/11/14 1:52 下午
 */
/*
 * @Author: zhangyong
 * description: 多文件上传配置(支持feign上传form/data 及文件列表)
 * 在需要使用的地方添加配置即可，比如 @FeignClient(value="extData-service",configuration={FeignTokenInterceptor.class, FeignMultipartSupportConfig.class})
 * @Date: 2021-07-23 14:36
 * @Param:
 * @Return:
 */
@Configuration
public class FeignMultipartSupportConfig {

    @Autowired
    private ObjectFactory<HttpMessageConverters> messageConverters;

    @Bean
    public Encoder feignEncoder() {
        return new SpringMultipartEncoder(new SpringEncoder(messageConverters));
    }

}