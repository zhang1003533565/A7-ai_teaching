<!-- 作业概览页面 - 显示作业统计信息和概览 -->
<template>
  <div class="p-6 bg-gray-50 min-h-screen">
    <!-- 页面标题 -->
    <div class="mb-8">
      <h1 class="text-2xl font-bold text-gray-900 mb-2">作业概览</h1>
      <p class="text-gray-600">全面掌握作业布置和完成情况</p>
    </div>

    <!-- 统计卡片 -->
    <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6 mb-8">
      <!-- 总作业数 -->
      <div class="bg-white p-6 rounded-lg shadow-sm border border-gray-200">
        <div class="flex items-center">
          <div class="p-3 bg-blue-100 rounded-lg">
            <i class="fas fa-tasks text-2xl text-blue-600"></i>
          </div>
          <div class="ml-4">
            <h3 class="text-lg font-semibold text-gray-900">{{ stats.totalHomework }}</h3>
            <p class="text-sm text-gray-600">总作业数</p>
          </div>
        </div>
      </div>

      <!-- 待批改 -->
      <div class="bg-white p-6 rounded-lg shadow-sm border border-gray-200">
        <div class="flex items-center">
          <div class="p-3 bg-orange-100 rounded-lg">
            <i class="fas fa-clock text-2xl text-orange-600"></i>
          </div>
          <div class="ml-4">
            <h3 class="text-lg font-semibold text-gray-900">{{ stats.pendingGrading }}</h3>
            <p class="text-sm text-gray-600">待批改</p>
          </div>
        </div>
      </div>

      <!-- 学生总数 -->
      <div class="bg-white p-6 rounded-lg shadow-sm border border-gray-200">
        <div class="flex items-center">
          <div class="p-3 bg-green-100 rounded-lg">
            <i class="fas fa-user-graduate text-2xl text-green-600"></i>
          </div>
          <div class="ml-4">
            <h3 class="text-lg font-semibold text-gray-900">{{ stats.totalStudents }}</h3>
            <p class="text-sm text-gray-600">学生总数</p>
          </div>
        </div>
      </div>

      <!-- 平均提交率 -->
      <div class="bg-white p-6 rounded-lg shadow-sm border border-gray-200">
        <div class="flex items-center">
          <div class="p-3 bg-purple-100 rounded-lg">
            <i class="fas fa-percentage text-2xl text-purple-600"></i>
          </div>
          <div class="ml-4">
            <h3 class="text-lg font-semibold text-gray-900">{{ stats.averageSubmissionRate }}%</h3>
            <p class="text-sm text-gray-600">平均提交率</p>
          </div>
        </div>
      </div>
    </div>

    <div class="grid grid-cols-1 lg:grid-cols-2 gap-8">
      <!-- 最近的作业 -->
      <div class="bg-white rounded-lg shadow-sm border border-gray-200">
        <div class="p-6 border-b border-gray-200">
          <div class="flex items-center justify-between">
            <h2 class="text-lg font-semibold text-gray-900">最近作业</h2>
            <button class="text-blue-600 hover:text-blue-700 text-sm font-medium">
              查看全部
            </button>
          </div>
        </div>
        <div class="p-6">
          <div class="space-y-4">
            <div 
              v-for="homework in recentHomework" 
              :key="homework.id"
              class="flex items-center justify-between p-4 border border-gray-200 rounded-lg hover:shadow-md transition-shadow"
            >
              <div class="flex items-center">
                <div class="w-12 h-12 bg-blue-100 rounded-lg flex items-center justify-center">
                  <i class="fas fa-file-alt text-blue-600"></i>
                </div>
                <div class="ml-4">
                  <h3 class="font-medium text-gray-900">{{ homework.title }}</h3>
                  <p class="text-sm text-gray-600">{{ homework.course }} • {{ homework.dueDate }}</p>
                  <p class="text-xs text-gray-500">已提交: {{ homework.submitted }}/{{ homework.total }}</p>
                </div>
              </div>
              <div class="flex items-center space-x-2">
                <span 
                  class="px-2 py-1 text-xs rounded-full"
                  :class="getStatusClass(homework.status)"
                >
                  {{ getStatusText(homework.status) }}
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

    <!-- 作业统计图表 -->
    <div class="mt-8 grid grid-cols-1 lg:grid-cols-2 gap-8">
      <!-- 提交率趋势 -->
      <div class="bg-white rounded-lg shadow-sm border border-gray-200">
        <div class="p-6 border-b border-gray-200">
          <h2 class="text-lg font-semibold text-gray-900">提交率趋势</h2>
        </div>
        <div class="p-6">
          <div class="space-y-4">
            <div v-for="trend in submissionTrends" :key="trend.date" class="flex items-center justify-between">
              <div class="flex items-center">
                <div class="w-3 h-3 bg-blue-500 rounded-full mr-3"></div>
                <span class="text-sm text-gray-700">{{ trend.date }}</span>
              </div>
              <div class="flex items-center">
                <div class="w-32 bg-gray-200 rounded-full h-2 mr-3">
                  <div 
                    class="bg-blue-500 h-2 rounded-full"
                    :style="{ width: trend.rate + '%' }"
                  ></div>
                </div>
                <span class="text-sm font-medium text-gray-900">{{ trend.rate }}%</span>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- 作业类型分布 -->
      <div class="bg-white rounded-lg shadow-sm border border-gray-200">
        <div class="p-6 border-b border-gray-200">
          <h2 class="text-lg font-semibold text-gray-900">作业类型分布</h2>
        </div>
        <div class="p-6">
          <div class="space-y-4">
            <div v-for="type in homeworkTypes" :key="type.name" class="flex items-center justify-between">
              <div class="flex items-center">
                <div 
                  class="w-4 h-4 rounded mr-3"
                  :style="{ backgroundColor: type.color }"
                ></div>
                <span class="text-sm text-gray-700">{{ type.name }}</span>
              </div>
              <div class="flex items-center">
                <span class="text-sm font-medium text-gray-900 mr-2">{{ type.count }}</span>
                <span class="text-xs text-gray-500">({{ type.percentage }}%)</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- 待处理事项 -->
    <div class="mt-8 bg-white rounded-lg shadow-sm border border-gray-200">
      <div class="p-6 border-b border-gray-200">
        <h2 class="text-lg font-semibold text-gray-900">待处理事项</h2>
      </div>
      <div class="p-6">
        <div class="space-y-3">
          <div v-for="task in pendingTasks" :key="task.id" class="flex items-center justify-between p-3 bg-gray-50 rounded-lg">
            <div class="flex items-center">
              <i :class="task.icon + ' text-orange-500 mr-3'"></i>
              <div>
                <p class="text-sm font-medium text-gray-900">{{ task.title }}</p>
                <p class="text-xs text-gray-600">{{ task.description }}</p>
              </div>
            </div>
            <div class="flex items-center space-x-2">
              <span class="text-xs text-gray-500">{{ task.deadline }}</span>
              <button class="text-blue-600 hover:text-blue-800 text-sm">处理</button>
            </div>
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
  totalHomework: 25,
  pendingGrading: 8,
  totalStudents: 245,
  averageSubmissionRate: 87
})

