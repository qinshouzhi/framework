/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : newtouch_lion

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2015-01-08 17:10:57
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `bas_app_properties`
-- ----------------------------
DROP TABLE IF EXISTS `bas_app_properties`;
CREATE TABLE `bas_app_properties` (
  `APP_PROPERTIES_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `APP_ID` varchar(20) NOT NULL DEFAULT '' COMMENT '应用名称',
  `KEY_` varchar(120) NOT NULL DEFAULT '' COMMENT '参数名称',
  `VALUE_` varchar(225) NOT NULL COMMENT '数参值',
  `DESCRIPTION` varchar(225) DEFAULT NULL COMMENT '数参描述',
  PRIMARY KEY (`APP_PROPERTIES_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='应用参数表';

-- ----------------------------
-- Records of bas_app_properties
-- ----------------------------
INSERT INTO `bas_app_properties` VALUES ('1', 'lion', 'hibernate.cache.provider_class', 'net.sf.ehcache.hibernate.SingletonEhCacheProvider', '配置使用二级缓存的类－提供商');
INSERT INTO `bas_app_properties` VALUES ('2', 'lion', 'hibernate.cache.region.factory_class', 'net.sf.ehcache.hibernate.EhCacheRegionFactory', '解决使用echache2.5以后的版本时出现的异常问题 ');
INSERT INTO `bas_app_properties` VALUES ('3', 'lion', 'hibernate.cache.use_minimal_puts', 'true', '以频繁的读操作为代价，优化二级缓存来最小化写操作，默认是开启的，取值为：true|false');
INSERT INTO `bas_app_properties` VALUES ('4', 'lion', 'hibernate.cache.use_query_cache', 'false', '是否开启一级缓存');
INSERT INTO `bas_app_properties` VALUES ('5', 'lion', 'hibernate.cache.use_second_level_cache', 'false', '是否开启二级缓存');
INSERT INTO `bas_app_properties` VALUES ('6', 'lion', 'hibernate.dialect', 'org.hibernate.dialect.MySQLDialect', 'Hibernate SQL方言');
INSERT INTO `bas_app_properties` VALUES ('7', 'lion', 'hibernate.format_sql', 'true', '是否格式SQL');
INSERT INTO `bas_app_properties` VALUES ('8', 'lion', 'hibernate.generate_statistics', 'true', '如果开启，Hibernate将收集有助性能调节的统计数据');
INSERT INTO `bas_app_properties` VALUES ('9', 'lion', 'hibernate.hbm2ddl.auto', 'none', '在SessionFactory创建时，自动检查数据库结构，或者将数据库schema的DDL导出数据库，使用create-drop时，在显示关闭SessionFactory时，将drop掉数据库schema，取值:vaildate|update|create|create-drop|no');
INSERT INTO `bas_app_properties` VALUES ('10', 'lion', 'hibernate.jdbc.batch_size', '50', '允许Hibernate使用JDBC的批量更新，取值建议在5到50之间的值');
INSERT INTO `bas_app_properties` VALUES ('11', 'lion', 'hibernate.jdbc.fetch_size', '100', '设置JDBC抓取数量的大小（调用Statement.setFetchSize()）');
INSERT INTO `bas_app_properties` VALUES ('12', 'lion', 'hibernate.max_fetch_depth', '3', '设置外连接抓取的最大深度取值，建议设置为0~3之间');
INSERT INTO `bas_app_properties` VALUES ('13', 'lion', 'hibernate.order-updates', 'true', '强制Hibernate按照被更新数据的主键，为SQL更新排序，将减少在高并发系统中事务的死锁。取值:true|false');
INSERT INTO `bas_app_properties` VALUES ('14', 'lion', 'hibernate.show_sql', 'true', '是否显示SQL');
INSERT INTO `bas_app_properties` VALUES ('15', 'lion', 'hibernate.use_sql_comments', 'true', '如果开启，Hibernate将在SQL中生成有助于调试的注释信息，默认值为：false,取值：true|false');
INSERT INTO `bas_app_properties` VALUES ('16', 'lion', 'database.prefix', 'mysql', 'ORM配置文件前缀，用于切换不同数据库');

-- ----------------------------
-- Table structure for `bas_code_list`
-- ----------------------------
DROP TABLE IF EXISTS `bas_code_list`;
CREATE TABLE `bas_code_list` (
  `BAS_CODE_LIST_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `BAS_CODE_TYPE_ID` int(11) DEFAULT NULL COMMENT '表ID',
  `CODE_VALUE` varchar(255) DEFAULT NULL COMMENT '编码值',
  `NAME_ZH` varchar(255) DEFAULT NULL COMMENT '编码中文名称',
  `NAME_EN` varchar(255) DEFAULT NULL COMMENT '编码英文名称',
  `SORT_NO` int(11) DEFAULT NULL COMMENT '排序编号',
  `CREATE_BY` int(11) NOT NULL COMMENT '系统默认字段.创建人ID',
  `CREATE_DATE` datetime NOT NULL COMMENT '系统默认字段.创建日期',
  `UPDATE_BY` int(11) NOT NULL COMMENT '系统默认字段.更新人ID',
  `UPDATE_DATE` datetime NOT NULL COMMENT '系统默认字段.更新日期',
  `MARK_FOR_DELETE` int(1) DEFAULT '0' COMMENT '系统默认字段.逻辑删除标记  0 未删除   1 表示删除',
  `OPT_COUNTER` int(6) DEFAULT '0' COMMENT '系统默认字段.版本记录',
  `DELETE_DATE` datetime DEFAULT NULL COMMENT '系统默认字段.删除日期',
  `EDITABLE` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否编辑',
  `SELECTED` tinyint(1) DEFAULT NULL COMMENT 'true 默认选择项',
  PRIMARY KEY (`BAS_CODE_LIST_ID`),
  UNIQUE KEY `AK_UK_BAS_CODE_LIST_NAME_ZH` (`NAME_ZH`),
  UNIQUE KEY `UK_BAS_CODE_LIST_NAME_EN` (`NAME_EN`),
  KEY `FK_BAS_CODE_TYPE_ID_001` (`BAS_CODE_TYPE_ID`),
  CONSTRAINT `FK_BAS_CODE_TYPE_ID_001` FOREIGN KEY (`BAS_CODE_TYPE_ID`) REFERENCES `bas_code_type` (`BAS_CODE_TYPE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8 COMMENT='通用编码表';

-- ----------------------------
-- Records of bas_code_list
-- ----------------------------
INSERT INTO `bas_code_list` VALUES ('1', '19', 'Users', '用户', 'Users', '2', '1', '2013-03-19 17:15:16', '1', '2014-04-08 10:28:59', '0', '3', null, '1', '0');
INSERT INTO `bas_code_list` VALUES ('2', '19', 'file', '文件类', 'Files', '1', '1', '2013-03-19 17:19:03', '1', '2014-04-08 10:28:53', '0', '2', null, '1', '0');
INSERT INTO `bas_code_list` VALUES ('3', '19', 'systems', '系统类', 'systems', '3', '1', '2013-03-19 20:42:18', '1', '2014-04-08 10:55:56', '0', '9', null, '1', '1');
INSERT INTO `bas_code_list` VALUES ('11', '21', 'system', '系统类型', 'system', '1', '1', '2013-03-20 18:03:39', '1', '2014-04-08 13:54:54', '0', '6', null, '1', '0');
INSERT INTO `bas_code_list` VALUES ('12', '21', 'Products', '产品管理', 'Products', '2', '1', '2013-03-20 18:04:45', '1', '2013-03-20 18:04:45', '0', '0', null, '1', '0');
INSERT INTO `bas_code_list` VALUES ('13', '21', 'Customers', '客户管理', 'Customers', '3', '1', '2013-03-20 18:06:49', '1', '2013-03-24 21:43:12', '0', '1', null, '0', '0');
INSERT INTO `bas_code_list` VALUES ('14', '21', 'ReportsCenter', '报表中心', 'Reports Center', '4', '1', '2013-03-20 18:07:36', '1', '2013-03-29 22:33:34', '0', '3', null, '1', '0');
INSERT INTO `bas_code_list` VALUES ('15', '32', '_blank', '_blank', '_blank', '1', '1', '2013-03-24 18:03:14', '1', '2013-03-29 23:17:44', '0', '2', null, '1', '0');
INSERT INTO `bas_code_list` VALUES ('17', '32', '_self', '_self', '_self', '2', '1', '2013-03-24 18:22:46', '1', '2013-03-24 18:22:46', '0', '0', null, '1', '0');
INSERT INTO `bas_code_list` VALUES ('18', '32', '_parent', '_parent', '_parent', '3', '1', '2013-03-24 18:29:43', '1', '2013-03-24 18:29:43', '0', '0', null, '1', '0');
INSERT INTO `bas_code_list` VALUES ('19', '21', 'CustomerCenter', '客户中心', 'CustomerCenter', '2', '1', '2013-03-24 18:53:21', '1', '2013-03-24 18:53:21', '0', '0', null, '1', '0');
INSERT INTO `bas_code_list` VALUES ('21', '33', 'application', '应用层', 'application', '1', '1', '2013-03-24 21:51:33', '1', '2013-03-24 22:12:27', '0', '2', null, '1', '0');
INSERT INTO `bas_code_list` VALUES ('22', '33', 'module', '模块', 'module', '2', '1', '2013-03-24 22:06:47', '1', '2014-04-08 10:30:45', '0', '2', null, '1', '0');
INSERT INTO `bas_code_list` VALUES ('23', '33', 'module_menu_category', '菜单项', 'module_menu_category', '3', '1', '2013-03-24 22:12:07', '1', '2014-04-08 14:10:05', '0', '2', null, '1', '0');
INSERT INTO `bas_code_list` VALUES ('24', '34', 'text', 'text', 'text', '0', '1', '2013-03-27 22:47:22', '1', '2013-03-29 22:21:34', '0', '1', null, '1', '0');
INSERT INTO `bas_code_list` VALUES ('25', '34', 'textarea', 'textarea', 'textarea', '1', '1', '2013-03-27 22:47:58', '1', '2013-03-27 22:47:58', '0', '0', null, '1', '0');
INSERT INTO `bas_code_list` VALUES ('26', '34', 'checkbox', 'checkbox', 'checkbox', '3', '1', '2013-03-27 22:48:32', '1', '2013-03-27 22:48:32', '0', '0', null, '1', '0');
INSERT INTO `bas_code_list` VALUES ('27', '34', 'numberbox', 'numberbox', 'numberbox', '2', '1', '2013-03-27 22:50:02', '1', '2013-03-27 22:50:02', '0', '0', null, '1', '0');
INSERT INTO `bas_code_list` VALUES ('28', '34', 'validatebox', 'validatebox', 'validatebox', '4', '1', '2013-03-27 22:50:37', '1', '2013-03-27 22:50:37', '0', '0', null, '1', '0');
INSERT INTO `bas_code_list` VALUES ('29', '34', 'datebox', 'datebox', 'datebox', '5', '1', '2013-03-27 22:50:54', '1', '2013-03-27 22:50:54', '0', '0', null, '1', '0');
INSERT INTO `bas_code_list` VALUES ('30', '34', 'combobox', 'combobox', 'combobox', '6', '1', '2013-03-27 22:51:17', '1', '2013-03-27 22:51:17', '0', '0', null, '1', '0');
INSERT INTO `bas_code_list` VALUES ('31', '34', 'combotree', 'combotree', 'combotree', '7', '1', '2013-03-27 22:51:47', '1', '2013-03-27 22:51:47', '0', '0', null, '1', '0');
INSERT INTO `bas_code_list` VALUES ('32', '35', 'top', 'top', 'top', '1', '1', '2013-03-28 22:32:04', '1', '2013-03-28 22:32:04', '0', '0', null, '1', '0');
INSERT INTO `bas_code_list` VALUES ('33', '35', 'bottom', 'bottom', 'bottom', '2', '1', '2013-03-28 22:32:21', '1', '2013-03-29 23:19:34', '0', '2', null, '1', '1');
INSERT INTO `bas_code_list` VALUES ('34', '35', 'both', 'both', 'both', '3', '1', '2013-03-28 22:32:43', '1', '2013-03-29 23:18:09', '0', '1', null, '1', '0');
INSERT INTO `bas_code_list` VALUES ('35', '36', '[10,20,30,40,50]', '[10,20,30,40,50]', '[10,20,30,40,50]', '1', '1', '2013-03-28 22:34:14', '1', '2013-03-28 22:34:14', '0', '0', null, '1', null);
INSERT INTO `bas_code_list` VALUES ('36', '36', '[5,10,15,20,25,30,40,50]', '[5,10,15,20,25,30,40,50]', '[5,10,15,20,25,30,40,50]', '2', '1', '2013-03-28 22:35:28', '1', '2013-03-28 22:35:28', '0', '0', null, '1', null);
INSERT INTO `bas_code_list` VALUES ('37', '36', '[10,15,20,25,30,40,50,100]', '[10,15,20,25,30,40,50,100]', '[10,15,20,25,30,40,50,100]', '0', '1', '2013-03-28 22:36:24', '1', '2013-03-29 22:28:31', '0', '3', null, '1', '1');
INSERT INTO `bas_code_list` VALUES ('40', '33', 'module_menu_category_item', '菜单子项', 'module_menu_category_item', '4', '1', '2014-04-08 14:09:11', '1', '2014-04-08 14:10:11', '0', '1', null, '1', '0');
INSERT INTO `bas_code_list` VALUES ('41', '33', 'mobile_mdule', '移动应用', 'mobile_mdule', '1', '1', '2014-04-08 21:14:06', '1', '2014-04-08 21:14:06', '0', '0', null, '1', '0');
INSERT INTO `bas_code_list` VALUES ('42', '33', 'mobile_mdule_menu', ' 移动应用菜单项', 'mobile_mdule_menu', '5', '1', '2014-04-08 21:15:06', '1', '2014-04-08 21:15:06', '0', '0', null, '1', '0');
INSERT INTO `bas_code_list` VALUES ('43', '33', 'mobile_mdule_menu_item', '移动应用菜单子项', 'mobile_mdule_menu_item', '0', '1', '2014-04-08 21:15:44', '1', '2014-04-08 21:20:00', '0', '2', null, '1', '0');
INSERT INTO `bas_code_list` VALUES ('44', '37', 'users_datagrid', '用户管理', 'users_datagrid', '0', '1', '2014-04-08 23:09:47', '1', '2014-04-08 23:11:08', '0', '1', null, '1', '0');
INSERT INTO `bas_code_list` VALUES ('45', '37', 'code_datagrid', '编码管理', 'code_datagrid', '1', '1', '2014-04-08 23:10:22', '1', '2014-04-08 23:11:03', '0', '1', null, '1', '0');
INSERT INTO `bas_code_list` VALUES ('46', '37', 'system_datagrid', '系统管理', 'system_datagrid', '2', '1', '2014-04-08 23:11:52', '1', '2014-04-08 23:11:52', '0', '0', null, '1', '0');
INSERT INTO `bas_code_list` VALUES ('47', '37', 'datagrid_datagrid ', 'DataGrid管理', 'datagrid_datagrid ', '3', '1', '2014-04-08 23:12:43', '1', '2014-04-08 23:26:15', '0', '1', null, '1', '0');
INSERT INTO `bas_code_list` VALUES ('48', '37', 'SYSTEM_MINITOR', '系统监控管理', 'SYSTEM_MINITOR', '4', '1', '2014-04-08 23:13:18', '1', '2014-04-08 23:13:18', '0', '0', null, '1', '0');
INSERT INTO `bas_code_list` VALUES ('49', '19', 'sysInterface', '接口管理', 'sysInterface', '3', '1', '2014-06-01 21:48:36', '1', '2014-06-01 21:48:36', '0', '0', null, '1', '1');

-- ----------------------------
-- Table structure for `bas_code_type`
-- ----------------------------
DROP TABLE IF EXISTS `bas_code_type`;
CREATE TABLE `bas_code_type` (
  `BAS_CODE_TYPE_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '表ID',
  `CODE_TYPE` varchar(20) DEFAULT NULL COMMENT '编码类型',
  `NAME_ZH` varchar(128) DEFAULT NULL COMMENT '编码中文名称',
  `NAME_EN` varchar(128) DEFAULT NULL COMMENT '编码英文名称',
  `EDITABLE` tinyint(1) DEFAULT NULL COMMENT '该记录是否可编辑',
  `CODE_LEN_LIMIT` int(11) DEFAULT NULL COMMENT '编码的值长度约束',
  `CREATE_BY` int(11) NOT NULL COMMENT '系统默认字段.创建人ID',
  `CREATE_DATE` datetime NOT NULL COMMENT '系统默认字段.创建日期',
  `UPDATE_BY` int(11) NOT NULL COMMENT '系统默认字段.更新人ID',
  `UPDATE_DATE` datetime NOT NULL COMMENT '系统默认字段.更新日期',
  `MARK_FOR_DELETE` int(1) DEFAULT '0' COMMENT '系统默认字段.逻辑删除标记  0 未删除   1 表示删除',
  `OPT_COUNTER` int(6) DEFAULT '0' COMMENT '系统默认字段.版本记录',
  `DELETE_DATE` datetime DEFAULT NULL COMMENT '系统默认字段.删除日期',
  PRIMARY KEY (`BAS_CODE_TYPE_ID`),
  UNIQUE KEY `UK_BAS_CODE_TYPE_NAME_ZH` (`NAME_ZH`),
  UNIQUE KEY `UK_BAS_CODE_TYPE_NAME_EN` (`NAME_EN`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COMMENT='通用编码类型定义表';

-- ----------------------------
-- Records of bas_code_type
-- ----------------------------
INSERT INTO `bas_code_type` VALUES ('16', 'Customers', '客户管理', 'CustomerManage', '1', '1000', '1', '2013-03-19 09:37:06', '1', '2014-04-08 13:33:26', '0', '5', null);
INSERT INTO `bas_code_type` VALUES ('19', 'system', '系统参数', 'SystemParamter', '1', '999999999', '1', '2013-03-19 09:41:17', '1', '2014-03-24 17:04:53', '0', '52', null);
INSERT INTO `bas_code_type` VALUES ('21', 'Customers', '通用编码类型', 'codeTypes', '1', '10000', '1', '2013-03-19 10:42:58', '1', '2013-04-13 21:55:52', '0', '4', null);
INSERT INTO `bas_code_type` VALUES ('23', 'system', '参数类型', 'paramterType', '1', '11111', '1', '2013-03-19 13:24:26', '1', '2013-03-24 21:38:51', '0', '3', null);
INSERT INTO `bas_code_type` VALUES ('24', 'Products', '产品管理', 'ProductsManage', '1', '1000', '1', '2013-03-19 14:33:58', '1', '2013-03-24 21:38:27', '0', '2', null);
INSERT INTO `bas_code_type` VALUES ('28', 'Reports Center', '报表中心', 'ReportCenter', '1', '11113', '1', '2013-03-19 14:35:25', '1', '2013-03-24 21:39:04', '0', '2', null);
INSERT INTO `bas_code_type` VALUES ('32', 'system', 'Target', 'Target', '1', '100', '1', '2013-03-24 16:53:50', '1', '2013-03-24 17:17:07', '0', '1', null);
INSERT INTO `bas_code_type` VALUES ('33', 'system', '资源类型', 'ResourceType', '1', '1000', '1', '2013-03-24 21:46:03', '1', '2013-03-24 21:46:03', '0', '0', null);
INSERT INTO `bas_code_type` VALUES ('34', 'system', 'DataGrid_Editors', 'DataGrid_Editors', '1', '100', '1', '2013-03-27 22:45:16', '1', '2013-03-27 22:45:16', '0', '0', null);
INSERT INTO `bas_code_type` VALUES ('35', 'system', 'PagePosition', 'PagePosition', '1', '1000', '1', '2013-03-28 22:31:02', '1', '2013-03-28 22:31:02', '0', '0', null);
INSERT INTO `bas_code_type` VALUES ('36', 'system', 'PageList', 'PageList', '1', '1000', '1', '2013-03-28 22:33:45', '1', '2013-03-28 22:33:45', '0', '0', null);
INSERT INTO `bas_code_type` VALUES ('37', 'system', 'Datagrid类型', 'datagrid_type', '1', '1000', '1', '2014-04-08 23:08:45', '1', '2014-04-08 23:08:45', '0', '0', null);
INSERT INTO `bas_code_type` VALUES ('38', 'system', '接口管理', 'sysInterface', '1', '1111', '1', '2014-06-01 21:47:51', '1', '2014-06-01 21:47:51', '0', '0', null);
INSERT INTO `bas_code_type` VALUES ('39', 'paramter', 'nameZh1', 'nameEn1', '1', '11', '1', '2014-11-07 15:26:53', '1', '2014-11-07 15:26:53', '0', '0', null);

-- ----------------------------
-- Table structure for `bas_column`
-- ----------------------------
DROP TABLE IF EXISTS `bas_column`;
CREATE TABLE `bas_column` (
  `BAS_COLUMN_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '列ID',
  `BAS_DATAGRID_ID` int(11) DEFAULT NULL,
  `SHOW_ORDER` int(11) NOT NULL COMMENT '显示顺序',
  `FILED` varchar(100) NOT NULL COMMENT '绑定字段',
  `NAME` varchar(100) DEFAULT NULL COMMENT '显示列名',
  `WIDTH` decimal(10,2) NOT NULL COMMENT '列宽度',
  `ROWSPAN` int(11) DEFAULT NULL COMMENT '合并列',
  `COLSPAN` int(11) DEFAULT NULL COMMENT '合并行',
  `SORTABLE` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否排序',
  `SORT_ORDER` varchar(5) DEFAULT 'ASC' COMMENT '排序方式:ASC DESC',
  `HEADER_ALIGN` varchar(10) DEFAULT 'LIFE' COMMENT 'LIFE RIGHT CENTER',
  `ALIGN` varchar(10) NOT NULL DEFAULT 'LIFE' COMMENT 'LIFE RIGHT CENTER',
  `HIDDEN` tinyint(1) DEFAULT '1' COMMENT '显示与否:TRUE FALSE',
  `CHECK_BOX` tinyint(1) DEFAULT '0',
  `FORMATTER` varchar(256) DEFAULT NULL,
  `STYLER` varchar(256) DEFAULT NULL,
  `SORTER` varchar(256) DEFAULT NULL,
  `EDITOR` varchar(256) DEFAULT NULL,
  `CREATE_BY` int(11) NOT NULL COMMENT '创建人ID',
  `CREATE_DATE` datetime NOT NULL COMMENT '创建日期',
  `UPDATE_BY` int(11) NOT NULL COMMENT '更新人ID',
  `UPDATE_DATE` datetime NOT NULL COMMENT '更新日期',
  `MARK_FOR_DELETE` int(1) DEFAULT '0' COMMENT '是否标记删除 0 未删除   1 表示删除',
  `OPT_COUNTER` int(6) DEFAULT '0' COMMENT '记录操作次',
  `DELETE_DATE` datetime DEFAULT NULL COMMENT '删除日期',
  PRIMARY KEY (`BAS_COLUMN_ID`),
  KEY `FK_BAS_COLUMN_BAS_DATAGRID_ID` (`BAS_DATAGRID_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=184 DEFAULT CHARSET=utf8 COMMENT='DataGrid列属性配置';

-- ----------------------------
-- Records of bas_column
-- ----------------------------
INSERT INTO `bas_column` VALUES ('1', '6', '1', 'id', 'name', '10.00', '0', '0', '1', 'asc', 'left', 'center', '0', '1', '', '', '', null, '1', '2013-03-27 23:35:50', '1', '2013-03-30 12:45:36', '0', '1', null);
INSERT INTO `bas_column` VALUES ('2', '6', '2', 'showOrder', 'ShowOrder', '20.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', '', '', '', null, '1', '2013-03-27 23:42:09', '1', '2013-03-27 23:42:09', '0', '0', null);
INSERT INTO `bas_column` VALUES ('3', '6', '3', 'field', 'Field', '30.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', '', '', '', null, '1', '2013-03-27 23:44:50', '1', '2013-04-01 23:11:48', '0', '3', null);
INSERT INTO `bas_column` VALUES ('4', '6', '5', 'name', 'Name', '150.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', '', '', '', null, '1', '2013-04-01 22:17:36', '1', '2013-04-02 11:01:13', '0', '3', null);
INSERT INTO `bas_column` VALUES ('5', '6', '6', 'width', 'Width', '100.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', '', '', '', null, '1', '2013-04-01 22:18:54', '1', '2013-04-01 23:08:30', '0', '4', null);
INSERT INTO `bas_column` VALUES ('7', '6', '4', 'order', 'Order', '20.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', '', '', '', null, '1', '2013-04-01 22:26:48', '1', '2013-04-01 23:06:45', '0', '1', null);
INSERT INTO `bas_column` VALUES ('8', '6', '7', 'align', 'Align', '100.00', '0', '0', '1', 'asc', 'left', 'center', '0', '0', '', '', '', null, '1', '2013-04-01 23:10:22', '1', '2013-04-01 23:14:31', '0', '5', null);
INSERT INTO `bas_column` VALUES ('9', '6', '8', 'sortable', 'Sortable', '100.00', '0', '0', '1', 'asc', 'left', 'center', '0', '0', '', '', '', null, '1', '2013-04-01 23:15:12', '1', '2013-04-01 23:16:36', '0', '2', null);
INSERT INTO `bas_column` VALUES ('10', '6', '9', 'headerAlign', 'HeaderAlign', '100.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', '', '', '', null, '1', '2013-04-01 23:16:26', '1', '2013-04-01 23:17:05', '0', '1', null);
INSERT INTO `bas_column` VALUES ('11', '7', '0', 'id', '状态', '8.00', '0', '0', '1', 'asc', 'center', 'center', '0', '0', 'formatterCheckBox', '', '', null, '1', '2013-04-02 11:12:03', '1', '2013-04-05 20:42:44', '0', '8', null);
INSERT INTO `bas_column` VALUES ('12', '7', '2', 'nameZh', '用户组名称(中文)', '80.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', '', '', '', null, '1', '2013-04-02 11:12:39', '1', '2013-04-02 11:14:19', '0', '2', null);
INSERT INTO `bas_column` VALUES ('13', '7', '1', 'nameEn', '用户组名称(英文)', '80.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', '', '', '', null, '1', '2013-04-02 11:14:59', '1', '2013-04-02 11:14:59', '0', '0', null);
INSERT INTO `bas_column` VALUES ('14', '8', '0', 'id', '状态', '8.00', '0', '0', '1', 'asc', 'left', 'center', '0', '0', 'formatterCheckBox', '', '', null, '1', '2013-04-02 13:25:58', '1', '2013-04-07 17:28:50', '0', '5', null);
INSERT INTO `bas_column` VALUES ('15', '8', '1', 'nameEn', '角色名称(英文)', '80.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', '', '', '', null, '1', '2013-04-02 13:26:58', '1', '2013-04-05 18:24:17', '0', '1', null);
INSERT INTO `bas_column` VALUES ('16', '8', '2', 'nameZh', '角色名称(中文)', '80.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', '', '', '', null, '1', '2013-04-02 13:27:42', '1', '2013-04-02 13:27:42', '0', '0', null);
INSERT INTO `bas_column` VALUES ('17', '9', '0', 'id', ' ', '30.00', '0', '0', '1', 'asc', 'left', 'center', '0', '1', '', '', '', null, '1', '2013-04-02 14:42:24', '1', '2013-04-02 19:09:25', '0', '10', null);
INSERT INTO `bas_column` VALUES ('18', '9', '1', 'nameEn', '用户组名称(英文)', '80.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', '', '', '', null, '1', '2013-04-02 14:43:00', '1', '2013-04-02 19:21:21', '0', '6', null);
INSERT INTO `bas_column` VALUES ('19', '9', '2', 'nameZh', '用户组名称(中文)', '80.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', '', '', '', null, '1', '2013-04-02 14:44:53', '1', '2013-04-02 14:46:27', '0', '1', null);
INSERT INTO `bas_column` VALUES ('20', '10', '0', 'id', ' ', '30.00', '0', '0', '1', 'asc', 'left', 'left', '0', '1', '', '', '', null, '1', '2013-04-05 17:50:00', '1', '2013-04-05 17:50:00', '0', '0', null);
INSERT INTO `bas_column` VALUES ('21', '10', '1', 'tableId', 'TableId', '100.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', '', '', '', null, '1', '2013-04-05 17:54:38', '1', '2013-04-05 17:54:38', '0', '0', null);
INSERT INTO `bas_column` VALUES ('22', '10', '2', 'title', 'Title', '100.00', '0', '0', '1', 'asc', 'left', 'left', '1', '0', '', '', '', null, '1', '2013-04-05 17:55:10', '1', '2013-04-05 19:01:00', '0', '1', null);
INSERT INTO `bas_column` VALUES ('23', '10', '5', 'pagination', 'Pagination', '70.00', '0', '0', '1', 'asc', 'center', 'center', '0', '0', '', '', '', null, '1', '2013-04-05 17:55:48', '1', '2013-04-05 17:57:16', '0', '2', null);
INSERT INTO `bas_column` VALUES ('24', '10', '4', 'frozen', 'Frozen', '50.00', '0', '0', '1', 'asc', 'left', 'center', '0', '0', '', '', '', null, '1', '2013-04-05 17:56:27', '1', '2013-04-05 17:56:27', '0', '0', null);
INSERT INTO `bas_column` VALUES ('26', '10', '3', 'fit', ' Fit', '50.00', '0', '0', '1', 'asc', 'left', 'center', '0', '0', '', '', '', null, '1', '2013-04-05 17:59:45', '1', '2013-04-05 17:59:45', '0', '0', null);
INSERT INTO `bas_column` VALUES ('27', '10', '6', 'fitColumns', 'FitColumns', '60.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', '', '', '', null, '1', '2013-04-05 18:00:32', '1', '2013-04-05 18:03:07', '0', '2', null);
INSERT INTO `bas_column` VALUES ('28', '10', '7', 'striped', 'Striped', '60.00', '0', '0', '1', 'asc', 'left', 'center', '0', '0', '', '', '', null, '1', '2013-04-05 18:02:58', '1', '2013-04-05 18:02:58', '0', '0', null);
INSERT INTO `bas_column` VALUES ('29', '10', '8', 'method', 'Method', '80.00', '0', '0', '1', 'asc', 'left', 'left', '1', '0', '', '', '', '', '1', '2013-04-05 18:03:37', '1', '2014-04-09 00:06:48', '0', '1', null);
INSERT INTO `bas_column` VALUES ('30', '10', '9', 'nowrap', 'Nowrap', '50.00', '0', '0', '1', 'asc', 'left', 'center', '0', '0', '', '', '', null, '1', '2013-04-05 18:05:14', '1', '2013-04-05 18:05:14', '0', '0', null);
INSERT INTO `bas_column` VALUES ('31', '10', '10', 'url', 'URL', '120.00', '0', '0', '1', 'asc', 'left', 'left', '1', '0', '', '', '', '', '1', '2013-04-05 18:05:48', '1', '2014-04-09 00:04:54', '0', '1', null);
INSERT INTO `bas_column` VALUES ('32', '10', '11', 'rownumbers', 'Rownumbers', '50.00', '0', '0', '1', 'asc', 'left', 'center', '0', '0', '', '', '', null, '1', '2013-04-05 18:06:30', '1', '2013-04-05 18:06:30', '0', '0', null);
INSERT INTO `bas_column` VALUES ('33', '10', '12', 'singleSelect', '单行选择', '50.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', '', '', '', null, '1', '2013-04-05 18:07:08', '1', '2013-04-05 18:07:08', '0', '0', null);
INSERT INTO `bas_column` VALUES ('34', '10', '13', 'checkOnSelect', 'CheckOnSelect', '80.00', '0', '0', '1', 'asc', 'left', 'center', '0', '0', '', '', '', null, '1', '2013-04-05 18:07:41', '1', '2013-04-05 18:07:41', '0', '0', null);
INSERT INTO `bas_column` VALUES ('35', '10', '14', 'selectOnCheck', 'SelectOnCheck', '80.00', '0', '0', '1', 'asc', 'left', 'center', '0', '0', '', '', '', null, '1', '2013-04-05 18:08:14', '1', '2013-04-05 18:08:14', '0', '0', null);
INSERT INTO `bas_column` VALUES ('36', '10', '15', 'pagePosition', 'PagePosition', '100.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', '', '', '', null, '1', '2013-04-05 18:08:42', '1', '2013-04-05 18:08:42', '0', '0', null);
INSERT INTO `bas_column` VALUES ('37', '10', '16', 'pageNumber', 'PageNumber', '100.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', '', '', '', null, '1', '2013-04-05 18:09:25', '1', '2013-04-05 18:12:19', '0', '3', null);
INSERT INTO `bas_column` VALUES ('38', '10', '17', 'pageSize', 'PageSize', '100.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', '', '', '', null, '1', '2013-04-05 18:09:59', '1', '2013-04-05 18:09:59', '0', '0', null);
INSERT INTO `bas_column` VALUES ('39', '10', '18', 'pageList', 'PageList', '150.00', '0', '0', '1', 'asc', 'left', 'left', '1', '0', '', '', '', '', '1', '2013-04-05 18:10:33', '1', '2014-04-09 00:05:54', '0', '2', null);
INSERT INTO `bas_column` VALUES ('40', '10', '20', 'sortOrder', 'SortOrder', '80.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', '', '', '', null, '1', '2013-04-05 18:13:49', '1', '2013-04-05 18:27:14', '0', '2', null);
INSERT INTO `bas_column` VALUES ('41', '10', '21', 'remoteSort', 'RemoteSort', '80.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', '', '', '', null, '1', '2013-04-05 18:14:09', '1', '2013-04-05 18:27:22', '0', '3', null);
INSERT INTO `bas_column` VALUES ('42', '10', '19', 'sortName', 'SortName', '80.00', '0', '0', '1', 'asc', 'left', 'left', '1', '0', '', '', '', null, '1', '2013-04-05 18:27:04', '1', '2013-04-05 19:00:26', '0', '1', null);
INSERT INTO `bas_column` VALUES ('43', '10', '22', 'showHeader', 'ShowHeader', '50.00', '0', '0', '1', 'asc', 'left', 'center', '0', '0', '', '', '', null, '1', '2013-04-05 18:28:14', '1', '2013-04-05 18:28:14', '0', '0', null);
INSERT INTO `bas_column` VALUES ('44', '10', '23', 'showFooter', 'ShowFooter', '50.00', '0', '0', '1', 'asc', 'left', 'center', '1', '0', '', '', '', null, '1', '2013-04-05 18:28:33', '1', '2013-04-05 19:00:15', '0', '1', null);
INSERT INTO `bas_column` VALUES ('45', '10', '24', 'data', 'Data', '100.00', '0', '0', '1', 'asc', 'left', 'left', '1', '0', '', '', '', null, '1', '2013-04-05 18:29:50', '1', '2013-04-05 18:29:50', '0', '0', null);
INSERT INTO `bas_column` VALUES ('46', '10', '25', 'loadMsg', 'LoadMsg', '100.00', '0', '0', '1', 'asc', 'left', 'left', '1', '0', '', '', '', null, '1', '2013-04-05 18:30:17', '1', '2013-04-05 18:30:17', '0', '0', null);
INSERT INTO `bas_column` VALUES ('47', '10', '26', 'scrollbarSize', 'ScrollbarSize', '100.00', '0', '0', '1', 'asc', 'left', 'left', '1', '0', '', '', '', null, '1', '2013-04-05 18:30:44', '1', '2013-04-05 18:30:44', '0', '0', null);
INSERT INTO `bas_column` VALUES ('48', '10', '27', 'rowStyler', 'RowStyler', '100.00', '0', '0', '1', 'asc', 'left', 'left', '1', '0', '', '', '', null, '1', '2013-04-05 18:31:07', '1', '2013-04-05 19:02:02', '0', '2', null);
INSERT INTO `bas_column` VALUES ('49', '10', '28', 'loader', 'Loader', '50.00', '0', '0', '1', 'asc', 'left', 'left', '1', '0', '', '', '', null, '1', '2013-04-05 18:31:45', '1', '2013-04-05 18:31:45', '0', '0', null);
INSERT INTO `bas_column` VALUES ('50', '10', '29', 'loadFilter', 'LoadFilter', '50.00', '0', '0', '1', 'asc', 'left', 'left', '1', '0', '', '', '', null, '1', '2013-04-05 18:32:11', '1', '2013-04-05 18:32:11', '0', '0', null);
INSERT INTO `bas_column` VALUES ('51', '12', '1', 'id', ' ', '10.00', '0', '0', '1', 'asc', 'left', 'center', '0', '1', '', '', '', null, '1', '2013-04-05 21:00:44', '1', '2013-04-05 21:00:44', '0', '0', null);
INSERT INTO `bas_column` VALUES ('52', '12', '2', 'nameEn', '角色名称(英文)', '100.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', '', '', '', null, '1', '2013-04-05 21:01:24', '1', '2013-04-05 21:01:24', '0', '0', null);
INSERT INTO `bas_column` VALUES ('53', '12', '3', 'nameZh', '角色名称(中文)', '100.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', '', '', '', null, '1', '2013-04-05 21:01:54', '1', '2013-04-05 21:01:54', '0', '0', null);
INSERT INTO `bas_column` VALUES ('56', '9', '3', 'users', 'users', '10.00', '0', '0', '1', 'asc', 'left', 'left', '1', '0', '', '', '', null, '1', '2013-04-08 15:38:16', '1', '2013-04-08 15:38:16', '0', '0', null);
INSERT INTO `bas_column` VALUES ('57', '12', '4', 'users', 'users', '10.00', '0', '0', '1', 'asc', 'left', 'left', '1', '0', '', '', '', null, '1', '2013-04-08 16:32:18', '1', '2013-04-08 16:32:18', '0', '0', null);
INSERT INTO `bas_column` VALUES ('58', '13', '1', 'id', 'id', '8.00', '0', '0', '1', 'asc', 'center', 'center', '0', '1', '', '', '', null, '1', '2013-04-09 17:13:28', '1', '2013-04-09 17:13:28', '0', '0', null);
INSERT INTO `bas_column` VALUES ('59', '13', '3', 'nameZh', '用户组名称(中文)', '100.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', '', '', '', null, '1', '2013-04-09 17:15:26', '1', '2013-04-09 17:19:08', '0', '1', null);
INSERT INTO `bas_column` VALUES ('60', '13', '2', 'nameEn', '用户组名称(英文)', '100.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', '', '', '', null, '1', '2013-04-09 17:16:14', '1', '2013-04-09 17:16:14', '0', '0', null);
INSERT INTO `bas_column` VALUES ('61', '13', '4', 'description', '描述', '150.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', '', '', '', null, '1', '2013-04-09 17:16:44', '1', '2013-04-09 17:16:44', '0', '0', null);
INSERT INTO `bas_column` VALUES ('62', '13', '5', 'editable', '是否可编辑', '60.00', '0', '0', '1', 'asc', 'center', 'center', '0', '0', 'formatterEidtable', '', '', null, '1', '2013-04-09 17:17:18', '1', '2013-04-09 17:18:56', '0', '1', null);
INSERT INTO `bas_column` VALUES ('63', '13', '6', 'createdDate', '创建时间', '100.00', '0', '0', '1', 'asc', 'left', 'center', '0', '0', '', '', '', null, '1', '2013-04-09 17:17:53', '1', '2013-04-09 17:17:53', '0', '0', null);
INSERT INTO `bas_column` VALUES ('64', '13', '7', 'updatedDate', ' 更新时间', '120.00', '0', '0', '1', 'asc', 'center', 'center', '0', '0', '', '', '', null, '1', '2013-04-09 17:18:28', '1', '2013-04-09 17:18:28', '0', '0', null);
INSERT INTO `bas_column` VALUES ('65', '14', '1', 'id', '状态', '12.00', '0', '0', '1', 'asc', 'center', 'center', '0', '0', 'formatterCheckBox', '', '', null, '1', '2013-04-09 20:28:25', '1', '2013-04-09 20:51:58', '0', '1', null);
INSERT INTO `bas_column` VALUES ('66', '14', '2', 'username', '用户名', '60.00', '0', '0', '1', 'asc', 'center', 'left', '0', '0', '', '', '', null, '1', '2013-04-09 20:29:30', '1', '2013-04-09 20:51:50', '0', '1', null);
INSERT INTO `bas_column` VALUES ('67', '14', '3', 'realnameEn', '用户姓名(英文)', '80.00', '0', '0', '1', 'asc', 'center', 'left', '0', '0', '', '', '', null, '1', '2013-04-09 20:30:46', '1', '2013-04-09 20:50:34', '0', '2', null);
INSERT INTO `bas_column` VALUES ('68', '14', '4', 'realnameZh', '用户姓名(英文)', '80.00', '0', '0', '1', 'asc', 'center', 'left', '0', '0', '', '', '', null, '1', '2013-04-09 20:31:50', '1', '2013-04-09 20:50:27', '0', '1', null);
INSERT INTO `bas_column` VALUES ('69', '16', '0', 'id', '状态', '12.00', '0', '0', '1', 'asc', 'center', 'center', '0', '0', 'formatterCheckBox', '', '', null, '1', '2013-04-09 21:04:17', '1', '2013-04-09 21:04:17', '0', '0', null);
INSERT INTO `bas_column` VALUES ('70', '16', '1', 'nameEn', '角色名称(英文)', '100.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', '', '', '', null, '1', '2013-04-09 21:05:19', '1', '2013-04-09 21:05:19', '0', '0', null);
INSERT INTO `bas_column` VALUES ('71', '16', '2', 'nameZh', '角色名称(中文)', '100.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', '', '', '', null, '1', '2013-04-09 21:05:53', '1', '2013-04-09 21:05:53', '0', '0', null);
INSERT INTO `bas_column` VALUES ('72', '17', '0', 'id', 'id', '10.00', '0', '0', '1', 'asc', 'left', 'left', '0', '1', '', '', '', null, '1', '2013-04-09 21:09:33', '1', '2013-04-09 21:31:26', '0', '2', null);
INSERT INTO `bas_column` VALUES ('73', '17', '1', 'username', '用户名', '80.00', '0', '0', '1', 'asc', 'center', 'left', '0', '0', '', '', '', null, '1', '2013-04-09 21:10:27', '1', '2013-04-09 21:31:36', '0', '1', null);
INSERT INTO `bas_column` VALUES ('74', '17', '2', 'realnameEn', '姓名(英文)', '80.00', '0', '0', '1', 'asc', 'center', 'left', '0', '0', '', '', '', null, '1', '2013-04-09 21:11:30', '1', '2013-04-09 21:31:45', '0', '1', null);
INSERT INTO `bas_column` VALUES ('75', '17', '3', 'realnameZh', '姓名(中文)', '80.00', '0', '0', '1', 'asc', 'center', 'left', '0', '0', '', '', '', null, '1', '2013-04-09 21:11:54', '1', '2013-04-09 21:32:07', '0', '2', null);
INSERT INTO `bas_column` VALUES ('76', '18', '0', 'id', 'id', '10.00', '0', '0', '1', 'asc', 'left', 'left', '0', '1', '', '', '', null, '1', '2013-04-09 21:13:37', '1', '2013-04-09 21:13:37', '0', '0', null);
INSERT INTO `bas_column` VALUES ('77', '18', '1', 'nameEn', '角色名称(英文)', '100.00', '0', '0', '1', 'asc', 'center', 'left', '0', '0', '', '', '', null, '1', '2013-04-09 21:14:54', '1', '2013-04-09 21:14:54', '0', '0', null);
INSERT INTO `bas_column` VALUES ('78', '18', '2', 'nameZh', '角色名称(中文)', '100.00', '0', '0', '1', 'asc', 'center', 'left', '0', '0', '', '', '', null, '1', '2013-04-09 21:15:25', '1', '2013-04-09 21:15:25', '0', '0', null);
INSERT INTO `bas_column` VALUES ('79', '17', '4', 'groups', '用户组', '10.00', '0', '0', '1', 'asc', 'left', 'center', '1', '0', '', '', '', null, '1', '2013-04-09 21:39:59', '1', '2013-04-09 21:57:23', '0', '2', null);
INSERT INTO `bas_column` VALUES ('80', '18', '3', 'groups', '用户组', '10.00', '0', '0', '1', 'asc', 'center', 'left', '1', '0', '', '', '', null, '1', '2013-04-09 21:41:07', '1', '2013-04-09 21:53:11', '0', '2', null);
INSERT INTO `bas_column` VALUES ('81', '19', '0', 'id', 'id', '10.00', '0', '0', '0', 'asc', 'center', 'center', '0', '1', '', '', '', null, '1', '2013-04-10 20:39:16', '1', '2013-04-10 20:39:16', '0', '0', null);
INSERT INTO `bas_column` VALUES ('82', '19', '1', 'nameEn', '角色名称(英文)', '80.00', '0', '0', '1', 'asc', 'center', 'left', '0', '0', '', '', '', null, '1', '2013-04-10 20:41:33', '1', '2013-04-10 20:41:33', '0', '0', null);
INSERT INTO `bas_column` VALUES ('83', '19', '2', 'nameZh', '角色名称(中文)', '80.00', '0', '0', '1', 'asc', 'center', 'left', '0', '0', '', '', '', null, '1', '2013-04-10 20:42:34', '1', '2013-04-10 20:42:34', '0', '0', null);
INSERT INTO `bas_column` VALUES ('84', '19', '3', 'description', '角色描述', '120.00', '0', '0', '1', 'asc', 'center', 'left', '0', '0', '', '', '', null, '1', '2013-04-10 20:43:15', '1', '2013-04-10 20:51:04', '0', '1', null);
INSERT INTO `bas_column` VALUES ('85', '19', '4', 'editable', '是否可编辑', '30.00', '0', '0', '1', 'asc', 'center', 'center', '0', '0', 'formatterEidtable', '', '', null, '1', '2013-04-10 20:43:56', '1', '2013-04-10 20:51:35', '0', '1', null);
INSERT INTO `bas_column` VALUES ('86', '19', '5', 'createdDate', '创建时间', '60.00', '0', '0', '1', 'asc', 'center', 'center', '0', '0', '', '', '', null, '1', '2013-04-10 20:44:30', '1', '2013-04-10 20:50:57', '0', '1', null);
INSERT INTO `bas_column` VALUES ('87', '19', '6', 'updatedDate', '更新时间', '60.00', '0', '0', '1', 'asc', 'center', 'center', '0', '0', '', '', '', null, '1', '2013-04-10 20:45:01', '1', '2013-04-10 20:50:48', '0', '1', null);
INSERT INTO `bas_column` VALUES ('88', '20', '0', 'id', '状态', '12.00', '0', '0', '1', 'asc', 'center', 'center', '0', '0', 'formatterCheckBox', '', '', null, '1', '2013-04-10 23:00:09', '1', '2013-04-10 23:34:30', '0', '1', null);
INSERT INTO `bas_column` VALUES ('89', '20', '1', 'username', '用户名', '80.00', '0', '0', '1', 'asc', 'center', 'left', '0', '0', '', '', '', null, '1', '2013-04-10 23:01:07', '1', '2013-04-10 23:01:07', '0', '0', null);
INSERT INTO `bas_column` VALUES ('90', '20', '2', 'realnameEn', '姓名(英文)', '80.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', '', '', '', null, '1', '2013-04-10 23:02:44', '1', '2013-04-10 23:02:44', '0', '0', null);
INSERT INTO `bas_column` VALUES ('91', '20', '3', 'realnameZh', '姓名(中文)', '80.00', '0', '0', '1', 'asc', 'left', 'center', '0', '0', '', '', '', null, '1', '2013-04-10 23:03:16', '1', '2013-04-10 23:03:16', '0', '0', null);
INSERT INTO `bas_column` VALUES ('92', '21', '0', 'id', '状态', '12.00', '0', '0', '1', 'asc', 'center', 'center', '0', '0', 'formatterCheckBox', '', '', null, '1', '2013-04-10 23:06:12', '1', '2013-04-10 23:34:39', '0', '1', null);
INSERT INTO `bas_column` VALUES ('93', '21', '1', 'nameEn', '用户组名称(英文)', '80.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', '', '', '', null, '1', '2013-04-10 23:07:19', '1', '2013-04-10 23:07:19', '0', '0', null);
INSERT INTO `bas_column` VALUES ('94', '21', '2', 'nameZh', '用户组名称(中文) ', '80.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', '', '', '', null, '1', '2013-04-10 23:07:48', '1', '2013-04-10 23:07:48', '0', '0', null);
INSERT INTO `bas_column` VALUES ('97', '22', '0', 'id', 'id', '10.00', '0', '0', '0', 'asc', 'center', 'center', '0', '1', '', '', '', null, '1', '2013-04-11 15:21:00', '1', '2013-04-11 16:14:02', '0', '1', null);
INSERT INTO `bas_column` VALUES ('98', '22', '1', 'parentResourceId', '上一级ID', '80.00', '0', '0', '0', 'asc', 'center', 'center', '1', '0', '', '', '', null, '1', '2013-04-11 15:21:53', '1', '2013-04-11 16:14:08', '0', '3', null);
INSERT INTO `bas_column` VALUES ('99', '22', '3', 'nameEn', '资源名称(英文)', '80.00', '0', '0', '0', 'asc', 'left', 'left', '0', '0', '', '', '', null, '1', '2013-04-11 15:22:45', '1', '2013-04-11 16:16:45', '0', '3', null);
INSERT INTO `bas_column` VALUES ('100', '22', '2', 'nameZh', '资源名称(中文)', '80.00', '0', '0', '0', 'asc', 'left', 'left', '0', '0', '', '', '', null, '1', '2013-04-11 15:23:53', '1', '2013-04-11 16:16:51', '0', '4', null);
INSERT INTO `bas_column` VALUES ('102', '24', '0', 'id', 'id', '10.00', '0', '0', '1', 'asc', 'center', 'center', '0', '1', '', '', '', null, '1', '2013-04-11 15:37:21', '1', '2013-04-11 15:37:21', '0', '0', null);
INSERT INTO `bas_column` VALUES ('103', '24', '1', 'username', '用户名', '80.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', '', '', '', null, '1', '2013-04-11 15:37:58', '1', '2013-04-11 15:37:58', '0', '0', null);
INSERT INTO `bas_column` VALUES ('104', '24', '3', 'realnameZh', '姓名(中文)', '80.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', '', '', '', null, '1', '2013-04-11 15:38:51', '1', '2013-04-11 15:38:51', '0', '0', null);
INSERT INTO `bas_column` VALUES ('105', '24', '2', 'realnameEn', '姓名(英文)', '80.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', '', '', '', null, '1', '2013-04-11 15:39:39', '1', '2013-04-11 15:39:39', '0', '0', null);
INSERT INTO `bas_column` VALUES ('106', '23', '0', 'id', 'id', '10.00', '0', '0', '1', 'asc', 'left', 'left', '0', '1', '', '', '', null, '1', '2013-04-11 15:40:11', '1', '2013-04-11 15:40:11', '0', '0', null);
INSERT INTO `bas_column` VALUES ('107', '23', '1', 'nameEn', '用户组名称(英文)', '80.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', '', '', '', null, '1', '2013-04-11 15:40:44', '1', '2013-04-11 15:42:07', '0', '1', null);
INSERT INTO `bas_column` VALUES ('108', '23', '2', 'nameZh', '用户组名称(中文)', '80.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', '', '', '', null, '1', '2013-04-11 15:41:57', '1', '2013-04-11 15:41:57', '0', '0', null);
INSERT INTO `bas_column` VALUES ('110', '22', '4', 'roles', 'roles', '10.00', '0', '0', '1', 'asc', 'left', 'left', '1', '0', '', '', '', null, '1', '2013-04-11 16:17:40', '1', '2013-04-11 16:17:40', '0', '0', null);
INSERT INTO `bas_column` VALUES ('111', '23', '3', 'roles', 'roles', '10.00', '0', '0', '1', 'asc', 'left', 'left', '1', '0', '', '', '', null, '1', '2013-04-11 18:38:42', '1', '2013-04-11 18:38:42', '0', '0', null);
INSERT INTO `bas_column` VALUES ('112', '24', '4', 'roles', 'roles', '10.00', '0', '0', '1', 'asc', 'left', 'left', '1', '0', '', '', '', null, '1', '2013-04-11 18:39:27', '1', '2013-04-11 18:39:27', '0', '0', null);
INSERT INTO `bas_column` VALUES ('113', '25', '0', 'id', 'id', '10.00', '0', '0', '1', 'asc', 'center', 'center', '0', '1', '', '', '', null, '1', '2013-04-14 09:07:06', '1', '2013-04-14 09:33:27', '0', '8', null);
INSERT INTO `bas_column` VALUES ('114', '25', '2', 'nameEn', '编码名称(英文)', '80.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', '', '', '', null, '1', '2013-04-14 09:30:31', '1', '2013-04-14 09:30:31', '0', '0', null);
INSERT INTO `bas_column` VALUES ('115', '25', '1', 'codeType', '编码类型', '60.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', 'formatterName', '', '', null, '1', '2013-04-14 09:31:32', '1', '2013-04-14 09:43:15', '0', '1', null);
INSERT INTO `bas_column` VALUES ('116', '25', '3', 'nameZh', '编码名称(中文)', '80.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', '', '', '', null, '1', '2013-04-14 09:34:05', '1', '2013-04-14 09:34:17', '0', '1', null);
INSERT INTO `bas_column` VALUES ('117', '25', '4', 'codeValue', '编码值', '80.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', '', '', '', null, '1', '2013-04-14 09:34:45', '1', '2013-04-14 09:34:45', '0', '0', null);
INSERT INTO `bas_column` VALUES ('118', '25', '5', 'editable', '可编辑', '20.00', '0', '0', '1', 'asc', 'center', 'center', '0', '0', 'formatterEidtable', '', '', null, '1', '2013-04-14 09:35:15', '1', '2013-04-14 09:45:14', '0', '3', null);
INSERT INTO `bas_column` VALUES ('119', '25', '6', 'selected', '默认项', '20.00', '0', '0', '1', 'asc', 'center', 'center', '0', '0', 'formatterEidtable', '', '', null, '1', '2013-04-14 09:39:19', '1', '2013-04-14 09:45:20', '0', '3', null);
INSERT INTO `bas_column` VALUES ('120', '25', '7', 'sortNo', '排序', '20.00', '0', '0', '1', 'asc', 'center', 'center', '0', '0', '', '', '', null, '1', '2013-04-14 09:40:07', '1', '2013-04-14 09:40:07', '0', '0', null);
INSERT INTO `bas_column` VALUES ('121', '25', '8', 'createdDate', '创建时间', '60.00', '0', '0', '1', 'asc', 'center', 'center', '0', '0', '', '', '', null, '1', '2013-04-14 09:40:52', '1', '2013-04-14 09:40:52', '0', '0', null);
INSERT INTO `bas_column` VALUES ('122', '25', '9', 'updatedDate', '更新时间', '60.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', '', '', '', null, '1', '2013-04-14 09:41:15', '1', '2013-04-14 09:41:15', '0', '0', null);
INSERT INTO `bas_column` VALUES ('123', '26', '0', 'id', 'id', '10.00', '0', '0', '1', 'asc', 'center', 'center', '0', '1', '', '', '', null, '1', '2013-04-14 09:48:05', '1', '2013-04-14 09:50:48', '0', '1', null);
INSERT INTO `bas_column` VALUES ('124', '26', '1', 'type', '通用编码类型', '85.00', '0', '0', '1', 'asc', 'center', 'center', '0', '0', 'formatterCodeList', '', '', '', '1', '2013-04-14 09:51:52', '1', '2014-04-08 12:54:52', '0', '7', null);
INSERT INTO `bas_column` VALUES ('125', '26', '2', 'nameEn', '通用编码类型名称(英文)', '85.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', '', '', '', null, '1', '2013-04-14 09:52:37', '1', '2013-04-14 09:52:37', '0', '0', null);
INSERT INTO `bas_column` VALUES ('126', '26', '3', 'nameZh', '通用编码类型名称(中文)', '85.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', '', '', '', null, '1', '2013-04-14 09:52:56', '1', '2013-04-14 09:52:56', '0', '0', null);
INSERT INTO `bas_column` VALUES ('127', '26', '4', 'codeLenLimit', '编码参数值长度', '85.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', '', '', '', null, '1', '2013-04-14 09:53:32', '1', '2013-04-14 09:53:32', '0', '0', null);
INSERT INTO `bas_column` VALUES ('128', '26', '5', 'editable', '可编辑', '20.00', '0', '0', '1', 'asc', 'center', 'center', '0', '0', 'formatterEidtable', '', '', null, '1', '2013-04-14 09:53:57', '1', '2013-04-14 09:55:07', '0', '2', null);
INSERT INTO `bas_column` VALUES ('129', '26', '6', 'createdDate', '创建时间', '80.00', '0', '0', '1', 'asc', 'center', 'center', '0', '0', '', '', '', null, '1', '2013-04-14 09:55:00', '1', '2013-04-14 09:55:00', '0', '0', null);
INSERT INTO `bas_column` VALUES ('130', '26', '7', 'updatedDate', '更新时间', '80.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', '', '', '', null, '1', '2013-04-14 09:55:31', '1', '2013-04-14 09:55:31', '0', '0', null);
INSERT INTO `bas_column` VALUES ('131', '27', '0', 'id', 'id', '10.00', '0', '0', '1', 'asc', 'center', 'center', '0', '1', '', '', '', null, '1', '2013-04-14 12:38:08', '1', '2013-04-14 12:38:08', '0', '0', null);
INSERT INTO `bas_column` VALUES ('132', '27', '1', 'parentResourceId', '上一级ID', '10.00', '0', '0', '1', 'asc', 'left', 'left', '1', '0', '', '', '', null, '1', '2013-04-14 12:39:36', '1', '2013-04-14 12:39:36', '0', '0', null);
INSERT INTO `bas_column` VALUES ('133', '27', '2', 'nameZh', '资源名称(中文)', '80.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', '', '', '', null, '1', '2013-04-14 16:37:08', '1', '2013-04-14 16:37:08', '0', '0', null);
INSERT INTO `bas_column` VALUES ('134', '27', '3', 'type', '资源类型', '80.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', 'formatterCodeResource', '', '', null, '1', '2013-04-14 16:37:46', '1', '2013-04-14 16:51:23', '0', '2', null);
INSERT INTO `bas_column` VALUES ('135', '27', '4', 'path', '资源路径', '120.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', '', '', '', null, '1', '2013-04-14 16:38:11', '1', '2013-04-14 16:38:11', '0', '0', null);
INSERT INTO `bas_column` VALUES ('136', '27', '5', 'seqNum', '排序', '20.00', '0', '0', '1', 'asc', 'center', 'center', '0', '0', '', '', '', null, '1', '2013-04-14 16:39:13', '1', '2013-04-14 16:58:03', '0', '1', null);
INSERT INTO `bas_column` VALUES ('137', '27', '6', 'target', '目标', '15.00', '0', '0', '1', 'asc', 'center', 'left', '1', '0', '', '', '', null, '1', '2013-04-14 16:39:54', '1', '2013-04-14 16:58:14', '0', '2', null);
INSERT INTO `bas_column` VALUES ('138', '27', '7', 'nameEn', '资源名称(英文)', '80.00', '0', '0', '1', 'asc', 'left', 'left', '1', '0', '', '', '', null, '1', '2013-04-14 16:40:27', '1', '2013-04-14 16:40:27', '0', '0', null);
INSERT INTO `bas_column` VALUES ('139', '27', '8', 'description', '资源描述', '120.00', '0', '0', '1', 'asc', 'left', 'left', '1', '0', '', '', '', null, '1', '2013-04-14 16:40:51', '1', '2013-04-14 16:42:17', '0', '1', null);
INSERT INTO `bas_column` VALUES ('140', '27', '9', 'editable', '可编辑', '15.00', '0', '0', '1', 'asc', 'center', 'center', '0', '0', 'formatterEidtable', '', '', null, '1', '2013-04-14 16:41:21', '1', '2013-04-14 16:58:09', '0', '3', null);
INSERT INTO `bas_column` VALUES ('141', '27', '10', 'isLeaf', '是否叶节点', '40.00', '0', '0', '1', 'asc', 'left', 'left', '1', '0', '', '', '', null, '1', '2013-04-14 16:42:05', '1', '2013-04-14 16:42:05', '0', '0', null);
INSERT INTO `bas_column` VALUES ('142', '28', '0', 'id', 'id', '10.00', '0', '0', '1', 'asc', 'center', 'center', '0', '1', '', '', '', null, '1', '2013-04-14 17:00:55', '1', '2013-04-14 17:00:55', '0', '0', null);
INSERT INTO `bas_column` VALUES ('143', '28', '1', 'parentDepartmentId', 'parentDepartmentId', '10.00', '0', '0', '1', 'asc', 'center', 'center', '1', '0', '', '', '', null, '1', '2013-04-14 17:01:38', '1', '2013-04-14 17:01:43', '0', '1', null);
INSERT INTO `bas_column` VALUES ('144', '28', '2', 'nameEn', '部门名称(英文)', '80.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', '', '', '', null, '1', '2013-04-14 17:03:07', '1', '2013-04-14 17:03:07', '0', '0', null);
INSERT INTO `bas_column` VALUES ('145', '28', '3', 'nameZh', '部门名称(中文)', '80.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', '', '', '', null, '1', '2013-04-14 17:03:39', '1', '2013-04-14 17:03:39', '0', '0', null);
INSERT INTO `bas_column` VALUES ('146', '28', '4', 'departmentNO', '部门编号', '80.00', '0', '0', '1', 'asc', 'center', 'center', '0', '0', '', '', '', null, '1', '2013-04-14 17:04:08', '1', '2013-04-14 17:04:08', '0', '0', null);
INSERT INTO `bas_column` VALUES ('147', '28', '5', 'description', '部门描述', '120.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', '', '', '', null, '1', '2013-04-14 17:04:34', '1', '2013-04-14 17:04:34', '0', '0', null);
INSERT INTO `bas_column` VALUES ('148', '28', '6', 'editable', '可编辑', '20.00', '0', '0', '1', 'asc', 'center', 'center', '0', '0', 'formatterEidtable', '', '', null, '1', '2013-04-14 17:05:25', '1', '2013-04-14 17:05:25', '0', '0', null);
INSERT INTO `bas_column` VALUES ('149', '29', '0', 'id', '序号', '10.00', '0', '0', '1', 'asc', 'left', 'left', '0', '1', '', '', '', null, '1', '2013-04-14 17:27:21', '1', '2013-04-14 17:27:21', '0', '0', null);
INSERT INTO `bas_column` VALUES ('150', '29', '1', 'type', '参数类型', '60.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', 'formatterCodeList', '', '', null, '1', '2013-04-14 17:28:00', '1', '2013-04-14 18:36:42', '0', '3', null);
INSERT INTO `bas_column` VALUES ('151', '29', '2', 'nameEn', '参数名称(英文)', '80.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', '', '', '', null, '1', '2013-04-14 17:28:47', '1', '2013-04-14 17:28:47', '0', '0', null);
INSERT INTO `bas_column` VALUES ('152', '29', '3', 'nameZh', '参数名称(中文)', '80.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', '', '', '', null, '1', '2013-04-14 17:29:17', '1', '2013-04-14 17:29:17', '0', '0', null);
INSERT INTO `bas_column` VALUES ('153', '28', '4', 'value', '参数值', '100.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', '', '', '', null, '1', '2013-04-14 17:29:37', '1', '2013-04-14 17:30:09', '0', '1', null);
INSERT INTO `bas_column` VALUES ('154', '29', '5', 'description', '参数描述', '100.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', '', '', '', null, '1', '2013-04-14 17:30:02', '1', '2013-04-14 17:30:02', '0', '0', null);
INSERT INTO `bas_column` VALUES ('155', '29', '7', 'editable', '可编辑', '25.00', '0', '0', '1', 'asc', 'center', 'center', '0', '0', 'formatterEidtable', '', '', null, '1', '2013-04-14 17:30:36', '1', '2013-04-14 18:59:37', '0', '4', null);
INSERT INTO `bas_column` VALUES ('156', '29', '8', 'createdDate', '创建时间', '80.00', '0', '0', '1', 'asc', 'center', 'center', '0', '0', '', '', '', null, '1', '2013-04-14 17:31:00', '1', '2013-04-14 18:57:56', '0', '2', null);
INSERT INTO `bas_column` VALUES ('157', '29', '9', 'updatedDate', '更新时间', '80.00', '0', '0', '1', 'asc', 'center', 'center', '0', '0', '', '', '', null, '1', '2013-04-14 17:31:24', '1', '2013-04-14 18:57:50', '0', '1', null);
INSERT INTO `bas_column` VALUES ('158', '29', '6', 'value', '参数值', '80.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', '', '', '', null, '1', '2013-04-14 18:57:34', '1', '2013-04-14 18:57:34', '0', '0', null);
INSERT INTO `bas_column` VALUES ('159', '11', '0', 'id', 'id', '30.00', '0', '0', '1', 'asc', 'left', 'left', '0', '1', '', '', '', null, '1', '2014-04-03 12:17:05', '1', '2014-04-03 12:57:33', '0', '4', null);
INSERT INTO `bas_column` VALUES ('160', '11', '1', 'username', '用户名', '80.00', '0', '0', '1', 'asc', 'left', 'center', '0', '0', '', '', '', 'text', '1', '2014-04-03 12:48:38', '1', '2014-04-03 13:07:47', '0', '4', null);
INSERT INTO `bas_column` VALUES ('161', '11', '2', 'sapUsername', '超级用户名', '80.00', '0', '0', '1', 'asc', 'left', 'center', '0', '0', '', '', '', null, '1', '2014-04-03 12:57:14', '1', '2014-04-03 12:57:14', '0', '0', null);
INSERT INTO `bas_column` VALUES ('162', '11', '4', 'realnameZh', '真实姓名(中文)', '80.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', '', '', '', 'text', '1', '2014-04-03 12:58:51', '1', '2014-04-03 13:04:44', '0', '2', null);
INSERT INTO `bas_column` VALUES ('163', '11', '5', 'realnameEn', '真实姓名(英文)', '80.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', '', '', '', 'text', '1', '2014-04-03 12:59:22', '1', '2014-04-03 13:07:59', '0', '2', null);
INSERT INTO `bas_column` VALUES ('164', '11', '6', 'employeeCode', '员工号', '80.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', '', '', '', null, '1', '2014-04-03 13:00:56', '1', '2014-04-03 13:02:26', '0', '1', null);
INSERT INTO `bas_column` VALUES ('165', '11', '7', 'description', '描述', '80.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', '', '', '', 'textarea', '1', '2014-04-03 13:01:50', '1', '2014-04-03 13:08:06', '0', '1', null);
INSERT INTO `bas_column` VALUES ('166', '11', '8', 'telephone', '联系电话', '80.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', '', '', '', 'text', '1', '2014-04-03 13:09:33', '1', '2014-04-03 13:09:33', '0', '0', null);
INSERT INTO `bas_column` VALUES ('167', '11', '9', 'officePhone', '办公室', '80.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', '', '', '', '', '1', '2014-04-03 13:10:10', '1', '2014-04-03 13:10:10', '0', '0', null);
INSERT INTO `bas_column` VALUES ('168', '11', '10', 'accountExpired', '账户是否有效', '80.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', 'formatterEidtable', '', '', '', '1', '2014-04-03 13:11:20', '1', '2014-04-03 13:12:01', '0', '1', null);
INSERT INTO `bas_column` VALUES ('170', '11', '11', 'accountExpiredDate', '账户有效日期', '100.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', '', '', '', 'datebox', '1', '2014-04-03 14:00:03', '1', '2014-04-03 14:03:21', '0', '1', null);
INSERT INTO `bas_column` VALUES ('172', '11', '13', 'nameZh', '部门名称', '10.00', '0', '0', '1', 'asc', 'left', 'left', '1', '0', '', '', '', 'combotree', '1', '2014-04-03 16:05:49', '1', '2014-04-03 21:17:39', '0', '2', null);
INSERT INTO `bas_column` VALUES ('173', '30', '0', 'id', 'id', '30.00', '0', '0', '1', 'asc', 'left', 'left', '0', '1', '', '', '', '', '1', '2014-04-04 01:05:31', '1', '2014-04-04 01:05:31', '0', '0', null);
INSERT INTO `bas_column` VALUES ('174', '30', '1', 'nameEn', 'nameEn', '300.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', '', '', '', '', '1', '2014-04-04 01:05:51', '1', '2014-04-04 01:05:51', '0', '0', null);
INSERT INTO `bas_column` VALUES ('175', '11', '12', 'department', '所属部门', '100.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', 'formatterShowDepartment', '', '', '', '1', '2014-04-04 14:57:13', '4', '2014-05-07 16:23:05', '0', '2', null);
INSERT INTO `bas_column` VALUES ('176', '33', '1', 'name', '缓存名称', '100.00', '0', '0', '0', 'asc', 'left', 'left', '0', '0', '', '', '', '', '1', '2014-04-21 15:29:37', '1', '2014-04-22 10:30:19', '0', '1', null);
INSERT INTO `bas_column` VALUES ('177', '33', '2', 'size', '缓存大小', '100.00', '0', '0', '0', 'asc', 'left', 'left', '0', '0', '', '', '', '', '1', '2014-04-21 15:30:36', '1', '2014-04-22 10:30:36', '0', '1', null);
INSERT INTO `bas_column` VALUES ('178', '33', '3', 'memoryStoreSize', '内存缓存数量', '100.00', '0', '0', '0', 'asc', 'left', 'left', '0', '0', '', '', '', '', '1', '2014-04-21 15:31:01', '1', '2014-04-22 10:30:40', '0', '2', null);
INSERT INTO `bas_column` VALUES ('179', '33', '4', 'diskStoreSize', '磁盘数量', '100.00', '0', '0', '1', 'asc', 'left', 'left', '0', '0', '', '', '', '', '1', '2014-04-21 15:31:30', '1', '2014-04-21 15:31:30', '0', '0', null);
INSERT INTO `bas_column` VALUES ('180', '33', '5', 'cacheHits', '命中次数', '100.00', '0', '0', '0', 'asc', 'left', 'left', '0', '0', '', '', '', '', '1', '2014-04-21 15:32:02', '1', '2014-04-22 10:30:45', '0', '1', null);
INSERT INTO `bas_column` VALUES ('181', '33', '6', 'inMemoryHits', '内存命中次数', '100.00', '0', '0', '0', 'asc', 'left', 'left', '0', '0', '', '', '', '', '1', '2014-04-21 15:32:26', '1', '2014-04-22 10:30:49', '0', '1', null);
INSERT INTO `bas_column` VALUES ('182', '33', '7', 'onDiskHits', '磁盘命中次数', '100.00', '0', '0', '0', 'asc', 'left', 'left', '0', '0', '', '', '', '', '1', '2014-04-21 15:32:45', '1', '2014-04-22 10:30:55', '0', '1', null);
INSERT INTO `bas_column` VALUES ('183', '33', '8', 'memoryStoreEvictionPolicy', '缓存策略', '100.00', '0', '0', '0', 'asc', 'left', 'left', '0', '0', '', '', '', '', '1', '2014-04-21 15:33:04', '1', '2014-04-22 10:30:59', '0', '1', null);

-- ----------------------------
-- Table structure for `bas_datagrid`
-- ----------------------------
DROP TABLE IF EXISTS `bas_datagrid`;
CREATE TABLE `bas_datagrid` (
  `BAS_DATAGRID_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '序列号',
  `TYPE` varchar(30) DEFAULT NULL COMMENT '表格类型',
  `TABLE_ID` varchar(60) NOT NULL COMMENT '表格ID',
  `TITLE` varchar(128) DEFAULT NULL,
  `SHOW_TITLE` tinyint(1) DEFAULT '0' COMMENT '是否显示标题 默认为否',
  `FIT` tinyint(1) DEFAULT '1' COMMENT '设置为true将自动使列适应表格宽度以防止出现水平滚动',
  `FROZEN` tinyint(1) DEFAULT '0' COMMENT '跟列属性一样，但是这些列固定在左边，不会滚动',
  `SHOW_GROUP` tinyint(1) DEFAULT '0' COMMENT '是否合并表头并分组显示，默认值：false',
  `PAGINACTION` tinyint(1) DEFAULT '1' COMMENT '是否显示分页显示条',
  `FIT_COLUMNS` tinyint(1) DEFAULT '0',
  `STRIPED` tinyint(1) DEFAULT '0',
  `METHOD` varchar(15) DEFAULT 'POST' COMMENT 'The method type to request remote data.Default value is:post',
  `NOWRAP` tinyint(1) DEFAULT '1' COMMENT ' 默认设置为：true，当数据长度超出列宽时将会自动截取',
  `URL` varchar(256) DEFAULT NULL COMMENT ' A URL to request data from remote site',
  `DATA` varchar(512) DEFAULT NULL COMMENT 'The data to be loaded',
  `LOADMSG` varchar(100) DEFAULT NULL COMMENT ' When loading data from remote site,show a prompt message.Default value is:''Processing, please wait …''',
  `ROW_NUMBERS` tinyint(1) DEFAULT '1' COMMENT 'True to show a row number column. Default value is:true',
  `SINGLE_SELECT` tinyint(1) DEFAULT '1' COMMENT 'True to allow selecting only one row. Default value is:true',
  `CHECK_ON_SEELCT` tinyint(1) DEFAULT '1',
  `SELECT_ON_CHECK` tinyint(1) DEFAULT '1',
  `PAGE_POSITION` varchar(10) DEFAULT NULL COMMENT ' Defines position of the pager bar. Available values are: ''top'',''bottom'',''both''.The default value is:bottom',
  `PAGE_NUMBER` int(11) DEFAULT '1',
  `PAGE_SIZE` int(11) DEFAULT '15',
  `PAGE_LIST` varchar(50) DEFAULT '[10,15,20,30,40,50,100]',
  `QUERY_PARAMS` varchar(50) DEFAULT '100',
  `SORT_NAME` varchar(30) DEFAULT 'id' COMMENT 'Defines which column can be sorted.',
  `SORT_ORDER` varchar(5) DEFAULT 'asc' COMMENT 'Defines the column sort order, can only be ''asc'' or ''desc''.',
  `REMOTE_SORT` tinyint(1) DEFAULT '1' COMMENT 'Defines if to sort data from server.',
  `SHOW_HEADER` tinyint(1) DEFAULT '1' COMMENT 'Defines if to show row header.',
  `SHOW_FOOTER` tinyint(1) DEFAULT '0' COMMENT 'Defines if to show row footer.',
  `SCROLLBER_SIZE` int(11) DEFAULT '18',
  `ROW_STYLER` varchar(100) DEFAULT NULL COMMENT 'Return style such as ''background:red''. The function take two parameter:',
  `LOAD_FILTER` varchar(100) DEFAULT NULL COMMENT 'Return the filtered data to display.',
  `EDITERS` varchar(100) DEFAULT NULL,
  `LOADER` varchar(100) DEFAULT NULL COMMENT 'Defines how to load data from remote server.',
  `VIEW` varchar(100) DEFAULT NULL,
  `CREATE_BY` int(11) NOT NULL COMMENT '创建人ID',
  `CREATE_DATE` datetime NOT NULL COMMENT '创建日期',
  `UPDATE_BY` int(11) NOT NULL COMMENT '更新人ID',
  `UPDATE_DATE` datetime NOT NULL COMMENT '更新日期',
  `MARK_FOR_DELETE` int(1) DEFAULT '0' COMMENT '是否标记删除 0 未删除   1 表示删除',
  `OPT_COUNTER` int(6) DEFAULT '0' COMMENT '记录操作次',
  `DELETE_DATE` datetime DEFAULT NULL COMMENT '删除日期',
  PRIMARY KEY (`BAS_DATAGRID_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COMMENT='DATAGRID 表格控件显示数据';

-- ----------------------------
-- Records of bas_datagrid
-- ----------------------------
INSERT INTO `bas_datagrid` VALUES ('6', 'datagrid_datagrid ', 'datacolumn_tb', 'DataGrid列管理列表', '0', '1', '1', '0', '1', '1', '1', 'post', '1', 'main/datacolumn_lists.action', '', '', '1', '1', '1', '1', 'bottom', '1', '15', '[10,15,20,25,30,40,50,100]', null, '', 'asc', '1', '1', '1', '18', '', '', null, '', null, '1', '2013-04-01 22:25:07', '1', '2014-04-08 23:30:21', '0', '12', null);
INSERT INTO `bas_datagrid` VALUES ('7', 'users_datagrid', 'usergroup_tb', '已关联用户组 ', '1', '1', '1', '0', '0', '1', '1', 'post', '1', 'main/auth_usergrouplist.action', '', '', '1', '1', '0', '0', 'bottom', '1', '15', '[10,15,20,25,30,40,50,100]', null, '', 'asc', '1', '1', '0', '18', '', '', null, '', null, '1', '2013-04-02 11:10:42', '1', '2014-04-08 23:29:14', '0', '24', null);
INSERT INTO `bas_datagrid` VALUES ('8', 'users_datagrid', 'userrole_tb', '已关联角色', '1', '1', '0', '0', '0', '1', '1', 'post', '1', 'main/role_lists.action', '', '', '1', '1', '0', '0', 'bottom', '1', '15', '[10,15,20,25,30,40,50,100]', null, '', 'asc', '1', '1', '0', '18', '', '', null, '', null, '1', '2013-04-02 13:23:51', '1', '2014-04-08 23:47:06', '0', '5', null);
INSERT INTO `bas_datagrid` VALUES ('9', 'users_datagrid', 'authgroup_tb', '', '0', '1', '1', '0', '0', '1', '1', 'post', '1', 'main/auth_grouplist.action', '', '', '1', '0', '1', '0', 'bottom', '1', '15', '[10,15,20,25,30,40,50,100]', null, '', 'asc', '1', '1', '0', '18', '', '', null, '', null, '1', '2013-04-02 14:41:23', '1', '2013-04-07 19:20:02', '0', '4', null);
INSERT INTO `bas_datagrid` VALUES ('10', 'datagrid_datagrid ', 'datagrid_dt', 'DataGrid表格', '0', '1', '1', '0', '1', '1', '1', 'post', '1', 'main/datagrid_lists.action', '', '', '1', '1', '0', '0', 'bottom', '1', '15', '[10,15,20,25,30,40,50,100]', null, '', 'asc', '1', '1', '0', '18', '', '', null, '', null, '1', '2013-04-05 17:41:16', '1', '2014-04-08 23:45:56', '0', '33', null);
INSERT INTO `bas_datagrid` VALUES ('11', 'users_datagrid', 'userlist_dt', '用户列表', '0', '1', '1', '0', '1', '1', '1', 'post', '1', 'main/userlists.action', '', '', '1', '1', '0', '0', 'bottom', '1', '15', '[10,15,20,25,30,40,50,100]', null, '', 'asc', '1', '1', '0', '18', '', '', null, '', null, '1', '2013-04-05 19:42:23', '1', '2014-04-08 23:47:18', '0', '1', null);
INSERT INTO `bas_datagrid` VALUES ('12', 'users_datagrid', 'authrole_tb', null, '0', '1', '1', '0', '0', '1', '1', 'post', '1', 'main/auth_rolelist.action', '', '', '1', '0', '1', '0', 'bottom', '1', '15', '[10,15,20,25,30,40,50,100]', null, '', 'asc', '1', '1', '0', '18', '', '', null, '', null, '1', '2013-04-05 20:59:17', '1', '2013-04-08 15:06:57', '0', '8', null);
INSERT INTO `bas_datagrid` VALUES ('13', 'users_datagrid', 'sys_group_list_tb', null, '0', '1', '0', '0', '1', '1', '1', 'post', '1', 'main/group_lists.action', '', '', '1', '1', '1', '0', 'bottom', '1', '15', '[10,15,20,25,30,40,50,100]', null, '', 'asc', '1', '1', '0', '18', '', '', null, '', null, '1', '2013-04-09 17:11:43', '1', '2013-04-09 17:11:43', '0', '0', null);
INSERT INTO `bas_datagrid` VALUES ('14', 'users_datagrid', 'sys_authuserlistforgroup_tb', '已关联用户', '1', '1', '1', '0', '0', '1', '1', 'post', '1', 'main/auth_serlistforgroup.action', '', '', '0', '1', '0', '0', 'bottom', '1', '15', '[10,15,20,25,30,40,50,100]', null, '', 'asc', '1', '1', '0', '18', '', '', null, '', null, '1', '2013-04-09 20:25:43', '1', '2014-04-08 23:47:23', '0', '2', null);
INSERT INTO `bas_datagrid` VALUES ('16', 'users_datagrid', 'sys_authrolelistforgroup_tb', '已关联角色', '1', '1', '0', '0', '0', '1', '1', 'post', '1', 'main/authgroup_rolelistforgroup.action', '', '', '0', '1', '0', '0', 'bottom', '1', '15', '[10,15,20,25,30,40,50,100]', null, '', 'asc', '1', '1', '0', '18', '', '', null, '', null, '1', '2013-04-09 21:03:09', '1', '2013-04-09 23:21:55', '0', '2', null);
INSERT INTO `bas_datagrid` VALUES ('17', 'users_datagrid', 'sys_authuserforgroup_tb', '关联用户组', '0', '1', '1', '0', '0', '1', '1', 'post', '1', 'main/authgroup_userlist.action', '', '', '1', '0', '0', '0', 'bottom', '1', '15', '[10,15,20,25,30,40,50,100]', null, '', 'asc', '1', '1', '0', '18', '', '', null, '', null, '1', '2013-04-09 21:08:50', '1', '2014-04-08 23:48:29', '0', '3', null);
INSERT INTO `bas_datagrid` VALUES ('18', 'users_datagrid', 'sys_authroleforgroup_tb', '', '0', '1', '1', '0', '0', '1', '1', 'post', '1', 'main/authgroup_rolelist.action', '', '', '1', '0', '1', '0', 'bottom', '1', '15', '[10,15,20,25,30,40,50,100]', null, '', 'asc', '1', '1', '0', '18', '', '', null, '', null, '1', '2013-04-09 21:13:06', '1', '2013-04-09 22:01:22', '0', '2', null);
INSERT INTO `bas_datagrid` VALUES ('19', 'users_datagrid', 'sys_rolelist_tb', '角色列表', '0', '1', '1', '0', '1', '1', '1', 'post', '1', 'main/role_lists.action', '', '', '1', '1', '1', '0', 'bottom', '1', '15', '[10,15,20,25,30,40,50,100]', null, '', 'asc', '1', '1', '0', '18', '', '', null, '', null, '1', '2013-04-10 20:37:53', '1', '2014-04-08 23:30:51', '0', '1', null);
INSERT INTO `bas_datagrid` VALUES ('20', 'users_datagrid', 'sys_authrole_userlist_tb', '已关联用户组', '1', '1', '1', '0', '0', '1', '1', 'post', '1', 'main/authrole_userlist.action', '', '', '0', '1', '0', '0', 'bottom', '1', '15', '[10,15,20,25,30,40,50,100]', null, '', 'asc', '1', '1', '0', '18', '', '', null, '', null, '1', '2013-04-10 22:59:07', '1', '2014-04-08 23:47:34', '0', '1', null);
INSERT INTO `bas_datagrid` VALUES ('21', 'users_datagrid', 'sys_authrole_grouplist_tb', null, '0', '1', '1', '0', '1', '1', '1', 'post', '1', 'main/authrole_grouplist.action', '', '', '1', '0', '0', '0', 'bottom', '1', '15', '[10,15,20,25,30,40,50,100]', null, '', 'asc', '1', '1', '0', '18', '', '', null, '', null, '1', '2013-04-10 23:04:55', '1', '2014-04-08 17:30:07', '0', '1', null);
INSERT INTO `bas_datagrid` VALUES ('22', 'users_datagrid', 'sys_authrole_resources_tb', null, '0', '1', '0', '0', '0', '1', '1', 'post', '1', 'main/authrole_resources.action', '', '', '1', '0', '0', '0', 'bottom', '1', '15', '[10,15,20,25,30,40,50,100]', null, '', 'asc', '0', '1', '0', '18', '', '', null, '', null, '1', '2013-04-11 15:19:08', '1', '2013-04-11 16:16:02', '0', '7', null);
INSERT INTO `bas_datagrid` VALUES ('23', 'users_datagrid', 'sys_authrole_groups_tb', null, '0', '1', '1', '0', '1', '1', '1', 'post', '1', 'main/authrole_groups.action', '', '', '1', '0', '0', '0', 'bottom', '1', '15', '[10,15,20,25,30,40,50,100]', null, '', 'asc', '1', '1', '0', '18', '', '', null, '', null, '1', '2013-04-11 15:35:23', '1', '2014-04-02 17:23:29', '0', '5', null);
INSERT INTO `bas_datagrid` VALUES ('24', 'users_datagrid', 'sys_authrole_users_tb', '角色授权表格', '0', '1', '1', '0', '0', '1', '1', 'post', '1', 'main/authrole_users.action', '', '', '1', '0', '0', '0', 'bottom', '1', '15', '[10,15,20,25,30,40,50,100]', null, '', 'asc', '1', '1', '0', '18', '', '', null, '', null, '1', '2013-04-11 15:36:43', '1', '2014-04-08 23:48:03', '0', '7', null);
INSERT INTO `bas_datagrid` VALUES ('25', 'code_datagrid', 'sys_codelist_tb', '编码列表', '0', '1', '1', '0', '1', '1', '1', 'post', '1', 'main/codelist_lists.action', '', '', '1', '1', '0', '0', 'bottom', '1', '15', '[10,15,20,25,30,40,50,100]', null, '', 'asc', '1', '1', '0', '18', '', '', null, '', null, '1', '2013-04-14 09:05:30', '1', '2014-04-08 23:50:25', '0', '3', null);
INSERT INTO `bas_datagrid` VALUES ('26', 'code_datagrid', 'sys_codetype_lists_tb', '编码类型列表', '0', '1', '1', '0', '1', '1', '1', 'post', '1', 'main/codetype_lists.action', '', '', '1', '1', '0', '0', 'bottom', '1', '15', '[10,15,20,25,30,40,50,100]', null, '', 'asc', '1', '1', '0', '18', '', '', null, '', null, '1', '2013-04-14 09:47:00', '1', '2014-04-08 23:50:30', '0', '2', null);
INSERT INTO `bas_datagrid` VALUES ('27', 'system_datagrid', 'sys_resource_lists_tb', '资源列表', '0', '1', '1', '0', '1', '1', '1', 'post', '1', 'main/resource_lists.action', '', '', '1', '1', '0', '0', 'bottom', '1', '15', '[10,15,20,25,30,40,50,100]', null, '', 'asc', '1', '1', '0', '18', '', '', null, '', null, '1', '2013-04-14 11:45:43', '1', '2014-04-08 23:51:21', '0', '7', null);
INSERT INTO `bas_datagrid` VALUES ('28', 'system_datagrid', 'sys_department_lists', '部门列表', '0', '1', '0', '0', '1', '1', '0', null, '0', 'main/department_lists.action', '', '', '1', '0', '0', '0', 'bottom', '1', '15', '[10,15,20,25,30,40,50,100]', null, '', null, '0', '1', '0', '18', '', '', null, '', null, '1', '2013-04-14 16:35:22', '1', '2014-04-08 23:51:11', '0', '8', null);
INSERT INTO `bas_datagrid` VALUES ('29', 'system_datagrid', 'sys_parameter_lists_tb', '系统参数列表', '0', '1', '1', '0', '1', '1', '1', 'post', '1', 'main/parameter_lists.action', '', '', '1', '1', '0', '0', 'bottom', '1', '15', '[10,15,20,25,30,40,50,100]', null, '', 'asc', '1', '1', '0', '18', '', '', null, '', null, '1', '2013-04-14 17:26:58', '1', '2014-04-08 23:50:57', '0', '2', null);
INSERT INTO `bas_datagrid` VALUES ('30', 'system_datagrid', 'sys_department_lists2', null, '0', '1', '1', '0', '1', '1', '1', 'post', '1', '/main/system/department/lists.jhtml', '', '', '1', '1', '0', '0', 'bottom', '1', '15', '[10,15,20,25,30,40,50,100]', null, '', 'asc', '1', '1', '0', '18', '', '', null, '', null, '1', '2014-04-04 01:04:59', '1', '2014-04-04 01:10:43', '0', '2', null);
INSERT INTO `bas_datagrid` VALUES ('32', 'datagrid_datagrid ', 'sys_datagrid_tb1', 'DataGrid表格测试 ', '0', '1', '1', '0', '1', '1', '0', 'post', '1', 'sys_datagrid_tb1', '', '', '1', '0', '0', '0', 'bottom', '1', '15', '[10,15,20,25,30,40,50,100]', null, '', 'asc', '1', '1', '0', '18', '', '', null, '', null, '1', '2014-04-08 23:04:00', '1', '2014-04-08 23:46:14', '0', '2', null);
INSERT INTO `bas_datagrid` VALUES ('33', 'system_datagrid', 'sys_cachelist_tb', '缓存管理', '0', '1', '1', '0', '0', '1', '1', 'post', '1', 'sys_cachelist_tb', '', '', '1', '1', '0', '0', 'bottom', '1', '15', '[10,15,20,25,30,40,50,100]', null, '', 'asc', '0', '1', '0', '18', '', '', null, '', null, '1', '2014-04-21 15:13:40', '1', '2014-04-22 10:30:01', '0', '1', null);

-- ----------------------------
-- Table structure for `bas_demo`
-- ----------------------------
DROP TABLE IF EXISTS `bas_demo`;
CREATE TABLE `bas_demo` (
  `bas_demo_id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) DEFAULT NULL,
  `name_zh` varchar(30) DEFAULT NULL,
  `CREATE_BY` int(11) DEFAULT NULL,
  `CREATE_DATE` date DEFAULT NULL,
  `UPDATE_BY` int(11) DEFAULT NULL,
  `UPDATE_DATE` date DEFAULT NULL,
  PRIMARY KEY (`bas_demo_id`),
  KEY `FK_BAS_DEMO_PARENT_ID` (`parent_id`),
  CONSTRAINT `FK_BAS_DEMO_PARENT_ID` FOREIGN KEY (`parent_id`) REFERENCES `bas_demo` (`bas_demo_id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bas_demo
-- ----------------------------
INSERT INTO `bas_demo` VALUES ('1', null, '总公司', '1', null, null, null);
INSERT INTO `bas_demo` VALUES ('2', '1', '上海分公司', '1', null, null, null);
INSERT INTO `bas_demo` VALUES ('3', '1', '江苏分公司', '1', null, null, null);
INSERT INTO `bas_demo` VALUES ('4', '2', '上海中支公司', '1', null, null, null);
INSERT INTO `bas_demo` VALUES ('5', '2', '上海中支公司1', null, null, null, null);
INSERT INTO `bas_demo` VALUES ('6', '3', '南京中支公司', null, null, null, null);
INSERT INTO `bas_demo` VALUES ('7', '3', '常州中支公司', null, null, null, null);
INSERT INTO `bas_demo` VALUES ('8', '1', '山东分公司', null, null, null, null);
INSERT INTO `bas_demo` VALUES ('24', null, 'HP1', null, null, null, null);
INSERT INTO `bas_demo` VALUES ('25', '24', 'HP3', null, null, null, null);
INSERT INTO `bas_demo` VALUES ('26', '24', 'HP2', null, null, null, null);
INSERT INTO `bas_demo` VALUES ('27', null, 'HP1', null, null, null, null);
INSERT INTO `bas_demo` VALUES ('28', '27', 'HP3', null, null, null, null);
INSERT INTO `bas_demo` VALUES ('29', '27', 'HP2', null, null, null, null);
INSERT INTO `bas_demo` VALUES ('30', null, 'HP1', null, null, null, null);
INSERT INTO `bas_demo` VALUES ('31', '30', 'HP2', null, null, null, null);
INSERT INTO `bas_demo` VALUES ('32', '30', 'HP3', null, null, null, null);
INSERT INTO `bas_demo` VALUES ('33', null, 'HP1', null, null, null, null);
INSERT INTO `bas_demo` VALUES ('34', '33', 'HP2', null, null, null, null);
INSERT INTO `bas_demo` VALUES ('35', '33', 'HP3', null, null, null, null);
INSERT INTO `bas_demo` VALUES ('36', null, 'HP1', '1', '2014-12-23', '1', '2014-12-23');
INSERT INTO `bas_demo` VALUES ('37', '36', 'HP3', null, null, null, null);
INSERT INTO `bas_demo` VALUES ('38', '36', 'HP2', null, null, null, null);
INSERT INTO `bas_demo` VALUES ('39', null, 'HP1', '1', '2014-12-24', '1', '2014-12-24');
INSERT INTO `bas_demo` VALUES ('40', '39', 'HP2', null, null, null, null);
INSERT INTO `bas_demo` VALUES ('41', '39', 'HP3', null, null, null, null);
INSERT INTO `bas_demo` VALUES ('42', null, 'HP1', '1', '2015-01-07', '1', '2015-01-07');
INSERT INTO `bas_demo` VALUES ('43', '42', 'HP2', null, null, null, null);
INSERT INTO `bas_demo` VALUES ('44', '42', 'HP3', null, null, null, null);
INSERT INTO `bas_demo` VALUES ('45', null, 'HP1', '1', '2015-01-07', '1', '2015-01-07');
INSERT INTO `bas_demo` VALUES ('46', '45', 'HP2', null, null, null, null);
INSERT INTO `bas_demo` VALUES ('47', '45', 'HP3', null, null, null, null);

-- ----------------------------
-- Table structure for `bas_department`
-- ----------------------------
DROP TABLE IF EXISTS `bas_department`;
CREATE TABLE `bas_department` (
  `BAS_DEPARTMENT_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '部门ID',
  `PARENT_BAS_DEPARTMENT_ID` int(11) DEFAULT NULL COMMENT '父部门ID',
  `NAME_ZH` varchar(128) NOT NULL COMMENT '部门名称－中文',
  `NAME_EN` varchar(128) NOT NULL COMMENT '部门名称－英文',
  `DEPARTMENTNO` varchar(30) NOT NULL COMMENT '部门编号',
  `DESCRIPTION` varchar(256) DEFAULT NULL COMMENT '部门描述',
  `EDITABLE` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否能编辑',
  `CREATE_BY` int(11) NOT NULL COMMENT '创建人ID',
  `CREATE_DATE` datetime NOT NULL COMMENT '创建日期',
  `UPDATE_BY` int(11) NOT NULL COMMENT '更新人ID',
  `UPDATE_DATE` datetime NOT NULL COMMENT '更新日期',
  `MARK_FOR_DELETE` int(1) DEFAULT '0' COMMENT '是否标记删除 0 未删除   1 表示删除',
  `OPT_COUNTER` int(6) DEFAULT '0' COMMENT '记录操作次',
  `DELETE_DATE` datetime DEFAULT NULL COMMENT '删除日期',
  PRIMARY KEY (`BAS_DEPARTMENT_ID`),
  UNIQUE KEY `UK_BAS_DEARPMENT_NAMEZH` (`NAME_ZH`),
  KEY `FK_BAS_DEPARTMENT_PARENT_ID` (`PARENT_BAS_DEPARTMENT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COMMENT='系统部门表';

-- ----------------------------
-- Records of bas_department
-- ----------------------------
INSERT INTO `bas_department` VALUES ('3', null, 'CPIC', 'CPIC', '000001', 'CPIC集团', '1', '1', '2012-12-31 21:30:01', '1', '2012-12-31 21:30:01', '0', '0', null);
INSERT INTO `bas_department` VALUES ('8', '3', 'CPIC信息技术部', 'CPIC IT Drpt', '000021', 'CPIC信息技术部', '1', '1', '2012-12-31 21:49:31', '1', '2012-12-31 21:49:31', '0', '0', null);
INSERT INTO `bas_department` VALUES ('9', '8', '开发一部', 'IT_Drpt1', '000024', null, '1', '1', '2013-01-13 21:52:57', '1', '2013-01-13 21:52:57', '0', '0', null);
INSERT INTO `bas_department` VALUES ('10', '8', '开发二部', 'IT_Drpt2', '000022', '研发中心', '1', '1', '2012-12-31 21:52:57', '1', '2014-04-04 18:09:25', '0', '1', null);
INSERT INTO `bas_department` VALUES ('11', '3', '信息技术部1', 'HONBANG IT Drpt1', '000023', '宏邦信息技术部1', '1', '1', '2013-03-19 09:32:38', '1', '2013-03-19 09:32:38', '0', '0', null);
INSERT INTO `bas_department` VALUES ('14', null, '研发中心1', '研发中心1', '000025', '1111', '1', '1', '2013-03-22 16:47:18', '1', '2013-03-22 16:47:18', '0', '0', null);
INSERT INTO `bas_department` VALUES ('15', null, '开发部1', 'SOFTWARE1', '00000025', 'SOFTWARE1', '1', '1', '2013-03-22 16:49:08', '1', '2013-03-22 16:49:08', '0', '0', null);
INSERT INTO `bas_department` VALUES ('18', '10', 'name222222', 'DDC1', 'name222222', 'name222222', '1', '1', '2013-03-22 16:52:34', '1', '2014-04-04 18:12:18', '0', '2', null);
INSERT INTO `bas_department` VALUES ('20', '10', '研发中心3', 'DDC2', '0000022', '研发中心3', '1', '1', '2013-03-23 19:23:51', '1', '2013-03-24 00:26:49', '0', '2', null);
INSERT INTO `bas_department` VALUES ('21', '10', 'DDC3', 'DDC3', 'DDC3', 'DDC3', '1', '1', '2013-04-14 16:29:00', '1', '2013-04-14 16:29:00', '0', '0', null);
INSERT INTO `bas_department` VALUES ('22', '20', 'DDC21', 'DDC21', 'DDC21', 'DDC213', '1', '1', '2013-04-14 16:29:53', '1', '2013-04-14 16:31:40', '0', '1', null);
INSERT INTO `bas_department` VALUES ('24', '20', 'DDC22', 'DDC22', 'DDC22', 'DDC22', '1', '1', '2013-04-14 16:32:30', '1', '2013-04-14 16:32:30', '0', '0', null);
INSERT INTO `bas_department` VALUES ('25', '21', 'DDC221', 'DDC221', 'DDC221', 'DDC221', '1', '1', '2013-04-14 16:32:52', '1', '2013-04-14 16:32:52', '0', '0', null);
INSERT INTO `bas_department` VALUES ('26', '18', 'name22222210', 'name22222210', 'name22222210', 'name222222100', '1', '1', '2014-04-04 18:12:34', '1', '2014-04-04 18:26:48', '0', '2', null);
INSERT INTO `bas_department` VALUES ('30', '21', 'name3333333', 'name3333333', 'name3333333', 'name3333333', '1', '1', '2014-04-04 18:27:11', '1', '2014-04-04 18:27:11', '0', '0', null);
INSERT INTO `bas_department` VALUES ('32', '31', 'name2233332', 'name2233332', 'name2233332', 'name2233332', '1', '1', '2014-04-08 14:56:35', '1', '2014-04-08 14:56:35', '0', '0', null);

-- ----------------------------
-- Table structure for `bas_group`
-- ----------------------------
DROP TABLE IF EXISTS `bas_group`;
CREATE TABLE `bas_group` (
  `BAS_GROUP_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色组ID',
  `NAME_ZH` varchar(128) NOT NULL COMMENT '角色组中文名称',
  `NAME_EN` varchar(128) NOT NULL COMMENT '角色组英语名称',
  `DESCRIPTION` varchar(256) DEFAULT NULL COMMENT '角色组描述',
  `EDITABLE` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否可编辑',
  `CREATE_BY` int(11) NOT NULL COMMENT '系统默认字段.创建人ID',
  `CREATE_DATE` datetime NOT NULL COMMENT '系统默认字段.创建日期',
  `UPDATE_BY` int(11) NOT NULL COMMENT '系统默认字段.更新人ID',
  `UPDATE_DATE` datetime NOT NULL COMMENT '系统默认字段.更新日期',
  `MARK_FOR_DELETE` int(1) DEFAULT '0' COMMENT '系统默认字段.逻辑删除标记  0 未删除   1 表示删除',
  `OPT_COUNTER` int(6) DEFAULT '0' COMMENT '系统默认字段.版本记录',
  `DELETE_DATE` datetime DEFAULT NULL COMMENT '删除日期',
  PRIMARY KEY (`BAS_GROUP_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='角色组表';

-- ----------------------------
-- Records of bas_group
-- ----------------------------
INSERT INTO `bas_group` VALUES ('1', '系统管理员', 'SysAdmin', '描述', '1', '1', '2013-03-23 22:40:16', '1', '2014-04-06 00:55:38', '0', '90', null);
INSERT INTO `bas_group` VALUES ('2', '产品管理员', 'ProductsUser', '产品管理员', '0', '1', '2013-03-23 22:44:40', '1', '2014-04-06 00:52:50', '0', '28', null);
INSERT INTO `bas_group` VALUES ('3', '销售管理员', 'SalesUser', '销售管理员', '1', '1', '2013-03-24 10:30:13', '1', '2014-04-06 01:02:33', '0', '26', null);
INSERT INTO `bas_group` VALUES ('4', '客户管理员', 'CustomersUser', '客户管理员', '1', '1', '2013-03-24 10:30:47', '1', '2014-03-31 16:14:51', '0', '16', null);
INSERT INTO `bas_group` VALUES ('5', '邮件管理员', 'EmailUser', '邮件管理员', '1', '1', '2013-04-02 13:38:48', '2', '2014-04-06 01:08:03', '0', '22', null);
INSERT INTO `bas_group` VALUES ('6', '报表管理员', 'ReportAdmin', '报表管理员', '1', '1', '2013-04-10 20:27:30', '2', '2014-04-06 01:07:37', '0', '3', null);
INSERT INTO `bas_group` VALUES ('7', 'name22222210', 'name222222', 'name22222210', '1', '2', '2014-04-06 01:03:15', '5', '2014-04-06 01:06:17', '0', '3', null);

-- ----------------------------
-- Table structure for `bas_group_role`
-- ----------------------------
DROP TABLE IF EXISTS `bas_group_role`;
CREATE TABLE `bas_group_role` (
  `BAS_GROUP_ID` int(11) NOT NULL COMMENT '角色组ID',
  `BAS_ROLE_ID` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`BAS_GROUP_ID`,`BAS_ROLE_ID`),
  KEY `FK_BAS_GROUP_ROLE_ROLE_ID_001` (`BAS_ROLE_ID`),
  CONSTRAINT `FK_BAS_GROUP_ROLE_GROUP_ID_001` FOREIGN KEY (`BAS_GROUP_ID`) REFERENCES `bas_group` (`BAS_GROUP_ID`),
  CONSTRAINT `FK_BAS_GROUP_ROLE_ROLE_ID_001` FOREIGN KEY (`BAS_ROLE_ID`) REFERENCES `bas_role` (`BAS_ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户组与角色关系表';

-- ----------------------------
-- Records of bas_group_role
-- ----------------------------
INSERT INTO `bas_group_role` VALUES ('2', '1');
INSERT INTO `bas_group_role` VALUES ('3', '1');
INSERT INTO `bas_group_role` VALUES ('4', '1');
INSERT INTO `bas_group_role` VALUES ('1', '2');
INSERT INTO `bas_group_role` VALUES ('2', '2');
INSERT INTO `bas_group_role` VALUES ('4', '2');
INSERT INTO `bas_group_role` VALUES ('5', '2');
INSERT INTO `bas_group_role` VALUES ('1', '4');
INSERT INTO `bas_group_role` VALUES ('2', '4');
INSERT INTO `bas_group_role` VALUES ('3', '4');
INSERT INTO `bas_group_role` VALUES ('4', '4');
INSERT INTO `bas_group_role` VALUES ('1', '6');
INSERT INTO `bas_group_role` VALUES ('2', '6');
INSERT INTO `bas_group_role` VALUES ('3', '6');
INSERT INTO `bas_group_role` VALUES ('4', '6');
INSERT INTO `bas_group_role` VALUES ('1', '8');
INSERT INTO `bas_group_role` VALUES ('2', '8');

-- ----------------------------
-- Table structure for `bas_login_log`
-- ----------------------------
DROP TABLE IF EXISTS `bas_login_log`;
CREATE TABLE `bas_login_log` (
  `BAS_LOGIN_LOG_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `BAS_USER_ID` int(11) DEFAULT NULL COMMENT '用户ID',
  `LOGIN_IP` varchar(20) DEFAULT NULL COMMENT '登录用户IP',
  `LOGIN_MAC` varchar(20) DEFAULT NULL COMMENT '登录用户MAC',
  `BROWSER_NAME` varchar(100) DEFAULT NULL COMMENT '登录用户浏览器名称、版本信息等',
  `SESSION_ID` varchar(60) DEFAULT NULL COMMENT '会话ID',
  `LOGIN_RESULT` varchar(120) DEFAULT NULL COMMENT '登录结果',
  `LOGIN_TIME` datetime DEFAULT NULL COMMENT '登录时间',
  `LOGOUT_TIME` datetime DEFAULT NULL COMMENT '退出时间',
  `OS_INFO` varchar(120) DEFAULT NULL COMMENT '用户的操作系统',
  `IS_SUCCESS` tinyint(1) DEFAULT '1' COMMENT '是否登录成功',
  `CREATE_BY` int(11) NOT NULL COMMENT '系统默认字段.创建人ID',
  `CREATE_DATE` datetime NOT NULL COMMENT '系统默认字段.创建日期',
  `UPDATE_BY` int(11) NOT NULL COMMENT '系统默认字段.更新人ID',
  `UPDATE_DATE` datetime NOT NULL COMMENT '系统默认字段.更新日期',
  `MARK_FOR_DELETE` int(1) DEFAULT '0' COMMENT '系统默认字段.逻辑删除标记  0 未删除   1 表示删除',
  `OPT_COUNTER` int(6) DEFAULT '0' COMMENT '系统默认字段.版本记录',
  `DELETE_DATE` datetime DEFAULT NULL COMMENT '系统默认字段.删除日期',
  PRIMARY KEY (`BAS_LOGIN_LOG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户登录信息表';

-- ----------------------------
-- Records of bas_login_log
-- ----------------------------

-- ----------------------------
-- Table structure for `bas_parameter`
-- ----------------------------
DROP TABLE IF EXISTS `bas_parameter`;
CREATE TABLE `bas_parameter` (
  `BAS_PARAMETER_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '系统参数ID',
  `CATEGORY` varchar(30) NOT NULL COMMENT '系统参数类型',
  `NAME_EN` varchar(128) NOT NULL COMMENT '系统参数英文名称，最小为：6，最大为非作歹0100',
  `NAME_ZH` varchar(128) NOT NULL COMMENT '系统参数中文名称',
  `VALUE` varchar(256) NOT NULL COMMENT '系统参数值',
  `EDITABLE` tinyint(1) DEFAULT '1' COMMENT '是否可编辑',
  `DESCRIPTION` varchar(512) DEFAULT NULL COMMENT '系统参数描述',
  `CREATE_BY` int(11) NOT NULL COMMENT '创建人ID',
  `CREATE_DATE` datetime NOT NULL COMMENT '创建日期',
  `UPDATE_BY` int(11) NOT NULL COMMENT '更新人ID',
  `UPDATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `MARK_FOR_DELETE` int(1) DEFAULT '0' COMMENT '是否标记删除 0 未删除   1 表示删除',
  `OPT_COUNTER` int(6) DEFAULT '0' COMMENT '记录操作次',
  `DELETE_DATE` datetime DEFAULT NULL COMMENT '删除日期',
  PRIMARY KEY (`BAS_PARAMETER_ID`),
  UNIQUE KEY `UK_BAS_PARAMETER_NAMEEN` (`NAME_ZH`)
) ENGINE=InnoDB AUTO_INCREMENT=181 DEFAULT CHARSET=utf8 COMMENT=' 系统参数定义表';

-- ----------------------------
-- Records of bas_parameter
-- ----------------------------
INSERT INTO `bas_parameter` VALUES ('1', 'Files', 'uploadFilePath1', '上传文件路径11', 'c:/temp/', '0', '文件上传路径', '1', '2012-12-16 20:31:11', '1', '2014-04-02 15:57:37', '0', '3', null);
INSERT INTO `bas_parameter` VALUES ('4', 'systems', 'uploadFilePath111', '上传文件路径111', 'c:/temp/111', '1', '文件上传路径', '1', '2012-12-16 21:28:26', '1', '2013-04-14 22:19:47', '0', '3', null);
INSERT INTO `bas_parameter` VALUES ('5', 'Users', 'uploadFilePath', '上传文件路径', 'c:/temp/temp/', '1', '文件上传路径1', '1', '2012-12-16 21:28:27', '1', '2014-04-02 17:19:57', '0', '17', null);
INSERT INTO `bas_parameter` VALUES ('9', 'systems', 'systemName', '系统名称', 'eCommerce', '1', '', '1', '2013-03-12 16:24:21', '1', '2013-04-05 11:39:52', '0', '3', null);
INSERT INTO `bas_parameter` VALUES ('78', 'systems', 'PwdInitialization', '用户初始化密码', '111aaa', '1', '用户初始化密码,用于管理系统用户的创建的密码', '1', '2013-03-23 18:36:53', '1', '2014-03-28 13:55:56', '0', '1', null);
INSERT INTO `bas_parameter` VALUES ('99', 'Files', 'name2', 'name2', 'name2', '1', 'name2', '1', '2013-04-14 23:02:55', '1', '2013-04-14 23:02:55', '0', '0', null);
INSERT INTO `bas_parameter` VALUES ('101', 'Files', 'name3', 'name3', 'name3', '1', 'name3', '1', '2013-04-14 23:04:06', '1', '2014-04-05 11:49:24', '0', '1', null);
INSERT INTO `bas_parameter` VALUES ('120', 'systems', 'uploadFilePath1', '上传文ss', 'c:/temp/', null, '文件上传路径', '1', '2013-04-20 22:30:05', '1', '2013-04-20 22:30:05', '0', '0', null);
INSERT INTO `bas_parameter` VALUES ('128', 'systems', 'name2222', 'name222', 'name', '0', '', '1', '2013-05-21 20:35:31', '1', '2013-05-21 20:35:31', '0', '0', null);
INSERT INTO `bas_parameter` VALUES ('135', 'Users', 'name223333', 'name22', 'name22', '1', '22ddd', '1', '2014-03-27 15:22:05', '1', '2014-03-30 13:23:08', '0', '1', null);
INSERT INTO `bas_parameter` VALUES ('138', 'systems', 'name2333222', 'name222dd', 'nameeeddd', '1', '', '1', '2014-03-27 15:25:28', '1', '2014-03-27 15:25:28', '0', '0', null);
INSERT INTO `bas_parameter` VALUES ('139', 'systems', 'name23332221', 'name222dd1', 'nameeeddd1', '1', '', '1', '2014-03-27 15:26:07', '1', '2014-03-27 15:26:07', '0', '0', null);
INSERT INTO `bas_parameter` VALUES ('140', 'systems', 'wanglijun', 'wanglijun', 'wanglijun', '1', '3333', '1', '2014-03-27 15:26:30', '1', '2014-03-27 15:26:30', '0', '0', null);
INSERT INTO `bas_parameter` VALUES ('141', 'systems', 'wanglijun1', 'wanglijun1', 'wanglijun1', '1', 'wanglijun1', '1', '2014-03-27 15:27:53', '1', '2014-03-27 15:27:53', '0', '0', null);
INSERT INTO `bas_parameter` VALUES ('142', 'systems', 'wanglijun3', 'wanglijun3', 'wanglijun3', '1', 'wanglijun3', '1', '2014-03-27 15:32:15', '1', '2014-03-27 15:32:15', '0', '0', null);
INSERT INTO `bas_parameter` VALUES ('143', 'systems', 'name', 'name', 'name', '1', 'name', '1', '2014-03-27 15:32:45', '1', '2014-03-27 15:32:45', '0', '0', null);
INSERT INTO `bas_parameter` VALUES ('144', 'systems', 'name2ddd', 'named', 'nameddd', '1', 'name', '1', '2014-03-27 15:32:57', '1', '2014-03-27 15:32:57', '0', '0', null);
INSERT INTO `bas_parameter` VALUES ('145', 'systems', 'name222222', 'name222222', 'name222222', '1', 'name222222', '1', '2014-03-27 15:34:23', '1', '2014-03-27 15:34:23', '0', '0', null);
INSERT INTO `bas_parameter` VALUES ('154', 'systems', 'wanglijun5', 'wanglijun5', 'wanglijun5', '1', 'name2222', '1', '2014-03-27 15:35:58', '1', '2014-03-27 15:35:58', '0', '0', null);
INSERT INTO `bas_parameter` VALUES ('155', 'systems', 'name3333', 'name3333', 'name3333', '1', 'name3333', '1', '2014-03-27 15:37:00', '1', '2014-03-27 15:37:00', '0', '0', null);
INSERT INTO `bas_parameter` VALUES ('156', 'systems', 'isVaild', 'isVaild', 'isVaild', '1', 'isVaild', '1', '2014-03-27 15:47:34', '1', '2014-03-27 15:47:34', '0', '0', null);
INSERT INTO `bas_parameter` VALUES ('157', 'systems', 'wanglijun8', 'wanglijun8', 'wanglijun8', '1', 'wangliju', '1', '2014-03-27 15:47:53', '1', '2014-03-28 17:45:15', '0', '1', null);
INSERT INTO `bas_parameter` VALUES ('158', 'Files', 'wanglijun10', 'wanglijun10', 'wanglijun10', '1', 'wanglijun10', '1', '2014-03-27 15:48:34', '1', '2014-03-27 21:30:49', '0', '1', null);
INSERT INTO `bas_parameter` VALUES ('159', 'systems', 'wanlgijun9', 'wanlgijun9', 'wanlgijun9', '1', 'wanlgijun9', '1', '2014-03-27 15:49:27', '1', '2014-03-27 21:31:25', '0', '4', null);
INSERT INTO `bas_parameter` VALUES ('160', 'systems', 'name5', 'name5', 'name5', '1', 'name5', '1', '2014-03-27 15:50:07', '1', '2014-03-27 21:31:06', '0', '2', null);
INSERT INTO `bas_parameter` VALUES ('161', 'systems', 'wanglijun9', 'wanglijun9', 'wanglijun9', '1', '修改成功', '1', '2014-03-27 15:52:06', '1', '2014-03-27 21:31:33', '0', '2', null);
INSERT INTO `bas_parameter` VALUES ('162', 'systems', 'name200', 'name200', 'name200', '1', 'name200', '1', '2014-04-02 15:52:34', '1', '2014-04-02 15:52:34', '0', '0', null);
INSERT INTO `bas_parameter` VALUES ('163', 'sysInterface', 'userInterface', '用户登录接口', 'http://www.site.com/user', '1', '用户登录接口', '1', '2014-06-01 21:49:10', '1', '2014-06-01 21:50:12', '0', '3', null);
INSERT INTO `bas_parameter` VALUES ('164', 'sysInterface', 'policyApplyInterface', '保单承保接口', 'http://www.site.com/policyapply', '1', '保单承保接口', '1', '2014-06-01 21:51:10', '1', '2014-06-01 21:51:10', '0', '0', null);
INSERT INTO `bas_parameter` VALUES ('165', 'sysInterface', 'smsInterface', '短信接口', '192.168.1.60', '1', '短信接口', '1', '2014-06-01 21:51:57', '1', '2014-06-01 21:51:57', '0', '0', null);
INSERT INTO `bas_parameter` VALUES ('166', 'sysInterface', 'emailInterface', '邮件接口', '192.168.1.79', '1', '邮件接口', '1', '2014-06-01 21:52:47', '1', '2014-06-01 21:52:47', '0', '0', null);
INSERT INTO `bas_parameter` VALUES ('167', 'sysInterface', 'mpi.backTransUrl', '后台交易请求地址', 'https://unionpaysecure.com/gateway/api/frontTransRequest.do,https://unionpaysecure.com/gateway/api/backTransRequest.do', '1', '后台交易请求地址', '1', '2014-06-01 21:54:39', '1', '2014-06-01 21:58:55', '0', '1', null);
INSERT INTO `bas_parameter` VALUES ('168', 'sysInterface', 'mpi.frontTransUrl', '前台交易请求地址', 'https://unionpaysecure.com/gateway/api/frontTransRequest.do', '1', '前台交易请求地址', '1', '2014-06-01 21:59:42', '1', '2014-06-01 21:59:42', '0', '0', null);
INSERT INTO `bas_parameter` VALUES ('169', 'sysInterface', 'mpi.singleQueryUrl', '单笔查询请求地址', 'https://unionpaysecure.com/gateway/api/singleQueryRequest.do', '1', '单笔查询请求地址', '1', '2014-06-01 22:00:11', '1', '2014-06-01 22:00:11', '0', '0', null);
INSERT INTO `bas_parameter` VALUES ('170', 'sysInterface', 'mpi.batchTransUrl', '批量交易请求地址', 'https://unionpaysecure.com/gateway/api/batchTransRequest.do', '1', '批量交易请求地址', '1', '2014-06-01 22:00:58', '1', '2014-06-01 22:00:58', '0', '0', null);
INSERT INTO `bas_parameter` VALUES ('171', 'sysInterface', 'mpi.batchQueryUrl', '批量查询请求地址', 'https://unionpaysecure.com/gateway/api/batchQueryRequest.do', '1', '批量查询请求地址', '1', '2014-06-01 22:02:33', '1', '2014-06-01 22:03:44', '0', '2', null);
INSERT INTO `bas_parameter` VALUES ('172', 'sysInterface', 'mpi.fileTransUrl', '文件传输交易地址', 'https://unionpaysecure.com/gateway/api/fileTransRequest.do', '1', '文件传输交易地址', '1', '2014-06-01 22:03:11', '1', '2014-06-01 22:03:11', '0', '0', null);
INSERT INTO `bas_parameter` VALUES ('173', 'sysInterface', 'mpi.cbFrontTransUrl', '跨行收单开通并支付交易地址', 'https://unionpaysecure.com/gateway/cb/frontTransRequest.do', '1', '跨行收单开通并支付交易地址', '1', '2014-06-01 22:04:20', '1', '2014-06-01 22:04:20', '0', '0', null);
INSERT INTO `bas_parameter` VALUES ('174', 'sysInterface', 'mpi.cbBackTransUrl', '跨行收单支付地址', 'https://unionpaysecure.com/gateway/cb/backTransRequest.do', '1', '跨行收单支付地址', '1', '2014-06-01 22:04:54', '1', '2014-06-01 22:04:54', '0', '0', null);
INSERT INTO `bas_parameter` VALUES ('175', 'sysInterface', 'mpi.signCert.path', '签名证书路径', '/weblog10/bea/deploy_files/ICBC/tel/700000000000001.pfx', '1', '签名证书路径', '1', '2014-06-01 22:05:26', '1', '2014-06-01 22:05:26', '0', '0', null);
INSERT INTO `bas_parameter` VALUES ('176', 'sysInterface', 'mpi.signCert.pwd', '数字证书密码', '000000', '1', '数字证书密码', '1', '2014-06-01 22:06:10', '1', '2014-06-01 22:06:10', '0', '0', null);
INSERT INTO `bas_parameter` VALUES ('177', 'sysInterface', 'mpi.signCert.type', '数字证书类型', 'PKCS12', '1', '数字证书类型\r\n', '1', '2014-06-01 22:06:44', '1', '2014-06-01 22:06:44', '0', '0', null);
INSERT INTO `bas_parameter` VALUES ('178', 'sysInterface', 'mpi.encryptCert.path', '密码加密证书路径', '/weblog10/bea/deploy_files/ICBC/tel/PM_enc.cer', '1', '密码加密证书路径，敏感信息加密证书路径，请根据实际情况进行修改', '1', '2014-06-01 22:07:26', '1', '2014-06-01 22:07:26', '0', '0', null);
INSERT INTO `bas_parameter` VALUES ('179', 'sysInterface', 'mpi.validateCert.dir', '验证签名证书目录', '/weblog10/bea/deploy_files/ICBC/tel', '1', '验证签名证书目录，验证银联系统返回报文的证书目录', '1', '2014-06-01 22:08:05', '1', '2014-06-01 22:08:19', '0', '1', null);
INSERT INTO `bas_parameter` VALUES ('180', 'sysInterface', 'mpi.merchantNo', '银联在线商户号', '102310063000015', '1', '银联在线商户号', '1', '2014-06-01 22:08:48', '1', '2014-06-01 22:08:48', '0', '0', null);

-- ----------------------------
-- Table structure for `bas_position`
-- ----------------------------
DROP TABLE IF EXISTS `bas_position`;
CREATE TABLE `bas_position` (
  `BAS_POSITION_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `BAS_PARENT_POSITION_ID` int(11) DEFAULT NULL COMMENT '父级岗位ID',
  `BAS_DEPARTMENT_ID` int(11) NOT NULL COMMENT '部门ID',
  `NAME_EN` varchar(128) NOT NULL COMMENT '位岗文英名称',
  `NAME_ZH` varchar(128) NOT NULL COMMENT '位岗中文名称',
  `DESCRIPTION` varchar(512) DEFAULT NULL COMMENT '位岗描述',
  `CREATE_BY` int(11) NOT NULL COMMENT '创建人ID',
  `CREATE_DATE` datetime NOT NULL COMMENT '创建日期',
  `UPDATE_BY` int(11) NOT NULL COMMENT '更新人ID',
  `UPDATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `MARK_FOR_DELETE` int(1) DEFAULT '0' COMMENT '是否标记删除 0 未删除   1 表示删除',
  `OPT_COUNTER` int(6) DEFAULT '0' COMMENT '记录操作次',
  `DELETE_DATE` datetime DEFAULT NULL COMMENT '删除日期',
  PRIMARY KEY (`BAS_POSITION_ID`),
  UNIQUE KEY `UK_BAS_PARAMETER_NAMEEN` (`NAME_ZH`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=' 系统参数定义表';

-- ----------------------------
-- Records of bas_position
-- ----------------------------

-- ----------------------------
-- Table structure for `bas_reminder_body`
-- ----------------------------
DROP TABLE IF EXISTS `bas_reminder_body`;
CREATE TABLE `bas_reminder_body` (
  `BAS_REMINDER_BODY_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '表ID',
  `TITLE_KEY` varchar(100) DEFAULT NULL COMMENT '标题的消息Key,与资源文件对应',
  `CONTENT_KEY` varchar(100) DEFAULT NULL COMMENT '内容的消息Key,与资源文件对应',
  `RELATED_URL` varchar(255) DEFAULT NULL COMMENT '相关页面链接，相对地址',
  `IMPORTANCE_LEVEL` int(1) DEFAULT NULL COMMENT '重要级别，数字越大越重要',
  `TITLE_PARAMS` varchar(1024) DEFAULT NULL COMMENT '标题的消息参数，分隔符分割',
  `CONTENT_PARAMS` varchar(1024) DEFAULT NULL COMMENT '内容的消息参数，分隔符分割',
  `CREATE_BY` int(11) NOT NULL COMMENT '系统默认字段.创建人ID',
  `CREATE_DATE` datetime NOT NULL COMMENT '系统默认字段.创建日期',
  `UPDATE_BY` int(11) NOT NULL COMMENT '系统默认字段.更新人ID',
  `UPDATE_DATE` datetime NOT NULL COMMENT '系统默认字段.更新日期',
  `MARK_FOR_DELETE` int(1) DEFAULT '0' COMMENT '系统默认字段.逻辑删除标记  0 未删除   1 表示删除',
  `OPT_COUNTER` int(6) DEFAULT '0' COMMENT '系统默认字段.版本记录',
  `DELETE_DATE` datetime DEFAULT NULL COMMENT '系统默认字段.删除日期',
  PRIMARY KEY (`BAS_REMINDER_BODY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统消息表';

-- ----------------------------
-- Records of bas_reminder_body
-- ----------------------------

-- ----------------------------
-- Table structure for `bas_reminder_reader`
-- ----------------------------
DROP TABLE IF EXISTS `bas_reminder_reader`;
CREATE TABLE `bas_reminder_reader` (
  `BAS_REMINDER_READER_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '表ID',
  `BAS_USER_ID` int(11) DEFAULT NULL COMMENT '接收消息用户ID',
  `IS_READ` tinyint(1) DEFAULT '0' COMMENT '是否阅读 默认为：false',
  `BAS_REMINDER_BODY_ID` int(11) DEFAULT NULL COMMENT '接收消息主体ID',
  `CREATE_BY` int(11) NOT NULL COMMENT '系统默认字段.创建人ID',
  `CREATE_DATE` datetime NOT NULL COMMENT '系统默认字段.创建日期',
  `UPDATE_BY` int(11) NOT NULL COMMENT '系统默认字段.更新人ID',
  `UPDATE_DATE` datetime NOT NULL COMMENT '系统默认字段.更新日期',
  `MARK_FOR_DELETE` int(1) DEFAULT '0' COMMENT '系统默认字段.逻辑删除标记  0 未删除   1 表示删除',
  `OPT_COUNTER` int(6) DEFAULT '0' COMMENT '系统默认字段.版本记录',
  `DELETE_DATE` datetime DEFAULT NULL COMMENT '系统默认字段.删除日期',
  PRIMARY KEY (`BAS_REMINDER_READER_ID`),
  KEY `FK_BAS_REMINDER_READER_BODY_ID_001` (`BAS_REMINDER_BODY_ID`),
  CONSTRAINT `FK_BAS_REMINDER_READER_BODY_ID_001` FOREIGN KEY (`BAS_REMINDER_BODY_ID`) REFERENCES `bas_reminder_body` (`BAS_REMINDER_BODY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='接收消息和用户关系表，\r\n标记是否阅读';

-- ----------------------------
-- Records of bas_reminder_reader
-- ----------------------------

-- ----------------------------
-- Table structure for `bas_resource`
-- ----------------------------
DROP TABLE IF EXISTS `bas_resource`;
CREATE TABLE `bas_resource` (
  `BAS_RESOURCE_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '资源ID',
  `PARENT_BAS_RESOURCE_ID` int(11) DEFAULT NULL COMMENT '父资源ID，可空',
  `TYPE` varchar(256) DEFAULT NULL COMMENT '资源类型，应用自定义，参见CODELIS',
  `PATH` varchar(1024) DEFAULT NULL COMMENT '资源路径(URL,CLASS.METHOD)',
  `NAME_ZH` varchar(256) DEFAULT NULL COMMENT '资源中文名称',
  `NAME_EN` varchar(256) DEFAULT NULL COMMENT '资源英文名称',
  `DESCRIPTION` varchar(256) DEFAULT NULL COMMENT '资源描述',
  `SEQ_NUM` int(11) DEFAULT NULL COMMENT '资源同级排序',
  `IS_LEAF` tinyint(1) DEFAULT '1' COMMENT '是否叶节点，其下没有子资源 默认为：true\r\n            ',
  `EDITABLE` tinyint(1) DEFAULT '1' COMMENT '记录是否可编辑  默认为true\r\n            ',
  `TARGET` varchar(16) DEFAULT NULL COMMENT '指HTML链接的target属性',
  `ICON` varchar(30) DEFAULT NULL COMMENT '资源图标',
  `CREATE_BY` int(11) NOT NULL COMMENT '系统默认字段.创建人ID',
  `CREATE_DATE` datetime NOT NULL COMMENT '系统默认字段.创建日期',
  `UPDATE_BY` int(11) NOT NULL COMMENT '系统默认字段.更新人ID',
  `UPDATE_DATE` datetime NOT NULL COMMENT '系统默认字段.更新日期',
  `MARK_FOR_DELETE` int(1) DEFAULT '0' COMMENT '系统默认字段.逻辑删除标记  0 未删除   1 表示删除',
  `OPT_COUNTER` int(6) DEFAULT '0' COMMENT '系统默认字段.版本记录',
  `DELETE_DATE` datetime DEFAULT NULL COMMENT '系统默认字段.删除日期',
  PRIMARY KEY (`BAS_RESOURCE_ID`),
  KEY `FK_BAS_PARENT_BAS_RESOURCE_ID` (`PARENT_BAS_RESOURCE_ID`),
  CONSTRAINT `FK_BAS_PARENT_BAS_RESOURCE_ID` FOREIGN KEY (`PARENT_BAS_RESOURCE_ID`) REFERENCES `bas_resource` (`BAS_RESOURCE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8 COMMENT='系统资源表';

-- ----------------------------
-- Records of bas_resource
-- ----------------------------
INSERT INTO `bas_resource` VALUES ('1', null, 'application', 'Application', '应用资源', 'application', 'Application', '0', '0', '1', '_blank', 'icon-star', '1', '2013-03-25 15:56:44', '1', '2013-03-25 17:41:06', '0', '1', null);
INSERT INTO `bas_resource` VALUES ('2', '1', 'module', 'SystemConfig', '系统设置', 'SystemConfig', '系统设置', '1', null, '1', '_parent', 'icon-settings', '1', '2013-03-25 17:16:05', '1', '2013-03-25 17:16:05', '0', '0', null);
INSERT INTO `bas_resource` VALUES ('4', '1', 'module', '报表中心', '报表中心', 'ReportCenter', '111', '5', null, '1', '_blank', 'icon-bar-chart', '1', '2013-03-25 17:20:33', '1', '2013-03-25 17:20:33', '0', '0', null);
INSERT INTO `bas_resource` VALUES ('5', '1', 'module', 'documentManage', '单证管理', 'documentManage', '单证管理', '4', null, '1', '_parent', 'icon-paper-plane', '1', '2013-03-25 17:43:02', '1', '2013-03-25 17:43:02', '0', '0', null);
INSERT INTO `bas_resource` VALUES ('6', '1', 'application', 'supplier', '供应商管理', 'supplier', '', '3', null, '1', '_blank', 'icon-puzzle', '1', '2013-03-25 17:46:35', '1', '2013-03-25 17:46:35', '0', '0', null);
INSERT INTO `bas_resource` VALUES ('7', '1', 'module', 'customers', '客户管理', 'customers', '客户管理', '3', '1', '1', '_blank', 'icon-user', '1', '2013-03-25 17:47:49', '1', '2014-04-08 14:47:23', '0', '1', null);
INSERT INTO `bas_resource` VALUES ('8', '1', 'module', 'ProductManage', '产品管理', 'ProductManage', '产品管理', '1', '0', '1', '_blank', 'icon-present', '1', '2013-03-25 18:31:10', '8', '2014-05-22 17:52:57', '0', '1', null);
INSERT INTO `bas_resource` VALUES ('9', '2', 'module_menu_category', 'usermanage', '用户管理', 'usermanage', '用户管理', '0', null, '1', '_blank', 'icon-star', '1', '2013-03-25 19:26:09', '1', '2013-03-25 19:26:09', '0', '5', null);
INSERT INTO `bas_resource` VALUES ('10', '2', 'module_menu_category', 'CodeManage', '编码管理', 'CodeManage', '编码管理', '1', '0', '1', '_blank', 'icon-star', '1', '2013-03-25 19:28:08', '1', '2014-04-05 11:39:39', '0', '4', null);
INSERT INTO `bas_resource` VALUES ('11', '2', 'module_menu_category_item', '/system/resource/index.htm', '资源管理', 'ResourceManage', '', '2', null, '1', '_parent', 'icon-star', '1', '2013-03-25 19:30:56', '1', '2013-03-26 22:51:30', '0', '1', null);
INSERT INTO `bas_resource` VALUES ('12', '2', 'module_menu_category_item', '/system/parameter/index.htm', '系统参数', 'ParameterManage', '系统参数', '3', null, '1', '_blank', 'icon-star', '1', '2013-03-25 19:36:46', '1', '2013-03-26 22:53:21', '0', '2', null);
INSERT INTO `bas_resource` VALUES ('13', '1', 'module_menu_category_item', '/index.htm', '控制面板', 'MyMenu', '控制面板', '0', null, '1', '_blank', 'icon-home', '1', '2013-03-25 19:43:04', '1', '2013-03-25 19:43:04', '0', '0', null);
INSERT INTO `bas_resource` VALUES ('14', '9', 'module_menu_category_item', '/system/role/index.htm', '角色管理', 'RoleManage', '角色管理', '0', null, '1', '_blank', 'icon-star', '1', '2013-03-25 19:59:34', '1', '2013-03-26 22:46:59', '0', '2', null);
INSERT INTO `bas_resource` VALUES ('15', '9', 'module_menu_category_item', '/system/group/index.htm', '用户组管理', 'RoleManage', '用户组管理', '1', null, '1', '_blank', 'icon-star', '1', '2013-03-25 20:10:13', '1', '2013-03-26 22:47:25', '0', '2', null);
INSERT INTO `bas_resource` VALUES ('16', '10', 'module_menu_category_item', '/system/codetype/index.htm', '通用编码类型', 'codeType', '通用编码类型', '0', null, '1', '_blank', 'icon-star', '1', '2013-03-26 22:49:20', '1', '2013-03-26 22:49:20', '0', '0', null);
INSERT INTO `bas_resource` VALUES ('17', '10', 'module_menu_category_item', 'system/codelist/index.htm', '编码列表', 'codelist', '编码列表', '1', null, '1', '_blank', 'icon-star', '1', '2013-03-26 22:51:12', '1', '2013-03-27 21:00:16', '0', '1', null);
INSERT INTO `bas_resource` VALUES ('18', '9', 'module_menu_category_item', '/system/user/index.htm', '用户管理', 'userManage', '用户管理', '2', null, '1', '_blank', 'icon-star', '1', '2013-03-27 14:13:53', '1', '2013-03-27 14:13:53', '0', '0', null);
INSERT INTO `bas_resource` VALUES ('19', '2', 'module_menu_category_item', '/system/department/index.htm', '部门管理', 'DepartmentManage', '部门管理', '1', '0', '1', '_blank', 'icon-star', '1', '2013-03-27 14:17:33', '1', '2014-05-22 10:24:05', '0', '1', null);
INSERT INTO `bas_resource` VALUES ('20', '2', 'module_menu_category', 'DataGridManage', 'DataGrid管理', 'DataGrid', 'DataGrid管理', '5', '0', '1', '_blank', 'icon-star', '1', '2013-03-27 20:59:31', '1', '2013-03-30 10:59:06', '0', '11', null);
INSERT INTO `bas_resource` VALUES ('21', '20', 'module_menu_category_item', '/system/datagrid/index.htm', 'DataGrid', 'DataGrid', 'DataGrid', '0', null, '1', '_blank', 'icon-star', '1', '2013-03-27 21:01:06', '1', '2013-03-27 21:47:27', '0', '1', null);
INSERT INTO `bas_resource` VALUES ('24', '20', 'module_menu_category_item', '/system/datacolumn/index.htm', 'DataColumn管理', 'DataColumn', 'DataColumn管理', '2', '1', '1', '_blank', 'icon-star', '1', '2013-03-27 21:47:09', '1', '2013-03-27 21:48:39', '0', '1', null);
INSERT INTO `bas_resource` VALUES ('25', '4', 'module_menu_category', '报表设计', '报表设计', '报表设计', '', '1', '1', '1', '_blank', 'icon-bar-chart', '1', '2013-04-14 11:20:30', '1', '2013-04-14 11:43:15', '0', '2', null);
INSERT INTO `bas_resource` VALUES ('26', '2', 'module_menu_category_item', '/system/application/index.htm', '系统信息', 'SystemInfo', '系统信息', '1', null, '1', '_blank', 'icon-star', '1', '2013-04-14 20:53:24', '1', '2013-04-14 20:55:42', '0', '1', null);
INSERT INTO `bas_resource` VALUES ('27', '5', 'module_menu_category_item', '菜单1', '菜单1', '菜单1', '菜单1', '0', '0', '1', '_self', 'icon-star', '2', '2014-04-06 13:30:32', '2', '2014-04-06 13:30:32', '0', '0', null);
INSERT INTO `bas_resource` VALUES ('32', '7', 'module_menu_category', '网页菜单1', '网页菜单1', 'WEB_MENU1', '网页菜单1', '1', '0', '1', '_blank', 'icon-star', '1', '2014-04-08 14:48:09', '1', '2014-04-08 14:48:09', '0', '0', null);
INSERT INTO `bas_resource` VALUES ('33', '32', 'module_menu_category_item', '菜单子项1', '菜单子项1', '菜单子项1', '菜单子项1', '1', '1', '1', '_self', 'icon-star', '1', '2014-04-08 14:48:41', '1', '2014-04-08 14:55:34', '0', '4', null);
INSERT INTO `bas_resource` VALUES ('36', '2', 'module_menu_category', 'SYSTEM_MONITOR', '系统监控', 'SYSTEM_MONITOR', '', '5', '0', '1', '_parent', 'icon-star', '1', '2014-04-08 15:03:34', '1', '2014-04-08 15:03:34', '0', '0', null);
INSERT INTO `bas_resource` VALUES ('37', '36', 'module_menu_category_item', 'SESSION_MONITOR', '用户会话监控', 'SESSION_MONITOR', '用户会话监控', '1', '0', '1', '_blank', 'icon-star', '1', '2014-04-08 15:06:21', '1', '2014-04-08 15:06:21', '0', '0', null);
INSERT INTO `bas_resource` VALUES ('38', '36', 'module_menu_category_item', '/system/cache/index.htm', '应用缓存监控', 'APP_CACHE_MINITOR', '', '2', '0', '1', '_blank', 'icon-star', '1', '2014-04-08 15:08:03', '1', '2014-04-21 11:37:57', '0', '1', null);
INSERT INTO `bas_resource` VALUES ('39', '36', 'module_menu_category_item', 'SYS_OS_MINITOR', '操作系统资源监控', 'SYS_OS_MINITOR', '操作系统资源监控', '3', '0', '1', '_blank', 'icon-star', '1', '2014-04-08 15:09:17', '1', '2014-04-08 15:09:17', '0', '0', null);
INSERT INTO `bas_resource` VALUES ('40', '1', 'mobile_mdule_menu_item', 'mobile_board', '财务看板', 'mobile_board', '财务看板模块', '1', '0', '1', '_blank', 'icon-wallet', '1', '2014-04-08 21:19:04', '1', '2014-04-08 21:19:16', '0', '1', null);
INSERT INTO `bas_resource` VALUES ('41', '40', 'mobile_mdule_menu', 'app_store', '应用大厅', 'app_store', '手机财务应用大厅', '0', '0', '1', '_blank', 'icon-star', '1', '2014-04-08 21:21:26', '1', '2014-04-08 21:21:26', '0', '0', null);

-- ----------------------------
-- Table structure for `bas_role`
-- ----------------------------
DROP TABLE IF EXISTS `bas_role`;
CREATE TABLE `bas_role` (
  `BAS_ROLE_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `NAME_ZH` varchar(128) NOT NULL COMMENT '角色中文名称',
  `NAME_EN` varchar(128) NOT NULL COMMENT '角色英文名称',
  `DESCRIPTION` varchar(256) DEFAULT NULL COMMENT '角色描述',
  `EDITABLE` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否可编辑,可为空',
  `CREATE_BY` int(11) NOT NULL COMMENT '系统默认字段.创建人ID',
  `CREATE_DATE` datetime NOT NULL COMMENT '系统默认字段.创建日期',
  `UPDATE_BY` int(11) NOT NULL COMMENT '系统默认字段.更新人ID',
  `UPDATE_DATE` datetime NOT NULL COMMENT '系统默认字段.更新日期',
  `MARK_FOR_DELETE` int(1) DEFAULT '0' COMMENT '系统默认字段.逻辑删除标记  0 未删除   1 表示删除',
  `OPT_COUNTER` int(6) DEFAULT '0' COMMENT '系统默认字段.版本记录',
  `DELETE_DATE` datetime DEFAULT NULL COMMENT '系统默认字段.删除日期',
  PRIMARY KEY (`BAS_ROLE_ID`),
  UNIQUE KEY `UK_BAS_ROLE_NAME_ZH` (`NAME_ZH`),
  UNIQUE KEY `AK_UK_BAS_ROLE_NAME_EN` (`NAME_EN`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='系统角色表';

-- ----------------------------
-- Records of bas_role
-- ----------------------------
INSERT INTO `bas_role` VALUES ('1', '系统管理角色', 'SYSADMIN_ROLE', '系统管理角色', '1', '1', '2013-03-24 11:34:17', '1', '2014-04-08 17:31:25', '0', '43', null);
INSERT INTO `bas_role` VALUES ('2', '销售总经理', 'SALES_MGR', '销售总经理', '1', '1', '2013-03-24 11:34:58', '1', '2014-05-21 17:29:27', '0', '21', null);
INSERT INTO `bas_role` VALUES ('4', '邮件角色', 'EMAIL_ROLES', '邮件角色', '1', '1', '2013-04-10 20:26:40', '1', '2014-05-22 10:39:26', '0', '9', null);
INSERT INTO `bas_role` VALUES ('6', '报表管理角色', 'ReportRoles', '报表管理角色', '1', '1', '2013-04-10 20:28:57', '1', '2014-05-22 14:24:11', '0', '11', null);
INSERT INTO `bas_role` VALUES ('8', 'SUPER_ADMIN', 'SUPER_ADMIN1', 'SUPER_ADMIN', '0', '1', '2014-03-28 22:35:19', '1', '2014-03-31 17:47:52', '0', '9', null);
INSERT INTO `bas_role` VALUES ('10', 'demo', 'demo', 'demo', '1', '1', '2014-05-22 17:51:18', '1', '2014-05-22 17:51:36', '0', '1', null);

-- ----------------------------
-- Table structure for `bas_role_resource`
-- ----------------------------
DROP TABLE IF EXISTS `bas_role_resource`;
CREATE TABLE `bas_role_resource` (
  `BAS_RESOURCE_ID` int(11) NOT NULL COMMENT '资源ID',
  `BAS_ROLE_ID` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`BAS_RESOURCE_ID`,`BAS_ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与资源关系表';

-- ----------------------------
-- Records of bas_role_resource
-- ----------------------------
INSERT INTO `bas_role_resource` VALUES ('1', '1');
INSERT INTO `bas_role_resource` VALUES ('2', '1');
INSERT INTO `bas_role_resource` VALUES ('4', '1');
INSERT INTO `bas_role_resource` VALUES ('4', '2');
INSERT INTO `bas_role_resource` VALUES ('4', '4');
INSERT INTO `bas_role_resource` VALUES ('5', '1');
INSERT INTO `bas_role_resource` VALUES ('5', '2');
INSERT INTO `bas_role_resource` VALUES ('6', '1');
INSERT INTO `bas_role_resource` VALUES ('7', '1');
INSERT INTO `bas_role_resource` VALUES ('7', '2');
INSERT INTO `bas_role_resource` VALUES ('8', '1');
INSERT INTO `bas_role_resource` VALUES ('8', '2');
INSERT INTO `bas_role_resource` VALUES ('9', '1');
INSERT INTO `bas_role_resource` VALUES ('9', '4');
INSERT INTO `bas_role_resource` VALUES ('10', '1');
INSERT INTO `bas_role_resource` VALUES ('11', '1');
INSERT INTO `bas_role_resource` VALUES ('11', '4');
INSERT INTO `bas_role_resource` VALUES ('11', '10');
INSERT INTO `bas_role_resource` VALUES ('12', '1');
INSERT INTO `bas_role_resource` VALUES ('12', '2');
INSERT INTO `bas_role_resource` VALUES ('12', '4');
INSERT INTO `bas_role_resource` VALUES ('13', '1');
INSERT INTO `bas_role_resource` VALUES ('14', '1');
INSERT INTO `bas_role_resource` VALUES ('14', '2');
INSERT INTO `bas_role_resource` VALUES ('14', '4');
INSERT INTO `bas_role_resource` VALUES ('15', '1');
INSERT INTO `bas_role_resource` VALUES ('15', '4');
INSERT INTO `bas_role_resource` VALUES ('15', '10');
INSERT INTO `bas_role_resource` VALUES ('16', '1');
INSERT INTO `bas_role_resource` VALUES ('17', '1');
INSERT INTO `bas_role_resource` VALUES ('17', '2');
INSERT INTO `bas_role_resource` VALUES ('17', '4');
INSERT INTO `bas_role_resource` VALUES ('18', '1');
INSERT INTO `bas_role_resource` VALUES ('18', '2');
INSERT INTO `bas_role_resource` VALUES ('18', '4');
INSERT INTO `bas_role_resource` VALUES ('18', '6');
INSERT INTO `bas_role_resource` VALUES ('19', '1');
INSERT INTO `bas_role_resource` VALUES ('19', '4');
INSERT INTO `bas_role_resource` VALUES ('20', '1');
INSERT INTO `bas_role_resource` VALUES ('21', '1');
INSERT INTO `bas_role_resource` VALUES ('24', '1');
INSERT INTO `bas_role_resource` VALUES ('24', '2');
INSERT INTO `bas_role_resource` VALUES ('24', '4');
INSERT INTO `bas_role_resource` VALUES ('24', '6');
INSERT INTO `bas_role_resource` VALUES ('25', '1');
INSERT INTO `bas_role_resource` VALUES ('25', '2');
INSERT INTO `bas_role_resource` VALUES ('25', '4');
INSERT INTO `bas_role_resource` VALUES ('26', '1');
INSERT INTO `bas_role_resource` VALUES ('26', '4');
INSERT INTO `bas_role_resource` VALUES ('26', '10');
INSERT INTO `bas_role_resource` VALUES ('27', '1');
INSERT INTO `bas_role_resource` VALUES ('27', '2');
INSERT INTO `bas_role_resource` VALUES ('32', '1');
INSERT INTO `bas_role_resource` VALUES ('33', '1');
INSERT INTO `bas_role_resource` VALUES ('36', '1');
INSERT INTO `bas_role_resource` VALUES ('36', '4');
INSERT INTO `bas_role_resource` VALUES ('37', '1');
INSERT INTO `bas_role_resource` VALUES ('37', '4');
INSERT INTO `bas_role_resource` VALUES ('38', '1');
INSERT INTO `bas_role_resource` VALUES ('38', '4');
INSERT INTO `bas_role_resource` VALUES ('38', '6');
INSERT INTO `bas_role_resource` VALUES ('38', '10');
INSERT INTO `bas_role_resource` VALUES ('39', '1');
INSERT INTO `bas_role_resource` VALUES ('39', '4');

-- ----------------------------
-- Table structure for `bas_tiptext`
-- ----------------------------
DROP TABLE IF EXISTS `bas_tiptext`;
CREATE TABLE `bas_tiptext` (
  `BAS_TIPTEXT_ID` int(11) NOT NULL COMMENT 'ID',
  `TIP_TEXT_KEY` varchar(50) DEFAULT NULL COMMENT '提示信息关键字',
  `TEXT_ZH` varchar(400) DEFAULT NULL COMMENT '中文名称',
  `TEXT_EN` varchar(400) DEFAULT NULL COMMENT '英文名称',
  `CREATE_BY` int(11) NOT NULL COMMENT '系统默认字段.创建人ID',
  `CREATE_DATE` datetime NOT NULL COMMENT '系统默认字段.创建日期',
  `UPDATE_BY` int(11) NOT NULL COMMENT '系统默认字段.更新人ID',
  `UPDATE_DATE` datetime NOT NULL COMMENT '系统默认字段.更新日期',
  `MARK_FOR_DELETE` int(1) DEFAULT '0' COMMENT '系统默认字段.逻辑删除标记  0 未删除   1 表示删除',
  `OPT_COUNTER` int(6) DEFAULT '0' COMMENT '系统默认字段.版本记录',
  `DELETE_DATE` datetime DEFAULT NULL COMMENT '系统默认字段.删除日期',
  UNIQUE KEY `UK_BAS_TIPTEXT_KEY` (`TIP_TEXT_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bas_tiptext
-- ----------------------------

-- ----------------------------
-- Table structure for `bas_user`
-- ----------------------------
DROP TABLE IF EXISTS `bas_user`;
CREATE TABLE `bas_user` (
  `BAS_USER_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `BAS_DEPARTMENT_ID` int(11) DEFAULT NULL COMMENT '部门ID',
  `BAS_POSITION_ID` int(11) DEFAULT NULL,
  `MANAGER_USER_ID` int(11) DEFAULT NULL COMMENT '经理USERID',
  `USERNAME` varchar(30) NOT NULL COMMENT '用户登录名',
  `PASSWORD` varchar(128) NOT NULL COMMENT '密码，存储采用SHA算法加密',
  `PASSWORD_HINT` varchar(30) DEFAULT NULL COMMENT '密码提示语',
  `EMPLOYEE_NO` varchar(30) NOT NULL COMMENT '员工号',
  `REALNAME_ZH` varchar(128) DEFAULT NULL COMMENT '用户中文名称',
  `REALNAME_EN` varchar(128) DEFAULT NULL COMMENT '用户英语名称',
  `GENDER` int(1) NOT NULL COMMENT '性别 0 男 1 女',
  `USERTYPE` varchar(10) DEFAULT NULL COMMENT '用户类型EMPLOYEE, SUPPLIER, DEALER可自行扩展',
  `AUTHTYPE` varchar(10) DEFAULT NULL COMMENT '认证类型 LDAP, DB, DUMMY',
  `TELEPHONE` varchar(30) DEFAULT NULL COMMENT '办公室电话',
  `MOBILE` varchar(30) DEFAULT NULL COMMENT '手机号码',
  `EMAIL` varchar(128) DEFAULT NULL COMMENT '邮件地址',
  `OFFICE_TELEPHONE` varchar(30) NOT NULL,
  `FAX` varchar(30) DEFAULT NULL,
  `POSTCODE` varchar(6) DEFAULT NULL COMMENT '邮编',
  `LOCATION` varchar(128) DEFAULT NULL COMMENT '办公地址',
  `ACCOUNT_EXPIRED` tinyint(1) NOT NULL DEFAULT '1' COMMENT '账户是否有效',
  `ACCOUNT_LOCKED` tinyint(1) NOT NULL DEFAULT '1' COMMENT '账户是否被锁定',
  `CREDENTIAL_EXPIRED` tinyint(1) NOT NULL DEFAULT '1' COMMENT '密码是否有效',
  `CREDENTIAL_EXPIRED_DATE` datetime NOT NULL COMMENT '密码有效日期',
  `ACCOUNT_EXPIRED_DATE` datetime NOT NULL COMMENT '账户有效日期',
  `EDITABLE` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否可编辑',
  `DESCRIPTION` varchar(256) DEFAULT NULL COMMENT '用户描述',
  `CREATE_BY` int(11) NOT NULL COMMENT '创建人ID',
  `CREATE_DATE` datetime NOT NULL COMMENT '创建日期',
  `UPDATE_BY` int(11) NOT NULL COMMENT '更新人ID',
  `UPDATE_DATE` datetime NOT NULL COMMENT '更新日期',
  `OPT_COUNTER` int(6) DEFAULT '0' COMMENT '记录操作次',
  `DELETE_DATE` datetime DEFAULT NULL COMMENT '删除日期',
  `MARK_FOR_DELETE` int(1) DEFAULT '0' COMMENT '是否标记删除 0 未删除   1 表示删除',
  PRIMARY KEY (`BAS_USER_ID`,`ACCOUNT_LOCKED`),
  UNIQUE KEY `UK_BAS_USER_USERNAME` (`USERNAME`),
  KEY `FK_BAS_USER_DEPARTEMNT_ID_001` (`BAS_DEPARTMENT_ID`),
  KEY `FK_BAS_USER_MANAGER_USER_ID` (`MANAGER_USER_ID`),
  CONSTRAINT `FK_BAS_USER_DEPARTEMNT_ID_001` FOREIGN KEY (`BAS_DEPARTMENT_ID`) REFERENCES `bas_department` (`BAS_DEPARTMENT_ID`),
  CONSTRAINT `FK_BAS_USER_MANAGER_USER_ID` FOREIGN KEY (`MANAGER_USER_ID`) REFERENCES `bas_user` (`BAS_USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='系统用户信息表';

-- ----------------------------
-- Records of bas_user
-- ----------------------------
INSERT INTO `bas_user` VALUES ('1', '18', null, null, 'wanglijun', 'c150916baa97eeccd1d99541aad26170761b41a9', null, '0000001', '王立君', 'wanglijun', '0', null, null, '021-234298379', '15821501965', '', '021-234298379', '021-234298379', '293482', '', '0', '0', '0', '2015-04-03 21:18:25', '2015-04-03 21:18:25', '1', null, '1', '2013-03-23 21:08:49', '1', '2014-04-08 17:29:03', '84', null, '0');
INSERT INTO `bas_user` VALUES ('2', '18', null, null, 'duxusc', 'c150916baa97eeccd1d99541aad26170761b41a9', null, '0000002', '杜旭', 'duxusc', '0', null, null, '', '', '', '', '', '', '', '0', '0', '0', '2015-04-03 21:18:25', '2015-04-03 21:18:25', '1', null, '1', '2013-03-23 21:16:44', '1', '2014-04-06 00:31:43', '13', null, '0');
INSERT INTO `bas_user` VALUES ('3', '18', null, null, 'even', 'c150916baa97eeccd1d99541aad26170761b41a9', null, '0000001', 'zhjwww', 'zhjwww', '0', null, null, '', '', '', '', '', '', '', '0', '0', '0', '2015-04-03 21:18:25', '2015-04-03 21:18:25', '1', null, '1', '2013-03-24 14:18:47', '1', '2014-04-08 22:27:56', '2', null, '0');
INSERT INTO `bas_user` VALUES ('4', '18', null, null, '000000009', 'c150916baa97eeccd1d99541aad26170761b41a9', null, '000000009', '000000009', '000000009', '0', null, null, '', '', '', '', '', '', '', '0', '0', '1', '2014-07-03 15:34:36', '2015-04-03 15:34:36', '1', null, '1', '2014-04-03 15:34:36', '1', '2014-04-06 00:42:08', '9', null, '0');
INSERT INTO `bas_user` VALUES ('5', '18', null, null, '00000008', 'c150916baa97eeccd1d99541aad26170761b41a9', null, '00000008', '00000008', '00000008', '0', null, null, '', '', '', '', '', '', '', '1', '0', '1', '2014-07-03 21:18:25', '2015-04-03 21:18:25', '1', null, '1', '2014-04-03 21:18:25', '1', '2014-04-06 00:38:41', '1', null, '0');
INSERT INTO `bas_user` VALUES ('6', '9', null, null, '000000007', 'c150916baa97eeccd1d99541aad26170761b41a9', null, '000000007', '000000007', '000000007', '1', null, null, '', '', '', '', '', '', '', '0', '0', '0', '2014-07-07 18:19:43', '2015-04-07 18:19:43', '1', null, '1', '2014-04-07 18:19:43', '6', '2014-04-07 18:25:23', '7', null, '0');
INSERT INTO `bas_user` VALUES ('7', '8', null, null, 'admin', 'c150916baa97eeccd1d99541aad26170761b41a9', null, '00000001', '超级系统管理员', 'sysadmin', '1', null, null, '', '', '', '', '', '', '', '0', '0', '0', '2014-07-08 21:03:36', '2015-04-08 21:03:36', '1', null, '1', '2014-04-08 21:03:36', '7', '2014-05-22 10:44:27', '10', null, '0');
INSERT INTO `bas_user` VALUES ('8', '20', null, null, 'demo', 'c150916baa97eeccd1d99541aad26170761b41a9', null, 'demo1', 'demo', 'demo', '1', null, null, '', '', '', '', '', '', '', '0', '0', '0', '2014-08-22 11:05:28', '2015-05-22 11:05:28', '1', null, '1', '2014-05-22 11:05:28', '1', '2014-05-22 17:51:47', '2', null, '0');

-- ----------------------------
-- Table structure for `bas_user_group`
-- ----------------------------
DROP TABLE IF EXISTS `bas_user_group`;
CREATE TABLE `bas_user_group` (
  `BAS_GROUP_ID` int(11) NOT NULL COMMENT '用户组ID',
  `BAS_USER_ID` int(11) NOT NULL COMMENT '用户ID',
  KEY `FK_BAS_USER_ROLE_GROUP_GROUPID_001` (`BAS_GROUP_ID`),
  KEY `FK_BAS_USER_ROLE_GROUP_USERID_001` (`BAS_USER_ID`),
  CONSTRAINT `FK_BAS_USER_ROLE_GROUP_GROUPID_001` FOREIGN KEY (`BAS_GROUP_ID`) REFERENCES `bas_group` (`BAS_GROUP_ID`),
  CONSTRAINT `FK_BAS_USER_ROLE_GROUP_USERID_001` FOREIGN KEY (`BAS_USER_ID`) REFERENCES `bas_user` (`BAS_USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户与用户组关系表';

-- ----------------------------
-- Records of bas_user_group
-- ----------------------------
INSERT INTO `bas_user_group` VALUES ('1', '1');
INSERT INTO `bas_user_group` VALUES ('5', '1');
INSERT INTO `bas_user_group` VALUES ('6', '1');
INSERT INTO `bas_user_group` VALUES ('1', '3');
INSERT INTO `bas_user_group` VALUES ('4', '1');
INSERT INTO `bas_user_group` VALUES ('4', '2');
INSERT INTO `bas_user_group` VALUES ('2', '3');
INSERT INTO `bas_user_group` VALUES ('3', '1');
INSERT INTO `bas_user_group` VALUES ('3', '2');
INSERT INTO `bas_user_group` VALUES ('1', '2');
INSERT INTO `bas_user_group` VALUES ('1', '4');
INSERT INTO `bas_user_group` VALUES ('2', '2');
INSERT INTO `bas_user_group` VALUES ('5', '2');
INSERT INTO `bas_user_group` VALUES ('6', '2');
INSERT INTO `bas_user_group` VALUES ('1', '6');
INSERT INTO `bas_user_group` VALUES ('2', '6');
INSERT INTO `bas_user_group` VALUES ('2', '1');
INSERT INTO `bas_user_group` VALUES ('1', '7');

-- ----------------------------
-- Table structure for `bas_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `bas_user_role`;
CREATE TABLE `bas_user_role` (
  `BAS_USER_ID` int(11) NOT NULL,
  `BAS_ROLE_ID` int(11) NOT NULL,
  KEY `IDX_BAS_USER_ROLE_URID` (`BAS_USER_ID`,`BAS_ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bas_user_role
-- ----------------------------
INSERT INTO `bas_user_role` VALUES ('1', '1');
INSERT INTO `bas_user_role` VALUES ('1', '2');
INSERT INTO `bas_user_role` VALUES ('1', '4');
INSERT INTO `bas_user_role` VALUES ('2', '1');
INSERT INTO `bas_user_role` VALUES ('2', '2');
INSERT INTO `bas_user_role` VALUES ('2', '4');
INSERT INTO `bas_user_role` VALUES ('2', '8');
INSERT INTO `bas_user_role` VALUES ('2', '9');
INSERT INTO `bas_user_role` VALUES ('3', '1');
INSERT INTO `bas_user_role` VALUES ('4', '1');
INSERT INTO `bas_user_role` VALUES ('6', '2');
INSERT INTO `bas_user_role` VALUES ('8', '10');
