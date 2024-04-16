package com.zy.seckill.taskCenter.bo.dto;


import com.zy.seckill.common.bo.dto.PageReqDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.Digits;


/*
 * @Author zhangyong
 * @Description //BidRecordPageReqDto
 * @Date 2023/12/18 11:43
 * @Param
 * @return
 **/
@Getter
@Setter
@Accessors(chain=true)
public class BidRecordPageReqDto extends PageReqDto {



    @ApiModelProperty(name = "projectId", value = "项目ID", example = "xxx",dataType="Long")
    private Long projectId;


    @ApiModelProperty(name = "projectName", value = "名称名称", example = "xxx",dataType="String")
    private String projectName;


    @ApiModelProperty(name = "projectQuantity", value = "项目数量", example = "xxx",dataType="Double")
    private Double projectQuantity;


    @ApiModelProperty(name = "curUnitPrice", value = "当前出价单价", example = "xxx",dataType="Double")
    private Double curUnitPrice;


    @ApiModelProperty(name = "curTotalPrice", value = "当前出价总价", example = "xxx",dataType="Double")
    private Double curTotalPrice;


    @ApiModelProperty(name = "userId", value = "出价人ID", example = "xxx",dataType="Long")
    private Long userId;


    @ApiModelProperty(name = "orgName", value = "出价人名称(公司)", example = "xxx",dataType="String")
    private String orgName;


    @ApiModelProperty(name = "userMobile", value = "出价人联系电话", example = "xxx",dataType="String")
    private String userMobile;


    @ApiModelProperty(name = "startBidTime", value = "出价时间开始时间", example = "1567764943000",dataType="Long")
    @Digits(integer = 13,fraction = 0,message = "时间戳必须为13位数字(毫秒)")
    private Long startBidTime;

    @ApiModelProperty(name = "endBidTime", value = "出价时间结束时间", example = "1567764958000",dataType="Long")
    @Digits(integer = 13,fraction = 0,message = "时间戳必须为13位数字(毫秒)")
    private Long endBidTime;



    @ApiModelProperty(name = "bidStatus", value = "拍卖状态(领先/出局)", example = "xxx",dataType="Long")
    private Long bidStatus;


    @ApiModelProperty(name = "orderNum", value = "排序号", example = "xxx",dataType="Integer")
    private Integer orderNum;




}