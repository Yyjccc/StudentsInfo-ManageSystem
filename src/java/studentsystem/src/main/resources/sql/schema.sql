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
