package com.zy.seckill.sys.controller;

import com.zy.seckill.common.bo.dto.OperateLogPageReqDto;
import com.zy.seckill.common.bo.vo.PageVo;
import com.zy.seckill.common.bo.vo.ResultVo;
import com.zy.seckill.common.bo.dto.HttpPushLogPageReqDto;
import com.zy.seckill.common.valid.ValidationGroup;
import com.zy.seckill.sys.bo.dto.HttpPushLogReqDto;
import com.zy.seckill.sys.service.HttpPushLogService;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import springfox.documentation.annotations.ApiIgnore;
import org.springframework.validation.annotation.Validated;
import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;


/*
 * @Author wangchao
 * @Description HttpPushLogController类
 * @Date 2023/03/23 11:02
 * @Param
 * @return
 **/
@RestController
@RequestMapping("/httpPushLog")
@Api(tags = "HTTP推送日志相关接口")
@Validated
@RequiredArgsConstructor
public class HttpPushLogController {

    private final HttpPushLogService httpPushLogService;


    /**
     * @author zhangyong
     * @description //(1) 通过id查询HTTP推送日志信息
     * @date 2023/3/29 15:39
     * @param
     * @return
     */
    @ApiOperation("通过id查询HTTP推送日志信息")
    @ApiImplicitParam(name = "id", value = "HTTP推送日志id", example = "xcn9247jaeklakhdaksfj", dataType = "String", paramType = "form")
    @GetMapping("/getHttpPushLogById/{id}")
    public ResultVo<Map<String, Object>> getHttpPushLogById(
            @PathVariable String id
    ){
        return ResultVo.ok(httpPushLogService.getHttpPushLogById(id));
    }

    /**
     * @Author zhangyong
     * @Description //(2) 查询所有的HTTP推送日志信息列表并分页(支持关键字查询)
     * @Date 2023/03/23 11:02
     * @Param
     * @return
     */
    @ApiOperation("查询所有的HTTP推送日志信息列表并分页(支持关键字查询)")
    @PostMapping(value = "getHttpPushLogListPageVo", produces = { "application/json" })
    public ResultVo<PageVo<Map<String, Object>>> getHttpPushLogListPageVo(
            @Validated({ValidationGroup.ValidationPage.class}) @RequestBody HttpPushLogPageReqDto httpPushLogPageReqDto
    ){
        return ResultVo.ok(httpPushLogService.getHttpPushLogListPageVo(httpPushLogPageReqDto));
    }


    /**
     * @author zhangyong
     * @description (3) 导出HTTP推送日志到excel
     * @date 2023/3/31 16:18
     * @param
     * @return
     */
    @ApiOperation("导出HTTP推送日志到excel")
    @PostMapping(value = "exportToExcel", produces = { "application/json" })
    public void exportToExcel(
            @Validated @RequestBody HttpPushLogPageReqDto httpPushLogPageReqDto,
            @ApiIgnore HttpServletResponse response
    ){
        httpPushLogService.exportToExcel(httpPushLogPageReqDto,response);
    }


    /**
     * @author zhangyong
     * @description //(3) 导出HTTP推送日志到物联感知提供的标准excel
     * @date 2023/3/31 16:18
     * @param
     * @return
     */
    @ApiOperation("导出HTTP推送日志到物联感知提供的标准excel")
    @PostMapping(value = "exportToCdlotExcel", produces = { "application/json" })
    public void exportToCdlotExcel(
            @Validated @RequestBody HttpPushLogPageReqDto httpPushLogPageReqDto,
            @ApiIgnore HttpServletResponse response
    ){
        httpPushLogService.exportToCdlotExcel(httpPushLogPageReqDto,response);
    }


 /*
  * @Author wangchao
  * @Description
  * @Date 2023/6/13 14:10 
  * @Param  
  * @return  
  **/
    @ApiOperation("重新推送日志到物联感知")
    @PostMapping(value = "againPushLog", produces = { "application/json" })
    public ResultVo<Map<String, Object>> againPushLog(
            @Validated @RequestBody HttpPushLogReqDto httpPushLogReqDto
    ){
     return    ResultVo.ok(httpPushLogService.againPushLog(httpPushLogReqDto));
    }

}