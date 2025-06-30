<template>
  <div>
    <!-- 面包屑导航 -->
    <div class="mb-6 flex items-center text-sm text-gray-500">
      <span class="cursor-pointer hover:text-blue-600" @click="$router.push('/dashboard')">首页</span>
      <i class="fas fa-chevron-right mx-2 text-xs"></i>
      <span class="cursor-pointer hover:text-blue-600" @click="$router.push('/system/permission')">权限管理</span>
      <i class="fas fa-chevron-right mx-2 text-xs"></i>
      <span class="text-gray-700">用户管理</span>
    </div>

    <!-- 页面标题和操作按钮 -->
    <div class="mb-6 flex items-center justify-between">
      <div>
        <h1 class="text-2xl font-bold text-gray-900">用户管理</h1>
        <p class="text-gray-600 mt-1">管理系统用户信息和角色分配</p>
      </div>
      <div class="flex space-x-3">
        <button 
          @click="showAddDialog = true"
          class="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition-colors flex items-center"
        >
          <i class="fas fa-plus mr-2"></i>
          新增用户
        </button>
        <button 
          @click="refreshUsers"
          class="px-4 py-2 bg-gray-100 text-gray-700 rounded-lg hover:bg-gray-200 transition-colors"
        >
          <i class="fas fa-sync-alt mr-2"></i>
          刷新
        </button>
      </div>
    </div>

    <!-- 搜索和筛选 -->
    <div class="mb-6 bg-white rounded-lg shadow-sm p-4 border border-gray-200">
      <div class="grid grid-cols-1 md:grid-cols-4 gap-4">
        <div>
          <label class="block text-sm font-medium text-gray-700 mb-1">用户名</label>
          <input 
            v-model="searchForm.username"
            type="text" 
            placeholder="搜索用户名"
            class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
          />
        </div>
        <div>
          <label class="block text-sm font-medium text-gray-700 mb-1">真实姓名</label>
          <input 
            v-model="searchForm.realName"
            type="text" 
            placeholder="搜索真实姓名"
            class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
          />
        </div>
        <div>
          <label class="block text-sm font-medium text-gray-700 mb-1">角色</label>
          <select 
            v-model="searchForm.roleId"
            class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
          >
            <option value="">全部角色</option>
            <option v-for="role in roleList" :key="role.id" :value="role.id">
              {{ role.roleName }}
            </option>
          </select>
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
          @click="searchUsers"
          class="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition-colors"
        >
          搜索
        </button>
      </div>
    </div>

    <!-- 用户表格 -->
    <div class="bg-white rounded-lg shadow-sm border border-gray-200">
      <div class="px-6 py-4 border-b border-gray-200">
        <h2 class="text-lg font-semibold text-gray-900">用户列表</h2>
      </div>
      <div class="overflow-x-auto">
        <table class="w-full">
          <thead class="bg-gray-50">
            <tr>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">用户信息</th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">联系方式</th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">角色</th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">状态</th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">最后登录</th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">创建时间</th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">操作</th>
            </tr>
          </thead>
          <tbody class="bg-white divide-y divide-gray-200">
            <tr v-for="user in userList" :key="user.id">
              <td class="px-6 py-4 whitespace-nowrap">
                <div class="flex items-center">
                  <div class="w-10 h-10 rounded-full bg-gradient-to-r from-blue-500 to-indigo-600 flex items-center justify-center text-white font-bold">
                    {{ user.realName ? user.realName.charAt(0) : user.username.charAt(0) }}
                  </div>
                  <div class="ml-3">
                    <div class="text-sm font-medium text-gray-900">{{ user.realName || user.username }}</div>
                    <div class="text-sm text-gray-500">@{{ user.username }}</div>
                  </div>
                </div>
              </td>
              <td class="px-6 py-4 whitespace-nowrap">
                <div class="text-sm text-gray-900">{{ user.email || '-' }}</div>
                <div class="text-sm text-gray-500">{{ user.phone || '-' }}</div>
              </td>
              <td class="px-6 py-4 whitespace-nowrap">
                <div class="flex flex-wrap gap-1">
                  <span 
                    v-for="role in user.roles" 
                    :key="role.id"
                    class="inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium bg-blue-100 text-blue-800"
                  >
                    {{ role.roleName }}
                  </span>
                  <span v-if="!user.roles || user.roles.length === 0" class="text-sm text-gray-400">未分配角色</span>
                </div>
              </td>
              <td class="px-6 py-4 whitespace-nowrap">
                <span :class="user.status === 1 ? 'bg-green-100 text-green-800' : 'bg-red-100 text-red-800'" 
                      class="inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium">
                  {{ user.status === 1 ? '启用' : '禁用' }}
                </span>
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                {{ user.lastLoginTime || '从未登录' }}
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                {{ user.createTime }}
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm font-medium">
                <div class="flex space-x-2">
                  <button 
                    @click="editUser(user)"
                    class="text-blue-600 hover:text-blue-900"
                  >
                    编辑
                  </button>
                  <button 
                    @click="assignRoles(user)"
                    class="text-green-600 hover:text-green-900"
                  >
                    分配角色
                  </button>
                  <button 
                    @click="resetPassword(user)"
                    class="text-orange-600 hover:text-orange-900"
                  >
                    重置密码
                  </button>
                  <button 
                    @click="deleteUser(user)"
                    class="text-red-600 hover:text-red-900"
                  >
                    删除
                  </button>
                </div>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      
      <!-- 分页 -->
      <div class="px-6 py-4 border-t border-gray-200 flex items-center justify-between">
        <div class="text-sm text-gray-700">
          显示 {{ (currentPage - 1) * pageSize + 1 }} 到 {{ Math.min(currentPage * pageSize, totalRecords) }} 条，共 {{ totalRecords }} 条记录
        </div>
        <div class="flex space-x-2">
          <button 
            @click="prevPage" 
            :disabled="currentPage === 1"
            class="px-3 py-1 border border-gray-300 rounded-md text-sm disabled:opacity-50"
          >
            上一页
          </button>
          <span class="px-3 py-1 text-sm">{{ currentPage }} / {{ totalPages }}</span>
          <button 
            @click="nextPage" 
            :disabled="currentPage === totalPages"
            class="px-3 py-1 border border-gray-300 rounded-md text-sm disabled:opacity-50"
          >
            下一页
          </button>
        </div>
      </div>
    </div>

    <!-- 新增/编辑用户对话框 -->
    <div v-if="showAddDialog || showEditDialog" class="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
      <div class="bg-white rounded-lg p-6 w-full max-w-lg">
        <h3 class="text-lg font-semibold text-gray-900 mb-4">
          {{ showAddDialog ? '新增用户' : '编辑用户' }}
        </h3>
        <form @submit.prevent="submitForm">
          <div class="space-y-4">
            <div class="grid grid-cols-2 gap-4">
              <div>
                <label class="block text-sm font-medium text-gray-700 mb-1">用户名 *</label>
                <input v-model="userForm.username" type="text" class="w-full px-3 py-2 border border-gray-300 rounded-md" required />
              </div>
              
              <div>
                <label class="block text-sm font-medium text-gray-700 mb-1">真实姓名 *</label>
                <input v-model="userForm.realName" type="text" class="w-full px-3 py-2 border border-gray-300 rounded-md" required />
              </div>
            </div>
            
            <div v-if="showAddDialog">
              <label class="block text-sm font-medium text-gray-700 mb-1">密码 *</label>
              <input v-model="userForm.password" type="password" class="w-full px-3 py-2 border border-gray-300 rounded-md" required />
            </div>
            
            <div class="grid grid-cols-2 gap-4">
              <div>
                <label class="block text-sm font-medium text-gray-700 mb-1">邮箱</label>
                <input v-model="userForm.email" type="email" class="w-full px-3 py-2 border border-gray-300 rounded-md" />
              </div>
              
              <div>
                <label class="block text-sm font-medium text-gray-700 mb-1">手机号</label>
                <input v-model="userForm.phone" type="tel" class="w-full px-3 py-2 border border-gray-300 rounded-md" />
              </div>
            </div>
            
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">状态</label>
              <select v-model="userForm.status" class="w-full px-3 py-2 border border-gray-300 rounded-md">
                <option value="1">启用</option>
                <option value="0">禁用</option>
              </select>
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

    <!-- 角色分配对话框 -->
    <div v-if="showRoleDialog" class="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
      <div class="bg-white rounded-lg p-6 w-full max-w-md">
        <h3 class="text-lg font-semibold text-gray-900 mb-4">角色分配 - {{ selectedUser.realName || selectedUser.username }}</h3>
        <div class="space-y-3">
          <div v-for="role in roleList" :key="role.id" class="flex items-center">
            <input 
              :id="'role-' + role.id"
              v-model="selectedUser.assignedRoles"
              :value="role.id"
              type="checkbox" 
              class="h-4 w-4 text-blue-600 rounded"
            />
            <label :for="'role-' + role.id" class="ml-2 text-sm text-gray-700">
              {{ role.roleName }}
              <span class="text-xs text-gray-500">({{ role.roleCode }})</span>
            </label>
          </div>
        </div>
        
        <div class="mt-6 flex justify-end space-x-3">
          <button @click="showRoleDialog = false" class="px-4 py-2 bg-gray-300 text-gray-700 rounded-lg hover:bg-gray-400">
            取消
          </button>
          <button @click="saveUserRoles" class="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700">
            保存
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive, computed, onMounted } from 'vue';

