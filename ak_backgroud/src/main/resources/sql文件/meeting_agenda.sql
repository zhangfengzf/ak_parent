/*
 Navicat Premium Data Transfer

 Source Server         : new_ak
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : ak_backgroud

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 16/08/2019 17:58:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for meeting_agenda
-- ----------------------------
DROP TABLE IF EXISTS `meeting_agenda`;
CREATE TABLE `meeting_agenda`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `agenda_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会议类型',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  `is_delete` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标记',
  `meeting_id` int(11) NULL DEFAULT NULL COMMENT '会议id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of meeting_agenda
-- ----------------------------
INSERT INTO `meeting_agenda` VALUES (7, '嘉宾发言', '2019-08-16 09:23:51', '2019-08-16 09:23:53', '0', 43);
INSERT INTO `meeting_agenda` VALUES (8, '茶歇', '2019-08-16 09:23:51', '2019-08-16 09:23:53', '0', 43);
INSERT INTO `meeting_agenda` VALUES (10, '嘉宾发言', '2019-08-16 13:48:36', '2019-08-16 13:48:37', '0', 44);
INSERT INTO `meeting_agenda` VALUES (11, '嘉宾发言', '2019-08-16 13:59:57', '2019-08-29 00:00:00', '0', 32);
INSERT INTO `meeting_agenda` VALUES (12, '嘉宾发言', '2019-08-16 13:59:57', '2019-08-29 00:00:00', '0', 32);
INSERT INTO `meeting_agenda` VALUES (13, '嘉宾发言', '2019-08-16 14:02:54', '2019-08-23 00:00:00', '0', 32);

SET FOREIGN_KEY_CHECKS = 1;
