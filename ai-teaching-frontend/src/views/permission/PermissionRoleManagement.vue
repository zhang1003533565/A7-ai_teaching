<template>
  <div>
    <!-- 面包屑导航 -->
    <div class="mb-6 flex items-center text-sm text-gray-500">
      <span class="cursor-pointer hover:text-blue-600" @click="$router.push('/dashboard')">首页</span>
      <i class="fas fa-chevron-right mx-2 text-xs"></i>
      <span class="cursor-pointer hover:text-blue-600" @click="$router.push('/system/permission')">权限管理</span>
      <i class="fas fa-chevron-right mx-2 text-xs"></i>
      <span class="text-gray-700">角色管理</span>
    </div>

    <!-- 页面标题和操作按钮 -->
    <div class="mb-6 flex items-center justify-between">
      <div>
        <h1 class="text-2xl font-bold text-gray-900">角色管理</h1>
        <p class="text-gray-600 mt-1">管理系统角色和权限配置</p>
      </div>
      <div class="flex space-x-3">
        <button 
          @click="showAddDialog = true"
          class="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition-colors flex items-center"
        >
          <i class="fas fa-plus mr-2"></i>
          新增角色
        </button>
        <button 
          @click="refreshRoles"
          class="px-4 py-2 bg-gray-100 text-gray-700 rounded-lg hover:bg-gray-200 transition-colors"
        >
          <i class="fas fa-sync-alt mr-2"></i>
          刷新
        </button>
      </div>
    </div>

    <!-- 搜索和筛选 -->
    <div class="mb-6 bg-white rounded-lg shadow-sm p-4 border border-gray-200">
      <div class="grid grid-cols-1 md:grid-cols-3 gap-4">
        <div>
          <label class="block text-sm font-medium text-gray-700 mb-1">角色名称</label>
          <input 
            v-model="searchForm.name"
            type="text" 
            placeholder="搜索角色名称"
            class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
          />
        </div>
        <div>
          <label class="block text-sm font-medium text-gray-700 mb-1">角色编码</label>
          <input 
            v-model="searchForm.code"
            type="text" 
            placeholder="搜索角色编码"
            class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
          />
        </div>
        <div>
          <label class="block text-sm font-medium text-gray-700 mb-1">状态</label>
          <select 
            v-model="searchForm.status"
            class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
          >
            <option value="">全部状态</option>
            <option value="1">启用</option>
            <option value="0">禁用</option>
          </select>
        </div>
      </div>
      <div class="mt-4 flex justify-end">
        <button 
          @click="searchRoles"
          class="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition-colors"
        >
          搜索
        </button>
      </div>
    </div>

    <!-- 角色卡片列表 -->
    <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6 mb-6">
      <div 
        v-for="role in roleList" 
        :key="role.id"
        class="bg-white rounded-lg shadow-sm border border-gray-200 p-6 hover:shadow-md transition-shadow"
      >
        <div class="flex items-center justify-between mb-4">
          <div class="flex items-center">
            <div class="w-12 h-12 rounded-lg bg-gradient-to-r from-blue-500 to-indigo-600 flex items-center justify-center text-white font-bold text-xl">
              {{ role.roleName.charAt(0) }}
            </div>
            <div class="ml-3">
              <h3 class="text-lg font-semibold text-gray-900">{{ role.roleName }}</h3>
              <p class="text-sm text-gray-500">{{ role.roleCode }}</p>
            </div>
          </div>
          <span :class="role.status === 1 ? 'bg-green-100 text-green-800' : 'bg-red-100 text-red-800'" 
                class="inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium">
            {{ role.status === 1 ? '启用' : '禁用' }}
          </span>
        </div>
        
        <div class="mb-4">
          <p class="text-sm text-gray-600">{{ role.description || '暂无描述' }}</p>
        </div>
        
        <div class="mb-4">
          <div class="flex items-center justify-between text-sm">
            <span class="text-gray-500">用户数量</span>
            <span class="font-medium text-gray-900">{{ role.userCount || 0 }}</span>
          </div>
          <div class="flex items-center justify-between text-sm mt-1">
            <span class="text-gray-500">权限数量</span>
            <span class="font-medium text-gray-900">{{ role.permissionCount || 0 }}</span>
          </div>
          <div class="flex items-center justify-between text-sm mt-1">
            <span class="text-gray-500">创建时间</span>
            <span class="text-gray-600">{{ role.createTime }}</span>
          </div>
        </div>
        
        <div class="space-y-2">
          <div class="flex space-x-2">
            <button 
              @click="editRole(role)"
              class="flex-1 px-3 py-2 bg-blue-50 text-blue-600 rounded-md hover:bg-blue-100 transition-colors text-sm"
            >
              <i class="fas fa-edit mr-1"></i>
              编辑
            </button>
            <button 
              @click="deleteRole(role)"
              class="px-3 py-2 bg-red-50 text-red-600 rounded-md hover:bg-red-100 transition-colors text-sm"
            >
              <i class="fas fa-trash"></i>
            </button>
          </div>
          <div class="flex space-x-2">
            <button 
              @click="configMenuPermissions(role)"
              class="flex-1 px-3 py-2 bg-green-50 text-green-600 rounded-md hover:bg-green-100 transition-colors text-sm"
            >
              <i class="fas fa-sitemap mr-1"></i>
              菜单权限
            </button>
            <button 
              @click="configRoutePermissions(role)"
              class="flex-1 px-3 py-2 bg-purple-50 text-purple-600 rounded-md hover:bg-purple-100 transition-colors text-sm"
            >
              <i class="fas fa-route mr-1"></i>
              路由权限
            </button>
          </div>
        </div>
      </div>
    </div>

    <!-- 新增/编辑角色对话框 -->
    <div v-if="showAddDialog || showEditDialog" class="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
      <div class="bg-white rounded-lg p-6 w-full max-w-md">
        <h3 class="text-lg font-semibold text-gray-900 mb-4">
          {{ showAddDialog ? '新增角色' : '编辑角色' }}
        </h3>
        <form @submit.prevent="submitForm">
          <div class="space-y-4">
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">角色名称 *</label>
              <input v-model="roleForm.roleName" type="text" class="w-full px-3 py-2 border border-gray-300 rounded-md" required />
            </div>
            
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">角色编码 *</label>
              <input v-model="roleForm.roleCode" type="text" class="w-full px-3 py-2 border border-gray-300 rounded-md" required />
            </div>
            
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">角色描述</label>
              <textarea v-model="roleForm.description" rows="3" class="w-full px-3 py-2 border border-gray-300 rounded-md"></textarea>
            </div>
            
            <div class="grid grid-cols-2 gap-4">
              <div>
                <label class="block text-sm font-medium text-gray-700 mb-1">排序</label>
                <input v-model="roleForm.sort" type="number" class="w-full px-3 py-2 border border-gray-300 rounded-md" />
              </div>
              
              <div>
                <label class="block text-sm font-medium text-gray-700 mb-1">状态</label>
                <select v-model="roleForm.status" class="w-full px-3 py-2 border border-gray-300 rounded-md">
                  <option value="1">启用</option>
                  <option value="0">禁用</option>
                </select>
              </div>
            </div>
          </div>
          
          <div class="mt-6 flex justify-end space-x-3">
            <button type="button" @click="cancelForm" class="px-4 py-2 bg-gray-300 text-gray-700 rounded-lg hover:bg-gray-400">
              取消
            </button>
            <button type="submit" class="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700">
              {{ showAddDialog ? '新增' : '保存' }}
            </button>
          </div>
        </form>
      </div>
    </div>

    <!-- 菜单权限配置对话框 -->
    <div v-if="showMenuPermissionDialog" class="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
      <div class="bg-white rounded-lg p-6 w-full max-w-2xl max-h-[80vh] overflow-y-auto">
        <h3 class="text-lg font-semibold text-gray-900 mb-4">菜单权限配置 - {{ selectedRole.roleName }}</h3>
        
        <div class="space-y-4">
          <div v-for="permission in menuPermissionTree" :key="permission.id" class="border border-gray-200 rounded-lg p-4">
            <div class="flex items-center mb-3">
              <input 
                :id="'menu-permission-' + permission.id"
                v-model="selectedMenuPermissions"
                :value="permission.id"
                type="checkbox" 
                class="h-4 w-4 text-blue-600 rounded"
                @change="handleParentMenuPermissionChange(permission)"
              />
              <label :for="'menu-permission-' + permission.id" class="ml-2 text-sm font-medium text-gray-900">
                <i :class="permission.icon" class="mr-2"></i>
                {{ permission.permissionName }}
              </label>
            </div>
            
            <div v-if="permission.children && permission.children.length > 0" class="ml-6 space-y-2">
              <div v-for="child in permission.children" :key="child.id" class="flex items-center">
                <input 
                  :id="'menu-permission-' + child.id"
                  v-model="selectedMenuPermissions"
                  :value="child.id"
                  type="checkbox" 
                  class="h-4 w-4 text-blue-600 rounded"
                  @change="handleChildMenuPermissionChange(permission)"
                />
                <label :for="'menu-permission-' + child.id" class="ml-2 text-sm text-gray-700">
                  <i :class="child.icon" class="mr-2"></i>
                  {{ child.permissionName }}
                  <span :class="getPermissionTypeClass(child.permissionType)" class="ml-2">
                    {{ getPermissionTypeText(child.permissionType) }}
                  </span>
                </label>
              </div>
            </div>
          </div>
        </div>
        
        <div class="mt-6 flex justify-end space-x-3">
          <button @click="showMenuPermissionDialog = false" class="px-4 py-2 bg-gray-300 text-gray-700 rounded-lg hover:bg-gray-400">
            取消
          </button>
          <button @click="saveRoleMenuPermissions" class="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700">
            保存
          </button>
        </div>
      </div>
    </div>

    <!-- 路由权限配置对话框 -->
    <div v-if="showRoutePermissionDialog" class="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
      <div class="bg-white rounded-lg p-6 w-full max-w-4xl max-h-[80vh] overflow-y-auto">
        <h3 class="text-lg font-semibold text-gray-900 mb-4">路由权限配置 - {{ selectedRole.roleName }}</h3>
        
        <div class="space-y-6">
          <div v-for="module in routePermissionList" :key="module.id" class="border border-gray-200 rounded-lg p-4">
            <div class="flex items-center justify-between mb-4">
              <div class="flex items-center">
                <i :class="module.icon" class="text-lg text-blue-600 mr-3"></i>
                <h4 class="text-lg font-medium text-gray-900">{{ module.module }}</h4>
              </div>
              <div class="flex items-center space-x-2">
                <button 
                  @click="selectAllModuleRoutes(module)"
                  class="px-3 py-1 text-xs bg-blue-50 text-blue-600 rounded hover:bg-blue-100"
                >
                  全选
                </button>
                <button 
                  @click="clearAllModuleRoutes(module)"
                  class="px-3 py-1 text-xs bg-gray-50 text-gray-600 rounded hover:bg-gray-100"
                >
                  清空
                </button>
              </div>
            </div>
            
            <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-3">
              <div v-for="route in module.routes" :key="route.id" class="border border-gray-100 rounded-md p-3 hover:bg-gray-50">
                <div class="flex items-center mb-2">
                  <input 
                    :id="'route-permission-' + route.id"
                    v-model="selectedRoutePermissions"
                    :value="route.id"
                    type="checkbox" 
                    class="h-4 w-4 text-blue-600 rounded"
                  />
                  <label :for="'route-permission-' + route.id" class="ml-2 flex-1">
                    <div class="flex items-center justify-between">
                      <span class="text-sm font-medium text-gray-900">{{ route.name }}</span>
                      <span :class="getOperationTypeClass(route.type)">
                        {{ getOperationTypeText(route.type) }}
                      </span>
                    </div>
                    <div class="text-xs text-gray-500 mt-1">{{ route.description }}</div>
                    <div class="text-xs text-gray-400 mt-1 font-mono">{{ route.code }}</div>
                  </label>
                </div>
              </div>
            </div>
          </div>
        </div>
        
        <div class="mt-6 flex justify-end space-x-3">
          <button @click="showRoutePermissionDialog = false" class="px-4 py-2 bg-gray-300 text-gray-700 rounded-lg hover:bg-gray-400">
            取消
          </button>
          <button @click="saveRoleRoutePermissions" class="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700">
            保存
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue';

