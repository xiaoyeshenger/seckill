package com.jsxa.vapp.order.service;

import com.jsxa.vapp.common.bo.vo.PageVo;
import com.jsxa.vapp.order.bo.dto.VaccinationRecordReqDto;
import com.jsxa.vapp.order.bo.dto.VaccinationRecordPageReqDto;
import java.util.Map;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author zhangyong
 * @Description //VaccinationRecordService接口
 * @Date 2021/02/27 15:20
 * @Param
 * @return
 */
public interface VaccinationRecordService {



    /**
     * @Description 添加疫苗预约
     * @Date 2021/02/27 15:20
     */
    Map<String, Object> addVaccinationRecord(VaccinationRecordReqDto vaccinationRecordReqDto);

    /**
     * @Description 通过id删除疫苗预约
     * @Date 2021/02/27 15:20
     */
    Map<String, Object> deleteVaccinationRecordById(Long id);


    /**
     * @Description 新增或更新疫苗预约
     * @Date 2021/02/27 15:20
     */
    Map<String, Object> insertOrUpdateVaccinationRecord(VaccinationRecordReqDto vaccinationRecordReqDto);

    /**
     * @Description 通过id查询疫苗预约
     * @Date 2021/02/27 15:20
     */
    Map<String, Object> getVaccinationRecordById(Long id);

    /**
     * @Description 查询所有疫苗预约列表并分页
     * @Date 2021/02/27 15:20
     */
    PageVo<Map<String, Object>> getVaccinationRecordListPageVo(VaccinationRecordPageReqDto vaccinationRecordPageReqDto);

    /**
    * @Description 下载标准上传模板
    * @Date 2021/02/27 15:20
    */
    void downloadTemplateExcel(HttpServletResponse response);

    /**
     * @Description 通过excel导入疫苗预约
     * @Date 2021/02/27 15:20
     */
    Map<String, Object> importByExcel(MultipartHttpServletRequest request);

    /**
     * @Description 导出疫苗预约到excel(easyExcel方式)
     * @Date 2021/02/27 15:20
     */
    void exportToExcel(VaccinationRecordPageReqDto vaccinationRecordPageReqDto,HttpServletResponse response);
}