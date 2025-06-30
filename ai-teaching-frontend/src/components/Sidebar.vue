<template>
  <!-- 左侧菜单栏 -->
  <aside
    :class="[
      'bg-white shadow-sm h-[calc(100vh-4rem)] fixed left-0 overflow-y-auto transition-all duration-300 z-40',
      collapsed ? 'w-16' : 'w-64'
    ]"
  >
    <nav class="py-4">
      <!-- 首页 -->
      <div class="px-4 py-2">
        <div
          class="flex items-center text-gray-600 hover:text-blue-600 cursor-pointer transition-colors"
          :class="{ 'justify-center': collapsed }"
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
        
        <div v-for="item in group.items" :key="item.key" class="px-4 py-2">
          <!-- 有子菜单的项目 -->
          <div v-if="item.children && item.children.length > 0">
            <div
              class="flex items-center cursor-pointer transition-colors"
              :class="[
                collapsed ? 'justify-center' : 'justify-between',
                isActiveGroup(item) ? 'text-blue-600' : 'text-gray-600 hover:text-blue-600'
              ]"
              @click="toggleSubmenu(item.key)"
              :title="collapsed ? item.name : ''"
            >
              <div class="flex items-center">
                <i :class="item.icon + ' w-5 text-center'"></i>
                <span v-show="!collapsed" class="ml-3 text-sm font-medium">{{ item.name }}</span>
              </div>
              <i 
                v-show="!collapsed"
                :class="[
                  'fas fa-chevron-down transition-transform text-xs',
                  item.expanded ? '' : '-rotate-90'
                ]"
              ></i>
            </div>
            
            <!-- 子菜单 -->
            <div 
              v-show="!collapsed && item.expanded" 
              class="mt-2 ml-8 space-y-1"
            >
              <div
                v-for="child in item.children"
                :key="child.key"
                class="flex items-center cursor-pointer transition-colors py-1"
                :class="isActive(child.path) ? 'text-blue-600' : 'text-gray-500 hover:text-blue-600'"
                @click="navigateTo(child.path)"
              >
                <i :class="child.icon + ' w-4 text-center mr-2'"></i>
                <span class="text-sm">{{ child.name }}</span>
              </div>
            </div>
          </div>
          
          <!-- 无子菜单的项目 -->
          <div
            v-else
            class="flex items-center cursor-pointer transition-colors"
            :class="[
              collapsed ? 'justify-center' : '',
              isActive(item.path) ? 'text-blue-600' : 'text-gray-600 hover:text-blue-600'
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

// 菜单数据配置
const menuGroups = reactive([
  {
    key: 'teaching',
    title: '教学管理',
    items: [
      {
        key: 'courses',
        name: '我的课程',
        icon: 'fas fa-book',
        path: '/courses'
      },
      {
        key: 'homework',
        name: '作业管理',
        icon: 'fas fa-tasks',
        path: '/homework'
      },
      {
        key: 'analysis',
        name: '学情分析',
        icon: 'fas fa-chart-bar',
        path: '/analysis'
      }
    ]
  },
  {
    key: 'resource',
    title: '资源中心',
    items: [
      {
        key: 'teaching-resources',
        name: '教学资源',
        icon: 'fas fa-file-alt',
        path: '/resources/teaching'
      },
      {
        key: 'question-bank',
        name: '题库管理',
        icon: 'fas fa-question-circle',
        path: '/resources/questions'
      }
    ]
  },
  {
    key: 'ai',
    title: 'AI助手',
    items: [
      {
        key: 'ai-chat',
        name: 'AI对话',
        icon: 'fas fa-robot',
        path: '/ai/chat'
      },
      {
        key: 'ai-grading',
        name: '智能批改',
        icon: 'fas fa-magic',
        path: '/ai/grading'
      },
      {
        key: 'ai-recommend',
        name: '学习推荐',
        icon: 'fas fa-lightbulb',
        path: '/ai/recommend'
      }
    ]
  },
  {
    key: 'system',
    title: '系统管理',
    items: [
      {
        key: 'permission',
        name: '权限管理',
        icon: 'fas fa-shield-alt',
        expanded: false,
        children: [
          {
            key: 'permission-overview',
            name: '管理概览',
            icon: 'fas fa-tachometer-alt',
            path: '/system/permission'
          },
          {
            key: 'menu-management',
            name: '菜单分配管理',
            icon: 'fas fa-sitemap',
            path: '/system/permission/menu'
          },
          {
            key: 'route-management',
            name: '路由分配管理',
            icon: 'fas fa-route',
            path: '/system/permission/route'
          },
          {
            key: 'role-management',
            name: '角色管理',
            icon: 'fas fa-user-tag',
            path: '/system/permission/role'
          },
          {
            key: 'user-management',
            name: '用户管理',
            icon: 'fas fa-users',
            path: '/system/permission/user'
          }
        ]
      },
      {
        key: 'user-settings',
        name: '个人设置',
        icon: 'fas fa-user-cog',
        path: '/settings/profile'
      },
      {
        key: 'system-settings',
        name: '系统设置',
        icon: 'fas fa-cog',
        path: '/settings/system'
      }
    ]
  }
])

