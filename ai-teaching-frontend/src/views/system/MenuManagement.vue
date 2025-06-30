<template>
  <div>
    <!-- 面包屑导航 -->
    <div class="mb-6 flex items-center text-sm text-gray-500">
      <span class="cursor-pointer hover:text-blue-600" @click="$router.push('/dashboard')">首页</span>
      <i class="fas fa-chevron-right mx-2 text-xs"></i>
      <span class="cursor-pointer hover:text-blue-600" @click="$router.push('/system/permission')">权限管理</span>
      <i class="fas fa-chevron-right mx-2 text-xs"></i>
      <span class="text-gray-700">菜单分配管理</span>
    </div>

    <!-- 页面标题和操作按钮 -->
    <div class="mb-6 flex items-center justify-between">
      <div>
        <h1 class="text-2xl font-bold text-gray-900">菜单分配管理</h1>
        <p class="text-gray-600 mt-1">管理系统菜单权限的分配</p>
      </div>
      <div class="flex space-x-3">
        <button 
          @click="showAddDialog = true"
          class="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition-colors flex items-center"
        >
          <i class="fas fa-plus mr-2"></i>
          新增菜单
        </button>
        <button 
          @click="refreshMenus"
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
          <label class="block text-sm font-medium text-gray-700 mb-1">菜单名称</label>
          <input 
            v-model="searchForm.name"
            type="text" 
            placeholder="搜索菜单名称"
            class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
          />
        </div>
        <div>
          <label class="block text-sm font-medium text-gray-700 mb-1">菜单类型</label>
          <select 
            v-model="searchForm.type"
            class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
          >
            <option value="">全部类型</option>
            <option value="1">菜单</option>
            <option value="2">按钮</option>
            <option value="3">接口</option>
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
          @click="searchMenus"
          class="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition-colors"
        >
          搜索
        </button>
      </div>
    </div>

    <!-- 菜单树表格 -->
    <div class="bg-white rounded-lg shadow-sm border border-gray-200">
      <div class="px-6 py-4 border-b border-gray-200">
        <h2 class="text-lg font-semibold text-gray-900">菜单列表</h2>
      </div>
      <div class="overflow-x-auto">
        <table class="w-full">
          <thead class="bg-gray-50">
            <tr>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">菜单名称</th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">图标</th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">类型</th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">路由路径</th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">排序</th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">状态</th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">操作</th>
            </tr>
          </thead>
          <tbody class="bg-white divide-y divide-gray-200">
            <template v-for="menu in menuList" :key="menu.id">
              <tr>
                <td class="px-6 py-4 whitespace-nowrap">
                  <div class="flex items-center">
                    <button 
                      v-if="menu.children && menu.children.length > 0"
                      @click="toggleExpand(menu.id)"
                      class="mr-2 w-4 h-4 flex items-center justify-center text-gray-400 hover:text-gray-600"
                    >
                      <i :class="menu.expanded ? 'fas fa-chevron-down' : 'fas fa-chevron-right'"></i>
                    </button>
                    <span v-else class="mr-6"></span>
                    <div class="text-sm font-medium text-gray-900">{{ menu.permissionName }}</div>
                  </div>
                </td>
                <td class="px-6 py-4 whitespace-nowrap">
                  <i v-if="menu.icon" :class="menu.icon + ' text-gray-600'"></i>
                </td>
                <td class="px-6 py-4 whitespace-nowrap">
                  <span :class="getTypeClass(menu.permissionType)">
                    {{ getTypeText(menu.permissionType) }}
                  </span>
                </td>
                <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                  {{ menu.routePath || '-' }}
                </td>
                <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                  {{ menu.sort }}
                </td>
                <td class="px-6 py-4 whitespace-nowrap">
                  <span :class="menu.status === 1 ? 'bg-green-100 text-green-800' : 'bg-red-100 text-red-800'" 
                        class="inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium">
                    {{ menu.status === 1 ? '启用' : '禁用' }}
                  </span>
                </td>
                <td class="px-6 py-4 whitespace-nowrap text-sm font-medium">
                  <div class="flex space-x-2">
                    <button 
                      @click="editMenu(menu)"
                      class="text-blue-600 hover:text-blue-900"
                    >
                      编辑
                    </button>
                    <button 
                      @click="addSubMenu(menu)"
                      class="text-green-600 hover:text-green-900"
                    >
                      添加子菜单
                    </button>
                    <button 
                      @click="deleteMenu(menu)"
                      class="text-red-600 hover:text-red-900"
                    >
                      删除
                    </button>
                  </div>
                </td>
              </tr>
              
              <!-- 子菜单 -->
              <template v-if="menu.expanded && menu.children">
                <tr v-for="child in menu.children" :key="child.id" class="bg-gray-50">
                  <td class="px-6 py-4 whitespace-nowrap">
                    <div class="flex items-center">
                      <span class="mr-8 ml-4"></span>
                      <div class="text-sm font-medium text-gray-900">{{ child.permissionName }}</div>
                    </div>
                  </td>
                  <td class="px-6 py-4 whitespace-nowrap">
                    <i v-if="child.icon" :class="child.icon + ' text-gray-600'"></i>
                  </td>
                  <td class="px-6 py-4 whitespace-nowrap">
                    <span :class="getTypeClass(child.permissionType)">
                      {{ getTypeText(child.permissionType) }}
                    </span>
                  </td>
                  <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                    {{ child.routePath || '-' }}
                  </td>
                  <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                    {{ child.sort }}
                  </td>
                  <td class="px-6 py-4 whitespace-nowrap">
                    <span :class="child.status === 1 ? 'bg-green-100 text-green-800' : 'bg-red-100 text-red-800'" 
                          class="inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium">
                      {{ child.status === 1 ? '启用' : '禁用' }}
                    </span>
                  </td>
                  <td class="px-6 py-4 whitespace-nowrap text-sm font-medium">
                    <div class="flex space-x-2">
                      <button 
                        @click="editMenu(child)"
                        class="text-blue-600 hover:text-blue-900"
                      >
                        编辑
                      </button>
                      <button 
                        @click="deleteMenu(child)"
                        class="text-red-600 hover:text-red-900"
                      >
                        删除
                      </button>
                    </div>
                  </td>
                </tr>
              </template>
            </template>
          </tbody>
        </table>
      </div>
    </div>

    <!-- 新增/编辑菜单对话框 -->
    <div v-if="showAddDialog || showEditDialog" class="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
      <div class="bg-white rounded-lg p-6 w-full max-w-md">
        <h3 class="text-lg font-semibold text-gray-900 mb-4">
          {{ showAddDialog ? '新增菜单' : '编辑菜单' }}
        </h3>
        <form @submit.prevent="submitForm">
          <div class="space-y-4">
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">上级菜单</label>
              <select v-model="menuForm.parentId" class="w-full px-3 py-2 border border-gray-300 rounded-md">
                <option value="0">根菜单</option>
                <option v-for="menu in parentMenuOptions" :key="menu.id" :value="menu.id">
                  {{ menu.permissionName }}
                </option>
              </select>
            </div>
            
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">菜单名称</label>
              <input v-model="menuForm.permissionName" type="text" class="w-full px-3 py-2 border border-gray-300 rounded-md" required />
            </div>
            
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">权限编码</label>
              <input v-model="menuForm.permissionCode" type="text" class="w-full px-3 py-2 border border-gray-300 rounded-md" required />
            </div>
            
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">菜单类型</label>
              <select v-model="menuForm.permissionType" class="w-full px-3 py-2 border border-gray-300 rounded-md">
                <option value="1">菜单</option>
                <option value="2">按钮</option>
                <option value="3">接口</option>
              </select>
            </div>
            
            <div v-if="menuForm.permissionType === '1'">
              <label class="block text-sm font-medium text-gray-700 mb-1">路由路径</label>
              <input v-model="menuForm.routePath" type="text" class="w-full px-3 py-2 border border-gray-300 rounded-md" />
            </div>
            
            <div v-if="menuForm.permissionType === '1'">
              <label class="block text-sm font-medium text-gray-700 mb-1">组件路径</label>
              <input v-model="menuForm.component" type="text" class="w-full px-3 py-2 border border-gray-300 rounded-md" />
            </div>
            
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">图标</label>
              <input v-model="menuForm.icon" type="text" class="w-full px-3 py-2 border border-gray-300 rounded-md" placeholder="如: fas fa-home" />
            </div>
            
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">排序</label>
              <input v-model="menuForm.sort" type="number" class="w-full px-3 py-2 border border-gray-300 rounded-md" />
            </div>
            
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">状态</label>
              <select v-model="menuForm.status" class="w-full px-3 py-2 border border-gray-300 rounded-md">
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
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue';

