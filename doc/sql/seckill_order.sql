/*
Navicat MySQL Data Transfer

Source Server         : proxysql_241_6033
Source Server Version : 80027
Source Host           : 192.168.2.241:6033
Source Database       : seckill_order

Target Server Type    : MYSQL
Target Server Version : 80027
File Encoding         : 65001

Date: 2024-04-16 18:51:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for order_record0
-- ----------------------------
DROP TABLE IF EXISTS `order_record0`;
CREATE TABLE `order_record0` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `record_type` bigint DEFAULT NULL,
  `productRelease_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `productRelease_id` bigint DEFAULT NULL,
  `person_id` bigint DEFAULT NULL,
  `person_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sex` tinyint DEFAULT NULL,
  `age` int DEFAULT NULL,
  `mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `id_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `open_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `site_id` bigint DEFAULT NULL,
  `site_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `record_status` bigint DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  `product_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `product_batch` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `manufacturer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `appointment_time` bigint DEFAULT NULL,
  `image_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `msg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1803550721 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of order_record0
-- ----------------------------

-- ----------------------------
-- Table structure for order_record1
-- ----------------------------
DROP TABLE IF EXISTS `order_record1`;
CREATE TABLE `order_record1` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `record_type` bigint DEFAULT NULL,
  `productRelease_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `productRelease_id` bigint DEFAULT NULL,
  `person_id` bigint DEFAULT NULL,
  `person_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sex` tinyint DEFAULT NULL,
  `age` int DEFAULT NULL,
  `mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `id_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `open_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `site_id` bigint DEFAULT NULL,
  `site_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `record_status` bigint DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  `product_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `product_batch` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `manufacturer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `appointment_time` bigint DEFAULT NULL,
  `image_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `msg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1803550721 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of order_record1
-- ----------------------------

-- ----------------------------
-- Table structure for order_record2
-- ----------------------------
DROP TABLE IF EXISTS `order_record2`;
CREATE TABLE `order_record2` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `record_type` bigint DEFAULT NULL,
  `productRelease_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `productRelease_id` bigint DEFAULT NULL,
  `person_id` bigint DEFAULT NULL,
  `person_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sex` tinyint DEFAULT NULL,
  `age` int DEFAULT NULL,
  `mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `id_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `open_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `site_id` bigint DEFAULT NULL,
  `site_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `record_status` bigint DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  `product_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `product_batch` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `manufacturer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `appointment_time` bigint DEFAULT NULL,
  `image_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `msg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1803550721 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of order_record2
-- ----------------------------

-- ----------------------------
-- Table structure for order_record3
-- ----------------------------
DROP TABLE IF EXISTS `order_record3`;
CREATE TABLE `order_record3` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `record_type` bigint DEFAULT NULL,
  `productRelease_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `productRelease_id` bigint DEFAULT NULL,
  `person_id` bigint DEFAULT NULL,
  `person_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sex` tinyint DEFAULT NULL,
  `age` int DEFAULT NULL,
  `mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `id_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `open_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `site_id` bigint DEFAULT NULL,
  `site_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `record_status` bigint DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  `product_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `product_batch` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `manufacturer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `appointment_time` bigint DEFAULT NULL,
  `image_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `msg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1803550721 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of order_record3
-- ----------------------------

-- ----------------------------
-- Table structure for order_record4
-- ----------------------------
DROP TABLE IF EXISTS `order_record4`;
CREATE TABLE `order_record4` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `record_type` bigint DEFAULT NULL,
  `productRelease_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `productRelease_id` bigint DEFAULT NULL,
  `person_id` bigint DEFAULT NULL,
  `person_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sex` tinyint DEFAULT NULL,
  `age` int DEFAULT NULL,
  `mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `id_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `open_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `site_id` bigint DEFAULT NULL,
  `site_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `record_status` bigint DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  `product_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `product_batch` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `manufacturer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `appointment_time` bigint DEFAULT NULL,
  `image_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `msg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1803550721 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of order_record4
-- ----------------------------

-- ----------------------------
-- Table structure for order_record5
-- ----------------------------
DROP TABLE IF EXISTS `order_record5`;
CREATE TABLE `order_record5` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `record_type` bigint DEFAULT NULL,
  `productRelease_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `productRelease_id` bigint DEFAULT NULL,
  `person_id` bigint DEFAULT NULL,
  `person_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sex` tinyint DEFAULT NULL,
  `age` int DEFAULT NULL,
  `mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `id_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `open_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `site_id` bigint DEFAULT NULL,
  `site_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `record_status` bigint DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  `product_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `product_batch` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `manufacturer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `appointment_time` bigint DEFAULT NULL,
  `image_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `msg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1803550721 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of order_record5
-- ----------------------------

-- ----------------------------
-- Table structure for order_record6
-- ----------------------------
DROP TABLE IF EXISTS `order_record6`;
CREATE TABLE `order_record6` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `record_type` bigint DEFAULT NULL,
  `productRelease_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `productRelease_id` bigint DEFAULT NULL,
  `person_id` bigint DEFAULT NULL,
  `person_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sex` tinyint DEFAULT NULL,
  `age` int DEFAULT NULL,
  `mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `id_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `open_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `site_id` bigint DEFAULT NULL,
  `site_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `record_status` bigint DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  `product_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `product_batch` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `manufacturer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `appointment_time` bigint DEFAULT NULL,
  `image_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `msg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1803550721 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of order_record6
-- ----------------------------

-- ----------------------------
-- Table structure for order_record7
-- ----------------------------
DROP TABLE IF EXISTS `order_record7`;
CREATE TABLE `order_record7` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `record_type` bigint DEFAULT NULL,
  `productRelease_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `productRelease_id` bigint DEFAULT NULL,
  `person_id` bigint DEFAULT NULL,
  `person_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sex` tinyint DEFAULT NULL,
  `age` int DEFAULT NULL,
  `mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `id_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `open_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `site_id` bigint DEFAULT NULL,
  `site_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `record_status` bigint DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  `product_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `product_batch` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `manufacturer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `appointment_time` bigint DEFAULT NULL,
  `image_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `msg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1803550721 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of order_record7
-- ----------------------------

-- ----------------------------
-- Table structure for order_record8
-- ----------------------------
DROP TABLE IF EXISTS `order_record8`;
CREATE TABLE `order_record8` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `record_type` bigint DEFAULT NULL,
  `productRelease_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `productRelease_id` bigint DEFAULT NULL,
  `person_id` bigint DEFAULT NULL,
  `person_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sex` tinyint DEFAULT NULL,
  `age` int DEFAULT NULL,
  `mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `id_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `open_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `site_id` bigint DEFAULT NULL,
  `site_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `record_status` bigint DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  `product_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `product_batch` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `manufacturer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `appointment_time` bigint DEFAULT NULL,
  `image_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `msg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1803550721 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of order_record8
-- ----------------------------

-- ----------------------------
-- Table structure for order_record9
-- ----------------------------
DROP TABLE IF EXISTS `order_record9`;
CREATE TABLE `order_record9` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `record_type` bigint DEFAULT NULL,
  `productRelease_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `productRelease_id` bigint DEFAULT NULL,
  `person_id` bigint DEFAULT NULL,
  `person_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sex` tinyint DEFAULT NULL,
  `age` int DEFAULT NULL,
  `mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `id_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `open_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `site_id` bigint DEFAULT NULL,
  `site_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `record_status` bigint DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  `product_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `product_batch` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `manufacturer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `appointment_time` bigint DEFAULT NULL,
  `image_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `msg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1803550721 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of order_record9
-- ----------------------------

-- ----------------------------
-- Table structure for rocket_mq_fail_msg
-- ----------------------------
DROP TABLE IF EXISTS `rocket_mq_fail_msg`;
CREATE TABLE `rocket_mq_fail_msg` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `type` bigint DEFAULT NULL,
  `obj_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `reconsume_times` int DEFAULT NULL,
  `msg_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `msg_body` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  `queue_id` int DEFAULT NULL,
  `queue_offset` bigint DEFAULT NULL,
  `commitLog_offset` bigint DEFAULT NULL,
  `broker_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `bornHost_string` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_date` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=224 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of rocket_mq_fail_msg
-- ----------------------------
INSERT INTO `rocket_mq_fail_msg` VALUES ('173', '2650', '1770736477819371520', '3', 'C0A802E68F64610644256AA2BB4200D0', '{\"id\":1770736477819371520,\"recordType\":2032,\"vaild\":1,\"vaccineReleaseId\":1,\"vaccineReleaseName\":null,\"personId\":1764897018334937088,\"personName\":\"zy1\",\"sex\":1,\"age\":29,\"mobile\":\"15208492745\",\"idNumber\":\"510121198901221219\",\"openId\":\"xcn256hb\",\"siteId\":1,\"siteName\":\"攀成钢青白江医院（教育街51号）\",\"recordStatus\":null,\"vaccineId\":1,\"vaccineName\":\"新冠病毒灭活疫苗（Vero细胞）\",\"vaccineBatch\":\"SZKT10006\",\"manufacturer\":\"深圳康泰\",\"dose\":1,\"doseUnit\":\"成都市青白江区卫健局\",\"appointmentTime\":1711011449662,\"timePeriod\":2012,\"timePeriodName\":\"08:00-09:00\",\"doseTime\":null,\"imageUrl\":null,\"city\":null,\"county\":null,\"town\":null,\"msg\":null,\"createTime\":1711011449662}', '0', '7691', '24628624', 'broker-a', '192.168.2.230', '2024-03-21 16:59:09', '1711011549741');
INSERT INTO `rocket_mq_fail_msg` VALUES ('174', '2650', '1770736523507924992', '3', 'C0A802E68F64610644256AA2E5D000EB', '{\"id\":1770736523507924992,\"recordType\":2032,\"vaild\":1,\"vaccineReleaseId\":1,\"vaccineReleaseName\":null,\"personId\":1764897018334937088,\"personName\":\"zy1\",\"sex\":1,\"age\":29,\"mobile\":\"15208492745\",\"idNumber\":\"510121198901221219\",\"openId\":\"xcn256hb\",\"siteId\":1,\"siteName\":\"攀成钢青白江医院（教育街51号）\",\"recordStatus\":null,\"vaccineId\":1,\"vaccineName\":\"新冠病毒灭活疫苗（Vero细胞）\",\"vaccineBatch\":\"SZKT10006\",\"manufacturer\":\"深圳康泰\",\"dose\":1,\"doseUnit\":\"成都市青白江区卫健局\",\"appointmentTime\":1711011460555,\"timePeriod\":2012,\"timePeriodName\":\"08:00-09:00\",\"doseTime\":null,\"imageUrl\":null,\"city\":null,\"county\":null,\"town\":null,\"msg\":null,\"createTime\":1711011460555}', '0', '7692', '24631387', 'broker-a', '192.168.2.230', '2024-03-21 16:59:20', '1711011560650');
INSERT INTO `rocket_mq_fail_msg` VALUES ('175', '2650', '1770737716233764864', '3', 'C0A802E65324610644256AA73CAB002B', '{\"id\":1770737716233764864,\"recordType\":2032,\"vaild\":1,\"vaccineReleaseId\":1,\"vaccineReleaseName\":null,\"personId\":1764897018334937088,\"personName\":\"zy1\",\"sex\":1,\"age\":29,\"mobile\":\"15208492745\",\"idNumber\":\"510121198901221219\",\"openId\":\"xcn256hb\",\"siteId\":1,\"siteName\":\"攀成钢青白江医院（教育街51号）\",\"recordStatus\":null,\"vaccineId\":1,\"vaccineName\":\"新冠病毒灭活疫苗（Vero细胞）\",\"vaccineBatch\":\"SZKT10006\",\"manufacturer\":\"深圳康泰\",\"dose\":1,\"doseUnit\":\"成都市青白江区卫健局\",\"appointmentTime\":1711011744922,\"timePeriod\":2012,\"timePeriodName\":\"08:00-09:00\",\"doseTime\":null,\"imageUrl\":null,\"city\":null,\"county\":null,\"town\":null,\"msg\":null,\"createTime\":1711011744922}', '0', '7734', '24751617', 'broker-a', '192.168.2.230', '2024-03-21 17:04:05', '1711011845043');
INSERT INTO `rocket_mq_fail_msg` VALUES ('176', '2650', '1770737737259810816', '3', 'C0A802E65324610644256AA750320032', '{\"id\":1770737737259810816,\"recordType\":2032,\"vaild\":1,\"vaccineReleaseId\":1,\"vaccineReleaseName\":null,\"personId\":1764897018334937088,\"personName\":\"zy1\",\"sex\":1,\"age\":29,\"mobile\":\"15208492745\",\"idNumber\":\"510121198901221219\",\"openId\":\"xcn256hb\",\"siteId\":1,\"siteName\":\"攀成钢青白江医院（教育街51号）\",\"recordStatus\":null,\"vaccineId\":1,\"vaccineName\":\"新冠病毒灭活疫苗（Vero细胞）\",\"vaccineBatch\":\"SZKT10006\",\"manufacturer\":\"深圳康泰\",\"dose\":1,\"doseUnit\":\"成都市青白江区卫健局\",\"appointmentTime\":1711011749936,\"timePeriod\":2012,\"timePeriodName\":\"08:00-09:00\",\"doseTime\":null,\"imageUrl\":null,\"city\":null,\"county\":null,\"town\":null,\"msg\":null,\"createTime\":1711011749936}', '0', '7735', '24754380', 'broker-a', '192.168.2.230', '2024-03-21 17:04:10', '1711011850144');
INSERT INTO `rocket_mq_fail_msg` VALUES ('177', '2650', '1770737775079849984', '3', 'C0A802E65324610644256AA7736A003D', '{\"id\":1770737775079849984,\"recordType\":2032,\"vaild\":1,\"vaccineReleaseId\":1,\"vaccineReleaseName\":null,\"personId\":1764897018334937088,\"personName\":\"zy1\",\"sex\":1,\"age\":29,\"mobile\":\"15208492745\",\"idNumber\":\"510121198901221219\",\"openId\":\"xcn256hb\",\"siteId\":1,\"siteName\":\"攀成钢青白江医院（教育街51号）\",\"recordStatus\":null,\"vaccineId\":1,\"vaccineName\":\"新冠病毒灭活疫苗（Vero细胞）\",\"vaccineBatch\":\"SZKT10006\",\"manufacturer\":\"深圳康泰\",\"dose\":1,\"doseUnit\":\"成都市青白江区卫健局\",\"appointmentTime\":1711011758953,\"timePeriod\":2012,\"timePeriodName\":\"08:00-09:00\",\"doseTime\":null,\"imageUrl\":null,\"city\":null,\"county\":null,\"town\":null,\"msg\":null,\"createTime\":1711011758953}', '0', '7737', '24759705', 'broker-a', '192.168.2.230', '2024-03-21 17:04:19', '1711011859032');
INSERT INTO `rocket_mq_fail_msg` VALUES ('178', '2650', '1770738897454628864', '3', 'C0A802E65324610644256AAB88B70128', '{\"id\":1770738897454628864,\"recordType\":2032,\"vaild\":1,\"vaccineReleaseId\":1,\"vaccineReleaseName\":null,\"personId\":1764897018334937088,\"personName\":\"zy1\",\"sex\":1,\"age\":29,\"mobile\":\"15208492745\",\"idNumber\":\"510121198901221219\",\"openId\":\"xcn256hb\",\"siteId\":1,\"siteName\":\"攀成钢青白江医院（教育街51号）\",\"recordStatus\":null,\"vaccineId\":1,\"vaccineName\":\"新冠病毒灭活疫苗（Vero细胞）\",\"vaccineBatch\":\"SZKT10006\",\"manufacturer\":\"深圳康泰\",\"dose\":1,\"doseUnit\":\"成都市青白江区卫健局\",\"appointmentTime\":1711012026548,\"timePeriod\":2012,\"timePeriodName\":\"08:00-09:00\",\"doseTime\":null,\"imageUrl\":null,\"city\":null,\"county\":null,\"town\":null,\"msg\":null,\"createTime\":1711012026548}', '0', '7819', '24997325', 'broker-a', '192.168.2.230', '2024-03-21 17:08:46', '1711012126645');
INSERT INTO `rocket_mq_fail_msg` VALUES ('179', '2650', '1770738932196048896', '3', 'C0A802E65324610644256AABA912012F', '{\"id\":1770738932196048896,\"recordType\":2032,\"vaild\":1,\"vaccineReleaseId\":1,\"vaccineReleaseName\":null,\"personId\":1764897018334937088,\"personName\":\"zy1\",\"sex\":1,\"age\":29,\"mobile\":\"15208492745\",\"idNumber\":\"510121198901221219\",\"openId\":\"xcn256hb\",\"siteId\":1,\"siteName\":\"攀成钢青白江医院（教育街51号）\",\"recordStatus\":null,\"vaccineId\":1,\"vaccineName\":\"新冠病毒灭活疫苗（Vero细胞）\",\"vaccineBatch\":\"SZKT10006\",\"manufacturer\":\"深圳康泰\",\"dose\":1,\"doseUnit\":\"成都市青白江区卫健局\",\"appointmentTime\":1711012034831,\"timePeriod\":2012,\"timePeriodName\":\"08:00-09:00\",\"doseTime\":null,\"imageUrl\":null,\"city\":null,\"county\":null,\"town\":null,\"msg\":null,\"createTime\":1711012034831}', '0', '7821', '25002650', 'broker-a', '192.168.2.230', '2024-03-21 17:08:54', '1711012134921');
INSERT INTO `rocket_mq_fail_msg` VALUES ('180', '2650', '1770738943520669696', '3', 'C0A802E65324610644256AABB39C013A', '{\"id\":1770738943520669696,\"recordType\":2032,\"vaild\":1,\"vaccineReleaseId\":1,\"vaccineReleaseName\":null,\"personId\":1764897018334937088,\"personName\":\"zy1\",\"sex\":1,\"age\":29,\"mobile\":\"15208492745\",\"idNumber\":\"510121198901221219\",\"openId\":\"xcn256hb\",\"siteId\":1,\"siteName\":\"攀成钢青白江医院（教育街51号）\",\"recordStatus\":null,\"vaccineId\":1,\"vaccineName\":\"新冠病毒灭活疫苗（Vero细胞）\",\"vaccineBatch\":\"SZKT10006\",\"manufacturer\":\"深圳康泰\",\"dose\":1,\"doseUnit\":\"成都市青白江区卫健局\",\"appointmentTime\":1711012037531,\"timePeriod\":2012,\"timePeriodName\":\"08:00-09:00\",\"doseTime\":null,\"imageUrl\":null,\"city\":null,\"county\":null,\"town\":null,\"msg\":null,\"createTime\":1711012037531}', '0', '7822', '25005413', 'broker-a', '192.168.2.230', '2024-03-21 17:08:57', '1711012137608');
INSERT INTO `rocket_mq_fail_msg` VALUES ('181', '2650', '1770738986768138240', '3', 'C0A802E65324610644256AABDBE50149', '{\"id\":1770738986768138240,\"recordType\":2032,\"vaild\":1,\"vaccineReleaseId\":1,\"vaccineReleaseName\":null,\"personId\":1764897018334937088,\"personName\":\"zy1\",\"sex\":1,\"age\":29,\"mobile\":\"15208492745\",\"idNumber\":\"510121198901221219\",\"openId\":\"xcn256hb\",\"siteId\":1,\"siteName\":\"攀成钢青白江医院（教育街51号）\",\"recordStatus\":null,\"vaccineId\":1,\"vaccineName\":\"新冠病毒灭活疫苗（Vero细胞）\",\"vaccineBatch\":\"SZKT10006\",\"manufacturer\":\"深圳康泰\",\"dose\":1,\"doseUnit\":\"成都市青白江区卫健局\",\"appointmentTime\":1711012047842,\"timePeriod\":2012,\"timePeriodName\":\"08:00-09:00\",\"doseTime\":null,\"imageUrl\":null,\"city\":null,\"county\":null,\"town\":null,\"msg\":null,\"createTime\":1711012047842}', '0', '7824', '25010938', 'broker-a', '192.168.2.230', '2024-03-21 17:09:09', '1711012149949');
INSERT INTO `rocket_mq_fail_msg` VALUES ('182', '2650', '1770739136068583424', '3', 'C0A802E65324610644256AAC66F001B4', '{\"id\":1770739136068583424,\"recordType\":2032,\"vaild\":1,\"vaccineReleaseId\":1,\"vaccineReleaseName\":null,\"personId\":1764897018334937088,\"personName\":\"zy1\",\"sex\":1,\"age\":29,\"mobile\":\"15208492745\",\"idNumber\":\"510121198901221219\",\"openId\":\"xcn256hb\",\"siteId\":1,\"siteName\":\"攀成钢青白江医院（教育街51号）\",\"recordStatus\":null,\"vaccineId\":1,\"vaccineName\":\"新冠病毒灭活疫苗（Vero细胞）\",\"vaccineBatch\":\"SZKT10006\",\"manufacturer\":\"深圳康泰\",\"dose\":1,\"doseUnit\":\"成都市青白江区卫健局\",\"appointmentTime\":1711012083438,\"timePeriod\":2012,\"timePeriodName\":\"08:00-09:00\",\"doseTime\":null,\"imageUrl\":null,\"city\":null,\"county\":null,\"town\":null,\"msg\":null,\"createTime\":1711012083438}', '0', '7827', '25019025', 'broker-a', '192.168.2.230', '2024-03-21 17:09:47', '1711012187533');
INSERT INTO `rocket_mq_fail_msg` VALUES ('183', '2650', '1771005031928037376', '3', 'C0A802E65344610644256E73BAD50000', '{\"id\":1771005031928037376,\"recordType\":2032,\"vaild\":1,\"vaccineReleaseId\":1,\"vaccineReleaseName\":null,\"personId\":1764897018334937088,\"personName\":\"zy1\",\"sex\":1,\"age\":29,\"mobile\":\"15208492745\",\"idNumber\":\"510121198901221219\",\"openId\":\"xcn256hb\",\"siteId\":1,\"siteName\":\"攀成钢青白江医院（教育街51号）\",\"recordStatus\":null,\"vaccineId\":1,\"vaccineName\":\"新冠病毒灭活疫苗（Vero细胞）\",\"vaccineBatch\":\"SZKT10006\",\"manufacturer\":\"深圳康泰\",\"dose\":1,\"doseUnit\":\"成都市青白江区卫健局\",\"appointmentTime\":1711075477950,\"timePeriod\":2012,\"timePeriodName\":\"08:00-09:00\",\"doseTime\":null,\"imageUrl\":null,\"city\":null,\"county\":null,\"town\":null,\"msg\":null,\"createTime\":1711075477950}', '0', '8179', '26115944', 'broker-a', '192.168.2.230', '2024-03-22 10:55:22', '1711076122017');
INSERT INTO `rocket_mq_fail_msg` VALUES ('184', '2650', '1771005031928037376', '3', 'C0A802E65344610644256E73BAD50000', '{\"id\":1771005031928037376,\"recordType\":2032,\"vaild\":1,\"vaccineReleaseId\":1,\"vaccineReleaseName\":null,\"personId\":1764897018334937088,\"personName\":\"zy1\",\"sex\":1,\"age\":29,\"mobile\":\"15208492745\",\"idNumber\":\"510121198901221219\",\"openId\":\"xcn256hb\",\"siteId\":1,\"siteName\":\"攀成钢青白江医院（教育街51号）\",\"recordStatus\":null,\"vaccineId\":1,\"vaccineName\":\"新冠病毒灭活疫苗（Vero细胞）\",\"vaccineBatch\":\"SZKT10006\",\"manufacturer\":\"深圳康泰\",\"dose\":1,\"doseUnit\":\"成都市青白江区卫健局\",\"appointmentTime\":1711075477950,\"timePeriod\":2012,\"timePeriodName\":\"08:00-09:00\",\"doseTime\":null,\"imageUrl\":null,\"city\":null,\"county\":null,\"town\":null,\"msg\":null,\"createTime\":1711075477950}', '0', '8179', '26115944', 'broker-a', '192.168.2.230', '2024-03-22 10:56:11', '1711076171420');
INSERT INTO `rocket_mq_fail_msg` VALUES ('185', '2650', '1771008811675418624', '3', 'C0A802E6256C610644256E8179FA0006', '{\"id\":1771008811675418624,\"recordType\":2032,\"vaild\":1,\"vaccineReleaseId\":1,\"vaccineReleaseName\":null,\"personId\":1764897018334937088,\"personName\":\"zy1\",\"sex\":1,\"age\":29,\"mobile\":\"15208492745\",\"idNumber\":\"510121198901221219\",\"openId\":\"xcn256hb\",\"siteId\":1,\"siteName\":\"攀成钢青白江医院（教育街51号）\",\"recordStatus\":null,\"vaccineId\":1,\"vaccineName\":\"新冠病毒灭活疫苗（Vero细胞）\",\"vaccineBatch\":\"SZKT10006\",\"manufacturer\":\"深圳康泰\",\"dose\":1,\"doseUnit\":\"成都市青白江区卫健局\",\"appointmentTime\":1711076379113,\"timePeriod\":2012,\"timePeriodName\":\"08:00-09:00\",\"doseTime\":null,\"imageUrl\":null,\"city\":null,\"county\":null,\"town\":null,\"msg\":null,\"createTime\":1711076379113}', '0', '8183', '26130322', 'broker-a', '192.168.2.230', '2024-03-22 11:02:12', '1711076532583');
INSERT INTO `rocket_mq_fail_msg` VALUES ('186', '2650', '1771011174960201728', '3', 'C0A802E69AA8610644256E8A12F80010', '{\"id\":1771011174960201728,\"recordType\":2032,\"vaild\":1,\"vaccineReleaseId\":1,\"vaccineReleaseName\":null,\"personId\":1764897018334937088,\"personName\":\"zy1\",\"sex\":1,\"age\":29,\"mobile\":\"15208492745\",\"idNumber\":\"510121198901221219\",\"openId\":\"xcn256hb\",\"siteId\":1,\"siteName\":\"攀成钢青白江医院（教育街51号）\",\"recordStatus\":null,\"vaccineId\":1,\"vaccineName\":\"新冠病毒灭活疫苗（Vero细胞）\",\"vaccineBatch\":\"SZKT10006\",\"manufacturer\":\"深圳康泰\",\"dose\":1,\"doseUnit\":\"成都市青白江区卫健局\",\"appointmentTime\":1711076942564,\"timePeriod\":2012,\"timePeriodName\":\"08:00-09:00\",\"doseTime\":null,\"imageUrl\":null,\"city\":null,\"county\":null,\"town\":null,\"msg\":null,\"createTime\":1711076942564}', '0', '8190', '26152680', 'broker-a', '192.168.2.230', '2024-03-22 11:11:10', '1711077070370');
INSERT INTO `rocket_mq_fail_msg` VALUES ('187', '2650', '1771011981977845760', '3', 'C0A802E68438610644256E8D028F0008', '{\"id\":1771011981977845760,\"recordType\":2032,\"vaild\":1,\"vaccineReleaseId\":1,\"vaccineReleaseName\":null,\"personId\":1764897018334937088,\"personName\":\"zy1\",\"sex\":1,\"age\":29,\"mobile\":\"15208492745\",\"idNumber\":\"510121198901221219\",\"openId\":\"xcn256hb\",\"siteId\":1,\"siteName\":\"攀成钢青白江医院（教育街51号）\",\"recordStatus\":null,\"vaccineId\":1,\"vaccineName\":\"新冠病毒灭活疫苗（Vero细胞）\",\"vaccineBatch\":\"SZKT10006\",\"manufacturer\":\"深圳康泰\",\"dose\":1,\"doseUnit\":\"成都市青白江区卫健局\",\"appointmentTime\":1711077134972,\"timePeriod\":2012,\"timePeriodName\":\"08:00-09:00\",\"doseTime\":null,\"imageUrl\":null,\"city\":null,\"county\":null,\"town\":null,\"msg\":null,\"createTime\":1711077134972}', '0', '8196', '26172111', 'broker-a', '192.168.2.230', '2024-03-22 11:14:26', '1711077266634');
INSERT INTO `rocket_mq_fail_msg` VALUES ('188', '2650', '1771013950662508544', '3', 'C0A802E66B1C610644256E942BF9001D', '{\"id\":1771013950662508544,\"recordType\":2032,\"vaild\":1,\"vaccineReleaseId\":1,\"vaccineReleaseName\":null,\"personId\":1764897018334937088,\"personName\":\"zy1\",\"sex\":1,\"age\":29,\"mobile\":\"15208492745\",\"idNumber\":\"510121198901221219\",\"openId\":\"xcn256hb\",\"siteId\":1,\"siteName\":\"攀成钢青白江医院（教育街51号）\",\"recordStatus\":null,\"vaccineId\":1,\"vaccineName\":\"新冠病毒灭活疫苗（Vero细胞）\",\"vaccineBatch\":\"SZKT10006\",\"manufacturer\":\"深圳康泰\",\"dose\":1,\"doseUnit\":\"成都市青白江区卫健局\",\"appointmentTime\":1711077604343,\"timePeriod\":2012,\"timePeriodName\":\"08:00-09:00\",\"doseTime\":null,\"imageUrl\":null,\"city\":null,\"county\":null,\"town\":null,\"msg\":null,\"createTime\":1711077604343}', '0', '8205', '26205574', 'broker-a', '192.168.2.230', '2024-03-22 11:22:21', '1711077741601');
INSERT INTO `rocket_mq_fail_msg` VALUES ('189', '2650', '1771014741137817600', '3', 'C0A802E66B1C610644256E970C280037', '{\"id\":1771014741137817600,\"recordType\":2032,\"vaild\":1,\"vaccineReleaseId\":1,\"vaccineReleaseName\":null,\"personId\":1764897018334937088,\"personName\":\"zy1\",\"sex\":1,\"age\":29,\"mobile\":\"15208492745\",\"idNumber\":\"510121198901221219\",\"openId\":\"xcn256hb\",\"siteId\":1,\"siteName\":\"攀成钢青白江医院（教育街51号）\",\"recordStatus\":null,\"vaccineId\":1,\"vaccineName\":\"新冠病毒灭活疫苗（Vero细胞）\",\"vaccineBatch\":\"SZKT10006\",\"manufacturer\":\"深圳康泰\",\"dose\":1,\"doseUnit\":\"成都市青白江区卫健局\",\"appointmentTime\":1711077792807,\"timePeriod\":2012,\"timePeriodName\":\"08:00-09:00\",\"doseTime\":null,\"imageUrl\":null,\"city\":null,\"county\":null,\"town\":null,\"msg\":null,\"createTime\":1711077792807}', '0', '8214', '26235944', 'broker-a', '192.168.2.230', '2024-03-22 11:25:29', '1711077929473');
INSERT INTO `rocket_mq_fail_msg` VALUES ('190', '2650', '1771014741137817600', '3', 'C0A802E66B1C610644256E970C280037', '{\"id\":1771014741137817600,\"recordType\":2032,\"vaild\":1,\"vaccineReleaseId\":1,\"vaccineReleaseName\":null,\"personId\":1764897018334937088,\"personName\":\"zy1\",\"sex\":1,\"age\":29,\"mobile\":\"15208492745\",\"idNumber\":\"510121198901221219\",\"openId\":\"xcn256hb\",\"siteId\":1,\"siteName\":\"攀成钢青白江医院（教育街51号）\",\"recordStatus\":null,\"vaccineId\":1,\"vaccineName\":\"新冠病毒灭活疫苗（Vero细胞）\",\"vaccineBatch\":\"SZKT10006\",\"manufacturer\":\"深圳康泰\",\"dose\":1,\"doseUnit\":\"成都市青白江区卫健局\",\"appointmentTime\":1711077792807,\"timePeriod\":2012,\"timePeriodName\":\"08:00-09:00\",\"doseTime\":null,\"imageUrl\":null,\"city\":null,\"county\":null,\"town\":null,\"msg\":null,\"createTime\":1711077792807}', '0', '8214', '26235944', 'broker-a', '192.168.2.230', '2024-03-22 11:27:25', '1711078045768');
INSERT INTO `rocket_mq_fail_msg` VALUES ('191', '2650', '1771014977868529664', '3', 'C0A802E66B1C610644256E97E8A20045', '{\"id\":1771014977868529664,\"recordType\":2032,\"vaild\":1,\"vaccineReleaseId\":1,\"vaccineReleaseName\":null,\"personId\":1764897018334937088,\"personName\":\"zy1\",\"sex\":1,\"age\":29,\"mobile\":\"15208492745\",\"idNumber\":\"510121198901221219\",\"openId\":\"xcn256hb\",\"siteId\":1,\"siteName\":\"攀成钢青白江医院（教育街51号）\",\"recordStatus\":null,\"vaccineId\":1,\"vaccineName\":\"新冠病毒灭活疫苗（Vero细胞）\",\"vaccineBatch\":\"SZKT10006\",\"manufacturer\":\"深圳康泰\",\"dose\":1,\"doseUnit\":\"成都市青白江区卫健局\",\"appointmentTime\":1711077849248,\"timePeriod\":2012,\"timePeriodName\":\"08:00-09:00\",\"doseTime\":null,\"imageUrl\":null,\"city\":null,\"county\":null,\"town\":null,\"msg\":null,\"createTime\":1711077849248}', '0', '8231', '26302904', 'broker-a', '192.168.2.230', '2024-03-22 11:28:34', '1711078114216');
INSERT INTO `rocket_mq_fail_msg` VALUES ('192', '2650', '1771015095485202432', '3', 'C0A802E66B1C610644256E98562B0058', '{\"id\":1771015095485202432,\"recordType\":2032,\"vaild\":1,\"vaccineReleaseId\":1,\"vaccineReleaseName\":null,\"personId\":1764897018334937088,\"personName\":\"zy1\",\"sex\":1,\"age\":29,\"mobile\":\"15208492745\",\"idNumber\":\"510121198901221219\",\"openId\":\"xcn256hb\",\"siteId\":1,\"siteName\":\"攀成钢青白江医院（教育街51号）\",\"recordStatus\":null,\"vaccineId\":1,\"vaccineName\":\"新冠病毒灭活疫苗（Vero细胞）\",\"vaccineBatch\":\"SZKT10006\",\"manufacturer\":\"深圳康泰\",\"dose\":1,\"doseUnit\":\"成都市青白江区卫健局\",\"appointmentTime\":1711077877290,\"timePeriod\":2012,\"timePeriodName\":\"08:00-09:00\",\"doseTime\":null,\"imageUrl\":null,\"city\":null,\"county\":null,\"town\":null,\"msg\":null,\"createTime\":1711077877290}', '0', '8234', '26311192', 'broker-a', '192.168.2.230', '2024-03-22 11:29:11', '1711078151589');
INSERT INTO `rocket_mq_fail_msg` VALUES ('193', '2650', '1771015443868286976', '3', 'C0A802E66DA4610644256E999AAA0005', '{\"id\":1771015443868286976,\"recordType\":2032,\"vaild\":1,\"vaccineReleaseId\":1,\"vaccineReleaseName\":null,\"personId\":1764897018334937088,\"personName\":\"zy1\",\"sex\":1,\"age\":29,\"mobile\":\"15208492745\",\"idNumber\":\"510121198901221219\",\"openId\":\"xcn256hb\",\"siteId\":1,\"siteName\":\"攀成钢青白江医院（教育街51号）\",\"recordStatus\":null,\"vaccineId\":1,\"vaccineName\":\"新冠病毒灭活疫苗（Vero细胞）\",\"vaccineBatch\":\"SZKT10006\",\"manufacturer\":\"深圳康泰\",\"dose\":1,\"doseUnit\":\"成都市青白江区卫健局\",\"appointmentTime\":1711077960351,\"timePeriod\":2012,\"timePeriodName\":\"08:00-09:00\",\"doseTime\":null,\"imageUrl\":null,\"city\":null,\"county\":null,\"town\":null,\"msg\":null,\"createTime\":1711077960351}', '0', '8235', '26312270', 'broker-a', '192.168.2.230', '2024-03-22 11:29:11', '1711078151597');
INSERT INTO `rocket_mq_fail_msg` VALUES ('194', '2650', '1771015534096154624', '3', 'C0A802E66DA4610644256E99EEA8000F', '{\"id\":1771015534096154624,\"recordType\":2032,\"vaild\":1,\"vaccineReleaseId\":1,\"vaccineReleaseName\":null,\"personId\":1764897018334937088,\"personName\":\"zy1\",\"sex\":1,\"age\":29,\"mobile\":\"15208492745\",\"idNumber\":\"510121198901221219\",\"openId\":\"xcn256hb\",\"siteId\":1,\"siteName\":\"攀成钢青白江医院（教育街51号）\",\"recordStatus\":null,\"vaccineId\":1,\"vaccineName\":\"新冠病毒灭活疫苗（Vero细胞）\",\"vaccineBatch\":\"SZKT10006\",\"manufacturer\":\"深圳康泰\",\"dose\":1,\"doseUnit\":\"成都市青白江区卫健局\",\"appointmentTime\":1711077981863,\"timePeriod\":2012,\"timePeriodName\":\"08:00-09:00\",\"doseTime\":null,\"imageUrl\":null,\"city\":null,\"county\":null,\"town\":null,\"msg\":null,\"createTime\":1711077981863}', '0', '8236', '26313799', 'broker-a', '192.168.2.230', '2024-03-22 11:29:20', '1711078160550');
INSERT INTO `rocket_mq_fail_msg` VALUES ('195', '2650', '1771015555579379712', '3', 'C0A802E66DA4610644256E9A02AB0016', '{\"id\":1771015555579379712,\"recordType\":2032,\"vaild\":1,\"vaccineReleaseId\":1,\"vaccineReleaseName\":null,\"personId\":1764897018334937088,\"personName\":\"zy1\",\"sex\":1,\"age\":29,\"mobile\":\"15208492745\",\"idNumber\":\"510121198901221219\",\"openId\":\"xcn256hb\",\"siteId\":1,\"siteName\":\"攀成钢青白江医院（教育街51号）\",\"recordStatus\":null,\"vaccineId\":1,\"vaccineName\":\"新冠病毒灭活疫苗（Vero细胞）\",\"vaccineBatch\":\"SZKT10006\",\"manufacturer\":\"深圳康泰\",\"dose\":1,\"doseUnit\":\"成都市青白江区卫健局\",\"appointmentTime\":1711077986985,\"timePeriod\":2012,\"timePeriodName\":\"08:00-09:00\",\"doseTime\":null,\"imageUrl\":null,\"city\":null,\"county\":null,\"town\":null,\"msg\":null,\"createTime\":1711077986985}', '0', '8237', '26314877', 'broker-a', '192.168.2.230', '2024-03-22 11:29:20', '1711078160556');
INSERT INTO `rocket_mq_fail_msg` VALUES ('196', '2650', '1771015491574300672', '3', 'C0A802E66DA4610644256E99C70F000A', '{\"id\":1771015491574300672,\"recordType\":2032,\"vaild\":1,\"vaccineReleaseId\":1,\"vaccineReleaseName\":null,\"personId\":1764897018334937088,\"personName\":\"zy1\",\"sex\":1,\"age\":29,\"mobile\":\"15208492745\",\"idNumber\":\"510121198901221219\",\"openId\":\"xcn256hb\",\"siteId\":1,\"siteName\":\"攀成钢青白江医院（教育街51号）\",\"recordStatus\":null,\"vaccineId\":1,\"vaccineName\":\"新冠病毒灭活疫苗（Vero细胞）\",\"vaccineBatch\":\"SZKT10006\",\"manufacturer\":\"深圳康泰\",\"dose\":1,\"doseUnit\":\"成都市青白江区卫健局\",\"appointmentTime\":1711077971725,\"timePeriod\":2012,\"timePeriodName\":\"08:00-09:00\",\"doseTime\":null,\"imageUrl\":null,\"city\":null,\"county\":null,\"town\":null,\"msg\":null,\"createTime\":1711077971725}', '0', '8239', '26317033', 'broker-a', '192.168.2.230', '2024-03-22 11:29:20', '1711078160557');
INSERT INTO `rocket_mq_fail_msg` VALUES ('197', '2650', '1771015589574213632', '3', 'C0A802E66DA4610644256E9A2253001E', '{\"id\":1771015589574213632,\"recordType\":2032,\"vaild\":1,\"vaccineReleaseId\":1,\"vaccineReleaseName\":null,\"personId\":1764897018334937088,\"personName\":\"zy1\",\"sex\":1,\"age\":29,\"mobile\":\"15208492745\",\"idNumber\":\"510121198901221219\",\"openId\":\"xcn256hb\",\"siteId\":1,\"siteName\":\"攀成钢青白江医院（教育街51号）\",\"recordStatus\":null,\"vaccineId\":1,\"vaccineName\":\"新冠病毒灭活疫苗（Vero细胞）\",\"vaccineBatch\":\"SZKT10006\",\"manufacturer\":\"深圳康泰\",\"dose\":1,\"doseUnit\":\"成都市青白江区卫健局\",\"appointmentTime\":1711077995090,\"timePeriod\":2012,\"timePeriodName\":\"08:00-09:00\",\"doseTime\":null,\"imageUrl\":null,\"city\":null,\"county\":null,\"town\":null,\"msg\":null,\"createTime\":1711077995090}', '0', '8238', '26315955', 'broker-a', '192.168.2.230', '2024-03-22 11:29:20', '1711078160556');
INSERT INTO `rocket_mq_fail_msg` VALUES ('198', '2650', '1771015912837611520', '3', 'C0A802E68FF0610644256E9B4F770026', '{\"id\":1771015912837611520,\"recordType\":2032,\"vaild\":1,\"vaccineReleaseId\":1,\"vaccineReleaseName\":null,\"personId\":1764897018334937088,\"personName\":\"zy1\",\"sex\":1,\"age\":29,\"mobile\":\"15208492745\",\"idNumber\":\"510121198901221219\",\"openId\":\"xcn256hb\",\"siteId\":1,\"siteName\":\"攀成钢青白江医院（教育街51号）\",\"recordStatus\":null,\"vaccineId\":1,\"vaccineName\":\"新冠病毒灭活疫苗（Vero细胞）\",\"vaccineBatch\":\"SZKT10006\",\"manufacturer\":\"深圳康泰\",\"dose\":1,\"doseUnit\":\"成都市青白江区卫健局\",\"appointmentTime\":1711078072162,\"timePeriod\":2012,\"timePeriodName\":\"08:00-09:00\",\"doseTime\":null,\"imageUrl\":null,\"city\":null,\"county\":null,\"town\":null,\"msg\":null,\"createTime\":1711078072162}', '0', '8242', '26332305', 'broker-a', '192.168.2.230', '2024-03-22 11:29:43', '1711078183192');
INSERT INTO `rocket_mq_fail_msg` VALUES ('199', '2650', '1771019414733324288', '3', 'C0A802E64B10610644256EA80CD9002C', '{\"id\":1771019414733324288,\"recordType\":2032,\"vaild\":1,\"vaccineReleaseId\":1,\"vaccineReleaseName\":null,\"personId\":1764897018334937088,\"personName\":\"zy1\",\"sex\":1,\"age\":29,\"mobile\":\"15208492745\",\"idNumber\":\"510121198901221219\",\"openId\":\"xcn256hb\",\"siteId\":1,\"siteName\":\"攀成钢青白江医院（教育街51号）\",\"recordStatus\":null,\"vaccineId\":1,\"vaccineName\":\"新冠病毒灭活疫苗（Vero细胞）\",\"vaccineBatch\":\"SZKT10006\",\"manufacturer\":\"深圳康泰\",\"dose\":1,\"doseUnit\":\"成都市青白江区卫健局\",\"appointmentTime\":1711078907078,\"timePeriod\":2012,\"timePeriodName\":\"08:00-09:00\",\"doseTime\":null,\"imageUrl\":null,\"city\":null,\"county\":null,\"town\":null,\"msg\":null,\"createTime\":1711078907078}', '0', '8269', '26406595', 'broker-a', '192.168.2.230', '2024-03-22 11:43:42', '1711079022204');
INSERT INTO `rocket_mq_fail_msg` VALUES ('200', '2650', '1771021077695168512', '3', 'C0A802E67C04610644256EAE198C0036', '{\"id\":1771021077695168512,\"recordType\":2032,\"vaild\":1,\"vaccineReleaseId\":1,\"vaccineReleaseName\":null,\"personId\":1764897018334937088,\"personName\":\"zy1\",\"sex\":1,\"age\":29,\"mobile\":\"15208492745\",\"idNumber\":\"510121198901221219\",\"openId\":\"xcn256hb\",\"siteId\":1,\"siteName\":\"攀成钢青白江医院（教育街51号）\",\"recordStatus\":null,\"vaccineId\":1,\"vaccineName\":\"新冠病毒灭活疫苗（Vero细胞）\",\"vaccineBatch\":\"SZKT10006\",\"manufacturer\":\"深圳康泰\",\"dose\":1,\"doseUnit\":\"成都市青白江区卫健局\",\"appointmentTime\":1711079303560,\"timePeriod\":2012,\"timePeriodName\":\"08:00-09:00\",\"doseTime\":null,\"imageUrl\":null,\"city\":null,\"county\":null,\"town\":null,\"msg\":null,\"createTime\":1711079303560}', '0', '8289', '26449596', 'broker-a', '192.168.2.230', '2024-03-22 15:48:19', '1711093699598');
INSERT INTO `rocket_mq_fail_msg` VALUES ('201', '2650', '1771093875083968512', '3', 'C0A802E6B208610644256FB6EF720025', '{\"id\":1771093875083968512,\"recordType\":2032,\"vaild\":1,\"vaccineReleaseId\":1,\"vaccineReleaseName\":null,\"personId\":1764897018334937088,\"personName\":\"zy1\",\"sex\":1,\"age\":29,\"mobile\":\"15208492745\",\"idNumber\":\"510121198901221219\",\"openId\":\"xcn256hb\",\"siteId\":1,\"siteName\":\"攀成钢青白江医院（教育街51号）\",\"recordStatus\":null,\"vaccineId\":1,\"vaccineName\":\"新冠病毒灭活疫苗（Vero细胞）\",\"vaccineBatch\":\"SZKT10006\",\"manufacturer\":\"深圳康泰\",\"dose\":1,\"doseUnit\":\"成都市青白江区卫健局\",\"appointmentTime\":1711096659809,\"timePeriod\":2012,\"timePeriodName\":\"08:00-09:00\",\"doseTime\":null,\"imageUrl\":null,\"city\":null,\"county\":null,\"town\":null,\"msg\":null,\"createTime\":1711096659809}', '0', '8322', '26541757', 'broker-a', '192.168.2.230', '2024-03-22 16:39:19', '1711096759975');
INSERT INTO `rocket_mq_fail_msg` VALUES ('202', '2650', '1771094455307206656', '3', 'C0A802E6B208610644256FB90BC40037', '{\"id\":1771094455307206656,\"recordType\":2032,\"vaild\":1,\"vaccineReleaseId\":1,\"vaccineReleaseName\":null,\"personId\":1764897018334937088,\"personName\":\"zy1\",\"sex\":1,\"age\":29,\"mobile\":\"15208492745\",\"idNumber\":\"510121198901221219\",\"openId\":\"xcn256hb\",\"siteId\":1,\"siteName\":\"攀成钢青白江医院（教育街51号）\",\"recordStatus\":null,\"vaccineId\":1,\"vaccineName\":\"新冠病毒灭活疫苗（Vero细胞）\",\"vaccineBatch\":\"SZKT10006\",\"manufacturer\":\"深圳康泰\",\"dose\":1,\"doseUnit\":\"成都市青白江区卫健局\",\"appointmentTime\":1711096798146,\"timePeriod\":2012,\"timePeriodName\":\"08:00-09:00\",\"doseTime\":null,\"imageUrl\":null,\"city\":null,\"county\":null,\"town\":null,\"msg\":null,\"createTime\":1711096798146}', '0', '8326', '26555614', 'broker-a', '192.168.2.230', '2024-03-22 16:41:38', '1711096898238');
INSERT INTO `rocket_mq_fail_msg` VALUES ('203', '2650', '1771097753317801984', '3', 'C0A802E6B208610644256FC50B47006F', '{\"id\":1771097753317801984,\"recordType\":2032,\"vaild\":1,\"vaccineReleaseId\":1,\"vaccineReleaseName\":null,\"personId\":1764897018334937088,\"personName\":\"zy1\",\"sex\":1,\"age\":29,\"mobile\":\"15208492745\",\"idNumber\":\"510121198901221219\",\"openId\":\"xcn256hb\",\"siteId\":1,\"siteName\":\"攀成钢青白江医院（教育街51号）\",\"recordStatus\":null,\"vaccineId\":1,\"vaccineName\":\"新冠病毒灭活疫苗（Vero细胞）\",\"vaccineBatch\":\"SZKT10006\",\"manufacturer\":\"深圳康泰\",\"dose\":1,\"doseUnit\":\"成都市青白江区卫健局\",\"appointmentTime\":1711097584453,\"timePeriod\":2012,\"timePeriodName\":\"08:00-09:00\",\"doseTime\":null,\"imageUrl\":null,\"city\":null,\"county\":null,\"town\":null,\"msg\":null,\"createTime\":1711097584453}', '0', '8345', '26614468', 'broker-a', '192.168.2.230', '2024-03-22 16:54:44', '1711097684503');
INSERT INTO `rocket_mq_fail_msg` VALUES ('204', '2650', '1771097857248460800', '3', 'C0A802E6B208610644256FC56C11007E', '{\"id\":1771097857248460800,\"recordType\":2032,\"vaild\":1,\"vaccineReleaseId\":1,\"vaccineReleaseName\":null,\"personId\":1764897018334937088,\"personName\":\"zy1\",\"sex\":1,\"age\":29,\"mobile\":\"15208492745\",\"idNumber\":\"510121198901221219\",\"openId\":\"xcn256hb\",\"siteId\":1,\"siteName\":\"攀成钢青白江医院（教育街51号）\",\"recordStatus\":null,\"vaccineId\":1,\"vaccineName\":\"新冠病毒灭活疫苗（Vero细胞）\",\"vaccineBatch\":\"SZKT10006\",\"manufacturer\":\"深圳康泰\",\"dose\":1,\"doseUnit\":\"成都市青白江区卫健局\",\"appointmentTime\":1711097609232,\"timePeriod\":2012,\"timePeriodName\":\"08:00-09:00\",\"doseTime\":null,\"imageUrl\":null,\"city\":null,\"county\":null,\"town\":null,\"msg\":null,\"createTime\":1711097609232}', '0', '8346', '26617231', 'broker-a', '192.168.2.230', '2024-03-22 16:55:09', '1711097709279');
INSERT INTO `rocket_mq_fail_msg` VALUES ('205', '2650', '1771097863741243392', '3', 'C0A802E6B208610644256FC5721D0085', '{\"id\":1771097863741243392,\"recordType\":2032,\"vaild\":1,\"vaccineReleaseId\":1,\"vaccineReleaseName\":null,\"personId\":1764897018334937088,\"personName\":\"zy1\",\"sex\":1,\"age\":29,\"mobile\":\"15208492745\",\"idNumber\":\"510121198901221219\",\"openId\":\"xcn256hb\",\"siteId\":1,\"siteName\":\"攀成钢青白江医院（教育街51号）\",\"recordStatus\":null,\"vaccineId\":1,\"vaccineName\":\"新冠病毒灭活疫苗（Vero细胞）\",\"vaccineBatch\":\"SZKT10006\",\"manufacturer\":\"深圳康泰\",\"dose\":1,\"doseUnit\":\"成都市青白江区卫健局\",\"appointmentTime\":1711097610780,\"timePeriod\":2012,\"timePeriodName\":\"08:00-09:00\",\"doseTime\":null,\"imageUrl\":null,\"city\":null,\"county\":null,\"town\":null,\"msg\":null,\"createTime\":1711097610780}', '0', '8347', '26619994', 'broker-a', '192.168.2.230', '2024-03-22 16:55:10', '1711097710824');
INSERT INTO `rocket_mq_fail_msg` VALUES ('206', '2650', '1771098881203896320', '3', 'C0A802E6B208610644256FC925B400B7', '{\"id\":1771098881203896320,\"recordType\":2032,\"vaild\":1,\"vaccineReleaseId\":1,\"vaccineReleaseName\":null,\"personId\":1764897018334937088,\"personName\":\"zy1\",\"sex\":1,\"age\":29,\"mobile\":\"15208492745\",\"idNumber\":\"510121198901221219\",\"openId\":\"xcn256hb\",\"siteId\":1,\"siteName\":\"攀成钢青白江医院（教育街51号）\",\"recordStatus\":null,\"vaccineId\":1,\"vaccineName\":\"新冠病毒灭活疫苗（Vero细胞）\",\"vaccineBatch\":\"SZKT10006\",\"manufacturer\":\"深圳康泰\",\"dose\":1,\"doseUnit\":\"成都市青白江区卫健局\",\"appointmentTime\":1711097853362,\"timePeriod\":2012,\"timePeriodName\":\"08:00-09:00\",\"doseTime\":null,\"imageUrl\":null,\"city\":null,\"county\":null,\"town\":null,\"msg\":null,\"createTime\":1711097853362}', '0', '8365', '26693984', 'broker-a', '192.168.2.230', '2024-03-22 16:59:13', '1711097953411');
INSERT INTO `rocket_mq_fail_msg` VALUES ('207', '2650', '1771098996199129088', '3', 'C0A802E6B208610644256FC990CC00C0', '{\"id\":1771098996199129088,\"recordType\":2032,\"vaild\":1,\"vaccineReleaseId\":1,\"vaccineReleaseName\":null,\"personId\":1764897018334937088,\"personName\":\"zy1\",\"sex\":1,\"age\":29,\"mobile\":\"15208492745\",\"idNumber\":\"510121198901221219\",\"openId\":\"xcn256hb\",\"siteId\":1,\"siteName\":\"攀成钢青白江医院（教育街51号）\",\"recordStatus\":null,\"vaccineId\":1,\"vaccineName\":\"新冠病毒灭活疫苗（Vero细胞）\",\"vaccineBatch\":\"SZKT10006\",\"manufacturer\":\"深圳康泰\",\"dose\":1,\"doseUnit\":\"成都市青白江区卫健局\",\"appointmentTime\":1711097880779,\"timePeriod\":2012,\"timePeriodName\":\"08:00-09:00\",\"doseTime\":null,\"imageUrl\":null,\"city\":null,\"county\":null,\"town\":null,\"msg\":null,\"createTime\":1711097880779}', '0', '8372', '26712721', 'broker-a', '192.168.2.230', '2024-03-22 16:59:40', '1711097980826');
INSERT INTO `rocket_mq_fail_msg` VALUES ('208', '2650', '1771099002914209792', '3', 'C0A802E6B208610644256FC9970E00C6', '{\"id\":1771099002914209792,\"recordType\":2032,\"vaild\":1,\"vaccineReleaseId\":1,\"vaccineReleaseName\":null,\"personId\":1764897018334937088,\"personName\":\"zy1\",\"sex\":1,\"age\":29,\"mobile\":\"15208492745\",\"idNumber\":\"510121198901221219\",\"openId\":\"xcn256hb\",\"siteId\":1,\"siteName\":\"攀成钢青白江医院（教育街51号）\",\"recordStatus\":null,\"vaccineId\":1,\"vaccineName\":\"新冠病毒灭活疫苗（Vero细胞）\",\"vaccineBatch\":\"SZKT10006\",\"manufacturer\":\"深圳康泰\",\"dose\":1,\"doseUnit\":\"成都市青白江区卫健局\",\"appointmentTime\":1711097882380,\"timePeriod\":2012,\"timePeriodName\":\"08:00-09:00\",\"doseTime\":null,\"imageUrl\":null,\"city\":null,\"county\":null,\"town\":null,\"msg\":null,\"createTime\":1711097882380}', '0', '8373', '26715484', 'broker-a', '192.168.2.230', '2024-03-22 16:59:42', '1711097982437');
INSERT INTO `rocket_mq_fail_msg` VALUES ('209', '2650', '1771099007611830272', '3', 'C0A802E6B208610644256FC99B6E00CC', '{\"id\":1771099007611830272,\"recordType\":2032,\"vaild\":1,\"vaccineReleaseId\":1,\"vaccineReleaseName\":null,\"personId\":1764897018334937088,\"personName\":\"zy1\",\"sex\":1,\"age\":29,\"mobile\":\"15208492745\",\"idNumber\":\"510121198901221219\",\"openId\":\"xcn256hb\",\"siteId\":1,\"siteName\":\"攀成钢青白江医院（教育街51号）\",\"recordStatus\":null,\"vaccineId\":1,\"vaccineName\":\"新冠病毒灭活疫苗（Vero细胞）\",\"vaccineBatch\":\"SZKT10006\",\"manufacturer\":\"深圳康泰\",\"dose\":1,\"doseUnit\":\"成都市青白江区卫健局\",\"appointmentTime\":1711097883500,\"timePeriod\":2012,\"timePeriodName\":\"08:00-09:00\",\"doseTime\":null,\"imageUrl\":null,\"city\":null,\"county\":null,\"town\":null,\"msg\":null,\"createTime\":1711097883500}', '0', '8374', '26718247', 'broker-a', '192.168.2.230', '2024-03-22 16:59:43', '1711097983552');
INSERT INTO `rocket_mq_fail_msg` VALUES ('210', '2650', '1771099164818538496', '3', 'C0A802E6B208610644256FCA2DD600E4', '{\"id\":1771099164818538496,\"recordType\":2032,\"vaild\":1,\"vaccineReleaseId\":1,\"vaccineReleaseName\":null,\"personId\":1764897018334937088,\"personName\":\"zy1\",\"sex\":1,\"age\":29,\"mobile\":\"15208492745\",\"idNumber\":\"510121198901221219\",\"openId\":\"xcn256hb\",\"siteId\":1,\"siteName\":\"攀成钢青白江医院（教育街51号）\",\"recordStatus\":null,\"vaccineId\":1,\"vaccineName\":\"新冠病毒灭活疫苗（Vero细胞）\",\"vaccineBatch\":\"SZKT10006\",\"manufacturer\":\"深圳康泰\",\"dose\":1,\"doseUnit\":\"成都市青白江区卫健局\",\"appointmentTime\":1711097920981,\"timePeriod\":2012,\"timePeriodName\":\"08:00-09:00\",\"doseTime\":null,\"imageUrl\":null,\"city\":null,\"county\":null,\"town\":null,\"msg\":null,\"createTime\":1711097920981}', '0', '8378', '26728896', 'broker-a', '192.168.2.230', '2024-03-22 17:00:21', '1711098021030');
INSERT INTO `rocket_mq_fail_msg` VALUES ('211', '2650', '1771099169604239360', '3', 'C0A802E6B208610644256FCA324B00EB', '{\"id\":1771099169604239360,\"recordType\":2032,\"vaild\":1,\"vaccineReleaseId\":1,\"vaccineReleaseName\":null,\"personId\":1764897018334937088,\"personName\":\"zy1\",\"sex\":1,\"age\":29,\"mobile\":\"15208492745\",\"idNumber\":\"510121198901221219\",\"openId\":\"xcn256hb\",\"siteId\":1,\"siteName\":\"攀成钢青白江医院（教育街51号）\",\"recordStatus\":null,\"vaccineId\":1,\"vaccineName\":\"新冠病毒灭活疫苗（Vero细胞）\",\"vaccineBatch\":\"SZKT10006\",\"manufacturer\":\"深圳康泰\",\"dose\":1,\"doseUnit\":\"成都市青白江区卫健局\",\"appointmentTime\":1711097922122,\"timePeriod\":2012,\"timePeriodName\":\"08:00-09:00\",\"doseTime\":null,\"imageUrl\":null,\"city\":null,\"county\":null,\"town\":null,\"msg\":null,\"createTime\":1711097922122}', '0', '8379', '26731659', 'broker-a', '192.168.2.230', '2024-03-22 17:00:22', '1711098022184');
INSERT INTO `rocket_mq_fail_msg` VALUES ('212', '2650', '1771099175849558016', '3', 'C0A802E6B208610644256FCA381C00F9', '{\"id\":1771099175849558016,\"recordType\":2032,\"vaild\":1,\"vaccineReleaseId\":1,\"vaccineReleaseName\":null,\"personId\":1764897018334937088,\"personName\":\"zy1\",\"sex\":1,\"age\":29,\"mobile\":\"15208492745\",\"idNumber\":\"510121198901221219\",\"openId\":\"xcn256hb\",\"siteId\":1,\"siteName\":\"攀成钢青白江医院（教育街51号）\",\"recordStatus\":null,\"vaccineId\":1,\"vaccineName\":\"新冠病毒灭活疫苗（Vero细胞）\",\"vaccineBatch\":\"SZKT10006\",\"manufacturer\":\"深圳康泰\",\"dose\":1,\"doseUnit\":\"成都市青白江区卫健局\",\"appointmentTime\":1711097923611,\"timePeriod\":2012,\"timePeriodName\":\"08:00-09:00\",\"doseTime\":null,\"imageUrl\":null,\"city\":null,\"county\":null,\"town\":null,\"msg\":null,\"createTime\":1711097923611}', '0', '8380', '26734422', 'broker-a', '192.168.2.230', '2024-03-22 17:00:23', '1711098023660');
INSERT INTO `rocket_mq_fail_msg` VALUES ('213', '2650', '1771099187962707968', '3', 'C0A802E6B208610644256FCA436400FF', '{\"id\":1771099187962707968,\"recordType\":2032,\"vaild\":1,\"vaccineReleaseId\":1,\"vaccineReleaseName\":null,\"personId\":1764897018334937088,\"personName\":\"zy1\",\"sex\":1,\"age\":29,\"mobile\":\"15208492745\",\"idNumber\":\"510121198901221219\",\"openId\":\"xcn256hb\",\"siteId\":1,\"siteName\":\"攀成钢青白江医院（教育街51号）\",\"recordStatus\":null,\"vaccineId\":1,\"vaccineName\":\"新冠病毒灭活疫苗（Vero细胞）\",\"vaccineBatch\":\"SZKT10006\",\"manufacturer\":\"深圳康泰\",\"dose\":1,\"doseUnit\":\"成都市青白江区卫健局\",\"appointmentTime\":1711097926499,\"timePeriod\":2012,\"timePeriodName\":\"08:00-09:00\",\"doseTime\":null,\"imageUrl\":null,\"city\":null,\"county\":null,\"town\":null,\"msg\":null,\"createTime\":1711097926499}', '0', '8381', '26737185', 'broker-a', '192.168.2.230', '2024-03-22 17:00:26', '1711098026552');
INSERT INTO `rocket_mq_fail_msg` VALUES ('214', '2650', '1771099194468073472', '3', 'C0A802E6B208610644256FCA49730105', '{\"id\":1771099194468073472,\"recordType\":2032,\"vaild\":1,\"vaccineReleaseId\":1,\"vaccineReleaseName\":null,\"personId\":1764897018334937088,\"personName\":\"zy1\",\"sex\":1,\"age\":29,\"mobile\":\"15208492745\",\"idNumber\":\"510121198901221219\",\"openId\":\"xcn256hb\",\"siteId\":1,\"siteName\":\"攀成钢青白江医院（教育街51号）\",\"recordStatus\":null,\"vaccineId\":1,\"vaccineName\":\"新冠病毒灭活疫苗（Vero细胞）\",\"vaccineBatch\":\"SZKT10006\",\"manufacturer\":\"深圳康泰\",\"dose\":1,\"doseUnit\":\"成都市青白江区卫健局\",\"appointmentTime\":1711097928050,\"timePeriod\":2012,\"timePeriodName\":\"08:00-09:00\",\"doseTime\":null,\"imageUrl\":null,\"city\":null,\"county\":null,\"town\":null,\"msg\":null,\"createTime\":1711097928050}', '0', '8382', '26739948', 'broker-a', '192.168.2.230', '2024-03-22 17:00:28', '1711098028100');
INSERT INTO `rocket_mq_fail_msg` VALUES ('215', '2650', '1771099721314598912', '3', 'C0A802E6B208610644256FCC341D0160', '{\"id\":1771099721314598912,\"recordType\":2032,\"vaild\":1,\"vaccineReleaseId\":1,\"vaccineReleaseName\":null,\"personId\":1764897018334937088,\"personName\":\"zy1\",\"sex\":1,\"age\":29,\"mobile\":\"15208492745\",\"idNumber\":\"510121198901221219\",\"openId\":\"xcn256hb\",\"siteId\":1,\"siteName\":\"攀成钢青白江医院（教育街51号）\",\"recordStatus\":null,\"vaccineId\":1,\"vaccineName\":\"新冠病毒灭活疫苗（Vero细胞）\",\"vaccineBatch\":\"SZKT10006\",\"manufacturer\":\"深圳康泰\",\"dose\":1,\"doseUnit\":\"成都市青白江区卫健局\",\"appointmentTime\":1711098053660,\"timePeriod\":2012,\"timePeriodName\":\"08:00-09:00\",\"doseTime\":null,\"imageUrl\":null,\"city\":null,\"county\":null,\"town\":null,\"msg\":null,\"createTime\":1711098053660}', '0', '8396', '26780231', 'broker-a', '192.168.2.230', '2024-03-22 17:02:34', '1711098154167');
INSERT INTO `rocket_mq_fail_msg` VALUES ('216', '2650', '1771100205437943808', '3', 'C0A802E6B208610644256FCDF7020198', '{\"id\":1771100205437943808,\"recordType\":2032,\"vaild\":1,\"vaccineReleaseId\":1,\"vaccineReleaseName\":null,\"personId\":1764897018334937088,\"personName\":\"zy1\",\"sex\":1,\"age\":29,\"mobile\":\"15208492745\",\"idNumber\":\"510121198901221219\",\"openId\":\"xcn256hb\",\"siteId\":1,\"siteName\":\"攀成钢青白江医院（教育街51号）\",\"recordStatus\":null,\"vaccineId\":1,\"vaccineName\":\"新冠病毒灭活疫苗（Vero细胞）\",\"vaccineBatch\":\"SZKT10006\",\"manufacturer\":\"深圳康泰\",\"dose\":1,\"doseUnit\":\"成都市青白江区卫健局\",\"appointmentTime\":1711098169084,\"timePeriod\":2012,\"timePeriodName\":\"08:00-09:00\",\"doseTime\":null,\"imageUrl\":null,\"city\":null,\"county\":null,\"town\":null,\"msg\":null,\"createTime\":1711098169084}', '0', '8403', '26801774', 'broker-a', '192.168.2.230', '2024-03-22 17:04:29', '1711098269137');
INSERT INTO `rocket_mq_fail_msg` VALUES ('217', '2650', '1771100667658633216', '3', 'C0A802E6B208610644256FCFA57701BA', '{\"id\":1771100667658633216,\"recordType\":2032,\"vaild\":1,\"vaccineReleaseId\":1,\"vaccineReleaseName\":null,\"personId\":1764897018334937088,\"personName\":\"zy1\",\"sex\":1,\"age\":29,\"mobile\":\"15208492745\",\"idNumber\":\"510121198901221219\",\"openId\":\"xcn256hb\",\"siteId\":1,\"siteName\":\"攀成钢青白江医院（教育街51号）\",\"recordStatus\":null,\"vaccineId\":1,\"vaccineName\":\"新冠病毒灭活疫苗（Vero细胞）\",\"vaccineBatch\":\"SZKT10006\",\"manufacturer\":\"深圳康泰\",\"dose\":1,\"doseUnit\":\"成都市青白江区卫健局\",\"appointmentTime\":1711098279286,\"timePeriod\":2012,\"timePeriodName\":\"08:00-09:00\",\"doseTime\":null,\"imageUrl\":null,\"city\":null,\"county\":null,\"town\":null,\"msg\":null,\"createTime\":1711098279286}', '0', '8416', '26842097', 'broker-a', '192.168.2.230', '2024-03-22 17:06:19', '1711098379329');
INSERT INTO `rocket_mq_fail_msg` VALUES ('218', '2650', '1771101031451590656', '3', 'C0A802E6B208610644256FD0F84601E4', '{\"id\":1771101031451590656,\"recordType\":2032,\"vaild\":1,\"vaccineReleaseId\":1,\"vaccineReleaseName\":null,\"personId\":1764897018334937088,\"personName\":\"zy1\",\"sex\":1,\"age\":29,\"mobile\":\"15208492745\",\"idNumber\":\"510121198901221219\",\"openId\":\"xcn256hb\",\"siteId\":1,\"siteName\":\"攀成钢青白江医院（教育街51号）\",\"recordStatus\":null,\"vaccineId\":1,\"vaccineName\":\"新冠病毒灭活疫苗（Vero细胞）\",\"vaccineBatch\":\"SZKT10006\",\"manufacturer\":\"深圳康泰\",\"dose\":1,\"doseUnit\":\"成都市青白江区卫健局\",\"appointmentTime\":1711098366021,\"timePeriod\":2012,\"timePeriodName\":\"08:00-09:00\",\"doseTime\":null,\"imageUrl\":null,\"city\":null,\"county\":null,\"town\":null,\"msg\":null,\"createTime\":1711098366021}', '0', '8421', '26855110', 'broker-a', '192.168.2.230', '2024-03-22 17:07:46', '1711098466076');
INSERT INTO `rocket_mq_fail_msg` VALUES ('219', '2650', '1771101622349332480', '3', 'C0A802E6B208610644256FD31E970207', '{\"id\":1771101622349332480,\"recordType\":2032,\"vaild\":1,\"vaccineReleaseId\":1,\"vaccineReleaseName\":null,\"personId\":1764897018334937088,\"personName\":\"zy1\",\"sex\":1,\"age\":29,\"mobile\":\"15208492745\",\"idNumber\":\"510121198901221219\",\"openId\":\"xcn256hb\",\"siteId\":1,\"siteName\":\"攀成钢青白江医院（教育街51号）\",\"recordStatus\":null,\"vaccineId\":1,\"vaccineName\":\"新冠病毒灭活疫苗（Vero细胞）\",\"vaccineBatch\":\"SZKT10006\",\"manufacturer\":\"深圳康泰\",\"dose\":1,\"doseUnit\":\"成都市青白江区卫健局\",\"appointmentTime\":1711098506902,\"timePeriod\":2012,\"timePeriodName\":\"08:00-09:00\",\"doseTime\":null,\"imageUrl\":null,\"city\":null,\"county\":null,\"town\":null,\"msg\":null,\"createTime\":1711098506902}', '0', '8444', '26924662', 'broker-a', '192.168.2.230', '2024-03-22 17:10:06', '1711098606956');
INSERT INTO `rocket_mq_fail_msg` VALUES ('220', '2650', '1771101726615535616', '3', 'C0A802E6B208610644256FD37FB20222', '{\"id\":1771101726615535616,\"recordType\":2032,\"vaild\":1,\"vaccineReleaseId\":1,\"vaccineReleaseName\":null,\"personId\":1764897018334937088,\"personName\":\"zy1\",\"sex\":1,\"age\":29,\"mobile\":\"15208492745\",\"idNumber\":\"510121198901221219\",\"openId\":\"xcn256hb\",\"siteId\":1,\"siteName\":\"攀成钢青白江医院（教育街51号）\",\"recordStatus\":null,\"vaccineId\":1,\"vaccineName\":\"新冠病毒灭活疫苗（Vero细胞）\",\"vaccineBatch\":\"SZKT10006\",\"manufacturer\":\"深圳康泰\",\"dose\":1,\"doseUnit\":\"成都市青白江区卫健局\",\"appointmentTime\":1711098531761,\"timePeriod\":2012,\"timePeriodName\":\"08:00-09:00\",\"doseTime\":null,\"imageUrl\":null,\"city\":null,\"county\":null,\"town\":null,\"msg\":null,\"createTime\":1711098531761}', '0', '8445', '26927425', 'broker-a', '192.168.2.230', '2024-03-22 17:10:31', '1711098631807');
INSERT INTO `rocket_mq_fail_msg` VALUES ('221', '2650', '1771101744185475072', '3', 'C0A802E6B208610644256FD390100228', '{\"id\":1771101744185475072,\"recordType\":2032,\"vaild\":1,\"vaccineReleaseId\":1,\"vaccineReleaseName\":null,\"personId\":1764897018334937088,\"personName\":\"zy1\",\"sex\":1,\"age\":29,\"mobile\":\"15208492745\",\"idNumber\":\"510121198901221219\",\"openId\":\"xcn256hb\",\"siteId\":1,\"siteName\":\"攀成钢青白江医院（教育街51号）\",\"recordStatus\":null,\"vaccineId\":1,\"vaccineName\":\"新冠病毒灭活疫苗（Vero细胞）\",\"vaccineBatch\":\"SZKT10006\",\"manufacturer\":\"深圳康泰\",\"dose\":1,\"doseUnit\":\"成都市青白江区卫健局\",\"appointmentTime\":1711098535950,\"timePeriod\":2012,\"timePeriodName\":\"08:00-09:00\",\"doseTime\":null,\"imageUrl\":null,\"city\":null,\"county\":null,\"town\":null,\"msg\":null,\"createTime\":1711098535950}', '0', '8446', '26930188', 'broker-a', '192.168.2.230', '2024-03-22 17:10:35', '1711098635995');
INSERT INTO `rocket_mq_fail_msg` VALUES ('222', '2650', '1771105516047564800', '3', 'C0A802E6BB48610644256FE148EE000C', '{\"id\":1771105516047564800,\"recordType\":2032,\"vaild\":1,\"vaccineReleaseId\":1,\"vaccineReleaseName\":null,\"personId\":1764897018334937088,\"personName\":\"zy1\",\"sex\":1,\"age\":29,\"mobile\":\"15208492745\",\"idNumber\":\"510121198901221219\",\"openId\":\"xcn256hb\",\"siteId\":1,\"siteName\":\"攀成钢青白江医院（教育街51号）\",\"recordStatus\":null,\"vaccineId\":1,\"vaccineName\":\"新冠病毒灭活疫苗（Vero细胞）\",\"vaccineBatch\":\"SZKT10006\",\"manufacturer\":\"深圳康泰\",\"dose\":1,\"doseUnit\":\"成都市青白江区卫健局\",\"appointmentTime\":1711099435232,\"timePeriod\":2012,\"timePeriodName\":\"08:00-09:00\",\"doseTime\":null,\"imageUrl\":null,\"city\":null,\"county\":null,\"town\":null,\"msg\":null,\"createTime\":1711099435232}', '0', '8505', '27091056', 'broker-a', '192.168.2.230', '2024-03-22 17:25:35', '1711099535308');
INSERT INTO `rocket_mq_fail_msg` VALUES ('223', '2650', '1771105527242162176', '3', 'C0A802E6BB48610644256FE153560012', '{\"id\":1771105527242162176,\"recordType\":2032,\"vaild\":1,\"vaccineReleaseId\":1,\"vaccineReleaseName\":null,\"personId\":1764897018334937088,\"personName\":\"zy1\",\"sex\":1,\"age\":29,\"mobile\":\"15208492745\",\"idNumber\":\"510121198901221219\",\"openId\":\"xcn256hb\",\"siteId\":1,\"siteName\":\"攀成钢青白江医院（教育街51号）\",\"recordStatus\":null,\"vaccineId\":1,\"vaccineName\":\"新冠病毒灭活疫苗（Vero细胞）\",\"vaccineBatch\":\"SZKT10006\",\"manufacturer\":\"深圳康泰\",\"dose\":1,\"doseUnit\":\"成都市青白江区卫健局\",\"appointmentTime\":1711099437901,\"timePeriod\":2012,\"timePeriodName\":\"08:00-09:00\",\"doseTime\":null,\"imageUrl\":null,\"city\":null,\"county\":null,\"town\":null,\"msg\":null,\"createTime\":1711099437901}', '0', '8506', '27093819', 'broker-a', '192.168.2.230', '2024-03-22 17:25:38', '1711099538035');

-- ----------------------------
-- Table structure for undo_log
-- ----------------------------
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'pk',
  `branch_id` bigint NOT NULL COMMENT 'branch transaction id',
  `xid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'global transaction id',
  `context` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'undo_log context,such as serialization',
  `rollback_info` longblob NOT NULL COMMENT 'rollback info',
  `log_status` int NOT NULL COMMENT '0:normal status,1:defense status',
  `log_created` datetime(6) NOT NULL COMMENT 'create datetime',
  `log_modified` datetime(6) NOT NULL COMMENT 'modify datetime',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1343 DEFAULT CHARSET=utf8mb3 COMMENT='AT transaction mode undo table';

-- ----------------------------
-- Records of undo_log
-- ----------------------------
