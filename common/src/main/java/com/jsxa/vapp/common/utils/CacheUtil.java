package com.jsxa.vapp.common.utils;

import com.jsxa.vapp.common.bo.vo.RegionVo;
import com.jsxa.vapp.common.cache.RegionCache;
import com.jsxa.vapp.common.redis.RedisKey;
import com.jsxa.vapp.common.redis.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class CacheUtil {

    private final RedisService redisService;

    private final RegionCache regionCache;


    public String getParkName(Long parkId) {
        String parkName = (String)redisService.hmGet(RedisKey.SYS_PARK_KEY, String.valueOf(parkId));
        if (ObjUtil.isEmpty(parkName)){
            throw new IllegalArgumentException("ID为 "+parkId+" 的园区不存在");
        }
        return parkName;
    }

    public RegionVo getRegionByCode(String regionCode) {
        RegionVo regionVoByCode = regionCache.getRegionVoByCode(regionCode);
        if (ObjUtil.isEmpty(regionVoByCode)){
            throw new IllegalArgumentException("码值为 "+regionCode+" 的区域不存在");
        }
        return regionVoByCode;
    }

    public String getRegionName(String regionCode) {
        RegionVo regionVoByCode = regionCache.getRegionVoByCode(regionCode);
        if (ObjUtil.isEmpty(regionVoByCode)){
            throw new IllegalArgumentException("码值为 "+regionCode+" 的区域不存在");
        }
        return regionVoByCode.getName();
    }

    public String getParentAndSelfRegionName(String regionCode) {
        RegionVo regionVoByCode = regionCache.getRegionVoByCode(regionCode);
        if (ObjUtil.isEmpty(regionVoByCode)){
            throw new IllegalArgumentException("码值为 "+regionCode+" 的区域不存在");
        }
        String parentCode = regionVoByCode.getParentCode();
        RegionVo parentRegion = regionCache.getRegionVoByCode(parentCode);
        if (ObjUtil.isEmpty(parentRegion)){
            throw new IllegalArgumentException("码值为 "+parentCode+" 的父级1区域不存在");
        }

        String pcCode = parentRegion.getParentCode();
        RegionVo pcRegion = regionCache.getRegionVoByCode(pcCode);
        if (ObjUtil.isEmpty(pcRegion)){
            throw new IllegalArgumentException("码值为 "+pcCode+" 的父级2区域不存在");
        }
        return pcRegion.getName() + "-" + parentRegion.getName() + "-" + regionVoByCode.getName();
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
