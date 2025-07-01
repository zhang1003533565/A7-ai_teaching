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
            v-model="searchForm.roleName"
            type="text" 
            placeholder="搜索角色名称"
            class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
          />
        </div>
        <div>
          <label class="block text-sm font-medium text-gray-700 mb-1">角色编码</label>
          <input 
            v-model="searchForm.roleCode"
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

    <!-- 分页 -->
    <div class="flex justify-center mt-6">
      <el-pagination
        v-model:current-page="pagination.current"
        v-model:page-size="pagination.size"
        :total="pagination.total"
        :page-sizes="[10, 20, 50, 100]"
        layout="total, sizes, prev, pager, next, jumper"
        @size-change="loadRoleList"
        @current-change="loadRoleList"
      />
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
              <input 
                v-model="roleForm.roleName" 
                type="text" 
                class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500 text-gray-900 bg-white" 
                required 
                placeholder="请输入角色名称"
              />
            </div>
            
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">角色编码 *</label>
              <input 
                v-model="roleForm.roleCode" 
                type="text" 
                class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500 text-gray-900 bg-white" 
                required 
                placeholder="请输入角色编码"
              />
            </div>
            
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">角色描述</label>
              <textarea 
                v-model="roleForm.description" 
                rows="3" 
                class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500 text-gray-900 bg-white" 
                placeholder="请输入角色描述"
              ></textarea>
            </div>
            
            <div class="grid grid-cols-2 gap-4">
              <div>
                <label class="block text-sm font-medium text-gray-700 mb-1">排序</label>
                <input 
                  v-model="roleForm.sort" 
                  type="number" 
                  class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500 text-gray-900 bg-white" 
                  placeholder="请输入排序号"
                />
              </div>
              
              <div>
                <label class="block text-sm font-medium text-gray-700 mb-1">状态</label>
                <select 
                  v-model="roleForm.status" 
                  class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500 text-gray-900 bg-white"
                >
                  <option value="1">启用</option>
                  <option value="0">禁用</option>
                </select>
              </div>
            </div>
          </div>
          
          <div class="mt-6 flex justify-end space-x-3">
            <button 
              type="button" 
              @click="cancelForm" 
              class="px-4 py-2 bg-gray-100 text-gray-700 rounded-lg hover:bg-gray-200 transition-colors"
            >
              取消
            </button>
            <button 
              type="submit" 
              class="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition-colors"
            >
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
import { createRole, updateRole, deleteRole as deleteRoleApi, getRole, getRolePage } from '@/api/role';
import { ElMessage, ElMessageBox } from 'element-plus';

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

// 分页参数
const pagination = reactive({
  current: 1,
  size: 10,
  total: 0
});

// 搜索表单
const searchForm = reactive({
  roleName: '',
  roleCode: '',
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

// 加载角色列表
const loadRoleList = async () => {
  try {
    const params = {
      current: pagination.current,
      size: pagination.size,
      roleName: searchForm.roleName,
      roleCode: searchForm.roleCode
    };
    console.log('请求参数：', params);
    const res = await getRolePage(params);
    console.log('获取到的角色列表：', res);
    if (res && res.data) {
      roleList.value = res.data.records || [];
      pagination.total = res.data.total || 0;
      pagination.current = res.data.current;
      pagination.size = res.data.size;
    } else {
      console.error('角色列表数据格式不正确:', res);
      ElMessage.warning('获取角色列表数据格式不正确');
    }
  } catch (error) {
    console.error('获取角色列表失败：', error);
    ElMessage.error('获取角色列表失败：' + error.message);
  }
};

// 刷新角色列表
const refreshRoles = () => {
  loadRoleList();
};

// 搜索角色
const searchRoles = () => {
  pagination.current = 1;
  loadRoleList();
};

// 编辑角色
const editRole = async (role) => {
  try {
    const roleDetail = await getRole(role.id);
    Object.assign(roleForm, roleDetail);
    showEditDialog.value = true;
  } catch (error) {
    ElMessage.error('获取角色详情失败：' + error.message);
  }
};

// 删除角色
const deleteRole = async (role) => {
  if (role.userCount > 0) {
    ElMessage.warning('该角色下还有用户，无法删除！');
    return;
  }
  
  try {
    await ElMessageBox.confirm(`确定要删除角色"${role.roleName}"吗？`, '提示', {
      type: 'warning'
    });
    
    await deleteRoleApi(role.id);
    ElMessage.success('删除成功');
    refreshRoles();
  } catch (error) {
    if (error !== 'cancel') {
      ElMessage.error('删除失败：' + error.message);
    }
  }
};

// 提交表单
const submitForm = async () => {
  try {
    if (showAddDialog.value) {
      await createRole(roleForm);
      ElMessage.success('新增成功');
    } else {
      await updateRole(roleForm.id, roleForm);
      ElMessage.success('更新成功');
    }
    cancelForm();
    refreshRoles();
  } catch (error) {
    ElMessage.error((showAddDialog.value ? '新增' : '更新') + '失败：' + error.message);
  }
};

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
  loadRoleList();
});
</script> 