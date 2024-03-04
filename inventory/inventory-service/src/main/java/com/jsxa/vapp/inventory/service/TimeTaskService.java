package com.jsxa.vapp.inventory.service;

import com.jsxa.vapp.inventory.bo.dto.TimeTaskPageReqDto;
import com.jsxa.vapp.inventory.bo.dto.TimeTaskReqDto;
import com.jsxa.vapp.common.bo.vo.PageVo;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Author zhangyong
 * @Description //TimeTaskService接口
 * @Date 2023/12/27 17:02
 * @Param
 * @return
 */
public interface TimeTaskService {



    /**
     * @Description 添加定时任务
     * @Date 2023/12/27 17:02
     */
    Map<String, Object> addTimeTask(TimeTaskReqDto timeTaskReqDto);

    /**
     * @Description 通过id删除定时任务
     * @Date 2023/12/27 17:02
     */
    Map<String, Object> deleteTimeTaskById(Long id);

    /**
     * @Description 更新定时任务
     * @Date 2023/12/27 17:02
     */
    Map<String, Object> updateTimeTask(TimeTaskReqDto timeTaskReqDto);

    /**
     * @Description 新增或更新定时任务
     * @Date 2023/12/27 17:02
     */
    Map<String, Object> insertOrUpdateTimeTask(TimeTaskReqDto timeTaskReqDto);

    /**
     * @Description 通过id查询定时任务
     * @Date 2023/12/27 17:02
     */
    Map<String, Object> getTimeTaskById(Long id);

    /**
     * @Description 查询所有定时任务列表并分页
     * @Date 2023/12/27 17:02
     */
    PageVo<Map<String, Object>> getTimeTaskListPageVo(TimeTaskPageReqDto timeTaskPageReqDto);

    /**
    * @Description 下载标准上传模板
    * @Date 2023/12/27 17:02
    */
    void downloadTemplateExcel(HttpServletResponse response);

    /**
     * @Description 通过excel导入定时任务
     * @Date 2023/12/27 17:02
     */
    Map<String, Object> importByExcel(MultipartHttpServletRequest request);

    /**
     * @Description 导出定时任务到excel(easyExcel方式)
     * @Date 2023/12/27 17:02
     */
    void exportToExcel(TimeTaskPageReqDto timeTaskPageReqDto,HttpServletResponse response);
}