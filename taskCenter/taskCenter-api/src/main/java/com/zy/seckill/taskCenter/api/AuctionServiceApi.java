package com.zy.seckill.taskCenter.api;

import com.zy.seckill.common.bo.dto.PageReqDto;
import com.zy.seckill.common.bo.vo.PageVo;
import com.zy.seckill.common.bo.vo.RegionVo;
import com.zy.seckill.common.bo.vo.ResultVo;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/*
 * @Author: zhangyong
 * description: 人员微服务 API接口(内部微服务之间调用)
 * @Date: xxxx-08-31 11:20
 * @Param:
 * @Return:
 */
public interface AuctionServiceApi {

    //查询权限菜单列表并分页
    @PostMapping("iotPer/sys/permission/getAppPermissionListPageVo")
    ResultVo<PageVo<Map<String, Object>>> getAppPermissionListPageVo(@RequestBody PageReqDto pageReqDto);

    @GetMapping("iotPer/sys/dept/getDeptById/{id}")
    ResultVo<Map<String, Object>> getDeptById(@PathVariable("id") Long id);


    @GetMapping("iotPer/sys/region/getRegionByCode/{regionCode}")
    RegionVo getRegionVoByCode(@PathVariable("regionCode") String regionCode);

}
