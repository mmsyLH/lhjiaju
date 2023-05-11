/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : home_furnishing

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2023-05-11 10:24:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for furn
-- ----------------------------
DROP TABLE IF EXISTS `furn`;
CREATE TABLE `furn` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `maker` varchar(64) NOT NULL,
  `price` decimal(11,2) NOT NULL,
  `sales` int(10) unsigned NOT NULL,
  `stock` int(10) unsigned NOT NULL,
  `img_path` varchar(256) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of furn
-- ----------------------------
INSERT INTO `furn` VALUES ('1', '北欧风格小桌子', '熊猫家居', '180.00', '675', '85', 'assets/images/product-image/default.jpg');
INSERT INTO `furn` VALUES ('2', '简约风格小椅子', '熊猫家居', '180.00', '669', '785', 'assets/images/product-image/4.jpg');
INSERT INTO `furn` VALUES ('3', '典雅风格小台灯', '蚂蚁家居', '180.00', '669', '75', 'assets/images/product-image/14.jpg');
INSERT INTO `furn` VALUES ('4', '温馨风格盆景架', '蚂蚁家居', '180.00', '666', '78', 'assets/images/product-image/16.jpg');
INSERT INTO `furn` VALUES ('7', '卡通风玩具猫3', '罗汉家具', '99.99', '555', '78', 'assets/images/product-image/default.jpg');
INSERT INTO `furn` VALUES ('8', '卡通风玩具猫4', '罗汉家具', '99.99', '555', '78', 'assets/images/product-image/default.jpg');
INSERT INTO `furn` VALUES ('11', 'Name', 'èèå®¶å±', '60.00', '100', '80', 'assets/images/product-image/default.jpg');
INSERT INTO `furn` VALUES ('12', '卡通风玩具猫4', '罗汉家具', '99.99', '555', '78', 'assets/images/product-image/default.jpg');
INSERT INTO `furn` VALUES ('13', '我不是玩具熊', '罗汉家具', '99.99', '555', '7', 'assets/images/product-image/default.jpg');
INSERT INTO `furn` VALUES ('14', '我是玩具鱼', '蚂蚁家居', '60.00', '100', '80', 'assets/images/product-image/default.jpg');
INSERT INTO `furn` VALUES ('15', '卡通风玩具猫6', '罗汉家具', '99.99', '555', '7', 'assets/images/product-image/default.jpg');
INSERT INTO `furn` VALUES ('16', '玩具熊2号', '蚂蚁家居', '60.00', '100', '80', 'assets/images/product-image/default.jpg');
INSERT INTO `furn` VALUES ('17', '玩具熊', '蚂蚁家居', '60.00', '100', '80', 'assets/images/product-image/2023/4/18//a7077f41-f1ce-4cdc-8d36-3195dc109f62_header.jpg');
INSERT INTO `furn` VALUES ('18', '玩具熊', '蚂蚁家居', '60.00', '100', '80', 'assets/images/product-image/2023/4/18//a7077f41-f1ce-4cdc-8d36-3195dc109f62_header.jpg');
INSERT INTO `furn` VALUES ('19', '乱码熊', '乱码商家', '60.00', '100', '80', 'assets/images/product-image/2023/4/18//a7077f41-f1ce-4cdc-8d36-3195dc109f62_header.jpg');
INSERT INTO `furn` VALUES ('20', '玩具鱼', '罗汉家居', '60.00', '100', '80', 'assets/images/product-image/default.jpg');
INSERT INTO `furn` VALUES ('21', '玩具鱼2', '蚂蚁家居', '60.00', '100', '80', 'assets/images/product-image/default.jpg');
INSERT INTO `furn` VALUES ('22', '玩具鱼3', '罗汉家居', '60.00', '100', '80', 'assets/images/product-image/default.jpg');
INSERT INTO `furn` VALUES ('23', '玩具鱼4', '罗汉家居', '60.00', '1000', '80', 'assets/images/product-image/default.jpg');
INSERT INTO `furn` VALUES ('24', 'Name2', '蚂蚁家居', '60.00', '100', '80', 'assets/images/product-image/default.jpg');
INSERT INTO `furn` VALUES ('25', 'Name3', '蚂蚁家居', '60.00', '100', '80', 'assets/images/product-image/default.jpg');
INSERT INTO `furn` VALUES ('27', 'Name4', '蚂蚁家居', '60.00', '100', '80', 'assets/images/product-image/default.jpg');
INSERT INTO `furn` VALUES ('29', 'Name5', '蚂蚁家居', '60.00', '100', '80', 'assets/images/product-image/default.jpg');
INSERT INTO `furn` VALUES ('30', 'Name6', '蚂蚁家居', '60.00', '100', '80', 'assets/images/product-image/default.jpg');

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `email` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES ('1', 'admin', '21232f297a57a5a743894a0e4a801fc3', '1072344372@qq.com');
INSERT INTO `member` VALUES ('2', 'milan123', '49e34051a5bb3df733080908649b9ad1', 'milan123@qq.com');
INSERT INTO `member` VALUES ('3', 'jack', '4ff9fc6e4e5d5f590c4f2134a8cc96d1', 'jack@qq.com');
INSERT INTO `member` VALUES ('4', 'lh', '8ecc6960abbf70f7a5a70d9bfaae585c', 'lh@qq.com');
INSERT INTO `member` VALUES ('10', 'jack1', 'c38ccc9693a077376bf81fc8db2c1689', 'jack@qq.com');
INSERT INTO `member` VALUES ('12', 'lh11', '7ca8dd5c1d65818e996061a3e5c93f7b', '1072344372@qq.com');
INSERT INTO `member` VALUES ('13', 'lh13', 'ad72b0f027deb1e6c0dc74be47dbf60e', '1072344372@qq.com');
INSERT INTO `member` VALUES ('14', 'luohan', '25f9e794323b453885f5181f1b624d0b', '1072344372@qq.com');
INSERT INTO `member` VALUES ('15', 'luohan300', '25f9e794323b453885f5181f1b624d0b', '1072344372@qq.com');
INSERT INTO `member` VALUES ('16', 'luohan2', 'e10adc3949ba59abbe56e057f20f883e', '1072344372@qq.com');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` varchar(64) NOT NULL,
  `create_time` datetime NOT NULL,
  `price` decimal(11,2) NOT NULL,
  `status` tinyint(4) NOT NULL,
  `member_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES ('16809529058101', '2023-04-08 19:21:46', '600.00', '0', '1');
