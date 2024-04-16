package com.zy.seckill.codeGen.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author zhangyong
 * @description 设备信息
 * @date 2024/4/9 12:09
 * @param
 * @return
 */
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class Device implements Serializable {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "设备类型")
    private Long type;

    @ApiModelProperty(value = "设备序列号")
    private String deviceSerial;

    @ApiModelProperty(value = "设备IP")
    private String deviceIp;

    @ApiModelProperty(value = "设备状态(1=在线/0=离线")
    private Byte deviceStatus;

    @ApiModelProperty(value = "安装方向(1=进入方向/0=出口方向)")
    private Byte direction;

    @ApiModelProperty(value = "设备安装位置")
    private String location;

    @ApiModelProperty(value = "经度")
    private Double longitude;

    @ApiModelProperty(value = "纬度")
    private Double latitude;

    @ApiModelProperty(value = "排序号")
    private Integer orderNum;

    @ApiModelProperty(value = "状态(0=停用,1=启用)")
    private Byte status;

    @ApiModelProperty(value = "站点(小区)ID")
    private String plotId;

    @ApiModelProperty(value = "视频播放地址")
    private String playUrl;

    @ApiModelProperty(value = "视频国标设备编码")
    private String gbDeviceCode;

    @ApiModelProperty(value = "视频国标通道编码")
    private String gbChannelCode;

    @ApiModelProperty(value = "扩展字段(预留)")
    private String extData;

    @ApiModelProperty(value = "创建时间")
    private Long createTime;

    @ApiModelProperty(value = "更新时间")
    private Long updateTime;

}