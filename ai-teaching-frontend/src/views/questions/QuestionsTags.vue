<!-- 标签管理页面 -->
<template>
  <div class="space-y-6">
    <!-- 页面标题和操作按钮 -->
    <div class="flex items-center justify-between">
      <h2 class="text-2xl font-bold text-gray-800">标签管理</h2>
      <button class="btn-primary">
        <i class="fas fa-plus mr-2"></i>新建标签
      </button>
    </div>

    <div class="grid grid-cols-1 md:grid-cols-3 gap-6">
      <!-- 标签列表 -->
      <div class="md:col-span-2">
        <div class="bg-white rounded-lg shadow-sm">
          <div class="p-6">
            <div class="flex items-center justify-between mb-4">
              <h3 class="text-lg font-semibold">标签列表</h3>
              <div class="flex items-center space-x-2">
                <input
                  type="text"
                  placeholder="搜索标签..."
                  class="px-3 py-1 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                />
                <button class="btn-outline-sm">
                  <i class="fas fa-search"></i>
                </button>
              </div>
            </div>

            <!-- 标签展示区域 -->
            <div class="space-y-4">
              <div v-for="category in ['算法', '数据结构', '编程语言', '开发工具']" :key="category" class="border rounded-lg p-4">
                <h4 class="text-sm font-medium text-gray-700 mb-3">{{ category }}</h4>
                <div class="flex flex-wrap gap-2">
                  <div 
                    v-for="i in 5" 
                    :key="i"
                    class="group relative inline-flex items-center bg-gray-100 rounded-full px-4 py-1"
                  >
                    <span class="text-sm text-gray-700">{{ category }}标签{{ i }}</span>
                    <span class="ml-2 text-xs text-gray-500">(23)</span>
                    <div class="hidden group-hover:flex absolute right-0 top-0 h-full items-center pr-2 space-x-1">
                      <button class="p-1 hover:text-blue-600">
                        <i class="fas fa-edit text-xs"></i>
                      </button>
                      <button class="p-1 hover:text-red-600">
                        <i class="fas fa-times text-xs"></i>
                      </button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- 标签详情/编辑表单 -->
      <div class="md:col-span-1">
        <div class="bg-white rounded-lg shadow-sm p-6">
          <h3 class="text-lg font-semibold mb-4">编辑标签</h3>
          <form class="space-y-4">
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">标签名称</label>
              <input
                type="text"
                class="w-full px-3 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                placeholder="请输入标签名称"
              />
            </div>

            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">所属分类</label>
              <select class="w-full px-3 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
                <option value="">请选择分类</option>
                <option>算法</option>
                <option>数据结构</option>
                <option>编程语言</option>
                <option>开发工具</option>
              </select>
            </div>
            
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">标签描述</label>
              <textarea
                rows="4"
                class="w-full px-3 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                placeholder="请输入标签描述"
              ></textarea>
            </div>

            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">标签颜色</label>
              <div class="grid grid-cols-8 gap-2">
                <div
                  v-for="color in ['bg-red-100', 'bg-yellow-100', 'bg-green-100', 'bg-blue-100', 'bg-indigo-100', 'bg-purple-100', 'bg-pink-100', 'bg-gray-100']"
                  :key="color"
                  :class="[color, 'w-6 h-6 rounded-full cursor-pointer hover:ring-2 hover:ring-offset-2 hover:ring-blue-500']"
                ></div>
              </div>
            </div>

            <div class="flex items-center justify-end space-x-3">
              <button type="button" class="btn-outline">取消</button>
              <button type="submit" class="btn-primary">保存</button>
            </div>
          </form>
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
  @apply px-2 py-1 border border-gray-300 rounded-md hover:bg-gray-50 transition-colors;
}
</style> 