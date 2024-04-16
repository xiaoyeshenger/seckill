package com.zy.seckill.common.utils;

import com.zy.seckill.common.bo.vo.RegionVo;
import com.zy.seckill.common.redis.RedisKey;
import com.zy.seckill.common.redis.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class CacheUtil {

    private final RedisService redisService;


    public String getParkName(Long parkId) {
        String parkName = (String)redisService.hmGet(RedisKey.SYS_PARK_KEY, String.valueOf(parkId));
        if (ObjUtil.isEmpty(parkName)){
            throw new IllegalArgumentException("ID为 "+parkId+" 的园区不存在");
        }
        return parkName;
    }

    public String getDataDictName(Long dataDictId) {
        String dataDictName = (String)redisService.hmGet(RedisKey.SYS_DATADICT_KEY, String.valueOf(dataDictId));
        if (ObjUtil.isEmpty(dataDictName)){
            throw new IllegalArgumentException("ID为 "+dataDictId+" 的数据字典不存在");
        }
        return dataDictName;
    }

    //通过父级id+字典名称查找字典ID
    public Long getDataDictId(String dataDictName) {

        Integer dataDictIdInt = (Integer) redisService.get(dataDictName);
        if (ObjUtil.isEmpty(dataDictIdInt)){
            throw new IllegalArgumentException("名称为 "+dataDictName+" 的数据字典不存在");
        }
        Long dataDictId = dataDictIdInt.longValue();
        return dataDictId;
    }

    public String getProductNameById(String productId) {

        Object productInfoObj = redisService.hmGet("productInfo", productId);
        if (ObjUtil.isEmpty(productInfoObj)){
            throw new IllegalArgumentException("ID为 "+productId+" 的产品不存在");
        }
        String productInfo = (String) productInfoObj;
        return productInfo;
    }
}
