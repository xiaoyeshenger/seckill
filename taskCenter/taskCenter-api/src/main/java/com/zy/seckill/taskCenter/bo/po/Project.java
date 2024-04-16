package com.zy.seckill.taskCenter.bo.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 项目
 */
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class Project implements Serializable {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "拍卖开始时间")
    private Long startTime;

    @ApiModelProperty(value = "拍卖结束时间")
    private Long endTime;

    @ApiModelProperty(value = "数量单位")
    private String quantityUnit;

    @ApiModelProperty(value = "数量")
    private Double quantity;

    @ApiModelProperty(value = "起拍价格")
    private Double upsetPrice;

    @ApiModelProperty(value = "加价幅度")
    private Double bidIncr;

    @ApiModelProperty(value = "加价最长时间")
    private Integer bidIncrMaxTime;

    @ApiModelProperty(value = "文件(列表)url")
    private String oosUrl;

    /**
     *上线/下线/即将开始/交易中/公式中/已结束
     */
    @ApiModelProperty(value = "拍卖状态")
    private Long auctionStatus;

    @ApiModelProperty(value = "项目详细信息")
    private String projectDetails;

    @ApiModelProperty(value = "竞拍须知")
    private String bidNotes;

    @ApiModelProperty(value = "竞拍规则")
    private String bidRule;

    @ApiModelProperty(value = "联系人")
    private String contact;

    @ApiModelProperty(value = "联系人手机号")
    private String contactMobile;

    @ApiModelProperty(value = "交易状态(成交/流拍)")
    private Long transStatus;

    @ApiModelProperty(value = "成交单价")
    private Double transUnitPrice;

    @ApiModelProperty(value = "成交总价")
    private Double transTotalPrice;

    @ApiModelProperty(value = "交易成功后的出价记录ID")
    private Long bidRecordId;

    @ApiModelProperty(value = "排序号")
    private Integer orderNum;

    @ApiModelProperty(value = "创建时间")
    private Long createTime;
}