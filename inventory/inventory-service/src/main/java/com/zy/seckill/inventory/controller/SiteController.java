package com.zy.seckill.inventory.controller;

import com.zy.seckill.common.bo.vo.ResultVo;
import com.zy.seckill.common.bo.vo.PageVo;
import com.zy.seckill.inventory.bo.dto.SiteReqDto;
import com.zy.seckill.inventory.bo.dto.SitePageReqDto;
import com.zy.seckill.inventory.service.SiteService;
import com.zy.seckill.common.enums.BusinessType;
import com.zy.seckill.common.valid.ValidationGroup;
import com.zy.seckill.common.valid.ValidationGroup.ValidationUpdate;
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
 * @Description SiteController类
 * @Date xxxx/02/27 14:32
 * @Param
 * @return
 */
@RestController
@RequestMapping("/inventory/Site")
@Api(tags = "商铺相关接口")
@Validated
@RequiredArgsConstructor
public class SiteController {

    private final SiteService siteService;


    /**
     * @Author zhangyong
     * @Description //(1) 添加商铺信息
     * @Date xxxx/02/27 14:32
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.INSERT)
    @ApiOperation("添加商铺信息")
    @PostMapping(value = "addSite", produces = { "application/json" })
    public ResultVo<Map<String, Object>> addSite(
            @Validated @RequestBody SiteReqDto siteReqDto
    ){
        return ResultVo.ok(siteService.addSite(siteReqDto));
    }

    /**
     * @Author zhangyong
     * @Description //(2) 通过id删除商铺信息
     * @Date xxxx/02/27 14:32
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.DELETE)
    @ApiOperation("通过id删除商铺信息")
    @ApiImplicitParam(name = "id", value = "商铺id", example = "1386532156978321", dataType = "Long", paramType = "form")
    @GetMapping("/deleteSiteById/{id}")
    public ResultVo<Map<String, Object>> deleteSiteById(
            @PathVariable Long id
    ){
         return ResultVo.ok(siteService.deleteSiteById(id));
    }

    /**
     * @Author zhangyong
     * @Description //(3) 更新商铺信息
     * @Date xxxx/02/27 14:32
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.UPDATE)
    @ApiOperation("更新商铺信息")
    @PostMapping(value = "updateSite", produces = {"application/json"})
    public ResultVo<Map<String, Object>> updateSite(
            @Validated({ValidationUpdate.class}) @RequestBody SiteReqDto siteReqDto
    ){
        return ResultVo.ok(siteService.updateSite(siteReqDto));
    }

    /**
     * @Author zhangyong
     * @Description //(4) 通过id查询商铺信息
     * @Date xxxx/02/27 14:32
     * @Param
     * @return
     */
    @ApiOperation("通过id查询商铺信息")
    @ApiImplicitParam(name = "id", value = "商铺id", example = "136985121358326", dataType = "Long", paramType = "form")
    @GetMapping("/getSiteById/{id}")
    public ResultVo<Map<String, Object>> getSiteById(
            @PathVariable Long id
    ){
        return ResultVo.ok(siteService.getSiteById(id));
    }

    /**
     * @Author zhangyong
     * @Description //(5) 查询所有的商铺信息列表并分页(支持关键字查询)
     * @Date xxxx/02/27 14:32
     * @Param
     * @return
     */
    @ApiOperation("查询所有的商铺信息列表并分页(支持关键字查询)")
    @PostMapping(value = "getSiteListPageVo", produces = { "application/json" })
    public ResultVo<PageVo<Map<String, Object>>> getSiteListPageVo(
            @Validated({ValidationGroup.ValidationPage.class}) @RequestBody SitePageReqDto sitePageReqDto
    ){
        return ResultVo.ok(siteService.getSiteListPageVo(sitePageReqDto));
    }

    /**
     * @Author zhangyong
     * @Description //(6) 下载商铺标准上传模板
     * @Date xxxx/02/27 14:32
     * @Param
     * @return
     */
    @ApiOperation("下载商铺标准上传模板")
    @GetMapping("/downloadTemplateExcel")
    public void downloadTemplateExcel(
        @ApiIgnore HttpServletResponse response
    ){
        siteService.downloadTemplateExcel(response);
    }

    /**
     * @Author zhangyong
     * @Description //(6) 通过excel导入商铺
     * @Date xxxx/02/27 14:32
     * @Param
     * @return
     */
    @ApiOperation("通过excel导入商铺")
    @PostMapping("importByExcel")
    public  ResultVo<Map<String, Object>> importByExcel(
        @ApiIgnore MultipartHttpServletRequest request
    ){
        return ResultVo.ok(siteService.importByExcel(request));
    }

    /**
     * @Author zhangyong
     * @Description //(8) 导出商铺到excel
     * @Date xxxx/02/27 14:32
     * @Param
     * @return
     */
    @ApiOperation("导出商铺到excel")
    @GetMapping("/exportToExcel")
    public void exportToExcel(
        @ApiIgnore HttpServletResponse response,
        @Validated @RequestBody SitePageReqDto sitePageReqDto
    ){
        siteService.exportToExcel(sitePageReqDto,response);
    }

}