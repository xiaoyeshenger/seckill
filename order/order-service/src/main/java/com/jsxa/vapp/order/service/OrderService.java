package com.jsxa.vapp.order.service;

import com.jsxa.vapp.order.bo.po.VaccinationRecord;


/**
 * @Author zhangyong
 * @Description //OrderService接口
 * @Date 2021/02/27 15:20
 * @Param
 * @return
 */
public interface OrderService {



    /**
     * @Description 处理订单(疫苗预约)
     * @Date 2021/02/27 15:20
     */
    void processOrder(VaccinationRecord vaccinationRecord);

}