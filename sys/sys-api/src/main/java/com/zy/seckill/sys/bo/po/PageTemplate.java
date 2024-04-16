package com.zy.seckill.sys.bo.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

//页面模板
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class PageTemplate implements Serializable {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "页面使用类型(首页/统计页/事件处理页)")
    private Long pageType;

    @ApiModelProperty(value = "页面键名称")
    private String pageKey;

    @ApiModelProperty("模板类型(GeoJson/3D)")
    private Long templateType;

    @ApiModelProperty(value = "名称描述")
    private String description;

    @ApiModelProperty(value = "图片")
    private String picUrl;

    @ApiModelProperty(value = "geoJson文件地址")
    private String geoJsonUrl;

    @ApiModelProperty(value = "排序号")
    private Integer orderNum;

    @ApiModelProperty(value = "启用标志(0=未启用,1=已启用)")
    private Byte activeFlag;

    @ApiModelProperty(value = "园区ID")
    private Long parkId;

    @ApiModelProperty(value = "页面跳转路径")
    private String path;

    @ApiModelProperty(value = "创建时间")
    private Long createTime;

}