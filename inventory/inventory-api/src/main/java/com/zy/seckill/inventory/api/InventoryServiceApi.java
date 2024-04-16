package com.zy.seckill.inventory.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

/**
 * @Author: zhangyong
 * description: 库存微服务
 * @Date: xxxx-03-30 11:16
 * @Param:
 * @Return:
 */
public interface InventoryServiceApi {

    /**
     * 库存扣减
     */
    @GetMapping("vapp/inventory/reduceDock/{productReleaseId}")
    Map<String, Object> reduceDock(@PathVariable("productReleaseId") Long productReleaseId);

}
