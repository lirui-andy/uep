/**新增110用户*/
insert `yc_uep`.`y_org`(`org_id`,`org_name`,`org_parent_id`) 
values(12,'110',1);
insert `yc_uep`.`y_user`(`user_id`,`org_id`,`user_name`,`pwd`,`real_name`,`org_name`) 
values(12,12,'110','123456','110','110');
insert `yc_uep`.`y_user_perm`(`user_name`,`permcode`) 
values('110','ROLE_110');