INSERT INTO `order` VALUES ('168095688774816', '2023-04-08 20:28:08', '540.00', '0', '16');
INSERT INTO `order` VALUES ('168095692051916', '2023-04-08 20:28:41', '0.00', '0', '16');
INSERT INTO `order` VALUES ('168097356423716', '2023-04-09 01:06:04', '360.00', '0', '16');
INSERT INTO `order` VALUES ('168105063341416', '2023-04-09 22:30:33', '540.00', '0', '16');
INSERT INTO `order` VALUES ('168105070960216', '2023-04-09 22:31:50', '180.00', '0', '16');

-- ----------------------------
-- Table structure for order_item
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `price` decimal(11,2) NOT NULL,
  `count` int(11) NOT NULL,
  `total_price` decimal(11,2) NOT NULL,
  `order_id` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_item
-- ----------------------------
INSERT INTO `order_item` VALUES ('4', '北欧风格小桌子', '200.00', '3', '600.00', '16809529058101');
INSERT INTO `order_item` VALUES ('5', '北欧风格小桌子', '180.00', '1', '180.00', '168095688774816');
INSERT INTO `order_item` VALUES ('6', '简约风格小椅子', '180.00', '1', '180.00', '168095688774816');
INSERT INTO `order_item` VALUES ('7', '典雅风格小台灯', '180.00', '1', '180.00', '168095688774816');
INSERT INTO `order_item` VALUES ('8', '简约风格小椅子', '180.00', '1', '180.00', '168097356423716');
INSERT INTO `order_item` VALUES ('9', '典雅风格小台灯', '180.00', '1', '180.00', '168097356423716');
INSERT INTO `order_item` VALUES ('10', '北欧风格小桌子', '180.00', '1', '180.00', '168105063341416');
INSERT INTO `order_item` VALUES ('11', '简约风格小椅子', '180.00', '1', '180.00', '168105063341416');
INSERT INTO `order_item` VALUES ('12', '典雅风格小台灯', '180.00', '1', '180.00', '168105063341416');
INSERT INTO `order_item` VALUES ('13', '北欧风格小桌子', '180.00', '1', '180.00', '168105070960216');
