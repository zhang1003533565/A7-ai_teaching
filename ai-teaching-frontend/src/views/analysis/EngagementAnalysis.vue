<template>
  <div class="p-6 bg-gray-50 min-h-screen">
    <div class="mb-8">
      <h1 class="text-2xl font-bold text-gray-900 mb-2">参与度分析</h1>
      <p class="text-gray-600">分析学生的课程参与情况和互动表现</p>
    </div>

    <!-- 参与度概览 -->
    <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6 mb-8">
      <div class="bg-white p-6 rounded-lg shadow-sm border border-gray-200">
        <div class="flex items-center justify-between mb-4">
          <div>
            <p class="text-sm text-gray-600">出勤率</p>
            <p class="text-2xl font-bold text-gray-900">95%</p>
          </div>
          <div class="w-12 h-12 bg-blue-100 rounded-lg flex items-center justify-center">
            <i class="fas fa-user-check text-blue-600"></i>
          </div>
        </div>
        <p class="text-sm text-gray-500">较上月 <span class="text-green-600">+2%</span></p>
      </div>

      <div class="bg-white p-6 rounded-lg shadow-sm border border-gray-200">
        <div class="flex items-center justify-between mb-4">
          <div>
            <p class="text-sm text-gray-600">课堂互动</p>
            <p class="text-2xl font-bold text-gray-900">78%</p>
          </div>
          <div class="w-12 h-12 bg-green-100 rounded-lg flex items-center justify-center">
            <i class="fas fa-comments text-green-600"></i>
          </div>
        </div>
        <p class="text-sm text-gray-500">较上月 <span class="text-green-600">+5%</span></p>
      </div>

      <div class="bg-white p-6 rounded-lg shadow-sm border border-gray-200">
        <div class="flex items-center justify-between mb-4">
          <div>
            <p class="text-sm text-gray-600">资源访问</p>
            <p class="text-2xl font-bold text-gray-900">856</p>
          </div>
          <div class="w-12 h-12 bg-purple-100 rounded-lg flex items-center justify-center">
            <i class="fas fa-book-reader text-purple-600"></i>
          </div>
        </div>
        <p class="text-sm text-gray-500">较上月 <span class="text-red-600">-12</span></p>
      </div>

      <div class="bg-white p-6 rounded-lg shadow-sm border border-gray-200">
        <div class="flex items-center justify-between mb-4">
          <div>
            <p class="text-sm text-gray-600">讨论参与</p>
            <p class="text-2xl font-bold text-gray-900">234</p>
          </div>
          <div class="w-12 h-12 bg-orange-100 rounded-lg flex items-center justify-center">
            <i class="fas fa-users text-orange-600"></i>
          </div>
        </div>
        <p class="text-sm text-gray-500">较上月 <span class="text-green-600">+45</span></p>
      </div>
    </div>

    <!-- 活跃时段分析 -->
    <div class="grid grid-cols-1 lg:grid-cols-2 gap-8 mb-8">
      <!-- 每日活跃时段 -->
      <div class="bg-white rounded-lg shadow-sm border border-gray-200">
        <div class="p-6 border-b border-gray-200">
          <h2 class="text-lg font-semibold text-gray-900">每日活跃时段</h2>
        </div>
        <div class="p-6">
          <div class="h-64 bg-gray-50 rounded-lg border border-gray-200 p-4">
            <!-- 这里可以添加图表组件 -->
            <div class="flex items-center justify-center h-full text-gray-500">
              <p>图表区域</p>
            </div>
          </div>
        </div>
      </div>

      <!-- 周活跃度分布 -->
      <div class="bg-white rounded-lg shadow-sm border border-gray-200">
        <div class="p-6 border-b border-gray-200">
          <h2 class="text-lg font-semibold text-gray-900">周活跃度分布</h2>
        </div>
        <div class="p-6">
          <div class="h-64 bg-gray-50 rounded-lg border border-gray-200 p-4">
            <!-- 这里可以添加图表组件 -->
            <div class="flex items-center justify-center h-full text-gray-500">
              <p>图表区域</p>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- 参与度详情 -->
    <div class="bg-white rounded-lg shadow-sm border border-gray-200">
      <div class="p-6 border-b border-gray-200">
        <div class="flex justify-between items-center">
          <h2 class="text-lg font-semibold text-gray-900">参与度详情</h2>
          <div class="flex space-x-2">
            <select v-model="selectedClass" class="border border-gray-300 rounded-lg px-3 py-2 text-sm">
              <option value="">全部班级</option>
              <option v-for="class_ in classes" :key="class_" :value="class_">{{ class_ }}</option>
            </select>
            <select v-model="selectedMetric" class="border border-gray-300 rounded-lg px-3 py-2 text-sm">
              <option value="">全部指标</option>
              <option v-for="metric in metrics" :key="metric" :value="metric">{{ metric }}</option>
            </select>
          </div>
        </div>
      </div>
      <div class="p-6">
        <div class="overflow-x-auto">
          <table class="min-w-full divide-y divide-gray-200">
            <thead>
              <tr>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">学生</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">班级</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">出勤率</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">互动次数</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">资源访问</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">活跃度</th>
              </tr>
            </thead>
            <tbody class="bg-white divide-y divide-gray-200">
              <tr v-for="student in filteredStudents" :key="student.id">
                <td class="px-6 py-4 whitespace-nowrap">
                  <div class="text-sm font-medium text-gray-900">{{ student.name }}</div>
                </td>
                <td class="px-6 py-4 whitespace-nowrap">
                  <div class="text-sm text-gray-900">{{ student.class }}</div>
                </td>
                <td class="px-6 py-4 whitespace-nowrap">
                  <div class="text-sm font-medium" :class="getMetricClass(student.attendance)">
                    {{ student.attendance }}%
                  </div>
                </td>
                <td class="px-6 py-4 whitespace-nowrap">
                  <div class="text-sm text-gray-900">{{ student.interactions }}</div>
                </td>
                <td class="px-6 py-4 whitespace-nowrap">
                  <div class="text-sm text-gray-900">{{ student.resourceAccess }}</div>
                </td>
                <td class="px-6 py-4 whitespace-nowrap">
                  <div class="flex items-center">
                    <div class="w-24 bg-gray-200 rounded-full h-2 mr-2">
                      <div 
                        class="h-2 rounded-full"
                        :style="{ width: student.activityLevel + '%', backgroundColor: getActivityColor(student.activityLevel) }"
                      ></div>
                    </div>
                    <span class="text-sm text-gray-900">{{ student.activityLevel }}%</span>
                  </div>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'

