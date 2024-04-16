package com.zy.seckill.order.redisMq;


import com.alibaba.fastjson.JSONObject;
import com.zy.seckill.common.utils.HttpClientUtil;
import com.zy.seckill.common.utils.ObjUtil;
import com.zy.seckill.order.service.impl.OrderRecordServiceImpl;
import com.zy.seckill.order.webSocket.WebSocketKey;
import com.zy.seckill.order.webSocket.WebSocketService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;

import java.util.HashMap;
import java.util.Map;

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

    @Value("${vapp.nginx.url}")
    private String nginxUrl;

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

        //(3).获取到抢苗标识的值(start=1/0)和放苗活动id
        JSONObject jsonObject = JSONObject.parseObject(s);
        String vaccineReleaseId = jsonObject.getString("vaccineReleaseId");
        Integer start = jsonObject.getInteger("start");
        Integer stock = jsonObject.getInteger("stock");

        //(4).修改本地内存的抢苗启用标识(start=1/0)
        OrderRecordServiceImpl.runtimeVaccineStockMap.put("start", start);

        //(5).下发活动启动状态和库存到openresty(nginx)的本地内存
        updateNginxStock(vaccineReleaseId,stock);

        //(6).通过ws推送抢苗是否启用给前端(start=1/0),前端监听ws://192.168.2.230:850/api/ws/runtimeVaccineStock/{vaccineReleaseId}即可收到消息
        TextMessage textMessage = new TextMessage(s);
        webSocketService.sendMessageToOne(WebSocketKey.RuntimeVaccineStock + vaccineReleaseId,textMessage);

    }

    /**
     * 通过http向nginx内存下发预约启动状态和库存数量
     */
    private void updateNginxStock(String vaccineReleaseId,Integer amount){
        //--1.请求地址
        String apiUrl = nginxUrl + "updateStockStatus";
        //--2.请求参数
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("vaccineReleaseId", vaccineReleaseId);
        paramMap.put("amount", amount);
        String paramStr = JSONObject.toJSONString(paramMap);
        //--3.请求头
        Map<String, Object> headerMap = new HashMap<>();
        //--4.执行请求
        JSONObject nginxJo = HttpClientUtil.doPostJson(apiUrl, paramStr, headerMap);
        if (ObjUtil.isEmpty(nginxJo)) {
            throw new IllegalArgumentException("下发库存到Nginx失败");
        }
        //--5.解析响应数据
        Integer code = nginxJo.getInteger("code");
        if (code != 200) {
            throw new IllegalArgumentException("下发库存到Nginx失败,错误码:"+code);
        }
    }
}

