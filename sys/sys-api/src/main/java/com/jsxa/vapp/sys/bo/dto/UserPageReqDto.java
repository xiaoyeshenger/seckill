package com.jsxa.vapp.sys.bo.dto;

import com.jsxa.vapp.common.bo.dto.BaseDto;
import com.jsxa.vapp.common.bo.dto.PageReqDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.Tolerate;

import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
@Accessors(chain=true)
public class UserPageReqDto extends PageReqDto {

    @ApiModelProperty(name = "orgId", value = "组织ID", example = "11451758690780202",dataType="Long")
    private Long orgId;

    @ApiModelProperty(name = "username", value = "用户名称(登录名)", example = "zhangsan",dataType="String")
    private String username;

    @ApiModelProperty(name = "mobileNumber", value = "电话号码", example = "18900009999",dataType="String")
    private String mobileNumber;

    @ApiModelProperty(name = "roleKeyList", value = "角色关键字列表(以逗号分隔)", example = "Admin",dataType="String")
    private String roleKeyList;


    @ApiModelProperty(name = "status", value = "状态(0=停用,1=正常)", example = "1",dataType="Byte")
    private Byte status;

    @ApiModelProperty(name = "regType", value = "注册类型(正式/申请中/未通过)", example = "9",dataType="Long")
    private Long regType;

    @ApiModelProperty(name = "startTime", value = "创建开始时间", example = "1567764943000",dataType="Long")
    @Digits(integer = 13,fraction = 0,message = "时间戳必须为13位数字(毫秒)")
    private Long startTime;

    @ApiModelProperty(name = "endTime", value = "创建结束时间", example = "1567764958000",dataType="Long")
    @Digits(integer = 13,fraction = 0,message = "时间戳必须为13位数字(毫秒)")
    private Long endTime;

}
