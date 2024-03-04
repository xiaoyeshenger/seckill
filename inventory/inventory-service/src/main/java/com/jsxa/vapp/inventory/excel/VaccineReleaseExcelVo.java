package com.jsxa.vapp.inventory.excel;


import com.jsxa.vapp.common.bo.dto.BaseDto;
import com.jsxa.vapp.common.valid.ValidationGroup;
import com.jsxa.vapp.common.annotation.FieldDupValid;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.Tolerate;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.util.List;


/*
 * @Author zhangyong
 * @Description //VaccineReleaseExcelVo
 * @Date 2024/02/27 15:03
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
public class VaccineReleaseExcelVo {

    @ExcelProperty(value = "序号",index = 0)
    private Integer order;

    @ExcelProperty(value = "主键",index = 1)
    @ApiModelProperty(name = "id", value = "主键", example = "xxx",dataType="Long")
    @NotNull(message = "主键不能为空")
    private Long id;
    

    @ExcelProperty(value = "名称(3月9日第3次放苗)",index = 1)
    @ApiModelProperty(name = "name", value = "名称(3月9日第3次放苗)", example = "xxx",dataType="String")
    @NotNull(message = "名称(3月9日第3次放苗)不能为空")
    private String name;
    

    @ExcelProperty(value = "接种点ID",index = 1)
    @ApiModelProperty(name = "siteId", value = "接种点ID", example = "xxx",dataType="Long")
    @NotNull(message = "接种点ID不能为空")
    private Long siteId;
    

    @ExcelProperty(value = "接种点名称",index = 1)
    @ApiModelProperty(name = "siteName", value = "接种点名称", example = "xxx",dataType="String")
    @NotNull(message = "接种点名称不能为空")
    private String siteName;
    

    @ExcelProperty(value = "剂次",index = 1)
    @ApiModelProperty(name = "dose", value = "剂次", example = "xxx",dataType="Integer")
    @NotNull(message = "剂次不能为空")
    private Integer dose;
    

    @ExcelProperty(value = "放苗数量",index = 1)
    @ApiModelProperty(name = "amount", value = "放苗数量", example = "xxx",dataType="Integer")
    @NotNull(message = "放苗数量不能为空")
    private Integer amount;
    

    @ExcelProperty(value = "市民抢苗开始时间(小程序能够开始预约)",index = 1)
    @ApiModelProperty(name = "startTime", value = "市民抢苗开始时间(小程序能够开始预约)", example = "xxx",dataType="Long")
    @NotNull(message = "市民抢苗开始时间(小程序能够开始预约)不能为空")
    private Long startTime;
    

    @ExcelProperty(value = "接种时间段",index = 1)
    @ApiModelProperty(name = "timePeriod", value = "接种时间段", example = "xxx",dataType="Long")
    @NotNull(message = "接种时间段不能为空")
    private Long timePeriod;
    

    @ExcelProperty(value = "接种时间段名称",index = 1)
    @ApiModelProperty(name = "timePeriodName", value = "接种时间段名称", example = "xxx",dataType="String")
    @NotNull(message = "接种时间段名称不能为空")
    private String timePeriodName;
    

    @ExcelProperty(value = "接种点负责人名称",index = 1)
    @ApiModelProperty(name = "contactName", value = "接种点负责人名称", example = "xxx",dataType="String")
    @NotNull(message = "接种点负责人名称不能为空")
    private String contactName;
    

    @ExcelProperty(value = "接种点负责人电话",index = 1)
    @ApiModelProperty(name = "contactMobile", value = "接种点负责人电话", example = "xxx",dataType="String")
    @NotNull(message = "接种点负责人电话不能为空")
    private String contactMobile;
    

    @ExcelProperty(value = "状态(0=停用,1=发布)",index = 1)
    @ApiModelProperty(name = "status", value = "状态(0=停用,1=发布)", example = "xxx",dataType="Byte")
    @NotNull(message = "状态(0=停用,1=发布)不能为空")
    private Byte status;
    

    @ExcelProperty(value = "创建时间",index = 1)
    @ApiModelProperty(name = "createTime", value = "创建时间", example = "xxx",dataType="Long")
    @NotNull(message = "创建时间不能为空")
    private Long createTime;
    

}