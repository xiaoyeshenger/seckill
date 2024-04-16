package com.zy.seckill.sys.bo.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

//系统设置(主要用于全局设置，和单个园区及账户无关，比如整个系统的文件上传路径等)
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class SysSetting implements Serializable {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "键名称")
    private String settingKey;

    @ApiModelProperty(value = "值名称")
    private String settingValue;

    @ApiModelProperty("类型(字符串/开关)")
    private Long type;

    @ApiModelProperty(value = "名称描述")
    private String description;

    @ApiModelProperty(value = "排序号")
    private Integer orderNum;

    @ApiModelProperty(value = "创建时间")
    private Long createTime;

}