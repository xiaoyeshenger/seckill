package com.jsxa.vapp.inventory.service;

import com.jsxa.vapp.common.bo.vo.PageVo;
import com.jsxa.vapp.inventory.bo.dto.VaccineReqDto;
import com.jsxa.vapp.inventory.bo.dto.VaccinePageReqDto;
import java.util.Map;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author zhangyong
 * @Description //VaccineService接口
 * @Date 2021/02/27 14:20
 * @Param
 * @return
 */
public interface VaccineService {



    /**
     * @Description 添加疫苗
     * @Date 2021/02/27 14:20
     */
    Map<String, Object> addVaccine(VaccineReqDto vaccineReqDto);

    /**
     * @Description 通过id删除疫苗
     * @Date 2021/02/27 14:20
     */
    Map<String, Object> deleteVaccineById(Long id);

    /**
     * @Description 更新疫苗
     * @Date 2021/02/27 14:20
     */
    Map<String, Object> updateVaccine(VaccineReqDto vaccineReqDto);

    /**
     * @Description 新增或更新疫苗
     * @Date 2021/02/27 14:20
     */
    Map<String, Object> insertOrUpdateVaccine(VaccineReqDto vaccineReqDto);

    /**
     * @Description 通过id查询疫苗
     * @Date 2021/02/27 14:20
     */
    Map<String, Object> getVaccineById(Long id);

    /**
     * @Description 查询所有疫苗列表并分页
     * @Date 2021/02/27 14:20
     */
    PageVo<Map<String, Object>> getVaccineListPageVo(VaccinePageReqDto vaccinePageReqDto);

    /**
    * @Description 下载标准上传模板
    * @Date 2021/02/27 14:20
    */
    void downloadTemplateExcel(HttpServletResponse response);

    /**
     * @Description 通过excel导入疫苗
     * @Date 2021/02/27 14:20
     */
    Map<String, Object> importByExcel(MultipartHttpServletRequest request);

    /**
     * @Description 导出疫苗到excel(easyExcel方式)
     * @Date 2021/02/27 14:20
     */
    void exportToExcel(VaccinePageReqDto vaccinePageReqDto,HttpServletResponse response);
}