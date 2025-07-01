<!-- 资源模板页面 -->
<template>
  <div class="space-y-6">
    <!-- 页面标题 -->
    <div class="flex items-center justify-between">
      <h2 class="text-2xl font-bold text-gray-800">资源模板</h2>
      <button class="btn-primary">
        <i class="fas fa-plus mr-2"></i>新建模板
      </button>
    </div>

    <!-- 模板分类标签 -->
    <div class="bg-white rounded-lg shadow-sm p-4">
      <div class="flex items-center space-x-4">
        <button 
          v-for="category in ['全部', '课件模板', '试卷模板', '作业模板', '教案模板']" 
          :key="category"
          class="px-4 py-2 rounded-full text-sm font-medium"
          :class="category === '全部' ? 'bg-blue-100 text-blue-600' : 'text-gray-600 hover:bg-gray-100'"
        >
          {{ category }}
        </button>
      </div>
    </div>

    <!-- 模板列表 -->
    <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
      <!-- 模板卡片 -->
      <div v-for="i in 6" :key="i" class="bg-white rounded-lg shadow-sm overflow-hidden group">
        <div class="aspect-w-16 aspect-h-9 bg-gray-100">
          <img 
            :src="`https://picsum.photos/400/225?random=${i}`" 
            class="object-cover w-full h-full"
            alt="模板预览"
          />
          <div class="absolute inset-0 bg-black bg-opacity-40 opacity-0 group-hover:opacity-100 transition-opacity flex items-center justify-center space-x-4">
            <button class="btn-icon">
              <i class="fas fa-edit"></i>
            </button>
            <button class="btn-icon">
              <i class="fas fa-copy"></i>
            </button>
            <button class="btn-icon">
              <i class="fas fa-download"></i>
            </button>
            <button class="btn-icon text-red-500 hover:text-red-600">
              <i class="fas fa-trash"></i>
            </button>
          </div>
        </div>
        <div class="p-4">
          <div class="flex items-start justify-between">
            <div>
              <h3 class="text-lg font-medium text-gray-900">教学课件模板 {{ i }}</h3>
              <p class="mt-1 text-sm text-gray-500">适用于教学课件制作的精美模板</p>
            </div>
            <span class="inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium bg-green-100 text-green-800">
              课件模板
            </span>
          </div>
          <div class="mt-4 flex items-center justify-between text-sm text-gray-500">
            <div class="flex items-center">
              <i class="fas fa-download mr-1"></i>
              <span>{{ 100 + i * 23 }}次下载</span>
            </div>
            <div class="flex items-center">
              <i class="fas fa-clock mr-1"></i>
              <span>2024-03-{{ 10 + i }}</span>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- 分页 -->
    <div class="flex items-center justify-center space-x-2 pt-4">
      <button class="btn-outline-sm">上一页</button>
      <button class="btn-outline-sm bg-blue-50">1</button>
      <button class="btn-outline-sm">2</button>
      <button class="btn-outline-sm">3</button>
      <button class="btn-outline-sm">下一页</button>
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

.btn-outline-sm {
  @apply px-3 py-1 border border-gray-300 rounded-md hover:bg-gray-50 transition-colors;
}

.btn-icon {
  @apply w-8 h-8 rounded-full bg-white bg-opacity-90 text-gray-700 hover:text-blue-600 flex items-center justify-center transition-colors;
}

/* 设置图片容器的宽高比 */
.aspect-w-16 {
  position: relative;
  padding-bottom: 56.25%; /* 16:9 */
}

.aspect-h-9 {
  position: absolute;
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
}
</style> 