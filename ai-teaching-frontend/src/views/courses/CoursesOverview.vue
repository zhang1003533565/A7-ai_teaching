<!-- 课程概览页面 - 显示课程统计信息和概览 -->
<template>
  <div class="p-6 bg-gray-50 min-h-screen">
    <!-- 页面标题 -->
    <div class="mb-8">
      <h1 class="text-2xl font-bold text-gray-900 mb-2">课程概览</h1>
      <p class="text-gray-600">全面掌握教学情况，快速管理课程内容</p>
    </div>

    <!-- 统计卡片 -->
    <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6 mb-8">
      <!-- 总课程数 -->
      <div class="bg-white p-6 rounded-lg shadow-sm border border-gray-200">
        <div class="flex items-center">
          <div class="p-3 bg-blue-100 rounded-lg">
            <i class="fas fa-book text-2xl text-blue-600"></i>
          </div>
          <div class="ml-4">
            <h3 class="text-lg font-semibold text-gray-900">{{ stats.totalCourses }}</h3>
            <p class="text-sm text-gray-600">总课程数</p>
          </div>
        </div>
      </div>

      <!-- 正在进行的课程 -->
      <div class="bg-white p-6 rounded-lg shadow-sm border border-gray-200">
        <div class="flex items-center">
          <div class="p-3 bg-green-100 rounded-lg">
            <i class="fas fa-play-circle text-2xl text-green-600"></i>
          </div>
          <div class="ml-4">
            <h3 class="text-lg font-semibold text-gray-900">{{ stats.activeCourses }}</h3>
            <p class="text-sm text-gray-600">进行中</p>
          </div>
        </div>
      </div>

      <!-- 学生总数 -->
      <div class="bg-white p-6 rounded-lg shadow-sm border border-gray-200">
        <div class="flex items-center">
          <div class="p-3 bg-purple-100 rounded-lg">
            <i class="fas fa-user-graduate text-2xl text-purple-600"></i>
          </div>
          <div class="ml-4">
            <h3 class="text-lg font-semibold text-gray-900">{{ stats.totalStudents }}</h3>
            <p class="text-sm text-gray-600">学生总数</p>
          </div>
        </div>
      </div>

      <!-- 本月作业数 -->
      <div class="bg-white p-6 rounded-lg shadow-sm border border-gray-200">
        <div class="flex items-center">
          <div class="p-3 bg-orange-100 rounded-lg">
            <i class="fas fa-tasks text-2xl text-orange-600"></i>
          </div>
          <div class="ml-4">
            <h3 class="text-lg font-semibold text-gray-900">{{ stats.monthlyAssignments }}</h3>
            <p class="text-sm text-gray-600">本月作业</p>
          </div>
        </div>
      </div>
    </div>

    <div class="grid grid-cols-1 lg:grid-cols-2 gap-8">
      <!-- 最近的课程 -->
      <div class="bg-white rounded-lg shadow-sm border border-gray-200">
        <div class="p-6 border-b border-gray-200">
          <div class="flex items-center justify-between">
            <h2 class="text-lg font-semibold text-gray-900">最近课程</h2>
            <button class="text-blue-600 hover:text-blue-700 text-sm font-medium">
              查看全部
            </button>
          </div>
        </div>
        <div class="p-6">
          <div class="space-y-4">
            <div 
              v-for="course in recentCourses" 
              :key="course.id"
              class="flex items-center justify-between p-4 border border-gray-200 rounded-lg hover:shadow-md transition-shadow"
            >
              <div class="flex items-center">
                <div class="w-12 h-12 bg-blue-100 rounded-lg flex items-center justify-center">
                  <i class="fas fa-book text-blue-600"></i>
                </div>
                <div class="ml-4">
                  <h3 class="font-medium text-gray-900">{{ course.name }}</h3>
                  <p class="text-sm text-gray-600">{{ course.students }}名学生 • {{ course.lastUpdate }}</p>
                </div>
              </div>
              <div class="flex items-center space-x-2">
                <span 
                  class="px-2 py-1 text-xs rounded-full"
                  :class="course.status === '进行中' ? 'bg-green-100 text-green-800' : 'bg-gray-100 text-gray-800'"
                >
                  {{ course.status }}
                </span>
                <button class="text-gray-400 hover:text-gray-600">
                  <i class="fas fa-ellipsis-v"></i>
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- 快捷操作 -->
      <div class="bg-white rounded-lg shadow-sm border border-gray-200">
        <div class="p-6 border-b border-gray-200">
          <h2 class="text-lg font-semibold text-gray-900">快捷操作</h2>
        </div>
        <div class="p-6">
          <div class="grid grid-cols-2 gap-4">
            <button 
              v-for="action in quickActions"
              :key="action.key"
              @click="handleQuickAction(action.key)"
              class="p-4 border-2 border-dashed border-gray-300 rounded-lg hover:border-blue-500 hover:bg-blue-50 transition-all group"
            >
              <div class="text-center">
                <i :class="action.icon + ' text-2xl text-gray-400 group-hover:text-blue-500 mb-2'"></i>
                <p class="text-sm font-medium text-gray-700 group-hover:text-blue-600">{{ action.name }}</p>
              </div>
            </button>
          </div>
        </div>
      </div>
    </div>

    <!-- 课程日历 -->
    <div class="mt-8 bg-white rounded-lg shadow-sm border border-gray-200">
      <div class="p-6 border-b border-gray-200">
        <h2 class="text-lg font-semibold text-gray-900">课程日历</h2>
      </div>
      <div class="p-6">
        <div class="grid grid-cols-7 gap-1 text-center text-sm">
          <div v-for="day in ['日', '一', '二', '三', '四', '五', '六']" :key="day" class="p-2 font-semibold text-gray-600">
            {{ day }}
          </div>
          <div 
            v-for="date in calendarDates" 
            :key="date.date"
            class="p-2 h-12 border border-gray-100 rounded relative"
            :class="date.hasEvent ? 'bg-blue-50' : ''"
          >
            <span class="text-xs text-gray-600">{{ date.day }}</span>
            <div v-if="date.hasEvent" class="absolute bottom-1 left-1 w-2 h-2 bg-blue-500 rounded-full"></div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive } from 'vue'

