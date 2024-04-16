package com.zy.seckill.taskCenter.bo.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 竞价记录
 */
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class BidRecord implements Serializable {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "项目ID")
    private Long projectId;

    @ApiModelProperty(value = "名称名称")
    private String projectName;

    @ApiModelProperty(value = "项目数量")
    private Double projectQuantity;

    @ApiModelProperty(value = "当前出价单价")
    private Double curUnitPrice;

    @ApiModelProperty(value = "当前出价总价")
    private Double curTotalPrice;

    @ApiModelProperty(value = "出价人ID")
    private Long userId;

    @ApiModelProperty(value = "出价人名称(公司)")
    private String orgName;

    @ApiModelProperty(value = "出价人联系电话")
    private String userMobile;

    @ApiModelProperty(value = "出价时间")
    private Long bidTime;

    @ApiModelProperty(value = "拍卖状态(领先/出局)")
    private Long bidStatus;

    @ApiModelProperty(value = "排序号")
    private Integer orderNum;

    @ApiModelProperty(value = "创建时间")
    private Long createTime;
}