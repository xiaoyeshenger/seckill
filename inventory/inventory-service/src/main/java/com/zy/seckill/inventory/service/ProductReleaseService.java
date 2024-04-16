package com.zy.seckill.inventory.service;

import com.zy.seckill.common.bo.vo.PageVo;
import com.zy.seckill.inventory.bo.dto.UpdateProductReleaseStatusReqDto;
import com.zy.seckill.inventory.bo.dto.ProductReleaseReqDto;
import com.zy.seckill.inventory.bo.dto.ProductReleasePageReqDto;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author zhangyong
 * @Description //ProductReleaseService接口
 * @Date xxxx/02/27 15:03
 * @Param
 * @return
 */
public interface ProductReleaseService {



    /**
     * @Description 添加疫苗发放
     * @Date xxxx/02/27 15:03
     */
    Map<String, Object> addProductRelease(ProductReleaseReqDto productReleaseReqDto);

    /**
     * @Description 通过id删除疫苗发放
     * @Date xxxx/02/27 15:03
     */
    Map<String, Object> deleteProductReleaseById(Long id);

    /**
     * @Description 更新疫苗发放
     * @Date xxxx/02/27 15:03
     */
    Map<String, Object> updateProductRelease(ProductReleaseReqDto productReleaseReqDto);

    /**
     * @Description 新增或更新疫苗发放
     * @Date xxxx/02/27 15:03
     */
    Map<String, Object> insertOrUpdateProductRelease(ProductReleaseReqDto productReleaseReqDto);

    /**
     * @Description 通过id查询疫苗发放
     * @Date xxxx/02/27 15:03
     */
    Map<String, Object> getProductReleaseById(Long id);

    /**
     * @Description 查询所有疫苗发放列表并分页
     * @Date xxxx/02/27 15:03
     */
    PageVo<Map<String, Object>> getProductReleaseListPageVo(ProductReleasePageReqDto productReleasePageReqDto);

    /**
    * @Description 下载标准上传模板
    * @Date xxxx/02/27 15:03
    */
    void downloadTemplateExcel(HttpServletResponse response);

    /**
     * @Description 通过excel导入疫苗发放
     * @Date xxxx/02/27 15:03
     */
    Map<String, Object> importByExcel(MultipartHttpServletRequest request);

    /**
     * @Description 导出疫苗发放到excel(easyExcel方式)
     * @Date xxxx/02/27 15:03
     */
    void exportToExcel(ProductReleasePageReqDto productReleasePageReqDto, HttpServletResponse response);

    /**
     * @Description 修改放苗状态
     * @Date 2023/12/18 11:14
     */
    Map<String, Object> updateStatus(UpdateProductReleaseStatusReqDto updateProductReleaseStatusReqDto);


    /**
     * @Description 库存扣减
     * @Date xxxx/03/30 13:26
     */
    Map<String, Object> reduceDock(@PathVariable("productReleaseId") Long productReleaseId);
}