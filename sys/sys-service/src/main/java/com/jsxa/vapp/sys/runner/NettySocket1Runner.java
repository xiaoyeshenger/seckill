package com.jsxa.vapp.sys.runner;

import com.jsxa.vapp.common.redis.RedisService;
import com.jsxa.vapp.common.utils.SpringUtil;
import com.jsxa.vapp.sys.nettySocket.modbus.server.ModbusSocketServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;


/**
 * 项目启动后，自动启动socket
 */
@Slf4j
@Component
@ConditionalOnProperty(name = "vapp.socket.server1.enable", havingValue = "true")
public class NettySocket1Runner implements ApplicationRunner {


    @Value("${vapp.socket.server1.port}")
    private Integer server1Port;

    @Value("${vapp.socket.server2.port}")
    private Integer server2Port;


    @Resource
    private RedisService redisService;


    @Resource
    private ExecutorService executorService;

    @Override
    public void run(ApplicationArguments args) {
        initWebSocketClient();
    }

    private void initWebSocketClient(){
        log.info("starting socket server1...,port:{}",server1Port);
        executorService.execute(()-> {
            SpringUtil.getBean(ModbusSocketServer.class).start(server1Port);
        });

        /*log.info("starting socket server2...,port:{}",server2Port);
        executorService.execute(()-> {
            SpringUtil.getBean(MySocketServer.class).start(server2Port);
        });*/
    }

}
