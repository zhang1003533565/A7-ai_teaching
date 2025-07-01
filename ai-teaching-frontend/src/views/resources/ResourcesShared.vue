<!-- 共享资源页面 -->
<template>
  <div class="space-y-6">
    <!-- 页面标题 -->
    <div class="flex items-center justify-between">
      <h2 class="text-2xl font-bold text-gray-800">共享资源</h2>
      <div class="flex space-x-4">
        <button class="btn-outline">
          <i class="fas fa-filter mr-2"></i>筛选
        </button>
        <button class="btn-primary">
          <i class="fas fa-share-alt mr-2"></i>分享资源
        </button>
      </div>
    </div>

    <!-- 搜索和筛选 -->
    <div class="bg-white rounded-lg shadow-sm p-4">
      <div class="grid grid-cols-1 md:grid-cols-4 gap-4">
        <div class="col-span-2">
          <div class="relative">
            <input
              type="text"
              placeholder="搜索共享资源..."
              class="w-full pl-10 pr-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
            />
            <i class="fas fa-search absolute left-3 top-3 text-gray-400"></i>
          </div>
        </div>
        <div>
          <select class="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
            <option value="">资源类型</option>
            <option>课件</option>
            <option>试卷</option>
            <option>教案</option>
            <option>视频</option>
          </select>
        </div>
        <div>
          <select class="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
            <option value="">排序方式</option>
            <option>最新分享</option>
            <option>下载最多</option>
            <option>评分最高</option>
          </select>
        </div>
      </div>
    </div>

    <!-- 资源列表 -->
    <div class="grid grid-cols-1 gap-6">
      <!-- 资源卡片 -->
      <div v-for="i in 5" :key="i" class="bg-white rounded-lg shadow-sm p-6">
        <div class="flex items-start space-x-6">
          <!-- 资源预览图 -->
          <div class="w-48 h-32 bg-gray-100 rounded-lg overflow-hidden flex-shrink-0">
            <img 
              :src="`https://picsum.photos/400/225?random=${i}`" 
              class="w-full h-full object-cover"
              alt="资源预览"
            />
          </div>
          
          <!-- 资源信息 -->
          <div class="flex-grow">
            <div class="flex items-start justify-between">
              <div>
                <h3 class="text-lg font-medium text-gray-900">高等数学第{{ i }}章教学课件</h3>
                <p class="mt-1 text-sm text-gray-500 line-clamp-2">
                  这是一份详细的高等数学教学课件，包含了完整的教学内容、习题和解析。适合大学教学使用。
                </p>
              </div>
              <div class="flex items-center space-x-2">
                <span class="inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium bg-blue-100 text-blue-800">
                  课件
                </span>
                <span class="inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium bg-green-100 text-green-800">
                  免费
                </span>
              </div>
            </div>
            
            <div class="mt-4 flex items-center text-sm text-gray-500 space-x-6">
              <div class="flex items-center">
                <i class="fas fa-user-circle mr-1"></i>
                <span>张老师</span>
              </div>
              <div class="flex items-center">
                <i class="fas fa-download mr-1"></i>
                <span>{{ 234 + i * 45 }}次下载</span>
              </div>
              <div class="flex items-center">
                <i class="fas fa-star text-yellow-400 mr-1"></i>
                <span>4.{{ 5 + i }}分</span>
              </div>
              <div class="flex items-center">
                <i class="fas fa-clock mr-1"></i>
                <span>2024-03-{{ 10 + i }}</span>
              </div>
            </div>

            <!-- 操作按钮 -->
            <div class="mt-4 flex items-center space-x-4">
              <button class="btn-outline-sm">
                <i class="fas fa-eye mr-1"></i>预览
              </button>
              <button class="btn-outline-sm">
                <i class="fas fa-download mr-1"></i>下载
              </button>
              <button class="btn-outline-sm">
                <i class="fas fa-share-alt mr-1"></i>分享
              </button>
              <button class="btn-outline-sm">
                <i class="fas fa-star mr-1"></i>收藏
              </button>
              <button class="text-gray-500 hover:text-gray-700">
                <i class="fas fa-ellipsis-h"></i>
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- 分页 -->
    <div class="flex items-center justify-between border-t border-gray-200 pt-4">
      <div class="flex items-center">
        <select class="mr-2 px-2 py-1 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
          <option>10条/页</option>
          <option>20条/页</option>
          <option>50条/页</option>
        </select>
        <span class="text-sm text-gray-700">
          共 100 条记录
        </span>
      </div>
      <div class="flex items-center space-x-2">
        <button class="btn-outline-sm">上一页</button>
        <button class="btn-outline-sm bg-blue-50">1</button>
        <button class="btn-outline-sm">2</button>
        <button class="btn-outline-sm">3</button>
        <button class="btn-outline-sm">下一页</button>
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
  @apply px-3 py-1 border border-gray-300 rounded-md hover:bg-gray-50 transition-colors;
}

/* 文本截断 */
.line-clamp-2 {
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
}
</style> 