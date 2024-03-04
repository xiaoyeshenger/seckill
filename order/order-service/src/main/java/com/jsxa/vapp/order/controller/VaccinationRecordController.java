package com.jsxa.vapp.order.controller;

import com.jsxa.vapp.common.bo.vo.ResultVo;
import com.jsxa.vapp.common.bo.vo.PageVo;
import com.jsxa.vapp.order.bo.dto.VaccinationRecordReqDto;
import com.jsxa.vapp.order.bo.dto.VaccinationRecordPageReqDto;
import com.jsxa.vapp.order.service.VaccinationRecordService;
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
 * @Description VaccinationRecordController类
 * @Date 2024/02/27 15:20
 * @Param
 * @return
 */
@RestController
@RequestMapping("/order/vaccinationRecord")
@Api(tags = "疫苗预约相关接口")
@Validated
@RequiredArgsConstructor
public class VaccinationRecordController {

    private final VaccinationRecordService vaccinationRecordService;


    /**
     * @Author zhangyong
     * @Description //(1) 添加疫苗预约信息
     * @Date 2024/02/27 15:20
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.INSERT)
    @ApiOperation("添加疫苗预约信息")
    @PostMapping(value = "addVaccinationRecord", produces = { "application/json" })
    public ResultVo<Map<String, Object>> addVaccinationRecord(
            @Validated @RequestBody VaccinationRecordReqDto vaccinationRecordReqDto
    ){
        return ResultVo.ok(vaccinationRecordService.addVaccinationRecord(vaccinationRecordReqDto));
    }

    /**
     * @Author zhangyong
     * @Description //(2) 通过id删除疫苗预约信息
     * @Date 2024/02/27 15:20
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.DELETE)
    @ApiOperation("通过id删除疫苗预约信息")
    @ApiImplicitParam(name = "id", value = "疫苗预约id", example = "1386532156978321", dataType = "Long", paramType = "form")
    @GetMapping("/deleteVaccinationRecordById/{id}")
    public ResultVo<Map<String, Object>> deleteVaccinationRecordById(
            @PathVariable Long id
    ){
         return ResultVo.ok(vaccinationRecordService.deleteVaccinationRecordById(id));
    }


    /**
     * @Author zhangyong
     * @Description //(4) 通过id查询疫苗预约信息
     * @Date 2024/02/27 15:20
     * @Param
     * @return
     */
    @ApiOperation("通过id查询疫苗预约信息")
    @ApiImplicitParam(name = "id", value = "疫苗预约id", example = "136985121358326", dataType = "Long", paramType = "form")
    @GetMapping("/getVaccinationRecordById/{id}")
    public ResultVo<Map<String, Object>> getVaccinationRecordById(
            @PathVariable Long id
    ){
        return ResultVo.ok(vaccinationRecordService.getVaccinationRecordById(id));
    }

    /**
     * @Author zhangyong
     * @Description //(5) 查询所有的疫苗预约信息列表并分页(支持关键字查询)
     * @Date 2024/02/27 15:20
     * @Param
     * @return
     */
    @ApiOperation("查询所有的疫苗预约信息列表并分页(支持关键字查询)")
    @PostMapping(value = "getVaccinationRecordListPageVo", produces = { "application/json" })
    public ResultVo<PageVo<Map<String, Object>>> getVaccinationRecordListPageVo(
            @Validated({ValidationGroup.ValidationPage.class}) @RequestBody VaccinationRecordPageReqDto vaccinationRecordPageReqDto
    ){
        return ResultVo.ok(vaccinationRecordService.getVaccinationRecordListPageVo(vaccinationRecordPageReqDto));
    }

    /**
     * @Author zhangyong
     * @Description //(6) 下载疫苗预约标准上传模板
     * @Date 2024/02/27 15:20
     * @Param
     * @return
     */
    @ApiOperation("下载疫苗预约标准上传模板")
    @GetMapping("/downloadTemplateExcel")
    public void downloadTemplateExcel(
        @ApiIgnore HttpServletResponse response
    ){
        vaccinationRecordService.downloadTemplateExcel(response);
    }

    /**
     * @Author zhangyong
     * @Description //(6) 通过excel导入疫苗预约
     * @Date 2024/02/27 15:20
     * @Param
     * @return
     */
    @ApiOperation("通过excel导入疫苗预约")
    @PostMapping("importByExcel")
    public  ResultVo<Map<String, Object>> importByExcel(
        @ApiIgnore MultipartHttpServletRequest request
    ){
        return ResultVo.ok(vaccinationRecordService.importByExcel(request));
    }

    /**
     * @Author zhangyong
     * @Description //(8) 导出疫苗预约到excel
     * @Date 2024/02/27 15:20
     * @Param
     * @return
     */
    @ApiOperation("导出疫苗预约到excel")
    @GetMapping("/exportToExcel")
    public void exportToExcel(
        @ApiIgnore HttpServletResponse response,
        @Validated @RequestBody VaccinationRecordPageReqDto vaccinationRecordPageReqDto
    ){
        vaccinationRecordService.exportToExcel(vaccinationRecordPageReqDto,response);
    }

}