package com.zy.seckill.sys.bo.dto;


import com.zy.seckill.common.bo.dto.BaseDto;
import com.zy.seckill.common.valid.ValidationGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;


/*
 * @Author zhangyong
 * @Description //RegionReqDto
 * @Date xxxx/09/16 16:43
 * @Param
 * @return
 **/
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class RegionReqDto extends BaseDto {

    @NotNull(message = "id 不能为空",groups = ValidationGroup.ValidationUpdate.class)
    private Long id;

    @ApiModelProperty(name = "name", value = "name", example = "xxx",dataType="String")
    @NotNull(message = "name不能为空")
    private String name;

    @ApiModelProperty(name = "value", value = "value", example = "xxx",dataType="String")
    @NotNull(message = "value不能为空")
    private String value;

    @ApiModelProperty(name = "code", value = "code", example = "xxx",dataType="String")
    @NotNull(message = "code不能为空")
    private String code;

    @ApiModelProperty(name = "type", value = "类型", example = "406",dataType="Long")
    @NotNull(message = "类型不能为空")
    private Long type;

    @ApiModelProperty(name = "address", value = "地址", example = "xxx",dataType="String")
    @NotNull(message = "地址不能为空")
    private String address;

    @ApiModelProperty(name = "longitude", value = "经度", example = "104.369",dataType="String")
    @NotNull(message = "经度不能为空")
    private String longitude;

    @ApiModelProperty(name = "latitude", value = "纬度", example = "30.2265",dataType="String")
    @NotNull(message = "纬度不能为空")
    private String latitude;

    @ApiModelProperty(name = "parentCode", value = "父级code", example = "xxx",dataType="String")
    @NotNull(message = "父级code不能为空")
    private String parentCode;


    @ApiModelProperty(name = "dataType", value = "1:表示行政区域 2:表示委办单位", example = "123",dataType="Integer")
    private Integer dataType;




}