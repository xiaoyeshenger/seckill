package com.zy.seckill.sys.bo.vo;

import com.zy.seckill.common.bo.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * @Classname DeviceVo
 * @Description TODO
 * @Date 2022/12/15 15:18
 * @Created by Administrator
 */
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class DeviceVo extends BaseVo {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "设备序列号")
    private String serialNumber;

    @ApiModelProperty(value = "设备名称")
    private String name;

    @ApiModelProperty(value = "设备类型")
    private Long deviceType;

    @ApiModelProperty(value = "设备父级类型")
    private Long deviceParentType;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "经度")
    private String longitude;

    @ApiModelProperty(value = "纬度")
    private String latitude;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "排序号")
    private Integer orderNum;

    @ApiModelProperty(value = "1=正常/2=离线")
    private Integer deviceStatus;

    @ApiModelProperty(value = "状态(0=停用,1=启用)")
    private Byte status;

    @ApiModelProperty(value = "删除标识")
    private Byte delFlag;

    @ApiModelProperty(value = "创建时间")
    private Long createTime;

    @ApiModelProperty(value = "最后上报时间")
    private Long lastTime;

    @ApiModelProperty(value = "扩展信息")
    private String extendData;

    @ApiModelProperty(value = "安装区域code")
    private String regionCode;


    @ApiModelProperty(value = "安装区域code父级")
    private String regionParentCode;

    @ApiModelProperty(value = "委办单位")
    private String manageCode;


    @ApiModelProperty(value = "设备类型别名")
    private String alias;


    @ApiModelProperty(value = "设备类型中文")
    private String deviceTypeCn;


    private String uuid;

    private Long productId;

    @ApiModelProperty(value = "网格ID")
    private Long gridId;
}
