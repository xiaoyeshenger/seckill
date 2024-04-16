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
public class HttpPushLogExcelVo {


    @Tolerate
    public HttpPushLogExcelVo() {}

    @ExcelProperty(value = "序号",index = 0)
    private Integer order;

    @ExcelProperty(value = "设备ID(sensorId)",index = 1)
    private String deviceUuid;

    @ExcelProperty(value = "产品名称",index = 2)
    private String productName;

    @ExcelProperty(value = "推送类型",index = 3)
    private String pushTypeCn;

    @ExcelProperty(value = "推送状态",index = 4)
    private String statusCn;

    @ExcelProperty(value = "推送时间",index = 5)
    private String operateDate;

    @ExcelProperty(value = "推送区域",index = 6)
    private String regionValue;

    @ExcelProperty(value = "http请求地址",index = 7)
    private String httpReqUrl;

    @ExcelProperty(value = "http请求头",index = 8)
    private String httpReqHeader;

    @ExcelProperty(value = "http请求参数",index = 9)
    private String httpReqParam;

    @ExcelProperty(value = "http返回结果",index = 10)
    private String httpResult;

    @ExcelProperty(value = "是否为最新推送",index = 11)
    private String latestDataCn;

    @ExcelProperty(value = "错误信息",index = 12)
    private String errorMsg;
}
