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
 * @Description //VaccinationSitePageReqDto
 * @Date 2024/02/27 14:32
 * @Param
 * @return
 **/
@Getter
@Setter
@Accessors(chain=true)
public class VaccinationSitePageReqDto extends PageReqDto {



    @ApiModelProperty(name = "name", value = "名称", example = "xxx",dataType="String")
    private String name;


    @ApiModelProperty(name = "addressCode", value = "区域码(接种点所属社区)", example = "xxx",dataType="String")
    private String addressCode;


    @ApiModelProperty(name = "orgId", value = "所属单位ID", example = "xxx",dataType="Long")
    private Long orgId;


    @ApiModelProperty(name = "orgName", value = "所属单位名称", example = "xxx",dataType="String")
    private String orgName;


    @ApiModelProperty(name = "contactName", value = "接种点负责人名称", example = "xxx",dataType="String")
    private String contactName;


    @ApiModelProperty(name = "contactMobile", value = "接种点负责人电话", example = "xxx",dataType="String")
    private String contactMobile;


    @ApiModelProperty(name = "status", value = "状态(0=停用,1=启用)", example = "xxx",dataType="Byte")
    private Byte status;


    @ApiModelProperty(name = "orderNum", value = "排序号", example = "xxx",dataType="Integer")
    private Integer orderNum;




}