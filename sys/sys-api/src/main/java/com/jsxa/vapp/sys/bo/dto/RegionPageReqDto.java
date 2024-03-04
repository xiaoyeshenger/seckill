package com.jsxa.vapp.sys.bo.dto;


import com.jsxa.vapp.common.bo.dto.BaseDto;
import com.jsxa.vapp.common.bo.dto.PageReqDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


/*
 * @Author zhangyong
 * @Description //RegionPageReqDto
 * @Date 2021/09/16 16:43
 * @Param
 * @return
 **/
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class RegionPageReqDto extends PageReqDto {

    @ApiModelProperty(name = "code", value = "区域码", example = "510113103",dataType="String")
    private String code;

    @ApiModelProperty(name = "type", value = "区域类型", example = "406",dataType="String")
    private Long type;
}