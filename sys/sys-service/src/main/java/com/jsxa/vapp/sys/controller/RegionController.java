package com.jsxa.vapp.sys.controller;

import com.jsxa.vapp.common.annotation.Log;
import com.jsxa.vapp.common.bo.vo.PageVo;
import com.jsxa.vapp.common.bo.vo.ResultVo;
import com.jsxa.vapp.common.cache.RegionCache;
import com.jsxa.vapp.common.enums.BusinessType;
import com.jsxa.vapp.common.valid.ValidationGroup;
import com.jsxa.vapp.sys.bo.dto.RegionPageReqDto;
import com.jsxa.vapp.sys.bo.dto.RegionReqDto;
import com.jsxa.vapp.sys.service.RegionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.Map;


/*
 * @Author zhangyong
 * @Description RegionController类
 * @Date 2021/09/16 16:43
 * @Param
 * @return
 **/
@RestController
@RequestMapping("/sys/region")
@Api(tags = "区域")
@Validated
@RequiredArgsConstructor
public class RegionController {

    private final RegionService regionService;

    private final RegionCache regionCache;


    @ApiIgnore
    @ApiOperation("同步区域信息到redis")
    @GetMapping("/reloadRegion")
    @Log(businessType = BusinessType.RELOAD)
    public ResultVo<Map<String, Object>> reloadRegion(){
        regionCache.reloadRegion();
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("msg","同步区域信息到redis");
        return ResultVo.ok(resultMap);
    }

    /*
     * @Author zhangyong
     * @Description //(2) 获取所有的数据字典
     * @Date 下午 2:54 2019/5/31 0031
     * @Param
     * @return
     **/
    @ApiOperation("获取所有的区域信息")
    @GetMapping("/getRegion")
    public ResultVo<Map<String, Object>> getRegion(){
        return ResultVo.ok(regionService.getRegionVo());
    }

    /*
     * @Author zhangyong
     * @Description //(1) 添加区域信息
     * @Date 2021/09/16 16:43
     * @Param
     * @return
     **/
    @Log(businessType = BusinessType.INSERT)
    @ApiOperation("添加区域信息")
    @PostMapping(value = "addRegion", produces = { "application/json" })
    public ResultVo<Map<String, Object>> addRegion(
            @Validated @RequestBody RegionReqDto regionReqDto
    ){
        return ResultVo.ok(regionService.addRegion(regionReqDto));
    }

    /*
     * @Author zhangyong
     * @Description //(2) 通过id删除区域信息
     * @Date 2021/09/16 16:43
     * @Param
     * @return
     **/
    @Log(businessType = BusinessType.DELETE)
    @ApiOperation("通过id删除区域信息")
    @ApiImplicitParam(name = "id", value = "区域id", example = "133", dataType = "Long", paramType = "form")
    @GetMapping("/deleteRegionById/{id}")
    public ResultVo<Map<String, Object>> deleteRegionById(
            @PathVariable Long id
    ){
        return ResultVo.ok(regionService.deleteRegionById(id));
    }

    /*
     * @Author zhangyong
     * @Description //(3) 更新区域信息
     * @Date 2021/09/16 16:43
     * @Param
     * @return
     **/
    @Log(businessType = BusinessType.UPDATE)
    @ApiOperation("更新区域信息")
    @PostMapping(value = "updateRegion", produces = {"application/json"})
    public ResultVo<Map<String, Object>> updateRegion(
            @Validated({ValidationGroup.ValidationUpdate.class}) @RequestBody RegionReqDto regionReqDto
    ){
        return ResultVo.ok(regionService.updateRegion(regionReqDto));
    }

    /*
     * @Author zhangyong
     * @Description //(4) 通过id查询区域信息
     * @Date 2021/09/16 16:43
     * @Param
     * @return
     **/
    @ApiOperation("通过id查询区域信息")
    @ApiImplicitParam(name = "id", value = "区域id", example = "133", dataType = "Long", paramType = "form")
    @GetMapping("/getRegionById/{id}")
    public ResultVo<Map<String, Object>> getRegionById(
            @PathVariable Long id
    ){
        return ResultVo.ok(regionService.getRegionById(id));
    }

