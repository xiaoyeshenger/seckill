package com.zy.seckill.order.feign;

import com.zy.seckill.common.config.FeignTokenInterceptor;
import com.zy.seckill.inventory.api.InventoryServiceApi;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(value="inventory-service",configuration={FeignTokenInterceptor.class},fallbackFactory  = InventoryServiceFallBackFactory.class)
public interface InventoryServiceFeignClient extends InventoryServiceApi {}
