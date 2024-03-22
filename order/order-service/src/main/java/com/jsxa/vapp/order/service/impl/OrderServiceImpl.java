package com.jsxa.vapp.order.service.impl;

import com.jsxa.vapp.order.bo.po.VaccinationRecord;
import com.jsxa.vapp.order.feign.InventoryServiceFeignClient;
import com.jsxa.vapp.order.mapper.VaccinationRecordMapper;
import com.jsxa.vapp.order.service.OrderService;
import io.seata.core.context.RootContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @Author zhangyong
 * @Description //VaccinationRecordService接口实现类
 * @Date 2021/02/27 15:20
 * @Param
 * @return
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final InventoryServiceFeignClient inventoryServiceFeignClient;

    private final VaccinationRecordMapper vaccinationRecordMapper;


    /**
     * 处理订单
     * 注意: 调用inventory微服务扣减库存和将订单信息保存到数据库是在2个不同的微服务中进行操作,
     * 所以需要使用分布式事务Seata或rocketmq的事务消息保证2个动作的事务性,
     * 此处使用seata的AT模式，使用方法(二者只能选其一):
     * 1.如果没有使用sharingjdbc则需要在开启分布式事务的方法或类上开启全局事务@GlobalTransactional即可
     * 2.使用了sharingjdbc则在需要在开启分布式事务的方法或类上开启全局事务@ShardingTransactionType(TransactionType.BASE)和@Transactional
     */
    @ShardingTransactionType(TransactionType.BASE)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void processOrder(VaccinationRecord vaccinationRecord) {
        //获取当前事务的XID
        String xid = RootContext.getXID();
        log.info("Order--------------> threadId:{},threadName:{},Seata_XID:{}", Thread.currentThread().getId(), Thread.currentThread().getName(), xid);

        //(1).查询到该订单对应的放苗活动id
        Long vaccineReleaseId = vaccinationRecord.getVaccineReleaseId();

        //(2).调用inventory微服务扣减库存(库存扣减采用乐观锁version版本的机制),判断库存微服务响应码是否正常，不正常则抛出异常让seata分布式事务感知该异常以便做出回滚
        Map<String, Object> resultMap = inventoryServiceFeignClient.reduceDock(vaccineReleaseId);
        Integer code = (Integer) resultMap.get("code");
        if(code != 200){
            throw new IllegalArgumentException("调用扣减库存失败");
        }

        //(3).将订单信息保存到数据库
        vaccinationRecordMapper.insert(vaccinationRecord);

    }
}