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

 Date: 16/08/2019 17:58:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `user_realname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户姓名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `telphone` bigint(11) NOT NULL COMMENT '电话号码',
  `department` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所属单位',
  `user_scope` int(11) NOT NULL COMMENT '用户范围   1.内部用户，2 外部用户',
  `user_type` int(11) NOT NULL COMMENT '用户类型	1.长期用户，2.单次用户',
  `user_state` int(11) NOT NULL COMMENT '用户状态	1.启动		2.停用',
  `isdelete` int(11) NOT NULL DEFAULT 0 COMMENT '删除标记	0.未删除	1.已删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '管理员', '$2a$10$ISCYQ3zFJKrKKd9DawMXWuRhIZZ.5s1VAMkJvtPkpputlJPsW6Snq', 18282097683, '成都数译科技有限公司', 1, 1, 1, 0, '2019-08-15 10:03:11');
INSERT INTO `sys_user` VALUES (26, 'hejun', '何俊', '$2a$10$DSxeCju6EP2i4JvLq.6XFOHez91fAAPE3j65nsYxTJEojUwLiseTy', 18682044444, '成都数译科技', 1, 1, 2, 0, '2019-08-15 11:40:59');
INSERT INTO `sys_user` VALUES (27, 'z00864', '张三', '$2a$10$c4wEkVQQaVK/K7Na2zBSPuPDVlv2lHqNWbiRNp8MOfRFzUI.3mZoe', 15208440524, '数译科技有限公司', 1, 1, 2, 0, '2019-08-15 17:03:48');
INSERT INTO `sys_user` VALUES (29, 'z00866', '李四', '$2a$10$jOT.NpUcx4cQph9ATg6Mzej66ccZoi0tJWR01hCgUrMiufRKkjZ0a', 15208440525, '数译科技', 1, 1, 2, 0, '2019-08-15 17:22:01');
INSERT INTO `sys_user` VALUES (30, 'z0001', '张锋', '$2a$10$TgwEN8S7bQylnDEpTRd0r.c3PBnzW0nco5PgVWh5Pjo9oESbb1NwS', 13568724568, '语言桥科技', 1, 1, 1, 0, '2019-08-16 09:19:15');
INSERT INTO `sys_user` VALUES (31, 'A0000', '李四', '$2a$10$wZD3rR5l4TD1vXHWvHHCdugyGEi3xMHyLVCccDoHt3IYWSeK3a4H6', 13568729933, '成都数译科技', 1, 1, 1, 0, '2019-08-16 13:46:30');
INSERT INTO `sys_user` VALUES (32, 'Q0001', '向超', '$2a$10$DWOmcwjhmw6aSbAPXEC/ruOeTB4RE8lpGvbPxAAIUd.bMnxVbKceO', 18681111683, '成都数译', 1, 1, 1, 0, '2019-08-16 14:15:26');
INSERT INTO `sys_user` VALUES (33, 'A00011', '大萨达', '$2a$10$PAG8swaQE6wpaHMawevWJeZsRC2auHiyyhpoegeTgZBeSMUs1DX7a', 18682297683, '数译可以', 1, 1, 1, 0, '2019-08-16 14:17:19');
INSERT INTO `sys_user` VALUES (34, 'dd000', '打击大家觉得', '$2a$10$gPn7hUJbJ4MetvG3FvTVRuJgQ6ik4hgP/tqDX0Dvz9qUhL0vf9YvO', 18682097683, '顶顶顶顶', 1, 1, 1, 0, '2019-08-16 14:22:26');
INSERT INTO `sys_user` VALUES (35, 'dddd', '李四', '$2a$10$6Dhg143C.e6ZSCrCFL3EoO24S2ayElZS3M4bMbNtYk53hFlV4S6VO', 18328466467, '李四', 1, 1, 1, 0, '2019-08-16 14:37:33');

SET FOREIGN_KEY_CHECKS = 1;
