package com.zy.seckill.common.bo.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


/**
 * @Author zhangyong
 * @Description //OperateLogPageReqDto
 * @Date 2022/03/02 15:39
 * @Param
 * @return
 */
@Getter
@Setter
@Accessors(chain=true)
public class HttpPushLogPageReqDto extends PageReqDto {

    @ApiModelProperty(name = "deviceKey", value = "设备序列号/UUID", example = "00401010101_15/cb22688c89da4adc",dataType="String")
    private String deviceKey;

    @ApiModelProperty(name = "productType", value = "产品类型", example = "1379",dataType="Long")
    private Long productType;

    @ApiModelProperty(name = "regionCode", value = "区域码", example = "510113103",dataType="String")
    private String regionCode;


    @ApiModelProperty(name = "pushType", value = "推送类型(详见字典)", example = "911",dataType="Long")
    private Long pushType;

    @ApiModelProperty(name = "status", value = "推送状态(0=失败,1=成功)", example = "xxx",dataType="Byte")
    @Min(value = 0,message = "推送状态只能为0或1")
    @Max(value = 1,message = "推送状态只能为0或1")
    private Byte status;


    @ApiModelProperty(name = "latestData", value = "是否是最新数据(0=否,1=是)", example = "xxx",dataType="Byte")
    @Min(value = 0,message = "是否是最新数据只能为0或1")
    @Max(value = 1,message = "是否是最新数据只能为0或1")
    private Byte latestData;


    @ApiModelProperty(name = "startTime", value = "推送开始时间", example = "1567764943000",dataType="Long")
    @Digits(integer = 13,fraction = 0,message = "时间戳必须为13位数字(毫秒)")
    private Long startTime;

    @ApiModelProperty(name = "endTime", value = "推送结束时间", example = "1567764958000",dataType="Long")
    @Digits(integer = 13,fraction = 0,message = "时间戳必须为13位数字(毫秒)")
    private Long endTime;

}