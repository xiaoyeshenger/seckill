package com.jsxa.vapp.common.dao;

import com.jsxa.vapp.common.bo.po.LogRecordData;
import com.jsxa.vapp.common.mongo.MongoBaseDao;
import org.springframework.data.domain.Page;


public interface LogRecordDataDao extends MongoBaseDao<LogRecordData> {

    Page<LogRecordData> getLogRecordDataPageList(String alarmType, Integer dataType,String deviceId, String searchKey, Integer pageIndex, Integer pageSize);

    LogRecordData getLastLogRecordData(String deviceId);
}
