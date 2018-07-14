/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : themis

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 14/07/2018 20:39:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for club_application
-- ----------------------------
DROP TABLE IF EXISTS `club_application`;
CREATE TABLE `club_application` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `activity_end` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `activity_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `activity_place` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `activity_start` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `activitypeople` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `apply_date` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `has_file` int(11) DEFAULT NULL,
  `introduce` varchar(1500) COLLATE utf8mb4_bin DEFAULT NULL,
  `is_fine` int(11) DEFAULT NULL,
  `lv` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `club_id` bigint(20) DEFAULT NULL,
  `reserve_money` double DEFAULT NULL,
  `self_money` double DEFAULT NULL,
  `real_reserve_money` double DEFAULT NULL,
  `real_self_money` double DEFAULT NULL,
  `refund_date` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `is_apply_refund` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6aaux2r0m23t7wty02srh1m71` (`club_id`),
  CONSTRAINT `FK6aaux2r0m23t7wty02srh1m71` FOREIGN KEY (`club_id`) REFERENCES `club_club` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of club_application
-- ----------------------------
BEGIN;
INSERT INTO `club_application` VALUES (37, '1532620800000', '1', '1', '1528905600000', '1', '2018年06月06日 11时19分09秒 Wed ', 0, '1', 0, 5, 0, 1, 3, 1, NULL, NULL, NULL, 0);
INSERT INTO `club_application` VALUES (38, '1532016000000', '2', '2', '1528992000000', '1', '2018年06月06日 16时47分11秒 Wed ', 0, 'f', 0, 100, -1, 1, 1, 1, NULL, NULL, NULL, 0);
INSERT INTO `club_application` VALUES (39, '1533052800000', '1', '1', '1533052800000', '1', '2018年07月11日 17时15分32秒 星期三 ', 0, '111', 1, 100, -1, 1, 0, 0, NULL, NULL, NULL, 0);
INSERT INTO `club_application` VALUES (40, '1534694400000', 'test1', '1', '1530892800000', '1', '2018年07月11日 17时23分28秒 星期三 ', 0, 'test', 0, 5, 2, 1, 200, 20, NULL, NULL, NULL, 1);
INSERT INTO `club_application` VALUES (41, '1533657600000', 'new', '123', '1530892800000', '123', '2018年07月14日 19时27分55秒 星期六 ', 0, '123', 0, 2, 1, 1, 100, 200, NULL, NULL, NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for club_approval
-- ----------------------------
DROP TABLE IF EXISTS `club_approval`;
CREATE TABLE `club_approval` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `approval_lv` int(11) DEFAULT NULL,
  `comment` varchar(1000) COLLATE utf8mb4_bin DEFAULT NULL,
  `result` int(11) DEFAULT NULL,
  `application_id` bigint(20) DEFAULT NULL,
  `auditor_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6etiqfyvqmflhnlologee70e1` (`application_id`),
  KEY `FKprb7anskv1p1igea4kp91ccd0` (`auditor_id`),
  CONSTRAINT `FK6etiqfyvqmflhnlologee70e1` FOREIGN KEY (`application_id`) REFERENCES `club_application` (`id`),
  CONSTRAINT `FKprb7anskv1p1igea4kp91ccd0` FOREIGN KEY (`auditor_id`) REFERENCES `common_user_info` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of club_approval
-- ----------------------------
BEGIN;
INSERT INTO `club_approval` VALUES (38, 2, '1', 1, 37, 2);
INSERT INTO `club_approval` VALUES (39, 3, '1', 1, 37, 12);
INSERT INTO `club_approval` VALUES (40, 4, '1', 1, 37, 13);
INSERT INTO `club_approval` VALUES (41, 2, '', 0, 39, 2);
INSERT INTO `club_approval` VALUES (42, 2, 'test', 1, 38, 2);
INSERT INTO `club_approval` VALUES (43, 3, '', 1, 38, 12);
INSERT INTO `club_approval` VALUES (44, 4, '', 0, 38, 13);
INSERT INTO `club_approval` VALUES (45, 2, '财务审批 评语test', 1, 40, 2);
INSERT INTO `club_approval` VALUES (46, 3, '', 1, 40, 12);
INSERT INTO `club_approval` VALUES (47, 4, '123', 1, 40, 13);
COMMIT;

-- ----------------------------
-- Table structure for club_club
-- ----------------------------
DROP TABLE IF EXISTS `club_club`;
CREATE TABLE `club_club` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `club_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `reserve_money` double DEFAULT NULL,
  `self_money` double DEFAULT NULL,
  `chief_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpwfyi0t2kpypp8mueo3biklj3` (`chief_id`),
  CONSTRAINT `FKpwfyi0t2kpypp8mueo3biklj3` FOREIGN KEY (`chief_id`) REFERENCES `common_user_info` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of club_club
-- ----------------------------
BEGIN;
INSERT INTO `club_club` VALUES (1, 'betahouse', 100, 12.2, 1);
COMMIT;

-- ----------------------------
-- Table structure for club_file
-- ----------------------------
DROP TABLE IF EXISTS `club_file`;
CREATE TABLE `club_file` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `file_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `origin_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `path` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `application_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8btefuuog0nqiqs8gnhodcfx7` (`application_id`),
  CONSTRAINT `FK8btefuuog0nqiqs8gnhodcfx7` FOREIGN KEY (`application_id`) REFERENCES `club_application` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for club_role
-- ----------------------------
DROP TABLE IF EXISTS `club_role`;
CREATE TABLE `club_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `lv` int(11) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_lb66rywc45prhtkqs2raqywi4` (`user_id`),
  CONSTRAINT `FK4pchrxrr4t2x2kkb0t46oojmn` FOREIGN KEY (`user_id`) REFERENCES `common_user_info` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of club_role
-- ----------------------------
BEGIN;
INSERT INTO `club_role` VALUES (1, 1, 1);
INSERT INTO `club_role` VALUES (2, 2, 2);
INSERT INTO `club_role` VALUES (3, 3, 12);
INSERT INTO `club_role` VALUES (4, 4, 13);
COMMIT;

-- ----------------------------
-- Table structure for common_inform
-- ----------------------------
DROP TABLE IF EXISTS `common_inform`;
CREATE TABLE `common_inform` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `title` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgx9x2to0svpsw3esutqlg4gtl` (`user_id`),
  CONSTRAINT `FKgx9x2to0svpsw3esutqlg4gtl` FOREIGN KEY (`user_id`) REFERENCES `common_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of common_inform
-- ----------------------------
BEGIN;
INSERT INTO `common_inform` VALUES (2, 'bbbbb', '2018-02-22 08:55:44', 'aaaa', 2, NULL);
INSERT INTO `common_inform` VALUES (4, '<p>ttt</p>', '2018-05-02 14:01:19', 'tet', 1, '2018-05-02 14:01:19');
INSERT INTO `common_inform` VALUES (5, '<p>faddd</p>', '2018-05-02 14:06:49', 'faf', 1, '2018-05-14 15:22:44');
INSERT INTO `common_inform` VALUES (6, '<p>degsgsdgsdddd</p>', '2018-05-12 16:31:34', '关于开展2017年杭州电子科技大学信息工程学院“与信仰对话”主题团日活动的通知关于开展2017年杭州电子科技大学信息工程学院“与信仰对话”主题团日活动的通知', 1, '2018-05-14 15:22:28');
INSERT INTO `common_inform` VALUES (7, '<div>&nbsp;各团支部：&nbsp;<br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;为引导广大青年学生学思践悟习近平总书记青年时期的成长故事，更加全面、历史地认识党的十八届六中全会确立的重大意义，以奋发向上、团结一心的精神状态迎接党的十九大顺利召开，<br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学院团委开展了“与信仰对<br>\n</div>', '2018-05-12 16:58:10', '总书记青年时期的成长故事，更加全面、历史', 1, '2018-05-12 16:58:10');
COMMIT;

-- ----------------------------
-- Table structure for common_user
-- ----------------------------
DROP TABLE IF EXISTS `common_user`;
CREATE TABLE `common_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `salt` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `username` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of common_user
-- ----------------------------
BEGIN;
INSERT INTO `common_user` VALUES (1, 'BCB41CCDC4363C6848A1D760F26C28A0', '590', '1');
INSERT INTO `common_user` VALUES (2, 'BCB41CCDC4363C6848A1D760F26C28A0', '590', '2');
INSERT INTO `common_user` VALUES (7, 'BCB41CCDC4363C6848A1D760F26C28A0', '590', '3');
INSERT INTO `common_user` VALUES (8, 'BCB41CCDC4363C6848A1D760F26C28A0', '590', '4');
INSERT INTO `common_user` VALUES (9, 'BCB41CCDC4363C6848A1D760F26C28A0', '590', '5');
INSERT INTO `common_user` VALUES (10, 'BCB41CCDC4363C6848A1D760F26C28A0', '590', '6');
COMMIT;

-- ----------------------------
-- Table structure for common_user_info
-- ----------------------------
DROP TABLE IF EXISTS `common_user_info`;
CREATE TABLE `common_user_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `sex` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `real_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrkdfdtdluswr6x5i6qlskco6q` (`user_id`),
  CONSTRAINT `FKrkdfdtdluswr6x5i6qlskco6q` FOREIGN KEY (`user_id`) REFERENCES `common_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of common_user_info
-- ----------------------------
BEGIN;
INSERT INTO `common_user_info` VALUES (1, '1@163.com', '男', 1, 'beta', NULL);
INSERT INTO `common_user_info` VALUES (2, '1@qq.com', '女', 2, '财务', NULL);
INSERT INTO `common_user_info` VALUES (12, '2@qq.com', '男', 7, '场地社联', NULL);
INSERT INTO `common_user_info` VALUES (13, '2@qq.com', '男', 8, '团委', NULL);
INSERT INTO `common_user_info` VALUES (14, '2@qq.com', '女', 9, ' stu1', NULL);
INSERT INTO `common_user_info` VALUES (15, '2@qq.com', '男', 10, 'stu2', NULL);
COMMIT;

-- ----------------------------
-- Table structure for core_authority_gourp
-- ----------------------------
DROP TABLE IF EXISTS `core_authority_gourp`;
CREATE TABLE `core_authority_gourp` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for core_authority_menu
-- ----------------------------
DROP TABLE IF EXISTS `core_authority_menu`;
CREATE TABLE `core_authority_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `component` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `icon` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `path` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkeko5i5m2j9qfmpool93h4lmt` (`parent_id`),
  CONSTRAINT `FKkeko5i5m2j9qfmpool93h4lmt` FOREIGN KEY (`parent_id`) REFERENCES `core_authority_menu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of core_authority_menu
-- ----------------------------
BEGIN;
INSERT INTO `core_authority_menu` VALUES (1, 'db', 'cogs', '德育分数据库', '/db', NULL);
INSERT INTO `core_authority_menu` VALUES (2, 'inform', 'file', '公告', '/inform', NULL);
INSERT INTO `core_authority_menu` VALUES (3, 'inform/send', 'send', '公告发送', 'send', 2);
INSERT INTO `core_authority_menu` VALUES (4, 'inform/mine', 'envelope-open-o', '我的公告', 'mine', 2);
INSERT INTO `core_authority_menu` VALUES (5, 'inform/manage', 'file-text-o', '公告管理', 'menage', 2);
INSERT INTO `core_authority_menu` VALUES (6, 'user', 'cogs', '用户管理', '/user', NULL);
INSERT INTO `core_authority_menu` VALUES (7, 'user/account', 'cogs', '账号管理', 'account', 6);
INSERT INTO `core_authority_menu` VALUES (8, 'user/common', 'cogs', '基础信息管理', 'common', 6);
INSERT INTO `core_authority_menu` VALUES (9, 'student', 'cogs', '德育分管理', '/common', NULL);
INSERT INTO `core_authority_menu` VALUES (10, 'student/self', 'cogs', '个人信息查看', 'self', 9);
INSERT INTO `core_authority_menu` VALUES (11, 'student/class', 'cogs', '班级信息管理', 'class', 9);
INSERT INTO `core_authority_menu` VALUES (12, 'student/major', 'cogs', '专业信息管理', 'major', 9);
INSERT INTO `core_authority_menu` VALUES (13, 'student/school', 'cogs', '全校信息管理', 'school', 9);
INSERT INTO `core_authority_menu` VALUES (14, 'db/stu', 'cogs', '学生基础管理', 'stu', 1);
INSERT INTO `core_authority_menu` VALUES (15, 'db/activity', 'cogs', '学生活动加分', 'activity', 1);
INSERT INTO `core_authority_menu` VALUES (16, 'db/honor', 'cogs', '学生荣誉加分', 'honor', 1);
INSERT INTO `core_authority_menu` VALUES (17, 'db/office', 'cogs', '学生任职加分', 'office', 1);
INSERT INTO `core_authority_menu` VALUES (18, 'db/practice', 'cogs', '学生实践加分', 'practice', 1);
INSERT INTO `core_authority_menu` VALUES (19, 'db/volunteer', 'cogs', '学生志愿加分', 'volunteer', 1);
INSERT INTO `core_authority_menu` VALUES (20, 'db/skill', 'cogs', '学生技能加分', 'skill', 1);
INSERT INTO `core_authority_menu` VALUES (21, 'db/reserve', 'cogs', '学生其他加分', 'reserve', 1);
INSERT INTO `core_authority_menu` VALUES (22, 'club', 'cogs', '社团管理', '/club', NULL);
INSERT INTO `core_authority_menu` VALUES (23, 'club/apply', 'cogs', '活动申请', 'apply', 22);
INSERT INTO `core_authority_menu` VALUES (24, 'club/check', 'cogs', '申请查看', 'check', 22);
INSERT INTO `core_authority_menu` VALUES (25, 'club/approve', 'cogs', '申请审核', 'approve', 22);
COMMIT;

-- ----------------------------
-- Table structure for core_authority_type
-- ----------------------------
DROP TABLE IF EXISTS `core_authority_type`;
CREATE TABLE `core_authority_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `gourp_id` bigint(20) DEFAULT NULL,
  `menu_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfpl4imlh5fcdcumy5gamhbpkt` (`gourp_id`),
  KEY `FK4x8ghnnqgb44oidl1u393523i` (`menu_id`),
  CONSTRAINT `FK4x8ghnnqgb44oidl1u393523i` FOREIGN KEY (`menu_id`) REFERENCES `core_authority_menu` (`id`),
  CONSTRAINT `FKfpl4imlh5fcdcumy5gamhbpkt` FOREIGN KEY (`gourp_id`) REFERENCES `core_authority_gourp` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of core_authority_type
-- ----------------------------
BEGIN;
INSERT INTO `core_authority_type` VALUES (1, '个人管理', NULL, 1);
INSERT INTO `core_authority_type` VALUES (2, '发送公告', NULL, 3);
INSERT INTO `core_authority_type` VALUES (3, '我的公告', NULL, 4);
INSERT INTO `core_authority_type` VALUES (4, '我的公告', NULL, 5);
INSERT INTO `core_authority_type` VALUES (5, '用户管理', NULL, 7);
INSERT INTO `core_authority_type` VALUES (6, '学生信息查看', NULL, 10);
INSERT INTO `core_authority_type` VALUES (7, '基础信息管理', NULL, 8);
INSERT INTO `core_authority_type` VALUES (8, '班级信息管理', NULL, 11);
INSERT INTO `core_authority_type` VALUES (9, '专业信息管理', NULL, 12);
INSERT INTO `core_authority_type` VALUES (10, '全校信息管理', NULL, 13);
INSERT INTO `core_authority_type` VALUES (11, '学生基础管理', NULL, 14);
INSERT INTO `core_authority_type` VALUES (12, '学生活动加分', NULL, 15);
INSERT INTO `core_authority_type` VALUES (13, '学生荣誉加分', NULL, 16);
INSERT INTO `core_authority_type` VALUES (14, '学生任职加分', NULL, 17);
INSERT INTO `core_authority_type` VALUES (15, '学生实践加分', NULL, 18);
INSERT INTO `core_authority_type` VALUES (16, '学生志愿加分', NULL, 19);
INSERT INTO `core_authority_type` VALUES (17, '学生技能加分', NULL, 20);
INSERT INTO `core_authority_type` VALUES (18, '学生其他加分', NULL, 21);
INSERT INTO `core_authority_type` VALUES (19, '活动申请', NULL, 23);
INSERT INTO `core_authority_type` VALUES (20, '申请查看', NULL, 24);
INSERT INTO `core_authority_type` VALUES (21, '申请审批', NULL, 25);
COMMIT;

-- ----------------------------
-- Table structure for core_authority_user
-- ----------------------------
DROP TABLE IF EXISTS `core_authority_user`;
CREATE TABLE `core_authority_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `authority_list` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKr1irb02nktof36jly4obc1sn2` (`user_id`),
  CONSTRAINT `FK4jiquya8o4buumi5aj6jw4tlx` FOREIGN KEY (`user_id`) REFERENCES `common_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of core_authority_user
-- ----------------------------
BEGIN;
INSERT INTO `core_authority_user` VALUES (1, '[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,22]', 1);
INSERT INTO `core_authority_user` VALUES (2, '[20,21]', 2);
INSERT INTO `core_authority_user` VALUES (5, '[6, 7,20,21]', 7);
INSERT INTO `core_authority_user` VALUES (6, '[6, 7,20,21]', 8);
INSERT INTO `core_authority_user` VALUES (7, '[6]', 9);
INSERT INTO `core_authority_user` VALUES (8, '[6]', 10);
COMMIT;

-- ----------------------------
-- Table structure for score_class
-- ----------------------------
DROP TABLE IF EXISTS `score_class`;
CREATE TABLE `score_class` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `class_num` int(11) DEFAULT NULL,
  `major_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKr2gwmp6jmvphas8mk9qsj2cuw` (`major_id`),
  CONSTRAINT `FKr2gwmp6jmvphas8mk9qsj2cuw` FOREIGN KEY (`major_id`) REFERENCES `score_major` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of score_class
-- ----------------------------
BEGIN;
INSERT INTO `score_class` VALUES (1, 1, 1);
INSERT INTO `score_class` VALUES (2, 2, 1);
COMMIT;

-- ----------------------------
-- Table structure for score_dormitory
-- ----------------------------
DROP TABLE IF EXISTS `score_dormitory`;
CREATE TABLE `score_dormitory` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `build` int(11) DEFAULT NULL,
  `floor` int(11) DEFAULT NULL,
  `room` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for score_duty_manager
-- ----------------------------
DROP TABLE IF EXISTS `score_duty_manager`;
CREATE TABLE `score_duty_manager` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `class_id` bigint(20) DEFAULT NULL,
  `major_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbigytnu44n98xmkbatwy3c0xt` (`class_id`),
  KEY `FK3978vowy8csq7679sv24mggd7` (`major_id`),
  KEY `FKqqryrrprmabvymbetpwyqkedv` (`user_id`),
  CONSTRAINT `FK3978vowy8csq7679sv24mggd7` FOREIGN KEY (`major_id`) REFERENCES `score_major` (`id`),
  CONSTRAINT `FKbigytnu44n98xmkbatwy3c0xt` FOREIGN KEY (`class_id`) REFERENCES `score_class` (`id`),
  CONSTRAINT `FKqqryrrprmabvymbetpwyqkedv` FOREIGN KEY (`user_id`) REFERENCES `common_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of score_duty_manager
-- ----------------------------
BEGIN;
INSERT INTO `score_duty_manager` VALUES (1, 1, 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for score_major
-- ----------------------------
DROP TABLE IF EXISTS `score_major`;
CREATE TABLE `score_major` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `major_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of score_major
-- ----------------------------
BEGIN;
INSERT INTO `score_major` VALUES (1, '计算机', 2018);
INSERT INTO `score_major` VALUES (3, '产品设计', 2017);
INSERT INTO `score_major` VALUES (4, '计算机科学', 2017);
COMMIT;

-- ----------------------------
-- Table structure for score_student_activity
-- ----------------------------
DROP TABLE IF EXISTS `score_student_activity`;
CREATE TABLE `score_student_activity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `activity_date` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `activity_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `common` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `term` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `base_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKm1uma57hh6e6clgw5mgcg7vvg` (`base_id`),
  CONSTRAINT `FKm1uma57hh6e6clgw5mgcg7vvg` FOREIGN KEY (`base_id`) REFERENCES `score_student_base_info` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of score_student_activity
-- ----------------------------
BEGIN;
INSERT INTO `score_student_activity` VALUES (2, '43191', '没有中间商赚差价', '没啥子', '201702', 1);
INSERT INTO `score_student_activity` VALUES (3, '2018/7/2', 'lala', '11111', '201702', 1);
COMMIT;

-- ----------------------------
-- Table structure for score_student_base_info
-- ----------------------------
DROP TABLE IF EXISTS `score_student_base_info`;
CREATE TABLE `score_student_base_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `entrance_time` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `photo` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `political` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `stu_id` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `class_id` bigint(20) DEFAULT NULL,
  `base_id` bigint(20) NOT NULL,
  `dormitory_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_a4649q1a3lpf8h5io59xcyq5w` (`stu_id`),
  UNIQUE KEY `UK_neh7nf35qjokb4y2juivuh2c6` (`base_id`),
  KEY `FKin3t78ndjxjeosvcccy3fme8p` (`class_id`),
  KEY `FKs5aeygpph2jbd34nppe7kedw5` (`dormitory_id`),
  CONSTRAINT `FKin3t78ndjxjeosvcccy3fme8p` FOREIGN KEY (`class_id`) REFERENCES `score_class` (`id`),
  CONSTRAINT `FKlrkcyi1cicpdu2mgl9d7x22si` FOREIGN KEY (`base_id`) REFERENCES `common_user_info` (`id`),
  CONSTRAINT `FKs5aeygpph2jbd34nppe7kedw5` FOREIGN KEY (`dormitory_id`) REFERENCES `score_dormitory` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of score_student_base_info
-- ----------------------------
BEGIN;
INSERT INTO `score_student_base_info` VALUES (1, '2018', 'xxx', '党员', '15905631', 1, 1, NULL);
INSERT INTO `score_student_base_info` VALUES (2, '2018', 'yyy', '团员', '15905632', 2, 2, NULL);
INSERT INTO `score_student_base_info` VALUES (3, '2018', NULL, '党员', '15905633', NULL, 12, NULL);
INSERT INTO `score_student_base_info` VALUES (4, '2012', NULL, '群众', '15905611', NULL, 13, NULL);
INSERT INTO `score_student_base_info` VALUES (5, '1', NULL, '1', '15905717', 1, 14, NULL);
INSERT INTO `score_student_base_info` VALUES (6, '11', NULL, '1', '15905777', 1, 15, NULL);
COMMIT;

-- ----------------------------
-- Table structure for score_student_honor
-- ----------------------------
DROP TABLE IF EXISTS `score_student_honor`;
CREATE TABLE `score_student_honor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `common` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `honor_lv` int(11) DEFAULT NULL,
  `honor_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `honor_score` double DEFAULT NULL,
  `term` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `base_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKar5d68pl8emhgpaipc7uxf5c7` (`base_id`),
  CONSTRAINT `FKar5d68pl8emhgpaipc7uxf5c7` FOREIGN KEY (`base_id`) REFERENCES `score_student_base_info` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of score_student_honor
-- ----------------------------
BEGIN;
INSERT INTO `score_student_honor` VALUES (1, '真的', 7, '喊666奖', 20, '201702', 1);
COMMIT;

-- ----------------------------
-- Table structure for score_student_office
-- ----------------------------
DROP TABLE IF EXISTS `score_student_office`;
CREATE TABLE `score_student_office` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `common` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `end_date` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `office_lv` int(11) DEFAULT NULL,
  `office_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `result` int(11) DEFAULT NULL,
  `start_date` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `term` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `base_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKg4urimok38ud7i9lupsg51a76` (`base_id`),
  CONSTRAINT `FKg4urimok38ud7i9lupsg51a76` FOREIGN KEY (`base_id`) REFERENCES `score_student_base_info` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of score_student_office
-- ----------------------------
BEGIN;
INSERT INTO `score_student_office` VALUES (1, '', '2017', 1, '学生会主席', 0, '2016', '201702', 1);
INSERT INTO `score_student_office` VALUES (2, '', '2017', 1, '团委副书记', 1, '2016', '201702', 1);
COMMIT;

-- ----------------------------
-- Table structure for score_student_practice
-- ----------------------------
DROP TABLE IF EXISTS `score_student_practice`;
CREATE TABLE `score_student_practice` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `common` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `practice_date` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `practice_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `result` int(11) DEFAULT NULL,
  `term` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `base_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4ldomdxdwter66faa36o20abj` (`base_id`),
  CONSTRAINT `FK4ldomdxdwter66faa36o20abj` FOREIGN KEY (`base_id`) REFERENCES `score_student_base_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for score_student_reserve
-- ----------------------------
DROP TABLE IF EXISTS `score_student_reserve`;
CREATE TABLE `score_student_reserve` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `common` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `course` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `score` double DEFAULT NULL,
  `term` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `base_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKji4749xykatd0jwivucha55q3` (`base_id`),
  CONSTRAINT `FKji4749xykatd0jwivucha55q3` FOREIGN KEY (`base_id`) REFERENCES `score_student_base_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for score_student_skill
-- ----------------------------
DROP TABLE IF EXISTS `score_student_skill`;
CREATE TABLE `score_student_skill` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `common` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `skill_lv` int(11) DEFAULT NULL,
  `skill_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `skill_score` double DEFAULT NULL,
  `term` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `base_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKk7qs5a5t7i7v1i7tyrmeg2ie8` (`base_id`),
  CONSTRAINT `FKk7qs5a5t7i7v1i7tyrmeg2ie8` FOREIGN KEY (`base_id`) REFERENCES `score_student_base_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for score_student_volunteer
-- ----------------------------
DROP TABLE IF EXISTS `score_student_volunteer`;
CREATE TABLE `score_student_volunteer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `common` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `term` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `volunteer_date` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `volunteer_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `volunteer_time` double DEFAULT NULL,
  `base_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKd36jmf9585ye03w6fhpfskss` (`base_id`),
  CONSTRAINT `FKd36jmf9585ye03w6fhpfskss` FOREIGN KEY (`base_id`) REFERENCES `score_student_base_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

SET FOREIGN_KEY_CHECKS = 1;