// 最近作业数据
const recentHomework = ref([
  {
    id: 1,
    title: '高等数学作业三',
    course: '高等数学A',
    dueDate: '2024-03-15',
    submitted: 42,
    total: 45,
    status: 'active'
  },
  {
    id: 2,
    title: '线性代数第二次作业',
    course: '线性代数',
    dueDate: '2024-03-10',
    submitted: 38,
    total: 38,
    status: 'completed'
  },
  {
    id: 3,
    title: '概率论练习题',
    course: '概率论与数理统计',
    dueDate: '2024-03-20',
    submitted: 15,
    total: 42,
    status: 'pending'
  },
  {
    id: 4,
    title: '微积分基础练习',
    course: '微积分基础',
    dueDate: '2024-03-08',
    submitted: 35,
    total: 35,
    status: 'grading'
  }
])

// 快捷操作
const quickActions = ref([
  {
    key: 'create-homework',
    name: '创建作业',
    icon: 'fas fa-plus'
  },
  {
    key: 'batch-grade',
    name: '批量批改',
    icon: 'fas fa-check-double'
  },
  {
    key: 'export-grades',
    name: '导出成绩',
    icon: 'fas fa-download'
  },
  {
    key: 'send-reminder',
    name: '发送提醒',
    icon: 'fas fa-bell'
  }
])

// 提交率趋势
const submissionTrends = ref([
  { date: '本周', rate: 92 },
  { date: '上周', rate: 88 },
  { date: '两周前', rate: 85 },
  { date: '三周前', rate: 90 },
  { date: '四周前', rate: 87 }
])

// 作业类型分布
const homeworkTypes = ref([
  { name: '练习题', count: 12, percentage: 48, color: '#3B82F6' },
  { name: '编程作业', count: 8, percentage: 32, color: '#10B981' },
  { name: '实验报告', count: 3, percentage: 12, color: '#F59E0B' },
  { name: '论文写作', count: 2, percentage: 8, color: '#EF4444' }
])

// 待处理事项
const pendingTasks = ref([
  {
    id: 1,
    title: '高等数学作业三待批改',
    description: '8份作业等待批改',
    deadline: '明天截止',
    icon: 'fas fa-clock'
  },
  {
    id: 2,
    title: '线性代数成绩录入',
    description: '需要录入第二次作业成绩',
    deadline: '2天后',
    icon: 'fas fa-edit'
  },
  {
    id: 3,
    title: '发送作业提醒',
    description: '提醒学生提交概率论作业',
    deadline: '今天',
    icon: 'fas fa-bell'
  }
])

// 方法
const getStatusClass = (status) => {
  const classes = {
    'active': 'bg-blue-100 text-blue-800',
    'completed': 'bg-green-100 text-green-800',
    'pending': 'bg-yellow-100 text-yellow-800',
    'grading': 'bg-orange-100 text-orange-800'
  }
  return classes[status] || 'bg-gray-100 text-gray-800'
}

const getStatusText = (status) => {
  const texts = {
    'active': '进行中',
    'completed': '已完成',
    'pending': '待提交',
    'grading': '批改中'
  }
  return texts[status] || '未知'
}

// 处理快捷操作
const handleQuickAction = (actionKey) => {
  console.log('执行快捷操作:', actionKey)
  switch (actionKey) {
    case 'create-homework':
      // 跳转到创建作业页面
      break
    case 'batch-grade':
      // 打开批量批改页面
      break
    case 'export-grades':
      // 导出成绩功能
      break
    case 'send-reminder':
      // 发送提醒功能
      break
  }
}
</script> 