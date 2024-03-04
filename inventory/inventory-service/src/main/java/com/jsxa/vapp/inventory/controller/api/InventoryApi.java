package com.jsxa.vapp.inventory.controller.api;

import com.jsxa.vapp.inventory.service.VaccineReleaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;


/*
 * @Author: zhangyong
 * description: 银海_微服务API接口（内部调用）
 * @Date: 2021-08-31 12:07
 * @Param:
 * @Return:
 */
@RestController
@RequestMapping("vapp/inventory")
@ApiIgnore
@RequiredArgsConstructor
public class InventoryApi {

    private final VaccineReleaseService vaccineReleaseService;


    /**
     * 库存扣减
     */
    @GetMapping("/reduceDock/{vaccineReleaseId}")
    Map<String, Object> reduceDock(@PathVariable Long vaccineReleaseId){
        return vaccineReleaseService.reduceDock(vaccineReleaseId);
    }
}
