SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for uasp_app_notice
-- ----------------------------
CREATE TABLE `uasp_app_notice`
(
  `notice_id_`    varchar(36)   NOT NULL COMMENT '公告ID',
  `app_id_`       varchar(36)   NOT NULL COMMENT '系统ID',
  `tenant_id_`    varchar(36)   NULL DEFAULT NULL COMMENT '公司ID',
  `title_`        varchar(128)  NOT NULL COMMENT '标题',
  `content_`      varchar(1024) NOT NULL COMMENT '内容',
  `release_time_` datetime      NULL DEFAULT NULL COMMENT '发布时间',
  `creator_id_`   varchar(36)   NOT NULL COMMENT '创建人ID',
  `create_time_`  datetime      NOT NULL COMMENT '创建时间',
  `updater_id_`   varchar(36)   NULL DEFAULT NULL COMMENT '修改人ID',
  `update_time_`  datetime      NULL DEFAULT NULL COMMENT '修改时间',
  `status_`       varchar(16)   NOT NULL COMMENT '状态:D草搞,V可用,I禁用;',
  PRIMARY KEY (`notice_id_`) USING BTREE
) COMMENT = '系统公告';

-- ----------------------------
-- Table structure for uasp_app_store
-- ----------------------------
CREATE TABLE `uasp_app_store`
(
  `release_id_`   varchar(36)    NOT NULL COMMENT '发布ID',
  `app_id_`       varchar(36)    NOT NULL COMMENT '应用ID',
  `version_`      decimal(18, 2) NOT NULL COMMENT '版本号',
  `forced_`       bit(1)         NOT NULL COMMENT '强制更新',
  `mode_`         varchar(16)    NOT NULL COMMENT '更新方式;A增量;F整包;',
  `title_`        varchar(64)    NOT NULL COMMENT '标题',
  `description_`  varchar(512)   NULL DEFAULT NULL COMMENT '描述',
  `resource_id_`  varchar(36)    NULL DEFAULT NULL COMMENT '包文件ID',
  `released_`     bit(1)         NOT NULL COMMENT '已发布',
  `release_time_` datetime       NULL DEFAULT NULL COMMENT '发布时间',
  `creator_id_`   varchar(36)    NOT NULL COMMENT '创建人ID',
  `create_time_`  datetime       NOT NULL COMMENT '创建时间',
  `updater_id_`   varchar(36)    NULL DEFAULT NULL COMMENT '修改人ID',
  `update_time_`  datetime       NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`release_id_`) USING BTREE,
  INDEX `fk_uasp_applib_applic` (`app_id_` ASC) USING BTREE,
  CONSTRAINT `fk_uasp_applib_applic` FOREIGN KEY (`app_id_`) REFERENCES `uasp_applic` (`app_id_`) ON DELETE CASCADE ON UPDATE RESTRICT
) COMMENT = '应用库';

-- ----------------------------
-- Table structure for uasp_applic
-- ----------------------------
CREATE TABLE `uasp_applic`
(
  `app_id_`       varchar(36)  NOT NULL COMMENT '应用ID',
  `code_`         varchar(32)  NOT NULL COMMENT '系统代码:UASP、其它;',
  `name_`         varchar(64)  NOT NULL COMMENT '系统名称',
  `level_`        int          NULL DEFAULT NULL COMMENT '系统级别',
  `type_`         varchar(16)  NULL DEFAULT NULL COMMENT '系统类型:PC,WX,APP',
  `url_`          varchar(512) NULL DEFAULT NULL COMMENT '入口URL',
  `sort_`         int          NULL DEFAULT NULL COMMENT '排序码',
  `need_release_` varchar(16)  NULL DEFAULT NULL COMMENT '需要发布下载',
  `creator_id_`   varchar(36)  NOT NULL COMMENT '创建人ID',
  `create_time_`  datetime     NOT NULL COMMENT '创建时间',
  `updater_id_`   varchar(36)  NULL DEFAULT NULL COMMENT '修改人ID',
  `update_time_`  datetime     NULL DEFAULT NULL COMMENT '修改时间',
  `status_`       varchar(16)  NOT NULL COMMENT '状态:D草搞,V可用,I禁用;',
  `remark_`       varchar(512) NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`app_id_`) USING BTREE
) COMMENT = '应用系统';

-- ----------------------------
-- Table structure for uasp_auto_no_config
-- ----------------------------
CREATE TABLE `uasp_auto_no_config`
(
  `config_id_`    varchar(36)   NOT NULL COMMENT '编号详情ID',
  `define_id_`    varchar(36)   NULL DEFAULT NULL COMMENT '自动编号ID',
  `tenant_id_`    varchar(36)   NULL DEFAULT NULL COMMENT '租户ID',
  `expression_`   varchar(128)  NOT NULL COMMENT '表达式:{类型}-{年}-{月}-{日}-{计数}',
  `inc_value_`    int           NOT NULL COMMENT '增量值',
  `count_length_` int           NOT NULL COMMENT '计数长度',
  `custom_cycle_` varchar(128)  NOT NULL COMMENT '定义周期',
  `creator_id_`   varchar(36)   NOT NULL COMMENT '创建人ID',
  `create_time_`  datetime      NOT NULL COMMENT '创建时间',
  `updater_id_`   varchar(36)   NULL DEFAULT NULL COMMENT '修改人ID',
  `update_time_`  datetime      NULL DEFAULT NULL COMMENT '修改时间',
  `remark_`       varchar(1024) NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`config_id_`) USING BTREE,
  INDEX `fk_uasp_number_config_define` (`define_id_` ASC) USING BTREE,
  CONSTRAINT `fk_uasp_number_config_define` FOREIGN KEY (`define_id_`) REFERENCES `uasp_auto_no_define` (`define_id_`) ON DELETE CASCADE ON UPDATE CASCADE
) COMMENT = '自动编号配置';

-- ----------------------------
-- Table structure for uasp_auto_no_count
-- ----------------------------
CREATE TABLE `uasp_auto_no_count`
(
  `count_id_`    varchar(36)  NOT NULL COMMENT '计数周期',
  `define_id_`   varchar(36)  NULL DEFAULT NULL COMMENT '自动编号ID',
  `tenant_id_`   varchar(36)  NULL DEFAULT NULL COMMENT '租户ID',
  `cycle_value_` varchar(128) NOT NULL COMMENT '周期',
  `count_value_` int          NOT NULL COMMENT '计数',
  PRIMARY KEY (`count_id_`) USING BTREE,
  INDEX `fk_uasp_count_define` (`define_id_` ASC) USING BTREE,
  CONSTRAINT `fk_uasp_count_define` FOREIGN KEY (`define_id_`) REFERENCES `uasp_auto_no_define` (`define_id_`) ON DELETE CASCADE ON UPDATE RESTRICT
) COMMENT = '计数周期';

-- ----------------------------
-- Table structure for uasp_auto_no_define
-- ----------------------------
CREATE TABLE `uasp_auto_no_define`
(
  `define_id_` varchar(36)  NOT NULL COMMENT '自动编号ID',
  `app_id_`    varchar(36)  NULL DEFAULT NULL COMMENT '应用ID',
  `code_`      varchar(32)  NOT NULL COMMENT '编号',
  `name_`      varchar(128) NOT NULL COMMENT '显示名',
  PRIMARY KEY (`define_id_`) USING BTREE,
  INDEX `fk_uasp_number_applic` (`app_id_` ASC) USING BTREE,
  CONSTRAINT `fk_uasp_number_applic` FOREIGN KEY (`app_id_`) REFERENCES `uasp_applic` (`app_id_`) ON DELETE CASCADE ON UPDATE RESTRICT
) COMMENT = '自动编号定义';

-- ----------------------------
-- Table structure for uasp_auto_no_recycle
-- ----------------------------
CREATE TABLE `uasp_auto_no_recycle`
(
  `count_id_`   varchar(36)  NOT NULL COMMENT '计数周期',
  `recycle_no_` varchar(256) NOT NULL COMMENT '回收编号',
  PRIMARY KEY (`count_id_`, `recycle_no_`) USING BTREE,
  CONSTRAINT `fk_uasp_number_recycle_count` FOREIGN KEY (`count_id_`) REFERENCES `uasp_auto_no_count` (`count_id_`) ON DELETE CASCADE ON UPDATE RESTRICT
) COMMENT = '编号回收站';

