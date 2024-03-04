package com.jsxa.vapp.sys.bo.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

//部门
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class Dept implements Serializable {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "父级ID")
    private Long parentId;

    @ApiModelProperty(value = "父级名称")
    private String parentName;

    @ApiModelProperty(value = "祖级列表(列表以逗号分隔的ID字符串存于数据库)")
    private String ancestors;

    @ApiModelProperty(value = "排序号")
    private Integer orderNum;

    @ApiModelProperty(value = "状态(0=停用,1=启用)")
    private Byte status;

    @ApiModelProperty(value = "删除标识")
    private Byte delFlag;

    @ApiModelProperty(value = "负责人名称")
    private String principalName;

    @ApiModelProperty(value = "负责人手机号")
    private String principalMobile;

    @ApiModelProperty(value = "组织ID")
    private Long orgId;

    @ApiModelProperty(value = "组织名称")
    private String orgName;

    @ApiModelProperty(value = "创建时间")
    private Long createTime;
}