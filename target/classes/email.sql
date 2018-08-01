ALTER TABLE `sys_user`
MODIFY COLUMN `user_email` varchar(50) NULL DEFAULT 'test@mybatis.tk'
COMMENT '邮箱'
AFTER `user_password`;