-- ----------------------------
-- Table structure for uasp_company
-- ----------------------------
CREATE TABLE `uasp_company`
(
  `company_id_`  varchar(36)  NOT NULL COMMENT '公司ID',
  `parent_id_`   varchar(36)  NULL DEFAULT NULL COMMENT '上级ID',
  `tenant_id_`   varchar(36)  NULL DEFAULT NULL COMMENT '所属租户ID',
  `type_`        varchar(16)  NULL DEFAULT NULL COMMENT '类型:TENANT租用公司,CHILD子公司;CONTRACT往来公司;',
  `category_id_` varchar(36)  NULL DEFAULT NULL COMMENT '分类ID',
  `code_`        varchar(16)  NOT NULL COMMENT '编号',
  `short_name_`  varchar(32)  NOT NULL COMMENT '公司简称',
  `full_name_`   varchar(128) NOT NULL COMMENT '公司全称',
  `post_code_`   varchar(16)  NULL DEFAULT NULL COMMENT '邮编',
  `contract_`    varchar(16)  NULL DEFAULT NULL COMMENT '联系人',
  `address_`     varchar(256) NULL DEFAULT NULL COMMENT '办公地址',
  `tel_`         varchar(128) NULL DEFAULT NULL COMMENT '办公电话',
  `mobile_`      varchar(128) NULL DEFAULT NULL COMMENT '联系电话',
  `fax_`         varchar(128) NULL DEFAULT NULL COMMENT '传真号',
  `email_`       varchar(256) NULL DEFAULT NULL COMMENT '邮编',
  `sort_`        int          NULL DEFAULT NULL COMMENT '排序码',
  `location_`    varchar(256) NULL DEFAULT NULL COMMENT '定位',
  `creator_id_`  varchar(36)  NOT NULL COMMENT '创建人ID',
  `create_time_` datetime     NOT NULL COMMENT '创建时间',
  `updater_id_`  varchar(36)  NULL DEFAULT NULL COMMENT '修改人ID',
  `update_time_` datetime     NULL DEFAULT NULL COMMENT '修改时间',
  `status_`      varchar(16)  NOT NULL COMMENT '状态:D草搞,V可用,I禁用;',
  `remark_`      varchar(512) NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`company_id_`) USING BTREE
) COMMENT = '公司';

