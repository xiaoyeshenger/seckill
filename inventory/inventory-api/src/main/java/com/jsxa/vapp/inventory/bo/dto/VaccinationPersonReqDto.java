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
 * @Description //VaccinationPersonReqDto
 * @Date 2024/02/02 16:07
 * @Param
 * @return
 **/
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class VaccinationPersonReqDto extends BaseDto {

    @ApiModelProperty(name = "id", value = "主键", example = "xxx",dataType="Long")
    @NotNull(message = "主键不能为空",groups = ValidationGroup.ValidationUpdate.class)
    private Long id;

 

    @ApiModelProperty(name = "name", value = "姓名", example = "xxx",dataType="String")
    @NotNull(message = "姓名不能为空")
    private String name;
 

    @ApiModelProperty(name = "sex", value = "性别", example = "xxx",dataType="Byte")
    @NotNull(message = "性别不能为空")
    private Byte sex;
 

    @ApiModelProperty(name = "age", value = "年龄", example = "xxx",dataType="Integer")
    @NotNull(message = "年龄不能为空")
    private Integer age;
 

    @ApiModelProperty(name = "mobile", value = "联系电话", example = "xxx",dataType="String")
    @NotNull(message = "联系电话不能为空")
    @Pattern(regexp = "^((13[0-9])|(14[0,1,4-9])|(15[0-3,5-9])|(16[2,5,6,7])|(17[0-8])|(18[0-9])|(19[0-3,5-9]))\\d{8}$",message = "电话号码格式不对")
    private String mobile;
 

    @ApiModelProperty(name = "idNumber", value = "身份证号码", example = "xxx",dataType="String")
    @NotNull(message = "身份证号码不能为空")
    @Pattern(regexp = "^(\\d{6})(\\d{4})(\\d{2})(\\d{2})(\\d{3})([0-9]|X|x)$",
    message = "身份证号码格式不对")
    private String idNumber;
 

    @ApiModelProperty(name = "openId", value = "openId", example = "xxx",dataType="String")
    @NotNull(message = "openId不能为空")
    private String openId;
 

    @ApiModelProperty(name = "address", value = "通讯地址", example = "xxx",dataType="String")
    @NotNull(message = "通讯地址不能为空")
    private String address;
 

    @ApiModelProperty(name = "workUnit", value = "工作单位", example = "xxx",dataType="String")
    @NotNull(message = "工作单位不能为空")
    private String workUnit;
 

    @ApiModelProperty(name = "personType", value = "人员类型", example = "xxx",dataType="Long")
    @NotNull(message = "人员类型不能为空")
    private Long personType;
 

    @ApiModelProperty(name = "doseStatus", value = "疫苗接种状态(从未接种/接种中/接种完成)", example = "xxx",dataType="Long")
    @NotNull(message = "疫苗接种状态(从未接种/接种中/接种完成)不能为空",groups = ValidationGroup.ValidationUpdate.class)
    @Min(value = 0,message = "状态只能为0或1")
    @Max(value = 1,message = "状态只能为0或1")
    private Long doseStatus;
 

    @ApiModelProperty(name = "firstDoseId", value = "第一剂接种疫苗ID", example = "xxx",dataType="String")
    @NotNull(message = "第一剂接种疫苗ID不能为空")
    private String firstDoseId;
 

    @ApiModelProperty(name = "firstDoseName", value = "第一剂接种疫苗名称", example = "xxx",dataType="String")
    @NotNull(message = "第一剂接种疫苗名称不能为空")
    private String firstDoseName;
 

    @ApiModelProperty(name = "firstDoseUnit", value = "第一剂接种单位", example = "xxx",dataType="String")
    @NotNull(message = "第一剂接种单位不能为空")
    private String firstDoseUnit;
 

    @ApiModelProperty(name = "firstDoseTime", value = "第一剂接种时间", example = "xxx",dataType="Long")
    @NotNull(message = "第一剂接种时间不能为空")
    @Digits(integer = 13,fraction = 0,message = "时间戳必须为13位数字(毫秒)")
    private Long firstDoseTime;
 

    @ApiModelProperty(name = "latestDoseId", value = "最近剂次接种疫苗ID", example = "xxx",dataType="String")
    @NotNull(message = "最近剂次接种疫苗ID不能为空")
    private String latestDoseId;
 

    @ApiModelProperty(name = "latestDoseName", value = "最近剂次接种疫苗名称", example = "xxx",dataType="String")
    @NotNull(message = "最近剂次接种疫苗名称不能为空")
    private String latestDoseName;
 

    @ApiModelProperty(name = "latestDoseUnit", value = "最近剂次接种单位", example = "xxx",dataType="String")
    @NotNull(message = "最近剂次接种单位不能为空")
    private String latestDoseUnit;
 

    @ApiModelProperty(name = "latestDoseTime", value = "最近剂次接种时间", example = "xxx",dataType="Long")
    @NotNull(message = "最近剂次接种时间不能为空")
    @Digits(integer = 13,fraction = 0,message = "时间戳必须为13位数字(毫秒)")
    private Long latestDoseTime;
 

 

    @ApiModelProperty(name = "updateTime", value = "更新时间", example = "xxx",dataType="Long")
    @NotNull(message = "更新时间不能为空")
    @Digits(integer = 13,fraction = 0,message = "时间戳必须为13位数字(毫秒)")
    private Long updateTime;
 

}