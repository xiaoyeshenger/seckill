package com.zy.seckill.order.bo.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 订单记录
 */
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class OrderRecord implements Serializable {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "记录产生来源(系统产生,电脑补录,小程序端自行补录)")
    private Long recordType;

    @ApiModelProperty(value = "产品抢购(活动)id")
    private Long productReleaseId;

    @ApiModelProperty(value = "产品抢购(活动)名称")
    private String productReleaseName;

    @ApiModelProperty(value = "顾客id")
    private Long personId;

    @ApiModelProperty(value = "顾客姓名")
    private String personName;

    @ApiModelProperty(value = "性别")
    private Byte sex;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "联系电话")
    private String mobile;

    @ApiModelProperty(value = "微信小程序openId")
    private String openId;

    @ApiModelProperty(value = "商铺id")
    private Long siteId;

    @ApiModelProperty(value = "商铺名称")
    private String siteName;

    @ApiModelProperty(value = "订单状态(待支付,已支付,退款中,已完成,已取消)")
    private Long recordStatus;

    @ApiModelProperty(value = "产品id")
    private Long productId;

    @ApiModelProperty(value = "产品名称")
    private String productName;

    @ApiModelProperty(value = "疫苗批次")
    private String productBatch;

    @ApiModelProperty(value = "疫苗生产厂家")
    private String manufacturer;

    @ApiModelProperty(value = "抢购成功时间")
    private Long appointmentTime;

    @ApiModelProperty(value = "产品图片")
    private String imageUrl;

    @ApiModelProperty(value = "其他原因")
    private String msg;

    @ApiModelProperty(value = "创建时间")
    private Long createTime;

}