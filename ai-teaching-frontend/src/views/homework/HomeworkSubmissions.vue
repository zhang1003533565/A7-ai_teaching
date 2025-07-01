<template>
  <div class="p-6 bg-gray-50 min-h-screen">
    <div class="mb-8">
      <h1 class="text-2xl font-bold text-gray-900 mb-2">提交统计</h1>
      <p class="text-gray-600">查看学生作业提交情况和统计分析</p>
    </div>

    <div class="bg-white p-6 rounded-lg shadow-sm border border-gray-200">
      <h2 class="text-lg font-semibold text-gray-900 mb-4">提交统计功能</h2>
      <p class="text-gray-600">在这里查看和分析学生作业提交情况。</p>
      
      <div class="mt-6 grid grid-cols-1 md:grid-cols-3 gap-4">
        <div class="p-4 border border-gray-200 rounded-lg">
          <i class="fas fa-chart-bar text-2xl text-blue-600 mb-2"></i>
          <h3 class="font-medium text-gray-900">提交率统计</h3>
          <p class="text-sm text-gray-600">查看作业提交率数据</p>
        </div>
        
        <div class="p-4 border border-gray-200 rounded-lg">
          <i class="fas fa-clock text-2xl text-green-600 mb-2"></i>
          <h3 class="font-medium text-gray-900">时间分析</h3>
          <p class="text-sm text-gray-600">分析提交时间分布</p>
        </div>
        
        <div class="p-4 border border-gray-200 rounded-lg">
          <i class="fas fa-users text-2xl text-purple-600 mb-2"></i>
          <h3 class="font-medium text-gray-900">学生跟踪</h3>
          <p class="text-sm text-gray-600">跟踪未提交学生</p>
        </div>
      </div>
    </div>

    <!-- 统计卡片 -->
    <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6 mb-6">
      <div class="bg-white rounded-lg shadow p-6">
        <div class="flex items-center justify-between">
          <div>
            <p class="text-gray-500 text-sm">总提交次数</p>
            <p class="text-2xl font-semibold mt-2">2,456</p>
          </div>
          <div class="bg-blue-100 rounded-full p-3">
            <i class="fas fa-paper-plane text-blue-600"></i>
          </div>
        </div>
        <div class="mt-4">
          <span class="text-green-500 text-sm">
            <i class="fas fa-arrow-up"></i> 12%
          </span>
          <span class="text-gray-400 text-sm ml-2">较上周</span>
        </div>
      </div>

      <div class="bg-white rounded-lg shadow p-6">
        <div class="flex items-center justify-between">
          <div>
            <p class="text-gray-500 text-sm">按时提交率</p>
            <p class="text-2xl font-semibold mt-2">85.7%</p>
          </div>
          <div class="bg-green-100 rounded-full p-3">
            <i class="fas fa-clock text-green-600"></i>
          </div>
        </div>
        <div class="mt-4">
          <span class="text-green-500 text-sm">
            <i class="fas fa-arrow-up"></i> 5%
          </span>
          <span class="text-gray-400 text-sm ml-2">较上月</span>
        </div>
      </div>

      <div class="bg-white rounded-lg shadow p-6">
        <div class="flex items-center justify-between">
          <div>
            <p class="text-gray-500 text-sm">平均提交时间</p>
            <p class="text-2xl font-semibold mt-2">2.5天</p>
          </div>
          <div class="bg-purple-100 rounded-full p-3">
            <i class="fas fa-calendar-alt text-purple-600"></i>
          </div>
        </div>
        <div class="mt-4">
          <span class="text-red-500 text-sm">
            <i class="fas fa-arrow-down"></i> 0.5天
          </span>
          <span class="text-gray-400 text-sm ml-2">较上月</span>
        </div>
      </div>

      <div class="bg-white rounded-lg shadow p-6">
        <div class="flex items-center justify-between">
          <div>
            <p class="text-gray-500 text-sm">未提交人数</p>
            <p class="text-2xl font-semibold mt-2">15</p>
          </div>
          <div class="bg-red-100 rounded-full p-3">
            <i class="fas fa-exclamation-circle text-red-600"></i>
          </div>
        </div>
        <div class="mt-4">
          <span class="text-green-500 text-sm">
            <i class="fas fa-arrow-down"></i> 3人
          </span>
          <span class="text-gray-400 text-sm ml-2">较上周</span>
        </div>
      </div>
    </div>

    <!-- 图表区域 -->
    <div class="grid grid-cols-1 lg:grid-cols-2 gap-6 mb-6">
      <!-- 提交时间分布 -->
      <div class="bg-white rounded-lg shadow p-6">
        <h3 class="text-lg font-medium mb-4">提交时间分布</h3>
        <div class="h-80">
          <!-- 这里放图表组件 -->
          <div class="w-full h-full bg-gray-50 rounded flex items-center justify-center">
            图表占位 - 提交时间分布
          </div>
        </div>
      </div>

      <!-- 提交进度追踪 -->
      <div class="bg-white rounded-lg shadow p-6">
        <h3 class="text-lg font-medium mb-4">提交进度追踪</h3>
        <div class="h-80">
          <!-- 这里放图表组件 -->
          <div class="w-full h-full bg-gray-50 rounded flex items-center justify-center">
            图表占位 - 提交进度追踪
          </div>
        </div>
      </div>
    </div>

    <!-- 提交详情表格 -->
    <div class="bg-white rounded-lg shadow">
      <div class="p-6 border-b">
        <div class="flex items-center justify-between">
          <h3 class="text-lg font-medium">提交详情</h3>
          <div class="flex space-x-4">
            <div class="relative">
              <input
                type="text"
                placeholder="搜索..."
                class="pl-10 pr-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
              >
              <i class="fas fa-search absolute left-3 top-3 text-gray-400"></i>
            </div>
            <button class="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-500">
              导出数据
            </button>
          </div>
        </div>
      </div>
      
      <div class="overflow-x-auto">
        <table class="w-full">
          <thead class="bg-gray-50">
            <tr>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">学生</th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">作业名称</th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">提交时间</th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">状态</th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">提交次数</th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">操作</th>
            </tr>
          </thead>
          <tbody class="bg-white divide-y divide-gray-200">
            <tr v-for="i in 5" :key="i">
              <td class="px-6 py-4 whitespace-nowrap">
                <div class="flex items-center">
                  <div class="h-8 w-8 rounded-full bg-gray-200"></div>
                  <div class="ml-4">
                    <div class="text-sm font-medium text-gray-900">学生{{ i }}</div>
                    <div class="text-sm text-gray-500">student{{ i }}@example.com</div>
                  </div>
                </div>
              </td>
              <td class="px-6 py-4 whitespace-nowrap">
                <div class="text-sm text-gray-900">第{{ i }}次作业</div>
                <div class="text-sm text-gray-500">课程名称</div>
              </td>
              <td class="px-6 py-4 whitespace-nowrap">
                <div class="text-sm text-gray-900">2024-03-{{ i + 10 }} 14:30</div>
                <div class="text-sm text-gray-500">距截止还有2天</div>
              </td>
              <td class="px-6 py-4 whitespace-nowrap">
                <span class="px-2 inline-flex text-xs leading-5 font-semibold rounded-full"
                      :class="{
                        'bg-green-100 text-green-800': i % 3 === 0,
                        'bg-yellow-100 text-yellow-800': i % 3 === 1,
                        'bg-red-100 text-red-800': i % 3 === 2
                      }">
                  {{ ['已提交', '待批改', '已逾期'][i % 3] }}
                </span>
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                {{ i + 1 }}次
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm font-medium">
                <a href="#" class="text-blue-600 hover:text-blue-900 mr-3">查看</a>
                <a href="#" class="text-gray-600 hover:text-gray-900">详情</a>
              </td>
            </tr>
          </tbody>
        </table>
      </div>

      <!-- 分页 -->
      <div class="px-6 py-4 flex items-center justify-between border-t">
        <div class="flex-1 flex justify-between sm:hidden">
          <button class="px-4 py-2 border rounded text-sm font-medium text-gray-700 hover:bg-gray-50">
            上一页
          </button>
          <button class="ml-3 px-4 py-2 border rounded text-sm font-medium text-gray-700 hover:bg-gray-50">
            下一页
          </button>
        </div>
        <div class="hidden sm:flex-1 sm:flex sm:items-center sm:justify-between">
          <div>
            <p class="text-sm text-gray-700">
              显示第
              <span class="font-medium">1</span>
              至
              <span class="font-medium">10</span>
              条，共
              <span class="font-medium">97</span>
              条记录
            </p>
          </div>
          <div>
            <nav class="relative z-0 inline-flex rounded-md shadow-sm -space-x-px">
              <button class="relative inline-flex items-center px-2 py-2 rounded-l-md border border-gray-300 bg-white text-sm font-medium text-gray-500 hover:bg-gray-50">
                <i class="fas fa-chevron-left"></i>
              </button>
              <button class="relative inline-flex items-center px-4 py-2 border border-gray-300 bg-white text-sm font-medium text-gray-700 hover:bg-gray-50">
                1
              </button>
              <button class="relative inline-flex items-center px-4 py-2 border border-gray-300 bg-blue-50 text-sm font-medium text-blue-600">
                2
              </button>
              <button class="relative inline-flex items-center px-4 py-2 border border-gray-300 bg-white text-sm font-medium text-gray-700 hover:bg-gray-50">
                3
              </button>
              <button class="relative inline-flex items-center px-2 py-2 rounded-r-md border border-gray-300 bg-white text-sm font-medium text-gray-500 hover:bg-gray-50">
                <i class="fas fa-chevron-right"></i>
              </button>
            </nav>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
// 这里可以添加需要的组件逻辑
</script> 