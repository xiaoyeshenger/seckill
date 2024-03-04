package com.jsxa.vapp.sys.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Tolerate;

import javax.validation.constraints.NotNull;

//小区excel表格导入对象
@Getter
@Setter
@Builder
@ContentRowHeight(20)
@HeadRowHeight(40)
@ColumnWidth(20)
public class RegionExcelVo {

    @Tolerate
    public RegionExcelVo() {}

    @ExcelProperty(value = "小区ID",index = 0)
    private String cid;

    @ExcelProperty(value = "小区名称",index = 1)
    @NotNull(message = "小区名称不能为空")
    private String name;


    @ExcelProperty( value = "小区地址",index = 2)
    @NotNull(message = "小区地址不能为空")
    private String address;

    @ExcelProperty(value = "所属街道ID",index = 3)
    @NotNull(message = "所属街道ID不能为空")
    private String belongTid;

    @ExcelProperty(value = "所属社区ID",index = 4)
    @NotNull(message = "所属社区ID不能为空")
    private String belongVid;
}
