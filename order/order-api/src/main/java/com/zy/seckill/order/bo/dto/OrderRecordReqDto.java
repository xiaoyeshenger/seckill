package com.zy.seckill.order.bo.dto;


import com.zy.seckill.common.bo.dto.BaseDto;
import com.zy.seckill.common.valid.ValidationGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.*;


/**
 * @Author zhangyong
 * @Description //OrderRecordReqDto
 * @Date xxxx/02/27 15:20
 * @Param
 * @return
 */
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class OrderRecordReqDto extends BaseDto {

    @ApiModelProperty(name = "id", value = "主键", example = "xxx",dataType="Long")
    @NotNull(message = "主键不能为空",groups = ValidationGroup.ValidationUpdate.class)
    private Long id;

    @ApiModelProperty(name = "recordType", value = "记录产生来源(系统产生,电脑补录,小程序端自行补录)", example = "xxx",dataType="Long")
    @NotNull(message = "记录产生来源(系统产生,电脑补录,小程序端自行补录)不能为空")
    private Long recordType;

    @ApiModelProperty(name = "openId", value = "微信小程序openId", example = "xxx",dataType="String")
    @NotNull(message = "微信小程序openId不能为空")
    private String openId;

    @ApiModelProperty(name = "productReleaseId", value = "商品秒杀活动id", example = "xxx",dataType="Long")
    @NotNull(message = "商品秒杀活动id不能为空")
    private Long productReleaseId;
}