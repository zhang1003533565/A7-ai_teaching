<template>
  <div class="p-6 bg-gray-50 min-h-screen">
    <div class="mb-8">
      <h1 class="text-2xl font-bold text-gray-900 mb-2">学习进度分析</h1>
      <p class="text-gray-600">跟踪和分析学生的课程学习进度</p>
    </div>

    <!-- 进度概览 -->
    <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6 mb-8">
      <div class="bg-white p-6 rounded-lg shadow-sm border border-gray-200">
        <div class="flex items-center justify-between mb-4">
          <div>
            <p class="text-sm text-gray-600">总体进度</p>
            <p class="text-2xl font-bold text-gray-900">78%</p>
          </div>
          <div class="w-12 h-12 bg-blue-100 rounded-lg flex items-center justify-center">
            <i class="fas fa-tasks text-blue-600"></i>
          </div>
        </div>
        <div class="w-full bg-gray-200 rounded-full h-2">
          <div class="bg-blue-600 h-2 rounded-full" style="width: 78%"></div>
        </div>
      </div>

      <div class="bg-white p-6 rounded-lg shadow-sm border border-gray-200">
        <div class="flex items-center justify-between mb-4">
          <div>
            <p class="text-sm text-gray-600">按时完成率</p>
            <p class="text-2xl font-bold text-gray-900">85%</p>
          </div>
          <div class="w-12 h-12 bg-green-100 rounded-lg flex items-center justify-center">
            <i class="fas fa-clock text-green-600"></i>
          </div>
        </div>
        <div class="w-full bg-gray-200 rounded-full h-2">
          <div class="bg-green-600 h-2 rounded-full" style="width: 85%"></div>
        </div>
      </div>

      <div class="bg-white p-6 rounded-lg shadow-sm border border-gray-200">
        <div class="flex items-center justify-between mb-4">
          <div>
            <p class="text-sm text-gray-600">进度超前</p>
            <p class="text-2xl font-bold text-gray-900">32%</p>
          </div>
          <div class="w-12 h-12 bg-purple-100 rounded-lg flex items-center justify-center">
            <i class="fas fa-rocket text-purple-600"></i>
          </div>
        </div>
        <div class="w-full bg-gray-200 rounded-full h-2">
          <div class="bg-purple-600 h-2 rounded-full" style="width: 32%"></div>
        </div>
      </div>

      <div class="bg-white p-6 rounded-lg shadow-sm border border-gray-200">
        <div class="flex items-center justify-between mb-4">
          <div>
            <p class="text-sm text-gray-600">进度落后</p>
            <p class="text-2xl font-bold text-gray-900">15%</p>
          </div>
          <div class="w-12 h-12 bg-red-100 rounded-lg flex items-center justify-center">
            <i class="fas fa-exclamation-triangle text-red-600"></i>
          </div>
        </div>
        <div class="w-full bg-gray-200 rounded-full h-2">
          <div class="bg-red-600 h-2 rounded-full" style="width: 15%"></div>
        </div>
      </div>
    </div>

    <!-- 课程进度列表 -->
    <div class="bg-white rounded-lg shadow-sm border border-gray-200 mb-8">
      <div class="p-6 border-b border-gray-200">
        <div class="flex justify-between items-center">
          <h2 class="text-lg font-semibold text-gray-900">课程进度详情</h2>
          <div class="flex space-x-2">
            <select v-model="selectedClass" class="border border-gray-300 rounded-lg px-3 py-2 text-sm">
              <option value="">全部班级</option>
              <option v-for="class_ in classes" :key="class_" :value="class_">{{ class_ }}</option>
            </select>
            <select v-model="selectedCourse" class="border border-gray-300 rounded-lg px-3 py-2 text-sm">
              <option value="">全部课程</option>
              <option v-for="course in courses" :key="course" :value="course">{{ course }}</option>
            </select>
          </div>
        </div>
      </div>
      <div class="p-6">
        <div class="overflow-x-auto">
          <table class="min-w-full divide-y divide-gray-200">
            <thead>
              <tr>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">课程名称</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">班级</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">总进度</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">最近更新</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">状态</th>
              </tr>
            </thead>
            <tbody class="bg-white divide-y divide-gray-200">
              <tr v-for="course in filteredCourses" :key="course.id">
                <td class="px-6 py-4 whitespace-nowrap">
                  <div class="text-sm font-medium text-gray-900">{{ course.name }}</div>
                </td>
                <td class="px-6 py-4 whitespace-nowrap">
                  <div class="text-sm text-gray-900">{{ course.class }}</div>
                </td>
                <td class="px-6 py-4 whitespace-nowrap">
                  <div class="flex items-center">
                    <div class="flex-1 h-2 bg-gray-200 rounded-full mr-2">
                      <div 
                        class="h-2 rounded-full" 
                        :style="{ width: course.progress + '%', backgroundColor: getProgressColor(course.progress) }"
                      ></div>
                    </div>
                    <span class="text-sm text-gray-900">{{ course.progress }}%</span>
                  </div>
                </td>
                <td class="px-6 py-4 whitespace-nowrap">
                  <div class="text-sm text-gray-500">{{ course.lastUpdate }}</div>
                </td>
                <td class="px-6 py-4 whitespace-nowrap">
                  <span 
                    class="px-2 py-1 text-xs rounded-full" 
                    :class="getStatusClass(course.status)"
                  >
                    {{ course.status }}
                  </span>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>

    <!-- 进度趋势 -->
    <div class="bg-white rounded-lg shadow-sm border border-gray-200">
      <div class="p-6 border-b border-gray-200">
        <h2 class="text-lg font-semibold text-gray-900">进度趋势</h2>
      </div>
      <div class="p-6">
        <div class="grid grid-cols-1 md:grid-cols-2 gap-8">
          <!-- 每周进度趋势 -->
          <div>
            <h3 class="text-sm font-medium text-gray-700 mb-4">每周进度趋势</h3>
            <div class="h-64 bg-gray-50 rounded-lg border border-gray-200 p-4">
              <!-- 这里可以添加图表组件 -->
              <div class="flex items-center justify-center h-full text-gray-500">
                <p>图表区域</p>
              </div>
            </div>
          </div>

          <!-- 课程完成预测 -->
          <div>
            <h3 class="text-sm font-medium text-gray-700 mb-4">课程完成预测</h3>
            <div class="h-64 bg-gray-50 rounded-lg border border-gray-200 p-4">
              <!-- 这里可以添加图表组件 -->
              <div class="flex items-center justify-center h-full text-gray-500">
                <p>图表区域</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'

