package com.jsxa.vapp.sys.bo.dto;


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
 * @Description //NoticePageReqDto
 * @Date 2022/02/28 14:46
 * @Param
 * @return
 **/
@Getter
@Setter
@Accessors(chain=true)
public class NoticePageReqDto extends PageReqDto {

    @ApiModelProperty(name = "regionCode", value = "区域编码", example = "123",dataType="Long")
    private String regionCode;

    @ApiModelProperty(name = "title", value = "标题", example = "xxx",dataType="String")
    private String title;

    @ApiModelProperty(name = "creatorName", value = "创建者姓名", example = "xxx",dataType="String")
    private String creatorName;

    @ApiModelProperty(name = "type", value = "类型", example = "123",dataType="Long")
    private Long type;

    @ApiModelProperty(name = "status", value = "状态(0=停用,1=启用)", example = "1",dataType="Byte")
    @NotNull(message = "状态(0=停用,1=启用)不能为空")
    private Byte status;

    @ApiModelProperty(name = "startTime", value = "创建开始时间", example = "1567764943000",dataType="Long")
    @Digits(integer = 13,fraction = 0,message = "时间戳必须为13位数字(毫秒)")
    private Long startTime;

    @ApiModelProperty(name = "endTime", value = "创建结束时间", example = "1567764958000",dataType="Long")
    @Digits(integer = 13,fraction = 0,message = "时间戳必须为13位数字(毫秒)")
    private Long endTime;

}