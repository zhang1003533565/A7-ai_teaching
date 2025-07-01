<!-- 题目导出页面 -->
<template>
  <div class="space-y-6">
    <!-- 页面标题 -->
    <div class="flex items-center justify-between">
      <h2 class="text-2xl font-bold text-gray-800">题目导出</h2>
    </div>

    <div class="bg-white rounded-lg shadow-sm p-6">
      <!-- 导出选项 -->
      <div class="space-y-6">
        <!-- 导出范围 -->
        <div>
          <h3 class="text-lg font-medium text-gray-900 mb-4">导出范围</h3>
          <div class="space-y-4">
            <div class="flex items-center space-x-4">
              <label class="inline-flex items-center">
                <input type="radio" class="form-radio text-blue-500" name="exportRange" value="all" checked>
                <span class="ml-2">导出全部题目</span>
              </label>
              <label class="inline-flex items-center">
                <input type="radio" class="form-radio text-blue-500" name="exportRange" value="selected">
                <span class="ml-2">导出选中题目</span>
              </label>
              <label class="inline-flex items-center">
                <input type="radio" class="form-radio text-blue-500" name="exportRange" value="filtered">
                <span class="ml-2">导出筛选结果</span>
              </label>
            </div>
          </div>
        </div>

        <!-- 导出内容 -->
        <div>
          <h3 class="text-lg font-medium text-gray-900 mb-4">导出内容</h3>
          <div class="grid grid-cols-2 md:grid-cols-4 gap-4">
            <label class="inline-flex items-center">
              <input type="checkbox" class="form-checkbox text-blue-500" checked>
              <span class="ml-2">题目标题</span>
            </label>
            <label class="inline-flex items-center">
              <input type="checkbox" class="form-checkbox text-blue-500" checked>
              <span class="ml-2">题目描述</span>
            </label>
            <label class="inline-flex items-center">
              <input type="checkbox" class="form-checkbox text-blue-500" checked>
              <span class="ml-2">题目类型</span>
            </label>
            <label class="inline-flex items-center">
              <input type="checkbox" class="form-checkbox text-blue-500" checked>
              <span class="ml-2">难度等级</span>
            </label>
            <label class="inline-flex items-center">
              <input type="checkbox" class="form-checkbox text-blue-500" checked>
              <span class="ml-2">分类信息</span>
            </label>
            <label class="inline-flex items-center">
              <input type="checkbox" class="form-checkbox text-blue-500" checked>
              <span class="ml-2">标签信息</span>
            </label>
            <label class="inline-flex items-center">
              <input type="checkbox" class="form-checkbox text-blue-500" checked>
              <span class="ml-2">参考答案</span>
            </label>
            <label class="inline-flex items-center">
              <input type="checkbox" class="form-checkbox text-blue-500">
              <span class="ml-2">创建时间</span>
            </label>
          </div>
        </div>

        <!-- 导出格式 -->
        <div>
          <h3 class="text-lg font-medium text-gray-900 mb-4">导出格式</h3>
          <div class="flex space-x-4">
            <label class="inline-flex items-center">
              <input type="radio" class="form-radio text-blue-500" name="exportFormat" value="excel" checked>
              <span class="ml-2">Excel (.xlsx)</span>
            </label>
            <label class="inline-flex items-center">
              <input type="radio" class="form-radio text-blue-500" name="exportFormat" value="csv">
              <span class="ml-2">CSV (.csv)</span>
            </label>
            <label class="inline-flex items-center">
              <input type="radio" class="form-radio text-blue-500" name="exportFormat" value="json">
              <span class="ml-2">JSON (.json)</span>
            </label>
          </div>
        </div>

        <!-- 导出按钮 -->
        <div class="flex justify-end space-x-4 pt-4">
          <button class="btn-outline">
            取消
          </button>
          <button class="btn-primary">
            <i class="fas fa-download mr-2"></i>开始导出
          </button>
        </div>
      </div>

      <!-- 导出记录 -->
      <div class="mt-8">
        <h3 class="text-lg font-medium text-gray-900 mb-4">导出记录</h3>
        <table class="min-w-full">
          <thead>
            <tr class="bg-gray-50">
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">文件名</th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">导出时间</th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">题目数量</th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">状态</th>
              <th class="px-6 py-3 text-right text-xs font-medium text-gray-500 uppercase tracking-wider">操作</th>
            </tr>
          </thead>
          <tbody class="bg-white divide-y divide-gray-200">
            <tr v-for="i in 3" :key="i">
              <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">题库导出_{{ new Date().toLocaleDateString() }}.xlsx</td>
              <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">{{ new Date().toLocaleString() }}</td>
              <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">234</td>
              <td class="px-6 py-4 whitespace-nowrap">
                <span class="px-2 inline-flex text-xs leading-5 font-semibold rounded-full bg-green-100 text-green-800">
                  已完成
                </span>
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-right text-sm font-medium">
                <button class="text-blue-600 hover:text-blue-900">下载</button>
              </td>
            </tr>
          </tbody>
        </table>
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

/* 自定义单选框和复选框样式 */
.form-radio {
  @apply h-4 w-4 text-blue-600 focus:ring-blue-500 border-gray-300;
}

.form-checkbox {
  @apply h-4 w-4 text-blue-600 focus:ring-blue-500 border-gray-300 rounded;
}
</style> 