package com.zy.seckill.common.bo.vo;

import lombok.Data;

/*
 * @Author wangchao
 * @Description  智能电表年月日统计返回对象
 * @Date xxxx/11/25 14:55
 * @Param
 * @return
 **/
@Data
public class ElectricityRecordDataStatisticsVo {
    private String times;
    private Double count;
}
