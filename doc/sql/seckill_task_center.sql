/*
Navicat MySQL Data Transfer

Source Server         : proxysql_241_6033
Source Server Version : 80027
Source Host           : 192.168.2.241:6033
Source Database       : seckill_task_center

Target Server Type    : MYSQL
Target Server Version : 80027
File Encoding         : 65001

Date: 2024-04-16 18:52:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for xxl_job_group
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_group`;
CREATE TABLE `xxl_job_group` (
  `id` int NOT NULL AUTO_INCREMENT,
  `app_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '执行器AppName',
  `title` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '执行器名称',
  `address_type` tinyint NOT NULL DEFAULT '0' COMMENT '执行器地址类型：0=自动注册、1=手动录入',
  `address_list` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '执行器地址列表，多地址逗号分隔',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of xxl_job_group
-- ----------------------------

-- ----------------------------
-- Table structure for xxl_job_info
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_info`;
CREATE TABLE `xxl_job_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `job_group` int NOT NULL COMMENT '执行器主键ID',
  `job_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `add_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `author` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '作者',
  `alarm_email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '报警邮件',
  `schedule_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'NONE' COMMENT '调度类型',
  `schedule_conf` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '调度配置，值含义取决于调度类型',
  `misfire_strategy` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'DO_NOTHING' COMMENT '调度过期策略',
  `executor_route_strategy` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '执行器路由策略',
  `executor_handler` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '执行器任务handler',
  `executor_param` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '执行器任务参数',
  `executor_block_strategy` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '阻塞处理策略',
  `executor_timeout` int NOT NULL DEFAULT '0' COMMENT '任务执行超时时间，单位秒',
  `executor_fail_retry_count` int NOT NULL DEFAULT '0' COMMENT '失败重试次数',
  `glue_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'GLUE类型',
  `glue_source` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT 'GLUE源代码',
  `glue_remark` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'GLUE备注',
  `glue_updatetime` datetime DEFAULT NULL COMMENT 'GLUE更新时间',
  `child_jobid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '子任务ID，多个逗号分隔',
  `trigger_status` tinyint NOT NULL DEFAULT '0' COMMENT '调度状态：0-停止，1-运行',
  `trigger_last_time` bigint NOT NULL DEFAULT '0' COMMENT '上次调度时间',
  `trigger_next_time` bigint NOT NULL DEFAULT '0' COMMENT '下次调度时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=142 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of xxl_job_info
-- ----------------------------

-- ----------------------------
-- Table structure for xxl_job_lock
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_lock`;
CREATE TABLE `xxl_job_lock` (
  `lock_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '锁名称',
  PRIMARY KEY (`lock_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of xxl_job_lock
-- ----------------------------
INSERT INTO `xxl_job_lock` VALUES ('schedule_lock');

-- ----------------------------
-- Table structure for xxl_job_log
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_log`;
CREATE TABLE `xxl_job_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `job_group` int NOT NULL COMMENT '执行器主键ID',
  `job_id` int NOT NULL COMMENT '任务，主键ID',
  `executor_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '执行器地址，本次执行的地址',
  `executor_handler` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '执行器任务handler',
  `executor_param` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '执行器任务参数',
  `executor_sharding_param` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '执行器任务分片参数，格式如 1/2',
  `executor_fail_retry_count` int NOT NULL DEFAULT '0' COMMENT '失败重试次数',
  `trigger_time` datetime DEFAULT NULL COMMENT '调度-时间',
  `trigger_code` int NOT NULL COMMENT '调度-结果',
  `trigger_msg` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '调度-日志',
  `handle_time` datetime DEFAULT NULL COMMENT '执行-时间',
  `handle_code` int NOT NULL COMMENT '执行-状态',
  `handle_msg` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '执行-日志',
  `alarm_status` tinyint NOT NULL DEFAULT '0' COMMENT '告警状态：0-默认、1-无需告警、2-告警成功、3-告警失败',
  PRIMARY KEY (`id`),
  KEY `I_trigger_time` (`trigger_time`) USING BTREE,
  KEY `I_handle_code` (`handle_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of xxl_job_log
-- ----------------------------
INSERT INTO `xxl_job_log` VALUES ('3099', '2', '140', 'http://192.168.2.230:9998/', 'updateVaccineReleaseStatusByRtJobHandler', '{\"vaccineReleaseId\":1}', null, '0', '2024-03-05 09:14:00', '200', '任务触发类型：Cron触发<br>调度机器：192.168.2.230<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://192.168.2.230:9998/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://192.168.2.230:9998/<br>code：200<br>msg：null', '2024-03-05 09:14:00', '200', '', '0');
INSERT INTO `xxl_job_log` VALUES ('3100', '2', '141', 'http://192.168.2.230:9998/', 'updateVaccineReleaseStatusByRtJobHandler', '{\"vaccineReleaseId\":1}', null, '0', '2024-03-05 14:28:00', '200', '任务触发类型：Cron触发<br>调度机器：192.168.2.230<br>执行器-注册方式：自动注册<br>执行器-地址列表：[http://192.168.2.230:9998/]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://192.168.2.230:9998/<br>code：200<br>msg：null', '2024-03-05 14:28:00', '200', '', '0');

-- ----------------------------
-- Table structure for xxl_job_log_report
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_log_report`;
CREATE TABLE `xxl_job_log_report` (
  `id` int NOT NULL AUTO_INCREMENT,
  `trigger_day` datetime DEFAULT NULL COMMENT '调度-时间',
  `running_count` int NOT NULL DEFAULT '0' COMMENT '运行中-日志数量',
  `suc_count` int NOT NULL DEFAULT '0' COMMENT '执行成功-日志数量',
  `fail_count` int NOT NULL DEFAULT '0' COMMENT '执行失败-日志数量',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `i_trigger_day` (`trigger_day`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of xxl_job_log_report
-- ----------------------------
INSERT INTO `xxl_job_log_report` VALUES ('1', '2023-12-26 00:00:00', '0', '0', '0', null);
INSERT INTO `xxl_job_log_report` VALUES ('2', '2023-12-25 00:00:00', '0', '0', '0', null);
INSERT INTO `xxl_job_log_report` VALUES ('3', '2023-12-24 00:00:00', '0', '0', '0', null);
INSERT INTO `xxl_job_log_report` VALUES ('4', '2023-12-27 00:00:00', '0', '0', '0', null);
INSERT INTO `xxl_job_log_report` VALUES ('5', '2023-12-28 00:00:00', '0', '14', '2', null);
INSERT INTO `xxl_job_log_report` VALUES ('6', '2023-12-29 00:00:00', '0', '0', '0', null);
INSERT INTO `xxl_job_log_report` VALUES ('7', '2023-12-30 00:00:00', '0', '0', '0', null);
INSERT INTO `xxl_job_log_report` VALUES ('8', '2023-12-31 00:00:00', '0', '11', '0', null);
INSERT INTO `xxl_job_log_report` VALUES ('9', '2024-01-01 00:00:00', '0', '0', '0', null);
INSERT INTO `xxl_job_log_report` VALUES ('10', '2024-01-02 00:00:00', '0', '4', '0', null);
INSERT INTO `xxl_job_log_report` VALUES ('11', '2024-01-03 00:00:00', '0', '3', '0', null);
INSERT INTO `xxl_job_log_report` VALUES ('12', '2024-01-04 00:00:00', '0', '0', '0', null);
INSERT INTO `xxl_job_log_report` VALUES ('13', '2024-01-05 00:00:00', '0', '6', '0', null);
INSERT INTO `xxl_job_log_report` VALUES ('14', '2024-01-06 00:00:00', '0', '0', '0', null);
INSERT INTO `xxl_job_log_report` VALUES ('15', '2024-01-07 00:00:00', '0', '0', '0', null);
INSERT INTO `xxl_job_log_report` VALUES ('16', '2024-01-08 00:00:00', '0', '2', '0', null);
INSERT INTO `xxl_job_log_report` VALUES ('17', '2024-01-09 00:00:00', '0', '0', '0', null);
INSERT INTO `xxl_job_log_report` VALUES ('18', '2024-01-10 00:00:00', '0', '0', '0', null);
INSERT INTO `xxl_job_log_report` VALUES ('19', '2024-01-11 00:00:00', '0', '0', '0', null);
INSERT INTO `xxl_job_log_report` VALUES ('20', '2024-01-12 00:00:00', '0', '2', '10', null);
INSERT INTO `xxl_job_log_report` VALUES ('21', '2024-01-16 00:00:00', '0', '6', '0', null);
INSERT INTO `xxl_job_log_report` VALUES ('22', '2024-01-15 00:00:00', '0', '18', '0', null);
INSERT INTO `xxl_job_log_report` VALUES ('23', '2024-01-14 00:00:00', '0', '0', '0', null);
INSERT INTO `xxl_job_log_report` VALUES ('24', '2024-01-17 00:00:00', '0', '0', '0', null);
INSERT INTO `xxl_job_log_report` VALUES ('25', '2024-01-18 00:00:00', '0', '2', '6', null);
INSERT INTO `xxl_job_log_report` VALUES ('26', '2024-01-19 00:00:00', '0', '0', '3', null);
INSERT INTO `xxl_job_log_report` VALUES ('27', '2024-01-20 00:00:00', '0', '0', '0', null);
INSERT INTO `xxl_job_log_report` VALUES ('28', '2024-01-21 00:00:00', '0', '1', '0', null);
INSERT INTO `xxl_job_log_report` VALUES ('29', '2024-01-22 00:00:00', '0', '2', '0', null);
INSERT INTO `xxl_job_log_report` VALUES ('30', '2024-01-23 00:00:00', '0', '0', '0', null);
INSERT INTO `xxl_job_log_report` VALUES ('31', '2024-01-24 00:00:00', '0', '0', '0', null);
INSERT INTO `xxl_job_log_report` VALUES ('32', '2024-01-25 00:00:00', '0', '1', '0', null);
INSERT INTO `xxl_job_log_report` VALUES ('33', '2024-01-26 00:00:00', '0', '0', '0', null);
INSERT INTO `xxl_job_log_report` VALUES ('34', '2024-01-27 00:00:00', '0', '0', '0', null);
INSERT INTO `xxl_job_log_report` VALUES ('35', '2024-01-28 00:00:00', '0', '0', '0', null);
INSERT INTO `xxl_job_log_report` VALUES ('36', '2024-01-29 00:00:00', '0', '4', '0', null);
INSERT INTO `xxl_job_log_report` VALUES ('37', '2024-02-27 00:00:00', '0', '1', '0', null);
INSERT INTO `xxl_job_log_report` VALUES ('38', '2024-02-26 00:00:00', '0', '0', '0', null);
INSERT INTO `xxl_job_log_report` VALUES ('39', '2024-02-25 00:00:00', '0', '0', '0', null);
INSERT INTO `xxl_job_log_report` VALUES ('40', '2024-02-28 00:00:00', '0', '0', '0', null);
INSERT INTO `xxl_job_log_report` VALUES ('41', '2024-03-04 00:00:00', '0', '0', '0', null);
INSERT INTO `xxl_job_log_report` VALUES ('42', '2024-03-03 00:00:00', '0', '0', '0', null);
INSERT INTO `xxl_job_log_report` VALUES ('43', '2024-03-02 00:00:00', '0', '0', '0', null);
INSERT INTO `xxl_job_log_report` VALUES ('44', '2024-03-05 00:00:00', '0', '2', '0', null);
INSERT INTO `xxl_job_log_report` VALUES ('45', '2024-03-06 00:00:00', '0', '0', '0', null);
INSERT INTO `xxl_job_log_report` VALUES ('46', '2024-03-07 00:00:00', '0', '0', '0', null);
INSERT INTO `xxl_job_log_report` VALUES ('47', '2024-03-08 00:00:00', '0', '0', '0', null);
INSERT INTO `xxl_job_log_report` VALUES ('48', '2024-03-09 00:00:00', '0', '0', '0', null);
INSERT INTO `xxl_job_log_report` VALUES ('49', '2024-03-10 00:00:00', '0', '0', '0', null);
INSERT INTO `xxl_job_log_report` VALUES ('50', '2024-03-11 00:00:00', '0', '0', '0', null);

-- ----------------------------
-- Table structure for xxl_job_logglue
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_logglue`;
CREATE TABLE `xxl_job_logglue` (
  `id` int NOT NULL AUTO_INCREMENT,
  `job_id` int NOT NULL COMMENT '任务，主键ID',
  `glue_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'GLUE类型',
  `glue_source` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT 'GLUE源代码',
  `glue_remark` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'GLUE备注',
  `add_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of xxl_job_logglue
-- ----------------------------

-- ----------------------------
-- Table structure for xxl_job_registry
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_registry`;
CREATE TABLE `xxl_job_registry` (
  `id` int NOT NULL AUTO_INCREMENT,
  `registry_group` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `registry_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `registry_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `i_g_k_v` (`registry_group`,`registry_key`,`registry_value`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=271 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of xxl_job_registry
-- ----------------------------
INSERT INTO `xxl_job_registry` VALUES ('270', 'EXECUTOR', 'inventory-service', 'http://192.168.2.230:9998/', '2024-03-11 10:21:18');

-- ----------------------------
-- Table structure for xxl_job_user
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_user`;
CREATE TABLE `xxl_job_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '账号',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `role` tinyint NOT NULL COMMENT '角色：0-普通用户、1-管理员',
  `permission` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '权限：执行器ID列表，多个逗号分割',
  PRIMARY KEY (`id`),
  UNIQUE KEY `i_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of xxl_job_user
-- ----------------------------
INSERT INTO `xxl_job_user` VALUES ('1', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '1', null);
