/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/4/29 22:41:52                           */
/*==============================================================*/


drop table if exists contact_info;

drop table if exists customer_info;

drop table if exists customer_note;

drop table if exists img_info;

drop table if exists product_catagory;

drop table if exists product_info;

drop table if exists sys_param;

drop table if exists text_info;

/*==============================================================*/
/* Table: contact_info                                          */
/*==============================================================*/
create table contact_info
(
   id                   varchar(32) not null comment 'id',
   contact_name         varchar(50) comment '联系人姓名',
   contact_tel          varchar(30) not null comment '联系方式  手机 座机 邮箱',
   contact_content      varchar(20) comment '联系内容  文本id  ',
   contact_title        varchar(100) comment '联系标题',
   contact_time         datetime not null comment '联系时间',
   contact_state        varchar(2) not null comment '联系状态',
   delete_flg           char(1) comment '逻辑删除标记',
   primary key (id)
);

alter table contact_info comment '联系信息表';

/*==============================================================*/
/* Table: customer_info                                         */
/*==============================================================*/
create table customer_info
(
   id                   varchar(32) not null comment 'id',
   customer_name        varchar(50) comment '客户名称',
   customer_title       varchar(50) comment '客户称呼',
   customer_tel         varchar(20) comment '客户座机',
   customer_mob         varchar(20) comment '客户手机',
   customer_mail        varchar(50) comment '客户邮箱',
   customer_fox         varchar(20) comment '客户传真',
   customer_company     varchar(100) comment '客户公司',
   company_link         varchar(100) comment '客户公司官网',
   company_address      varchar(200) comment '客户公司地址',
   customer_lv          varchar(2) not null comment '客户级别  10至关重要，20重要，30普通，40不重要，50忽视
            默认20重要',
   customer_state       varchar(2) not null comment '客户状态 10初期意向，20已沟通，30交易中，40已交易，50删除
            默认10初期意向',
   customer_need        varchar(500) comment '客户需求',
   costomer_cost        varchar(100) comment '客户投资',
   costomer_mark        varchar(500) comment '备注',
   costomer_plan        varchar(20) comment '项目方案',
   plan_time            datetime comment '客户方案时间',
   costomer_price       varchar(20) comment '项目报价',
   price_time           datetime comment '客户报价时间',
   customer_contract    varchar(20) comment '项目合同',
   contract_time        datetime comment '客户合同时间',
   register_date        datetime not null comment '创建时间',
   register_id          varchar(32) comment '创建者',
   delete_flg           char(1) comment '逻辑删除标识',
   primary key (id)
);

alter table customer_info comment '客户信息表';

/*==============================================================*/
/* Table: customer_note                                         */
/*==============================================================*/
create table customer_note
(
   id                   varchar(32) not null comment 'id',
   customer_id          varchar(32) not null comment '客户id',
   note_content         varchar(500) not null comment '笔记跟进内容',
   note_method          varchar(10) not null comment '笔记跟进方式（TEL电话，EMAIL邮件，VISIT拜访，MEET会议）',
   note_type            varchar(20) not null comment '笔记跟进类型（Communicate沟通，PLAN方案，Price报价，contract合同）',
   note_time            datetime not null comment '笔记跟进时间',
   register_id          varchar(32) not null comment '登记人',
   note_file            varchar(20) comment '笔记跟进附件（合同，报价，方案等）',
   primary key (id)
);

alter table customer_note comment '客户笔记表（跟进用）';

/*==============================================================*/
/* Table: img_info                                              */
/*==============================================================*/
create table img_info
(
   id                   varchar(32) not null comment 'id',
   img_id               varchar(20) not null comment '图片id',
   img_name             varchar(20) not null comment '图片名',
   img_url              varchar(200) not null comment '图片链接',
   primary key (id)
);

alter table img_info comment '图片对应表';

/*==============================================================*/
/* Table: product_catagory                                      */
/*==============================================================*/
create table product_catagory
(
   id                   varchar(32) not null comment 'id',
   catagory_sort        int(1) not null comment '分类排序  6大分类 排序1-6',
   parent_id            varchar(32) comment '父级分类id',
   catagory_lv          char(9) comment '分类排序序列号 3级9位',
   catagory_name        varchar(30) comment '分类名称',
   catagory_img         varchar(20) comment '分类图片',
   primary key (id)
);

alter table product_catagory comment '产品目录表';

/*==============================================================*/
/* Table: product_info                                          */
/*==============================================================*/
create table product_info
(
   id                   varchar(32) not null comment 'id',
   product_sort         int(2) not null comment '产品排序  分类下最多20个商品 排序1-20',
   catagory_id          varchar(32) not null comment '产品所属分类id',
   product_name         varchar(100) comment '产品名称 50字以内',
   product_img          varchar(20) comment '产品图片 图片id',
   product_des          varchar(20) comment '产品描述 文本id',
   primary key (id)
);

alter table product_info comment '产品信息表';

/*==============================================================*/
/* Table: sys_param                                             */
/*==============================================================*/
create table sys_param
(
   id                   varchar(32) not null comment 'id',
   param_type           varchar(30) not null comment '参数类型：CHAR为普通短字符串，TEXT为文本，PIC为图片',
   param_key            varchar(50) not null comment '参数对应key',
   param_value          varchar(100) comment '参数对应值',
   primary key (id)
);

alter table sys_param comment '系统参数表';

/*==============================================================*/
/* Table: text_info                                             */
/*==============================================================*/
create table text_info
(
   id                   varchar(32) not null comment 'id',
   text_id              varchar(20) comment '文本id',
   text_content         text comment '文本内容',
   primary key (id)
);

alter table text_info comment '大字符串对应表';

