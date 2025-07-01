<!-- 代码评审页面 -->
<template>
  <div class="h-[calc(100vh-7rem)] flex bg-gray-50">
    <!-- 左侧代码列表 -->
    <div class="w-80 bg-white border-r border-gray-200 flex flex-col">
      <!-- 搜索和筛选 -->
      <div class="p-4 border-b border-gray-200">
        <div class="relative mb-4">
          <input
            type="text"
            class="w-full pl-10 pr-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
            placeholder="搜索代码..."
          />
          <i class="fas fa-search absolute left-3 top-1/2 -translate-y-1/2 text-gray-400"></i>
        </div>
        <div class="flex flex-wrap gap-2">
          <button class="px-3 py-1 bg-blue-100 text-blue-600 rounded-full text-sm">
            全部
          </button>
          <button class="px-3 py-1 text-gray-500 hover:bg-gray-100 rounded-full text-sm">
            待评审
          </button>
          <button class="px-3 py-1 text-gray-500 hover:bg-gray-100 rounded-full text-sm">
            已评审
          </button>
        </div>
      </div>

      <!-- 代码列表 -->
      <div class="flex-1 overflow-y-auto">
        <div class="space-y-2 p-2">
          <div 
            v-for="i in 10" 
            :key="i"
            class="p-4 rounded-lg cursor-pointer hover:bg-gray-50 transition-colors"
            :class="i === 1 ? 'bg-blue-50' : ''"
          >
            <div class="flex items-center justify-between">
              <h4 class="font-medium">数据结构实验 {{ i }}</h4>
              <span class="px-2 py-1 bg-yellow-100 text-yellow-600 text-xs rounded-full">
                待评审
              </span>
            </div>
            <div class="mt-2 text-sm text-gray-500">
              <p>提交人：李四</p>
              <p class="mt-1">文件：binary_tree.py</p>
              <p class="mt-1">提交时间：2024-03-20 14:30</p>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- 右侧评审区域 -->
    <div class="flex-1 flex flex-col">
      <!-- 工具栏 -->
      <div class="bg-white p-4 border-b border-gray-200">
        <div class="flex items-center justify-between">
          <div class="flex items-center space-x-4">
            <button class="btn-primary">
              <i class="fas fa-magic mr-2"></i>智能评审
            </button>
            <button class="btn-outline">
              <i class="fas fa-code mr-2"></i>代码对比
            </button>
            <button class="btn-outline">
              <i class="fas fa-bug mr-2"></i>运行测试
            </button>
          </div>
          <div class="flex items-center space-x-2">
            <button class="btn-icon" title="设置">
              <i class="fas fa-cog"></i>
            </button>
            <button class="btn-icon" title="全屏">
              <i class="fas fa-expand"></i>
            </button>
          </div>
        </div>
      </div>

      <!-- 代码内容和评审区 -->
      <div class="flex-1 overflow-hidden flex">
        <!-- 代码内容 -->
        <div class="w-1/2 overflow-y-auto p-6 border-r border-gray-200">
          <div class="prose prose-sm max-w-none">
            <div class="flex items-center justify-between">
              <h3>二叉树实现</h3>
              <div class="flex items-center space-x-2">
                <span class="text-sm text-gray-500">binary_tree.py</span>
                <button class="btn-icon-sm">
                  <i class="fas fa-copy"></i>
                </button>
                <button class="btn-icon-sm">
                  <i class="fas fa-download"></i>
                </button>
              </div>
            </div>
            <pre class="bg-gray-50 p-4 rounded-md mt-4 relative group">
              <div class="absolute right-2 top-2 hidden group-hover:flex items-center space-x-2">
                <button class="p-1 hover:bg-gray-200 rounded">
                  <i class="fas fa-comment text-gray-500"></i>
                </button>
                <button class="p-1 hover:bg-gray-200 rounded">
                  <i class="fas fa-exclamation-circle text-gray-500"></i>
                </button>
              </div>
              <code>class Node:
    def __init__(self, value):
        self.value = value
        self.left = None
        self.right = None