// 状态管理
const showAddDialog = ref(false);
const showEditDialog = ref(false);
const showMenuPermissionDialog = ref(false);
const showRoutePermissionDialog = ref(false);
const roleList = ref([]);
const menuPermissionTree = ref([]);
const routePermissionList = ref([]);
const selectedRole = ref({});
const selectedMenuPermissions = ref([]);
const selectedRoutePermissions = ref([]);

// 搜索表单
const searchForm = reactive({
  name: '',
  code: '',
  status: ''
});

// 角色表单
const roleForm = reactive({
  id: null,
  roleName: '',
  roleCode: '',
  description: '',
  sort: 0,
  status: 1
});

// 模拟角色数据
const mockRoleData = ref([
  {
    id: 1,
    roleName: '超级管理员',
    roleCode: 'super_admin',
    description: '拥有系统所有权限的超级管理员',
    sort: 1,
    status: 1,
    userCount: 1,
    permissionCount: 28,
    createTime: '2024-01-01 00:00:00'
  },
  {
    id: 2,
    roleName: '系统管理员',
    roleCode: 'admin',
    description: '负责系统管理和维护的管理员',
    sort: 2,
    status: 1,
    userCount: 3,
    permissionCount: 20,
    createTime: '2024-01-02 10:00:00'
  },
  {
    id: 3,
    roleName: '教师',
    roleCode: 'teacher',
    description: '教学人员，负责课程管理和教学活动',
    sort: 3,
    status: 1,
    userCount: 156,
    permissionCount: 12,
    createTime: '2024-01-03 09:00:00'
  },
  {
    id: 4,
    roleName: '学生',
    roleCode: 'student',
    description: '学习者，可以参与课程学习和作业提交',
    sort: 4,
    status: 1,
    userCount: 2489,
    permissionCount: 6,
    createTime: '2024-01-03 09:30:00'
  }
]);

