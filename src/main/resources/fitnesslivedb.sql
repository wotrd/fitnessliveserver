

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

-- auto-generated definition
INSERT INTO `fitnesslive.db`.fans (fs_id, fs_account, fs_nickname, fs_phonenum, fs_amatar, uid, account) VALUES (69, '100039', '执念', null, 'http://thirdqq.qlogo.cn/qqapp/1106140497/09A6B15F49530FC17C40B23B50624A88/40', 39, '100039');
INSERT INTO `fitnesslive.db`.fans (fs_id, fs_account, fs_nickname, fs_phonenum, fs_amatar, uid, account) VALUES (57, '100000', '小灰灰', '17862901468', 'http://192.168.191.1:8080/fitnesslive/img/amatar/100000460b8686-abf7-4c7e-b297-f5a83cf362e4.jpg', 28, '17862901383');
INSERT INTO `fitnesslive.db`.fans (fs_id, fs_account, fs_nickname, fs_phonenum, fs_amatar, uid, account) VALUES (58, '100000', '小灰灰', '17862901468', 'http://192.168.191.1:8080/fitnesslive/img/amatar/100000460b8686-abf7-4c7e-b297-f5a83cf362e4.jpg', 5, '100005');
INSERT INTO `fitnesslive.db`.fans (fs_id, fs_account, fs_nickname, fs_phonenum, fs_amatar, uid, account) VALUES (55, '100003', '执念', '17862901489', 'http://192.168.191.1:8080/fitnesslive/img/amatar/1000035b7052e3-d3b1-4b2a-a2a8-d35164ac1222.jpg', 5, '100005');
INSERT INTO `fitnesslive.db`.fans (fs_id, fs_account, fs_nickname, fs_phonenum, fs_amatar, uid, account) VALUES (52, '100004', 'sir', null, 'http://192.168.191.1:8080/fitnesslive/img/amatar/100004cee39c9c-99a2-4334-8458-7509a0d359e6.jpg', 5, '100005');
INSERT INTO `fitnesslive.db`.fans (fs_id, fs_account, fs_nickname, fs_phonenum, fs_amatar, uid, account) VALUES (53, '100004', 'sir', null, 'http://192.168.191.1:8080/fitnesslive/img/amatar/100004cee39c9c-99a2-4334-8458-7509a0d359e6.jpg', 3, '100003');
INSERT INTO `fitnesslive.db`.fans (fs_id, fs_account, fs_nickname, fs_phonenum, fs_amatar, uid, account) VALUES (54, '100003', '执念', '17862901489', 'http://192.168.191.1:8080/fitnesslive/img/amatar/1000035b7052e3-d3b1-4b2a-a2a8-d35164ac1222.jpg', 3, '100003');
INSERT INTO `fitnesslive.db`.fans (fs_id, fs_account, fs_nickname, fs_phonenum, fs_amatar, uid, account) VALUES (62, '100000', '小灰灰', '17862901468', 'http://192.168.191.1:8080/fitnesslive/img/amatar/100000460b8686-abf7-4c7e-b297-f5a83cf362e4.jpg', 34, 'test2');
INSERT INTO `fitnesslive.db`.fans (fs_id, fs_account, fs_nickname, fs_phonenum, fs_amatar, uid, account) VALUES (64, '100036', '执念', null, 'http://thirdqq.qlogo.cn/qqapp/1106140497/09A6B15F49530FC17C40B23B50624A88/40', 29, 'hanyi');
INSERT INTO `fitnesslive.db`.fans (fs_id, fs_account, fs_nickname, fs_phonenum, fs_amatar, uid, account) VALUES (65, '100036', '执念', null, 'http://thirdqq.qlogo.cn/qqapp/1106140497/09A6B15F49530FC17C40B23B50624A88/40', 27, 'diaolizhao');
INSERT INTO `fitnesslive.db`.fans (fs_id, fs_account, fs_nickname, fs_phonenum, fs_amatar, uid, account) VALUES (66, '100036', '执念', null, 'http://thirdqq.qlogo.cn/qqapp/1106140497/09A6B15F49530FC17C40B23B50624A88/40', 26, 'machunhua');
INSERT INTO `fitnesslive.db`.fans (fs_id, fs_account, fs_nickname, fs_phonenum, fs_amatar, uid, account) VALUES (67, '100036', '执念', null, 'http://thirdqq.qlogo.cn/qqapp/1106140497/09A6B15F49530FC17C40B23B50624A88/40', 25, 'dexiang');
INSERT INTO `fitnesslive.db`.fans (fs_id, fs_account, fs_nickname, fs_phonenum, fs_amatar, uid, account) VALUES (68, '100036', '执念', null, 'http://thirdqq.qlogo.cn/qqapp/1106140497/09A6B15F49530FC17C40B23B50624A88/40', 2, '100000');
INSERT INTO `fitnesslive.db`.fans (fs_id, fs_account, fs_nickname, fs_phonenum, fs_amatar, uid, account) VALUES (71, '17862901383', '小奈', '17862901383', 'http://192.168.191.1:8080/fitnesslive/img/amatar/283a6658d4-e44d-4adb-b8dd-b79f4db46d54.jpg', 2, '100000');
INSERT INTO `fitnesslive.db`.fans (fs_id, fs_account, fs_nickname, fs_phonenum, fs_amatar, uid, account) VALUES (72, '17862901383', '小奈', '17862901383', 'http://192.168.191.1:8080/fitnesslive/img/amatar/283a6658d4-e44d-4adb-b8dd-b79f4db46d54.jpg', 40, '100040');
INSERT INTO `fitnesslive.db`.fans (fs_id, fs_account, fs_nickname, fs_phonenum, fs_amatar, uid, account) VALUES (73, '17862901368', 'test11', '17862901368', 'http://192.168.191.1:8080/fitnesslive/img/amatar/33c7fe0601-e23f-447e-abd5-046ab7075d32.jpg', 2, '100000');
INSERT INTO `fitnesslive.db`.fans (fs_id, fs_account, fs_nickname, fs_phonenum, fs_amatar, uid, account) VALUES (74, '17862901268', '小赵', '17862901268', 'http://192.168.191.1:8080/fitnesslive/img/amatar/178629012688550dd83-a5fd-411e-a51b-423e4eae3631.jpg', 2, '100000');
INSERT INTO `fitnesslive.db`.fans (fs_id, fs_account, fs_nickname, fs_phonenum, fs_amatar, uid, account) VALUES (155, '100040', '执念', null, 'http://thirdqq.qlogo.cn/qqapp/1106140497/09A6B15F49530FC17C40B23B50624A88/40', 4, '100004');
INSERT INTO `fitnesslive.db`.fans (fs_id, fs_account, fs_nickname, fs_phonenum, fs_amatar, uid, account) VALUES (162, '100040', '执念', null, 'http://thirdqq.qlogo.cn/qqapp/1106140497/09A6B15F49530FC17C40B23B50624A88/40', 28, '17862901383');
INSERT INTO `fitnesslive.db`.fans (fs_id, fs_account, fs_nickname, fs_phonenum, fs_amatar, uid, account) VALUES (160, '100040', '执念', null, 'http://thirdqq.qlogo.cn/qqapp/1106140497/09A6B15F49530FC17C40B23B50624A88/40', 37, '100037');

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

