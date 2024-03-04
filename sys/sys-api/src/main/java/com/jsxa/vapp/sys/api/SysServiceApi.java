package com.jsxa.vapp.sys.api;

import com.jsxa.vapp.common.bo.dto.PageReqDto;
import com.jsxa.vapp.common.bo.vo.PageVo;
import com.jsxa.vapp.common.bo.vo.RegionVo;
import com.jsxa.vapp.common.bo.vo.ResultVo;
import com.jsxa.vapp.sys.bo.po.PageTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/*
 * @Author: zhangyong
 * description: 人员微服务 API接口(内部微服务之间调用)
 * @Date: 2021-08-31 11:20
 * @Param:
 * @Return:
 */
public interface SysServiceApi {

    //查询权限菜单列表并分页
    @PostMapping("iotPer/sys/permission/getAppPermissionListPageVo")
    ResultVo<PageVo<Map<String, Object>>> getAppPermissionListPageVo(@RequestBody PageReqDto pageReqDto);

    @GetMapping("iotPer/sys/dept/getDeptById/{id}")
    ResultVo<Map<String, Object>> getDeptById(@PathVariable("id") Long id);

    //获取模板
    @GetMapping("iotPer/sys/pageTemplate/getPageTemplateByParkId/{parkId}")
    PageTemplate getPageTemplateByParkId(@PathVariable("parkId") Long parkId);


    @GetMapping("iotPer/sys/region/getRegionByCode/{regionCode}")
    RegionVo getRegionVoByCode(@PathVariable("regionCode") String regionCode);

}
