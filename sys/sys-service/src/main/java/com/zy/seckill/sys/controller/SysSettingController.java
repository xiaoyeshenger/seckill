package com.zy.seckill.sys.controller;

import com.zy.seckill.common.bo.vo.PageVo;
import com.zy.seckill.common.bo.vo.ResultVo;
import com.zy.seckill.sys.bo.dto.SysSettingReqDto;
import com.zy.seckill.sys.bo.dto.SysSettingPageReqDto;
import com.zy.seckill.sys.service.SysSettingService;
import com.zy.seckill.common.enums.BusinessType;
import com.zy.seckill.common.valid.ValidationGroup.ValidationUpdate;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import springfox.documentation.annotations.ApiIgnore;
import org.springframework.validation.annotation.Validated;
import com.zy.seckill.common.annotation.Log;
import lombok.RequiredArgsConstructor;

import java.util.Map;


/*
 * @Author zhangyong
 * @Description SysSettingController类
 * @Date 2022/03/09 09:38
 * @Param
 * @return
 **/
@RestController
@RequestMapping("/sys/sysSetting")
@Api(tags = "系统设置相关接口")
@Validated
@RequiredArgsConstructor
public class SysSettingController {

    private final SysSettingService sysSettingService;


    /*
     * @Author zhangyong
     * @Description //(1) 添加系统设置信息
     * @Date 2022/03/09 09:38
     * @Param
     * @return
     **/
    @Log(businessType = BusinessType.INSERT)
    @ApiOperation("添加系统设置信息")
    @PostMapping(value = "addSysSetting", produces = { "application/json" })
    public ResultVo<Map<String, Object>> addSysSetting(
            @ApiIgnore @RequestHeader Map<String, Object> headerMap,
            @Validated @RequestBody SysSettingReqDto sysSettingReqDto
    ){
        return ResultVo.ok(sysSettingService.addSysSetting(headerMap,sysSettingReqDto));
    }

    /*
     * @Author zhangyong
     * @Description //(2) 通过id删除系统设置信息
     * @Date 2022/03/09 09:38
     * @Param
     * @return
     **/
    @Log(businessType = BusinessType.DELETE)
    @ApiOperation("通过id删除系统设置信息")
    @ApiImplicitParam(name = "id", value = "系统设置id", example = "1386532156978321", dataType = "Long", paramType = "form")
    @GetMapping("/deleteSysSettingById/{id}")
    public ResultVo<Map<String, Object>> deleteSysSettingById(
            @ApiIgnore @RequestHeader Map<String, Object> headerMap,
            @PathVariable Long id
    ){
         return ResultVo.ok(sysSettingService.deleteSysSettingById(headerMap,id));
    }

    /*
     * @Author zhangyong
     * @Description //(3) 更新系统设置信息
     * @Date 2022/03/09 09:38
     * @Param
     * @return
     **/
    @Log(businessType = BusinessType.UPDATE)
    @ApiOperation("更新系统设置信息")
    @PostMapping(value = "updateSysSetting", produces = {"application/json"})
    public ResultVo<Map<String, Object>> updateSysSetting(
            @ApiIgnore @RequestHeader Map<String, Object> headerMap,
            @Validated({ValidationUpdate.class}) @RequestBody SysSettingReqDto sysSettingReqDto
    ){
        return ResultVo.ok(sysSettingService.updateSysSetting(headerMap,sysSettingReqDto));
    }

    /*
     * @Author zhangyong
     * @Description //(4) 通过id查询系统设置信息
     * @Date 2022/03/09 09:38
     * @Param
     * @return
     **/
    @ApiOperation("通过id查询系统设置信息")
    @ApiImplicitParam(name = "id", value = "系统设置id", example = "136985121358326", dataType = "Long", paramType = "form")
    @GetMapping("/getSysSettingById/{id}")
    public ResultVo<Map<String, Object>> getSysSettingById(
            @ApiIgnore @RequestHeader Map<String, Object> headerMap,
            @PathVariable Long id
    ){
        return ResultVo.ok(sysSettingService.getSysSettingById(headerMap,id));
    }

    /*
     * @Author zhangyong
     * @Description //(5) 查询所有的系统设置信息列表并分页(支持关键字查询)
     * @Date 2022/03/09 09:38
     * @Param
     * @return
     **/
     @ApiOperation("查询所有的系统设置信息列表并分页(支持关键字查询)")
    @PostMapping(value = "getSysSettingListPageVo", produces = { "application/json" })
    public ResultVo<PageVo<Map<String, Object>>> getSysSettingListPageVo(
            @ApiIgnore @RequestHeader Map<String, Object> headerMap,
            @Validated @RequestBody SysSettingPageReqDto sysSettingPageReqDto
    ){
        return ResultVo.ok(sysSettingService.getSysSettingListPageVo(headerMap,sysSettingPageReqDto));
    }
}