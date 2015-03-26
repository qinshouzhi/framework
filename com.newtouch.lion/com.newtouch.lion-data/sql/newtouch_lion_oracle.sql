create table BAS_APP_PROPERTIES
(
  app_properties_id NUMBER(11),
  app_id            VARCHAR2(20),
  key_              VARCHAR2(128),
  value_            VARCHAR2(500),
  create_by         NUMBER(11),
  create_date       DATE default sysdate,
  update_by         NUMBER(11),
  update_date       DATE default sysdate,
  mark_for_delete   NUMBER(1),
  opt_counter       NUMBER(6),
  delete_date       DATE,
  description       VARCHAR2(256)
);
comment on table BAS_APP_PROPERTIES
  is '应用参数表';
comment on column BAS_APP_PROPERTIES.app_properties_id
  is '参数ID';
comment on column BAS_APP_PROPERTIES.app_id
  is '参数项目';
comment on column BAS_APP_PROPERTIES.key_
  is '参数KEY';
comment on column BAS_APP_PROPERTIES.value_
  is '参数值';
comment on column BAS_APP_PROPERTIES.create_by
  is '创建人';
comment on column BAS_APP_PROPERTIES.create_date
  is '创建时间';
comment on column BAS_APP_PROPERTIES.update_by
  is '更新人';
comment on column BAS_APP_PROPERTIES.update_date
  is '更新时间';
comment on column BAS_APP_PROPERTIES.mark_for_delete
  is '系统默认字段.逻辑删除标记  0 未删除   1 表示删除';
comment on column BAS_APP_PROPERTIES.opt_counter
  is '系统默认字段.版本记录';
comment on column BAS_APP_PROPERTIES.delete_date
  is '删除时间';
comment on column BAS_APP_PROPERTIES.description
  is '参数描述';

create table BAS_CODE_LIST
(
  bas_code_list_id NUMBER(11) not null,
  bas_code_type_id NUMBER(11),
  code_value       VARCHAR2(255),
  name_zh          VARCHAR2(255),
  name_en          VARCHAR2(255),
  sort_no          NUMBER(11),
  create_by        NUMBER(11) not null,
  create_date      DATE not null,
  update_by        NUMBER(11) not null,
  update_date      DATE not null,
  mark_for_delete  NUMBER(1),
  opt_counter      NUMBER(6),
  delete_date      DATE,
  editable         NUMBER(1) not null,
  selected         NUMBER(1)
);
create index BAS_CODE_LIST_BAS_CODE_TYPE_ID on BAS_CODE_LIST (BAS_CODE_TYPE_ID);
create unique index BAS_CODE_LIST_NAME_EN on BAS_CODE_LIST (NAME_EN);
create unique index BAS_CODE_LIST_NAME_ZH on BAS_CODE_LIST (NAME_ZH);
alter table BAS_CODE_LIST  add constraint BAS_CODE_LIST_PRIMARYKEY primary key (BAS_CODE_LIST_ID) using index ;

create table BAS_CODE_TYPE
(
  bas_code_type_id NUMBER(11) not null,
  code_type        VARCHAR2(20),
  name_zh          VARCHAR2(128),
  name_en          VARCHAR2(128),
  editable         NUMBER(1),
  code_len_limit   NUMBER(11),
  create_by        NUMBER(11) not null,
  create_date      DATE not null,
  update_by        NUMBER(11) not null,
  update_date      DATE not null,
  mark_for_delete  NUMBER(1),
  opt_counter      NUMBER(6),
  delete_date      DATE
);
create unique index BAS_CODE_TYPE_NAME_EN on BAS_CODE_TYPE (NAME_EN);
create unique index BAS_CODE_TYPE_NAME_ZH on BAS_CODE_TYPE (NAME_ZH);
alter table BAS_CODE_TYPE
  add constraint BAS_CODE_TYPE_PRIMARYKEY primary key (BAS_CODE_TYPE_ID)
  using index ;

create table BAS_COLUMN
(
  bas_column_id   NUMBER(11) not null,
  bas_datagrid_id NUMBER(11),
  show_order      NUMBER(11) not null,
  filed           VARCHAR2(100) not null,
  name            VARCHAR2(100),
  width           NUMBER(10,2) not null,
  rowspan         NUMBER(11),
  colspan         NUMBER(11),
  sortable        NUMBER(1) not null,
  sort_order      VARCHAR2(5),
  header_align    VARCHAR2(10),
  align           VARCHAR2(10) not null,
  hidden          NUMBER(1),
  check_box       NUMBER(1),
  formatter       VARCHAR2(256),
  styler          VARCHAR2(256),
  sorter          VARCHAR2(256),
  editor          VARCHAR2(256),
  create_by       NUMBER(11) not null,
  create_date     DATE not null,
  update_by       NUMBER(11) not null,
  update_date     DATE not null,
  mark_for_delete NUMBER(1),
  opt_counter     NUMBER(6),
  delete_date     DATE
);
create index BAS_COLUMN_BAS_DATAGRID_ID on BAS_COLUMN (BAS_DATAGRID_ID);
alter table BAS_COLUMN
  add constraint BAS_COLUMN_PRIMARYKEY primary key (BAS_COLUMN_ID)
  using index ;

create table BAS_DATAGRID
(
  bas_datagrid_id NUMBER(11) not null,
  type_            VARCHAR2(30),
  table_id        VARCHAR2(60) not null,
  title           VARCHAR2(128),
  show_title      NUMBER(1),
  fit             NUMBER(1),
  frozen          NUMBER(1),
  show_group      NUMBER(1),
  paginaction     NUMBER(1),
  fit_columns     NUMBER(1),
  striped         NUMBER(1),
  method          VARCHAR2(15),
  nowrap          NUMBER(1),
  url             VARCHAR2(256),
  data_            VARCHAR2(512),
  load_msg        VARCHAR2(100),
  row_numbers     NUMBER(1),
  single_select   NUMBER(1),
  check_on_select NUMBER(1),
  select_on_check NUMBER(1),
  page_position   VARCHAR2(10),
  page_number     NUMBER(11),
  page_size       NUMBER(11),
  page_list       VARCHAR2(50),
  queyr_params    VARCHAR2(50),
  sort_name       VARCHAR2(30),
  sort_order      VARCHAR2(5),
  remote_sort     NUMBER(1),
  show_header     NUMBER(1),
  show_footer     NUMBER(1),
  scrollber_size  NUMBER(11),
  row_styler      VARCHAR2(100),
  load_filter     VARCHAR2(100),
  editers         VARCHAR2(100),
  loader          VARCHAR2(100),
  view_            VARCHAR2(100),
  create_by       NUMBER(11) not null,
  create_date     DATE not null,
  update_by       NUMBER(11) not null,
  update_date     DATE not null,
  mark_for_delete NUMBER(1),
  opt_counter     NUMBER(6),
  delete_date     DATE
);
alter table BAS_DATAGRID
  add constraint BAS_DATAGRID_PRIMARYKEY primary key (BAS_DATAGRID_ID)
  using index ;

create table BAS_DEMO
(
  bas_demo_id NUMBER(19) not null,
  create_by   NUMBER(19),
  create_date DATE,
  update_by   NUMBER(19),
  update_date DATE,
  name_zh     VARCHAR2(30),
  parent_id   NUMBER(19)
);
alter table BAS_DEMO
  add primary key (BAS_DEMO_ID)
  using index ;
alter table BAS_DEMO
  add constraint FK_MGIHAFSPYQV9K6B08L0JKFGEU foreign key (PARENT_ID)
  references BAS_DEMO (BAS_DEMO_ID);

create table BAS_DEPARTMENT
(
  bas_department_id        NUMBER(11) not null,
  parent_bas_department_id NUMBER(11),
  name_zh                  VARCHAR2(128) not null,
  name_en                  VARCHAR2(128) not null,
  departmentno             VARCHAR2(30) not null,
  description              VARCHAR2(256),
  editable                 NUMBER(1) not null,
  create_by                NUMBER(11) not null,
  create_date              DATE not null,
  update_by                NUMBER(11) not null,
  update_date              DATE not null,
  mark_for_delete          NUMBER(1),
  opt_counter              NUMBER(6),
  delete_date              DATE
);
create index BAS_DEPARTMENT_PARENT_BAS_DEPA on BAS_DEPARTMENT (PARENT_BAS_DEPARTMENT_ID);
alter table BAS_DEPARTMENT
  add constraint BAS_DEPARTMENT_PRIMARYKEY primary key (BAS_DEPARTMENT_ID)
  using index ;
create table BAS_GROUP
(
  bas_group_id    NUMBER(11) not null,
  name_zh         VARCHAR2(128) not null,
  name_en         VARCHAR2(128) not null,
  description     VARCHAR2(256),
  editable        NUMBER(1) not null,
  create_by       NUMBER(11) not null,
  create_date     DATE not null,
  update_by       NUMBER(11) not null,
  update_date     DATE not null,
  mark_for_delete NUMBER(1),
  opt_counter     NUMBER(6),
  delete_date     DATE
);
alter table BAS_GROUP
  add constraint BAS_GROUP_PRIMARYKEY primary key (BAS_GROUP_ID)
  using index ;

create table BAS_GROUP_ROLE
(
  bas_role_id  NUMBER(19) not null,
  bas_group_id NUMBER(19) not null
);
alter table BAS_GROUP_ROLE
  add primary key (BAS_GROUP_ID, BAS_ROLE_ID)
  using index ;
create table BAS_ICON
(
  bas_icon_id     NUMBER(11) not null,
  icon_type       VARCHAR2(255),
  icon_class      VARCHAR2(255),
  icon_image      VARCHAR2(255),
  create_by       NUMBER(11) not null,
  create_date     DATE not null,
  update_by       NUMBER(11) not null,
  update_date     DATE not null,
  mark_for_delete NUMBER(11),
  opt_counter     NUMBER(11),
  delete_date     DATE
);
alter table BAS_ICON
  add constraint BAS_ICON_PRIMARYKEY primary key (BAS_ICON_ID)
  using index ;

create table BAS_LOGIN_LOG
(
  bas_login_log_id NUMBER(11) not null,
  bas_user_id      NUMBER(11),
  login_ip         VARCHAR2(20),
  login_mac        VARCHAR2(20),
  browser_name     VARCHAR2(100),
  session_id       VARCHAR2(60),
  login_result     VARCHAR2(120),
  login_time       DATE,
  logout_time      DATE,
  os_info          VARCHAR2(120),
  is_success       NUMBER(1),
  create_by        NUMBER(11) not null,
  create_date      DATE not null,
  update_by        NUMBER(11) not null,
  update_date      DATE not null,
  mark_for_delete  NUMBER(1),
  opt_counter      NUMBER(6),
  delete_date      DATE
);

create table BAS_PARAMETER
(
  bas_parameter_id NUMBER(11) not null,
  category         VARCHAR2(30) not null,
  name_en          VARCHAR2(128) not null,
  name_zh          VARCHAR2(128) not null,
  parameter_value  VARCHAR2(256) not null,
  editable         NUMBER(1),
  description      VARCHAR2(512),
  create_by        NUMBER(11) not null,
  create_date      DATE not null,
  update_by        NUMBER(11) not null,
  update_date      DATE not null,
  mark_for_delete  NUMBER(1),
  opt_counter      NUMBER(6),
  delete_date      DATE
);
create unique index BAS_PARAMETER_NAME_EN on BAS_PARAMETER (NAME_EN);
alter table BAS_PARAMETER
  add constraint BAS_PARAMETER_PRIMARYKEY primary key (BAS_PARAMETER_ID)
  using index ;

create table BAS_POSITION
(
  bas_position_id        NUMBER(11) not null,
  bas_parent_position_id NUMBER(11),
  bas_department_id      NUMBER(11) not null,
  name_en                VARCHAR2(128) not null,
  name_zh                VARCHAR2(128) not null,
  description            VARCHAR2(512),
  create_by              NUMBER(11) not null,
  create_date            DATE not null,
  update_by              NUMBER(11) not null,
  update_date            DATE not null,
  mark_for_delete        NUMBER(1),
  opt_counter            NUMBER(6),
  delete_date            DATE
);
create unique index BAS_POSITION_NAME_ZH on BAS_POSITION (NAME_ZH);
alter table BAS_POSITION   add constraint BAS_POSITION_PRIMARYKEY primary key (BAS_POSITION_ID);

create table BAS_REMINDER_BODY
(
  bas_reminder_body_id NUMBER(11) not null,
  title_key            VARCHAR2(100),
  content_key          VARCHAR2(100),
  related_url          VARCHAR2(255),
  importance_level     NUMBER(1),
  title_params         VARCHAR2(1024),
  content_params       VARCHAR2(1024),
  create_by            NUMBER(11) not null,
  create_date          DATE not null,
  update_by            NUMBER(11) not null,
  update_date          DATE not null,
  mark_for_delete      NUMBER(1),
  opt_counter          NUMBER(6),
  delete_date          DATE
);
alter table BAS_REMINDER_BODY  add constraint BAS_REMINDER_BODY_PRIMARYKEY primary key (BAS_REMINDER_BODY_ID);

create table BAS_REMINDER_READER
(
  bas_reminder_reader_id NUMBER(11) not null,
  bas_user_id            NUMBER(11),
  is_read                NUMBER(1),
  bas_reminder_body_id   NUMBER(11),
  create_by              NUMBER(11) not null,
  create_date            DATE not null,
  update_by              NUMBER(11) not null,
  update_date            DATE not null,
  mark_for_delete        NUMBER(1),
  opt_counter            NUMBER(6),
  delete_date            DATE
);
create index BAS_REMINDER_READER_BAS_REMIND on BAS_REMINDER_READER (BAS_REMINDER_BODY_ID);
alter table BAS_REMINDER_READER
  add constraint BAS_REMINDER_READER_PRIMARYKEY primary key (BAS_REMINDER_READER_ID);

create table BAS_RESOURCE
(
  bas_resource_id        NUMBER(11) not null,
  parent_bas_resource_id NUMBER(11),
  type_                   VARCHAR2(256),
  path                   VARCHAR2(1024),
  name_zh                VARCHAR2(256),
  name_en                VARCHAR2(256),
  description            VARCHAR2(256),
  seq_num                NUMBER(11),
  is_leaf                NUMBER(1),
  editable               NUMBER(1),
  target                 VARCHAR2(16),
  icon                   VARCHAR2(30),
  permission             VARCHAR2(128),
  create_by              NUMBER(11) not null,
  create_date            DATE not null,
  update_by              NUMBER(11) not null,
  update_date            DATE not null,
  mark_for_delete        NUMBER(1),
  opt_counter            NUMBER(6),
  delete_date            DATE
);
create index BAS_RESOURCE_PARENT_BAS_RESOUR on BAS_RESOURCE (PARENT_BAS_RESOURCE_ID);
alter table BAS_RESOURCE
  add constraint BAS_RESOURCE_PRIMARYKEY primary key (BAS_RESOURCE_ID)
  using index ;

create table BAS_ROLE
(
  bas_role_id     NUMBER(11) not null,
  name_zh         VARCHAR2(128) not null,
  name_en         VARCHAR2(128) not null,
  description     VARCHAR2(256),
  editable        NUMBER(1) not null,
  create_by       NUMBER(11) not null,
  create_date     DATE not null,
  update_by       NUMBER(11) not null,
  update_date     DATE not null,
  mark_for_delete NUMBER(1),
  opt_counter     NUMBER(6),
  delete_date     DATE
);
create unique index BAS_ROLE_NAME_EN on BAS_ROLE (NAME_EN);
create unique index BAS_ROLE_NAME_ZH on BAS_ROLE (NAME_ZH);
alter table BAS_ROLE
  add constraint BAS_ROLE_PRIMARYKEY primary key (BAS_ROLE_ID)
  using index ;

create table BAS_ROLE_RESOURCE
(
  bas_resource_id NUMBER(19) not null,
  bas_role_id     NUMBER(19) not null
);
alter table BAS_ROLE_RESOURCE
  add primary key (BAS_ROLE_ID, BAS_RESOURCE_ID)
  using index ;
create table BAS_TIPTEXT
(
  bas_tiptext_id  NUMBER(11) not null,
  tip_text_key    VARCHAR2(50),
  text_zh         VARCHAR2(400),
  text_en         VARCHAR2(400),
  create_by       NUMBER(11) not null,
  create_date     DATE not null,
  update_by       NUMBER(11) not null,
  update_date     DATE not null,
  mark_for_delete NUMBER(1),
  opt_counter     NUMBER(6),
  delete_date     DATE
);
create unique index BAS_TIPTEXT_TIP_TEXT_KEY on BAS_TIPTEXT (TIP_TEXT_KEY);
create table BAS_USER
(
  bas_user_id             NUMBER(11) not null,
  bas_department_id       NUMBER(11),
  bas_position_id         NUMBER(11),
  manager_user_id         NUMBER(11),
  username                VARCHAR2(30) not null,
  password                VARCHAR2(128) not null,
  password_hint           VARCHAR2(30),
  employee_no             VARCHAR2(30) not null,
  realname_zh             VARCHAR2(128),
  realname_en             VARCHAR2(128),
  gender                  NUMBER(1) not null,
  usertype                VARCHAR2(10),
  authtype                VARCHAR2(10),
  telephone               VARCHAR2(30),
  mobile                  VARCHAR2(30),
  email                   VARCHAR2(128),
  office_telephone        VARCHAR2(30),
  fax                     VARCHAR2(30),
  postcode                VARCHAR2(6),
  location                VARCHAR2(256),
  account_expired         NUMBER(1) not null,
  account_locked          NUMBER(1) not null,
  credential_expired      NUMBER(1) not null,
  credential_expired_date DATE not null,
  account_expired_date    DATE not null,
  editable                NUMBER(1) not null,
  description             VARCHAR2(256),
  image                   VARCHAR2(128),
  create_by               NUMBER(11) not null,
  create_date             DATE not null,
  update_by               NUMBER(11) not null,
  update_date             DATE not null,
  opt_counter             NUMBER(6),
  delete_date             DATE,
  mark_for_delete         NUMBER(1)
);
create index BAS_USER_BAS_DEPARTMENT_ID on BAS_USER (BAS_DEPARTMENT_ID);
create index BAS_USER_MANAGER_USER_ID on BAS_USER (MANAGER_USER_ID);
create unique index BAS_USER_USERNAME on BAS_USER (USERNAME);
alter table BAS_USER
  add constraint BAS_USER_PRIMARYKEY primary key (BAS_USER_ID, ACCOUNT_LOCKED)
  using index ;
create table BAS_USER_GROUP
(
  bas_group_id NUMBER(19) not null,
  bas_user_id  NUMBER(19) not null
);
alter table BAS_USER_GROUP
  add primary key (BAS_USER_ID, BAS_GROUP_ID)
  using index ;

create table BAS_USER_ROLE
(
  bas_role_id NUMBER(19) not null,
  bas_user_id NUMBER(19) not null
);
alter table BAS_USER_ROLE add primary key (BAS_USER_ID, BAS_ROLE_ID) using index ;


alter table BAS_APP_PROPERTIES disable all triggers;

alter table BAS_CODE_LIST disable all triggers;

alter table BAS_CODE_TYPE disable all triggers;

alter table BAS_COLUMN disable all triggers;
alter table BAS_DATAGRID disable all triggers;

alter table BAS_DEMO disable all triggers;

alter table BAS_DEPARTMENT disable all triggers;

alter table BAS_GROUP disable all triggers;

alter table BAS_GROUP_ROLE disable all triggers;
alter table BAS_ICON disable all triggers;
alter table BAS_LOGIN_LOG disable all triggers;
alter table BAS_PARAMETER disable all triggers;
alter table BAS_POSITION disable all triggers;
alter table BAS_REMINDER_BODY disable all triggers;
alter table BAS_REMINDER_READER disable all triggers;

alter table BAS_RESOURCE disable all triggers;

alter table BAS_ROLE disable all triggers;

alter table BAS_ROLE_RESOURCE disable all triggers;

alter table BAS_TIPTEXT disable all triggers;

alter table BAS_USER disable all triggers;

alter table BAS_USER_GROUP disable all triggers;

alter table BAS_USER_ROLE disable all triggers;

alter table BAS_DEMO disable constraint FK_MGIHAFSPYQV9K6B08L0JKFGEU;

