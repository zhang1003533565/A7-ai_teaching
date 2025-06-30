<template>
  <div>
    <!-- 面包屑导航 -->
    <div class="mb-6 flex items-center text-sm text-gray-500">
      <span class="cursor-pointer hover:text-blue-600" @click="$router.push('/dashboard')">首页</span>
      <i class="fas fa-chevron-right mx-2 text-xs"></i>
      <span class="cursor-pointer hover:text-blue-600" @click="$router.push('/system/permission')">权限管理</span>
      <i class="fas fa-chevron-right mx-2 text-xs"></i>
      <span class="text-gray-700">路由分配管理</span>
    </div>

    <!-- 页面标题和操作按钮 -->
    <div class="mb-6 flex items-center justify-between">
      <div>
        <h1 class="text-2xl font-bold text-gray-900">路由分配管理</h1>
        <p class="text-gray-600 mt-1">管理系统路由和权限的关联关系</p>
      </div>
      <div class="flex space-x-3">
        <button 
          @click="showAddDialog = true"
          class="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition-colors flex items-center"
        >
          <i class="fas fa-plus mr-2"></i>
          新增路由
        </button>
        <button 
          @click="refreshRoutes"
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
          <label class="block text-sm font-medium text-gray-700 mb-1">路由名称</label>
          <input 
            v-model="searchForm.name"
            type="text" 
            placeholder="搜索路由名称"
            class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
          />
        </div>
        <div>
          <label class="block text-sm font-medium text-gray-700 mb-1">路由路径</label>
          <input 
            v-model="searchForm.path"
            type="text" 
            placeholder="搜索路由路径"
            class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
          />
        </div>
        <div>
          <label class="block text-sm font-medium text-gray-700 mb-1">HTTP方法</label>
          <select 
            v-model="searchForm.method"
            class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
          >
            <option value="">全部方法</option>
            <option value="GET">GET</option>
            <option value="POST">POST</option>
            <option value="PUT">PUT</option>
            <option value="DELETE">DELETE</option>
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
          @click="searchRoutes"
          class="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition-colors"
        >
          搜索
        </button>
      </div>
    </div>

    <!-- 路由表格 -->
    <div class="bg-white rounded-lg shadow-sm border border-gray-200">
      <div class="px-6 py-4 border-b border-gray-200">
        <h2 class="text-lg font-semibold text-gray-900">路由列表</h2>
      </div>
      <div class="overflow-x-auto">
        <table class="w-full">
          <thead class="bg-gray-50">
            <tr>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">路由名称</th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">路由路径</th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">HTTP方法</th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">组件</th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">权限编码</th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">状态</th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">创建时间</th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">操作</th>
            </tr>
          </thead>
          <tbody class="bg-white divide-y divide-gray-200">
            <tr v-for="route in routeList" :key="route.id">
              <td class="px-6 py-4 whitespace-nowrap">
                <div class="text-sm font-medium text-gray-900">{{ route.name }}</div>
              </td>
              <td class="px-6 py-4 whitespace-nowrap">
                <div class="text-sm text-gray-900">{{ route.path }}</div>
              </td>
              <td class="px-6 py-4 whitespace-nowrap">
                <span :class="getMethodClass(route.method)">
                  {{ route.method }}
                </span>
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                {{ route.component || '-' }}
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                {{ route.permissionCode || '-' }}
              </td>
              <td class="px-6 py-4 whitespace-nowrap">
                <span :class="route.status === 1 ? 'bg-green-100 text-green-800' : 'bg-red-100 text-red-800'" 
                      class="inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium">
                  {{ route.status === 1 ? '启用' : '禁用' }}
                </span>
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                {{ route.createTime }}
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm font-medium">
                <div class="flex space-x-2">
                  <button 
                    @click="editRoute(route)"
                    class="text-blue-600 hover:text-blue-900"
                  >
                    编辑
                  </button>
                  <button 
                    @click="viewPermissions(route)"
                    class="text-green-600 hover:text-green-900"
                  >
                    权限分配
                  </button>
                  <button 
                    @click="deleteRoute(route)"
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

    <!-- 新增/编辑路由对话框 -->
    <div v-if="showAddDialog || showEditDialog" class="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
      <div class="bg-white rounded-lg p-6 w-full max-w-lg">
        <h3 class="text-lg font-semibold text-gray-900 mb-4">
          {{ showAddDialog ? '新增路由' : '编辑路由' }}
        </h3>
        <form @submit.prevent="submitForm">
          <div class="space-y-4">
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">路由名称 *</label>
              <input v-model="routeForm.name" type="text" class="w-full px-3 py-2 border border-gray-300 rounded-md" required />
            </div>
            
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">路由路径 *</label>
              <input v-model="routeForm.path" type="text" class="w-full px-3 py-2 border border-gray-300 rounded-md" required />
            </div>
            
            <div class="grid grid-cols-2 gap-4">
              <div>
                <label class="block text-sm font-medium text-gray-700 mb-1">HTTP方法</label>
                <select v-model="routeForm.method" class="w-full px-3 py-2 border border-gray-300 rounded-md">
                  <option value="GET">GET</option>
                  <option value="POST">POST</option>
                  <option value="PUT">PUT</option>
                  <option value="DELETE">DELETE</option>
                  <option value="PATCH">PATCH</option>
                </select>
              </div>
              
              <div>
                <label class="block text-sm font-medium text-gray-700 mb-1">状态</label>
                <select v-model="routeForm.status" class="w-full px-3 py-2 border border-gray-300 rounded-md">
                  <option value="1">启用</option>
                  <option value="0">禁用</option>
                </select>
              </div>
            </div>
            
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">组件路径</label>
              <input v-model="routeForm.component" type="text" class="w-full px-3 py-2 border border-gray-300 rounded-md" />
            </div>
            
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">权限编码</label>
              <input v-model="routeForm.permissionCode" type="text" class="w-full px-3 py-2 border border-gray-300 rounded-md" />
            </div>
            
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">路由描述</label>
              <textarea v-model="routeForm.description" rows="2" class="w-full px-3 py-2 border border-gray-300 rounded-md"></textarea>
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

    <!-- 权限分配对话框 -->
    <div v-if="showPermissionDialog" class="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
      <div class="bg-white rounded-lg p-6 w-full max-w-md">
        <h3 class="text-lg font-semibold text-gray-900 mb-4">权限分配 - {{ selectedRoute.name }}</h3>
        <div class="space-y-3">
          <div v-for="role in roleList" :key="role.id" class="flex items-center">
            <input 
              :id="'role-' + role.id"
              v-model="selectedRoute.assignedRoles"
              :value="role.id"
              type="checkbox" 
              class="h-4 w-4 text-blue-600 rounded"
            />
            <label :for="'role-' + role.id" class="ml-2 text-sm text-gray-700">
              {{ role.roleName }}
            </label>
          </div>
        </div>
        
        <div class="mt-6 flex justify-end space-x-3">
          <button @click="showPermissionDialog = false" class="px-4 py-2 bg-gray-300 text-gray-700 rounded-lg hover:bg-gray-400">
            取消
          </button>
          <button @click="savePermissions" class="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700">
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
const showPermissionDialog = ref(false);
const routeList = ref([]);
const roleList = ref([]);
const selectedRoute = ref({});
const currentPage = ref(1);
const pageSize = ref(10);
const totalRecords = ref(0);

