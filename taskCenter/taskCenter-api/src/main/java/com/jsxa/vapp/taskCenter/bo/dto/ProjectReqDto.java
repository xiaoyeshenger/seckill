package com.jsxa.vapp.taskCenter.bo.dto;


import com.jsxa.vapp.common.bo.dto.BaseDto;
import com.jsxa.vapp.common.valid.ValidationGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


/*
 * @Author zhangyong
 * @Description //ProjectReqDto
 * @Date 2023/12/18 11:14
 * @Param
 * @return
 **/
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class ProjectReqDto extends BaseDto {

    @ApiModelProperty(name = "id", value = "主键", example = "xxx",dataType="Long")
    @NotNull(message = "主键不能为空",groups = ValidationGroup.ValidationUpdate.class)
    private Long id;


    @ApiModelProperty(name = "name", value = "名称", example = "xxx",dataType="String")
    @NotNull(message = "项目名称不能为空")
    private String name;
 

    @ApiModelProperty(name = "startTime", value = "拍卖开始时间", example = "xxx",dataType="Long")
    @NotNull(message = "拍卖开始时间不能为空")
    @Digits(integer = 13,fraction = 0,message = "时间戳必须为13位数字(毫秒)")
    private Long startTime;
 

    @ApiModelProperty(name = "endTime", value = "拍卖结束时间", example = "xxx",dataType="Long")
    @NotNull(message = "拍卖结束时间不能为空")
    @Digits(integer = 13,fraction = 0,message = "时间戳必须为13位数字(毫秒)")
    private Long endTime;
 

    @ApiModelProperty(name = "quantityUnit", value = "数量单位", example = "xxx",dataType="String")
    @NotNull(message = "数量单位不能为空")
    private String quantityUnit;
 

    @ApiModelProperty(name = "quantity", value = "数量", example = "xxx",dataType="Double")
    @NotNull(message = "数量不能为空")
    private Double quantity;
 

    @ApiModelProperty(name = "upsetPrice", value = "起拍价格", example = "xxx",dataType="Double")
    @NotNull(message = "起拍价格不能为空")
    private Double upsetPrice;
 

    @ApiModelProperty(name = "bidIncr", value = "加价幅度", example = "xxx",dataType="Double")
    @NotNull(message = "加价幅度不能为空")
    private Double bidIncr;
 

    @ApiModelProperty(name = "bidIncrMaxTime", value = "加价最长时间", example = "xxx",dataType="Integer")
    @NotNull(message = "加价最长时间不能为空")
    @Digits(integer = 13,fraction = 0,message = "时间戳必须为13位数字(毫秒)")
    private Integer bidIncrMaxTime;


    @ApiModelProperty(name = "projectDetails", value = "项目详细信息", example = "xxx",dataType="String")
    @NotNull(message = "项目详细信息不能为空")
    private String projectDetails;
 

    @ApiModelProperty(name = "bidNotes", value = "竞拍须知", example = "xxx",dataType="String")
    @NotNull(message = "竞拍须知不能为空")
    private String bidNotes;
 

    @ApiModelProperty(name = "bidRule", value = "竞拍规则", example = "xxx",dataType="String")
    @NotNull(message = "竞拍规则不能为空")
    private String bidRule;


    @ApiModelProperty(name = "contact", value = "联系人", example = "xxx",dataType="String")
    @NotNull(message = "联系人不能为空")
    private String contact;


    @ApiModelProperty(name = "contactMobile", value = "联系人手机号", example = "xxx",dataType="String")
    @NotNull(message = "联系人手机号不能为空")
    private String contactMobile;

    @ApiModelProperty(name = "fileOperate", value = "文件上传操作(null:不新增也不删除文件;0:在上次基础上新增文件;1,3,4:删除上次的第1、3、4个文件)", example = "xxx",dataType="String")
    private String fileOperate;

}