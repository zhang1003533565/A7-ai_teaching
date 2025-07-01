<template>
  <!-- 左侧菜单栏 -->
  <aside
    :class="[
      'bg-white shadow-sm h-[calc(100vh-4rem)] fixed left-0 overflow-y-auto transition-all duration-300 ease-in-out z-40',
      collapsed ? 'w-16' : 'w-64'
    ]"
  >
    <nav class="py-4">
      <!-- 首页 -->
      <div class="px-4 py-2">
        <div
          class="flex items-center text-gray-600 hover:text-blue-600 cursor-pointer transition-all duration-200 ease-in-out rounded-lg hover:bg-blue-50"
          :class="[
            { 'justify-center': collapsed },
            isActive('/dashboard') ? 'text-blue-600 bg-blue-50' : ''
          ]"
          @click="navigateTo('/dashboard')"
        >
          <i class="fas fa-home w-5 text-center"></i>
          <span v-show="!collapsed" class="ml-3 text-sm font-medium">首页</span>
        </div>
      </div>

      <!-- 菜单分组渲染 -->
      <div v-for="group in menuGroups" :key="group.key" class="mt-2">
        <div
          v-show="!collapsed"
          class="px-4 py-2 text-xs font-semibold text-gray-500 uppercase tracking-wider"
        >
          {{ group.title }}
        </div>
        
        <div v-for="item in group.items" :key="item.key" class="px-4 py-1">
          <!-- 有子菜单的项目 -->
          <div v-if="item.children && item.children.length > 0">
            <div
              class="flex items-center cursor-pointer transition-all duration-200 ease-in-out rounded-lg hover:bg-blue-50 p-2"
              :class="[
                collapsed ? 'justify-center' : 'justify-between',
                isActiveGroup(item) ? 'text-blue-600 bg-blue-50' : 'text-gray-600 hover:text-blue-600'
              ]"
              @click="toggleSubmenu(item.key)"
              :title="collapsed ? item.name : ''"
            >
              <div class="flex items-center">
                <i :class="item.icon + ' w-5 text-center transition-transform duration-200'"></i>
                <span v-show="!collapsed" class="ml-3 text-sm font-medium">{{ item.name }}</span>
              </div>
              <i 
                v-show="!collapsed"
                :class="[
                  'fas fa-chevron-right transition-transform duration-200 ease-in-out text-xs',
                  item.expanded ? 'rotate-90' : 'rotate-0'
                ]"
              ></i>
            </div>
            
            <!-- 子菜单 -->
            <transition
              enter-active-class="transition-all duration-300 ease-out"
              leave-active-class="transition-all duration-200 ease-in"
              enter-from-class="transform opacity-0 -translate-y-4 scale-95"
              enter-to-class="transform opacity-100 translate-y-0 scale-100"
              leave-from-class="transform opacity-100 translate-y-0 scale-100"
              leave-to-class="transform opacity-0 -translate-y-4 scale-95"
            >
              <div 
                v-show="!collapsed && item.expanded" 
                class="mt-1 ml-7 space-y-1 overflow-hidden"
              >
                <div
                  v-for="child in item.children"
                  :key="child.key"
                  class="flex items-center cursor-pointer transform transition-all duration-150 ease-out rounded-lg p-2 hover:bg-blue-50 hover:translate-x-1"
                  :class="isActive(child.path) ? 'text-blue-600 bg-blue-50' : 'text-gray-500 hover:text-blue-600'"
                  @click="navigateTo(child.path)"
                >
                  <i :class="child.icon + ' w-4 text-center mr-2'"></i>
                  <span class="text-sm">{{ child.name }}</span>
                </div>
              </div>
            </transition>
          </div>
          
          <!-- 无子菜单的项目 -->
          <div
            v-else
            class="flex items-center cursor-pointer transition-all duration-200 ease-in-out rounded-lg hover:bg-blue-50 p-2"
            :class="[
              collapsed ? 'justify-center' : '',
              isActive(item.path) ? 'text-blue-600 bg-blue-50' : 'text-gray-600 hover:text-blue-600'
            ]"
            @click="navigateTo(item.path)"
            :title="collapsed ? item.name : ''"
          >
            <i :class="item.icon + ' w-5 text-center'"></i>
            <span v-show="!collapsed" class="ml-3 text-sm font-medium">{{ item.name }}</span>
          </div>
        </div>
      </div>
    </nav>
  </aside>
</template>

<script setup>
import { ref, reactive, computed, watch, onMounted } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { getUserMenuPermissions } from '@/api/permission'
import { ElMessage } from 'element-plus'

// Props
const props = defineProps({
  collapsed: {
    type: Boolean,
    default: false
  },
  currentPath: {
    type: String,
    default: ''
  }
})

// Emits
const emit = defineEmits(['navigate'])

// Router
const router = useRouter()
const route = useRoute()

// 内部状态跟踪当前激活的菜单项
const activeMenuPath = ref(route.path === '/' ? '/dashboard' : route.path)

// 菜单数据
const menuGroups = ref([])

