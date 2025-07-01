<template>
  <div class="p-6 bg-gray-50 min-h-screen">
    <div class="mb-8">
      <h1 class="text-2xl font-bold text-gray-900 mb-2">成绩分析</h1>
      <p class="text-gray-600">分析学生的成绩表现和分布情况</p>
    </div>

    <!-- 成绩概览 -->
    <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6 mb-8">
      <div class="bg-white p-6 rounded-lg shadow-sm border border-gray-200">
        <div class="flex items-center justify-between mb-4">
          <div>
            <p class="text-sm text-gray-600">平均分</p>
            <p class="text-2xl font-bold text-gray-900">85.6</p>
          </div>
          <div class="w-12 h-12 bg-blue-100 rounded-lg flex items-center justify-center">
            <i class="fas fa-chart-line text-blue-600"></i>
          </div>
        </div>
        <p class="text-sm text-gray-500">较上月 <span class="text-green-600">+2.3</span></p>
      </div>

      <div class="bg-white p-6 rounded-lg shadow-sm border border-gray-200">
        <div class="flex items-center justify-between mb-4">
          <div>
            <p class="text-sm text-gray-600">及格率</p>
            <p class="text-2xl font-bold text-gray-900">92%</p>
          </div>
          <div class="w-12 h-12 bg-green-100 rounded-lg flex items-center justify-center">
            <i class="fas fa-check-circle text-green-600"></i>
          </div>
        </div>
        <p class="text-sm text-gray-500">较上月 <span class="text-green-600">+5%</span></p>
      </div>

      <div class="bg-white p-6 rounded-lg shadow-sm border border-gray-200">
        <div class="flex items-center justify-between mb-4">
          <div>
            <p class="text-sm text-gray-600">优秀率</p>
            <p class="text-2xl font-bold text-gray-900">35%</p>
          </div>
          <div class="w-12 h-12 bg-purple-100 rounded-lg flex items-center justify-center">
            <i class="fas fa-star text-purple-600"></i>
          </div>
        </div>
        <p class="text-sm text-gray-500">较上月 <span class="text-red-600">-2%</span></p>
      </div>

      <div class="bg-white p-6 rounded-lg shadow-sm border border-gray-200">
        <div class="flex items-center justify-between mb-4">
          <div>
            <p class="text-sm text-gray-600">标准差</p>
            <p class="text-2xl font-bold text-gray-900">8.2</p>
          </div>
          <div class="w-12 h-12 bg-orange-100 rounded-lg flex items-center justify-center">
            <i class="fas fa-chart-bar text-orange-600"></i>
          </div>
        </div>
        <p class="text-sm text-gray-500">较上月 <span class="text-green-600">-0.5</span></p>
      </div>
    </div>

    <!-- 成绩分布 -->
    <div class="grid grid-cols-1 lg:grid-cols-2 gap-8 mb-8">
      <!-- 分数段分布 -->
      <div class="bg-white rounded-lg shadow-sm border border-gray-200">
        <div class="p-6 border-b border-gray-200">
          <h2 class="text-lg font-semibold text-gray-900">分数段分布</h2>
        </div>
        <div class="p-6">
          <div class="space-y-4">
            <div v-for="range in scoreRanges" :key="range.range" class="flex items-center justify-between">
              <div class="flex items-center">
                <div 
                  class="w-3 h-3 rounded-full mr-3"
                  :style="{ backgroundColor: range.color }"
                ></div>
                <span class="text-sm text-gray-700">{{ range.range }}</span>
              </div>
              <div class="flex items-center">
                <div class="w-32 bg-gray-200 rounded-full h-2 mr-3">
                  <div 
                    class="h-2 rounded-full"
                    :style="{ width: range.percentage + '%', backgroundColor: range.color }"
                  ></div>
                </div>
                <span class="text-sm font-medium text-gray-900">{{ range.count }}人</span>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- 成绩趋势 -->
      <div class="bg-white rounded-lg shadow-sm border border-gray-200">
        <div class="p-6 border-b border-gray-200">
          <h2 class="text-lg font-semibold text-gray-900">成绩趋势</h2>
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

    <!-- 成绩详情 -->
    <div class="bg-white rounded-lg shadow-sm border border-gray-200">
      <div class="p-6 border-b border-gray-200">
        <div class="flex justify-between items-center">
          <h2 class="text-lg font-semibold text-gray-900">成绩详情</h2>
          <div class="flex space-x-2">
            <select v-model="selectedClass" class="border border-gray-300 rounded-lg px-3 py-2 text-sm">
              <option value="">全部班级</option>
              <option v-for="class_ in classes" :key="class_" :value="class_">{{ class_ }}</option>
            </select>
            <select v-model="selectedSubject" class="border border-gray-300 rounded-lg px-3 py-2 text-sm">
              <option value="">全部科目</option>
              <option v-for="subject in subjects" :key="subject" :value="subject">{{ subject }}</option>
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
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">科目</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">成绩</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">排名</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">趋势</th>
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
                  <div class="text-sm text-gray-900">{{ student.subject }}</div>
                </td>
                <td class="px-6 py-4 whitespace-nowrap">
                  <div class="text-sm font-medium" :class="getScoreClass(student.score)">
                    {{ student.score }}
                  </div>
                </td>
                <td class="px-6 py-4 whitespace-nowrap">
                  <div class="text-sm text-gray-900">{{ student.rank }}</div>
                </td>
                <td class="px-6 py-4 whitespace-nowrap">
                  <div class="flex items-center">
                    <i 
                      :class="getTrendIcon(student.trend)"
                      class="mr-1"
                    ></i>
                    <span 
                      class="text-sm"
                      :class="getTrendClass(student.trend)"
                    >
                      {{ student.trend }}
                    </span>
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
const selectedSubject = ref('')

