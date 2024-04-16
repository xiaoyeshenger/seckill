package com.zy.seckill.sys.service;

import com.zy.seckill.common.bo.dto.HttpPushLogPageReqDto;
import com.zy.seckill.common.bo.vo.PageVo;
import com.zy.seckill.sys.bo.dto.HttpPushLogReqDto;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/*
 * @Author wangchao
 * @Description //HttpPushLogService接口
 * @Date 2023/03/23 11:02
 * @Param
 * @return
 **/
public interface HttpPushLogService {


    //通过id查询HTTP推送日志
    Map<String, Object> getHttpPushLogById(String id);

    //查询所有HTTP推送日志列表并分页
    PageVo<Map<String, Object>> getHttpPushLogListPageVo(HttpPushLogPageReqDto httpPushLogPageReqDto);

    void exportToExcel(HttpPushLogPageReqDto httpPushLogPageReqDto, HttpServletResponse response);


    void exportToCdlotExcel(HttpPushLogPageReqDto httpPushLogPageReqDto, HttpServletResponse response);

    Map<String, Object>  againPushLog(HttpPushLogReqDto httpPushLogReqDto);
}