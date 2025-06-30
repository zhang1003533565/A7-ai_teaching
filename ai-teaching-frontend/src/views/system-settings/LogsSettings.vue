<template>
  <div>
    <!-- 面包屑导航 -->
    <div class="mb-6 flex items-center text-sm text-gray-500">
      <span class="cursor-pointer hover:text-blue-600">首页</span>
      <i class="fas fa-chevron-right mx-2 text-xs"></i>
      <span class="cursor-pointer hover:text-blue-600">系统管理</span>
      <i class="fas fa-chevron-right mx-2 text-xs"></i>
      <span class="cursor-pointer hover:text-blue-600">系统设置</span>
      <i class="fas fa-chevron-right mx-2 text-xs"></i>
      <span class="text-gray-700">日志管理</span>
    </div>

    <!-- 页面标题 -->
    <div class="mb-6">
      <h1 class="text-2xl font-bold text-gray-900">日志管理</h1>
      <p class="mt-2 text-gray-600">查看和管理系统运行日志</p>
    </div>

    <!-- 日志统计 -->
    <div class="grid grid-cols-1 md:grid-cols-4 gap-4 mb-6">
      <div class="bg-white rounded-lg shadow-sm p-4">
        <div class="flex items-center">
          <div class="p-2 bg-blue-100 rounded-lg">
            <i class="fas fa-info-circle text-blue-600"></i>
          </div>
          <div class="ml-3">
            <p class="text-sm font-medium text-gray-500">信息日志</p>
            <p class="text-lg font-semibold text-gray-900">1,234</p>
          </div>
        </div>
      </div>
      <div class="bg-white rounded-lg shadow-sm p-4">
        <div class="flex items-center">
          <div class="p-2 bg-yellow-100 rounded-lg">
            <i class="fas fa-exclamation-triangle text-yellow-600"></i>
          </div>
          <div class="ml-3">
            <p class="text-sm font-medium text-gray-500">警告日志</p>
            <p class="text-lg font-semibold text-gray-900">45</p>
          </div>
        </div>
      </div>
      <div class="bg-white rounded-lg shadow-sm p-4">
        <div class="flex items-center">
          <div class="p-2 bg-red-100 rounded-lg">
            <i class="fas fa-times-circle text-red-600"></i>
          </div>
          <div class="ml-3">
            <p class="text-sm font-medium text-gray-500">错误日志</p>
            <p class="text-lg font-semibold text-gray-900">12</p>
          </div>
        </div>
      </div>
      <div class="bg-white rounded-lg shadow-sm p-4">
        <div class="flex items-center">
          <div class="p-2 bg-gray-100 rounded-lg">
            <i class="fas fa-file-alt text-gray-600"></i>
          </div>
          <div class="ml-3">
            <p class="text-sm font-medium text-gray-500">总日志量</p>
            <p class="text-lg font-semibold text-gray-900">1,291</p>
          </div>
        </div>
      </div>
    </div>

    <!-- 日志搜索和筛选 -->
    <div class="bg-white rounded-lg shadow-sm p-6 mb-6">
      <h2 class="text-lg font-semibold text-gray-900 mb-4">
        <i class="fas fa-search text-blue-600 mr-2"></i>日志查询
      </h2>
      <div class="grid grid-cols-1 md:grid-cols-4 gap-4">
        <div>
          <label class="block text-sm font-medium text-gray-700 mb-2">日志级别</label>
          <select
            v-model="filters.level"
            class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
          >
            <option value="">全部级别</option>
            <option value="info">信息</option>
            <option value="warning">警告</option>
            <option value="error">错误</option>
            <option value="debug">调试</option>
          </select>
        </div>
        <div>
          <label class="block text-sm font-medium text-gray-700 mb-2">日志模块</label>
          <select
            v-model="filters.module"
            class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
          >
            <option value="">全部模块</option>
            <option value="auth">认证模块</option>
            <option value="user">用户管理</option>
            <option value="course">课程管理</option>
            <option value="system">系统模块</option>
          </select>
        </div>
        <div>
          <label class="block text-sm font-medium text-gray-700 mb-2">开始时间</label>
          <input
            type="datetime-local"
            v-model="filters.startTime"
            class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
          />
        </div>
        <div>
          <label class="block text-sm font-medium text-gray-700 mb-2">结束时间</label>
          <input
            type="datetime-local"
            v-model="filters.endTime"
            class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
          />
        </div>
      </div>
      <div class="mt-4">
        <label class="block text-sm font-medium text-gray-700 mb-2">关键词搜索</label>
        <div class="flex space-x-2">
          <input
            type="text"
            v-model="filters.keyword"
            class="flex-1 px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
            placeholder="输入关键词搜索日志内容..."
          />
          <button
            @click="searchLogs"
            class="px-4 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700 transition-colors"
          >
            搜索
          </button>
          <button
            @click="clearFilters"
            class="px-4 py-2 bg-gray-100 text-gray-700 rounded-md hover:bg-gray-200 transition-colors"
          >
            清空
          </button>
        </div>
      </div>
    </div>

    <!-- 日志列表 -->
    <div class="bg-white rounded-lg shadow-sm p-6">
      <div class="flex justify-between items-center mb-4">
        <h2 class="text-lg font-semibold text-gray-900">
          <i class="fas fa-list text-purple-600 mr-2"></i>日志记录
        </h2>
        <div class="flex space-x-2">
          <button
            @click="exportLogs"
            class="px-4 py-2 bg-green-600 text-white rounded-md hover:bg-green-700 transition-colors text-sm"
          >
            <i class="fas fa-download mr-1"></i>导出
          </button>
          <button
            @click="clearLogs"
            class="px-4 py-2 bg-red-600 text-white rounded-md hover:bg-red-700 transition-colors text-sm"
          >
            <i class="fas fa-trash mr-1"></i>清空
          </button>
        </div>
      </div>
      
      <div class="overflow-x-auto">
        <table class="min-w-full divide-y divide-gray-200">
          <thead class="bg-gray-50">
            <tr>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                时间
              </th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                级别
              </th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                模块
              </th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                用户
              </th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                消息
              </th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                操作
              </th>
            </tr>
          </thead>
          <tbody class="bg-white divide-y divide-gray-200">
            <tr v-for="log in filteredLogs" :key="log.id" class="hover:bg-gray-50">
              <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
                {{ formatTime(log.timestamp) }}
              </td>
              <td class="px-6 py-4 whitespace-nowrap">
                <span 
                  :class="[
                    'px-2 inline-flex text-xs leading-5 font-semibold rounded-full',
                    getLevelClass(log.level)
                  ]"
                >
                  {{ getLevelText(log.level) }}
                </span>
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
                {{ log.module }}
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
                {{ log.user || '-' }}
              </td>
              <td class="px-6 py-4 text-sm text-gray-900 max-w-md truncate">
                {{ log.message }}
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm font-medium">
                <button
                  @click="viewLogDetail(log)"
                  class="text-blue-600 hover:text-blue-900"
                >
                  详情
                </button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      
      <!-- 分页 -->
      <div class="mt-4 flex items-center justify-between">
        <div class="text-sm text-gray-700">
          显示 {{ (currentPage - 1) * pageSize + 1 }} 到 {{ Math.min(currentPage * pageSize, totalLogs) }} 条，共 {{ totalLogs }} 条记录
        </div>
        <div class="flex space-x-2">
          <button
            @click="previousPage"
            :disabled="currentPage === 1"
            class="px-3 py-1 border border-gray-300 rounded-md text-sm disabled:opacity-50"
          >
            上一页
          </button>
          <span class="px-3 py-1 text-sm">{{ currentPage }} / {{ totalPages }}</span>
          <button
            @click="nextPage"
            :disabled="currentPage === totalPages"
            class="px-3 py-1 border border-gray-300 rounded-md text-sm disabled:opacity-50"
          >
            下一页
          </button>
        </div>
      </div>
    </div>

    <!-- 日志详情模态框 -->
    <div v-if="showLogDetail" class="fixed inset-0 bg-gray-600 bg-opacity-50 overflow-y-auto h-full w-full z-50">
      <div class="relative top-20 mx-auto p-5 border w-11/12 md:w-3/4 lg:w-1/2 shadow-lg rounded-md bg-white">
        <div class="flex justify-between items-center mb-4">
          <h3 class="text-lg font-medium text-gray-900">日志详情</h3>
          <button
            @click="closeLogDetail"
            class="text-gray-400 hover:text-gray-600"
          >
            <i class="fas fa-times"></i>
          </button>
        </div>
        <div v-if="selectedLog" class="space-y-3">
          <div class="grid grid-cols-2 gap-4">
            <div>
              <span class="text-sm font-medium text-gray-500">时间:</span>
              <span class="text-sm text-gray-900 ml-2">{{ formatTime(selectedLog.timestamp) }}</span>
            </div>
            <div>
              <span class="text-sm font-medium text-gray-500">级别:</span>
              <span :class="['text-sm ml-2', getLevelTextClass(selectedLog.level)]">
                {{ getLevelText(selectedLog.level) }}
              </span>
            </div>
            <div>
              <span class="text-sm font-medium text-gray-500">模块:</span>
              <span class="text-sm text-gray-900 ml-2">{{ selectedLog.module }}</span>
            </div>
            <div>
              <span class="text-sm font-medium text-gray-500">用户:</span>
              <span class="text-sm text-gray-900 ml-2">{{ selectedLog.user || '-' }}</span>
            </div>
          </div>
          <div>
            <span class="text-sm font-medium text-gray-500">消息:</span>
            <p class="text-sm text-gray-900 mt-1 p-3 bg-gray-50 rounded">{{ selectedLog.message }}</p>
          </div>
          <div v-if="selectedLog.details">
            <span class="text-sm font-medium text-gray-500">详细信息:</span>
            <pre class="text-xs text-gray-900 mt-1 p-3 bg-gray-50 rounded overflow-x-auto">{{ selectedLog.details }}</pre>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive, computed } from 'vue'

