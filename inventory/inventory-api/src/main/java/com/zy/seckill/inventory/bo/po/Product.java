package com.zy.seckill.inventory.bo.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 商品
 */
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "所属单位ID")
    private Long orgId;

    @ApiModelProperty(value = "所属单位名称")
    private String orgName;

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "厂家名称")
    private String manufacturer;

    @ApiModelProperty(value = "生产批号")
    private String batchNumber;

    @ApiModelProperty(value = "库存数")
    private Integer stock;

    @ApiModelProperty(value = "商品图片(文件列表以逗号分隔)url")
    private String oosUrl;

    @ApiModelProperty(value = "排序号")
    private Integer orderNum;

    @ApiModelProperty(value = "状态(0=停用,1=启用)")
    private Byte status;

    @ApiModelProperty(value = "创建时间")
    private Long createTime;

}