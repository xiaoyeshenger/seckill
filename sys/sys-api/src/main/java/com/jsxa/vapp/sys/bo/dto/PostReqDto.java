package com.jsxa.vapp.sys.bo.dto;


import com.jsxa.vapp.common.bo.dto.BaseDto;
import com.jsxa.vapp.common.bo.dto.PageReqDto;
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
 * @Description //PostReqDto
 * @Date 2022/02/21 15:03
 * @Param
 * @return
 **/
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class PostReqDto extends BaseDto {

    @ApiModelProperty(name = "id", value = "id", example = "123",dataType="Long")
    @NotNull(message = "id 不能为空",groups = ValidationGroup.ValidationUpdate.class)
    private Long id;

    @ApiModelProperty(name = "parkId", value = "parkId", example = "123",dataType="Long")
    private Long parkId;

    @ApiModelProperty(name = "name", value = "职位名称", example = "总经理",dataType="String")
    @NotNull(message = "name不能为空")
    private String name;

    @ApiModelProperty(name = "code", value = "职位编码", example = "ceo",dataType="String")
    @NotNull(message = "职位编码不能为空")
    private String code;

    @ApiModelProperty(name = "orderNum", value = "排序号", example = "3",dataType="String")
    @NotNull(message = "排序号不能为空")
    private Integer orderNum;

    @ApiModelProperty(name = "status", value = "状态(0=停用,1=正常)", example = "1",dataType="Byte")
    @NotNull(message = "status不能为空")
    private Byte status;

}