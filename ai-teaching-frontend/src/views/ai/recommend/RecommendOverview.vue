<!-- 推荐概览页面 -->
<template>
  <div class="h-[calc(100vh-7rem)] p-6 bg-gray-50">
    <!-- 统计卡片 -->
    <div class="grid grid-cols-4 gap-6 mb-6">
      <div class="bg-white rounded-lg shadow-sm p-6">
        <div class="flex items-center justify-between">
          <div>
            <p class="text-sm text-gray-500">今日推荐</p>
            <h3 class="text-2xl font-bold mt-1">12</h3>
          </div>
          <div class="w-10 h-10 rounded-full bg-blue-100 flex items-center justify-center">
            <i class="fas fa-lightbulb text-blue-500"></i>
          </div>
        </div>
        <div class="mt-4">
          <div class="flex items-center text-sm">
            <span class="text-green-500">
              <i class="fas fa-arrow-up mr-1"></i>8%
            </span>
            <span class="text-gray-500 ml-2">较昨日</span>
          </div>
        </div>
      </div>

      <div class="bg-white rounded-lg shadow-sm p-6">
        <div class="flex items-center justify-between">
          <div>
            <p class="text-sm text-gray-500">学习时长</p>
            <h3 class="text-2xl font-bold mt-1">3.5h</h3>
          </div>
          <div class="w-10 h-10 rounded-full bg-purple-100 flex items-center justify-center">
            <i class="fas fa-clock text-purple-500"></i>
          </div>
        </div>
        <div class="mt-4">
          <div class="flex items-center text-sm">
            <span class="text-green-500">
              <i class="fas fa-arrow-up mr-1"></i>12%
            </span>
            <span class="text-gray-500 ml-2">较昨日</span>
          </div>
        </div>
      </div>

      <div class="bg-white rounded-lg shadow-sm p-6">
        <div class="flex items-center justify-between">
          <div>
            <p class="text-sm text-gray-500">完成任务</p>
            <h3 class="text-2xl font-bold mt-1">8</h3>
          </div>
          <div class="w-10 h-10 rounded-full bg-green-100 flex items-center justify-center">
            <i class="fas fa-check-circle text-green-500"></i>
          </div>
        </div>
        <div class="mt-4">
          <div class="flex items-center text-sm">
            <span class="text-red-500">
              <i class="fas fa-arrow-down mr-1"></i>5%
            </span>
            <span class="text-gray-500 ml-2">较昨日</span>
          </div>
        </div>
      </div>

      <div class="bg-white rounded-lg shadow-sm p-6">
        <div class="flex items-center justify-between">
          <div>
            <p class="text-sm text-gray-500">知识点</p>
            <h3 class="text-2xl font-bold mt-1">25</h3>
          </div>
          <div class="w-10 h-10 rounded-full bg-orange-100 flex items-center justify-center">
            <i class="fas fa-brain text-orange-500"></i>
          </div>
        </div>
        <div class="mt-4">
          <div class="flex items-center text-sm">
            <span class="text-green-500">
              <i class="fas fa-arrow-up mr-1"></i>15%
            </span>
            <span class="text-gray-500 ml-2">较昨日</span>
          </div>
        </div>
      </div>
    </div>

    <div class="grid grid-cols-2 gap-6">
      <!-- 今日推荐 -->
      <div class="bg-white rounded-lg shadow-sm p-6">
        <div class="flex items-center justify-between mb-6">
          <h3 class="text-lg font-medium">今日推荐</h3>
          <button class="text-sm text-blue-600 hover:text-blue-800">
            查看全部
          </button>
        </div>
        <div class="space-y-4">
          <div class="flex items-start p-4 bg-gray-50 rounded-lg">
            <div class="w-10 h-10 rounded-lg bg-blue-100 flex items-center justify-center flex-shrink-0">
              <i class="fas fa-book text-blue-500"></i>
            </div>
            <div class="ml-4 flex-1">
              <h4 class="text-sm font-medium">Python高级编程技巧</h4>
              <p class="mt-1 text-sm text-gray-500">根据您最近的学习进度推荐</p>
              <div class="mt-2 flex items-center">
                <span class="text-xs text-gray-500">难度：中等</span>
                <span class="mx-2 text-gray-300">|</span>
                <span class="text-xs text-gray-500">预计时长：2小时</span>
              </div>
            </div>
            <button class="px-3 py-1 text-sm text-blue-600 hover:bg-blue-50 rounded">
              开始学习
            </button>
          </div>

          <div class="flex items-start p-4 bg-gray-50 rounded-lg">
            <div class="w-10 h-10 rounded-lg bg-green-100 flex items-center justify-center flex-shrink-0">
              <i class="fas fa-tasks text-green-500"></i>
            </div>
            <div class="ml-4 flex-1">
              <h4 class="text-sm font-medium">算法练习：二叉树遍历</h4>
              <p class="mt-1 text-sm text-gray-500">巩固数据结构知识点</p>
              <div class="mt-2 flex items-center">
                <span class="text-xs text-gray-500">难度：简单</span>
                <span class="mx-2 text-gray-300">|</span>
                <span class="text-xs text-gray-500">预计时长：30分钟</span>
              </div>
            </div>
            <button class="px-3 py-1 text-sm text-blue-600 hover:bg-blue-50 rounded">
              开始练习
            </button>
          </div>

          <div class="flex items-start p-4 bg-gray-50 rounded-lg">
            <div class="w-10 h-10 rounded-lg bg-purple-100 flex items-center justify-center flex-shrink-0">
              <i class="fas fa-video text-purple-500"></i>
            </div>
            <div class="ml-4 flex-1">
              <h4 class="text-sm font-medium">视频：设计模式实战</h4>
              <p class="mt-1 text-sm text-gray-500">软件工程必备知识</p>
              <div class="mt-2 flex items-center">
                <span class="text-xs text-gray-500">难度：高级</span>
                <span class="mx-2 text-gray-300">|</span>
                <span class="text-xs text-gray-500">时长：45分钟</span>
              </div>
            </div>
            <button class="px-3 py-1 text-sm text-blue-600 hover:bg-blue-50 rounded">
              观看视频
            </button>
          </div>
        </div>
      </div>

      <!-- 学习进度 -->
      <div class="bg-white rounded-lg shadow-sm p-6">
        <div class="flex items-center justify-between mb-6">
          <h3 class="text-lg font-medium">学习进度</h3>
          <select class="border rounded-md px-3 py-1 text-sm">
            <option>本周</option>
            <option>本月</option>
            <option>全部</option>
          </select>
        </div>
        <div class="space-y-4">
          <div>
            <div class="flex items-center justify-between mb-2">
              <span class="text-sm font-medium">Python基础课程</span>
              <span class="text-sm text-gray-500">85%</span>
            </div>
            <div class="h-2 bg-gray-200 rounded-full">
              <div class="h-2 bg-blue-500 rounded-full" style="width: 85%"></div>
            </div>
          </div>
          <div>
            <div class="flex items-center justify-between mb-2">
              <span class="text-sm font-medium">数据结构与算法</span>
              <span class="text-sm text-gray-500">60%</span>
            </div>
            <div class="h-2 bg-gray-200 rounded-full">
              <div class="h-2 bg-blue-500 rounded-full" style="width: 60%"></div>
            </div>
          </div>
          <div>
            <div class="flex items-center justify-between mb-2">
              <span class="text-sm font-medium">软件工程实践</span>
              <span class="text-sm text-gray-500">45%</span>
            </div>
            <div class="h-2 bg-gray-200 rounded-full">
              <div class="h-2 bg-blue-500 rounded-full" style="width: 45%"></div>
            </div>
          </div>
          <div>
            <div class="flex items-center justify-between mb-2">
              <span class="text-sm font-medium">Web开发基础</span>
              <span class="text-sm text-gray-500">30%</span>
            </div>
            <div class="h-2 bg-gray-200 rounded-full">
              <div class="h-2 bg-blue-500 rounded-full" style="width: 30%"></div>
            </div>
          </div>
        </div>
        <div class="mt-6 p-4 bg-blue-50 rounded-lg">
          <div class="flex items-center">
            <i class="fas fa-info-circle text-blue-500 mr-2"></i>
            <p class="text-sm text-blue-700">根据您的学习进度，建议优先完成Python基础课程的剩余内容。</p>
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
/* 可以添加自定义样式 */
</style> 