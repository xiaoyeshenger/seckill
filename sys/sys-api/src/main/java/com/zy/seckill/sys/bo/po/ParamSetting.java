package com.zy.seckill.sys.bo.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

//参数设置(主要用于某个园区及账户的个性化设置，比如给某个园区设置首页的颜色)
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class ParamSetting implements Serializable {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "键名称")
    private String paramKey;

    @ApiModelProperty(value = "值名称")
    private String paramValue;

    @ApiModelProperty("类型(字符串/开关)")
    private Long type;

    @ApiModelProperty(value = "名称描述")
    private String description;

    @ApiModelProperty(value = "排序号")
    private Integer orderNum;

    @ApiModelProperty(value = "园区ID")
    private Long parkId;

    @ApiModelProperty(value = "创建时间")
    private Long createTime;

}