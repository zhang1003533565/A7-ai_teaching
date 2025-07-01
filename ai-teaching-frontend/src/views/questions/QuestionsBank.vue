<!-- 题目管理页面 -->
<template>
  <div class="space-y-6">
    <!-- 页面标题和操作按钮 -->
    <div class="flex items-center justify-between">
      <h2 class="text-2xl font-bold text-gray-800">题目管理</h2>
      <div class="flex space-x-4">
        <button class="btn-outline">
          <i class="fas fa-file-import mr-2"></i>导入题目
        </button>
        <button class="btn-primary">
          <i class="fas fa-plus mr-2"></i>新建题目
        </button>
      </div>
    </div>

    <!-- 搜索和筛选 -->
    <div class="bg-white p-6 rounded-lg shadow-sm space-y-4">
      <div class="grid grid-cols-1 md:grid-cols-4 gap-4">
        <div>
          <input
            type="text"
            placeholder="搜索题目..."
            class="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
          />
        </div>
        <div>
          <select class="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
            <option value="">全部分类</option>
            <option>编程基础</option>
            <option>算法</option>
            <option>数据结构</option>
          </select>
        </div>
        <div>
          <select class="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
            <option value="">全部难度</option>
            <option>简单</option>
            <option>中等</option>
            <option>困难</option>
          </select>
        </div>
        <div>
          <select class="w-full px-4 py-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
            <option value="">全部状态</option>
            <option>已发布</option>
            <option>草稿</option>
          </select>
        </div>
      </div>
    </div>

    <!-- 题目列表 -->
    <div class="bg-white rounded-lg shadow-sm">
      <div class="overflow-x-auto">
        <table class="min-w-full">
          <thead>
            <tr class="bg-gray-50">
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                <input type="checkbox" class="rounded text-blue-500 focus:ring-blue-500" />
              </th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">题目ID</th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">题目标题</th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">分类</th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">难度</th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">状态</th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">更新时间</th>
              <th class="px-6 py-3 text-right text-xs font-medium text-gray-500 uppercase tracking-wider">操作</th>
            </tr>
          </thead>
          <tbody class="bg-white divide-y divide-gray-200">
            <tr v-for="i in 10" :key="i">
              <td class="px-6 py-4 whitespace-nowrap">
                <input type="checkbox" class="rounded text-blue-500 focus:ring-blue-500" />
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">#{{ 1000 + i }}</td>
              <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">示例题目标题</td>
              <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">编程基础</td>
              <td class="px-6 py-4 whitespace-nowrap">
                <span class="px-2 inline-flex text-xs leading-5 font-semibold rounded-full bg-green-100 text-green-800">
                  简单
                </span>
              </td>
              <td class="px-6 py-4 whitespace-nowrap">
                <span class="px-2 inline-flex text-xs leading-5 font-semibold rounded-full bg-blue-100 text-blue-800">
                  已发布
                </span>
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">2024-03-20</td>
              <td class="px-6 py-4 whitespace-nowrap text-right text-sm font-medium space-x-2">
                <button class="text-blue-600 hover:text-blue-900">编辑</button>
                <button class="text-red-600 hover:text-red-900">删除</button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      
      <!-- 分页 -->
      <div class="px-6 py-4 flex items-center justify-between border-t border-gray-200">
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
          <button class="px-3 py-1 border rounded-md hover:bg-gray-50">上一页</button>
          <button class="px-3 py-1 bg-blue-500 text-white rounded-md">1</button>
          <button class="px-3 py-1 border rounded-md hover:bg-gray-50">2</button>
          <button class="px-3 py-1 border rounded-md hover:bg-gray-50">3</button>
          <button class="px-3 py-1 border rounded-md hover:bg-gray-50">下一页</button>
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
</style> 