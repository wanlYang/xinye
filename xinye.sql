/*
 Navicat Premium Data Transfer

 Source Server         : YANG
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : xinye

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 05/12/2019 17:37:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for about
-- ----------------------------
DROP TABLE IF EXISTS `about`;
CREATE TABLE `about`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `about_cantent` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `activity_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `activity_photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `activity_time` datetime(0) NOT NULL,
  `activity_content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `activity_state` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员名称',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '你会后悔',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'TOPSHOW', 'YzRjYTQyMzhhMGI5MjM4MjBkY2M1MDlhNmY3NTg0OWI=');

-- ----------------------------
-- Table structure for admin_nav
-- ----------------------------
DROP TABLE IF EXISTS `admin_nav`;
CREATE TABLE `admin_nav`  (
  `nav_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '导航栏ID',
  `nav_parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父级ID',
  `nav_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '导航栏标题',
  `nav_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '导航路径',
  `nav_index` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '索引',
  `is_target` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否打开新标签',
  `nav_icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `nav_spread` tinyint(4) NULL DEFAULT 0 COMMENT '是否展开',
  `nav_role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限ID',
  PRIMARY KEY (`nav_id`) USING BTREE,
  INDEX `nav_parent_id`(`nav_parent_id`) USING BTREE,
  INDEX `nav_role`(`nav_role`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 48 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin_nav
-- ----------------------------
INSERT INTO `admin_nav` VALUES (1, 0, '首页内容', '0', 'contentManagement', NULL, '&#xe63c;', 0, '1,3');
INSERT INTO `admin_nav` VALUES (2, 1, '新晔导师', 'danceteacher/list', 'articleList', NULL, 'icon-text', 0, '1,3');
INSERT INTO `admin_nav` VALUES (3, 1, '培训项目', 'dancetraining/list', 'otherPage', NULL, '&#xe630;', 0, '1,3');
INSERT INTO `admin_nav` VALUES (6, 0, '关于我们', '0', 'about us', NULL, 'icon-icon10', 0, '1,3');
INSERT INTO `admin_nav` VALUES (7, 6, '公司介绍', 'about/page', 'about', NULL, '&#xe612;', 0, '1,3');
INSERT INTO `admin_nav` VALUES (27, 1, '视频管理', 'video/list', 'video', NULL, 'icon-mokuai', 0, '1,3');
INSERT INTO `admin_nav` VALUES (28, 1, '课程管理', 'cources/list', 'cources', NULL, 'layui-icon layui-icon-app', 0, '1,3');
INSERT INTO `admin_nav` VALUES (29, 6, '留言管理', 'suggestion/list', 'suggestion', NULL, 'icon-icon10', 0, '1,3');
INSERT INTO `admin_nav` VALUES (32, 6, '新晔资讯', 'topshow/information', 'topshowInformation', NULL, '&#xe63c;', 0, '1,3');
INSERT INTO `admin_nav` VALUES (36, 1, '管理员管理', 'admin/list', 'adminList', NULL, 'icon-mokuai', 0, '1,3');
INSERT INTO `admin_nav` VALUES (37, 1, '公共管理', '0', 'public', NULL, '&#xe63c;', 0, '1,3');
INSERT INTO `admin_nav` VALUES (38, 37, 'BANNER管理', 'banner/list', 'banner', NULL, 'icon-mokuai', 0, '1,3');
INSERT INTO `admin_nav` VALUES (39, 37, '店面图管理', 'shop/list', 'storeFront', NULL, 'icon-mokuai', 0, '1,3');
INSERT INTO `admin_nav` VALUES (40, 6, '企业荣誉', 'corporate/honor', 'corporateHonor', NULL, 'icon-mokuai', 0, '1,3');
INSERT INTO `admin_nav` VALUES (41, 6, '新晔风采', '0', 'newElegance', NULL, '&#xe63c;', 0, '1,3');
INSERT INTO `admin_nav` VALUES (42, 41, '公司活动', 'company/event', 'companyEvent', NULL, 'icon-mokuai', 0, '1,3');
INSERT INTO `admin_nav` VALUES (43, 41, '学员风采', 'cadet/style', 'cadetStyle', NULL, 'icon-mokuai', 0, '1,3');
INSERT INTO `admin_nav` VALUES (44, 1, '视频集锦', '0', 'videoHighlights', NULL, 'icon-mokuai', 0, '1,3');
INSERT INTO `admin_nav` VALUES (45, 44, '外教视频', 'foreign/teachers/video', 'foreignTeachersVideo', NULL, 'icon-mokuai', 0, '1,3');
INSERT INTO `admin_nav` VALUES (46, 44, '教师视频', 'teacher/video', 'teacherVideo', NULL, 'icon-mokuai', 0, '1,3');
INSERT INTO `admin_nav` VALUES (47, 44, '学员视频', 'student/video', 'studentVideo', NULL, 'icon-mokuai', 0, '1,3');

-- ----------------------------
-- Table structure for banner
-- ----------------------------
DROP TABLE IF EXISTS `banner`;
CREATE TABLE `banner`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `banner_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `banner_mol` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `banner_pc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of banner
-- ----------------------------
INSERT INTO `banner` VALUES (2, '精品课程', '/admin/uploads/banner/e17c1aa168483acb56991b81ebda3f70.jpg', '/admin/uploads/banner/e17c1aa168483acb56991b81ebda3f70.jpg');

-- ----------------------------
-- Table structure for callme
-- ----------------------------
DROP TABLE IF EXISTS `callme`;
CREATE TABLE `callme`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `call` int(11) NOT NULL,
  `e-mail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `add` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `call_shop` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for certificate
-- ----------------------------
DROP TABLE IF EXISTS `certificate`;
CREATE TABLE `certificate`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `certificate_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `certificate_photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `certificate_time` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `class_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `class_time` datetime(0) NOT NULL,
  `class_photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for dance
-- ----------------------------
DROP TABLE IF EXISTS `dance`;
CREATE TABLE `dance`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dence_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `dance_photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `dance_describe` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'miaoshu',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for honor
-- ----------------------------
DROP TABLE IF EXISTS `honor`;
CREATE TABLE `honor`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `honor_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `honor_photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for idea
-- ----------------------------
DROP TABLE IF EXISTS `idea`;
CREATE TABLE `idea`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idea_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `idea_sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `idea_call` int(11) NOT NULL,
  `idea_centent` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `idea_state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `idea_time` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for news
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `news_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `news_photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `news_time` datetime(0) NOT NULL,
  `news_content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `news_person` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for shop
-- ----------------------------
DROP TABLE IF EXISTS `shop`;
CREATE TABLE `shop`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shop_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `shop_photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `shop_add` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `student_photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `student_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `teacher_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `teacher_dance` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `teacher_pcphoto` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `teacher_modphoto` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for video
-- ----------------------------
DROP TABLE IF EXISTS `video`;
CREATE TABLE `video`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `video_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `video_link` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `video_photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `teacher_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `video_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `persion` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
