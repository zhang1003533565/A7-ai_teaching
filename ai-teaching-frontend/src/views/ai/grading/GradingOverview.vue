<!-- 批改概览页面 -->
<template>
  <div class="h-[calc(100vh-7rem)] p-6 bg-gray-50">
    <!-- 统计卡片 -->
    <div class="grid grid-cols-4 gap-6 mb-6">
      <div class="bg-white rounded-lg shadow-sm p-6">
        <div class="flex items-center justify-between">
          <div>
            <p class="text-sm text-gray-500">待批改作业</p>
            <h3 class="text-2xl font-bold mt-1">24</h3>
          </div>
          <div class="w-10 h-10 rounded-full bg-blue-100 flex items-center justify-center">
            <i class="fas fa-tasks text-blue-500"></i>
          </div>
        </div>
        <div class="mt-4">
          <div class="flex items-center text-sm">
            <span class="text-green-500">
              <i class="fas fa-arrow-up mr-1"></i>12%
            </span>
            <span class="text-gray-500 ml-2">较上周</span>
          </div>
        </div>
      </div>

      <div class="bg-white rounded-lg shadow-sm p-6">
        <div class="flex items-center justify-between">
          <div>
            <p class="text-sm text-gray-500">待批改试卷</p>
            <h3 class="text-2xl font-bold mt-1">8</h3>
          </div>
          <div class="w-10 h-10 rounded-full bg-purple-100 flex items-center justify-center">
            <i class="fas fa-file-alt text-purple-500"></i>
          </div>
        </div>
        <div class="mt-4">
          <div class="flex items-center text-sm">
            <span class="text-red-500">
              <i class="fas fa-arrow-down mr-1"></i>5%
            </span>
            <span class="text-gray-500 ml-2">较上周</span>
          </div>
        </div>
      </div>

      <div class="bg-white rounded-lg shadow-sm p-6">
        <div class="flex items-center justify-between">
          <div>
            <p class="text-sm text-gray-500">代码评审</p>
            <h3 class="text-2xl font-bold mt-1">16</h3>
          </div>
          <div class="w-10 h-10 rounded-full bg-green-100 flex items-center justify-center">
            <i class="fas fa-code text-green-500"></i>
          </div>
        </div>
        <div class="mt-4">
          <div class="flex items-center text-sm">
            <span class="text-green-500">
              <i class="fas fa-arrow-up mr-1"></i>8%
            </span>
            <span class="text-gray-500 ml-2">较上周</span>
          </div>
        </div>
      </div>

      <div class="bg-white rounded-lg shadow-sm p-6">
        <div class="flex items-center justify-between">
          <div>
            <p class="text-sm text-gray-500">本周已批改</p>
            <h3 class="text-2xl font-bold mt-1">156</h3>
          </div>
          <div class="w-10 h-10 rounded-full bg-orange-100 flex items-center justify-center">
            <i class="fas fa-check-circle text-orange-500"></i>
          </div>
        </div>
        <div class="mt-4">
          <div class="flex items-center text-sm">
            <span class="text-green-500">
              <i class="fas fa-arrow-up mr-1"></i>15%
            </span>
            <span class="text-gray-500 ml-2">较上周</span>
          </div>
        </div>
      </div>
    </div>

    <div class="grid grid-cols-2 gap-6">
      <!-- 批改进度 -->
      <div class="bg-white rounded-lg shadow-sm p-6">
        <div class="flex items-center justify-between mb-6">
          <h3 class="text-lg font-medium">批改进度</h3>
          <select class="border rounded-md px-3 py-1 text-sm">
            <option>最近7天</option>
            <option>最近30天</option>
            <option>最近90天</option>
          </select>
        </div>
        <div class="space-y-4">
          <div>
            <div class="flex items-center justify-between mb-2">
              <span class="text-sm font-medium">Python程序设计作业</span>
              <span class="text-sm text-gray-500">85%</span>
            </div>
            <div class="h-2 bg-gray-200 rounded-full">
              <div class="h-2 bg-blue-500 rounded-full" style="width: 85%"></div>
            </div>
          </div>
          <div>
            <div class="flex items-center justify-between mb-2">
              <span class="text-sm font-medium">数据结构期中考试</span>
              <span class="text-sm text-gray-500">60%</span>
            </div>
            <div class="h-2 bg-gray-200 rounded-full">
              <div class="h-2 bg-blue-500 rounded-full" style="width: 60%"></div>
            </div>
          </div>
          <div>
            <div class="flex items-center justify-between mb-2">
              <span class="text-sm font-medium">算法设计代码评审</span>
              <span class="text-sm text-gray-500">45%</span>
            </div>
            <div class="h-2 bg-gray-200 rounded-full">
              <div class="h-2 bg-blue-500 rounded-full" style="width: 45%"></div>
            </div>
          </div>
        </div>
      </div>

      <!-- 批改统计 -->
      <div class="bg-white rounded-lg shadow-sm p-6">
        <div class="flex items-center justify-between mb-6">
          <h3 class="text-lg font-medium">批改统计</h3>
          <select class="border rounded-md px-3 py-1 text-sm">
            <option>按课程</option>
            <option>按类型</option>
            <option>按时间</option>
          </select>
        </div>
        <div class="grid grid-cols-2 gap-4">
          <div class="p-4 bg-gray-50 rounded-lg">
            <div class="flex items-center justify-between">
              <div>
                <p class="text-sm text-gray-500">平均分</p>
                <p class="text-xl font-bold mt-1">85.6</p>
              </div>
              <div class="text-green-500">
                <i class="fas fa-arrow-up"></i>
              </div>
            </div>
          </div>
          <div class="p-4 bg-gray-50 rounded-lg">
            <div class="flex items-center justify-between">
              <div>
                <p class="text-sm text-gray-500">及格率</p>
                <p class="text-xl font-bold mt-1">92%</p>
              </div>
              <div class="text-green-500">
                <i class="fas fa-arrow-up"></i>
              </div>
            </div>
          </div>
          <div class="p-4 bg-gray-50 rounded-lg">
            <div class="flex items-center justify-between">
              <div>
                <p class="text-sm text-gray-500">优秀率</p>
                <p class="text-xl font-bold mt-1">45%</p>
              </div>
              <div class="text-red-500">
                <i class="fas fa-arrow-down"></i>
              </div>
            </div>
          </div>
          <div class="p-4 bg-gray-50 rounded-lg">
            <div class="flex items-center justify-between">
              <div>
                <p class="text-sm text-gray-500">批改速度</p>
                <p class="text-xl font-bold mt-1">5min</p>
              </div>
              <div class="text-green-500">
                <i class="fas fa-arrow-up"></i>
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