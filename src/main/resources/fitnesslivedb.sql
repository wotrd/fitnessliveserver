CREATE DATABASE /*!32312 IF NOT EXISTS*/`animaldb` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `animaldb`;

DROP TABLE IF EXISTS `users`;
-- auto-generated definition
create table users
(
    uid          bigint auto_increment
        comment 'UID'
        primary key,
    account      varchar(200)            not null
        comment '账户',
    name         varchar(200)            null
        comment '姓名',
    password     varchar(200)            null
        comment '密码',
    gender       varchar(20) default '男' null
        comment '性别',
    nickname     varchar(200)            null
        comment '昵称',
    email        varchar(200)            null
        comment '邮箱',
    idcard       varchar(200)            null
        comment '身份证',
    phonenum     varchar(200)            null
        comment '手机号',
    role         bigint default '0'      null
        comment '角色',
    amatar       varchar(200)            null
        comment '头像',
    borndata     date                    null,
    personalsign varchar(200) default '' null
        comment '个性签名',
    islive       tinyint default '0'     null
        comment '是否直播',
    grade        bigint(200) default '0' null
        comment '积分',
    fansnum      bigint(200) default '0' null
        comment '粉丝数',
    attentionnum bigint(200) default '0' null
        comment '关注数',
    livebigpic   varchar(200)            null
        comment '直播大图',
    createtime   datetime                null
        comment '注册时间'
)
    engine = InnoDB
    charset = utf8;
DROP TABLE IF EXISTS `fans`;


DROP TABLE IF EXISTS `attentions`;

-- auto-generated definition
CREATE TABLE attentions
(
    gz_id       BIGINT AUTO_INCREMENT
        COMMENT 'FID'
        PRIMARY KEY,
    gz_account  VARCHAR(200) NOT NULL
        COMMENT '账户',
    gz_nickname VARCHAR(200) NULL
        COMMENT '昵称',
    gz_phonenum VARCHAR(200) NULL
        COMMENT '手机号',
    gz_amatar   VARCHAR(200) NULL
        COMMENT '头像',
    uid         BIGINT       NOT NULL
        COMMENT 'userid',
    account     VARCHAR(200) NULL
)
    ENGINE = InnoDB
    CHARSET = utf8;

DROP TABLE IF EXISTS tickets;

-- auto-generated definition
CREATE TABLE livethemes
(
    lt_id     BIGINT AUTO_INCREMENT
        COMMENT 'ltID'
        PRIMARY KEY,
    lt_name   VARCHAR(200)        NULL
        COMMENT '直播风格',
    lt_islive TINYINT DEFAULT '0' NULL
        COMMENT '是否在直播',
    uid       BIGINT              NOT NULL
        COMMENT 'userid'
)
    ENGINE = InnoDB
    CHARSET = utf8;

DROP TABLE IF EXISTS animalinfo;
-- auto-generated definition
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
DROP TABLE IF EXISTS suggestioninfo;
-- auto-generated definition
CREATE TABLE sysmessage
(
    sm_id      BIGINT AUTO_INCREMENT
        COMMENT 'messageID'
        PRIMARY KEY,
    sm_title   VARCHAR(1000) NULL
        COMMENT '消息标题',
    sm_content VARCHAR(200)  NULL
        COMMENT '消息内容',
    sm_from    VARCHAR(200)  NULL
        COMMENT '消息来源',
    sm_to      VARCHAR(200)  NULL
        COMMENT '消息所有者',
    intent     BIGINT        NOT NULL
        COMMENT '意图',
    isRead     BIGINT        NULL
        COMMENT '是否已读',
    owner      VARCHAR(30)   NULL
        COMMENT '消息所有者',
    time       DATETIME      NULL
        COMMENT '推送时间',
    result     VARCHAR(100)  NOT NULL
        COMMENT '推送结果',
    uid        BIGINT        NOT NULL
        COMMENT 'userid'
)
    ENGINE = MyISAM
    CHARSET = utf8;
