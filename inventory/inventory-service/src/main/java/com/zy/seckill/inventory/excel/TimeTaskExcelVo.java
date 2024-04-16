package com.zy.seckill.inventory.excel;


import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;


/*
 * @Author zhangyong
 * @Description //TimeTaskExcelVo
 * @Date 2023/12/27 17:02
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
public class TimeTaskExcelVo {

    @ExcelProperty(value = "序号",index = 0)
    private Integer order;

    @ExcelProperty(value = "主键",index = 1)
    @ApiModelProperty(name = "id", value = "主键", example = "xxx",dataType="Long")
    @NotNull(message = "主键不能为空")
    private Long id;
    

    @ExcelProperty(value = "项目ID",index = 1)
    @ApiModelProperty(name = "projectId", value = "项目ID", example = "xxx",dataType="Long")
    @NotNull(message = "项目ID不能为空")
    private Long projectId;
    

    @ExcelProperty(value = "名称名称",index = 1)
    @ApiModelProperty(name = "projectName", value = "名称名称", example = "xxx",dataType="String")
    @NotNull(message = "名称名称不能为空")
    private String projectName;
    

    @ExcelProperty(value = "xxl-job-admin中的任务ID",index = 1)
    @ApiModelProperty(name = "jobId", value = "xxl-job-admin中的任务ID", example = "xxx",dataType="String")
    @NotNull(message = "xxl-job-admin中的任务ID不能为空")
    private String jobId;
    

    @ExcelProperty(value = "xxl-job-admin的服务器地址",index = 1)
    @ApiModelProperty(name = "xxlJobAdminAddress", value = "xxl-job-admin的服务器地址", example = "xxx",dataType="String")
    @NotNull(message = "xxl-job-admin的服务器地址不能为空")
    private String xxlJobAdminAddress;
    

    @ExcelProperty(value = "cron表达式",index = 1)
    @ApiModelProperty(name = "corn", value = "cron表达式", example = "xxx",dataType="String")
    @NotNull(message = "cron表达式不能为空")
    private String corn;
    

    @ExcelProperty(value = "处理器",index = 1)
    @ApiModelProperty(name = "handler", value = "处理器", example = "xxx",dataType="String")
    @NotNull(message = "处理器不能为空")
    private String handler;
    

    @ExcelProperty(value = "appName",index = 1)
    @ApiModelProperty(name = "appName", value = "appName", example = "xxx",dataType="String")
    @NotNull(message = "appName不能为空")
    private String appName;
    

    @ExcelProperty(value = "创建时间",index = 1)
    @ApiModelProperty(name = "createTime", value = "创建时间", example = "xxx",dataType="Long")
    @NotNull(message = "创建时间不能为空")
    private Long createTime;
    

}