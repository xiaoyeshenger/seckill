package com.zy.seckill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: zhangyong
 * description: 库存微服务启动器
 * @Date: xxxx-08-31 15:30
 * @Param:
 * @Return:
 */
@SpringBootApplication
@EnableFeignClients
public class InventoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryApplication.class);
    }
}
