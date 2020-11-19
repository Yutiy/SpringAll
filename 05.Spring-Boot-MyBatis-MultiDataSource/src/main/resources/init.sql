/*
Navicat MySQL Data Transfer
Source Server         : localhost_3306
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : test
Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001
Date: 2018-05-02 14:32:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `sno` varchar(3) NOT NULL PRIMARY KEY,
  `name` varchar(10) NOT NULL,
  `sex` char(2) NOT NULL,
  `datasource` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('001', 'KangKang', 'M', 'mysql1');
INSERT INTO `student` VALUES ('002', 'Mike', 'M', 'mysql1');