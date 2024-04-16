package com.zy.seckill.inventory.controller.api;

import com.zy.seckill.inventory.service.ProductReleaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;


/*
 * @Author: zhangyong
 * description: 银海_微服务API接口（内部调用）
 * @Date: xxxx-08-31 12:07
 * @Param:
 * @Return:
 */
@RestController
@RequestMapping("vapp/inventory")
@ApiIgnore
@RequiredArgsConstructor
public class InventoryApi {

    private final ProductReleaseService productReleaseService;


    /**
     * 库存扣减
     */
    @GetMapping("/reduceDock/{productReleaseId}")
    Map<String, Object> reduceDock(@PathVariable Long productReleaseId){
        return productReleaseService.reduceDock(productReleaseId);
    }
}
