package com.jsxa.vapp.inventory.service;

import com.jsxa.vapp.common.bo.vo.PageVo;
import com.jsxa.vapp.inventory.bo.dto.VaccinationPersonReqDto;
import com.jsxa.vapp.inventory.bo.dto.VaccinationPersonPageReqDto;
import java.util.Map;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author zhangyong
 * @Description //VaccinationPersonService接口
 * @Date 2024/02/02 15:06
 * @Param
 * @return
 */
public interface VaccinationPersonService {



    /**
     * @Description 添加接种人员
     * @Date 2024/02/02 15:06
     */
    Map<String, Object> addVaccinationPerson(VaccinationPersonReqDto vaccinationPersonReqDto);

    /**
     * @Description 通过id删除接种人员
     * @Date 2024/02/02 15:06
     */
    Map<String, Object> deleteVaccinationPersonById(Long id);

    /**
     * @Description 更新接种人员
     * @Date 2024/02/02 15:06
     */
    Map<String, Object> updateVaccinationPerson(VaccinationPersonReqDto vaccinationPersonReqDto);

    /**
     * @Description 新增或更新接种人员
     * @Date 2024/02/02 15:06
     */
    Map<String, Object> insertOrUpdateVaccinationPerson(VaccinationPersonReqDto vaccinationPersonReqDto);

    /**
     * @Description 通过id查询接种人员
     * @Date 2024/02/02 15:06
     */
    Map<String, Object> getVaccinationPersonById(Long id);

    /**
     * @Description 查询所有接种人员列表并分页
     * @Date 2024/02/02 15:06
     */
    PageVo<Map<String, Object>> getVaccinationPersonListPageVo(VaccinationPersonPageReqDto vaccinationPersonPageReqDto);

    /**
    * @Description 下载标准上传模板
    * @Date 2024/02/02 15:06
    */
    void downloadTemplateExcel(HttpServletResponse response);

    /**
     * @Description 通过excel导入接种人员
     * @Date 2024/02/02 15:06
     */
    Map<String, Object> importByExcel(MultipartHttpServletRequest request);

    /**
     * @Description 导出接种人员到excel(easyExcel方式)
     * @Date 2024/02/02 15:06
     */
    void exportToExcel(VaccinationPersonPageReqDto vaccinationPersonPageReqDto,HttpServletResponse response);
}