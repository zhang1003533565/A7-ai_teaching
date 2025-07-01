<!-- 对话历史页面 -->
<template>
  <div class="h-[calc(100vh-7rem)] flex bg-gray-50">
    <!-- 左侧筛选面板 -->
    <div class="w-64 bg-white border-r border-gray-200 flex flex-col">
      <div class="p-4 border-b border-gray-200">
        <h3 class="text-lg font-medium">对话历史</h3>
        <p class="text-sm text-gray-500 mt-1">
          查看和管理您的历史对话记录
        </p>
      </div>
      
      <!-- 筛选选项 -->
      <div class="p-4 border-b border-gray-200">
        <div class="space-y-4">
          <!-- 时间范围 -->
          <div>
            <label class="block text-sm font-medium text-gray-700 mb-2">时间范围</label>
            <select class="w-full border rounded-md p-2 text-sm">
              <option>全部时间</option>
              <option>最近一周</option>
              <option>最近一月</option>
              <option>最近三月</option>
              <option>自定义</option>
            </select>
          </div>

          <!-- 对话类型 -->
          <div>
            <label class="block text-sm font-medium text-gray-700 mb-2">对话类型</label>
            <div class="space-y-2">
              <label class="flex items-center">
                <input type="checkbox" class="form-checkbox text-blue-500" checked />
                <span class="ml-2 text-sm text-gray-700">智能助教</span>
              </label>
              <label class="flex items-center">
                <input type="checkbox" class="form-checkbox text-blue-500" checked />
                <span class="ml-2 text-sm text-gray-700">问答系统</span>
              </label>
              <label class="flex items-center">
                <input type="checkbox" class="form-checkbox text-blue-500" checked />
                <span class="ml-2 text-sm text-gray-700">写作助手</span>
              </label>
              <label class="flex items-center">
                <input type="checkbox" class="form-checkbox text-blue-500" checked />
                <span class="ml-2 text-sm text-gray-700">解题分析</span>
              </label>
            </div>
          </div>

          <!-- 标签 -->
          <div>
            <label class="block text-sm font-medium text-gray-700 mb-2">标签</label>
            <div class="flex flex-wrap gap-2">
              <span class="px-2 py-1 bg-blue-100 text-blue-600 text-xs rounded-full">
                教学设计
              </span>
              <span class="px-2 py-1 bg-green-100 text-green-600 text-xs rounded-full">
                作业批改
              </span>
              <span class="px-2 py-1 bg-purple-100 text-purple-600 text-xs rounded-full">
                课程规划
              </span>
            </div>
          </div>
        </div>
      </div>

      <!-- 导出选项 -->
      <div class="p-4 mt-auto border-t border-gray-200">
        <button class="btn-outline w-full">
          <i class="fas fa-download mr-2"></i>导出记录
        </button>
      </div>
    </div>

    <!-- 右侧历史记录列表 -->
    <div class="flex-1 flex flex-col">
      <!-- 工具栏 -->
      <div class="bg-white p-4 border-b border-gray-200">
        <div class="flex items-center justify-between">
          <div class="relative">
            <input
              type="text"
              class="pl-10 pr-4 py-2 border rounded-lg w-64 focus:outline-none focus:ring-2 focus:ring-blue-500"
              placeholder="搜索对话内容..."
            />
            <i class="fas fa-search absolute left-3 top-1/2 -translate-y-1/2 text-gray-400"></i>
          </div>
          <div class="flex items-center space-x-4">
            <button class="btn-outline-sm">
              <i class="fas fa-sort mr-1"></i>排序
            </button>
            <button class="btn-outline-sm">
              <i class="fas fa-trash-alt mr-1"></i>清空
            </button>
          </div>
        </div>
      </div>

      <!-- 历史记录列表 -->
      <div class="flex-1 overflow-y-auto p-6">
        <div class="space-y-4">
          <div v-for="i in 5" :key="i" class="bg-white rounded-lg shadow-sm p-6">
            <div class="flex items-start justify-between">
              <div class="flex items-start space-x-4">
                <div class="w-10 h-10 rounded-full bg-blue-100 flex items-center justify-center">
                  <i class="fas fa-robot text-blue-500"></i>
                </div>
                <div>
                  <h4 class="text-lg font-medium">Python课程设计讨论</h4>
                  <p class="text-sm text-gray-500 mt-1">
                    与智能助教讨论Python基础课程的教学设计和课程规划...
                  </p>
                  <div class="flex items-center space-x-4 mt-2">
                    <span class="text-sm text-gray-500">
                      <i class="far fa-clock mr-1"></i>2024-03-20 14:30
                    </span>
                    <span class="text-sm text-gray-500">
                      <i class="far fa-comment mr-1"></i>12条对话
                    </span>
                    <span class="px-2 py-1 bg-blue-100 text-blue-600 text-xs rounded-full">
                      教学设计
                    </span>
                  </div>
                </div>
              </div>
              <div class="flex items-center space-x-2">
                <button class="btn-icon" title="收藏">
                  <i class="far fa-star"></i>
                </button>
                <button class="btn-icon" title="导出">
                  <i class="fas fa-download"></i>
                </button>
                <button class="btn-icon" title="删除">
                  <i class="fas fa-trash-alt"></i>
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- 分页 -->
      <div class="bg-white p-4 border-t border-gray-200">
        <div class="flex items-center justify-between">
          <span class="text-sm text-gray-500">
            共 24 条记录
          </span>
          <div class="flex items-center space-x-2">
            <button class="btn-icon" disabled>
              <i class="fas fa-chevron-left"></i>
            </button>
            <span class="px-3 py-1 bg-blue-500 text-white rounded">1</span>
            <button class="btn-icon">
              <i class="fas fa-chevron-right"></i>
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
// 组件逻辑可以在这里添加
</script>

<style scoped>
.btn-outline {
  @apply px-4 py-2 border border-gray-300 rounded-md hover:bg-gray-50 transition-colors;
}

.btn-outline-sm {
  @apply px-3 py-1 border border-gray-300 rounded-md hover:bg-gray-50 transition-colors;
}

.btn-icon {
  @apply w-8 h-8 flex items-center justify-center rounded-full text-gray-500 hover:bg-gray-100 transition-colors disabled:opacity-50 disabled:cursor-not-allowed;
}

.form-checkbox {
  @apply rounded border-gray-300 text-blue-500 focus:ring-blue-500;
}
</style> 