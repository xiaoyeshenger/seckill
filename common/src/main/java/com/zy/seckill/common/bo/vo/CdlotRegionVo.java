package com.zy.seckill.common.bo.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.Tolerate;

import java.util.List;


/**
 * @author zhangyong
 * @description 成都物联感知平台区域vo
 * @date 2023/2/27 15:02
 * @param
 * @return
 */
@Getter
@Setter
@Builder
@Accessors(chain=true)
public class CdlotRegionVo extends BaseVo {

    @Tolerate
    CdlotRegionVo() {}

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

    private Integer level;

    private String token;

    //排序号
    private Integer orderNum;

    //子集列表
    private List<CdlotRegionVo> childList;
}
