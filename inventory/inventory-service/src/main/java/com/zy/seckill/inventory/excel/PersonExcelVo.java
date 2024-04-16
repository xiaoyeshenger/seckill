package com.zy.seckill.inventory.excel;


import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;

import javax.validation.constraints.NotNull;


/*
 * @Author zhangyong
 * @Description //VaccinationPersonExcelVo
 * @Date 2024/02/02 16:07
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
public class PersonExcelVo {

    @ExcelProperty(value = "序号",index = 0)
    private Integer order;

    @ExcelProperty(value = "主键",index = 1)
    @ApiModelProperty(name = "id", value = "主键", example = "xxx",dataType="Long")
    @NotNull(message = "主键不能为空")
    private Long id;
    

    @ExcelProperty(value = "姓名",index = 1)
    @ApiModelProperty(name = "name", value = "姓名", example = "xxx",dataType="String")
    @NotNull(message = "姓名不能为空")
    private String name;
    

    @ExcelProperty(value = "性别",index = 1)
    @ApiModelProperty(name = "sex", value = "性别", example = "xxx",dataType="Byte")
    @NotNull(message = "性别不能为空")
    private Byte sex;
    

    @ExcelProperty(value = "年龄",index = 1)
    @ApiModelProperty(name = "age", value = "年龄", example = "xxx",dataType="Integer")
    @NotNull(message = "年龄不能为空")
    private Integer age;
    

    @ExcelProperty(value = "联系电话",index = 1)
    @ApiModelProperty(name = "mobile", value = "联系电话", example = "xxx",dataType="String")
    @NotNull(message = "联系电话不能为空")
    private String mobile;
    

    @ExcelProperty(value = "身份证号码",index = 1)
    @ApiModelProperty(name = "idNumber", value = "身份证号码", example = "xxx",dataType="String")
    @NotNull(message = "身份证号码不能为空")
    private String idNumber;
    

    @ExcelProperty(value = "openId",index = 1)
    @ApiModelProperty(name = "openId", value = "openId", example = "xxx",dataType="String")
    @NotNull(message = "openId不能为空")
    private String openId;
    

    @ExcelProperty(value = "通讯地址",index = 1)
    @ApiModelProperty(name = "address", value = "通讯地址", example = "xxx",dataType="String")
    @NotNull(message = "通讯地址不能为空")
    private String address;
    

    @ExcelProperty(value = "工作单位",index = 1)
    @ApiModelProperty(name = "workUnit", value = "工作单位", example = "xxx",dataType="String")
    @NotNull(message = "工作单位不能为空")
    private String workUnit;
    

    @ExcelProperty(value = "人员类型",index = 1)
    @ApiModelProperty(name = "personType", value = "人员类型", example = "xxx",dataType="Long")
    @NotNull(message = "人员类型不能为空")
    private Long personType;
    

    @ExcelProperty(value = "疫苗接种状态(从未接种/接种中/接种完成)",index = 1)
    @ApiModelProperty(name = "doseStatus", value = "疫苗接种状态(从未接种/接种中/接种完成)", example = "xxx",dataType="Long")
    @NotNull(message = "疫苗接种状态(从未接种/接种中/接种完成)不能为空")
    private Long doseStatus;
    

    @ExcelProperty(value = "第一剂接种疫苗ID",index = 1)
    @ApiModelProperty(name = "firstDoseId", value = "第一剂接种疫苗ID", example = "xxx",dataType="String")
    @NotNull(message = "第一剂接种疫苗ID不能为空")
    private String firstDoseId;
    

    @ExcelProperty(value = "第一剂接种疫苗名称",index = 1)
    @ApiModelProperty(name = "firstDoseName", value = "第一剂接种疫苗名称", example = "xxx",dataType="String")
    @NotNull(message = "第一剂接种疫苗名称不能为空")
    private String firstDoseName;
    

    @ExcelProperty(value = "第一剂接种单位",index = 1)
    @ApiModelProperty(name = "firstDoseUnit", value = "第一剂接种单位", example = "xxx",dataType="String")
    @NotNull(message = "第一剂接种单位不能为空")
    private String firstDoseUnit;
    

    @ExcelProperty(value = "第一剂接种时间",index = 1)
    @ApiModelProperty(name = "firstDoseTime", value = "第一剂接种时间", example = "xxx",dataType="Long")
    @NotNull(message = "第一剂接种时间不能为空")
    private Long firstDoseTime;
    

    @ExcelProperty(value = "最近剂次接种疫苗ID",index = 1)
    @ApiModelProperty(name = "latestDoseId", value = "最近剂次接种疫苗ID", example = "xxx",dataType="String")
    @NotNull(message = "最近剂次接种疫苗ID不能为空")
    private String latestDoseId;
    

    @ExcelProperty(value = "最近剂次接种疫苗名称",index = 1)
    @ApiModelProperty(name = "latestDoseName", value = "最近剂次接种疫苗名称", example = "xxx",dataType="String")
    @NotNull(message = "最近剂次接种疫苗名称不能为空")
    private String latestDoseName;
    

    @ExcelProperty(value = "最近剂次接种单位",index = 1)
    @ApiModelProperty(name = "latestDoseUnit", value = "最近剂次接种单位", example = "xxx",dataType="String")
    @NotNull(message = "最近剂次接种单位不能为空")
    private String latestDoseUnit;
    

    @ExcelProperty(value = "最近剂次接种时间",index = 1)
    @ApiModelProperty(name = "latestDoseTime", value = "最近剂次接种时间", example = "xxx",dataType="Long")
    @NotNull(message = "最近剂次接种时间不能为空")
    private Long latestDoseTime;
    

    @ExcelProperty(value = "创建时间",index = 1)
    @ApiModelProperty(name = "createTime", value = "创建时间", example = "xxx",dataType="Long")
    @NotNull(message = "创建时间不能为空")
    private Long createTime;
    

    @ExcelProperty(value = "更新时间",index = 1)
    @ApiModelProperty(name = "updateTime", value = "更新时间", example = "xxx",dataType="Long")
    @NotNull(message = "更新时间不能为空")
    private Long updateTime;
    

}