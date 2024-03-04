package com.jsxa.vapp.common.bo.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author jsxa
 * @since 2022-05-17
 */
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class GridInfo implements Serializable{

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "乡镇编码")
    private String streetCode;

    @ApiModelProperty(value = "乡镇名称")
    private String streetName;

    @ApiModelProperty(value = "村社区编码")
    private String community;

    @ApiModelProperty(value = "村社区名称")
    private String communityName;

    @ApiModelProperty(value = "网格编码")
    private String gridCode;

    @ApiModelProperty(value = "网格名称")
    private String gridName;

    @ApiModelProperty(value = "小区id")
    private String addressCode;

    @ApiModelProperty(value = "小区名称")
    private String addressName;

    @ApiModelProperty(value = "状态(0=停用,1=启用)")
    private Byte status;

    @ApiModelProperty(value = "删除标识")
    private Byte delFlag;

    @ApiModelProperty(value = "创建时间")
    private Long createTime;
}
