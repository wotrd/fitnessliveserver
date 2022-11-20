use scenePlatform;
/*
 Navicat Premium Data Transfer

 Source Server         : aliyun
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : ailijie.top:3306
 Source Schema         : animalsales

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 19/10/2019 17:04:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for appraise
-- ----------------------------
DROP TABLE IF EXISTS info;
CREATE TABLE `appraise` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `b_name` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '商品名字',
  `reason` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '商品评价',
  `create_time` datetime DEFAULT NULL COMMENT '评价时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='评价表';

-- ----------------------------
-- Records of appraise
-- ----------------------------
BEGIN;
INSERT INTO info VALUES (1, '1', '1', '2007-01-01 00:00:00');
INSERT INTO info VALUES (2, '2', '2', '2019-10-15 23:10:15');
INSERT INTO info VALUES (3, NULL, 'ss', '2019-10-16 22:22:24');
INSERT INTO info VALUES (4, NULL, 'ddd', '2019-10-16 22:29:56');
INSERT INTO info VALUES (5, '测试', 'sss', '2019-10-16 22:30:38');
INSERT INTO info VALUES (6, '测试', 'ddd', '2019-10-16 22:34:11');
INSERT INTO info VALUES (7, '测试', 'abc', '2019-10-16 22:34:20');
INSERT INTO info VALUES (8, '小熊', 'hhh', '2019-10-16 22:34:52');
COMMIT;

-- ----------------------------
-- Table structure for businesses
-- ----------------------------
DROP TABLE IF EXISTS project;
CREATE TABLE `businesses` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `b_name` varchar(64) DEFAULT NULL COMMENT '商品名字',
  `type` varchar(32) DEFAULT '0' COMMENT '商品类型',
  `price` decimal(9,2) NOT NULL COMMENT '商品单价',
  `avatar` varchar(128) DEFAULT NULL COMMENT '商品图片',
  `seller_id` int(11) DEFAULT NULL COMMENT '买家ID',
  `seller_name` varchar(32) DEFAULT NULL COMMENT '卖家名字',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='商品表';

-- ----------------------------
-- Records of businesses
-- ----------------------------
BEGIN;
INSERT INTO project VALUES (1, '小熊', '卡通', 100.00, 'https://blog.ailijie.top/upload/2019/6/WechatIMG93-3631413726214a198d1ffbce54b0866f.jpg', 2, '小李');
INSERT INTO project VALUES (4, '测试', '测试', 13.00, 'https://blog.ailijie.top/upload/2019/6/WechatIMG93-3631413726214a198d1ffbce54b0866f.jpg', 1, '小张');
COMMIT;

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `b_name` varchar(64) DEFAULT NULL COMMENT '商品名字',
  `b_count` int(2) DEFAULT NULL COMMENT '商品数量',
  `b_price` decimal(9,2) DEFAULT NULL COMMENT '商品单价',
  `total_price` decimal(9,2) DEFAULT NULL COMMENT '商品订单总价',
  `buyer_id` bigint(11) DEFAULT '0' COMMENT '买家ID',
  `sell_id` bigint(11) DEFAULT '0' COMMENT '卖家ID',
  `buyer_name` varchar(32) DEFAULT NULL COMMENT '买家名字',
  `sell_name` varchar(32) DEFAULT NULL COMMENT '卖家名字',
  `create_time` datetime DEFAULT NULL COMMENT '订单创建时间',
  `status` int(11) DEFAULT '2' COMMENT '1已完成 2未完成 3已取消',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------
BEGIN;
INSERT INTO `orders` VALUES (1, '测试', 2, 30.00, 60.00, NULL, NULL, '小张', '小李', '2019-10-15 19:46:28', NULL);
INSERT INTO `orders` VALUES (2, NULL, 4, NULL, NULL, 35, NULL, NULL, NULL, '2019-10-15 20:42:30', 3);
INSERT INTO `orders` VALUES (3, '测试', 2, 13.00, 26.00, 35, NULL, NULL, NULL, '2019-10-15 20:43:43', 2);
INSERT INTO `orders` VALUES (4, '测试', 2, 13.00, 26.00, 35, NULL, '17862901741', NULL, '2019-10-15 20:45:20', 1);
INSERT INTO `orders` VALUES (5, '测试', 3, 13.00, 39.00, 35, 1, '17862901741', '小张', '2019-10-15 20:47:32', 3);
INSERT INTO `orders` VALUES (6, '小熊', 9, 100.00, 900.00, 35, 2, '17862901741', '小李', '2019-10-15 20:47:58', 1);
INSERT INTO `orders` VALUES (7, '测试', 2, 13.00, 26.00, 35, 1, '17862901741', '小张', '2019-10-15 20:59:17', 2);
INSERT INTO `orders` VALUES (8, '小熊', 2, 100.00, 200.00, 35, 2, '17862901741', '小李', '2019-10-15 21:22:40', 1);
INSERT INTO `orders` VALUES (9, '小熊', 5, 100.00, 500.00, 35, 2, '17862901741', '小李', '2019-10-15 21:53:18', 1);
INSERT INTO `orders` VALUES (10, '小熊', 2, 100.00, 200.00, 35, 2, '17862901741', '小李', '2019-10-15 21:58:52', 1);
COMMIT;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `account` varchar(64) NOT NULL COMMENT '账户',
  `name` varchar(64) DEFAULT NULL COMMENT '姓名',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `gender` int(1) DEFAULT '0' COMMENT '性别（1男 0女）',
  `nick_name` varchar(64) DEFAULT NULL COMMENT '昵称',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `idcard` varchar(32) DEFAULT NULL COMMENT '身份证',
  `mobile_num` varchar(12) DEFAULT NULL COMMENT '手机号',
  `role` int(2) DEFAULT '0' COMMENT '角色（1管理员 0客户）',
  `amatar` varchar(100) DEFAULT NULL COMMENT '头像',
  `remark` varchar(200) DEFAULT '' COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '注册时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
BEGIN;
INSERT INTO `users` VALUES (2, '178629014', '小灰灰', '1234567', 0, '小灰灰', '178629010@163.com', '371324199501216810', '178629014', 1, 'https://blog.ailijie.top/upload/2019/6/WechatIMG93-3631413726214a198d1ffbce54b0866f.jpg', '我是最棒的！', '2018-01-14 09:01:03');
INSERT INTO `users` VALUES (4, '1786480884', 'F5D9CE214497C3BD9ADEBA30A5288CE8', '123456', 0, 'sir', NULL, NULL, '1786480884', 0, 'https://blog.ailijie.top/upload/2019/6/WechatIMG93-3631413726214a198d1ffbce54b0866f.jpg', '', '2017-12-12 22:22:53');
INSERT INTO `users` VALUES (5, '17862901470', NULL, '123456', 0, '测试', '47273828@qq.com', '12345', '17862901470', 0, 'https://blog.ailijie.top/upload/2019/6/WechatIMG93-3631413726214a198d1ffbce54b0866f.jpg', '', '2017-12-23 21:58:41');
INSERT INTO `users` VALUES (12, 'admin', 'admin', 'admin', 0, 'admin', 'admin@wotrd.com', '371324199501216813', '17862901467', 1, 'https://blog.ailijie.top/upload/2019/6/WechatIMG93-3631413726214a198d1ffbce54b0866f.jpg', '', '2018-01-12 00:19:19');
INSERT INTO `users` VALUES (34, '17862901425', NULL, '', NULL, 'tom', NULL, NULL, '17862901425', 0, 'https://blog.ailijie.top/upload/2019/6/WechatIMG93-3631413726214a198d1ffbce54b0866f.jpg', NULL, '2019-10-07 14:55:31');
INSERT INTO `users` VALUES (35, '17862901741', NULL, '666666', NULL, 'tom', NULL, NULL, '17862901741', 0, 'https://blog.ailijie.top/upload/2019/6/WechatIMG93-3631413726214a198d1ffbce54b0866f.jpg', NULL, '2019-10-07 14:57:41');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
