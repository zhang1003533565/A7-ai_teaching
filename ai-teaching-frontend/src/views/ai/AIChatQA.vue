<!-- 问答系统页面 -->
<template>
  <div class="h-[calc(100vh-7rem)] flex flex-col bg-gray-50">
    <!-- 顶部搜索栏 -->
    <div class="bg-white p-4 border-b border-gray-200">
      <div class="max-w-4xl mx-auto">
        <div class="relative">
          <input
            type="text"
            class="w-full pl-10 pr-4 py-3 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
            placeholder="输入您的问题，例如：'如何使用Python处理文件？'"
          />
          <i class="fas fa-search absolute left-3 top-1/2 -translate-y-1/2 text-gray-400"></i>
        </div>
        <div class="mt-2 flex items-center space-x-2 text-sm text-gray-500">
          <span>热门问题：</span>
          <a href="#" class="hover:text-blue-500">Python基础语法</a>
          <span>·</span>
          <a href="#" class="hover:text-blue-500">函数定义</a>
          <span>·</span>
          <a href="#" class="hover:text-blue-500">面向对象编程</a>
        </div>
      </div>
    </div>

    <!-- 主要内容区 -->
    <div class="flex-1 overflow-y-auto">
      <div class="max-w-4xl mx-auto py-6 px-4">
        <!-- 问答卡片 -->
        <div class="bg-white rounded-lg shadow-sm p-6 mb-6">
          <div class="flex items-start space-x-4">
            <div class="w-8 h-8 rounded-full bg-blue-100 flex items-center justify-center flex-shrink-0">
              <i class="fas fa-question text-blue-500"></i>
            </div>
            <div class="flex-1">
              <h3 class="text-lg font-medium text-gray-900">如何在Python中创建和使用类？</h3>
              <div class="mt-2 text-gray-700">
                <p>在Python中创建类使用class关键字，基本语法如下：</p>
                <pre class="mt-2 bg-gray-50 p-4 rounded-md"><code>class MyClass:
    def __init__(self):
        self.data = []
    
    def method(self):
        return "Hello"</code></pre>
                <p class="mt-2">类的主要特点：</p>
                <ul class="mt-2 list-disc list-inside space-y-1">
                  <li>使用class关键字定义</li>
                  <li>可以包含属性和方法</li>
                  <li>__init__方法是构造函数</li>
                  <li>self参数代表实例本身</li>
                </ul>
              </div>
              <div class="mt-4 flex items-center justify-between">
                <div class="flex items-center space-x-4">
                  <button class="flex items-center text-gray-500 hover:text-blue-500">
                    <i class="fas fa-thumbs-up mr-1"></i>
                    <span>有帮助 (24)</span>
                  </button>
                  <button class="flex items-center text-gray-500 hover:text-blue-500">
                    <i class="fas fa-comment mr-1"></i>
                    <span>评论 (5)</span>
                  </button>
                  <button class="flex items-center text-gray-500 hover:text-blue-500">
                    <i class="fas fa-share mr-1"></i>
                    <span>分享</span>
                  </button>
                </div>
                <div class="text-sm text-gray-500">
                  更新于：2024-03-20
                </div>
              </div>
            </div>
          </div>
        </div>

        <!-- 相关问题 -->
        <div class="bg-white rounded-lg shadow-sm p-6">
          <h3 class="text-lg font-medium text-gray-900 mb-4">相关问题</h3>
          <div class="space-y-4">
            <div v-for="i in 3" :key="i" class="flex items-start space-x-4 p-4 hover:bg-gray-50 rounded-lg cursor-pointer">
              <div class="w-6 h-6 rounded-full bg-gray-100 flex items-center justify-center flex-shrink-0">
                <i class="fas fa-link text-gray-500 text-sm"></i>
              </div>
              <div>
                <h4 class="text-base font-medium text-gray-800">Python类的继承和多态是什么？</h4>
                <p class="mt-1 text-sm text-gray-500">
                  解释了Python面向对象编程中继承和多态的概念，包含实际示例...
                </p>
                <div class="mt-2 flex items-center space-x-4 text-sm text-gray-500">
                  <span>浏览 1.2k</span>
                  <span>回答 8</span>
                  <span>收藏 45</span>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
// 组件逻辑可以在这里添加
</script>

<style scoped>
pre {
  font-family: ui-monospace, SFMono-Regular, Menlo, Monaco, Consolas, "Liberation Mono", "Courier New", monospace;
  font-size: 0.875rem;
  line-height: 1.5;
}
</style> 