<template>
  <div class="p-6 bg-gray-50 min-h-screen">
    <div class="mb-8">
      <h1 class="text-2xl font-bold text-gray-900 mb-2">成绩管理</h1>
      <p class="text-gray-600">管理和查看学生作业成绩</p>
    </div>

    <div class="bg-white p-6 rounded-lg shadow-sm border border-gray-200">
      <h2 class="text-lg font-semibold text-gray-900 mb-4">成绩管理功能</h2>
      <p class="text-gray-600">在这里管理和分析学生作业成绩。</p>
      
      <div class="mt-6 grid grid-cols-1 md:grid-cols-3 gap-4">
        <div class="p-4 border border-gray-200 rounded-lg">
          <i class="fas fa-chart-line text-2xl text-blue-600 mb-2"></i>
          <h3 class="font-medium text-gray-900">成绩统计</h3>
          <p class="text-sm text-gray-600">查看成绩分布和统计</p>
        </div>
        
        <div class="p-4 border border-gray-200 rounded-lg">
          <i class="fas fa-download text-2xl text-green-600 mb-2"></i>
          <h3 class="font-medium text-gray-900">成绩导出</h3>
          <p class="text-sm text-gray-600">导出成绩表格</p>
        </div>
        
        <div class="p-4 border border-gray-200 rounded-lg">
          <i class="fas fa-edit text-2xl text-purple-600 mb-2"></i>
          <h3 class="font-medium text-gray-900">成绩修改</h3>
          <p class="text-sm text-gray-600">批量修改成绩</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
// 成绩管理页面逻辑
</script> 