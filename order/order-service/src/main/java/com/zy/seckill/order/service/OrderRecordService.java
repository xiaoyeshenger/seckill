package com.zy.seckill.order.service;

import com.zy.seckill.common.bo.vo.PageVo;
import com.zy.seckill.order.bo.dto.OrderRecordReqDto;
import com.zy.seckill.order.bo.dto.OrderRecordPageReqDto;
import java.util.Map;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author zhangyong
 * @Description //OrderRecordService接口
 * @Date xxxx/02/27 15:20
 * @Param
 * @return
 */
public interface OrderRecordService {



    /**
     * @Description 添加秒杀记录
     * @Date xxxx/02/27 15:20
     */
    Map<String, Object> addOrderRecord(OrderRecordReqDto orderRecordReqDto);

    /**
     * @Description 通过id删除秒杀记录
     * @Date xxxx/02/27 15:20
     */
    Map<String, Object> deleteOrderRecordById(Long id);


    /**
     * @Description 新增或更新秒杀记录
     * @Date xxxx/02/27 15:20
     */
    Map<String, Object> insertOrUpdateOrderRecord(OrderRecordReqDto orderRecordReqDto);

    /**
     * @Description 通过id查询秒杀记录
     * @Date xxxx/02/27 15:20
     */
    Map<String, Object> getOrderRecordById(Long id);

    /**
     * @Description 查询所有秒杀记录列表并分页
     * @Date xxxx/02/27 15:20
     */
    PageVo<Map<String, Object>> getOrderRecordListPageVo(OrderRecordPageReqDto orderRecordPageReqDto);

    /**
    * @Description 下载标准上传模板
    * @Date xxxx/02/27 15:20
    */
    void downloadTemplateExcel(HttpServletResponse response);

    /**
     * @Description 通过excel导入秒杀记录
     * @Date xxxx/02/27 15:20
     */
    Map<String, Object> importByExcel(MultipartHttpServletRequest request);

    /**
     * @Description 导出秒杀记录到excel(easyExcel方式)
     * @Date xxxx/02/27 15:20
     */
    void exportToExcel(OrderRecordPageReqDto orderRecordPageReqDto, HttpServletResponse response);
}