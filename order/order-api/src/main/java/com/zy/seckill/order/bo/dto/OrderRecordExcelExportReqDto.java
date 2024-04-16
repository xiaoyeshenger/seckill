package com.zy.seckill.order.bo.dto;


import com.zy.seckill.common.bo.dto.BaseDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;


/**
 * @Author zhangyong
 * @Description //VaccinationRecordReqDto
 * @Date xxxx/02/27 15:20
 * @Param
 * @return
 */
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class OrderRecordExcelExportReqDto extends BaseDto {

    @ApiModelProperty(name = "id", value = "主键", example = "xxx",dataType="Long")
    private Long id;
    

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
    

    @ApiModelProperty(name = "appointmentTime", value = "预约成功时间", example = "xxx",dataType="Long")
    private Long appointmentTime;
    

    @ApiModelProperty(name = "timePeriod", value = "接种时间段", example = "xxx",dataType="Long")
    private Long timePeriod;
    

    @ApiModelProperty(name = "timePeriodName", value = "接种时间段名称", example = "xxx",dataType="String")
    private String timePeriodName;
    

    @ApiModelProperty(name = "doseTime", value = "实际接种时间", example = "xxx",dataType="Long")
    private Long doseTime;
    

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
    

    @ApiModelProperty(name = "createTime", value = "创建时间", example = "xxx",dataType="Long")
    private Long createTime;
    

}