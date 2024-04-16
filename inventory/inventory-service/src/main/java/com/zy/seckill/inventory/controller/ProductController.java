package com.zy.seckill.inventory.controller;

import com.zy.seckill.common.bo.vo.ResultVo;
import com.zy.seckill.common.bo.vo.PageVo;
import com.zy.seckill.inventory.bo.dto.ProductReqDto;
import com.zy.seckill.inventory.bo.dto.ProductPageReqDto;
import com.zy.seckill.inventory.service.ProductService;
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
 * @Description ProductController类
 * @Date xxxx/02/27 14:20
 * @Param
 * @return
 */
@RestController
@RequestMapping("/inventory/product")
@Api(tags = "商品相关接口")
@Validated
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    /**
     * @Author zhangyong
     * @Description //(1) 添加商品信息
     * @Date xxxx/02/27 14:20
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.INSERT)
    @ApiOperation("添加商品信息")
    @PostMapping(value = "addProduct", produces = { "application/json" })
    public ResultVo<Map<String, Object>> addProduct(
            @Validated @RequestBody ProductReqDto productReqDto
    ){
        return ResultVo.ok(productService.addProduct(productReqDto));
    }

    /**
     * @Author zhangyong
     * @Description //(2) 通过id删除商品信息
     * @Date xxxx/02/27 14:20
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.DELETE)
    @ApiOperation("通过id删除商品信息")
    @ApiImplicitParam(name = "id", value = "商品id", example = "1386532156978321", dataType = "Long", paramType = "form")
    @GetMapping("/deleteProductById/{id}")
    public ResultVo<Map<String, Object>> deleteProductById(
            @PathVariable Long id
    ){
         return ResultVo.ok(productService.deleteProductById(id));
    }

    /**
     * @Author zhangyong
     * @Description //(3) 更新商品信息
     * @Date xxxx/02/27 14:20
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.UPDATE)
    @ApiOperation("更新商品信息")
    @PostMapping(value = "updateProduct", produces = {"application/json"})
    public ResultVo<Map<String, Object>> updateProduct(
            @Validated({ValidationUpdate.class}) @RequestBody ProductReqDto productReqDto
    ){
        return ResultVo.ok(productService.updateProduct(productReqDto));
    }

    /**
     * @Author zhangyong
     * @Description //(4) 通过id查询商品信息
     * @Date xxxx/02/27 14:20
     * @Param
     * @return
     */
    @ApiOperation("通过id查询商品信息")
    @ApiImplicitParam(name = "id", value = "商品id", example = "136985121358326", dataType = "Long", paramType = "form")
    @GetMapping("/getProductById/{id}")
    public ResultVo<Map<String, Object>> getProductById(
            @PathVariable Long id
    ){
        return ResultVo.ok(productService.getProductById(id));
    }

    /**
     * @Author zhangyong
     * @Description //(5) 查询所有的商品信息列表并分页(支持关键字查询)
     * @Date xxxx/02/27 14:20
     * @Param
     * @return
     */
    @ApiOperation("查询所有的商品信息列表并分页(支持关键字查询)")
    @PostMapping(value = "getProductListPageVo", produces = { "application/json" })
    public ResultVo<PageVo<Map<String, Object>>> getProductListPageVo(
            @Validated({ValidationGroup.ValidationPage.class}) @RequestBody ProductPageReqDto productPageReqDto
    ){
        return ResultVo.ok(productService.getProductListPageVo(productPageReqDto));
    }

    /**
     * @Author zhangyong
     * @Description //(6) 下载商品标准上传模板
     * @Date xxxx/02/27 14:20
     * @Param
     * @return
     */
    @ApiOperation("下载商品标准上传模板")
    @GetMapping("/downloadTemplateExcel")
    public void downloadTemplateExcel(
        @ApiIgnore HttpServletResponse response
    ){
        productService.downloadTemplateExcel(response);
    }

    /**
     * @Author zhangyong
     * @Description //(6) 通过excel导入商品
     * @Date xxxx/02/27 14:20
     * @Param
     * @return
     */
    @ApiOperation("通过excel导入商品")
    @PostMapping("importByExcel")
    public  ResultVo<Map<String, Object>> importByExcel(
        @ApiIgnore MultipartHttpServletRequest request
    ){
        return ResultVo.ok(productService.importByExcel(request));
    }

    /**
     * @Author zhangyong
     * @Description //(8) 导出商品到excel
     * @Date xxxx/02/27 14:20
     * @Param
     * @return
     */
    @ApiOperation("导出商品到excel")
    @GetMapping("/exportToExcel")
    public void exportToExcel(
        @ApiIgnore HttpServletResponse response,
        @Validated @RequestBody ProductPageReqDto productPageReqDto
    ){
        productService.exportToExcel(productPageReqDto,response);
    }

}