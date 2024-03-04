package com.jsxa.vapp.sys.bo.dto;


import com.jsxa.vapp.common.bo.dto.BaseDto;
import com.jsxa.vapp.common.bo.dto.PageReqDto;
import com.jsxa.vapp.common.valid.ValidationGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.Tolerate;

import javax.validation.constraints.*;

import java.util.List;


/*
 * @Author zhangyong
 * @Description //PostPageReqDto
 * @Date 2022/02/21 15:03
 * @Param
 * @return
 **/
@Getter
@Setter
@Accessors(chain=true)
public class PostPageReqDto extends PageReqDto  {

    @ApiModelProperty(name = "parkId", value = "园区ID", example = "123695463251",dataType="Long")
    @NotNull(message = "parkId不能为空")
    private Long parkId;

    @ApiModelProperty(name = "code", value = "职位编码", example = "ceo",dataType="String")
    private String code;

    @ApiModelProperty(name = "name", value = "职位名称", example = "总经理",dataType="String")
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