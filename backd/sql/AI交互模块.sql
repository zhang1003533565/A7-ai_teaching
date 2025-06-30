-- AI问答记录表
CREATE TABLE `qa_log` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `question` text NOT NULL COMMENT '问题内容',
  `answer` text NOT NULL COMMENT 'AI回答内容',
  `type` varchar(20) NOT NULL COMMENT '类型：COURSE_QA/EXERCISE_GUIDE',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_created_at` (`created_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='AI问答记录表';