package com.jsxa.vapp.sys.bo.dto;


import com.jsxa.vapp.common.bo.dto.BaseDto;
import com.jsxa.vapp.common.valid.ValidationGroup;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.Tolerate;

import javax.validation.constraints.NotNull;

import java.util.List;


/*
 * @Author zhangyong
 * @Description //PageTemplateReqDto
 * @Date 2022/04/26 11:24
 * @Param
 * @return
 **/
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class PageTemplateReqDto extends BaseDto {

    @ApiModelProperty(name = "id", value = "主键", example = "1233",dataType="Long")
    @NotNull(message = "主键不能为空",groups = ValidationGroup.ValidationUpdate.class)
    private Long id;

    @ApiModelProperty(name = "name", value = "名称", example = "xxx",dataType="String")
    @NotNull(message = "名称不能为空")
    private String name;
    

    @ApiModelProperty(name = "pageType", value = "页面类型(首页/统计页/事件处理页)", example = "123",dataType="Long")
    @NotNull(message = "页面类型(首页/统计页/事件处理页)不能为空")
    private Long pageType;
    

    @ApiModelProperty(name = "pageKey", value = "页面键名称", example = "xxx",dataType="String")
    @NotNull(message = "页面键名称不能为空")
    private String pageKey;
    

    @ApiModelProperty(name = "templateType", value = "模板类型(GeoJson/3D)", example = "556",dataType="Long")
    @NotNull(message = "模板类型(GeoJson/3D)不能为空")
    private Long templateType;
    

    @ApiModelProperty(name = "description", value = "名称描述", example = "xxx",dataType="String")
    @NotNull(message = "名称描述不能为空")
    private String description;
    

    @ApiModelProperty(name = "orderNum", value = "排序号", example = "1",dataType="Integer")
    @NotNull(message = "排序号不能为空")
    private Integer orderNum;


    @ApiModelProperty(name = "path", value = "跳转路径", example = "xxx",dataType="String")
    @NotNull(message = "跳转路径不能为空")
    private String path;


    @ApiModelProperty(name = "parkId", value = "园区ID", example = "123",dataType="Long")
    @NotNull(message = "园区ID不能为空")
    private Long parkId;
}