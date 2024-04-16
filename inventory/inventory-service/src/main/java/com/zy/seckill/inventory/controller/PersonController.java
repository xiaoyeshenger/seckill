package com.zy.seckill.inventory.controller;

import com.zy.seckill.common.bo.vo.ResultVo;
import com.zy.seckill.common.bo.vo.PageVo;
import com.zy.seckill.inventory.bo.dto.PersonReqDto;
import com.zy.seckill.inventory.bo.dto.PersonPageReqDto;
import com.zy.seckill.inventory.service.PersonService;
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
 * @Description VaccinationPersonController类
 * @Date 2024/02/02 15:06
 * @Param
 * @return
 */
@RestController
@RequestMapping("/inventory/vaccinationPerson")
@Api(tags = "顾客相关接口")
@Validated
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;


    /**
     * @Author zhangyong
     * @Description //(1) 添加顾客信息
     * @Date 2024/02/02 15:06
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.INSERT)
    @ApiOperation("添加顾客信息")
    @PostMapping(value = "addVaccinationPerson", produces = { "application/json" })
    public ResultVo<Map<String, Object>> addVaccinationPerson(
            @Validated @RequestBody PersonReqDto personReqDto
    ){
        return ResultVo.ok(personService.addPerson(personReqDto));
    }

    /**
     * @Author zhangyong
     * @Description //(2) 通过id删除顾客信息
     * @Date 2024/02/02 15:06
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.DELETE)
    @ApiOperation("通过id删除顾客信息")
    @ApiImplicitParam(name = "id", value = "顾客id", example = "1386532156978321", dataType = "Long", paramType = "form")
    @GetMapping("/deleteVaccinationPersonById/{id}")
    public ResultVo<Map<String, Object>> deleteVaccinationPersonById(
            @PathVariable Long id
    ){
         return ResultVo.ok(personService.deletePersonById(id));
    }

    /**
     * @Author zhangyong
     * @Description //(3) 更新顾客信息
     * @Date 2024/02/02 15:06
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.UPDATE)
    @ApiOperation("更新顾客信息")
    @PostMapping(value = "updateVaccinationPerson", produces = {"application/json"})
    public ResultVo<Map<String, Object>> updateVaccinationPerson(
            @Validated({ValidationUpdate.class}) @RequestBody PersonReqDto personReqDto
    ){
        return ResultVo.ok(personService.updatePerson(personReqDto));
    }

    /**
     * @Author zhangyong
     * @Description //(4) 通过id查询顾客信息
     * @Date 2024/02/02 15:06
     * @Param
     * @return
     */
    @ApiOperation("通过id查询顾客信息")
    @ApiImplicitParam(name = "id", value = "顾客id", example = "136985121358326", dataType = "Long", paramType = "form")
    @GetMapping("/getVaccinationPersonById/{id}")
    public ResultVo<Map<String, Object>> getVaccinationPersonById(
            @PathVariable Long id
    ){
        return ResultVo.ok(personService.getPersonById(id));
    }

    /**
     * @Author zhangyong
     * @Description //(5) 查询所有的顾客信息列表并分页(支持关键字查询)
     * @Date 2024/02/02 15:06
     * @Param
     * @return
     */
    @ApiOperation("查询所有的顾客信息列表并分页(支持关键字查询)")
    @PostMapping(value = "getVaccinationPersonListPageVo", produces = { "application/json" })
    public ResultVo<PageVo<Map<String, Object>>> getVaccinationPersonListPageVo(
            @Validated({ValidationGroup.ValidationPage.class}) @RequestBody PersonPageReqDto personPageReqDto
    ){
        return ResultVo.ok(personService.getPersonListPageVo(personPageReqDto));
    }

    /**
     * @Author zhangyong
     * @Description //(6) 下载顾客标准上传模板
     * @Date 2024/02/02 15:06
     * @Param
     * @return
     */
    @ApiOperation("下载顾客标准上传模板")
    @GetMapping("/downloadTemplateExcel")
    public void downloadTemplateExcel(
        @ApiIgnore HttpServletResponse response
    ){
        personService.downloadTemplateExcel(response);
    }

    /**
     * @Author zhangyong
     * @Description //(6) 通过excel导入顾客
     * @Date 2024/02/02 15:06
     * @Param
     * @return
     */
    @ApiOperation("通过excel导入顾客")
    @PostMapping("importByExcel")
    public  ResultVo<Map<String, Object>> importByExcel(
        @ApiIgnore MultipartHttpServletRequest request
    ){
        return ResultVo.ok(personService.importByExcel(request));
    }

    /**
     * @Author zhangyong
     * @Description //(8) 导出顾客到excel
     * @Date 2024/02/02 15:06
     * @Param
     * @return
     */
    @ApiOperation("导出顾客到excel")
    @GetMapping("/exportToExcel")
    public void exportToExcel(
        @ApiIgnore HttpServletResponse response,
        @Validated @RequestBody PersonPageReqDto personPageReqDto
    ){
        personService.exportToExcel(personPageReqDto,response);
    }

}