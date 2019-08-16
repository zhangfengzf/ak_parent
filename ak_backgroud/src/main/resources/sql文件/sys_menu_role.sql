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

 Date: 16/08/2019 17:58:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_menu_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu_role`;
CREATE TABLE `sys_menu_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_id` int(11) NOT NULL COMMENT '菜单id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 63 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu_role
-- ----------------------------
INSERT INTO `sys_menu_role` VALUES (1, 1, 1);
INSERT INTO `sys_menu_role` VALUES (2, 1, 2);
INSERT INTO `sys_menu_role` VALUES (3, 2, 1);
INSERT INTO `sys_menu_role` VALUES (4, 2, 2);
INSERT INTO `sys_menu_role` VALUES (5, 3, 1);
INSERT INTO `sys_menu_role` VALUES (6, 3, 2);
INSERT INTO `sys_menu_role` VALUES (7, 4, 1);
INSERT INTO `sys_menu_role` VALUES (8, 4, 2);
INSERT INTO `sys_menu_role` VALUES (9, 5, 1);
INSERT INTO `sys_menu_role` VALUES (10, 5, 2);
INSERT INTO `sys_menu_role` VALUES (11, 5, 3);
INSERT INTO `sys_menu_role` VALUES (12, 6, 1);
INSERT INTO `sys_menu_role` VALUES (13, 6, 2);
INSERT INTO `sys_menu_role` VALUES (14, 6, 3);
INSERT INTO `sys_menu_role` VALUES (15, 7, 1);
INSERT INTO `sys_menu_role` VALUES (16, 7, 2);
INSERT INTO `sys_menu_role` VALUES (17, 8, 1);
INSERT INTO `sys_menu_role` VALUES (18, 8, 2);
INSERT INTO `sys_menu_role` VALUES (19, 9, 1);
INSERT INTO `sys_menu_role` VALUES (20, 9, 2);
INSERT INTO `sys_menu_role` VALUES (21, 9, 3);
INSERT INTO `sys_menu_role` VALUES (22, 10, 1);
INSERT INTO `sys_menu_role` VALUES (23, 11, 1);
INSERT INTO `sys_menu_role` VALUES (24, 11, 2);
INSERT INTO `sys_menu_role` VALUES (25, 11, 3);
INSERT INTO `sys_menu_role` VALUES (26, 12, 1);
INSERT INTO `sys_menu_role` VALUES (27, 12, 2);
INSERT INTO `sys_menu_role` VALUES (28, 13, 1);
INSERT INTO `sys_menu_role` VALUES (29, 13, 2);
INSERT INTO `sys_menu_role` VALUES (30, 14, 1);
INSERT INTO `sys_menu_role` VALUES (31, 14, 2);
INSERT INTO `sys_menu_role` VALUES (32, 14, 3);
INSERT INTO `sys_menu_role` VALUES (33, 15, 1);
INSERT INTO `sys_menu_role` VALUES (34, 15, 2);
INSERT INTO `sys_menu_role` VALUES (35, 16, 1);
INSERT INTO `sys_menu_role` VALUES (36, 16, 2);
INSERT INTO `sys_menu_role` VALUES (37, 16, 3);
INSERT INTO `sys_menu_role` VALUES (38, 17, 1);
INSERT INTO `sys_menu_role` VALUES (39, 17, 2);
INSERT INTO `sys_menu_role` VALUES (40, 18, 1);
INSERT INTO `sys_menu_role` VALUES (41, 18, 2);

SET FOREIGN_KEY_CHECKS = 1;
