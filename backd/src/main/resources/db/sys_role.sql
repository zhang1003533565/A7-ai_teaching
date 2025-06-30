-- 角色表
CREATE TABLE IF NOT EXISTS `sys_role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(50) NOT NULL COMMENT '角色名称',
  `role_code` varchar(50) NOT NULL COMMENT '角色编码',
  `description` varchar(200) DEFAULT NULL COMMENT '角色描述',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态：0-禁用，1-启用',
  `sort` int NOT NULL DEFAULT '0' COMMENT '排序',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_role_code` (`role_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- 权限表（路由权限）
CREATE TABLE IF NOT EXISTS `sys_permission` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '权限ID',
  `parent_id` bigint DEFAULT '0' COMMENT '父级权限ID，0表示根节点',
  `permission_name` varchar(50) NOT NULL COMMENT '权限名称',
  `permission_code` varchar(100) NOT NULL COMMENT '权限编码',
  `permission_type` tinyint(1) NOT NULL DEFAULT '1' COMMENT '权限类型：1-菜单，2-按钮，3-接口',
  `route_path` varchar(200) DEFAULT NULL COMMENT '路由路径',
  `component` varchar(200) DEFAULT NULL COMMENT '组件路径',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标',
  `sort` int NOT NULL DEFAULT '0' COMMENT '排序',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态：0-禁用，1-启用',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_permission_code` (`permission_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限表';

-- 角色权限关联表
CREATE TABLE IF NOT EXISTS `sys_role_permission` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `permission_id` bigint NOT NULL COMMENT '权限ID',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_role_permission` (`role_id`, `permission_id`),
  KEY `idx_role_id` (`role_id`),
  KEY `idx_permission_id` (`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色权限关联表';

-- 用户角色关联表
CREATE TABLE IF NOT EXISTS `sys_user_role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_user_role` (`user_id`, `role_id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_role_id` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关联表';

-- 插入默认角色数据
INSERT INTO `sys_role` (`role_name`, `role_code`, `description`, `sort`) VALUES
('超级管理员', 'SUPER_ADMIN', '系统超级管理员，拥有所有权限', 1),
('系统管理员', 'ADMIN', '系统管理员，拥有大部分管理权限', 2),
('教师', 'TEACHER', '教师角色，拥有教学相关权限', 3),
('学生', 'STUDENT', '学生角色，拥有学习相关权限', 4);

-- 插入默认权限数据（菜单权限）
INSERT INTO `sys_permission` (`parent_id`, `permission_name`, `permission_code`, `permission_type`, `route_path`, `component`, `icon`, `sort`) VALUES
-- 一级菜单
(0, '首页', 'dashboard', 1, '/dashboard', 'Home', 'fa-home', 1),
(0, '教学管理', 'teaching', 1, '/teaching', NULL, 'fa-chalkboard-teacher', 2),
(0, '学生管理', 'student', 1, '/student', NULL, 'fa-user-graduate', 3),
(0, '课程管理', 'course', 1, '/course', NULL, 'fa-book', 4),
(0, '系统管理', 'system', 1, '/system', NULL, 'fa-cogs', 5),

-- 教学管理子菜单
(2, '我的课程', 'teaching:course', 1, '/teaching/course', 'TeachingCourse', 'fa-book-open', 1),
(2, '作业管理', 'teaching:homework', 1, '/teaching/homework', 'TeachingHomework', 'fa-tasks', 2),
(2, '考试管理', 'teaching:exam', 1, '/teaching/exam', 'TeachingExam', 'fa-file-alt', 3),
(2, '学情分析', 'teaching:analysis', 1, '/teaching/analysis', 'TeachingAnalysis', 'fa-chart-bar', 4),

-- 学生管理子菜单
(3, '学生列表', 'student:list', 1, '/student/list', 'StudentList', 'fa-list', 1),
(3, '学生成绩', 'student:grade', 1, '/student/grade', 'StudentGrade', 'fa-chart-line', 2),
(3, '学习记录', 'student:record', 1, '/student/record', 'StudentRecord', 'fa-history', 3),

-- 课程管理子菜单
(4, '课程列表', 'course:list', 1, '/course/list', 'CourseList', 'fa-list', 1),
(4, '课程资源', 'course:resource', 1, '/course/resource', 'CourseResource', 'fa-folder', 2),
(4, '题库管理', 'course:question', 1, '/course/question', 'CourseQuestion', 'fa-question-circle', 3),

-- 系统管理子菜单
(5, '用户管理', 'system:user', 1, '/system/user', 'SystemUser', 'fa-users', 1),
(5, '角色管理', 'system:role', 1, '/system/role', 'SystemRole', 'fa-user-tag', 2),
(5, '权限管理', 'system:permission', 1, '/system/permission', 'SystemPermission', 'fa-key', 3),
(5, '系统设置', 'system:setting', 1, '/system/setting', 'SystemSetting', 'fa-cog', 4);

-- 插入按钮权限数据
INSERT INTO `sys_permission` (`parent_id`, `permission_name`, `permission_code`, `permission_type`, `sort`) VALUES
-- 用户管理按钮权限
(16, '新增用户', 'system:user:add', 2, 1),
(16, '编辑用户', 'system:user:edit', 2, 2),
(16, '删除用户', 'system:user:delete', 2, 3),
(16, '重置密码', 'system:user:reset', 2, 4),

-- 角色管理按钮权限
(17, '新增角色', 'system:role:add', 2, 1),
(17, '编辑角色', 'system:role:edit', 2, 2),
(17, '删除角色', 'system:role:delete', 2, 3),
(17, '分配权限', 'system:role:permission', 2, 4),

-- 课程管理按钮权限
(10, '新增课程', 'course:add', 2, 1),
(10, '编辑课程', 'course:edit', 2, 2),
(10, '删除课程', 'course:delete', 2, 3),
(10, '发布课程', 'course:publish', 2, 4);

-- 为超级管理员分配所有权限
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`)
SELECT 1, id FROM `sys_permission` WHERE `is_deleted` = 0;

-- 为系统管理员分配基础权限（除了系统管理的删除权限）
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`)
SELECT 2, id FROM `sys_permission` 
WHERE `is_deleted` = 0 
AND `permission_code` NOT IN ('system:user:delete', 'system:role:delete');

-- 为教师分配教学相关权限
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`)
SELECT 3, id FROM `sys_permission` 
WHERE `is_deleted` = 0 
AND (`permission_code` LIKE 'dashboard%' 
     OR `permission_code` LIKE 'teaching%' 
     OR `permission_code` LIKE 'course%' 
     OR `permission_code` LIKE 'student:list%' 
     OR `permission_code` LIKE 'student:grade%');

-- 为学生分配学习相关权限
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`)
SELECT 4, id FROM `sys_permission` 
WHERE `is_deleted` = 0 
AND (`permission_code` LIKE 'dashboard%' 
     OR `permission_code` LIKE 'course:list%' 
     OR `permission_code` LIKE 'course:resource%');

-- 为admin用户分配超级管理员角色
INSERT INTO `sys_user_role` (`user_id`, `role_id`) 
SELECT u.id, 1 FROM `sys_user` u WHERE u.username = 'admin'; 