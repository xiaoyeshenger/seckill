package com.zy.seckill.inventory.service;

import com.zy.seckill.common.bo.vo.PageVo;
import com.zy.seckill.inventory.bo.dto.ProductReqDto;
import com.zy.seckill.inventory.bo.dto.ProductPageReqDto;
import java.util.Map;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author zhangyong
 * @Description //ProductService接口
 * @Date xxxx/02/27 14:20
 * @Param
 * @return
 */
public interface ProductService {



    /**
     * @Description 添加商品
     * @Date xxxx/02/27 14:20
     */
    Map<String, Object> addProduct(ProductReqDto productReqDto);

    /**
     * @Description 通过id删除商品
     * @Date xxxx/02/27 14:20
     */
    Map<String, Object> deleteProductById(Long id);

    /**
     * @Description 更新商品
     * @Date xxxx/02/27 14:20
     */
    Map<String, Object> updateProduct(ProductReqDto productReqDto);

    /**
     * @Description 新增或更新商品
     * @Date xxxx/02/27 14:20
     */
    Map<String, Object> insertOrUpdateProduct(ProductReqDto productReqDto);

    /**
     * @Description 通过id查询商品
     * @Date xxxx/02/27 14:20
     */
    Map<String, Object> getProductById(Long id);

    /**
     * @Description 查询所有商品列表并分页
     * @Date xxxx/02/27 14:20
     */
    PageVo<Map<String, Object>> getProductListPageVo(ProductPageReqDto productPageReqDto);

    /**
    * @Description 下载标准上传模板
    * @Date xxxx/02/27 14:20
    */
    void downloadTemplateExcel(HttpServletResponse response);

    /**
     * @Description 通过excel导入商品
     * @Date xxxx/02/27 14:20
     */
    Map<String, Object> importByExcel(MultipartHttpServletRequest request);

    /**
     * @Description 导出商品到excel(easyExcel方式)
     * @Date xxxx/02/27 14:20
     */
    void exportToExcel(ProductPageReqDto productPageReqDto, HttpServletResponse response);
}