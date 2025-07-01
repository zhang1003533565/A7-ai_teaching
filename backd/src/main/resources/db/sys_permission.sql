-- 创建权限表
DROP TABLE IF EXISTS `sys_permission`;

CREATE TABLE `sys_permission` (
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='权限表';

-- 插入基础权限数据
INSERT INTO `sys_permission` (`parent_id`, `permission_name`, `permission_code`, `permission_type`, `route_path`, `component`, `icon`, `sort`) VALUES
-- Dashboard
(0, '仪表盘', 'dashboard', 1, '/dashboard', 'Dashboard', 'dashboard', 1);

INSERT INTO `sys_permission` (`parent_id`, `permission_name`, `permission_code`, `permission_type`, `route_path`, `component`, `icon`, `sort`) VALUES
-- 权限管理
(0, '权限管理', 'permission', 1, '/dashboard/permission', 'Layout', 'lock', 2),
(2, '权限概览', 'permission:overview', 1, '/dashboard/permission/overview', 'permission/PermissionOverview', 'view', 1),
(2, '菜单管理', 'permission:menu', 1, '/dashboard/permission/menu', 'permission/PermissionMenuManagement', 'menu', 2),
(2, '路由管理', 'permission:route', 1, '/dashboard/permission/route', 'permission/PermissionRouteManagement', 'link', 3),
(2, '角色管理', 'permission:role', 1, '/dashboard/permission/role', 'permission/PermissionRoleManagement', 'peoples', 4),
(2, '用户管理', 'permission:user', 1, '/dashboard/permission/user', 'permission/PermissionUserManagement', 'user', 5);

INSERT INTO `sys_permission` (`parent_id`, `permission_name`, `permission_code`, `permission_type`, `route_path`, `component`, `icon`, `sort`) VALUES
-- 课程管理
(0, '课程管理', 'courses', 1, '/dashboard/courses', 'Layout', 'education', 3),
(8, '课程概览', 'courses:overview', 1, '/dashboard/courses/overview', 'courses/CoursesOverview', 'view', 1),
(8, '课程管理', 'courses:manage', 1, '/dashboard/courses/manage', 'courses/CoursesManage', 'manage', 2),
(8, '课程内容', 'courses:content', 1, '/dashboard/courses/content', 'courses/CoursesContent', 'documentation', 3),
(8, '课程安排', 'courses:schedule', 1, '/dashboard/courses/schedule', 'courses/CoursesSchedule', 'calendar', 4),
(8, '学生管理', 'courses:students', 1, '/dashboard/courses/students', 'courses/CoursesStudents', 'peoples', 5),
(8, '课程资源', 'courses:resources', 1, '/dashboard/courses/resources', 'courses/CoursesResources', 'folder', 6);

INSERT INTO `sys_permission` (`parent_id`, `permission_name`, `permission_code`, `permission_type`, `route_path`, `component`, `icon`, `sort`) VALUES
-- 作业管理
(0, '作业管理', 'homework', 1, '/dashboard/homework', 'Layout', 'clipboard', 4),
(15, '作业概览', 'homework:overview', 1, '/dashboard/homework/overview', 'homework/HomeworkOverview', 'view', 1),
(15, '作业发布', 'homework:publish', 1, '/dashboard/homework/publish', 'homework/HomeworkPublish', 'publish', 2),
(15, '作业批改', 'homework:grading', 1, '/dashboard/homework/grading', 'homework/HomeworkGrading', 'edit', 3),
(15, '成绩管理', 'homework:grades', 1, '/dashboard/homework/grades', 'homework/HomeworkGrades', 'grade', 4),
(15, '作业提交', 'homework:submissions', 1, '/dashboard/homework/submissions', 'homework/HomeworkSubmissions', 'submit', 5),
(15, '作业模板', 'homework:templates', 1, '/dashboard/homework/templates', 'homework/HomeworkTemplates', 'template', 6);

INSERT INTO `sys_permission` (`parent_id`, `permission_name`, `permission_code`, `permission_type`, `route_path`, `component`, `icon`, `sort`) VALUES
-- AI功能
(0, 'AI功能', 'ai', 1, '/dashboard/ai', 'Layout', 'robot', 5),
(22, 'AI助教', 'ai:assistant', 1, '/dashboard/ai/assistant', 'ai/AIChatAssistant', 'service', 1),
(22, '智能问答', 'ai:qa', 1, '/dashboard/ai/qa', 'ai/AIChatQA', 'question', 2),
(22, '智能写作', 'ai:writing', 1, '/dashboard/ai/writing', 'ai/AIChatWriting', 'edit', 3),
(22, '智能分析', 'ai:analysis', 1, '/dashboard/ai/analysis', 'ai/AIChatAnalysis', 'chart', 4),
(22, '对话历史', 'ai:history', 1, '/dashboard/ai/history', 'ai/AIChatHistory', 'history', 5);

INSERT INTO `sys_permission` (`parent_id`, `permission_name`, `permission_code`, `permission_type`, `route_path`, `component`, `icon`, `sort`) VALUES
-- AI批改
(22, 'AI批改', 'ai:grading', 1, '/dashboard/ai/grading', 'Layout', 'edit', 6),
(28, '批改概览', 'ai:grading:overview', 1, '/dashboard/ai/grading/overview', 'ai/grading/GradingOverview', 'view', 1),
(28, '作业批改', 'ai:grading:homework', 1, '/dashboard/ai/grading/homework', 'ai/grading/GradingHomework', 'homework', 2),
(28, '试卷批改', 'ai:grading:exam', 1, '/dashboard/ai/grading/exam', 'ai/grading/GradingExam', 'exam', 3),
(28, '代码批改', 'ai:grading:code', 1, '/dashboard/ai/grading/code', 'ai/grading/GradingCode', 'code', 4),
(28, '批改模板', 'ai:grading:template', 1, '/dashboard/ai/grading/template', 'ai/grading/GradingTemplate', 'template', 5);

INSERT INTO `sys_permission` (`parent_id`, `permission_name`, `permission_code`, `permission_type`, `route_path`, `component`, `icon`, `sort`) VALUES
-- 学习推荐
(22, '学习推荐', 'ai:recommend', 1, '/dashboard/ai/recommend', 'Layout', 'star', 7),
(34, '推荐概览', 'ai:recommend:overview', 1, '/dashboard/ai/recommend/overview', 'ai/recommend/RecommendOverview', 'view', 1),
(34, '课程推荐', 'ai:recommend:course', 1, '/dashboard/ai/recommend/course', 'ai/recommend/RecommendCourse', 'course', 2),
(34, '资源推荐', 'ai:recommend:resource', 1, '/dashboard/ai/recommend/resource', 'ai/recommend/RecommendResource', 'resource', 3),
(34, '练习推荐', 'ai:recommend:exercise', 1, '/dashboard/ai/recommend/exercise', 'ai/recommend/RecommendExercise', 'exercise', 4),
(34, '学习计划', 'ai:recommend:plan', 1, '/dashboard/ai/recommend/plan', 'ai/recommend/RecommendPlan', 'plan', 5);

INSERT INTO `sys_permission` (`parent_id`, `permission_name`, `permission_code`, `permission_type`, `route_path`, `component`, `icon`, `sort`) VALUES
-- 学情分析
(0, '学情分析', 'analysis', 1, '/dashboard/analysis', 'Layout', 'chart', 6),
(40, '分析概览', 'analysis:overview', 1, '/dashboard/analysis/overview', 'analysis/AnalysisOverview', 'view', 1),
(40, '学习进度', 'analysis:progress', 1, '/dashboard/analysis/progress', 'analysis/LearningProgress', 'process', 2),
(40, '表现分析', 'analysis:performance', 1, '/dashboard/analysis/performance', 'analysis/PerformanceAnalysis', 'performance', 3),
(40, '参与度分析', 'analysis:engagement', 1, '/dashboard/analysis/engagement', 'analysis/EngagementAnalysis', 'engagement', 4),
(40, '知识图谱', 'analysis:knowledge', 1, '/dashboard/analysis/knowledge', 'analysis/KnowledgeMap', 'map', 5),
(40, '学习报告', 'analysis:report', 1, '/dashboard/analysis/report', 'analysis/LearningReport', 'report', 6);

INSERT INTO `sys_permission` (`parent_id`, `permission_name`, `permission_code`, `permission_type`, `route_path`, `component`, `icon`, `sort`) VALUES
-- 教学资源
(0, '教学资源', 'resources', 1, '/dashboard/resources', 'Layout', 'folder', 7),
(47, '资源概览', 'resources:overview', 1, '/dashboard/resources/overview', 'resources/ResourcesOverview', 'view', 1),
(47, '教材资源', 'resources:materials', 1, '/dashboard/resources/materials', 'resources/ResourcesMaterials', 'book', 2),
(47, '视频资源', 'resources:videos', 1, '/dashboard/resources/videos', 'resources/ResourcesVideos', 'video', 3),
(47, '文档资源', 'resources:documents', 1, '/dashboard/resources/documents', 'resources/ResourcesDocuments', 'document', 4),
(47, '资源模板', 'resources:templates', 1, '/dashboard/resources/templates', 'resources/ResourcesTemplates', 'template', 5),
(47, '共享资源', 'resources:shared', 1, '/dashboard/resources/shared', 'resources/ResourcesShared', 'share', 6);

INSERT INTO `sys_permission` (`parent_id`, `permission_name`, `permission_code`, `permission_type`, `route_path`, `component`, `icon`, `sort`) VALUES
-- 题库管理
(0, '题库管理', 'questions', 1, '/dashboard/questions', 'Layout', 'question', 8),
(54, '题库概览', 'questions:overview', 1, '/dashboard/questions/overview', 'questions/QuestionsOverview', 'view', 1),
(54, '题库管理', 'questions:bank', 1, '/dashboard/questions/bank', 'questions/QuestionsBank', 'database', 2),
(54, '分类管理', 'questions:category', 1, '/dashboard/questions/category', 'questions/QuestionsCategory', 'category', 3),
(54, '标签管理', 'questions:tags', 1, '/dashboard/questions/tags', 'questions/QuestionsTags', 'tag', 4),
(54, '题库导入', 'questions:import', 1, '/dashboard/questions/import', 'questions/QuestionsImport', 'import', 5),
(54, '题库导出', 'questions:export', 1, '/dashboard/questions/export', 'questions/QuestionsExport', 'export', 6);

INSERT INTO `sys_permission` (`parent_id`, `permission_name`, `permission_code`, `permission_type`, `route_path`, `component`, `icon`, `sort`) VALUES
-- 个人设置
(0, '个人设置', 'personal', 1, '/dashboard/personal', 'Layout', 'user', 9),
(61, '个人资料', 'personal:profile', 1, '/dashboard/personal/profile', 'personal/PersonalProfileSettings', 'profile', 1),
(61, '安全设置', 'personal:security', 1, '/dashboard/personal/security', 'personal/PersonalSecuritySettings', 'security', 2),
(61, '通知设置', 'personal:notification', 1, '/dashboard/personal/notification', 'personal/PersonalNotificationSettings', 'bell', 3),
(61, '偏好设置', 'personal:preference', 1, '/dashboard/personal/preference', 'personal/PersonalPreferenceSettings', 'setting', 4),
(61, '账号设置', 'personal:account', 1, '/dashboard/personal/account', 'personal/PersonalAccountSettings', 'user', 5);

INSERT INTO `sys_permission` (`parent_id`, `permission_name`, `permission_code`, `permission_type`, `route_path`, `component`, `icon`, `sort`) VALUES
-- 系统设置
(0, '系统设置', 'system', 1, '/dashboard/system-settings', 'Layout', 'setting', 10),
(67, '基础设置', 'system:basic', 1, '/dashboard/system-settings/basic', 'system-settings/BasicSettings', 'setting', 1),
(67, '邮件设置', 'system:email', 1, '/dashboard/system-settings/email', 'system-settings/EmailSettings', 'email', 2),
(67, '安全设置', 'system:security', 1, '/dashboard/system-settings/security', 'system-settings/SystemSecuritySettings', 'security', 3),
(67, '备份设置', 'system:backup', 1, '/dashboard/system-settings/backup', 'system-settings/BackupSettings', 'backup', 4),
(67, '日志设置', 'system:logs', 1, '/dashboard/system-settings/logs', 'system-settings/LogsSettings', 'log', 5),
(67, '系统信息', 'system:info', 1, '/dashboard/system-settings/info', 'system-settings/SystemInfo', 'info', 6);