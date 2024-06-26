package com.zy.seckill.sys.controller;

import com.zy.seckill.common.bo.vo.ResultVo;
import com.zy.seckill.common.bo.vo.PageVo;
import com.zy.seckill.sys.bo.dto.OrganizationReqDto;
import com.zy.seckill.sys.bo.dto.OrganizationPageReqDto;
import com.zy.seckill.sys.service.OrganizationService;
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

import java.util.List;
import java.util.Map;


/**
 * @Author zhangyong
 * @Description OrganizationController类
 * @Date 2023/12/27 08:50
 * @Param
 * @return
 */
@RestController
@RequestMapping("/sys/organization")
@Api(tags = "组织机构相关接口")
@Validated
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;


    /**
     * @Author zhangyong
     * @Description //(1) 添加组织机构信息
     * @Date 2023/12/27 08:50
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.INSERT)
    @ApiOperation("添加组织机构信息")
    @PostMapping(value = "addOrganization", produces = { "application/json" })
    public ResultVo<Map<String, Object>> addOrganization(
            @Validated @RequestBody OrganizationReqDto organizationReqDto
    ){
        return ResultVo.ok(organizationService.addOrganization(organizationReqDto));
    }

    /**
     * @Author zhangyong
     * @Description //(2) 通过id删除组织机构信息
     * @Date 2023/12/27 08:50
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.DELETE)
    @ApiOperation("通过id删除组织机构信息")
    @ApiImplicitParam(name = "id", value = "组织机构id", example = "1386532156978321", dataType = "Long", paramType = "form")
    @GetMapping("/deleteOrganizationById/{id}")
    public ResultVo<Map<String, Object>> deleteOrganizationById(
            @PathVariable Long id
    ){
         return ResultVo.ok(organizationService.deleteOrganizationById(id));
    }

    /**
     * @Author zhangyong
     * @Description //(3) 更新组织机构信息
     * @Date 2023/12/27 08:50
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.UPDATE)
    @ApiOperation("更新组织机构信息")
    @PostMapping(value = "updateOrganization", produces = {"application/json"})
    public ResultVo<Map<String, Object>> updateOrganization(
            @Validated({ValidationUpdate.class}) @RequestBody OrganizationReqDto organizationReqDto
    ){
        return ResultVo.ok(organizationService.updateOrganization(organizationReqDto));
    }

    /**
     * @Author zhangyong
     * @Description //(4) 通过id查询组织机构信息
     * @Date 2023/12/27 08:50
     * @Param
     * @return
     */
    @ApiOperation("通过id查询组织机构信息")
    @ApiImplicitParam(name = "id", value = "组织机构id", example = "136985121358326", dataType = "Long", paramType = "form")
    @GetMapping("/getOrganizationById/{id}")
    public ResultVo<Map<String, Object>> getOrganizationById(
            @PathVariable Long id
    ){
        return ResultVo.ok(organizationService.getOrganizationById(id));
    }

    /**
     * @Author zhangyong
     * @Description //(5) 查询所有的组织机构信息列表并分页(支持关键字查询)
     * @Date 2023/12/27 08:50
     * @Param
     * @return
     */
    @ApiOperation("查询所有的组织机构信息列表并分页(支持关键字查询)")
    @PostMapping(value = "getOrganizationListPageVo", produces = { "application/json" })
    public ResultVo<PageVo<Map<String, Object>>> getOrganizationListPageVo(
            @Validated({ValidationGroup.ValidationPage.class}) @RequestBody OrganizationPageReqDto organizationPageReqDto
    ){
        return ResultVo.ok(organizationService.getOrganizationListPageVo(organizationPageReqDto));
    }

    /**
     * @Author zhangyong
     * @Description //(6) 下载组织机构标准上传模板
     * @Date 2023/12/27 08:50
     * @Param
     * @return
     */
    @ApiOperation("下载组织机构标准上传模板")
    @GetMapping("/downloadTemplateExcel")
    public void downloadTemplateExcel(
        @ApiIgnore HttpServletResponse response
    ){
        organizationService.downloadTemplateExcel(response);
    }

    /**
     * @Author zhangyong
     * @Description //(6) 通过excel导入组织机构
     * @Date 2023/12/27 08:50
     * @Param
     * @return
     */
    @ApiOperation("通过excel导入组织机构")
    @PostMapping("importByExcel")
    public  ResultVo<Map<String, Object>> importByExcel(
        @ApiIgnore MultipartHttpServletRequest request
    ){
        return ResultVo.ok(organizationService.importByExcel(request));
    }

    /**
     * @Author zhangyong
     * @Description //(8) 导出组织机构到excel
     * @Date 2023/12/27 08:50
     * @Param
     * @return
     */
    @ApiOperation("导出组织机构到excel")
    @GetMapping("/exportToExcel")
    public void exportToExcel(
        @ApiIgnore HttpServletResponse response,
        @Validated @RequestBody OrganizationPageReqDto organizationPageReqDto
    ){
        organizationService.exportToExcel(organizationPageReqDto,response);
    }

    /**
     * @Author zhangyong
     * @Description //(9) 查询所有的组织单位列表
     * @Date 2022/02/09 17:31
     * @Param
     * @return
     */
    @ApiOperation("查询所有的组织单位列表")
    @GetMapping(value = "getOrgList", produces = { "application/json" })
    public ResultVo<List<Map<String, Object>>> getOrgList(
            @PathVariable Long userId
    ){
        return ResultVo.ok(organizationService.getOrgList(userId));
    }

}