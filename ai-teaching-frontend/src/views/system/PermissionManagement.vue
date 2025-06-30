<template>
  <div class="min-h-screen bg-gray-50">
    <!-- 顶部导航栏 -->
    <header class="fixed top-0 left-0 right-0 bg-white bg-opacity-95 shadow-sm z-50">
      <div class="container mx-auto px-4 flex items-center justify-between h-16">
        <!-- Logo 和系统名称 -->
        <div class="flex items-center">
          <button 
            @click="$router.push('/dashboard')"
            class="p-2 rounded-lg hover:bg-gray-100 transition-colors mr-2"
          >
            <i class="fas fa-arrow-left text-gray-600"></i>
          </button>
          <div class="w-10 h-10 rounded-lg bg-gradient-to-r from-blue-500 to-indigo-600 flex items-center justify-center text-white font-bold text-xl">
            <i class="fas fa-shield-alt"></i>
          </div>
          <h1 class="ml-3 text-xl font-bold text-gray-800">权限管理系统</h1>
        </div>

        <!-- 用户信息 -->
        <div class="flex items-center space-x-4">
          <div class="flex items-center">
            <div class="w-8 h-8 rounded-full bg-blue-500 flex items-center justify-center text-white">
              <i class="fas fa-user"></i>
            </div>
            <span class="ml-2 text-sm font-medium text-gray-700">{{ userInfo.realName || userInfo.username || '管理员' }}</span>
          </div>
        </div>
      </div>
    </header>

    <div class="flex pt-16">
      <!-- 左侧权限管理菜单 -->
      <aside class="w-64 bg-white shadow-sm h-[calc(100vh-4rem)] fixed left-0 overflow-y-auto">
        <nav class="py-4">
          <div class="px-4 py-2">
            <h2 class="text-sm font-semibold text-gray-500 uppercase tracking-wider mb-4">权限管理</h2>
            
            <div class="space-y-1">
              <router-link 
                to="/system/permission"
                class="flex items-center px-3 py-2 text-sm font-medium rounded-md transition-colors"
                :class="$route.path === '/system/permission' ? 'bg-blue-50 text-blue-700' : 'text-gray-600 hover:text-blue-600 hover:bg-gray-50'"
              >
                <i class="fas fa-tachometer-alt w-5 text-center mr-3"></i>
                管理概览
              </router-link>

              <router-link 
                to="/system/permission/menu"
                class="flex items-center px-3 py-2 text-sm font-medium rounded-md transition-colors"
                :class="$route.path === '/system/permission/menu' ? 'bg-blue-50 text-blue-700' : 'text-gray-600 hover:text-blue-600 hover:bg-gray-50'"
              >
                <i class="fas fa-sitemap w-5 text-center mr-3"></i>
                菜单分配管理
              </router-link>

              <router-link 
                to="/system/permission/route"
                class="flex items-center px-3 py-2 text-sm font-medium rounded-md transition-colors"
                :class="$route.path === '/system/permission/route' ? 'bg-blue-50 text-blue-700' : 'text-gray-600 hover:text-blue-600 hover:bg-gray-50'"
              >
                <i class="fas fa-route w-5 text-center mr-3"></i>
                路由分配管理
              </router-link>

              <router-link 
                to="/system/permission/role"
                class="flex items-center px-3 py-2 text-sm font-medium rounded-md transition-colors"
                :class="$route.path === '/system/permission/role' ? 'bg-blue-50 text-blue-700' : 'text-gray-600 hover:text-blue-600 hover:bg-gray-50'"
              >
                <i class="fas fa-user-tag w-5 text-center mr-3"></i>
                角色管理
              </router-link>

              <router-link 
                to="/system/permission/user"
                class="flex items-center px-3 py-2 text-sm font-medium rounded-md transition-colors"
                :class="$route.path === '/system/permission/user' ? 'bg-blue-50 text-blue-700' : 'text-gray-600 hover:text-blue-600 hover:bg-gray-50'"
              >
                <i class="fas fa-users w-5 text-center mr-3"></i>
                用户管理
              </router-link>
            </div>
          </div>
        </nav>
      </aside>

      <!-- 主内容区 -->
      <main class="ml-64 flex-1 p-6">
        <router-view />
      </main>
    </div>
  </div>
</template>

<script setup>
import { reactive } from 'vue';

// 用户信息
const userInfo = reactive(JSON.parse(localStorage.getItem('userInfo') || '{}'));
</script>

<style scoped>
.router-link-active {
  @apply bg-blue-50 text-blue-700;
}
</style> 