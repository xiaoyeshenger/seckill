package com.jsxa.vapp.inventory.controller;

import com.jsxa.vapp.common.bo.vo.ResultVo;
import com.jsxa.vapp.common.bo.vo.PageVo;
import com.jsxa.vapp.inventory.bo.dto.UpdateVaccineReleaseStatusReqDto;
import com.jsxa.vapp.inventory.bo.dto.VaccineReleaseReqDto;
import com.jsxa.vapp.inventory.bo.dto.VaccineReleasePageReqDto;
import com.jsxa.vapp.inventory.service.VaccineReleaseService;
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
 * @Description VaccineReleaseController类
 * @Date 2024/02/27 15:03
 * @Param
 * @return
 */
@RestController
@RequestMapping("/inventory/vaccineRelease")
@Api(tags = "疫苗发放相关接口")
@Validated
@RequiredArgsConstructor
public class VaccineReleaseController {

    private final VaccineReleaseService vaccineReleaseService;


    /**
     * @Author zhangyong
     * @Description //(1) 添加疫苗发放信息
     * @Date 2024/02/27 15:03
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.INSERT)
    @ApiOperation("添加疫苗发放信息")
    @PostMapping(value = "addVaccineRelease", produces = { "application/json" })
    public ResultVo<Map<String, Object>> addVaccineRelease(
            @Validated @RequestBody VaccineReleaseReqDto vaccineReleaseReqDto
    ){
        return ResultVo.ok(vaccineReleaseService.addVaccineRelease(vaccineReleaseReqDto));
    }

    /**
     * @Author zhangyong
     * @Description //(2) 通过id删除疫苗发放信息
     * @Date 2024/02/27 15:03
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.DELETE)
    @ApiOperation("通过id删除疫苗发放信息")
    @ApiImplicitParam(name = "id", value = "疫苗发放id", example = "1386532156978321", dataType = "Long", paramType = "form")
    @GetMapping("/deleteVaccineReleaseById/{id}")
    public ResultVo<Map<String, Object>> deleteVaccineReleaseById(
            @PathVariable Long id
    ){
         return ResultVo.ok(vaccineReleaseService.deleteVaccineReleaseById(id));
    }

    /**
     * @Author zhangyong
     * @Description //(3) 更新疫苗发放信息
     * @Date 2024/02/27 15:03
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.UPDATE)
    @ApiOperation("更新疫苗发放信息")
    @PostMapping(value = "updateVaccineRelease", produces = {"application/json"})
    public ResultVo<Map<String, Object>> updateVaccineRelease(
            @Validated({ValidationUpdate.class}) @RequestBody VaccineReleaseReqDto vaccineReleaseReqDto
    ){
        return ResultVo.ok(vaccineReleaseService.updateVaccineRelease(vaccineReleaseReqDto));
    }

    /**
     * @Author zhangyong
     * @Description //(4) 通过id查询疫苗发放信息
     * @Date 2024/02/27 15:03
     * @Param
     * @return
     */
    @ApiOperation("通过id查询疫苗发放信息")
    @ApiImplicitParam(name = "id", value = "疫苗发放id", example = "136985121358326", dataType = "Long", paramType = "form")
    @GetMapping("/getVaccineReleaseById/{id}")
    public ResultVo<Map<String, Object>> getVaccineReleaseById(
            @PathVariable Long id
    ){
        return ResultVo.ok(vaccineReleaseService.getVaccineReleaseById(id));
    }

    /**
     * @Author zhangyong
     * @Description //(5) 查询所有的疫苗发放信息列表并分页(支持关键字查询)
     * @Date 2024/02/27 15:03
     * @Param
     * @return
     */
    @ApiOperation("查询所有的疫苗发放信息列表并分页(支持关键字查询)")
    @PostMapping(value = "getVaccineReleaseListPageVo", produces = { "application/json" })
    public ResultVo<PageVo<Map<String, Object>>> getVaccineReleaseListPageVo(
            @Validated({ValidationGroup.ValidationPage.class}) @RequestBody VaccineReleasePageReqDto vaccineReleasePageReqDto
    ){
        return ResultVo.ok(vaccineReleaseService.getVaccineReleaseListPageVo(vaccineReleasePageReqDto));
    }

    /**
     * @Author zhangyong
     * @Description //(6) 下载疫苗发放标准上传模板
     * @Date 2024/02/27 15:03
     * @Param
     * @return
     */
    @ApiOperation("下载疫苗发放标准上传模板")
    @GetMapping("/downloadTemplateExcel")
    public void downloadTemplateExcel(
        @ApiIgnore HttpServletResponse response
    ){
        vaccineReleaseService.downloadTemplateExcel(response);
    }

    /**
     * @Author zhangyong
     * @Description //(6) 通过excel导入疫苗发放
     * @Date 2024/02/27 15:03
     * @Param
     * @return
     */
    @ApiOperation("通过excel导入疫苗发放")
    @PostMapping("importByExcel")
    public  ResultVo<Map<String, Object>> importByExcel(
        @ApiIgnore MultipartHttpServletRequest request
    ){
        return ResultVo.ok(vaccineReleaseService.importByExcel(request));
    }

    /**
     * @Author zhangyong
     * @Description //(8) 导出疫苗发放到excel
     * @Date 2024/02/27 15:03
     * @Param
     * @return
     */
    @ApiOperation("导出疫苗发放到excel")
    @GetMapping("/exportToExcel")
    public void exportToExcel(
        @ApiIgnore HttpServletResponse response,
        @Validated @RequestBody VaccineReleasePageReqDto vaccineReleasePageReqDto
    ){
        vaccineReleaseService.exportToExcel(vaccineReleasePageReqDto,response);
    }


    /**
     * @Author zhangyong
     * @Description //(9) 修改项目状态
     * @Date 2023/12/18 11:14
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.UPDATE)
    @ApiOperation("修改项目状态")
    @PostMapping(value = "updateStatus", produces = {"application/json"})
    public ResultVo<Map<String, Object>> updateStatus(
            @Validated @RequestBody UpdateVaccineReleaseStatusReqDto updateVaccineReleaseStatusReqDto
    ){
        return ResultVo.ok(vaccineReleaseService.updateStatus(updateVaccineReleaseStatusReqDto));
    }

}