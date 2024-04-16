package com.zy.seckill.codeGen.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 接种地点
 */
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class VaccinationSite implements Serializable {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "区域码(接种点所属社区)")
    private String addressCode;

    @ApiModelProperty(value = "所属单位ID")
    private Long orgId;

    @ApiModelProperty(value = "所属单位名称")
    private String orgName;

    @ApiModelProperty(value = "接种点负责人名称")
    private String contactName;

    @ApiModelProperty(value = "接种点负责人电话")
    private String contactMobile;

    @ApiModelProperty(value = "状态(0=停用,1=启用)")
    private Byte status;

    @ApiModelProperty(value = "排序号")
    private Integer orderNum;

    @ApiModelProperty(value = "创建时间")
    private Long createTime;

}