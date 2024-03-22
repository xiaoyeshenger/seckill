package com.jsxa.vapp.inventory.bo.dto;


import com.jsxa.vapp.common.bo.dto.BaseDto;
import com.jsxa.vapp.common.valid.ValidationGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.Tolerate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.util.List;


/*
 * @Author zhangyong
 * @Description //VaccineReqDto
 * @Date 2021/02/27 14:20
 * @Param
 * @return
 **/
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class VaccineExcelExportReqDto extends BaseDto {

    @ApiModelProperty(name = "id", value = "主键", example = "xxx",dataType="Long")
    private Long id;
    

    @ApiModelProperty(name = "orgId", value = "所属单位ID", example = "xxx",dataType="Long")
    private Long orgId;
    

    @ApiModelProperty(name = "orgName", value = "所属单位名称", example = "xxx",dataType="String")
    private String orgName;
    

    @ApiModelProperty(name = "name", value = "名称", example = "xxx",dataType="String")
    private String name;
    

    @ApiModelProperty(name = "manufacturer", value = "厂家名称", example = "xxx",dataType="String")
    private String manufacturer;
    

    @ApiModelProperty(name = "batchNumber", value = "生产批号", example = "xxx",dataType="String")
    private String batchNumber;
    

    @ApiModelProperty(name = "totalDose", value = "总剂次", example = "xxx",dataType="Integer")
    private Integer totalDose;
    

    @ApiModelProperty(name = "doseInterval", value = "剂次间隔时间(21,60)(21代表第二剂与第一剂之间的时间间隔为21天，60代表第三剂与第一剂之间的时间间隔为60天)", example = "xxx",dataType="String")
    private String doseInterval;
    

    @ApiModelProperty(name = "stock", value = "库存数", example = "xxx",dataType="Integer")
    private Integer stock;
    

    @ApiModelProperty(name = "oosUrl", value = "文件(列表以逗号分隔)url", example = "xxx",dataType="String")
    private String oosUrl;
    

    @ApiModelProperty(name = "orderNum", value = "排序号", example = "xxx",dataType="Integer")
    private Integer orderNum;
    

    @ApiModelProperty(name = "status", value = "状态(0=停用,1=启用)", example = "xxx",dataType="Byte")
    private Byte status;
    

    @ApiModelProperty(name = "createTime", value = "创建时间", example = "xxx",dataType="Long")
    private Long createTime;
    

}