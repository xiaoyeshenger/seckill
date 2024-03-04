package com.jsxa.vapp.sys.bo.dto;


import com.jsxa.vapp.common.bo.dto.BaseDto;
import com.jsxa.vapp.common.valid.ValidationGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


/**
 * @Author 张勇
 * @Description //UserReqDto
 * @Date 2021/05/21 18:43
 * @Param
 * @return
 */
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class UserReqDto extends BaseDto {

    @NotNull(message = "id 不能为空",groups = ValidationGroup.ValidationUpdate.class)
    private Long id;

    @ApiModelProperty(name = "deptId", value = "部门ID", example = "1345175869078055",dataType="Long")
    @NotNull(message = "部门ID不能为空")
    private Long deptId;

    @ApiModelProperty(name = "name", value = "姓名(中文)", example = "后台运营部-1号",dataType="String")
    private String name;

    @ApiModelProperty(name = "mobileNumber", value = "电话号码", example = "18900009999",dataType="String")
    //@NotNull(message = "电话号码不能为空")
    @Pattern(regexp = "^((13[0-9])|(14[0,1,4-9])|(15[0-3,5-9])|(16[2,5,6,7])|(17[0-8])|(18[0-9])|(19[0-3,5-9]))\\d{8}$",message = "电话号码格式不对")
    private String mobileNumber;

    @ApiModelProperty(name = "username", value = "登录名", example = "test",dataType="String")
    @NotBlank(message = "登录名不能为空")
    private String username;

    @ApiModelProperty(name = "password", value = "密码", example = "xxx",dataType="String")
    @NotBlank(message = "密码不能为空",groups = ValidationGroup.ValidationAdd.class)
    @Length(min = 6,max = 128,message = "密码不能低于6位数或超过30位数",groups = ValidationGroup.ValidationAdd.class)
    private String password;

    @ApiModelProperty(name = "status", value = "状态(0=停用,1=正常)", example = "1",dataType="Byte")
    @NotNull(message = "状态不能为空")
    private Byte status;

    @ApiModelProperty(name = "regType", value = "用户注册类型(正式/申请中/未通过审核)", example = "7",dataType="Long")
    @NotNull(message = "用户注册类型不能为空")
    private Long regType;

    @ApiModelProperty(name = "roleId", value = "角色ID", example = "1145175869078011",dataType="Long")
    @NotNull(message = "角色ID不能为空")
    private Long roleId;

    @ApiModelProperty(value = "用户性别，0：男，1：女")
    private Byte userSex;

    @ApiModelProperty(value = "邮箱")
    private String email;

}