// 模拟菜单权限树数据
const mockMenuPermissionTree = ref([
  {
    id: 1,
    permissionName: '首页',
    permissionCode: 'dashboard',
    permissionType: 1,
    icon: 'fas fa-home',
    children: []
  },
  {
    id: 2,
    permissionName: '教学管理',
    permissionCode: 'teaching',
    permissionType: 1,
    icon: 'fas fa-chalkboard-teacher',
    children: [
      {
        id: 6,
        permissionName: '我的课程',
        permissionCode: 'teaching:course',
        permissionType: 1,
        icon: 'fas fa-book-open'
      },
      {
        id: 7,
        permissionName: '作业管理',
        permissionCode: 'teaching:homework',
        permissionType: 1,
        icon: 'fas fa-tasks'
      },
      {
        id: 8,
        permissionName: '学情分析',
        permissionCode: 'teaching:analysis',
        permissionType: 1,
        icon: 'fas fa-chart-bar'
      }
    ]
  },
  {
    id: 3,
    permissionName: '资源中心',
    permissionCode: 'resource',
    permissionType: 1,
    icon: 'fas fa-folder-open',
    children: [
      {
        id: 9,
        permissionName: '教学资源',
        permissionCode: 'resource:teaching',
        permissionType: 1,
        icon: 'fas fa-file-alt'
      },
      {
        id: 10,
        permissionName: '题库管理',
        permissionCode: 'resource:question',
        permissionType: 1,
        icon: 'fas fa-question-circle'
      }
    ]
  },
  {
    id: 5,
    permissionName: '系统管理',
    permissionCode: 'system',
    permissionType: 1,
    icon: 'fas fa-cogs',
    children: [
      {
        id: 16,
        permissionName: '用户管理',
        permissionCode: 'system:user',
        permissionType: 1,
        icon: 'fas fa-users'
      },
      {
        id: 17,
        permissionName: '角色管理',
        permissionCode: 'system:role',
        permissionType: 1,
        icon: 'fas fa-user-tag'
      },
      {
        id: 18,
        permissionName: '权限管理',
        permissionCode: 'system:permission',
        permissionType: 1,
        icon: 'fas fa-shield-alt'
      }
    ]
  }
]);

