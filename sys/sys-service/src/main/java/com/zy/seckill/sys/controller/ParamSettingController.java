package com.zy.seckill.sys.controller;

import com.zy.seckill.common.bo.vo.PageVo;
import com.zy.seckill.common.bo.vo.ResultVo;
import com.zy.seckill.sys.bo.dto.ParamSettingReqDto;
import com.zy.seckill.sys.bo.dto.ParamSettingPageReqDto;
import com.zy.seckill.sys.service.ParamSettingService;
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
 * @Description ParamSettingController类
 * @Date 2022/03/22 14:08
 * @Param
 * @return
 **/
@RestController
@RequestMapping("/sys/paramSetting")
@Api(tags = "参数设置相关接口")
@Validated
@RequiredArgsConstructor
public class ParamSettingController {

    private final ParamSettingService paramSettingService;


    /*
     * @Author zhangyong
     * @Description //(1) 添加参数设置信息
     * @Date 2022/03/22 14:08
     * @Param
     * @return
     **/
    @Log(businessType = BusinessType.INSERT)
    @ApiOperation("添加参数设置信息")
    @PostMapping(value = "addParamSetting", produces = { "application/json" })
    public ResultVo<Map<String, Object>> addParamSetting(
            @ApiIgnore @RequestHeader Map<String, Object> headerMap,
            @Validated @RequestBody ParamSettingReqDto paramSettingReqDto
    ){
        return ResultVo.ok(paramSettingService.addParamSetting(headerMap,paramSettingReqDto));
    }

    /*
     * @Author zhangyong
     * @Description //(2) 通过id删除参数设置信息
     * @Date 2022/03/22 14:08
     * @Param
     * @return
     **/
    @Log(businessType = BusinessType.DELETE)
    @ApiOperation("通过id删除参数设置信息")
    @ApiImplicitParam(name = "id", value = "参数设置id", example = "1386532156978321", dataType = "Long", paramType = "form")
    @GetMapping("/deleteParamSettingById/{id}")
    public ResultVo<Map<String, Object>> deleteParamSettingById(
            @ApiIgnore @RequestHeader Map<String, Object> headerMap,
            @PathVariable Long id
    ){
         return ResultVo.ok(paramSettingService.deleteParamSettingById(headerMap,id));
    }

    /*
     * @Author zhangyong
     * @Description //(3) 更新参数设置信息
     * @Date 2022/03/22 14:08
     * @Param
     * @return
     **/
    @Log(businessType = BusinessType.UPDATE)
    @ApiOperation("更新参数设置信息")
    @PostMapping(value = "updateParamSetting", produces = {"application/json"})
    public ResultVo<Map<String, Object>> updateParamSetting(
            @ApiIgnore @RequestHeader Map<String, Object> headerMap,
            @Validated({ValidationUpdate.class}) @RequestBody ParamSettingReqDto paramSettingReqDto
    ){
        return ResultVo.ok(paramSettingService.updateParamSetting(headerMap,paramSettingReqDto));
    }

    /*
     * @Author zhangyong
     * @Description //(4) 通过id查询参数设置信息
     * @Date 2022/03/22 14:08
     * @Param
     * @return
     **/
    @ApiOperation("通过id查询参数设置信息")
    @ApiImplicitParam(name = "id", value = "参数设置id", example = "136985121358326", dataType = "Long", paramType = "form")
    @GetMapping("/getParamSettingById/{id}")
    public ResultVo<Map<String, Object>> getParamSettingById(
            @ApiIgnore @RequestHeader Map<String, Object> headerMap,
            @PathVariable Long id
    ){
        return ResultVo.ok(paramSettingService.getParamSettingById(headerMap,id));
    }

    /*
     * @Author zhangyong
     * @Description //(5) 查询所有的参数设置信息列表并分页(支持关键字查询)
     * @Date 2022/03/22 14:08
     * @Param
     * @return
     **/
     @ApiOperation("查询所有的参数设置信息列表并分页(支持关键字查询)")
    @PostMapping(value = "getParamSettingListPageVo", produces = { "application/json" })
    public ResultVo<PageVo<Map<String, Object>>> getParamSettingListPageVo(
            @ApiIgnore @RequestHeader Map<String, Object> headerMap,
            @Validated @RequestBody ParamSettingPageReqDto paramSettingPageReqDto
    ){
        return ResultVo.ok(paramSettingService.getParamSettingListPageVo(headerMap,paramSettingPageReqDto));
    }
}