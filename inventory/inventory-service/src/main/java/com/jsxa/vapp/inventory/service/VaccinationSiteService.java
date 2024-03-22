package com.jsxa.vapp.inventory.service;

import com.jsxa.vapp.common.bo.vo.PageVo;
import com.jsxa.vapp.inventory.bo.dto.VaccinationSiteReqDto;
import com.jsxa.vapp.inventory.bo.dto.VaccinationSitePageReqDto;
import java.util.Map;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author zhangyong
 * @Description //VaccinationSiteService接口
 * @Date 2021/02/27 14:32
 * @Param
 * @return
 */
public interface VaccinationSiteService {



    /**
     * @Description 添加接种点
     * @Date 2021/02/27 14:32
     */
    Map<String, Object> addVaccinationSite(VaccinationSiteReqDto vaccinationSiteReqDto);

    /**
     * @Description 通过id删除接种点
     * @Date 2021/02/27 14:32
     */
    Map<String, Object> deleteVaccinationSiteById(Long id);

    /**
     * @Description 更新接种点
     * @Date 2021/02/27 14:32
     */
    Map<String, Object> updateVaccinationSite(VaccinationSiteReqDto vaccinationSiteReqDto);

    /**
     * @Description 新增或更新接种点
     * @Date 2021/02/27 14:32
     */
    Map<String, Object> insertOrUpdateVaccinationSite(VaccinationSiteReqDto vaccinationSiteReqDto);

    /**
     * @Description 通过id查询接种点
     * @Date 2021/02/27 14:32
     */
    Map<String, Object> getVaccinationSiteById(Long id);

    /**
     * @Description 查询所有接种点列表并分页
     * @Date 2021/02/27 14:32
     */
    PageVo<Map<String, Object>> getVaccinationSiteListPageVo(VaccinationSitePageReqDto vaccinationSitePageReqDto);

    /**
    * @Description 下载标准上传模板
    * @Date 2021/02/27 14:32
    */
    void downloadTemplateExcel(HttpServletResponse response);

    /**
     * @Description 通过excel导入接种点
     * @Date 2021/02/27 14:32
     */
    Map<String, Object> importByExcel(MultipartHttpServletRequest request);

    /**
     * @Description 导出接种点到excel(easyExcel方式)
     * @Date 2021/02/27 14:32
     */
    void exportToExcel(VaccinationSitePageReqDto vaccinationSitePageReqDto,HttpServletResponse response);
}