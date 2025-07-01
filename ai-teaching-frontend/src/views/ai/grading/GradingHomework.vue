<!-- 作业批改页面 -->
<template>
  <div class="h-[calc(100vh-7rem)] flex bg-gray-50">
    <!-- 左侧作业列表 -->
    <div class="w-80 bg-white border-r border-gray-200 flex flex-col">
      <!-- 搜索栏 -->
      <div class="p-4 border-b border-gray-200">
        <div class="relative">
          <input
            type="text"
            class="w-full pl-10 pr-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
            placeholder="搜索作业..."
          />
          <i class="fas fa-search absolute left-3 top-1/2 -translate-y-1/2 text-gray-400"></i>
        </div>
      </div>

      <!-- 筛选选项 -->
      <div class="p-4 border-b border-gray-200">
        <div class="flex items-center space-x-2">
          <button class="px-3 py-1 bg-blue-100 text-blue-600 rounded-full text-sm">
            全部
          </button>
          <button class="px-3 py-1 text-gray-500 hover:bg-gray-100 rounded-full text-sm">
            未批改
          </button>
          <button class="px-3 py-1 text-gray-500 hover:bg-gray-100 rounded-full text-sm">
            已批改
          </button>
        </div>
      </div>

      <!-- 作业列表 -->
      <div class="flex-1 overflow-y-auto">
        <div class="space-y-2 p-2">
          <div 
            v-for="i in 10" 
            :key="i"
            class="p-4 rounded-lg cursor-pointer hover:bg-gray-50 transition-colors"
            :class="i === 1 ? 'bg-blue-50' : ''"
          >
            <div class="flex items-center justify-between">
              <h4 class="font-medium">Python程序设计作业 {{ i }}</h4>
              <span class="px-2 py-1 bg-yellow-100 text-yellow-600 text-xs rounded-full">
                待批改
              </span>
            </div>
            <div class="mt-2 text-sm text-gray-500">
              <p>提交人：张三</p>
              <p class="mt-1">提交时间：2024-03-20 14:30</p>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- 右侧批改区域 -->
    <div class="flex-1 flex flex-col">
      <!-- 工具栏 -->
      <div class="bg-white p-4 border-b border-gray-200">
        <div class="flex items-center justify-between">
          <div class="flex items-center space-x-4">
            <button class="btn-primary">
              <i class="fas fa-magic mr-2"></i>智能批改
            </button>
            <button class="btn-outline">
              <i class="fas fa-history mr-2"></i>批改记录
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

      <!-- 作业内容和批改区 -->
      <div class="flex-1 overflow-hidden flex">
        <!-- 作业内容 -->
        <div class="w-1/2 overflow-y-auto p-6 border-r border-gray-200">
          <div class="prose prose-sm max-w-none">
            <h3>Python程序设计作业 1</h3>
            <div class="mt-4">
              <h4>题目要求：</h4>
              <p>实现一个简单的计算器程序，要求：</p>
              <ol>
                <li>支持基本的加减乘除运算</li>
                <li>处理输入异常情况</li>
                <li>实现历史记录功能</li>
              </ol>
            </div>
            <div class="mt-4">
              <h4>学生提交的代码：</h4>
              <pre class="bg-gray-50 p-4 rounded-md"><code>class Calculator:
    def __init__(self):
        self.history = []

    def calculate(self, a, b, op):
        result = 0
        if op == '+':
            result = a + b
        elif op == '-':
            result = a - b
        elif op == '*':
            result = a * b
        elif op == '/':
            result = a / b
        
        self.history.append(f"{a} {op} {b} = {result}")
        return result

    def get_history(self):
        return self.history</code></pre>
            </div>
          </div>
        </div>

        <!-- 批改区 -->
        <div class="w-1/2 overflow-y-auto p-6">
          <div class="space-y-6">
            <!-- AI 分析 -->
            <div class="bg-blue-50 rounded-lg p-4">
              <h4 class="font-medium flex items-center">
                <i class="fas fa-robot text-blue-500 mr-2"></i>
                AI 分析结果
              </h4>
              <div class="mt-3 space-y-2 text-sm">
                <p>代码实现基本完成了要求的功能，但存在以下问题：</p>
                <ul class="list-disc list-inside space-y-1 text-gray-700">
                  <li>缺少输入验证</li>
                  <li>除法未处理除数为0的情况</li>
                  <li>未实现清空历史记录的功能</li>
                </ul>
              </div>
            </div>

            <!-- 评分表 -->
            <div class="bg-white rounded-lg border border-gray-200 overflow-hidden">
              <div class="px-4 py-3 bg-gray-50 border-b border-gray-200">
                <h4 class="font-medium">评分表</h4>
              </div>
              <div class="p-4">
                <div class="space-y-4">
                  <div>
                    <div class="flex items-center justify-between mb-2">
                      <span class="text-sm font-medium">功能完整性</span>
                      <span class="text-sm text-gray-500">8/10分</span>
                    </div>
                    <div class="h-2 bg-gray-200 rounded-full">
                      <div class="h-2 bg-blue-500 rounded-full" style="width: 80%"></div>
                    </div>
                  </div>
                  <div>
                    <div class="flex items-center justify-between mb-2">
                      <span class="text-sm font-medium">代码规范</span>
                      <span class="text-sm text-gray-500">9/10分</span>
                    </div>
                    <div class="h-2 bg-gray-200 rounded-full">
                      <div class="h-2 bg-blue-500 rounded-full" style="width: 90%"></div>
                    </div>
                  </div>
                  <div>
                    <div class="flex items-center justify-between mb-2">
                      <span class="text-sm font-medium">异常处理</span>
                      <span class="text-sm text-gray-500">6/10分</span>
                    </div>
                    <div class="h-2 bg-gray-200 rounded-full">
                      <div class="h-2 bg-blue-500 rounded-full" style="width: 60%"></div>
                    </div>
                  </div>
                </div>
                <div class="mt-4 p-4 bg-gray-50 rounded-lg">
                  <div class="flex items-center justify-between">
                    <span class="font-medium">总分</span>
                    <span class="text-xl font-bold text-blue-600">85</span>
                  </div>
                </div>
              </div>
            </div>

            <!-- 评语 -->
            <div class="bg-white rounded-lg border border-gray-200">
              <div class="px-4 py-3 bg-gray-50 border-b border-gray-200">
                <h4 class="font-medium">评语</h4>
              </div>
              <div class="p-4">
                <textarea
                  rows="4"
                  class="w-full p-3 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                  placeholder="输入评语..."
                >代码结构清晰，基本功能实现完整。建议加强异常处理，特别是除数为0的情况。可以考虑添加更多的单元测试来提高代码质量。</textarea>
              </div>
            </div>

            <!-- 操作按钮 -->
            <div class="flex items-center justify-end space-x-4">
              <button class="btn-outline">保存草稿</button>
              <button class="btn-primary">提交批改</button>
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

.prose :deep(pre) {
  margin: 0;
  font-family: ui-monospace, SFMono-Regular, Menlo, Monaco, Consolas, "Liberation Mono", "Courier New", monospace;
  font-size: 0.875rem;
  line-height: 1.5;
}
</style> 