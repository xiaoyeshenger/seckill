package com.zy.seckill.inventory.bo.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * 秒杀活动
 */
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class ProductRelease implements Serializable {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "名称(3月9日第3次放苗)")
    private String name;

    @ApiModelProperty(value = "商品ID")
    private Long siteId;

    @ApiModelProperty(value = "商品名称")
    private String siteName;

    @ApiModelProperty(value = "次数")
    private Integer dose;

    @ApiModelProperty(value = "放苗数量")
    private Integer amount;

    @ApiModelProperty(value = "库存数量")
    private Integer dockAmount;

    @ApiModelProperty(value = "库存约满时间(即库存为0的时间)")
    private Long useupTime;

    @ApiModelProperty(value = "操作版本号，使用乐观锁的方式扣减mysql库存")
    private Integer version;

    @ApiModelProperty(value = "秒杀开始时间(小程序能够开始秒杀)")
    private Long startTime;

    @ApiModelProperty(value = "产品id")
    private Long productId;

    @ApiModelProperty(value = "产品名称")
    private String productName;

    @ApiModelProperty(value = "疫苗批次")
    private String productBatch;

    @ApiModelProperty(value = "疫苗生产厂家")
    private String manufacturer;

    @ApiModelProperty(value = "商铺负责人名称")
    private String contactName;

    @ApiModelProperty(value = "商铺负责人电话")
    private String contactMobile;

    @ApiModelProperty(value = "状态(0=停用,1=发布,2=已抢完)")
    private Byte status;

    @ApiModelProperty(value = "创建时间")
    private Long createTime;

}