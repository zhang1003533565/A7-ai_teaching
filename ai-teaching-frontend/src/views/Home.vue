<!-- The exported code uses Tailwind CSS. Install Tailwind CSS in your dev environment to ensure all styles work. -->
<template>
    <div class="min-h-screen bg-gray-50">
      <!-- 顶部导航栏 -->
      <header
        class="fixed top-0 left-0 right-0 bg-white bg-opacity-95 shadow-sm z-50"
      >
        <div
          class="container mx-auto px-4 flex items-center justify-between h-16"
        >
          <!-- Logo 和系统名称 -->
          <div class="flex items-center">
            <div
              class="w-10 h-10 rounded-lg bg-gradient-to-r from-blue-500 to-indigo-600 flex items-center justify-center text-white font-bold text-xl"
            >
              AI
            </div>
            <h1 class="ml-3 text-xl font-bold text-gray-800">智能教学系统</h1>
          </div>
  
          <!-- 搜索框 -->
          <div class="flex-1 max-w-xl mx-8">
            <div class="relative">
              <input
                type="text"
                placeholder="搜索课程、资源、问题..."
                class="w-full py-2 pl-10 pr-4 text-sm border-none rounded-lg bg-gray-100 focus:outline-none focus:ring-2 focus:ring-blue-500"
              />
              <i
                class="fas fa-search absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400"
              ></i>
            </div>
          </div>
  
          <!-- 右侧功能区 -->
          <div class="flex items-center space-x-4">
            <!-- 消息通知 -->
            <div class="relative cursor-pointer">
              <i class="fas fa-bell text-gray-600"></i>
              <span
                class="absolute -top-1 -right-1 w-4 h-4 bg-red-500 rounded-full text-white text-xs flex items-center justify-center"
                >3</span
              >
            </div>
  
            <!-- 用户信息 -->
            <div class="relative" ref="userMenuRef">
              <div 
                class="flex items-center cursor-pointer"
                @click="toggleUserMenu"
              >
                <div
                  class="w-8 h-8 rounded-full bg-blue-500 flex items-center justify-center text-white"
                >
                  <i class="fas fa-user"></i>
                </div>
                <span
                  class="ml-2 text-sm font-medium text-gray-700 whitespace-nowrap"
                  >{{ userInfo.realName || userInfo.username || '用户' }}</span
                >
                <i 
                  class="fas fa-chevron-down ml-1 text-xs text-gray-500 transition-transform"
                  :class="{ 'transform rotate-180': showUserMenu }"
                ></i>
              </div>
              
              <!-- 下拉菜单 -->
              <div 
                v-show="showUserMenu"
                class="absolute right-0 mt-2 w-48 bg-white rounded-md shadow-lg py-1 z-50 border border-gray-200"
              >
                <div class="px-4 py-2 text-sm text-gray-700 border-b border-gray-100">
                  <div class="font-medium">{{ userInfo.realName || userInfo.username || '用户' }}</div>
                  <div class="text-xs text-gray-500">{{ userInfo.role || '教师' }}</div>
                </div>
                <a href="#" class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100">
                  <i class="fas fa-user-circle mr-2"></i>个人资料
                </a>
                <a href="#" class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100">
                  <i class="fas fa-cog mr-2"></i>账户设置
                </a>
                <a href="#" class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100">
                  <i class="fas fa-bell mr-2"></i>通知设置
                </a>
                <div class="border-t border-gray-100 mt-1"></div>
                <div 
                  class="block px-4 py-2 text-sm text-red-600 hover:bg-red-50 cursor-pointer"
                  @click="handleLogout"
                >
                  <i class="fas fa-sign-out-alt mr-2"></i>退出登录
                </div>
              </div>
            </div>
          </div>
        </div>
      </header>
  
      <div class="flex pt-16">
        <!-- 左侧菜单栏 -->
        <aside
          class="w-64 bg-white shadow-sm h-[calc(100vh-4rem)] fixed left-0 overflow-y-auto"
        >
          <nav class="py-4">
            <div class="px-4 py-2">
              <div
                class="flex items-center text-gray-600 hover:text-blue-600 cursor-pointer"
              >
                <i class="fas fa-home w-5 text-center"></i>
                <span class="ml-3 text-sm font-medium">首页</span>
              </div>
            </div>
  
            <div class="mt-2">
              <div
                class="px-4 py-2 text-xs font-semibold text-gray-500 uppercase tracking-wider"
              >
                教学管理
              </div>
              <div class="px-4 py-2">
                <div class="flex items-center text-blue-600 cursor-pointer">
                  <i class="fas fa-book w-5 text-center"></i>
                  <span class="ml-3 text-sm font-medium">我的课程</span>
                </div>
              </div>
              <div class="px-4 py-2">
                <div
                  class="flex items-center text-gray-600 hover:text-blue-600 cursor-pointer"
                >
                  <i class="fas fa-tasks w-5 text-center"></i>
                  <span class="ml-3 text-sm font-medium">作业管理</span>
                </div>
              </div>
              <div class="px-4 py-2">
                <div
                  class="flex items-center text-gray-600 hover:text-blue-600 cursor-pointer"
                >
                  <i class="fas fa-chart-bar w-5 text-center"></i>
                  <span class="ml-3 text-sm font-medium">学情分析</span>
                </div>
              </div>
            </div>
  
            <div class="mt-2">
              <div
                class="px-4 py-2 text-xs font-semibold text-gray-500 uppercase tracking-wider"
              >
                资源中心
              </div>
              <div class="px-4 py-2">
                <div
                  class="flex items-center text-gray-600 hover:text-blue-600 cursor-pointer"
                >
                  <i class="fas fa-file-alt w-5 text-center"></i>
                  <span class="ml-3 text-sm font-medium">教学资源</span>
                </div>
              </div>
              <div class="px-4 py-2">
                <div
                  class="flex items-center text-gray-600 hover:text-blue-600 cursor-pointer"
                >
                  <i class="fas fa-question-circle w-5 text-center"></i>
                  <span class="ml-3 text-sm font-medium">题库管理</span>
                </div>
              </div>
            </div>
  
            <div class="mt-2">
              <div
                class="px-4 py-2 text-xs font-semibold text-gray-500 uppercase tracking-wider"
              >
                系统设置
              </div>
              <div class="px-4 py-2">
                <div
                  class="flex items-center text-gray-600 hover:text-blue-600 cursor-pointer"
                >
                  <i class="fas fa-user-cog w-5 text-center"></i>
                  <span class="ml-3 text-sm font-medium">个人设置</span>
                </div>
              </div>
              <div class="px-4 py-2">
                <div
                  class="flex items-center text-gray-600 hover:text-blue-600 cursor-pointer"
                >
                  <i class="fas fa-cog w-5 text-center"></i>
                  <span class="ml-3 text-sm font-medium">系统设置</span>
                </div>
              </div>
            </div>
          </nav>
        </aside>
  
        <!-- 主内容区 -->
        <main class="ml-64 flex-1 p-6">
          <!-- 面包屑导航 -->
          <div class="mb-6 flex items-center text-sm text-gray-500">
            <span class="cursor-pointer hover:text-blue-600">首页</span>
            <i class="fas fa-chevron-right mx-2 text-xs"></i>
            <span class="text-gray-700">教师工作台</span>
          </div>
  
          <!-- 数据概览卡片 -->
          <div class="grid grid-cols-4 gap-6 mb-6">
            <div
              class="bg-gradient-to-r from-blue-500 to-blue-600 rounded-lg shadow-sm p-4 text-white"
            >
              <div class="flex justify-between items-start">
                <div>
                  <p class="text-sm opacity-90">课程总数</p>
                  <h3 class="text-3xl font-bold mt-2">12</h3>
                  <p class="text-xs mt-2 opacity-80">
                    较上月 <i class="fas fa-arrow-up"></i> 2
                  </p>
                </div>
                <div
                  class="w-10 h-10 rounded-full bg-white bg-opacity-20 flex items-center justify-center"
                >
                  <i class="fas fa-book-open text-white"></i>
                </div>
              </div>
            </div>
  
            <div
              class="bg-gradient-to-r from-purple-500 to-purple-600 rounded-lg shadow-sm p-4 text-white"
            >
              <div class="flex justify-between items-start">
                <div>
                  <p class="text-sm opacity-90">学生总数</p>
                  <h3 class="text-3xl font-bold mt-2">248</h3>
                  <p class="text-xs mt-2 opacity-80">
                    较上月 <i class="fas fa-arrow-up"></i> 12
                  </p>
                </div>
                <div
                  class="w-10 h-10 rounded-full bg-white bg-opacity-20 flex items-center justify-center"
                >
                  <i class="fas fa-users text-white"></i>
                </div>
              </div>
            </div>
  
            <div
              class="bg-gradient-to-r from-orange-500 to-orange-600 rounded-lg shadow-sm p-4 text-white"
            >
              <div class="flex justify-between items-start">
                <div>
                  <p class="text-sm opacity-90">待批改作业</p>
                  <h3 class="text-3xl font-bold mt-2">18</h3>
                  <p class="text-xs mt-2 opacity-80">
                    今日新增 <i class="fas fa-arrow-up"></i> 5
                  </p>
                </div>
                <div
                  class="w-10 h-10 rounded-full bg-white bg-opacity-20 flex items-center justify-center"
                >
                  <i class="fas fa-clipboard-check text-white"></i>
                </div>
              </div>
            </div>
  
            <div
              class="bg-gradient-to-r from-green-500 to-green-600 rounded-lg shadow-sm p-4 text-white"
            >
              <div class="flex justify-between items-start">
                <div>
                  <p class="text-sm opacity-90">活跃度</p>
                  <h3 class="text-3xl font-bold mt-2">92%</h3>
                  <p class="text-xs mt-2 opacity-80">
                    较上周 <i class="fas fa-arrow-up"></i> 5%
                  </p>
                </div>
                <div
                  class="w-10 h-10 rounded-full bg-white bg-opacity-20 flex items-center justify-center"
                >
                  <i class="fas fa-chart-line text-white"></i>
                </div>
              </div>
            </div>
          </div>
  
          <!-- 中间区域 -->
          <div class="grid grid-cols-2 gap-6 mb-6">
            <!-- 教学日历 -->
            <div class="bg-white rounded-lg shadow-sm p-4">
              <div class="flex justify-between items-center mb-4">
                <h2 class="text-lg font-semibold text-gray-800">教学日历</h2>
                <div class="flex space-x-2">
                  <button
                    class="text-gray-500 hover:text-blue-600 cursor-pointer !rounded-button whitespace-nowrap"
                  >
                    <i class="fas fa-chevron-left"></i>
                  </button>
                  <span class="text-sm font-medium">2025年6月</span>
                  <button
                    class="text-gray-500 hover:text-blue-600 cursor-pointer !rounded-button whitespace-nowrap"
                  >
                    <i class="fas fa-chevron-right"></i>
                  </button>
                </div>
              </div>
              <div class="calendar">
                <div
                  class="grid grid-cols-7 gap-1 text-center text-sm text-gray-500 mb-2"
                >
                  <div>周日</div>
                  <div>周一</div>
                  <div>周二</div>
                  <div>周三</div>
                  <div>周四</div>
                  <div>周五</div>
                  <div>周六</div>
                </div>
                <div class="grid grid-cols-7 gap-1 text-center">
                  <div
                    v-for="day in calendarDays"
                    :key="day.date"
                    :class="[
                         'p-2 rounded-md text-sm relative',
                         day.isCurrentMonth ? 'text-gray-700' : 'text-gray-400',
                         day.isToday ? 'bg-blue-50 font-bold text-blue-600' : '',
                         day.hasEvent ? 'font-medium' : ''
                       ]"
                  >
                    <span>{{ day.day }}</span>
                    <div
                      v-if="day.hasEvent"
                      class="absolute bottom-1 left-1/2 transform -translate-x-1/2 w-1 h-1 rounded-full bg-blue-500"
                    ></div>
                  </div>
                </div>
              </div>
              <div class="mt-4 space-y-2">
                <div class="flex items-center p-2 bg-blue-50 rounded-md">
                  <div class="w-2 h-2 rounded-full bg-blue-500 mr-2"></div>
                  <span class="text-sm text-gray-700"
                    >高级编程 - 10:00-11:30</span
                  >
                </div>
                <div class="flex items-center p-2 bg-purple-50 rounded-md">
                  <div class="w-2 h-2 rounded-full bg-purple-500 mr-2"></div>
                  <span class="text-sm text-gray-700"
                    >数据结构 - 14:00-15:30</span
                  >
                </div>
                <div class="flex items-center p-2 bg-green-50 rounded-md">
                  <div class="w-2 h-2 rounded-full bg-green-500 mr-2"></div>
                  <span class="text-sm text-gray-700"
                    >期末考试安排会议 - 16:00</span
                  >
                </div>
              </div>
            </div>
  
            <!-- 待办事项 -->
            <div class="bg-white rounded-lg shadow-sm p-4">
              <div class="flex justify-between items-center mb-4">
                <h2 class="text-lg font-semibold text-gray-800">待办事项</h2>
                <div class="flex space-x-2">
                  <button
                    class="px-3 py-1 text-xs font-medium rounded-full bg-blue-100 text-blue-600 cursor-pointer !rounded-button whitespace-nowrap"
                  >
                    全部
                  </button>
                  <button
                    class="px-3 py-1 text-xs font-medium rounded-full bg-gray-100 text-gray-600 cursor-pointer !rounded-button whitespace-nowrap"
                  >
                    作业
                  </button>
                  <button
                    class="px-3 py-1 text-xs font-medium rounded-full bg-gray-100 text-gray-600 cursor-pointer !rounded-button whitespace-nowrap"
                  >
                    问题
                  </button>
                </div>
              </div>
              <div class="space-y-3">
                <div
                  v-for="(task, index) in todoTasks"
                  :key="index"
                  class="flex items-center p-3 border border-gray-100 rounded-lg hover:bg-gray-50"
                >
                  <div :class="`w-2 h-2 rounded-full ${task.priority}`"></div>
                  <div class="ml-3 flex-1">
                    <div class="flex justify-between">
                      <h4 class="text-sm font-medium text-gray-800">
                        {{ task.title }}
                      </h4>
                      <span class="text-xs text-gray-500"
                        >{{ task.dueDate }}</span
                      >
                    </div>
                    <p class="text-xs text-gray-500 mt-1">
                      {{ task.description }}
                    </p>
                  </div>
                  <button
                    class="ml-2 text-gray-400 hover:text-blue-500 cursor-pointer !rounded-button whitespace-nowrap"
                  >
                    <i class="fas fa-chevron-right"></i>
                  </button>
                </div>
              </div>
              <button
                class="mt-4 w-full py-2 text-sm text-blue-600 bg-blue-50 rounded-md hover:bg-blue-100 transition cursor-pointer !rounded-button whitespace-nowrap"
              >
                查看全部待办
              </button>
            </div>
          </div>
  
          <!-- 下方区域 -->
          <div class="grid grid-cols-3 gap-6">
            <!-- 教学分析 -->
            <div class="bg-white rounded-lg shadow-sm p-4">
              <div class="flex justify-between items-center mb-4">
                <h2 class="text-lg font-semibold text-gray-800">教学分析</h2>
                <div class="text-sm text-gray-500">
                  <span class="cursor-pointer hover:text-blue-600">本周</span>
                  <i class="fas fa-chevron-down ml-1 text-xs"></i>
                </div>
              </div>
              <div class="h-64" ref="chartRef"></div>
              <div class="grid grid-cols-3 gap-2 mt-4">
                <div class="text-center">
                  <p class="text-xs text-gray-500">课程进度</p>
                  <p class="font-semibold text-gray-800 mt-1">68%</p>
                </div>
                <div class="text-center">
                  <p class="text-xs text-gray-500">平均成绩</p>
                  <p class="font-semibold text-gray-800 mt-1">85分</p>
                </div>
                <div class="text-center">
                  <p class="text-xs text-gray-500">作业提交率</p>
                  <p class="font-semibold text-gray-800 mt-1">92%</p>
                </div>
              </div>
            </div>
  
            <!-- 最近互动 -->
            <div class="bg-white rounded-lg shadow-sm p-4">
              <div class="flex justify-between items-center mb-4">
                <h2 class="text-lg font-semibold text-gray-800">最近互动</h2>
                <button
                  class="text-sm text-blue-600 cursor-pointer !rounded-button whitespace-nowrap"
                >
                  查看全部
                </button>
              </div>
              <div class="space-y-4">
                <div
                  v-for="(interaction, index) in recentInteractions"
                  :key="index"
                  class="flex"
                >
                  <div
                    class="w-8 h-8 rounded-full bg-gray-200 flex-shrink-0 overflow-hidden"
                  >
                    <img
                      :src="interaction.avatar"
                      alt="用户头像"
                      class="w-full h-full object-cover"
                    />
                  </div>
                  <div class="ml-3 flex-1">
                    <div class="flex justify-between">
                      <h4 class="text-sm font-medium text-gray-800">
                        {{ interaction.name }}
                      </h4>
                      <span class="text-xs text-gray-500"
                        >{{ interaction.time }}</span
                      >
                    </div>
                    <p class="text-xs text-gray-600 mt-1">
                      {{ interaction.content }}
                    </p>
                    <div
                      v-if="interaction.type === 'question'"
                      class="mt-2 p-2 bg-gray-50 rounded text-xs text-gray-600"
                    >
                      {{ interaction.question }}
                    </div>
                  </div>
                </div>
              </div>
            </div>
  
            <!-- 快捷功能 -->
            <div class="bg-white rounded-lg shadow-sm p-4">
              <div class="mb-4">
                <h2 class="text-lg font-semibold text-gray-800">快捷功能</h2>
              </div>
              <div class="grid grid-cols-2 gap-3">
                <div
                  v-for="(tool, index) in quickTools"
                  :key="index"
                  class="p-3 border border-gray-100 rounded-lg hover:border-blue-200 hover:bg-blue-50 transition cursor-pointer flex flex-col items-center"
                >
                  <div
                    class="w-10 h-10 rounded-full flex items-center justify-center"
                    :class="tool.bgColor"
                  >
                    <i :class="`fas ${tool.icon} text-white`"></i>
                  </div>
                  <p class="text-sm font-medium text-gray-700 mt-2">
                    {{ tool.name }}
                  </p>
                  <p class="text-xs text-gray-500 mt-1">{{ tool.description }}</p>
                </div>
              </div>
              <div class="mt-6">
                <h3 class="text-sm font-medium text-gray-700 mb-3">最近访问</h3>
                <div class="space-y-2">
                  <div
                    v-for="(course, index) in recentCourses"
                    :key="index"
                    class="flex items-center p-2 hover:bg-gray-50 rounded cursor-pointer"
                  >
                    <div
                      class="w-8 h-8 rounded bg-blue-100 flex items-center justify-center"
                    >
                      <i class="fas fa-book text-blue-500"></i>
                    </div>
                    <div class="ml-3">
                      <p class="text-sm font-medium text-gray-800">
                        {{ course.name }}
                      </p>
                      <p class="text-xs text-gray-500">{{ course.lastVisit }}</p>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </main>
      </div>
    </div>
  </template>
  
  <script lang="ts" setup>
