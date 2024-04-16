package com.zy.seckill.inventory.bo.dto;


import com.zy.seckill.common.bo.dto.BaseDto;
import com.zy.seckill.common.valid.ValidationGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.*;


/*
 * @Author zhangyong
 * @Description //VaccineReleaseReqDto
 * @Date xxxx/02/27 15:03
 * @Param
 * @return
 **/
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class ProductReleaseReqDto extends BaseDto {

    @ApiModelProperty(name = "id", value = "主键", example = "xxx",dataType="Long")
    @NotNull(message = "主键不能为空",groups = ValidationGroup.ValidationUpdate.class)
    private Long id;

 

    @ApiModelProperty(name = "name", value = "名称(3月9日第3次放苗)", example = "xxx",dataType="String")
    @NotNull(message = "名称(3月9日第3次放苗)不能为空")
    private String name;
 

    @ApiModelProperty(name = "siteId", value = "接种点ID", example = "xxx",dataType="Long")
    @NotNull(message = "接种点ID不能为空")
    private Long siteId;
 

    @ApiModelProperty(name = "siteName", value = "接种点名称", example = "xxx",dataType="String")
    @NotNull(message = "接种点名称不能为空")
    private String siteName;
 

    @ApiModelProperty(name = "dose", value = "剂次", example = "xxx",dataType="Integer")
    @NotNull(message = "剂次不能为空")
    private Integer dose;
 

    @ApiModelProperty(name = "amount", value = "放苗数量", example = "xxx",dataType="Integer")
    @NotNull(message = "放苗数量不能为空")
    private Integer amount;
 

    @ApiModelProperty(name = "startTime", value = "市民抢苗开始时间(小程序能够开始预约)", example = "xxx",dataType="Long")
    @NotNull(message = "市民抢苗开始时间(小程序能够开始预约)不能为空")
    @Digits(integer = 13,fraction = 0,message = "时间戳必须为13位数字(毫秒)")
    private Long startTime;
 

    @ApiModelProperty(name = "timePeriod", value = "接种时间段", example = "xxx",dataType="Long")
    @NotNull(message = "接种时间段不能为空")
    private Long timePeriod;
 

    @ApiModelProperty(name = "timePeriodName", value = "接种时间段名称", example = "xxx",dataType="String")
    @NotNull(message = "接种时间段名称不能为空")
    private String timePeriodName;
 

    @ApiModelProperty(name = "contactName", value = "接种点负责人名称", example = "xxx",dataType="String")
    @NotNull(message = "接种点负责人名称不能为空")
    private String contactName;
 

    @ApiModelProperty(name = "contactMobile", value = "接种点负责人电话", example = "xxx",dataType="String")
    @NotNull(message = "接种点负责人电话不能为空")
    private String contactMobile;
 

    @ApiModelProperty(name = "status", value = "状态(0=停用,1=发布)", example = "xxx",dataType="Byte")
    @NotNull(message = "状态(0=停用,1=发布)不能为空",groups = ValidationGroup.ValidationUpdate.class)
    @Min(value = 0,message = "状态只能为0或1")
    @Max(value = 1,message = "状态只能为0或1")
    private Byte status;
 

 

}