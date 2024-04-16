package com.zy.seckill.sys.service;

import com.zy.seckill.common.bo.vo.PageVo;
import com.zy.seckill.sys.bo.dto.NoticeReqDto;
import com.zy.seckill.sys.bo.dto.NoticePageReqDto;
import java.util.Map;

/*
 * @Author zhangyong
 * @Description //NoticeService接口
 * @Date 2022/02/28 14:46
 * @Param
 * @return
 **/
public interface NoticeService {


    //添加通知
    Map<String, Object> addNotice(Map<String, Object> headerMap,NoticeReqDto noticeReqDto);

    //通过id删除通知
    Map<String, Object> deleteNoticeById(Map<String, Object> headerMap,Long id);

    //更新通知
    Map<String, Object> updateNotice(Map<String, Object> headerMap,NoticeReqDto noticeReqDto);

    //通过id查询通知
    Map<String, Object> getNoticeById(Map<String, Object> headerMap,Long id);

    //查询所有通知列表并分页
    PageVo<Map<String, Object>> getNoticeListPageVo(Map<String, Object> headerMap,NoticePageReqDto noticePageReqDto);
}