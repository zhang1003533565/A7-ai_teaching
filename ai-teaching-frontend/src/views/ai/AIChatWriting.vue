<!-- 写作助手页面 -->
<template>
  <div class="h-[calc(100vh-7rem)] flex bg-gray-50">
    <!-- 左侧编辑区 -->
    <div class="flex-1 flex flex-col">
      <!-- 工具栏 -->
      <div class="bg-white border-b border-gray-200 p-4">
        <div class="flex items-center justify-between">
          <div class="flex items-center space-x-4">
            <button class="btn-primary">
              <i class="fas fa-save mr-2"></i>保存
            </button>
            <button class="btn-outline">
              <i class="fas fa-download mr-2"></i>导出
            </button>
          </div>
          <div class="flex items-center space-x-2">
            <button class="btn-icon" title="撤销">
              <i class="fas fa-undo"></i>
            </button>
            <button class="btn-icon" title="重做">
              <i class="fas fa-redo"></i>
            </button>
            <div class="h-4 border-r border-gray-300 mx-2"></div>
            <button class="btn-icon" title="字体">
              <i class="fas fa-font"></i>
            </button>
            <button class="btn-icon" title="段落">
              <i class="fas fa-paragraph"></i>
            </button>
            <button class="btn-icon" title="格式">
              <i class="fas fa-text-width"></i>
            </button>
          </div>
        </div>
      </div>

      <!-- 编辑区 -->
      <div class="flex-1 p-6 overflow-y-auto">
        <div class="max-w-3xl mx-auto bg-white rounded-lg shadow-sm p-6">
          <input
            type="text"
            class="w-full text-2xl font-bold border-0 focus:outline-none mb-4"
            placeholder="输入标题..."
          />
          <div class="prose prose-sm max-w-none">
            <textarea
              class="w-full h-[calc(100vh-20rem)] resize-none border-0 focus:outline-none"
              placeholder="开始写作..."
            ></textarea>
          </div>
        </div>
      </div>

      <!-- 状态栏 -->
      <div class="bg-white border-t border-gray-200 p-2">
        <div class="flex items-center justify-between text-sm text-gray-500">
          <div class="flex items-center space-x-4">
            <span>字数：2,345</span>
            <span>预计阅读时间：5分钟</span>
          </div>
          <div class="flex items-center space-x-2">
            <span>最后保存：2分钟前</span>
            <span>|</span>
            <span>自动保存已开启</span>
          </div>
        </div>
      </div>
    </div>

    <!-- 右侧助手面板 -->
    <div class="w-80 bg-white border-l border-gray-200 flex flex-col">
      <!-- 助手标题 -->
      <div class="p-4 border-b border-gray-200">
        <h3 class="text-lg font-medium">写作助手</h3>
        <p class="text-sm text-gray-500 mt-1">
          智能写作建议和优化
        </p>
      </div>

      <!-- 助手功能区 -->
      <div class="flex-1 overflow-y-auto p-4">
        <!-- 写作建议 -->
        <div class="mb-6">
          <h4 class="text-sm font-medium text-gray-700 mb-2">写作建议</h4>
          <div class="space-y-2">
            <button class="w-full text-left p-2 rounded hover:bg-blue-50 text-sm">
              <i class="fas fa-magic text-blue-500 mr-2"></i>
              生成大纲
            </button>
            <button class="w-full text-left p-2 rounded hover:bg-blue-50 text-sm">
              <i class="fas fa-lightbulb text-blue-500 mr-2"></i>
              内容展开
            </button>
            <button class="w-full text-left p-2 rounded hover:bg-blue-50 text-sm">
              <i class="fas fa-sync text-blue-500 mr-2"></i>
              改写优化
            </button>
          </div>
        </div>

        <!-- 语言优化 -->
        <div class="mb-6">
          <h4 class="text-sm font-medium text-gray-700 mb-2">语言优化</h4>
          <div class="space-y-2">
            <button class="w-full text-left p-2 rounded hover:bg-blue-50 text-sm">
              <i class="fas fa-spell-check text-blue-500 mr-2"></i>
              语法检查
            </button>
            <button class="w-full text-left p-2 rounded hover:bg-blue-50 text-sm">
              <i class="fas fa-text-height text-blue-500 mr-2"></i>
              用词建议
            </button>
            <button class="w-full text-left p-2 rounded hover:bg-blue-50 text-sm">
              <i class="fas fa-align-left text-blue-500 mr-2"></i>
              句式优化
            </button>
          </div>
        </div>

        <!-- 写作分析 -->
        <div>
          <h4 class="text-sm font-medium text-gray-700 mb-2">写作分析</h4>
          <div class="bg-gray-50 rounded-lg p-4">
            <div class="flex items-center justify-between mb-3">
              <span class="text-sm text-gray-600">可读性评分</span>
              <span class="text-sm font-medium text-blue-600">85/100</span>
            </div>
            <div class="space-y-2">
              <div>
                <div class="flex items-center justify-between text-xs text-gray-500">
                  <span>句子长度</span>
                  <span>良好</span>
                </div>
                <div class="h-2 bg-gray-200 rounded-full mt-1">
                  <div class="h-2 bg-blue-500 rounded-full" style="width: 80%"></div>
                </div>
              </div>
              <div>
                <div class="flex items-center justify-between text-xs text-gray-500">
                  <span>词汇丰富度</span>
                  <span>优秀</span>
                </div>
                <div class="h-2 bg-gray-200 rounded-full mt-1">
                  <div class="h-2 bg-blue-500 rounded-full" style="width: 90%"></div>
                </div>
              </div>
              <div>
                <div class="flex items-center justify-between text-xs text-gray-500">
                  <span>段落结构</span>
                  <span>良好</span>
                </div>
                <div class="h-2 bg-gray-200 rounded-full mt-1">
                  <div class="h-2 bg-blue-500 rounded-full" style="width: 75%"></div>
                </div>
              </div>
            </div>
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

.btn-outline {
  @apply px-4 py-2 border border-gray-300 rounded-md hover:bg-gray-50 transition-colors;
}

.btn-icon {
  @apply w-8 h-8 flex items-center justify-center rounded-full text-gray-500 hover:bg-gray-100 transition-colors;
}

/* 自定义编辑器样式 */
.prose :deep(p) {
  margin: 0;
  line-height: 1.75;
}
</style> 