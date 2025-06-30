CREATE DATABASE IF NOT EXISTS ai_teaching DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

USE ai_teaching;

CREATE TABLE IF NOT EXISTS `sys_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `real_name` varchar(50) COMMENT '真实姓名',
  `email` varchar(100) COMMENT '邮箱',
  `phone` varchar(20) COMMENT '手机号',
  `role` varchar(20) NOT NULL COMMENT '角色：ADMIN/TEACHER/STUDENT',
  `status` tinyint(1) DEFAULT 1 COMMENT '状态：0-禁用，1-启用',
  `avatar` varchar(255) COMMENT '头像URL',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(1) DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 插入一条测试数据
INSERT INTO `sys_user` (`username`, `password`, `role`, `real_name`) 
VALUES ('admin', '123456', 'ADMIN', '管理员');

-- 导入角色权限系统
SOURCE sys_role.sql; 