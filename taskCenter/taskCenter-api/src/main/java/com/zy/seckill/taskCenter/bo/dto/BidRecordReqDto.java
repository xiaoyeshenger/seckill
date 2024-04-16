package com.zy.seckill.taskCenter.bo.dto;


import com.zy.seckill.common.bo.dto.BaseDto;
import com.zy.seckill.common.valid.ValidationGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


/*
 * @Author zhangyong
 * @Description //BidRecordReqDto
 * @Date 2023/12/18 11:43
 * @Param
 * @return
 **/
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class BidRecordReqDto extends BaseDto {


    @ApiModelProperty(name = "id", value = "主键", example = "xxx",dataType="Long")
    @NotNull(message = "主键不能为空",groups = ValidationGroup.ValidationUpdate.class)
    private Long id;

    @ApiModelProperty(name = "projectId", value = "项目ID", example = "xxx",dataType="Long")
    @NotNull(message = "项目ID不能为空")
    private Long projectId;

    @ApiModelProperty(name = "curUnitPrice", value = "当前出价单价", example = "xxx",dataType="Double")
    @NotNull(message = "当前出价单价不能为空")
    private Double curUnitPrice;

}