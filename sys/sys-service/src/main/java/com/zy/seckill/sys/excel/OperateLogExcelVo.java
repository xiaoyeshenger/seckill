package com.zy.seckill.sys.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
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
public class OperateLogExcelVo {


    @Tolerate
    public OperateLogExcelVo() {}

    @ExcelProperty(value = "序号",index = 0)
    private Integer order;

    @ExcelProperty(value = "操作状态",index = 1)
    private String statusCn;

    @ExcelProperty(value = "操作时间",index = 2)
    private String operateDate;

    @ExcelProperty(value = "模块名称",index = 3)
    private String moduleName;

    //(0=其它,1=新增,2=修改,3=删除,4=授权,5=导出,6=导入,7=强退,8=生成代码,9=清空数据)
    @ExcelProperty(value = "业务类型",index = 4)
    private String businessTypeCn;

    @ExcelProperty(value = "方法名称",index = 5)
    private String methodName;

    @ExcelProperty(value = "方法中文名称",index = 6)
    private String methodCnName;

    @ExcelProperty(value = "请求方法",index = 7)
    private String requestMethod;

    @ExcelProperty(value = "方法请求参数",index = 8)
    private String reqParam;

    @ExcelProperty(value = "方法返回结果",index = 9)
    private String respResult;

    @ExcelProperty(value = "服务类型",index = 10)
    private String serviceTypeCn;

    @ExcelProperty(value = "请求地址",index = 11)
    private String url;

    @ExcelProperty(value = "操作者IP",index = 12)
    private String ip;

    @ExcelProperty(value = "操作者地点(内网IP/外网IP)",index = 13)
    private String location;

    @ExcelProperty(value = "错误信息",index = 14)
    private String errorMsg;

    @ExcelProperty(value = "操作者姓名",index = 15)
    private String operatorName;

    @ExcelProperty(value = "操作者电话",index = 16)
    private String operatorMobile;

    @ExcelProperty(value = "区域码",index = 17)
    private String regionCode;

    @ExcelProperty(value = "区域",index = 18)
    private String regionName;
}
