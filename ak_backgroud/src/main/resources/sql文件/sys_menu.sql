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

 Date: 16/08/2019 17:58:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `url` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '路径',
  `islogin` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '添加会议', '/meeting/addMeeting', '1');
INSERT INTO `sys_menu` VALUES (2, '查询主会议', '/meeting/queryMainMeeting', '1');
INSERT INTO `sys_menu` VALUES (3, '编辑会议', '/meeting/updateMeeting', '1');
INSERT INTO `sys_menu` VALUES (4, '删除会议', '/meeting/deleteMeeting*', '1');
INSERT INTO `sys_menu` VALUES (5, '查询会议', '/meeting/queryById*', '0');
INSERT INTO `sys_menu` VALUES (6, '会议列表', '/meeting/fuzzyQueryMeetingByPage', '1');
INSERT INTO `sys_menu` VALUES (7, '开启会议', '/meeting/updateMeetingState*', '1');
INSERT INTO `sys_menu` VALUES (8, '停止会议', 'meeting/closeMeeting*', '1');
INSERT INTO `sys_menu` VALUES (9, '查询添加人', 'meeting/queryMeetingUserByCurrentUser', '1');
INSERT INTO `sys_menu` VALUES (10, '用户管理', '/yyq/**', '1');
INSERT INTO `sys_menu` VALUES (11, '个人管理', '/user/**', '1');
INSERT INTO `sys_menu` VALUES (12, '添加主持人', '/agenda/addAnchor', '1');
INSERT INTO `sys_menu` VALUES (13, '删除主持人', '/agenda/deleteAnchor*', '1');
INSERT INTO `sys_menu` VALUES (14, '查询主持人', '/agenda/queryAnchor*', '0');
INSERT INTO `sys_menu` VALUES (15, '添加会议议程', '/agenda/addAgenda', '1');
INSERT INTO `sys_menu` VALUES (16, '查询会议议程', '/agenda/queryAgenda*', '0');
INSERT INTO `sys_menu` VALUES (17, '删除会议议程', '/agenda/deleteAgenda*', '1');
INSERT INTO `sys_menu` VALUES (18, '上传文件', '/file/**', '1');

SET FOREIGN_KEY_CHECKS = 1;
