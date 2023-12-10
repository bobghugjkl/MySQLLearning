/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2023/12/8 21:49:27                           */
/*==============================================================*/


drop table if exists t_cursor;

drop table if exists t_s_course;

drop table if exists t_student;

drop table if exists t_teacher;

/*==============================================================*/
/* Table: t_cursor                                              */
/*==============================================================*/
create table t_cursor
(
   cno                  int(11) not null,
   cname                varchar(255),
   primary key (cno)
);

/*==============================================================*/
/* Table: t_s_course                                            */
/*==============================================================*/
create table t_s_course
(
   scno                 int(11) not null,
   sno                  int,
   cno                  int(11),
   primary key (scno)
);

/*==============================================================*/
/* Table: t_student                                             */
/*==============================================================*/
create table t_student
(
   sno                  int not null,
   tno                  int,
   sname                char(40),
   primary key (sno)
);

/*==============================================================*/
/* Table: t_teacher                                             */
/*==============================================================*/
create table t_teacher
(
   tno                  int not null,
   tname                varchar(40),
   gender               char(2),
   primary key (tno)
);

alter table t_teacher comment '存放教师信息';

alter table t_s_course add constraint FK_Reference_3 foreign key (cno)
      references t_cursor (cno) on delete restrict on update restrict;

alter table t_s_course add constraint FK_r_student_c foreign key (sno)
      references t_student (sno) on delete restrict on update restrict;

alter table t_student add constraint FK_rs_t_s foreign key (tno)
      references t_teacher (tno) on delete restrict on update restrict;

