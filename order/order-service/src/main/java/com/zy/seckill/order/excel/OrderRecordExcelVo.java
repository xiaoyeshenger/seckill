package com.zy.seckill.order.excel;


import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;

import javax.validation.constraints.NotNull;


/*
 * @Author zhangyong
 * @Description //VaccinationRecordExcelVo
 * @Date xxxx/02/27 15:20
 * @Param
 * @return
 **/
@Getter
@Setter
@Builder
@ContentRowHeight(20)
@HeadRowHeight(40)
@ColumnWidth(20)
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class OrderRecordExcelVo {

    @ExcelProperty(value = "序号",index = 0)
    private Integer order;

    @ExcelProperty(value = "主键",index = 1)
    @ApiModelProperty(name = "id", value = "主键", example = "xxx",dataType="Long")
    @NotNull(message = "主键不能为空")
    private Long id;
    

    @ExcelProperty(value = "记录产生来源(系统产生,电脑补录,小程序端自行补录)",index = 1)
    @ApiModelProperty(name = "recordType", value = "记录产生来源(系统产生,电脑补录,小程序端自行补录)", example = "xxx",dataType="Long")
    @NotNull(message = "记录产生来源(系统产生,电脑补录,小程序端自行补录)不能为空")
    private Long recordType;
    

    @ExcelProperty(value = "是否有效(如果是接种者通过小程序端自行补录的接种记录，需要上传接种截图(天府健康码)并由工作人员审核)",index = 1)
    @ApiModelProperty(name = "vaild", value = "是否有效(如果是接种者通过小程序端自行补录的接种记录，需要上传接种截图(天府健康码)并由工作人员审核)", example = "xxx",dataType="Byte")
    @NotNull(message = "是否有效(如果是接种者通过小程序端自行补录的接种记录，需要上传接种截图(天府健康码)并由工作人员审核)不能为空")
    private Byte vaild;
    

    @ExcelProperty(value = "接种人员id",index = 1)
    @ApiModelProperty(name = "personId", value = "接种人员id", example = "xxx",dataType="Long")
    @NotNull(message = "接种人员id不能为空")
    private Long personId;
    

    @ExcelProperty(value = "接种人员姓名",index = 1)
    @ApiModelProperty(name = "personName", value = "接种人员姓名", example = "xxx",dataType="String")
    @NotNull(message = "接种人员姓名不能为空")
    private String personName;
    

    @ExcelProperty(value = "性别",index = 1)
    @ApiModelProperty(name = "sex", value = "性别", example = "xxx",dataType="Byte")
    @NotNull(message = "性别不能为空")
    private Byte sex;
    

    @ExcelProperty(value = "年龄",index = 1)
    @ApiModelProperty(name = "age", value = "年龄", example = "xxx",dataType="Integer")
    @NotNull(message = "年龄不能为空")
    private Integer age;
    

    @ExcelProperty(value = "联系电话",index = 1)
    @ApiModelProperty(name = "mobile", value = "联系电话", example = "xxx",dataType="String")
    @NotNull(message = "联系电话不能为空")
    private String mobile;
    

    @ExcelProperty(value = "身份证号码",index = 1)
    @ApiModelProperty(name = "idNumber", value = "身份证号码", example = "xxx",dataType="String")
    @NotNull(message = "身份证号码不能为空")
    private String idNumber;
    

    @ExcelProperty(value = "微信小程序openId",index = 1)
    @ApiModelProperty(name = "openId", value = "微信小程序openId", example = "xxx",dataType="String")
    @NotNull(message = "微信小程序openId不能为空")
    private String openId;
    

    @ExcelProperty(value = "接种地点id",index = 1)
    @ApiModelProperty(name = "siteId", value = "接种地点id", example = "xxx",dataType="Long")
    @NotNull(message = "接种地点id不能为空")
    private Long siteId;
    

    @ExcelProperty(value = "接种地点名称",index = 1)
    @ApiModelProperty(name = "siteName", value = "接种地点名称", example = "xxx",dataType="String")
    @NotNull(message = "接种地点名称不能为空")
    private String siteName;
    

    @ExcelProperty(value = "接种记录状态(待接种,已入场,已接种,已作废,已取消)",index = 1)
    @ApiModelProperty(name = "recordStatus", value = "接种记录状态(待接种,已入场,已接种,已作废,已取消)", example = "xxx",dataType="Long")
    @NotNull(message = "接种记录状态(待接种,已入场,已接种,已作废,已取消)不能为空")
    private Long recordStatus;
    

    @ExcelProperty(value = "疫苗id",index = 1)
    @ApiModelProperty(name = "vaccineId", value = "疫苗id", example = "xxx",dataType="Long")
    @NotNull(message = "疫苗id不能为空")
    private Long vaccineId;
    

    @ExcelProperty(value = "疫苗名称",index = 1)
    @ApiModelProperty(name = "vaccineName", value = "疫苗名称", example = "xxx",dataType="String")
    @NotNull(message = "疫苗名称不能为空")
    private String vaccineName;
    

    @ExcelProperty(value = "疫苗批次",index = 1)
    @ApiModelProperty(name = "vaccineBatch", value = "疫苗批次", example = "xxx",dataType="String")
    @NotNull(message = "疫苗批次不能为空")
    private String vaccineBatch;
    

    @ExcelProperty(value = "疫苗生产厂家",index = 1)
    @ApiModelProperty(name = "manufacturer", value = "疫苗生产厂家", example = "xxx",dataType="String")
    @NotNull(message = "疫苗生产厂家不能为空")
    private String manufacturer;
    

    @ExcelProperty(value = "接种剂次",index = 1)
    @ApiModelProperty(name = "dose", value = "接种剂次", example = "xxx",dataType="Integer")
    @NotNull(message = "接种剂次不能为空")
    private Integer dose;
    

    @ExcelProperty(value = "接种单位",index = 1)
    @ApiModelProperty(name = "doseUnit", value = "接种单位", example = "xxx",dataType="String")
    @NotNull(message = "接种单位不能为空")
    private String doseUnit;
    

    @ExcelProperty(value = "预约成功时间",index = 1)
    @ApiModelProperty(name = "appointmentTime", value = "预约成功时间", example = "xxx",dataType="Long")
    @NotNull(message = "预约成功时间不能为空")
    private Long appointmentTime;
    

    @ExcelProperty(value = "接种时间段",index = 1)
    @ApiModelProperty(name = "timePeriod", value = "接种时间段", example = "xxx",dataType="Long")
    @NotNull(message = "接种时间段不能为空")
    private Long timePeriod;
    

    @ExcelProperty(value = "接种时间段名称",index = 1)
    @ApiModelProperty(name = "timePeriodName", value = "接种时间段名称", example = "xxx",dataType="String")
    @NotNull(message = "接种时间段名称不能为空")
    private String timePeriodName;
    

    @ExcelProperty(value = "实际接种时间",index = 1)
    @ApiModelProperty(name = "doseTime", value = "实际接种时间", example = "xxx",dataType="Long")
    @NotNull(message = "实际接种时间不能为空")
    private Long doseTime;
    

    @ExcelProperty(value = "接种报告图片",index = 1)
    @ApiModelProperty(name = "imageUrl", value = "接种报告图片", example = "xxx",dataType="String")
    @NotNull(message = "接种报告图片不能为空")
    private String imageUrl;
    

    @ExcelProperty(value = "市州",index = 1)
    @ApiModelProperty(name = "city", value = "市州", example = "xxx",dataType="String")
    @NotNull(message = "市州不能为空")
    private String city;
    

    @ExcelProperty(value = "区县",index = 1)
    @ApiModelProperty(name = "county", value = "区县", example = "xxx",dataType="String")
    @NotNull(message = "区县不能为空")
    private String county;
    

    @ExcelProperty(value = "乡镇",index = 1)
    @ApiModelProperty(name = "town", value = "乡镇", example = "xxx",dataType="String")
    @NotNull(message = "乡镇不能为空")
    private String town;
    

    @ExcelProperty(value = "未接种原因",index = 1)
    @ApiModelProperty(name = "msg", value = "未接种原因", example = "xxx",dataType="String")
    @NotNull(message = "未接种原因不能为空")
    private String msg;
    

    @ExcelProperty(value = "创建时间",index = 1)
    @ApiModelProperty(name = "createTime", value = "创建时间", example = "xxx",dataType="Long")
    @NotNull(message = "创建时间不能为空")
    private Long createTime;
    

}