-- ----------------------------
-- Table structure for uasp_company_applic
-- ----------------------------
CREATE TABLE `uasp_company_applic`
(
  `id_`          varchar(36) NOT NULL COMMENT '应用租户ID',
  `company_id_`  varchar(36) NULL DEFAULT NULL COMMENT '往来公司ID',
  `app_id_`      varchar(36) NOT NULL COMMENT '二级应用ID',
  `expire_time_` datetime    NULL DEFAULT NULL COMMENT '到期时间',
  `creator_id_`  varchar(36) NOT NULL COMMENT '开通人ID',
  `create_time_` datetime    NOT NULL COMMENT '开通时间',
  `status_`      varchar(16) NOT NULL COMMENT '状态:V可用,I禁用;',
  PRIMARY KEY (`id_`) USING BTREE,
  INDEX `fk_uasp_applic_company` (`company_id_` ASC) USING BTREE,
  INDEX `fk_uasp_company_applic` (`app_id_` ASC) USING BTREE,
  CONSTRAINT `fk_uasp_applic_company` FOREIGN KEY (`company_id_`) REFERENCES `uasp_company` (`company_id_`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `fk_uasp_company_applic` FOREIGN KEY (`app_id_`) REFERENCES `uasp_applic` (`app_id_`) ON DELETE CASCADE ON UPDATE RESTRICT
) COMMENT = '开通应用';

-- ----------------------------
-- Table structure for uasp_company_leader
-- ----------------------------
CREATE TABLE `uasp_company_leader`
(
  `company_id_` varchar(36) NOT NULL COMMENT '公司ID',
  `leader_id_`  varchar(36) NOT NULL COMMENT '领导ID',
  `duty_`       varchar(16) NOT NULL COMMENT '职责:DSZ董事长;DS董事;ZZJL正职总经理;FZJL副职总经理;LDBZ领导班子;',
  PRIMARY KEY (`leader_id_`, `company_id_`) USING BTREE,
  INDEX `fk_uasp_comleader_company` (`company_id_` ASC) USING BTREE,
  CONSTRAINT `fk_uasp_comleader_company` FOREIGN KEY (`company_id_`) REFERENCES `uasp_company` (`company_id_`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `fk_uasp_comleader_employee` FOREIGN KEY (`leader_id_`) REFERENCES `uasp_employee` (`employee_id_`) ON DELETE CASCADE ON UPDATE RESTRICT
) COMMENT = '公司领导';

-- ----------------------------
-- Table structure for uasp_config_item
-- ----------------------------
CREATE TABLE `uasp_config_item`
(
  `item_id_` varchar(36)  NOT NULL COMMENT '配置项ID',
  `node_id_` varchar(36)  NOT NULL COMMENT '节点ID',
  `key_`     varchar(128) NOT NULL COMMENT '键名',
  `type_`    varchar(16)  NOT NULL COMMENT '值类型:TEXT,NUMBER,DATE,OBJECT',
  `value_`   longtext     NOT NULL COMMENT '键值',
  PRIMARY KEY (`item_id_`) USING BTREE,
  INDEX `fk_uasp_config_item_node` (`node_id_` ASC) USING BTREE,
  CONSTRAINT `fk_uasp_config_item_node` FOREIGN KEY (`node_id_`) REFERENCES `uasp_config_node` (`node_id_`) ON DELETE CASCADE ON UPDATE RESTRICT
) COMMENT = '系统配置项';

-- ----------------------------
-- Table structure for uasp_config_node
-- ----------------------------
CREATE TABLE `uasp_config_node`
(
  `node_id_`   varchar(36)  NOT NULL COMMENT '节点ID',
  `name_`      varchar(128) NOT NULL COMMENT '节点名',
  `parent_`    varchar(36)  NULL DEFAULT NULL COMMENT '上级节点',
  `scope_`     varchar(16)  NOT NULL COMMENT '范围:APP应用级,TENANT租户级,USER用户级;',
  `app_id_`    varchar(36)  NULL DEFAULT NULL COMMENT '所属应用ID',
  `tenant_id_` varchar(36)  NULL DEFAULT NULL COMMENT '所属租户ID',
  `user_id_`   varchar(36)  NULL DEFAULT NULL COMMENT '所属用户ID',
  PRIMARY KEY (`node_id_`) USING BTREE
) COMMENT = '系统配置节';

-- ----------------------------
-- Table structure for uasp_data_change
-- ----------------------------
CREATE TABLE `uasp_data_change`
(
  `log_id_`        int           NOT NULL AUTO_INCREMENT COMMENT '日志ID',
  `app_code_`      varchar(32)   NULL DEFAULT NULL COMMENT '系统代码',
  `user_id_`       varchar(36)   NOT NULL COMMENT '账号名',
  `business_key_`  varchar(64)   NULL DEFAULT NULL COMMENT '业务数据KEY',
  `action_`        varchar(32)   NOT NULL COMMENT '动作类型:进行了什么操作,新增,修改,删除.',
  `summary_`       varchar(1024) NULL DEFAULT NULL COMMENT '日志摘要',
  `original_data_` longtext      NULL COMMENT '原字段数据',
  `new_data_`      longtext      NULL COMMENT '新字段数据',
  `create_time_`   datetime      NOT NULL COMMENT '记录时间',
  PRIMARY KEY (`log_id_`) USING BTREE
) AUTO_INCREMENT = 1
  COMMENT = '数据变动日志';

-- ----------------------------
-- Table structure for uasp_depart
-- ----------------------------
CREATE TABLE `uasp_depart`
(
  `depart_id_`   varchar(36)   NOT NULL COMMENT '部门ID',
  `parent_id_`   varchar(36)   NULL DEFAULT NULL COMMENT '上级ID',
  `company_id_`  varchar(36)   NULL DEFAULT NULL COMMENT '所属公司ID',
  `code_`        varchar(32)   NULL DEFAULT NULL COMMENT '编号',
  `name_`        varchar(128)  NOT NULL COMMENT '名称',
  `sort_`        smallint      NOT NULL COMMENT '排序码',
  `creator_id_`  varchar(36)   NOT NULL COMMENT '创建人ID',
  `create_time_` datetime      NOT NULL COMMENT '创建时间',
  `updater_id_`  varchar(36)   NULL DEFAULT NULL COMMENT '修改人ID',
  `update_time_` datetime      NULL DEFAULT NULL COMMENT '修改时间',
  `status_`      varchar(16)   NOT NULL COMMENT '状态:D草搞,V可用,I禁用;',
  `remark_`      varchar(2048) NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`depart_id_`) USING BTREE,
  INDEX `fk_uasp_depart_company` (`company_id_` ASC) USING BTREE,
  INDEX `idx_uasp_depart_parent` (`parent_id_` ASC) USING BTREE,
  CONSTRAINT `fk_uasp_depart_company` FOREIGN KEY (`company_id_`) REFERENCES `uasp_company` (`company_id_`) ON DELETE CASCADE ON UPDATE RESTRICT
) COMMENT = '部门';

-- ----------------------------
-- Table structure for uasp_depart_leader
-- ----------------------------
CREATE TABLE `uasp_depart_leader`
(
  `depart_id_`   varchar(36) NOT NULL COMMENT '部门ID',
  `leader_id_`   varchar(36) NOT NULL COMMENT '领导ID',
  `employee_id_` varchar(36) NULL DEFAULT NULL COMMENT '员工ID',
  `duty_`        char(1)     NOT NULL COMMENT '职责:Z正职;F副职;M直管;L分管',
  PRIMARY KEY (`leader_id_`, `depart_id_`) USING BTREE,
  INDEX `fk_uasp_depleader_depart` (`depart_id_` ASC) USING BTREE,
  INDEX `fk_uasp_depleader_employee` (`employee_id_` ASC) USING BTREE,
  CONSTRAINT `fk_uasp_depleader_depart` FOREIGN KEY (`depart_id_`) REFERENCES `uasp_depart` (`depart_id_`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `fk_uasp_depleader_employee` FOREIGN KEY (`employee_id_`) REFERENCES `uasp_employee` (`employee_id_`) ON DELETE CASCADE ON UPDATE RESTRICT
) COMMENT = '部门领导';

-- ----------------------------
-- Table structure for uasp_dict_define
-- ----------------------------
CREATE TABLE `uasp_dict_define`
(
  `define_id_`  varchar(36)  NOT NULL COMMENT '词典分类ID',
  `parent_id_`  varchar(36)  NULL DEFAULT NULL COMMENT '上级ID',
  `tree_path_`  varchar(512) NULL DEFAULT NULL COMMENT '树路径',
  `app_id_`     varchar(36)  NOT NULL COMMENT '所属应用ID',
  `code_`       varchar(32)  NULL DEFAULT NULL COMMENT '编号',
  `name_`       varchar(128) NOT NULL COMMENT '显示名',
  `scope_`      varchar(16)  NULL DEFAULT NULL COMMENT '应用范围:D开发;B业务;',
  `type_`       varchar(16)  NOT NULL COMMENT '定义类型：C分类,I字典;',
  `data_type_`  varchar(16)  NULL DEFAULT NULL COMMENT '数据类型:L列表;T树型;',
  `sort_`       int          NULL DEFAULT NULL COMMENT '排序码',
  `ext_fields_` longtext     NULL COMMENT '扩展字段',
  `remark_`     varchar(512) NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`define_id_`) USING BTREE
) COMMENT = '数据词典定义';

-- ----------------------------
-- Table structure for uasp_dict_item
-- ----------------------------
CREATE TABLE `uasp_dict_item`
(
  `item_id_`    varchar(36)  NOT NULL COMMENT '词典项ID',
  `define_id_`  varchar(36)  NOT NULL COMMENT '词典分类ID',
  `parent_id_`  varchar(36)  NULL DEFAULT NULL COMMENT '上级ID',
  `tenant_id_`  varchar(36)  NULL DEFAULT NULL COMMENT '租户ID',
  `code_`       varchar(32)  NULL DEFAULT NULL COMMENT '编号',
  `name_`       varchar(128) NOT NULL COMMENT '显示名',
  `sort_`       int          NULL DEFAULT NULL COMMENT '排序码',
  `status_`     varchar(16)  NOT NULL COMMENT '状态:V可用,I禁用;',
  `ext_field1_` varchar(512) NULL DEFAULT NULL COMMENT '扩展字段1',
  `ext_field2_` varchar(512) NULL DEFAULT NULL COMMENT '扩展字段2',
  `ext_field3_` varchar(512) NULL DEFAULT NULL COMMENT '扩展字段3',
  `ext_field4_` varchar(512) NULL DEFAULT NULL COMMENT '扩展字段4',
  `ext_field5_` varchar(512) NULL DEFAULT NULL COMMENT '扩展字段5',
  PRIMARY KEY (`item_id_`) USING BTREE,
  INDEX `fk_uasp_dict_item_define` (`define_id_` ASC) USING BTREE,
  CONSTRAINT `fk_uasp_dict_item_define` FOREIGN KEY (`define_id_`) REFERENCES `uasp_dict_define` (`define_id_`) ON DELETE CASCADE ON UPDATE CASCADE
) COMMENT = '数据词典项';

-- ----------------------------
-- Table structure for uasp_employee
-- ----------------------------
CREATE TABLE `uasp_employee`
(
  `employee_id_` varchar(36)    NOT NULL COMMENT '员工ID',
  `tenant_id_`   varchar(36)    NULL DEFAULT NULL COMMENT '租户ID',
  `company_id_`  varchar(36)    NULL DEFAULT NULL COMMENT '公司ID',
  `depart_id_`   varchar(36)    NULL DEFAULT NULL COMMENT '部门ID',
  `job_no_`      varchar(32)    NULL DEFAULT NULL COMMENT '工号',
  `real_name_`   varchar(32)    NULL DEFAULT NULL COMMENT '真实姓名',
  `birthday_`    datetime       NULL DEFAULT NULL COMMENT '出生日期',
  `origin_`      varchar(32)    NULL DEFAULT NULL COMMENT '民族',
  `nation_`      varchar(32)    NULL DEFAULT NULL COMMENT '籍贯',
  `sex_`         varchar(16)    NULL DEFAULT NULL COMMENT '性别:M男,W女,N未指明;',
  `education_`   varchar(32)    NULL DEFAULT NULL COMMENT '学历',
  `height_`      decimal(18, 2) NULL DEFAULT NULL COMMENT '身高',
  `weight_`      decimal(18, 2) NULL DEFAULT NULL COMMENT '体重',
  `marital_`     varchar(32)    NULL DEFAULT NULL COMMENT '婚姻状况',
  `id_card_`     varchar(64)    NULL DEFAULT NULL COMMENT '身份证号',
  `address_`     varchar(256)   NULL DEFAULT NULL COMMENT '常住地址',
  `enter_date_`  datetime       NULL DEFAULT NULL COMMENT '入职日期',
  `over_date_`   datetime       NULL DEFAULT NULL COMMENT '离职日期',
  `creator_id_`  varchar(36)    NOT NULL COMMENT '创建人ID',
  `create_time_` datetime       NOT NULL COMMENT '创建时间',
  `updater_id_`  varchar(36)    NULL DEFAULT NULL COMMENT '修改人ID',
  `update_time_` datetime       NULL DEFAULT NULL COMMENT '修改时间',
  `status_`      varchar(16)    NULL DEFAULT NULL COMMENT '工作状态:Active在职,Suspend停职,Leave离职;',
  `remark_`      varchar(512)   NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`employee_id_`) USING BTREE,
  INDEX `fk_uasp_employee_depart` (`depart_id_` ASC) USING BTREE,
  CONSTRAINT `fk_uasp_employee_depart` FOREIGN KEY (`depart_id_`) REFERENCES `uasp_depart` (`depart_id_`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_uasp_employee_user` FOREIGN KEY (`employee_id_`) REFERENCES `uasp_user` (`user_id_`) ON DELETE CASCADE ON UPDATE RESTRICT
) COMMENT = '员工';

-- ----------------------------
-- Table structure for uasp_file_folder
-- ----------------------------
CREATE TABLE `uasp_file_folder`
(
  `folder_id_`   varchar(36)  NOT NULL COMMENT '目录ID',
  `parent_id_`   varchar(36)  NULL DEFAULT NULL COMMENT '上级ID',
  `tenant_id_`   varchar(36)  NULL DEFAULT NULL COMMENT '租户ID',
  `name_`        varchar(128) NOT NULL COMMENT '目录名称',
  `owner_type_`  varchar(16)  NOT NULL COMMENT '所有类型:U个人;G群组;D部门;T租户共享;S系统共享;',
  `owner_id_`    varchar(36)  NULL DEFAULT NULL COMMENT '所有者ID',
  `create_time_` datetime     NOT NULL COMMENT '创建时间',
  `update_time_` datetime     NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`folder_id_`) USING BTREE
) COMMENT = '文件目录';

-- ----------------------------
-- Table structure for uasp_file_link
-- ----------------------------
CREATE TABLE `uasp_file_link`
(
  `link_id_`       varchar(36)   NOT NULL COMMENT '文件链接ID',
  `file_id_`       varchar(36)   NULL DEFAULT NULL COMMENT '文件ID',
  `folder_id_`     varchar(36)   NULL DEFAULT NULL COMMENT '目录ID',
  `tenant_id_`     varchar(36)   NULL DEFAULT NULL COMMENT '租户ID',
  `business_key_`  varchar(64)   NULL DEFAULT NULL COMMENT '业务主键',
  `category_`      varchar(128)  NULL DEFAULT NULL COMMENT '业务类别',
  `display_name_`  varchar(1024) NOT NULL COMMENT '显示名',
  `postfix_`       varchar(64)   NULL DEFAULT NULL COMMENT '扩展名',
  `uploader_id_`   varchar(36)   NULL DEFAULT NULL COMMENT '上传者ID',
  `uploader_name_` varchar(32)   NULL DEFAULT NULL COMMENT '上传者姓名',
  `upload_time_`   datetime      NULL DEFAULT NULL COMMENT '上传时间',
  `downloads_`     int           NULL DEFAULT 0 COMMENT '下载次数',
  `sort_`          int           NULL DEFAULT NULL COMMENT '排序码',
  `tags_`          varchar(2048) NULL DEFAULT NULL COMMENT '文件标签',
  PRIMARY KEY (`link_id_`) USING BTREE,
  INDEX `fk_uasp_file_link_folder` (`folder_id_` ASC) USING BTREE,
  INDEX `fk_uasp_file_link_store` (`file_id_` ASC) USING BTREE,
  CONSTRAINT `fk_uasp_file_link_folder` FOREIGN KEY (`folder_id_`) REFERENCES `uasp_file_folder` (`folder_id_`) ON DELETE SET NULL ON UPDATE RESTRICT,
  CONSTRAINT `fk_uasp_file_link_store` FOREIGN KEY (`file_id_`) REFERENCES `uasp_file_store` (`file_id_`) ON DELETE RESTRICT ON UPDATE RESTRICT
) COMMENT = '文件链接表';

-- ----------------------------
-- Table structure for uasp_file_share
-- ----------------------------
CREATE TABLE `uasp_file_share`
(
  `share_id_`    varchar(36) NOT NULL COMMENT '文件分享ID',
  `sharer_id_`   varchar(36) NOT NULL COMMENT '分享者ID',
  `receiver_id_` varchar(36) NOT NULL COMMENT '接收者Id',
  `folder_id_`   varchar(36) NOT NULL COMMENT '分享目录ID',
  `share_time_`  datetime    NOT NULL COMMENT '分享时间',
  `expire_time_` datetime    NULL DEFAULT NULL COMMENT '失效时间',
  `allow_write_` bit(1)      NULL DEFAULT NULL COMMENT '写权限',
  PRIMARY KEY (`share_id_`) USING BTREE,
  INDEX `fk_uasp_file_share_folder` (`folder_id_` ASC) USING BTREE,
  CONSTRAINT `fk_uasp_file_share_folder` FOREIGN KEY (`folder_id_`) REFERENCES `uasp_file_folder` (`folder_id_`) ON DELETE CASCADE ON UPDATE RESTRICT
) COMMENT = '文件分享';

-- ----------------------------
-- Table structure for uasp_file_store
-- ----------------------------
CREATE TABLE `uasp_file_store`
(
  `file_id_`       varchar(36)   NOT NULL COMMENT '文件ID',
  `file_size_`     bigint        NOT NULL COMMENT '大小',
  `content_type_`  varchar(1024) NOT NULL COMMENT '文件类型',
  `classify_`      varchar(16)   NULL DEFAULT NULL COMMENT '文件归类：IMAGE,VIDEO,DOC,ZIP,OTHER',
  `store_type_`    varchar(16)   NULL DEFAULT NULL COMMENT '存储方式:nfs,hdfs',
  `store_path_`    varchar(512)  NULL DEFAULT NULL COMMENT '存储路径',
  `hash_code_`     varchar(1024) NULL DEFAULT NULL COMMENT '哈希码256',
  `readonly_`      bit(1)        NOT NULL COMMENT '只读文件',
  `encrypted_`     bit(1)        NOT NULL COMMENT '是否加密',
  `salt_`          varchar(64)   NULL DEFAULT NULL COMMENT '加密盐',
  `last_modified_` timestamp     NULL DEFAULT NULL COMMENT '最后修改时间',
  `exists_`        bit(1)        NULL DEFAULT NULL,
  PRIMARY KEY (`file_id_`) USING BTREE
) COMMENT = '文件表';

-- ----------------------------
-- Table structure for uasp_flow_label
-- ----------------------------
CREATE TABLE `uasp_flow_label`
(
  `business_key_` varchar(36)  NOT NULL COMMENT '业务ID',
  `label_value_`  varchar(128) NOT NULL COMMENT '标签值',
  `update_time_`  datetime     NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`business_key_`) USING BTREE
) COMMENT = '流转标签';

-- ----------------------------
-- Table structure for uasp_form_define
-- ----------------------------
CREATE TABLE `uasp_form_define`
(
  `form_id_`   varchar(36)   NOT NULL COMMENT '业务表单ID',
  `app_id_`    varchar(36)   NOT NULL COMMENT '租户ID',
  `code_`      varchar(32)   NOT NULL COMMENT '表单编号',
  `name_`      varchar(1024) NOT NULL COMMENT '显示名',
  `category_`  varchar(32)   NULL DEFAULT NULL COMMENT '表单分组',
  `sort_`      int           NOT NULL COMMENT '排序码',
  `icon_`      varchar(4000) NULL DEFAULT NULL COMMENT '图标',
  `table_`     varchar(128)  NULL DEFAULT NULL COMMENT '物理表名',
  `module_id_` varchar(64)   NULL DEFAULT NULL COMMENT '绑定模块',
  `type_`      varchar(16)   NOT NULL COMMENT '表单类型:B 业务表单；C 自定义表单；',
  `status_`    varchar(16)   NOT NULL COMMENT '状态:D草搞,V可用,I禁用;',
  `remark_`    varchar(1024) NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`form_id_`) USING BTREE,
  INDEX `fk_uasp_formdefine_applic` (`app_id_` ASC) USING BTREE,
  INDEX `fk_uasp_form_module` (`module_id_` ASC) USING BTREE,
  CONSTRAINT `fk_uasp_form_module` FOREIGN KEY (`module_id_`) REFERENCES `uasp_module_define` (`module_id_`) ON DELETE SET NULL ON UPDATE RESTRICT,
  CONSTRAINT `fk_uasp_formdefine_applic` FOREIGN KEY (`app_id_`) REFERENCES `uasp_applic` (`app_id_`) ON DELETE CASCADE ON UPDATE RESTRICT
) COMMENT = '表单定义';

-- ----------------------------
-- Table structure for uasp_form_field
-- ----------------------------
CREATE TABLE `uasp_form_field`
(
  `field_id_`     varchar(36)  NOT NULL COMMENT '表单字段ID',
  `form_id_`      varchar(36)  NOT NULL COMMENT '业务表单ID',
  `field_name_`   varchar(128) NOT NULL COMMENT '字段名',
  `prop_name_`    varchar(128) NOT NULL COMMENT '属性名',
  `display_name_` varchar(128) NOT NULL COMMENT '显示名',
  `sort_`         int          NULL DEFAULT 0 COMMENT '排序号',
  `data_type_`    varchar(16)  NULL DEFAULT NULL COMMENT '数据类型',
  `usage_`        varchar(8)   NULL DEFAULT NULL COMMENT '字段类型:M主键,F流转码,S状态码',
  PRIMARY KEY (`field_id_`) USING BTREE,
  INDEX `fk_uasp_form_field_define` (`form_id_` ASC) USING BTREE,
  CONSTRAINT `fk_uasp_form_field_define` FOREIGN KEY (`form_id_`) REFERENCES `uasp_form_define` (`form_id_`) ON DELETE CASCADE ON UPDATE RESTRICT
) COMMENT = '表单字段';

-- ----------------------------
-- Table structure for uasp_form_flow
-- ----------------------------
CREATE TABLE `uasp_form_flow`
(
  `flow_id_`      varchar(36)   NOT NULL COMMENT '流程ID',
  `form_id_`      varchar(36)   NOT NULL COMMENT '业务表单ID',
  `tenant_id_`    varchar(36)   NOT NULL COMMENT '租户ID',
  `process_key_`  varchar(32)   NOT NULL COMMENT '过程编号',
  `display_name_` varchar(128)  NOT NULL COMMENT '流程名',
  `description_`  varchar(4000) NULL DEFAULT NULL COMMENT '流程描述',
  `primary_`      bit(1)        NOT NULL COMMENT '默认流程',
  `expr_type_`    varchar(16)   NULL DEFAULT NULL COMMENT '条件类型:NONE无,CUSTOM自定义,INNER内置',
  `custom_expr_`  longtext      NULL COMMENT '自定义表达式',
  `inner_expr_`   varchar(1024) NULL DEFAULT NULL COMMENT '内部表达式',
  `flow_labels_`  varchar(1024) NULL DEFAULT NULL COMMENT '流转标签',
  `status_`       varchar(16)   NOT NULL COMMENT '状态:D草搞,V可用,I禁用;',
  PRIMARY KEY (`flow_id_`) USING BTREE,
  INDEX `fk_uasp_form_flow_define` (`form_id_` ASC) USING BTREE,
  CONSTRAINT `fk_uasp_form_flow_define` FOREIGN KEY (`form_id_`) REFERENCES `uasp_form_define` (`form_id_`) ON DELETE CASCADE ON UPDATE RESTRICT
) COMMENT = '表单流程';

-- ----------------------------
-- Table structure for uasp_form_variable
-- ----------------------------
CREATE TABLE `uasp_form_variable`
(
  `variable_id_` varchar(36)  NOT NULL COMMENT '变量ID',
  `form_id_`     varchar(36)  NULL DEFAULT NULL COMMENT '业务表单ID',
  `name_`        varchar(256) NULL DEFAULT NULL COMMENT '变量显示名称',
  `tips_`        varchar(512) NULL DEFAULT NULL COMMENT '提示信息',
  `sort_`        int          NULL DEFAULT NULL COMMENT '排序号',
  `status_`      varchar(16)  NULL DEFAULT NULL COMMENT '状态:D草搞,V可用,I禁用;',
  PRIMARY KEY (`variable_id_`) USING BTREE
) COMMENT = '表单变量';

-- ----------------------------
-- Table structure for uasp_identity
-- ----------------------------
CREATE TABLE `uasp_identity`
(
  `identity_id_` varchar(36)   NOT NULL COMMENT '身份Id',
  `employee_id_` varchar(36)   NOT NULL COMMENT '用户ID',
  `depart_id_`   varchar(36)   NOT NULL COMMENT '部门ID',
  `position_id_` varchar(36)   NULL DEFAULT NULL COMMENT '岗位Id',
  `enter_date_`  datetime      NULL DEFAULT NULL COMMENT '入岗日期',
  `over_date_`   datetime      NULL DEFAULT NULL COMMENT '离岗日期',
  `status_`      varchar(16)   NULL DEFAULT NULL COMMENT '岗位状态：A在岗,L离岗;',
  `remark_`      varchar(2048) NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`identity_id_`) USING BTREE,
  INDEX `fk_uasp_identity_depart` (`depart_id_` ASC) USING BTREE,
  INDEX `fk_uasp_identity_position` (`position_id_` ASC) USING BTREE,
  INDEX `fk_uasp_id_employee` (`employee_id_` ASC) USING BTREE,
  CONSTRAINT `fk_uasp_id_employee` FOREIGN KEY (`employee_id_`) REFERENCES `uasp_employee` (`employee_id_`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `fk_uasp_identity_depart` FOREIGN KEY (`depart_id_`) REFERENCES `uasp_depart` (`depart_id_`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `fk_uasp_identity_position` FOREIGN KEY (`position_id_`) REFERENCES `uasp_position` (`position_id_`) ON DELETE SET NULL ON UPDATE RESTRICT
) COMMENT = '员工岗位';

-- ----------------------------
-- Table structure for uasp_ip_store
-- ----------------------------
CREATE TABLE `uasp_ip_store`
(
  `ip_addr_`     varchar(64)  NOT NULL COMMENT 'IP地址',
  `ip_desc_`     varchar(256) NOT NULL COMMENT '位置描述',
  `type_`        varchar(16)  NULL DEFAULT NULL COMMENT 'IP类型:LOCAL本机,LAN局域网,WAN广域网',
  `update_time_` datetime     NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ip_addr_`) USING BTREE
) COMMENT = 'IP地址库';

