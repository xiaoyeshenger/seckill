package com.zy.seckill.order.feign;


import com.zy.seckill.common.utils.DateUtil;
import com.zy.seckill.order.bo.po.MicroServiceInvokeFailMsg;
import com.zy.seckill.order.dao.MicroServiceInvokeFailMsgDao;
import feign.hystrix.FallbackFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class InventoryServiceFallBackFactory  implements FallbackFactory<InventoryServiceFeignClient> {

    private final MicroServiceInvokeFailMsgDao microServiceInvokeFailMsgDao;

    @Override
    public InventoryServiceFeignClient create(Throwable throwable) {
        log.info("=================》 调用库存扣减服务出错,降级原因: {}",throwable.getMessage());
        InventoryServiceFeignClient inventoryServiceFeignClient = new InventoryServiceFeignClient() {
            @Override
            public Map<String, Object> reduceDock(Long vaccineReleaseId) {
                //(1).保存调用失败消息到数据库，以便后期处理
                Long currentTime = System.currentTimeMillis();
                MicroServiceInvokeFailMsg microServiceInvokeFailMsg = MicroServiceInvokeFailMsg.builder()
                        .errorMsg(throwable.getMessage())
                        .type(3L)
                        .serviceName("库存服务")
                        .serviceValue("Inventory")
                        .methodName("库存扣减")
                        .methodValue("reduceDock(Long vaccineReleaseId)")
                        .paramStr("vaccineReleaseId:"+vaccineReleaseId)
                        .createTime(currentTime)
                        .createDate(DateUtil.timeStamp2dateStr(currentTime))
                        .build();
                microServiceInvokeFailMsgDao.insert(microServiceInvokeFailMsg);

                //(2).返回结果
                Map<String, Object> resultMap = new HashMap<>();
                resultMap.put("code",503);
                resultMap.put("msg",throwable.getMessage());
                resultMap.put("data",null);
                return resultMap;
            }
        };
        return inventoryServiceFeignClient;

    }
}
