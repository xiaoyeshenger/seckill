package com.zy.seckill.order.dao;

import com.zy.seckill.common.mongo.MongoBaseDao;
import com.zy.seckill.order.bo.po.MicroServiceInvokeFailMsg;


/**
 * @Author zhangyong
 * @Description //MicroServiceInvokeFailMsg
 * @Date xxxx/03/23 11:02
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