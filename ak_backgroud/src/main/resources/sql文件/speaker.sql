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

 Date: 16/08/2019 17:58:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for speaker
-- ----------------------------
DROP TABLE IF EXISTS `speaker`;
CREATE TABLE `speaker`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `speaker_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '嘉宾姓名',
  `speaker_introduce` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '嘉宾介绍',
  `photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '照片地址',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '演讲题目',
  `is_delete` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标记',
  `agenda_id` int(11) NULL DEFAULT NULL COMMENT '议程id',
  `meeting_id` int(11) NULL DEFAULT NULL COMMENT '会议id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of speaker
-- ----------------------------
INSERT INTO `speaker` VALUES (3, '何俊', '王牌支持人', 'D:\\ak\\20190816092353Penguins.jpg', '没有题目', '0', 7, NULL);
INSERT INTO `speaker` VALUES (4, '何俊', '王牌支持人', 'D:\\ak\\20190816092409Hydrangeas.jpg', '没有题目', '0', 8, NULL);
INSERT INTO `speaker` VALUES (6, '恶趣味群无', '顶顶顶顶', 'D:\\ak\\20190816134853Lighthouse.jpg', '顶顶顶顶', '0', 10, NULL);
INSERT INTO `speaker` VALUES (7, '想问下大', '大大王', 'D:\\ak\\20190816140001Hydrangeas.jpg', '的群阿瓦达', '0', 11, NULL);
INSERT INTO `speaker` VALUES (8, '想问下大先湿我心', '大大王', 'D:\\ak\\20190816140032Chrysanthemum.jpg', '的群阿瓦达', '0', 12, NULL);
INSERT INTO `speaker` VALUES (9, '车位服务', '短文 我的钱', 'D:\\ak\\20190816140307Hydrangeas.jpg', '大富翁让我', '0', 13, NULL);

SET FOREIGN_KEY_CHECKS = 1;
