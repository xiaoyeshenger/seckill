package com.zy.seckill.inventory.service;

import com.zy.seckill.common.bo.vo.PageVo;
import com.zy.seckill.inventory.bo.dto.PersonReqDto;
import com.zy.seckill.inventory.bo.dto.PersonPageReqDto;
import java.util.Map;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author zhangyong
 * @Description //PersonService接口
 * @Date 2024/02/02 15:06
 * @Param
 * @return
 */
public interface PersonService {



    /**
     * @Description 添加顾客
     * @Date 2024/02/02 15:06
     */
    Map<String, Object> addPerson(PersonReqDto personReqDto);

    /**
     * @Description 通过id删除顾客
     * @Date 2024/02/02 15:06
     */
    Map<String, Object> deletePersonById(Long id);

    /**
     * @Description 更新顾客
     * @Date 2024/02/02 15:06
     */
    Map<String, Object> updatePerson(PersonReqDto personReqDto);

    /**
     * @Description 新增或更新顾客
     * @Date 2024/02/02 15:06
     */
    Map<String, Object> insertOrUpdatePerson(PersonReqDto personReqDto);

    /**
     * @Description 通过id查询顾客
     * @Date 2024/02/02 15:06
     */
    Map<String, Object> getPersonById(Long id);

    /**
     * @Description 查询所有顾客列表并分页
     * @Date 2024/02/02 15:06
     */
    PageVo<Map<String, Object>> getPersonListPageVo(PersonPageReqDto personPageReqDto);

    /**
    * @Description 下载标准上传模板
    * @Date 2024/02/02 15:06
    */
    void downloadTemplateExcel(HttpServletResponse response);

    /**
     * @Description 通过excel导入顾客
     * @Date 2024/02/02 15:06
     */
    Map<String, Object> importByExcel(MultipartHttpServletRequest request);

    /**
     * @Description 导出顾客到excel(easyExcel方式)
     * @Date 2024/02/02 15:06
     */
    void exportToExcel(PersonPageReqDto personPageReqDto, HttpServletResponse response);
}