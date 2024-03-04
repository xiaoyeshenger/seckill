package com.jsxa.vapp.common.dao.impl;

import com.jsxa.vapp.common.bo.po.LogRecordData;
import com.jsxa.vapp.common.dao.LogRecordDataDao;
import com.jsxa.vapp.common.mongo.MongoBaseDaoImpl;
import com.jsxa.vapp.common.utils.CommonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class LogRecordDataDaoImpl extends MongoBaseDaoImpl<LogRecordData> implements LogRecordDataDao {


    @Override
    public Page<LogRecordData> getLogRecordDataPageList(String alarmType, Integer dataType, String deviceId, String searchKey, Integer pageIndex, Integer pageSize) {
        Query query = new Query();
        if (StringUtils.isNotEmpty(searchKey)) {
            String pattern = ".*?" + StringUtils.trim(searchKey) + ".*";//包含
            Criteria cri = new Criteria();
            cri.orOperator(Criteria.where("deviceName").regex(pattern));
            query.addCriteria(cri);
        }
        if (CommonUtils.isNotEmpty(deviceId)){
            Criteria criteria = Criteria.where("deviceId").in(deviceId);
            query.addCriteria(criteria);
        }
        if (CommonUtils.isNotEmpty(alarmType)){
            Criteria criteria = Criteria.where("alarmType").is(alarmType);
            query.addCriteria(criteria);
        }
        if (CommonUtils.isNotEmpty(dataType)){
            Criteria criteria = Criteria.where("dataType").is(dataType);
            query.addCriteria(criteria);
        }

//        Criteria criteria1 = Criteria.where("isDelete").ne(1);
//        query.addCriteria(criteria1);

        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        Page<LogRecordData> pageList = findPageList(query, pageIndex, pageSize, sort);
        return pageList;
    }

    @Override
    public LogRecordData getLastLogRecordData( String deviceId) {
        Query query = new Query();
        if (CommonUtils.isNotEmpty(deviceId)){
            Criteria criteria = Criteria.where("deviceId").in(deviceId);
            query.addCriteria(criteria);
        }
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        query.with(sort).limit(1);
        LogRecordData logRecordData = findOne(query);
        return logRecordData;
    }
}
