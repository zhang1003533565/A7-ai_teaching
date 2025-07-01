<template>
  <div class="p-6 bg-gray-50 min-h-screen">
    <div class="mb-8">
      <h1 class="text-2xl font-bold text-gray-900 mb-2">知识图谱</h1>
      <p class="text-gray-600">可视化展示学生的知识掌握情况和学习路径</p>
    </div>

    <!-- 知识点掌握概览 -->
    <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6 mb-8">
      <div class="bg-white p-6 rounded-lg shadow-sm border border-gray-200">
        <div class="flex items-center justify-between mb-4">
          <div>
            <p class="text-sm text-gray-600">已掌握知识点</p>
            <p class="text-2xl font-bold text-gray-900">156</p>
          </div>
          <div class="w-12 h-12 bg-blue-100 rounded-lg flex items-center justify-center">
            <i class="fas fa-check-circle text-blue-600"></i>
          </div>
        </div>
        <p class="text-sm text-gray-500">较上月 <span class="text-green-600">+23</span></p>
      </div>

      <div class="bg-white p-6 rounded-lg shadow-sm border border-gray-200">
        <div class="flex items-center justify-between mb-4">
          <div>
            <p class="text-sm text-gray-600">待巩固知识点</p>
            <p class="text-2xl font-bold text-gray-900">45</p>
          </div>
          <div class="w-12 h-12 bg-yellow-100 rounded-lg flex items-center justify-center">
            <i class="fas fa-exclamation-circle text-yellow-600"></i>
          </div>
        </div>
        <p class="text-sm text-gray-500">较上月 <span class="text-red-600">+8</span></p>
      </div>

      <div class="bg-white p-6 rounded-lg shadow-sm border border-gray-200">
        <div class="flex items-center justify-between mb-4">
          <div>
            <p class="text-sm text-gray-600">知识关联度</p>
            <p class="text-2xl font-bold text-gray-900">82%</p>
          </div>
          <div class="w-12 h-12 bg-purple-100 rounded-lg flex items-center justify-center">
            <i class="fas fa-project-diagram text-purple-600"></i>
          </div>
        </div>
        <p class="text-sm text-gray-500">较上月 <span class="text-green-600">+5%</span></p>
      </div>

      <div class="bg-white p-6 rounded-lg shadow-sm border border-gray-200">
        <div class="flex items-center justify-between mb-4">
          <div>
            <p class="text-sm text-gray-600">学习路径完成</p>
            <p class="text-2xl font-bold text-gray-900">75%</p>
          </div>
          <div class="w-12 h-12 bg-green-100 rounded-lg flex items-center justify-center">
            <i class="fas fa-route text-green-600"></i>
          </div>
        </div>
        <p class="text-sm text-gray-500">较上月 <span class="text-green-600">+8%</span></p>
      </div>
    </div>

    <!-- 知识图谱可视化 -->
    <div class="bg-white rounded-lg shadow-sm border border-gray-200 mb-8">
      <div class="p-6 border-b border-gray-200">
        <div class="flex justify-between items-center">
          <h2 class="text-lg font-semibold text-gray-900">知识图谱可视化</h2>
          <div class="flex space-x-2">
            <select v-model="selectedSubject" class="border border-gray-300 rounded-lg px-3 py-2 text-sm">
              <option value="">全部科目</option>
              <option v-for="subject in subjects" :key="subject" :value="subject">{{ subject }}</option>
            </select>
            <select v-model="selectedChapter" class="border border-gray-300 rounded-lg px-3 py-2 text-sm">
              <option value="">全部章节</option>
              <option v-for="chapter in chapters" :key="chapter" :value="chapter">{{ chapter }}</option>
            </select>
          </div>
        </div>
      </div>
      <div class="p-6">
        <div class="h-96 bg-gray-50 rounded-lg border border-gray-200 p-4">
          <!-- 这里可以添加知识图谱可视化组件 -->
          <div class="flex items-center justify-center h-full text-gray-500">
            <p>知识图谱可视化区域</p>
          </div>
        </div>
      </div>
    </div>

    <!-- 知识点掌握详情 -->
    <div class="bg-white rounded-lg shadow-sm border border-gray-200">
      <div class="p-6 border-b border-gray-200">
        <h2 class="text-lg font-semibold text-gray-900">知识点掌握详情</h2>
      </div>
      <div class="p-6">
        <div class="overflow-x-auto">
          <table class="min-w-full divide-y divide-gray-200">
            <thead>
              <tr>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">知识点</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">所属章节</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">掌握程度</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">练习次数</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">最近复习</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">状态</th>
              </tr>
            </thead>
            <tbody class="bg-white divide-y divide-gray-200">
              <tr v-for="point in filteredKnowledgePoints" :key="point.id">
                <td class="px-6 py-4 whitespace-nowrap">
                  <div class="text-sm font-medium text-gray-900">{{ point.name }}</div>
                </td>
                <td class="px-6 py-4 whitespace-nowrap">
                  <div class="text-sm text-gray-900">{{ point.chapter }}</div>
                </td>
                <td class="px-6 py-4 whitespace-nowrap">
                  <div class="flex items-center">
                    <div class="w-24 bg-gray-200 rounded-full h-2 mr-2">
                      <div 
                        class="h-2 rounded-full"
                        :style="{ width: point.mastery + '%', backgroundColor: getMasteryColor(point.mastery) }"
                      ></div>
                    </div>
                    <span class="text-sm text-gray-900">{{ point.mastery }}%</span>
                  </div>
                </td>
                <td class="px-6 py-4 whitespace-nowrap">
                  <div class="text-sm text-gray-900">{{ point.practiceCount }}</div>
                </td>
                <td class="px-6 py-4 whitespace-nowrap">
                  <div class="text-sm text-gray-500">{{ point.lastReview }}</div>
                </td>
                <td class="px-6 py-4 whitespace-nowrap">
                  <span 
                    class="px-2 py-1 text-xs rounded-full" 
                    :class="getStatusClass(point.status)"
                  >
                    {{ point.status }}
                  </span>
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
const selectedSubject = ref('')
const selectedChapter = ref('')

