package com.jsxa.vapp.common.bo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.Tolerate;

import java.util.List;


/*
 * @Author: zhangyong
 * description: 区域vo
 * @Date: 2021-02-01 10:26
 * @Param:
 * @Return:
 */
@Getter
@Setter
@Builder
@Accessors(chain=true)
public class RegionVo extends BaseVo {

    @Tolerate
    RegionVo() {}

    //id
    private Long id;

    //中文
    private String name;

    //英文
    private String value;

    //地址码
    private String code;

    //code
    private Long type;

    //类型中文
    private String typeCn;


    //1:表示行政区域 2:表示委办单位
    private Integer dataType;

    //地址码
    private String address;

    //经度
    private String longitude;

    //纬度
    private String latitude;

    //父级code
    private String parentCode;

    //排序号
    private Integer orderNum;

    //子集列表
    private List<RegionVo> childList;
}
