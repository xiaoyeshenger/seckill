package com.jsxa.vapp.common.bo.dto;

import lombok.Data;

/**
 * @Author wangchao
 * @Description  智能水表年月日统计返回对象
 * @Date 2021/11/25 14:55
 * @Param
 * @return
 */
@Data
public class WaterMeterRecordDataStatisticsVo {
    private String times;
    private Double count;
}