class BinaryTree:
    def __init__(self):
        self.root = None
    
    def insert(self, value):
        if not self.root:
            self.root = Node(value)
            return
        
        current = self.root
        while True:
            if value < current.value:
                if current.left is None:
                    current.left = Node(value)
                    break
                current = current.left
            else:
                if current.right is None:
                    current.right = Node(value)
                    break
                current = current.right
    
    def search(self, value):
        current = self.root
        while current:
            if current.value == value:
                return True
            elif value < current.value:
                current = current.left
            else:
                current = current.right
        return False</code></pre>
          </div>
        </div>

        <!-- 评审区 -->
        <div class="w-1/2 overflow-y-auto p-6">
          <div class="space-y-6">
            <!-- AI 分析 -->
            <div class="bg-blue-50 rounded-lg p-4">
              <h4 class="font-medium flex items-center">
                <i class="fas fa-robot text-blue-500 mr-2"></i>
                AI 代码分析
              </h4>
              <div class="mt-3 space-y-4 text-sm">
                <div>
                  <p class="font-medium">代码质量评估：</p>
                  <div class="mt-2 grid grid-cols-2 gap-4">
                    <div class="p-3 bg-white rounded-lg">
                      <div class="flex items-center justify-between">
                        <span>代码规范</span>
                        <span class="text-green-600">90%</span>
                      </div>
                      <div class="mt-2 h-1 bg-gray-200 rounded-full">
                        <div class="h-1 bg-green-500 rounded-full" style="width: 90%"></div>
                      </div>
                    </div>
                    <div class="p-3 bg-white rounded-lg">
                      <div class="flex items-center justify-between">
                        <span>性能效率</span>
                        <span class="text-yellow-600">75%</span>
                      </div>
                      <div class="mt-2 h-1 bg-gray-200 rounded-full">
                        <div class="h-1 bg-yellow-500 rounded-full" style="width: 75%"></div>
                      </div>
                    </div>
                  </div>
                </div>
                <div>
                  <p class="font-medium">主要发现：</p>
                  <ul class="mt-2 space-y-2">
                    <li class="flex items-start">
                      <i class="fas fa-check-circle text-green-500 mt-1 mr-2"></i>
                      <span>类的结构设计合理，代码组织清晰</span>
                    </li>
                    <li class="flex items-start">
                      <i class="fas fa-exclamation-triangle text-yellow-500 mt-1 mr-2"></i>
                      <span>缺少输入验证和异常处理机制</span>
                    </li>
                    <li class="flex items-start">
                      <i class="fas fa-info-circle text-blue-500 mt-1 mr-2"></i>
                      <span>建议添加类型注解提高代码可读性</span>
                    </li>
                  </ul>
                </div>
              </div>
            </div>

            <!-- 评审意见 -->
            <div class="bg-white rounded-lg border border-gray-200">
              <div class="px-4 py-3 bg-gray-50 border-b border-gray-200">
                <h4 class="font-medium">评审意见</h4>
              </div>
              <div class="p-4">
                <div class="space-y-4">
                  <div>
                    <label class="block text-sm font-medium text-gray-700 mb-2">
                      代码评分
                    </label>
                    <div class="flex items-center space-x-4">
                      <div class="flex-1">
                        <input
                          type="range"
                          min="0"
                          max="100"
                          value="85"
                          class="w-full"
                        />
                      </div>
                      <span class="text-lg font-bold text-blue-600">85</span>
                    </div>
                  </div>
                  <div>
                    <label class="block text-sm font-medium text-gray-700 mb-2">
                      详细评语
                    </label>
                    <textarea
                      rows="4"
                      class="w-full p-3 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                      placeholder="输入评审意见..."
                    >代码实现了基本的二叉树功能，结构清晰。建议：
1. 添加类型注解和文档字符串
2. 增加输入验证和异常处理
3. 考虑添加树的平衡性检查
4. 实现更多的树操作方法（如删除、遍历等）</textarea>
                  </div>
                </div>
              </div>
            </div>

            <!-- 操作按钮 -->
            <div class="flex items-center justify-end space-x-4">
              <button class="btn-outline">保存草稿</button>
              <button class="btn-primary">提交评审</button>
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

.btn-icon-sm {
  @apply w-6 h-6 flex items-center justify-center rounded text-gray-500 hover:bg-gray-100 transition-colors;
}

.prose :deep(pre) {
  margin: 0;
  font-family: ui-monospace, SFMono-Regular, Menlo, Monaco, Consolas, "Liberation Mono", "Courier New", monospace;
  font-size: 0.875rem;
  line-height: 1.5;
}
</style> 