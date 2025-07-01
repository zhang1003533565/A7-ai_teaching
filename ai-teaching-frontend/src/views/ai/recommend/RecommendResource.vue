<!-- 资源推荐页面 -->
<template>
  <div class="h-[calc(100vh-7rem)] p-6 bg-gray-50">
    <!-- 筛选栏 -->
    <div class="bg-white rounded-lg shadow-sm p-4 mb-6">
      <div class="flex items-center space-x-4">
        <div class="flex-1">
          <input
            type="text"
            placeholder="搜索资源..."
            class="w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
          >
        </div>
        <select class="px-4 py-2 border rounded-lg">
          <option>全部类型</option>
          <option>视频</option>
          <option>文档</option>
          <option>代码</option>
          <option>案例</option>
        </select>
        <select class="px-4 py-2 border rounded-lg">
          <option>全部主题</option>
          <option>前端开发</option>
          <option>后端开发</option>
          <option>数据库</option>
          <option>人工智能</option>
        </select>
        <button class="px-6 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700">
          筛选
        </button>
      </div>
    </div>

    <!-- 资源列表 -->
    <div class="grid grid-cols-2 gap-6">
      <!-- 资源卡片 -->
      <div class="bg-white rounded-lg shadow-sm p-4">
        <div class="flex">
          <div class="w-48 h-32 bg-gray-100 rounded-lg overflow-hidden flex-shrink-0">
            <img src="https://picsum.photos/200/150" alt="资源预览" class="w-full h-full object-cover">
          </div>
          <div class="ml-4 flex-1">
            <div class="flex items-center justify-between mb-2">
              <h3 class="text-lg font-medium">Vue.js 3.0实战教程</h3>
              <span class="text-sm text-blue-600">推荐指数：95%</span>
            </div>
            <p class="text-sm text-gray-500 mb-4">
              从基础到高级，全面讲解Vue 3的新特性和实战技巧
            </p>
            <div class="flex items-center text-sm text-gray-500 mb-4">
              <div class="flex items-center">
                <i class="fas fa-eye mr-1"></i>
                <span>1.2k浏览</span>
              </div>
              <div class="mx-4">|</div>
              <div class="flex items-center">
                <i class="fas fa-download mr-1"></i>
                <span>458下载</span>
              </div>
              <div class="mx-4">|</div>
              <div class="flex items-center">
                <i class="fas fa-video mr-1"></i>
                <span>视频教程</span>
              </div>
            </div>
            <div class="flex items-center justify-between">
              <div class="flex items-center">
                <img src="https://picsum.photos/32/32" alt="作者头像" class="w-6 h-6 rounded-full">
                <span class="ml-2 text-sm text-gray-600">张老师</span>
              </div>
              <button class="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700">
                查看详情
              </button>
            </div>
          </div>
        </div>
      </div>

      <div class="bg-white rounded-lg shadow-sm p-4">
        <div class="flex">
          <div class="w-48 h-32 bg-gray-100 rounded-lg overflow-hidden flex-shrink-0">
            <img src="https://picsum.photos/200/151" alt="资源预览" class="w-full h-full object-cover">
          </div>
          <div class="ml-4 flex-1">
            <div class="flex items-center justify-between mb-2">
              <h3 class="text-lg font-medium">Spring Boot最佳实践指南</h3>
              <span class="text-sm text-blue-600">推荐指数：92%</span>
            </div>
            <p class="text-sm text-gray-500 mb-4">
              详细介绍Spring Boot框架的核心概念和实践应用
            </p>
            <div class="flex items-center text-sm text-gray-500 mb-4">
              <div class="flex items-center">
                <i class="fas fa-eye mr-1"></i>
                <span>2.3k浏览</span>
              </div>
              <div class="mx-4">|</div>
              <div class="flex items-center">
                <i class="fas fa-download mr-1"></i>
                <span>892下载</span>
              </div>
              <div class="mx-4">|</div>
              <div class="flex items-center">
                <i class="fas fa-file-pdf mr-1"></i>
                <span>PDF文档</span>
              </div>
            </div>
            <div class="flex items-center justify-between">
              <div class="flex items-center">
                <img src="https://picsum.photos/32/33" alt="作者头像" class="w-6 h-6 rounded-full">
                <span class="ml-2 text-sm text-gray-600">李老师</span>
              </div>
              <button class="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700">
                查看详情
              </button>
            </div>
          </div>
        </div>
      </div>

      <div class="bg-white rounded-lg shadow-sm p-4">
        <div class="flex">
          <div class="w-48 h-32 bg-gray-100 rounded-lg overflow-hidden flex-shrink-0">
            <img src="https://picsum.photos/200/152" alt="资源预览" class="w-full h-full object-cover">
          </div>
          <div class="ml-4 flex-1">
            <div class="flex items-center justify-between mb-2">
              <h3 class="text-lg font-medium">React Native实战项目</h3>
              <span class="text-sm text-blue-600">推荐指数：88%</span>
            </div>
            <p class="text-sm text-gray-500 mb-4">
              从零开始构建一个完整的移动应用，包含源码和文档
            </p>
            <div class="flex items-center text-sm text-gray-500 mb-4">
              <div class="flex items-center">
                <i class="fas fa-eye mr-1"></i>
                <span>1.8k浏览</span>
              </div>
              <div class="mx-4">|</div>
              <div class="flex items-center">
                <i class="fas fa-download mr-1"></i>
                <span>635下载</span>
              </div>
              <div class="mx-4">|</div>
              <div class="flex items-center">
                <i class="fas fa-code mr-1"></i>
                <span>项目源码</span>
              </div>
            </div>
            <div class="flex items-center justify-between">
              <div class="flex items-center">
                <img src="https://picsum.photos/32/34" alt="作者头像" class="w-6 h-6 rounded-full">
                <span class="ml-2 text-sm text-gray-600">王老师</span>
              </div>
              <button class="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700">
                查看详情
              </button>
            </div>
          </div>
        </div>
      </div>

      <div class="bg-white rounded-lg shadow-sm p-4">
        <div class="flex">
          <div class="w-48 h-32 bg-gray-100 rounded-lg overflow-hidden flex-shrink-0">
            <img src="https://picsum.photos/200/153" alt="资源预览" class="w-full h-full object-cover">
          </div>
          <div class="ml-4 flex-1">
            <div class="flex items-center justify-between mb-2">
              <h3 class="text-lg font-medium">机器学习入门案例</h3>
              <span class="text-sm text-blue-600">推荐指数：85%</span>
            </div>
            <p class="text-sm text-gray-500 mb-4">
              精选机器学习入门案例，帮助理解核心概念
            </p>
            <div class="flex items-center text-sm text-gray-500 mb-4">
              <div class="flex items-center">
                <i class="fas fa-eye mr-1"></i>
                <span>2.1k浏览</span>
              </div>
              <div class="mx-4">|</div>
              <div class="flex items-center">
                <i class="fas fa-download mr-1"></i>
                <span>723下载</span>
              </div>
              <div class="mx-4">|</div>
              <div class="flex items-center">
                <i class="fas fa-file-code mr-1"></i>
                <span>案例代码</span>
              </div>
            </div>
            <div class="flex items-center justify-between">
              <div class="flex items-center">
                <img src="https://picsum.photos/32/35" alt="作者头像" class="w-6 h-6 rounded-full">
                <span class="ml-2 text-sm text-gray-600">赵老师</span>
              </div>
              <button class="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700">
                查看详情
              </button>
            </div>
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