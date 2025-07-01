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
        expanded: false,
        children: [
          {
            key: 'courses-overview',
            name: '课程概览',
            icon: 'fas fa-tachometer-alt',
            path: '/dashboard/courses/overview'
          },
          {
            key: 'courses-manage',
            name: '课程管理',
            icon: 'fas fa-list',
            path: '/dashboard/courses/manage'
          },
          {
            key: 'courses-content',
            name: '课程内容',
            icon: 'fas fa-file-alt',
            path: '/dashboard/courses/content'
          },
          {
            key: 'courses-schedule',
            name: '课程安排',
            icon: 'fas fa-calendar-alt',
            path: '/dashboard/courses/schedule'
          },
          {
            key: 'courses-students',
            name: '学生管理',
            icon: 'fas fa-users',
            path: '/dashboard/courses/students'
          }
        ]
      },
      {
        key: 'homework',
        name: '作业管理',
        icon: 'fas fa-tasks',
        expanded: false,
        children: [
          {
            key: 'homework-overview',
            name: '作业概览',
            icon: 'fas fa-tachometer-alt',
            path: '/dashboard/homework/overview'
          },
          {
            key: 'homework-publish',
            name: '作业发布',
            icon: 'fas fa-plus-circle',
            path: '/dashboard/homework/publish'
          },
          {
            key: 'homework-grading',
            name: '作业批改',
            icon: 'fas fa-check-circle',
            path: '/dashboard/homework/grading'
          },
          {
            key: 'homework-grades',
            name: '成绩管理',
            icon: 'fas fa-star',
            path: '/dashboard/homework/grades'
          },
          {
            key: 'homework-statistics',
            name: '提交统计',
            icon: 'fas fa-chart-bar',
            path: '/dashboard/homework/statistics'
          },
          {
            key: 'homework-templates',
            name: '作业模板',
            icon: 'fas fa-copy',
            path: '/dashboard/homework/templates'
          }
        ]
      },
      {
        key: 'analysis',
        name: '学情分析',
        icon: 'fas fa-chart-line',
        expanded: false,
        children: [
          {
            key: 'analysis-overview',
            name: '分析概览',
            icon: 'fas fa-tachometer-alt',
            path: '/dashboard/analysis/overview'
          },
          {
            key: 'analysis-progress',
            name: '学习进度',
            icon: 'fas fa-tasks',
            path: '/dashboard/analysis/progress'
          },
          {
            key: 'analysis-performance',
            name: '成绩分析',
            icon: 'fas fa-chart-bar',
            path: '/dashboard/analysis/performance'
          },
          {
            key: 'analysis-engagement',
            name: '参与度分析',
            icon: 'fas fa-user-clock',
            path: '/dashboard/analysis/engagement'
          },
          {
            key: 'analysis-knowledge',
            name: '知识图谱',
            icon: 'fas fa-project-diagram',
            path: '/dashboard/analysis/knowledge'
          },
          {
            key: 'analysis-report',
            name: '学习报告',
            icon: 'fas fa-file-alt',
            path: '/dashboard/analysis/report'
          }
        ]
      }
    ]
  },
  {
    key: 'resource',
    title: '资源中心',
    items: [
      {
        key: 'resources',
        name: '教学资源',
        icon: 'fas fa-archive',
        expanded: false,
        children: [
          {
            key: 'resources-overview',
            name: '资源概览',
            icon: 'fas fa-tachometer-alt',
            path: '/dashboard/resources/overview'
          },
          {
            key: 'resources-materials',
            name: '教学材料',
            icon: 'fas fa-file-alt',
            path: '/dashboard/resources/materials'
          },
          {
            key: 'resources-videos',
            name: '视频资源',
            icon: 'fas fa-video',
            path: '/dashboard/resources/videos'
          },
          {
            key: 'resources-documents',
            name: '文档资料',
            icon: 'fas fa-file-pdf',
            path: '/dashboard/resources/documents'
          },
          {
            key: 'resources-templates',
            name: '资源模板',
            icon: 'fas fa-copy',
            path: '/dashboard/resources/templates'
          },
          {
            key: 'resources-shared',
            name: '共享资源',
            icon: 'fas fa-share-alt',
            path: '/dashboard/resources/shared'
          }
        ]
      },
      {
        key: 'questions',
        name: '题库管理',
        icon: 'fas fa-question-circle',
        expanded: false,
        children: [
          {
            key: 'questions-overview',
            name: '题库概览',
            icon: 'fas fa-tachometer-alt',
            path: '/dashboard/questions/overview'
          },
          {
            key: 'questions-bank',
            name: '题目管理',
            icon: 'fas fa-list',
            path: '/dashboard/questions/bank'
          },
          {
            key: 'questions-category',
            name: '分类管理',
            icon: 'fas fa-folder',
            path: '/dashboard/questions/category'
          },
          {
            key: 'questions-tags',
            name: '标签管理',
            icon: 'fas fa-tags',
            path: '/dashboard/questions/tags'
          },
          {
            key: 'questions-import',
            name: '题目导入',
            icon: 'fas fa-file-import',
            path: '/dashboard/questions/import'
          },
          {
            key: 'questions-export',
            name: '题目导出',
            icon: 'fas fa-file-export',
            path: '/dashboard/questions/export'
          }
        ]
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
        expanded: false,
        children: [
          {
            key: 'ai-chat-assistant',
            name: '智能助教',
            icon: 'fas fa-user-graduate',
            path: '/dashboard/ai/assistant'
          },
          {
            key: 'ai-chat-qa',
            name: '问答系统',
            icon: 'fas fa-question-circle',
            path: '/dashboard/ai/qa'
          },
          {
            key: 'ai-chat-writing',
            name: '写作助手',
            icon: 'fas fa-pen-fancy',
            path: '/dashboard/ai/writing'
          },
          {
            key: 'ai-chat-analysis',
            name: '解题分析',
            icon: 'fas fa-chart-line',
            path: '/dashboard/ai/analysis'
          },
          {
            key: 'ai-chat-history',
            name: '对话历史',
            icon: 'fas fa-history',
            path: '/dashboard/ai/history'
          }
        ]
      },
      {
        key: 'ai-grading',
        name: '智能批改',
        icon: 'fas fa-magic',
        expanded: false,
        children: [
          {
            key: 'ai-grading-overview',
            name: '批改概览',
            icon: 'fas fa-tachometer-alt',
            path: '/dashboard/ai/grading/overview'
          },
          {
            key: 'ai-grading-homework',
            name: '作业批改',
            icon: 'fas fa-tasks',
            path: '/dashboard/ai/grading/homework'
          },
          {
            key: 'ai-grading-exam',
            name: '试卷批改',
            icon: 'fas fa-file-alt',
            path: '/dashboard/ai/grading/exam'
          },
          {
            key: 'ai-grading-code',
            name: '代码评审',
            icon: 'fas fa-code',
            path: '/dashboard/ai/grading/code'
          },
          {
            key: 'ai-grading-template',
            name: '批改模板',
            icon: 'fas fa-copy',
            path: '/dashboard/ai/grading/template'
          }
        ]
      },
      {
        key: 'ai-recommend',
        name: '学习推荐',
        icon: 'fas fa-lightbulb',
        expanded: false,
        children: [
          {
            key: 'ai-recommend-overview',
            name: '推荐概览',
            icon: 'fas fa-tachometer-alt',
            path: '/dashboard/ai/recommend/overview'
          },
          {
            key: 'ai-recommend-course',
            name: '课程推荐',
            icon: 'fas fa-book',
            path: '/dashboard/ai/recommend/course'
          },
          {
            key: 'ai-recommend-resource',
            name: '资源推荐',
            icon: 'fas fa-file-alt',
            path: '/dashboard/ai/recommend/resource'
          },
          {
            key: 'ai-recommend-exercise',
            name: '练习推荐',
            icon: 'fas fa-tasks',
            path: '/dashboard/ai/recommend/exercise'
          },
          {
            key: 'ai-recommend-plan',
            name: '学习计划',
            icon: 'fas fa-calendar-alt',
            path: '/dashboard/ai/recommend/plan'
          }
        ]
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
            name: '权限概览',
            icon: 'fas fa-tachometer-alt',
            path: '/dashboard/permission/overview'
          },
          {
            key: 'permission-menu',
            name: '菜单管理',
            icon: 'fas fa-bars',
            path: '/dashboard/permission/menu'
          },
          {
            key: 'permission-route',
            name: '路由管理',
            icon: 'fas fa-route',
            path: '/dashboard/permission/route'
          },
          {
            key: 'permission-role',
            name: '角色管理',
            icon: 'fas fa-user-tag',
            path: '/dashboard/permission/role'
          },
          {
            key: 'permission-user',
            name: '用户管理',
            icon: 'fas fa-users-cog',
            path: '/dashboard/permission/user'
          }
        ]
      },
      {
        key: 'user-settings',
        name: '个人设置',
        icon: 'fas fa-user-cog',
        expanded: false,
        children: [
          {
            key: 'profile-settings',
            name: '基本信息',
            icon: 'fas fa-user',
            path: '/dashboard/personal/profile'
          },
          {
            key: 'security-settings',
            name: '安全设置',
            icon: 'fas fa-shield-alt',
            path: '/dashboard/personal/security'
          },
          {
            key: 'notification-settings',
            name: '通知设置',
            icon: 'fas fa-bell',
            path: '/dashboard/personal/notification'
          },
          {
            key: 'preference-settings',
            name: '偏好设置',
            icon: 'fas fa-sliders-h',
            path: '/dashboard/personal/preference'
          },
          {
            key: 'account-settings',
            name: '账户管理',
            icon: 'fas fa-cog',
            path: '/dashboard/personal/account'
          }
        ]
      },
      {
        key: 'system-settings',
        name: '系统设置',
        icon: 'fas fa-cogs',
        expanded: false,
        children: [
          {
            key: 'system-basic',
            name: '基本设置',
            icon: 'fas fa-sliders-h',
            path: '/dashboard/system-settings/basic'
          },
          {
            key: 'system-email',
            name: '邮件配置',
            icon: 'fas fa-envelope-open',
            path: '/dashboard/system-settings/email'
          },
          {
            key: 'system-security',
            name: '安全策略',
            icon: 'fas fa-shield-alt',
            path: '/dashboard/system-settings/security'
          },
          {
            key: 'system-backup',
            name: '备份恢复',
            icon: 'fas fa-database',
            path: '/dashboard/system-settings/backup'
          },
          {
            key: 'system-logs',
            name: '日志管理',
            icon: 'fas fa-file-alt',
            path: '/dashboard/system-settings/logs'
          },
          {
            key: 'system-info',
            name: '系统信息',
            icon: 'fas fa-info-circle',
            path: '/dashboard/system-settings/info'
          }
        ]
      }
    ]
  }
])

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
  menuGroups.forEach(group => {
    group.items.forEach(item => {
      if (item.key !== key && item.children) {
        item.expanded = false
      }
    })
  })
  
  // 切换当前菜单的展开状态
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

// 组件挂载时自动展开相关菜单
onMounted(() => {
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