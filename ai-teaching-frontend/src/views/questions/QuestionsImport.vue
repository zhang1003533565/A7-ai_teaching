<!-- 题目导入页面 -->
<template>
  <div class="space-y-6">
    <!-- 页面标题 -->
    <div class="flex items-center justify-between">
      <h2 class="text-2xl font-bold text-gray-800">题目导入</h2>
    </div>

    <div class="bg-white rounded-lg shadow-sm p-6">
      <!-- 导入步骤 -->
      <div class="mb-8">
        <div class="flex items-center">
          <div class="flex items-center relative">
            <div class="rounded-full transition duration-500 ease-in-out h-12 w-12 py-3 border-2 bg-blue-500 border-blue-500">
              <svg xmlns="http://www.w3.org/2000/svg" width="100%" height="100%" fill="none" viewBox="0 0 24 24" stroke="white" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-file">
                <path d="M13 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V9z"></path>
                <polyline points="13 2 13 9 20 9"></polyline>
              </svg>
            </div>
            <div class="absolute top-0 -ml-10 text-center mt-16 w-32 text-xs font-medium text-blue-500">选择文件</div>
          </div>
          <div class="flex-auto border-t-2 border-blue-500"></div>
          <div class="flex items-center relative">
            <div class="rounded-full transition duration-500 ease-in-out h-12 w-12 py-3 border-2 border-gray-300">
              <svg xmlns="http://www.w3.org/2000/svg" width="100%" height="100%" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-settings">
                <circle cx="12" cy="12" r="3"></circle>
                <path d="M19.4 15a1.65 1.65 0 0 0 .33 1.82l.06.06a2 2 0 0 1 0 2.83 2 2 0 0 1-2.83 0l-.06-.06a1.65 1.65 0 0 0-1.82-.33 1.65 1.65 0 0 0-1 1.51V21a2 2 0 0 1-2 2 2 2 0 0 1-2-2v-.09A1.65 1.65 0 0 0 9 19.4a1.65 1.65 0 0 0-1.82.33l-.06.06a2 2 0 0 1-2.83 0 2 2 0 0 1 0-2.83l.06-.06a1.65 1.65 0 0 0 .33-1.82 1.65 1.65 0 0 0-1.51-1H3a2 2 0 0 1-2-2 2 2 0 0 1 2-2h.09A1.65 1.65 0 0 0 4.6 9a1.65 1.65 0 0 0-.33-1.82l-.06-.06a2 2 0 0 1 0-2.83 2 2 0 0 1 2.83 0l.06.06a1.65 1.65 0 0 0 1.82.33H9a1.65 1.65 0 0 0 1-1.51V3a2 2 0 0 1 2-2 2 2 0 0 1 2 2v.09a1.65 1.65 0 0 0 1 1.51 1.65 1.65 0 0 0 1.82-.33l.06-.06a2 2 0 0 1 2.83 0 2 2 0 0 1 0 2.83l-.06.06a1.65 1.65 0 0 0-.33 1.82V9a1.65 1.65 0 0 0 1.51 1H21a2 2 0 0 1 2 2 2 2 0 0 1-2 2h-.09a1.65 1.65 0 0 0-1.51 1z"></path>
              </svg>
            </div>
            <div class="absolute top-0 -ml-10 text-center mt-16 w-32 text-xs font-medium text-gray-500">导入设置</div>
          </div>
          <div class="flex-auto border-t-2 border-gray-300"></div>
          <div class="flex items-center relative">
            <div class="rounded-full transition duration-500 ease-in-out h-12 w-12 py-3 border-2 border-gray-300">
              <svg xmlns="http://www.w3.org/2000/svg" width="100%" height="100%" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-check-circle">
                <path d="M22 11.08V12a10 10 0 1 1-5.93-9.14"></path>
                <polyline points="22 4 12 14.01 9 11.01"></polyline>
              </svg>
            </div>
            <div class="absolute top-0 -ml-10 text-center mt-16 w-32 text-xs font-medium text-gray-500">确认导入</div>
          </div>
        </div>
      </div>

      <!-- 文件上传区域 -->
      <div class="border-2 border-dashed border-gray-300 rounded-lg p-8 text-center">
        <div class="space-y-4">
          <i class="fas fa-cloud-upload-alt text-4xl text-gray-400"></i>
          <div>
            <p class="text-gray-600">将文件拖放到此处，或</p>
            <button class="mt-2 btn-primary">
              选择文件
            </button>
          </div>
          <p class="text-sm text-gray-500">支持 .xlsx, .xls, .csv 格式文件</p>
        </div>
      </div>

      <!-- 导入模板 -->
      <div class="mt-6">
        <h3 class="text-lg font-medium text-gray-900 mb-4">导入说明</h3>
        <div class="bg-gray-50 rounded-lg p-4 space-y-2">
          <p class="text-sm text-gray-600">1. 下载标准导入模板，按模板格式填写题目信息</p>
          <p class="text-sm text-gray-600">2. 支持批量导入多个题目，每个题目占一行</p>
          <p class="text-sm text-gray-600">3. 必填字段：题目标题、题目类型、难度等级</p>
          <p class="text-sm text-gray-600">4. 选填字段：题目描述、参考答案、分类、标签等</p>
          <div class="pt-2">
            <button class="btn-outline-sm">
              <i class="fas fa-download mr-2"></i>下载导入模板
            </button>
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

.btn-outline-sm {
  @apply px-3 py-1 text-sm border border-gray-300 rounded-md hover:bg-gray-50 transition-colors;
}
</style> 