-- ----------------------------
-- Table structure for uasp_issue_answer
-- ----------------------------
CREATE TABLE `uasp_issue_answer`
(
  `answer_id_` varchar(36) NOT NULL COMMENT '评论ID',
  `issue_id_`  varchar(36) NULL DEFAULT NULL COMMENT '问题ID',
  `quote_id_`  varchar(36) NOT NULL COMMENT '引用ID（表示引用哪个评论）',
  `author_id_` varchar(64) NOT NULL COMMENT '评论人ID',
  `content_`   text        NOT NULL COMMENT '内容',
  `post_time_` datetime    NOT NULL COMMENT '时间',
  PRIMARY KEY (`answer_id_`) USING BTREE,
  INDEX `fk_uasp_issue_comment_post` (`issue_id_` ASC) USING BTREE,
  CONSTRAINT `fk_uasp_issue_comment_post` FOREIGN KEY (`issue_id_`) REFERENCES `uasp_issue_post` (`issue_id_`) ON DELETE RESTRICT ON UPDATE RESTRICT
) COMMENT = '问题回复';

-- ----------------------------
-- Table structure for uasp_issue_post
-- ----------------------------
CREATE TABLE `uasp_issue_post`
(
  `issue_id_`  varchar(36) NOT NULL COMMENT '问题ID',
  `title_`     varchar(32) NOT NULL COMMENT '标题',
  `author_id_` varchar(36) NOT NULL COMMENT '发表人ID',
  `content_`   text        NOT NULL COMMENT '内容',
  `post_time_` datetime    NOT NULL COMMENT '发表时间',
  `solved_`    bit(1)      NULL DEFAULT NULL COMMENT '是否解决',
  `tag_`       varchar(16) NULL DEFAULT NULL COMMENT '标签',
  `status_`    varchar(16) NULL DEFAULT NULL COMMENT '状态:状态:D草搞,V可用,I禁用,C已关闭;',
  PRIMARY KEY (`issue_id_`) USING BTREE
) COMMENT = '问题发布';

