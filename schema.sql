-- 用户表
CREATE TABLE `sys_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `real_name` varchar(50) DEFAULT NULL COMMENT '真实姓名',
  `dept_id` bigint DEFAULT NULL COMMENT '部门ID',
  `status` tinyint DEFAULT '1' COMMENT '状态 0-禁用 1-正常',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `token` varchar(500) DEFAULT NULL COMMENT 'JWT Token',
  `role` varchar(50) DEFAULT 'USER' COMMENT '用户角色',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 培训课程表
CREATE TABLE `training_course` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL COMMENT '课程标题',
  `content` text COMMENT '课程内容',
  `creator_id` bigint NOT NULL COMMENT '创建人ID',
  `status` tinyint DEFAULT '0' COMMENT '状态 0-待审核 1-已审核',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='培训课程表';

INSERT INTO sys_user (username, password, real_name, role, status) 
VALUES ('admin', '$2a$10$X/uMNuiis.fyO47cxbta3O4CV.z5ZBt.cNkF0G5MqJ3DhS9FWbpWK', '管理员', 'ADMIN', 1);
-- 密码是: 123456 