
INSERT INTO y_role (role_name,comment)
VALUES ('ROLE_110','110角色') ;
INSERT INTO y_role (role_name,comment)
VALUES ('ROLE_XZD','刑侦队角色') ;
INSERT INTO y_role (role_name,comment)
VALUES ('ROLE_EVENT_MAN','事件数据管理员角色') ;

INSERT INTO y_user_perm (user_name,permcode) VALUES ('xzzd','ROLE_EVENT_MAN') ;
delete from y_user_perm where user_name = 'xzzd' and permcode='ROLE_110';
delete from y_user_perm where user_name = 'ycfj' and permcode='ROLE_XZD';

alter table y_event add (
  `update_user_id` int(11) DEFAULT NULL COMMENT '修改人ID',
  `update_user_name` varchar(100) DEFAULT NULL COMMENT '修改人账号',
  `update_real_name` varchar(100) DEFAULT NULL COMMENT '修改人姓名',
  `update_org_id` int(11) DEFAULT NULL COMMENT '修改人单位id',
  `update_org_name` varchar(200) DEFAULT NULL COMMENT '修改人单位名称',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_reviewer_name` varchar(100) DEFAULT NULL COMMENT '修改审核人',
  `active` TINYINT DEFAULT 1 NOT NULL COMMENT '是否活动数据 1-活动数据 0-已删除' 
  )
;
CREATE TABLE `y_event_his` (
`rec_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '数据记录ID',
  `event_id` int(11) NOT NULL comment '事件ID',
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
  `brief_info` varchar(3000) DEFAULT NULL COMMENT '简要警情',
  `detail_info` varchar(3000) DEFAULT NULL COMMENT '人员情况',
  `update_user_id` int(11) DEFAULT NULL COMMENT '修改人ID',
  `update_user_name` varchar(100) DEFAULT NULL COMMENT '修改人账号',
  `update_real_name` varchar(100) DEFAULT NULL COMMENT '修改人姓名',
  `update_org_id` int(11) DEFAULT NULL COMMENT '修改人单位id',
  `update_org_name` varchar(200) DEFAULT NULL COMMENT '修改人单位名称',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_reviewer_name` varchar(100) DEFAULT NULL COMMENT '修改审核人',
  `active` TINYINT DEFAULT 1 NOT NULL COMMENT '是否活动数据 1-活动数据 0-已删除',
  PRIMARY KEY (`rec_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='事件表';