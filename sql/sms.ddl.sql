/*
Navicat MySQL Data Transfer

Source Server         : 192.168.239.128
Source Server Version : 50628
Source Host           : 192.168.239.128:3306
Source Database       : sms

Target Server Type    : MYSQL
Target Server Version : 50628
File Encoding         : 65001

Date: 2019-08-09 21:40:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sco_course
-- ----------------------------
DROP TABLE IF EXISTS `sco_course`;
CREATE TABLE `sco_course` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `create_user_no` varchar(255) DEFAULT NULL,
  `update_time` datetime NOT NULL,
  `update_user_no` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `no` varchar(255) DEFAULT NULL,
  `teacher_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_hdgpir6ylhdn5qxjpaewyj4re` (`teacher_id`),
  CONSTRAINT `FK_hdgpir6ylhdn5qxjpaewyj4re` FOREIGN KEY (`teacher_id`) REFERENCES `sys_teacher` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for sco_student_course
-- ----------------------------
DROP TABLE IF EXISTS `sco_student_course`;
CREATE TABLE `sco_student_course` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `create_user_no` varchar(255) DEFAULT NULL,
  `update_time` datetime NOT NULL,
  `update_user_no` varchar(255) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `course_id` bigint(20) NOT NULL,
  `student_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_pca7b27kbsvbx16iesacbnbw3` (`course_id`),
  KEY `FK_cg18njsujswo2sfb5xio8do0c` (`student_id`),
  CONSTRAINT `FK_cg18njsujswo2sfb5xio8do0c` FOREIGN KEY (`student_id`) REFERENCES `sys_student` (`id`),
  CONSTRAINT `FK_pca7b27kbsvbx16iesacbnbw3` FOREIGN KEY (`course_id`) REFERENCES `sco_course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for sys_student
-- ----------------------------
DROP TABLE IF EXISTS `sys_student`;
CREATE TABLE `sys_student` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `create_user_no` varchar(255) DEFAULT NULL,
  `update_time` datetime NOT NULL,
  `update_user_no` varchar(255) DEFAULT NULL,
  `college` varchar(255) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `dormitory` varchar(255) DEFAULT NULL,
  `grade` int(11) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_alhjgu46iqv12g1m5a3y1rpjk` (`user_id`),
  CONSTRAINT `FK_alhjgu46iqv12g1m5a3y1rpjk` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for sys_teacher
-- ----------------------------
DROP TABLE IF EXISTS `sys_teacher`;
CREATE TABLE `sys_teacher` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `create_user_no` varchar(255) DEFAULT NULL,
  `update_time` datetime NOT NULL,
  `update_user_no` varchar(255) DEFAULT NULL,
  `office_address` varchar(255) DEFAULT NULL,
  `office_phone` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_6rurdkp3l5ojgork08fxb97xh` (`user_id`),
  CONSTRAINT `FK_6rurdkp3l5ojgork08fxb97xh` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `create_user_no` varchar(255) DEFAULT NULL,
  `update_time` datetime NOT NULL,
  `update_user_no` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `home_address` varchar(255) DEFAULT NULL,
  `idNo` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `role` tinyint(4) NOT NULL,
  `sex` tinyint(4) DEFAULT NULL,
  `username` varchar(100) NOT NULL unique,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
