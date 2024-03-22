package com.jsxa.vapp.inventory.controller;

import com.jsxa.vapp.common.bo.vo.ResultVo;
import com.jsxa.vapp.common.bo.vo.PageVo;
import com.jsxa.vapp.inventory.bo.dto.VaccinationSiteReqDto;
import com.jsxa.vapp.inventory.bo.dto.VaccinationSitePageReqDto;
import com.jsxa.vapp.inventory.service.VaccinationSiteService;
import com.jsxa.vapp.common.enums.BusinessType;
import com.jsxa.vapp.common.valid.ValidationGroup;
import com.jsxa.vapp.common.valid.ValidationGroup.ValidationUpdate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import io.swagger.annotations.*;
import springfox.documentation.annotations.ApiIgnore;
import org.springframework.validation.annotation.Validated;
import com.jsxa.vapp.common.annotation.Log;
import lombok.RequiredArgsConstructor;

import java.util.Map;


/**
 * @Author zhangyong
 * @Description VaccinationSiteController类
 * @Date 2021/02/27 14:32
 * @Param
 * @return
 */
@RestController
@RequestMapping("/inventory/vaccinationSite")
@Api(tags = "接种点相关接口")
@Validated
@RequiredArgsConstructor
public class VaccinationSiteController {

    private final VaccinationSiteService vaccinationSiteService;


    /**
     * @Author zhangyong
     * @Description //(1) 添加接种点信息
     * @Date 2021/02/27 14:32
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.INSERT)
    @ApiOperation("添加接种点信息")
    @PostMapping(value = "addVaccinationSite", produces = { "application/json" })
    public ResultVo<Map<String, Object>> addVaccinationSite(
            @Validated @RequestBody VaccinationSiteReqDto vaccinationSiteReqDto
    ){
        return ResultVo.ok(vaccinationSiteService.addVaccinationSite(vaccinationSiteReqDto));
    }

    /**
     * @Author zhangyong
     * @Description //(2) 通过id删除接种点信息
     * @Date 2021/02/27 14:32
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.DELETE)
    @ApiOperation("通过id删除接种点信息")
    @ApiImplicitParam(name = "id", value = "接种点id", example = "1386532156978321", dataType = "Long", paramType = "form")
    @GetMapping("/deleteVaccinationSiteById/{id}")
    public ResultVo<Map<String, Object>> deleteVaccinationSiteById(
            @PathVariable Long id
    ){
         return ResultVo.ok(vaccinationSiteService.deleteVaccinationSiteById(id));
    }

    /**
     * @Author zhangyong
     * @Description //(3) 更新接种点信息
     * @Date 2021/02/27 14:32
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.UPDATE)
    @ApiOperation("更新接种点信息")
    @PostMapping(value = "updateVaccinationSite", produces = {"application/json"})
    public ResultVo<Map<String, Object>> updateVaccinationSite(
            @Validated({ValidationUpdate.class}) @RequestBody VaccinationSiteReqDto vaccinationSiteReqDto
    ){
        return ResultVo.ok(vaccinationSiteService.updateVaccinationSite(vaccinationSiteReqDto));
    }

    /**
     * @Author zhangyong
     * @Description //(4) 通过id查询接种点信息
     * @Date 2021/02/27 14:32
     * @Param
     * @return
     */
    @ApiOperation("通过id查询接种点信息")
    @ApiImplicitParam(name = "id", value = "接种点id", example = "136985121358326", dataType = "Long", paramType = "form")
    @GetMapping("/getVaccinationSiteById/{id}")
    public ResultVo<Map<String, Object>> getVaccinationSiteById(
            @PathVariable Long id
    ){
        return ResultVo.ok(vaccinationSiteService.getVaccinationSiteById(id));
    }

    /**
     * @Author zhangyong
     * @Description //(5) 查询所有的接种点信息列表并分页(支持关键字查询)
     * @Date 2021/02/27 14:32
     * @Param
     * @return
     */
    @ApiOperation("查询所有的接种点信息列表并分页(支持关键字查询)")
    @PostMapping(value = "getVaccinationSiteListPageVo", produces = { "application/json" })
    public ResultVo<PageVo<Map<String, Object>>> getVaccinationSiteListPageVo(
            @Validated({ValidationGroup.ValidationPage.class}) @RequestBody VaccinationSitePageReqDto vaccinationSitePageReqDto
    ){
        return ResultVo.ok(vaccinationSiteService.getVaccinationSiteListPageVo(vaccinationSitePageReqDto));
    }

    /**
     * @Author zhangyong
     * @Description //(6) 下载接种点标准上传模板
     * @Date 2021/02/27 14:32
     * @Param
     * @return
     */
    @ApiOperation("下载接种点标准上传模板")
    @GetMapping("/downloadTemplateExcel")
    public void downloadTemplateExcel(
        @ApiIgnore HttpServletResponse response
    ){
        vaccinationSiteService.downloadTemplateExcel(response);
    }

    /**
     * @Author zhangyong
     * @Description //(6) 通过excel导入接种点
     * @Date 2021/02/27 14:32
     * @Param
     * @return
     */
    @ApiOperation("通过excel导入接种点")
    @PostMapping("importByExcel")
    public  ResultVo<Map<String, Object>> importByExcel(
        @ApiIgnore MultipartHttpServletRequest request
    ){
        return ResultVo.ok(vaccinationSiteService.importByExcel(request));
    }

    /**
     * @Author zhangyong
     * @Description //(8) 导出接种点到excel
     * @Date 2021/02/27 14:32
     * @Param
     * @return
     */
    @ApiOperation("导出接种点到excel")
    @GetMapping("/exportToExcel")
    public void exportToExcel(
        @ApiIgnore HttpServletResponse response,
        @Validated @RequestBody VaccinationSitePageReqDto vaccinationSitePageReqDto
    ){
        vaccinationSiteService.exportToExcel(vaccinationSitePageReqDto,response);
    }

}