// 筛选选项
const selectedClass = ref('')
const selectedCourse = ref('')

// 班级和课程列表
const classes = ['计算机科学 1班', '计算机科学 2班', '软件工程 1班', '软件工程 2班']
const courses = ['高等数学', '线性代数', '概率论', '数据结构', '计算机网络']

// 课程进度数据
const courseProgressData = ref([
  {
    id: 1,
    name: '高等数学',
    class: '计算机科学 1班',
    progress: 85,
    lastUpdate: '2024-03-15',
    status: '正常'
  },
  {
    id: 2,
    name: '线性代数',
    class: '计算机科学 2班',
    progress: 92,
    lastUpdate: '2024-03-14',
    status: '超前'
  },
  {
    id: 3,
    name: '概率论',
    class: '软件工程 1班',
    progress: 45,
    lastUpdate: '2024-03-13',
    status: '落后'
  },
  {
    id: 4,
    name: '数据结构',
    class: '软件工程 2班',
    progress: 78,
    lastUpdate: '2024-03-15',
    status: '正常'
  }
])

// 筛选课程列表
const filteredCourses = computed(() => {
  return courseProgressData.value.filter(course => {
    if (selectedClass.value && course.class !== selectedClass.value) return false
    if (selectedCourse.value && course.name !== selectedCourse.value) return false
    return true
  })
})

// 获取进度条颜色
const getProgressColor = (progress) => {
  if (progress >= 90) return '#10B981' // 绿色
  if (progress >= 70) return '#3B82F6' // 蓝色
  if (progress >= 50) return '#F59E0B' // 黄色
  return '#EF4444' // 红色
}

// 获取状态样式
const getStatusClass = (status) => {
  const classes = {
    '正常': 'bg-blue-100 text-blue-800',
    '超前': 'bg-green-100 text-green-800',
    '落后': 'bg-red-100 text-red-800'
  }
  return classes[status] || 'bg-gray-100 text-gray-800'
}
</script> 