// 方法
const navigateTo = (path) => {
  if (path && path !== activeMenuPath.value) {
    activeMenuPath.value = path  // 更新内部激活状态
    
    // 如果是权限管理的子页面，不进行路由跳转，而是发出事件让父组件处理
    if (path.startsWith('/system/permission')) {
      emit('navigate', path)
      return
    }
    
    if (path !== route.path) {
      router.push(path)
    }
    emit('navigate', path)
  }
}

const isActive = (path) => {
  if (!path) return false
  // 对于权限管理的子页面，只进行精确匹配
  if (path.startsWith('/system/permission')) {
    return activeMenuPath.value === path
  }
  // 对于其他页面，使用原来的逻辑
  return activeMenuPath.value === path || activeMenuPath.value.startsWith(path + '/')
}

const isActiveGroup = (item) => {
  if (!item.children) return false
  return item.children.some(child => isActive(child.path))
}

const toggleSubmenu = (key) => {
  // 找到对应的菜单项并切换展开状态
  menuGroups.forEach(group => {
    group.items.forEach(item => {
      if (item.key === key && item.children) {
        item.expanded = !item.expanded
      }
    })
  })
}

// 当路由变化时，自动展开相关的父菜单
const autoExpandParentMenu = () => {
  menuGroups.forEach(group => {
    group.items.forEach(item => {
      if (item.children && isActiveGroup(item)) {
        item.expanded = true
      }
    })
  })
}

// 监听路由变化
watch(route, () => {
  if (!route.path.startsWith('/system/permission')) {
    activeMenuPath.value = route.path
  }
  autoExpandParentMenu()
}, { immediate: true })

// 监听currentPath prop变化
watch(() => props.currentPath, (newPath) => {
  if (newPath) {
    activeMenuPath.value = newPath
    autoExpandParentMenu()
  }
}, { immediate: true })

// 组件挂载时自动展开相关菜单
onMounted(() => {
  autoExpandParentMenu()
})
</script>

<style scoped>
/* 菜单项激活状态样式 */
.router-link-active {
  color: #3B82F6;
}

/* 悬停效果 */
.cursor-pointer:hover {
  background-color: rgba(59, 130, 246, 0.05);
  border-radius: 0.375rem;
  margin: 0 0.5rem;
  padding-left: 0.75rem;
  padding-right: 0.75rem;
}

/* 激活状态背景 */
.text-blue-600 {
  background-color: rgba(59, 130, 246, 0.1);
  border-radius: 0.375rem;
  margin: 0 0.5rem;
  padding-left: 0.75rem;
  padding-right: 0.75rem;
}

/* 子菜单项样式 */
.ml-8 .text-blue-600 {
  background-color: rgba(59, 130, 246, 0.15);
  border-radius: 0.25rem;
  margin: 0;
  padding: 0.25rem 0.5rem;
}

/* 子菜单悬停效果 */
.ml-8 .cursor-pointer:hover {
  background-color: rgba(59, 130, 246, 0.08);
  border-radius: 0.25rem;
  margin: 0;
  padding: 0.25rem 0.5rem;
}
</style> 