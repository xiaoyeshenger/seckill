package com.zy.seckill.sys.nettySocket.comon;

import com.zy.seckill.common.redis.RedisService;
import com.zy.seckill.common.utils.ObjUtil;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class ChannelMap {

    @Resource
    private RedisService redisService;

    public static int channelNum = 0;

    private static Map<String,Channel> channelHashMap = new ConcurrentHashMap<>();

    public static Map<String, Channel> getChannelHashMap() {
        return channelHashMap;
    }

    public static Channel getChannelByName(String name){
        if(ObjUtil.isEmpty(channelHashMap)){
            return null;
        }
        return channelHashMap.get(name);
    }

    public static String getNameByChannel(Channel channel){
        if(ObjUtil.isEmpty(channelHashMap)){
            return null;
        }
        String key = null;
        Iterator<Map.Entry<String, Channel>> iterator = channelHashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Channel> next = iterator.next();
            Channel cha = next.getValue();
            if(channel.equals(cha)){
                key = next.getKey();
            }
        }

        return key;
    }

    public static void addChannel(String name,Channel channel){
        channelHashMap.put(name,channel);
        channelNum ++;
    }
    public static int removeChannelByName(String name){
        if(!ObjUtil.isEmpty(channelHashMap)){
        //if(channelHashMap.containsKey(name)){
            channelHashMap.remove(name);
            return 0;
        }else{
            return 1;
        }
    }


    /**
     * @Author: zhangyong
     * description: 进入定时校验modbus设备在线状态(1分钟校验一次)
     * @Date: xxxx-01-28 11:24
     * @Param:
     * @Return:
     */
    @Scheduled(fixedRate = 60000)
    public void checkModbusOnlineStatus() {
        log.info("进入定时校验modbus设备socket在线状态(1分钟检查一次)");

        //(1).从内存获取所有的在线设备Map
        if(ObjUtil.isEmpty(channelHashMap)){
            return;
        }

        //(2).迭代器遍历
        String key = "ModbusDevice_YWY_Online";
        Iterator<Map.Entry<String, Channel>> iterator = channelHashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Channel> next = iterator.next();
            Channel channe = next.getValue();
            if(!channe.isActive()){
                channelHashMap.remove(next.getKey());
                redisService.hmDelete(key,next.getKey());
            }
        }
    }
}
