package com.jsxa.vapp.order.redisMq;


import com.alibaba.fastjson.JSONObject;
import com.jsxa.vapp.common.redis.RedisService;
import com.jsxa.vapp.order.service.impl.VaccinationRecordServiceImpl;
import com.jsxa.vapp.order.webSocket.WebSocketKey;
import com.jsxa.vapp.order.webSocket.WebSocketService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;

/**
 * @Author: zhangyong
 * description: redis消息监听器(类似redis队列的消费端)，保证ws能发到所有连接服务器的客户端
 *              (服务器有多台，每个用户连接的服务器不同，这就要求每个服务器都能发送ws消息)
 * @Date: 2019-01-18 19:15
 * @Param:
 * @Return:
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class RedisMqListener implements MessageListener {

    private final WebSocketService webSocketService;

    @Override
    @Synchronized
    @SneakyThrows
    public void onMessage(Message message, byte[] pattern) {
        //(1).订阅的topic
        String topic = new String(pattern);
        //log.info("监听到redis index消息,topic:{}",topic);

        //(2).消息内容
        byte[] body = message.getBody();
        String result= new String(body,"utf-8");
        result = result.substring(1,result.length()-1);
        String s = StringEscapeUtils.unescapeJava(result);

        //(3).获取到抢苗标识的值(start="1"/"0")和放苗活动id
        JSONObject jsonObject = JSONObject.parseObject(s);
        String start = jsonObject.getString("start");
        String vaccineReleaseId = jsonObject.getString("vaccineReleaseId");

        //(4).修改本地内存的抢苗启用标识(start="1"/"0")
        VaccinationRecordServiceImpl.runtimeVaccineStockMap.put("start", start);


        //(5).通过ws推送抢苗是否启用给前端(start="1"/"0"),前端监听ws://192.168.2.230:850/api/ws/runtimeVaccineStock/{vaccineReleaseId}即可收到消息
        TextMessage textMessage = new TextMessage(s);
        webSocketService.sendMessageToOne(WebSocketKey.RuntimeVaccineStock + vaccineReleaseId,textMessage);

    }
}

