/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50726 (5.7.26)
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50726 (5.7.26)
 File Encoding         : 65001

 Date: 21/11/2023 18:15:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for changecode
-- ----------------------------
DROP TABLE IF EXISTS `changecode`;
CREATE TABLE `changecode`  (
  `code` int(11) NOT NULL,
  `mean` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`code`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of changecode
-- ----------------------------

-- ----------------------------
-- Table structure for changes
-- ----------------------------
DROP TABLE IF EXISTS `changes`;
CREATE TABLE `changes`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `StudentId` bigint(20) NOT NULL,
  `changeId` int(11) NOT NULL COMMENT '变更代码',
  `recTime` date NULL DEFAULT NULL COMMENT '记录时间',
  `description` varchar(255) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of changes
-- ----------------------------
INSERT INTO `changes` VALUES (1, 5, 1, '2023-11-13', '转专业');
INSERT INTO `changes` VALUES (5, 22040431, 43, '2023-11-13', 'hsujd');
INSERT INTO `changes` VALUES (6, 2205030316, 5, '2023-11-11', '保研4');
INSERT INTO `changes` VALUES (7, 2205030316, 5, '2023-11-11', '保研');

-- ----------------------------
-- Table structure for classcode
-- ----------------------------
DROP TABLE IF EXISTS `classcode`;
CREATE TABLE `classcode`  (
  `code` int(11) NOT NULL,
  `mean` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `department` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`code`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of classcode
-- ----------------------------

-- ----------------------------
-- Table structure for departmentcode
-- ----------------------------
DROP TABLE IF EXISTS `departmentcode`;
CREATE TABLE `departmentcode`  (
  `code` int(11) NOT NULL,
  `mean` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `manager` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`code`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of departmentcode
-- ----------------------------

-- ----------------------------
-- Table structure for punishment
-- ----------------------------
DROP TABLE IF EXISTS `punishment`;
CREATE TABLE `punishment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `StudentId` bigint(20) NOT NULL,
  `levels` int(11) NOT NULL COMMENT '级别代码',
  `recTime` date NULL DEFAULT NULL,
  `enable` tinyint(4) NOT NULL COMMENT '是否生效',
  `description` varchar(255) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of punishment
-- ----------------------------
INSERT INTO `punishment` VALUES (1, 2002040721, 1, '2023-11-16', 0, '警告xxx');
INSERT INTO `punishment` VALUES (2, 2205030389, 2, '2023-11-01', 1, 'xxx');
INSERT INTO `punishment` VALUES (3, 2205030316, 2, '2023-11-11', 1, '没有恋爱');

-- ----------------------------
-- Table structure for reward
-- ----------------------------
DROP TABLE IF EXISTS `reward`;
CREATE TABLE `reward`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `StudentId` bigint(20) NOT NULL,
  `levels` int(11) NOT NULL COMMENT '级别代码',
  `recTime` date NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of reward
-- ----------------------------
INSERT INTO `reward` VALUES (1, 2205030337, 4, '2023-08-16', 'xxx一等奖');
INSERT INTO `reward` VALUES (2, 2205030316, 3, '2023-11-02', 'xxxxx一等奖');
INSERT INTO `reward` VALUES (3, 2205030316, 2, '2023-11-11', 'xx特等奖');

-- ----------------------------
-- Table structure for rewardcode
-- ----------------------------
DROP TABLE IF EXISTS `rewardcode`;
CREATE TABLE `rewardcode`  (
  `code` int(11) NOT NULL,
  `mean` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`code`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rewardcode
-- ----------------------------

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `StudentId` bigint(13) NOT NULL COMMENT '学号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `classId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `department` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `birthday` date NULL DEFAULT NULL,
  `nativePlace` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`StudentId`) USING BTREE,
  INDEX `my1`(`classId`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (2205030316, 'yyjccc', '男', '03', '计算机', '2023-11-21', '湖南');
INSERT INTO `student` VALUES (2205030337, 'qzy', '女', '03', '计算机学院', '2023-11-13', '湖南');
INSERT INTO `student` VALUES (2205030389, '小星星', '女', '02', '土木', '2023-11-07', '杭州');
INSERT INTO `student` VALUES (2103050605, '惠女市', '男', '01', '电气', '2023-10-18', '广州');
INSERT INTO `student` VALUES (2002040721, '智库', '男', '04', '人文', '2023-11-29', '成都');
INSERT INTO `student` VALUES (298324, '暗示', '女', '03', '建瓯', '2023-12-07', '哀');
INSERT INTO `student` VALUES (2205030378, '虎牙', '男', '08', '经研院', '2023-11-16', '北京');
INSERT INTO `student` VALUES (22312331, '十大', '女', '02', '阿萨德', NULL, '萨达');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'admin', 'admin');

SET FOREIGN_KEY_CHECKS = 1;
