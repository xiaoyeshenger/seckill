package com.zy.seckill.inventory.bo.dto;


import com.zy.seckill.common.bo.dto.BaseDto;
import com.zy.seckill.common.valid.ValidationGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.*;


/**
 * @Author zhangyong
 * @Description 商品信息
 * @Date xxxx/02/27 14:20
 * @Param
 * @return
 **/
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class ProductReqDto extends BaseDto {

    @ApiModelProperty(name = "id", value = "主键", example = "xxx",dataType="Long")
    @NotNull(message = "主键不能为空",groups = ValidationGroup.ValidationUpdate.class)
    private Long id;

 

    @ApiModelProperty(name = "orgId", value = "所属单位ID", example = "xxx",dataType="Long")
    @NotNull(message = "所属商家ID不能为空")
    private Long orgId;
 

    @ApiModelProperty(name = "orgName", value = "所属商家名称", example = "xxx",dataType="String")
    @NotNull(message = "所属商家名称不能为空")
    private String orgName;
 

    @ApiModelProperty(name = "name", value = "商品名称", example = "xxx",dataType="String")
    @NotNull(message = "名称不能为空")
    private String name;
 

    @ApiModelProperty(name = "manufacturer", value = "厂家名称", example = "xxx",dataType="String")
    @NotNull(message = "厂家名称不能为空")
    private String manufacturer;
 

    @ApiModelProperty(name = "batchNumber", value = "生产批号", example = "xxx",dataType="String")
    @NotNull(message = "生产批号不能为空")
    private String batchNumber;
 

    @ApiModelProperty(name = "stock", value = "库存数", example = "xxx",dataType="Integer")
    @NotNull(message = "库存数不能为空")
    private Integer stock;
 

    @ApiModelProperty(name = "oosUrl", value = "文件(列表以逗号分隔)url", example = "xxx",dataType="String")
    @NotNull(message = "文件(列表以逗号分隔)url不能为空")
    private String oosUrl;
 

    @ApiModelProperty(name = "orderNum", value = "排序号", example = "xxx",dataType="Integer")
    @NotNull(message = "排序号不能为空")
    private Integer orderNum;
 

    @ApiModelProperty(name = "status", value = "状态(0=停用,1=启用)", example = "xxx",dataType="Byte")
    @NotNull(message = "状态(0=停用,1=启用)不能为空",groups = ValidationGroup.ValidationUpdate.class)
    @Min(value = 0,message = "状态只能为0或1")
    @Max(value = 1,message = "状态只能为0或1")
    private Byte status;
 

 

}