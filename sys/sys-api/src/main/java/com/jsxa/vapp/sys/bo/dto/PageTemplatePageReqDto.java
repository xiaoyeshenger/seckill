package com.jsxa.vapp.sys.bo.dto;


import com.jsxa.vapp.common.bo.dto.PageReqDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;



/*
 * @Author zhangyong
 * @Description //PageTemplatePageReqDto
 * @Date 2022/04/26 11:24
 * @Param
 * @return
 **/
@Getter
@Setter
@Accessors(chain=true)
public class PageTemplatePageReqDto extends PageReqDto {

    @ApiModelProperty(name = "name", value = "名称", example = "xxx",dataType="String")
    private String name;
    

    @ApiModelProperty(name = "pageType", value = "页面类型(首页/统计页/事件处理页)", example = "123",dataType="Long")
    private Long pageType;
    

    @ApiModelProperty(name = "pageKey", value = "页面键名称", example = "xxx",dataType="String")
    private String pageKey;
    

    @ApiModelProperty(name = "templateType", value = "模板类型(GeoJson/3D)", example = "556",dataType="Long")
    private Long templateType;


    @ApiModelProperty(name = "activeFlag", value = "启用标志(0=未启用,1=已启用)", example = "1",dataType="Byte")
    private Byte activeFlag;


    @ApiModelProperty(name = "parkId", value = "园区ID", example = "123",dataType="Long")
    @NotNull(message = "园区ID不能为空")
    private Long parkId;
}