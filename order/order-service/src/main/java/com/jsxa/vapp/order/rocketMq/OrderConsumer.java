package com.jsxa.vapp.order.rocketMq;

import com.jsxa.vapp.order.bo.po.VaccinationRecord;
import com.jsxa.vapp.order.feign.InventoryServiceFeignClient;
import com.jsxa.vapp.order.mapper.VaccinationRecordMapper;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.RequiredArgsConstructor;
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
@Component
@RequiredArgsConstructor
@RocketMQMessageListener(consumerGroup = "group1", topic = "vaccination_record")
public class OrderConsumer implements RocketMQListener<VaccinationRecord> {

    private final InventoryServiceFeignClient inventoryServiceFeignClient;

    private final VaccinationRecordMapper vaccinationRecordMapper;



    @Override
    public void onMessage(VaccinationRecord vaccinationRecord) {
        processOrder(vaccinationRecord);
    }


    /**
     * 处理订单
     * 注意: 调用inventory微服务扣减库存和将订单信息保存到数据库是在2个不同的微服务中进行操作，
     *      所以需要使用分布式事务Seata或rocketmq的事务消息保证2个动作的事务性
     */
    @GlobalTransactional(rollbackFor = Exception.class)
    private void processOrder(VaccinationRecord vaccinationRecord){
        //(1).查询到该订单对应的放苗活动id
        Long vaccineReleaseId = vaccinationRecord.getVaccineReleaseId();

        //(2).调用inventory微服务扣减库存(库存扣减采用乐观锁version版本的机制)
        Map<String, Object> resultMap = inventoryServiceFeignClient.reduceDock(vaccineReleaseId);

        //(3).将订单信息保存到数据库
        int insert = vaccinationRecordMapper.insert(vaccinationRecord);
    }
}

