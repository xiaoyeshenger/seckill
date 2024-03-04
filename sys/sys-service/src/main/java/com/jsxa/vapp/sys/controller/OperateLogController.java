package com.jsxa.vapp.sys.controller;

import com.jsxa.vapp.common.annotation.Log;
import com.jsxa.vapp.common.bo.dto.OperateLogPageReqDto;
import com.jsxa.vapp.common.bo.vo.PageVo;
import com.jsxa.vapp.common.bo.vo.ResultVo;
import com.jsxa.vapp.common.enums.BusinessType;
import com.jsxa.vapp.common.valid.ValidationGroup;
import com.jsxa.vapp.sys.service.OperateLogService;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import springfox.documentation.annotations.ApiIgnore;
import org.springframework.validation.annotation.Validated;

import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;


/*
 * @Author zhangyong
 * @Description OperateLogController类
 * @Date 2022/03/02 15:39
 * @Param
 * @return
 **/
@RestController
@RequestMapping("/sys/operateLog")
@Api(tags = "操作日志相关接口")
@Validated
@RequiredArgsConstructor
public class OperateLogController {

    private final OperateLogService operateLogService;




    /**
     * @Author zhangyong
     * @Description //(2) 通过id删除操作日志信息
     * @Date 2022/03/02 15:39
     * @Param
     * @return
     *//*
    @Log(businessType = BusinessType.DELETE)
    @ApiOperation("通过id删除操作日志信息")
    @ApiImplicitParam(name = "id", value = "操作日志id", example = "xcn9247jaeklakhdaksfj", dataType = "String", paramType = "form")
    @GetMapping("/deleteOperateLogById/{id}")
    public ResultVo<Map<String, Object>> deleteOperateLogById(
            @ApiIgnore @RequestHeader Map<String, Object> headerMap,
            @PathVariable String id
    ){
         return ResultVo.ok(operateLogService.deleteOperateLogById(headerMap,id));
    }*/


    /**
     * @Author zhangyong
     * @Description //(4) 通过id查询操作日志信息
     * @Date 2022/03/02 15:39
     * @Param
     * @return
     */
    @ApiOperation("通过id查询操作日志信息")
    @ApiImplicitParam(name = "id", value = "操作日志id", example = "xcn9247jaeklakhdaksfj", dataType = "String", paramType = "form")
    @GetMapping("/getOperateLogById/{id}")
    public ResultVo<Map<String, Object>> getOperateLogById(
            @ApiIgnore @RequestHeader Map<String, Object> headerMap,
            @PathVariable String id
    ){
        return ResultVo.ok(operateLogService.getOperateLogById(headerMap,id));
    }

    /**
     * @Author zhangyong
     * @Description //(5) 查询所有的操作日志信息列表并分页(支持关键字查询)
     * @Date 2022/03/02 15:39
     * @Param
     * @return
     */
     @ApiOperation("查询所有的操作日志信息列表并分页(支持关键字查询)")
    @PostMapping(value = "getOperateLogListPageVo", produces = { "application/json" })
    public ResultVo<PageVo<Map<String, Object>>> getOperateLogListPageVo(
            @ApiIgnore @RequestHeader Map<String, Object> headerMap,
            @Validated({ValidationGroup.ValidationPage.class})  @RequestBody OperateLogPageReqDto operateLogPageReqDto
    ){
        return ResultVo.ok(operateLogService.getOperateLogListPageVo(headerMap,operateLogPageReqDto));
    }

    /**
     * @Author zhangyong
     * @Description //(8) 导出操作日志到excel
     * @Date 2022/07/29 11:14
     * @Param
     * @return
     */
    @ApiOperation("导出操作日志到excel")
    @PostMapping(value = "exportToExcel", produces = { "application/json" })
    public void exportToExcel(
            @Validated @RequestBody OperateLogPageReqDto operateLogPageReqDto,
            @ApiIgnore HttpServletResponse response
    ){
        operateLogService.exportToExcel(operateLogPageReqDto,response);
    }
}