-- ----------------------------
-- Table structure for uasp_issue_watch
-- ----------------------------
CREATE TABLE `uasp_issue_watch`
(
  `watch_id_`    int         NOT NULL COMMENT '观察ID',
  `answer_id_`   varchar(36) NOT NULL COMMENT '回答ID',
  `useful_`      bit(1)      NOT NULL COMMENT '是否有用',
  `create_time_` datetime    NOT NULL COMMENT '记录时间',
  `user_id_`     varchar(36) NOT NULL COMMENT '用户ID',
  PRIMARY KEY (`watch_id_`) USING BTREE,
  INDEX `fk_uasp_issue_watch_comment` (`answer_id_` ASC) USING BTREE,
  CONSTRAINT `fk_uasp_issue_watch_comment` FOREIGN KEY (`answer_id_`) REFERENCES `uasp_issue_answer` (`answer_id_`) ON DELETE CASCADE ON UPDATE RESTRICT
) COMMENT = '问题观察者';

-- ----------------------------
-- Table structure for uasp_login_access
-- ----------------------------
CREATE TABLE `uasp_login_access`
(
  `log_id_`         int           NOT NULL AUTO_INCREMENT COMMENT '日志ID',
  `app_code_`       varchar(32)   NULL DEFAULT NULL COMMENT '系统代码',
  `create_time_`    datetime      NOT NULL COMMENT '记录时间',
  `user_id_`        varchar(36)   NOT NULL COMMENT '账号名',
  `request_url_`    varchar(2048) NULL DEFAULT NULL COMMENT '请求URL',
  `request_header_` longtext      NULL COMMENT '请求头',
  `request_body_`   longtext      NULL COMMENT '请求内容',
  `client_ip_`      varchar(32)   NULL DEFAULT NULL COMMENT '客户端IP',
  `client_address_` varchar(128)  NULL DEFAULT NULL COMMENT '客户端地址',
  `device_type_`    varchar(16)   NULL DEFAULT NULL COMMENT '设备类型:Windows,Android,iOS',
  `user_agent_`     varchar(4000) NULL DEFAULT NULL COMMENT '用户代理',
  PRIMARY KEY (`log_id_`) USING BTREE
) AUTO_INCREMENT = 1
  COMMENT = '登录日志';

