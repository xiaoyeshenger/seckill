package com.zy.seckill.order.init;

import com.zy.seckill.common.redis.RedisService;
import com.zy.seckill.common.utils.ObjUtil;
import com.zy.seckill.order.service.impl.OrderRecordServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

/*
 * @Author: zhangyong
 * description: 系统初始化
 * @Date: xxxx-02-01 10:24
 * @Param:
 * @Return:
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class SysInit {


    private final RedisService redisService;



    //(1).系统启动后，加载系统配置
    @EventListener(ContextRefreshedEvent.class)
    public void initSysConfig() {

        log.info("step1 ---> 开始从redis同步疫苗抢苗状态到本机内存");
        Integer start = (Integer)redisService.get("RuntimeVaccineStatus");
        if(ObjUtil.isEmpty(start)){
            start = 0;
        }
        OrderRecordServiceImpl.runtimeVaccineStockMap.put("start", start);
    }
}
