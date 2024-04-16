package com.zy.seckill.sys.service;

import com.zy.seckill.common.bo.dto.OperateLogPageReqDto;
import com.zy.seckill.common.bo.vo.PageVo;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/*
 * @Author zhangyong
 * @Description //OperateLogService接口
 * @Date 2022/03/02 15:39
 * @Param
 * @return
 **/
public interface OperateLogService {


    //通过id删除操作日志
    Map<String, Object> deleteOperateLogById(Map<String, Object> headerMap,String id);

    //通过id查询操作日志
    Map<String, Object> getOperateLogById(Map<String, Object> headerMap,String id);

    //查询所有操作日志列表并分页
    PageVo<Map<String, Object>> getOperateLogListPageVo(Map<String, Object> headerMap,OperateLogPageReqDto operateLogPageReqDto);


    void exportToExcel(OperateLogPageReqDto operateLogPageReqDto, HttpServletResponse response);
}