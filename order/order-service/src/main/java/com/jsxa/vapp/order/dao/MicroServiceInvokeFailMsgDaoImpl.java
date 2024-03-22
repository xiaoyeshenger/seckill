package com.jsxa.vapp.order.dao;

import com.jsxa.vapp.common.mongo.MongoBaseDaoImpl;
import com.jsxa.vapp.order.bo.po.MicroServiceInvokeFailMsg;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;



@Repository
public class MicroServiceInvokeFailMsgDaoImpl extends MongoBaseDaoImpl<MicroServiceInvokeFailMsg> implements MicroServiceInvokeFailMsgDao {


    @Override
    public MicroServiceInvokeFailMsg getMicroServiceInvokeFailMsgById(String id) {
        MicroServiceInvokeFailMsg microServiceInvokeFailMsg = findById(id);
        return microServiceInvokeFailMsg;
    }

    @Override
    public MicroServiceInvokeFailMsg getMicroServiceInvokeFailMsgByColumn(String Column,Object value) {
        Query query = new Query(Criteria.where(Column).is(value));
        MicroServiceInvokeFailMsg microServiceInvokeFailMsg = findOne(query);
        return microServiceInvokeFailMsg;
    }
}