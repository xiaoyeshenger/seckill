package com.jsxa.vapp.inventory.controller;

import com.jsxa.vapp.common.bo.vo.ResultVo;
import com.jsxa.vapp.common.bo.vo.PageVo;
import com.jsxa.vapp.inventory.bo.dto.VaccinationPersonReqDto;
import com.jsxa.vapp.inventory.bo.dto.VaccinationPersonPageReqDto;
import com.jsxa.vapp.inventory.service.VaccinationPersonService;
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
 * @Description VaccinationPersonController类
 * @Date 2024/02/02 15:06
 * @Param
 * @return
 */
@RestController
@RequestMapping("/inventory/vaccinationPerson")
@Api(tags = "接种人员相关接口")
@Validated
@RequiredArgsConstructor
public class VaccinationPersonController {

    private final VaccinationPersonService vaccinationPersonService;


    /**
     * @Author zhangyong
     * @Description //(1) 添加接种人员信息
     * @Date 2024/02/02 15:06
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.INSERT)
    @ApiOperation("添加接种人员信息")
    @PostMapping(value = "addVaccinationPerson", produces = { "application/json" })
    public ResultVo<Map<String, Object>> addVaccinationPerson(
            @Validated @RequestBody VaccinationPersonReqDto vaccinationPersonReqDto
    ){
        return ResultVo.ok(vaccinationPersonService.addVaccinationPerson(vaccinationPersonReqDto));
    }

    /**
     * @Author zhangyong
     * @Description //(2) 通过id删除接种人员信息
     * @Date 2024/02/02 15:06
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.DELETE)
    @ApiOperation("通过id删除接种人员信息")
    @ApiImplicitParam(name = "id", value = "接种人员id", example = "1386532156978321", dataType = "Long", paramType = "form")
    @GetMapping("/deleteVaccinationPersonById/{id}")
    public ResultVo<Map<String, Object>> deleteVaccinationPersonById(
            @PathVariable Long id
    ){
         return ResultVo.ok(vaccinationPersonService.deleteVaccinationPersonById(id));
    }

    /**
     * @Author zhangyong
     * @Description //(3) 更新接种人员信息
     * @Date 2024/02/02 15:06
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.UPDATE)
    @ApiOperation("更新接种人员信息")
    @PostMapping(value = "updateVaccinationPerson", produces = {"application/json"})
    public ResultVo<Map<String, Object>> updateVaccinationPerson(
            @Validated({ValidationUpdate.class}) @RequestBody VaccinationPersonReqDto vaccinationPersonReqDto
    ){
        return ResultVo.ok(vaccinationPersonService.updateVaccinationPerson(vaccinationPersonReqDto));
    }

    /**
     * @Author zhangyong
     * @Description //(4) 通过id查询接种人员信息
     * @Date 2024/02/02 15:06
     * @Param
     * @return
     */
    @ApiOperation("通过id查询接种人员信息")
    @ApiImplicitParam(name = "id", value = "接种人员id", example = "136985121358326", dataType = "Long", paramType = "form")
    @GetMapping("/getVaccinationPersonById/{id}")
    public ResultVo<Map<String, Object>> getVaccinationPersonById(
            @PathVariable Long id
    ){
        return ResultVo.ok(vaccinationPersonService.getVaccinationPersonById(id));
    }

    /**
     * @Author zhangyong
     * @Description //(5) 查询所有的接种人员信息列表并分页(支持关键字查询)
     * @Date 2024/02/02 15:06
     * @Param
     * @return
     */
    @ApiOperation("查询所有的接种人员信息列表并分页(支持关键字查询)")
    @PostMapping(value = "getVaccinationPersonListPageVo", produces = { "application/json" })
    public ResultVo<PageVo<Map<String, Object>>> getVaccinationPersonListPageVo(
            @Validated({ValidationGroup.ValidationPage.class}) @RequestBody VaccinationPersonPageReqDto vaccinationPersonPageReqDto
    ){
        return ResultVo.ok(vaccinationPersonService.getVaccinationPersonListPageVo(vaccinationPersonPageReqDto));
    }

    /**
     * @Author zhangyong
     * @Description //(6) 下载接种人员标准上传模板
     * @Date 2024/02/02 15:06
     * @Param
     * @return
     */
    @ApiOperation("下载接种人员标准上传模板")
    @GetMapping("/downloadTemplateExcel")
    public void downloadTemplateExcel(
        @ApiIgnore HttpServletResponse response
    ){
        vaccinationPersonService.downloadTemplateExcel(response);
    }

    /**
     * @Author zhangyong
     * @Description //(6) 通过excel导入接种人员
     * @Date 2024/02/02 15:06
     * @Param
     * @return
     */
    @ApiOperation("通过excel导入接种人员")
    @PostMapping("importByExcel")
    public  ResultVo<Map<String, Object>> importByExcel(
        @ApiIgnore MultipartHttpServletRequest request
    ){
        return ResultVo.ok(vaccinationPersonService.importByExcel(request));
    }

    /**
     * @Author zhangyong
     * @Description //(8) 导出接种人员到excel
     * @Date 2024/02/02 15:06
     * @Param
     * @return
     */
    @ApiOperation("导出接种人员到excel")
    @GetMapping("/exportToExcel")
    public void exportToExcel(
        @ApiIgnore HttpServletResponse response,
        @Validated @RequestBody VaccinationPersonPageReqDto vaccinationPersonPageReqDto
    ){
        vaccinationPersonService.exportToExcel(vaccinationPersonPageReqDto,response);
    }

}