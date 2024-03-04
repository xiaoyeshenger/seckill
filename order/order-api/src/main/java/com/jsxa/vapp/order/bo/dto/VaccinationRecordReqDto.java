package com.jsxa.vapp.order.bo.dto;


import com.jsxa.vapp.common.bo.dto.BaseDto;
import com.jsxa.vapp.common.valid.ValidationGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.Tolerate;

import javax.validation.constraints.*;
import java.util.List;


/*
 * @Author zhangyong
 * @Description //VaccinationRecordReqDto
 * @Date 2024/02/27 15:20
 * @Param
 * @return
 **/
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class VaccinationRecordReqDto extends BaseDto {

    @ApiModelProperty(name = "id", value = "主键", example = "xxx",dataType="Long")
    @NotNull(message = "主键不能为空",groups = ValidationGroup.ValidationUpdate.class)
    private Long id;

    @ApiModelProperty(name = "recordType", value = "记录产生来源(系统产生,电脑补录,小程序端自行补录)", example = "xxx",dataType="Long")
    @NotNull(message = "记录产生来源(系统产生,电脑补录,小程序端自行补录)不能为空")
    private Long recordType;

    @ApiModelProperty(name = "openId", value = "微信小程序openId", example = "xxx",dataType="String")
    @NotNull(message = "微信小程序openId不能为空")
    private String openId;

    @ApiModelProperty(name = "vaccineReleaseId", value = "疫苗发放(活动)id", example = "xxx",dataType="Long")
    @NotNull(message = "疫苗发放(活动)id不能为空")
    private Long vaccineReleaseId;
}