<!-- The exported code uses Tailwind CSS. Install Tailwind CSS in your dev environment to ensure all styles work. -->
<template>
  <div class="h-screen bg-gray-50">
    <!-- 顶部导航栏 -->
    <header class="bg-white shadow-sm border-b border-gray-200 fixed top-0 left-0 right-0 z-50">
      <div class="h-16 px-4 flex items-center justify-between">
        <div class="flex items-center">
          <!-- 侧边栏收起/展开按钮 -->
          <button 
            @click="toggleSidebar"
            class="p-2 rounded-md text-gray-500 hover:text-gray-700 hover:bg-gray-100 transition-colors"
          >
            <i class="fas fa-bars"></i>
          </button>
          
          <!-- Logo 和标题 -->
          <div class="ml-4 flex items-center">
            <h1 class="text-xl font-semibold text-gray-900">AI智能教学系统</h1>
          </div>
        </div>

        <div class="flex items-center space-x-4">
          <!-- 通知图标 -->
          <div class="relative">
            <button class="p-2 text-gray-500 hover:text-gray-700 transition-colors">
              <i class="fas fa-bell"></i>
            </button>
            <span class="absolute -top-1 -right-1 w-5 h-5 bg-red-500 text-white rounded-full flex items-center justify-center text-xs">3</span>
          </div>

          <!-- 用户信息 -->
          <div class="relative" ref="userMenuRef">
            <div 
              class="flex items-center cursor-pointer"
              @click="toggleUserMenu"
            >
              <div class="w-8 h-8 rounded-full bg-blue-500 flex items-center justify-center text-white">
                <i class="fas fa-user"></i>
              </div>
              <span class="ml-2 text-sm font-medium text-gray-700 whitespace-nowrap">
                {{ userInfo.realName || userInfo.username || '用户' }}
              </span>
              <i 
                class="fas fa-chevron-down ml-1 text-xs text-gray-500 transition-transform"
                :class="{ 'transform rotate-180': showUserMenu }"
              ></i>
            </div>
            
            <!-- 下拉菜单 -->
            <div 
              v-show="showUserMenu"
              class="absolute right-0 mt-2 w-48 bg-white rounded-md shadow-lg py-1 z-50 border border-gray-200"
            >
              <div class="px-4 py-2 text-sm text-gray-700 border-b border-gray-100">
                <div class="font-medium">{{ userInfo.realName || userInfo.username || '用户' }}</div>
                <div class="text-xs text-gray-500">{{ userInfo.role || '教师' }}</div>
              </div>
              <a href="#" class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100">
                <i class="fas fa-user-circle mr-2"></i>个人资料
              </a>
              <a href="#" class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100">
                <i class="fas fa-cog mr-2"></i>账户设置
              </a>
              <a href="#" class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100">
                <i class="fas fa-bell mr-2"></i>通知设置
              </a>
              <div class="border-t border-gray-100 mt-1"></div>
              <div 
                class="block px-4 py-2 text-sm text-red-600 hover:bg-red-50 cursor-pointer"
                @click="handleLogout"
              >
                <i class="fas fa-sign-out-alt mr-2"></i>退出登录
              </div>
            </div>
          </div>
        </div>
      </div>
    </header>

    <div class="flex pt-16">
      <!-- 侧边栏组件 -->
      <Sidebar 
        :collapsed="sidebarCollapsed" 
        @navigate="handleSidebarNavigate"
      />

      <!-- 主内容区 -->
      <main 
        :class="[
          'flex-1 p-6 transition-all duration-300',
          sidebarCollapsed ? 'ml-16' : 'ml-64'
        ]"
      >
        <!-- 子路由内容 -->
        <router-view />
      </main>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted, onUnmounted } from 'vue'
import { useRouter } from 'vue-router'
import Sidebar from '@/components/Sidebar.vue'

const router = useRouter()

// 用户相关状态
const showUserMenu = ref(false)
const userMenuRef = ref(null)
const userInfo = reactive(JSON.parse(localStorage.getItem('userInfo') || '{}'))

// 侧边栏状态
const sidebarCollapsed = ref(false)

// 切换侧边栏状态
const toggleSidebar = () => {
  sidebarCollapsed.value = !sidebarCollapsed.value
}

// 切换用户菜单
const toggleUserMenu = () => {
  showUserMenu.value = !showUserMenu.value
}

// 处理侧边栏导航事件
const handleSidebarNavigate = (path) => {
  console.log('导航到:', path)
  
  // 直接使用路由跳转
  if (path && path !== router.currentRoute.value.path) {
    router.push(path)
  }
}

// 退出登录
const handleLogout = () => {
  if (confirm('确定要退出登录吗？')) {
    localStorage.removeItem('token')
    localStorage.removeItem('userInfo')
    router.push('/login')
  }
}

// 点击外部关闭用户菜单
const handleClickOutside = (event) => {
  if (userMenuRef.value && !userMenuRef.value.contains(event.target)) {
    showUserMenu.value = false
  }
}

// 组件挂载时添加全局点击事件监听
onMounted(() => {
  document.addEventListener('click', handleClickOutside)
})

// 组件卸载时移除事件监听
onUnmounted(() => {
  document.removeEventListener('click', handleClickOutside)
})
</script>
  