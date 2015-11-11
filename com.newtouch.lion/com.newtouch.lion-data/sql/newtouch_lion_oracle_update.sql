create table BAS_CALENDAR
(
  bas_calendar_id NUMBER(11) not null,
  bas_user_id     NUMBER(11),
  event_name      VARCHAR2(256),
  start_date      DATE not null,
  end_date        DATE,
  start_time      DATE,
  end_time        DATE,
  allday          NUMBER(1),
  create_by       NUMBER(11),
  create_date     DATE default sysdate,
  update_by       NUMBER(11),
  update_date     DATE default sysdate,
  opt_counter     NUMBER(6),
  delete_date     DATE,
  mark_for_delete NUMBER(1) default 0
);
-- Add comments to the table 
comment on table BAS_CALENDAR
  is '待办事项';
-- Add comments to the columns 
comment on column BAS_CALENDAR.bas_calendar_id
  is 'ID';
comment on column BAS_CALENDAR.bas_user_id
  is '用户ID';
comment on column BAS_CALENDAR.event_name
  is '事件名称';
comment on column BAS_CALENDAR.start_date
  is '事件开始时间';
comment on column BAS_CALENDAR.end_date
  is '事件结束时间';
comment on column BAS_CALENDAR.start_time
  is '事件结束时间';
comment on column BAS_CALENDAR.end_time
  is '事件结束时间';
comment on column BAS_CALENDAR.allday
  is '是否是全天';
comment on column BAS_CALENDAR.create_by
  is '创建人ID';
comment on column BAS_CALENDAR.create_date
  is '创建时间';
comment on column BAS_CALENDAR.update_by
  is '更新人ID';
comment on column BAS_CALENDAR.update_date
  is '更新时间';
comment on column BAS_CALENDAR.opt_counter
  is '记录操作次，乐观锁';
comment on column BAS_CALENDAR.delete_date
  is '删除时间';
comment on column BAS_CALENDAR.mark_for_delete
  is '是否标记删除 0 未删除   1 表示删除';
-- Create/Recreate primary, unique and foreign key constraints 
alter table BAS_CALENDAR  add constraint PK_BAS_CALENDAR_ID primary key (BAS_CALENDAR_ID);




-- Create table
create table BAS_TASKS
(
  bas_tasks_id    NUMBER(11) not null,
  name            VARCHAR2(500),
  cron            VARCHAR2(200),
  bean_class      VARCHAR2(200),
  bean_name       VARCHAR2(200),
  method_name     VARCHAR2(200),
  is_start        NUMBER(1),
  description     VARCHAR2(512),
  create_by       NUMBER(11),
  create_date     DATE default sysdate,
  update_by       NUMBER(11),
  update_date     DATE default sysdate,
  opt_counter     NUMBER(6),
  delete_date     DATE,
  mark_for_delete NUMBER(1) default 0
);
-- Add comments to the table 
comment on table BAS_TASKS
  is '任务列表';
-- Add comments to the columns 
comment on column BAS_TASKS.bas_tasks_id
  is 'ID';
comment on column BAS_TASKS.name
  is '名称';
comment on column BAS_TASKS.cron
  is 'CRON表达式';
comment on column BAS_TASKS.bean_class
  is 'bean class 类';
comment on column BAS_TASKS.bean_name
  is 'bean Name';
comment on column BAS_TASKS.method_name
  is '方法名称';
comment on column BAS_TASKS.is_start
  is '是否启动 0 未启动，1 已启动';
comment on column BAS_TASKS.description
  is '描述';
comment on column BAS_TASKS.create_by
  is '创建人ID';
comment on column BAS_TASKS.create_date
  is '创建时间';
comment on column BAS_TASKS.update_by
  is '更新人ID';
comment on column BAS_TASKS.update_date
  is '更新时间';
comment on column BAS_TASKS.opt_counter
  is '记录操作次，乐观锁';
comment on column BAS_TASKS.delete_date
  is '删除时间';
comment on column BAS_TASKS.mark_for_delete
  is '是否标记删除 0 未删除   1 表示删除';
-- Create/Recreate primary, unique and foreign key constraints 
alter table BAS_TASKS
  add constraint PK_BAS_TASKS_ID primary key (BAS_TASKS_ID);