import { ref, onMounted, reactive, onUnmounted } from "vue";
import { useRouter } from 'vue-router';
import * as echarts from "echarts";

const router = useRouter();

// 用户相关状态
const showUserMenu = ref(false);
const userMenuRef = ref(null);
const userInfo = reactive(JSON.parse(localStorage.getItem('userInfo') || '{}'));
  
  // 日历数据
  const calendarDays = reactive([
    {
      day: 26,
      date: "2025-05-26",
      isCurrentMonth: false,
      isToday: false,
      hasEvent: false,
    },
    {
      day: 27,
      date: "2025-05-27",
      isCurrentMonth: false,
      isToday: false,
      hasEvent: false,
    },
    {
      day: 28,
      date: "2025-05-28",
      isCurrentMonth: false,
      isToday: false,
      hasEvent: false,
    },
    {
      day: 29,
      date: "2025-05-29",
      isCurrentMonth: false,
      isToday: false,
      hasEvent: false,
    },
    {
      day: 30,
      date: "2025-05-30",
      isCurrentMonth: false,
      isToday: false,
      hasEvent: false,
    },
    {
      day: 31,
      date: "2025-05-31",
      isCurrentMonth: false,
      isToday: false,
      hasEvent: false,
    },
    {
      day: 1,
      date: "2025-06-01",
      isCurrentMonth: true,
      isToday: false,
      hasEvent: false,
    },
    {
      day: 2,
      date: "2025-06-02",
      isCurrentMonth: true,
      isToday: false,
      hasEvent: false,
    },
    {
      day: 3,
      date: "2025-06-03",
      isCurrentMonth: true,
      isToday: false,
      hasEvent: false,
    },
    {
      day: 4,
      date: "2025-06-04",
      isCurrentMonth: true,
      isToday: false,
      hasEvent: false,
    },
    {
      day: 5,
      date: "2025-06-05",
      isCurrentMonth: true,
      isToday: false,
      hasEvent: false,
    },
    {
      day: 6,
      date: "2025-06-06",
      isCurrentMonth: true,
      isToday: false,
      hasEvent: false,
    },
    {
      day: 7,
      date: "2025-06-07",
      isCurrentMonth: true,
      isToday: false,
      hasEvent: false,
    },
    {
      day: 8,
      date: "2025-06-08",
      isCurrentMonth: true,
      isToday: false,
      hasEvent: false,
    },
    {
      day: 9,
      date: "2025-06-09",
      isCurrentMonth: true,
      isToday: false,
      hasEvent: false,
    },
    {
      day: 10,
      date: "2025-06-10",
      isCurrentMonth: true,
      isToday: false,
      hasEvent: false,
    },
    {
      day: 11,
      date: "2025-06-11",
      isCurrentMonth: true,
      isToday: false,
      hasEvent: false,
    },
    {
      day: 12,
      date: "2025-06-12",
      isCurrentMonth: true,
      isToday: false,
      hasEvent: false,
    },
    {
      day: 13,
      date: "2025-06-13",
      isCurrentMonth: true,
      isToday: false,
      hasEvent: false,
    },
    {
      day: 14,
      date: "2025-06-14",
      isCurrentMonth: true,
      isToday: false,
      hasEvent: false,
    },
    {
      day: 15,
      date: "2025-06-15",
      isCurrentMonth: true,
      isToday: false,
      hasEvent: false,
    },
    {
      day: 16,
      date: "2025-06-16",
      isCurrentMonth: true,
      isToday: false,
      hasEvent: false,
    },
    {
      day: 17,
      date: "2025-06-17",
      isCurrentMonth: true,
      isToday: false,
      hasEvent: false,
    },
    {
      day: 18,
      date: "2025-06-18",
      isCurrentMonth: true,
      isToday: false,
      hasEvent: false,
    },
    {
      day: 19,
      date: "2025-06-19",
      isCurrentMonth: true,
      isToday: false,
      hasEvent: false,
    },
    {
      day: 20,
      date: "2025-06-20",
      isCurrentMonth: true,
      isToday: false,
      hasEvent: false,
    },
    {
      day: 21,
      date: "2025-06-21",
      isCurrentMonth: true,
      isToday: false,
      hasEvent: false,
    },
    {
      day: 22,
      date: "2025-06-22",
      isCurrentMonth: true,
      isToday: false,
      hasEvent: false,
    },
    {
      day: 23,
      date: "2025-06-23",
      isCurrentMonth: true,
      isToday: false,
      hasEvent: false,
    },
    {
      day: 24,
      date: "2025-06-24",
      isCurrentMonth: true,
      isToday: false,
      hasEvent: false,
    },
    {
      day: 25,
      date: "2025-06-25",
      isCurrentMonth: true,
      isToday: false,
      hasEvent: false,
    },
    {
      day: 26,
      date: "2025-06-26",
      isCurrentMonth: true,
      isToday: false,
      hasEvent: false,
    },
    {
      day: 27,
      date: "2025-06-27",
      isCurrentMonth: true,
      isToday: false,
      hasEvent: false,
    },
    {
      day: 28,
      date: "2025-06-28",
      isCurrentMonth: true,
      isToday: false,
      hasEvent: false,
    },
    {
      day: 29,
      date: "2025-06-29",
      isCurrentMonth: true,
      isToday: false,
      hasEvent: false,
    },
    {
      day: 30,
      date: "2025-06-30",
      isCurrentMonth: true,
      isToday: true,
      hasEvent: true,
    },
    {
      day: 1,
      date: "2025-07-01",
      isCurrentMonth: false,
      isToday: false,
      hasEvent: false,
    },
    {
      day: 2,
      date: "2025-07-02",
      isCurrentMonth: false,
      isToday: false,
      hasEvent: false,
    },
    {
      day: 3,
      date: "2025-07-03",
      isCurrentMonth: false,
      isToday: false,
      hasEvent: false,
    },
    {
      day: 4,
      date: "2025-07-04",
      isCurrentMonth: false,
      isToday: false,
      hasEvent: false,
    },
    {
      day: 5,
      date: "2025-07-05",
      isCurrentMonth: false,
      isToday: false,
      hasEvent: false,
    },
    {
      day: 6,
      date: "2025-07-06",
      isCurrentMonth: false,
      isToday: false,
      hasEvent: false,
    },
  ]);
  
  // 待办事项数据
  const todoTasks = reactive([
    {
      title: "高级编程作业批改",
      description: "需要批改 32 份学生作业",
      dueDate: "今天截止",
      priority: "bg-red-500",
    },
    {
      title: "数据结构期中测验出题",
      description: "准备 30 道选择题和 5 道编程题",
      dueDate: "明天截止",
      priority: "bg-orange-500",
    },
    {
      title: "回复学生问题",
      description: "张明关于算法复杂度的提问",
      dueDate: "2 天后截止",
      priority: "bg-blue-500",
    },
    {
      title: "准备人工智能课程材料",
      description: "更新深度学习章节的教学内容",
      dueDate: "3 天后截止",
      priority: "bg-green-500",
    },
  ]);
  
  // 最近互动数据
  const recentInteractions = reactive([
    {
      name: "李小明",
      avatar:
        "https://readdy.ai/api/search-image?query=young%20asian%20male%20student%20with%20glasses%2C%20professional%20headshot%20portrait%2C%20neutral%20background%2C%20simple%20lighting%2C%20high%20quality%2C%20realistic&width=100&height=100&seq=1&orientation=squarish",
      time: "10 分钟前",
      content: "提交了《数据结构》作业",
      type: "submission",
    },
    {
      name: "张华",
      avatar:
        "https://readdy.ai/api/search-image?query=young%20asian%20female%20student%20with%20short%20hair%2C%20professional%20headshot%20portrait%2C%20neutral%20background%2C%20simple%20lighting%2C%20high%20quality%2C%20realistic&width=100&height=100&seq=2&orientation=squarish",
      time: "30 分钟前",
      content: "在《高级编程》课程中提问：",
      question: "老师，请问递归函数的性能优化有哪些方法？",
      type: "question",
    },
    {
      name: "王芳",
      avatar:
        "https://readdy.ai/api/search-image?query=young%20asian%20female%20student%20with%20long%20hair%2C%20professional%20headshot%20portrait%2C%20neutral%20background%2C%20simple%20lighting%2C%20high%20quality%2C%20realistic&width=100&height=100&seq=3&orientation=squarish",
      time: "2 小时前",
      content: "参与了《算法设计》讨论",
      type: "discussion",
    },
  ]);
  
  // 快捷工具数据
  const quickTools = reactive([
    {
      name: "智能备课",
      description: "AI 辅助教案生成",
      icon: "fa-lightbulb",
      bgColor: "bg-blue-500",
    },
    {
      name: "作业批改",
      description: "快速评分与反馈",
      icon: "fa-check-square",
      bgColor: "bg-green-500",
    },
    {
      name: "题库管理",
      description: "创建与组织试题",
      icon: "fa-database",
      bgColor: "bg-purple-500",
    },
    {
      name: "数据分析",
      description: "学生学习情况报告",
      icon: "fa-chart-pie",
      bgColor: "bg-orange-500",
    },
  ]);
  
  // 最近访问课程
  const recentCourses = reactive([
    {
      name: "高级编程（2025春季）",
      lastVisit: "今天 09:30",
    },
    {
      name: "数据结构（2025春季）",
      lastVisit: "昨天 15:45",
    },
    {
      name: "算法设计（2025春季）",
      lastVisit: "前天 11:20",
    },
  ]);
  
  // 图表引用
  const chartRef = ref<HTMLElement | null>(null);
  let chart: echarts.ECharts | null = null;
  
  onMounted(() => {
    if (chartRef.value) {
      chart = echarts.init(chartRef.value);
  
      const option = {
        animation: false,
        tooltip: {
          trigger: "axis",
        },
        grid: {
          left: "3%",
          right: "4%",
          bottom: "3%",
          containLabel: true,
        },
        xAxis: {
          type: "category",
          boundaryGap: false,
          data: ["周一", "周二", "周三", "周四", "周五", "周六", "周日"],
        },
        yAxis: {
          type: "value",
        },
        series: [
          {
            name: "学习活跃度",
            type: "line",
            smooth: true,
            lineStyle: {
              width: 3,
              color: "#3B82F6",
            },
            areaStyle: {
              color: {
                type: "linear",
                x: 0,
                y: 0,
                x2: 0,
                y2: 1,
                colorStops: [
                  {
                    offset: 0,
                    color: "rgba(59, 130, 246, 0.3)",
                  },
                  {
                    offset: 1,
                    color: "rgba(59, 130, 246, 0.05)",
                  },
                ],
              },
            },
            data: [85, 78, 92, 88, 95, 70, 82],
          },
          {
            name: "作业提交率",
            type: "line",
            smooth: true,
            lineStyle: {
              width: 3,
              color: "#10B981",
            },
            areaStyle: {
              color: {
                type: "linear",
                x: 0,
                y: 0,
                x2: 0,
                y2: 1,
                colorStops: [
                  {
                    offset: 0,
                    color: "rgba(16, 185, 129, 0.3)",
                  },
                  {
                    offset: 1,
                    color: "rgba(16, 185, 129, 0.05)",
                  },
                ],
              },
            },
            data: [95, 90, 98, 92, 96, 85, 90],
          },
        ],
      };
  
      chart.setOption(option);
  
      window.addEventListener("resize", () => {
        chart?.resize();
      });
    }

    // 添加点击外部关闭菜单的事件监听
    document.addEventListener('click', handleClickOutside);
  });

  // 用户菜单相关方法
  const toggleUserMenu = () => {
    showUserMenu.value = !showUserMenu.value;
  };

  const handleClickOutside = (event) => {
    if (userMenuRef.value && !userMenuRef.value.contains(event.target)) {
      showUserMenu.value = false;
    }
  };

  const handleLogout = () => {
    console.log('handleLogout 函数被调用');
    
    if (confirm('确定要退出登录吗？')) {
      console.log('用户确认退出登录');
      
      // 清除本地存储的用户信息
      localStorage.removeItem('token');
      localStorage.removeItem('userInfo');
      localStorage.removeItem('rememberedAccount');
      
      console.log('本地存储已清除');
      console.log('当前路由:', router.currentRoute.value.path);
      console.log('正在跳转到登录页...');
      
      // 跳转到登录页
      router.push('/login').then(() => {
        console.log('路由跳转完成');
      }).catch((error) => {
        console.error('路由跳转失败:', error);
      });
    } else {
      console.log('用户取消退出登录');
    }
  };

  // 组件卸载时清理事件监听
  onUnmounted(() => {
    document.removeEventListener('click', handleClickOutside);
  });
  </script>
  
  <style scoped>
  .calendar .grid-cols-7 > div {
    height: 32px;
    display: flex;
    align-items: center;
    justify-content: center;
  }
  </style>
  