-- ----------------------------
-- Table structure for uasp_mock_form
-- ----------------------------
CREATE TABLE `uasp_mock_form`
(
  `mock_id_`     varchar(36)   NOT NULL COMMENT '模拟实例ID',
  `usage_`       varchar(32)   NOT NULL COMMENT '用途',
  `tenant_id_`   varchar(36)   NOT NULL COMMENT '租户ID',
  `form_id_`     varchar(36)   NOT NULL COMMENT '业务表单ID',
  `code_`        varchar(32)   NOT NULL COMMENT '编号',
  `name_`        varchar(64)   NULL DEFAULT NULL COMMENT '名称',
  `extend_`      text          NULL COMMENT '扩展属性',
  `flow_code_`   varchar(32)   NULL DEFAULT NULL COMMENT '流转码',
  `creator_id_`  varchar(36)   NOT NULL COMMENT '创建人ID',
  `create_time_` datetime      NOT NULL COMMENT '创建时间',
  `updater_id_`  varchar(36)   NULL DEFAULT NULL COMMENT '修改人ID',
  `update_time_` datetime      NULL DEFAULT NULL COMMENT '修改时间',
  `status_`      char(1)       NOT NULL COMMENT '表单状态:D草稿;A审批中;S已通过;R未通过;I已作废;',
  `remark_`      varchar(1024) NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`mock_id_`) USING BTREE,
  INDEX `fk_uasp_mock_define` (`form_id_` ASC) USING BTREE,
  CONSTRAINT `fk_uasp_mock_define` FOREIGN KEY (`form_id_`) REFERENCES `uasp_form_define` (`form_id_`) ON DELETE CASCADE ON UPDATE RESTRICT
) COMMENT = '模拟表单';

-- ----------------------------
-- Table structure for uasp_module_action
-- ----------------------------
CREATE TABLE `uasp_module_action`
(
  `module_id_` varchar(36)  NOT NULL COMMENT '模块ID',
  `type_`      varchar(16)  NOT NULL COMMENT '权限类型:FUNC,DATA',
  `code_`      varchar(64)  NOT NULL COMMENT '代码',
  `name_`      varchar(128) NULL DEFAULT NULL COMMENT '显示名',
  `sort_`      int          NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`module_id_`, `type_`, `code_`) USING BTREE,
  CONSTRAINT `fk_uasp_module_action_define` FOREIGN KEY (`module_id_`) REFERENCES `uasp_module_define` (`module_id_`) ON DELETE CASCADE ON UPDATE RESTRICT
) COMMENT = '功能模块动作';

-- ----------------------------
-- Table structure for uasp_module_define
-- ----------------------------
CREATE TABLE `uasp_module_define`
(
  `module_id_` varchar(36)  NOT NULL COMMENT '模块ID',
  `parent_id_` varchar(36)  NULL DEFAULT NULL COMMENT '上级ID',
  `app_id_`    varchar(36)  NULL DEFAULT NULL COMMENT '应用ID',
  `type_`      varchar(16)  NOT NULL COMMENT '类型:C分类,D分隔,M模块;',
  `code_`      varchar(64)  NULL DEFAULT NULL COMMENT '编号',
  `name_`      varchar(128) NOT NULL COMMENT '名称',
  `icon_`      varchar(512) NULL DEFAULT NULL COMMENT '图标',
  `url_`       varchar(512) NULL DEFAULT NULL COMMENT '功能路径',
  `target_`    varchar(16)  NULL DEFAULT NULL COMMENT '打开方式:blank新页;',
  `sort_`      int          NULL DEFAULT NULL COMMENT '排序码',
  `usage_`     varchar(16)  NULL DEFAULT NULL COMMENT '适用性:A管理,B业务,C通用;',
  `status_`    varchar(16)  NOT NULL COMMENT '状态:D草搞,V可用,I禁用;',
  `remark_`    varchar(512) NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`module_id_`) USING BTREE,
  INDEX `fk_uasp_module_applic` (`app_id_` ASC) USING BTREE,
  CONSTRAINT `fk_uasp_module_applic` FOREIGN KEY (`app_id_`) REFERENCES `uasp_applic` (`app_id_`) ON DELETE CASCADE ON UPDATE RESTRICT
) COMMENT = '功能模块定义';

-- ----------------------------
-- Table structure for uasp_position
-- ----------------------------
CREATE TABLE `uasp_position`
(
  `position_id_` varchar(36)   NOT NULL COMMENT '岗位Id',
  `depart_id_`   varchar(36)   NULL DEFAULT NULL COMMENT '部门ID',
  `code_`        varchar(32)   NULL DEFAULT NULL COMMENT '编码',
  `name_`        varchar(128)  NOT NULL COMMENT '名称',
  `sort_`        smallint      NOT NULL COMMENT '排序码',
  `level_`       int           NOT NULL COMMENT '级别:0-N 等级由高至低',
  `creator_id_`  varchar(36)   NOT NULL COMMENT '创建人ID',
  `create_time_` datetime      NOT NULL COMMENT '创建时间',
  `updater_id_`  varchar(36)   NULL DEFAULT NULL COMMENT '修改人ID',
  `update_time_` datetime      NULL DEFAULT NULL COMMENT '修改时间',
  `status_`      varchar(16)   NOT NULL COMMENT '状态:D草搞,V可用,I禁用;',
  `remark_`      varchar(2048) NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`position_id_`) USING BTREE,
  INDEX `fk_uasp_position_depart` (`depart_id_` ASC) USING BTREE,
  CONSTRAINT `fk_uasp_position_depart` FOREIGN KEY (`depart_id_`) REFERENCES `uasp_depart` (`depart_id_`) ON DELETE CASCADE ON UPDATE RESTRICT
) COMMENT = '岗位';

-- ----------------------------
-- Table structure for uasp_privilege
-- ----------------------------
CREATE TABLE `uasp_privilege`
(
  `link_id_`   varchar(36)   NOT NULL COMMENT '关联ID:租户ID,角色ID,用户ID,公司ID;',
  `type_`      varchar(16)   NOT NULL COMMENT '权限类型:T租户,R角色,U用户,C往来公司',
  `module_id_` varchar(36)   NOT NULL COMMENT '模块ID',
  `actions_`   varchar(4000) NOT NULL COMMENT '动作授权',
  PRIMARY KEY (`type_`, `link_id_`, `module_id_`) USING BTREE,
  INDEX `fk_uasp_privilege_module` (`module_id_` ASC) USING BTREE,
  CONSTRAINT `fk_uasp_privilege_module` FOREIGN KEY (`module_id_`) REFERENCES `uasp_module_define` (`module_id_`) ON DELETE CASCADE ON UPDATE RESTRICT
) COMMENT = '功能权限表';

-- ----------------------------
-- Table structure for uasp_remember_me
-- ----------------------------
CREATE TABLE `uasp_remember_me`
(
  `series_id_` varchar(36)  NOT NULL COMMENT '主键',
  `user_id_`   varchar(36)  NULL DEFAULT NULL COMMENT '用户ID',
  `token_`     varchar(128) NOT NULL COMMENT '令牌',
  `last_used_` datetime     NOT NULL COMMENT '最后操作',
  PRIMARY KEY (`series_id_`) USING BTREE,
  INDEX `fk_uasp_rememberme_user` (`user_id_` ASC) USING BTREE,
  CONSTRAINT `fk_uasp_rememberme_user` FOREIGN KEY (`user_id_`) REFERENCES `uasp_user` (`user_id_`) ON DELETE CASCADE ON UPDATE RESTRICT
) COMMENT = '记住用户';

