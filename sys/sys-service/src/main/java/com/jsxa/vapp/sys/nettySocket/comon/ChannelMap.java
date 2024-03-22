package com.jsxa.vapp.sys.nettySocket.comon;

import com.jsxa.vapp.common.redis.RedisService;
import com.jsxa.vapp.common.utils.ObjUtil;
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


    /*
     * @Author: zhangyong
     * description: 进入定时校验电子手表socket在线状态(2分钟校验一次)
     * @Date: 2021-01-28 11:24
     * @Param:
     * @Return:
     */
    @Scheduled(fixedRate = 60000)
    public void checkInterPhoneOnlineStatus() {
        log.info("进入定时校验电子手表socket在线状态(1分钟检查一次)");

        //(1).从redis 获取所有的在线设备Map
        if(ObjUtil.isEmpty(channelHashMap)){
            return;
        }

        //(2).迭代器遍历
        Iterator<Map.Entry<String, Channel>> iterator = channelHashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Channel> next = iterator.next();
            Channel channe = next.getValue();
            if(!channe.isActive()){
                channelHashMap.remove(next.getKey());
            }
        }

        //(3).将在线设备同步到redis
        //--1.清空redis
        String key = "ModbusDeviceStatus";
        redisService.remove(key);
        //--2.重新同步所有设备状态
        Iterator<Map.Entry<String, Channel>> iteratorRedis = channelHashMap.entrySet().iterator();
        while (iteratorRedis.hasNext()) {
            Map.Entry<String, Channel> nextReise = iteratorRedis.next();
            String deviceId = nextReise.getKey();
            redisService.hmSet(key,deviceId,"1");
        }
    }
}
