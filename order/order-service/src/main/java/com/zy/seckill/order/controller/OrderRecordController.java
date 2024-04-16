package com.zy.seckill.order.controller;

import com.zy.seckill.common.bo.vo.ResultVo;
import com.zy.seckill.common.bo.vo.PageVo;
import com.zy.seckill.order.bo.dto.OrderRecordReqDto;
import com.zy.seckill.order.bo.dto.OrderRecordPageReqDto;
import com.zy.seckill.order.service.OrderRecordService;
import com.zy.seckill.common.enums.BusinessType;
import com.zy.seckill.common.valid.ValidationGroup;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import io.swagger.annotations.*;
import springfox.documentation.annotations.ApiIgnore;
import org.springframework.validation.annotation.Validated;
import com.zy.seckill.common.annotation.Log;
import lombok.RequiredArgsConstructor;

import java.util.Map;


/**
 * @Author zhangyong
 * @Description OrderRecordController类
 * @Date xxxx/02/27 15:20
 * @Param
 * @return
 */
@RestController
@RequestMapping("/order/orderRecord")
@Api(tags = "秒杀相关接口")
@Validated
@RequiredArgsConstructor
public class OrderRecordController {

    private final OrderRecordService orderRecordService;



    /**
     * @Author zhangyong
     * @Description //(1) 添加秒杀信息
     * @Date xxxx/02/27 15:20
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.INSERT)
    @ApiOperation("添加秒杀信息")
    @PostMapping(value = "addOrderRecord", produces = { "application/json" })
    public ResultVo<Map<String, Object>> addOrderRecord(
            @Validated @RequestBody OrderRecordReqDto vaccinationRecordReqDto
    ){
        return ResultVo.ok(orderRecordService.addOrderRecord(vaccinationRecordReqDto));
    }

    /**
     * @Author zhangyong
     * @Description //(2) 通过id删除秒杀信息
     * @Date xxxx/02/27 15:20
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.DELETE)
    @ApiOperation("通过id删除秒杀信息")
    @ApiImplicitParam(name = "id", value = "秒杀id", example = "1386532156978321", dataType = "Long", paramType = "form")
    @GetMapping("/deleteOrderRecordById/{id}")
    public ResultVo<Map<String, Object>> deleteOrderRecordById(
            @PathVariable Long id
    ){
         return ResultVo.ok(orderRecordService.deleteOrderRecordById(id));
    }


    /**
     * @Author zhangyong
     * @Description //(4) 通过id查询秒杀信息
     * @Date xxxx/02/27 15:20
     * @Param
     * @return
     */
    @ApiOperation("通过id查询秒杀信息")
    @ApiImplicitParam(name = "id", value = "秒杀id", example = "136985121358326", dataType = "Long", paramType = "form")
    @GetMapping("/getOrderRecordById/{id}")
    public ResultVo<Map<String, Object>> getOrderRecordById(
            @PathVariable Long id
    ){
        return ResultVo.ok(orderRecordService.getOrderRecordById(id));
    }

    /**
     * @Author zhangyong
     * @Description //(5) 查询所有的秒杀信息列表并分页(支持关键字查询)
     * @Date xxxx/02/27 15:20
     * @Param
     * @return
     */
    @ApiOperation("查询所有的秒杀信息列表并分页(支持关键字查询)")
    @PostMapping(value = "getOrderRecordListPageVo", produces = { "application/json" })
    public ResultVo<PageVo<Map<String, Object>>> getOrderRecordListPageVo(
            @Validated({ValidationGroup.ValidationPage.class}) @RequestBody OrderRecordPageReqDto vaccinationRecordPageReqDto
    ){
        return ResultVo.ok(orderRecordService.getOrderRecordListPageVo(vaccinationRecordPageReqDto));
    }

    /**
     * @Author zhangyong
     * @Description //(6) 下载秒杀标准上传模板
     * @Date xxxx/02/27 15:20
     * @Param
     * @return
     */
    @ApiOperation("下载秒杀标准上传模板")
    @GetMapping("/downloadTemplateExcel")
    public void downloadTemplateExcel(
        @ApiIgnore HttpServletResponse response
    ){
        orderRecordService.downloadTemplateExcel(response);
    }

    /**
     * @Author zhangyong
     * @Description //(6) 通过excel导入秒杀
     * @Date xxxx/02/27 15:20
     * @Param
     * @return
     */
    @ApiOperation("通过excel导入秒杀")
    @PostMapping("importByExcel")
    public  ResultVo<Map<String, Object>> importByExcel(
        @ApiIgnore MultipartHttpServletRequest request
    ){
        return ResultVo.ok(orderRecordService.importByExcel(request));
    }

    /**
     * @Author zhangyong
     * @Description //(8) 导出秒杀到excel
     * @Date xxxx/02/27 15:20
     * @Param
     * @return
     */
    @ApiOperation("导出秒杀到excel")
    @GetMapping("/exportToExcel")
    public void exportToExcel(
        @ApiIgnore HttpServletResponse response,
        @Validated @RequestBody OrderRecordPageReqDto vaccinationRecordPageReqDto
    ){
        orderRecordService.exportToExcel(vaccinationRecordPageReqDto,response);
    }

}