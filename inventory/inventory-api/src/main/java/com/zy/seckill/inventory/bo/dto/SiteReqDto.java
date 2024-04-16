package com.zy.seckill.inventory.bo.dto;


import com.zy.seckill.common.bo.dto.BaseDto;
import com.zy.seckill.common.valid.ValidationGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.*;


/*
 * @Author zhangyong
 * @Description //VaccinationSiteReqDto
 * @Date xxxx/02/27 14:32
 * @Param
 * @return
 **/
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class SiteReqDto extends BaseDto {

    @ApiModelProperty(name = "id", value = "主键", example = "xxx",dataType="Long")
    @NotNull(message = "主键不能为空",groups = ValidationGroup.ValidationUpdate.class)
    private Long id;

 

    @ApiModelProperty(name = "name", value = "名称", example = "xxx",dataType="String")
    @NotNull(message = "名称不能为空")
    private String name;
 

    @ApiModelProperty(name = "addressCode", value = "区域码(接种点所属社区)", example = "xxx",dataType="String")
    @NotNull(message = "区域码(接种点所属社区)不能为空")
    private String addressCode;
 

    @ApiModelProperty(name = "orgId", value = "所属单位ID", example = "xxx",dataType="Long")
    @NotNull(message = "所属单位ID不能为空")
    private Long orgId;
 

    @ApiModelProperty(name = "orgName", value = "所属单位名称", example = "xxx",dataType="String")
    @NotNull(message = "所属单位名称不能为空")
    private String orgName;
 

    @ApiModelProperty(name = "contactName", value = "接种点负责人名称", example = "xxx",dataType="String")
    @NotNull(message = "接种点负责人名称不能为空")
    private String contactName;
 

    @ApiModelProperty(name = "contactMobile", value = "接种点负责人电话", example = "xxx",dataType="String")
    @NotNull(message = "接种点负责人电话不能为空")
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