package com.jsxa.vapp.inventory.webSocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jsxa.vapp.common.redis.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class WebSocketUtil {

    private final RedisService redisService;

    /**
     * @author zhangyong
     * @description 发送单个key-value数据到webSocket，流程是先发送数据到redis队列,redis消息队列客户端监听到消息就会自动推送到webSocket
     * @date 2023/12/19 15:22
     * @param
     * @return
     */
    public void sendDataToWebSocket(String redisChannel,String userName,String key,Object value){
        //1.封装数据
        Map<String, Object> subMap = new HashMap<>();
        subMap.put(key,value);
        String subMapStr = JSONObject.toJSONString(subMap);

        //--2.封装好所有的数据,准备发送到redis队列
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("userName",userName);
        dataMap.put("data",subMapStr);

        //--3.发送到redis队列,redis消息队列客户端监听到消息就会自动推送到webSocket
        String dataStr = JSON.toJSONString(dataMap);
        redisService.convertAndSend(redisChannel, dataStr);
    }

    /**
     * @author zhangyong
     * @description 发送多个ky-value数据到webSocket，流程是先发送数据到redis队列,redis消息队列客户端监听到消息就会自动推送到webSocket
     * @date 2023/12/19 15:22
     * @param
     * @return
     */
    public void sendDataToWebSocket(String redisChannel,String userName,Map<String, Object> wsDataMap){
        //1.封装数据
        Map<String, Object> subMap = new HashMap<>();
        wsDataMap.forEach((key,value)->{
            subMap.put(key,value);
        });
        String subMapStr = JSONObject.toJSONString(subMap);

        //--2.封装好所有的数据,准备发送到redis队列
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("userName",userName);
        dataMap.put("data",subMapStr);

        //--3.发送到redis队列,redis消息队列客户端监听到消息就会自动推送到webSocket
        String dataStr = JSON.toJSONString(dataMap);
        redisService.convertAndSend(redisChannel, dataStr);
    }

}