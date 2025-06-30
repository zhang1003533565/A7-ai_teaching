# 角色权限管理系统

## 系统概述

本系统实现了基于 RBAC（Role-Based Access Control）的权限管理，支持：
- 多角色管理
- 动态权限分配
- 路由权限控制
- 按钮级权限控制

## 数据库设计

### 核心表结构

1. **sys_role** - 角色表
   - 存储系统角色信息
   - 支持角色启用/禁用
   - 支持角色排序

2. **sys_permission** - 权限表
   - 存储菜单、按钮、接口权限
   - 支持树形结构（父子关系）
   - 包含路由路径和组件信息

3. **sys_role_permission** - 角色权限关联表
   - 建立角色与权限的多对多关系

4. **sys_user_role** - 用户角色关联表
   - 建立用户与角色的多对多关系

### 权限类型

- **permission_type = 1**: 菜单权限（对应前端路由）
- **permission_type = 2**: 按钮权限（页面内操作权限）
- **permission_type = 3**: 接口权限（API访问权限）

## 默认数据

### 默认角色

1. **超级管理员 (SUPER_ADMIN)**: 拥有所有权限
2. **系统管理员 (ADMIN)**: 拥有大部分管理权限
3. **教师 (TEACHER)**: 拥有教学相关权限
4. **学生 (STUDENT)**: 拥有学习相关权限

### 默认权限结构

```
首页 (dashboard)
├── 教学管理 (teaching)
│   ├── 我的课程 (teaching:course)
│   ├── 作业管理 (teaching:homework)
│   ├── 考试管理 (teaching:exam)
│   └── 学情分析 (teaching:analysis)
├── 学生管理 (student)
│   ├── 学生列表 (student:list)
│   ├── 学生成绩 (student:grade)
│   └── 学习记录 (student:record)
├── 课程管理 (course)
│   ├── 课程列表 (course:list)
│   ├── 课程资源 (course:resource)
│   └── 题库管理 (course:question)
└── 系统管理 (system)
    ├── 用户管理 (system:user)
    ├── 角色管理 (system:role)
    ├── 权限管理 (system:permission)
    └── 系统设置 (system:setting)
```

## 使用方法

### 1. 数据库初始化

```sql
-- 执行主初始化脚本
SOURCE init.sql;

-- 或者单独执行角色权限脚本
SOURCE sys_role.sql;
```

### 2. 为用户分配角色

```sql
-- 为用户分配教师角色
INSERT INTO sys_user_role (user_id, role_id) VALUES (用户ID, 3);
```

### 3. 为角色分配权限

```sql
-- 为角色分配权限
INSERT INTO sys_role_permission (role_id, permission_id) VALUES (角色ID, 权限ID);
```

### 4. 新增自定义角色

```sql
-- 添加新角色
INSERT INTO sys_role (role_name, role_code, description, sort) 
VALUES ('助教', 'ASSISTANT', '助教角色', 5);

-- 为新角色分配权限
INSERT INTO sys_role_permission (role_id, permission_id) 
SELECT 角色ID, permission_id FROM sys_role_permission WHERE role_id = 3; -- 复制教师权限
```

### 5. 新增自定义权限

```sql
-- 添加新菜单权限
INSERT INTO sys_permission (parent_id, permission_name, permission_code, permission_type, route_path, component, icon, sort) 
VALUES (0, '新功能', 'new_feature', 1, '/new-feature', 'NewFeature', 'fa-star', 10);

-- 添加按钮权限
INSERT INTO sys_permission (parent_id, permission_name, permission_code, permission_type, sort) 
VALUES (上级菜单ID, '导出数据', 'new_feature:export', 2, 1);
```

## 前端集成

### 路由守卫示例

```javascript
// 前端路由守卫中检查权限
router.beforeEach((to, from, next) => {
  const userPermissions = getUserPermissions(); // 获取用户权限列表
  const requiredPermission = to.meta.permission;
  
  if (requiredPermission && !userPermissions.includes(requiredPermission)) {
    next('/403'); // 无权限访问
  } else {
    next();
  }
});
```

### 按钮权限控制

```vue
<template>
  <button v-if="hasPermission('system:user:add')" @click="addUser">
    新增用户
  </button>
</template>

<script>
export default {
  methods: {
    hasPermission(permission) {
      return this.$store.getters.permissions.includes(permission);
    }
  }
}
</script>
```

## API 接口建议

建议实现以下接口来完善权限管理：

1. `GET /api/user/permissions` - 获取当前用户权限
2. `GET /api/role/list` - 获取角色列表
3. `POST /api/role` - 新增角色
4. `PUT /api/role/{id}` - 更新角色
5. `DELETE /api/role/{id}` - 删除角色
6. `POST /api/role/{id}/permissions` - 为角色分配权限
7. `POST /api/user/{id}/roles` - 为用户分配角色

这个权限系统设计灵活，支持动态扩展，可以满足大多数Web应用的权限管理需求。 