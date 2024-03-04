package com.jsxa.vapp.common.dao;

import com.jsxa.vapp.common.bo.dto.OperateLogPageReqDto;
import com.jsxa.vapp.common.bo.po.OperateLog;
import com.jsxa.vapp.common.mongo.MongoBaseDao;
import org.springframework.data.domain.Page;

import java.util.List;

/*
 * @Author zhangyong
 * @Description //OperateLogDao接口
 * @Date 2022/03/02 15:39
 * @Param
 * @return
 **/
public interface OperateLogDao extends MongoBaseDao<OperateLog> {

    //通过id查询对象
    OperateLog getOperateLogById(String id);

    //通过指定字段查询对象
    OperateLog getOperateLogByColumn(String Column, Object value);

    //通过指定字段查询对象列表
    List<OperateLog> getOperateLogListByColumn(String Column, Object value);

    //查询列表并分页
    Page<OperateLog> getOperateLogListPageVo(OperateLogPageReqDto operateLogPageReqDto);

    List<OperateLog> getOperateLogList(OperateLogPageReqDto operateLogPageReqDto);
}