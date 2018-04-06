insert into `y_const` (
ACTIVE_FLAG,	CONST_GROUP,	CONST_CODE,	CONST_NAME,	CONST_COMMENT)
values	
(1, 'EVENT_TYPE','1','未知名尸体',	''),
(1, 'EVENT_TYPE','2','落水失踪人员',''),	
(1, 'EVENT_TYPE','3','疑似落水失踪人员',	''),
(1, 'EVENT_TYPE','4','外单位协查',	''),
(1, 'EVENT_CHECKED_CLEAR','0','未查清',	'是否查清：未查清'),
(1, 'EVENT_CHECKED_CLEAR','1','已查清',	'是否查清：已查清'),
(1, 'EVENT_NOTIFY_CITY','0','未通知市局',	'110备注-是否通知市局：未通知'),
(1, 'EVENT_NOTIFY_CITY','1','已通知市局',	'110备注-是否通知市局：已通知'),
(1, 'EVENT_NOTIFY_OTHER','0','未通知其他分局',	'110备注-是否通知其他分局：未通知'),
(1, 'EVENT_NOTIFY_OTHER','1','已通知其他分局',	'110备注-是否通知其他分局：已通知'),
(1, 'EVENT_XZ_DNA','0','未采集',	'刑侦队备注-是否采集DNA:未采集'),
(1, 'EVENT_XZ_DNA','1','已采集',	'刑侦队备注-是否采集DNA:已采集'),
(1, 'EVENT_XZ_OTHER','0','已通知其他单位','刑侦队备注-是否通知其他单位：已通知'),
(1, 'EVENT_XZ_OTHER','1','未通知其他单位','刑侦队备注-是否通知其他单位：未通知'),
(1, 'EVENT_COMMENT','COMMENT_PCS',	'派出所备注',''),
(1, 'RECEIVE_EVENT_ORG','1','接警单位A',	'接警单位'),
(1, 'RECEIVE_EVENT_ORG','2','接警单位B',	'接警单位')	,
(1, 'RECEIVE_EVENT_ORG','3','接警单位C',	'接警单位'),	
(1, 'CO_ORG','1','协查单位1', '协查单位'),
(1, 'CO_ORG','2','协查单位2', '协查单位'),
(1, 'CO_ORG','3','协查单位3', '协查单位'),
(1,'GENDER','M', '男','性别'),
(1,'GENDER','F', '女','性别'),
(1,'GENDER','U', '未知','性别')
;
