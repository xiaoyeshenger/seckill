package com.jsxa.vapp.order.rocketMq;

import com.alibaba.fastjson.JSONObject;
import com.jsxa.vapp.common.utils.DateUtil;
import com.jsxa.vapp.common.utils.IdWorker;
import com.jsxa.vapp.order.bo.po.RocketMqFailMsg;
import com.jsxa.vapp.order.bo.po.VaccinationRecord;
import com.jsxa.vapp.order.feign.InventoryServiceFeignClient;
import com.jsxa.vapp.order.mapper.RocketMqFailMsgMapper;
import com.jsxa.vapp.order.mapper.VaccinationRecordMapper;
import com.jsxa.vapp.order.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author zhangyong
 * @description 订单消费者
 * @date 2021/2/28 20:30
 * @param
 * @return
 */
@Slf4j
@Component
@RequiredArgsConstructor
@RocketMQMessageListener(consumerGroup = "group1", topic = "vaccination_record")
public class OrderConsumer implements RocketMQListener<MessageExt> {

    private final OrderService orderService;

    private final RocketMqFailMsgMapper rocketMqFailMsgMapper;

    @Override
    public void onMessage(MessageExt messageExt) {

        //1.获取到消息体
        byte[] body = messageExt.getBody();
        String msg = new String(body);

        //2.转为java bean
        VaccinationRecord vaccinationRecord = JSONObject.parseObject(msg, VaccinationRecord.class);

        //3.连续消费3次都失败,存入数据库进行人工干预
        if(messageExt.getReconsumeTimes() == 3){
            log.error("{}消费了3次都消费失败",vaccinationRecord.toString());
            Long currentTime = System.currentTimeMillis();
            RocketMqFailMsg rocketMqFailMsg = RocketMqFailMsg.builder()
                    .id(new IdWorker().nextId())
                    .type(2650L)
                    .objKey(String.valueOf(vaccinationRecord.getId()))
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

        //4.创建订单
        orderService.processOrder(vaccinationRecord);
    }
}