// 搜索表单
const searchForm = reactive({
  name: '',
  path: '',
  method: '',
  status: ''
});

// 路由表单
const routeForm = reactive({
  id: null,
  name: '',
  path: '',
  method: 'GET',
  component: '',
  permissionCode: '',
  description: '',
  status: 1
});

// 计算属性
const totalPages = computed(() => Math.ceil(totalRecords.value / pageSize.value));

// 模拟数据
const mockRouteData = ref([
  {
    id: 1,
    name: '首页',
    path: '/dashboard',
    method: 'GET',
    component: 'Home',
    permissionCode: 'dashboard:view',
    description: '系统首页',
    status: 1,
    createTime: '2024-01-15 10:30:00'
  },
  {
    id: 2,
    name: '用户列表',
    path: '/api/users',
    method: 'GET',
    component: null,
    permissionCode: 'user:list',
    description: '获取用户列表',
    status: 1,
    createTime: '2024-01-15 11:00:00'
  },
  {
    id: 3,
    name: '创建用户',
    path: '/api/users',
    method: 'POST',
    component: null,
    permissionCode: 'user:create',
    description: '创建新用户',
    status: 1,
    createTime: '2024-01-15 11:15:00'
  },
  {
    id: 4,
    name: '用户管理页面',
    path: '/system/user',
    method: 'GET',
    component: 'SystemUser',
    permissionCode: 'system:user:view',
    description: '用户管理页面',
    status: 1,
    createTime: '2024-01-15 11:30:00'
  },
  {
    id: 5,
    name: '角色管理',
    path: '/system/role',
    method: 'GET',
    component: 'SystemRole',
    permissionCode: 'system:role:view',
    description: '角色管理页面',
    status: 1,
    createTime: '2024-01-15 12:00:00'
  }
]);

const mockRoleData = ref([
  { id: 1, roleName: '超级管理员', roleCode: 'super_admin' },
  { id: 2, roleName: '系统管理员', roleCode: 'admin' },
  { id: 3, roleName: '教师', roleCode: 'teacher' },
  { id: 4, roleName: '学生', roleCode: 'student' }
]);

// 方法
const getMethodClass = (method) => {
  const classes = {
    'GET': 'inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium bg-green-100 text-green-800',
    'POST': 'inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium bg-blue-100 text-blue-800',
    'PUT': 'inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium bg-orange-100 text-orange-800',
    'DELETE': 'inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium bg-red-100 text-red-800',
    'PATCH': 'inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium bg-purple-100 text-purple-800'
  };
  return classes[method] || 'inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium bg-gray-100 text-gray-800';
};

const refreshRoutes = () => {
  routeList.value = [...mockRouteData.value];
  totalRecords.value = mockRouteData.value.length;
};

const searchRoutes = () => {
  // 这里应该调用API进行搜索
  console.log('搜索路由', searchForm);
};

const editRoute = (route) => {
  Object.assign(routeForm, route);
  showEditDialog.value = true;
};

const deleteRoute = (route) => {
  if (confirm(`确定要删除路由"${route.name}"吗？`)) {
    console.log('删除路由', route);
    // 这里应该调用删除API
  }
};

const viewPermissions = (route) => {
  selectedRoute.value = { ...route, assignedRoles: [] };
  // 这里应该从API获取已分配的角色
  showPermissionDialog.value = true;
};

const savePermissions = () => {
  console.log('保存权限分配', selectedRoute.value);
  // 这里应该调用API保存权限分配
  showPermissionDialog.value = false;
};

const submitForm = () => {
  if (showAddDialog.value) {
    console.log('新增路由', routeForm);
    // 调用新增API
  } else {
    console.log('更新路由', routeForm);
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
  Object.assign(routeForm, {
    id: null,
    name: '',
    path: '',
    method: 'GET',
    component: '',
    permissionCode: '',
    description: '',
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
  refreshRoutes();
  roleList.value = [...mockRoleData.value];
});
</script> 