package com.jsxa.vapp.common.bo.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


/**
 * @Author zhangyong
 * @Description //OperateLogPageReqDto
 * @Date 2022/03/02 15:39
 * @Param
 * @return
 */
@Getter
@Setter
@Accessors(chain=true)
public class OperateLogPageReqDto extends PageReqDto {


    @ApiModelProperty(name = "regionCode", value = "区域码", example = "xxx",dataType="String")
    private String regionCode;

    @ApiModelProperty(name = "moduleName", value = "模块名称", example = "xxx",dataType="String")
    private String moduleName;

    @ApiModelProperty(name = "methodCnName", value = "方法中文名称", example = "xxx",dataType="String")
    private String methodCnName;

    @ApiModelProperty(name = "operatorName", value = "操作者姓名", example = "xxx",dataType="String")
    private String operatorName;

    @ApiModelProperty(name = "businessType", value = "业务类型(0=其它,1=新增,2=修改,3=删除,4=授权,5=导出,6=导入,7=强退,8=生成代码,9=清空数据)", example = "xxx",dataType="Long")
    private Long businessType;

    @ApiModelProperty(name = "serviceType", value = "服务类型(详见字典)", example = "911",dataType="Long")
    private Long serviceType;

    @ApiModelProperty(name = "status", value = "操作状态(0=失败,1=成功)", example = "1",dataType="Byte")
    @Min(value = 0,message = "操作状态只能为0或1")
    @Max(value = 1,message = "操作状态只能为0或1")
    private Byte status;

    @ApiModelProperty(name = "startTime", value = "操作开始时间", example = "1567764943000",dataType="Long")
    @Digits(integer = 13,fraction = 0,message = "时间戳必须为13位数字(毫秒)")
    private Long startTime;

    @ApiModelProperty(name = "endTime", value = "操作结束时间", example = "1567764958000",dataType="Long")
    @Digits(integer = 13,fraction = 0,message = "时间戳必须为13位数字(毫秒)")
    private Long endTime;

}