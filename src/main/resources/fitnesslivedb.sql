create table books
(
    id          bigint auto_increment
        primary key,
    name        varchar(64) null,
    type        varchar(64) null,
    price       int         null,
    avatar      varchar(64) null,
    remark      varchar(64) null,
    seller_name varchar(64) null,
    seller_id   bigint      null
);

create table orders
(
    id          bigint auto_increment
        primary key,
    b_name      varchar(64) null,
    b_count     int         null,
    status      int         null,
    b_price     decimal     null,
    total_price decimal     null,
    buyer_id    int         null,
    sell_id     int         null,
    buyer_name  varchar(64) null,
    sell_name   varchar(64) null,
    create_time timestamp   null
);

create table users
(
    id          bigint auto_increment
        primary key,
    account     varchar(64) null,
    name        varchar(64) null,
    password    int         null,
    gender      int         null,
    nick_name   varchar(64) null,
    email       varchar(64) null,
    idcard      varchar(64) null,
    mobile_num  varchar(64) null,
    role        int         null,
    amatar      varchar(64) null,
    remark      varchar(64) null,
    create_time timestamp   null
);