// 状态管理
const showAddDialog = ref(false);
const showEditDialog = ref(false);
const menuList = ref([]);
const parentMenuOptions = ref([]);

// 搜索表单
const searchForm = reactive({
  name: '',
  type: '',
  status: ''
});

// 菜单表单
const menuForm = reactive({
  id: null,
  parentId: 0,
  permissionName: '',
  permissionCode: '',
  permissionType: '1',
  routePath: '',
  component: '',
  icon: '',
  sort: 0,
  status: 1
});

// 模拟菜单数据
const mockMenuData = ref([
  {
    id: 1,
    parentId: 0,
    permissionName: '首页',
    permissionCode: 'dashboard',
    permissionType: 1,
    routePath: '/dashboard',
    component: 'Home',
    icon: 'fas fa-home',
    sort: 1,
    status: 1,
    expanded: false,
    children: []
  },
  {
    id: 2,
    parentId: 0,
    permissionName: '教学管理',
    permissionCode: 'teaching',
    permissionType: 1,
    routePath: '/teaching',
    component: null,
    icon: 'fas fa-chalkboard-teacher',
    sort: 2,
    status: 1,
    expanded: false,
    children: [
      {
        id: 6,
        parentId: 2,
        permissionName: '我的课程',
        permissionCode: 'teaching:course',
        permissionType: 1,
        routePath: '/teaching/course',
        component: 'TeachingCourse',
        icon: 'fas fa-book-open',
        sort: 1,
        status: 1
      },
      {
        id: 7,
        parentId: 2,
        permissionName: '作业管理',
        permissionCode: 'teaching:homework',
        permissionType: 1,
        routePath: '/teaching/homework',
        component: 'TeachingHomework',
        icon: 'fas fa-tasks',
        sort: 2,
        status: 1
      }
    ]
  },
  {
    id: 5,
    parentId: 0,
    permissionName: '系统管理',
    permissionCode: 'system',
    permissionType: 1,
    routePath: '/system',
    component: null,
    icon: 'fas fa-cogs',
    sort: 5,
    status: 1,
    expanded: false,
    children: [
      {
        id: 16,
        parentId: 5,
        permissionName: '用户管理',
        permissionCode: 'system:user',
        permissionType: 1,
        routePath: '/system/user',
        component: 'SystemUser',
        icon: 'fas fa-users',
        sort: 1,
        status: 1
      }
    ]
  }
]);

