<!-- 练习推荐页面 -->
<template>
  <div class="h-[calc(100vh-7rem)] p-6 bg-gray-50">
    <!-- 筛选栏 -->
    <div class="bg-white rounded-lg shadow-sm p-4 mb-6">
      <div class="flex items-center space-x-4">
        <div class="flex-1">
          <input
            type="text"
            placeholder="搜索练习..."
            class="w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
          >
        </div>
        <select class="px-4 py-2 border rounded-lg">
          <option>全部难度</option>
          <option>入门</option>
          <option>简单</option>
          <option>中等</option>
          <option>困难</option>
        </select>
        <select class="px-4 py-2 border rounded-lg">
          <option>全部类型</option>
          <option>编程题</option>
          <option>选择题</option>
          <option>填空题</option>
          <option>问答题</option>
        </select>
        <button class="px-6 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700">
          筛选
        </button>
      </div>
    </div>

    <!-- 练习列表 -->
    <div class="grid grid-cols-3 gap-6">
      <!-- 练习卡片 -->
      <div class="bg-white rounded-lg shadow-sm overflow-hidden">
        <div class="p-4 border-b">
          <div class="flex items-center justify-between mb-2">
            <h3 class="text-lg font-medium">二叉树的层序遍历</h3>
            <span class="px-2 py-1 bg-green-100 text-green-800 text-xs rounded-full">简单</span>
          </div>
          <p class="text-sm text-gray-500">
            使用队列实现二叉树的层序遍历，并返回遍历结果
          </p>
        </div>
        <div class="p-4">
          <div class="flex items-center text-sm text-gray-500 mb-4">
            <div class="flex items-center">
              <i class="fas fa-code mr-1"></i>
              <span>编程题</span>
            </div>
            <div class="mx-4">|</div>
            <div class="flex items-center">
              <i class="fas fa-users mr-1"></i>
              <span>1.2k已完成</span>
            </div>
            <div class="mx-4">|</div>
            <div class="flex items-center">
              <i class="fas fa-thumbs-up mr-1"></i>
              <span>95%通过率</span>
            </div>
          </div>
          <div class="flex items-center justify-between">
            <div class="flex items-center">
              <span class="text-sm text-blue-600">推荐指数：90%</span>
              <i class="fas fa-info-circle ml-1 text-gray-400" title="根据您的学习进度推荐"></i>
            </div>
            <button class="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700">
              开始练习
            </button>
          </div>
        </div>
      </div>

      <div class="bg-white rounded-lg shadow-sm overflow-hidden">
        <div class="p-4 border-b">
          <div class="flex items-center justify-between mb-2">
            <h3 class="text-lg font-medium">动态规划：最长递增子序列</h3>
            <span class="px-2 py-1 bg-yellow-100 text-yellow-800 text-xs rounded-full">中等</span>
          </div>
          <p class="text-sm text-gray-500">
            使用动态规划求解最长递增子序列问题
          </p>
        </div>
        <div class="p-4">
          <div class="flex items-center text-sm text-gray-500 mb-4">
            <div class="flex items-center">
              <i class="fas fa-code mr-1"></i>
              <span>编程题</span>
            </div>
            <div class="mx-4">|</div>
            <div class="flex items-center">
              <i class="fas fa-users mr-1"></i>
              <span>856已完成</span>
            </div>
            <div class="mx-4">|</div>
            <div class="flex items-center">
              <i class="fas fa-thumbs-up mr-1"></i>
              <span>82%通过率</span>
            </div>
          </div>
          <div class="flex items-center justify-between">
            <div class="flex items-center">
              <span class="text-sm text-blue-600">推荐指数：85%</span>
              <i class="fas fa-info-circle ml-1 text-gray-400" title="根据您的学习进度推荐"></i>
            </div>
            <button class="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700">
              开始练习
            </button>
          </div>
        </div>
      </div>

      <div class="bg-white rounded-lg shadow-sm overflow-hidden">
        <div class="p-4 border-b">
          <div class="flex items-center justify-between mb-2">
            <h3 class="text-lg font-medium">HTTP协议基础</h3>
            <span class="px-2 py-1 bg-green-100 text-green-800 text-xs rounded-full">简单</span>
          </div>
          <p class="text-sm text-gray-500">
            测试HTTP协议的基础知识，包含请求方法、状态码等
          </p>
        </div>
        <div class="p-4">
          <div class="flex items-center text-sm text-gray-500 mb-4">
            <div class="flex items-center">
              <i class="fas fa-list-ul mr-1"></i>
              <span>选择题</span>
            </div>
            <div class="mx-4">|</div>
            <div class="flex items-center">
              <i class="fas fa-users mr-1"></i>
              <span>2.1k已完成</span>
            </div>
            <div class="mx-4">|</div>
            <div class="flex items-center">
              <i class="fas fa-thumbs-up mr-1"></i>
              <span>98%通过率</span>
            </div>
          </div>
          <div class="flex items-center justify-between">
            <div class="flex items-center">
              <span class="text-sm text-blue-600">推荐指数：88%</span>
              <i class="fas fa-info-circle ml-1 text-gray-400" title="根据您的学习进度推荐"></i>
            </div>
            <button class="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700">
              开始练习
            </button>
          </div>
        </div>
      </div>

      <div class="bg-white rounded-lg shadow-sm overflow-hidden">
        <div class="p-4 border-b">
          <div class="flex items-center justify-between mb-2">
            <h3 class="text-lg font-medium">SQL高级查询</h3>
            <span class="px-2 py-1 bg-red-100 text-red-800 text-xs rounded-full">困难</span>
          </div>
          <p class="text-sm text-gray-500">
            复杂的SQL查询练习，包含子查询、连接和聚合函数
          </p>
        </div>
        <div class="p-4">
          <div class="flex items-center text-sm text-gray-500 mb-4">
            <div class="flex items-center">
              <i class="fas fa-code mr-1"></i>
              <span>编程题</span>
            </div>
            <div class="mx-4">|</div>
            <div class="flex items-center">
              <i class="fas fa-users mr-1"></i>
              <span>635已完成</span>
            </div>
            <div class="mx-4">|</div>
            <div class="flex items-center">
              <i class="fas fa-thumbs-up mr-1"></i>
              <span>75%通过率</span>
            </div>
          </div>
          <div class="flex items-center justify-between">
            <div class="flex items-center">
              <span class="text-sm text-blue-600">推荐指数：82%</span>
              <i class="fas fa-info-circle ml-1 text-gray-400" title="根据您的学习进度推荐"></i>
            </div>
            <button class="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700">
              开始练习
            </button>
          </div>
        </div>
      </div>
    </div>

    <!-- 分页 -->
    <div class="flex justify-center mt-6">
      <nav class="flex items-center space-x-2">
        <button class="px-3 py-1 rounded-lg border hover:bg-gray-50">
          <i class="fas fa-chevron-left"></i>
        </button>
        <button class="px-3 py-1 rounded-lg border bg-blue-600 text-white">1</button>
        <button class="px-3 py-1 rounded-lg border hover:bg-gray-50">2</button>
        <button class="px-3 py-1 rounded-lg border hover:bg-gray-50">3</button>
        <button class="px-3 py-1 rounded-lg border hover:bg-gray-50">4</button>
        <button class="px-3 py-1 rounded-lg border hover:bg-gray-50">5</button>
        <button class="px-3 py-1 rounded-lg border hover:bg-gray-50">
          <i class="fas fa-chevron-right"></i>
        </button>
      </nav>
    </div>
  </div>
</template>

<script setup>
// 组件逻辑可以在这里添加
</script>

<style scoped>
/* 可以添加自定义样式 */
</style> 