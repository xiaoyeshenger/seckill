package com.jsxa.vapp.inventory.controller;

import com.jsxa.vapp.common.bo.vo.ResultVo;
import com.jsxa.vapp.common.bo.vo.PageVo;
import com.jsxa.vapp.inventory.bo.dto.VaccineReqDto;
import com.jsxa.vapp.inventory.bo.dto.VaccinePageReqDto;
import com.jsxa.vapp.inventory.service.VaccineService;
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
 * @Description VaccineController类
 * @Date 2024/02/27 14:20
 * @Param
 * @return
 */
@RestController
@RequestMapping("/inventory/vaccine")
@Api(tags = "疫苗相关接口")
@Validated
@RequiredArgsConstructor
public class VaccineController {

    private final VaccineService vaccineService;


    /**
     * @Author zhangyong
     * @Description //(1) 添加疫苗信息
     * @Date 2024/02/27 14:20
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.INSERT)
    @ApiOperation("添加疫苗信息")
    @PostMapping(value = "addVaccine", produces = { "application/json" })
    public ResultVo<Map<String, Object>> addVaccine(
            @Validated @RequestBody VaccineReqDto vaccineReqDto
    ){
        return ResultVo.ok(vaccineService.addVaccine(vaccineReqDto));
    }

    /**
     * @Author zhangyong
     * @Description //(2) 通过id删除疫苗信息
     * @Date 2024/02/27 14:20
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.DELETE)
    @ApiOperation("通过id删除疫苗信息")
    @ApiImplicitParam(name = "id", value = "疫苗id", example = "1386532156978321", dataType = "Long", paramType = "form")
    @GetMapping("/deleteVaccineById/{id}")
    public ResultVo<Map<String, Object>> deleteVaccineById(
            @PathVariable Long id
    ){
         return ResultVo.ok(vaccineService.deleteVaccineById(id));
    }

    /**
     * @Author zhangyong
     * @Description //(3) 更新疫苗信息
     * @Date 2024/02/27 14:20
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.UPDATE)
    @ApiOperation("更新疫苗信息")
    @PostMapping(value = "updateVaccine", produces = {"application/json"})
    public ResultVo<Map<String, Object>> updateVaccine(
            @Validated({ValidationUpdate.class}) @RequestBody VaccineReqDto vaccineReqDto
    ){
        return ResultVo.ok(vaccineService.updateVaccine(vaccineReqDto));
    }

    /**
     * @Author zhangyong
     * @Description //(4) 通过id查询疫苗信息
     * @Date 2024/02/27 14:20
     * @Param
     * @return
     */
    @ApiOperation("通过id查询疫苗信息")
    @ApiImplicitParam(name = "id", value = "疫苗id", example = "136985121358326", dataType = "Long", paramType = "form")
    @GetMapping("/getVaccineById/{id}")
    public ResultVo<Map<String, Object>> getVaccineById(
            @PathVariable Long id
    ){
        return ResultVo.ok(vaccineService.getVaccineById(id));
    }

    /**
     * @Author zhangyong
     * @Description //(5) 查询所有的疫苗信息列表并分页(支持关键字查询)
     * @Date 2024/02/27 14:20
     * @Param
     * @return
     */
    @ApiOperation("查询所有的疫苗信息列表并分页(支持关键字查询)")
    @PostMapping(value = "getVaccineListPageVo", produces = { "application/json" })
    public ResultVo<PageVo<Map<String, Object>>> getVaccineListPageVo(
            @Validated({ValidationGroup.ValidationPage.class}) @RequestBody VaccinePageReqDto vaccinePageReqDto
    ){
        return ResultVo.ok(vaccineService.getVaccineListPageVo(vaccinePageReqDto));
    }

    /**
     * @Author zhangyong
     * @Description //(6) 下载疫苗标准上传模板
     * @Date 2024/02/27 14:20
     * @Param
     * @return
     */
    @ApiOperation("下载疫苗标准上传模板")
    @GetMapping("/downloadTemplateExcel")
    public void downloadTemplateExcel(
        @ApiIgnore HttpServletResponse response
    ){
        vaccineService.downloadTemplateExcel(response);
    }

    /**
     * @Author zhangyong
     * @Description //(6) 通过excel导入疫苗
     * @Date 2024/02/27 14:20
     * @Param
     * @return
     */
    @ApiOperation("通过excel导入疫苗")
    @PostMapping("importByExcel")
    public  ResultVo<Map<String, Object>> importByExcel(
        @ApiIgnore MultipartHttpServletRequest request
    ){
        return ResultVo.ok(vaccineService.importByExcel(request));
    }

    /**
     * @Author zhangyong
     * @Description //(8) 导出疫苗到excel
     * @Date 2024/02/27 14:20
     * @Param
     * @return
     */
    @ApiOperation("导出疫苗到excel")
    @GetMapping("/exportToExcel")
    public void exportToExcel(
        @ApiIgnore HttpServletResponse response,
        @Validated @RequestBody VaccinePageReqDto vaccinePageReqDto
    ){
        vaccineService.exportToExcel(vaccinePageReqDto,response);
    }

}