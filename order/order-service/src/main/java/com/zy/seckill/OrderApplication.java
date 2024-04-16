package com.zy.seckill;

import org.apache.shardingsphere.shardingjdbc.spring.boot.SpringBootConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

/**
 * @Author: zhangyong
 * description: 秒杀微服务启动器
 * @Date: xxxx-08-31 15:30
 * @Param:
 * @Return:
 */
@SpringBootApplication
@EnableFeignClients
@EnableWebSocket
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(com.zy.seckill.OrderApplication.class);
    }
}
