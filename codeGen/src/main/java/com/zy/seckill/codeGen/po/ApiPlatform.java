package com.zy.seckill.codeGen.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author zhangyong
 * @description 外部API平台信息
 * @date 2024/4/9 15:21
 */
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class ApiPlatform implements Serializable {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "类型")
    private Long type;

    @ApiModelProperty(value = "描述")
    private String desc;

    @ApiModelProperty(value = "基础url")
    private String baseUrl;

    @ApiModelProperty(value = "功能url集合")
    private String functionUrl;

    @ApiModelProperty(value = "访问验证信息(包含账户,密码,accessKey等信息)")
    private String accessVerify;

    @ApiModelProperty(value = "扩展字段")
    private String extData;

    @ApiModelProperty(value = "创建时间")
    private Long createTime;

    @ApiModelProperty(value = "更新时间")
    private Long updateTime;

}