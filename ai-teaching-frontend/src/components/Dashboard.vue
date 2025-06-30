<template>
  <div>
    <!-- 面包屑导航 -->
    <div class="mb-6 flex items-center text-sm text-gray-500">
      <span class="cursor-pointer hover:text-blue-600">首页</span>
      <i class="fas fa-chevron-right mx-2 text-xs"></i>
      <span class="text-gray-700">教师工作台</span>
    </div>

    <!-- 数据概览卡片 -->
    <div class="grid grid-cols-4 gap-6 mb-6">
      <div class="bg-gradient-to-r from-blue-500 to-blue-600 rounded-lg shadow-sm p-4 text-white">
        <div class="flex justify-between items-start">
          <div>
            <p class="text-sm opacity-90">课程总数</p>
            <h3 class="text-3xl font-bold mt-2">12</h3>
            <p class="text-xs mt-2 opacity-80">
              较上月 <i class="fas fa-arrow-up"></i> 2
            </p>
          </div>
          <div class="w-10 h-10 rounded-full bg-white bg-opacity-20 flex items-center justify-center">
            <i class="fas fa-book-open text-white"></i>
          </div>
        </div>
      </div>

      <div class="bg-gradient-to-r from-purple-500 to-purple-600 rounded-lg shadow-sm p-4 text-white">
        <div class="flex justify-between items-start">
          <div>
            <p class="text-sm opacity-90">学生总数</p>
            <h3 class="text-3xl font-bold mt-2">248</h3>
            <p class="text-xs mt-2 opacity-80">
              较上月 <i class="fas fa-arrow-up"></i> 12
            </p>
          </div>
          <div class="w-10 h-10 rounded-full bg-white bg-opacity-20 flex items-center justify-center">
            <i class="fas fa-users text-white"></i>
          </div>
        </div>
      </div>

      <div class="bg-gradient-to-r from-orange-500 to-orange-600 rounded-lg shadow-sm p-4 text-white">
        <div class="flex justify-between items-start">
          <div>
            <p class="text-sm opacity-90">待批改作业</p>
            <h3 class="text-3xl font-bold mt-2">18</h3>
            <p class="text-xs mt-2 opacity-80">
              今日新增 <i class="fas fa-arrow-up"></i> 5
            </p>
          </div>
          <div class="w-10 h-10 rounded-full bg-white bg-opacity-20 flex items-center justify-center">
            <i class="fas fa-clipboard-check text-white"></i>
          </div>
        </div>
      </div>

      <div class="bg-gradient-to-r from-green-500 to-green-600 rounded-lg shadow-sm p-4 text-white">
        <div class="flex justify-between items-start">
          <div>
            <p class="text-sm opacity-90">活跃度</p>
            <h3 class="text-3xl font-bold mt-2">92%</h3>
            <p class="text-xs mt-2 opacity-80">
              较上周 <i class="fas fa-arrow-up"></i> 5%
            </p>
          </div>
          <div class="w-10 h-10 rounded-full bg-white bg-opacity-20 flex items-center justify-center">
            <i class="fas fa-chart-line text-white"></i>
          </div>
        </div>
      </div>
    </div>

    <!-- 中间区域 -->
    <div class="grid grid-cols-2 gap-6 mb-6">
      <!-- 教学日历 -->
      <div class="bg-white rounded-lg shadow-sm p-4">
        <div class="flex justify-between items-center mb-4">
          <h2 class="text-lg font-semibold text-gray-800">教学日历</h2>
          <div class="flex space-x-2">
            <button class="text-gray-500 hover:text-blue-600 cursor-pointer !rounded-button whitespace-nowrap">
              <i class="fas fa-chevron-left"></i>
            </button>
            <span class="text-sm font-medium">2025年6月</span>
            <button class="text-gray-500 hover:text-blue-600 cursor-pointer !rounded-button whitespace-nowrap">
              <i class="fas fa-chevron-right"></i>
            </button>
          </div>
        </div>
        <div class="calendar">
          <div class="grid grid-cols-7 gap-1 text-center text-sm text-gray-500 mb-2">
            <div>周日</div>
            <div>周一</div>
            <div>周二</div>
            <div>周三</div>
            <div>周四</div>
            <div>周五</div>
            <div>周六</div>
          </div>
          <div class="grid grid-cols-7 gap-1 text-center">
            <div v-for="day in calendarDays" :key="day.date" class="p-2 text-sm cursor-pointer hover:bg-blue-50"
              :class="{
                'text-gray-400': !day.isCurrentMonth,
                'bg-blue-500 text-white rounded': day.isToday,
                'text-blue-600 font-medium': day.hasEvent && !day.isToday
              }">
              {{ day.day }}
            </div>
          </div>
        </div>
      </div>

      <!-- 快速统计 -->
      <div class="bg-white rounded-lg shadow-sm p-4">
        <h2 class="text-lg font-semibold text-gray-800 mb-4">今日概览</h2>
        <div class="space-y-4">
          <div class="flex items-center justify-between">
            <div class="flex items-center">
              <div class="w-2 h-2 bg-blue-500 rounded-full mr-3"></div>
              <span class="text-sm text-gray-600">今日课程</span>
            </div>
            <span class="text-sm font-medium">3 节</span>
          </div>
          <div class="flex items-center justify-between">
            <div class="flex items-center">
              <div class="w-2 h-2 bg-orange-500 rounded-full mr-3"></div>
              <span class="text-sm text-gray-600">待批改作业</span>
            </div>
            <span class="text-sm font-medium">18 份</span>
          </div>
          <div class="flex items-center justify-between">
            <div class="flex items-center">
              <div class="w-2 h-2 bg-green-500 rounded-full mr-3"></div>
              <span class="text-sm text-gray-600">新消息</span>
            </div>
            <span class="text-sm font-medium">5 条</span>
          </div>
          <div class="flex items-center justify-between">
            <div class="flex items-center">
              <div class="w-2 h-2 bg-purple-500 rounded-full mr-3"></div>
              <span class="text-sm text-gray-600">课程进度</span>
            </div>
            <span class="text-sm font-medium">75%</span>
          </div>
        </div>
      </div>
    </div>

    <!-- 底部区域 -->
    <div class="grid grid-cols-3 gap-6">
      <!-- 最近活动 -->
      <div class="bg-white rounded-lg shadow-sm p-4">
        <h2 class="text-lg font-semibold text-gray-800 mb-4">最近活动</h2>
        <div class="space-y-3">
          <div class="flex items-center">
            <div class="w-8 h-8 bg-blue-100 rounded-full flex items-center justify-center mr-3">
              <i class="fas fa-book text-blue-600 text-xs"></i>
            </div>
            <div class="flex-1">
              <p class="text-sm text-gray-800">发布了新课程《Vue3 实战》</p>
              <p class="text-xs text-gray-500">2 小时前</p>
            </div>
          </div>
          <div class="flex items-center">
            <div class="w-8 h-8 bg-green-100 rounded-full flex items-center justify-center mr-3">
              <i class="fas fa-check text-green-600 text-xs"></i>
            </div>
            <div class="flex-1">
              <p class="text-sm text-gray-800">批改了《JavaScript基础》作业</p>
              <p class="text-xs text-gray-500">5 小时前</p>
            </div>
          </div>
          <div class="flex items-center">
            <div class="w-8 h-8 bg-orange-100 rounded-full flex items-center justify-center mr-3">
              <i class="fas fa-comments text-orange-600 text-xs"></i>
            </div>
            <div class="flex-1">
              <p class="text-sm text-gray-800">回复了学生的问题</p>
              <p class="text-xs text-gray-500">1 天前</p>
            </div>
          </div>
        </div>
      </div>

      <!-- 课程统计 -->
      <div class="bg-white rounded-lg shadow-sm p-4">
        <h2 class="text-lg font-semibold text-gray-800 mb-4">课程统计</h2>
        <div class="space-y-4">
          <div>
            <div class="flex justify-between text-sm mb-1">
              <span class="text-gray-600">JavaScript 基础</span>
              <span class="text-gray-800">85%</span>
            </div>
            <div class="w-full bg-gray-200 rounded-full h-2">
              <div class="bg-blue-500 h-2 rounded-full" style="width: 85%"></div>
            </div>
          </div>
          <div>
            <div class="flex justify-between text-sm mb-1">
              <span class="text-gray-600">Vue.js 进阶</span>
              <span class="text-gray-800">60%</span>
            </div>
            <div class="w-full bg-gray-200 rounded-full h-2">
              <div class="bg-green-500 h-2 rounded-full" style="width: 60%"></div>
            </div>
          </div>
          <div>
            <div class="flex justify-between text-sm mb-1">
              <span class="text-gray-600">React 实战</span>
              <span class="text-gray-800">40%</span>
            </div>
            <div class="w-full bg-gray-200 rounded-full h-2">
              <div class="bg-orange-500 h-2 rounded-full" style="width: 40%"></div>
            </div>
          </div>
        </div>
      </div>

      <!-- 快捷操作 -->
      <div class="bg-white rounded-lg shadow-sm p-4">
        <h2 class="text-lg font-semibold text-gray-800 mb-4">快捷操作</h2>
        <div class="grid grid-cols-2 gap-3">
          <button class="flex flex-col items-center p-3 border border-gray-200 rounded-lg hover:bg-gray-50 transition-colors">
            <i class="fas fa-plus text-blue-600 text-xl mb-2"></i>
            <span class="text-xs text-gray-600">新建课程</span>
          </button>
          <button class="flex flex-col items-center p-3 border border-gray-200 rounded-lg hover:bg-gray-50 transition-colors">
            <i class="fas fa-tasks text-green-600 text-xl mb-2"></i>
            <span class="text-xs text-gray-600">发布作业</span>
          </button>
          <button class="flex flex-col items-center p-3 border border-gray-200 rounded-lg hover:bg-gray-50 transition-colors">
            <i class="fas fa-chart-bar text-orange-600 text-xl mb-2"></i>
            <span class="text-xs text-gray-600">查看统计</span>
          </button>
          <button class="flex flex-col items-center p-3 border border-gray-200 rounded-lg hover:bg-gray-50 transition-colors">
            <i class="fas fa-cog text-purple-600 text-xl mb-2"></i>
            <span class="text-xs text-gray-600">系统设置</span>
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { reactive } from 'vue'

