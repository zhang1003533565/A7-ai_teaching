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
          @click="handleAdd"
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
            class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500 text-gray-900"
          />
        </div>
        <div>
          <label class="block text-sm font-medium text-gray-700 mb-1">真实姓名</label>
          <input 
            v-model="searchForm.realName"
            type="text" 
            placeholder="搜索真实姓名"
            class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500 text-gray-900"
          />
        </div>
        <div>
          <label class="block text-sm font-medium text-gray-700 mb-1">角色</label>
          <select 
            v-model="searchForm.roleId"
            class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500 text-gray-900"
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
            class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500 text-gray-900"
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
                    @click="handleEdit(user)"
                    class="bg-blue-100 text-blue-700 px-2 py-1 rounded-md hover:bg-blue-200 transition-colors flex items-center"
                  >
                    <i class="fas fa-edit mr-1"></i>
                    编辑
                  </button>
                  <button 
                    @click="resetPassword(user)"
                    class="bg-[#FFF3E0] text-[#E65100] px-2 py-1 rounded-md hover:bg-[#FFE0B2] transition-colors flex items-center"
                    style="background-color: #FFF3E0; color: #E65100;"
                  >
                    <i class="fas fa-key mr-1"></i>
                    重置密码
                  </button>
                  <button 
                    @click="handleDelete(user)"
                    class="bg-red-100 text-red-700 px-2 py-1 rounded-md hover:bg-red-200 transition-colors flex items-center"
                  >
                    <i class="fas fa-trash-alt mr-1"></i>
                    删除
                  </button>
                </div>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      
      <!-- 分页 -->
      <div class="px-6 py-4 border-t border-gray-200">
        <el-pagination
          v-model:current-page="currentPage"
          v-model:page-size="pageSize"
          :page-sizes="[10, 20, 50, 100]"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handlePageChange"
          layout="total, sizes, prev, pager, next"
          background
          class="flex justify-end"
        />
      </div>
    </div>

    <!-- 新增/编辑用户对话框 -->
    <div v-if="dialogVisible" class="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
      <div class="bg-white rounded-lg p-6 w-full max-w-lg">
        <h3 class="text-lg font-semibold mb-4 text-gray-900">
          {{ isEdit ? '编辑用户' : '新增用户' }}
        </h3>
        <form @submit.prevent="submitForm" autocomplete="off">
          <div class="space-y-4">
            <div class="grid grid-cols-2 gap-4">
              <div>
                <label class="block text-sm font-medium text-gray-700 mb-1">用户名 *</label>
                <input 
                  v-model="userForm.username" 
                  type="text" 
                  class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500 text-gray-900" 
                  required 
                  autocomplete="off"
                />
              </div>
              
              <div>
                <label class="block text-sm font-medium text-gray-700 mb-1">真实姓名 *</label>
                <input 
                  v-model="userForm.realName" 
                  type="text" 
                  class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500 text-gray-900" 
                  required 
                  autocomplete="off"
                />
              </div>
            </div>
            
            <div v-if="!isEdit">
              <label class="block text-sm font-medium text-gray-700 mb-1">密码 *</label>
              <div class="relative">
                <input 
                  v-model="userForm.password" 
                  :type="showPassword ? 'text' : 'password'" 
                  class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500 text-gray-900" 
                  required 
                  autocomplete="new-password"
                />
                <button 
                  type="button"
                  class="absolute right-2 top-1/2 transform -translate-y-1/2 text-gray-500 hover:text-gray-700"
                  @click="showPassword = !showPassword"
                >
                  <i :class="showPassword ? 'fas fa-eye-slash' : 'fas fa-eye'"></i>
                </button>
              </div>
            </div>
            
            <div class="grid grid-cols-2 gap-4">
              <div>
                <label class="block text-sm font-medium text-gray-700 mb-1">邮箱</label>
                <input 
                  v-model="userForm.email" 
                  type="email" 
                  class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500 text-gray-900" 
                  autocomplete="off"
                />
              </div>
              
              <div>
                <label class="block text-sm font-medium text-gray-700 mb-1">手机号</label>
                <input 
                  v-model="userForm.phone" 
                  type="tel" 
                  class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500 text-gray-900" 
                  autocomplete="off"
                />
              </div>
            </div>
            
            <!-- 添加角色选择 -->
            <div class="mb-4">
              <label class="block text-sm font-medium text-gray-700 mb-1">
                用户角色 *
              </label>
              <select 
                v-model="userForm.roleIds" 
                multiple
                class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500 text-gray-900"
                required
              >
                <option v-for="role in roleOptions" :key="role.id" :value="role.id">
                  {{ role.roleName }}
                </option>
              </select>
            </div>

            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">状态</label>
              <select 
                v-model="userForm.status" 
                class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500 text-gray-900"
              >
                <option value="1">启用</option>
                <option value="0">禁用</option>
              </select>
            </div>
          </div>
          
          <div class="mt-6 flex justify-end space-x-3">
            <button 
              type="button" 
              @click="handleCancel" 
              class="px-4 py-2 bg-gray-300 text-gray-700 rounded-lg hover:bg-gray-400 focus:outline-none"
            >
              取消
            </button>
            <button 
              type="submit" 
              class="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 focus:outline-none"
            >
              {{ isEdit ? '保存' : '新增' }}
            </button>
          </div>
        </form>
      </div>
    </div>

    <!-- 添加loading状态 -->
    <div v-if="loading" class="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
      <div class="animate-spin rounded-full h-32 w-32 border-t-2 border-b-2 border-blue-500"></div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive, computed, onMounted } from 'vue';
