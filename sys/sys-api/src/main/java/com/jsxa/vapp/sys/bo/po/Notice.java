package com.jsxa.vapp.sys.bo.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

//通知
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class Notice implements Serializable {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "类型")
    private Long type;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "创建者姓名")
    private String creatorName;

    @ApiModelProperty(value = "创建者电话")
    private String creatorMobile;

    @ApiModelProperty(value = "排序号")
    private Integer orderNum;

    @ApiModelProperty(value = "删除标识")
    private Byte delFlag;

    @ApiModelProperty(value = "状态(0=停用,1=启用)")
    private Byte status;

    @ApiModelProperty(value = "园区ID")
    private Long parkId;

    @ApiModelProperty(value = "创建时间")
    private Long createTime;

}