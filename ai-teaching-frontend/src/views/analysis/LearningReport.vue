<template>
  <div class="p-6 bg-gray-50 min-h-screen">
    <!-- 页面标题 -->
    <div class="mb-8">
      <h1 class="text-2xl font-bold text-gray-900 mb-2">{{ title }}</h1>
      <p class="text-gray-600">生成和查看学生的学习情况综合分析报告</p>
    </div>

    <!-- 报告生成器 -->
    <div class="bg-white rounded-lg shadow p-6 mb-8">
      <div class="flex flex-wrap gap-4 items-center">
        <select v-model="selectedClass" class="border rounded-lg px-4 py-2">
          <option value="">选择班级</option>
          <option v-for="class_ in classes" :key="class_" :value="class_">{{ class_ }}</option>
        </select>
        <select v-model="selectedStudent" class="border rounded-lg px-4 py-2">
          <option value="">选择学生</option>
          <option v-for="student in students" :key="student" :value="student">{{ student }}</option>
        </select>
        <select v-model="reportType" class="border rounded-lg px-4 py-2">
          <option value="weekly">周报告</option>
          <option value="monthly">月报告</option>
          <option value="semester">学期报告</option>
        </select>
        <div class="flex gap-2">
          <button 
            @click="generateReport"
            class="bg-blue-600 text-white px-6 py-2 rounded-lg hover:bg-blue-700"
          >
            生成报告
          </button>
          <button 
            @click="exportReport"
            class="bg-gray-100 text-gray-700 px-6 py-2 rounded-lg hover:bg-gray-200 flex items-center gap-2"
          >
            <i class="fas fa-download"></i>
            导出
          </button>
        </div>
      </div>
    </div>

    <!-- 学习数据概览 -->
    <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6 mb-8">
      <div v-for="stat in stats" :key="stat.label" class="bg-white p-6 rounded-lg shadow">
        <h3 class="text-sm text-gray-500 mb-2">{{ stat.label }}</h3>
        <p class="text-2xl font-bold">{{ stat.value }}</p>
        <p class="text-sm mt-2" :class="stat.trend.color">
          {{ stat.trend.label }}
        </p>
      </div>
    </div>

    <!-- 详细报告内容 -->
    <div class="grid grid-cols-1 lg:grid-cols-2 gap-8 mb-8">
      <!-- 学习进度 -->
      <div class="bg-white rounded-lg shadow p-6">
        <h2 class="text-lg font-semibold mb-4">学习进度追踪</h2>
        <div class="space-y-4">
          <div v-for="course in courseProgress" :key="course.name" class="relative">
            <div class="flex justify-between mb-1">
              <span class="text-sm">{{ course.name }}</span>
              <span class="text-sm">{{ course.progress }}%</span>
            </div>
            <div class="h-2 bg-gray-200 rounded">
              <div 
                class="h-2 bg-blue-600 rounded" 
                :style="{ width: `${course.progress}%` }"
              ></div>
            </div>
          </div>
        </div>
      </div>

      <!-- 学习时间分布 -->
      <div class="bg-white rounded-lg shadow p-6">
        <h2 class="text-lg font-semibold mb-4">学习时间分布</h2>
        <div class="h-64 flex items-center justify-center bg-gray-50 rounded">
          <p class="text-gray-500">图表区域</p>
        </div>
      </div>
    </div>

    <!-- 学习建议 -->
    <div class="bg-white rounded-lg shadow p-6">
      <h2 class="text-lg font-semibold mb-6">学习建议</h2>
      <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        <div v-for="(suggestion, index) in suggestions" :key="index" class="p-4 rounded-lg" :class="suggestion.bgColor">
          <div class="flex items-start">
            <div class="flex-shrink-0">
              <div class="w-10 h-10 rounded-lg flex items-center justify-center" :class="suggestion.iconBg">
                <i :class="[suggestion.icon, suggestion.iconColor]"></i>
              </div>
            </div>
            <div class="ml-4">
              <h3 class="text-sm font-medium text-gray-900">{{ suggestion.title }}</h3>
              <p class="mt-1 text-sm text-gray-600">{{ suggestion.content }}</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const title = ref('学习报告')
const selectedClass = ref('')
const selectedStudent = ref('')
const reportType = ref('weekly')

// 模拟数据
const classes = [
  '计算机科学1班',
  '计算机科学2班',
  '软件工程1班',
  '软件工程2班'
]

const students = [
  '张三',
  '李四',
  '王五',
  '赵六'
]

const stats = ref([
  {
    label: '总学习时长',
    value: '68.5h',
    trend: {
      label: '较上周 +2.5h',
      color: 'text-green-600'
    }
  },
  {
    label: '平均成绩',
    value: '92.5',
    trend: {
      label: '较上周 +1.2',
      color: 'text-green-600'
    }
  },
  {
    label: '作业完成率',
    value: '95%',
    trend: {
      label: '较上周 -2%',
      color: 'text-red-600'
    }
  },
  {
    label: '知识点掌握',
    value: '86%',
    trend: {
      label: '较上周 +3%',
      color: 'text-green-600'
    }
  }
])

const courseProgress = ref([
  { name: '高等数学', progress: 85 },
  { name: '线性代数', progress: 92 },
  { name: '概率论', progress: 78 },
  { name: '数据结构', progress: 65 },
  { name: '计算机网络', progress: 88 }
])

const suggestions = ref([
  {
    title: '知识点巩固',
    content: '建议复习数据结构中的"红黑树"和"B+树"相关内容，这部分知识点的掌握程度需要提升。',
    icon: 'fas fa-book',
    iconBg: 'bg-blue-100',
    iconColor: 'text-blue-600',
    bgColor: 'bg-blue-50'
  },
  {
    title: '学习方法优化',
    content: '建议采用番茄工作法，每45分钟学习后休息5分钟，避免长时间连续学习导致效率下降。',
    icon: 'fas fa-clock',
    iconBg: 'bg-green-100',
    iconColor: 'text-green-600',
    bgColor: 'bg-green-50'
  },
  {
    title: '参与度提升',
    content: '课堂互动参与度有所下降，建议多主动回答问题，参与课堂讨论，这对知识理解和记忆都很有帮助。',
    icon: 'fas fa-users',
    iconBg: 'bg-purple-100',
    iconColor: 'text-purple-600',
    bgColor: 'bg-purple-50'
  }
])

const generateReport = () => {
  if (!selectedClass.value || !selectedStudent.value) {
    alert('请选择班级和学生')
    return
  }
  
  console.log('生成报告', {
    class: selectedClass.value,
    student: selectedStudent.value,
    type: reportType.value
  })
}

const exportReport = () => {
  if (!selectedClass.value || !selectedStudent.value) {
    alert('请先生成报告')
    return
  }
  
  console.log('导出报告', {
    class: selectedClass.value,
    student: selectedStudent.value,
    type: reportType.value
  })
}
</script> 