/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50540
Source Host           : localhost:3306
Source Database       : yang

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2018-05-06 19:59:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for order_seed_t
-- ----------------------------
DROP TABLE IF EXISTS `order_seed_t`;
CREATE TABLE `order_seed_t` (
  `seed_id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `seed_number` int(11) DEFAULT NULL COMMENT '种子号',
  `seed_date_str` varchar(255) DEFAULT NULL COMMENT '年月日 形如\r\n\r\n20180124',
  PRIMARY KEY (`seed_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