// 模拟路由权限数据
const mockRoutePermissionList = ref([
  {
    id: 1,
    module: '用户管理',
    moduleName: 'user',
    icon: 'fas fa-users',
    routes: [
      { id: 101, name: '用户列表', code: 'user:list', type: 'view', description: '查看用户列表' },
      { id: 102, name: '新增用户', code: 'user:create', type: 'create', description: '创建新用户' },
      { id: 103, name: '编辑用户', code: 'user:update', type: 'update', description: '修改用户信息' },
      { id: 104, name: '删除用户', code: 'user:delete', type: 'delete', description: '删除用户' },
      { id: 105, name: '重置密码', code: 'user:reset', type: 'action', description: '重置用户密码' },
      { id: 106, name: '分配角色', code: 'user:assign', type: 'action', description: '为用户分配角色' }
    ]
  },
  {
    id: 2,
    module: '角色管理',
    moduleName: 'role',
    icon: 'fas fa-user-tag',
    routes: [
      { id: 201, name: '角色列表', code: 'role:list', type: 'view', description: '查看角色列表' },
      { id: 202, name: '新增角色', code: 'role:create', type: 'create', description: '创建新角色' },
      { id: 203, name: '编辑角色', code: 'role:update', type: 'update', description: '修改角色信息' },
      { id: 204, name: '删除角色', code: 'role:delete', type: 'delete', description: '删除角色' },
      { id: 205, name: '分配权限', code: 'role:permission', type: 'action', description: '为角色分配权限' }
    ]
  },
  {
    id: 3,
    module: '课程管理',
    moduleName: 'course',
    icon: 'fas fa-book-open',
    routes: [
      { id: 301, name: '课程列表', code: 'course:list', type: 'view', description: '查看课程列表' },
      { id: 302, name: '新增课程', code: 'course:create', type: 'create', description: '创建新课程' },
      { id: 303, name: '编辑课程', code: 'course:update', type: 'update', description: '修改课程信息' },
      { id: 304, name: '删除课程', code: 'course:delete', type: 'delete', description: '删除课程' },
      { id: 305, name: '课程详情', code: 'course:detail', type: 'view', description: '查看课程详情' },
      { id: 306, name: '发布课程', code: 'course:publish', type: 'action', description: '发布或下架课程' }
    ]
  },
  {
    id: 4,
    module: '作业管理',
    moduleName: 'homework',
    icon: 'fas fa-tasks',
    routes: [
      { id: 401, name: '作业列表', code: 'homework:list', type: 'view', description: '查看作业列表' },
      { id: 402, name: '布置作业', code: 'homework:create', type: 'create', description: '布置新作业' },
      { id: 403, name: '编辑作业', code: 'homework:update', type: 'update', description: '修改作业信息' },
      { id: 404, name: '删除作业', code: 'homework:delete', type: 'delete', description: '删除作业' },
      { id: 405, name: '批改作业', code: 'homework:grade', type: 'action', description: '批改学生作业' }
    ]
  },
  {
    id: 5,
    module: '资源管理',
    moduleName: 'resource',
    icon: 'fas fa-file-alt',
    routes: [
      { id: 501, name: '资源列表', code: 'resource:list', type: 'view', description: '查看教学资源' },
      { id: 502, name: '上传资源', code: 'resource:upload', type: 'create', description: '上传教学资源' },
      { id: 503, name: '编辑资源', code: 'resource:update', type: 'update', description: '修改资源信息' },
      { id: 504, name: '删除资源', code: 'resource:delete', type: 'delete', description: '删除教学资源' },
      { id: 505, name: '下载资源', code: 'resource:download', type: 'action', description: '下载教学资源' }
    ]
  }
]);

