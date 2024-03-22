package com.jsxa.vapp.inventory.service;

import com.jsxa.vapp.common.bo.vo.PageVo;
import com.jsxa.vapp.inventory.bo.dto.UpdateVaccineReleaseStatusReqDto;
import com.jsxa.vapp.inventory.bo.dto.VaccineReleaseReqDto;
import com.jsxa.vapp.inventory.bo.dto.VaccineReleasePageReqDto;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author zhangyong
 * @Description //VaccineReleaseService接口
 * @Date 2021/02/27 15:03
 * @Param
 * @return
 */
public interface VaccineReleaseService {



    /**
     * @Description 添加疫苗发放
     * @Date 2021/02/27 15:03
     */
    Map<String, Object> addVaccineRelease(VaccineReleaseReqDto vaccineReleaseReqDto);

    /**
     * @Description 通过id删除疫苗发放
     * @Date 2021/02/27 15:03
     */
    Map<String, Object> deleteVaccineReleaseById(Long id);

    /**
     * @Description 更新疫苗发放
     * @Date 2021/02/27 15:03
     */
    Map<String, Object> updateVaccineRelease(VaccineReleaseReqDto vaccineReleaseReqDto);

    /**
     * @Description 新增或更新疫苗发放
     * @Date 2021/02/27 15:03
     */
    Map<String, Object> insertOrUpdateVaccineRelease(VaccineReleaseReqDto vaccineReleaseReqDto);

    /**
     * @Description 通过id查询疫苗发放
     * @Date 2021/02/27 15:03
     */
    Map<String, Object> getVaccineReleaseById(Long id);

    /**
     * @Description 查询所有疫苗发放列表并分页
     * @Date 2021/02/27 15:03
     */
    PageVo<Map<String, Object>> getVaccineReleaseListPageVo(VaccineReleasePageReqDto vaccineReleasePageReqDto);

    /**
    * @Description 下载标准上传模板
    * @Date 2021/02/27 15:03
    */
    void downloadTemplateExcel(HttpServletResponse response);

    /**
     * @Description 通过excel导入疫苗发放
     * @Date 2021/02/27 15:03
     */
    Map<String, Object> importByExcel(MultipartHttpServletRequest request);

    /**
     * @Description 导出疫苗发放到excel(easyExcel方式)
     * @Date 2021/02/27 15:03
     */
    void exportToExcel(VaccineReleasePageReqDto vaccineReleasePageReqDto,HttpServletResponse response);

    /**
     * @Description 修改放苗状态
     * @Date 2023/12/18 11:14
     */
    Map<String, Object> updateStatus(UpdateVaccineReleaseStatusReqDto updateVaccineReleaseStatusReqDto);


    /**
     * @Description 库存扣减
     * @Date 2021/03/30 13:26
     */
    Map<String, Object> reduceDock(@PathVariable("vaccineReleaseId") Long vaccineReleaseId);
}