// 方法
const navigateTo = (path) => {
  if (path && path !== activeMenuPath.value) {
    activeMenuPath.value = path  // 更新内部激活状态
    
    if (path !== route.path) {
      router.push(path)
    }
    emit('navigate', path)
  }
}

const isActive = (path) => {
  if (!path) return false
  return activeMenuPath.value === path || activeMenuPath.value.startsWith(path + '/')
}

const isActiveGroup = (item) => {
  if (!item.children) return false
  return item.children.some(child => isActive(child.path))
}

const toggleSubmenu = (key) => {
  // 关闭其他展开的菜单
  menuGroups.value.forEach(group => {
    group.items.forEach(item => {
      if (item.key !== key && item.children) {
        item.expanded = false
      }
    })
  })
  
  // 切换当前菜单的展开状态
  menuGroups.value.forEach(group => {
    group.items.forEach(item => {
      if (item.key === key && item.children) {
        item.expanded = !item.expanded
      }
    })
  })
}

// 将权限菜单数据转换为侧边栏菜单格式
const transformMenuData = (permissions) => {
  // 按照parentId和sort排序
  const sortedPermissions = [...permissions].sort((a, b) => {
    if (a.parentId === b.parentId) {
      return a.sort - b.sort;
    }
    return a.parentId - b.parentId;
  });

  // 创建一个映射来存储所有的菜单项
  const menuMap = new Map();
  
  // 首先将所有权限添加到映射中
  sortedPermissions.forEach(permission => {
    if (permission.permissionType === 1) {
      menuMap.set(permission.id, {
        id: permission.id,
        parentId: permission.parentId,
        key: permission.permissionCode,
        name: permission.permissionName,
        icon: `fas fa-${permission.icon || 'circle'}`,
        path: permission.routePath,
        sort: permission.sort,
        expanded: false,
        children: []
      });
    }
  });

  // 构建菜单树
  const menuGroups = [];
  menuMap.forEach(menu => {
    if (menu.parentId === 0) {
      // 这是一个顶级菜单
      menuGroups.push({
        key: menu.key,
        title: menu.name,
        items: [{
          ...menu,
          children: sortedPermissions
            .filter(p => p.parentId === menu.id && p.permissionType === 1)
            .map(child => ({
              key: child.permissionCode,
              name: child.permissionName,
              icon: `fas fa-${child.icon || 'circle'}`,
              path: child.routePath,
              sort: child.sort
            }))
            .sort((a, b) => a.sort - b.sort)
        }]
      });
    }
  });

  // 根据父菜单的sort字段排序
  menuGroups.sort((a, b) => {
    const aItem = a.items[0];
    const bItem = b.items[0];
    return aItem.sort - bItem.sort;
  });

  return menuGroups;
}

// 加载用户菜单权限
const loadUserMenus = async () => {
  try {
    const res = await getUserMenuPermissions()
    if (res && res.data) {
      menuGroups.value = transformMenuData(res.data)
    } else {
      ElMessage.warning('获取菜单数据格式不正确')
    }
  } catch (error) {
    console.error('获取用户菜单权限失败：', error)
    ElMessage.error('获取菜单失败：' + error.message)
  }
}

// 当路由变化时，自动展开相关的父菜单
const autoExpandParentMenu = () => {
  menuGroups.value.forEach(group => {
    group.items.forEach(item => {
      if (item.children && isActiveGroup(item)) {
        item.expanded = true
      }
    })
  })
}

// 监听路由变化
watch(route, () => {
  activeMenuPath.value = route.path
  autoExpandParentMenu()
}, { immediate: true })

// 监听currentPath prop变化
watch(() => props.currentPath, (newPath) => {
  if (newPath) {
    activeMenuPath.value = newPath
    autoExpandParentMenu()
  }
}, { immediate: true })

// 组件挂载时加载用户菜单并自动展开相关菜单
onMounted(() => {
  loadUserMenus()
  autoExpandParentMenu()
})
</script>

<style scoped>
/* 滚动条样式优化 */
aside::-webkit-scrollbar {
  width: 4px;
}

aside::-webkit-scrollbar-track {
  background: transparent;
}

aside::-webkit-scrollbar-thumb {
  background: #E5E7EB;
  border-radius: 2px;
}

aside::-webkit-scrollbar-thumb:hover {
  background: #D1D5DB;
}

/* 基础过渡效果 */
.cursor-pointer {
  transition: all 0.15s cubic-bezier(0.4, 0, 0.2, 1);
}

/* 图标旋转动画 */
.fa-chevron-right {
  transition: transform 0.2s cubic-bezier(0.4, 0, 0.2, 1);
}

/* 菜单项hover效果 */
.cursor-pointer:hover {
  transform: translateX(2px);
}

/* 激活状态过渡 */
.text-blue-600 {
  transition: background-color 0.2s ease-out;
}

/* 子菜单展开/收起动画 */
.submenu-enter-active {
  transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
  transform-origin: top;
}

.submenu-leave-active {
  transition: all 0.2s cubic-bezier(0.4, 0, 0.2, 1);
  transform-origin: top;
}

.submenu-enter-from,
.submenu-leave-to {
  opacity: 0;
  transform: scaleY(0.95) translateY(-4px);
}
</style> 