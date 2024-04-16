package com.zy.seckill.order.bo.dto;


import com.zy.seckill.common.bo.dto.PageReqDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.*;


/**
 * @Author zhangyong
 * @Description //VaccinationRecordPageReqDto
 * @Date xxxx/02/27 15:20
 * @Param
 * @return
 */
@Getter
@Setter
@Accessors(chain=true)
public class OrderRecordPageReqDto extends PageReqDto {



    @ApiModelProperty(name = "recordType", value = "记录产生来源(系统产生,电脑补录,小程序端自行补录)", example = "xxx",dataType="Long")
    private Long recordType;


    @ApiModelProperty(name = "vaild", value = "是否有效(如果是接种者通过小程序端自行补录的接种记录，需要上传接种截图(天府健康码)并由工作人员审核)", example = "xxx",dataType="Byte")
    private Byte vaild;


    @ApiModelProperty(name = "personId", value = "接种人员id", example = "xxx",dataType="Long")
    private Long personId;


    @ApiModelProperty(name = "personName", value = "接种人员姓名", example = "xxx",dataType="String")
    private String personName;


    @ApiModelProperty(name = "sex", value = "性别", example = "xxx",dataType="Byte")
    private Byte sex;


    @ApiModelProperty(name = "age", value = "年龄", example = "xxx",dataType="Integer")
    private Integer age;


    @ApiModelProperty(name = "mobile", value = "联系电话", example = "xxx",dataType="String")
    private String mobile;


    @ApiModelProperty(name = "idNumber", value = "身份证号码", example = "xxx",dataType="String")
    private String idNumber;


    @ApiModelProperty(name = "openId", value = "微信小程序openId", example = "xxx",dataType="String")
    private String openId;


    @ApiModelProperty(name = "siteId", value = "接种地点id", example = "xxx",dataType="Long")
    private Long siteId;


    @ApiModelProperty(name = "siteName", value = "接种地点名称", example = "xxx",dataType="String")
    private String siteName;


    @ApiModelProperty(name = "recordStatus", value = "接种记录状态(待接种,已入场,已接种,已作废,已取消)", example = "xxx",dataType="Long")
    private Long recordStatus;


    @ApiModelProperty(name = "vaccineId", value = "疫苗id", example = "xxx",dataType="Long")
    private Long vaccineId;


    @ApiModelProperty(name = "vaccineName", value = "疫苗名称", example = "xxx",dataType="String")
    private String vaccineName;


    @ApiModelProperty(name = "vaccineBatch", value = "疫苗批次", example = "xxx",dataType="String")
    private String vaccineBatch;


    @ApiModelProperty(name = "manufacturer", value = "疫苗生产厂家", example = "xxx",dataType="String")
    private String manufacturer;


    @ApiModelProperty(name = "dose", value = "接种剂次", example = "xxx",dataType="Integer")
    private Integer dose;


    @ApiModelProperty(name = "doseUnit", value = "接种单位", example = "xxx",dataType="String")
    private String doseUnit;


    @ApiModelProperty(name = "startAppointmentTime", value = "预约成功时间开始时间", example = "1567764943000",dataType="Long")
    @Digits(integer = 13,fraction = 0,message = "时间戳必须为13位数字(毫秒)")
    private Long startAppointmentTime;

    @ApiModelProperty(name = "endAppointmentTime", value = "预约成功时间结束时间", example = "1567764958000",dataType="Long")
    @Digits(integer = 13,fraction = 0,message = "时间戳必须为13位数字(毫秒)")
    private Long endAppointmentTime;



    @ApiModelProperty(name = "timePeriod", value = "接种时间段", example = "xxx",dataType="Long")
    private Long timePeriod;


    @ApiModelProperty(name = "timePeriodName", value = "接种时间段名称", example = "xxx",dataType="String")
    private String timePeriodName;


    @ApiModelProperty(name = "startDoseTime", value = "实际接种时间开始时间", example = "1567764943000",dataType="Long")
    @Digits(integer = 13,fraction = 0,message = "时间戳必须为13位数字(毫秒)")
    private Long startDoseTime;

    @ApiModelProperty(name = "endDoseTime", value = "实际接种时间结束时间", example = "1567764958000",dataType="Long")
    @Digits(integer = 13,fraction = 0,message = "时间戳必须为13位数字(毫秒)")
    private Long endDoseTime;



    @ApiModelProperty(name = "imageUrl", value = "接种报告图片", example = "xxx",dataType="String")
    private String imageUrl;


    @ApiModelProperty(name = "city", value = "市州", example = "xxx",dataType="String")
    private String city;


    @ApiModelProperty(name = "county", value = "区县", example = "xxx",dataType="String")
    private String county;


    @ApiModelProperty(name = "town", value = "乡镇", example = "xxx",dataType="String")
    private String town;


    @ApiModelProperty(name = "msg", value = "未接种原因", example = "xxx",dataType="String")
    private String msg;




}