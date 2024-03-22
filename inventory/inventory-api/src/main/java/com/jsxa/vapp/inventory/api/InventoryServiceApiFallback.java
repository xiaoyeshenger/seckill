package com.jsxa.vapp.inventory.api;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;


/**
 * @Author: zhangyong
 * description: 库存微服务 API接口降级处理类（服务提供端，默认降级处理）
 * @Date: 2021-03-30 11:20
 * @Param:
 * @Return:
 */
@Slf4j
@Component
public class InventoryServiceApiFallback implements InventoryServiceApi {


    @Override
    public Map<String, Object> reduceDock(Long vaccineReleaseId) {
        log.info("=======》进入库存扣减降级服务");
        return null;
    }
}
