<!-- 课程推荐页面 -->
<template>
  <div class="h-[calc(100vh-7rem)] p-6 bg-gray-50">
    <!-- 筛选栏 -->
    <div class="bg-white rounded-lg shadow-sm p-4 mb-6">
      <div class="flex items-center space-x-4">
        <div class="flex-1">
          <input
            type="text"
            placeholder="搜索课程..."
            class="w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
          >
        </div>
        <select class="px-4 py-2 border rounded-lg">
          <option>全部难度</option>
          <option>入门</option>
          <option>初级</option>
          <option>中级</option>
          <option>高级</option>
        </select>
        <select class="px-4 py-2 border rounded-lg">
          <option>全部分类</option>
          <option>编程语言</option>
          <option>数据结构</option>
          <option>算法</option>
          <option>Web开发</option>
          <option>数据库</option>
        </select>
        <button class="px-6 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700">
          筛选
        </button>
      </div>
    </div>

    <!-- 推荐课程列表 -->
    <div class="grid grid-cols-3 gap-6">
      <!-- 课程卡片 -->
      <div class="bg-white rounded-lg shadow-sm overflow-hidden">
        <div class="relative">
          <img src="https://picsum.photos/400/200" alt="课程封面" class="w-full h-48 object-cover">
          <div class="absolute top-2 right-2 bg-blue-600 text-white px-2 py-1 text-xs rounded">
            推荐指数：95%
          </div>
        </div>
        <div class="p-4">
          <h3 class="text-lg font-medium mb-2">Python高级编程实战</h3>
          <p class="text-sm text-gray-500 mb-4">深入学习Python高级特性，掌握编程技巧和最佳实践</p>
          <div class="flex items-center text-sm text-gray-500 mb-4">
            <div class="flex items-center">
              <i class="fas fa-user-graduate mr-1"></i>
              <span>2.5k人学习</span>
            </div>
            <div class="mx-4">|</div>
            <div class="flex items-center">
              <i class="fas fa-clock mr-1"></i>
              <span>20小时</span>
            </div>
            <div class="mx-4">|</div>
            <div class="flex items-center">
              <i class="fas fa-signal mr-1"></i>
              <span>中级</span>
            </div>
          </div>
          <div class="flex items-center justify-between">
            <div class="flex items-center">
              <div class="flex">
                <i class="fas fa-star text-yellow-400"></i>
                <i class="fas fa-star text-yellow-400"></i>
                <i class="fas fa-star text-yellow-400"></i>
                <i class="fas fa-star text-yellow-400"></i>
                <i class="fas fa-star-half-alt text-yellow-400"></i>
              </div>
              <span class="ml-2 text-sm text-gray-500">4.5 (125评价)</span>
            </div>
            <button class="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700">
              开始学习
            </button>
          </div>
        </div>
      </div>

      <div class="bg-white rounded-lg shadow-sm overflow-hidden">
        <div class="relative">
          <img src="https://picsum.photos/400/201" alt="课程封面" class="w-full h-48 object-cover">
          <div class="absolute top-2 right-2 bg-blue-600 text-white px-2 py-1 text-xs rounded">
            推荐指数：90%
          </div>
        </div>
        <div class="p-4">
          <h3 class="text-lg font-medium mb-2">数据结构与算法进阶</h3>
          <p class="text-sm text-gray-500 mb-4">系统学习常用数据结构和算法，提升编程能力</p>
          <div class="flex items-center text-sm text-gray-500 mb-4">
            <div class="flex items-center">
              <i class="fas fa-user-graduate mr-1"></i>
              <span>1.8k人学习</span>
            </div>
            <div class="mx-4">|</div>
            <div class="flex items-center">
              <i class="fas fa-clock mr-1"></i>
              <span>30小时</span>
            </div>
            <div class="mx-4">|</div>
            <div class="flex items-center">
              <i class="fas fa-signal mr-1"></i>
              <span>高级</span>
            </div>
          </div>
          <div class="flex items-center justify-between">
            <div class="flex items-center">
              <div class="flex">
                <i class="fas fa-star text-yellow-400"></i>
                <i class="fas fa-star text-yellow-400"></i>
                <i class="fas fa-star text-yellow-400"></i>
                <i class="fas fa-star text-yellow-400"></i>
                <i class="fas fa-star text-yellow-400"></i>
              </div>
              <span class="ml-2 text-sm text-gray-500">5.0 (89评价)</span>
            </div>
            <button class="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700">
              开始学习
            </button>
          </div>
        </div>
      </div>

      <div class="bg-white rounded-lg shadow-sm overflow-hidden">
        <div class="relative">
          <img src="https://picsum.photos/400/202" alt="课程封面" class="w-full h-48 object-cover">
          <div class="absolute top-2 right-2 bg-blue-600 text-white px-2 py-1 text-xs rounded">
            推荐指数：88%
          </div>
        </div>
        <div class="p-4">
          <h3 class="text-lg font-medium mb-2">Web全栈开发实践</h3>
          <p class="text-sm text-gray-500 mb-4">从前端到后端，全面掌握Web开发技能</p>
          <div class="flex items-center text-sm text-gray-500 mb-4">
            <div class="flex items-center">
              <i class="fas fa-user-graduate mr-1"></i>
              <span>3.2k人学习</span>
            </div>
            <div class="mx-4">|</div>
            <div class="flex items-center">
              <i class="fas fa-clock mr-1"></i>
              <span>40小时</span>
            </div>
            <div class="mx-4">|</div>
            <div class="flex items-center">
              <i class="fas fa-signal mr-1"></i>
              <span>中级</span>
            </div>
          </div>
          <div class="flex items-center justify-between">
            <div class="flex items-center">
              <div class="flex">
                <i class="fas fa-star text-yellow-400"></i>
                <i class="fas fa-star text-yellow-400"></i>
                <i class="fas fa-star text-yellow-400"></i>
                <i class="fas fa-star text-yellow-400"></i>
                <i class="fas fa-star text-gray-300"></i>
              </div>
              <span class="ml-2 text-sm text-gray-500">4.0 (156评价)</span>
            </div>
            <button class="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700">
              开始学习
            </button>
          </div>
        </div>
      </div>
    </div>

    <!-- 分页 -->
    <div class="flex justify-center mt-6">
      <nav class="flex items-center space-x-2">
        <button class="px-3 py-1 rounded-lg border hover:bg-gray-50">
          <i class="fas fa-chevron-left"></i>
        </button>
        <button class="px-3 py-1 rounded-lg border bg-blue-600 text-white">1</button>
        <button class="px-3 py-1 rounded-lg border hover:bg-gray-50">2</button>
        <button class="px-3 py-1 rounded-lg border hover:bg-gray-50">3</button>
        <button class="px-3 py-1 rounded-lg border hover:bg-gray-50">4</button>
        <button class="px-3 py-1 rounded-lg border hover:bg-gray-50">5</button>
        <button class="px-3 py-1 rounded-lg border hover:bg-gray-50">
          <i class="fas fa-chevron-right"></i>
        </button>
      </nav>
    </div>
  </div>
</template>

<script setup>
// 组件逻辑可以在这里添加
</script>

<style scoped>
/* 可以添加自定义样式 */
</style> 