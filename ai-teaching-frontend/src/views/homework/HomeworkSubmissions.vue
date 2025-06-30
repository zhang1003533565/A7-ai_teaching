<template>
  <div class="p-6 bg-gray-50 min-h-screen">
    <div class="mb-8">
      <h1 class="text-2xl font-bold text-gray-900 mb-2">提交统计</h1>
      <p class="text-gray-600">查看学生作业提交情况和统计分析</p>
    </div>

    <div class="bg-white p-6 rounded-lg shadow-sm border border-gray-200">
      <h2 class="text-lg font-semibold text-gray-900 mb-4">提交统计功能</h2>
      <p class="text-gray-600">在这里查看和分析学生作业提交情况。</p>
      
      <div class="mt-6 grid grid-cols-1 md:grid-cols-3 gap-4">
        <div class="p-4 border border-gray-200 rounded-lg">
          <i class="fas fa-chart-bar text-2xl text-blue-600 mb-2"></i>
          <h3 class="font-medium text-gray-900">提交率统计</h3>
          <p class="text-sm text-gray-600">查看作业提交率数据</p>
        </div>
        
        <div class="p-4 border border-gray-200 rounded-lg">
          <i class="fas fa-clock text-2xl text-green-600 mb-2"></i>
          <h3 class="font-medium text-gray-900">时间分析</h3>
          <p class="text-sm text-gray-600">分析提交时间分布</p>
        </div>
        
        <div class="p-4 border border-gray-200 rounded-lg">
          <i class="fas fa-users text-2xl text-purple-600 mb-2"></i>
          <h3 class="font-medium text-gray-900">学生跟踪</h3>
          <p class="text-sm text-gray-600">跟踪未提交学生</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
// 提交统计页面逻辑
</script> 