DROP TABLE IF EXISTS `livethemes`;

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

DROP TABLE IF EXISTS `sysvideos`;
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
DROP TABLE IF EXISTS `sysmessage`;
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

INSERT INTO `fitnesslive.db`.users (uid, account, name, password, gender, nickname, email, idcard, phonenum, role, amatar, borndata, personalsign, islive, grade, fansnum, attentionnum, livebigpic, createtime) VALUES (12, 'admin', 'admin', 'admin', '男', 'admin', 'admin@wotrd.com', '371324199501216813', '17862901467', 1, 'http://47.93.20.45:8080/fitnesslive/img/amatar/1000006a0cd7a2-8187-4802-bc60-fa4800a2f7fa.jpg', '2018-01-14', '', 0, 0, 0, 0, null, '2018-01-12 00:19:19');
INSERT INTO `fitnesslive.db`.users (uid, account, name, password, gender, nickname, email, idcard, phonenum, role, amatar, borndata, personalsign, islive, grade, fansnum, attentionnum, livebigpic, createtime) VALUES (2, '100000', '小灰灰', '1234567', '男', '小灰灰', '17862901260@163.com', '371324199501216810', '17862901468', 1, 'http://47.93.20.45:8080/fitnesslive/img/amatar/2a6233623-eb64-43e2-a4a6-cf692b6fe3e0.jpg', '2018-01-14', '我是最棒的！', 0, 10, 2, 2, 'http://47.93.20.45:8080/fitnesslive/img/livebigpic/10000009abd5af-d0f3-4654-8a2e-f3bf6812b590.jpg', '2018-01-14 09:01:03');
INSERT INTO `fitnesslive.db`.users (uid, account, name, password, gender, nickname, email, idcard, phonenum, role, amatar, borndata, personalsign, islive, grade, fansnum, attentionnum, livebigpic, createtime) VALUES (3, '100003', '09A6B15F49530FC17C40B23B50624A88', '123456', '男', '执念', '17862901478@163.com', '371324199501216818', '17862901489', 0, 'http://47.93.20.45:8080/fitnesslive/img/amatar/3578268a8-9568-417a-b74b-b299f072dc41.jpg', '2018-01-14', '我是最棒的', 0, 5, 1, 4, 'http://47.93.20.45:8080/fitnesslive/img/livebigpic/100003d6f17492-23a4-4b5c-8fc8-f60360553322.jpg', '2018-01-14 09:01:58');
INSERT INTO `fitnesslive.db`.users (uid, account, name, password, gender, nickname, email, idcard, phonenum, role, amatar, borndata, personalsign, islive, grade, fansnum, attentionnum, livebigpic, createtime) VALUES (4, '100004', 'F5D9CE214497C3BD9ADEBA30A5288CE8', null, '男', 'sir', null, null, null, 0, 'http://q.qlogo.cn/qqapp/1106140497/F5D9CE214497C3BD9ADEBA30A5288CE8/40', '2018-01-14', '', 0, 10, 2, 0, null, '2017-12-12 22:22:53');
INSERT INTO `fitnesslive.db`.users (uid, account, name, password, gender, nickname, email, idcard, phonenum, role, amatar, borndata, personalsign, islive, grade, fansnum, attentionnum, livebigpic, createtime) VALUES (5, '100005', null, '123456', '男', '小灰灰', null, null, '17862901470', 0, 'http://47.93.20.45:8080/fitnesslive/img/amatar/100000e7bc857a-3c4c-43d2-b6b4-80f9d61780f1.jpg', '2018-01-14', '', 0, 10, 2, 1, null, '2017-12-23 21:58:41');
INSERT INTO `fitnesslive.db`.users (uid, account, name, password, gender, nickname, email, idcard, phonenum, role, amatar, borndata, personalsign, islive, grade, fansnum, attentionnum, livebigpic, createtime) VALUES (21, 'wotrd', 'wotrd', '666666', '男', 'wotrd', 'wotrd_work@aliyun.com', '371324199501216815', '17862901469', 1, 'http://47.93.20.45:8080/fitnesslive/img/amatar/100000d84f5cb1-db9c-4ff1-b49b-607772e84409.jpg', '2018-01-14', '', 0, 0, 0, 0, null, '2018-01-13 09:38:17');
INSERT INTO `fitnesslive.db`.users (uid, account, name, password, gender, nickname, email, idcard, phonenum, role, amatar, borndata, personalsign, islive, grade, fansnum, attentionnum, livebigpic, createtime) VALUES (30, 'qinghu', '高清华', '666666', '男', '清华', 'gaoqinghua@163.com', '371324199501216801', '17862901472', 1, null, '2018-01-14', '', 0, 0, 0, 0, null, '2018-01-14 13:44:21');
INSERT INTO `fitnesslive.db`.users (uid, account, name, password, gender, nickname, email, idcard, phonenum, role, amatar, borndata, personalsign, islive, grade, fansnum, attentionnum, livebigpic, createtime) VALUES (25, 'dexiang', '仇德祥', '666666', '男', '德华', '17862901470@163.com', '371324199501216811', '17862901473', 1, 'http://47.93.20.45:8080/fitnesslive/img/amatar/1000007962bae9-9913-4062-aed5-147e383e9a57.jpg', '2018-01-14', '', 0, 0, 0, 0, null, '2018-01-13 13:03:50');
INSERT INTO `fitnesslive.db`.users (uid, account, name, password, gender, nickname, email, idcard, phonenum, role, amatar, borndata, personalsign, islive, grade, fansnum, attentionnum, livebigpic, createtime) VALUES (26, 'machunhua', '马春华', '666666', '女', '春花', 'machunhua@highgo.com', '371324199501216814', '17862901481', 1, 'http://47.93.20.45:8080/fitnesslive/img/amatar/1000007962bae9-9913-4062-aed5-147e383e9a57.jpg', '2018-01-14', '', 0, 0, 0, 0, null, '2018-01-13 13:04:42');
INSERT INTO `fitnesslive.db`.users (uid, account, name, password, gender, nickname, email, idcard, phonenum, role, amatar, borndata, personalsign, islive, grade, fansnum, attentionnum, livebigpic, createtime) VALUES (27, 'diaolizhao', '刁力钊', '666666', '男', '立zhao', 'diaolizhao@danei.com', '371324199501216812', '17862901482', 1, 'http://47.93.20.45:8080/fitnesslive/img/amatar/271b7cbd95-eecc-4322-88b1-2ea8b26c27c1.jpg', '2018-01-14', '我是最棒的！', 0, 0, 0, 0, null, '2018-01-14 09:01:20');
INSERT INTO `fitnesslive.db`.users (uid, account, name, password, gender, nickname, email, idcard, phonenum, role, amatar, borndata, personalsign, islive, grade, fansnum, attentionnum, livebigpic, createtime) VALUES (28, '17862901383', '康怀光', '666666', '男', '小奈', '17862901383@163.com', '371324199501216803', '17862901383', 1, 'http://47.93.20.45:8080/fitnesslive/img/amatar/100005793671d1-74cd-4f97-b28b-65553f1b80d2.jpg', '2018-01-14', '', 0, 0, 0, 0, null, '2018-01-13 13:07:05');
INSERT INTO `fitnesslive.db`.users (uid, account, name, password, gender, nickname, email, idcard, phonenum, role, amatar, borndata, personalsign, islive, grade, fansnum, attentionnum, livebigpic, createtime) VALUES (29, 'hanyi', '韩毅', '666666', '男', '韩毅', 'hanyi@163.com', '371324199501216800', '17862901484', 1, 'http://47.93.20.45:8080/fitnesslive/img/amatar/100005b0f2ea1d-0e1a-4410-8212-aa91e2df3271.jpg', '2018-01-14', '', 0, 0, 0, 0, null, '2018-01-13 13:08:35');