// 班级和科目列表
const classes = ['计算机科学 1班', '计算机科学 2班', '软件工程 1班', '软件工程 2班']
const subjects = ['高等数学', '线性代数', '概率论', '数据结构', '计算机网络']

// 分数段分布数据
const scoreRanges = ref([
  { range: '90-100分', count: 45, percentage: 18, color: '#10B981' },
  { range: '80-89分', count: 98, percentage: 40, color: '#3B82F6' },
  { range: '70-79分', count: 67, percentage: 27, color: '#F59E0B' },
  { range: '60-69分', count: 25, percentage: 10, color: '#6B7280' },
  { range: '60分以下', count: 10, percentage: 5, color: '#EF4444' }
])

// 学生成绩数据
const studentsData = ref([
  {
    id: 1,
    name: '张三',
    class: '计算机科学 1班',
    subject: '高等数学',
    score: 92,
    rank: '1/35',
    trend: '+5'
  },
  {
    id: 2,
    name: '李四',
    class: '计算机科学 2班',
    subject: '线性代数',
    score: 85,
    rank: '3/35',
    trend: '-2'
  },
  {
    id: 3,
    name: '王五',
    class: '软件工程 1班',
    subject: '概率论',
    score: 78,
    rank: '8/35',
    trend: '+3'
  }
])

// 筛选学生列表
const filteredStudents = computed(() => {
  return studentsData.value.filter(student => {
    if (selectedClass.value && student.class !== selectedClass.value) return false
    if (selectedSubject.value && student.subject !== selectedSubject.value) return false
    return true
  })
})

// 获取成绩样式
const getScoreClass = (score) => {
  if (score >= 90) return 'text-green-600'
  if (score >= 80) return 'text-blue-600'
  if (score >= 70) return 'text-yellow-600'
  if (score >= 60) return 'text-gray-600'
  return 'text-red-600'
}

// 获取趋势图标
const getTrendIcon = (trend) => {
  const value = parseFloat(trend)
  if (value > 0) return 'fas fa-arrow-up text-green-600'
  if (value < 0) return 'fas fa-arrow-down text-red-600'
  return 'fas fa-minus text-gray-600'
}

// 获取趋势样式
const getTrendClass = (trend) => {
  const value = parseFloat(trend)
  if (value > 0) return 'text-green-600'
  if (value < 0) return 'text-red-600'
  return 'text-gray-600'
}
</script> 