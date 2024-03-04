package com.jsxa.vapp.sys.excel;


import com.jsxa.vapp.common.bo.dto.BaseDto;
import com.jsxa.vapp.common.valid.ValidationGroup;
import com.jsxa.vapp.common.annotation.FieldDupValid;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.Tolerate;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.util.List;


/*
 * @Author zhangyong
 * @Description //SysFileExcelVo
 * @Date 2022/07/29 11:14
 * @Param
 * @return
 **/
@Getter
@Setter
@Builder
@ContentRowHeight(20)
@HeadRowHeight(40)
@ColumnWidth(20)
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class SysFileExcelVo {

    @ExcelProperty(value = "序号",index = 0)
    private Integer order;


    @ExcelProperty(value = "ID",index = 1)
    @ApiModelProperty(name = "id", value = "主键", example = "12",dataType="Long")
    @NotNull(message = "主键不能为空")
    private Long id;
    

    @ExcelProperty(value = "名称",index = 2)
    @ApiModelProperty(name = "name", value = "名称", example = "xxx",dataType="String")
    @NotNull(message = "名称不能为空")
    private String name;
    

    @ExcelProperty(value = "文件类型",index = 3)
    @ApiModelProperty(name = "typeCn", value = "文件类型", example = "图标",dataType="String")
    @NotNull(message = "文件类型(图标/excel导入模板/word导入模板/pdf导入模板/音频/视频)不能为空")
    private String typeCn;
    

    @ExcelProperty(value = "文件名称",index = 4)
    @ApiModelProperty(name = "fileName", value = "文件名称", example = "xxx",dataType="String")
    @NotNull(message = "文件名称不能为空")
    private String fileName;
    

    @ExcelProperty(value = "文件键名称",index = 5)
    @ApiModelProperty(name = "fileKey", value = "文件键名称", example = "xxx",dataType="String")
    @NotNull(message = "文件键名称不能为空")
    private String fileKey;
    

    @ExcelProperty(value = "访问路径",index = 6)
    @ApiModelProperty(name = "accessPath", value = "访问路径", example = "xxx",dataType="String")
    @NotNull(message = "访问路径不能为空")
    private String accessPath;

    @ExcelProperty(value = "源文件名",index = 7)
    @ApiModelProperty(name = "oriFileName", value = "源文件名", example = "xxx",dataType="String")
    @NotNull(message = "源文件名不能为空")
    private String oriFileName;


}