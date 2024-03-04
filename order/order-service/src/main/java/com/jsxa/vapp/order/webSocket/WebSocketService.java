package com.jsxa.vapp.order.webSocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.adapter.standard.StandardWebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @Author zhangyong
 * @Description //WebSocket服务
 * @Date 下午 2:54 2019/5/9 0009
 * @Param
 * @return
 */
@Component
@Slf4j
public class WebSocketService extends TextWebSocketHandler  {


    /**
     * @Author zhangyong
     * @Description //自定义一个session接结构，包含websocket session和lastPing的时间
     * @Date 上午 9:23 2019/5/13 0013
     * @Param
     * @return
     */
    private class SessionStruct {
        Date lastPing;
        WebSocketSession session;
    }

    /*
     * @Author zhangyong
     * @Description //声明一个线程安全的concurrentHashMap，用以装每个客户端的session
     * @Date 上午 9:25 2019/5/13 0013
     * @Param
     * @return
     **/
    private final Map<String, SessionStruct> sessionMap = new ConcurrentHashMap<>();

    /**
     * @Author zhangyong
     * @Description //第一次连接成功后执行的业务
     * @Date 上午 9:23 2019/5/13 0013
     * @Param
     * @return
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        if (sessionMap.size() > 1024) {
            session.close(CloseStatus.SERVICE_OVERLOAD.withReason("系统连接太多!.等会再连接"));
            return;
        }

        //log.info("客户端接入websocket.path: {}",session.getUri().getPath());
        //1.从webSocket访问的url中取出请求参数，将访问者的信息("index"+parkId)存入Attributes，便于后面一对一发送消息
        //ws://192.168.1.124:650/api/ws/bid/1501815782786256896
        String type = (String)session.getAttributes().get("type");
        String key = (String)session.getAttributes().get("key");

        //2.session保存参数
        session.getAttributes().put("userName",type + key);


        //3.保存webSocket客户端信息
        SessionStruct sessionStruct = new SessionStruct();
        sessionStruct.lastPing = new Date();
        sessionStruct.session = session;
        sessionMap.put(session.getId(),sessionStruct);
        ((StandardWebSocketSession) session).getNativeSession().setMaxIdleTimeout(TimeUnit.MINUTES.toMillis(60));
        session.setTextMessageSizeLimit(1024 * 1024 * 10);

    }

    /**
     * @Author zhangyong
     * @Description //关闭连接后执行的任务
     * @Date 上午 9:23 2019/5/13 0013
     * @Param
     * @return
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessionMap.remove(session.getId());
        //log.info("websocket关闭连接. sid: {}, 剩余在线连接个数: {}, status: {}", session.getId(), sessionMap.size(), status);
    }

    /**
     * @Author zhangyong
     * @Description //连接异常或断开执行的业务
     * @Date 上午 9:22 2019/5/13 0013
     * @Param
     * @return
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable t) throws Exception {
        if (t.getMessage() != null && t.getMessage().contains("code: 1013")) {
            log.warn("系统websocket连接超负载. 当前连接个数: {}", sessionMap.size()); return;
        }
        log.error("websocket发生错误 sid: {}", session.getId());
    }

    /**
     * @Author zhangyong
     * @Description //接收到消息执行的任务
     * @Date 上午 9:22 2019/5/13 0013
     * @Param
     * @return
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage msg) {
        /*String type = (String)session.getAttributes().get("type");
        String projectId = (String)session.getAttributes().get("projectId");
        log.debug("websocket收到消息. sid: {}, msg: {}, type: {}, parkId: {}", session.getId(), msg.getPayload(),type,projectId);*/
    }

    /**
     * @Author zhangyong
     * @Description //与客户端保持通信(100毫秒检查一次)
     * @Date 上午 9:21 2019/5/13 0013
     * @Param
     * @return
     */
    @Scheduled(fixedRate = 100)
    public void pingConnection() throws IOException {
        //1.获取当前时间
        Date current = new Date();
        //2.获取当前时间之前10000毫秒(10秒)的时间
        current.setTime(current.getTime() - 10000);
        for(SessionStruct sessionStruct : sessionMap.values()) {
            //3.如果最后一次ping的时间小于当前时间10秒之前的时间，即还没到需要ping的时间,不需要处理
            if(sessionStruct.lastPing.compareTo(current) < 0) {
                sessionStruct.session.close();
                continue;
            }
            //4.最后一次ping的时间超出10秒钟，重新ping
            sessionStruct.session.sendMessage(new PingMessage());
        }
    }

    /**
     * @Author zhangyong
     * @Description //PongMessage
     * @Date 上午 11:48 2019/5/13 0013
     * @Param
     * @return
     */
    @Override
    protected void handlePongMessage(WebSocketSession session, PongMessage message) {
        sessionMap.get(session.getId()).lastPing = new Date();
    }


    /**
     * @Author zhangyong
     * @Description //给监听了该路径的所有用户发送消息(一对一发)
     * @Date 上午 9:25 2019/7/17 0017
     * @Param
     * @return
     */
    public void sendMessageToOne(String userName, TextMessage message) {
            for (Map.Entry<String, SessionStruct> entry : sessionMap.entrySet()) {
                //System.out.println("key = " + entry.getKey() + ", value = " + entry.getValue());
                WebSocketSession webSocketSession = entry.getValue().session;
                String sessionUserName = (String) webSocketSession.getAttributes().get("userName");
                if (sessionUserName.equals(userName)) {
                try {
                    if (webSocketSession.isOpen()) {
                        synchronized (webSocketSession){
                            webSocketSession.sendMessage(message);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        /*Thread t = Thread.currentThread();
        String name = t.getName();
        log.info("推送ws成功，。。线程名:{}",name);*/
    }

    /**
     * @Author zhangyong
     * @Description //给所有用户发送消息(群发)
     * @Date 上午 9:25 2019/7/17 0017
     * @Param
     * @return
     */
    public void sendMessageToAll(TextMessage message) {
        for (Map.Entry<String, SessionStruct> entry : sessionMap.entrySet()) {
            //System.out.println("key = " + entry.getKey() + ", value = " + entry.getValue());
            WebSocketSession webSocketSession = entry.getValue().session;
            try {
                if (webSocketSession.isOpen()) {
                    webSocketSession.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
