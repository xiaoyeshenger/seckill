package com.jsxa.vapp.inventory.service;

import com.jsxa.vapp.common.bo.vo.PageVo;
import com.jsxa.vapp.inventory.bo.dto.OrganizationReqDto;
import com.jsxa.vapp.inventory.bo.dto.OrganizationPageReqDto;
import java.util.Map;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author zhangyong
 * @Description //OrganizationService接口
 * @Date 2024/02/02 15:01
 * @Param
 * @return
 */
public interface OrganizationService {



    /**
     * @Description 添加组织机构
     * @Date 2024/02/02 15:01
     */
    Map<String, Object> addOrganization(OrganizationReqDto organizationReqDto);

    /**
     * @Description 通过id删除组织机构
     * @Date 2024/02/02 15:01
     */
    Map<String, Object> deleteOrganizationById(Long id);

    /**
     * @Description 更新组织机构
     * @Date 2024/02/02 15:01
     */
    Map<String, Object> updateOrganization(OrganizationReqDto organizationReqDto);

    /**
     * @Description 新增或更新组织机构
     * @Date 2024/02/02 15:01
     */
    Map<String, Object> insertOrUpdateOrganization(OrganizationReqDto organizationReqDto);

    /**
     * @Description 通过id查询组织机构
     * @Date 2024/02/02 15:01
     */
    Map<String, Object> getOrganizationById(Long id);

    /**
     * @Description 查询所有组织机构列表并分页
     * @Date 2024/02/02 15:01
     */
    PageVo<Map<String, Object>> getOrganizationListPageVo(OrganizationPageReqDto organizationPageReqDto);

    /**
    * @Description 下载标准上传模板
    * @Date 2024/02/02 15:01
    */
    void downloadTemplateExcel(HttpServletResponse response);

    /**
     * @Description 通过excel导入组织机构
     * @Date 2024/02/02 15:01
     */
    Map<String, Object> importByExcel(MultipartHttpServletRequest request);

    /**
     * @Description 导出组织机构到excel(easyExcel方式)
     * @Date 2024/02/02 15:01
     */
    void exportToExcel(OrganizationPageReqDto organizationPageReqDto,HttpServletResponse response);
}