// 统计数据
const stats = reactive({
  totalCourses: 12,
  activeCourses: 8,
  totalStudents: 245,
  monthlyAssignments: 15
})

// 最近课程数据
const recentCourses = ref([
  {
    id: 1,
    name: '高等数学A',
    students: 45,
    lastUpdate: '2小时前',
    status: '进行中'
  },
  {
    id: 2,
    name: '线性代数',
    students: 38,
    lastUpdate: '1天前',
    status: '进行中'
  },
  {
    id: 3,
    name: '概率论与数理统计',
    students: 42,
    lastUpdate: '3天前',
    status: '已结束'
  },
  {
    id: 4,
    name: '微积分基础',
    students: 35,
    lastUpdate: '5天前',
    status: '进行中'
  }
])

// 快捷操作
const quickActions = ref([
  {
    key: 'create-course',
    name: '创建课程',
    icon: 'fas fa-plus'
  },
  {
    key: 'upload-resource',
    name: '上传资源',
    icon: 'fas fa-upload'
  },
  {
    key: 'create-assignment',
    name: '布置作业',
    icon: 'fas fa-tasks'
  },
  {
    key: 'schedule-class',
    name: '安排课程',
    icon: 'fas fa-calendar-plus'
  }
])

// 日历数据（简化版）
const calendarDates = ref(
  Array.from({ length: 35 }, (_, i) => ({
    date: i + 1,
    day: ((i % 31) + 1),
    hasEvent: Math.random() > 0.8
  }))
)

// 处理快捷操作
const handleQuickAction = (actionKey) => {
  console.log('执行快捷操作:', actionKey)
  // 这里可以添加具体的操作逻辑，比如弹出模态框或跳转页面
  switch (actionKey) {
    case 'create-course':
      // 跳转到创建课程页面或打开模态框
      break
    case 'upload-resource':
      // 打开文件上传对话框
      break
    case 'create-assignment':
      // 跳转到作业创建页面
      break
    case 'schedule-class':
      // 打开课程安排页面
      break
  }
}
</script> 