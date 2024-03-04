package com.jsxa.vapp.taskCenter.api;

import com.jsxa.vapp.common.bo.dto.PageReqDto;
import com.jsxa.vapp.common.bo.vo.PageVo;
import com.jsxa.vapp.common.bo.vo.RegionVo;
import com.jsxa.vapp.common.bo.vo.ResultVo;

import java.util.Map;


/*
 * @Author: zhangyong
 * description: sys数据微服务 API接口降级处理类（服务提供端，默认降级处理）
 * @Date: 2021-08-31 11:20
 * @Param:
 * @Return:
 */
public class AuctionServiceApiFallback implements AuctionServiceApi {

    @Override
    public ResultVo<PageVo<Map<String, Object>>> getAppPermissionListPageVo(PageReqDto pageReqDto) {
        return null;
    }

    @Override
    public ResultVo<Map<String, Object>> getDeptById(Long id) {
        return null;
    }

    @Override
    public RegionVo getRegionVoByCode(String regionCode) {
        return null;
    }
}