INSERT INTO `fitnesslive.db`.fans (fs_id, fs_account, fs_nickname, fs_phonenum, fs_amatar, uid, account) VALUES (27, '100003', '执念', null, 'http://q.qlogo.cn/qqapp/1106140497/09A6B15F49530FC17C40B23B50624A88/40', 5, '100005');
INSERT INTO `fitnesslive.db`.fans (fs_id, fs_account, fs_nickname, fs_phonenum, fs_amatar, uid, account) VALUES (31, '100003', '执念', null, 'http://q.qlogo.cn/qqapp/1106140497/09A6B15F49530FC17C40B23B50624A88/40', 4, '100004');
INSERT INTO `fitnesslive.db`.fans (fs_id, fs_account, fs_nickname, fs_phonenum, fs_amatar, uid, account) VALUES (30, '100003', '执念', null, 'http://q.qlogo.cn/qqapp/1106140497/09A6B15F49530FC17C40B23B50624A88/40', 3, '100003');
INSERT INTO `fitnesslive.db`.fans (fs_id, fs_account, fs_nickname, fs_phonenum, fs_amatar, uid, account) VALUES (32, '100000', '小灰灰', '17862901468', 'http://47.93.20.45:8080/fitnesslive/img/amatar/2a6233623-eb64-43e2-a4a6-cf692b6fe3e0.jpg', 4, '100004');
INSERT INTO `fitnesslive.db`.fans (fs_id, fs_account, fs_nickname, fs_phonenum, fs_amatar, uid, account) VALUES (33, '100000', '小灰灰', '17862901468', 'http://47.93.20.45:8080/fitnesslive/img/amatar/2a6233623-eb64-43e2-a4a6-cf692b6fe3e0.jpg', 5, '100005');
INSERT INTO `fitnesslive.db`.fans (fs_id, fs_account, fs_nickname, fs_phonenum, fs_amatar, uid, account) VALUES (34, '100003', '执念', '17862901489', 'http://47.93.20.45:8080/fitnesslive/img/amatar/3578268a8-9568-417a-b74b-b299f072dc41.jpg', 2, '100000');
INSERT INTO `fitnesslive.db`.fans (fs_id, fs_account, fs_nickname, fs_phonenum, fs_amatar, uid, account) VALUES (35, '100005', '小灰灰', '17862901470', 'http://47.93.20.45:8080/fitnesslive/img/amatar/100000e7bc857a-3c4c-43d2-b6b4-80f9d61780f1.jpg', 2, '100000');

