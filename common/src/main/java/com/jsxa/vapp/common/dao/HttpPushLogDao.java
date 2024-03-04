package com.jsxa.vapp.common.dao;

import com.jsxa.vapp.common.bo.po.HttpPushLog;
import com.jsxa.vapp.common.bo.dto.HttpPushLogPageReqDto;
import org.springframework.data.domain.Page;
import com.jsxa.vapp.common.mongo.MongoBaseDao;

import java.util.List;

/*
 * @Author zhangyong
 * @Description //HttpPushLogDao接口
 * @Date 2023/03/23 11:02
 * @Param
 * @return
 **/
public interface HttpPushLogDao extends MongoBaseDao<HttpPushLog> {

    //通过id查询对象
    HttpPushLog getHttpPushLogById(String id);

    //通过指定字段查询对象
    HttpPushLog getHttpPushLogByColumn(String Column,Object value);

    //通过指定字段查询对象列表
    List<HttpPushLog> getHttpPushLogListByColumn(String Column, Object value);

    //查询列表并分页
    Page<HttpPushLog> getHttpPushLogListPageVo(HttpPushLogPageReqDto httpPushLogPageReqDto);

    List<HttpPushLog> getHttpPushLogList(HttpPushLogPageReqDto httpPushLogPageReqDto);
}