<template>
  <div>
    <!-- 面包屑导航 -->
    <div class="mb-6 flex items-center text-sm text-gray-500">
      <span class="cursor-pointer hover:text-blue-600" @click="$router.push('/dashboard')">首页</span>
      <i class="fas fa-chevron-right mx-2 text-xs"></i>
      <span class="text-gray-700">权限管理</span>
      <i class="fas fa-chevron-right mx-2 text-xs"></i>
      <span class="text-gray-700">管理概览</span>
    </div>

    <!-- 页面标题 -->
    <div class="mb-6">
      <h1 class="text-2xl font-bold text-gray-900">权限管理概览</h1>
      <p class="text-gray-600 mt-1">系统权限管理模块的总体概览</p>
    </div>

    <!-- 统计卡片 -->
    <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6 mb-8">
      <div class="bg-white rounded-lg shadow-sm p-6 border border-gray-200">
        <div class="flex items-center">
          <div class="p-3 rounded-full bg-blue-100">
            <i class="fas fa-users text-blue-600 text-xl"></i>
          </div>
          <div class="ml-4">
            <h3 class="text-lg font-semibold text-gray-900">{{ stats.totalUsers }}</h3>
            <p class="text-sm text-gray-600">总用户数</p>
          </div>
        </div>
      </div>

      <div class="bg-white rounded-lg shadow-sm p-6 border border-gray-200">
        <div class="flex items-center">
          <div class="p-3 rounded-full bg-green-100">
            <i class="fas fa-user-tag text-green-600 text-xl"></i>
          </div>
          <div class="ml-4">
            <h3 class="text-lg font-semibold text-gray-900">{{ stats.totalRoles }}</h3>
            <p class="text-sm text-gray-600">角色总数</p>
          </div>
        </div>
      </div>

      <div class="bg-white rounded-lg shadow-sm p-6 border border-gray-200">
        <div class="flex items-center">
          <div class="p-3 rounded-full bg-purple-100">
            <i class="fas fa-key text-purple-600 text-xl"></i>
          </div>
          <div class="ml-4">
            <h3 class="text-lg font-semibold text-gray-900">{{ stats.totalPermissions }}</h3>
            <p class="text-sm text-gray-600">权限总数</p>
          </div>
        </div>
      </div>

      <div class="bg-white rounded-lg shadow-sm p-6 border border-gray-200">
        <div class="flex items-center">
          <div class="p-3 rounded-full bg-orange-100">
            <i class="fas fa-sitemap text-orange-600 text-xl"></i>
          </div>
          <div class="ml-4">
            <h3 class="text-lg font-semibold text-gray-900">{{ stats.totalMenus }}</h3>
            <p class="text-sm text-gray-600">菜单总数</p>
          </div>
        </div>
      </div>
    </div>

    <!-- 快捷操作 -->
    <div class="grid grid-cols-1 lg:grid-cols-2 gap-6 mb-8">
      <div class="bg-white rounded-lg shadow-sm p-6 border border-gray-200">
        <h2 class="text-lg font-semibold text-gray-900 mb-4">快捷操作</h2>
        <div class="space-y-3">
          <button 
            @click="$router.push('/system/permission/user')"
            class="w-full flex items-center justify-between p-3 border border-gray-200 rounded-lg hover:bg-gray-50 transition-colors"
          >
            <div class="flex items-center">
              <i class="fas fa-user-plus text-blue-600 mr-3"></i>
              <span class="text-sm font-medium">新增用户</span>
            </div>
            <i class="fas fa-chevron-right text-gray-400"></i>
          </button>

          <button 
            @click="$router.push('/system/permission/role')"
            class="w-full flex items-center justify-between p-3 border border-gray-200 rounded-lg hover:bg-gray-50 transition-colors"
          >
            <div class="flex items-center">
              <i class="fas fa-plus-circle text-green-600 mr-3"></i>
              <span class="text-sm font-medium">新增角色</span>
            </div>
            <i class="fas fa-chevron-right text-gray-400"></i>
          </button>

          <button 
            @click="$router.push('/system/permission/menu')"
            class="w-full flex items-center justify-between p-3 border border-gray-200 rounded-lg hover:bg-gray-50 transition-colors"
          >
            <div class="flex items-center">
              <i class="fas fa-cog text-purple-600 mr-3"></i>
              <span class="text-sm font-medium">权限分配</span>
            </div>
            <i class="fas fa-chevron-right text-gray-400"></i>
          </button>

          <button 
            @click="$router.push('/system/permission/route')"
            class="w-full flex items-center justify-between p-3 border border-gray-200 rounded-lg hover:bg-gray-50 transition-colors"
          >
            <div class="flex items-center">
              <i class="fas fa-route text-orange-600 mr-3"></i>
              <span class="text-sm font-medium">路由管理</span>
            </div>
            <i class="fas fa-chevron-right text-gray-400"></i>
          </button>
        </div>
      </div>

      <div class="bg-white rounded-lg shadow-sm p-6 border border-gray-200">
        <h2 class="text-lg font-semibold text-gray-900 mb-4">系统状态</h2>
        <div class="space-y-4">
          <div class="flex items-center justify-between">
            <span class="text-sm text-gray-600">系统运行状态</span>
            <span class="inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium bg-green-100 text-green-800">
              <div class="w-1.5 h-1.5 bg-green-500 rounded-full mr-1"></div>
              正常
            </span>
          </div>

          <div class="flex items-center justify-between">
            <span class="text-sm text-gray-600">在线用户数</span>
            <span class="text-sm font-medium text-gray-900">{{ stats.onlineUsers }}</span>
          </div>

          <div class="flex items-center justify-between">
            <span class="text-sm text-gray-600">今日登录次数</span>
            <span class="text-sm font-medium text-gray-900">{{ stats.todayLogins }}</span>
          </div>

          <div class="flex items-center justify-between">
            <span class="text-sm text-gray-600">权限检查次数</span>
            <span class="text-sm font-medium text-gray-900">{{ stats.permissionChecks }}</span>
          </div>
        </div>
      </div>
    </div>

    <!-- 最近活动 -->
    <div class="bg-white rounded-lg shadow-sm border border-gray-200">
      <div class="px-6 py-4 border-b border-gray-200">
        <h2 class="text-lg font-semibold text-gray-900">最近活动</h2>
      </div>
      <div class="p-6">
        <div class="space-y-4">
          <div 
            v-for="(activity, index) in recentActivities" 
            :key="index"
            class="flex items-center p-3 bg-gray-50 rounded-lg"
          >
            <div class="flex-shrink-0">
              <div :class="`w-8 h-8 rounded-full flex items-center justify-center ${activity.bgColor}`">
                <i :class="`${activity.icon} ${activity.iconColor} text-sm`"></i>
              </div>
            </div>
            <div class="ml-3 flex-1">
              <p class="text-sm font-medium text-gray-900">{{ activity.title }}</p>
              <p class="text-xs text-gray-500">{{ activity.description }}</p>
            </div>
            <div class="text-xs text-gray-400">{{ activity.time }}</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { reactive } from 'vue';

// 统计数据
const stats = reactive({
  totalUsers: 248,
  totalRoles: 4,
  totalPermissions: 28,
  totalMenus: 15,
  onlineUsers: 42,
  todayLogins: 156,
  permissionChecks: 2847
});

// 最近活动
const recentActivities = reactive([
  {
    title: '新用户注册',
    description: '张三 注册了新账号',
    time: '2分钟前',
    icon: 'fas fa-user-plus',
    iconColor: 'text-blue-600',
    bgColor: 'bg-blue-100'
  },
  {
    title: '角色权限更新',
    description: '教师角色权限配置已更新',
    time: '15分钟前',
    icon: 'fas fa-shield-alt',
    iconColor: 'text-green-600',
    bgColor: 'bg-green-100'
  },
  {
    title: '用户登录',
    description: '管理员 登录系统',
    time: '30分钟前',
    icon: 'fas fa-sign-in-alt',
    iconColor: 'text-purple-600',
    bgColor: 'bg-purple-100'
  },
  {
    title: '菜单配置变更',
    description: '系统管理菜单结构已调整',
    time: '1小时前',
    icon: 'fas fa-sitemap',
    iconColor: 'text-orange-600',
    bgColor: 'bg-orange-100'
  }
]);
</script> 