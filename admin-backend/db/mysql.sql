/*
 Navicat Premium Data Transfer

 Source Server         : localhost_mysql
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost
 Source Database       : admin_fast

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : utf-8

 Date: 09/19/2018 16:14:15 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `budget_main_contract`
-- ----------------------------
DROP TABLE IF EXISTS `budget_main_contract`;
CREATE TABLE `budget_main_contract` (
  `contract_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `contract_name` varchar(50) NOT NULL COMMENT '项目名称',
  `contract_category` varchar(20) DEFAULT NULL COMMENT '项目类型',
  `contact_location` varchar(50) DEFAULT NULL COMMENT '项目地点',
  `contract_deadline` date DEFAULT NULL COMMENT '项目截止日期',
  `contract_quota` varchar(20) DEFAULT NULL COMMENT '定额类型',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`contract_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='合同信息';

-- ----------------------------
--  Records of `budget_main_contract`
-- ----------------------------
BEGIN;
INSERT INTO `budget_main_contract` VALUES ('6', 'ee', '建筑工程', 'ee', '2018-06-04', 'eee', '2018-06-28 16:56:16');
COMMIT;

-- ----------------------------
--  Table structure for `qrtz_blob_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `qrtz_calendars`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `qrtz_cron_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(120) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `qrtz_cron_triggers`
-- ----------------------------
BEGIN;
INSERT INTO `qrtz_cron_triggers` VALUES ('RenrenScheduler', 'TASK_1', 'DEFAULT', '0 0/30 * * * ?', 'Asia/Shanghai'), ('RenrenScheduler', 'TASK_2', 'DEFAULT', '0 0/30 * * * ?', 'Asia/Shanghai');
COMMIT;

-- ----------------------------
--  Table structure for `qrtz_fired_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`),
  KEY `IDX_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`),
  KEY `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`,`INSTANCE_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `qrtz_job_details`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `qrtz_job_details`
-- ----------------------------
BEGIN;
INSERT INTO `qrtz_job_details` VALUES ('RenrenScheduler', 'TASK_1', 'DEFAULT', null, 'io.admin.modules.job.utils.ScheduleJob', '0', '0', '0', '0', 0xaced0005737200156f72672e71756172747a2e4a6f62446174614d61709fb083e8bfa9b0cb020000787200266f72672e71756172747a2e7574696c732e537472696e674b65794469727479466c61674d61708208e8c3fbc55d280200015a0013616c6c6f77735472616e7369656e74446174617872001d6f72672e71756172747a2e7574696c732e4469727479466c61674d617013e62ead28760ace0200025a000564697274794c00036d617074000f4c6a6176612f7574696c2f4d61703b787001737200116a6176612e7574696c2e486173684d61700507dac1c31660d103000246000a6c6f6164466163746f724900097468726573686f6c6478703f4000000000000c7708000000100000000174000d4a4f425f504152414d5f4b45597372002d696f2e61646d696e2e6d6f64756c65732e6a6f622e656e746974792e5363686564756c654a6f62456e7469747900000000000000010200084c00086265616e4e616d657400124c6a6176612f6c616e672f537472696e673b4c000a63726561746554696d657400104c6a6176612f7574696c2f446174653b4c000e63726f6e45787072657373696f6e71007e00094c00056a6f6249647400104c6a6176612f6c616e672f4c6f6e673b4c000a6d6574686f644e616d6571007e00094c0006706172616d7371007e00094c000672656d61726b71007e00094c00067374617475737400134c6a6176612f6c616e672f496e74656765723b7870740008746573745461736b7372000e6a6176612e7574696c2e44617465686a81014b5974190300007870770800000158baf593307874000e3020302f3330202a202a202a203f7372000e6a6176612e6c616e672e4c6f6e673b8be490cc8f23df0200014a000576616c7565787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b020000787000000000000000017400047465737474000561646d696e74000fe69c89e58f82e695b0e6b58be8af95737200116a6176612e6c616e672e496e746567657212e2a0a4f781873802000149000576616c75657871007e0013000000007800), ('RenrenScheduler', 'TASK_2', 'DEFAULT', null, 'io.admin.modules.job.utils.ScheduleJob', '0', '0', '0', '0', 0xaced0005737200156f72672e71756172747a2e4a6f62446174614d61709fb083e8bfa9b0cb020000787200266f72672e71756172747a2e7574696c732e537472696e674b65794469727479466c61674d61708208e8c3fbc55d280200015a0013616c6c6f77735472616e7369656e74446174617872001d6f72672e71756172747a2e7574696c732e4469727479466c61674d617013e62ead28760ace0200025a000564697274794c00036d617074000f4c6a6176612f7574696c2f4d61703b787001737200116a6176612e7574696c2e486173684d61700507dac1c31660d103000246000a6c6f6164466163746f724900097468726573686f6c6478703f4000000000000c7708000000100000000174000d4a4f425f504152414d5f4b45597372002d696f2e61646d696e2e6d6f64756c65732e6a6f622e656e746974792e5363686564756c654a6f62456e7469747900000000000000010200084c00086265616e4e616d657400124c6a6176612f6c616e672f537472696e673b4c000a63726561746554696d657400104c6a6176612f7574696c2f446174653b4c000e63726f6e45787072657373696f6e71007e00094c00056a6f6249647400104c6a6176612f6c616e672f4c6f6e673b4c000a6d6574686f644e616d6571007e00094c0006706172616d7371007e00094c000672656d61726b71007e00094c00067374617475737400134c6a6176612f6c616e672f496e74656765723b7870740008746573745461736b7372000e6a6176612e7574696c2e44617465686a81014b5974190300007870770800000158c377c4607874000e3020302f3330202a202a202a203f7372000e6a6176612e6c616e672e4c6f6e673b8be490cc8f23df0200014a000576616c7565787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b0200007870000000000000000274000574657374327074000fe697a0e58f82e695b0e6b58be8af95737200116a6176612e6c616e672e496e746567657212e2a0a4f781873802000149000576616c75657871007e0013000000017800);
COMMIT;

-- ----------------------------
--  Table structure for `qrtz_locks`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `qrtz_locks`
-- ----------------------------
BEGIN;
INSERT INTO `qrtz_locks` VALUES ('RenrenScheduler', 'STATE_ACCESS'), ('RenrenScheduler', 'TRIGGER_ACCESS');
COMMIT;

-- ----------------------------
--  Table structure for `qrtz_paused_trigger_grps`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `qrtz_scheduler_state`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `qrtz_scheduler_state`
-- ----------------------------
BEGIN;
INSERT INTO `qrtz_scheduler_state` VALUES ('RenrenScheduler', 'wangcandeMacBook-Pro.local1536157846647', '1536159104789', '15000');
COMMIT;

-- ----------------------------
--  Table structure for `qrtz_simple_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `qrtz_simprop_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `qrtz_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_J` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`),
  KEY `IDX_QRTZ_T_G` (`SCHED_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_STATE` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `qrtz_triggers`
-- ----------------------------
BEGIN;
INSERT INTO `qrtz_triggers` VALUES ('RenrenScheduler', 'TASK_1', 'DEFAULT', 'TASK_1', 'DEFAULT', null, '1529978400000', '-1', '5', 'PAUSED', 'CRON', '1529976789000', '0', null, '2', 0xaced0005737200156f72672e71756172747a2e4a6f62446174614d61709fb083e8bfa9b0cb020000787200266f72672e71756172747a2e7574696c732e537472696e674b65794469727479466c61674d61708208e8c3fbc55d280200015a0013616c6c6f77735472616e7369656e74446174617872001d6f72672e71756172747a2e7574696c732e4469727479466c61674d617013e62ead28760ace0200025a000564697274794c00036d617074000f4c6a6176612f7574696c2f4d61703b787001737200116a6176612e7574696c2e486173684d61700507dac1c31660d103000246000a6c6f6164466163746f724900097468726573686f6c6478703f4000000000000c7708000000100000000174000d4a4f425f504152414d5f4b45597372002d696f2e61646d696e2e6d6f64756c65732e6a6f622e656e746974792e5363686564756c654a6f62456e7469747900000000000000010200084c00086265616e4e616d657400124c6a6176612f6c616e672f537472696e673b4c000a63726561746554696d657400104c6a6176612f7574696c2f446174653b4c000e63726f6e45787072657373696f6e71007e00094c00056a6f6249647400104c6a6176612f6c616e672f4c6f6e673b4c000a6d6574686f644e616d6571007e00094c0006706172616d7371007e00094c000672656d61726b71007e00094c00067374617475737400134c6a6176612f6c616e672f496e74656765723b7870740008746573745461736b7372000e6a6176612e7574696c2e44617465686a81014b5974190300007870770800000158baf593307874000e3020302f3330202a202a202a203f7372000e6a6176612e6c616e672e4c6f6e673b8be490cc8f23df0200014a000576616c7565787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b020000787000000000000000017400047465737474000561646d696e74000fe69c89e58f82e695b0e6b58be8af95737200116a6176612e6c616e672e496e746567657212e2a0a4f781873802000149000576616c75657871007e0013000000017800), ('RenrenScheduler', 'TASK_2', 'DEFAULT', 'TASK_2', 'DEFAULT', null, '1529978400000', '-1', '5', 'PAUSED', 'CRON', '1529976789000', '0', null, '2', 0xaced0005737200156f72672e71756172747a2e4a6f62446174614d61709fb083e8bfa9b0cb020000787200266f72672e71756172747a2e7574696c732e537472696e674b65794469727479466c61674d61708208e8c3fbc55d280200015a0013616c6c6f77735472616e7369656e74446174617872001d6f72672e71756172747a2e7574696c732e4469727479466c61674d617013e62ead28760ace0200025a000564697274794c00036d617074000f4c6a6176612f7574696c2f4d61703b787001737200116a6176612e7574696c2e486173684d61700507dac1c31660d103000246000a6c6f6164466163746f724900097468726573686f6c6478703f4000000000000c7708000000100000000174000d4a4f425f504152414d5f4b45597372002d696f2e61646d696e2e6d6f64756c65732e6a6f622e656e746974792e5363686564756c654a6f62456e7469747900000000000000010200084c00086265616e4e616d657400124c6a6176612f6c616e672f537472696e673b4c000a63726561746554696d657400104c6a6176612f7574696c2f446174653b4c000e63726f6e45787072657373696f6e71007e00094c00056a6f6249647400104c6a6176612f6c616e672f4c6f6e673b4c000a6d6574686f644e616d6571007e00094c0006706172616d7371007e00094c000672656d61726b71007e00094c00067374617475737400134c6a6176612f6c616e672f496e74656765723b7870740008746573745461736b7372000e6a6176612e7574696c2e44617465686a81014b5974190300007870770800000158c377c4607874000e3020302f3330202a202a202a203f7372000e6a6176612e6c616e672e4c6f6e673b8be490cc8f23df0200014a000576616c7565787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b0200007870000000000000000274000574657374327074000fe697a0e58f82e695b0e6b58be8af95737200116a6176612e6c616e672e496e746567657212e2a0a4f781873802000149000576616c75657871007e0013000000017800);
COMMIT;

-- ----------------------------
--  Table structure for `schedule_job`
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job`;
CREATE TABLE `schedule_job` (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务id',
  `bean_name` varchar(200) DEFAULT NULL COMMENT 'spring bean名称',
  `method_name` varchar(100) DEFAULT NULL COMMENT '方法名',
  `params` varchar(2000) DEFAULT NULL COMMENT '参数',
  `cron_expression` varchar(100) DEFAULT NULL COMMENT 'cron表达式',
  `status` tinyint(4) DEFAULT NULL COMMENT '任务状态  0：正常  1：暂停',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='定时任务';

-- ----------------------------
--  Records of `schedule_job`
-- ----------------------------
BEGIN;
INSERT INTO `schedule_job` VALUES ('1', 'testTask', 'test', 'admin', '0 0/30 * * * ?', '1', '有参数测试', '2016-12-01 23:16:46'), ('2', 'testTask', 'test2', null, '0 0/30 * * * ?', '1', '无参数测试', '2016-12-03 14:55:56');
COMMIT;

-- ----------------------------
--  Table structure for `schedule_job_log`
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job_log`;
CREATE TABLE `schedule_job_log` (
  `log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志id',
  `job_id` bigint(20) NOT NULL COMMENT '任务id',
  `bean_name` varchar(200) DEFAULT NULL COMMENT 'spring bean名称',
  `method_name` varchar(100) DEFAULT NULL COMMENT '方法名',
  `params` varchar(2000) DEFAULT NULL COMMENT '参数',
  `status` tinyint(4) NOT NULL COMMENT '任务状态    0：成功    1：失败',
  `error` varchar(2000) DEFAULT NULL COMMENT '失败信息',
  `times` int(11) NOT NULL COMMENT '耗时(单位：毫秒)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`log_id`),
  KEY `job_id` (`job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='定时任务日志';

-- ----------------------------
--  Records of `schedule_job_log`
-- ----------------------------
BEGIN;
INSERT INTO `schedule_job_log` VALUES ('1', '1', 'testTask', 'test', 'admin', '0', null, '1030', '2018-06-26 10:00:00'), ('2', '1', 'testTask', 'test', 'admin', '0', null, '1044', '2018-06-26 21:30:00'), ('3', '1', 'testTask', 'test', 'admin', '0', null, '1011', '2018-06-26 22:00:00'), ('4', '1', 'testTask', 'test', 'admin', '0', null, '1011', '2018-06-26 22:30:00'), ('5', '1', 'testTask', 'test', 'admin', '0', null, '1009', '2018-06-26 23:00:00'), ('6', '1', 'testTask', 'test', 'admin', '0', null, '1016', '2018-06-26 23:30:00'), ('7', '1', 'testTask', 'test', 'admin', '0', null, '1124', '2018-06-27 00:00:00'), ('8', '1', 'testTask', 'test', 'admin', '0', null, '1021', '2018-06-27 07:30:00'), ('9', '1', 'testTask', 'test', 'admin', '0', null, '1019', '2018-06-27 08:00:00'), ('10', '1', 'testTask', 'test', 'admin', '0', null, '1028', '2018-06-27 08:30:00'), ('11', '1', 'testTask', 'test', 'admin', '0', null, '1018', '2018-06-27 09:00:00'), ('12', '1', 'testTask', 'test', 'admin', '0', null, '1013', '2018-06-27 09:30:00'), ('13', '1', 'testTask', 'test', 'admin', '0', null, '1033', '2018-06-27 10:00:00'), ('14', '1', 'testTask', 'test', 'admin', '0', null, '1112', '2018-06-27 15:30:00'), ('15', '1', 'testTask', 'test', 'admin', '0', null, '1074', '2018-06-27 15:41:32');
COMMIT;

-- ----------------------------
--  Table structure for `sys_captcha`
-- ----------------------------
DROP TABLE IF EXISTS `sys_captcha`;
CREATE TABLE `sys_captcha` (
  `uuid` char(36) NOT NULL COMMENT 'uuid',
  `code` varchar(6) NOT NULL COMMENT '验证码',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统验证码';

-- ----------------------------
--  Records of `sys_captcha`
-- ----------------------------
BEGIN;
INSERT INTO `sys_captcha` VALUES ('3e2dde0f-4826-496d-8476-6d0da29f4e8a', 'pyna2', '2018-06-27 17:13:53'), ('4e1ad6fa-a115-4723-8b00-301dabb2bd7b', 'mcmn6', '2018-06-26 09:35:46'), ('a702f621-f976-47e4-8c67-ab3fb656d72d', 'acff8', '2018-06-26 09:37:08'), ('bb2b55b2-6593-4f81-8e35-b81c8a47cc0b', 'xg854', '2018-06-28 16:58:16'), ('ceb80ed6-5f08-43fe-81eb-a2665f8450c4', 'n67ap', '2018-07-03 09:15:52'), ('edec4e80-3645-416f-817e-eb5e2cf32f5a', 'enbb8', '2018-06-26 09:37:19'), ('fd202382-8b46-44a6-8eee-9009c4034484', 'adggb', '2018-06-26 09:37:29');
COMMIT;

-- ----------------------------
--  Table structure for `sys_config`
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `param_key` varchar(50) DEFAULT NULL COMMENT 'key',
  `param_value` varchar(2000) DEFAULT NULL COMMENT 'value',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态   0：隐藏   1：显示',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `param_key` (`param_key`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='系统配置信息表';

-- ----------------------------
--  Records of `sys_config`
-- ----------------------------
BEGIN;
INSERT INTO `sys_config` VALUES ('1', 'CLOUD_STORAGE_CONFIG_KEY', '{\"aliyunAccessKeyId\":\"\",\"aliyunAccessKeySecret\":\"\",\"aliyunBucketName\":\"\",\"aliyunDomain\":\"\",\"aliyunEndPoint\":\"\",\"aliyunPrefix\":\"\",\"qcloudBucketName\":\"\",\"qcloudDomain\":\"\",\"qcloudPrefix\":\"\",\"qcloudSecretId\":\"\",\"qcloudSecretKey\":\"\",\"qiniuAccessKey\":\"NrgMfABZxWLo5B-YYSjoE8-AZ1EISdi1Z3ubLOeZ\",\"qiniuBucketName\":\"ios-app\",\"qiniuDomain\":\"http://7xqbwh.dl1.z0.glb.clouddn.com\",\"qiniuPrefix\":\"upload\",\"qiniuSecretKey\":\"uIwJHevMRWU0VLxFvgy0tAcOdGqasdtVlJkdy6vV\",\"type\":1}', '0', '云存储配置信息');
COMMIT;

-- ----------------------------
--  Table structure for `sys_dictionary`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dictionary`;
CREATE TABLE `sys_dictionary` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `code` varchar(10) NOT NULL COMMENT '编码',
  `name` varchar(20) NOT NULL COMMENT '名称',
  `type` varchar(10) NOT NULL COMMENT '类型',
  `active` tinyint(4) DEFAULT NULL COMMENT '是否有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='数据字典';

-- ----------------------------
--  Records of `sys_dictionary`
-- ----------------------------
BEGIN;
INSERT INTO `sys_dictionary` VALUES ('2', '12', '22', '22', '1'), ('3', '11', '11', '1111', '1'), ('4', '333', '12', '121212', '1');
COMMIT;

-- ----------------------------
--  Table structure for `sys_log`
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200) DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) DEFAULT NULL COMMENT '请求参数',
  `time` bigint(20) NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8 COMMENT='系统日志';

-- ----------------------------
--  Records of `sys_log`
-- ----------------------------
BEGIN;
INSERT INTO `sys_log` VALUES ('1', 'admin', '保存角色', 'io.admin.modules.sys.controller.SysRoleController.save()', '{\"roleId\":1,\"roleName\":\"项目经理\",\"remark\":\"\",\"createUserId\":1,\"menuIdList\":[4,23,24,25,26,-666666,1],\"createTime\":\"Jun 26, 2018 9:34:04 AM\"}', '82', '0:0:0:0:0:0:0:1', '2018-06-26 09:34:05'), ('2', 'admin', '修改角色', 'io.admin.modules.sys.controller.SysRoleController.update()', '{\"roleId\":1,\"roleName\":\"项目经理\",\"createUserId\":1,\"menuIdList\":[2,15,16,17,18,4,23,24,25,26,-666666,1]}', '27', '0:0:0:0:0:0:0:1', '2018-06-26 09:34:11'), ('3', 'admin', '修改角色', 'io.admin.modules.sys.controller.SysRoleController.update()', '{\"roleId\":1,\"roleName\":\"项目经理\",\"createUserId\":1,\"menuIdList\":[2,15,16,17,18,3,19,20,21,22,4,23,24,25,26,-666666,1]}', '30', '0:0:0:0:0:0:0:1', '2018-06-26 09:34:23'), ('4', 'admin', '保存用户', 'io.admin.modules.sys.controller.SysUserController.save()', '{\"userId\":2,\"username\":\"wangcan\",\"password\":\"d7d94c7038aa09f7a0fc70d07d11fa3703ebfec71ee1852b6f800ca4ac50445f\",\"salt\":\"NcFgUchliMrFO2NQVRTX\",\"email\":\"wangcan@qq.com\",\"mobile\":\"13615552868\",\"status\":1,\"roleIdList\":[1],\"createUserId\":1,\"createTime\":\"Jun 26, 2018 9:34:55 AM\"}', '60', '0:0:0:0:0:0:0:1', '2018-06-26 09:34:55'), ('5', 'admin', '修改用户', 'io.admin.modules.sys.controller.SysUserController.update()', '{\"userId\":2,\"username\":\"wangcan\",\"salt\":\"NcFgUchliMrFO2NQVRTX\",\"email\":\"wangcan@qq.com\",\"mobile\":\"13615552869\",\"status\":1,\"roleIdList\":[1],\"createUserId\":1}', '289', '0:0:0:0:0:0:0:1', '2018-06-26 09:35:03'), ('6', 'admin', '修改用户', 'io.admin.modules.sys.controller.SysUserController.update()', '{\"userId\":1,\"username\":\"admin\",\"salt\":\"YzcmCZNvbXocrsz9dm8e\",\"email\":\"root@admin.io\",\"mobile\":\"13612345678\",\"status\":1,\"roleIdList\":[],\"createUserId\":1}', '277', '0:0:0:0:0:0:0:1', '2018-06-26 21:20:42'), ('7', 'admin', '修改用户', 'io.admin.modules.sys.controller.SysUserController.update()', '{\"userId\":2,\"username\":\"wangcan\",\"salt\":\"NcFgUchliMrFO2NQVRTX\",\"email\":\"wangcan@qq.com\",\"mobile\":\"13615552868\",\"status\":1,\"roleIdList\":[],\"createUserId\":1}', '48789', '0:0:0:0:0:0:0:1', '2018-06-26 21:25:47'), ('8', 'admin', '修改用户', 'io.admin.modules.sys.controller.SysUserController.update()', '{\"userId\":2,\"username\":\"wangcan\",\"salt\":\"NcFgUchliMrFO2NQVRTX\",\"email\":\"wangcan@qq.com\",\"mobile\":\"13615552868\",\"status\":1,\"roleIdList\":[],\"createUserId\":1}', '26', '0:0:0:0:0:0:0:1', '2018-06-26 21:26:10'), ('9', 'admin', '修改用户', 'io.admin.modules.sys.controller.SysUserController.update()', '{\"userId\":2,\"username\":\"wangcan\",\"salt\":\"NcFgUchliMrFO2NQVRTX\",\"email\":\"wangcan@qq.com\",\"mobile\":\"13615552868\",\"status\":1,\"roleIdList\":[],\"createUserId\":1}', '14', '0:0:0:0:0:0:0:1', '2018-06-26 21:26:10'), ('10', 'admin', '修改用户', 'io.admin.modules.sys.controller.SysUserController.update()', '{\"userId\":2,\"username\":\"wangcan\",\"salt\":\"NcFgUchliMrFO2NQVRTX\",\"email\":\"wangcan@qq.com\",\"mobile\":\"13615552868\",\"status\":1,\"roleIdList\":[],\"createUserId\":1}', '19', '0:0:0:0:0:0:0:1', '2018-06-26 21:26:10'), ('11', 'admin', '修改用户', 'io.admin.modules.sys.controller.SysUserController.update()', '{\"userId\":2,\"username\":\"wangcan\",\"salt\":\"NcFgUchliMrFO2NQVRTX\",\"email\":\"wangcan@qq.com\",\"mobile\":\"13615552868\",\"status\":1,\"roleIdList\":[],\"createUserId\":1}', '20', '0:0:0:0:0:0:0:1', '2018-06-26 21:26:10'), ('12', 'admin', '修改用户', 'io.admin.modules.sys.controller.SysUserController.update()', '{\"userId\":1,\"username\":\"admin\",\"salt\":\"YzcmCZNvbXocrsz9dm8e\",\"email\":\"root@admin.io\",\"mobile\":\"13612345678\",\"status\":1,\"roleIdList\":[],\"createUserId\":1}', '21', '0:0:0:0:0:0:0:1', '2018-06-26 21:26:15'), ('13', 'admin', '修改用户', 'io.admin.modules.sys.controller.SysUserController.update()', '{\"userId\":2,\"username\":\"wangcan\",\"salt\":\"NcFgUchliMrFO2NQVRTX\",\"email\":\"wangcan@qq.com\",\"mobile\":\"13615552860\",\"status\":1,\"roleIdList\":[],\"createUserId\":1}', '19', '0:0:0:0:0:0:0:1', '2018-06-26 21:47:32'), ('14', 'admin', '修改用户', 'io.admin.modules.sys.controller.SysUserController.update()', '{\"userId\":2,\"username\":\"wangcan\",\"salt\":\"NcFgUchliMrFO2NQVRTX\",\"email\":\"wangcan@qq.com\",\"mobile\":\"13615552860\",\"status\":1,\"roleIdList\":[],\"createUserId\":1}', '21', '0:0:0:0:0:0:0:1', '2018-06-26 21:53:23'), ('15', 'admin', '修改用户', 'io.admin.modules.sys.controller.SysUserController.update()', '{\"userId\":2,\"username\":\"wangcan\",\"salt\":\"NcFgUchliMrFO2NQVRTX\",\"email\":\"wangcan@qq.com\",\"mobile\":\"13615552860\",\"status\":1,\"roleIdList\":[],\"createUserId\":1}', '15', '0:0:0:0:0:0:0:1', '2018-06-26 21:53:24'), ('16', 'admin', '修改用户', 'io.admin.modules.sys.controller.SysUserController.update()', '{\"userId\":2,\"username\":\"wangcan\",\"salt\":\"NcFgUchliMrFO2NQVRTX\",\"email\":\"wangcan@qq.com\",\"mobile\":\"13615552860\",\"status\":1,\"roleIdList\":[],\"createUserId\":1}', '14', '0:0:0:0:0:0:0:1', '2018-06-26 21:53:24'), ('17', 'admin', '修改用户', 'io.admin.modules.sys.controller.SysUserController.update()', '{\"userId\":2,\"username\":\"wangcan\",\"salt\":\"NcFgUchliMrFO2NQVRTX\",\"email\":\"wangcan@qq.com\",\"mobile\":\"13615552860\",\"status\":1,\"roleIdList\":[],\"createUserId\":1}', '29', '0:0:0:0:0:0:0:1', '2018-06-26 22:04:08'), ('18', 'admin', '修改用户', 'io.admin.modules.sys.controller.SysUserController.update()', '{\"userId\":2,\"username\":\"wangcan\",\"salt\":\"NcFgUchliMrFO2NQVRTX\",\"email\":\"wangcan@qq.com\",\"mobile\":\"13615552861\",\"status\":1,\"roleIdList\":[],\"createUserId\":1}', '14', '0:0:0:0:0:0:0:1', '2018-06-26 22:04:14'), ('19', 'admin', '修改用户', 'io.admin.modules.sys.controller.SysUserController.update()', '{\"userId\":2,\"username\":\"wangcan1\",\"salt\":\"NcFgUchliMrFO2NQVRTX\",\"email\":\"wangcan@qq.com\",\"mobile\":\"13615552861\",\"status\":1,\"roleIdList\":[],\"createUserId\":1}', '24', '0:0:0:0:0:0:0:1', '2018-06-26 22:04:25'), ('20', 'admin', '修改用户', 'io.admin.modules.sys.controller.SysUserController.update()', '{\"userId\":2,\"username\":\"wangcan1\",\"salt\":\"NcFgUchliMrFO2NQVRTX\",\"email\":\"wangcan@qq.com\",\"mobile\":\"13615552861\",\"status\":1,\"roleIdList\":[1],\"createUserId\":1}', '50', '0:0:0:0:0:0:0:1', '2018-06-26 22:10:25'), ('21', 'admin', '修改角色', 'io.admin.modules.sys.controller.SysRoleController.update()', '{\"roleId\":1,\"roleName\":\"项目经理\",\"createUserId\":1,\"menuIdList\":[2,15,16,17,18,3,19,20,21,22,4,23,24,25,26,5,-666666,1]}', '46', '0:0:0:0:0:0:0:1', '2018-06-26 22:18:14'), ('22', 'admin', '修改用户', 'io.admin.modules.sys.controller.SysUserController.update()', '{\"userId\":2,\"username\":\"wangcan1\",\"salt\":\"NcFgUchliMrFO2NQVRTX\",\"email\":\"wangcan@qq.com\",\"mobile\":\"13615552863\",\"status\":1,\"roleIdList\":[1],\"createUserId\":1}', '30', '0:0:0:0:0:0:0:1', '2018-06-26 22:43:10'), ('23', 'admin', '修改角色', 'io.admin.modules.sys.controller.SysRoleController.update()', '{\"roleId\":1,\"roleName\":\"项目经理\",\"remark\":\"222\",\"createUserId\":1,\"menuIdList\":[2,15,16,17,18,3,19,20,21,22,4,23,24,25,26,5,-666666,1]}', '38', '0:0:0:0:0:0:0:1', '2018-06-26 22:54:04'), ('24', 'admin', '修改用户', 'io.admin.modules.sys.controller.SysUserController.update()', '{\"userId\":2,\"username\":\"wangcan1\",\"salt\":\"NcFgUchliMrFO2NQVRTX\",\"email\":\"wangcan@qq.com\",\"mobile\":\"13615552863\",\"status\":1,\"roleIdList\":[1],\"createUserId\":1}', '89934', '0:0:0:0:0:0:0:1', '2018-06-26 22:58:53'), ('25', 'admin', '修改用户', 'io.admin.modules.sys.controller.SysUserController.update()', '{\"userId\":2,\"username\":\"wangcan1\",\"salt\":\"NcFgUchliMrFO2NQVRTX\",\"email\":\"wangcan@qq.com\",\"mobile\":\"13615552863\",\"status\":1,\"roleIdList\":[1],\"createUserId\":1}', '17', '0:0:0:0:0:0:0:1', '2018-06-26 22:58:57'), ('26', 'admin', '修改角色', 'io.admin.modules.sys.controller.SysRoleController.update()', '{\"roleId\":1,\"roleName\":\"项目经理\",\"remark\":\"222\",\"createUserId\":1,\"menuIdList\":[2,15,16,17,18,3,19,20,21,22,4,23,24,25,26,5,-666666,1]}', '39', '0:0:0:0:0:0:0:1', '2018-06-26 23:00:41'), ('27', 'admin', '保存菜单', 'io.admin.modules.sys.controller.SysMenuController.save()', '{\"menuId\":31,\"parentId\":0,\"name\":\"预算管理\",\"type\":0,\"icon\":\"log\",\"orderNum\":1}', '14', '0:0:0:0:0:0:0:1', '2018-06-27 08:03:41'), ('28', 'admin', '保存菜单', 'io.admin.modules.sys.controller.SysMenuController.save()', '{\"menuId\":32,\"parentId\":31,\"name\":\"施工预算\",\"url\":\"buget/construction/list\",\"perms\":\"\",\"type\":1,\"icon\":\"bianji\",\"orderNum\":0}', '12', '0:0:0:0:0:0:0:1', '2018-06-27 08:05:24'), ('29', 'admin', '修改角色', 'io.admin.modules.sys.controller.SysRoleController.update()', '{\"roleId\":1,\"roleName\":\"项目经理\",\"remark\":\"222\",\"createUserId\":1,\"menuIdList\":[2,15,16,17,18,3,19,20,21,22,4,23,24,25,26,5,31,32,-666666,1]}', '117', '0:0:0:0:0:0:0:1', '2018-06-27 08:05:47'), ('30', 'admin', '修改用户', 'io.admin.modules.sys.controller.SysUserController.update()', '{\"userId\":2,\"username\":\"wangcan1\",\"salt\":\"NcFgUchliMrFO2NQVRTX\",\"email\":\"wangcan@qq.com\",\"mobile\":\"13615552863\",\"status\":1,\"roleIdList\":[1],\"createUserId\":1}', '25', '0:0:0:0:0:0:0:1', '2018-06-27 08:05:58'), ('31', 'admin', '删除菜单', 'io.admin.modules.sys.controller.SysMenuController.delete()', '31', '1', '0:0:0:0:0:0:0:1', '2018-06-27 09:07:41'), ('32', 'admin', '删除菜单', 'io.admin.modules.sys.controller.SysMenuController.delete()', '32', '34', '0:0:0:0:0:0:0:1', '2018-06-27 09:07:48'), ('33', 'admin', '删除菜单', 'io.admin.modules.sys.controller.SysMenuController.delete()', '31', '0', '0:0:0:0:0:0:0:1', '2018-06-27 09:07:51'), ('34', 'admin', '删除菜单', 'io.admin.modules.sys.controller.SysMenuController.delete()', '31', '29', '0:0:0:0:0:0:0:1', '2018-06-27 09:10:17'), ('35', 'admin', '立即执行任务', 'io.admin.modules.job.controller.ScheduleJobController.run()', '[1]', '35', '0:0:0:0:0:0:0:1', '2018-06-27 15:41:32'), ('36', 'admin', '暂停定时任务', 'io.admin.modules.job.controller.ScheduleJobController.pause()', '[1]', '24', '0:0:0:0:0:0:0:1', '2018-06-27 15:41:37'), ('37', 'admin', '保存菜单', 'io.admin.modules.sys.controller.SysMenuController.save()', '{\"menuId\":33,\"parentId\":0,\"name\":\"合同预算管理\",\"url\":\"\",\"perms\":\"\",\"type\":0,\"icon\":\"log\",\"orderNum\":1}', '32', '0:0:0:0:0:0:0:1', '2018-06-27 17:09:44'), ('38', 'admin', '保存菜单', 'io.admin.modules.sys.controller.SysMenuController.save()', '{\"menuId\":34,\"parentId\":33,\"name\":\"合同管咯\",\"url\":\"budget/mainContract\",\"perms\":\"\",\"type\":1,\"icon\":\"dangdifill\",\"orderNum\":0}', '56', '0:0:0:0:0:0:0:1', '2018-06-27 17:40:42'), ('39', 'admin', '修改菜单', 'io.admin.modules.sys.controller.SysMenuController.update()', '{\"menuId\":34,\"parentId\":33,\"name\":\"合同管理\",\"url\":\"budget/mainContract\",\"type\":1,\"icon\":\"dangdifill\",\"orderNum\":0}', '38', '0:0:0:0:0:0:0:1', '2018-06-27 17:41:03'), ('40', 'admin', '保存菜单', 'io.admin.modules.sys.controller.SysMenuController.save()', '{\"menuId\":35,\"parentId\":34,\"name\":\"查看\",\"url\":\"\",\"perms\":\"budget:mainContract:list\",\"type\":2,\"icon\":\"\",\"orderNum\":0}', '12', '0:0:0:0:0:0:0:1', '2018-06-27 17:48:01'), ('41', 'admin', '修改菜单', 'io.admin.modules.sys.controller.SysMenuController.update()', '{\"menuId\":34,\"parentId\":33,\"name\":\"合同管理\",\"url\":\"budget/contract/mainContract\",\"type\":1,\"icon\":\"dangdifill\",\"orderNum\":0}', '52', '0:0:0:0:0:0:0:1', '2018-06-27 18:07:18'), ('42', 'admin', '保存菜单', 'io.admin.modules.sys.controller.SysMenuController.save()', '{\"menuId\":36,\"parentId\":34,\"name\":\"新增\",\"url\":\"\",\"perms\":\"budget:mainContract:save\",\"type\":2,\"icon\":\"\",\"orderNum\":0}', '19', '0:0:0:0:0:0:0:1', '2018-06-27 18:30:02'), ('43', 'admin', '保存菜单', 'io.admin.modules.sys.controller.SysMenuController.save()', '{\"menuId\":37,\"parentId\":34,\"name\":\"删除\",\"url\":\"\",\"perms\":\"budget:mainContract:delete\",\"type\":2,\"icon\":\"\",\"orderNum\":0}', '20', '0:0:0:0:0:0:0:1', '2018-06-27 18:33:56'), ('44', 'admin', '保存用户', 'io.admin.modules.sys.controller.SysUserController.save()', '{\"userId\":3,\"username\":\"呃呃呃\",\"password\":\"fab2e09d670a9dfdf1c81cd417fb047a6b64b4ce9425dc9e09c3cf955668e328\",\"salt\":\"UbT8uQF1KQY349IpMEy8\",\"email\":\"11@qq.com\",\"mobile\":\"13615552868\",\"status\":1,\"roleIdList\":[],\"createUserId\":1,\"createTime\":\"Jun 27, 2018 10:12:34 PM\"}', '150', '0:0:0:0:0:0:0:1', '2018-06-27 22:12:35'), ('45', 'admin', '保存菜单', 'io.admin.modules.sys.controller.SysMenuController.save()', '{\"menuId\":38,\"parentId\":34,\"name\":\"修改\",\"url\":\"\",\"perms\":\"budget:mainContract:update\",\"type\":2,\"icon\":\"\",\"orderNum\":0}', '47', '0:0:0:0:0:0:0:1', '2018-06-28 17:07:01'), ('46', 'admin', '修改菜单', 'io.admin.modules.sys.controller.SysMenuController.update()', '{\"menuId\":35,\"parentId\":34,\"name\":\"查看\",\"perms\":\"budget:mainContract:list,budget:mainContract:info\",\"type\":2,\"orderNum\":0}', '27', '0:0:0:0:0:0:0:1', '2018-06-28 17:08:12'), ('47', 'admin', '修改菜单', 'io.admin.modules.sys.controller.SysMenuController.update()', '{\"menuId\":29,\"parentId\":1,\"name\":\"系统日志\",\"url\":\"sys/log\",\"perms\":\"sys:log:list\",\"type\":1,\"icon\":\"log\",\"orderNum\":8}', '31', '0:0:0:0:0:0:0:1', '2018-07-04 20:01:39'), ('48', 'admin', '修改菜单', 'io.admin.modules.sys.controller.SysMenuController.update()', '{\"menuId\":27,\"parentId\":1,\"name\":\"参数管理\",\"url\":\"sys/config\",\"perms\":\"sys:config:list,sys:config:info,sys:config:save,sys:config:update,sys:config:delete\",\"type\":1,\"icon\":\"config\",\"orderNum\":7}', '19', '0:0:0:0:0:0:0:1', '2018-07-04 20:01:47'), ('49', 'admin', '修改菜单', 'io.admin.modules.sys.controller.SysMenuController.update()', '{\"menuId\":6,\"parentId\":1,\"name\":\"定时任务\",\"url\":\"job/schedule\",\"type\":1,\"icon\":\"job\",\"orderNum\":6}', '20', '0:0:0:0:0:0:0:1', '2018-07-04 20:01:56'), ('50', 'admin', '修改菜单', 'io.admin.modules.sys.controller.SysMenuController.update()', '{\"menuId\":5,\"parentId\":1,\"name\":\"SQL监控\",\"url\":\"http://localhost:8080/admin-fast/druid/sql.html\",\"type\":1,\"icon\":\"sql\",\"orderNum\":5}', '12', '0:0:0:0:0:0:0:1', '2018-07-04 20:02:04'), ('51', 'admin', '保存菜单', 'io.admin.modules.sys.controller.SysMenuController.save()', '{\"menuId\":39,\"parentId\":1,\"name\":\"数据字段\",\"url\":\"sys/dic\",\"perms\":\"\",\"type\":1,\"icon\":\"zhedie\",\"orderNum\":4}', '24', '0:0:0:0:0:0:0:1', '2018-07-04 20:03:24'), ('52', 'admin', '删除用户', 'io.admin.modules.sys.controller.SysUserController.delete()', '[3]', '24', '0:0:0:0:0:0:0:1', '2018-07-04 20:03:40'), ('53', 'admin', '删除用户', 'io.admin.modules.sys.controller.SysUserController.delete()', '[2]', '13', '0:0:0:0:0:0:0:1', '2018-07-04 20:03:42'), ('54', 'admin', '修改角色', 'io.admin.modules.sys.controller.SysRoleController.update()', '{\"roleId\":1,\"roleName\":\"项目经理\",\"remark\":\"222222\",\"createUserId\":1,\"menuIdList\":[2,15,16,17,18,3,19,20,21,22,4,23,24,25,26,5,-666666,1]}', '79', '0:0:0:0:0:0:0:1', '2018-07-04 21:28:00'), ('55', 'admin', '修改角色', 'io.admin.modules.sys.controller.SysRoleController.update()', '{\"roleId\":1,\"roleName\":\"项目经理\",\"remark\":\"222222\",\"createUserId\":1,\"menuIdList\":[2,15,16,17,18,3,19,20,21,22,4,23,24,25,26,5,6,7,8,9,10,11,12,13,14,-666666,1]}', '36', '0:0:0:0:0:0:0:1', '2018-07-04 21:28:05'), ('56', 'admin', '修改菜单', 'io.admin.modules.sys.controller.SysMenuController.update()', '{\"menuId\":39,\"parentId\":1,\"name\":\"数据字典\",\"url\":\"sys/dic\",\"type\":1,\"icon\":\"dictionary\",\"orderNum\":4}', '48', '0:0:0:0:0:0:0:1', '2018-07-04 22:15:34'), ('57', 'admin', '保存菜单', 'io.admin.modules.sys.controller.SysMenuController.save()', '{\"menuId\":40,\"parentId\":39,\"name\":\"查看\",\"url\":\"\",\"perms\":\"sys:dictionary:list,sys:dictionary:info\",\"type\":2,\"icon\":\"\",\"orderNum\":0}', '25', '0:0:0:0:0:0:0:1', '2018-07-04 22:34:57'), ('58', 'admin', '修改菜单', 'io.admin.modules.sys.controller.SysMenuController.update()', '{\"menuId\":40,\"parentId\":39,\"name\":\"查看\",\"perms\":\"sys:dic:list,sys:dic:info\",\"type\":2,\"orderNum\":0}', '98', '0:0:0:0:0:0:0:1', '2018-07-04 22:51:08'), ('59', 'admin', '保存菜单', 'io.admin.modules.sys.controller.SysMenuController.save()', '{\"menuId\":41,\"parentId\":39,\"name\":\"新增\",\"perms\":\"sys:dic:add\",\"type\":2,\"orderNum\":0}', '23', '0:0:0:0:0:0:0:1', '2018-07-04 22:52:08'), ('60', 'admin', '修改菜单', 'io.admin.modules.sys.controller.SysMenuController.update()', '{\"menuId\":41,\"parentId\":39,\"name\":\"新增\",\"perms\":\"sys:dic:save\",\"type\":2,\"orderNum\":0}', '16', '0:0:0:0:0:0:0:1', '2018-07-04 22:52:40'), ('61', 'admin', '保存菜单', 'io.admin.modules.sys.controller.SysMenuController.save()', '{\"menuId\":42,\"parentId\":39,\"name\":\"更新\",\"url\":\"\",\"perms\":\"sys:dic:update\",\"type\":2,\"icon\":\"\",\"orderNum\":0}', '11', '0:0:0:0:0:0:0:1', '2018-07-05 07:58:27'), ('62', 'admin', '保存菜单', 'io.admin.modules.sys.controller.SysMenuController.save()', '{\"menuId\":43,\"parentId\":39,\"name\":\"删除\",\"url\":\"\",\"perms\":\"sys:dic:delete\",\"type\":2,\"icon\":\"\",\"orderNum\":0}', '12', '0:0:0:0:0:0:0:1', '2018-07-05 08:09:24'), ('63', 'admin', '删除菜单', 'io.admin.modules.sys.controller.SysMenuController.delete()', '33', '6', '0:0:0:0:0:0:0:1', '2018-07-17 09:20:46'), ('64', 'admin', '删除菜单', 'io.admin.modules.sys.controller.SysMenuController.delete()', '38', '77', '0:0:0:0:0:0:0:1', '2018-07-17 09:20:52'), ('65', 'admin', '删除菜单', 'io.admin.modules.sys.controller.SysMenuController.delete()', '37', '23', '0:0:0:0:0:0:0:1', '2018-07-17 09:20:54'), ('66', 'admin', '删除菜单', 'io.admin.modules.sys.controller.SysMenuController.delete()', '36', '24', '0:0:0:0:0:0:0:1', '2018-07-17 09:21:01'), ('67', 'admin', '删除菜单', 'io.admin.modules.sys.controller.SysMenuController.delete()', '35', '24', '0:0:0:0:0:0:0:1', '2018-07-17 09:21:11'), ('68', 'admin', '删除菜单', 'io.admin.modules.sys.controller.SysMenuController.delete()', '34', '19', '0:0:0:0:0:0:0:1', '2018-07-17 09:21:17'), ('69', 'admin', '删除菜单', 'io.admin.modules.sys.controller.SysMenuController.delete()', '33', '18', '0:0:0:0:0:0:0:1', '2018-07-17 09:21:20'), ('70', 'admin', '删除菜单', 'io.admin.modules.sys.controller.SysMenuController.delete()', '27', '1', '0:0:0:0:0:0:0:1', '2018-09-05 22:46:34');
COMMIT;

-- ----------------------------
--  Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- ----------------------------
--  Records of `sys_menu`
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` VALUES ('1', '0', '系统管理', null, null, '0', 'system', '0'), ('2', '1', '管理员列表', 'sys/user', null, '1', 'admin', '1'), ('3', '1', '角色管理', 'sys/role', null, '1', 'role', '2'), ('4', '1', '菜单管理', 'sys/menu', null, '1', 'menu', '3'), ('5', '1', 'SQL监控', 'http://localhost:8080/admin-fast/druid/sql.html', null, '1', 'sql', '5'), ('6', '1', '定时任务', 'job/schedule', null, '1', 'job', '6'), ('7', '6', '查看', null, 'sys:schedule:list,sys:schedule:info', '2', null, '0'), ('8', '6', '新增', null, 'sys:schedule:save', '2', null, '0'), ('9', '6', '修改', null, 'sys:schedule:update', '2', null, '0'), ('10', '6', '删除', null, 'sys:schedule:delete', '2', null, '0'), ('11', '6', '暂停', null, 'sys:schedule:pause', '2', null, '0'), ('12', '6', '恢复', null, 'sys:schedule:resume', '2', null, '0'), ('13', '6', '立即执行', null, 'sys:schedule:run', '2', null, '0'), ('14', '6', '日志列表', null, 'sys:schedule:log', '2', null, '0'), ('15', '2', '查看', null, 'sys:user:list,sys:user:info', '2', null, '0'), ('16', '2', '新增', null, 'sys:user:save,sys:role:select', '2', null, '0'), ('17', '2', '修改', null, 'sys:user:update,sys:role:select', '2', null, '0'), ('18', '2', '删除', null, 'sys:user:delete', '2', null, '0'), ('19', '3', '查看', null, 'sys:role:list,sys:role:info', '2', null, '0'), ('20', '3', '新增', null, 'sys:role:save,sys:menu:list', '2', null, '0'), ('21', '3', '修改', null, 'sys:role:update,sys:menu:list', '2', null, '0'), ('22', '3', '删除', null, 'sys:role:delete', '2', null, '0'), ('23', '4', '查看', null, 'sys:menu:list,sys:menu:info', '2', null, '0'), ('24', '4', '新增', null, 'sys:menu:save,sys:menu:select', '2', null, '0'), ('25', '4', '修改', null, 'sys:menu:update,sys:menu:select', '2', null, '0'), ('26', '4', '删除', null, 'sys:menu:delete', '2', null, '0'), ('29', '1', '系统日志', 'sys/log', 'sys:log:list', '1', 'log', '8'), ('39', '1', '数据字典', 'sys/dic', null, '1', 'dictionary', '4'), ('40', '39', '查看', null, 'sys:dic:list,sys:dic:info', '2', null, '0'), ('41', '39', '新增', null, 'sys:dic:save', '2', null, '0'), ('42', '39', '更新', null, 'sys:dic:update', '2', null, '0'), ('43', '39', '删除', null, 'sys:dic:delete', '2', null, '0');
COMMIT;

-- ----------------------------
--  Table structure for `sys_oss`
-- ----------------------------
DROP TABLE IF EXISTS `sys_oss`;
CREATE TABLE `sys_oss` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(200) DEFAULT NULL COMMENT 'URL地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件上传';

-- ----------------------------
--  Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
--  Records of `sys_role`
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES ('1', '项目经理', '222222', '1', null);
COMMIT;

-- ----------------------------
--  Table structure for `sys_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=156 DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

-- ----------------------------
--  Records of `sys_role_menu`
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu` VALUES ('129', '1', '2'), ('130', '1', '15'), ('131', '1', '16'), ('132', '1', '17'), ('133', '1', '18'), ('134', '1', '3'), ('135', '1', '19'), ('136', '1', '20'), ('137', '1', '21'), ('138', '1', '22'), ('139', '1', '4'), ('140', '1', '23'), ('141', '1', '24'), ('142', '1', '25'), ('143', '1', '26'), ('144', '1', '5'), ('145', '1', '6'), ('146', '1', '7'), ('147', '1', '8'), ('148', '1', '9'), ('149', '1', '10'), ('150', '1', '11'), ('151', '1', '12'), ('152', '1', '13'), ('153', '1', '14'), ('154', '1', '-666666'), ('155', '1', '1');
COMMIT;

-- ----------------------------
--  Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `salt` varchar(20) DEFAULT NULL COMMENT '盐',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='系统用户';

-- ----------------------------
--  Records of `sys_user`
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES ('1', 'admin', '9ec9750e709431dad22365cabc5c625482e574c74adaebba7dd02f1129e4ce1d', 'YzcmCZNvbXocrsz9dm8e', 'root@admin.io', '13612345678', '1', '1', '2016-11-11 11:11:11');
COMMIT;

-- ----------------------------
--  Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

-- ----------------------------
--  Records of `sys_user_role`
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` VALUES ('7', '2', '1');
COMMIT;

-- ----------------------------
--  Table structure for `sys_user_token`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_token`;
CREATE TABLE `sys_user_token` (
  `user_id` bigint(20) NOT NULL,
  `token` varchar(100) NOT NULL COMMENT 'token',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `token` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户Token';

-- ----------------------------
--  Records of `sys_user_token`
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_token` VALUES ('1', 'adcd63b13be6c53b1c61b2b7b54796cb', '2018-09-06 10:45:51', '2018-09-05 22:45:51'), ('2', '940ae95d6f34f8b465333d2d80fab7b2', '2018-06-27 20:06:18', '2018-06-27 08:06:18');
COMMIT;

-- ----------------------------
--  Table structure for `tb_user`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `mobile` varchar(20) NOT NULL COMMENT '手机号',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户';

-- ----------------------------
--  Records of `tb_user`
-- ----------------------------
BEGIN;
INSERT INTO `tb_user` VALUES ('1', 'mark', '13612345678', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', '2017-03-23 22:37:41');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
