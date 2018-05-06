/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50540
Source Host           : localhost:3306
Source Database       : yang

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2018-05-06 19:58:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for logis_order
-- ----------------------------
DROP TABLE IF EXISTS `logis_order`;
CREATE TABLE `logis_order` (
  `order_id` varchar(30) NOT NULL COMMENT '订单id',
  `create_time` datetime DEFAULT NULL COMMENT '下单时间',
  `submit_user_id` int(11) DEFAULT NULL COMMENT '提交人id',
  `order_status` varchar(110) DEFAULT NULL COMMENT '订单状态',
  `order_amount` float(255,0) DEFAULT NULL COMMENT '订单金额',
  `order_depict` varchar(300) NOT NULL COMMENT '订单信息',
  `order_num` int(11) DEFAULT NULL COMMENT '提交数量',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
