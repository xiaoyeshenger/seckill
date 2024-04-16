package com.zy.seckill.inventory.bo.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 顾客
 */
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class Person implements Serializable {

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

    @ApiModelProperty(value = "人员类型")
    private Long personType;

    @ApiModelProperty(value = "购买过历史状态(从未购买过/购买过1次/购买过超过1次/其他)")
    private Long doseStatus;

    @ApiModelProperty(value = "第一次购买的商品ID")
    private String firstDoseId;

    @ApiModelProperty(value = "第一购买的商品名称")
    private String firstDoseName;

    @ApiModelProperty(value = "第一剂购买的商品时间")
    private Long firstDoseTime;

    @ApiModelProperty(value = "最近购买的商品ID")
    private String latestDoseId;

    @ApiModelProperty(value = "最近购买的商品名称")
    private String latestDoseName;

    @ApiModelProperty(value = "最近购买的商品时间")
    private Long latestDoseTime;

    @ApiModelProperty(value = "创建时间")
    private Long createTime;

    @ApiModelProperty(value = "更新时间")
    private Long updateTime;

}