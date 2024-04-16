package com.zy.seckill.inventory.service;

import com.zy.seckill.common.bo.vo.PageVo;
import com.zy.seckill.inventory.bo.dto.SiteReqDto;
import com.zy.seckill.inventory.bo.dto.SitePageReqDto;
import java.util.Map;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author zhangyong
 * @Description //SiteService接口
 * @Date xxxx/02/27 14:32
 * @Param
 * @return
 */
public interface SiteService {



    /**
     * @Description 添加商铺
     * @Date xxxx/02/27 14:32
     */
    Map<String, Object> addSite(SiteReqDto siteReqDto);

    /**
     * @Description 通过id删除商铺
     * @Date xxxx/02/27 14:32
     */
    Map<String, Object> deleteSiteById(Long id);

    /**
     * @Description 更新商铺
     * @Date xxxx/02/27 14:32
     */
    Map<String, Object> updateSite(SiteReqDto siteReqDto);

    /**
     * @Description 新增或更新商铺
     * @Date xxxx/02/27 14:32
     */
    Map<String, Object> insertOrUpdateSite(SiteReqDto siteReqDto);

    /**
     * @Description 通过id查询商铺
     * @Date xxxx/02/27 14:32
     */
    Map<String, Object> getSiteById(Long id);

    /**
     * @Description 查询所有商铺列表并分页
     * @Date xxxx/02/27 14:32
     */
    PageVo<Map<String, Object>> getSiteListPageVo(SitePageReqDto sitePageReqDto);

    /**
    * @Description 下载标准上传模板
    * @Date xxxx/02/27 14:32
    */
    void downloadTemplateExcel(HttpServletResponse response);

    /**
     * @Description 通过excel导入商铺
     * @Date xxxx/02/27 14:32
     */
    Map<String, Object> importByExcel(MultipartHttpServletRequest request);

    /**
     * @Description 导出商铺到excel(easyExcel方式)
     * @Date xxxx/02/27 14:32
     */
    void exportToExcel(SitePageReqDto sitePageReqDto, HttpServletResponse response);
}