package com.jsxa.vapp.sys.bo.dto;


import com.jsxa.vapp.common.bo.dto.PageReqDto;
import com.jsxa.vapp.common.valid.ValidationGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.Tolerate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.util.List;


/*
 * @Author zhangyong
 * @Description //ParamSettingPageReqDto
 * @Date 2022/03/22 14:08
 * @Param
 * @return
 **/
@Getter
@Setter
@Builder
@Accessors(chain=true)
public class ParamSettingPageReqDto extends PageReqDto {

    @ApiModelProperty(name = "parkId", value = "园区ID", example = "123",dataType="Long")
    @NotNull(message = "园区ID不能为空")
    private Long parkId;

    @ApiModelProperty(name = "name", value = "名称", example = "xxx",dataType="String")
    private String name;

    @ApiModelProperty(name = "paramKey", value = "键名称", example = "xxx",dataType="String")
    private String paramKey;

    @ApiModelProperty(name = "type", value = "参数类型(字符串/开关)", example = "123",dataType="Long")
    private Long type;

}