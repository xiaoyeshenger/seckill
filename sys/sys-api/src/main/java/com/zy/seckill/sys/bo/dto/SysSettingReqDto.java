package com.zy.seckill.sys.bo.dto;


import com.zy.seckill.common.bo.dto.BaseDto;
import com.zy.seckill.common.valid.ValidationGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.Tolerate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.util.List;


/*
 * @Author zhangyong
 * @Description //SysSettingReqDto
 * @Date 2022/03/09 09:38
 * @Param
 * @return
 **/
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class SysSettingReqDto extends BaseDto {

    @ApiModelProperty(name = "id", value = "主键", example = "123",dataType="Long")
    @NotNull(message = "主键不能为空",groups = ValidationGroup.ValidationUpdate.class)
    private Long id;


    @ApiModelProperty(name = "name", value = "名称", example = "xxx",dataType="String")
    @NotNull(message = "名称不能为空")
    private String name;
    

    @ApiModelProperty(name = "settingKey", value = "键名称", example = "xxx",dataType="String")
    @NotNull(message = "键名称不能为空")
    private String settingKey;
    

    @ApiModelProperty(name = "settingValue", value = "值名称", example = "xxx",dataType="String")
    @NotNull(message = "值名称不能为空")
    private String settingValue;
    

    @ApiModelProperty(name = "type", value = "类型(字符串/开关)", example = "123",dataType="Long")
    @NotNull(message = "类型(字符串/开关)不能为空")
    private Long type;
    

    @ApiModelProperty(name = "description", value = "名称描述", example = "xxx",dataType="String")
    @NotNull(message = "名称描述不能为空")
    private String description;
    

    @ApiModelProperty(name = "orderNum", value = "排序号", example = "123",dataType="Integer")
    @NotNull(message = "排序号不能为空")
    private Integer orderNum;
}