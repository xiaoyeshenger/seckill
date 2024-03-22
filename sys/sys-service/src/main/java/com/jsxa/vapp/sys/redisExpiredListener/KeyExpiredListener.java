package com.jsxa.vapp.sys.redisExpiredListener;

import java.nio.charset.StandardCharsets;

import com.jsxa.vapp.common.redis.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import com.jsxa.vapp.sys.nettySocket.comon.ChannelMap;

import javax.annotation.Resource;


/**
 * redis key过期时间监听
 */
@Slf4j
public class KeyExpiredListener extends KeyExpirationEventMessageListener {

    @Resource
    private RedisService redisService;

    public KeyExpiredListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String channel = new String(message.getChannel(), StandardCharsets.UTF_8);
        //过期的key
        String key = new String(message.getBody(), StandardCharsets.UTF_8);
        log.info("redis key 过期：pattern={},channel={},key={}", new String(pattern), channel, key);
        if (key.startsWith("Modbus")) {
            //TODO netty通道过期，处理对应的设备离线逻辑
            //String serial = key.replace("Modbus", "");
            //ChannelMap.removeChannelByName(serial);
        }
    }
}
