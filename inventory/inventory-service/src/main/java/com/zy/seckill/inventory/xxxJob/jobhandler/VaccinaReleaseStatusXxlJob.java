package com.zy.seckill.inventory.xxxJob.jobhandler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zy.seckill.inventory.bo.po.ProductRelease;
import com.zy.seckill.inventory.mapper.*;
import com.zy.seckill.inventory.webSocket.WebSocketUtil;
import com.zy.seckill.common.redis.RedisService;
import com.zy.seckill.common.utils.DateUtil;
import com.zy.seckill.common.utils.ObjUtil;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;


@Component
@AllArgsConstructor
@Slf4j
public class VaccinaReleaseStatusXxlJob {

    private final RedisService redisService;

    private final WebSocketUtil webSocketUtil;

    private final ProductReleaseMapper productReleaseMapper;


    /**
     * 抢苗开始后，通过ws发送消息给前端，前端将将预约按钮由灰色变为启用，实现"定时上架"
     */
    @XxlJob("updateVaccineReleaseStatusByRtJobHandler")
    public void updateProjectAuctionStatusBmzJobHandler() {
        XxlJobHelper.log("XXL-JOB, updateVaccineReleaseStatus");
        String jobParam = XxlJobHelper.getJobParam();
        log.info("执行任务-开启放苗后抢苗开始定时任务:{},jobParam:{}", DateUtil.getCurrentTimeStr(),jobParam.toString());

        //1.判断放苗信息是否存在
        JSONObject jsonObject = JSONObject.parseObject(jobParam);
        Long vaccineReleaseId = jsonObject.getLong("vaccineReleaseId");
        ProductRelease productRelease = productReleaseMapper.selectByPrimaryKey(vaccineReleaseId);
        if(ObjUtil.isEmpty(productRelease)){
            throw new IllegalArgumentException("id为:"+vaccineReleaseId+"的疫苗发放信息不存在");
        }

        //2.将启用状态保存到redis以便服务器宕机后再次查询(init.SysInit)
        redisService.set("RuntimeVaccineStatus",1);

        //3.将放苗库存数量放入redis，以便前端可以抢苗
        redisService.set("RuntimeVaccineStock", productRelease.getDockAmount());


        //4.通过redis消息队列发送可以开始抢苗的消息(因为有多台服务器节点，所以通过redis消息队列发出通知以便每台服务器均可接收到消息)，服务器监听到消息后(在com.zy.seckill.inventory.redisMq.RedisMqListener开始监听)
        // step1--> 通知后端本地内存中抢苗标识 修改为启用
        // step2--> 通知前端(通过websocket通知前端将将预约按钮由灰色变为启用)，即可以正式抢苗了
        Map<String, Object> wsDataMap = new HashMap<>();
        wsDataMap.put("vaccineReleaseId",String.valueOf(vaccineReleaseId));
        wsDataMap.put("start",1);
        wsDataMap.put("stock", productRelease.getDockAmount());
        String dataStr = JSON.toJSONString(wsDataMap);
        redisService.convertAndSend("RuntimeVaccineStock", dataStr);
    }
}

