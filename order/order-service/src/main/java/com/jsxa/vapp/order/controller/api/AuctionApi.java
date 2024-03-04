package com.jsxa.vapp.order.controller.api;

import com.jsxa.vapp.common.bo.dto.PageReqDto;
import com.jsxa.vapp.common.bo.vo.PageVo;
import com.jsxa.vapp.common.bo.vo.RegionVo;
import com.jsxa.vapp.common.bo.vo.ResultVo;
import com.jsxa.vapp.common.cache.RegionCache;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.Map;


/*
 * @Author wangchao
 * @Description  物联感知系统设置模块
 * @Date 2022/11/16 10:41
 * @Param
 * @return
 **/
@RestController
@RequestMapping("iotPer/sys")
@ApiIgnore
@RequiredArgsConstructor
public class AuctionApi {


    private final RegionCache regionCache;



    @GetMapping("/region/getRegionByCode/{regionCode}")
    RegionVo getRegionByCode(@PathVariable String regionCode){
        return regionCache.getRegionVoByCode(regionCode);
    }
}