INSERT INTO `fitnesslive.db`.attentions (gz_id, gz_account, gz_nickname, gz_phonenum, gz_amatar, uid, account) VALUES (27, '100005', '小灰灰', '17862901470', null, 3, '100003');
INSERT INTO `fitnesslive.db`.attentions (gz_id, gz_account, gz_nickname, gz_phonenum, gz_amatar, uid, account) VALUES (31, '100004', 'sir', null, 'http://q.qlogo.cn/qqapp/1106140497/F5D9CE214497C3BD9ADEBA30A5288CE8/40', 3, '100003');
INSERT INTO `fitnesslive.db`.attentions (gz_id, gz_account, gz_nickname, gz_phonenum, gz_amatar, uid, account) VALUES (30, '100003', '执念', null, 'http://q.qlogo.cn/qqapp/1106140497/09A6B15F49530FC17C40B23B50624A88/40', 3, '100003');
INSERT INTO `fitnesslive.db`.attentions (gz_id, gz_account, gz_nickname, gz_phonenum, gz_amatar, uid, account) VALUES (32, '100004', 'sir', null, 'http://q.qlogo.cn/qqapp/1106140497/F5D9CE214497C3BD9ADEBA30A5288CE8/40', 2, '100000');
INSERT INTO `fitnesslive.db`.attentions (gz_id, gz_account, gz_nickname, gz_phonenum, gz_amatar, uid, account) VALUES (33, '100005', '小灰灰', '17862901470', 'http://47.93.20.45:8080/fitnesslive/img/amatar/100000e7bc857a-3c4c-43d2-b6b4-80f9d61780f1.jpg', 2, '100000');
INSERT INTO `fitnesslive.db`.attentions (gz_id, gz_account, gz_nickname, gz_phonenum, gz_amatar, uid, account) VALUES (34, '100000', '小灰灰', '17862901468', 'http://47.93.20.45:8080/fitnesslive/img/amatar/2a6233623-eb64-43e2-a4a6-cf692b6fe3e0.jpg', 3, '100003');
INSERT INTO `fitnesslive.db`.attentions (gz_id, gz_account, gz_nickname, gz_phonenum, gz_amatar, uid, account) VALUES (35, '100000', '小灰灰', '17862901468', 'http://47.93.20.45:8080/fitnesslive/img/amatar/2a6233623-eb64-43e2-a4a6-cf692b6fe3e0.jpg', 5, '100005');

