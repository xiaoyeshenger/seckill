package com.jsxa.vapp.inventory.bo.dto;


import com.jsxa.vapp.common.bo.dto.PageReqDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


/*
 * @Author zhangyong
 * @Description //TimeTaskPageReqDto
 * @Date 2023/12/27 17:02
 * @Param
 * @return
 **/
@Getter
@Setter
@Accessors(chain=true)
public class TimeTaskPageReqDto extends PageReqDto {



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




}