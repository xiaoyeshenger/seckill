package com.jsxa.vapp.sys.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.Tolerate;

//成都物联感知设备表格导出对象
@Getter
@Setter
@Builder
@Accessors(chain=true)
@ContentRowHeight(20)
@HeadRowHeight(40)
@ColumnWidth(20)
public class HttpPushLogCdlotExcelVo {


    @Tolerate
    public HttpPushLogCdlotExcelVo() {}

    @ExcelProperty(value = "序号",index = 0)
    private Integer order;

    @ExcelProperty(value = "设备展示名称",index = 1)
    private String deviceShowName;

    @ExcelProperty(value = "设备名称",index = 2)
    private String deviceName;

    @ExcelProperty(value = "设备编号",index = 3)
    private String deviceUuid;

    @ExcelProperty(value = "设备序列号",index = 4)
    private String deviceSerial;

    @ExcelProperty(value = "生产厂家",index = 5)
    private String manufacturer;

    @ExcelProperty(value = "设备型号",index = 6)
    private String deviceModel;

    @ExcelProperty(value = "设备类型",index = 7)
    private String deviceType;

    @ExcelProperty(value = "所属区域",index = 8)
    private String belongRegion;

    @ExcelProperty(value = "街镇",index = 9)
    private String belongTown;

    @ExcelProperty(value = "社区",index = 10)
    private String belongVillage;

    @ExcelProperty(value = "网格",index = 11)
    private String belongGrid;

    @ExcelProperty(value = "地址",index = 12)
    private String address;

    @ExcelProperty(value = "使用对象",index = 13)
    private String useObject;

    @ExcelProperty(value = "经度",index = 14)
    private String longitude;

    @ExcelProperty(value = "纬度",index = 15)
    private String latitude;

    @ExcelProperty(value = "海拔高度",index = 16)
    private String altitude;

    @ExcelProperty(value = "建设单位",index = 17)
    private String constructionUnit;

    @ExcelProperty(value = "运维单位",index = 18)
    private String maintenanceUnit;

    @ExcelProperty(value = "联系人",index = 19)
    private String contactPerson;

    @ExcelProperty(value = "联系电话",index = 20)
    private String contactMobile;

    @ExcelProperty(value = "是否接入物联平台",index = 21)
    private String switchInCdlot;

    @ExcelProperty(value = "备注",index = 22)
    private String remark;
}
