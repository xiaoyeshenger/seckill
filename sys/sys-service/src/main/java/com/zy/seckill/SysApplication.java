package com.zy.seckill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author: zhangyong
 * description: 系统微服务启动器
 * @Date: xxxx-08-31 15:30
 * @Param:
 * @Return:
 */
@SpringBootApplication
@EnableFeignClients
@EnableScheduling
public class SysApplication {

    public static void main(String[] args) {
        SpringApplication.run(SysApplication.class);
    }
}