INSERT INTO `fitnesslive.db`.livethemes (lt_id, lt_name, lt_islive, uid) VALUES (108, '开朗', 0, 3);
INSERT INTO `fitnesslive.db`.livethemes (lt_id, lt_name, lt_islive, uid) VALUES (107, '大方', 0, 3);
INSERT INTO `fitnesslive.db`.livethemes (lt_id, lt_name, lt_islive, uid) VALUES (106, '热情', 0, 3);
INSERT INTO `fitnesslive.db`.livethemes (lt_id, lt_name, lt_islive, uid) VALUES (4, '热情', 0, 4);
INSERT INTO `fitnesslive.db`.livethemes (lt_id, lt_name, lt_islive, uid) VALUES (5, '大方', 0, 4);
INSERT INTO `fitnesslive.db`.livethemes (lt_id, lt_name, lt_islive, uid) VALUES (6, '开朗', 0, 4);
INSERT INTO `fitnesslive.db`.livethemes (lt_id, lt_name, lt_islive, uid) VALUES (111, '你好', 0, 2);
INSERT INTO `fitnesslive.db`.livethemes (lt_id, lt_name, lt_islive, uid) VALUES (110, '热情', 0, 2);
INSERT INTO `fitnesslive.db`.livethemes (lt_id, lt_name, lt_islive, uid) VALUES (109, '大方', 0, 2);

INSERT INTO `fitnesslive.db`.sysvideos (uv_id, uv_title, uv_videourl, uv_thumbnailurl, uv_uploadtime, uv_type, uid) VALUES (13, '测试3', 'http://47.93.20.45:8080/fitnesslive/img/media/video/395c65d6-4e44-4a09-82a2-2522b71e69ab.mp4', 'http://47.93.20.45:8080/fitnesslive/img/media/pic/395c65d6-4e44-4a09-82a2-2522b71e69ab.jpg', '2018-01-29 23:44:28', 0, 1);
INSERT INTO `fitnesslive.db`.sysvideos (uv_id, uv_title, uv_videourl, uv_thumbnailurl, uv_uploadtime, uv_type, uid) VALUES (12, '测试2', 'http://47.93.20.45:8080/fitnesslive/img/media/video/42436ef0-9340-46df-9f59-acee563139de.mp4', 'http://47.93.20.45:8080/fitnesslive/img/media/pic/42436ef0-9340-46df-9f59-acee563139de.jpg', '2018-01-29 23:44:02', 0, 1);
INSERT INTO `fitnesslive.db`.sysvideos (uv_id, uv_title, uv_videourl, uv_thumbnailurl, uv_uploadtime, uv_type, uid) VALUES (11, '测试', 'http://47.93.20.45:8080/fitnesslive/img/media/video/996427e7-3ff6-4417-98c9-32ec3df6d676.mp4', 'http://47.93.20.45:8080/fitnesslive/img/media/pic/996427e7-3ff6-4417-98c9-32ec3df6d676.jpg', '2018-01-29 23:43:44', 0, 1);
INSERT INTO `fitnesslive.db`.sysvideos (uv_id, uv_title, uv_videourl, uv_thumbnailurl, uv_uploadtime, uv_type, uid) VALUES (14, '测试5', 'http://47.93.20.45:8080/fitnesslive/img/media/video/0721324e-9489-4e88-b2dd-152e9f02a1d1.mp4', 'http://47.93.20.45:8080/fitnesslive/img/media/pic/0721324e-9489-4e88-b2dd-152e9f02a1d1.jpg', '2018-01-29 23:44:47', 0, 1);
INSERT INTO `fitnesslive.db`.sysvideos (uv_id, uv_title, uv_videourl, uv_thumbnailurl, uv_uploadtime, uv_type, uid) VALUES (15, '测试4', 'http://47.93.20.45:8080/fitnesslive/img/media/video/b376e4de-90f3-464a-8996-59ca3f4073e3.mp4', 'http://47.93.20.45:8080/fitnesslive/img/media/pic/b376e4de-90f3-464a-8996-59ca3f4073e3.jpg', '2018-01-29 23:45:08', 0, 1);
INSERT INTO `fitnesslive.db`.sysvideos (uv_id, uv_title, uv_videourl, uv_thumbnailurl, uv_uploadtime, uv_type, uid) VALUES (16, '测试3', 'http://47.93.20.45:8080/fitnesslive/img/media/video/be5a4fab-6c38-42e7-a669-97ef1ac22f30.mp4', 'http://47.93.20.45:8080/fitnesslive/img/media/pic/be5a4fab-6c38-42e7-a669-97ef1ac22f30.jpg', '2018-01-29 23:45:26', 0, 1);
INSERT INTO `fitnesslive.db`.sysvideos (uv_id, uv_title, uv_videourl, uv_thumbnailurl, uv_uploadtime, uv_type, uid) VALUES (17, '足球', 'http://47.93.20.45:8080/fitnesslive/img/media/video/1fc673be-942d-4ef5-b7e3-214726f9a0f9.mp4', 'http://47.93.20.45:8080/fitnesslive/img/media/pic/1fc673be-942d-4ef5-b7e3-214726f9a0f9.jpg', '2018-01-30 20:51:17', 0, 1);
INSERT INTO `fitnesslive.db`.sysvideos (uv_id, uv_title, uv_videourl, uv_thumbnailurl, uv_uploadtime, uv_type, uid) VALUES (18, '篮球', 'http://47.93.20.45:8080/fitnesslive/img/media/video/87e36857-17af-4af7-b400-2c8a94ab892b.mp4', 'http://47.93.20.45:8080/fitnesslive/img/media/pic/87e36857-17af-4af7-b400-2c8a94ab892b.jpg', '2018-01-30 20:51:50', 0, 1);
INSERT INTO `fitnesslive.db`.sysvideos (uv_id, uv_title, uv_videourl, uv_thumbnailurl, uv_uploadtime, uv_type, uid) VALUES (19, '乒乓球', 'http://47.93.20.45:8080/fitnesslive/img/media/video/ad8f7358-20ea-4c74-b993-e209d6d04192.mp4', 'http://47.93.20.45:8080/fitnesslive/img/media/pic/ad8f7358-20ea-4c74-b993-e209d6d04192.jpg', '2018-01-30 20:52:14', 0, 1);
INSERT INTO `fitnesslive.db`.sysvideos (uv_id, uv_title, uv_videourl, uv_thumbnailurl, uv_uploadtime, uv_type, uid) VALUES (20, '羽毛球', 'http://47.93.20.45:8080/fitnesslive/img/media/video/8692a560-3ac0-4bd0-919a-79875399318c.mp4', 'http://47.93.20.45:8080/fitnesslive/img/media/pic/8692a560-3ac0-4bd0-919a-79875399318c.jpg', '2018-01-30 20:52:31', 0, 1);
INSERT INTO `fitnesslive.db`.sysvideos (uv_id, uv_title, uv_videourl, uv_thumbnailurl, uv_uploadtime, uv_type, uid) VALUES (21, '排球', 'http://47.93.20.45:8080/fitnesslive/img/media/video/7535abf8-1656-4d6b-a159-e81057ba9c68.mp4', 'http://47.93.20.45:8080/fitnesslive/img/media/pic/7535abf8-1656-4d6b-a159-e81057ba9c68.jpg', '2018-01-30 20:52:50', 0, 1);


