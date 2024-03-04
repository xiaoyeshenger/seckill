package com.jsxa.vapp.common.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * @Author: zhangyong
 * description: rabbitMq配置(配置队列,交换机及队列和对应交换机绑定)
 * @Date: 2021-07-23 14:36
 * @Param:
 * @Return:
 */
@Configuration
@ConditionalOnProperty(prefix = "spring.rabbitmq", name = "host", matchIfMissing = false)
public class RabbitConfig {


    /**
     * 添加人员_队列
     */
    @Bean
    public Queue addPersonQueue() {
        return new Queue("ADD-PERSON-QUEUE", true);
    }

    /**
     * 添加车辆_队列
     */
    @Bean
    public Queue addCarQueue() {
        return new Queue("ADD-CAR-QUEUE", true);
        //return new Queue("ADD-CAR-QUEUE-TEST", true); //本地测试
    }

    /**
     * 添加房屋_队列
     */
    @Bean
    public Queue addHouseQueue() {
        return new Queue("ADD-HOUSE-QUEUE", true);
    }


    /**
     * 添加人员交换机
     */
    @Bean
    public FanoutExchange addPersonExchange() {
        return new FanoutExchange("ADD-PERSON-EXCHANGE");
    }


    /**
     * 添加车辆交换机
     */
    @Bean
    public FanoutExchange addCarExchange() {
        return new FanoutExchange("ADD-CAR-EXCHANGE");
    }


    /**
     * 添加房屋交换机
     */
    @Bean
    public FanoutExchange addHouseExchange() {
        return new FanoutExchange("ADD-HOUSE-EXCHANGE");
    }


    /**
     * 添加人员 绑定到交换机
     */
    @Bean
    public Binding addPersonBinding() {

        return BindingBuilder.bind(addPersonQueue()).to(addPersonExchange());
    }


    /**
     * 添加车辆 绑定到交换机
     */
    @Bean
    public Binding addCarBinding() {

        return BindingBuilder.bind(addCarQueue()).to(addCarExchange());
    }

    /**
     * 添加房屋 绑定到交换机
     */
    @Bean
    public Binding addHouseBinding() {

        return BindingBuilder.bind(addHouseQueue()).to(addHouseExchange());
    }


}