// 状态管理
const showAddDialog = ref(false);
const showEditDialog = ref(false);
const showRoleDialog = ref(false);
const userList = ref([]);
const roleList = ref([]);
const selectedUser = ref({});
const currentPage = ref(1);
const pageSize = ref(10);
const totalRecords = ref(0);

// 搜索表单
const searchForm = reactive({
  username: '',
  realName: '',
  roleId: '',
  status: ''
});

// 用户表单
const userForm = reactive({
  id: null,
  username: '',
  realName: '',
  password: '',
  email: '',
  phone: '',
  status: 1
});

// 计算属性
const totalPages = computed(() => Math.ceil(totalRecords.value / pageSize.value));

// 模拟用户数据
const mockUserData = ref([
  {
    id: 1,
    username: 'admin',
    realName: '系统管理员',
    email: 'admin@example.com',
    phone: '13800138000',
    status: 1,
    lastLoginTime: '2024-01-15 10:30:00',
    createTime: '2024-01-01 00:00:00',
    roles: [
      { id: 1, roleName: '超级管理员', roleCode: 'super_admin' }
    ]
  },
  {
    id: 2,
    username: 'teacher001',
    realName: '张老师',
    email: 'zhang@example.com',
    phone: '13800138001',
    status: 1,
    lastLoginTime: '2024-01-15 09:15:00',
    createTime: '2024-01-02 10:00:00',
    roles: [
      { id: 3, roleName: '教师', roleCode: 'teacher' }
    ]
  },
  {
    id: 3,
    username: 'student001',
    realName: '李小明',
    email: 'li@example.com',
    phone: '13800138002',
    status: 1,
    lastLoginTime: '2024-01-15 08:45:00',
    createTime: '2024-01-03 09:00:00',
    roles: [
      { id: 4, roleName: '学生', roleCode: 'student' }
    ]
  },
  {
    id: 4,
    username: 'teacher002',
    realName: '王老师',
    email: 'wang@example.com',
    phone: '13800138003',
    status: 1,
    lastLoginTime: '2024-01-14 16:20:00',
    createTime: '2024-01-03 14:30:00',
    roles: [
      { id: 3, roleName: '教师', roleCode: 'teacher' }
    ]
  },
  {
    id: 5,
    username: 'student002',
    realName: '张小红',
    email: 'zhanghong@example.com',
    phone: '13800138004',
    status: 0,
    lastLoginTime: '2024-01-10 11:30:00',
    createTime: '2024-01-04 10:15:00',
    roles: [
      { id: 4, roleName: '学生', roleCode: 'student' }
    ]
  }
]);

