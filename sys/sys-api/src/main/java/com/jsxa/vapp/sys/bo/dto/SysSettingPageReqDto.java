package com.jsxa.vapp.sys.bo.dto;


import com.jsxa.vapp.common.bo.dto.PageReqDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;


/*
 * @Author zhangyong
 * @Description //SysSettingPageReqDto
 * @Date 2022/03/09 09:38
 * @Param
 * @return
 **/
@Getter
@Setter
@Accessors(chain=true)
public class SysSettingPageReqDto extends PageReqDto {


    @ApiModelProperty(name = "name", value = "名称", example = "xxx",dataType="String")
    private String name;

    @ApiModelProperty(name = "settingKey", value = "键名称", example = "xxx",dataType="String")
    private String settingKey;

    @ApiModelProperty(name = "type", value = "参数类型(字符串/开关)", example = "123",dataType="Long")
    private Long type;

}