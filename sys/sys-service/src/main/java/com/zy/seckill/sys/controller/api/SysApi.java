package com.zy.seckill.sys.controller.api;

import com.zy.seckill.common.bo.dto.PageReqDto;
import com.zy.seckill.common.bo.vo.PageVo;
import com.zy.seckill.common.bo.vo.RegionVo;
import com.zy.seckill.common.bo.vo.ResultVo;
import com.zy.seckill.sys.bo.po.PageTemplate;
import com.zy.seckill.sys.service.DeptService;
import com.zy.seckill.sys.service.PageTemplateService;
import com.zy.seckill.sys.service.PermissionService;
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
public class SysApi {

    private final PermissionService permissionService;

    private final DeptService deptService;

    private final PageTemplateService pageTemplateService;


    //查询APP的权限列表并分页
    @PostMapping("/permission/getAppPermissionListPageVo")
    ResultVo<PageVo<Map<String, Object>>> getAppPermissionListPageVo(@RequestBody PageReqDto pageReqDto){
        return ResultVo.ok(permissionService.getAppPermissionListPageVo(pageReqDto));
    }


    @GetMapping("/dept/getDeptById/{id}")
    ResultVo<Map<String, Object>> getDeptById(@PathVariable Long id){
        return ResultVo.ok(deptService.getDeptById(new HashMap<>(),id));
    }


    @GetMapping("/pageTemplate/getPageTemplateByParkId/{parkId}")
    PageTemplate getPageTemplateByParkId(@PathVariable Long parkId){
        return pageTemplateService.getPageTemplateByParkId(parkId);
    }
}
