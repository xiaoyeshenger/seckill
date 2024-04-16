package com.zy.seckill.inventory.rocketMq;

import com.alibaba.fastjson.JSONObject;
import com.zy.seckill.common.utils.DateUtil;
import com.zy.seckill.common.utils.IdWorker;
import com.zy.seckill.inventory.bo.po.RocketMqFailMsg;
import com.zy.seckill.inventory.mapper.RocketMqFailMsgMapper;
import com.zy.seckill.inventory.mapper.ProductReleaseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangyong
 * @description 库存消费者
 * @date xxxx/2/28 20:30
 * @param
 * @return
 */
@Slf4j
@Component
@RequiredArgsConstructor
@RocketMQMessageListener(consumerGroup = "group2", topic = "product_stock")
public class InventoryConsumer implements RocketMQListener<MessageExt> {

    private final RocketMqFailMsgMapper rocketMqFailMsgMapper;

    private final ProductReleaseMapper productReleaseMapper;

    @Override
    public void onMessage(MessageExt messageExt) {

        //1.获取到消息体
        byte[] body = messageExt.getBody();
        String msg = new String(body);

        //2.转为map
        Map<String,Object> stockMap = JSONObject.parseObject(msg, HashMap.class);
        Long vaccineReleaseId = (Long)stockMap.get("productReleaseId");
        Integer amount = (Integer)stockMap.get("amount");

        //3.连续消费3次都失败,存入数据库进行人工干预
        if(messageExt.getReconsumeTimes() == 3){
            Long currentTime = System.currentTimeMillis();
            RocketMqFailMsg rocketMqFailMsg = RocketMqFailMsg.builder()
                    .id(new IdWorker().nextId())
                    .type(2651L)
                    .objKey(String.valueOf(vaccineReleaseId))
                    .reconsumeTimes(messageExt.getReconsumeTimes())
                    .msgId(messageExt.getMsgId())
                    .msgBody(msg)
                    .queueId(messageExt.getQueueId())
                    .queueOffset(messageExt.getQueueOffset())
                    .commitLogOffset(messageExt.getCommitLogOffset())
                    .brokerName(messageExt.getBrokerName())
                    .bornHostString(messageExt.getBornHostString())
                    .createTime(currentTime)
                    .createDate(DateUtil.timeStamp2dateStr(currentTime))
                    .build();
            rocketMqFailMsgMapper.insert(rocketMqFailMsg);
        }

        //4.扣减库存
        int version = productReleaseMapper.selectVersionNum(vaccineReleaseId);
        int result = productReleaseMapper.reduceDock(amount,vaccineReleaseId, version);
        if(result == 0){
            //(1).更新不成功，直接抛出异常，让rocketmq感知异常后，以便rocketmq再次投递该消息
            throw new IllegalArgumentException("InventoryConsumer扣减库存不成功");
        }
    }
}

