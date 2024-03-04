package com.jsxa.vapp.sys.controller;

import com.jsxa.vapp.common.annotation.Log;
import com.jsxa.vapp.common.bo.vo.PageVo;
import com.jsxa.vapp.common.bo.vo.ResultVo;
import com.jsxa.vapp.common.enums.BusinessType;
import com.jsxa.vapp.sys.bo.dto.NoticeReqDto;
import com.jsxa.vapp.sys.bo.dto.NoticePageReqDto;
import com.jsxa.vapp.sys.service.NoticeService;
import com.jsxa.vapp.common.valid.ValidationGroup.ValidationUpdate;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import springfox.documentation.annotations.ApiIgnore;
import org.springframework.validation.annotation.Validated;

import lombok.RequiredArgsConstructor;

import java.util.Map;



/*
 * @Author zhangyong
 * @Description NoticeController类
 * @Date 2022/02/28 14:46
 * @Param
 * @return
 **/
@RestController
@RequestMapping("/sys/notice")
@Api(tags = "通知相关接口")
@Validated
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;


    /*
     * @Author zhangyong
     * @Description //(1) 添加通知信息
     * @Date 2022/02/28 14:46
     * @Param
     * @return
     **/
    @Log(businessType = BusinessType.INSERT)
    @ApiOperation("添加通知信息")
    @PostMapping(value = "addNotice", produces = { "application/json" })
    public ResultVo<Map<String, Object>> addNotice(
            @ApiIgnore @RequestHeader Map<String, Object> headerMap,
            @Validated @RequestBody NoticeReqDto noticeReqDto
    ){
        return ResultVo.ok(noticeService.addNotice(headerMap,noticeReqDto));
    }

    /*
     * @Author zhangyong
     * @Description //(2) 通过id删除通知信息
     * @Date 2022/02/28 14:46
     * @Param
     * @return
     **/
    @Log(businessType = BusinessType.DELETE)
    @ApiOperation("通过id删除通知信息")
    @ApiImplicitParam(name = "id", value = "通知id", example = "133", dataType = "Long", paramType = "form")
    @GetMapping("/deleteNoticeById/{id}")
    public ResultVo<Map<String, Object>> deleteNoticeById(
            @ApiIgnore @RequestHeader Map<String, Object> headerMap,
            @PathVariable Long id
    ){
         return ResultVo.ok(noticeService.deleteNoticeById(headerMap,id));
    }

    /*
     * @Author zhangyong
     * @Description //(3) 更新通知信息
     * @Date 2022/02/28 14:46
     * @Param
     * @return
     **/
    @Log(businessType = BusinessType.UPDATE)
    @ApiOperation("更新通知信息")
    @PostMapping(value = "updateNotice", produces = {"application/json"})
    public ResultVo<Map<String, Object>> updateNotice(
            @ApiIgnore @RequestHeader Map<String, Object> headerMap,
            @Validated({ValidationUpdate.class}) @RequestBody NoticeReqDto noticeReqDto
    ){
        return ResultVo.ok(noticeService.updateNotice(headerMap,noticeReqDto));
    }

    /*
     * @Author zhangyong
     * @Description //(4) 通过id查询通知信息
     * @Date 2022/02/28 14:46
     * @Param
     * @return
     **/
    @ApiOperation("通过id查询通知信息")
    @ApiImplicitParam(name = "id", value = "通知id", example = "133", dataType = "Long", paramType = "form")
    @GetMapping("/getNoticeById/{id}")
    public ResultVo<Map<String, Object>> getNoticeById(
            @ApiIgnore @RequestHeader Map<String, Object> headerMap,
            @PathVariable Long id
    ){
        return ResultVo.ok(noticeService.getNoticeById(headerMap,id));
    }

    /*
     * @Author zhangyong
     * @Description //(5) 查询所有的通知信息列表并分页(支持关键字查询)
     * @Date 2022/02/28 14:46
     * @Param
     * @return
     **/
    @ApiOperation("查询所有的通知信息列表并分页(支持关键字查询)")
    @PostMapping(value = "getNoticeListPageVo", produces = { "application/json" })
    public ResultVo<PageVo<Map<String, Object>>> getNoticeListPageVo(
            @ApiIgnore @RequestHeader Map<String, Object> headerMap,
            @Validated @RequestBody NoticePageReqDto noticePageReqDto
    ){
        return ResultVo.ok(noticeService.getNoticeListPageVo(headerMap,noticePageReqDto));
    }
}