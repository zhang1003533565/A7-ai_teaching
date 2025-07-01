<!-- 批改模板页面 -->
<template>
  <div class="h-[calc(100vh-7rem)] p-6 bg-gray-50">
    <!-- 页面标题和操作 -->
    <div class="flex items-center justify-between mb-6">
      <h2 class="text-xl font-medium">批改模板管理</h2>
      <button class="btn-primary">
        <i class="fas fa-plus mr-2"></i>新建模板
      </button>
    </div>

    <!-- 模板列表 -->
    <div class="grid grid-cols-3 gap-6">
      <!-- 作业批改模板 -->
      <div class="bg-white rounded-lg shadow-sm">
        <div class="p-4 border-b border-gray-200">
          <div class="flex items-center justify-between">
            <h3 class="font-medium">Python作业批改</h3>
            <div class="flex items-center space-x-2">
              <button class="btn-icon-sm" title="编辑">
                <i class="fas fa-edit"></i>
              </button>
              <button class="btn-icon-sm" title="删除">
                <i class="fas fa-trash"></i>
              </button>
            </div>
          </div>
          <p class="mt-2 text-sm text-gray-500">适用于Python程序设计课程的作业批改</p>
        </div>
        <div class="p-4">
          <div class="space-y-3">
            <div>
              <h4 class="text-sm font-medium mb-2">评分项目</h4>
              <div class="space-y-2">
                <div class="flex items-center justify-between text-sm">
                  <span>功能实现</span>
                  <span>40分</span>
                </div>
                <div class="flex items-center justify-between text-sm">
                  <span>代码规范</span>
                  <span>20分</span>
                </div>
                <div class="flex items-center justify-between text-sm">
                  <span>性能优化</span>
                  <span>20分</span>
                </div>
                <div class="flex items-center justify-between text-sm">
                  <span>文档注释</span>
                  <span>20分</span>
                </div>
              </div>
            </div>
            <div class="pt-3 border-t border-gray-200">
              <h4 class="text-sm font-medium mb-2">智能评分规则</h4>
              <div class="space-y-2 text-sm text-gray-600">
                <p>• 自动检查代码运行结果</p>
                <p>• 代码静态分析评估</p>
                <p>• 注释完整性检查</p>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- 试卷批改模板 -->
      <div class="bg-white rounded-lg shadow-sm">
        <div class="p-4 border-b border-gray-200">
          <div class="flex items-center justify-between">
            <h3 class="font-medium">数据结构试卷批改</h3>
            <div class="flex items-center space-x-2">
              <button class="btn-icon-sm" title="编辑">
                <i class="fas fa-edit"></i>
              </button>
              <button class="btn-icon-sm" title="删除">
                <i class="fas fa-trash"></i>
              </button>
            </div>
          </div>
          <p class="mt-2 text-sm text-gray-500">适用于数据结构与算法课程的试卷批改</p>
        </div>
        <div class="p-4">
          <div class="space-y-3">
            <div>
              <h4 class="text-sm font-medium mb-2">题型配置</h4>
              <div class="space-y-2">
                <div class="flex items-center justify-between text-sm">
                  <span>选择题</span>
                  <span>30分</span>
                </div>
                <div class="flex items-center justify-between text-sm">
                  <span>填空题</span>
                  <span>20分</span>
                </div>
                <div class="flex items-center justify-between text-sm">
                  <span>简答题</span>
                  <span>20分</span>
                </div>
                <div class="flex items-center justify-between text-sm">
                  <span>算法题</span>
                  <span>30分</span>
                </div>
              </div>
            </div>
            <div class="pt-3 border-t border-gray-200">
              <h4 class="text-sm font-medium mb-2">智能评分规则</h4>
              <div class="space-y-2 text-sm text-gray-600">
                <p>• 标准答案匹配</p>
                <p>• 关键词识别评分</p>
                <p>• 算法正确性验证</p>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- 代码评审模板 -->
      <div class="bg-white rounded-lg shadow-sm">
        <div class="p-4 border-b border-gray-200">
          <div class="flex items-center justify-between">
            <h3 class="font-medium">代码评审标准</h3>
            <div class="flex items-center space-x-2">
              <button class="btn-icon-sm" title="编辑">
                <i class="fas fa-edit"></i>
              </button>
              <button class="btn-icon-sm" title="删除">
                <i class="fas fa-trash"></i>
              </button>
            </div>
          </div>
          <p class="mt-2 text-sm text-gray-500">通用代码评审标准和规范</p>
        </div>
        <div class="p-4">
          <div class="space-y-3">
            <div>
              <h4 class="text-sm font-medium mb-2">评审维度</h4>
              <div class="space-y-2">
                <div class="flex items-center justify-between text-sm">
                  <span>代码质量</span>
                  <span>30分</span>
                </div>
                <div class="flex items-center justify-between text-sm">
                  <span>性能优化</span>
                  <span>25分</span>
                </div>
                <div class="flex items-center justify-between text-sm">
                  <span>安全性</span>
                  <span>25分</span>
                </div>
                <div class="flex items-center justify-between text-sm">
                  <span>可维护性</span>
                  <span>20分</span>
                </div>
              </div>
            </div>
            <div class="pt-3 border-t border-gray-200">
              <h4 class="text-sm font-medium mb-2">检查项目</h4>
              <div class="space-y-2 text-sm text-gray-600">
                <p>• 代码规范检查</p>
                <p>• 复杂度分析</p>
                <p>• 安全漏洞扫描</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- 最近使用的模板 -->
    <div class="mt-8">
      <h3 class="text-lg font-medium mb-4">最近使用</h3>
      <div class="bg-white rounded-lg shadow-sm">
        <table class="min-w-full">
          <thead>
            <tr class="bg-gray-50">
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                模板名称
              </th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                类型
              </th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                使用次数
              </th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                最后使用时间
              </th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                操作
              </th>
            </tr>
          </thead>
          <tbody class="bg-white divide-y divide-gray-200">
            <tr v-for="i in 5" :key="i">
              <td class="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900">
                Python作业批改
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                作业批改
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                {{ 20 - i * 2 }}
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                2024-03-{{ 20 - i }}
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                <button class="text-blue-600 hover:text-blue-800">
                  使用
                </button>
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

.btn-icon-sm {
  @apply w-6 h-6 flex items-center justify-center rounded text-gray-500 hover:bg-gray-100 transition-colors;
}
</style> 