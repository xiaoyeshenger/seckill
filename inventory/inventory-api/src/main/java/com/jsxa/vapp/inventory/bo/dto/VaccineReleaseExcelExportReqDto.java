package com.jsxa.vapp.inventory.bo.dto;


import com.jsxa.vapp.common.bo.dto.BaseDto;
import com.jsxa.vapp.common.valid.ValidationGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.Tolerate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.util.List;


/*
 * @Author zhangyong
 * @Description //VaccineReleaseReqDto
 * @Date 2024/02/27 15:03
 * @Param
 * @return
 **/
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class VaccineReleaseExcelExportReqDto extends BaseDto {

    @ApiModelProperty(name = "id", value = "主键", example = "xxx",dataType="Long")
    private Long id;
    

    @ApiModelProperty(name = "name", value = "名称(3月9日第3次放苗)", example = "xxx",dataType="String")
    private String name;
    

    @ApiModelProperty(name = "siteId", value = "接种点ID", example = "xxx",dataType="Long")
    private Long siteId;
    

    @ApiModelProperty(name = "siteName", value = "接种点名称", example = "xxx",dataType="String")
    private String siteName;
    

    @ApiModelProperty(name = "dose", value = "剂次", example = "xxx",dataType="Integer")
    private Integer dose;
    

    @ApiModelProperty(name = "amount", value = "放苗数量", example = "xxx",dataType="Integer")
    private Integer amount;
    

    @ApiModelProperty(name = "startTime", value = "市民抢苗开始时间(小程序能够开始预约)", example = "xxx",dataType="Long")
    private Long startTime;
    

    @ApiModelProperty(name = "timePeriod", value = "接种时间段", example = "xxx",dataType="Long")
    private Long timePeriod;
    

    @ApiModelProperty(name = "timePeriodName", value = "接种时间段名称", example = "xxx",dataType="String")
    private String timePeriodName;
    

    @ApiModelProperty(name = "contactName", value = "接种点负责人名称", example = "xxx",dataType="String")
    private String contactName;
    

    @ApiModelProperty(name = "contactMobile", value = "接种点负责人电话", example = "xxx",dataType="String")
    private String contactMobile;
    

    @ApiModelProperty(name = "status", value = "状态(0=停用,1=发布)", example = "xxx",dataType="Byte")
    private Byte status;
    

    @ApiModelProperty(name = "createTime", value = "创建时间", example = "xxx",dataType="Long")
    private Long createTime;
    

}