import { addUser, updateUser, deleteUser as deleteUserApi, getUser as getUserApi, listUsers as listUsersApi, getUserPage, resetPassword as resetUserPassword } from '@/api/user';
import { getRolePage } from '@/api/role';
import { ElMessage, ElMessageBox } from 'element-plus';

// 状态管理
const dialogVisible = ref(false);
const isEdit = ref(false);
const userList = ref([]);
const roleList = ref([]);
const currentPage = ref(1);
const pageSize = ref(10);
const total = ref(0);
const loading = ref(false);

// 搜索表单
const searchForm = reactive({
  username: '',
  realName: '',
  roleId: '',
  status: ''
});

// 用户表单
const userForm = ref({
  username: '',
  password: '',
  realName: '',
  email: '',
  phone: '',
  status: '1',
  roleIds: []
});

// 计算属性
const totalPages = computed(() => Math.ceil(total.value / pageSize.value));

// 添加密码显示/隐藏状态
const showPassword = ref(false);

// 角色选项
const roleOptions = ref([]);

// 方法
const refreshUsers = async () => {
  try {
    loading.value = true;
    const response = await getUserPage({
      current: currentPage.value,
      size: pageSize.value
    });
    if (response && response.data && response.data.records) {
      userList.value = response.data.records;
      total.value = response.data.total;
    } else {
      console.error('用户数据格式不正确:', response);
      ElMessage.warning('获取用户列表数据格式不正确');
    }
  } catch (error) {
    console.error('获取用户列表失败:', error);
    ElMessage.error('获取用户列表失败');
  } finally {
    loading.value = false;
  }
};

const searchUsers = async () => {
  // 由于后端暂未提供搜索接口，这里先在前端进行过滤
  try {
    loading.value = true;
    const res = await listUsersApi();
    if (res && res.code === 200 && res.data) {
      let filteredUsers = res.data;
      
      if (searchForm.username) {
        filteredUsers = filteredUsers.filter(user => 
          user.username.toLowerCase().includes(searchForm.username.toLowerCase())
        );
      }
      
      if (searchForm.realName) {
        filteredUsers = filteredUsers.filter(user => 
          user.realName && user.realName.includes(searchForm.realName)
        );
      }
      
      if (searchForm.roleId) {
        filteredUsers = filteredUsers.filter(user => 
          user.roleIds && user.roleIds.includes(Number(searchForm.roleId))
        );
      }
      
      if (searchForm.status !== '') {
        filteredUsers = filteredUsers.filter(user => 
          user.status === Number(searchForm.status)
        );
      }
      
      userList.value = filteredUsers;
      total.value = filteredUsers.length;
    }
  } catch (error) {
    ElMessage.error('搜索用户失败');
    console.error('搜索用户错误:', error);
  } finally {
    loading.value = false;
  }
};