// 方法
const getPermissionTypeText = (type) => {
  const types = { 1: '菜单', 2: '按钮', 3: '接口' };
  return types[type] || '';
};

const getPermissionTypeClass = (type) => {
  const classes = {
    1: 'inline-flex items-center px-2 py-0.5 rounded text-xs font-medium bg-blue-100 text-blue-800',
    2: 'inline-flex items-center px-2 py-0.5 rounded text-xs font-medium bg-green-100 text-green-800',
    3: 'inline-flex items-center px-2 py-0.5 rounded text-xs font-medium bg-purple-100 text-purple-800'
  };
  return classes[type] || '';
};

const getOperationTypeClass = (type) => {
  const classes = {
    'view': 'inline-flex items-center px-2 py-0.5 rounded text-xs font-medium bg-blue-100 text-blue-800',
    'create': 'inline-flex items-center px-2 py-0.5 rounded text-xs font-medium bg-green-100 text-green-800',
    'update': 'inline-flex items-center px-2 py-0.5 rounded text-xs font-medium bg-orange-100 text-orange-800',
    'delete': 'inline-flex items-center px-2 py-0.5 rounded text-xs font-medium bg-red-100 text-red-800',
    'action': 'inline-flex items-center px-2 py-0.5 rounded text-xs font-medium bg-purple-100 text-purple-800'
  };
  return classes[type] || '';
};

const getOperationTypeText = (type) => {
  const types = { 
    'view': '查看', 
    'create': '新增', 
    'update': '编辑', 
    'delete': '删除', 
    'action': '操作' 
  };
  return types[type] || type;
};

