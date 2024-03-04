package com.jsxa.vapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

/**
 * @Author: zhangyong
 * description: 订单微服务启动器
 * @Date: 2021-08-31 15:30
 * @Param:
 * @Return:
 */
@SpringBootApplication
@EnableFeignClients
@EnableWebSocket
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(com.jsxa.vapp.OrderApplication.class);
    }
}
