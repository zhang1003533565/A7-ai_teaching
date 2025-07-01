<!-- 学习计划页面 -->
<template>
  <div class="h-[calc(100vh-7rem)] p-6 bg-gray-50">
    <!-- 顶部统计 -->
    <div class="grid grid-cols-4 gap-6 mb-6">
      <div class="bg-white rounded-lg shadow-sm p-4">
        <div class="flex items-center justify-between">
          <div>
            <p class="text-sm text-gray-500">今日任务</p>
            <h3 class="text-2xl font-bold mt-1">5/8</h3>
          </div>
          <div class="w-10 h-10 rounded-full bg-blue-100 flex items-center justify-center">
            <i class="fas fa-tasks text-blue-500"></i>
          </div>
        </div>
        <div class="mt-4">
          <div class="w-full bg-gray-200 rounded-full h-2">
            <div class="bg-blue-600 h-2 rounded-full" style="width: 62.5%"></div>
          </div>
        </div>
      </div>

      <div class="bg-white rounded-lg shadow-sm p-4">
        <div class="flex items-center justify-between">
          <div>
            <p class="text-sm text-gray-500">学习时长</p>
            <h3 class="text-2xl font-bold mt-1">2.5h</h3>
          </div>
          <div class="w-10 h-10 rounded-full bg-purple-100 flex items-center justify-center">
            <i class="fas fa-clock text-purple-500"></i>
          </div>
        </div>
        <div class="mt-4">
          <div class="w-full bg-gray-200 rounded-full h-2">
            <div class="bg-purple-600 h-2 rounded-full" style="width: 50%"></div>
          </div>
        </div>
      </div>

      <div class="bg-white rounded-lg shadow-sm p-4">
        <div class="flex items-center justify-between">
          <div>
            <p class="text-sm text-gray-500">完成习题</p>
            <h3 class="text-2xl font-bold mt-1">12</h3>
          </div>
          <div class="w-10 h-10 rounded-full bg-green-100 flex items-center justify-center">
            <i class="fas fa-check-circle text-green-500"></i>
          </div>
        </div>
        <div class="mt-4">
          <div class="w-full bg-gray-200 rounded-full h-2">
            <div class="bg-green-600 h-2 rounded-full" style="width: 80%"></div>
          </div>
        </div>
      </div>

      <div class="bg-white rounded-lg shadow-sm p-4">
        <div class="flex items-center justify-between">
          <div>
            <p class="text-sm text-gray-500">学习效率</p>
            <h3 class="text-2xl font-bold mt-1">85%</h3>
          </div>
          <div class="w-10 h-10 rounded-full bg-yellow-100 flex items-center justify-center">
            <i class="fas fa-chart-line text-yellow-500"></i>
          </div>
        </div>
        <div class="mt-4">
          <div class="w-full bg-gray-200 rounded-full h-2">
            <div class="bg-yellow-600 h-2 rounded-full" style="width: 85%"></div>
          </div>
        </div>
      </div>
    </div>

    <div class="grid grid-cols-2 gap-6">
      <!-- 今日计划 -->
      <div class="bg-white rounded-lg shadow-sm p-6">
        <div class="flex items-center justify-between mb-6">
          <h3 class="text-lg font-medium">今日学习计划</h3>
          <div class="flex items-center space-x-2">
            <span class="text-sm text-gray-500">2024-03-20</span>
            <button class="text-blue-600 hover:text-blue-800">
              <i class="fas fa-calendar-alt"></i>
            </button>
          </div>
        </div>
        <div class="space-y-4">
          <div class="flex items-center p-4 bg-gray-50 rounded-lg">
            <div class="w-8 h-8 rounded-lg bg-blue-100 flex items-center justify-center flex-shrink-0">
              <i class="fas fa-book text-blue-500"></i>
            </div>
            <div class="ml-4 flex-1">
              <div class="flex items-center justify-between">
                <h4 class="text-sm font-medium">Python高级编程 - 第5章</h4>
                <span class="text-xs text-gray-500">09:00-10:30</span>
              </div>
              <p class="mt-1 text-xs text-gray-500">装饰器和元类的高级用法</p>
            </div>
            <input type="checkbox" class="w-5 h-5 text-blue-600 rounded border-gray-300 ml-4">
          </div>

          <div class="flex items-center p-4 bg-gray-50 rounded-lg">
            <div class="w-8 h-8 rounded-lg bg-green-100 flex items-center justify-center flex-shrink-0">
              <i class="fas fa-code text-green-500"></i>
            </div>
            <div class="ml-4 flex-1">
              <div class="flex items-center justify-between">
                <h4 class="text-sm font-medium">算法练习 - 动态规划</h4>
                <span class="text-xs text-gray-500">11:00-12:00</span>
              </div>
              <p class="mt-1 text-xs text-gray-500">完成5道动态规划相关题目</p>
            </div>
            <input type="checkbox" class="w-5 h-5 text-blue-600 rounded border-gray-300 ml-4">
          </div>

          <div class="flex items-center p-4 bg-gray-50 rounded-lg">
            <div class="w-8 h-8 rounded-lg bg-purple-100 flex items-center justify-center flex-shrink-0">
              <i class="fas fa-video text-purple-500"></i>
            </div>
            <div class="ml-4 flex-1">
              <div class="flex items-center justify-between">
                <h4 class="text-sm font-medium">观看视频课程</h4>
                <span class="text-xs text-gray-500">14:00-15:30</span>
              </div>
              <p class="mt-1 text-xs text-gray-500">Web安全基础知识讲解</p>
            </div>
            <input type="checkbox" class="w-5 h-5 text-blue-600 rounded border-gray-300 ml-4">
          </div>
        </div>
      </div>

      <!-- 学习建议 -->
      <div class="bg-white rounded-lg shadow-sm p-6">
        <div class="flex items-center justify-between mb-6">
          <h3 class="text-lg font-medium">个性化学习建议</h3>
          <button class="text-sm text-blue-600 hover:text-blue-800">
            刷新建议
          </button>
        </div>
        <div class="space-y-4">
          <div class="p-4 bg-blue-50 rounded-lg">
            <div class="flex items-start">
              <i class="fas fa-lightbulb text-blue-500 mt-1"></i>
              <div class="ml-4">
                <h4 class="text-sm font-medium text-blue-900">知识点巩固建议</h4>
                <p class="mt-1 text-sm text-blue-700">
                  建议您复习装饰器相关内容，这将有助于理解今天的课程内容。可以查看之前的练习题和笔记。
                </p>
              </div>
            </div>
          </div>

          <div class="p-4 bg-green-50 rounded-lg">
            <div class="flex items-start">
              <i class="fas fa-chart-bar text-green-500 mt-1"></i>
              <div class="ml-4">
                <h4 class="text-sm font-medium text-green-900">学习效率提升建议</h4>
                <p class="mt-1 text-sm text-green-700">
                  您在动态规划题目的解决速度有所提升，建议继续保持这个学习节奏，每天解决3-5道相关题目。
                </p>
              </div>
            </div>
          </div>

          <div class="p-4 bg-purple-50 rounded-lg">
            <div class="flex items-start">
              <i class="fas fa-clock text-purple-500 mt-1"></i>
              <div class="ml-4">
                <h4 class="text-sm font-medium text-purple-900">时间管理建议</h4>
                <p class="mt-1 text-sm text-purple-700">
                  注意到您在下午的学习效率较高，建议将较难的课程安排在14:00-16:00这个时间段。
                </p>
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
/* 可以添加自定义样式 */
</style> 