    /*
     * @Author zhangyong
     * @Description //(5) 查询所有的区域信息列表并分页(支持关键字查询)
     * @Date 2021/09/16 16:43
     * @Param
     * @return
     **/
    @ApiOperation("查询所有的区域信息列表并分页(支持关键字查询)")
    @PostMapping(value = "getRegionListPageVo", produces = { "application/json" })
    public ResultVo<PageVo<Map<String, Object>>> getRegionListPageVo(
            @Validated @RequestBody RegionPageReqDto regionPageReqDto
    ){
        return ResultVo.ok(regionService.getRegionListPageVo(regionPageReqDto));
    }


    /*
     * @Author zhangyong
     * @Description //(4) 通过父级地址码(parentCode)查询所有的子级地址列表
     * @Date 2021/09/16 16:43
     * @Param
     * @return
     **/
    @ApiOperation("通过父级地址码(parentCode)查询所有的子级地址列表(包括子级的子级)")
    @ApiImplicitParam(name = "parentCode", value = "父级地址码", example = "510113", dataType = "String", paramType = "form")
    @GetMapping("/getAllChildListByParentCode/{parentCode}")
    public ResultVo<Map<String, Object>> getAllChildListByParentCode(
            @PathVariable String parentCode
    ){
        return ResultVo.ok(regionService.getAllChildListByParentCode(parentCode));
    }


    @ApiOperation("通过父级地址码(parentCode)查询所有的子级委办列表(包括子级的子级)")
    @ApiImplicitParam(name = "parentCode", value = "父级地址码", example = "510113", dataType = "String", paramType = "form")
    @GetMapping("/getAssignChildListByParentCode/{parentCode}")
    public ResultVo<Map<String, Object>> getAssignChildListByParentCode(
            @PathVariable String parentCode
    ){
        return ResultVo.ok(regionService.getAssignChildListByParentCode(parentCode));
    }


    @Log(businessType = BusinessType.UPDATE)
    @ApiOperation("通过父级地址码(parentCode)查询子级地址列表(所有子级)")
    @ApiImplicitParam(name = "parentCode", value = "父级地址码", example = "510113", dataType = "String", paramType = "form")
    @GetMapping("/getChildListByParentCode/{parentCode}")
    public ResultVo<Map<String, Object>> getChildListByParentCode(
            @PathVariable String parentCode
    ){
        return ResultVo.ok(regionService.getChildListByParentCode(parentCode));
    }



    /*
     * @Author zhangyong
     * @Description //(4) 通过子级地址码(childCode)查询所有的父级级地址列表
     * @Date 2021/09/16 16:43
     * @Param
     * @return
     **/
    @ApiOperation("通过子级地址码(childCode)查询所有的父级地址列表")
    @ApiImplicitParam(name = "childCode", value = "父级地址码", example = "510113", dataType = "String", paramType = "form")
    @GetMapping("/getParentListByChildCode/{childCode}")
    public ResultVo<Map<String, Object>> getParentListByChildCode(
            @PathVariable String childCode
    ){
        return ResultVo.ok(regionService.getParentListByChildCode(childCode));
    }



    /*
     * @Author zhangyong
     * @Description //(4) 通过父级地址码(parentCode)查询所有的区市县地址列表
     * @Date 2021/09/16 16:43
     * @Param
     * @return
     **/
    @ApiOperation("通过父级地址码(parentCode)查询所有的区市县地址列表")
    @ApiImplicitParam(name = "parentCode", value = "父级地址码", example = "510113", dataType = "String", paramType = "form")
    @GetMapping("/getCountyListByParentCode/{parentCode}")
    public ResultVo<Map<String, Object>> getCountyListByParentCode(
            @PathVariable String parentCode
    ){
        return ResultVo.ok(regionService.getCountyListByParentCode(parentCode));
    }
}