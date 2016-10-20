SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `roles`
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `userId` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(128) NOT NULL,
  `archived` tinyint(1) DEFAULT '0',
  `version` int(11) DEFAULT '0',
  `create_time` bigint(20) DEFAULT '0',
  PRIMARY KEY (`userId`),
  UNIQUE KEY `role_name` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `roles_permissions`
-- ----------------------------
DROP TABLE IF EXISTS `roles_permissions`;
CREATE TABLE `roles_permissions` (
  `roles_id` bigint(20) NOT NULL,
  `permission` varchar(128) NOT NULL,
  `create_time` bigint(20) DEFAULT '0',
  KEY `roles_id_index` (`roles_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_seq`
-- ----------------------------
DROP TABLE IF EXISTS `sys_seq`;
CREATE TABLE `sys_seq` (
  `seq_name` varchar(128) NOT NULL,
  `current_val` bigint(20) unsigned NOT NULL,
  `increment` int(10) unsigned NOT NULL DEFAULT '1',
  PRIMARY KEY (`seq_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `user_roles`
-- ----------------------------
DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE `user_roles` (
  `users_id` bigint(20) NOT NULL,
  `roles_id` bigint(20) NOT NULL,
  `create_time` bigint(20) DEFAULT '0',
  KEY `users_id_index` (`users_id`),
  KEY `roles_id_index` (`roles_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `userId` bigint(20) NOT NULL,
  `username` varchar(128) NOT NULL,
  `password` varchar(64) NOT NULL,
  `salt` varchar(8) NOT NULL,
  `archived` tinyint(1) DEFAULT '0',
  `version` int(11) DEFAULT '0',
  ` create_time` bigint(20) DEFAULT '0',
  PRIMARY KEY (`userId`),
  UNIQUE KEY `username` (`username`),
  KEY `username_index` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Function structure for `currval`
-- ----------------------------
DROP FUNCTION IF EXISTS `currval`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `currval`(var_seq_name VARCHAR(64)) RETURNS bigint(20)
BEGIN
  DECLARE current BIGINT;
  SET current = 0;
  SELECT current_val INTO current
  FROM sys_seq
  WHERE seq_name = var_seq_name;
  RETURN current;
END
 ;;
delimiter ;

-- ----------------------------
--  Function structure for `nextval`
-- ----------------------------
DROP FUNCTION IF EXISTS `nextval`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `nextval`(var_seq_name VARCHAR(64)) RETURNS bigint(20)
BEGIN
   UPDATE sys_seq
   SET current_val = current_val + increment
   WHERE seq_name = var_seq_name;
   RETURN currval(var_seq_name);
END
 ;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
