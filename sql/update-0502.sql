ALTER TABLE yc_uep.y_event_comment ADD active TINYINT DEFAULT 1 NOT NULL COMMENT '是否生效：1-生效 0-失效' ;

INSERT INTO y_const (const_id,const_group,const_code,const_name,const_comment,active_flag) VALUES 
(25,'EVENT_COMMENT_XZZD','COMMENT_XZZD','刑侦支队备注','刑侦支队备注：输入内容',1)
,(26,'EVENT_COMMENT_110','COMMENT_110','110备注','110备注：输入肉',1)
;