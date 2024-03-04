package com.jsxa.vapp.common.bo.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


/**
 * @Author zhangyong
 * @Description //LoginLogPageReqDto
 * @Date 2022/03/02 16:01
 * @Param
 * @return
 */
@Getter
@Setter
@Accessors(chain=true)
public class LoginLogPageReqDto extends PageReqDto {


    @ApiModelProperty(name = "regionCode", value = "区域码", example = "xxx",dataType="String")
    private String regionCode;

    @ApiModelProperty(name = "userName", value = "用户名", example = "xxx",dataType="String")
    private String userName;

    @ApiModelProperty(name = "ip", value = "登录地址(ip)", example = "xxx",dataType="String")
    private String ip;

    @ApiModelProperty(name = "status", value = "登录状态(0=成功,1=失败)", example = "1",dataType="Byte")
    @Min(value = 0,message = "登录状态只能为0或1")
    @Max(value = 1,message = "登录状态只能为0或1")
    private Byte status;

    @ApiModelProperty(name = "startTime", value = "登录开始时间", example = "1567764943000",dataType="Long")
    @Digits(integer = 13,fraction = 0,message = "时间戳必须为13位数字(毫秒)")
    private Long startTime;

    @ApiModelProperty(name = "endTime", value = "登录结束时间", example = "1567764958000",dataType="Long")
    @Digits(integer = 13,fraction = 0,message = "时间戳必须为13位数字(毫秒)")
    private Long endTime;
    

}