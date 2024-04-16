package com.zy.seckill.sys.runner;

import com.zy.seckill.common.redis.RedisService;
import com.zy.seckill.common.utils.ConvertCode;
import com.zy.seckill.common.utils.Crc16Util;
import com.zy.seckill.common.utils.ObjUtil;
import com.zy.seckill.common.utils.SpringUtil;
import com.zy.seckill.sys.nettySocket.comon.ChannelMap;
import com.zy.seckill.sys.nettySocket.modbus.server.ModbusSocketServer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
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

        /*log.info("starting socket server2...,port:{}",20842);
        executorService.execute(()-> {
            SpringUtil.getBean(ModbusSocketServer.class).start(20842);
        });*/
    }

    /**
     * 每隔60秒定时查询modbus寄存器数据
     */
    //@Scheduled(fixedRate = 10000)
    public void brokenReconnect() {

        //1.构建查询指令
        //(1).从机地址
        String address = "01";
        //(2).功能码，读取寄存器
        String code = "03";
        //(3).根据设备寄存器定义表, 0000 代表距离/物位瞬时值(2字节(1个位16进制等于4位2进制数，所以0000*4=16位二进制数，1字节=8位二进制数，所以0000=2字节),高位在前低位在后)，即高位为00(前两位)，低位为00(后两位)
        //寄存器开始位置(高位在前(00)，低位在后(00))
        String[] start = {"00", "00"};
        //(4).要读取的寄存器数量为1位，即0001，高位在前(00)，低位在后(01)
        String[] length = {"00", "01"};
        //(5).计算CRC，得出完整消息
        byte[] data = Crc16Util.getData(address, code, start[0], start[1], length[0], length[1]);
        String msg = Crc16Util.byteTo16String(data).replaceAll(" ", "");
        //log.info("msg=====> {}",msg);

        //2.查询出所有在线的液位仪设备，并下发指令
        String Key = "ModbusDevice_YWY_Online";
        Map<String, Object> stringObjectMap = redisService.hmGet(Key);
        for (Map.Entry<String, Object> entry: stringObjectMap.entrySet()){
            String serialNum = entry.getKey();
            Channel channel = ChannelMap.getChannelByName(serialNum);
            if(!ObjUtil.isEmpty(channel) && channel.isActive()){
                //向设备下发指令
                writeToClient(msg,channel);
            }
        }
    }

    private void writeToClient(final String receiveStr, Channel channel) {
        try {
            // netty需要用ByteBuf传输
            ByteBuf bufff = Unpooled.buffer();
            // 对接需要16进制
            bufff.writeBytes(ConvertCode.hexString2Bytes(receiveStr));
            channel.writeAndFlush(bufff).addListener((ChannelFutureListener) future -> {
                if (future.isSuccess()) {
                    log.info("向modbus设备写入数据成功,Channel_Id:{}, data:{}" ,channel.id().asLongText(), receiveStr);
                } else {
                    log.info("向modbus设备写入数据失败,Channel_Id:{}, data:{}" ,channel.id().asLongText(), receiveStr);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("调用通用writeToClient()异常" + e.getMessage());
        }
    }
}