-- ----------------------------
-- Table structure for uasp_role
-- ----------------------------
CREATE TABLE `uasp_role`
(
  `role_id_`     varchar(36)   NOT NULL COMMENT '角色ID',
  `app_id_`      varchar(36)   NULL DEFAULT NULL COMMENT '应用ID',
  `tenant_id_`   varchar(36)   NULL DEFAULT NULL COMMENT '租户ID',
  `code_`        varchar(16)   NULL DEFAULT NULL COMMENT '编号',
  `name_`        varchar(32)   NOT NULL COMMENT '名称',
  `type_`        varchar(16)   NOT NULL COMMENT '类型:FN功能角色;WF流程角色;DT数据角色;',
  `sort_`        int           NULL DEFAULT NULL COMMENT '排序码',
  `remark_`      varchar(2048) NULL DEFAULT NULL COMMENT '备注',
  `creator_id_`  varchar(36)   NOT NULL COMMENT '创建人ID',
  `create_time_` datetime      NOT NULL COMMENT '创建时间',
  `updater_id_`  varchar(36)   NULL DEFAULT NULL COMMENT '修改人ID',
  `update_time_` datetime      NULL DEFAULT NULL COMMENT '修改时间',
  `status_`      varchar(16)   NOT NULL COMMENT '状态:D草搞,V可用,I禁用;',
  PRIMARY KEY (`role_id_`) USING BTREE
) COMMENT = '角色';

-- ----------------------------
-- Table structure for uasp_role_user
-- ----------------------------
CREATE TABLE `uasp_role_user`
(
  `role_id_`     varchar(36) NOT NULL COMMENT '角色ID',
  `user_id_`     varchar(36) NOT NULL COMMENT '用户ID',
  `creator_id_`  varchar(36) NOT NULL COMMENT '创建人ID',
  `create_time_` datetime    NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id_`, `user_id_`) USING BTREE,
  CONSTRAINT `fk_uasp_roleuser_role` FOREIGN KEY (`role_id_`) REFERENCES `uasp_role` (`role_id_`) ON DELETE CASCADE ON UPDATE RESTRICT
) COMMENT = '角色用户';

-- ----------------------------
-- Table structure for uasp_signup
-- ----------------------------
CREATE TABLE `uasp_signup`
(
  `signup_id_`   varchar(36)  NOT NULL COMMENT '注册ID',
  `mobile_`      varchar(128) NOT NULL COMMENT '手机号码',
  `user_name_`   varchar(32)  NULL DEFAULT NULL COMMENT '登录名',
  `nick_name_`   varchar(64)  NULL DEFAULT NULL COMMENT '呢称',
  `password_`    varchar(512) NOT NULL COMMENT '密码',
  `email_`       varchar(256) NULL DEFAULT NULL COMMENT '电子邮件',
  `create_time_` datetime     NOT NULL COMMENT '申请时间',
  `status_`      varchar(16)  NOT NULL COMMENT '状态:APPLYING 申请中,PASSED允许,REFUSED拒绝;',
  `remark_`      varchar(512) NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`signup_id_`) USING BTREE
) COMMENT = '账号注册';

-- ----------------------------
-- Table structure for uasp_sms_send
-- ----------------------------
CREATE TABLE `uasp_sms_send`
(
  `log_id_`    varchar(36)  NOT NULL COMMENT '日志ID',
  `usage_`     varchar(32)  NOT NULL COMMENT '用途码',
  `app_id_`    varchar(36)  NULL DEFAULT NULL COMMENT '来源系统ID',
  `tenant_id_` varchar(36)  NULL DEFAULT NULL COMMENT '来源租户ID',
  `mobile_`    varchar(32)  NOT NULL COMMENT '接收手机号',
  `content_`   varchar(512) NOT NULL COMMENT '短信内容',
  `send_time_` datetime     NOT NULL COMMENT '发送时间',
  PRIMARY KEY (`log_id_`) USING BTREE
) COMMENT = '短信发送日志';

-- ----------------------------
-- Table structure for uasp_sneak
-- ----------------------------
CREATE TABLE `uasp_sneak`
(
  `token_`  varchar(512) NOT NULL COMMENT '令牌',
  `expire_` bigint       NOT NULL COMMENT '创建时间',
  `user_`   varchar(64)  NOT NULL COMMENT '登录者',
  `caller_` varchar(64)  NOT NULL COMMENT '调用者',
  PRIMARY KEY (`token_`) USING BTREE
) COMMENT = '访问令牌';

-- ----------------------------
-- Table structure for uasp_task_delegate
-- ----------------------------
CREATE TABLE `uasp_task_delegate`
(
  `delegate_id_` varchar(36) NOT NULL COMMENT '任务委托ID',
  `tenant_id_`   varchar(36) NOT NULL COMMENT '租户ID',
  `owner_id_`    varchar(36) NOT NULL COMMENT '所属人ID',
  `proxy_id_`    varchar(36) NOT NULL COMMENT '代理人ID',
  `start_time_`  datetime    NOT NULL COMMENT '生效时间',
  `end_time_`    datetime    NULL DEFAULT NULL COMMENT '失效时间',
  `creator_id_`  varchar(36) NOT NULL COMMENT '创建人Id',
  `create_time_` datetime    NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`delegate_id_`) USING BTREE
) COMMENT = '任务委托定义';

-- ----------------------------
-- Table structure for uasp_task_delegate_form
-- ----------------------------
CREATE TABLE `uasp_task_delegate_form`
(
  `delegate_id_` varchar(36) NOT NULL COMMENT '任务委托ID',
  `form_id_`     varchar(36) NOT NULL COMMENT '业务表单ID',
  PRIMARY KEY (`delegate_id_`, `form_id_`) USING BTREE,
  INDEX `fk_uasp_tdform_bizform` (`form_id_` ASC) USING BTREE,
  CONSTRAINT `fk_uasp_form_task_delegate` FOREIGN KEY (`delegate_id_`) REFERENCES `uasp_task_delegate` (`delegate_id_`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `fk_uasp_tdform_bizform` FOREIGN KEY (`form_id_`) REFERENCES `uasp_form_define` (`form_id_`) ON DELETE CASCADE ON UPDATE RESTRICT
) COMMENT = '任务委托表单';

-- ----------------------------
-- Table structure for uasp_team
-- ----------------------------
CREATE TABLE `uasp_team`
(
  `team_id_`     varchar(36)  NOT NULL COMMENT '群组ID',
  `tenant_id_`   varchar(36)  NULL DEFAULT NULL COMMENT '租户ID',
  `code_`        varchar(16)  NOT NULL COMMENT '编号',
  `name_`        varchar(128) NOT NULL COMMENT '名称',
  `sort_`        int          NOT NULL COMMENT '排序码',
  `start_time_`  datetime     NOT NULL COMMENT '成立时间',
  `end_time_`    datetime     NULL DEFAULT NULL COMMENT '解散时间',
  `creator_id_`  varchar(36)  NOT NULL COMMENT '创建人ID',
  `create_time_` datetime     NOT NULL COMMENT '创建时间',
  `updater_id_`  varchar(36)  NULL DEFAULT NULL COMMENT '修改人ID',
  `update_time_` datetime     NULL DEFAULT NULL COMMENT '修改时间',
  `status_`      varchar(16)  NOT NULL COMMENT '状态:D草搞,V可用,I禁用;',
  `remark_`      varchar(512) NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`team_id_`) USING BTREE
) COMMENT = '团队';

-- ----------------------------
-- Table structure for uasp_team_user
-- ----------------------------
CREATE TABLE `uasp_team_user`
(
  `member_id_`   varchar(36)  NOT NULL COMMENT '小组成员ID',
  `team_id_`     varchar(36)  NOT NULL COMMENT '群组ID',
  `employee_id_` varchar(36)  NULL DEFAULT NULL COMMENT '员工ID',
  `duty_`        varchar(64)  NULL DEFAULT NULL COMMENT '职责',
  `level_`       int          NULL DEFAULT NULL COMMENT '级别:0-N 等级由高至低',
  `join_time_`   datetime     NOT NULL COMMENT '加入时间',
  `leave_time_`  datetime     NULL DEFAULT NULL COMMENT '离开时间',
  `remark_`      varchar(512) NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`member_id_`) USING BTREE,
  INDEX `fk_uasp_teamuser_team` (`team_id_` ASC) USING BTREE,
  INDEX `fk_uasp_teamuser_employee` (`employee_id_` ASC) USING BTREE,
  CONSTRAINT `fk_uasp_teamuser_employee` FOREIGN KEY (`employee_id_`) REFERENCES `uasp_employee` (`employee_id_`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `fk_uasp_teamuser_team` FOREIGN KEY (`team_id_`) REFERENCES `uasp_team` (`team_id_`) ON DELETE CASCADE ON UPDATE RESTRICT
) COMMENT = '团队成员';

-- ----------------------------
-- Table structure for uasp_tenant
-- ----------------------------
CREATE TABLE `uasp_tenant`
(
  `tenant_id_`   varchar(36)   NOT NULL COMMENT '租户ID',
  `admin_id_`    varchar(36)   NOT NULL COMMENT '管理员ID',
  `expire_time_` datetime      NULL DEFAULT NULL COMMENT '到期时间',
  `remark_`      varchar(1024) NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`tenant_id_`) USING BTREE,
  CONSTRAINT `fk_uasp_tenant_company` FOREIGN KEY (`tenant_id_`) REFERENCES `uasp_company` (`company_id_`) ON DELETE CASCADE ON UPDATE RESTRICT
) COMMENT = '租户表';

-- ----------------------------
-- Table structure for uasp_used_opinion
-- ----------------------------
CREATE TABLE `uasp_used_opinion`
(
  `opinion_id_` varchar(36)   NOT NULL COMMENT '常用语ID',
  `user_id_`    varchar(36)   NOT NULL COMMENT '所属用户ID',
  `content_`    varchar(1024) NULL DEFAULT NULL COMMENT '常用语',
  `use_count_`  int           NULL DEFAULT NULL COMMENT '使用次数',
  `use_time_`   datetime      NULL DEFAULT NULL COMMENT '使用时间',
  PRIMARY KEY (`opinion_id_`) USING BTREE
) COMMENT = '审批常用语';

-- ----------------------------
-- Table structure for uasp_user
-- ----------------------------
CREATE TABLE `uasp_user`
(
  `user_id_`         varchar(36)   NOT NULL COMMENT '用户ID',
  `tenant_id_`       varchar(36)   NULL DEFAULT NULL COMMENT '租户ID',
  `user_name_`       varchar(32)   NOT NULL COMMENT '登录名',
  `display_name_`    varchar(64)   NOT NULL COMMENT '显示名',
  `user_type_`       varchar(64)   NOT NULL COMMENT '用户类型:ROOT,ADMIN,USER,CLIENT;',
  `avatar_`          varchar(36)   NULL DEFAULT NULL COMMENT '头像:文件链接ID',
  `password_`        varchar(512)  NOT NULL COMMENT '密码',
  `salt_`            varchar(64)   NULL DEFAULT NULL COMMENT '加密盐',
  `email_`           varchar(256)  NULL DEFAULT NULL COMMENT '电子邮件',
  `mobile_`          varchar(128)  NULL DEFAULT NULL COMMENT '手机号码',
  `level_`           int           NULL DEFAULT 0 COMMENT '级别',
  `account_expire_`  datetime      NULL DEFAULT NULL COMMENT '账号到期',
  `password_expire_` datetime      NULL DEFAULT NULL COMMENT '密码到期',
  `enabled_`         bit(1)        NOT NULL COMMENT '是否可用',
  `locked_`          bit(1)        NOT NULL COMMENT '是否锁定',
  `creator_id_`      varchar(36)   NOT NULL COMMENT '创建人ID',
  `create_time_`     datetime      NOT NULL COMMENT '创建时间',
  `updater_id_`      varchar(36)   NULL DEFAULT NULL COMMENT '修改人ID',
  `update_time_`     datetime      NULL DEFAULT NULL COMMENT '修改时间',
  `remark_`          varchar(2048) NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id_`) USING BTREE,
  UNIQUE INDEX `idx_uasp_user_name` (`user_name_` ASC) USING BTREE,
  UNIQUE INDEX `idx_uasp_user_mobile` (`mobile_` ASC) USING BTREE,
  INDEX `fk_uasp_user_tenant` (`tenant_id_` ASC) USING BTREE,
  CONSTRAINT `fk_uasp_user_tenant` FOREIGN KEY (`tenant_id_`) REFERENCES `uasp_tenant` (`tenant_id_`) ON DELETE CASCADE ON UPDATE RESTRICT
) COMMENT = '账号';

