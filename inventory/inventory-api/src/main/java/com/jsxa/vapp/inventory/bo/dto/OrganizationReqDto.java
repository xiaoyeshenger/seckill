package com.jsxa.vapp.inventory.bo.dto;


import com.jsxa.vapp.common.bo.dto.BaseDto;
import com.jsxa.vapp.common.valid.ValidationGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.Tolerate;

import javax.validation.constraints.*;
import java.util.List;


/*
 * @Author zhangyong
 * @Description //OrganizationReqDto
 * @Date 2024/02/02 15:01
 * @Param
 * @return
 **/
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationReqDto extends BaseDto {

    @ApiModelProperty(name = "id", value = "主键", example = "xxx",dataType="Long")
    @NotNull(message = "主键不能为空",groups = ValidationGroup.ValidationUpdate.class)
    private Long id;

 

    @ApiModelProperty(name = "addressCode", value = "区域码", example = "xxx",dataType="String")
    @NotNull(message = "区域码不能为空")
    private String addressCode;
 

    @ApiModelProperty(name = "name", value = "名称", example = "xxx",dataType="String")
    @NotNull(message = "名称不能为空")
    private String name;
 

    @ApiModelProperty(name = "contactName", value = "联系人名称", example = "xxx",dataType="String")
    @NotNull(message = "联系人名称不能为空")
    private String contactName;
 

    @ApiModelProperty(name = "contactMobile", value = "联系电话", example = "xxx",dataType="String")
    @NotNull(message = "联系电话不能为空")
    private String contactMobile;
 

    @ApiModelProperty(name = "status", value = "状态(0=停用,1=启用)", example = "xxx",dataType="Byte")
    @NotNull(message = "状态(0=停用,1=启用)不能为空",groups = ValidationGroup.ValidationUpdate.class)
    @Min(value = 0,message = "状态只能为0或1")
    @Max(value = 1,message = "状态只能为0或1")
    private Byte status;
 

    @ApiModelProperty(name = "orderNum", value = "排序号", example = "xxx",dataType="Integer")
    @NotNull(message = "排序号不能为空")
    private Integer orderNum;
 

 

}