// 科目和章节列表
const subjects = ['高等数学', '线性代数', '概率论', '数据结构', '计算机网络']
const chapters = ['第一章', '第二章', '第三章', '第四章', '第五章']

// 知识点数据
const knowledgePointsData = ref([
  {
    id: 1,
    name: '函数极限',
    chapter: '第一章',
    mastery: 95,
    practiceCount: 12,
    lastReview: '2024-03-15',
    status: '已掌握'
  },
  {
    id: 2,
    name: '导数定义',
    chapter: '第二章',
    mastery: 85,
    practiceCount: 8,
    lastReview: '2024-03-14',
    status: '良好'
  },
  {
    id: 3,
    name: '积分应用',
    chapter: '第三章',
    mastery: 65,
    practiceCount: 5,
    lastReview: '2024-03-13',
    status: '需要巩固'
  }
])

// 筛选知识点列表
const filteredKnowledgePoints = computed(() => {
  return knowledgePointsData.value.filter(point => {
    if (selectedChapter.value && point.chapter !== selectedChapter.value) return false
    return true
  })
})

// 获取掌握度颜色
const getMasteryColor = (value) => {
  if (value >= 90) return '#10B981'
  if (value >= 70) return '#3B82F6'
  if (value >= 50) return '#F59E0B'
  return '#EF4444'
}

// 获取状态样式
const getStatusClass = (status) => {
  const classes = {
    '已掌握': 'bg-green-100 text-green-800',
    '良好': 'bg-blue-100 text-blue-800',
    '需要巩固': 'bg-yellow-100 text-yellow-800',
    '待学习': 'bg-gray-100 text-gray-800'
  }
  return classes[status] || 'bg-gray-100 text-gray-800'
}
</script> 