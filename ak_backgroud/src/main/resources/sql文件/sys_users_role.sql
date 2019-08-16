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

 Date: 16/08/2019 17:58:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_users_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_users_role`;
CREATE TABLE `sys_users_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `role_id` int(11) NOT NULL DEFAULT 2 COMMENT '角色',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_users_role
-- ----------------------------
INSERT INTO `sys_users_role` VALUES (21, 'admin', 1);
INSERT INTO `sys_users_role` VALUES (22, 'H11050', 2);
INSERT INTO `sys_users_role` VALUES (23, 'H110555550', 2);
INSERT INTO `sys_users_role` VALUES (24, 'hejun', 2);
INSERT INTO `sys_users_role` VALUES (25, 'z00864', 2);
INSERT INTO `sys_users_role` VALUES (26, 'z00866', 3);
INSERT INTO `sys_users_role` VALUES (27, 'z0001', 2);
INSERT INTO `sys_users_role` VALUES (28, 'A0000', 2);
INSERT INTO `sys_users_role` VALUES (29, 'Q0001', 2);
INSERT INTO `sys_users_role` VALUES (30, 'A00011', 2);
INSERT INTO `sys_users_role` VALUES (31, 'dd000', 2);
INSERT INTO `sys_users_role` VALUES (32, 'HJ520LYF', 2);

SET FOREIGN_KEY_CHECKS = 1;
