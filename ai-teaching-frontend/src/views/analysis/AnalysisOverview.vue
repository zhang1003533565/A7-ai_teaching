<template>
  <div class="p-6 bg-gray-50 min-h-screen">
    <div class="mb-8">
      <h1 class="text-2xl font-bold text-gray-900 mb-2">学情分析概览</h1>
      <p class="text-gray-600">全面了解学生的学习情况和表现</p>
    </div>

    <!-- 统计卡片 -->
    <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6 mb-8">
      <!-- 总学生数 -->
      <div class="bg-white p-6 rounded-lg shadow-sm border border-gray-200">
        <div class="flex items-center">
          <div class="p-3 bg-blue-100 rounded-lg">
            <i class="fas fa-users text-2xl text-blue-600"></i>
          </div>
          <div class="ml-4">
            <h3 class="text-lg font-semibold text-gray-900">{{ stats.totalStudents }}</h3>
            <p class="text-sm text-gray-600">总学生数</p>
          </div>
        </div>
      </div>

      <!-- 平均成绩 -->
      <div class="bg-white p-6 rounded-lg shadow-sm border border-gray-200">
        <div class="flex items-center">
          <div class="p-3 bg-green-100 rounded-lg">
            <i class="fas fa-chart-line text-2xl text-green-600"></i>
          </div>
          <div class="ml-4">
            <h3 class="text-lg font-semibold text-gray-900">{{ stats.averageScore }}</h3>
            <p class="text-sm text-gray-600">平均成绩</p>
          </div>
        </div>
      </div>

      <!-- 课程参与率 -->
      <div class="bg-white p-6 rounded-lg shadow-sm border border-gray-200">
        <div class="flex items-center">
          <div class="p-3 bg-purple-100 rounded-lg">
            <i class="fas fa-user-check text-2xl text-purple-600"></i>
          </div>
          <div class="ml-4">
            <h3 class="text-lg font-semibold text-gray-900">{{ stats.participationRate }}%</h3>
            <p class="text-sm text-gray-600">课程参与率</p>
          </div>
        </div>
      </div>

      <!-- 学习进度 -->
      <div class="bg-white p-6 rounded-lg shadow-sm border border-gray-200">
        <div class="flex items-center">
          <div class="p-3 bg-orange-100 rounded-lg">
            <i class="fas fa-tasks text-2xl text-orange-600"></i>
          </div>
          <div class="ml-4">
            <h3 class="text-lg font-semibold text-gray-900">{{ stats.averageProgress }}%</h3>
            <p class="text-sm text-gray-600">平均学习进度</p>
          </div>
        </div>
      </div>
    </div>

    <div class="grid grid-cols-1 lg:grid-cols-2 gap-8">
      <!-- 学习状态分布 -->
      <div class="bg-white rounded-lg shadow-sm border border-gray-200">
        <div class="p-6 border-b border-gray-200">
          <h2 class="text-lg font-semibold text-gray-900">学习状态分布</h2>
        </div>
        <div class="p-6">
          <div class="space-y-4">
            <div v-for="status in learningStatus" :key="status.name" class="flex items-center justify-between">
              <div class="flex items-center">
                <div 
                  class="w-3 h-3 rounded-full mr-3"
                  :style="{ backgroundColor: status.color }"
                ></div>
                <span class="text-sm text-gray-700">{{ status.name }}</span>
              </div>
              <div class="flex items-center">
                <div class="w-32 bg-gray-200 rounded-full h-2 mr-3">
                  <div 
                    class="h-2 rounded-full"
                    :style="{ width: status.percentage + '%', backgroundColor: status.color }"
                  ></div>
                </div>
                <span class="text-sm font-medium text-gray-900">{{ status.count }}人</span>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- 最近活跃 -->
      <div class="bg-white rounded-lg shadow-sm border border-gray-200">
        <div class="p-6 border-b border-gray-200">
          <h2 class="text-lg font-semibold text-gray-900">最近活跃</h2>
        </div>
        <div class="p-6">
          <div class="space-y-4">
            <div v-for="activity in recentActivities" :key="activity.id" class="flex items-center justify-between">
              <div class="flex items-center">
                <div class="w-10 h-10 bg-gray-100 rounded-full flex items-center justify-center">
                  <i :class="activity.icon + ' text-gray-600'"></i>
                </div>
                <div class="ml-3">
                  <p class="text-sm font-medium text-gray-900">{{ activity.student }}</p>
                  <p class="text-xs text-gray-500">{{ activity.action }}</p>
                </div>
              </div>
              <span class="text-xs text-gray-500">{{ activity.time }}</span>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- 分析入口 -->
    <div class="mt-8 bg-white rounded-lg shadow-sm border border-gray-200">
      <div class="p-6 border-b border-gray-200">
        <h2 class="text-lg font-semibold text-gray-900">快速分析</h2>
      </div>
      <div class="p-6">
        <div class="grid grid-cols-1 md:grid-cols-3 gap-6">
          <div 
            v-for="analysis in analysisOptions" 
            :key="analysis.key"
            class="p-4 border border-gray-200 rounded-lg hover:shadow-md transition-shadow cursor-pointer"
            @click="navigateToAnalysis(analysis.path)"
          >
            <div class="flex items-center mb-3">
              <div class="w-10 h-10 rounded-lg flex items-center justify-center" :class="analysis.bgColor">
                <i :class="analysis.icon + ' ' + analysis.iconColor"></i>
              </div>
              <h3 class="ml-3 font-medium text-gray-900">{{ analysis.name }}</h3>
            </div>
            <p class="text-sm text-gray-600">{{ analysis.description }}</p>
          </div>
        </div>
      </div>
    </div>

    <!-- 需要关注的学生 -->
    <div class="mt-8 bg-white rounded-lg shadow-sm border border-gray-200">
      <div class="p-6 border-b border-gray-200">
        <h2 class="text-lg font-semibold text-gray-900">需要关注的学生</h2>
      </div>
      <div class="p-6">
        <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
          <div 
            v-for="student in attentionStudents" 
            :key="student.id"
            class="p-4 border border-gray-200 rounded-lg"
          >
            <div class="flex items-center justify-between mb-3">
              <div class="flex items-center">
                <div class="w-10 h-10 bg-gray-100 rounded-full flex items-center justify-center">
                  <i class="fas fa-user text-gray-600"></i>
                </div>
                <div class="ml-3">
                  <p class="font-medium text-gray-900">{{ student.name }}</p>
                  <p class="text-xs text-gray-500">{{ student.class }}</p>
                </div>
              </div>
              <span 
                class="px-2 py-1 text-xs rounded-full"
                :class="getStatusClass(student.status)"
              >
                {{ student.status }}
              </span>
            </div>
            <div class="space-y-2">
              <div class="flex justify-between text-sm">
                <span class="text-gray-600">出勤率</span>
                <span class="font-medium" :class="getColorClass(student.attendance)">
                  {{ student.attendance }}%
                </span>
              </div>
              <div class="flex justify-between text-sm">
                <span class="text-gray-600">作业完成</span>
                <span class="font-medium" :class="getColorClass(student.homework)">
                  {{ student.homework }}%
                </span>
              </div>
              <div class="flex justify-between text-sm">
                <span class="text-gray-600">平均成绩</span>
                <span class="font-medium" :class="getColorClass(student.averageScore)">
                  {{ student.averageScore }}
                </span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()