// 日历数据
const calendarDays = reactive([
  { day: 26, date: "2025-05-26", isCurrentMonth: false, isToday: false, hasEvent: false },
  { day: 27, date: "2025-05-27", isCurrentMonth: false, isToday: false, hasEvent: false },
  { day: 28, date: "2025-05-28", isCurrentMonth: false, isToday: false, hasEvent: false },
  { day: 29, date: "2025-05-29", isCurrentMonth: false, isToday: false, hasEvent: false },
  { day: 30, date: "2025-05-30", isCurrentMonth: false, isToday: false, hasEvent: false },
  { day: 31, date: "2025-05-31", isCurrentMonth: false, isToday: false, hasEvent: false },
  { day: 1, date: "2025-06-01", isCurrentMonth: true, isToday: false, hasEvent: false },
  { day: 2, date: "2025-06-02", isCurrentMonth: true, isToday: false, hasEvent: false },
  { day: 3, date: "2025-06-03", isCurrentMonth: true, isToday: false, hasEvent: false },
  { day: 4, date: "2025-06-04", isCurrentMonth: true, isToday: false, hasEvent: false },
  { day: 5, date: "2025-06-05", isCurrentMonth: true, isToday: false, hasEvent: false },
  { day: 6, date: "2025-06-06", isCurrentMonth: true, isToday: false, hasEvent: false },
  { day: 7, date: "2025-06-07", isCurrentMonth: true, isToday: false, hasEvent: false },
  { day: 8, date: "2025-06-08", isCurrentMonth: true, isToday: false, hasEvent: false },
  { day: 9, date: "2025-06-09", isCurrentMonth: true, isToday: false, hasEvent: false },
  { day: 10, date: "2025-06-10", isCurrentMonth: true, isToday: true, hasEvent: false },
  { day: 11, date: "2025-06-11", isCurrentMonth: true, isToday: false, hasEvent: true },
  { day: 12, date: "2025-06-12", isCurrentMonth: true, isToday: false, hasEvent: false },
  { day: 13, date: "2025-06-13", isCurrentMonth: true, isToday: false, hasEvent: false },
  { day: 14, date: "2025-06-14", isCurrentMonth: true, isToday: false, hasEvent: false },
  { day: 15, date: "2025-06-15", isCurrentMonth: true, isToday: false, hasEvent: true }
])
</script> 