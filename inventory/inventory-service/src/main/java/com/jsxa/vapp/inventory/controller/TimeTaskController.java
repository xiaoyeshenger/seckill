package com.jsxa.vapp.inventory.controller;

import com.jsxa.vapp.inventory.bo.dto.TimeTaskPageReqDto;
import com.jsxa.vapp.inventory.bo.dto.TimeTaskReqDto;
import com.jsxa.vapp.common.annotation.Log;
import com.jsxa.vapp.common.bo.vo.PageVo;
import com.jsxa.vapp.common.bo.vo.ResultVo;
import com.jsxa.vapp.common.enums.BusinessType;
import com.jsxa.vapp.common.valid.ValidationGroup;
import com.jsxa.vapp.common.valid.ValidationGroup.ValidationUpdate;
import com.jsxa.vapp.inventory.service.TimeTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;


/**
 * @Author zhangyong
 * @Description TimeTaskController类
 * @Date 2023/12/27 17:02
 * @Param
 * @return
 */
@RestController
@RequestMapping("/inventory/timeTask")
@Api(tags = "定时任务相关接口")
@Validated
@RequiredArgsConstructor
public class TimeTaskController {

    private final TimeTaskService timeTaskService;


    /**
     * @Author zhangyong
     * @Description //(1) 添加定时任务信息
     * @Date 2023/12/27 17:02
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.INSERT)
    @ApiOperation("添加定时任务信息")
    @PostMapping(value = "addTimeTask", produces = { "application/json" })
    public ResultVo<Map<String, Object>> addTimeTask(
            @Validated @RequestBody TimeTaskReqDto timeTaskReqDto
    ){
        return ResultVo.ok(timeTaskService.addTimeTask(timeTaskReqDto));
    }

    /**
     * @Author zhangyong
     * @Description //(2) 通过id删除定时任务信息
     * @Date 2023/12/27 17:02
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.DELETE)
    @ApiOperation("通过id删除定时任务信息")
    @ApiImplicitParam(name = "id", value = "定时任务id", example = "1386532156978321", dataType = "Long", paramType = "form")
    @GetMapping("/deleteTimeTaskById/{id}")
    public ResultVo<Map<String, Object>> deleteTimeTaskById(
            @PathVariable Long id
    ){
         return ResultVo.ok(timeTaskService.deleteTimeTaskById(id));
    }

    /**
     * @Author zhangyong
     * @Description //(3) 更新定时任务信息
     * @Date 2023/12/27 17:02
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.UPDATE)
    @ApiOperation("更新定时任务信息")
    @PostMapping(value = "updateTimeTask", produces = {"application/json"})
    public ResultVo<Map<String, Object>> updateTimeTask(
            @Validated({ValidationUpdate.class}) @RequestBody TimeTaskReqDto timeTaskReqDto
    ){
        return ResultVo.ok(timeTaskService.updateTimeTask(timeTaskReqDto));
    }

    /**
     * @Author zhangyong
     * @Description //(4) 通过id查询定时任务信息
     * @Date 2023/12/27 17:02
     * @Param
     * @return
     */
    @ApiOperation("通过id查询定时任务信息")
    @ApiImplicitParam(name = "id", value = "定时任务id", example = "136985121358326", dataType = "Long", paramType = "form")
    @GetMapping("/getTimeTaskById/{id}")
    public ResultVo<Map<String, Object>> getTimeTaskById(
            @PathVariable Long id
    ){
        return ResultVo.ok(timeTaskService.getTimeTaskById(id));
    }

    /**
     * @Author zhangyong
     * @Description //(5) 查询所有的定时任务信息列表并分页(支持关键字查询)
     * @Date 2023/12/27 17:02
     * @Param
     * @return
     */
    @ApiOperation("查询所有的定时任务信息列表并分页(支持关键字查询)")
    @PostMapping(value = "getTimeTaskListPageVo", produces = { "application/json" })
    public ResultVo<PageVo<Map<String, Object>>> getTimeTaskListPageVo(
            @Validated({ValidationGroup.ValidationPage.class}) @RequestBody TimeTaskPageReqDto timeTaskPageReqDto
    ){
        return ResultVo.ok(timeTaskService.getTimeTaskListPageVo(timeTaskPageReqDto));
    }

    /**
     * @Author zhangyong
     * @Description //(6) 下载定时任务标准上传模板
     * @Date 2023/12/27 17:02
     * @Param
     * @return
     */
    @ApiOperation("下载定时任务标准上传模板")
    @GetMapping("/downloadTemplateExcel")
    public void downloadTemplateExcel(
        @ApiIgnore HttpServletResponse response
    ){
        timeTaskService.downloadTemplateExcel(response);
    }

    /**
     * @Author zhangyong
     * @Description //(6) 通过excel导入定时任务
     * @Date 2023/12/27 17:02
     * @Param
     * @return
     */
    @ApiOperation("通过excel导入定时任务")
    @PostMapping("importByExcel")
    public  ResultVo<Map<String, Object>> importByExcel(
        @ApiIgnore MultipartHttpServletRequest request
    ){
        return ResultVo.ok(timeTaskService.importByExcel(request));
    }

    /**
     * @Author zhangyong
     * @Description //(8) 导出定时任务到excel
     * @Date 2023/12/27 17:02
     * @Param
     * @return
     */
    @ApiOperation("导出定时任务到excel")
    @GetMapping("/exportToExcel")
    public void exportToExcel(
        @ApiIgnore HttpServletResponse response,
        @Validated @RequestBody TimeTaskPageReqDto timeTaskPageReqDto
    ){
        timeTaskService.exportToExcel(timeTaskPageReqDto,response);
    }

}