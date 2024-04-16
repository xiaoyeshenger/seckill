package com.zy.seckill.common.bo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

/*
 * @Author: zhangyong
 * description: 用户
 * @Date: 2020-03-22 12:13
 * @Param:
 * @Return:
 */
@Data
public class UserVo {

    @ApiModelProperty(value = "用户id")
    private Long id;

    @ApiModelProperty(value = "组织机构id")
    private Long orgId;

    @ApiModelProperty(value = "组织名称")
    private String orgName;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "用户注册状态(7:申请中,8:正式用户,9:未通过)")
    private Long regType;

    @ApiModelProperty(value = "头像")
    private String picUrl;

    @ApiModelProperty(value = "用户性别，0：男，1：女")
    private Byte userSex;

    @ApiModelProperty(value = "角色")
    private String roleKey;

    @ApiModelProperty(value = "角色ID")
    private Long roleId;

    @ApiModelProperty(value = "电话号码")
    private String mobileNumber;

    @ApiModelProperty(value = "园区经度")
    private Double longitude;

    @ApiModelProperty(value = "园区纬度")
    private Double latitude;

    @ApiModelProperty(value = "页面模板类型")
    private Long templateType;

    @ApiModelProperty(value = "模板跳转路径")
    private String path;

    @ApiModelProperty(value = "页面主键")
    private String pageKey;

    @ApiModelProperty(value = "genJsonUrl")
    private String genJsonUrl;

    @ApiModelProperty(value = "默认园区ID")
    private Long parkType;

    @ApiModelProperty(value = "默认园区ID")
    private Long parkId;

    @ApiModelProperty(value = "园区名称")
    private String parkName;

    @ApiModelProperty(value = "园区logo")
    private String parkLogo;

    @ApiModelProperty(value = "父级园区ID(主管单位ID)")
    private Long parentId;

    private String regionCode;

    private String regionParentCode;

    //code的类型 区 街道 社区
    private Long regionCodeType;

    //1 表示行政单位 2 表示委办单位
    private Integer dataType;

    @ApiModelProperty(value = "组织列表")
    private List<Map<String,Object>> orgList;
}
