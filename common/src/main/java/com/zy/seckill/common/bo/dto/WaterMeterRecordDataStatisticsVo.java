package com.zy.seckill.common.bo.dto;

import lombok.Data;

/**
 * @Author wangchao
 * @Description  智能水表年月日统计返回对象
 * @Date xxxx/11/25 14:55
 * @Param
 * @return
 */
@Data
public class WaterMeterRecordDataStatisticsVo {
    private String times;
    private Double count;
}
