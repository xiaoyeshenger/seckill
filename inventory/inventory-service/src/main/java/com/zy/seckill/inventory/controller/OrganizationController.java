package com.zy.seckill.inventory.controller;

import com.zy.seckill.common.bo.vo.ResultVo;
import com.zy.seckill.common.bo.vo.PageVo;
import com.zy.seckill.inventory.bo.dto.OrganizationReqDto;
import com.zy.seckill.inventory.bo.dto.OrganizationPageReqDto;
import com.zy.seckill.inventory.service.OrganizationService;
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
 * @Description OrganizationController类
 * @Date 2024/02/02 15:01
 * @Param
 * @return
 */
@RestController
@RequestMapping("/inventory/organization")
@Api(tags = "商家相关接口")
@Validated
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;


    /**
     * @Author zhangyong
     * @Description //(1) 添加商家信息
     * @Date 2024/02/02 15:01
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.INSERT)
    @ApiOperation("添加商家信息")
    @PostMapping(value = "addOrganization", produces = { "application/json" })
    public ResultVo<Map<String, Object>> addOrganization(
            @Validated @RequestBody OrganizationReqDto organizationReqDto
    ){
        return ResultVo.ok(organizationService.addOrganization(organizationReqDto));
    }

    /**
     * @Author zhangyong
     * @Description //(2) 通过id删除商家信息
     * @Date 2024/02/02 15:01
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.DELETE)
    @ApiOperation("通过id删除商家信息")
    @ApiImplicitParam(name = "id", value = "商家id", example = "1386532156978321", dataType = "Long", paramType = "form")
    @GetMapping("/deleteOrganizationById/{id}")
    public ResultVo<Map<String, Object>> deleteOrganizationById(
            @PathVariable Long id
    ){
         return ResultVo.ok(organizationService.deleteOrganizationById(id));
    }

    /**
     * @Author zhangyong
     * @Description //(3) 更新商家信息
     * @Date 2024/02/02 15:01
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.UPDATE)
    @ApiOperation("更新商家信息")
    @PostMapping(value = "updateOrganization", produces = {"application/json"})
    public ResultVo<Map<String, Object>> updateOrganization(
            @Validated({ValidationUpdate.class}) @RequestBody OrganizationReqDto organizationReqDto
    ){
        return ResultVo.ok(organizationService.updateOrganization(organizationReqDto));
    }

    /**
     * @Author zhangyong
     * @Description //(4) 通过id查询商家信息
     * @Date 2024/02/02 15:01
     * @Param
     * @return
     */
    @ApiOperation("通过id查询商家信息")
    @ApiImplicitParam(name = "id", value = "商家id", example = "136985121358326", dataType = "Long", paramType = "form")
    @GetMapping("/getOrganizationById/{id}")
    public ResultVo<Map<String, Object>> getOrganizationById(
            @PathVariable Long id
    ){
        return ResultVo.ok(organizationService.getOrganizationById(id));
    }

    /**
     * @Author zhangyong
     * @Description //(5) 查询所有的商家信息列表并分页(支持关键字查询)
     * @Date 2024/02/02 15:01
     * @Param
     * @return
     */
    @ApiOperation("查询所有的商家信息列表并分页(支持关键字查询)")
    @PostMapping(value = "getOrganizationListPageVo", produces = { "application/json" })
    public ResultVo<PageVo<Map<String, Object>>> getOrganizationListPageVo(
            @Validated({ValidationGroup.ValidationPage.class}) @RequestBody OrganizationPageReqDto organizationPageReqDto
    ){
        return ResultVo.ok(organizationService.getOrganizationListPageVo(organizationPageReqDto));
    }

    /**
     * @Author zhangyong
     * @Description //(6) 下载商家标准上传模板
     * @Date 2024/02/02 15:01
     * @Param
     * @return
     */
    @ApiOperation("下载商家标准上传模板")
    @GetMapping("/downloadTemplateExcel")
    public void downloadTemplateExcel(
        @ApiIgnore HttpServletResponse response
    ){
        organizationService.downloadTemplateExcel(response);
    }

    /**
     * @Author zhangyong
     * @Description //(6) 通过excel导入商家
     * @Date 2024/02/02 15:01
     * @Param
     * @return
     */
    @ApiOperation("通过excel导入商家")
    @PostMapping("importByExcel")
    public  ResultVo<Map<String, Object>> importByExcel(
        @ApiIgnore MultipartHttpServletRequest request
    ){
        return ResultVo.ok(organizationService.importByExcel(request));
    }

    /**
     * @Author zhangyong
     * @Description //(8) 导出商家到excel
     * @Date 2024/02/02 15:01
     * @Param
     * @return
     */
    @ApiOperation("导出商家到excel")
    @GetMapping("/exportToExcel")
    public void exportToExcel(
        @ApiIgnore HttpServletResponse response,
        @Validated @RequestBody OrganizationPageReqDto organizationPageReqDto
    ){
        organizationService.exportToExcel(organizationPageReqDto,response);
    }

}