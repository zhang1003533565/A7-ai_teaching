<!-- 试卷批改页面 -->
<template>
  <div class="h-[calc(100vh-7rem)] flex bg-gray-50">
    <!-- 左侧试卷列表 -->
    <div class="w-80 bg-white border-r border-gray-200 flex flex-col">
      <!-- 搜索和筛选 -->
      <div class="p-4 border-b border-gray-200">
        <div class="relative mb-4">
          <input
            type="text"
            class="w-full pl-10 pr-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
            placeholder="搜索试卷..."
          />
          <i class="fas fa-search absolute left-3 top-1/2 -translate-y-1/2 text-gray-400"></i>
        </div>
        <div class="flex flex-wrap gap-2">
          <button class="px-3 py-1 bg-blue-100 text-blue-600 rounded-full text-sm">
            全部
          </button>
          <button class="px-3 py-1 text-gray-500 hover:bg-gray-100 rounded-full text-sm">
            期中考试
          </button>
          <button class="px-3 py-1 text-gray-500 hover:bg-gray-100 rounded-full text-sm">
            期末考试
          </button>
          <button class="px-3 py-1 text-gray-500 hover:bg-gray-100 rounded-full text-sm">
            单元测试
          </button>
        </div>
      </div>

      <!-- 试卷列表 -->
      <div class="flex-1 overflow-y-auto">
        <div class="space-y-2 p-2">
          <div 
            v-for="i in 8" 
            :key="i"
            class="p-4 rounded-lg cursor-pointer hover:bg-gray-50 transition-colors"
            :class="i === 1 ? 'bg-blue-50' : ''"
          >
            <div class="flex items-center justify-between">
              <h4 class="font-medium">Python期中考试</h4>
              <span class="px-2 py-1 bg-yellow-100 text-yellow-600 text-xs rounded-full">
                待批改
              </span>
            </div>
            <div class="mt-2 text-sm text-gray-500">
              <p>班级：2023级软件工程1班</p>
              <p class="mt-1">考试时间：2024-03-20</p>
              <div class="mt-2 flex items-center justify-between">
                <span>已批改：25/40</span>
                <span class="text-blue-600">62.5%</span>
              </div>
              <div class="mt-1 h-1 bg-gray-200 rounded-full">
                <div class="h-1 bg-blue-500 rounded-full" style="width: 62.5%"></div>
              </div>
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
              <i class="fas fa-chart-bar mr-2"></i>成绩分析
            </button>
            <button class="btn-outline">
              <i class="fas fa-download mr-2"></i>导出成绩
            </button>
          </div>
          <div class="flex items-center space-x-2">
            <span class="text-sm text-gray-500">批改进度：25/40</span>
            <div class="h-4 border-r border-gray-300 mx-2"></div>
            <button class="btn-icon" title="设置">
              <i class="fas fa-cog"></i>
            </button>
          </div>
        </div>
      </div>

      <!-- 试卷内容和批改区 -->
      <div class="flex-1 overflow-hidden flex">
        <!-- 试卷内容 -->
        <div class="w-1/2 overflow-y-auto p-6 border-r border-gray-200">
          <div class="prose prose-sm max-w-none">
            <div class="flex items-center justify-between">
              <h3>Python期中考试</h3>
              <span class="text-sm text-gray-500">学号：2023001</span>
            </div>
            
            <!-- 选择题 -->
            <div class="mt-6">
              <h4>一、选择题（每题5分，共20分）</h4>
              <div class="space-y-4 mt-4">
                <div class="bg-white rounded-lg border border-gray-200 p-4">
                  <p class="font-medium">1. 以下哪个不是Python的基本数据类型？</p>
                  <div class="mt-2 space-y-2">
                    <label class="flex items-center">
                      <input type="radio" name="q1" class="form-radio text-blue-500" />
                      <span class="ml-2">A. int</span>
                    </label>
                    <label class="flex items-center">
                      <input type="radio" name="q1" class="form-radio text-blue-500" checked />
                      <span class="ml-2">B. array</span>
                    </label>
                    <label class="flex items-center">
                      <input type="radio" name="q1" class="form-radio text-blue-500" />
                      <span class="ml-2">C. float</span>
                    </label>
                    <label class="flex items-center">
                      <input type="radio" name="q1" class="form-radio text-blue-500" />
                      <span class="ml-2">D. str</span>
                    </label>
                  </div>
                  <div class="mt-2 flex items-center text-sm text-green-600">
                    <i class="fas fa-check-circle mr-1"></i>
                    <span>正确答案：B</span>
                  </div>
                </div>
              </div>
            </div>

            <!-- 编程题 -->
            <div class="mt-6">
              <h4>二、编程题（每题20分，共40分）</h4>
              <div class="mt-4">
                <div class="bg-white rounded-lg border border-gray-200 p-4">
                  <p class="font-medium">1. 实现一个函数，计算斐波那契数列的第n项。</p>
                  <pre class="bg-gray-50 p-4 rounded-md mt-4"><code>def fibonacci(n):
    if n <= 1:
        return n
    return fibonacci(n-1) + fibonacci(n-2)

