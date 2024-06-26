package com.zy.seckill.common.dao;

import com.zy.seckill.common.bo.dto.LoginLogPageReqDto;
import com.zy.seckill.common.bo.dto.OperateLogPageReqDto;
import com.zy.seckill.common.bo.po.LoginLog;
import com.zy.seckill.common.bo.po.OperateLog;
import com.zy.seckill.common.mongo.MongoBaseDao;
import org.springframework.data.domain.Page;

import java.util.List;

/*
 * @Author zhangyong
 * @Description //LoginLogDao接口
 * @Date 2022/03/02 16:01
 * @Param
 * @return
 **/
public interface LoginLogDao extends MongoBaseDao<LoginLog> {

    //通过id查询对象
    LoginLog getLoginLogById(String id);

    //通过指定字段查询对象
    LoginLog getLoginLogByColumn(String Column, Object value);

    //通过指定字段查询对象列表
    List<LoginLog> getLoginLogListByColumn(String Column, Object value);

    //查询列表并分页
    Page<LoginLog> getLoginLogListPageVo(LoginLogPageReqDto loginLogPageReqDto);

    List<LoginLog> getLoginLogList(LoginLogPageReqDto loginLogPageReqDto);
}