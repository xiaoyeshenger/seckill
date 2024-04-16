package com.zy.seckill.sys.bo.dto;


import com.zy.seckill.common.bo.dto.BaseDto;
import com.zy.seckill.common.valid.ValidationGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


/**
 * @Author 张勇
 * @Description //UserRegisterReqDto
 * @Date xxxx/02/04 14:49
 * @Param
 * @return
 **/
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterReqDto extends BaseDto {

    @ApiModelProperty(name = "orgName", value = "机构名称", example = "xxx",dataType="String")
    @NotBlank(message = "机构名称不能为空")
    private String orgName;

    @ApiModelProperty(name = "name", value = "联系人姓名(中文)", example = "后台运营部-1号",dataType="String")
    @NotBlank(message = "联系人姓名不能为空")
    private String name;

    @ApiModelProperty(name = "mobileNumber", value = "电话号码", example = "18900009999",dataType="String")
    @NotNull(message = "电话号码不能为空")
    @Pattern(regexp = "^((13[0-9])|(14[0,1,4-9])|(15[0-3,5-9])|(16[2,5,6,7])|(17[0-8])|(18[0-9])|(19[0-3,5-9]))\\d{8}$",message = "电话号码格式不对")
    private String mobileNumber;

    @ApiModelProperty(name = "username", value = "登录名", example = "test",dataType="String")
    @NotBlank(message = "登录名不能为空")
    private String username;

    @ApiModelProperty(name = "password", value = "密码", example = "xxx",dataType="String")
    @NotBlank(message = "密码不能为空",groups = ValidationGroup.ValidationAdd.class)
    @Length(min = 6,max = 128,message = "密码不能低于6位数或超过30位数",groups = ValidationGroup.ValidationAdd.class)
    private String password;

}