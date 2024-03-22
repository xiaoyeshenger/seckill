package com.jsxa.vapp.order.feign;

import com.jsxa.vapp.common.config.FeignTokenInterceptor;
import com.jsxa.vapp.inventory.api.InventoryServiceApi;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(value="inventory-service",configuration={FeignTokenInterceptor.class},fallbackFactory  = InventoryServiceFallBackFactory.class)
public interface InventoryServiceFeignClient extends InventoryServiceApi {}