insert into BAS_APP_PROPERTIES (app_properties_id, app_id, key_, value_, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date, description)
values (1, 'lion', 'hibernate.cache.provider_class', 'net.sf.ehcache.hibernate.SingletonEhCacheProvider', null, to_date('13-03-2015 02:35:39', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('13-03-2015 02:35:39', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null);
insert into BAS_APP_PROPERTIES (app_properties_id, app_id, key_, value_, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date, description)
values (2, 'lion', 'hibernate.cache.region.factory_class', 'net.sf.ehcache.hibernate.EhCacheRegionFactory', null, to_date('13-03-2015 02:35:39', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('13-03-2015 02:35:39', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null);
insert into BAS_APP_PROPERTIES (app_properties_id, app_id, key_, value_, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date, description)
values (3, 'lion', 'hibernate.cache.use_minimal_puts', 'true', null, to_date('13-03-2015 02:35:39', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('13-03-2015 02:35:39', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null);
insert into BAS_APP_PROPERTIES (app_properties_id, app_id, key_, value_, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date, description)
values (4, 'lion', 'hibernate.cache.use_query_cache', 'false', null, to_date('13-03-2015 02:35:39', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('13-03-2015 02:35:39', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null);
insert into BAS_APP_PROPERTIES (app_properties_id, app_id, key_, value_, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date, description)
values (5, 'lion', 'hibernate.cache.use_second_level_cache', 'false', null, to_date('13-03-2015 02:35:39', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('13-03-2015 02:35:39', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null);
insert into BAS_APP_PROPERTIES (app_properties_id, app_id, key_, value_, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date, description)
values (6, 'lion', 'hibernate.dialect', 'org.hibernate.dialect.OracleDialect', null, to_date('13-03-2015 02:35:39', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('13-03-2015 02:35:39', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null);
insert into BAS_APP_PROPERTIES (app_properties_id, app_id, key_, value_, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date, description)
values (7, 'lion', 'hibernate.format_sql', 'true', null, to_date('13-03-2015 02:35:39', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('13-03-2015 02:35:39', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null);
insert into BAS_APP_PROPERTIES (app_properties_id, app_id, key_, value_, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date, description)
values (8, 'lion', 'hibernate.generate_statistics', 'true', null, to_date('13-03-2015 02:35:39', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('13-03-2015 02:35:39', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null);
insert into BAS_APP_PROPERTIES (app_properties_id, app_id, key_, value_, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date, description)
values (9, 'lion', 'hibernate.hbm2ddl.auto', 'update', null, to_date('13-03-2015 02:35:39', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('13-03-2015 02:35:39', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null);
insert into BAS_APP_PROPERTIES (app_properties_id, app_id, key_, value_, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date, description)
values (10, 'lion', 'hibernate.jdbc.batch_size', '50', null, to_date('13-03-2015 02:35:39', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('13-03-2015 02:35:39', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null);
insert into BAS_APP_PROPERTIES (app_properties_id, app_id, key_, value_, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date, description)
values (11, 'lion', 'hibernate.jdbc.fetch_size', '100', null, to_date('13-03-2015 02:35:39', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('13-03-2015 02:35:39', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null);
insert into BAS_APP_PROPERTIES (app_properties_id, app_id, key_, value_, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date, description)
values (12, 'lion', 'hibernate.max_fetch_depth', '3', null, to_date('13-03-2015 02:35:39', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('13-03-2015 02:35:39', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null);
insert into BAS_APP_PROPERTIES (app_properties_id, app_id, key_, value_, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date, description)
values (13, 'lion', 'hibernate.order-updates', 'true', null, to_date('13-03-2015 02:35:39', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('13-03-2015 02:35:39', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null);
insert into BAS_APP_PROPERTIES (app_properties_id, app_id, key_, value_, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date, description)
values (14, 'lion', 'hibernate.show_sql', 'true', null, to_date('13-03-2015 02:35:40', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('13-03-2015 02:35:40', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null);
insert into BAS_APP_PROPERTIES (app_properties_id, app_id, key_, value_, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date, description)
values (15, 'lion', 'hibernate.use_sql_comments', 'true', null, to_date('13-03-2015 02:35:40', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('13-03-2015 02:35:40', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null);
insert into BAS_APP_PROPERTIES (app_properties_id, app_id, key_, value_, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date, description)
values (16, 'lion', 'database.prefix', 'oracle', null, to_date('13-03-2015 02:35:40', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('13-03-2015 02:35:40', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null);
insert into BAS_APP_PROPERTIES (app_properties_id, app_id, key_, value_, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date, description)
values (17, 'lion', 'excel.temp.path', 'D:/app/excel/temp/', null, to_date('13-03-2015 02:35:40', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('13-03-2015 02:35:40', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null);
insert into BAS_APP_PROPERTIES (app_properties_id, app_id, key_, value_, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date, description)
values (18, 'lion', 'super.username', 'wanglijun', null, to_date('13-03-2015 02:35:40', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('13-03-2015 02:35:40', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null);
commit;

insert into BAS_CODE_LIST (bas_code_list_id, bas_code_type_id, code_value, name_zh, name_en, sort_no, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date, editable, selected)
values (1, 19, 'Users', '用户', 'Users', 2, 1, to_date('19-03-2013 17:15:16', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('08-04-2014 10:28:59', 'dd-mm-yyyy hh24:mi:ss'), 0, 3, null, 1, 0);
insert into BAS_CODE_LIST (bas_code_list_id, bas_code_type_id, code_value, name_zh, name_en, sort_no, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date, editable, selected)
values (2, 19, 'Files', '文件类', 'Files', 1, 1, to_date('19-03-2013 17:19:03', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('08-04-2014 10:28:53', 'dd-mm-yyyy hh24:mi:ss'), 0, 2, null, 1, 0);
insert into BAS_CODE_LIST (bas_code_list_id, bas_code_type_id, code_value, name_zh, name_en, sort_no, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date, editable, selected)
values (3, 19, 'systems', '系统类', 'systems', 3, 1, to_date('19-03-2013 20:42:18', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('08-04-2014 10:55:56', 'dd-mm-yyyy hh24:mi:ss'), 0, 9, null, 1, 0);
insert into BAS_CODE_LIST (bas_code_list_id, bas_code_type_id, code_value, name_zh, name_en, sort_no, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date, editable, selected)
values (11, 21, 'system', '系统类型', 'system', 1, 1, to_date('20-03-2013 18:03:39', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('03-03-2015 23:39:20', 'dd-mm-yyyy hh24:mi:ss'), 0, 7, null, 1, 0);
insert into BAS_CODE_LIST (bas_code_list_id, bas_code_type_id, code_value, name_zh, name_en, sort_no, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date, editable, selected)
values (12, 21, 'Products', '产品管理', 'Products', 2, 1, to_date('20-03-2013 18:04:45', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('20-03-2013 18:04:45', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null, 1, 0);
insert into BAS_CODE_LIST (bas_code_list_id, bas_code_type_id, code_value, name_zh, name_en, sort_no, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date, editable, selected)
values (13, 21, 'Customers', '客户管理', 'Customers', 3, 1, to_date('20-03-2013 18:06:49', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('24-03-2013 21:43:12', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null, 0, 0);
insert into BAS_CODE_LIST (bas_code_list_id, bas_code_type_id, code_value, name_zh, name_en, sort_no, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date, editable, selected)
values (14, 21, 'ReportsCenter', '报表中心', 'Reports Center', 4, 1, to_date('20-03-2013 18:07:36', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('12-02-2015 19:38:10', 'dd-mm-yyyy hh24:mi:ss'), 0, 4, null, 1, 0);
insert into BAS_CODE_LIST (bas_code_list_id, bas_code_type_id, code_value, name_zh, name_en, sort_no, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date, editable, selected)
values (15, 32, '_blank', '_blank', '_blank', 1, 1, to_date('24-03-2013 18:03:14', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('29-03-2013 23:17:44', 'dd-mm-yyyy hh24:mi:ss'), 0, 2, null, 1, 0);
insert into BAS_CODE_LIST (bas_code_list_id, bas_code_type_id, code_value, name_zh, name_en, sort_no, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date, editable, selected)
values (17, 32, '_self', '_self', '_self', 2, 1, to_date('24-03-2013 18:22:46', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('24-03-2013 18:22:46', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null, 1, 1);
insert into BAS_CODE_LIST (bas_code_list_id, bas_code_type_id, code_value, name_zh, name_en, sort_no, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date, editable, selected)
values (18, 32, '_parent', '_parent', '_parent', 3, 1, to_date('24-03-2013 18:29:43', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('24-03-2013 18:29:43', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null, 1, 0);
insert into BAS_CODE_LIST (bas_code_list_id, bas_code_type_id, code_value, name_zh, name_en, sort_no, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date, editable, selected)
values (19, 21, 'CustomerCenter', '客户中心', 'CustomerCenter', 2, 1, to_date('24-03-2013 18:53:21', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('24-03-2013 18:53:21', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null, 1, 0);
insert into BAS_CODE_LIST (bas_code_list_id, bas_code_type_id, code_value, name_zh, name_en, sort_no, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date, editable, selected)
values (21, 33, 'application', '应用层', 'application', 1, 1, to_date('24-03-2013 21:51:33', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('24-03-2013 22:12:27', 'dd-mm-yyyy hh24:mi:ss'), 0, 2, null, 1, 0);
insert into BAS_CODE_LIST (bas_code_list_id, bas_code_type_id, code_value, name_zh, name_en, sort_no, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date, editable, selected)
values (22, 33, 'module', '模块', 'module', 2, 1, to_date('24-03-2013 22:06:47', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('08-04-2014 10:30:45', 'dd-mm-yyyy hh24:mi:ss'), 0, 2, null, 1, 0);
insert into BAS_CODE_LIST (bas_code_list_id, bas_code_type_id, code_value, name_zh, name_en, sort_no, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date, editable, selected)
values (23, 33, 'module_menu_category', '菜单项', 'module_menu_category', 3, 1, to_date('24-03-2013 22:12:07', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('08-04-2014 14:10:05', 'dd-mm-yyyy hh24:mi:ss'), 0, 2, null, 1, 0);
insert into BAS_CODE_LIST (bas_code_list_id, bas_code_type_id, code_value, name_zh, name_en, sort_no, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date, editable, selected)
values (24, 34, 'text', 'text', 'text', 0, 1, to_date('27-03-2013 22:47:22', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('29-03-2013 22:21:34', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null, 1, 0);
insert into BAS_CODE_LIST (bas_code_list_id, bas_code_type_id, code_value, name_zh, name_en, sort_no, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date, editable, selected)
values (25, 34, 'textarea', 'textarea', 'textarea', 1, 1, to_date('27-03-2013 22:47:58', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('27-03-2013 22:47:58', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null, 1, 0);
insert into BAS_CODE_LIST (bas_code_list_id, bas_code_type_id, code_value, name_zh, name_en, sort_no, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date, editable, selected)
values (26, 34, 'checkbox', 'checkbox', 'checkbox', 3, 1, to_date('27-03-2013 22:48:32', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('27-03-2013 22:48:32', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null, 1, 0);
insert into BAS_CODE_LIST (bas_code_list_id, bas_code_type_id, code_value, name_zh, name_en, sort_no, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date, editable, selected)
values (27, 34, 'numberbox', 'numberbox', 'numberbox', 2, 1, to_date('27-03-2013 22:50:02', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('27-03-2013 22:50:02', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null, 1, 0);
insert into BAS_CODE_LIST (bas_code_list_id, bas_code_type_id, code_value, name_zh, name_en, sort_no, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date, editable, selected)
values (28, 34, 'validatebox', 'validatebox', 'validatebox', 4, 1, to_date('27-03-2013 22:50:37', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('27-03-2013 22:50:37', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null, 1, 0);
insert into BAS_CODE_LIST (bas_code_list_id, bas_code_type_id, code_value, name_zh, name_en, sort_no, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date, editable, selected)
values (29, 34, 'datebox', 'datebox', 'datebox', 5, 1, to_date('27-03-2013 22:50:54', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('27-03-2013 22:50:54', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null, 1, 0);
insert into BAS_CODE_LIST (bas_code_list_id, bas_code_type_id, code_value, name_zh, name_en, sort_no, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date, editable, selected)
values (30, 34, 'combobox', 'combobox', 'combobox', 6, 1, to_date('27-03-2013 22:51:17', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('27-03-2013 22:51:17', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null, 1, 0);
insert into BAS_CODE_LIST (bas_code_list_id, bas_code_type_id, code_value, name_zh, name_en, sort_no, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date, editable, selected)
values (31, 34, 'combotree', 'combotree', 'combotree', 7, 1, to_date('27-03-2013 22:51:47', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('27-03-2013 22:51:47', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null, 1, 0);
insert into BAS_CODE_LIST (bas_code_list_id, bas_code_type_id, code_value, name_zh, name_en, sort_no, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date, editable, selected)
values (32, 35, 'top', 'top', 'top', 1, 1, to_date('28-03-2013 22:32:04', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('28-03-2013 22:32:04', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null, 1, 0);
insert into BAS_CODE_LIST (bas_code_list_id, bas_code_type_id, code_value, name_zh, name_en, sort_no, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date, editable, selected)
values (33, 35, 'bottom', 'bottom', 'bottom', 2, 1, to_date('28-03-2013 22:32:21', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('29-03-2013 23:19:34', 'dd-mm-yyyy hh24:mi:ss'), 0, 2, null, 1, 0);
insert into BAS_CODE_LIST (bas_code_list_id, bas_code_type_id, code_value, name_zh, name_en, sort_no, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date, editable, selected)
values (34, 35, 'both', 'both', 'both', 3, 1, to_date('28-03-2013 22:32:43', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('29-03-2013 23:18:09', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null, 1, 0);
insert into BAS_CODE_LIST (bas_code_list_id, bas_code_type_id, code_value, name_zh, name_en, sort_no, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date, editable, selected)
values (35, 36, '[10,20,30,40,50]', '[10,20,30,40,50]', '[10,20,30,40,50]', 1, 1, to_date('28-03-2013 22:34:14', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('28-03-2013 22:34:14', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null, 1, 0);
insert into BAS_CODE_LIST (bas_code_list_id, bas_code_type_id, code_value, name_zh, name_en, sort_no, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date, editable, selected)
values (36, 36, '[5,10,15,20,25,30,40,50]', '[5,10,15,20,25,30,40,50]', '[5,10,15,20,25,30,40,50]', 2, 1, to_date('28-03-2013 22:35:28', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('28-03-2013 22:35:28', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null, 1, 0);
insert into BAS_CODE_LIST (bas_code_list_id, bas_code_type_id, code_value, name_zh, name_en, sort_no, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date, editable, selected)
values (37, 36, '[10,15,20,25,30,40,50,100]', '[10,15,20,25,30,40,50,100]', '[10,15,20,25,30,40,50,100]', 0, 1, to_date('28-03-2013 22:36:24', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('29-03-2013 22:28:31', 'dd-mm-yyyy hh24:mi:ss'), 0, 3, null, 1, 0);
insert into BAS_CODE_LIST (bas_code_list_id, bas_code_type_id, code_value, name_zh, name_en, sort_no, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date, editable, selected)
values (40, 33, 'module_menu_category_item', '菜单子项', 'module_menu_category_item', 4, 1, to_date('08-04-2014 14:09:11', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('08-04-2014 14:10:11', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null, 1, 0);
insert into BAS_CODE_LIST (bas_code_list_id, bas_code_type_id, code_value, name_zh, name_en, sort_no, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date, editable, selected)
values (44, 37, 'users_datagrid', '用户管理', 'users_datagrid', 0, 1, to_date('08-04-2014 23:09:47', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('08-04-2014 23:11:08', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null, 1, 0);
insert into BAS_CODE_LIST (bas_code_list_id, bas_code_type_id, code_value, name_zh, name_en, sort_no, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date, editable, selected)
values (45, 37, 'code_datagrid', '编码管理', 'code_datagrid', 1, 1, to_date('08-04-2014 23:10:22', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('08-04-2014 23:11:03', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null, 1, 0);
insert into BAS_CODE_LIST (bas_code_list_id, bas_code_type_id, code_value, name_zh, name_en, sort_no, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date, editable, selected)
values (46, 37, 'system_datagrid', '系统管理', 'system_datagrid', 2, 1, to_date('08-04-2014 23:11:52', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('08-04-2014 23:11:52', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null, 1, 0);
insert into BAS_CODE_LIST (bas_code_list_id, bas_code_type_id, code_value, name_zh, name_en, sort_no, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date, editable, selected)
values (47, 37, 'datagrid_datagrid ', 'DataGrid管理', 'datagrid_datagrid ', 3, 1, to_date('08-04-2014 23:12:43', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('08-04-2014 23:26:15', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null, 1, 0);
insert into BAS_CODE_LIST (bas_code_list_id, bas_code_type_id, code_value, name_zh, name_en, sort_no, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date, editable, selected)
values (48, 37, 'SYSTEM_MINITOR', '系统监控管理', 'SYSTEM_MINITOR', 4, 1, to_date('08-04-2014 23:13:18', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('08-04-2014 23:13:18', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null, 1, 0);
insert into BAS_CODE_LIST (bas_code_list_id, bas_code_type_id, code_value, name_zh, name_en, sort_no, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date, editable, selected)
values (49, 19, 'sysInterface', '接口管理', 'sysInterface', 3, 1, to_date('01-06-2014 21:48:36', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-06-2014 21:48:36', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null, 1, 0);
insert into BAS_CODE_LIST (bas_code_list_id, bas_code_type_id, code_value, name_zh, name_en, sort_no, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date, editable, selected)
values (52, 33, 'module_menu_category_item_button', '按钮项', 'item_button', 5, 1, to_date('04-03-2015 21:11:44', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('04-03-2015 21:11:44', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null, 1, 0);
commit;

insert into BAS_CODE_TYPE (bas_code_type_id, code_type, name_zh, name_en, editable, code_len_limit, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (16, 'Customers', '客户管理', 'CustomerManage', null, 1000, 1, to_date('19-03-2013 09:37:06', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('06-03-2015 11:17:59', 'dd-mm-yyyy hh24:mi:ss'), 0, 6, null);
insert into BAS_CODE_TYPE (bas_code_type_id, code_type, name_zh, name_en, editable, code_len_limit, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (19, 'system', '系统参数', 'SystemParamter', 1, 999999999, 1, to_date('19-03-2013 09:41:17', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('24-03-2014 17:04:53', 'dd-mm-yyyy hh24:mi:ss'), 0, 52, null);
insert into BAS_CODE_TYPE (bas_code_type_id, code_type, name_zh, name_en, editable, code_len_limit, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (21, 'Customers', '通用编码类型', 'codeTypes', 1, 10000, 1, to_date('19-03-2013 10:42:58', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('13-04-2013 21:55:52', 'dd-mm-yyyy hh24:mi:ss'), 0, 4, null);
insert into BAS_CODE_TYPE (bas_code_type_id, code_type, name_zh, name_en, editable, code_len_limit, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (23, 'system', '参数类型', 'paramterType', 1, 11111, 1, to_date('19-03-2013 13:24:26', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('24-03-2013 21:38:51', 'dd-mm-yyyy hh24:mi:ss'), 0, 3, null);
insert into BAS_CODE_TYPE (bas_code_type_id, code_type, name_zh, name_en, editable, code_len_limit, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (24, 'Products', '产品管理', 'ProductsManage', 1, 1000, 1, to_date('19-03-2013 14:33:58', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('24-03-2013 21:38:27', 'dd-mm-yyyy hh24:mi:ss'), 0, 2, null);
insert into BAS_CODE_TYPE (bas_code_type_id, code_type, name_zh, name_en, editable, code_len_limit, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (28, 'ReportsCenter', '报表中心', 'ReportCenter', null, 11113, 1, to_date('19-03-2013 14:35:25', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('09-03-2015 16:38:20', 'dd-mm-yyyy hh24:mi:ss'), 0, 4, null);
insert into BAS_CODE_TYPE (bas_code_type_id, code_type, name_zh, name_en, editable, code_len_limit, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (32, 'system', 'Target', 'Target', 1, 100, 1, to_date('24-03-2013 16:53:50', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('24-03-2013 17:17:07', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_CODE_TYPE (bas_code_type_id, code_type, name_zh, name_en, editable, code_len_limit, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (33, 'system', '资源类型', 'ResourceType', null, 1000, 1, to_date('24-03-2013 21:46:03', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('05-03-2015 23:43:47', 'dd-mm-yyyy hh24:mi:ss'), 0, 10, null);
insert into BAS_CODE_TYPE (bas_code_type_id, code_type, name_zh, name_en, editable, code_len_limit, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (34, 'system', 'DataGrid_Editors', 'DataGrid_Editors', 1, 100, 1, to_date('27-03-2013 22:45:16', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('27-03-2013 22:45:16', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_CODE_TYPE (bas_code_type_id, code_type, name_zh, name_en, editable, code_len_limit, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (35, 'system', 'PagePosition', 'PagePosition', 1, 1000, 1, to_date('28-03-2013 22:31:02', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('28-03-2013 22:31:02', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_CODE_TYPE (bas_code_type_id, code_type, name_zh, name_en, editable, code_len_limit, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (36, 'system', 'PageList', 'PageList', 1, 1000, 1, to_date('28-03-2013 22:33:45', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('28-03-2013 22:33:45', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_CODE_TYPE (bas_code_type_id, code_type, name_zh, name_en, editable, code_len_limit, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (37, 'system', 'Datagrid类型', 'datagrid_type', null, 1000, 1, to_date('08-04-2014 23:08:45', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('06-03-2015 09:39:36', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
commit;
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (159, 11, 0, 'id', 'id', 30, 0, 0, 1, 'asc', 'left', 'left', 0, 1, null, null, null, null, 1, to_date('03-04-2014 12:17:05', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('03-04-2014 12:57:33', 'dd-mm-yyyy hh24:mi:ss'), 0, 4, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (160, 11, 1, 'username', '用户名', 80, 0, 0, 1, 'asc', 'left', 'left', 0, 0, null, null, null, 'text', 1, to_date('03-04-2014 12:48:38', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('07-02-2015 15:04:07', 'dd-mm-yyyy hh24:mi:ss'), 0, 7, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (162, 11, 4, 'realnameZh', '真实姓名(中文)', 80, 0, 0, 1, 'asc', 'left', 'left', 0, 0, null, null, null, 'text', 1, to_date('03-04-2014 12:58:51', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('03-04-2014 13:04:44', 'dd-mm-yyyy hh24:mi:ss'), 0, 2, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (163, 11, 5, 'realnameEn', '真实姓名(英文)', 80, 0, 0, 1, 'asc', 'left', 'left', 0, 0, null, null, null, 'text', 1, to_date('03-04-2014 12:59:22', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('03-04-2014 13:07:59', 'dd-mm-yyyy hh24:mi:ss'), 0, 2, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (164, 11, 6, 'employeeCode', '员工号', 80, 0, 0, 1, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('03-04-2014 13:00:56', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('03-04-2014 13:02:26', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (165, 11, 10, 'accountLocked', '账户锁定状态', 90, 0, 0, 1, 'asc', 'left', 'center', 0, 0, 'formatterAccountLocked', null, null, null, 1, to_date('03-04-2014 13:01:50', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('07-02-2015 15:00:27', 'dd-mm-yyyy hh24:mi:ss'), 0, 6, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (166, 11, 8, 'telephone', '联系电话', 80, 0, 0, 1, 'asc', 'left', 'left', 0, 0, null, null, null, 'text', 1, to_date('03-04-2014 13:09:33', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('03-04-2014 13:09:33', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (167, 11, 9, 'officePhone', '办公室', 80, 0, 0, 1, 'asc', 'left', 'left', 1, 0, null, null, null, null, 1, to_date('03-04-2014 13:10:10', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('07-02-2015 14:58:51', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (168, 11, 10, 'accountExpired', '账户状态', 80, 0, 0, 1, 'asc', 'left', 'left', 0, 0, 'formatterEidtable', null, null, null, 1, to_date('03-04-2014 13:11:20', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('07-02-2015 14:31:51', 'dd-mm-yyyy hh24:mi:ss'), 0, 2, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (170, 11, 11, 'accountExpiredDate', '账户有效日期', 100, 0, 0, 1, 'asc', 'left', 'left', 0, 0, 'formatterDate', null, null, 'datebox', 1, to_date('03-04-2014 14:00:03', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('07-02-2015 15:59:59', 'dd-mm-yyyy hh24:mi:ss'), 0, 2, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (173, 30, 0, 'id', 'id', 30, 0, 0, 1, 'asc', 'left', 'left', 0, 1, null, null, null, null, 1, to_date('04-04-2014 01:05:31', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('04-04-2014 01:05:31', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (174, 30, 1, 'nameEn', 'nameEn', 300, 0, 0, 1, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('04-04-2014 01:05:51', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('04-04-2014 01:05:51', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (175, 11, 12, 'department', '所属部门', 100, 0, 0, 1, 'asc', 'left', 'left', 0, 0, 'formatterShowDepartment', null, null, null, 1, to_date('04-04-2014 14:57:13', 'dd-mm-yyyy hh24:mi:ss'), 4, to_date('07-05-2014 16:23:05', 'dd-mm-yyyy hh24:mi:ss'), 0, 2, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (176, 33, 1, 'name', '缓存名称', 100, 0, 0, 0, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('21-04-2014 15:29:37', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('22-04-2014 10:30:19', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (177, 33, 2, 'size', '缓存大小', 100, 0, 0, 0, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('21-04-2014 15:30:36', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('22-04-2014 10:30:36', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (178, 33, 3, 'memoryStoreSize', '内存缓存数量', 100, 0, 0, 0, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('21-04-2014 15:31:01', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('22-04-2014 10:30:40', 'dd-mm-yyyy hh24:mi:ss'), 0, 2, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (179, 33, 4, 'diskStoreSize', '磁盘数量', 100, 0, 0, 1, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('21-04-2014 15:31:30', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('21-04-2014 15:31:30', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (180, 33, 5, 'cacheHits', '命中次数', 100, 0, 0, 0, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('21-04-2014 15:32:02', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('22-04-2014 10:30:45', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (181, 33, 6, 'inMemoryHits', '内存命中次数', 100, 0, 0, 0, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('21-04-2014 15:32:26', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('22-04-2014 10:30:49', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (182, 33, 7, 'onDiskHits', '磁盘命中次数', 100, 0, 0, 0, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('21-04-2014 15:32:45', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('22-04-2014 10:30:55', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (183, 33, 8, 'memoryStoreEvictionPolicy', '缓存策略', 100, 0, 0, 0, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('21-04-2014 15:33:04', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('22-04-2014 10:30:59', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (184, 27, 11, 'resources', '资源', 15, 0, 0, 1, 'ASC', 'LIFE', 'LIFE', 1, 0, null, null, null, null, 1, to_date('06-02-2015 15:55:59', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('06-02-2015 15:56:04', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (1, 6, 1, 'id', 'name', 10, 0, 0, 1, 'asc', 'left', 'center', 0, 1, null, null, null, null, 1, to_date('27-03-2013 23:35:50', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('30-03-2013 12:45:36', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (2, 6, 2, 'showOrder', 'ShowOrder', 20, 0, 0, 1, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('27-03-2013 23:42:09', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('27-03-2013 23:42:09', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (3, 6, 3, 'field', 'Field', 30, 0, 0, 1, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('27-03-2013 23:44:50', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-04-2013 23:11:48', 'dd-mm-yyyy hh24:mi:ss'), 0, 3, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (4, 6, 5, 'name', 'Name', 150, 0, 0, 1, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('01-04-2013 22:17:36', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('02-04-2013 11:01:13', 'dd-mm-yyyy hh24:mi:ss'), 0, 3, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (5, 6, 6, 'width', 'Width', 100, 0, 0, 1, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('01-04-2013 22:18:54', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-04-2013 23:08:30', 'dd-mm-yyyy hh24:mi:ss'), 0, 4, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (7, 6, 4, 'order', 'Order', 20, 0, 0, 1, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('01-04-2013 22:26:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-04-2013 23:06:45', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (8, 6, 7, 'align', 'Align', 100, 0, 0, 1, 'asc', 'left', 'center', 0, 0, null, null, null, null, 1, to_date('01-04-2013 23:10:22', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-04-2013 23:14:31', 'dd-mm-yyyy hh24:mi:ss'), 0, 5, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (9, 6, 8, 'sortable', 'Sortable', 100, 0, 0, 1, 'asc', 'left', 'center', 0, 0, null, null, null, null, 1, to_date('01-04-2013 23:15:12', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-04-2013 23:16:36', 'dd-mm-yyyy hh24:mi:ss'), 0, 2, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (10, 6, 9, 'headerAlign', 'HeaderAlign', 100, 0, 0, 1, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('01-04-2013 23:16:26', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-04-2013 23:17:05', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (11, 7, 0, 'id', '编辑', 8, 0, 0, 1, 'asc', 'center', 'center', 0, 0, 'formatterCheckBox', null, null, null, 1, to_date('02-04-2013 11:12:03', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('05-04-2013 20:42:44', 'dd-mm-yyyy hh24:mi:ss'), 0, 8, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (12, 7, 2, 'nameZh', '用户组名称(中文)', 80, 0, 0, 1, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('02-04-2013 11:12:39', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('02-04-2013 11:14:19', 'dd-mm-yyyy hh24:mi:ss'), 0, 2, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (13, 7, 1, 'nameEn', '用户组名称(英文)', 80, 0, 0, 1, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('02-04-2013 11:14:59', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('02-04-2013 11:14:59', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (14, 8, 0, 'id', '状态', 8, 0, 0, 1, 'asc', 'left', 'center', 0, 0, 'formatterCheckBox', null, null, null, 1, to_date('02-04-2013 13:25:58', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('07-04-2013 17:28:50', 'dd-mm-yyyy hh24:mi:ss'), 0, 5, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (15, 8, 1, 'nameEn', '角色名称(英文)', 80, 0, 0, 1, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('02-04-2013 13:26:58', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('05-04-2013 18:24:17', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (16, 8, 2, 'nameZh', '角色名称(中文)', 80, 0, 0, 1, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('02-04-2013 13:27:42', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('02-04-2013 13:27:42', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (17, 9, 0, 'id', ' ', 30, 0, 0, 1, 'asc', 'left', 'center', 0, 1, null, null, null, null, 1, to_date('02-04-2013 14:42:24', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('02-04-2013 19:09:25', 'dd-mm-yyyy hh24:mi:ss'), 0, 10, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (18, 9, 1, 'nameEn', '用户组名称(英文)', 80, 0, 0, 1, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('02-04-2013 14:43:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('02-04-2013 19:21:21', 'dd-mm-yyyy hh24:mi:ss'), 0, 6, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (19, 9, 2, 'nameZh', '用户组名称(中文)', 80, 0, 0, 1, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('02-04-2013 14:44:53', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('02-04-2013 14:46:27', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (20, 10, 0, 'id', ' ', 30, 0, 0, 1, 'asc', 'left', 'left', 0, 1, null, null, null, null, 1, to_date('05-04-2013 17:50:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('05-04-2013 17:50:00', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (21, 10, 1, 'tableId', 'TableId', 100, 0, 0, 1, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('05-04-2013 17:54:38', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('05-04-2013 17:54:38', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (22, 10, 2, 'title', 'Title', 100, 0, 0, 1, 'asc', 'left', 'left', 1, 0, null, null, null, null, 1, to_date('05-04-2013 17:55:10', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('05-04-2013 19:01:00', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (23, 10, 5, 'pagination', 'Pagination', 70, 0, 0, 1, 'asc', 'center', 'center', 0, 0, null, null, null, null, 1, to_date('05-04-2013 17:55:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('05-04-2013 17:57:16', 'dd-mm-yyyy hh24:mi:ss'), 0, 2, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (24, 10, 4, 'frozen', 'Frozen', 50, 0, 0, 1, 'asc', 'left', 'center', 0, 0, null, null, null, null, 1, to_date('05-04-2013 17:56:27', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('05-04-2013 17:56:27', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (26, 10, 3, 'fit', ' Fit', 50, 0, 0, 1, 'asc', 'left', 'center', 0, 0, null, null, null, null, 1, to_date('05-04-2013 17:59:45', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('05-04-2013 17:59:45', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (27, 10, 6, 'fitColumns', 'FitColumns', 60, 0, 0, 1, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('05-04-2013 18:00:32', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('05-04-2013 18:03:07', 'dd-mm-yyyy hh24:mi:ss'), 0, 2, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (28, 10, 7, 'striped', 'Striped', 60, 0, 0, 1, 'asc', 'left', 'center', 0, 0, null, null, null, null, 1, to_date('05-04-2013 18:02:58', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('05-04-2013 18:02:58', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (29, 10, 8, 'method', 'Method', 80, 0, 0, 1, 'asc', 'left', 'left', 1, 0, null, null, null, null, 1, to_date('05-04-2013 18:03:37', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('09-04-2014 00:06:48', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (30, 10, 9, 'nowrap', 'Nowrap', 50, 0, 0, 1, 'asc', 'left', 'center', 0, 0, null, null, null, null, 1, to_date('05-04-2013 18:05:14', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('05-04-2013 18:05:14', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (31, 10, 10, 'url', 'URL', 120, 0, 0, 1, 'asc', 'left', 'left', 1, 0, null, null, null, null, 1, to_date('05-04-2013 18:05:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('09-04-2014 00:04:54', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (32, 10, 11, 'rownumbers', 'Rownumbers', 50, 0, 0, 1, 'asc', 'left', 'center', 0, 0, null, null, null, null, 1, to_date('05-04-2013 18:06:30', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('05-04-2013 18:06:30', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (33, 10, 12, 'singleSelect', '单行选择', 50, 0, 0, 1, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('05-04-2013 18:07:08', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('05-04-2013 18:07:08', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (34, 10, 13, 'checkOnSelect', 'CheckOnSelect', 80, 0, 0, 1, 'asc', 'left', 'center', 0, 0, null, null, null, null, 1, to_date('05-04-2013 18:07:41', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('05-04-2013 18:07:41', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (35, 10, 14, 'selectOnCheck', 'SelectOnCheck', 80, 0, 0, 1, 'asc', 'left', 'center', 0, 0, null, null, null, null, 1, to_date('05-04-2013 18:08:14', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('05-04-2013 18:08:14', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (36, 10, 15, 'pagePosition', 'PagePosition', 100, 0, 0, 1, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('05-04-2013 18:08:42', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('05-04-2013 18:08:42', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (37, 10, 16, 'pageNumber', 'PageNumber', 100, 0, 0, 1, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('05-04-2013 18:09:25', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('05-04-2013 18:12:19', 'dd-mm-yyyy hh24:mi:ss'), 0, 3, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (38, 10, 17, 'pageSize', 'PageSize', 100, 0, 0, 1, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('05-04-2013 18:09:59', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('05-04-2013 18:09:59', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (39, 10, 18, 'pageList', 'PageList', 150, 0, 0, 1, 'asc', 'left', 'left', 1, 0, null, null, null, null, 1, to_date('05-04-2013 18:10:33', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('09-04-2014 00:05:54', 'dd-mm-yyyy hh24:mi:ss'), 0, 2, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (40, 10, 20, 'sortOrder', 'SortOrder', 80, 0, 0, 1, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('05-04-2013 18:13:49', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('05-04-2013 18:27:14', 'dd-mm-yyyy hh24:mi:ss'), 0, 2, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (41, 10, 21, 'remoteSort', 'RemoteSort', 80, 0, 0, 1, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('05-04-2013 18:14:09', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('05-04-2013 18:27:22', 'dd-mm-yyyy hh24:mi:ss'), 0, 3, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (42, 10, 19, 'sortName', 'SortName', 80, 0, 0, 1, 'asc', 'left', 'left', 1, 0, null, null, null, null, 1, to_date('05-04-2013 18:27:04', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('05-04-2013 19:00:26', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (43, 10, 22, 'showHeader', 'ShowHeader', 50, 0, 0, 1, 'asc', 'left', 'center', 0, 0, null, null, null, null, 1, to_date('05-04-2013 18:28:14', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('05-04-2013 18:28:14', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (44, 10, 23, 'showFooter', 'ShowFooter', 50, 0, 0, 1, 'asc', 'left', 'center', 1, 0, null, null, null, null, 1, to_date('05-04-2013 18:28:33', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('05-04-2013 19:00:15', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (45, 10, 24, 'data', 'Data', 100, 0, 0, 1, 'asc', 'left', 'left', 1, 0, null, null, null, null, 1, to_date('05-04-2013 18:29:50', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('05-04-2013 18:29:50', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (46, 10, 25, 'loadMsg', 'LoadMsg', 100, 0, 0, 1, 'asc', 'left', 'left', 1, 0, null, null, null, null, 1, to_date('05-04-2013 18:30:17', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('05-04-2013 18:30:17', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (47, 10, 26, 'scrollbarSize', 'ScrollbarSize', 100, 0, 0, 1, 'asc', 'left', 'left', 1, 0, null, null, null, null, 1, to_date('05-04-2013 18:30:44', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('05-04-2013 18:30:44', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (48, 10, 27, 'rowStyler', 'RowStyler', 100, 0, 0, 1, 'asc', 'left', 'left', 1, 0, null, null, null, null, 1, to_date('05-04-2013 18:31:07', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('05-04-2013 19:02:02', 'dd-mm-yyyy hh24:mi:ss'), 0, 2, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (49, 10, 28, 'loader', 'Loader', 50, 0, 0, 1, 'asc', 'left', 'left', 1, 0, null, null, null, null, 1, to_date('05-04-2013 18:31:45', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('05-04-2013 18:31:45', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (50, 10, 29, 'loadFilter', 'LoadFilter', 50, 0, 0, 1, 'asc', 'left', 'left', 1, 0, null, null, null, null, 1, to_date('05-04-2013 18:32:11', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('05-04-2013 18:32:11', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (51, 12, 1, 'id', ' ', 10, 0, 0, 1, 'asc', 'left', 'center', 0, 1, null, null, null, null, 1, to_date('05-04-2013 21:00:44', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('05-04-2013 21:00:44', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (52, 12, 2, 'nameEn', '角色名称(英文)', 100, 0, 0, 1, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('05-04-2013 21:01:24', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('05-04-2013 21:01:24', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (53, 12, 3, 'nameZh', '角色名称(中文)', 100, 0, 0, 1, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('05-04-2013 21:01:54', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('05-04-2013 21:01:54', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (56, 9, 3, 'users', 'users', 10, 0, 0, 1, 'asc', 'left', 'left', 1, 0, null, null, null, null, 1, to_date('08-04-2013 15:38:16', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('08-04-2013 15:38:16', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (57, 12, 4, 'users', 'users', 10, 0, 0, 1, 'asc', 'left', 'left', 1, 0, null, null, null, null, 1, to_date('08-04-2013 16:32:18', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('08-04-2013 16:32:18', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (58, 13, 1, 'id', 'id', 8, 0, 0, 1, 'asc', 'center', 'center', 0, 1, null, null, null, null, 1, to_date('09-04-2013 17:13:28', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('09-04-2013 17:13:28', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (59, 13, 3, 'nameZh', '用户组名称(中文)', 100, 0, 0, 1, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('09-04-2013 17:15:26', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('09-04-2013 17:19:08', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (60, 13, 2, 'nameEn', '用户组名称(英文)', 100, 0, 0, 1, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('09-04-2013 17:16:14', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('09-04-2013 17:16:14', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (61, 13, 4, 'description', '描述', 150, 0, 0, 1, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('09-04-2013 17:16:44', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('09-04-2013 17:16:44', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (62, 13, 5, 'editable', '是否可编辑', 60, 0, 0, 1, 'asc', 'center', 'center', 0, 0, 'formatterEidtable', null, null, null, 1, to_date('09-04-2013 17:17:18', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('09-04-2013 17:18:56', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (63, 13, 6, 'createdDate', '创建时间', 100, 0, 0, 1, 'asc', 'left', 'center', 0, 0, null, null, null, null, 1, to_date('09-04-2013 17:17:53', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('09-04-2013 17:17:53', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (64, 13, 7, 'updatedDate', ' 更新时间', 120, 0, 0, 1, 'asc', 'center', 'center', 0, 0, null, null, null, null, 1, to_date('09-04-2013 17:18:28', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('09-04-2013 17:18:28', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (65, 14, 1, 'id', '状态', 12, 0, 0, 1, 'asc', 'center', 'center', 0, 0, 'formatterCheckBox', null, null, null, 1, to_date('09-04-2013 20:28:25', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('09-04-2013 20:51:58', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (66, 14, 2, 'username', '用户名', 60, 0, 0, 1, 'asc', 'center', 'left', 0, 0, null, null, null, null, 1, to_date('09-04-2013 20:29:30', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('09-04-2013 20:51:50', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (67, 14, 3, 'realnameEn', '用户姓名(英文)', 80, 0, 0, 1, 'asc', 'center', 'left', 0, 0, null, null, null, null, 1, to_date('09-04-2013 20:30:46', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('09-04-2013 20:50:34', 'dd-mm-yyyy hh24:mi:ss'), 0, 2, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (68, 14, 4, 'realnameZh', '用户姓名(英文)', 80, 0, 0, 1, 'asc', 'center', 'left', 0, 0, null, null, null, null, 1, to_date('09-04-2013 20:31:50', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('09-04-2013 20:50:27', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (69, 16, 0, 'id', '状态', 12, 0, 0, 1, 'asc', 'center', 'center', 0, 0, 'formatterCheckBox', null, null, null, 1, to_date('09-04-2013 21:04:17', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('09-04-2013 21:04:17', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (70, 16, 1, 'nameEn', '角色名称(英文)', 100, 0, 0, 1, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('09-04-2013 21:05:19', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('09-04-2013 21:05:19', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (71, 16, 2, 'nameZh', '角色名称(中文)', 100, 0, 0, 1, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('09-04-2013 21:05:53', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('09-04-2013 21:05:53', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (72, 17, 0, 'id', 'id', 10, 0, 0, 1, 'asc', 'left', 'left', 0, 1, null, null, null, null, 1, to_date('09-04-2013 21:09:33', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('09-04-2013 21:31:26', 'dd-mm-yyyy hh24:mi:ss'), 0, 2, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (73, 17, 1, 'username', '用户名', 80, 0, 0, 1, 'asc', 'center', 'left', 0, 0, null, null, null, null, 1, to_date('09-04-2013 21:10:27', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('09-04-2013 21:31:36', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (74, 17, 2, 'realnameEn', '姓名(英文)', 80, 0, 0, 1, 'asc', 'center', 'left', 0, 0, null, null, null, null, 1, to_date('09-04-2013 21:11:30', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('09-04-2013 21:31:45', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (75, 17, 3, 'realnameZh', '姓名(中文)', 80, 0, 0, 1, 'asc', 'center', 'left', 0, 0, null, null, null, null, 1, to_date('09-04-2013 21:11:54', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('09-04-2013 21:32:07', 'dd-mm-yyyy hh24:mi:ss'), 0, 2, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (76, 18, 0, 'id', 'id', 10, 0, 0, 1, 'asc', 'left', 'left', 0, 1, null, null, null, null, 1, to_date('09-04-2013 21:13:37', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('09-04-2013 21:13:37', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (77, 18, 1, 'nameEn', '角色名称(英文)', 100, 0, 0, 1, 'asc', 'center', 'left', 0, 0, null, null, null, null, 1, to_date('09-04-2013 21:14:54', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('09-04-2013 21:14:54', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (78, 18, 2, 'nameZh', '角色名称(中文)', 100, 0, 0, 1, 'asc', 'center', 'left', 0, 0, null, null, null, null, 1, to_date('09-04-2013 21:15:25', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('09-04-2013 21:15:25', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (79, 17, 4, 'groups', '用户组', 10, 0, 0, 1, 'asc', 'left', 'center', 1, 0, null, null, null, null, 1, to_date('09-04-2013 21:39:59', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('09-04-2013 21:57:23', 'dd-mm-yyyy hh24:mi:ss'), 0, 2, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (80, 18, 3, 'groups', '用户组', 10, 0, 0, 1, 'asc', 'center', 'left', 1, 0, null, null, null, null, 1, to_date('09-04-2013 21:41:07', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('09-04-2013 21:53:11', 'dd-mm-yyyy hh24:mi:ss'), 0, 2, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (81, 19, 0, 'id', 'id', 10, 0, 0, 0, 'asc', 'center', 'center', 0, 1, null, null, null, null, 1, to_date('10-04-2013 20:39:16', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('10-04-2013 20:39:16', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (82, 19, 1, 'nameEn', '角色名称(英文)', 80, 0, 0, 1, 'asc', 'center', 'left', 0, 0, null, null, null, null, 1, to_date('10-04-2013 20:41:33', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('10-04-2013 20:41:33', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
commit;
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (83, 19, 2, 'nameZh', '角色名称(中文)', 80, 0, 0, 1, 'asc', 'center', 'left', 0, 0, null, null, null, null, 1, to_date('10-04-2013 20:42:34', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('10-04-2013 20:42:34', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (84, 19, 3, 'description', '角色描述', 120, 0, 0, 1, 'asc', 'center', 'left', 0, 0, null, null, null, null, 1, to_date('10-04-2013 20:43:15', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('10-04-2013 20:51:04', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (85, 19, 4, 'editable', '是否可编辑', 30, 0, 0, 1, 'asc', 'center', 'center', 0, 0, 'formatterEidtable', null, null, null, 1, to_date('10-04-2013 20:43:56', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('10-04-2013 20:51:35', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (86, 19, 5, 'createdDate', '创建时间', 60, 0, 0, 1, 'asc', 'center', 'center', 0, 0, null, null, null, null, 1, to_date('10-04-2013 20:44:30', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('10-04-2013 20:50:57', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (87, 19, 6, 'updatedDate', '更新时间', 60, 0, 0, 1, 'asc', 'center', 'center', 0, 0, null, null, null, null, 1, to_date('10-04-2013 20:45:01', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('10-04-2013 20:50:48', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (88, 20, 0, 'id', '状态', 12, 0, 0, 1, 'asc', 'center', 'center', 0, 0, 'formatterCheckBox', null, null, null, 1, to_date('10-04-2013 23:00:09', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('10-04-2013 23:34:30', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (89, 20, 1, 'username', '用户名', 80, 0, 0, 1, 'asc', 'center', 'left', 0, 0, null, null, null, null, 1, to_date('10-04-2013 23:01:07', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('10-04-2013 23:01:07', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (90, 20, 2, 'realnameEn', '姓名(英文)', 80, 0, 0, 1, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('10-04-2013 23:02:44', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('10-04-2013 23:02:44', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (91, 20, 3, 'realnameZh', '姓名(中文)', 80, 0, 0, 1, 'asc', 'left', 'center', 0, 0, null, null, null, null, 1, to_date('10-04-2013 23:03:16', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('10-04-2013 23:03:16', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (92, 21, 0, 'id', '状态', 12, 0, 0, 1, 'asc', 'center', 'center', 0, 0, 'formatterCheckBox', null, null, null, 1, to_date('10-04-2013 23:06:12', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('10-04-2013 23:34:39', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (93, 21, 1, 'nameEn', '用户组名称(英文)', 80, 0, 0, 1, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('10-04-2013 23:07:19', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('10-04-2013 23:07:19', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (94, 21, 2, 'nameZh', '用户组名称(中文) ', 80, 0, 0, 1, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('10-04-2013 23:07:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('10-04-2013 23:07:48', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (97, 22, 0, 'id', 'id', 10, 0, 0, 0, 'asc', 'center', 'center', 0, 1, null, null, null, null, 1, to_date('11-04-2013 15:21:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('11-04-2013 16:14:02', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (98, 22, 1, 'parentResourceId', '上一级ID', 80, 0, 0, 0, 'asc', 'center', 'center', 1, 0, null, null, null, null, 1, to_date('11-04-2013 15:21:53', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('11-04-2013 16:14:08', 'dd-mm-yyyy hh24:mi:ss'), 0, 3, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (99, 22, 3, 'nameEn', '资源名称(英文)', 80, 0, 0, 0, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('11-04-2013 15:22:45', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('11-04-2013 16:16:45', 'dd-mm-yyyy hh24:mi:ss'), 0, 3, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (100, 22, 2, 'nameZh', '资源名称(中文)', 80, 0, 0, 0, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('11-04-2013 15:23:53', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('11-04-2013 16:16:51', 'dd-mm-yyyy hh24:mi:ss'), 0, 4, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (102, 24, 0, 'id', 'id', 10, 0, 0, 1, 'asc', 'center', 'center', 0, 1, null, null, null, null, 1, to_date('11-04-2013 15:37:21', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('11-04-2013 15:37:21', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (103, 24, 1, 'username', '用户名', 80, 0, 0, 1, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('11-04-2013 15:37:58', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('11-04-2013 15:37:58', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (104, 24, 3, 'realnameZh', '姓名(中文)', 80, 0, 0, 1, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('11-04-2013 15:38:51', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('11-04-2013 15:38:51', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (105, 24, 2, 'realnameEn', '姓名(英文)', 80, 0, 0, 1, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('11-04-2013 15:39:39', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('11-04-2013 15:39:39', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (106, 23, 0, 'id', 'id', 10, 0, 0, 1, 'asc', 'left', 'left', 0, 1, null, null, null, null, 1, to_date('11-04-2013 15:40:11', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('11-04-2013 15:40:11', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (107, 23, 1, 'nameEn', '用户组名称(英文)', 80, 0, 0, 1, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('11-04-2013 15:40:44', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('11-04-2013 15:42:07', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (108, 23, 2, 'nameZh', '用户组名称(中文)', 80, 0, 0, 1, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('11-04-2013 15:41:57', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('11-04-2013 15:41:57', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (110, 22, 4, 'roles', 'roles', 10, 0, 0, 1, 'asc', 'left', 'left', 1, 0, null, null, null, null, 1, to_date('11-04-2013 16:17:40', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('11-04-2013 16:17:40', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (111, 23, 3, 'roles', 'roles', 10, 0, 0, 1, 'asc', 'left', 'left', 1, 0, null, null, null, null, 1, to_date('11-04-2013 18:38:42', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('11-04-2013 18:38:42', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (112, 24, 4, 'roles', 'roles', 10, 0, 0, 1, 'asc', 'left', 'left', 1, 0, null, null, null, null, 1, to_date('11-04-2013 18:39:27', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('11-04-2013 18:39:27', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (113, 25, 0, 'id', 'id', 10, 0, 0, 1, 'asc', 'center', 'center', 0, 1, null, null, null, null, 1, to_date('14-04-2013 09:07:06', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('14-04-2013 09:33:27', 'dd-mm-yyyy hh24:mi:ss'), 0, 8, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (114, 25, 2, 'nameEn', '编码名称(英文)', 80, 0, 0, 1, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('14-04-2013 09:30:31', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('14-04-2013 09:30:31', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (115, 25, 1, 'codeType', '编码类型', 60, 0, 0, 1, 'asc', 'left', 'left', 0, 0, 'formatterName', null, null, null, 1, to_date('14-04-2013 09:31:32', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('14-04-2013 09:43:15', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (116, 25, 3, 'nameZh', '编码名称(中文)', 80, 0, 0, 1, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('14-04-2013 09:34:05', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('14-04-2013 09:34:17', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (117, 25, 4, 'codeValue', '编码值', 80, 0, 0, 1, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('14-04-2013 09:34:45', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('14-04-2013 09:34:45', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (118, 25, 5, 'editable', '可编辑', 20, 0, 0, 1, 'asc', 'center', 'center', 0, 0, 'formatterEidtable', null, null, null, 1, to_date('14-04-2013 09:35:15', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('14-04-2013 09:45:14', 'dd-mm-yyyy hh24:mi:ss'), 0, 3, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (119, 25, 6, 'selected', '默认项', 20, 0, 0, 1, 'asc', 'center', 'center', 0, 0, 'formatterEidtable', null, null, null, 1, to_date('14-04-2013 09:39:19', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('14-04-2013 09:45:20', 'dd-mm-yyyy hh24:mi:ss'), 0, 3, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (120, 25, 7, 'sortNo', '排序', 20, 0, 0, 1, 'asc', 'center', 'center', 0, 0, null, null, null, null, 1, to_date('14-04-2013 09:40:07', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('14-04-2013 09:40:07', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (121, 25, 8, 'createdDate', '创建时间', 60, 0, 0, 1, 'asc', 'center', 'center', 0, 0, null, null, null, null, 1, to_date('14-04-2013 09:40:52', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('14-04-2013 09:40:52', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (122, 25, 9, 'updatedDate', '更新时间', 60, 0, 0, 1, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('14-04-2013 09:41:15', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('14-04-2013 09:41:15', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (123, 26, 0, 'id', 'id', 10, 0, 0, 1, 'asc', 'center', 'center', 0, 1, null, null, null, null, 1, to_date('14-04-2013 09:48:05', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('14-04-2013 09:50:48', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (124, 26, 1, 'type', '通用编码类型', 85, 0, 0, 1, 'asc', 'center', 'center', 0, 0, 'formatterCodeList', null, null, null, 1, to_date('14-04-2013 09:51:52', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('08-04-2014 12:54:52', 'dd-mm-yyyy hh24:mi:ss'), 0, 7, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (125, 26, 2, 'nameEn', '通用编码类型名称(英文)', 85, 0, 0, 1, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('14-04-2013 09:52:37', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('14-04-2013 09:52:37', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (126, 26, 3, 'nameZh', '通用编码类型名称(中文)', 85, 0, 0, 1, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('14-04-2013 09:52:56', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('14-04-2013 09:52:56', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (127, 26, 4, 'codeLenLimit', '编码参数值长度', 85, 0, 0, 1, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('14-04-2013 09:53:32', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('14-04-2013 09:53:32', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (128, 26, 5, 'editable', '可编辑', 20, 0, 0, 1, 'asc', 'center', 'center', 0, 0, 'formatterEidtable', null, null, null, 1, to_date('14-04-2013 09:53:57', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('14-04-2013 09:55:07', 'dd-mm-yyyy hh24:mi:ss'), 0, 2, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (129, 26, 6, 'createdDate', '创建时间', 80, 0, 0, 1, 'asc', 'center', 'center', 0, 0, null, null, null, null, 1, to_date('14-04-2013 09:55:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('14-04-2013 09:55:00', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (130, 26, 7, 'updatedDate', '更新时间', 80, 0, 0, 1, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('14-04-2013 09:55:31', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('14-04-2013 09:55:31', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (131, 27, 0, 'id', 'id', 10, 0, 0, 1, 'asc', 'center', 'center', 0, 1, null, null, null, null, 1, to_date('14-04-2013 12:38:08', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('14-04-2013 12:38:08', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (132, 27, 1, 'parentResourceId', '上一级ID', 10, 0, 0, 1, 'asc', 'left', 'left', 1, 0, null, null, null, null, 1, to_date('14-04-2013 12:39:36', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('14-04-2013 12:39:36', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (133, 27, 2, 'nameZh', '资源名称(中文)', 80, 0, 0, 1, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('14-04-2013 16:37:08', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('14-04-2013 16:37:08', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (134, 27, 3, 'type', '资源类型', 80, 0, 0, 1, 'asc', 'left', 'left', 0, 0, 'formatterCodeResource', null, null, null, 1, to_date('14-04-2013 16:37:46', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('14-04-2013 16:51:23', 'dd-mm-yyyy hh24:mi:ss'), 0, 2, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (135, 27, 4, 'path', '资源路径', 120, 0, 0, 1, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('14-04-2013 16:38:11', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('14-04-2013 16:38:11', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (136, 27, 5, 'seqNum', '排序', 20, 0, 0, 1, 'asc', 'center', 'center', 0, 0, null, null, null, null, 1, to_date('14-04-2013 16:39:13', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('14-04-2013 16:58:03', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (137, 27, 6, 'target', '目标', 15, 0, 0, 1, 'asc', 'center', 'left', 1, 0, null, null, null, null, 1, to_date('14-04-2013 16:39:54', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('14-04-2013 16:58:14', 'dd-mm-yyyy hh24:mi:ss'), 0, 2, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (138, 27, 7, 'nameEn', '资源名称(英文)', 80, 0, 0, 1, 'asc', 'left', 'left', 1, 0, null, null, null, null, 1, to_date('14-04-2013 16:40:27', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('14-04-2013 16:40:27', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (139, 27, 8, 'description', '资源描述', 120, 0, 0, 1, 'asc', 'left', 'left', 1, 0, null, null, null, null, 1, to_date('14-04-2013 16:40:51', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('14-04-2013 16:42:17', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (140, 27, 9, 'editable', '可编辑', 15, 0, 0, 1, 'asc', 'center', 'center', 0, 0, 'formatterEidtable', null, null, null, 1, to_date('14-04-2013 16:41:21', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('14-04-2013 16:58:09', 'dd-mm-yyyy hh24:mi:ss'), 0, 3, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (141, 27, 10, 'isLeaf', '是否叶节点', 40, 0, 0, 1, 'asc', 'left', 'left', 1, 0, null, null, null, null, 1, to_date('14-04-2013 16:42:05', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('14-04-2013 16:42:05', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (142, 28, 0, 'id', '部门ID', 10, 0, 0, 1, 'asc', 'center', 'center', 0, 1, null, null, null, null, 1, to_date('14-04-2013 17:00:55', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('14-04-2013 17:00:55', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (143, 28, 1, 'parentDepartmentId', '上级部门ID', 10, 0, 0, 1, 'asc', 'center', 'center', 1, 0, null, null, null, null, 1, to_date('14-04-2013 17:01:38', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('14-04-2013 17:01:43', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (144, 28, 2, 'nameEn', '部门名称(英文)', 80, 0, 0, 1, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('14-04-2013 17:03:07', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('14-04-2013 17:03:07', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (145, 28, 3, 'nameZh', '部门名称(中文)', 80, 0, 0, 1, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('14-04-2013 17:03:39', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('14-04-2013 17:03:39', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (146, 28, 4, 'departmentNo', '部门编号', 80, 0, 0, 1, 'asc', 'center', 'center', 0, 0, null, null, null, null, 1, to_date('14-04-2013 17:04:08', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('14-04-2013 17:04:08', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (147, 28, 5, 'description', '部门描述', 120, 0, 0, 1, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('14-04-2013 17:04:34', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('14-04-2013 17:04:34', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (148, 28, 6, 'editable', '可编辑', 20, 0, 0, 1, 'asc', 'center', 'center', 0, 0, 'formatterEidtable', null, null, null, 1, to_date('14-04-2013 17:05:25', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('14-04-2013 17:05:25', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (149, 29, 0, 'id', '序号', 10, 0, 0, 1, 'asc', 'left', 'center', 0, 1, null, null, null, null, 1, to_date('14-04-2013 17:27:21', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('14-04-2013 17:27:21', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (150, 29, 1, 'type', '参数类型', 60, 0, 0, 1, 'asc', 'left', 'left', 0, 0, 'formatterCodeList', null, null, null, 1, to_date('14-04-2013 17:28:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('14-04-2013 18:36:42', 'dd-mm-yyyy hh24:mi:ss'), 0, 3, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (151, 29, 2, 'nameEn', '参数名称(英文)', 80, 0, 0, 1, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('14-04-2013 17:28:47', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('14-04-2013 17:28:47', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (152, 29, 3, 'nameZh', '参数名称(中文)', 80, 0, 0, 1, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('14-04-2013 17:29:17', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('14-04-2013 17:29:17', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (154, 29, 5, 'description', '参数描述', 100, 0, 0, 1, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('14-04-2013 17:30:02', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('14-04-2013 17:30:02', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (155, 29, 7, 'editable', '可编辑', 25, 0, 0, 1, 'asc', 'center', 'center', 0, 0, 'formatterEidtable', null, null, null, 1, to_date('14-04-2013 17:30:36', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('14-04-2013 18:59:37', 'dd-mm-yyyy hh24:mi:ss'), 0, 4, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (156, 29, 8, 'createdDate', '创建时间', 80, 0, 0, 1, 'asc', 'center', 'center', 0, 0, null, null, null, null, 1, to_date('14-04-2013 17:31:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('14-04-2013 18:57:56', 'dd-mm-yyyy hh24:mi:ss'), 0, 2, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (157, 29, 9, 'updatedDate', '更新时间', 80, 0, 0, 1, 'asc', 'center', 'center', 0, 0, null, null, null, null, 1, to_date('14-04-2013 17:31:24', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('14-04-2013 18:57:50', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_COLUMN (bas_column_id, bas_datagrid_id, show_order, filed, name, width, rowspan, colspan, sortable, sort_order, header_align, align, hidden, check_box, formatter, styler, sorter, editor, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (158, 29, 6, 'value', '参数值', 80, 0, 0, 1, 'asc', 'left', 'left', 0, 0, null, null, null, null, 1, to_date('14-04-2013 18:57:34', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('14-04-2013 18:57:34', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
commit;
insert into BAS_DATAGRID (bas_datagrid_id, type_, table_id, title, show_title, fit, frozen, show_group, paginaction, fit_columns, striped, method, nowrap, url, data_, load_msg, row_numbers, single_select, check_on_select, select_on_check, page_position, page_number, page_size, page_list, queyr_params, sort_name, sort_order, remote_sort, show_header, show_footer, scrollber_size, row_styler, load_filter, editers, loader, view_, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (6, 'datagrid_datagrid ', 'datacolumn_tb', 'DataGrid列管理列表', 0, 0, 1, 0, 1, 1, 1, 'post', 1, 'main/datacolumn_lists.action', null, null, 1, 1, 1, 1, 'bottom', 1, 15, '[10,15,20,25,30,40,50,100]', null, null, 'asc', 1, 1, 1, 18, null, null, null, null, null, 1, to_date('01-04-2013 22:25:07', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('08-04-2014 23:30:21', 'dd-mm-yyyy hh24:mi:ss'), 0, 12, null);
insert into BAS_DATAGRID (bas_datagrid_id, type_, table_id, title, show_title, fit, frozen, show_group, paginaction, fit_columns, striped, method, nowrap, url, data_, load_msg, row_numbers, single_select, check_on_select, select_on_check, page_position, page_number, page_size, page_list, queyr_params, sort_name, sort_order, remote_sort, show_header, show_footer, scrollber_size, row_styler, load_filter, editers, loader, view_, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (7, 'users_datagrid', 'usergroup_tb', '已关联用户组 ', 1, 0, 0, 0, 0, 1, 1, 'post', 1, 'main/auth_usergrouplist.action', null, null, 1, 1, 0, 0, 'bottom', 1, 15, '[10,15,20,25,30,40,50,100]', null, null, 'asc', 1, 1, 0, 18, null, null, null, null, null, 1, to_date('02-04-2013 11:10:42', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('08-04-2014 23:29:14', 'dd-mm-yyyy hh24:mi:ss'), 0, 24, null);
insert into BAS_DATAGRID (bas_datagrid_id, type_, table_id, title, show_title, fit, frozen, show_group, paginaction, fit_columns, striped, method, nowrap, url, data_, load_msg, row_numbers, single_select, check_on_select, select_on_check, page_position, page_number, page_size, page_list, queyr_params, sort_name, sort_order, remote_sort, show_header, show_footer, scrollber_size, row_styler, load_filter, editers, loader, view_, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (8, 'users_datagrid', 'userrole_tb', '已关联角色', 1, 0, 0, 0, 0, 1, 1, 'post', 1, 'main/role_lists.action', null, null, 1, 1, 0, 0, 'bottom', 1, 15, '[10,15,20,25,30,40,50,100]', null, null, 'asc', 1, 1, 0, 18, null, null, null, null, null, 1, to_date('02-04-2013 13:23:51', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('08-04-2014 23:47:06', 'dd-mm-yyyy hh24:mi:ss'), 0, 5, null);
insert into BAS_DATAGRID (bas_datagrid_id, type_, table_id, title, show_title, fit, frozen, show_group, paginaction, fit_columns, striped, method, nowrap, url, data_, load_msg, row_numbers, single_select, check_on_select, select_on_check, page_position, page_number, page_size, page_list, queyr_params, sort_name, sort_order, remote_sort, show_header, show_footer, scrollber_size, row_styler, load_filter, editers, loader, view_, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (9, 'users_datagrid', 'authgroup_tb', null, 0, 0, 1, 0, 0, 1, 1, 'post', 1, 'main/auth_grouplist.action', null, null, 1, 0, 1, 0, 'bottom', 1, 15, '[10,15,20,25,30,40,50,100]', null, null, 'asc', 1, 1, 0, 18, null, null, null, null, null, 1, to_date('02-04-2013 14:41:23', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('07-04-2013 19:20:02', 'dd-mm-yyyy hh24:mi:ss'), 0, 4, null);
insert into BAS_DATAGRID (bas_datagrid_id, type_, table_id, title, show_title, fit, frozen, show_group, paginaction, fit_columns, striped, method, nowrap, url, data_, load_msg, row_numbers, single_select, check_on_select, select_on_check, page_position, page_number, page_size, page_list, queyr_params, sort_name, sort_order, remote_sort, show_header, show_footer, scrollber_size, row_styler, load_filter, editers, loader, view_, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (10, 'datagrid_datagrid ', 'datagrid_dt', 'DataGrid表格', 0, 0, 1, 0, 1, 1, 1, 'post', 1, 'main/datagrid_lists.action', null, null, 1, 1, 0, 0, 'bottom', 1, 15, '[10,15,20,25,30,40,50,100]', null, null, 'asc', 1, 1, 0, 18, null, null, null, null, null, 1, to_date('05-04-2013 17:41:16', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('08-04-2014 23:45:56', 'dd-mm-yyyy hh24:mi:ss'), 0, 33, null);
insert into BAS_DATAGRID (bas_datagrid_id, type_, table_id, title, show_title, fit, frozen, show_group, paginaction, fit_columns, striped, method, nowrap, url, data_, load_msg, row_numbers, single_select, check_on_select, select_on_check, page_position, page_number, page_size, page_list, queyr_params, sort_name, sort_order, remote_sort, show_header, show_footer, scrollber_size, row_styler, load_filter, editers, loader, view_, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (11, 'users_datagrid', 'sys_user_list_tb', '用户列表', 0, 0, 1, 0, 1, 1, 1, 'post', 1, 'main/userlists.action', null, null, 1, 1, 0, 0, 'bottom', 1, 15, '[10,15,20,25,30,40,50,100]', null, null, 'asc', 1, 1, 0, 18, null, null, null, null, null, 1, to_date('05-04-2013 19:42:23', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('08-04-2014 23:47:18', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_DATAGRID (bas_datagrid_id, type_, table_id, title, show_title, fit, frozen, show_group, paginaction, fit_columns, striped, method, nowrap, url, data_, load_msg, row_numbers, single_select, check_on_select, select_on_check, page_position, page_number, page_size, page_list, queyr_params, sort_name, sort_order, remote_sort, show_header, show_footer, scrollber_size, row_styler, load_filter, editers, loader, view_, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (12, 'users_datagrid', 'authrole_tb', null, 0, 0, 1, 0, 0, 1, 1, 'post', 1, 'main/auth_rolelist.action', null, null, 1, 0, 1, 0, 'bottom', 1, 15, '[10,15,20,25,30,40,50,100]', null, null, 'asc', 1, 1, 0, 18, null, null, null, null, null, 1, to_date('05-04-2013 20:59:17', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('08-04-2013 15:06:57', 'dd-mm-yyyy hh24:mi:ss'), 0, 8, null);
insert into BAS_DATAGRID (bas_datagrid_id, type_, table_id, title, show_title, fit, frozen, show_group, paginaction, fit_columns, striped, method, nowrap, url, data_, load_msg, row_numbers, single_select, check_on_select, select_on_check, page_position, page_number, page_size, page_list, queyr_params, sort_name, sort_order, remote_sort, show_header, show_footer, scrollber_size, row_styler, load_filter, editers, loader, view_, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (13, 'users_datagrid', 'sys_group_list_tb', '用户组管理', 0, 0, 0, 0, 1, 1, 1, 'post', 1, 'main/group_lists.action', null, null, 1, 1, 1, 0, 'bottom', 1, 15, '[10,15,20,25,30,40,50,100]', null, null, 'asc', 1, 1, 0, 18, null, null, null, null, null, 1, to_date('09-04-2013 17:11:43', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('09-04-2013 17:11:43', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_DATAGRID (bas_datagrid_id, type_, table_id, title, show_title, fit, frozen, show_group, paginaction, fit_columns, striped, method, nowrap, url, data_, load_msg, row_numbers, single_select, check_on_select, select_on_check, page_position, page_number, page_size, page_list, queyr_params, sort_name, sort_order, remote_sort, show_header, show_footer, scrollber_size, row_styler, load_filter, editers, loader, view_, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (14, 'users_datagrid', 'sys_authuserlistforgroup_tb', '已关联用户', 1, 0, 1, 0, 0, 1, 1, 'post', 1, 'main/auth_serlistforgroup.action', null, null, 0, 1, 0, 0, 'bottom', 1, 15, '[10,15,20,25,30,40,50,100]', null, null, 'asc', 1, 1, 0, 18, null, null, null, null, null, 1, to_date('09-04-2013 20:25:43', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('08-04-2014 23:47:23', 'dd-mm-yyyy hh24:mi:ss'), 0, 2, null);
insert into BAS_DATAGRID (bas_datagrid_id, type_, table_id, title, show_title, fit, frozen, show_group, paginaction, fit_columns, striped, method, nowrap, url, data_, load_msg, row_numbers, single_select, check_on_select, select_on_check, page_position, page_number, page_size, page_list, queyr_params, sort_name, sort_order, remote_sort, show_header, show_footer, scrollber_size, row_styler, load_filter, editers, loader, view_, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (16, 'users_datagrid', 'sys_authrolelistforgroup_tb', '已关联角色', 1, 0, 0, 0, 0, 1, 1, 'post', 1, 'main/authgroup_rolelistforgroup.action', null, null, 0, 1, 0, 0, 'bottom', 1, 15, '[10,15,20,25,30,40,50,100]', null, null, 'asc', 1, 1, 0, 18, null, null, null, null, null, 1, to_date('09-04-2013 21:03:09', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('09-04-2013 23:21:55', 'dd-mm-yyyy hh24:mi:ss'), 0, 2, null);
insert into BAS_DATAGRID (bas_datagrid_id, type_, table_id, title, show_title, fit, frozen, show_group, paginaction, fit_columns, striped, method, nowrap, url, data_, load_msg, row_numbers, single_select, check_on_select, select_on_check, page_position, page_number, page_size, page_list, queyr_params, sort_name, sort_order, remote_sort, show_header, show_footer, scrollber_size, row_styler, load_filter, editers, loader, view_, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (17, 'users_datagrid', 'sys_authuserforgroup_tb', '关联用户组', 0, 0, 1, 0, 0, 1, 1, 'post', 1, 'main/authgroup_userlist.action', null, null, 1, 0, 0, 0, 'bottom', 1, 15, '[10,15,20,25,30,40,50,100]', null, null, 'asc', 1, 1, 0, 18, null, null, null, null, null, 1, to_date('09-04-2013 21:08:50', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('08-04-2014 23:48:29', 'dd-mm-yyyy hh24:mi:ss'), 0, 3, null);
insert into BAS_DATAGRID (bas_datagrid_id, type_, table_id, title, show_title, fit, frozen, show_group, paginaction, fit_columns, striped, method, nowrap, url, data_, load_msg, row_numbers, single_select, check_on_select, select_on_check, page_position, page_number, page_size, page_list, queyr_params, sort_name, sort_order, remote_sort, show_header, show_footer, scrollber_size, row_styler, load_filter, editers, loader, view_, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (18, 'users_datagrid', 'sys_authroleforgroup_tb', null, 0, 0, 1, 0, 0, 1, 1, 'post', 1, 'main/authgroup_rolelist.action', null, null, 1, 0, 1, 0, 'bottom', 1, 15, '[10,15,20,25,30,40,50,100]', null, null, 'asc', 1, 1, 0, 18, null, null, null, null, null, 1, to_date('09-04-2013 21:13:06', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('09-04-2013 22:01:22', 'dd-mm-yyyy hh24:mi:ss'), 0, 2, null);
insert into BAS_DATAGRID (bas_datagrid_id, type_, table_id, title, show_title, fit, frozen, show_group, paginaction, fit_columns, striped, method, nowrap, url, data_, load_msg, row_numbers, single_select, check_on_select, select_on_check, page_position, page_number, page_size, page_list, queyr_params, sort_name, sort_order, remote_sort, show_header, show_footer, scrollber_size, row_styler, load_filter, editers, loader, view_, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (19, 'users_datagrid', 'sys_rolelist_tb', '角色列表', 0, 0, 1, 0, 1, 1, 1, 'post', 1, 'main/role_lists.action', null, null, 1, 1, 1, 0, 'bottom', 1, 15, '[10,15,20,25,30,40,50,100]', null, null, 'asc', 1, 1, 0, 18, null, null, null, null, null, 1, to_date('10-04-2013 20:37:53', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('08-04-2014 23:30:51', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_DATAGRID (bas_datagrid_id, type_, table_id, title, show_title, fit, frozen, show_group, paginaction, fit_columns, striped, method, nowrap, url, data_, load_msg, row_numbers, single_select, check_on_select, select_on_check, page_position, page_number, page_size, page_list, queyr_params, sort_name, sort_order, remote_sort, show_header, show_footer, scrollber_size, row_styler, load_filter, editers, loader, view_, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (20, 'users_datagrid', 'sys_authrole_userlist_tb', '已关联用户组', 1, 0, 1, 0, 0, 1, 1, 'post', 1, 'main/authrole_userlist.action', null, null, 0, 1, 0, 0, 'bottom', 1, 15, '[10,15,20,25,30,40,50,100]', null, null, 'asc', 1, 1, 0, 18, null, null, null, null, null, 1, to_date('10-04-2013 22:59:07', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('08-04-2014 23:47:34', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_DATAGRID (bas_datagrid_id, type_, table_id, title, show_title, fit, frozen, show_group, paginaction, fit_columns, striped, method, nowrap, url, data_, load_msg, row_numbers, single_select, check_on_select, select_on_check, page_position, page_number, page_size, page_list, queyr_params, sort_name, sort_order, remote_sort, show_header, show_footer, scrollber_size, row_styler, load_filter, editers, loader, view_, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (21, 'users_datagrid', 'sys_authrole_grouplist_tb', null, 0, 0, 1, 0, 1, 1, 1, 'post', 1, 'main/authrole_grouplist.action', null, null, 1, 0, 0, 0, 'bottom', 1, 15, '[10,15,20,25,30,40,50,100]', null, null, 'asc', 1, 1, 0, 18, null, null, null, null, null, 1, to_date('10-04-2013 23:04:55', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('08-04-2014 17:30:07', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_DATAGRID (bas_datagrid_id, type_, table_id, title, show_title, fit, frozen, show_group, paginaction, fit_columns, striped, method, nowrap, url, data_, load_msg, row_numbers, single_select, check_on_select, select_on_check, page_position, page_number, page_size, page_list, queyr_params, sort_name, sort_order, remote_sort, show_header, show_footer, scrollber_size, row_styler, load_filter, editers, loader, view_, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (22, 'users_datagrid', 'sys_authrole_resources_tb', null, 0, 0, 0, 0, 0, 1, 1, 'post', 1, 'main/authrole_resources.action', null, null, 1, 0, 0, 0, 'bottom', 1, 15, '[10,15,20,25,30,40,50,100]', null, null, 'asc', 0, 1, 0, 18, null, null, null, null, null, 1, to_date('11-04-2013 15:19:08', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('11-04-2013 16:16:02', 'dd-mm-yyyy hh24:mi:ss'), 0, 7, null);
insert into BAS_DATAGRID (bas_datagrid_id, type_, table_id, title, show_title, fit, frozen, show_group, paginaction, fit_columns, striped, method, nowrap, url, data_, load_msg, row_numbers, single_select, check_on_select, select_on_check, page_position, page_number, page_size, page_list, queyr_params, sort_name, sort_order, remote_sort, show_header, show_footer, scrollber_size, row_styler, load_filter, editers, loader, view_, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (23, 'users_datagrid', 'sys_authrole_groups_tb', null, 0, 0, 1, 0, 1, 1, 1, 'post', 1, 'main/authrole_groups.action', null, null, 1, 0, 0, 0, 'bottom', 1, 15, '[10,15,20,25,30,40,50,100]', null, null, 'asc', 1, 1, 0, 18, null, null, null, null, null, 1, to_date('11-04-2013 15:35:23', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('02-04-2014 17:23:29', 'dd-mm-yyyy hh24:mi:ss'), 0, 5, null);
insert into BAS_DATAGRID (bas_datagrid_id, type_, table_id, title, show_title, fit, frozen, show_group, paginaction, fit_columns, striped, method, nowrap, url, data_, load_msg, row_numbers, single_select, check_on_select, select_on_check, page_position, page_number, page_size, page_list, queyr_params, sort_name, sort_order, remote_sort, show_header, show_footer, scrollber_size, row_styler, load_filter, editers, loader, view_, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (24, 'users_datagrid', 'sys_authrole_users_tb', '角色授权表格', 0, 0, 1, 0, 0, 1, 1, 'post', 1, 'main/authrole_users.action', null, null, 1, 0, 0, 0, 'bottom', 1, 15, '[10,15,20,25,30,40,50,100]', null, null, 'asc', 1, 1, 0, 18, null, null, null, null, null, 1, to_date('11-04-2013 15:36:43', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('08-04-2014 23:48:03', 'dd-mm-yyyy hh24:mi:ss'), 0, 7, null);
insert into BAS_DATAGRID (bas_datagrid_id, type_, table_id, title, show_title, fit, frozen, show_group, paginaction, fit_columns, striped, method, nowrap, url, data_, load_msg, row_numbers, single_select, check_on_select, select_on_check, page_position, page_number, page_size, page_list, queyr_params, sort_name, sort_order, remote_sort, show_header, show_footer, scrollber_size, row_styler, load_filter, editers, loader, view_, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (25, 'code_datagrid', 'sys_codelist_tb', '编码列表', 0, 0, 1, 0, 1, 1, 1, 'post', 1, 'main/codelist_lists.action', null, null, 1, 1, 0, 0, 'bottom', 1, 15, '[10,15,20,25,30,40,50,100]', null, null, 'asc', 1, 1, 0, 18, null, null, null, null, null, 1, to_date('14-04-2013 09:05:30', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('08-04-2014 23:50:25', 'dd-mm-yyyy hh24:mi:ss'), 0, 3, null);
insert into BAS_DATAGRID (bas_datagrid_id, type_, table_id, title, show_title, fit, frozen, show_group, paginaction, fit_columns, striped, method, nowrap, url, data_, load_msg, row_numbers, single_select, check_on_select, select_on_check, page_position, page_number, page_size, page_list, queyr_params, sort_name, sort_order, remote_sort, show_header, show_footer, scrollber_size, row_styler, load_filter, editers, loader, view_, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (26, 'code_datagrid', 'sys_codetype_lists_tb', '编码类型列表', 0, 0, 1, 0, 1, 1, 1, 'post', 1, 'main/codetype_lists.action', null, null, 1, 1, 0, 0, 'bottom', 1, 15, '[10,15,20,25,30,40,50,100]', null, null, 'asc', 1, 1, 0, 18, null, null, null, null, null, 1, to_date('14-04-2013 09:47:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('08-04-2014 23:50:30', 'dd-mm-yyyy hh24:mi:ss'), 0, 2, null);
insert into BAS_DATAGRID (bas_datagrid_id, type_, table_id, title, show_title, fit, frozen, show_group, paginaction, fit_columns, striped, method, nowrap, url, data_, load_msg, row_numbers, single_select, check_on_select, select_on_check, page_position, page_number, page_size, page_list, queyr_params, sort_name, sort_order, remote_sort, show_header, show_footer, scrollber_size, row_styler, load_filter, editers, loader, view_, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (27, 'system_datagrid', 'sys_resource_lists_tb', '资源列表', 0, 0, 1, 0, 1, 1, 1, 'post', 1, 'main/resource_lists.action', null, null, 1, 1, 0, 0, 'bottom', 1, 15, '[10,15,20,25,30,40,50,100]', null, null, 'asc', 1, 1, 0, 18, null, null, null, null, null, 1, to_date('14-04-2013 11:45:43', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('08-04-2014 23:51:21', 'dd-mm-yyyy hh24:mi:ss'), 0, 7, null);
insert into BAS_DATAGRID (bas_datagrid_id, type_, table_id, title, show_title, fit, frozen, show_group, paginaction, fit_columns, striped, method, nowrap, url, data_, load_msg, row_numbers, single_select, check_on_select, select_on_check, page_position, page_number, page_size, page_list, queyr_params, sort_name, sort_order, remote_sort, show_header, show_footer, scrollber_size, row_styler, load_filter, editers, loader, view_, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (28, 'system_datagrid', 'sys_department_lists', '部门列表', 0, 0, 0, 0, 1, 1, 0, null, 0, 'main/department_lists.action', null, null, 1, 0, 0, 0, 'bottom', 1, 15, '[10,15,20,25,30,40,50,100]', null, null, null, 0, 1, 0, 18, null, null, null, null, null, 1, to_date('14-04-2013 16:35:22', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('08-04-2014 23:51:11', 'dd-mm-yyyy hh24:mi:ss'), 0, 8, null);
insert into BAS_DATAGRID (bas_datagrid_id, type_, table_id, title, show_title, fit, frozen, show_group, paginaction, fit_columns, striped, method, nowrap, url, data_, load_msg, row_numbers, single_select, check_on_select, select_on_check, page_position, page_number, page_size, page_list, queyr_params, sort_name, sort_order, remote_sort, show_header, show_footer, scrollber_size, row_styler, load_filter, editers, loader, view_, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (29, 'system_datagrid', 'sys_parameter_lists_tb', '系统参数列表', 0, 0, 1, 0, 1, 1, 1, 'post', 1, 'main/parameter_lists.action', null, null, 1, 1, 0, 0, 'bottom', 1, 15, '[10,15,20,25,30,40,50,100]', null, null, 'asc', 1, 1, 0, 18, null, null, null, null, null, 1, to_date('14-04-2013 17:26:58', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('08-04-2014 23:50:57', 'dd-mm-yyyy hh24:mi:ss'), 0, 2, null);
insert into BAS_DATAGRID (bas_datagrid_id, type_, table_id, title, show_title, fit, frozen, show_group, paginaction, fit_columns, striped, method, nowrap, url, data_, load_msg, row_numbers, single_select, check_on_select, select_on_check, page_position, page_number, page_size, page_list, queyr_params, sort_name, sort_order, remote_sort, show_header, show_footer, scrollber_size, row_styler, load_filter, editers, loader, view_, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (30, 'system_datagrid', 'sys_department_lists2', null, 0, 0, 1, 0, 1, 1, 1, 'post', 1, '/main/system/department/lists.jhtml', null, null, 1, 1, 0, 0, 'bottom', 1, 15, '[10,15,20,25,30,40,50,100]', null, null, 'asc', 1, 1, 0, 18, null, null, null, null, null, 1, to_date('04-04-2014 01:04:59', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('04-04-2014 01:10:43', 'dd-mm-yyyy hh24:mi:ss'), 0, 2, null);
insert into BAS_DATAGRID (bas_datagrid_id, type_, table_id, title, show_title, fit, frozen, show_group, paginaction, fit_columns, striped, method, nowrap, url, data_, load_msg, row_numbers, single_select, check_on_select, select_on_check, page_position, page_number, page_size, page_list, queyr_params, sort_name, sort_order, remote_sort, show_header, show_footer, scrollber_size, row_styler, load_filter, editers, loader, view_, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (32, 'datagrid_datagrid ', 'sys_datagrid_tb1', 'DataGrid表格测试 ', 0, 0, 1, 0, 1, 1, 0, 'post', 1, 'sys_datagrid_tb1', null, null, 1, 0, 0, 0, 'bottom', 1, 15, '[10,15,20,25,30,40,50,100]', null, null, 'asc', 1, 1, 0, 18, null, null, null, null, null, 1, to_date('08-04-2014 23:04:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('08-04-2014 23:46:14', 'dd-mm-yyyy hh24:mi:ss'), 0, 2, null);
insert into BAS_DATAGRID (bas_datagrid_id, type_, table_id, title, show_title, fit, frozen, show_group, paginaction, fit_columns, striped, method, nowrap, url, data_, load_msg, row_numbers, single_select, check_on_select, select_on_check, page_position, page_number, page_size, page_list, queyr_params, sort_name, sort_order, remote_sort, show_header, show_footer, scrollber_size, row_styler, load_filter, editers, loader, view_, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (33, 'system_datagrid', 'sys_cachelist_tb', '缓存管理', 0, 0, 1, 0, 0, 1, 1, 'post', 1, 'sys_cachelist_tb', null, null, 1, 1, 0, 0, 'bottom', 1, 15, '[10,15,20,25,30,40,50,100]', null, null, 'asc', 0, 1, 0, 18, null, null, null, null, null, 1, to_date('21-04-2014 15:13:40', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('22-04-2014 10:30:01', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
commit;
insert into BAS_DEMO (bas_demo_id, create_by, create_date, update_by, update_date, name_zh, parent_id)
values (1, 1, null, null, null, '总公司', null);
insert into BAS_DEMO (bas_demo_id, create_by, create_date, update_by, update_date, name_zh, parent_id)
values (2, 1, null, null, null, '上海分公司', 1);
insert into BAS_DEMO (bas_demo_id, create_by, create_date, update_by, update_date, name_zh, parent_id)
values (3, 1, null, null, null, '江苏分公司', 1);
insert into BAS_DEMO (bas_demo_id, create_by, create_date, update_by, update_date, name_zh, parent_id)
values (4, 1, null, null, null, '上海中支公司', 2);
insert into BAS_DEMO (bas_demo_id, create_by, create_date, update_by, update_date, name_zh, parent_id)
values (5, null, null, null, null, '上海中支公司1', 2);
insert into BAS_DEMO (bas_demo_id, create_by, create_date, update_by, update_date, name_zh, parent_id)
values (6, null, null, null, null, '南京中支公司', 3);
insert into BAS_DEMO (bas_demo_id, create_by, create_date, update_by, update_date, name_zh, parent_id)
values (7, null, null, null, null, '常州中支公司', 3);
insert into BAS_DEMO (bas_demo_id, create_by, create_date, update_by, update_date, name_zh, parent_id)
values (8, null, null, null, null, '山东分公司', 1);
insert into BAS_DEMO (bas_demo_id, create_by, create_date, update_by, update_date, name_zh, parent_id)
values (24, null, null, null, null, 'HP1', null);
insert into BAS_DEMO (bas_demo_id, create_by, create_date, update_by, update_date, name_zh, parent_id)
values (25, null, null, null, null, 'HP3', 24);
insert into BAS_DEMO (bas_demo_id, create_by, create_date, update_by, update_date, name_zh, parent_id)
values (26, null, null, null, null, 'HP2', 24);
insert into BAS_DEMO (bas_demo_id, create_by, create_date, update_by, update_date, name_zh, parent_id)
values (27, null, null, null, null, 'HP1', null);
insert into BAS_DEMO (bas_demo_id, create_by, create_date, update_by, update_date, name_zh, parent_id)
values (28, null, null, null, null, 'HP3', 27);
insert into BAS_DEMO (bas_demo_id, create_by, create_date, update_by, update_date, name_zh, parent_id)
values (29, null, null, null, null, 'HP2', 27);
insert into BAS_DEMO (bas_demo_id, create_by, create_date, update_by, update_date, name_zh, parent_id)
values (30, null, null, null, null, 'HP1', null);
insert into BAS_DEMO (bas_demo_id, create_by, create_date, update_by, update_date, name_zh, parent_id)
values (31, null, null, null, null, 'HP2', 30);
insert into BAS_DEMO (bas_demo_id, create_by, create_date, update_by, update_date, name_zh, parent_id)
values (32, null, null, null, null, 'HP3', 30);
insert into BAS_DEMO (bas_demo_id, create_by, create_date, update_by, update_date, name_zh, parent_id)
values (33, null, null, null, null, 'HP1', null);
insert into BAS_DEMO (bas_demo_id, create_by, create_date, update_by, update_date, name_zh, parent_id)
values (34, null, null, null, null, 'HP2', 33);
insert into BAS_DEMO (bas_demo_id, create_by, create_date, update_by, update_date, name_zh, parent_id)
values (35, null, null, null, null, 'HP3', 33);
insert into BAS_DEMO (bas_demo_id, create_by, create_date, update_by, update_date, name_zh, parent_id)
values (36, 1, to_date('23-12-2014', 'dd-mm-yyyy'), 1, to_date('23-12-2014', 'dd-mm-yyyy'), 'HP1', null);
insert into BAS_DEMO (bas_demo_id, create_by, create_date, update_by, update_date, name_zh, parent_id)
values (37, null, null, null, null, 'HP3', 36);
insert into BAS_DEMO (bas_demo_id, create_by, create_date, update_by, update_date, name_zh, parent_id)
values (38, null, null, null, null, 'HP2', 36);
insert into BAS_DEMO (bas_demo_id, create_by, create_date, update_by, update_date, name_zh, parent_id)
values (39, 1, to_date('24-12-2014', 'dd-mm-yyyy'), 1, to_date('24-12-2014', 'dd-mm-yyyy'), 'HP1', null);
insert into BAS_DEMO (bas_demo_id, create_by, create_date, update_by, update_date, name_zh, parent_id)
values (40, null, null, null, null, 'HP2', 39);
insert into BAS_DEMO (bas_demo_id, create_by, create_date, update_by, update_date, name_zh, parent_id)
values (41, null, null, null, null, 'HP3', 39);
insert into BAS_DEMO (bas_demo_id, create_by, create_date, update_by, update_date, name_zh, parent_id)
values (42, 1, to_date('07-01-2015', 'dd-mm-yyyy'), 1, to_date('07-01-2015', 'dd-mm-yyyy'), 'HP1', null);
insert into BAS_DEMO (bas_demo_id, create_by, create_date, update_by, update_date, name_zh, parent_id)
values (43, null, null, null, null, 'HP2', 42);
insert into BAS_DEMO (bas_demo_id, create_by, create_date, update_by, update_date, name_zh, parent_id)
values (44, null, null, null, null, 'HP3', 42);
insert into BAS_DEMO (bas_demo_id, create_by, create_date, update_by, update_date, name_zh, parent_id)
values (45, 1, to_date('07-01-2015', 'dd-mm-yyyy'), 1, to_date('07-01-2015', 'dd-mm-yyyy'), 'HP1', null);
insert into BAS_DEMO (bas_demo_id, create_by, create_date, update_by, update_date, name_zh, parent_id)
values (46, null, null, null, null, 'HP2', 45);
insert into BAS_DEMO (bas_demo_id, create_by, create_date, update_by, update_date, name_zh, parent_id)
values (47, null, null, null, null, 'HP3', 45);
insert into BAS_DEMO (bas_demo_id, create_by, create_date, update_by, update_date, name_zh, parent_id)
values (48, 1, to_date('13-02-2015', 'dd-mm-yyyy'), 1, to_date('13-02-2015', 'dd-mm-yyyy'), 'HP1', null);
insert into BAS_DEMO (bas_demo_id, create_by, create_date, update_by, update_date, name_zh, parent_id)
values (49, null, null, null, null, 'HP2', 48);
insert into BAS_DEMO (bas_demo_id, create_by, create_date, update_by, update_date, name_zh, parent_id)
values (50, null, null, null, null, 'HP3', 48);
commit;
insert into BAS_DEPARTMENT (bas_department_id, parent_bas_department_id, name_zh, name_en, departmentno, description, editable, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (3, null, '新致软件', 'NewTouch', '000001', 'NewTouch', 1, 1, to_date('31-12-2012 21:30:01', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('06-02-2015 10:56:11', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_DEPARTMENT (bas_department_id, parent_bas_department_id, name_zh, name_en, departmentno, description, editable, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (8, 3, '金融事业部', 'FIM1', '000021', '金融事业部', 1, 1, to_date('31-12-2012 21:49:31', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('06-02-2015 10:57:38', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_DEPARTMENT (bas_department_id, parent_bas_department_id, name_zh, name_en, departmentno, description, editable, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (9, 8, '开发一部', '开发一部', '000024', '开发一部', 1, 1, to_date('13-01-2013 21:52:57', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('06-02-2015 10:59:23', 'dd-mm-yyyy hh24:mi:ss'), 0, 3, null);
insert into BAS_DEPARTMENT (bas_department_id, parent_bas_department_id, name_zh, name_en, departmentno, description, editable, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (10, 8, '开发二部', 'IT_Drpt2', '000022', '研发中心', 1, 1, to_date('31-12-2012 21:52:57', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('04-04-2014 18:09:25', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_DEPARTMENT (bas_department_id, parent_bas_department_id, name_zh, name_en, departmentno, description, editable, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (11, 3, '信息技术部1', 'IT Drpt1', '000023', '信息技术部1', 1, 1, to_date('19-03-2013 09:32:38', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('19-03-2013 09:32:38', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_DEPARTMENT (bas_department_id, parent_bas_department_id, name_zh, name_en, departmentno, description, editable, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (18, 10, '开发二部一处', '开发二部一处', '开发二部一处', '开发二部一处', 1, 1, to_date('22-03-2013 16:52:34', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('06-02-2015 11:00:36', 'dd-mm-yyyy hh24:mi:ss'), 0, 3, null);
insert into BAS_DEPARTMENT (bas_department_id, parent_bas_department_id, name_zh, name_en, departmentno, description, editable, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (20, 10, '研发中心3', 'DDC2', '0000022', '研发中心3', 1, 1, to_date('23-03-2013 19:23:51', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('24-03-2013 00:26:49', 'dd-mm-yyyy hh24:mi:ss'), 0, 2, null);
insert into BAS_DEPARTMENT (bas_department_id, parent_bas_department_id, name_zh, name_en, departmentno, description, editable, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (21, 10, '开发二部二处', '开发二部二处', '开发二部二处', '开发二部二处', 1, 1, to_date('14-04-2013 16:29:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('06-02-2015 11:01:06', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_DEPARTMENT (bas_department_id, parent_bas_department_id, name_zh, name_en, departmentno, description, editable, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (25, 21, 'DDC221', 'DDC221', 'DDC221', 'DDC221', 1, 1, to_date('14-04-2013 16:32:52', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('14-04-2013 16:32:52', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
commit;
insert into BAS_GROUP (bas_group_id, name_zh, name_en, description, editable, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (1, '系统管理员', 'SysAdmin', '描述', 1, 1, to_date('23-03-2013 22:40:16', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('15-02-2015 21:15:03', 'dd-mm-yyyy hh24:mi:ss'), 0, 105, null);
insert into BAS_GROUP (bas_group_id, name_zh, name_en, description, editable, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (2, '产品管理员', 'ProductsUser', '产品管理员', 1, 1, to_date('23-03-2013 22:44:40', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('13-02-2015 10:30:21', 'dd-mm-yyyy hh24:mi:ss'), 0, 29, null);
insert into BAS_GROUP (bas_group_id, name_zh, name_en, description, editable, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (3, '销售管理员', 'SalesUser', '销售管理员', 1, 1, to_date('24-03-2013 10:30:13', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('10-03-2015 17:35:24', 'dd-mm-yyyy hh24:mi:ss'), 0, 27, null);
insert into BAS_GROUP (bas_group_id, name_zh, name_en, description, editable, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (4, '客户管理员', 'CustomersUser', '客户管理员', 1, 1, to_date('24-03-2013 10:30:47', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('31-03-2014 16:14:51', 'dd-mm-yyyy hh24:mi:ss'), 0, 16, null);
insert into BAS_GROUP (bas_group_id, name_zh, name_en, description, editable, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (5, '邮件管理员', 'EmailUser', '邮件管理员', 1, 1, to_date('02-04-2013 13:38:48', 'dd-mm-yyyy hh24:mi:ss'), 2, to_date('06-04-2014 01:08:03', 'dd-mm-yyyy hh24:mi:ss'), 0, 22, null);
insert into BAS_GROUP (bas_group_id, name_zh, name_en, description, editable, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (6, '报表管理员', 'ReportAdmin', '报表管理员', 1, 1, to_date('10-04-2013 20:27:30', 'dd-mm-yyyy hh24:mi:ss'), 2, to_date('06-04-2014 01:07:37', 'dd-mm-yyyy hh24:mi:ss'), 0, 3, null);
insert into BAS_GROUP (bas_group_id, name_zh, name_en, description, editable, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (7, 'name22222210', 'name222222', 'name22222210', 1, 2, to_date('06-04-2014 01:03:15', 'dd-mm-yyyy hh24:mi:ss'), 5, to_date('06-04-2014 01:06:17', 'dd-mm-yyyy hh24:mi:ss'), 0, 3, null);
insert into BAS_GROUP (bas_group_id, name_zh, name_en, description, editable, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (9, 'name2', 'name2', 'name2', 1, 1, to_date('13-02-2015 10:33:41', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('13-02-2015 10:33:41', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_GROUP (bas_group_id, name_zh, name_en, description, editable, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (12, 'name5', 'name5', 'name5', 1, 1, to_date('13-02-2015 10:34:19', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('13-02-2015 10:34:19', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_GROUP (bas_group_id, name_zh, name_en, description, editable, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (13, 'name6', 'name6', 'name6', 1, 1, to_date('13-02-2015 10:34:30', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('13-02-2015 10:34:30', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_GROUP (bas_group_id, name_zh, name_en, description, editable, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (14, 'name7', 'name7', 'name7', 1, 1, to_date('13-02-2015 10:34:40', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('13-02-2015 10:48:40', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_GROUP (bas_group_id, name_zh, name_en, description, editable, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (16, 'name9', 'name9', 'name9', 1, 1, to_date('13-02-2015 10:53:02', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('13-02-2015 10:53:02', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
commit;
insert into BAS_GROUP_ROLE (bas_role_id, bas_group_id)
values (1, 1);
insert into BAS_GROUP_ROLE (bas_role_id, bas_group_id)
values (2, 1);
insert into BAS_GROUP_ROLE (bas_role_id, bas_group_id)
values (4, 1);
insert into BAS_GROUP_ROLE (bas_role_id, bas_group_id)
values (6, 1);
insert into BAS_GROUP_ROLE (bas_role_id, bas_group_id)
values (1, 2);
insert into BAS_GROUP_ROLE (bas_role_id, bas_group_id)
values (4, 2);
insert into BAS_GROUP_ROLE (bas_role_id, bas_group_id)
values (6, 2);
insert into BAS_GROUP_ROLE (bas_role_id, bas_group_id)
values (1, 3);
insert into BAS_GROUP_ROLE (bas_role_id, bas_group_id)
values (4, 3);
insert into BAS_GROUP_ROLE (bas_role_id, bas_group_id)
values (6, 3);
insert into BAS_GROUP_ROLE (bas_role_id, bas_group_id)
values (1, 4);
insert into BAS_GROUP_ROLE (bas_role_id, bas_group_id)
values (6, 4);
insert into BAS_GROUP_ROLE (bas_role_id, bas_group_id)
values (1, 6);
insert into BAS_GROUP_ROLE (bas_role_id, bas_group_id)
values (4, 6);
insert into BAS_GROUP_ROLE (bas_role_id, bas_group_id)
values (1, 16);
insert into BAS_GROUP_ROLE (bas_role_id, bas_group_id)
values (4, 16);
commit;
insert into BAS_ICON (bas_icon_id, icon_type, icon_class, icon_image, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (1, 'tubiao', '111', 'image.jpg', 1, to_date('04-03-2015 14:35:57', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('04-03-2015 14:35:59', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
commit;

insert into BAS_PARAMETER (bas_parameter_id, category, name_en, name_zh, parameter_value, editable, description, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (1, 'Files', 'uploadFilePath12', '上传文件路径11', 'c:/temp/', 1, '文件上传路径', 1, to_date('16-12-2012 20:31:11', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('26-01-2015 15:34:52', 'dd-mm-yyyy hh24:mi:ss'), 0, 14, null);
insert into BAS_PARAMETER (bas_parameter_id, category, name_en, name_zh, parameter_value, editable, description, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (4, 'Users', 'uploadFilePath111', '上传文件路径111', 'c:/temp/111', 1, '文件上传路径1', 1, to_date('16-12-2012 21:28:26', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('18-02-2015 10:47:05', 'dd-mm-yyyy hh24:mi:ss'), 0, 8, null);
insert into BAS_PARAMETER (bas_parameter_id, category, name_en, name_zh, parameter_value, editable, description, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (5, 'Files', 'uploadFilePath', '上传文件路径', 'c:/temp/temp/', 1, '文件上传路径11', 1, to_date('16-12-2012 21:28:27', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('04-02-2015 11:20:22', 'dd-mm-yyyy hh24:mi:ss'), 0, 21, null);
insert into BAS_PARAMETER (bas_parameter_id, category, name_en, name_zh, parameter_value, editable, description, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (9, 'systems', 'systemName', '系统名称', 'eCommerce', 1, null, 1, to_date('12-03-2013 16:24:21', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('05-04-2013 11:39:52', 'dd-mm-yyyy hh24:mi:ss'), 0, 3, null);
insert into BAS_PARAMETER (bas_parameter_id, category, name_en, name_zh, parameter_value, editable, description, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (78, 'systems', 'PwdInitialization', '用户初始化密码', '111aaa', 0, '用户初始化密码,用于管理系统用户的创建的密码', 1, to_date('23-03-2013 18:36:53', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('26-01-2015 15:35:04', 'dd-mm-yyyy hh24:mi:ss'), 0, 2, null);
insert into BAS_PARAMETER (bas_parameter_id, category, name_en, name_zh, parameter_value, editable, description, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (99, 'Files', 'name2', 'name2', 'name2', 1, 'name2', 1, to_date('14-04-2013 23:02:55', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('14-04-2013 23:02:55', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_PARAMETER (bas_parameter_id, category, name_en, name_zh, parameter_value, editable, description, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (128, 'systems', 'name2222', 'name222', 'name', 1, '文件上传路径1', 1, to_date('21-05-2013 20:35:31', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('26-01-2015 15:35:00', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_PARAMETER (bas_parameter_id, category, name_en, name_zh, parameter_value, editable, description, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (135, 'Users', 'name223333', 'name22', 'name22', 1, '22ddd', 1, to_date('27-03-2014 15:22:05', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('30-03-2014 13:23:08', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_PARAMETER (bas_parameter_id, category, name_en, name_zh, parameter_value, editable, description, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (138, 'systems', 'name2333222', 'name222dd', 'nameeeddd', 1, null, 1, to_date('27-03-2014 15:25:28', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('27-03-2014 15:25:28', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_PARAMETER (bas_parameter_id, category, name_en, name_zh, parameter_value, editable, description, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (139, 'systems', 'name23332221', 'name222dd1', 'nameeeddd1', 1, null, 1, to_date('27-03-2014 15:26:07', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('27-03-2014 15:26:07', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_PARAMETER (bas_parameter_id, category, name_en, name_zh, parameter_value, editable, description, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (140, 'systems', 'wanglijun', 'wanglijun', 'wanglijun', 1, '3333', 1, to_date('27-03-2014 15:26:30', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('27-03-2014 15:26:30', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_PARAMETER (bas_parameter_id, category, name_en, name_zh, parameter_value, editable, description, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (141, 'systems', 'wanglijun1', 'wanglijun1', 'wanglijun1', 1, 'wanglijun1', 1, to_date('27-03-2014 15:27:53', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('27-03-2014 15:27:53', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_PARAMETER (bas_parameter_id, category, name_en, name_zh, parameter_value, editable, description, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (142, 'systems', 'wanglijun3', 'wanglijun3', 'wanglijun3', 1, 'wanglijun3', 1, to_date('27-03-2014 15:32:15', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('27-03-2014 15:32:15', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_PARAMETER (bas_parameter_id, category, name_en, name_zh, parameter_value, editable, description, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (143, 'systems', 'name', 'name', 'name', 1, 'name', 1, to_date('27-03-2014 15:32:45', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('27-03-2014 15:32:45', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_PARAMETER (bas_parameter_id, category, name_en, name_zh, parameter_value, editable, description, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (144, 'systems', 'name2ddd', 'named', 'nameddd', 1, 'name', 1, to_date('27-03-2014 15:32:57', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('27-03-2014 15:32:57', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_PARAMETER (bas_parameter_id, category, name_en, name_zh, parameter_value, editable, description, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (145, 'systems', 'name222222', 'name222222', 'name222222', 1, 'name222222', 1, to_date('27-03-2014 15:34:23', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('27-03-2014 15:34:23', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_PARAMETER (bas_parameter_id, category, name_en, name_zh, parameter_value, editable, description, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (154, 'systems', 'wanglijun5', 'wanglijun5', 'wanglijun5', 1, 'name2222', 1, to_date('27-03-2014 15:35:58', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('27-03-2014 15:35:58', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_PARAMETER (bas_parameter_id, category, name_en, name_zh, parameter_value, editable, description, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (155, 'systems', 'name3333', 'name3333', 'name3333', 1, 'name3333', 1, to_date('27-03-2014 15:37:00', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('27-03-2014 15:37:00', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_PARAMETER (bas_parameter_id, category, name_en, name_zh, parameter_value, editable, description, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (156, 'systems', 'isVaild', 'isVaild', 'isVaild', 1, 'isVaild', 1, to_date('27-03-2014 15:47:34', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('27-03-2014 15:47:34', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_PARAMETER (bas_parameter_id, category, name_en, name_zh, parameter_value, editable, description, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (157, 'systems', 'wanglijun8', 'wanglijun8', 'wanglijun8', 1, 'wangliju', 1, to_date('27-03-2014 15:47:53', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('28-03-2014 17:45:15', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_PARAMETER (bas_parameter_id, category, name_en, name_zh, parameter_value, editable, description, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (158, 'Files', 'wanglijun10', 'wanglijun10', 'wanglijun10', 1, 'wanglijun1', 1, to_date('27-03-2014 15:48:34', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('02-02-2015 10:07:25', 'dd-mm-yyyy hh24:mi:ss'), 0, 2, null);
insert into BAS_PARAMETER (bas_parameter_id, category, name_en, name_zh, parameter_value, editable, description, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (159, 'systems', 'wanlgijun9', 'wanlgijun9', 'wanlgijun9', 1, 'wanlgijun9', 1, to_date('27-03-2014 15:49:27', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('27-03-2014 21:31:25', 'dd-mm-yyyy hh24:mi:ss'), 0, 4, null);
insert into BAS_PARAMETER (bas_parameter_id, category, name_en, name_zh, parameter_value, editable, description, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (160, 'systems', 'name5', 'name5', 'name5', 1, 'name5', 1, to_date('27-03-2014 15:50:07', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('27-03-2014 21:31:06', 'dd-mm-yyyy hh24:mi:ss'), 0, 2, null);
insert into BAS_PARAMETER (bas_parameter_id, category, name_en, name_zh, parameter_value, editable, description, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (161, 'systems', 'wanglijun9', 'wanglijun9', 'wanglijun9', 1, '修改成功', 1, to_date('27-03-2014 15:52:06', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('27-03-2014 21:31:33', 'dd-mm-yyyy hh24:mi:ss'), 0, 2, null);
insert into BAS_PARAMETER (bas_parameter_id, category, name_en, name_zh, parameter_value, editable, description, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (162, 'systems', 'name200', 'name200', 'name200', 1, 'name200', 1, to_date('02-04-2014 15:52:34', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('02-04-2014 15:52:34', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_PARAMETER (bas_parameter_id, category, name_en, name_zh, parameter_value, editable, description, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (163, 'sysInterface', 'userInterface', '用户登录接口', 'http://www.site.com/user', 1, '用户登录接口', 1, to_date('01-06-2014 21:49:10', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-06-2014 21:50:12', 'dd-mm-yyyy hh24:mi:ss'), 0, 3, null);
insert into BAS_PARAMETER (bas_parameter_id, category, name_en, name_zh, parameter_value, editable, description, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (164, 'sysInterface', 'policyApplyInterface', '保单承保接口', 'http://www.site.com/policyapply', 1, '保单承保接口', 1, to_date('01-06-2014 21:51:10', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-06-2014 21:51:10', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_PARAMETER (bas_parameter_id, category, name_en, name_zh, parameter_value, editable, description, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (165, 'sysInterface', 'smsInterface', '短信接口', '192.168.1.60', 1, '短信接口', 1, to_date('01-06-2014 21:51:57', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-06-2014 21:51:57', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_PARAMETER (bas_parameter_id, category, name_en, name_zh, parameter_value, editable, description, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (166, 'sysInterface', 'emailInterface', '邮件接口', '192.168.1.79', 1, '邮件接口', 1, to_date('01-06-2014 21:52:47', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-06-2014 21:52:47', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_PARAMETER (bas_parameter_id, category, name_en, name_zh, parameter_value, editable, description, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (167, 'sysInterface', 'mpi.backTransUrl', '后台交易请求地址', 'https://unionpaysecure.com/gateway/api/frontTransRequest.do,https://unionpaysecure.com/gateway/api/backTransRequest.do', 1, '后台交易请求地址', 1, to_date('01-06-2014 21:54:39', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-06-2014 21:58:55', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_PARAMETER (bas_parameter_id, category, name_en, name_zh, parameter_value, editable, description, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (168, 'sysInterface', 'mpi.frontTransUrl', '前台交易请求地址', 'https://unionpaysecure.com/gateway/api/frontTransRequest.do', 1, '前台交易请求地址', 1, to_date('01-06-2014 21:59:42', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-06-2014 21:59:42', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_PARAMETER (bas_parameter_id, category, name_en, name_zh, parameter_value, editable, description, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (169, 'sysInterface', 'mpi.singleQueryUrl', '单笔查询请求地址', 'https://unionpaysecure.com/gateway/api/singleQueryRequest.do', 1, '单笔查询请求地址', 1, to_date('01-06-2014 22:00:11', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-06-2014 22:00:11', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_PARAMETER (bas_parameter_id, category, name_en, name_zh, parameter_value, editable, description, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (170, 'sysInterface', 'mpi.batchTransUrl', '批量交易请求地址', 'https://unionpaysecure.com/gateway/api/batchTransRequest.do', 1, '批量交易请求地址', 1, to_date('01-06-2014 22:00:58', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-06-2014 22:00:58', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_PARAMETER (bas_parameter_id, category, name_en, name_zh, parameter_value, editable, description, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (171, 'sysInterface', 'mpi.batchQueryUrl', '批量查询请求地址', 'https://unionpaysecure.com/gateway/api/batchQueryRequest.do', 1, '批量查询请求地址', 1, to_date('01-06-2014 22:02:33', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-06-2014 22:03:44', 'dd-mm-yyyy hh24:mi:ss'), 0, 2, null);
insert into BAS_PARAMETER (bas_parameter_id, category, name_en, name_zh, parameter_value, editable, description, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (172, 'sysInterface', 'mpi.fileTransUrl', '文件传输交易地址', 'https://unionpaysecure.com/gateway/api/fileTransRequest.do', 1, '文件传输交易地址', 1, to_date('01-06-2014 22:03:11', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-06-2014 22:03:11', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_PARAMETER (bas_parameter_id, category, name_en, name_zh, parameter_value, editable, description, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (173, 'sysInterface', 'mpi.cbFrontTransUrl', '跨行收单开通并支付交易地址', 'https://unionpaysecure.com/gateway/cb/frontTransRequest.do', 1, '跨行收单开通并支付交易地址', 1, to_date('01-06-2014 22:04:20', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-06-2014 22:04:20', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_PARAMETER (bas_parameter_id, category, name_en, name_zh, parameter_value, editable, description, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (174, 'sysInterface', 'mpi.cbBackTransUrl', '跨行收单支付地址', 'https://unionpaysecure.com/gateway/cb/backTransRequest.do', 1, '跨行收单支付地址', 1, to_date('01-06-2014 22:04:54', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-06-2014 22:04:54', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_PARAMETER (bas_parameter_id, category, name_en, name_zh, parameter_value, editable, description, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (175, 'sysInterface', 'mpi.signCert.path', '签名证书路径', '/weblog10/bea/deploy_files/ICBC/tel/700000000000001.pfx', 1, '签名证书路径', 1, to_date('01-06-2014 22:05:26', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-06-2014 22:05:26', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_PARAMETER (bas_parameter_id, category, name_en, name_zh, parameter_value, editable, description, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (176, 'sysInterface', 'mpi.signCert.pwd', '数字证书密码', '000000', 1, '数字证书密码', 1, to_date('01-06-2014 22:06:10', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-06-2014 22:06:10', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_PARAMETER (bas_parameter_id, category, name_en, name_zh, parameter_value, editable, description, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (177, 'sysInterface', 'mpi.signCert.type', '数字证书类型', 'PKCS12', 1, '数字证书类型' || chr(13) || '' || chr(10) || '', 1, to_date('01-06-2014 22:06:44', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-06-2014 22:06:44', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_PARAMETER (bas_parameter_id, category, name_en, name_zh, parameter_value, editable, description, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (178, 'sysInterface', 'mpi.encryptCert.path', '密码加密证书路径', '/weblog10/bea/deploy_files/ICBC/tel/PM_enc.cer', 1, '密码加密证书路径，敏感信息加密证书路径，请根据实际情况进行修改', 1, to_date('01-06-2014 22:07:26', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-06-2014 22:07:26', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_PARAMETER (bas_parameter_id, category, name_en, name_zh, parameter_value, editable, description, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (179, 'sysInterface', 'mpi.validateCert.dir', '验证签名证书目录', '/weblog10/bea/deploy_files/ICBC/tel', 1, '验证签名证书目录，验证银联系统返回报文的证书目录', 1, to_date('01-06-2014 22:08:05', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-06-2014 22:08:19', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_PARAMETER (bas_parameter_id, category, name_en, name_zh, parameter_value, editable, description, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (180, 'sysInterface', 'mpi.merchantNo', '银联在线商户号', '102310063000015', 1, '银联在线商户号', 1, to_date('01-06-2014 22:08:48', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-06-2014 22:08:48', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_PARAMETER (bas_parameter_id, category, name_en, name_zh, parameter_value, editable, description, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (181, 'Users', 'dddd', 'dddd', 'dddd', 1, 'dddd', 1, to_date('24-01-2015 22:55:35', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('24-01-2015 22:55:35', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_PARAMETER (bas_parameter_id, category, name_en, name_zh, parameter_value, editable, description, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (187, 'Users', 'ddddddddddd', 'dddddd', 'dddddddd', 1, 'ddddddd', 1, to_date('24-01-2015 22:59:20', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('24-01-2015 22:59:20', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_PARAMETER (bas_parameter_id, category, name_en, name_zh, parameter_value, editable, description, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (196, 'Users', 'ddddd', 'dddd', 'dddd', 1, 'dddd', 1, to_date('24-01-2015 23:24:31', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('24-01-2015 23:24:31', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_PARAMETER (bas_parameter_id, category, name_en, name_zh, parameter_value, editable, description, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (197, 'Users', 'dddddd', 'ddddd', 'ddddd', 1, 'ddddd1', 1, to_date('25-01-2015 12:13:25', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('26-01-2015 15:55:17', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_PARAMETER (bas_parameter_id, category, name_en, name_zh, parameter_value, editable, description, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (198, 'Users', 'name4', 'name', 'value', 1, 'ddd', 1, to_date('25-01-2015 12:14:37', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('25-01-2015 12:14:37', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_PARAMETER (bas_parameter_id, category, name_en, name_zh, parameter_value, editable, description, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (199, 'Users', 'dddd4444', 'dddd', 'dddd2', 0, 'dddd', 1, to_date('25-01-2015 12:15:30', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('26-01-2015 15:41:34', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_PARAMETER (bas_parameter_id, category, name_en, name_zh, parameter_value, editable, description, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (200, 'Users', 'dddd4444e', 'dddd', 'dddd', 1, 'dddd', 1, to_date('25-01-2015 12:16:16', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('26-01-2015 15:46:00', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
commit;
insert into BAS_RESOURCE (bas_resource_id, parent_bas_resource_id, type_, path, name_zh, name_en, description, seq_num, is_leaf, editable, target, icon, permission, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (1, null, 'application', 'Application', '应用资源', 'application', 'Application', 0, 0, 1, '_self', 'icon-star', null, 1, to_date('25-03-2013 15:56:44', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('26-02-2015 17:52:14', 'dd-mm-yyyy hh24:mi:ss'), 0, 7, null);
insert into BAS_RESOURCE (bas_resource_id, parent_bas_resource_id, type_, path, name_zh, name_en, description, seq_num, is_leaf, editable, target, icon, permission, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (2, 1, 'module_menu_category', 'SystemConfig', '系统设置', 'SystemConfig', '系统设置', 1, 0, 1, '_self', 'icon-settings', null, 1, to_date('25-03-2013 17:16:05', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('25-03-2013 17:16:05', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_RESOURCE (bas_resource_id, parent_bas_resource_id, type_, path, name_zh, name_en, description, seq_num, is_leaf, editable, target, icon, permission, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (9, 2, 'module_menu_category', null, '用户管理', 'usermanage', null, 0, 0, 1, '_self', 'icon-star', null, 1, to_date('25-03-2013 19:26:09', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('09-03-2015 16:09:26', 'dd-mm-yyyy hh24:mi:ss'), 0, 6, null);
insert into BAS_RESOURCE (bas_resource_id, parent_bas_resource_id, type_, path, name_zh, name_en, description, seq_num, is_leaf, editable, target, icon, permission, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (10, 2, 'module_menu_category', null, '编码管理', 'CodeManage', null, 1, 0, 1, '_self', 'icon-star', null, 1, to_date('25-03-2013 19:28:08', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('05-03-2015 13:37:28', 'dd-mm-yyyy hh24:mi:ss'), 0, 5, null);
insert into BAS_RESOURCE (bas_resource_id, parent_bas_resource_id, type_, path, name_zh, name_en, description, seq_num, is_leaf, editable, target, icon, permission, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (11, 2, 'module_menu_category_item', '/system/resource/index.htm', '资源管理', 'ResourceManage', null, 2, null, 1, '_self', 'icon-star', null, 1, to_date('25-03-2013 19:30:56', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('26-03-2013 22:51:30', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_RESOURCE (bas_resource_id, parent_bas_resource_id, type_, path, name_zh, name_en, description, seq_num, is_leaf, editable, target, icon, permission, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (12, 2, 'module_menu_category_item', '/system/parameter/index.htm', '系统参数', 'ParameterManage', '系统参数', 3, null, 1, '_self', 'icon-star', null, 1, to_date('25-03-2013 19:36:46', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('26-03-2013 22:53:21', 'dd-mm-yyyy hh24:mi:ss'), 0, 2, null);
insert into BAS_RESOURCE (bas_resource_id, parent_bas_resource_id, type_, path, name_zh, name_en, description, seq_num, is_leaf, editable, target, icon, permission, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (13, 1, 'module_menu_category_item', '/index.htm', '控制面板', 'MyMenu', null, 0, 0, 1, '_self', 'icon-home', 'dashboard:index', 1, to_date('25-03-2013 19:43:04', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('05-03-2015 16:52:04', 'dd-mm-yyyy hh24:mi:ss'), 0, 3, null);
insert into BAS_RESOURCE (bas_resource_id, parent_bas_resource_id, type_, path, name_zh, name_en, description, seq_num, is_leaf, editable, target, icon, permission, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (14, 9, 'module_menu_category_item', '/system/role/index.htm', '角色管理', 'RoleManage', '角色管理', 0, null, 1, '_self', 'icon-star', null, 1, to_date('25-03-2013 19:59:34', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('26-03-2013 22:46:59', 'dd-mm-yyyy hh24:mi:ss'), 0, 2, null);
insert into BAS_RESOURCE (bas_resource_id, parent_bas_resource_id, type_, path, name_zh, name_en, description, seq_num, is_leaf, editable, target, icon, permission, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (15, 9, 'module_menu_category_item', '/system/group/index.htm', '用户组管理', 'RoleManage', '用户组管理', 1, null, 1, '_self', 'icon-star', null, 1, to_date('25-03-2013 20:10:13', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('26-03-2013 22:47:25', 'dd-mm-yyyy hh24:mi:ss'), 0, 2, null);
insert into BAS_RESOURCE (bas_resource_id, parent_bas_resource_id, type_, path, name_zh, name_en, description, seq_num, is_leaf, editable, target, icon, permission, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (16, 10, 'module_menu_category_item', '/system/codetype/index.htm', '通用编码类型', 'codeType', '通用编码类型', 0, null, 1, '_self', 'icon-star', null, 1, to_date('26-03-2013 22:49:20', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('26-03-2013 22:49:20', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_RESOURCE (bas_resource_id, parent_bas_resource_id, type_, path, name_zh, name_en, description, seq_num, is_leaf, editable, target, icon, permission, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (17, 10, 'module_menu_category_item', '/system/codelist/index.htm', '编码列表', 'codelist', null, 1, 0, 1, '_self', 'icon-star', 'system.codelist.index', 1, to_date('26-03-2013 22:51:12', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('04-03-2015 20:59:00', 'dd-mm-yyyy hh24:mi:ss'), 0, 4, null);
insert into BAS_RESOURCE (bas_resource_id, parent_bas_resource_id, type_, path, name_zh, name_en, description, seq_num, is_leaf, editable, target, icon, permission, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (18, 9, 'module_menu_category_item', '/system/user/index.htm', '用户管理', 'userManage', '用户管理', 2, null, 1, '_self', 'icon-star', null, 1, to_date('27-03-2013 14:13:53', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('27-03-2013 14:13:53', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_RESOURCE (bas_resource_id, parent_bas_resource_id, type_, path, name_zh, name_en, description, seq_num, is_leaf, editable, target, icon, permission, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (19, 2, 'module_menu_category_item', '/system/department/index.htm', '部门管理', 'DepartmentManage', '部门管理', 1, 0, 1, '_self', 'icon-star', null, 1, to_date('27-03-2013 14:17:33', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('22-05-2014 10:24:05', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_RESOURCE (bas_resource_id, parent_bas_resource_id, type_, path, name_zh, name_en, description, seq_num, is_leaf, editable, target, icon, permission, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (20, 2, 'module_menu_category', 'DataGridManage', 'DataGrid管理', 'DataGrid', 'DataGrid管理', 5, 0, 1, '_self', 'icon-star', null, 1, to_date('27-03-2013 20:59:31', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('30-03-2013 10:59:06', 'dd-mm-yyyy hh24:mi:ss'), 0, 11, null);
insert into BAS_RESOURCE (bas_resource_id, parent_bas_resource_id, type_, path, name_zh, name_en, description, seq_num, is_leaf, editable, target, icon, permission, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (21, 20, 'module_menu_category_item', '/system/datagrid/index.htm', 'DataGrid', 'DataGrid', null, 0, 0, 1, '_self', 'icon-star', null, 1, to_date('27-03-2013 21:01:06', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('09-03-2015 16:09:54', 'dd-mm-yyyy hh24:mi:ss'), 0, 4, null);
insert into BAS_RESOURCE (bas_resource_id, parent_bas_resource_id, type_, path, name_zh, name_en, description, seq_num, is_leaf, editable, target, icon, permission, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (24, 20, 'module_menu_category_item', '/system/datacolumn/index.htm', 'DataColumn管理', 'DataColumn', null, 2, 0, 1, '_self', 'icon-star', null, 1, to_date('27-03-2013 21:47:09', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('09-03-2015 16:05:25', 'dd-mm-yyyy hh24:mi:ss'), 0, 2, null);
insert into BAS_RESOURCE (bas_resource_id, parent_bas_resource_id, type_, path, name_zh, name_en, description, seq_num, is_leaf, editable, target, icon, permission, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (25, 4, 'module_menu_category', '报表设计', '报表设计', '报表设计', null, 1, 1, 1, '_self', 'icon-bar-chart', null, 1, to_date('14-04-2013 11:20:30', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('14-04-2013 11:43:15', 'dd-mm-yyyy hh24:mi:ss'), 0, 2, null);
insert into BAS_RESOURCE (bas_resource_id, parent_bas_resource_id, type_, path, name_zh, name_en, description, seq_num, is_leaf, editable, target, icon, permission, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (26, 2, 'module_menu_category_item', '/system/application/index.htm', '系统信息', 'SystemInfo', '系统信息', 1, null, 1, '_self', 'icon-star', null, 1, to_date('14-04-2013 20:53:24', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('14-04-2013 20:55:42', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_RESOURCE (bas_resource_id, parent_bas_resource_id, type_, path, name_zh, name_en, description, seq_num, is_leaf, editable, target, icon, permission, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (27, 5, 'module_menu_category_item', '菜单1', '菜单1', '菜单1', '菜单1', 0, 0, 1, '_self', 'icon-star', null, 2, to_date('06-04-2014 13:30:32', 'dd-mm-yyyy hh24:mi:ss'), 2, to_date('06-04-2014 13:30:32', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_RESOURCE (bas_resource_id, parent_bas_resource_id, type_, path, name_zh, name_en, description, seq_num, is_leaf, editable, target, icon, permission, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (32, 7, 'module_menu_category', '网页菜单1', '网页菜单1', 'WEB_MENU1', '网页菜单1', 1, 0, 1, '_self', 'icon-star', null, 1, to_date('08-04-2014 14:48:09', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('08-04-2014 14:48:09', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_RESOURCE (bas_resource_id, parent_bas_resource_id, type_, path, name_zh, name_en, description, seq_num, is_leaf, editable, target, icon, permission, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (33, 32, 'module_menu_category_item', '菜单子项3', '菜单子项3', '菜单子项3', null, 2, 0, 1, '_self', 'icon-star', null, 1, to_date('08-04-2014 14:48:41', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('07-02-2015 14:18:10', 'dd-mm-yyyy hh24:mi:ss'), 0, 6, null);
insert into BAS_RESOURCE (bas_resource_id, parent_bas_resource_id, type_, path, name_zh, name_en, description, seq_num, is_leaf, editable, target, icon, permission, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (36, 2, 'module_menu_category', 'SYSTEM_MONITOR', '系统监控', 'SYSTEM_MONITOR', null, 5, 0, 1, '_self', 'icon-star', null, 1, to_date('08-04-2014 15:03:34', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('07-02-2015 13:34:57', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_RESOURCE (bas_resource_id, parent_bas_resource_id, type_, path, name_zh, name_en, description, seq_num, is_leaf, editable, target, icon, permission, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (37, 36, 'module_menu_category_item', '/sessions/index.htm', '用户会话监控', '用户会话监控', null, 1, 0, 1, '_self', 'icon-star', null, 1, to_date('08-04-2014 15:06:21', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('27-02-2015 10:19:34', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_RESOURCE (bas_resource_id, parent_bas_resource_id, type_, path, name_zh, name_en, description, seq_num, is_leaf, editable, target, icon, permission, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (38, 36, 'module_menu_category_item', '/montior/jvm/memory.htm', 'JVM内存监控', 'JVM内存监控', null, 2, 0, 1, '_self', 'icon-star', null, 1, to_date('08-04-2014 15:08:03', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-03-2015 23:32:43', 'dd-mm-yyyy hh24:mi:ss'), 0, 4, null);
insert into BAS_RESOURCE (bas_resource_id, parent_bas_resource_id, type_, path, name_zh, name_en, description, seq_num, is_leaf, editable, target, icon, permission, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (39, 36, 'module_menu_category_item', '/montior/jvm/index.htm', 'JVM监控', 'JVM监控', null, 3, 0, 1, '_self', 'icon-star', null, 1, to_date('08-04-2014 15:09:17', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('28-02-2015 15:55:25', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_RESOURCE (bas_resource_id, parent_bas_resource_id, type_, path, name_zh, name_en, description, seq_num, is_leaf, editable, target, icon, permission, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (41, 40, 'mobile_mdule_menu', 'app_store', '应用大厅', 'app_store', '手机财务应用大厅', 0, 0, 1, '_self', 'icon-star', null, 1, to_date('08-04-2014 21:21:26', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('08-04-2014 21:21:26', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_RESOURCE (bas_resource_id, parent_bas_resource_id, type_, path, name_zh, name_en, description, seq_num, is_leaf, editable, target, icon, permission, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (43, 2, 'module_menu_category_item', '/system/applicationproperty/index.htm', '项目属性配置', 'PROJECT_PRO_CONF', '项目属性配置', 6, 0, 1, '_self', 'icon-star', null, 1, to_date('17-02-2015 14:56:18', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('17-02-2015 14:56:18', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_RESOURCE (bas_resource_id, parent_bas_resource_id, type_, path, name_zh, name_en, description, seq_num, is_leaf, editable, target, icon, permission, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (44, 1, 'module_menu_category', null, '账户管理', '账户管理', null, 8, 0, 1, '_self', 'icon-user', null, 1, to_date('25-02-2015 10:53:33', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('05-03-2015 11:15:21', 'dd-mm-yyyy hh24:mi:ss'), 0, 6, null);
insert into BAS_RESOURCE (bas_resource_id, parent_bas_resource_id, type_, path, name_zh, name_en, description, seq_num, is_leaf, editable, target, icon, permission, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (45, 44, 'module_menu_category_item', '/system/account/index.htm', '个人资料', '个人资料', null, 0, 0, 1, '_self', 'icon-user', null, 1, to_date('25-02-2015 10:57:12', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('25-02-2015 13:51:59', 'dd-mm-yyyy hh24:mi:ss'), 0, 5, null);
insert into BAS_RESOURCE (bas_resource_id, parent_bas_resource_id, type_, path, name_zh, name_en, description, seq_num, is_leaf, editable, target, icon, permission, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (47, 44, 'module_menu_category_item', '/system/account/notifications.htm', '通知消息', '通知消息', null, 3, 0, 1, '_self', 'icon-bell', null, 1, to_date('25-02-2015 14:38:22', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('25-02-2015 14:38:22', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_RESOURCE (bas_resource_id, parent_bas_resource_id, type_, path, name_zh, name_en, description, seq_num, is_leaf, editable, target, icon, permission, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (48, 44, 'module_menu_category_item', '/system/account/calendar.htm', '待办事项', '待办事项', null, 1, 0, 1, '_self', 'icon-calendar', null, 1, to_date('25-02-2015 14:46:23', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('25-02-2015 14:46:23', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_RESOURCE (bas_resource_id, parent_bas_resource_id, type_, path, name_zh, name_en, description, seq_num, is_leaf, editable, target, icon, permission, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (49, 36, 'module_menu_category_item', '/druid/index.html', '数据库连接池', '数据库连接池', null, 7, 0, 1, '_blank ', 'icon-star', null, 1, to_date('28-02-2015 09:59:01', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-03-2015 23:31:03', 'dd-mm-yyyy hh24:mi:ss'), 0, 5, null);
insert into BAS_RESOURCE (bas_resource_id, parent_bas_resource_id, type_, path, name_zh, name_en, description, seq_num, is_leaf, editable, target, icon, permission, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (50, 36, 'module_menu_category_item', '/montior/hibernate/index.htm', 'Hibernate监控', 'Hibernate监控', null, 5, 0, 1, '_self', 'icon-star', null, 1, to_date('01-03-2015 02:15:07', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-03-2015 23:31:48', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_RESOURCE (bas_resource_id, parent_bas_resource_id, type_, path, name_zh, name_en, description, seq_num, is_leaf, editable, target, icon, permission, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (51, 36, 'module_menu_category_item', '/montior/cache/index.htm', '缓存监控', '缓存监控', null, 8, 0, 1, '_self', 'icon-star', null, 1, to_date('01-03-2015 23:28:04', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-03-2015 23:29:52', 'dd-mm-yyyy hh24:mi:ss'), 0, 1, null);
insert into BAS_RESOURCE (bas_resource_id, parent_bas_resource_id, type_, path, name_zh, name_en, description, seq_num, is_leaf, editable, target, icon, permission, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (52, 36, 'module_menu_category_item', '/montior/jvm/thread.htm', 'JVM线程监控', 'JVM线程监控', null, 4, 0, 1, '_self', 'icon-star', null, 1, to_date('01-03-2015 23:29:13', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('01-03-2015 23:32:59', 'dd-mm-yyyy hh24:mi:ss'), 0, 3, null);
insert into BAS_RESOURCE (bas_resource_id, parent_bas_resource_id, type_, path, name_zh, name_en, description, seq_num, is_leaf, editable, target, icon, permission, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (53, 16, 'module_menu_category_item_button', '/system/codetype/add.json', '添加', 'ADD', null, 6, 0, 1, '_self', null, 'system.codetype.add', 1, to_date('04-03-2015 21:17:01', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('04-03-2015 21:17:01', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_RESOURCE (bas_resource_id, parent_bas_resource_id, type_, path, name_zh, name_en, description, seq_num, is_leaf, editable, target, icon, permission, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (54, 16, 'module_menu_category_item_button', '/system/codetype/edit.json', '编辑', 'EDIT', null, 7, 0, 1, '_selft', null, 'sytem.codetype.edit', 1, to_date('04-03-2015 21:19:56', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('04-03-2015 21:19:56', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_RESOURCE (bas_resource_id, parent_bas_resource_id, type_, path, name_zh, name_en, description, seq_num, is_leaf, editable, target, icon, permission, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (55, 16, 'module_menu_category_item_button', '/system/codetype/list.json', '刷新', 'Refresh', null, 8, 0, 1, '_self', null, 'system.codetype.list', 1, to_date('04-03-2015 21:21:24', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('04-03-2015 21:21:24', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_RESOURCE (bas_resource_id, parent_bas_resource_id, type_, path, name_zh, name_en, description, seq_num, is_leaf, editable, target, icon, permission, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (56, 16, 'module_menu_category_item_button', '/system/codetype/export.json', 'Excel', 'Excel', null, 9, 0, 1, '_self', null, 'system.codetype.export', 1, to_date('05-03-2015 10:37:11', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('05-03-2015 10:37:11', 'dd-mm-yyyy hh24:mi:ss'), 0, 0, null);
insert into BAS_RESOURCE (bas_resource_id, parent_bas_resource_id, type_, path, name_zh, name_en, description, seq_num, is_leaf, editable, target, icon, permission, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (57, 16, 'module_menu_category_item_button', '/sytsem/codetype/delete.json', '删除', 'Delete', null, 10, 0, 1, '_self', null, 'system.codetype.delete', 1, to_date('05-03-2015 10:38:22', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('09-03-2015 16:05:34', 'dd-mm-yyyy hh24:mi:ss'), 0, 2, null);
insert into BAS_RESOURCE (bas_resource_id, parent_bas_resource_id, type_, path, name_zh, name_en, description, seq_num, is_leaf, editable, target, icon, permission, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (59, 2, 'module_menu_category_item', '/system/icon/index.htm', '图标管理', 'Icon', null, 7, 0, 1, '_self', null, null, 1, to_date('05-03-2015 16:27:06', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('05-03-2015 16:52:31', 'dd-mm-yyyy hh24:mi:ss'), 0, 4, null);
commit;

insert into BAS_ROLE (bas_role_id, name_zh, name_en, description, editable, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (1, '系统管理角色', 'SYSADMIN_ROLE', '系统管理角色', 1, 1, to_date('24-03-2013 11:34:17', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('06-03-2015 13:52:41', 'dd-mm-yyyy hh24:mi:ss'), 0, 63, null);
insert into BAS_ROLE (bas_role_id, name_zh, name_en, description, editable, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (2, '销售总经理', 'SALES_MGR', '销售总经理', 1, 1, to_date('24-03-2013 11:34:58', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('21-05-2014 17:29:27', 'dd-mm-yyyy hh24:mi:ss'), 0, 22, null);
insert into BAS_ROLE (bas_role_id, name_zh, name_en, description, editable, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (4, '邮件角色', 'EMAIL_ROLES', '邮件角色', 1, 1, to_date('10-04-2013 20:26:40', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('16-02-2015 18:03:32', 'dd-mm-yyyy hh24:mi:ss'), 0, 13, null);
insert into BAS_ROLE (bas_role_id, name_zh, name_en, description, editable, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (6, '报表管理角色', 'ReportRoles', '报表管理角色', 1, 1, to_date('10-04-2013 20:28:57', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('22-05-2014 14:24:11', 'dd-mm-yyyy hh24:mi:ss'), 0, 11, null);
insert into BAS_ROLE (bas_role_id, name_zh, name_en, description, editable, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (8, 'SUPER_ADMIN', 'SUPER_ADMIN1', 'SUPER_ADMIN', 0, 1, to_date('28-03-2014 22:35:19', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('10-03-2015 18:03:58', 'dd-mm-yyyy hh24:mi:ss'), 0, 22, null);
insert into BAS_ROLE (bas_role_id, name_zh, name_en, description, editable, create_by, create_date, update_by, update_date, mark_for_delete, opt_counter, delete_date)
values (10, 'demo', 'demo', 'demo', 1, 1, to_date('22-05-2014 17:51:18', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('04-03-2015 21:01:15', 'dd-mm-yyyy hh24:mi:ss'), 0, 3, null);
commit;

insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (1, 1);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (2, 1);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (9, 1);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (10, 1);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (11, 1);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (12, 1);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (13, 1);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (14, 1);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (15, 1);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (16, 1);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (17, 1);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (18, 1);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (19, 1);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (20, 1);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (21, 1);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (24, 1);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (26, 1);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (36, 1);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (37, 1);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (38, 1);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (39, 1);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (43, 1);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (44, 1);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (45, 1);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (47, 1);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (48, 1);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (49, 1);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (50, 1);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (51, 1);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (52, 1);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (53, 1);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (54, 1);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (55, 1);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (56, 1);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (57, 1);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (59, 1);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (12, 2);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (14, 2);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (17, 2);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (18, 2);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (24, 2);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (25, 2);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (27, 2);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (9, 4);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (11, 4);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (12, 4);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (14, 4);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (15, 4);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (17, 4);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (18, 4);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (19, 4);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (24, 4);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (25, 4);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (26, 4);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (36, 4);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (37, 4);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (38, 4);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (39, 4);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (18, 6);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (24, 6);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (38, 6);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (1, 8);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (2, 8);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (9, 8);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (10, 8);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (11, 8);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (12, 8);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (13, 8);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (14, 8);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (15, 8);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (16, 8);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (18, 8);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (19, 8);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (26, 8);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (36, 8);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (37, 8);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (38, 8);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (39, 8);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (43, 8);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (44, 8);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (45, 8);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (47, 8);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (48, 8);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (49, 8);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (50, 8);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (51, 8);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (52, 8);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (53, 8);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (55, 8);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (56, 8);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (57, 8);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (59, 8);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (11, 10);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (15, 10);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (26, 10);
insert into BAS_ROLE_RESOURCE (bas_resource_id, bas_role_id)
values (38, 10);
commit;
insert into BAS_USER (bas_user_id, bas_department_id, bas_position_id, manager_user_id, username, password, password_hint, employee_no, realname_zh, realname_en, gender, usertype, authtype, telephone, mobile, email, office_telephone, fax, postcode, location, account_expired, account_locked, credential_expired, credential_expired_date, account_expired_date, editable, description, image, create_by, create_date, update_by, update_date, opt_counter, delete_date, mark_for_delete)
values (1, 9, null, null, 'wanglijun', 'c150916baa97eeccd1d99541aad26170761b41a9', null, '0000001', '王立君', 'wanglijun', 0, null, null, '021-234298379', '15821501966', 'wanglijun@126.com', '021-234298379', '021-234298379', '293482', 'eeeeewww', 1, 0, 1, to_date('03-04-2015 21:18:25', 'dd-mm-yyyy hh24:mi:ss'), to_date('03-04-2015 21:18:25', 'dd-mm-yyyy hh24:mi:ss'), 1, 'ee111', null, 1, to_date('23-03-2013 21:08:49', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('26-02-2015 15:45:21', 'dd-mm-yyyy hh24:mi:ss'), 117, null, 0);
insert into BAS_USER (bas_user_id, bas_department_id, bas_position_id, manager_user_id, username, password, password_hint, employee_no, realname_zh, realname_en, gender, usertype, authtype, telephone, mobile, email, office_telephone, fax, postcode, location, account_expired, account_locked, credential_expired, credential_expired_date, account_expired_date, editable, description, image, create_by, create_date, update_by, update_date, opt_counter, delete_date, mark_for_delete)
values (2, 18, null, null, 'duxusc', 'c150916baa97eeccd1d99541aad26170761b41a9', null, '0000002', '杜旭', 'duxusc', 0, null, null, null, null, 'duxusc@126.com', null, null, null, null, 1, 0, 1, to_date('03-04-2015 21:18:25', 'dd-mm-yyyy hh24:mi:ss'), to_date('03-04-2015 21:18:25', 'dd-mm-yyyy hh24:mi:ss'), 1, null, null, 1, to_date('23-03-2013 21:16:44', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('07-02-2015 14:49:09', 'dd-mm-yyyy hh24:mi:ss'), 14, null, 0);
insert into BAS_USER (bas_user_id, bas_department_id, bas_position_id, manager_user_id, username, password, password_hint, employee_no, realname_zh, realname_en, gender, usertype, authtype, telephone, mobile, email, office_telephone, fax, postcode, location, account_expired, account_locked, credential_expired, credential_expired_date, account_expired_date, editable, description, image, create_by, create_date, update_by, update_date, opt_counter, delete_date, mark_for_delete)
values (3, 21, null, null, 'even', '1ec99bbee04c68ff0b0b5119260dba30e0e2bf29', null, '222222', 'zhjwww', 'zhjwww', 0, null, null, null, null, 'even@126.com', null, null, null, null, 1, 0, 1, to_date('03-04-2015', 'dd-mm-yyyy'), to_date('10-04-2015', 'dd-mm-yyyy'), 1, null, null, 1, to_date('24-03-2013 14:18:47', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('02-03-2015 21:22:31', 'dd-mm-yyyy hh24:mi:ss'), 6, null, 0);
insert into BAS_USER (bas_user_id, bas_department_id, bas_position_id, manager_user_id, username, password, password_hint, employee_no, realname_zh, realname_en, gender, usertype, authtype, telephone, mobile, email, office_telephone, fax, postcode, location, account_expired, account_locked, credential_expired, credential_expired_date, account_expired_date, editable, description, image, create_by, create_date, update_by, update_date, opt_counter, delete_date, mark_for_delete)
values (4, 20, null, null, '000000009', 'c150916baa97eeccd1d99541aad26170761b41a9', null, '000000009', '000000009', '000000009', 0, null, null, null, null, '000000009@126.com', null, null, null, null, 1, 0, 1, to_date('03-07-2014 15:34:36', 'dd-mm-yyyy hh24:mi:ss'), to_date('03-04-2015 15:34:36', 'dd-mm-yyyy hh24:mi:ss'), 1, null, null, 1, to_date('03-04-2014 15:34:36', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('26-02-2015 15:56:57', 'dd-mm-yyyy hh24:mi:ss'), 12, null, 0);
insert into BAS_USER (bas_user_id, bas_department_id, bas_position_id, manager_user_id, username, password, password_hint, employee_no, realname_zh, realname_en, gender, usertype, authtype, telephone, mobile, email, office_telephone, fax, postcode, location, account_expired, account_locked, credential_expired, credential_expired_date, account_expired_date, editable, description, image, create_by, create_date, update_by, update_date, opt_counter, delete_date, mark_for_delete)
values (5, 18, null, null, '00000008', 'c150916baa97eeccd1d99541aad26170761b41a9', null, '000000081', '000000081', '00000008', 0, null, null, null, null, '00000008@126.com', null, null, null, null, 1, 0, 1, to_date('03-07-2014 21:18:25', 'dd-mm-yyyy hh24:mi:ss'), to_date('03-04-2015 21:18:25', 'dd-mm-yyyy hh24:mi:ss'), 1, null, null, 1, to_date('03-04-2014 21:18:25', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('07-02-2015 14:35:26', 'dd-mm-yyyy hh24:mi:ss'), 2, null, 0);
insert into BAS_USER (bas_user_id, bas_department_id, bas_position_id, manager_user_id, username, password, password_hint, employee_no, realname_zh, realname_en, gender, usertype, authtype, telephone, mobile, email, office_telephone, fax, postcode, location, account_expired, account_locked, credential_expired, credential_expired_date, account_expired_date, editable, description, image, create_by, create_date, update_by, update_date, opt_counter, delete_date, mark_for_delete)
values (6, 9, null, null, '000000007', '6ba7d6391334202082a34be8f2fed881a70391fb', null, '0000000071555', '0000000071', '000000007', 1, null, null, null, null, '000000007@gmail.com', null, null, null, null, 1, 0, 1, to_date('07-07-2014 18:19:43', 'dd-mm-yyyy hh24:mi:ss'), to_date('07-04-2015 18:19:43', 'dd-mm-yyyy hh24:mi:ss'), 1, null, null, 1, to_date('07-04-2014 18:19:43', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('02-03-2015 16:06:01', 'dd-mm-yyyy hh24:mi:ss'), 14, null, 0);
insert into BAS_USER (bas_user_id, bas_department_id, bas_position_id, manager_user_id, username, password, password_hint, employee_no, realname_zh, realname_en, gender, usertype, authtype, telephone, mobile, email, office_telephone, fax, postcode, location, account_expired, account_locked, credential_expired, credential_expired_date, account_expired_date, editable, description, image, create_by, create_date, update_by, update_date, opt_counter, delete_date, mark_for_delete)
values (7, 8, null, null, 'admin', 'c150916baa97eeccd1d99541aad26170761b41a9', null, '00000001', '超级系统管理员', 'sysadmin', 1, null, null, null, null, null, null, null, null, null, 1, 0, 1, to_date('08-07-2014 21:03:36', 'dd-mm-yyyy hh24:mi:ss'), to_date('08-04-2015 21:03:36', 'dd-mm-yyyy hh24:mi:ss'), 1, null, null, 1, to_date('08-04-2014 21:03:36', 'dd-mm-yyyy hh24:mi:ss'), 7, to_date('22-05-2014 10:44:27', 'dd-mm-yyyy hh24:mi:ss'), 10, null, 0);
insert into BAS_USER (bas_user_id, bas_department_id, bas_position_id, manager_user_id, username, password, password_hint, employee_no, realname_zh, realname_en, gender, usertype, authtype, telephone, mobile, email, office_telephone, fax, postcode, location, account_expired, account_locked, credential_expired, credential_expired_date, account_expired_date, editable, description, image, create_by, create_date, update_by, update_date, opt_counter, delete_date, mark_for_delete)
values (8, 20, null, null, 'demo', '7d67e4df76211d82086965b536ceebdab48a2933', null, 'demo11', 'demo', 'demo', 1, null, null, null, null, null, null, null, null, null, 1, 0, 1, to_date('22-08-2014 11:05:28', 'dd-mm-yyyy hh24:mi:ss'), to_date('22-05-2015 11:05:28', 'dd-mm-yyyy hh24:mi:ss'), 1, null, null, 1, to_date('22-05-2014 11:05:28', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('02-03-2015 15:25:58', 'dd-mm-yyyy hh24:mi:ss'), 3, null, 0);
insert into BAS_USER (bas_user_id, bas_department_id, bas_position_id, manager_user_id, username, password, password_hint, employee_no, realname_zh, realname_en, gender, usertype, authtype, telephone, mobile, email, office_telephone, fax, postcode, location, account_expired, account_locked, credential_expired, credential_expired_date, account_expired_date, editable, description, image, create_by, create_date, update_by, update_date, opt_counter, delete_date, mark_for_delete)
values (14, 3, null, null, 'dddddd', '11ab9e5b844792968c48b64a8da1c1dc654f1cac', null, 'dddddd', 'ddddd', 'ddddd', 0, null, null, null, null, 'dddddd@126.com', null, null, null, null, 1, 0, 1, to_date('04-05-2015 14:49:29', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-02-2016 14:49:29', 'dd-mm-yyyy hh24:mi:ss'), 1, null, null, 1, to_date('04-02-2015 14:49:29', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('04-02-2015 14:49:29', 'dd-mm-yyyy hh24:mi:ss'), 0, null, 0);
insert into BAS_USER (bas_user_id, bas_department_id, bas_position_id, manager_user_id, username, password, password_hint, employee_no, realname_zh, realname_en, gender, usertype, authtype, telephone, mobile, email, office_telephone, fax, postcode, location, account_expired, account_locked, credential_expired, credential_expired_date, account_expired_date, editable, description, image, create_by, create_date, update_by, update_date, opt_counter, delete_date, mark_for_delete)
values (15, 11, null, null, 'wanglijun11', '4bd8e01746d825fc1ae538971b0bc750c857151d', null, 'dddd', 'dddd', 'dddd', 0, null, null, 'dddd', '15502152629', 'wanglijun11@126.com', '15502152629', '15502152629', '123333', 'china', 1, 0, 1, to_date('04-05-2015 14:51:14', 'dd-mm-yyyy hh24:mi:ss'), to_date('04-02-2016 14:51:14', 'dd-mm-yyyy hh24:mi:ss'), 1, '2222', null, 1, to_date('04-02-2015 14:51:14', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('04-03-2015 21:33:37', 'dd-mm-yyyy hh24:mi:ss'), 12, null, 0);
insert into BAS_USER (bas_user_id, bas_department_id, bas_position_id, manager_user_id, username, password, password_hint, employee_no, realname_zh, realname_en, gender, usertype, authtype, telephone, mobile, email, office_telephone, fax, postcode, location, account_expired, account_locked, credential_expired, credential_expired_date, account_expired_date, editable, description, image, create_by, create_date, update_by, update_date, opt_counter, delete_date, mark_for_delete)
values (16, 11, null, null, 'wanglijun1111', '307fdb5468d2c4abc2b1bb272b0cea12c371c35e', null, '111111', null, null, 0, null, null, null, null, 'scwanglijun@gmail.com', null, null, null, null, 1, 0, 1, to_date('15-05-2015 22:54:52', 'dd-mm-yyyy hh24:mi:ss'), to_date('15-02-2016 22:54:52', 'dd-mm-yyyy hh24:mi:ss'), 1, null, null, 1, to_date('15-02-2015 22:54:52', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('16-02-2015 15:32:20', 'dd-mm-yyyy hh24:mi:ss'), 4, null, 0);
insert into BAS_USER (bas_user_id, bas_department_id, bas_position_id, manager_user_id, username, password, password_hint, employee_no, realname_zh, realname_en, gender, usertype, authtype, telephone, mobile, email, office_telephone, fax, postcode, location, account_expired, account_locked, credential_expired, credential_expired_date, account_expired_date, editable, description, image, create_by, create_date, update_by, update_date, opt_counter, delete_date, mark_for_delete)
values (17, 20, null, null, 'wangkexin', '4393bb1f4a1896957e5559e6a929e6c835017013', null, '00001', 'wangkexin', 'wangkexin', 0, null, null, null, null, 'wangkexin@gmail.com', null, null, null, null, 1, 0, 1, to_date('27-05-2015', 'dd-mm-yyyy'), to_date('15-07-2016', 'dd-mm-yyyy'), 1, null, null, 1, to_date('27-02-2015 17:22:57', 'dd-mm-yyyy hh24:mi:ss'), 1, to_date('10-03-2015 16:58:19', 'dd-mm-yyyy hh24:mi:ss'), 10, null, 0);
commit;
insert into BAS_USER_GROUP (bas_group_id, bas_user_id)
values (1, 1);
insert into BAS_USER_GROUP (bas_group_id, bas_user_id)
values (2, 1);
insert into BAS_USER_GROUP (bas_group_id, bas_user_id)
values (3, 1);
insert into BAS_USER_GROUP (bas_group_id, bas_user_id)
values (4, 1);
insert into BAS_USER_GROUP (bas_group_id, bas_user_id)
values (5, 1);
insert into BAS_USER_GROUP (bas_group_id, bas_user_id)
values (6, 1);
insert into BAS_USER_GROUP (bas_group_id, bas_user_id)
values (1, 2);
insert into BAS_USER_GROUP (bas_group_id, bas_user_id)
values (2, 2);
insert into BAS_USER_GROUP (bas_group_id, bas_user_id)
values (3, 2);
insert into BAS_USER_GROUP (bas_group_id, bas_user_id)
values (4, 2);
insert into BAS_USER_GROUP (bas_group_id, bas_user_id)
values (5, 2);
insert into BAS_USER_GROUP (bas_group_id, bas_user_id)
values (6, 2);
insert into BAS_USER_GROUP (bas_group_id, bas_user_id)
values (1, 3);
insert into BAS_USER_GROUP (bas_group_id, bas_user_id)
values (1, 4);
insert into BAS_USER_GROUP (bas_group_id, bas_user_id)
values (6, 4);
insert into BAS_USER_GROUP (bas_group_id, bas_user_id)
values (1, 5);
insert into BAS_USER_GROUP (bas_group_id, bas_user_id)
values (1, 6);
insert into BAS_USER_GROUP (bas_group_id, bas_user_id)
values (2, 6);
insert into BAS_USER_GROUP (bas_group_id, bas_user_id)
values (1, 7);
insert into BAS_USER_GROUP (bas_group_id, bas_user_id)
values (1, 8);
insert into BAS_USER_GROUP (bas_group_id, bas_user_id)
values (1, 15);
insert into BAS_USER_GROUP (bas_group_id, bas_user_id)
values (1, 16);
insert into BAS_USER_GROUP (bas_group_id, bas_user_id)
values (2, 16);
insert into BAS_USER_GROUP (bas_group_id, bas_user_id)
values (3, 16);
insert into BAS_USER_GROUP (bas_group_id, bas_user_id)
values (6, 16);
insert into BAS_USER_GROUP (bas_group_id, bas_user_id)
values (16, 16);
commit;
insert into BAS_USER_ROLE (bas_role_id, bas_user_id)
values (1, 1);
insert into BAS_USER_ROLE (bas_role_id, bas_user_id)
values (2, 1);
insert into BAS_USER_ROLE (bas_role_id, bas_user_id)
values (4, 1);
insert into BAS_USER_ROLE (bas_role_id, bas_user_id)
values (6, 1);
insert into BAS_USER_ROLE (bas_role_id, bas_user_id)
values (8, 1);
insert into BAS_USER_ROLE (bas_role_id, bas_user_id)
values (1, 2);
insert into BAS_USER_ROLE (bas_role_id, bas_user_id)
values (2, 2);
insert into BAS_USER_ROLE (bas_role_id, bas_user_id)
values (4, 2);
insert into BAS_USER_ROLE (bas_role_id, bas_user_id)
values (8, 2);
insert into BAS_USER_ROLE (bas_role_id, bas_user_id)
values (9, 2);
insert into BAS_USER_ROLE (bas_role_id, bas_user_id)
values (1, 3);
insert into BAS_USER_ROLE (bas_role_id, bas_user_id)
values (4, 3);
insert into BAS_USER_ROLE (bas_role_id, bas_user_id)
values (1, 4);
insert into BAS_USER_ROLE (bas_role_id, bas_user_id)
values (8, 4);
insert into BAS_USER_ROLE (bas_role_id, bas_user_id)
values (2, 6);
insert into BAS_USER_ROLE (bas_role_id, bas_user_id)
values (10, 8);
insert into BAS_USER_ROLE (bas_role_id, bas_user_id)
values (1, 15);
insert into BAS_USER_ROLE (bas_role_id, bas_user_id)
values (2, 15);
insert into BAS_USER_ROLE (bas_role_id, bas_user_id)
values (4, 15);
insert into BAS_USER_ROLE (bas_role_id, bas_user_id)
values (6, 15);
insert into BAS_USER_ROLE (bas_role_id, bas_user_id)
values (8, 15);
insert into BAS_USER_ROLE (bas_role_id, bas_user_id)
values (1, 16);
insert into BAS_USER_ROLE (bas_role_id, bas_user_id)
values (2, 16);
insert into BAS_USER_ROLE (bas_role_id, bas_user_id)
values (4, 16);
insert into BAS_USER_ROLE (bas_role_id, bas_user_id)
values (6, 16);
insert into BAS_USER_ROLE (bas_role_id, bas_user_id)
values (8, 16);
insert into BAS_USER_ROLE (bas_role_id, bas_user_id)
values (8, 17);
commit;
alter table BAS_DEMO enable constraint FK_MGIHAFSPYQV9K6B08L0JKFGEU;
alter table BAS_APP_PROPERTIES enable all triggers;
alter table BAS_CODE_LIST enable all triggers;
alter table BAS_CODE_TYPE enable all triggers;
alter table BAS_COLUMN enable all triggers;
alter table BAS_DATAGRID enable all triggers;
alter table BAS_DEMO enable all triggers;
alter table BAS_DEPARTMENT enable all triggers;
alter table BAS_GROUP enable all triggers;
alter table BAS_GROUP_ROLE enable all triggers;
alter table BAS_ICON enable all triggers;
alter table BAS_LOGIN_LOG enable all triggers;
alter table BAS_PARAMETER enable all triggers;
alter table BAS_POSITION enable all triggers;
alter table BAS_REMINDER_BODY enable all triggers;
alter table BAS_REMINDER_READER enable all triggers;
alter table BAS_RESOURCE enable all triggers;
alter table BAS_ROLE enable all triggers;
alter table BAS_ROLE_RESOURCE enable all triggers;
alter table BAS_TIPTEXT enable all triggers;
alter table BAS_USER enable all triggers;
alter table BAS_USER_GROUP enable all triggers;
alter table BAS_USER_ROLE enable all triggers;

