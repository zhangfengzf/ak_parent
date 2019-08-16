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

 Date: 16/08/2019 17:57:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for anchor
-- ----------------------------
DROP TABLE IF EXISTS `anchor`;
CREATE TABLE `anchor`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `anchor_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主持人姓名\r\n',
  `anchor_introduce` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支持人介绍',
  `is_delete` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标记',
  `meeting_id` int(11) NULL DEFAULT NULL COMMENT '会议id\r\n',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of anchor
-- ----------------------------
INSERT INTO `anchor` VALUES (6, 'hejun', '1111', '0', NULL);
INSERT INTO `anchor` VALUES (7, '嗯嗯嗯', '恩恩', '0', 42);
INSERT INTO `anchor` VALUES (8, '多对多', '断点 ', '1', 32);
INSERT INTO `anchor` VALUES (9, '多对多断点', '断点 多对多', '1', 32);
INSERT INTO `anchor` VALUES (10, '断点', '断点', '1', 42);
INSERT INTO `anchor` VALUES (11, '多对多', '断点', '1', 42);
INSERT INTO `anchor` VALUES (12, '多对多', '断点', '0', 42);
INSERT INTO `anchor` VALUES (13, '华少', '金牌支持人', '0', 43);
INSERT INTO `anchor` VALUES (14, '何炅', '王牌主持人', '0', 43);
INSERT INTO `anchor` VALUES (15, '瓦尔码字', '王二短时', '0', 44);
INSERT INTO `anchor` VALUES (16, '顶顶顶顶', '顶顶顶顶', '0', 44);
INSERT INTO `anchor` VALUES (17, '惊喜价', '到前端无群', '1', 32);
INSERT INTO `anchor` VALUES (18, '同4同步v', '的确', '0', 32);
INSERT INTO `anchor` VALUES (19, '同4同步v', '的确', '0', 32);
INSERT INTO `anchor` VALUES (20, '同4同步v', '的确', '1', 32);

SET FOREIGN_KEY_CHECKS = 1;
