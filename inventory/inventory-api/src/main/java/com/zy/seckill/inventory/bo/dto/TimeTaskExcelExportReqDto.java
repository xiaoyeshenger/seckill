package com.zy.seckill.inventory.bo.dto;


import com.zy.seckill.common.bo.dto.BaseDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;


/*
 * @Author zhangyong
 * @Description //TimeTaskReqDto
 * @Date 2023/12/27 17:02
 * @Param
 * @return
 **/
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class TimeTaskExcelExportReqDto extends BaseDto {

    @ApiModelProperty(name = "id", value = "主键", example = "xxx",dataType="Long")
    private Long id;
    

    @ApiModelProperty(name = "projectId", value = "项目ID", example = "xxx",dataType="Long")
    private Long projectId;
    

    @ApiModelProperty(name = "projectName", value = "名称名称", example = "xxx",dataType="String")
    private String projectName;
    

    @ApiModelProperty(name = "jobId", value = "xxl-job-admin中的任务ID", example = "xxx",dataType="String")
    private String jobId;
    

    @ApiModelProperty(name = "xxlJobAdminAddress", value = "xxl-job-admin的服务器地址", example = "xxx",dataType="String")
    private String xxlJobAdminAddress;
    

    @ApiModelProperty(name = "corn", value = "cron表达式", example = "xxx",dataType="String")
    private String corn;
    

    @ApiModelProperty(name = "handler", value = "处理器", example = "xxx",dataType="String")
    private String handler;
    

    @ApiModelProperty(name = "appName", value = "appName", example = "xxx",dataType="String")
    private String appName;
    

    @ApiModelProperty(name = "createTime", value = "创建时间", example = "xxx",dataType="Long")
    private Long createTime;
    

}