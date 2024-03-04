package com.jsxa.vapp.order.init;

import com.alibaba.fastjson.JSONObject;
import com.jsxa.vapp.common.redis.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

/*
 * @Author: zhangyong
 * description: 系统初始化
 * @Date: 2021-02-01 10:24
 * @Param:
 * @Return:
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class SysInit {


    private final RedisService redisService;



    //(1).系统启动后，加载系统配置
/*    @EventListener(ContextRefreshedEvent.class)
    @Transactional(rollbackFor = Exception.class)
    public void initSysConfig() {

        log.info("step1 ---> 开始同步项目信息到Redis");
        List<Project> projectList = projectMapper.selectByExample()
                .build()
                .execute();
        projectList.forEach(p -> {
            redisService.hmSet("Project", String.valueOf(p.getId()), JSONObject.toJSONString(p));
        });

        log.info("step2 ---> 开始同步产品拍卖信息和产品单价到Redis");
        List<ProductAuction> productAuctionList = productAuctionMapper.selectByExample()
                .build()
                .execute();
        productAuctionList.forEach(pa -> {
            redisService.hmSet("ProductAuction:"+pa.getProjectId(), String.valueOf(pa.getId()), JSONObject.toJSONString(pa));
        });
    }*/
}
