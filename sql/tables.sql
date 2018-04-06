CREATE TABLE `y_attachment` (
  `att_id` int(11) NOT NULL AUTO_INCREMENT,
  `att_uri` varchar(100) NOT NULL,
  `event_id` int(11) DEFAULT NULL,
  `up_time` datetime DEFAULT NULL,
  `up_user` varchar(100) DEFAULT NULL,
  `file_name` varchar(500) DEFAULT NULL,
  `file_size` bigint(20) DEFAULT NULL,
  `file_type` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`att_id`),
  KEY `idxatt_1` (`event_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='附件表';

CREATE TABLE `y_const` (
  `const_id` int(11) NOT NULL AUTO_INCREMENT,
  `const_group` varchar(100) NOT NULL,
  `const_code` varchar(100) NOT NULL,
  `const_name` varchar(200) DEFAULT NULL,
  `const_comment` varchar(100) DEFAULT NULL,
  `active_flag` smallint(1) DEFAULT '1',
  PRIMARY KEY (`const_id`),
  KEY `idxconst_1` (`const_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='常量表';

CREATE TABLE `y_event` (
  `event_id` int(11) NOT NULL AUTO_INCREMENT,
  `event_type` varchar(100) DEFAULT NULL COMMENT '事件类型',
  `rcv_org_code` varchar(100) DEFAULT NULL COMMENT '接警单位代码',
  `rcv_org_name` varchar(200) DEFAULT NULL COMMENT '接警单位名称',
  `handler_name` varchar(100) DEFAULT NULL COMMENT '处警民警',
  `reportor_name` varchar(100) DEFAULT NULL COMMENT '报警人姓名',
  `reportor_tel` varchar(200) DEFAULT NULL COMMENT '报警人电话',
  `name` varchar(100) DEFAULT NULL COMMENT '姓名',
  `gender` varchar(10) DEFAULT NULL COMMENT '性别',
  `event_time` datetime DEFAULT NULL COMMENT '事件时间',
  `id_num` varchar(50) DEFAULT NULL COMMENT '身份证号',
  `co_org_name` varchar(200) DEFAULT NULL COMMENT '协查单位名称',
  `co_org_contact` varchar(100) DEFAULT NULL COMMENT '协查单位联系人',
  `co_org_tel` varchar(50) DEFAULT NULL COMMENT '协查单位电话',
  `input_user_id` int(11) DEFAULT NULL COMMENT '录入人ID',
  `input_user_name` varchar(100) DEFAULT NULL COMMENT '录入人账号',
  `input_real_name` varchar(100) DEFAULT NULL COMMENT '录入人姓名',
  `input_org_id` int(11) DEFAULT NULL COMMENT '录入人单位id',
  `input_org_name` varchar(200) DEFAULT NULL COMMENT '录入人单位名称',
  `input_time` datetime DEFAULT NULL COMMENT '录入时间',
  `reviewer_name` varchar(100) DEFAULT NULL COMMENT '审核人',
  `brief_info` varchar(1000) DEFAULT NULL COMMENT '简要警情',
  `detail_info` varchar(1000) DEFAULT NULL COMMENT '人员情况',
  PRIMARY KEY (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='事件表';

CREATE TABLE `y_event_comment` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '备注id。自增长列',
  `event_id` int(11) NOT NULL COMMENT '事件id',
  `comment_type` varchar(100) NOT NULL COMMENT '备注类型。',
  `comment_value` varchar(1000) NOT NULL COMMENT '备注值',
  `oper_user` varchar(100) NOT NULL COMMENT '备注人',
  `oper_time` datetime NOT NULL COMMENT '备注时间',
  PRIMARY KEY (`comment_id`),
  KEY `I_CMT_EVENTID` (`event_id`,`comment_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='事件表';

CREATE TABLE `y_event_receipt` (
  `receipt_id` int(11) NOT NULL AUTO_INCREMENT,
  `event_id` int(11) NOT NULL COMMENT '事件ID',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `org_id` int(11) NOT NULL COMMENT '单位id',
  `receipt_time` datetime NOT NULL COMMENT '签收时间',
  `receipt_org_name` varchar(200) NOT NULL COMMENT '签收单位名称',
  `receipt_user` varchar(100) NOT NULL COMMENT '签收人姓名（页面填写）',
  PRIMARY KEY (`receipt_id`),
  KEY `I_RCPT_EVENTID` (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='事件表';

CREATE TABLE `y_org` (
  `org_id` int(11) NOT NULL AUTO_INCREMENT,
  `org_name` varchar(200) DEFAULT NULL,
  `org_parent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`org_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `y_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `org_id` int(11) DEFAULT NULL,
  `user_name` varchar(100) DEFAULT NULL,
  `pwd` varchar(100) DEFAULT NULL,
  `real_name` varchar(100) DEFAULT NULL,
  `org_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `y_user_perm` (
  `perm_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) NOT NULL,
  `permcode` varchar(100) DEFAULT NULL,
  `perm_comment` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`perm_id`),
  KEY `idx_perm1` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `y_role` (
  `role_name` varchar(100) NOT NULL,
  `comment` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