// 统计数据
const stats = reactive({
  totalStudents: 245,
  averageScore: 85.6,
  participationRate: 92,
  averageProgress: 78
})

// 学习状态分布
const learningStatus = ref([
  { name: '优秀', count: 45, percentage: 18, color: '#10B981' },
  { name: '良好', count: 98, percentage: 40, color: '#3B82F6' },
  { name: '一般', count: 67, percentage: 27, color: '#F59E0B' },
  { name: '需要关注', count: 35, percentage: 15, color: '#EF4444' }
])

// 最近活跃
const recentActivities = ref([
  {
    id: 1,
    student: '张三',
    action: '完成了《高等数学》第三章作业',
    time: '10分钟前',
    icon: 'fas fa-file-alt'
  },
  {
    id: 2,
    student: '李四',
    action: '参与了《线性代数》课堂讨论',
    time: '25分钟前',
    icon: 'fas fa-comments'
  },
  {
    id: 3,
    student: '王五',
    action: '观看了《概率论》视频课程',
    time: '1小时前',
    icon: 'fas fa-play-circle'
  }
])

// 分析选项
const analysisOptions = ref([
  {
    key: 'progress',
    name: '学习进度',
    description: '查看学生的课程学习进度',
    icon: 'fas fa-tasks',
    bgColor: 'bg-blue-100',
    iconColor: 'text-blue-600',
    path: '/dashboard/analysis/progress'
  },
  {
    key: 'performance',
    name: '成绩分析',
    description: '分析学生的成绩表现',
    icon: 'fas fa-chart-line',
    bgColor: 'bg-green-100',
    iconColor: 'text-green-600',
    path: '/dashboard/analysis/performance'
  },
  {
    key: 'engagement',
    name: '参与度分析',
    description: '了解学生的课程参与情况',
    icon: 'fas fa-user-clock',
    bgColor: 'bg-purple-100',
    iconColor: 'text-purple-600',
    path: '/dashboard/analysis/engagement'
  }
])

// 需要关注的学生
const attentionStudents = ref([
  {
    id: 1,
    name: '张三',
    class: '计算机科学 2班',
    status: '学习困难',
    attendance: 65,
    homework: 55,
    averageScore: 62
  },
  {
    id: 2,
    name: '李四',
    class: '软件工程 1班',
    status: '进度落后',
    attendance: 78,
    homework: 45,
    averageScore: 71
  },
  {
    id: 3,
    name: '王五',
    class: '人工智能 3班',
    status: '注意力不集中',
    attendance: 82,
    homework: 68,
    averageScore: 75
  }
])

// 方法
const getStatusClass = (status) => {
  const classes = {
    '学习困难': 'bg-red-100 text-red-800',
    '进度落后': 'bg-yellow-100 text-yellow-800',
    '注意力不集中': 'bg-orange-100 text-orange-800'
  }
  return classes[status] || 'bg-gray-100 text-gray-800'
}

const getColorClass = (value) => {
  if (value >= 80) return 'text-green-600'
  if (value >= 60) return 'text-yellow-600'
  return 'text-red-600'
}

const navigateToAnalysis = (path) => {
  router.push(path)
}
</script> 