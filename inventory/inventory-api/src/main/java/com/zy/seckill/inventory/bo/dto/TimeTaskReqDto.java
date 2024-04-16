package com.zy.seckill.inventory.bo.dto;


import com.zy.seckill.common.bo.dto.BaseDto;
import com.zy.seckill.common.valid.ValidationGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;


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
public class TimeTaskReqDto extends BaseDto {

    @ApiModelProperty(name = "id", value = "主键", example = "xxx",dataType="Long")
    @NotNull(message = "主键不能为空",groups = ValidationGroup.ValidationUpdate.class)
    private Long id;

 

    @ApiModelProperty(name = "projectId", value = "项目ID", example = "xxx",dataType="Long")
    @NotNull(message = "项目ID不能为空")
    private Long projectId;
 

    @ApiModelProperty(name = "projectName", value = "名称名称", example = "xxx",dataType="String")
    @NotNull(message = "名称名称不能为空")
    private String projectName;
 

    @ApiModelProperty(name = "jobId", value = "xxl-job-admin中的任务ID", example = "xxx",dataType="String")
    @NotNull(message = "xxl-job-admin中的任务ID不能为空")
    private String jobId;
 

    @ApiModelProperty(name = "xxlJobAdminAddress", value = "xxl-job-admin的服务器地址", example = "xxx",dataType="String")
    @NotNull(message = "xxl-job-admin的服务器地址不能为空")
    private String xxlJobAdminAddress;
 

    @ApiModelProperty(name = "corn", value = "cron表达式", example = "xxx",dataType="String")
    @NotNull(message = "cron表达式不能为空")
    private String corn;
 

    @ApiModelProperty(name = "handler", value = "处理器", example = "xxx",dataType="String")
    @NotNull(message = "处理器不能为空")
    private String handler;
 

    @ApiModelProperty(name = "appName", value = "appName", example = "xxx",dataType="String")
    @NotNull(message = "appName不能为空")
    private String appName;
 

 

}