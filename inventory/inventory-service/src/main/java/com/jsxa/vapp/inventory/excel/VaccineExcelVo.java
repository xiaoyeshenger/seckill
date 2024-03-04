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
 * @Description //VaccineExcelVo
 * @Date 2024/02/27 14:20
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
public class VaccineExcelVo {

    @ExcelProperty(value = "序号",index = 0)
    private Integer order;

    @ExcelProperty(value = "主键",index = 1)
    @ApiModelProperty(name = "id", value = "主键", example = "xxx",dataType="Long")
    @NotNull(message = "主键不能为空")
    private Long id;
    

    @ExcelProperty(value = "所属单位ID",index = 1)
    @ApiModelProperty(name = "orgId", value = "所属单位ID", example = "xxx",dataType="Long")
    @NotNull(message = "所属单位ID不能为空")
    private Long orgId;
    

    @ExcelProperty(value = "所属单位名称",index = 1)
    @ApiModelProperty(name = "orgName", value = "所属单位名称", example = "xxx",dataType="String")
    @NotNull(message = "所属单位名称不能为空")
    private String orgName;
    

    @ExcelProperty(value = "名称",index = 1)
    @ApiModelProperty(name = "name", value = "名称", example = "xxx",dataType="String")
    @NotNull(message = "名称不能为空")
    private String name;
    

    @ExcelProperty(value = "厂家名称",index = 1)
    @ApiModelProperty(name = "manufacturer", value = "厂家名称", example = "xxx",dataType="String")
    @NotNull(message = "厂家名称不能为空")
    private String manufacturer;
    

    @ExcelProperty(value = "生产批号",index = 1)
    @ApiModelProperty(name = "batchNumber", value = "生产批号", example = "xxx",dataType="String")
    @NotNull(message = "生产批号不能为空")
    private String batchNumber;
    

    @ExcelProperty(value = "总剂次",index = 1)
    @ApiModelProperty(name = "totalDose", value = "总剂次", example = "xxx",dataType="Integer")
    @NotNull(message = "总剂次不能为空")
    private Integer totalDose;
    

    @ExcelProperty(value = "剂次间隔时间(21,60)(21代表第二剂与第一剂之间的时间间隔为21天，60代表第三剂与第一剂之间的时间间隔为60天)",index = 1)
    @ApiModelProperty(name = "doseInterval", value = "剂次间隔时间(21,60)(21代表第二剂与第一剂之间的时间间隔为21天，60代表第三剂与第一剂之间的时间间隔为60天)", example = "xxx",dataType="String")
    @NotNull(message = "剂次间隔时间(21,60)(21代表第二剂与第一剂之间的时间间隔为21天，60代表第三剂与第一剂之间的时间间隔为60天)不能为空")
    private String doseInterval;
    

    @ExcelProperty(value = "库存数",index = 1)
    @ApiModelProperty(name = "stock", value = "库存数", example = "xxx",dataType="Integer")
    @NotNull(message = "库存数不能为空")
    private Integer stock;
    

    @ExcelProperty(value = "文件(列表以逗号分隔)url",index = 1)
    @ApiModelProperty(name = "oosUrl", value = "文件(列表以逗号分隔)url", example = "xxx",dataType="String")
    @NotNull(message = "文件(列表以逗号分隔)url不能为空")
    private String oosUrl;
    

    @ExcelProperty(value = "排序号",index = 1)
    @ApiModelProperty(name = "orderNum", value = "排序号", example = "xxx",dataType="Integer")
    @NotNull(message = "排序号不能为空")
    private Integer orderNum;
    

    @ExcelProperty(value = "状态(0=停用,1=启用)",index = 1)
    @ApiModelProperty(name = "status", value = "状态(0=停用,1=启用)", example = "xxx",dataType="Byte")
    @NotNull(message = "状态(0=停用,1=启用)不能为空")
    private Byte status;
    

    @ExcelProperty(value = "创建时间",index = 1)
    @ApiModelProperty(name = "createTime", value = "创建时间", example = "xxx",dataType="Long")
    @NotNull(message = "创建时间不能为空")
    private Long createTime;
    

}