package com.zy.seckill.inventory.bo.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * 定时任务
 */
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class TimeTask {

	@ApiModelProperty(value = "主键")
	private Long id;

	@ApiModelProperty(value = "任务类型(详见字典)")
	private Long type;

	@ApiModelProperty(value = "项目ID")
	private Long projectId;

	@ApiModelProperty(value = "名称名称")
	private String projectName;

	@ApiModelProperty(value = "xxl-job-admin中的任务ID")
	private String jobId;

	@ApiModelProperty(value = "xxl-job-admin的服务器地址")
	private String xxlJobAdminAddress;

	@ApiModelProperty(value = "cron表达式")
	private String corn;

	@ApiModelProperty(value = "处理器")
	private String handler;

	@ApiModelProperty(value = "appName")
	private String appName;

	@ApiModelProperty(value = "创建时间")
	private Long createTime;
}
