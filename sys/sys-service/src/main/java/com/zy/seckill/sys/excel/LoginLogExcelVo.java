package com.zy.seckill.sys.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Tolerate;

@Getter
@Setter
@Builder
@ContentRowHeight(20)
@HeadRowHeight(40)
@ColumnWidth(20)
public class LoginLogExcelVo {


    @Tolerate
    public LoginLogExcelVo() {}

    @ExcelProperty(value = "序号",index = 0)
    private Integer order;

    @ExcelProperty(value = "用户名",index = 1)
    private String userName;

    @ExcelProperty(value = "IP地址",index = 2)
    private String ip;

    @ExcelProperty(value = "登录地址",index = 3)
    private String location;

    @ExcelProperty(value = "浏览器",index = 4)
    private String browser;

    @ExcelProperty(value = "提示信息",index = 5)
    private String msg;

    @ExcelProperty(value = "操作系统",index = 6)
    private String os;

    @ExcelProperty(value = "登录状态",index = 7)
    private String statusCn;

    @ExcelProperty(value = "登录时间",index = 8)
    private String operateDate;

    @ExcelProperty(value = "区域码",index = 9)
    private String regionCode;

    @ExcelProperty(value = "区域",index = 10)
    private String regionName;


}
