<template>
  <div class="p-6 bg-gray-50 min-h-screen">
    <div class="mb-8">
      <h1 class="text-2xl font-bold text-gray-900 mb-2">作业批改</h1>
      <p class="text-gray-600">批改学生提交的作业并给出评分</p>
    </div>

    <div class="bg-white p-6 rounded-lg shadow-sm border border-gray-200">
      <h2 class="text-lg font-semibold text-gray-900 mb-4">作业批改功能</h2>
      <p class="text-gray-600">在这里批改学生提交的作业。</p>
      
      <div class="mt-6 grid grid-cols-1 md:grid-cols-3 gap-4">
        <div class="p-4 border border-gray-200 rounded-lg">
          <i class="fas fa-check-circle text-2xl text-blue-600 mb-2"></i>
          <h3 class="font-medium text-gray-900">在线批改</h3>
          <p class="text-sm text-gray-600">在线查看和批改作业</p>
        </div>
        
        <div class="p-4 border border-gray-200 rounded-lg">
          <i class="fas fa-comments text-2xl text-green-600 mb-2"></i>
          <h3 class="font-medium text-gray-900">评语系统</h3>
          <p class="text-sm text-gray-600">为作业添加详细评语</p>
        </div>
        
        <div class="p-4 border border-gray-200 rounded-lg">
          <i class="fas fa-check-double text-2xl text-purple-600 mb-2"></i>
          <h3 class="font-medium text-gray-900">批量批改</h3>
          <p class="text-sm text-gray-600">批量处理相似作业</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
// 作业批改页面逻辑
</script> 