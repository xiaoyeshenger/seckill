package com.jsxa.vapp.inventory.api;


import java.util.Map;


/**
 * @Author: zhangyong
 * description: 库存微服务 API接口降级处理类（服务提供端，默认降级处理）
 * @Date: 2021-03-30 11:20
 * @Param:
 * @Return:
 */
public class InventoryServiceApiFallback implements InventoryServiceApi {


    @Override
    public Map<String, Object> reduceDock(Long vaccineReleaseId) {
        return null;
    }
}