-- ----------------------------
-- Table structure for uasp_user_avatar
-- ----------------------------
CREATE TABLE `uasp_user_avatar`
(
  `avatar_id_`   varchar(36) NOT NULL COMMENT '头像ID',
  `user_id_`     varchar(36) NULL DEFAULT NULL COMMENT '用户ID',
  `photo_id_`    varchar(36) NOT NULL COMMENT '图片ID',
  `updater_id_`  varchar(36) NOT NULL COMMENT '创建人ID',
  `update_time_` datetime    NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`avatar_id_`) USING BTREE,
  INDEX `fk_uasp_avatar_user` (`user_id_` ASC) USING BTREE,
  CONSTRAINT `fk_uasp_avatar_user` FOREIGN KEY (`user_id_`) REFERENCES `uasp_user` (`user_id_`) ON DELETE CASCADE ON UPDATE RESTRICT
) COMMENT = '用户头像';

-- ----------------------------
-- Table structure for uasp_user_log
-- ----------------------------
CREATE TABLE `uasp_user_log`
(
  `log_id_`      varchar(36)  NOT NULL,
  `user_id_`     varchar(36)  NOT NULL COMMENT '用户ID',
  `type_`        varchar(16)  NOT NULL COMMENT '类型:Fail失败,Success成功',
  `remark_`      varchar(512) NULL DEFAULT NULL COMMENT '描述',
  `url_`         varchar(512) NULL DEFAULT NULL COMMENT '请求URL',
  `ip_`          varchar(32)  NULL DEFAULT NULL COMMENT '客户端IP',
  `address_`     varchar(128) NULL DEFAULT NULL COMMENT '客户端地址',
  `device_`      varchar(16)  NULL DEFAULT NULL COMMENT '登录设备:Windows,Android,iOS',
  `create_time_` datetime     NOT NULL COMMENT '记录时间',
  PRIMARY KEY (`log_id_`) USING BTREE,
  INDEX `fk_uasp_log_user` (`user_id_` ASC) USING BTREE,
  CONSTRAINT `fk_uasp_log_user` FOREIGN KEY (`user_id_`) REFERENCES `uasp_user` (`user_id_`) ON DELETE CASCADE ON UPDATE RESTRICT
) COMMENT = '用户日志';

-- ----------------------------
-- View structure for uasp_membership
-- ----------------------------
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `uasp_membership` AS
select `mt`.`employee_id_` AS `USER_ID_`, `mt`.`company_id_` AS `GROUP_ID_`
from `uasp_employee` `mt`
union all
select `mt`.`employee_id_` AS `USER_ID_`, `mt`.`depart_id_` AS `GROUP_ID_`
from `uasp_employee` `mt`
union all
select `mt`.`employee_id_` AS `USER_ID_`, `mt`.`position_id_` AS `GROUP_ID_`
from `uasp_identity` `mt`
where (`mt`.`position_id_` is not null)
union all
select `ru`.`user_id_` AS `USER_ID_`, `ru`.`role_id_` AS `GROUP_ID_`
from `uasp_role_user` `ru`
union all
select `mt`.`leader_id_` AS `USER_ID_`, concat(`mt`.`depart_id_`, '_', `mt`.`duty_`) AS `GROUP_ID_`
from `uasp_depart_leader` `mt`;

-- ----------------------------
-- View structure for uasp_organize
-- ----------------------------
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `uasp_organize` AS
select `cc`.`company_id_` AS `id_`, `cc`.`parent_id_` AS `parent_id_`, `cc`.`code_` AS `code_`, `cc`.`short_name_` AS `name_`, 'C' AS `type_`, `cc`.`sort_` AS `sort_`
from `uasp_company` `cc`
union all
select `dd`.`depart_id_` AS `depart_id_`, ifnull(`dd`.`parent_id_`, `dd`.`company_id_`) AS `parent_id_`, `dd`.`code_` AS `code_`, `dd`.`name_` AS `name_`, 'D' AS `type_`, `dd`.`sort_` AS `sort_`
from `uasp_depart` `dd`
union all
select `pp`.`position_id_` AS `position_id_`, `pp`.`depart_id_` AS `depart_id_`, `pp`.`code_` AS `code_`, `pp`.`name_` AS `name_`, 'P' AS `type_`, `pp`.`sort_` AS `sort_`
from `uasp_position` `pp`;

SET FOREIGN_KEY_CHECKS = 1;