const refreshRoles = () => {
  roleList.value = [...mockRoleData.value];
};

const searchRoles = () => {
  console.log('搜索角色', searchForm);
};

const editRole = (role) => {
  Object.assign(roleForm, role);
  showEditDialog.value = true;
};

const deleteRole = (role) => {
  if (role.userCount > 0) {
    alert('该角色下还有用户，无法删除！');
    return;
  }
  
  if (confirm(`确定要删除角色"${role.roleName}"吗？`)) {
    console.log('删除角色', role);
    // 这里应该调用删除API
  }
};

const configMenuPermissions = (role) => {
  selectedRole.value = { ...role };
  selectedMenuPermissions.value = [];
  // 这里应该从API获取该角色已有的菜单权限
  menuPermissionTree.value = [...mockMenuPermissionTree.value];
  showMenuPermissionDialog.value = true;
};

const configRoutePermissions = (role) => {
  selectedRole.value = { ...role };
  selectedRoutePermissions.value = [];
  // 这里应该从API获取该角色已有的路由权限
  routePermissionList.value = [...mockRoutePermissionList.value];
  showRoutePermissionDialog.value = true;
};

const handleParentMenuPermissionChange = (permission) => {
  if (selectedMenuPermissions.value.includes(permission.id)) {
    // 如果父权限被选中，自动选中所有子权限
    if (permission.children) {
      permission.children.forEach(child => {
        if (!selectedMenuPermissions.value.includes(child.id)) {
          selectedMenuPermissions.value.push(child.id);
        }
      });
    }
  } else {
    // 如果父权限被取消，自动取消所有子权限
    if (permission.children) {
      permission.children.forEach(child => {
        const index = selectedMenuPermissions.value.indexOf(child.id);
        if (index > -1) {
          selectedMenuPermissions.value.splice(index, 1);
        }
      });
    }
  }
};

const handleChildMenuPermissionChange = (parentPermission) => {
  // 检查是否所有子权限都被选中
  const allChildrenSelected = parentPermission.children.every(child => 
    selectedMenuPermissions.value.includes(child.id)
  );
  
  // 检查是否有任何子权限被选中
  const anyChildSelected = parentPermission.children.some(child => 
    selectedMenuPermissions.value.includes(child.id)
  );
  
  if (allChildrenSelected) {
    // 如果所有子权限都被选中，自动选中父权限
    if (!selectedMenuPermissions.value.includes(parentPermission.id)) {
      selectedMenuPermissions.value.push(parentPermission.id);
    }
  } else if (!anyChildSelected) {
    // 如果没有任何子权限被选中，取消父权限
    const index = selectedMenuPermissions.value.indexOf(parentPermission.id);
    if (index > -1) {
      selectedMenuPermissions.value.splice(index, 1);
    }
  }
};

const saveRoleMenuPermissions = () => {
  console.log('保存角色菜单权限', {
    roleId: selectedRole.value.id,
    menuPermissions: selectedMenuPermissions.value
  });
  // 这里应该调用API保存角色菜单权限
  showMenuPermissionDialog.value = false;
};

const saveRoleRoutePermissions = () => {
  console.log('保存角色路由权限', {
    roleId: selectedRole.value.id,
    routePermissions: selectedRoutePermissions.value
  });
  // 这里应该调用API保存角色路由权限
  showRoutePermissionDialog.value = false;
};

const selectAllModuleRoutes = (module) => {
  module.routes.forEach(route => {
    if (!selectedRoutePermissions.value.includes(route.id)) {
      selectedRoutePermissions.value.push(route.id);
    }
  });
};

const clearAllModuleRoutes = (module) => {
  module.routes.forEach(route => {
    const index = selectedRoutePermissions.value.indexOf(route.id);
    if (index > -1) {
      selectedRoutePermissions.value.splice(index, 1);
    }
  });
};

const submitForm = () => {
  if (showAddDialog.value) {
    console.log('新增角色', roleForm);
    // 调用新增API
  } else {
    console.log('更新角色', roleForm);
    // 调用更新API
  }
  cancelForm();
};

const cancelForm = () => {
  showAddDialog.value = false;
  showEditDialog.value = false;
  resetForm();
};

const resetForm = () => {
  Object.assign(roleForm, {
    id: null,
    roleName: '',
    roleCode: '',
    description: '',
    sort: 0,
    status: 1
  });
};

onMounted(() => {
  refreshRoles();
});
</script> 