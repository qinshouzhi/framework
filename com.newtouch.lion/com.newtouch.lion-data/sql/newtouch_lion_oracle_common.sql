-- Add/modify columns 
alter table TABLE_NAME add create_by NUMBER(11);
alter table TABLE_NAME add create_date date default sysdate;
alter table TABLE_NAME add update_by NUMBER(11);
alter table TABLE_NAME add update_date date default sysdate;
alter table TABLE_NAME add opt_counter NUMBER(6);
alter table TABLE_NAME add delete_date date;
alter table TABLE_NAME add mark_for_delete NUMBER(1) default 0;
-- Add comments to the columns 
comment on column TABLE_NAME.create_by
  is '创建人ID';
comment on column TABLE_NAME.create_date
  is '创建时间';
comment on column TABLE_NAME.update_by
  is '更新人ID';
comment on column TABLE_NAME.update_date
  is '更新时间';
comment on column TABLE_NAME.opt_counter
  is '记录操作次，乐观锁';
comment on column TABLE_NAME.delete_date
  is '删除时间';
comment on column TABLE_NAME.mark_for_delete
  is '是否标记删除 0 未删除   1 表示删除';
