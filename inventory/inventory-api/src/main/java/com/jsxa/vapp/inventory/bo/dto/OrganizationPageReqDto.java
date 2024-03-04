package com.jsxa.vapp.inventory.bo.dto;


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
 * @Description //OrganizationPageReqDto
 * @Date 2024/02/02 15:01
 * @Param
 * @return
 **/
@Getter
@Setter
@Accessors(chain=true)
public class OrganizationPageReqDto extends PageReqDto {



    @ApiModelProperty(name = "addressCode", value = "区域码", example = "xxx",dataType="String")
    private String addressCode;


    @ApiModelProperty(name = "name", value = "名称", example = "xxx",dataType="String")
    private String name;


    @ApiModelProperty(name = "contactName", value = "联系人名称", example = "xxx",dataType="String")
    private String contactName;


    @ApiModelProperty(name = "contactMobile", value = "联系电话", example = "xxx",dataType="String")
    private String contactMobile;


    @ApiModelProperty(name = "status", value = "状态(0=停用,1=启用)", example = "xxx",dataType="Byte")
    private Byte status;


    @ApiModelProperty(name = "orderNum", value = "排序号", example = "xxx",dataType="Integer")
    private Integer orderNum;




}