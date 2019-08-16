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

 Date: 16/08/2019 17:58:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_meeting
-- ----------------------------
DROP TABLE IF EXISTS `sys_meeting`;
CREATE TABLE `sys_meeting`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `meeting_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会议类型    1.主会议  2 ,独立会议 3.分论坛',
  `meeting_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会议名称',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  `meeting_scale` int(255) NULL DEFAULT NULL COMMENT '会议规模',
  `meeting_bill` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会议海报',
  `meeting_context` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '会议简介',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `isdelete` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标记',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会议地点',
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '状态  1.未开始  2  已启动  3 已结束',
  `main_id` int(11) NOT NULL COMMENT '主会议id   0:无主会议     ',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_meeting
-- ----------------------------
INSERT INTO `sys_meeting` VALUES (30, '1', '语言桥年中大会', '2019-08-15 11:43:30', '2019-08-15 11:43:31', 50, 'D:\\ak\\20190815114355Desert.jpg', '年中', 'hejun', '1', '泰达时代中心', '1', 0);
INSERT INTO `sys_meeting` VALUES (31, '2', '语言桥年中大会编辑嘻嘻嘻', '2019-08-15 11:43:30', '2019-08-15 11:43:31', 501, 'D:\\ak\\20190816114435Hydrangeas.jpg', '编辑', 'hejun', '0', '泰达时代中心111', '1', 0);
INSERT INTO `sys_meeting` VALUES (32, '1', '语言桥年中大会信息', '2019-08-15 11:43:30', '2019-08-15 11:43:31', 300, 'D:\\ak\\20190816132118Lighthouse.jpg', '年中额鹅鹅鹅', 'hejun', '0', '泰达时代中心', '1', 0);
INSERT INTO `sys_meeting` VALUES (33, '1', '语言桥年中大会', '2019-08-15 11:43:30', '2019-08-15 11:43:31', 50, 'D:\\ak\\20190815114633Penguins.jpg', '年中额鹅鹅鹅', 'hejun', '1', '泰达时代中心', '1', 0);
INSERT INTO `sys_meeting` VALUES (34, '1', '语言桥年中大会', '2019-08-15 11:43:30', '2019-08-15 11:43:31', 50, 'D:\\ak\\20190815114706Lighthouse.jpg', '年中额鹅鹅鹅', 'hejun', '1', '泰达时代中心', '1', 0);
INSERT INTO `sys_meeting` VALUES (35, '1', '语言桥年中大会', '2019-08-15 11:43:30', '2019-08-15 11:43:31', 50, 'D:\\ak\\20190815133337Jellyfish.jpg', '年中额鹅鹅鹅', 'hejun', '1', '泰达时代中心', '1', 0);
INSERT INTO `sys_meeting` VALUES (36, '1', '语言桥年中大会', '2019-08-15 11:43:30', '2019-08-15 11:43:31', 50, 'D:\\ak\\20190815133837Lighthouse.jpg', '年中额鹅鹅鹅', 'hejun', '0', '泰达时代中心', '1', 0);
INSERT INTO `sys_meeting` VALUES (37, '3', '测试会议', '2019-08-15 13:53:05', '2019-08-15 13:53:07', 50, 'D:\\ak\\20190815135320Jellyfish.jpg', '222323321', 'hejun', '0', '1111111', '1', 30);
INSERT INTO `sys_meeting` VALUES (38, '3', '测试会议', '2019-08-15 13:53:05', '2019-08-15 13:53:07', 50, 'D:\\ak\\20190815135431Koala.jpg', '222323321', 'hejun', '0', '1111111', '1', 30);
INSERT INTO `sys_meeting` VALUES (39, '3', '测试会议', '2019-08-15 13:53:05', '2019-08-15 13:53:07', 50, 'D:\\ak\\20190815135455Lighthouse.jpg', '222323321', 'hejun', '0', '1111111', '1', 30);
INSERT INTO `sys_meeting` VALUES (40, '3', '测试会议', '2019-08-15 13:53:05', '2019-08-15 13:53:07', 50, 'D:\\ak\\20190815135648Koala.jpg', '222323321', 'hejun', '0', '1111111', '1', 30);
INSERT INTO `sys_meeting` VALUES (41, '3', '测试会议', '2019-08-15 13:53:05', '2019-08-15 13:53:07', 50, 'D:\\ak\\20190815135719Hydrangeas.jpg', '222323321', 'hejun', '0', '1111111', '1', 30);
INSERT INTO `sys_meeting` VALUES (42, '1', '顶顶顶顶', '2019-08-15 17:05:30', '2019-08-15 17:05:32', 50, 'D:\\ak\\20190815170538Jellyfish.jpg', '多对多', 'hejun', '1', '顶顶顶顶', '1', 0);
INSERT INTO `sys_meeting` VALUES (43, '1', '语言桥金牌大会', '2019-08-16 09:22:02', '2019-08-16 00:00:00', 50, 'D:\\ak\\20190816092231Desert.jpg', '金牌主持人', 'admin', '0', '泰达时代中心', '1', 0);
INSERT INTO `sys_meeting` VALUES (44, '1', '张家界，语言桥年中大会', '2019-08-16 13:47:50', '2019-08-16 13:47:52', 500, 'D:\\ak\\20190816134803Desert.jpg', '11111', 'A0000', '0', '张家界', '1', 0);

SET FOREIGN_KEY_CHECKS = 1;
