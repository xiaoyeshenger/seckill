package com.jsxa.vapp.inventory.bo.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 接种人员
 */
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class VaccinationPerson implements Serializable {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "性别")
    private Byte sex;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "联系电话")
    private String mobile;

    @ApiModelProperty(value = "身份证号码")
    private String idNumber;

    @ApiModelProperty(value = "openId")
    private String openId;

    @ApiModelProperty(value = "通讯地址")
    private String address;

    @ApiModelProperty(value = "工作单位")
    private String workUnit;

    @ApiModelProperty(value = "人员类型")
    private Long personType;

    @ApiModelProperty(value = "疫苗接种状态(从未接种/接种中/接种完成)")
    private Long doseStatus;

    @ApiModelProperty(value = "第一剂接种疫苗ID")
    private String firstDoseId;

    @ApiModelProperty(value = "第一剂接种疫苗名称")
    private String firstDoseName;

    @ApiModelProperty(value = "第一剂接种单位")
    private String firstDoseUnit;

    @ApiModelProperty(value = "第一剂接种时间")
    private Long firstDoseTime;

    @ApiModelProperty(value = "最近剂次接种疫苗ID")
    private String latestDoseId;

    @ApiModelProperty(value = "最近剂次接种疫苗名称")
    private String latestDoseName;

    @ApiModelProperty(value = "最近剂次接种单位")
    private String latestDoseUnit;

    @ApiModelProperty(value = "最近剂次接种时间")
    private Long latestDoseTime;

    @ApiModelProperty(value = "创建时间")
    private Long createTime;

    @ApiModelProperty(value = "更新时间")
    private Long updateTime;

}