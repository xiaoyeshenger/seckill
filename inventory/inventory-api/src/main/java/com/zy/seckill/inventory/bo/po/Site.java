package com.zy.seckill.inventory.bo.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 商铺
 */
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class Site implements Serializable {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "区域码")
    private String addressCode;

    @ApiModelProperty(value = "所属商家ID")
    private Long orgId;

    @ApiModelProperty(value = "所属商家名称")
    private String orgName;

    @ApiModelProperty(value = "商铺负责人名称")
    private String contactName;

    @ApiModelProperty(value = "商铺负责人电话")
    private String contactMobile;

    @ApiModelProperty(value = "状态(0=停用,1=启用)")
    private Byte status;

    @ApiModelProperty(value = "排序号")
    private Integer orderNum;

    @ApiModelProperty(value = "创建时间")
    private Long createTime;

}