// 筛选选项
const selectedClass = ref('')
const selectedMetric = ref('')

// 班级和指标列表
const classes = ['计算机科学 1班', '计算机科学 2班', '软件工程 1班', '软件工程 2班']
const metrics = ['出勤率', '互动次数', '资源访问', '活跃度']

// 学生参与度数据
const studentsData = ref([
  {
    id: 1,
    name: '张三',
    class: '计算机科学 1班',
    attendance: 98,
    interactions: 45,
    resourceAccess: 156,
    activityLevel: 92
  },
  {
    id: 2,
    name: '李四',
    class: '计算机科学 2班',
    attendance: 85,
    interactions: 32,
    resourceAccess: 98,
    activityLevel: 78
  },
  {
    id: 3,
    name: '王五',
    class: '软件工程 1班',
    attendance: 92,
    interactions: 28,
    resourceAccess: 124,
    activityLevel: 85
  }
])

// 筛选学生列表
const filteredStudents = computed(() => {
  return studentsData.value.filter(student => {
    if (selectedClass.value && student.class !== selectedClass.value) return false
    return true
  })
})

// 获取指标样式
const getMetricClass = (value) => {
  if (value >= 90) return 'text-green-600'
  if (value >= 80) return 'text-blue-600'
  if (value >= 70) return 'text-yellow-600'
  return 'text-red-600'
}

// 获取活跃度颜色
const getActivityColor = (value) => {
  if (value >= 90) return '#10B981'
  if (value >= 70) return '#3B82F6'
  if (value >= 50) return '#F59E0B'
  return '#EF4444'
}
</script> 