INSERT INTO `fitnesslive.db`.sysmessage (sm_id, sm_title, sm_content, sm_from, sm_to, intent, isRead, owner, time, result, uid) VALUES (1, '提醒', 'nihao', 'server', 'wotrd', 0, 0, 'wotrd', '2017-12-17 09:45:12', 'success', 0);
INSERT INTO `fitnesslive.db`.sysmessage (sm_id, sm_title, sm_content, sm_from, sm_to, intent, isRead, owner, time, result, uid) VALUES (43, 'ggg', 'ggg', 'admin', 'all', 0, null, 'all', '2018-01-17 12:01:15', 'success', 12);
INSERT INTO `fitnesslive.db`.sysmessage (sm_id, sm_title, sm_content, sm_from, sm_to, intent, isRead, owner, time, result, uid) VALUES (42, 'nihao', 'gggg', 'admin', 'all', 0, null, 'all', '2018-01-16 11:01:04', 'success', 12);
INSERT INTO `fitnesslive.db`.sysmessage (sm_id, sm_title, sm_content, sm_from, sm_to, intent, isRead, owner, time, result, uid) VALUES (5, '警告', 'helloa', 'server', 'all', 0, 0, 'all', '2017-12-17 09:55:12', 'success', 0);
INSERT INTO `fitnesslive.db`.sysmessage (sm_id, sm_title, sm_content, sm_from, sm_to, intent, isRead, owner, time, result, uid) VALUES (6, '提醒', 'nihao', 'server', 'all', 0, 0, 'all', '2018-01-02 00:00:00', 'success', 0);
INSERT INTO `fitnesslive.db`.sysmessage (sm_id, sm_title, sm_content, sm_from, sm_to, intent, isRead, owner, time, result, uid) VALUES (7, '提醒', 'welcom', 'server', 'all', 0, 0, 'all', '2017-12-17 10:12:38', 'success', 0);
INSERT INTO `fitnesslive.db`.sysmessage (sm_id, sm_title, sm_content, sm_from, sm_to, intent, isRead, owner, time, result, uid) VALUES (12, '提醒', 'gggggg', 'server', '', 0, 0, '', '2017-12-17 10:41:12', 'success', 0);
INSERT INTO `fitnesslive.db`.sysmessage (sm_id, sm_title, sm_content, sm_from, sm_to, intent, isRead, owner, time, result, uid) VALUES (13, '提醒', 'gggggg', 'server', 'all', 0, 0, 'all', '2017-12-17 10:12:23', 'success', 0);
INSERT INTO `fitnesslive.db`.sysmessage (sm_id, sm_title, sm_content, sm_from, sm_to, intent, isRead, owner, time, result, uid) VALUES (14, '提醒', 'gggggg', 'server', 'all', 0, 0, 'all', '2017-12-17 10:12:09', 'success', 0);
INSERT INTO `fitnesslive.db`.sysmessage (sm_id, sm_title, sm_content, sm_from, sm_to, intent, isRead, owner, time, result, uid) VALUES (15, '提醒', 'gggggg', 'server', 'all', 0, 0, 'all', '2017-12-17 10:12:31', 'success', 0);
INSERT INTO `fitnesslive.db`.sysmessage (sm_id, sm_title, sm_content, sm_from, sm_to, intent, isRead, owner, time, result, uid) VALUES (16, '提醒', 'gggggg', 'server', 'all', 0, 0, 'all', '2017-12-17 10:12:09', 'success', 0);
INSERT INTO `fitnesslive.db`.sysmessage (sm_id, sm_title, sm_content, sm_from, sm_to, intent, isRead, owner, time, result, uid) VALUES (17, '提醒', 'gggggg', 'server', 'all', 0, 0, 'all', '2017-12-17 10:12:59', 'success', 0);
INSERT INTO `fitnesslive.db`.sysmessage (sm_id, sm_title, sm_content, sm_from, sm_to, intent, isRead, owner, time, result, uid) VALUES (18, '提醒', 'gggggg', 'server', 'all', 0, 0, 'all', '2017-12-17 10:12:41', 'success', 0);
INSERT INTO `fitnesslive.db`.sysmessage (sm_id, sm_title, sm_content, sm_from, sm_to, intent, isRead, owner, time, result, uid) VALUES (19, '提醒', 'gggggg', 'server', 'all', 0, 0, 'all', '2017-12-17 10:12:46', 'success', 0);
INSERT INTO `fitnesslive.db`.sysmessage (sm_id, sm_title, sm_content, sm_from, sm_to, intent, isRead, owner, time, result, uid) VALUES (20, '提醒', '', 'server', 'all', 0, 0, 'all', '2017-12-18 09:12:04', 'success', 0);
INSERT INTO `fitnesslive.db`.sysmessage (sm_id, sm_title, sm_content, sm_from, sm_to, intent, isRead, owner, time, result, uid) VALUES (21, '提醒', '', 'server', 'all', 0, 0, 'all', '2017-12-18 09:12:34', 'success', 0);
INSERT INTO `fitnesslive.db`.sysmessage (sm_id, sm_title, sm_content, sm_from, sm_to, intent, isRead, owner, time, result, uid) VALUES (22, '提醒', '', 'server', 'all', 0, 0, 'all', '2017-12-18 09:12:58', 'success', 0);
INSERT INTO `fitnesslive.db`.sysmessage (sm_id, sm_title, sm_content, sm_from, sm_to, intent, isRead, owner, time, result, uid) VALUES (23, '提醒', '', 'server', 'all', 0, 0, 'all', '2017-12-18 09:12:11', 'success', 0);
INSERT INTO `fitnesslive.db`.sysmessage (sm_id, sm_title, sm_content, sm_from, sm_to, intent, isRead, owner, time, result, uid) VALUES (24, '提醒', '', 'server', 'all', 0, 0, 'all', '2017-12-20 08:12:44', 'success', 0);
INSERT INTO `fitnesslive.db`.sysmessage (sm_id, sm_title, sm_content, sm_from, sm_to, intent, isRead, owner, time, result, uid) VALUES (25, '提醒', 'ggggg', 'server', 'all', 0, 0, 'all', '2017-12-20 10:12:18', 'success', 0);
INSERT INTO `fitnesslive.db`.sysmessage (sm_id, sm_title, sm_content, sm_from, sm_to, intent, isRead, owner, time, result, uid) VALUES (26, '提醒', 'ggggg', 'server', 'all', 0, 0, 'all', '2017-12-20 10:12:01', 'success', 0);
INSERT INTO `fitnesslive.db`.sysmessage (sm_id, sm_title, sm_content, sm_from, sm_to, intent, isRead, owner, time, result, uid) VALUES (27, '提醒', '年后', 'server', 'all', 0, 0, 'all', '2017-12-20 10:12:24', 'success', 0);
INSERT INTO `fitnesslive.db`.sysmessage (sm_id, sm_title, sm_content, sm_from, sm_to, intent, isRead, owner, time, result, uid) VALUES (28, '提醒', '开始', 'server', 'all', 0, 0, 'all', '2017-12-20 10:12:45', 'success', 0);
INSERT INTO `fitnesslive.db`.sysmessage (sm_id, sm_title, sm_content, sm_from, sm_to, intent, isRead, owner, time, result, uid) VALUES (29, '提醒', '开始', 'server', 'all', 0, 0, 'all', '2017-12-20 10:12:48', 'success', 0);
INSERT INTO `fitnesslive.db`.sysmessage (sm_id, sm_title, sm_content, sm_from, sm_to, intent, isRead, owner, time, result, uid) VALUES (30, '提醒', '开始', 'server', 'all', 0, 0, 'all', '2017-12-20 10:12:31', 'success', 0);
INSERT INTO `fitnesslive.db`.sysmessage (sm_id, sm_title, sm_content, sm_from, sm_to, intent, isRead, owner, time, result, uid) VALUES (31, '提醒', 'test', 'server', 'all', 0, 0, 'all', '2017-12-20 10:12:58', 'success', 0);
INSERT INTO `fitnesslive.db`.sysmessage (sm_id, sm_title, sm_content, sm_from, sm_to, intent, isRead, owner, time, result, uid) VALUES (32, '提醒', 'test', 'server', 'all', 0, 0, 'all', '2017-12-20 11:12:54', 'success', 0);
INSERT INTO `fitnesslive.db`.sysmessage (sm_id, sm_title, sm_content, sm_from, sm_to, intent, isRead, owner, time, result, uid) VALUES (33, '提醒', 'test', 'server', 'all', 0, 0, 'all', '2017-12-20 11:12:06', 'success', 0);
INSERT INTO `fitnesslive.db`.sysmessage (sm_id, sm_title, sm_content, sm_from, sm_to, intent, isRead, owner, time, result, uid) VALUES (34, '提醒', 'test', 'server', 'all', 0, 0, 'all', '2017-12-20 11:12:47', 'success', 0);
INSERT INTO `fitnesslive.db`.sysmessage (sm_id, sm_title, sm_content, sm_from, sm_to, intent, isRead, owner, time, result, uid) VALUES (35, '提醒', 'test', 'server', 'all', 0, 0, 'all', '2017-12-20 11:12:22', 'success', 0);
INSERT INTO `fitnesslive.db`.sysmessage (sm_id, sm_title, sm_content, sm_from, sm_to, intent, isRead, owner, time, result, uid) VALUES (36, '提醒', 'test', 'server', 'all', 0, 0, 'all', '2017-12-20 11:12:46', 'success', 0);
INSERT INTO `fitnesslive.db`.sysmessage (sm_id, sm_title, sm_content, sm_from, sm_to, intent, isRead, owner, time, result, uid) VALUES (37, '提醒', 'test', 'server', 'all', 0, 0, 'all', '2017-12-20 11:12:00', 'success', 0);
INSERT INTO `fitnesslive.db`.sysmessage (sm_id, sm_title, sm_content, sm_from, sm_to, intent, isRead, owner, time, result, uid) VALUES (38, '提醒', 'fffffffffff', 'server', 'all', 0, 0, 'all', '2017-12-21 09:12:46', 'success', 0);
INSERT INTO `fitnesslive.db`.sysmessage (sm_id, sm_title, sm_content, sm_from, sm_to, intent, isRead, owner, time, result, uid) VALUES (39, '提醒', 'fffffffffff', 'server', 'all', 0, 0, 'all', '2017-12-21 09:12:03', 'success', 0);
INSERT INTO `fitnesslive.db`.sysmessage (sm_id, sm_title, sm_content, sm_from, sm_to, intent, isRead, owner, time, result, uid) VALUES (40, '提醒', '你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好', 'server', 'all', 0, 0, 'all', '2017-12-21 10:12:47', 'success', 0);
INSERT INTO `fitnesslive.db`.sysmessage (sm_id, sm_title, sm_content, sm_from, sm_to, intent, isRead, owner, time, result, uid) VALUES (41, '提醒', '灌灌灌灌灌过过过过过过过过过过过过过过过过过过过过过过过过过过过过过过过过过过过过过过过过过过过过过过过过过过过过过过过过过过过灌灌灌灌灌过过过过过过过过过过过过过过过过', 'server', 'all', 0, 0, 'all', '2017-12-23 09:12:38', 'success', 0);
INSERT INTO `fitnesslive.db`.sysmessage (sm_id, sm_title, sm_content, sm_from, sm_to, intent, isRead, owner, time, result, uid) VALUES (44, 'ggg', 'gggg', 'admin', 'all', 0, null, 'all', '2018-01-17 12:01:25', 'failed', 12);
INSERT INTO `fitnesslive.db`.sysmessage (sm_id, sm_title, sm_content, sm_from, sm_to, intent, isRead, owner, time, result, uid) VALUES (45, 'ggg', 'ggg', 'admin', 'all', 0, null, 'all', '2018-01-17 12:01:12', 'failed', 12);
INSERT INTO `fitnesslive.db`.sysmessage (sm_id, sm_title, sm_content, sm_from, sm_to, intent, isRead, owner, time, result, uid) VALUES (46, 'ggg', 'ggg', 'admin', 'all', 0, null, 'all', '2018-01-17 12:01:31', 'success', 12);
INSERT INTO `fitnesslive.db`.sysmessage (sm_id, sm_title, sm_content, sm_from, sm_to, intent, isRead, owner, time, result, uid) VALUES (47, 'afgafa', 'afafafafaffffffffffffff', 'admin', 'all', 0, null, 'all', '2018-01-17 12:01:48', 'success', 12);
