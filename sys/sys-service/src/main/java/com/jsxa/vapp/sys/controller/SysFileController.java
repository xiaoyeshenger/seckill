package com.jsxa.vapp.sys.controller;

import com.jsxa.vapp.common.bo.vo.ResultVo;
import com.jsxa.vapp.common.bo.vo.PageVo;
import com.jsxa.vapp.common.valid.ValidationGroup.ValidationUpdate;
import com.jsxa.vapp.sys.bo.dto.SysFileExcelExportReqDto;
import com.jsxa.vapp.sys.bo.dto.SysFileReqDto;
import com.jsxa.vapp.sys.bo.dto.SysFilePageReqDto;
import com.jsxa.vapp.sys.service.SysFileService;
import com.jsxa.vapp.common.enums.BusinessType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import io.swagger.annotations.*;
import springfox.documentation.annotations.ApiIgnore;
import org.springframework.validation.annotation.Validated;
import com.jsxa.vapp.common.annotation.Log;
import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;



/*
 * @Author zhangyong
 * @Description SysFileController类
 * @Date 2022/07/29 11:14
 * @Param
 * @return
 **/
@RestController
@RequestMapping("/sys/sysFile")
@Api(tags = "系统文件相关接口")
@Validated
@RequiredArgsConstructor
public class SysFileController {

    private final SysFileService sysFileService;


    /*
     * @Author zhangyong
     * @Description //(1) 添加系统文件信息
     * @Date 2022/07/29 11:14
     * @Param
     * @return
     **/
    @Log(businessType = BusinessType.INSERT)
    @ApiOperation("添加系统文件信息")
    @PostMapping(value = "addSysFile", produces = { "application/json" })
    public ResultVo<Map<String, Object>> addSysFile(
            @ApiIgnore @RequestHeader("userInfo") String userInfo,
            @Validated SysFileReqDto sysFileReqDto,
            @ApiIgnore MultipartHttpServletRequest request
    ){
        return ResultVo.ok(sysFileService.addSysFile(userInfo,sysFileReqDto,request));
    }

    /*
     * @Author zhangyong
     * @Description //(2) 通过id删除系统文件信息
     * @Date 2022/07/29 11:14
     * @Param
     * @return
     **/
    @Log(businessType = BusinessType.DELETE)
    @ApiOperation("通过id删除系统文件信息")
    @ApiImplicitParam(name = "id", value = "系统文件id", example = "133", dataType = "Long", paramType = "form")
    @GetMapping("/deleteSysFileById/{id}")
    public ResultVo<Map<String, Object>> deleteSysFileById(
            @ApiIgnore @RequestHeader("userInfo") String userInfo,
            @PathVariable Long id
    ){
         return ResultVo.ok(sysFileService.deleteSysFileById(userInfo,id));
    }

    /*
     * @Author zhangyong
     * @Description //(3) 更新系统文件信息
     * @Date 2022/07/29 11:14
     * @Param
     * @return
     **/
    @Log(businessType = BusinessType.UPDATE)
    @ApiOperation("更新系统文件信息")
    @PostMapping(value = "updateSysFile", produces = {"application/json"})
    public ResultVo<Map<String, Object>> updateSysFile(
            @ApiIgnore @RequestHeader("userInfo") String userInfo,
            @Validated({ValidationUpdate.class}) SysFileReqDto sysFileReqDto,
            @ApiIgnore MultipartHttpServletRequest request
    ){
        return ResultVo.ok(sysFileService.updateSysFile(userInfo,sysFileReqDto,request));
    }

    /*
     * @Author zhangyong
     * @Description //(4) 通过id查询系统文件信息
     * @Date 2022/07/29 11:14
     * @Param
     * @return
     **/
    @ApiOperation("通过id查询系统文件信息")
    @ApiImplicitParam(name = "id", value = "系统文件id", example = "133", dataType = "Long", paramType = "form")
    @GetMapping("/getSysFileById/{id}")
    public ResultVo<Map<String, Object>> getSysFileById(
            @ApiIgnore @RequestHeader("userInfo") String userInfo,
            @PathVariable Long id
    ){
        return ResultVo.ok(sysFileService.getSysFileById(userInfo,id));
    }

    /*
     * @Author zhangyong
     * @Description //(5) 查询所有的系统文件信息列表并分页(支持关键字查询)
     * @Date 2022/07/29 11:14
     * @Param
     * @return
     **/
    @ApiOperation("查询所有的系统文件信息列表并分页(支持关键字查询)")
    @PostMapping(value = "getSysFileListPageVo", produces = { "application/json" })
    public ResultVo<PageVo<Map<String, Object>>> getSysFileListPageVo(
            @ApiIgnore @RequestHeader("userInfo") String userInfo,
            @Validated @RequestBody SysFilePageReqDto sysFilePageReqDto
    ){
        return ResultVo.ok(sysFileService.getSysFileListPageVo(userInfo,sysFilePageReqDto));
    }

    /*
     * @Author zhangyong
     * @Description //(6) 下载系统文件标准上传模板
     * @Date 2022/07/29 11:14
     * @Param
     * @return
     **/
    @ApiOperation("下载系统文件标准上传模板")
    @GetMapping("/downloadTemplateExcel")
    public void downloadTemplateExcel(
        @ApiIgnore @RequestHeader("userInfo") String userInfo,
        @ApiIgnore HttpServletResponse response
    ){
        sysFileService.downloadTemplateExcel(userInfo,response);
    }

    /*
     * @Author zhangyong
     * @Description //(6) 通过excel导入系统文件
     * @Date 2022/07/29 11:14
     * @Param
     * @return
     **/
    @ApiOperation("通过excel导入系统文件")
    @PostMapping("importByExcel")
    public  ResultVo<Map<String, Object>> importByExcel(
        @ApiIgnore @RequestHeader("userInfo") String userInfo,
        @ApiIgnore MultipartHttpServletRequest request
    ){
        return ResultVo.ok(sysFileService.importByExcel(userInfo,request));
    }

    /*
     * @Author zhangyong
     * @Description //(8) 导出系统文件到excel
     * @Date 2022/07/29 11:14
     * @Param
     * @return
     **/
    @ApiOperation("导出系统文件到excel")
    //@GetMapping("/exportToExcel")
    @PostMapping(value = "exportToExcel", produces = { "application/json" })
    public void exportToExcel(
        @ApiIgnore @RequestHeader("userInfo") String userInfo,
        @Validated @RequestBody SysFileExcelExportReqDto sysFileExcelExportReqDto,
        @ApiIgnore HttpServletResponse response
    ){
        sysFileService.exportToExcel(userInfo,sysFileExcelExportReqDto,response);
    }
}