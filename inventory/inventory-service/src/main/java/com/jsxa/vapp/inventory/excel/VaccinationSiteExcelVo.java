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
 * @Description //VaccinationSiteExcelVo
 * @Date 2021/02/27 14:32
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
public class VaccinationSiteExcelVo {

    @ExcelProperty(value = "序号",index = 0)
    private Integer order;

    @ExcelProperty(value = "主键",index = 1)
    @ApiModelProperty(name = "id", value = "主键", example = "xxx",dataType="Long")
    @NotNull(message = "主键不能为空")
    private Long id;
    

    @ExcelProperty(value = "名称",index = 1)
    @ApiModelProperty(name = "name", value = "名称", example = "xxx",dataType="String")
    @NotNull(message = "名称不能为空")
    private String name;
    

    @ExcelProperty(value = "区域码(接种点所属社区)",index = 1)
    @ApiModelProperty(name = "addressCode", value = "区域码(接种点所属社区)", example = "xxx",dataType="String")
    @NotNull(message = "区域码(接种点所属社区)不能为空")
    private String addressCode;
    

    @ExcelProperty(value = "所属单位ID",index = 1)
    @ApiModelProperty(name = "orgId", value = "所属单位ID", example = "xxx",dataType="Long")
    @NotNull(message = "所属单位ID不能为空")
    private Long orgId;
    

    @ExcelProperty(value = "所属单位名称",index = 1)
    @ApiModelProperty(name = "orgName", value = "所属单位名称", example = "xxx",dataType="String")
    @NotNull(message = "所属单位名称不能为空")
    private String orgName;
    

    @ExcelProperty(value = "接种点负责人名称",index = 1)
    @ApiModelProperty(name = "contactName", value = "接种点负责人名称", example = "xxx",dataType="String")
    @NotNull(message = "接种点负责人名称不能为空")
    private String contactName;
    

    @ExcelProperty(value = "接种点负责人电话",index = 1)
    @ApiModelProperty(name = "contactMobile", value = "接种点负责人电话", example = "xxx",dataType="String")
    @NotNull(message = "接种点负责人电话不能为空")
    private String contactMobile;
    

    @ExcelProperty(value = "状态(0=停用,1=启用)",index = 1)
    @ApiModelProperty(name = "status", value = "状态(0=停用,1=启用)", example = "xxx",dataType="Byte")
    @NotNull(message = "状态(0=停用,1=启用)不能为空")
    private Byte status;
    

    @ExcelProperty(value = "排序号",index = 1)
    @ApiModelProperty(name = "orderNum", value = "排序号", example = "xxx",dataType="Integer")
    @NotNull(message = "排序号不能为空")
    private Integer orderNum;
    

    @ExcelProperty(value = "创建时间",index = 1)
    @ApiModelProperty(name = "createTime", value = "创建时间", example = "xxx",dataType="Long")
    @NotNull(message = "创建时间不能为空")
    private Long createTime;
    

}