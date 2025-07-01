<!-- 解题分析页面 -->
<template>
  <div class="h-[calc(100vh-7rem)] flex bg-gray-50">
    <!-- 左侧题目区域 -->
    <div class="w-1/2 flex flex-col border-r border-gray-200">
      <!-- 题目工具栏 -->
      <div class="bg-white p-4 border-b border-gray-200">
        <div class="flex items-center justify-between">
          <div class="flex items-center space-x-4">
            <button class="btn-primary">
              <i class="fas fa-camera mr-2"></i>拍照识题
            </button>
            <button class="btn-outline">
              <i class="fas fa-upload mr-2"></i>上传题目
            </button>
          </div>
          <div class="flex items-center space-x-2">
            <button class="btn-icon" title="历史记录">
              <i class="fas fa-history"></i>
            </button>
            <button class="btn-icon" title="收藏">
              <i class="far fa-star"></i>
            </button>
          </div>
        </div>
      </div>

      <!-- 题目内容 -->
      <div class="flex-1 overflow-y-auto p-6">
        <div class="bg-white rounded-lg shadow-sm p-6">
          <div class="flex items-center justify-between mb-4">
            <h3 class="text-lg font-medium">Python函数递归问题</h3>
            <span class="px-2 py-1 bg-blue-100 text-blue-600 text-sm rounded">算法题</span>
          </div>
          <div class="prose prose-sm max-w-none">
            <p class="text-gray-700">
              请实现一个递归函数来计算斐波那契数列的第n项。斐波那契数列定义如下：
            </p>
            <pre class="bg-gray-50 p-4 rounded-md mt-2"><code>F(0) = 0
F(1) = 1
F(n) = F(n-1) + F(n-2), n > 1</code></pre>
            <p class="mt-4 text-gray-700">要求：</p>
            <ul class="list-disc list-inside text-gray-700">
              <li>函数接收一个非负整数n作为参数</li>
              <li>返回斐波那契数列的第n项</li>
              <li>考虑时间复杂度和空间复杂度</li>
            </ul>
            <p class="mt-4 text-gray-700">示例：</p>
            <pre class="bg-gray-50 p-4 rounded-md mt-2"><code>输入：n = 5
输出：5
解释：F(5) = F(4) + F(3) = 3 + 2 = 5</code></pre>
          </div>
        </div>
      </div>
    </div>

    <!-- 右侧分析区域 -->
    <div class="w-1/2 flex flex-col">
      <!-- 分析工具栏 -->
      <div class="bg-white p-4 border-b border-gray-200">
        <div class="flex items-center space-x-4">
          <button class="btn-primary">
            <i class="fas fa-lightbulb mr-2"></i>开始分析
          </button>
          <button class="btn-outline">
            <i class="fas fa-code mr-2"></i>查看代码
          </button>
          <button class="btn-outline">
            <i class="fas fa-share mr-2"></i>分享
          </button>
        </div>
      </div>

      <!-- 分析内容 -->
      <div class="flex-1 overflow-y-auto p-6">
        <div class="space-y-6">
          <!-- 解题思路 -->
          <div class="bg-white rounded-lg shadow-sm p-6">
            <h4 class="text-lg font-medium mb-4">解题思路</h4>
            <div class="space-y-4">
              <div class="flex items-start">
                <div class="w-6 h-6 rounded-full bg-blue-100 flex items-center justify-center flex-shrink-0">
                  <span class="text-blue-600 text-sm">1</span>
                </div>
                <p class="ml-3 text-gray-700">理解递归的基本概念和终止条件</p>
              </div>
              <div class="flex items-start">
                <div class="w-6 h-6 rounded-full bg-blue-100 flex items-center justify-center flex-shrink-0">
                  <span class="text-blue-600 text-sm">2</span>
                </div>
                <p class="ml-3 text-gray-700">分析递归公式 F(n) = F(n-1) + F(n-2)</p>
              </div>
              <div class="flex items-start">
                <div class="w-6 h-6 rounded-full bg-blue-100 flex items-center justify-center flex-shrink-0">
                  <span class="text-blue-600 text-sm">3</span>
                </div>
                <p class="ml-3 text-gray-700">考虑优化方案，如记忆化递归或动态规划</p>
              </div>
            </div>
          </div>

          <!-- 关键知识点 -->
          <div class="bg-white rounded-lg shadow-sm p-6">
            <h4 class="text-lg font-medium mb-4">关键知识点</h4>
            <div class="grid grid-cols-2 gap-4">
              <div class="p-4 bg-gray-50 rounded-lg">
                <h5 class="font-medium text-gray-800 mb-2">递归基础</h5>
                <p class="text-sm text-gray-600">
                  理解递归的定义、基本条件和递归条件
                </p>
              </div>
              <div class="p-4 bg-gray-50 rounded-lg">
                <h5 class="font-medium text-gray-800 mb-2">时间复杂度</h5>
                <p class="text-sm text-gray-600">
                  分析递归算法的时间复杂度计算方法
                </p>
              </div>
              <div class="p-4 bg-gray-50 rounded-lg">
                <h5 class="font-medium text-gray-800 mb-2">优化技巧</h5>
                <p class="text-sm text-gray-600">
                  记忆化搜索和动态规划的应用
                </p>
              </div>
              <div class="p-4 bg-gray-50 rounded-lg">
                <h5 class="font-medium text-gray-800 mb-2">Python特性</h5>
                <p class="text-sm text-gray-600">
                  Python中实现递归的语法和注意事项
                </p>
              </div>
            </div>
          </div>

          <!-- 解题步骤 -->
          <div class="bg-white rounded-lg shadow-sm p-6">
            <h4 class="text-lg font-medium mb-4">解题步骤</h4>
            <div class="space-y-4">
              <div class="border-l-4 border-blue-500 pl-4">
                <h5 class="font-medium text-gray-800 mb-2">1. 基本递归实现</h5>
                <pre class="bg-gray-50 p-4 rounded-md text-sm"><code>def fibonacci(n):
    if n <= 1:
        return n
    return fibonacci(n-1) + fibonacci(n-2)</code></pre>
              </div>
              <div class="border-l-4 border-green-500 pl-4">
                <h5 class="font-medium text-gray-800 mb-2">2. 记忆化递归优化</h5>
                <pre class="bg-gray-50 p-4 rounded-md text-sm"><code>def fibonacci_memo(n, memo=None):
    if memo is None:
        memo = {}
    if n <= 1:
        return n
    if n not in memo:
        memo[n] = fibonacci_memo(n-1, memo) + fibonacci_memo(n-2, memo)
    return memo[n]</code></pre>
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

pre {
  font-family: ui-monospace, SFMono-Regular, Menlo, Monaco, Consolas, "Liberation Mono", "Courier New", monospace;
  font-size: 0.875rem;
  line-height: 1.5;
}
</style> 