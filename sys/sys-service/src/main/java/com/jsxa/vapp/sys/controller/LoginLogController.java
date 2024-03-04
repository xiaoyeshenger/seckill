package com.jsxa.vapp.sys.controller;

import com.jsxa.vapp.common.annotation.Log;
import com.jsxa.vapp.common.bo.dto.LoginLogPageReqDto;
import com.jsxa.vapp.common.bo.dto.OperateLogPageReqDto;
import com.jsxa.vapp.common.bo.vo.PageVo;
import com.jsxa.vapp.common.bo.vo.ResultVo;
import com.jsxa.vapp.common.enums.BusinessType;
import com.jsxa.vapp.common.valid.ValidationGroup;
import com.jsxa.vapp.sys.service.LoginLogService;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import springfox.documentation.annotations.ApiIgnore;
import org.springframework.validation.annotation.Validated;

import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;


/*
 * @Author zhangyong
 * @Description LoginLogController类
 * @Date 2022/03/02 16:01
 * @Param
 * @return
 **/
@RestController
@RequestMapping("/sys/loginLog")
@Api(tags = "登录日志相关接口")
@Validated
@RequiredArgsConstructor
public class LoginLogController {

    private final LoginLogService loginLogService;


/*    *//**
     * @Author zhangyong
     * @Description //(2) 通过id删除登录日志信息
     * @Date 2022/03/02 16:01
     * @Param
     * @return
     *//*
    @Log(businessType = BusinessType.DELETE)
    @ApiOperation("通过id删除登录日志信息")
    @ApiImplicitParam(name = "id", value = "登录日志id", example = "xcn9247jaeklakhdaksfj", dataType = "String", paramType = "form")
    @GetMapping("/deleteLoginLogById/{id}")
    public ResultVo<Map<String, Object>> deleteLoginLogById(
            @ApiIgnore @RequestHeader Map<String, Object> headerMap,
            @PathVariable String id
    ){
         return ResultVo.ok(loginLogService.deleteLoginLogById(headerMap,id));
    }*/


    /**
     * @Author zhangyong
     * @Description //(4) 通过id查询登录日志信息
     * @Date 2022/03/02 16:01
     * @Param
     * @return
     */
    @ApiOperation("通过id查询登录日志信息")
    @ApiImplicitParam(name = "id", value = "登录日志id", example = "xcn9247jaeklakhdaksfj", dataType = "String", paramType = "form")
    @GetMapping("/getLoginLogById/{id}")
    public ResultVo<Map<String, Object>> getLoginLogById(
            @ApiIgnore @RequestHeader Map<String, Object> headerMap,
            @PathVariable String id
    ){
        return ResultVo.ok(loginLogService.getLoginLogById(headerMap,id));
    }

    /**
     * @Author zhangyong
     * @Description //(5) 查询所有的登录日志信息列表并分页(支持关键字查询)
     * @Date 2022/03/02 16:01
     * @Param
     * @return
     */
     @ApiOperation("查询所有的登录日志信息列表并分页(支持关键字查询)")
    @PostMapping(value = "getLoginLogListPageVo", produces = { "application/json" })
    public ResultVo<PageVo<Map<String, Object>>> getLoginLogListPageVo(
            @ApiIgnore @RequestHeader Map<String, Object> headerMap,
            @Validated({ValidationGroup.ValidationPage.class}) @RequestBody LoginLogPageReqDto loginLogPageReqDto
    ){
        return ResultVo.ok(loginLogService.getLoginLogListPageVo(headerMap,loginLogPageReqDto));
    }


    /**
     * @Author zhangyong
     * @Description //(8) 导出登录日志到excel
     * @Date 2022/07/29 11:14
     * @Param
     * @return
     */
    @ApiOperation("导出登录日志到excel")
    @PostMapping(value = "exportToExcel", produces = { "application/json" })
    public void exportToExcel(
            @Validated @RequestBody LoginLogPageReqDto loginLogPageReqDto,
            @ApiIgnore HttpServletResponse response
    ){
        loginLogService.exportToExcel(loginLogPageReqDto,response);
    }
}