package com.zy.seckill.sys.bo.dto;


import com.zy.seckill.common.bo.dto.BaseDto;
import com.zy.seckill.common.bo.dto.PageReqDto;
import com.zy.seckill.common.valid.ValidationGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.Tolerate;

import javax.validation.constraints.*;

import java.util.List;


/*
 * @Author zhangyong
 * @Description //DeptPageReqDto
 * @Date 2022/02/21 15:06
 * @Param
 * @return
 **/
@Getter
@Setter
@Accessors(chain=true)
public class DeptPageReqDto extends PageReqDto {

    @ApiModelProperty(name = "orgId", value = "组织ID", example = "123695463251",dataType="Long")
    @NotNull(message = "组织ID不能为空")
    private Long orgId;

    @ApiModelProperty(name = "name", value = "部门名称", example = "运营二部",dataType="String")
    private String name;

    @ApiModelProperty(name = "status", value = "状态(0=停用,1=正常)", example = "1",dataType="Byte")
    private Byte status;

    @ApiModelProperty(name = "startTime", value = "创建开始时间", example = "1567764943000",dataType="Long")
    @Digits(integer = 13,fraction = 0,message = "时间戳必须为13位数字(毫秒)")
    private Long startTime;

    @ApiModelProperty(name = "endTime", value = "创建结束时间", example = "1567764958000",dataType="Long")
    @Digits(integer = 13,fraction = 0,message = "时间戳必须为13位数字(毫秒)")
    private Long endTime;

}