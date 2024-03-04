package com.jsxa.vapp.sys.controller;

import com.jsxa.vapp.common.bo.vo.ResultVo;
import com.jsxa.vapp.common.bo.vo.PageVo;
import com.jsxa.vapp.common.valid.ValidationGroup.ValidationUpdate;
import com.jsxa.vapp.sys.bo.dto.PageTemplateReqDto;
import com.jsxa.vapp.sys.bo.dto.PageTemplatePageReqDto;
import com.jsxa.vapp.sys.service.PageTemplateService;
import com.jsxa.vapp.common.enums.BusinessType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import io.swagger.annotations.*;
import springfox.documentation.annotations.ApiIgnore;
import org.springframework.validation.annotation.Validated;
import com.jsxa.vapp.common.annotation.Log;
import lombok.RequiredArgsConstructor;

import java.util.Map;



/*
 * @Author zhangyong
 * @Description PageTemplateController类
 * @Date 2022/04/26 11:24
 * @Param
 * @return
 **/
@RestController
@RequestMapping("/sys/pageTemplate")
@Api(tags = "页面模板相关接口")
@Validated
@RequiredArgsConstructor
public class PageTemplateController {

    private final PageTemplateService pageTemplateService;


    /*
     * @Author zhangyong
     * @Description //(1) 添加页面模板信息
     * @Date 2022/04/26 11:24
     * @Param
     * @return
     **/
    @Log(businessType = BusinessType.INSERT)
    @ApiOperation("添加页面模板信息")
    @PostMapping(value = "addPageTemplate", produces = { "application/json" })
    public ResultVo<Map<String, Object>> addPageTemplate(
            @Validated PageTemplateReqDto pageTemplateReqDto,
            @ApiIgnore MultipartHttpServletRequest request
    ){
        return ResultVo.ok(pageTemplateService.addPageTemplate(pageTemplateReqDto,request));
    }

    /*
     * @Author zhangyong
     * @Description //(2) 通过id删除页面模板信息
     * @Date 2022/04/26 11:24
     * @Param
     * @return
     **/
    @Log(businessType = BusinessType.DELETE)
    @ApiOperation("通过id删除页面模板信息")
    @ApiImplicitParam(name = "id", value = "页面模板id", example = "133", dataType = "Long", paramType = "form")
    @GetMapping("/deletePageTemplateById/{id}")
    public ResultVo<Map<String, Object>> deletePageTemplateById(
            @PathVariable Long id
    ){
         return ResultVo.ok(pageTemplateService.deletePageTemplateById(id));
    }

    /*
     * @Author zhangyong
     * @Description //(3) 更新页面模板信息
     * @Date 2022/04/26 11:24
     * @Param
     * @return
     **/
    @Log(businessType = BusinessType.UPDATE)
    @ApiOperation("更新页面模板信息")
    @PostMapping(value = "updatePageTemplate", produces = {"application/json"})
    public ResultVo<Map<String, Object>> updatePageTemplate(
            @Validated({ValidationUpdate.class}) PageTemplateReqDto pageTemplateReqDto,
            @ApiIgnore MultipartHttpServletRequest request
    ){
        return ResultVo.ok(pageTemplateService.updatePageTemplate(pageTemplateReqDto,request));
    }

    /*
     * @Author zhangyong
     * @Description //(4) 通过id查询页面模板信息
     * @Date 2022/04/26 11:24
     * @Param
     * @return
     **/
    @ApiOperation("通过id查询页面模板信息")
    @ApiImplicitParam(name = "id", value = "页面模板id", example = "133", dataType = "Long", paramType = "form")
    @GetMapping("/getPageTemplateById/{id}")
    public ResultVo<Map<String, Object>> getPageTemplateById(
            @PathVariable Long id
    ){
        return ResultVo.ok(pageTemplateService.getPageTemplateById(id));
    }

    /*
     * @Author zhangyong
     * @Description //(5) 查询所有的页面模板信息列表并分页(支持关键字查询)
     * @Date 2022/04/26 11:24
     * @Param
     * @return
     **/
    @ApiOperation("查询所有的页面模板信息列表并分页(支持关键字查询)")
    @PostMapping(value = "getPageTemplateListPageVo", produces = { "application/json" })
    public ResultVo<PageVo<Map<String, Object>>> getPageTemplateListPageVo(
            @Validated @RequestBody PageTemplatePageReqDto pageTemplatePageReqDto
    ){
        return ResultVo.ok(pageTemplateService.getPageTemplateListPageVo(pageTemplatePageReqDto));
    }

    /*
     * @Author zhangyong
     * @Description //(5) 通过id启用页面模板
     * @Date 2022/04/26 11:24
     * @Param
     * @return
     **/
    @ApiOperation("通过id启用页面模板")
    @ApiImplicitParam(name = "id", value = "页面模板id", example = "133", dataType = "Long", paramType = "form")
    @GetMapping("/enablePageTemplateById/{id}")
    public ResultVo<Map<String, Object>> enablePageTemplateById(
            @PathVariable Long id
    ){
        return ResultVo.ok(pageTemplateService.enablePageTemplateById(id));
    }

    /*
     * @Author zhangyong
     * @Description //(4) 通过parkId查询页面模板信息
     * @Date 2022/04/26 11:24
     * @Param
     * @return
     **/
    @ApiOperation("通过parkId查询页面模板信息")
    @ApiImplicitParam(name = "parkId", value = "园区ID", example = "133", dataType = "Long", paramType = "form")
    @GetMapping("/queryPageTemplateByParkId/{parkId}")
    public ResultVo<Map<String, Object>> queryPageTemplateByParkId(
            @PathVariable Long parkId
    ){
        return ResultVo.ok(pageTemplateService.queryPageTemplateByParkId(parkId));
    }

}