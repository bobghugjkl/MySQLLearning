/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2023/12/10 19:43:01                          */
/*==============================================================*/


drop table if exists t_power;

drop table if exists t_role;

drop table if exists t_rolepower;

drop table if exists t_user;

/*==============================================================*/
/* Table: t_power                                               */
/*==============================================================*/
create table t_power
(
   pid                  varchar(40) not null,
   powername            varchar(40),
   primary key (pid)
);

/*==============================================================*/
/* Table: t_role                                                */
/*==============================================================*/
create table t_role
(
   rid                  varchar(40) not null,
   rolename             varchar(40),
   primary key (rid)
);

/*==============================================================*/
/* Table: t_rolepower                                           */
/*==============================================================*/
create table t_rolepower
(
   rpid                 varchar(40) not null,
   rid                  varchar(40),
   pid                  varchar(40),
   primary key (rpid)
);

/*==============================================================*/
/* Table: t_user                                                */
/*==============================================================*/
create table t_user
(
   ID                   varchar(40) not null,
   rid                  varchar(40),
   username             varchar(40),
   password             varchar(40),
   phonenumber          int,
   creattime            timestamp,
   primary key (ID)
);

alter table t_rolepower add constraint FK_Reference_2 foreign key (rid)
      references t_role (rid) on delete restrict on update restrict;

alter table t_rolepower add constraint FK_Reference_3 foreign key (pid)
      references t_power (pid) on delete restrict on update restrict;

alter table t_user add constraint FK_rp_user_role foreign key (rid)
      references t_role (rid) on delete restrict on update restrict;

