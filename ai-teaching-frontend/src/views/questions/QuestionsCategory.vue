<!-- 分类管理页面 -->
<template>
  <div class="space-y-6">
    <!-- 页面标题和操作按钮 -->
    <div class="flex items-center justify-between">
      <h2 class="text-2xl font-bold text-gray-800">分类管理</h2>
      <button class="btn-primary">
        <i class="fas fa-plus mr-2"></i>新建分类
      </button>
    </div>

    <div class="grid grid-cols-1 md:grid-cols-3 gap-6">
      <!-- 分类列表 -->
      <div class="md:col-span-2">
        <div class="bg-white rounded-lg shadow-sm">
          <div class="p-6">
            <div class="flex items-center justify-between mb-4">
              <h3 class="text-lg font-semibold">分类列表</h3>
              <div class="flex items-center space-x-2">
                <input
                  type="text"
                  placeholder="搜索分类..."
                  class="px-3 py-1 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                />
                <button class="btn-outline-sm">
                  <i class="fas fa-search"></i>
                </button>
              </div>
            </div>

            <table class="min-w-full">
              <thead>
                <tr class="bg-gray-50">
                  <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">分类名称</th>
                  <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">题目数量</th>
                  <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">创建时间</th>
                  <th class="px-6 py-3 text-right text-xs font-medium text-gray-500 uppercase tracking-wider">操作</th>
                </tr>
              </thead>
              <tbody class="bg-white divide-y divide-gray-200">
                <tr v-for="i in 8" :key="i">
                  <td class="px-6 py-4 whitespace-nowrap">
                    <div class="flex items-center">
                      <i class="fas fa-folder text-yellow-500 mr-2"></i>
                      <span class="text-sm text-gray-900">编程基础 {{ i }}</span>
                    </div>
                  </td>
                  <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">{{ 20 + i }}</td>
                  <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">2024-03-20</td>
                  <td class="px-6 py-4 whitespace-nowrap text-right text-sm font-medium space-x-2">
                    <button class="text-blue-600 hover:text-blue-900">编辑</button>
                    <button class="text-red-600 hover:text-red-900">删除</button>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>

      <!-- 分类详情/编辑表单 -->
      <div class="md:col-span-1">
        <div class="bg-white rounded-lg shadow-sm p-6">
          <h3 class="text-lg font-semibold mb-4">编辑分类</h3>
          <form class="space-y-4">
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">分类名称</label>
              <input
                type="text"
                class="w-full px-3 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                placeholder="请输入分类名称"
              />
            </div>
            
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">分类描述</label>
              <textarea
                rows="4"
                class="w-full px-3 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                placeholder="请输入分类描述"
              ></textarea>
            </div>

            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">排序</label>
              <input
                type="number"
                class="w-full px-3 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                placeholder="请输入排序号"
              />
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