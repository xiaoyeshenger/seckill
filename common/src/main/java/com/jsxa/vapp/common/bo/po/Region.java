package com.jsxa.vapp.common.bo.po;

import com.jsxa.vapp.common.bo.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.Tolerate;


/*
 * @Author: zhangyong
 * description: 区域
 * @Date: 2021-02-01 10:26
 * @Param:
 * @Return:
 */
@Getter
@Setter
@Builder
@Accessors(chain=true)
public class Region extends BaseVo {

    @Tolerate
    Region() {}

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "英文名")
    private String value;

    @ApiModelProperty(value = "区域码")
    private String code;

    @ApiModelProperty(value = "类型(乡镇/区市县/市/省/)")
    private Long type;


    //1:表示行政区域 2:表示委办单位
    private Integer dataType;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "经度")
    private String longitude;

    @ApiModelProperty(value = "纬度")
    private String latitude;

    @ApiModelProperty(value = "父级code")
    private String parentCode;


    @ApiModelProperty(value = "排序")
    private Integer orderNum;

//    @ApiModelProperty(value = "区域等级(从小区开始,从低到高)")
//    private Integer level;

    @ApiModelProperty(value = "创建时间")
    private Long createTime;
}
