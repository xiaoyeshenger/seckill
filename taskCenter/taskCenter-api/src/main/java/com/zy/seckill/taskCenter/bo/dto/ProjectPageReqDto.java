package com.zy.seckill.taskCenter.bo.dto;


import com.zy.seckill.common.bo.dto.PageReqDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.Digits;


/*
 * @Author zhangyong
 * @Description //ProjectPageReqDto
 * @Date 2023/12/18 11:14
 * @Param
 * @return
 **/
@Getter
@Setter
@Accessors(chain=true)
public class ProjectPageReqDto extends PageReqDto {


    @ApiModelProperty(name = "name", value = "名称", example = "xxx",dataType="String")
    private String name;

    @ApiModelProperty(name = "startTime", value = "拍卖开始时间", example = "1567764958000",dataType="Long")
    @Digits(integer = 13,fraction = 0,message = "时间戳必须为13位数字(毫秒)")
    private Long startTime;

    @ApiModelProperty(name = "endTime", value = "拍卖结束时间", example = "1567764958000",dataType="Long")
    @Digits(integer = 13,fraction = 0,message = "时间戳必须为13位数字(毫秒)")
    private Long endTime;

    @ApiModelProperty(name = "auctionStatus", value = "拍卖状态", example = "xxx",dataType="Long")
    private Long auctionStatus;

    @ApiModelProperty(name = "transStatus", value = "交易状态(成交/流拍)", example = "xxx",dataType="Long")
    private Long transStatus;
}