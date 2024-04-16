package com.zy.seckill.common.dao;

import com.zy.seckill.common.bo.po.LogRecordData;
import com.zy.seckill.common.mongo.MongoBaseDao;
import org.springframework.data.domain.Page;


public interface LogRecordDataDao extends MongoBaseDao<LogRecordData> {

    Page<LogRecordData> getLogRecordDataPageList(String alarmType, Integer dataType,String deviceId, String searchKey, Integer pageIndex, Integer pageSize);

    LogRecordData getLastLogRecordData(String deviceId);
}