// 筛选条件
const filters = reactive({
  level: '',
  module: '',
  startTime: '',
  endTime: '',
  keyword: ''
})

// 分页
const currentPage = ref(1)
const pageSize = ref(20)
const totalLogs = ref(256)

// 日志详情
const showLogDetail = ref(false)
const selectedLog = ref(null)

// 计算属性
const totalPages = computed(() => Math.ceil(totalLogs.value / pageSize.value))

// 模拟日志数据
const logs = reactive([
  {
    id: 1,
    timestamp: '2024-01-15T10:30:25',
    level: 'info',
    module: '用户管理',
    user: 'admin',
    message: '用户 teacher001 登录成功',
    details: 'IP: 192.168.1.100\nUser-Agent: Mozilla/5.0...'
  },
  {
    id: 2,
    timestamp: '2024-01-15T10:28:15',
    level: 'warning',
    module: '认证模块',
    user: 'student001',
    message: '密码错误次数过多，账户已被锁定',
    details: 'Failed attempts: 5\nLock duration: 30 minutes'
  },
  {
    id: 3,
    timestamp: '2024-01-15T10:25:42',
    level: 'error',
    module: '系统模块',
    user: null,
    message: '数据库连接超时',
    details: 'Connection timeout after 30 seconds\nDatabase: mysql://localhost:3306/ai_teaching'
  },
  {
    id: 4,
    timestamp: '2024-01-15T10:20:18',
    level: 'info',
    module: '课程管理',
    user: 'teacher001',
    message: '创建了新课程：Vue.js进阶教程',
    details: 'Course ID: 12345\nStudent limit: 50'
  }
])

