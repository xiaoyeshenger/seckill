package com.zy.seckill.codeGen.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 接种记录
 */
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class VaccinationRecord implements Serializable {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "记录产生来源(系统产生,电脑补录,小程序端自行补录)")
    private Long recordType;

    @ApiModelProperty(value = "是否有效(如果是接种者通过小程序端自行补录的接种记录，需要上传接种截图(天府健康码)并由工作人员审核)")
    private Byte vaild;

    @ApiModelProperty(value = "接种人员id")
    private Long personId;

    @ApiModelProperty(value = "接种人员姓名")
    private String personName;

    @ApiModelProperty(value = "性别")
    private Byte sex;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "联系电话")
    private String mobile;

    @ApiModelProperty(value = "身份证号码")
    private String idNumber;

    @ApiModelProperty(value = "微信小程序openId")
    private String openId;

    @ApiModelProperty(value = "接种地点id")
    private Long siteId;

    @ApiModelProperty(value = "接种地点名称")
    private String siteName;

    @ApiModelProperty(value = "接种记录状态(待接种,已入场,已接种,已作废,已取消)")
    private Long recordStatus;

    @ApiModelProperty(value = "疫苗id")
    private Long vaccineId;

    @ApiModelProperty(value = "疫苗名称")
    private String vaccineName;

    @ApiModelProperty(value = "疫苗批次")
    private String vaccineBatch;

    @ApiModelProperty(value = "疫苗生产厂家")
    private String manufacturer;

    @ApiModelProperty(value = "接种剂次")
    private Integer dose;

    @ApiModelProperty(value = "接种单位")
    private String doseUnit;

    @ApiModelProperty(value = "预约成功时间")
    private Long appointmentTime;

    @ApiModelProperty(value = "接种时间段")
    private Long timePeriod;

    @ApiModelProperty(value = "接种时间段名称")
    private String timePeriodName;

    @ApiModelProperty(value = "实际接种时间")
    private Long doseTime;

    @ApiModelProperty(value = "接种报告图片")
    private String imageUrl;

    @ApiModelProperty(value = "市州")
    private String city;

    @ApiModelProperty(value = "区县")
    private String county;

    @ApiModelProperty(value = "乡镇")
    private String town;

    @ApiModelProperty(value = "未接种原因")
    private String msg;

    @ApiModelProperty(value = "创建时间")
    private Long createTime;

}