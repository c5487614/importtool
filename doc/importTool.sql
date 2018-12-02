drop table if exists LegalPerson;

drop table if exists Person;

/*==============================================================*/
/* Table: LegalPerson                                           */
/*==============================================================*/
create table LegalPerson
(
   Id                   varchar(36) not null comment '序列号',
   PersonName           varchar(64) comment '法人姓名',
   PersonIdCardNum      varchar(36) comment '法人身份证',
   Mobile               varchar(36) comment '移动电话',
   Password             varchar(36) comment '密码',
   CompanyName          varchar(256) comment '公司名称',
   CompanyIdNum         varchar(36) comment '公司统一社会信用代码',
   CompanyType          varchar(36) comment '公司类型',
   ImportTag            varchar(128) comment '导入标记',
   ImportTime           datetime comment '导入时间',
   Result               varchar(36) comment '导入结果',
   UpdateTime           datetime comment '更新时间',
   primary key (Id)
);

/*==============================================================*/
/* Table: Person                                                */
/*==============================================================*/
create table Person
(
   Id                   varchar(36) not null comment '序列号',
   Name                 varchar(64) comment '姓名',
   IdCardNum            varchar(36) comment '身份证',
   Mobile               varchar(36) comment '移动电话',
   Password             varchar(36) comment '密码',
   ImportTag            varchar(128) comment '导入标注',
   ImportTime           datetime comment '导入时间',
   Result               varchar(36) comment '导入结果',
   UpdateTime           datetime comment '更新时间',
   ResultFlag           tinyint comment '业务标志',
   primary key (Id)
);