# 测试代码
print(fibonacci(5))</code></pre>
                </div>
              </div>
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
                <div class="flex items-center justify-between">
                  <span>选择题得分：</span>
                  <span class="font-medium">15/20</span>
                </div>
                <div class="flex items-center justify-between">
                  <span>编程题得分：</span>
                  <span class="font-medium">35/40</span>
                </div>
                <div class="mt-4">
                  <p class="font-medium">编程题分析：</p>
                  <ul class="list-disc list-inside mt-2 space-y-1 text-gray-700">
                    <li>基本实现了斐波那契数列的计算</li>
                    <li>使用了递归方法，但效率较低</li>
                    <li>缺少输入验证和边界条件处理</li>
                  </ul>
                </div>
              </div>
            </div>

            <!-- 评分表 -->
            <div class="bg-white rounded-lg border border-gray-200">
              <div class="px-4 py-3 bg-gray-50 border-b border-gray-200">
                <h4 class="font-medium">评分详情</h4>
              </div>
              <div class="p-4">
                <div class="space-y-4">
                  <div>
                    <div class="flex items-center justify-between mb-2">
                      <span class="text-sm font-medium">选择题 1</span>
                      <div class="flex items-center">
                        <input
                          type="number"
                          class="w-16 px-2 py-1 border rounded text-center"
                          value="5"
                        />
                        <span class="ml-1 text-sm text-gray-500">/5分</span>
                      </div>
                    </div>
                  </div>
                  <div>
                    <div class="flex items-center justify-between mb-2">
                      <span class="text-sm font-medium">编程题 1</span>
                      <div class="flex items-center">
                        <input
                          type="number"
                          class="w-16 px-2 py-1 border rounded text-center"
                          value="18"
                        />
                        <span class="ml-1 text-sm text-gray-500">/20分</span>
                      </div>
                    </div>
                    <textarea
                      rows="2"
                      class="w-full mt-2 p-2 border rounded-lg text-sm"
                      placeholder="评语..."
                    >实现正确，建议优化算法效率，添加输入验证。</textarea>
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

            <!-- 操作按钮 -->
            <div class="flex items-center justify-end space-x-4">
              <button class="btn-outline">
                <i class="fas fa-chevron-left mr-2"></i>上一份
              </button>
              <button class="btn-primary">
                下一份<i class="fas fa-chevron-right ml-2"></i>
              </button>
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

.form-radio {
  @apply rounded-full border-gray-300 text-blue-500 focus:ring-blue-500;
}

.prose :deep(pre) {
  margin: 0;
  font-family: ui-monospace, SFMono-Regular, Menlo, Monaco, Consolas, "Liberation Mono", "Courier New", monospace;
  font-size: 0.875rem;
  line-height: 1.5;
}
</style> 