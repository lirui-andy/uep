INSERT INTO uep.y_role (role_name,comment)
VALUES ('ROLE_110','110角色') ;
INSERT INTO uep.y_role (role_name,comment)
VALUES ('ROLE_XZD','刑侦队角色') ;


DELETE FROM uep.y_user_perm
WHERE perm_id=2 ;
DELETE FROM uep.y_user_perm
WHERE perm_id=3 ;
DELETE FROM uep.y_user_perm
WHERE perm_id=5 ;
DELETE FROM uep.y_user_perm
WHERE perm_id=6 ;
DELETE FROM uep.y_user_perm
WHERE perm_id=7 ;
