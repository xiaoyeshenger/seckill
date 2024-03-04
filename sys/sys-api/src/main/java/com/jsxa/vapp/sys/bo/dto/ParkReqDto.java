package com.jsxa.vapp.sys.bo.dto;


import com.jsxa.vapp.common.bo.dto.BaseDto;
import com.jsxa.vapp.common.bo.dto.PageReqDto;
import com.jsxa.vapp.common.valid.ValidationGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


/*
 * @Author zhangyong
 * @Description //ParkReqDto
 * @Date 2022/02/09 17:31
 * @Param
 * @return
 **/
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class ParkReqDto extends BaseDto {

    @ApiModelProperty(name = "id", value = "id", example = "123",dataType="Long")
    @NotNull(message = "id 不能为空",groups = ValidationGroup.ValidationUpdate.class)
    private Long id;

    @ApiModelProperty(name = "parentId", value = "主管部门ID", example = "1500354392018309001",dataType="Long")
    @NotNull(message = "主管部门ID不能为空")
    private Long parentId;

    @ApiModelProperty(name = "name", value = "园区名称", example = "云钢联",dataType="String")
    @NotNull(message = "园区名称不能为空")
    private String name;

    @ApiModelProperty(name = "addressCode", value = "地址码", example = "成都市",dataType="String")
    @NotNull(message = "地址码不能为空")
    private String addressCode;

    @ApiModelProperty(name = "longitude", value = "经度", example = "104.2365",dataType="Long")
    @NotNull(message = "经度不能为空")
    private Double longitude;

    @ApiModelProperty(name = "latitude", value = "纬度", example = "30.1283",dataType="Long")
    @NotNull(message = "纬度不能为空")
    private Double latitude;

    @ApiModelProperty(name = "principalName", value = "园区负责人姓名", example = "王五",dataType="String")
    @NotNull(message = "园区负责人姓名不能为空")
    private String principalName;

    @ApiModelProperty(name = "principalMobile", value = "园区负责人电话", example = "xxx",dataType="String")
    @NotNull(message = "园区负责人电话不能为空")
    @Pattern(regexp = "^((13[0-9])|(14[0,1,4-9])|(15[0-3,5-9])|(16[2,5,6,7])|(17[0-8])|(18[0-9])|(19[0-3,5-9]))\\d{8}$",message = "电话号码格式不对")
    private String principalMobile;

    @ApiModelProperty(name = "type", value = "企业类型", example = "123",dataType="Long")
    @NotNull(message = "企业类型不能为空")
    private Long type;

    @ApiModelProperty(name = "industry", value = "所属行业", example = "123",dataType="Long")
    @NotNull(message = "所属行业不能为空")
    private Long industry;

    @ApiModelProperty(name = "staffScale", value = "员工规模", example = "123",dataType="Long")
    @NotNull(message = "员工规模不能为空")
    private Long staffScale;

    @ApiModelProperty(name = "startingTime", value = "投运时间", example = "123",dataType="Long")
    @NotNull(message = "投运时间不能为空")
    private Long startingTime;

    @ApiModelProperty(name = "floorSpace", value = "占地面积(亩)", example = "123",dataType="Double")
    @NotNull(message = "占地面积不能为空")
    private Double floorSpace;

    @ApiModelProperty(name = "wareArea", value = "库房面积(平方米)(普通库、冷库、堆场)", example = "123",dataType="Double")
    @NotNull(message = "库房面积不能为空")
    private Double wareArea;

}