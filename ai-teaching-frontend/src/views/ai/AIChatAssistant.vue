<!-- 智能助教对话页面 -->
<template>
  <div class="h-[calc(100vh-7rem)] flex">
    <!-- 左侧会话列表 -->
    <div class="w-64 bg-white border-r border-gray-200 flex flex-col">
      <div class="p-4 border-b border-gray-200">
        <button class="w-full btn-primary">
          <i class="fas fa-plus mr-2"></i>新建会话
        </button>
      </div>
      <div class="flex-1 overflow-y-auto p-2 space-y-2">
        <div 
          v-for="i in 5" 
          :key="i"
          class="p-3 rounded-lg cursor-pointer hover:bg-gray-50 transition-colors"
          :class="i === 1 ? 'bg-blue-50' : ''"
        >
          <div class="flex items-center justify-between">
            <div class="flex items-center">
              <i class="fas fa-comments text-blue-500 mr-2"></i>
              <span class="text-sm font-medium">教学设计讨论 {{ i }}</span>
            </div>
            <button class="text-gray-400 hover:text-gray-600">
              <i class="fas fa-ellipsis-v"></i>
            </button>
          </div>
          <p class="mt-1 text-xs text-gray-500 truncate">
            上次对话：{{ new Date().toLocaleString() }}
          </p>
        </div>
      </div>
    </div>

    <!-- 右侧对话区域 -->
    <div class="flex-1 bg-gray-50 flex flex-col">
      <!-- 对话头部 -->
      <div class="bg-white border-b border-gray-200 p-4">
        <div class="flex items-center justify-between">
          <div class="flex items-center">
            <div class="w-10 h-10 rounded-full bg-blue-100 flex items-center justify-center">
              <i class="fas fa-robot text-blue-500"></i>
            </div>
            <div class="ml-3">
              <h3 class="text-lg font-medium">智能助教</h3>
              <p class="text-sm text-gray-500">在线 · 随时为您提供帮助</p>
            </div>
          </div>
          <div class="flex items-center space-x-4">
            <button class="btn-outline-sm">
              <i class="fas fa-cog mr-1"></i>设置
            </button>
            <button class="btn-outline-sm">
              <i class="fas fa-expand-alt mr-1"></i>全屏
            </button>
          </div>
        </div>
      </div>

      <!-- 对话内容区 -->
      <div class="flex-1 overflow-y-auto p-4 space-y-6">
        <!-- AI消息 -->
        <div class="flex items-start">
          <div class="w-8 h-8 rounded-full bg-blue-100 flex items-center justify-center flex-shrink-0">
            <i class="fas fa-robot text-blue-500 text-sm"></i>
          </div>
          <div class="ml-4 bg-white rounded-lg p-4 shadow-sm max-w-3xl">
            <p class="text-gray-800">
              您好！我是您的智能助教。我可以帮助您进行教学设计、课程规划、教案编写等工作。请问有什么我可以帮您的吗？
            </p>
          </div>
        </div>

        <!-- 用户消息 -->
        <div class="flex items-start justify-end">
          <div class="mr-4 bg-blue-500 rounded-lg p-4 shadow-sm max-w-3xl">
            <p class="text-white">
              我需要设计一节Python编程基础课程，主题是"循环结构"，你能帮我吗？
            </p>
          </div>
          <div class="w-8 h-8 rounded-full bg-gray-200 flex items-center justify-center flex-shrink-0">
            <i class="fas fa-user text-gray-500 text-sm"></i>
          </div>
        </div>

        <!-- AI消息 -->
        <div class="flex items-start">
          <div class="w-8 h-8 rounded-full bg-blue-100 flex items-center justify-center flex-shrink-0">
            <i class="fas fa-robot text-blue-500 text-sm"></i>
          </div>
          <div class="ml-4 bg-white rounded-lg p-4 shadow-sm max-w-3xl">
            <p class="text-gray-800">
              当然可以帮您设计。让我们从以下几个方面来规划这节课：
            </p>
            <ol class="mt-2 space-y-2 list-decimal list-inside">
              <li>教学目标设定</li>
              <li>知识点梳理</li>
              <li>教学重难点分析</li>
              <li>教学活动设计</li>
              <li>案例和练习准备</li>
            </ol>
            <p class="mt-2 text-gray-800">
              您想先从哪个方面开始讨论？
            </p>
          </div>
        </div>
      </div>

      <!-- 输入区域 -->
      <div class="bg-white border-t border-gray-200 p-4">
        <div class="flex items-end space-x-4">
          <div class="flex-1">
            <textarea 
              rows="3" 
              class="w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 resize-none"
              placeholder="输入您的问题..."
            ></textarea>
          </div>
          <div class="flex items-center space-x-2">
            <button class="btn-icon" title="上传文件">
              <i class="fas fa-paperclip"></i>
            </button>
            <button class="btn-icon" title="插入代码">
              <i class="fas fa-code"></i>
            </button>
            <button class="btn-primary px-6">
              发送
            </button>
          </div>
        </div>
        <div class="mt-2 flex items-center justify-between text-xs text-gray-500">
          <div class="flex items-center space-x-4">
            <span>Shift + Enter 换行</span>
            <span>Enter 发送</span>
          </div>
          <div class="flex items-center space-x-2">
            <button class="hover:text-gray-700">
              <i class="fas fa-keyboard mr-1"></i>快捷键
            </button>
            <button class="hover:text-gray-700">
              <i class="fas fa-cog mr-1"></i>设置
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
.btn-primary {
  @apply px-4 py-2 bg-blue-500 text-white rounded-md hover:bg-blue-600 transition-colors;
}

.btn-outline-sm {
  @apply px-3 py-1 border border-gray-300 rounded-md hover:bg-gray-50 transition-colors;
}

.btn-icon {
  @apply w-8 h-8 flex items-center justify-center rounded-full text-gray-500 hover:bg-gray-100 transition-colors;
}
</style> 