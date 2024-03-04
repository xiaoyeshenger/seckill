package com.jsxa.vapp.sys.bo.dto;


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
 * @Description //OrganizationReqDto
 * @Date 2023/12/27 08:50
 * @Param
 * @return
 **/
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationExcelExportReqDto extends BaseDto {

    @ApiModelProperty(name = "id", value = "主键", example = "xxx",dataType="Long")
    private Long id;
    

    @ApiModelProperty(name = "code", value = "编码", example = "xxx",dataType="String")
    private String code;
    

    @ApiModelProperty(name = "name", value = "名称", example = "xxx",dataType="String")
    private String name;
    

    @ApiModelProperty(name = "contactName", value = "联系人名称", example = "xxx",dataType="String")
    private String contactName;
    

    @ApiModelProperty(name = "contactMobile", value = "联系电话", example = "xxx",dataType="String")
    private String contactMobile;
    

    @ApiModelProperty(name = "status", value = "状态(0=停用,1=启用)", example = "xxx",dataType="Byte")
    private Byte status;
    

    @ApiModelProperty(name = "createTime", value = "创建时间", example = "xxx",dataType="Long")
    private Long createTime;
    

}