const mockRoleData = ref([
  { id: 1, roleName: '超级管理员', roleCode: 'super_admin' },
  { id: 2, roleName: '系统管理员', roleCode: 'admin' },
  { id: 3, roleName: '教师', roleCode: 'teacher' },
  { id: 4, roleName: '学生', roleCode: 'student' }
]);

// 方法
const refreshUsers = () => {
  userList.value = [...mockUserData.value];
  totalRecords.value = mockUserData.value.length;
};

const searchUsers = () => {
  console.log('搜索用户', searchForm);
  // 这里应该调用API进行搜索
};

const editUser = (user) => {
  Object.assign(userForm, user);
  delete userForm.password; // 编辑时不显示密码字段
  showEditDialog.value = true;
};

const deleteUser = (user) => {
  if (user.username === 'admin') {
    alert('超级管理员账号不能删除！');
    return;
  }
  
  if (confirm(`确定要删除用户"${user.realName || user.username}"吗？`)) {
    console.log('删除用户', user);
    // 这里应该调用删除API
  }
};

const assignRoles = (user) => {
  selectedUser.value = { 
    ...user, 
    assignedRoles: user.roles ? user.roles.map(role => role.id) : []
  };
  showRoleDialog.value = true;
};

const resetPassword = (user) => {
  if (confirm(`确定要重置用户"${user.realName || user.username}"的密码吗？新密码将设为默认密码123456。`)) {
    console.log('重置密码', user);
    // 这里应该调用重置密码API
    alert('密码重置成功！新密码为：123456');
  }
};

const saveUserRoles = () => {
  console.log('保存用户角色', {
    userId: selectedUser.value.id,
    roleIds: selectedUser.value.assignedRoles
  });
  // 这里应该调用API保存用户角色
  showRoleDialog.value = false;
};

const submitForm = () => {
  if (showAddDialog.value) {
    console.log('新增用户', userForm);
    // 调用新增API
  } else {
    console.log('更新用户', userForm);
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
  Object.assign(userForm, {
    id: null,
    username: '',
    realName: '',
    password: '',
    email: '',
    phone: '',
    status: 1
  });
};

const prevPage = () => {
  if (currentPage.value > 1) {
    currentPage.value--;
  }
};

const nextPage = () => {
  if (currentPage.value < totalPages.value) {
    currentPage.value++;
  }
};

onMounted(() => {
  refreshUsers();
  roleList.value = [...mockRoleData.value];
});
</script> 