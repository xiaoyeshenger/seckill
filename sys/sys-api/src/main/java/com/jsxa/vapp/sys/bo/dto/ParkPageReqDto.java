package com.jsxa.vapp.sys.bo.dto;


import com.jsxa.vapp.common.bo.dto.BaseDto;
import com.jsxa.vapp.common.bo.dto.PageReqDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


/*
 * @Author zhangyong
 * @Description //ParkPageReqDto
 * @Date 2022/02/09 17:31
 * @Param
 * @return
 **/
@Getter
@Setter
@Accessors(chain=true)
public class ParkPageReqDto extends PageReqDto {

    @ApiModelProperty(name = "parkId", value = "园区ID", example = "11451758690780233",dataType="Long")
    @NotNull(message = "园区ID不能为空")
    private Long parkId;

    @ApiModelProperty(name = "name", value = "园区名称", example = "云钢联",dataType="String")
    private String name;

    @ApiModelProperty(name = "type", value = "企业类型", example = "631",dataType="Long")
    private Long type;

    @ApiModelProperty(name = "parkType", value = "园区类型", example = "132",dataType="Long")
    private Long parkType;

    @ApiModelProperty(name = "principalName", value = "园区负责人姓名", example = "王五",dataType="String")
    private String principalName;

    @ApiModelProperty(name = "status", value = "状态(0=停用,1=正常)", example = "1",dataType="Byte")
    private Byte status;

    @ApiModelProperty(name = "startTime", value = "创建开始时间", example = "1567764943000",dataType="Long")
    @Digits(integer = 13,fraction = 0,message = "时间戳必须为13位数字(毫秒)")
    private Long startTime;

    @ApiModelProperty(name = "endTime", value = "创建结束时间", example = "1567764958000",dataType="Long")
    @Digits(integer = 13,fraction = 0,message = "时间戳必须为13位数字(毫秒)")
    private Long endTime;


}