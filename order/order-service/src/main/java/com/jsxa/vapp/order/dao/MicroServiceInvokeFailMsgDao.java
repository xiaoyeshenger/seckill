package com.jsxa.vapp.order.dao;

import com.jsxa.vapp.common.mongo.MongoBaseDao;
import com.jsxa.vapp.order.bo.po.MicroServiceInvokeFailMsg;


/**
 * @Author zhangyong
 * @Description //MicroServiceInvokeFailMsg
 * @Date 2021/03/23 11:02
 * @Param
 * @return
 **/
public interface MicroServiceInvokeFailMsgDao extends MongoBaseDao<MicroServiceInvokeFailMsg> {

    /**
     * 通过id查询对象
     */
    MicroServiceInvokeFailMsg getMicroServiceInvokeFailMsgById(String id);

     /**
     * 通过指定字段查询对象
     */
    MicroServiceInvokeFailMsg getMicroServiceInvokeFailMsgByColumn(String Column,Object value);

}