const handleAdd = () => {
  resetForm();
  dialogVisible.value = true;
  isEdit.value = false;
};

const handleEdit = (row) => {
  userForm.value = {
    ...row,
    password: '', // 编辑时不显示密码
    roleIds: row.roleIds || [] // 确保roleIds存在
  };
  dialogVisible.value = true;
  isEdit.value = true;
};

const handleDelete = async (row) => {
  try {
    await ElMessageBox.confirm('确认删除该用户吗？', '提示', {
      type: 'warning'
    });
    await deleteUserApi(row.id);
    ElMessage.success('删除成功');
    await refreshUsers();
  } catch (error) {
    if (error !== 'cancel') {
      console.error('删除用户错误:', error);
      ElMessage.error('删除失败');
    }
  }
};

const handleCancel = () => {
  dialogVisible.value = false;
  resetForm();
};

const submitForm = async () => {
  try {
    // 表单验证
    if (!userForm.value.username) {
      ElMessage.warning('请输入用户名');
      return;
    }
    if (!userForm.value.realName) {
      ElMessage.warning('请输入真实姓名');
      return;
    }
    if (!isEdit.value && !userForm.value.password) {
      ElMessage.warning('请输入密码');
      return;
    }
    if (!userForm.value.roleIds || userForm.value.roleIds.length === 0) {
      ElMessage.warning('请选择至少一个角色');
      return;
    }

    loading.value = true;
    // 创建一个普通对象来存储表单数据
    const formData = {
      id: isEdit.value ? userForm.value.id : undefined,  // 编辑时需要传id
      username: userForm.value.username,
      realName: userForm.value.realName,
      email: userForm.value.email,
      phone: userForm.value.phone,
      status: parseInt(userForm.value.status),  // 转换为数字
      roleIds: userForm.value.roleIds
    };

    // 只在新增用户时添加密码字段
    if (!isEdit.value) {
      formData.password = userForm.value.password;
    }

    if (isEdit.value) {
      await updateUser(formData);
      ElMessage.success('更新用户成功');
    } else {
      await addUser(formData);
      ElMessage.success('新增用户成功');
    }
    dialogVisible.value = false;
    await refreshUsers();
  } catch (error) {
    console.error(isEdit.value ? '更新用户错误:' : '新增用户错误:', error);
    ElMessage.error(error.response?.data?.message || (isEdit.value ? '更新用户失败' : '新增用户失败'));
  } finally {
    loading.value = false;
  }
};

const resetForm = () => {
  userForm.value = {
    username: '',
    password: '',
    realName: '',
    email: '',
    phone: '',
    status: '1',
    roleIds: []
  };
};

const handlePageChange = (page) => {
  currentPage.value = page;
  refreshUsers();
};

const handleSizeChange = (size) => {
  pageSize.value = size;
  currentPage.value = 1;
  refreshUsers();
};

// 获取角色列表
const fetchRoles = async () => {
  try {
    const response = await getRolePage({
      current: 1,
      size: 100  // 获取足够多的角色
    });
    if (response && response.data && response.data.records) {
      roleOptions.value = response.data.records;
      roleList.value = response.data.records;  // 同时更新roleList，因为搜索框也需要用到
    } else {
      console.error('角色数据格式不正确:', response);
      ElMessage.warning('获取角色列表数据格式不正确');
    }
  } catch (error) {
    console.error('获取角色列表失败:', error);
    ElMessage.error('获取角色列表失败');
  }
};

const resetPassword = async (user) => {
  try {
    await ElMessageBox.confirm(
      `确定要重置用户"${user.realName || user.username}"的密码吗？新密码将设为默认密码123456。`,
      '提示',
      {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }
    );
    
    await resetUserPassword(user.id);
    ElMessage.success('密码重置成功！新密码为：123456');
  } catch (error) {
    if (error !== 'cancel') {
      console.error('重置密码错误:', error);
      ElMessage.error('重置密码失败');
    }
  }
};

onMounted(() => {
  refreshUsers();
  fetchRoles();  // 组件加载时获取角色列表
});
</script> 