package com.zy.seckill.inventory.bo.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * xxl-job info
 */
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class XxlJobInfo {

	@ApiModelProperty(value = "ID")
	private int id;

	@ApiModelProperty(value = "执行器ID")
	private int jobGroup;

	@ApiModelProperty(value = "任务描述")
	private String jobDesc;


	private Date addTime;

	private Date updateTime;

	@ApiModelProperty(value = "负责人")
	private String author;

	@ApiModelProperty(value = "接收告警消息的邮箱")
	private String alarmEmail;

	@ApiModelProperty(value = "调度类型")
	private String scheduleType;

	@ApiModelProperty(value = "调度配置，值含义取决于调度类型")
	private String scheduleConf;

	@ApiModelProperty(value = "调度过期策略")
	private String misfireStrategy;

	@ApiModelProperty(value = "执行器路由策略")
	private String executorRouteStrategy;

	@ApiModelProperty(value = "执行器，任务Handler名称")
	private String executorHandler;

	@ApiModelProperty(value = "执行器，任务参数")
	private String executorParam;

	@ApiModelProperty(value = "阻塞处理策略")
	private String executorBlockStrategy;

	@ApiModelProperty(value = "任务执行超时时间，单位秒")
	private int executorTimeout;

	@ApiModelProperty(value = "失败重试次数")
	private int executorFailRetryCount;

	@ApiModelProperty(value = "GLUE类型 #com.xxl.job.core.glue.GlueTypeEnum")
	private String glueType;

	@ApiModelProperty(value = "GLUE源代码")
	private String glueSource;

	@ApiModelProperty(value = "GLUE备注")
	private String glueRemark;

	@ApiModelProperty(value = "GLUE更新时间")
	private Date glueUpdatetime;

	@ApiModelProperty(value = "子任务ID，多个逗号分隔")
	private String childJobId;

	@ApiModelProperty(value = "调度状态：0-停止，1-运行")
	private int triggerStatus;

	@ApiModelProperty(value = "上次调度时间")
	private long triggerLastTime;


	@ApiModelProperty(value = "下次调度时间")
	private long triggerNextTime;

}
