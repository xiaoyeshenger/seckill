package com.zy.seckill.sys.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Tolerate;

//设备表格导入对象
@Getter
@Setter
@Builder
@ContentRowHeight(20)
@HeadRowHeight(40)
@ColumnWidth(20)
public class DeviceInfoPushExcelVo {


    @Tolerate
    public DeviceInfoPushExcelVo() {}

    @ExcelProperty(value = "序号",index = 0)
    private Integer order;

    @ExcelProperty(value = "操作状态",index = 1)
    private String statusCn;

    @ExcelProperty(value = "操作时间",index = 2)
    private String operateDate;

    @ExcelProperty(value = "设备UUID",index = 3)
    private String deviceUuid;

    @ExcelProperty(value = "产品名称",index = 4)
    private String productName;

    @ExcelProperty(value = "区域名称(社区)",index = 5)
    private String regionName;

    @ExcelProperty(value = "http请求地址",index = 6)
    private String httpReqUrl;

    @ExcelProperty(value = "http请求头",index = 7)
    private String httpReqHeader;

    @ExcelProperty(value = "http请求参数",index = 8)
    private String httpReqParam;

    @ExcelProperty(value = "http返回结果",index = 9)
    private String httpResult;

}
