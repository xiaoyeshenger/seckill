package com.zy.seckill.inventory.controller;

import com.zy.seckill.common.bo.vo.ResultVo;
import com.zy.seckill.common.bo.vo.PageVo;
import com.zy.seckill.inventory.bo.dto.UpdateProductReleaseStatusReqDto;
import com.zy.seckill.inventory.bo.dto.ProductReleaseReqDto;
import com.zy.seckill.inventory.bo.dto.ProductReleasePageReqDto;
import com.zy.seckill.inventory.service.ProductReleaseService;
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
 * @Description ProductReleaseController类
 * @Date xxxx/02/27 15:03
 * @Param
 * @return
 */
@RestController
@RequestMapping("/inventory/ProductRelease")
@Api(tags = "商品发布相关接口")
@Validated
@RequiredArgsConstructor
public class ProductReleaseController {

    private final ProductReleaseService productReleaseService;


    /**
     * @Author zhangyong
     * @Description //(1) 添加商品发布信息
     * @Date xxxx/02/27 15:03
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.INSERT)
    @ApiOperation("添加商品发布信息")
    @PostMapping(value = "addProductRelease", produces = { "application/json" })
    public ResultVo<Map<String, Object>> addProductRelease(
            @Validated @RequestBody ProductReleaseReqDto productReleaseReqDto
    ){
        return ResultVo.ok(productReleaseService.addProductRelease(productReleaseReqDto));
    }

    /**
     * @Author zhangyong
     * @Description //(2) 通过id删除商品发布信息
     * @Date xxxx/02/27 15:03
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.DELETE)
    @ApiOperation("通过id删除商品发布信息")
    @ApiImplicitParam(name = "id", value = "商品发布id", example = "1386532156978321", dataType = "Long", paramType = "form")
    @GetMapping("/deleteProductReleaseById/{id}")
    public ResultVo<Map<String, Object>> deleteProductReleaseById(
            @PathVariable Long id
    ){
         return ResultVo.ok(productReleaseService.deleteProductReleaseById(id));
    }

    /**
     * @Author zhangyong
     * @Description //(3) 更新商品发布信息
     * @Date xxxx/02/27 15:03
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.UPDATE)
    @ApiOperation("更新商品发布信息")
    @PostMapping(value = "updateProductRelease", produces = {"application/json"})
    public ResultVo<Map<String, Object>> updateProductRelease(
            @Validated({ValidationUpdate.class}) @RequestBody ProductReleaseReqDto productReleaseReqDto
    ){
        return ResultVo.ok(productReleaseService.updateProductRelease(productReleaseReqDto));
    }

    /**
     * @Author zhangyong
     * @Description //(4) 通过id查询商品发布信息
     * @Date xxxx/02/27 15:03
     * @Param
     * @return
     */
    @ApiOperation("通过id查询商品发布信息")
    @ApiImplicitParam(name = "id", value = "商品发布id", example = "136985121358326", dataType = "Long", paramType = "form")
    @GetMapping("/getProductReleaseById/{id}")
    public ResultVo<Map<String, Object>> getProductReleaseById(
            @PathVariable Long id
    ){
        return ResultVo.ok(productReleaseService.getProductReleaseById(id));
    }

    /**
     * @Author zhangyong
     * @Description //(5) 查询所有的商品发布信息列表并分页(支持关键字查询)
     * @Date xxxx/02/27 15:03
     * @Param
     * @return
     */
    @ApiOperation("查询所有的商品发布信息列表并分页(支持关键字查询)")
    @PostMapping(value = "getProductReleaseListPageVo", produces = { "application/json" })
    public ResultVo<PageVo<Map<String, Object>>> getProductReleaseListPageVo(
            @Validated({ValidationGroup.ValidationPage.class}) @RequestBody ProductReleasePageReqDto productReleasePageReqDto
    ){
        return ResultVo.ok(productReleaseService.getProductReleaseListPageVo(productReleasePageReqDto));
    }

    /**
     * @Author zhangyong
     * @Description //(6) 下载商品发布标准上传模板
     * @Date xxxx/02/27 15:03
     * @Param
     * @return
     */
    @ApiOperation("下载商品发布标准上传模板")
    @GetMapping("/downloadTemplateExcel")
    public void downloadTemplateExcel(
        @ApiIgnore HttpServletResponse response
    ){
        productReleaseService.downloadTemplateExcel(response);
    }

    /**
     * @Author zhangyong
     * @Description //(6) 通过excel导入商品发布
     * @Date xxxx/02/27 15:03
     * @Param
     * @return
     */
    @ApiOperation("通过excel导入商品发布")
    @PostMapping("importByExcel")
    public  ResultVo<Map<String, Object>> importByExcel(
        @ApiIgnore MultipartHttpServletRequest request
    ){
        return ResultVo.ok(productReleaseService.importByExcel(request));
    }

    /**
     * @Author zhangyong
     * @Description //(8) 导出商品发布到excel
     * @Date xxxx/02/27 15:03
     * @Param
     * @return
     */
    @ApiOperation("导出商品发布到excel")
    @GetMapping("/exportToExcel")
    public void exportToExcel(
        @ApiIgnore HttpServletResponse response,
        @Validated @RequestBody ProductReleasePageReqDto productReleasePageReqDto
    ){
        productReleaseService.exportToExcel(productReleasePageReqDto,response);
    }


    /**
     * @Author zhangyong
     * @Description //(9) 修改项目状态
     * @Date 2023/12/18 11:14
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.UPDATE)
    @ApiOperation("修改放苗状态")
    @PostMapping(value = "updateStatus", produces = {"application/json"})
    public ResultVo<Map<String, Object>> updateStatus(
            @Validated @RequestBody UpdateProductReleaseStatusReqDto updateProductReleaseStatusReqDto
    ){
        return ResultVo.ok(productReleaseService.updateStatus(updateProductReleaseStatusReqDto));
    }

}