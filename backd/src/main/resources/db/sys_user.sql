DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `real_name` varchar(50) DEFAULT NULL COMMENT '真实姓名',
  `avatar` varchar(200) DEFAULT NULL COMMENT '头像',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `status` tinyint NOT NULL DEFAULT '1' COMMENT '状态 0:禁用 1:启用',
  `role` varchar(50) NOT NULL DEFAULT 'user' COMMENT '角色',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除 0:未删除 1:已删除',
  `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `school` varchar(100) DEFAULT NULL COMMENT '学校',
  `college` varchar(100) DEFAULT NULL COMMENT '学院',
  `major` varchar(100) DEFAULT NULL COMMENT '专业',
  `class_name` varchar(100) DEFAULT NULL COMMENT '班级',
  `student_number` varchar(50) DEFAULT NULL COMMENT '学号',
  `age` int DEFAULT NULL COMMENT '年龄',
  `birth_date` date DEFAULT NULL COMMENT '出生日期',
  `gender` varchar(10) DEFAULT NULL COMMENT '性别',
  `id_card` varchar(20) DEFAULT NULL COMMENT '身份证号',
  `nation` varchar(50) DEFAULT NULL COMMENT '民族',
  `political_status` varchar(50) DEFAULT NULL COMMENT '政治面貌',
  `entry_year` int DEFAULT NULL COMMENT '入学年份',
  `grade` int DEFAULT NULL COMMENT '年级',
  `address` varchar(255) DEFAULT NULL COMMENT '家庭住址',
  `emergency_contact` varchar(100) DEFAULT NULL COMMENT '紧急联系人',
  `emergency_phone` varchar(20) DEFAULT NULL COMMENT '紧急联系人电话',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_username_not_deleted_time` (`username`,`is_deleted`,`delete_time`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表'
-- 插入一个测试管理员账号
INSERT INTO `sys_user` (`username`, `password`, `real_name`, `role`, `status`)
VALUES ('admin', '123456', '管理员', 'admin', 1); 