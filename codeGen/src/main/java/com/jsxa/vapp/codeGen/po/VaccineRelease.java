package com.jsxa.vapp.codeGen.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * 疫苗发放
 */
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class VaccineRelease implements Serializable {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "名称(3月9日第3次放苗)")
    private String name;

    @ApiModelProperty(value = "接种点ID")
    private Long siteId;

    @ApiModelProperty(value = "接种点名称")
    private String siteName;

    @ApiModelProperty(value = "剂次")
    private Integer dose;

    @ApiModelProperty(value = "放苗数量")
    private Integer amount;

    @ApiModelProperty(value = "库存数量")
    private Integer dockAmount;

    @ApiModelProperty(value = "库存约满时间(即库存为0的时间)")
    private Long useupTime;

    @ApiModelProperty(value = "操作版本号，使用乐观锁的方式扣减mysql库存")
    private Integer version;

    @ApiModelProperty(value = "市民抢苗开始时间(小程序能够开始预约)")
    private Long startTime;

    @ApiModelProperty(value = "疫苗id")
    private Long vaccineId;

    @ApiModelProperty(value = "疫苗名称")
    private String vaccineName;

    @ApiModelProperty(value = "疫苗批次")
    private String vaccineBatch;

    @ApiModelProperty(value = "疫苗生产厂家")
    private String manufacturer;


    @ApiModelProperty(value = "接种日期(2021-03-27 00:00:00)")
    private Long doseTime;

    @ApiModelProperty(value = "接种时间段")
    private Long timePeriod;

    @ApiModelProperty(value = "接种时间段名称")
    private String timePeriodName;

    @ApiModelProperty(value = "接种点负责人名称")
    private String contactName;

    @ApiModelProperty(value = "接种点负责人电话")
    private String contactMobile;

    @ApiModelProperty(value = "状态(0=停用,1=发布,2=已抢完)")
    private Byte status;

    @ApiModelProperty(value = "创建时间")
    private Long createTime;

}