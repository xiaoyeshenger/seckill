package com.jsxa.vapp.sys.controller;

import com.jsxa.vapp.common.annotation.Log;
import com.jsxa.vapp.common.bo.vo.PageVo;
import com.jsxa.vapp.common.bo.vo.ResultVo;
import com.jsxa.vapp.common.enums.BusinessType;
import com.jsxa.vapp.sys.bo.dto.DeptPageReqDto;
import com.jsxa.vapp.sys.bo.dto.DeptReqDto;
import com.jsxa.vapp.sys.service.DeptService;
import com.jsxa.vapp.common.valid.ValidationGroup.ValidationUpdate;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import springfox.documentation.annotations.ApiIgnore;
import org.springframework.validation.annotation.Validated;

import lombok.RequiredArgsConstructor;

import java.util.Map;



/*
 * @Author zhangyong
 * @Description DeptController类
 * @Date 2022/02/21 15:06
 * @Param
 * @return
 **/
@RestController
@RequestMapping("/sys/dept")
@Api(tags = "部门相关接口")
@Validated
@RequiredArgsConstructor
public class DeptController {

    private final DeptService deptService;


    /*
     * @Author zhangyong
     * @Description //(1) 添加部门信息
     * @Date 2022/02/21 15:06
     * @Param
     * @return
     **/
    @Log(businessType = BusinessType.INSERT)
    @ApiOperation("添加部门信息")
    @PostMapping(value = "addDept", produces = { "application/json" })
    public ResultVo<Map<String, Object>> addDept(
            @ApiIgnore @RequestHeader Map<String, Object> headerMap,
            @Validated @RequestBody DeptReqDto deptReqDto
    ){
        return ResultVo.ok(deptService.addDept(headerMap,deptReqDto));
    }

    /*
     * @Author zhangyong
     * @Description //(2) 通过id删除部门信息
     * @Date 2022/02/21 15:06
     * @Param
     * @return
     **/
    @Log(businessType = BusinessType.DELETE)
    @ApiOperation("通过id删除部门信息")
    @ApiImplicitParam(name = "id", value = "部门id", example = "133", dataType = "Long", paramType = "form")
    @GetMapping("/deleteDeptById/{id}")
    public ResultVo<Map<String, Object>> deleteDeptById(
            @ApiIgnore @RequestHeader Map<String, Object> headerMap,
            @PathVariable Long id
    ){
         return ResultVo.ok(deptService.deleteDeptById(headerMap,id));
    }

    /*
     * @Author zhangyong
     * @Description //(3) 更新部门信息
     * @Date 2022/02/21 15:06
     * @Param
     * @return
     **/
    @Log(businessType = BusinessType.UPDATE)
    @ApiOperation("更新部门信息")
    @PostMapping(value = "updateDept", produces = {"application/json"})
    public ResultVo<Map<String, Object>> updateDept(
            @ApiIgnore @RequestHeader Map<String, Object> headerMap,
            @Validated({ValidationUpdate.class}) @RequestBody DeptReqDto deptReqDto
    ){
        return ResultVo.ok(deptService.updateDept(headerMap,deptReqDto));
    }

    /*
     * @Author zhangyong
     * @Description //(4) 通过id查询部门信息
     * @Date 2022/02/21 15:06
     * @Param
     * @return
     **/
    @ApiOperation("通过id查询部门信息")
    @ApiImplicitParam(name = "id", value = "部门id", example = "133", dataType = "Long", paramType = "form")
    @GetMapping("/getDeptById/{id}")
    public ResultVo<Map<String, Object>> getDeptById(
            @ApiIgnore @RequestHeader Map<String, Object> headerMap,
            @PathVariable Long id
    ){
        return ResultVo.ok(deptService.getDeptById(headerMap,id));
    }

    /*
     * @Author zhangyong
     * @Description //(5) 查询所有的部门信息列表并分页(支持关键字查询)
     * @Date 2022/02/21 15:06
     * @Param
     * @return
     **/
    @ApiOperation("查询所有的部门信息列表并分页(支持关键字查询)")
    @PostMapping(value = "getDeptListPageVo", produces = { "application/json" })
    public ResultVo<PageVo<Map<String, Object>>> getDeptListPageVo(
            @ApiIgnore @RequestHeader Map<String, Object> headerMap,
            @Validated @RequestBody DeptPageReqDto deptPageReqDto
    ){
        return ResultVo.ok(deptService.getDeptListPageVo(headerMap,deptPageReqDto));
    }
}