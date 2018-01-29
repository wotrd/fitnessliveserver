CREATE DATABASE /*!32312 IF NOT EXISTS*/`fitnesslive.db` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `fitnesslive.db`;

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `uid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'UID',
  `account` varchar(200) NOT NULL COMMENT '账户',
  `name` varchar(200)  DEFAULT NULL COMMENT '姓名',
  `password` varchar(200)  DEFAULT NULL COMMENT '密码',
  `gender` varchar(20)  DEFAULT "男" COMMENT '性别',
  `nickname` varchar(200)  DEFAULT NULL COMMENT '昵称',
  `email` varchar(200)  DEFAULT NULL COMMENT '邮箱',
  `idcard` varchar(200)  DEFAULT NULL COMMENT '身份证',
  `phonenum` varchar(200)  DEFAULT NULL COMMENT '手机号',
  `role` bigint(20)  DEFAULT 0 COMMENT '角色',
  `amatar` varchar(200) DEFAULT NULL COMMENT '头像',
  `age` bigint(20) DEFAULT 0 COMMENT '年龄',
  `personalsign` VARCHAR(200) DEFAULT NULL COMMENT '个性签名',
  `islive` TINYINT DEFAULT 0 COMMENT '是否直播',
  `grade` bigint(200) DEFAULT 0 COMMENT '积分',
  `fansnum` bigint(200) DEFAULT 0 COMMENT '粉丝数',
  `attentionnum` bigint(200) DEFAULT 0 COMMENT '关注数',
  `livebigpic` VARCHAR(200) DEFAULT NULL COMMENT '直播大图',
  `createtime` DATETIME DEFAULT NULL COMMENT '注册时间',
  PRIMARY KEY (`uid`)
) ENGINE=MyISAM AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `fans`;

CREATE TABLE `fans` (
  `fs_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'FID',
  `fs_account` varchar(200) NOT NULL COMMENT '账户',
  `fs_nickname` varchar(200)  DEFAULT NULL COMMENT '昵称',
  `fs_phonenum` varchar(200)  DEFAULT NULL COMMENT '手机号',
  `fs_amatar` varchar(200) DEFAULT NULL COMMENT '头像',
  `uid` bigint(20) NOT NULL COMMENT 'userid',
  PRIMARY KEY (`fs_id`)
) ENGINE=MyISAM AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `attentions`;

CREATE TABLE `attentions` (
  `gz_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'FID',
  `gz_account` varchar(200) NOT NULL COMMENT '账户',
  `gz_nickname` varchar(200)  DEFAULT NULL COMMENT '昵称',
  `gz_phonenum` varchar(200)  DEFAULT NULL COMMENT '手机号',
  `gz_amatar` varchar(200) DEFAULT NULL COMMENT '头像',
  `uid` bigint(20) NOT NULL COMMENT 'userid',
  PRIMARY KEY (`gz_id`)
) ENGINE=MyISAM AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `livethemes`;

CREATE TABLE `livethemes` (
  `lt_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ltID',
  `lt_name` varchar(200)  COMMENT '直播风格',
  `lt_islive` TINYINT DEFAULT 0 COMMENT '是否在直播',
  `uid` bigint(20) NOT NULL COMMENT 'userid',
  PRIMARY KEY (`lt_id`)
) ENGINE=MyISAM AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `sysvideos`;
CREATE TABLE sysvideos
(
  uv_id           BIGINT AUTO_INCREMENT
  COMMENT 'videoID'
    PRIMARY KEY,
  uv_title        VARCHAR(200)       NULL
  COMMENT '视频标题',
  uv_videourl     VARCHAR(200)       NULL
  COMMENT '视频地址',
  uv_thumbnailurl VARCHAR(200)       NULL
  COMMENT '视频缩略图地址',
  uv_uploadtime   DATETIME           NULL
  COMMENT '视频上传时间',
  uv_type         INT DEFAULT '0'    NULL,
  uid             BIGINT DEFAULT '0' NOT NULL
  COMMENT 'userid'
)
  ENGINE = MyISAM
  CHARSET = utf8;


DROP TABLE IF EXISTS `sysmessage`;

CREATE TABLE `sysmessage` (
  `sm_id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'messageID',
  `sm_title` varchar(1000) DEFAULT NULL COMMENT '消息标题',
  `sm_content` varchar(200)  DEFAULT NULL COMMENT '消息内容',
  `sm_from` varchar(200)  DEFAULT NULL COMMENT '消息来源',
  `sm_to` VARCHAR(200) DEFAULT NULL COMMENT '消息所有者',
  `intent` BIGINT(20) NOT NULL COMMENT '意图',
  `isRead` BIGINT(20)  DEFAULT NULL COMMENT '是否已读',
  `owner` VARCHAR(30) DEFAULT NULL COMMENT '消息所有者',
  `time` DATETIME DEFAULT NULL COMMENT '推送时间',
  `result` VARCHAR(100) NOT NULL COMMENT '推送结果',
  `uid` BIGINT(20) COMMENT 'userid',
  PRIMARY KEY (`sm_id`)
) ENGINE=MyISAM AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;


insert  into `users`(`account`,`password`,`nickname`,`role`) values('admin','admin','admin',1);
insert  into `users`(`account`,`password`,`nickname`,`phonenum`) values('100000','123456','小灰灰','17862901468');