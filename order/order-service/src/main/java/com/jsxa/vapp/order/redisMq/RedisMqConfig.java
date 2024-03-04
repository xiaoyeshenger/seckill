package com.jsxa.vapp.order.redisMq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;


@Slf4j
@Configuration
public class RedisMqConfig {

    /**
     * @author zhangyong
     * @description redis消息队列(监听的topic为bid)
     * @date 2023/12/19 15:15
     * @param
     * @return
     */
    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(
            RedisConnectionFactory connectionFactory,
            MessageListenerAdapter messageListenerAdapter
    ) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        //可以添加多个MessageListener，配置不同的交换机
        container.addMessageListener(messageListenerAdapter, new PatternTopic("RuntimeVaccineStock"));
        //container.addMessageListener(messageListenerAdapter, new PatternTopic("xxx"));
        return container;
    }
    @Bean
    public MessageListenerAdapter messageListenerAdapter(RedisMqListener redisMqListener) {
        return new MessageListenerAdapter(redisMqListener, "onMessage");
    }
}