// 方法
const getTypeText = (type) => {
  const types = { 1: '菜单', 2: '按钮', 3: '接口' };
  return types[type] || '';
};

const getTypeClass = (type) => {
  const classes = {
    1: 'inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium bg-blue-100 text-blue-800',
    2: 'inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium bg-green-100 text-green-800',
    3: 'inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium bg-purple-100 text-purple-800'
  };
  return classes[type] || '';
};

const toggleExpand = (menuId) => {
  const menu = findMenuById(menuList.value, menuId);
  if (menu) {
    menu.expanded = !menu.expanded;
  }
};

const findMenuById = (menus, id) => {
  for (const menu of menus) {
    if (menu.id === id) return menu;
    if (menu.children) {
      const found = findMenuById(menu.children, id);
      if (found) return found;
    }
  }
  return null;
};

const refreshMenus = () => {
  menuList.value = [...mockMenuData.value];
  parentMenuOptions.value = mockMenuData.value.filter(menu => menu.permissionType === 1);
};

const searchMenus = () => {
  // 这里应该调用API进行搜索
  console.log('搜索菜单', searchForm);
};

const editMenu = (menu) => {
  Object.assign(menuForm, menu);
  showEditDialog.value = true;
};

const addSubMenu = (parentMenu) => {
  resetForm();
  menuForm.parentId = parentMenu.id;
  showAddDialog.value = true;
};

const deleteMenu = (menu) => {
  if (confirm(`确定要删除菜单"${menu.permissionName}"吗？`)) {
    console.log('删除菜单', menu);
    // 这里应该调用删除API
  }
};

const submitForm = () => {
  if (showAddDialog.value) {
    console.log('新增菜单', menuForm);
    // 调用新增API
  } else {
    console.log('更新菜单', menuForm);
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
  Object.assign(menuForm, {
    id: null,
    parentId: 0,
    permissionName: '',
    permissionCode: '',
    permissionType: '1',
    routePath: '',
    component: '',
    icon: '',
    sort: 0,
    status: 1
  });
};

onMounted(() => {
  refreshMenus();
});
</script> 