// 筛选后的日志
const filteredLogs = computed(() => {
  return logs.filter(log => {
    if (filters.level && log.level !== filters.level) return false
    if (filters.module && log.module !== filters.module) return false
    if (filters.keyword && !log.message.toLowerCase().includes(filters.keyword.toLowerCase())) return false
    return true
  })
})

// 格式化时间
const formatTime = (timestamp) => {
  return new Date(timestamp).toLocaleString('zh-CN')
}

// 获取级别样式类
const getLevelClass = (level) => {
  const classes = {
    info: 'bg-blue-100 text-blue-800',
    warning: 'bg-yellow-100 text-yellow-800',
    error: 'bg-red-100 text-red-800',
    debug: 'bg-gray-100 text-gray-800'
  }
  return classes[level] || 'bg-gray-100 text-gray-800'
}

// 获取级别文本样式类
const getLevelTextClass = (level) => {
  const classes = {
    info: 'text-blue-600',
    warning: 'text-yellow-600',
    error: 'text-red-600',
    debug: 'text-gray-600'
  }
  return classes[level] || 'text-gray-600'
}

// 获取级别文本
const getLevelText = (level) => {
  const texts = {
    info: '信息',
    warning: '警告',
    error: '错误',
    debug: '调试'
  }
  return texts[level] || level
}

// 搜索日志
const searchLogs = () => {
  console.log('搜索日志:', filters)
  // 这里应该调用API搜索日志
}

// 清空筛选条件
const clearFilters = () => {
  Object.assign(filters, {
    level: '',
    module: '',
    startTime: '',
    endTime: '',
    keyword: ''
  })
}

// 查看日志详情
const viewLogDetail = (log) => {
  selectedLog.value = log
  showLogDetail.value = true
}

// 关闭日志详情
const closeLogDetail = () => {
  showLogDetail.value = false
  selectedLog.value = null
}

// 导出日志
const exportLogs = () => {
  console.log('导出日志')
  alert('正在导出日志文件...')
}

// 清空日志
const clearLogs = () => {
  if (confirm('确定要清空所有日志吗？此操作不可撤销！')) {
    console.log('清空日志')
    logs.splice(0, logs.length)
    totalLogs.value = 0
  }
}

// 分页方法
const previousPage = () => {
  if (currentPage.value > 1) {
    currentPage.value--
  }
}

const nextPage = () => {
  if (currentPage.value < totalPages.value) {
    currentPage.value++
  }
}
</script> 