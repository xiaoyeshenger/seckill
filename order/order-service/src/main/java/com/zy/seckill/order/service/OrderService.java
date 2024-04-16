package com.zy.seckill.order.service;


import com.zy.seckill.order.bo.po.OrderRecord;

/**
 * @Author zhangyong
 * @Description //OrderService接口
 * @Date xxxx/02/27 15:20
 * @Param
 * @return
 */
public interface OrderService {



    /**
     * @Description 处理订单
     * @Date xxxx/02/27 15:20
     */
    void processOrder(OrderRecord orderRecord);

}