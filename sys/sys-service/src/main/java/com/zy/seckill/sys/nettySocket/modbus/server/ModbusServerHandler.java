package com.zy.seckill.sys.nettySocket.modbus.server;

import com.zy.seckill.common.redis.RedisService;
import com.zy.seckill.common.utils.*;
import com.zy.seckill.sys.nettySocket.comon.ChannelMap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ModbusServerHandler extends SimpleChannelInboundHandler<Object> {



    //@Value("${sys.gsis.lotUrl:http://qbj.jszhcs.cn:9585/prod-api/accessAlarm/iot/eleWatch}")
    //private String gsisLotUrl = "http://qbj.jszhcs.cn:9585/prod-api/accessAlarm/iot/eleWatch";

    public static Map<String, Object> moduleBuDeviceMap = new HashMap<String, Object>(){{
        put("111111", "姚渡龙王大桥");
        put("111112", "姚渡天坪堰村麻柳湾");
        put("111113", "姚渡泰山村一组提灌站");
        put("BBBBBB", "康家渡大桥");
        put("111115", "城埝小区旁水闸处");
        put("111116", "马燕闸门");
        put("111117", "十八湾村");
        put("111118", "玉虹电站");
        put("111119", "王家河");
        put("111120", "羊叉河");
        put("111121", "界牌村水质检测");
    }};

    private RedisService redisService;


    public ModbusServerHandler(RedisService redisService) {
        this.redisService = redisService;
    }


    /**
     * 写数据方法
     * @param serialNum 设备序列号
     * @param msg 数据（16进制字符串）
     */
    public void write(String serialNum, String msg) {
        Channel channel = ChannelMap.getChannelByName(serialNum);
        if(!ObjUtil.isEmpty(channel)){
            if(channel.isActive()){
                channel.writeAndFlush(msg.getBytes());
            }
        }
    }

    /**
     * 写数据到客户端的方法
     *
     * @param receiveStr
     * @param channel
     */
    private void writeToClient(final String receiveStr, Channel channel) {
        try {
            //(1).netty需要用ByteBuf传输
            ByteBuf bufff = Unpooled.buffer();
            //(2).访问设备需要16进制数据
            bufff.writeBytes(ConvertCode.hexString2Bytes(receiveStr));
            //(3).向设备读取/写入数据
            channel.writeAndFlush(bufff).addListener((ChannelFutureListener) future -> {
                if (future.isSuccess()) {
                    log.info("回写成功：" + receiveStr);
                } else {
                    log.info("回写失败：" + receiveStr);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("调用通用writeToClient()异常" + e.getMessage());
        }
    }


    /**
     *
     * @param ctx 可以拿到本次处理的上下文信息
     * @param msg 就是获取到的信息
     * @throws Exception
     * 此处的逻辑就是将Netty和Spring整合后Netty并不在Spring的应用环境中
     * 需要编写一个SpringUtil来获取Spring内的Bean，用Json来规范字符串
     * 根据Type来判断请求类型，根据不同类型进行不同的数据库操作
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        //1.消息为空，不处理
        if (null == msg || !(msg instanceof byte[])) {
            super.channelRead(ctx, msg);
            return;
        }

        //2.解析客户端上传的消息
        //(1).获取到字节数组
        byte[] data = (byte[]) msg;
        int dataLength = data.length;

        //(2).转为普通字符串
        String messageStr = StrUtils.byteArrayToStr(data);
        String hexStrOri = ConvertCode.bytes2HexString(data);
        log.info("step0 --->接收到原始数据,时间:{},普通字符串:{},16进制字符串:{}", DateUtil.getCurTimeStr(),messageStr,hexStrOri);

        //(3).以1开头或者B开头并且数据长度为6位则是心跳数据
        if((messageStr.startsWith("1") || messageStr.startsWith("B")) && dataLength == 6){
            //--1.设备名称
            String serialNum = messageStr;
            String deviceName = (String)moduleBuDeviceMap.get(messageStr);
            log.info("step1 --->接收到心跳数据(普通字符串), 时间:{}, 名称:{}, 内容:{},", DateUtil.getCurTimeStr(),deviceName,messageStr);

            //--2.保存socket通道到map及同步redis
            if(!ObjUtil.isEmpty(deviceName)){
                ChannelMap.addChannel(serialNum,ctx.channel());
                String key = "ModbusDevice_YWY_Online";
                redisService.hmSet(key,serialNum,deviceName);
            }
        }else {
            //(4).如果不是心跳数据，则转为16进制字符串
            //--1.获取到16进制字符串
            String hexStr = ConvertCode.bytes2HexString(data);

            //-2.获取到请求客户端的Channel
            Channel channel = ctx.channel();

            //--3.通过Channel(value)反过来获取到序列号(key)
            Object keyObj = MapUtil.getKey(ChannelMap.getChannelHashMap(), channel);
            if(!ObjUtil.isEmpty(keyObj)){
                String key = "ModbusDevice_YWY_DATA";
                String serialNum = (String) keyObj;
                log.info("step1 --->接收到寄存器数据(16进制字符串), 时间:{}, 设备:{}, 内容:{},", DateUtil.getCurTimeStr(),serialNum,hexStr);
                //a.保存最新的数据到redis
                redisService.hmSet(key,serialNum,hexStr);

                //b.如果距离/物位数据则根据阈值判断是否需要告警
                if(hexStr.startsWith("010302")){
                    //--1.传感器到水面的距离
                    String substring = hexStr.substring(6, 10);
                    Integer decimalNumber = Integer.parseInt(substring, 16);
                    Double distance = ((double) decimalNumber / 100);

                    //--2.距离阈值
                    String thresholdKey = "ModbusDevice_YWY_Threshold";
                    String thresholdValueStr = (String)redisService.hmGet(thresholdKey, serialNum);
                    if(!ObjUtil.isEmpty(thresholdValueStr)){
                        Double thresholdValue = Double.parseDouble(thresholdValueStr) ;
                        //当前距离小于阈值--->告警
                        if(distance <= thresholdValue){
                            List<String> fileNameList = new ArrayList<>();
                            fileNameList.add("/test.jpg");
                            String imgListStr = String.join(",", fileNameList);

                            String deviceName = (String)moduleBuDeviceMap.get(messageStr);
                            //pushAlarmDataToKafka(imgListStr,serialNum+"_1",deviceName,System.currentTimeMillis()/1000);
                            log.info("pushToKafka 成功");
                        }
                    }
                }
            }
        }
    }


    /**
     * ctx 当前的应用上下文，通道建立事件(处理资源的释放与申请)
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        log.info(" ---> 客户端已建立连接");
    }

    /**
     * ctx 当前的应用上下文，通道移除事件
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        log.info(" ---> 客户端已移除");
    }


    /**
     * ctx 当前的应用上下文，通道活跃事件
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        log.info(channel.remoteAddress() + "上线了");
    }

    /**
     * 通道不活跃事件(下线，此处也可以用作统计在线数量)
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        log.info(channel.remoteAddress() + "下线了");
        //--1.移除设备在线列表
        String name = ChannelMap.getNameByChannel(channel);
        ChannelMap.removeChannelByName(name);
        //--2.同步redis
        if(!ObjUtil.isEmpty(name)){
            String key = "ModbusDevice_YWY_Online";
            redisService.hmDelete(key,name);
        }
    }

    /**
     * ctx 当前的应用上下文，通道异常事件
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        //有异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }

}
