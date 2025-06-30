<template>
  <div>
    <!-- 面包屑导航 -->
    <div class="mb-6 flex items-center text-sm text-gray-500">
      <span class="cursor-pointer hover:text-blue-600" @click="$router.push('/dashboard')">首页</span>
      <i class="fas fa-chevron-right mx-2 text-xs"></i>
      <span class="cursor-pointer hover:text-blue-600">个人设置</span>
      <i class="fas fa-chevron-right mx-2 text-xs"></i>
      <span class="text-gray-700">偏好设置</span>
    </div>

    <!-- 页面标题 -->
    <div class="mb-6">
      <h1 class="text-2xl font-bold text-gray-900">偏好设置</h1>
      <p class="text-gray-600 mt-1">个性化您的使用体验，设置界面和功能偏好</p>
    </div>

    <div class="space-y-6">
      <!-- 界面设置 -->
      <div class="bg-white rounded-lg shadow-sm border border-gray-200 p-6">
        <h2 class="text-lg font-semibold text-gray-900 mb-6">界面设置</h2>
        
        <div class="space-y-6">
          <!-- 主题选择 -->
          <div>
            <label class="block text-sm font-medium text-gray-700 mb-4">主题模式</label>
            <div class="grid grid-cols-1 md:grid-cols-3 gap-4">
              <div 
                class="cursor-pointer border-2 rounded-lg p-4 transition-colors"
                :class="preferences.theme === 'light' ? 'border-blue-500 bg-blue-50' : 'border-gray-200 hover:border-gray-300'"
                @click="preferences.theme = 'light'"
              >
                <div class="flex items-center justify-center mb-3">
                  <div class="w-12 h-8 bg-white border border-gray-300 rounded-md flex items-center justify-center">
                    <i class="fas fa-sun text-yellow-500"></i>
                  </div>
                </div>
                <div class="text-center">
                  <h3 class="font-medium text-gray-900">浅色模式</h3>
                  <p class="text-xs text-gray-600 mt-1">经典的白色背景界面</p>
                </div>
              </div>

              <div 
                class="cursor-pointer border-2 rounded-lg p-4 transition-colors"
                :class="preferences.theme === 'dark' ? 'border-blue-500 bg-blue-50' : 'border-gray-200 hover:border-gray-300'"
                @click="preferences.theme = 'dark'"
              >
                <div class="flex items-center justify-center mb-3">
                  <div class="w-12 h-8 bg-gray-800 border border-gray-600 rounded-md flex items-center justify-center">
                    <i class="fas fa-moon text-gray-300"></i>
                  </div>
                </div>
                <div class="text-center">
                  <h3 class="font-medium text-gray-900">深色模式</h3>
                  <p class="text-xs text-gray-600 mt-1">护眼的深色背景界面</p>
                </div>
              </div>

              <div 
                class="cursor-pointer border-2 rounded-lg p-4 transition-colors"
                :class="preferences.theme === 'auto' ? 'border-blue-500 bg-blue-50' : 'border-gray-200 hover:border-gray-300'"
                @click="preferences.theme = 'auto'"
              >
                <div class="flex items-center justify-center mb-3">
                  <div class="w-12 h-8 bg-gradient-to-r from-white via-gray-400 to-gray-800 border border-gray-300 rounded-md flex items-center justify-center">
                    <i class="fas fa-adjust text-gray-600"></i>
                  </div>
                </div>
                <div class="text-center">
                  <h3 class="font-medium text-gray-900">自动模式</h3>
                  <p class="text-xs text-gray-600 mt-1">跟随系统自动切换</p>
                </div>
              </div>
            </div>
          </div>

          <!-- 字体大小 -->
          <div>
            <label class="block text-sm font-medium text-gray-700 mb-4">字体大小</label>
            <div class="flex items-center space-x-4">
              <span class="text-sm text-gray-600 w-12">小</span>
              <input 
                v-model="preferences.fontSize"
                type="range" 
                min="12" 
                max="20" 
                step="2"
                class="flex-1 h-2 bg-gray-200 rounded-lg appearance-none cursor-pointer"
              />
              <span class="text-sm text-gray-600 w-12">大</span>
            </div>
            <div class="mt-2 text-center">
              <span class="text-sm text-gray-500">当前大小: {{ preferences.fontSize }}px</span>
            </div>
            <div class="mt-3 p-3 border border-gray-200 rounded-lg">
              <p :style="{ fontSize: preferences.fontSize + 'px' }" class="text-gray-700">
                这是预览文字，展示当前字体大小效果
              </p>
            </div>
          </div>

          <!-- 侧边栏设置 -->
          <div>
            <label class="block text-sm font-medium text-gray-700 mb-4">侧边栏</label>
            <div class="space-y-3">
              <div class="flex items-center justify-between">
                <div>
                  <p class="font-medium text-gray-900">默认收起侧边栏</p>
                  <p class="text-sm text-gray-600">页面加载时侧边栏保持收起状态</p>
                </div>
                <label class="relative inline-flex items-center cursor-pointer">
                  <input v-model="preferences.sidebarCollapsed" type="checkbox" class="sr-only peer">
                  <div class="w-11 h-6 bg-gray-200 peer-focus:outline-none peer-focus:ring-4 peer-focus:ring-blue-300 rounded-full peer peer-checked:after:translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:left-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-5 after:w-5 after:transition-all peer-checked:bg-blue-600"></div>
                </label>
              </div>
              
              <div class="flex items-center justify-between">
                <div>
                  <p class="font-medium text-gray-900">记住侧边栏状态</p>
                  <p class="text-sm text-gray-600">记住上次使用时的侧边栏展开/收起状态</p>
                </div>
                <label class="relative inline-flex items-center cursor-pointer">
                  <input v-model="preferences.rememberSidebarState" type="checkbox" class="sr-only peer">
                  <div class="w-11 h-6 bg-gray-200 peer-focus:outline-none peer-focus:ring-4 peer-focus:ring-blue-300 rounded-full peer peer-checked:after:translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:left-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-5 after:w-5 after:transition-all peer-checked:bg-blue-600"></div>
                </label>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- 语言和地区 -->
      <div class="bg-white rounded-lg shadow-sm border border-gray-200 p-6">
        <h2 class="text-lg font-semibold text-gray-900 mb-6">语言和地区</h2>
        
        <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
          <div>
            <label class="block text-sm font-medium text-gray-700 mb-2">界面语言</label>
            <select 
              v-model="preferences.language"
              class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
            >
              <option value="zh-CN">简体中文</option>
              <option value="zh-TW">繁體中文</option>
              <option value="en-US">English (US)</option>
              <option value="en-GB">English (UK)</option>
              <option value="ja-JP">日本語</option>
              <option value="ko-KR">한국어</option>
            </select>
          </div>
          
          <div>
            <label class="block text-sm font-medium text-gray-700 mb-2">时区</label>
            <select 
              v-model="preferences.timezone"
              class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
            >
              <option value="Asia/Shanghai">北京时间 (UTC+8)</option>
              <option value="Asia/Tokyo">东京时间 (UTC+9)</option>
              <option value="Asia/Seoul">首尔时间 (UTC+9)</option>
              <option value="America/New_York">纽约时间 (UTC-5)</option>
              <option value="America/Los_Angeles">洛杉矶时间 (UTC-8)</option>
              <option value="Europe/London">伦敦时间 (UTC+0)</option>
            </select>
          </div>
          
          <div>
            <label class="block text-sm font-medium text-gray-700 mb-2">日期格式</label>
            <select 
              v-model="preferences.dateFormat"
              class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
            >
              <option value="YYYY-MM-DD">2024-01-20</option>
              <option value="DD/MM/YYYY">20/01/2024</option>
              <option value="MM/DD/YYYY">01/20/2024</option>
              <option value="DD.MM.YYYY">20.01.2024</option>
            </select>
          </div>
          
          <div>
            <label class="block text-sm font-medium text-gray-700 mb-2">时间格式</label>
            <select 
              v-model="preferences.timeFormat"
              class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
            >
              <option value="24">24小时制 (14:30)</option>
              <option value="12">12小时制 (2:30 PM)</option>
            </select>
          </div>
        </div>
      </div>

      <!-- 功能偏好 -->
      <div class="bg-white rounded-lg shadow-sm border border-gray-200 p-6">
        <h2 class="text-lg font-semibold text-gray-900 mb-6">功能偏好</h2>
        
        <div class="space-y-4">
          <div class="flex items-center justify-between py-2">
            <div>
              <p class="font-medium text-gray-900">启用动画效果</p>
              <p class="text-sm text-gray-600">界面切换和交互动画</p>
            </div>
            <label class="relative inline-flex items-center cursor-pointer">
              <input v-model="preferences.animations" type="checkbox" class="sr-only peer">
              <div class="w-11 h-6 bg-gray-200 peer-focus:outline-none peer-focus:ring-4 peer-focus:ring-blue-300 rounded-full peer peer-checked:after:translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:left-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-5 after:w-5 after:transition-all peer-checked:bg-blue-600"></div>
            </label>
          </div>
          
          <div class="flex items-center justify-between py-2">
            <div>
              <p class="font-medium text-gray-900">自动保存草稿</p>
              <p class="text-sm text-gray-600">编辑内容时自动保存为草稿</p>
            </div>
            <label class="relative inline-flex items-center cursor-pointer">
              <input v-model="preferences.autoSave" type="checkbox" class="sr-only peer">
              <div class="w-11 h-6 bg-gray-200 peer-focus:outline-none peer-focus:ring-4 peer-focus:ring-blue-300 rounded-full peer peer-checked:after:translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:left-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-5 after:w-5 after:transition-all peer-checked:bg-blue-600"></div>
            </label>
          </div>
          
          <div class="flex items-center justify-between py-2">
            <div>
              <p class="font-medium text-gray-900">快捷键提示</p>
              <p class="text-sm text-gray-600">在界面上显示键盘快捷键提示</p>
            </div>
            <label class="relative inline-flex items-center cursor-pointer">
              <input v-model="preferences.keyboardShortcuts" type="checkbox" class="sr-only peer">
              <div class="w-11 h-6 bg-gray-200 peer-focus:outline-none peer-focus:ring-4 peer-focus:ring-blue-300 rounded-full peer peer-checked:after:translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:left-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-5 after:w-5 after:transition-all peer-checked:bg-blue-600"></div>
            </label>
          </div>
          
          <div class="flex items-center justify-between py-2">
            <div>
              <p class="font-medium text-gray-900">声音提示</p>
              <p class="text-sm text-gray-600">操作成功或错误时播放提示音</p>
            </div>
            <label class="relative inline-flex items-center cursor-pointer">
              <input v-model="preferences.soundEffects" type="checkbox" class="sr-only peer">
              <div class="w-11 h-6 bg-gray-200 peer-focus:outline-none peer-focus:ring-4 peer-focus:ring-blue-300 rounded-full peer peer-checked:after:translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:left-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-5 after:w-5 after:transition-all peer-checked:bg-blue-600"></div>
            </label>
          </div>
        </div>

        <div class="mt-6">
          <label class="block text-sm font-medium text-gray-700 mb-2">每页显示条数</label>
          <select 
            v-model="preferences.pageSize"
            class="w-full md:w-48 px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
          >
            <option value="10">10 条/页</option>
            <option value="20">20 条/页</option>
            <option value="50">50 条/页</option>
            <option value="100">100 条/页</option>
          </select>
        </div>
      </div>

      <!-- AI助手偏好 -->
      <div class="bg-white rounded-lg shadow-sm border border-gray-200 p-6">
        <h2 class="text-lg font-semibold text-gray-900 mb-6">AI助手偏好</h2>
        
        <div class="space-y-4">
          <div class="flex items-center justify-between py-2">
            <div>
              <p class="font-medium text-gray-900">启用AI智能推荐</p>
              <p class="text-sm text-gray-600">根据学习习惯推荐相关内容</p>
            </div>
            <label class="relative inline-flex items-center cursor-pointer">
              <input v-model="preferences.aiRecommendations" type="checkbox" class="sr-only peer">
              <div class="w-11 h-6 bg-gray-200 peer-focus:outline-none peer-focus:ring-4 peer-focus:ring-blue-300 rounded-full peer peer-checked:after:translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:left-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-5 after:w-5 after:transition-all peer-checked:bg-blue-600"></div>
            </label>
          </div>
          
          <div class="flex items-center justify-between py-2">
            <div>
              <p class="font-medium text-gray-900">自动批改作业</p>
              <p class="text-sm text-gray-600">提交作业后自动进行AI智能批改</p>
            </div>
            <label class="relative inline-flex items-center cursor-pointer">
              <input v-model="preferences.autoGrading" type="checkbox" class="sr-only peer">
              <div class="w-11 h-6 bg-gray-200 peer-focus:outline-none peer-focus:ring-4 peer-focus:ring-blue-300 rounded-full peer peer-checked:after:translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:left-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-5 after:w-5 after:transition-all peer-checked:bg-blue-600"></div>
            </label>
          </div>
          
          <div>
            <label class="block text-sm font-medium text-gray-700 mb-2">AI对话风格</label>
            <select 
              v-model="preferences.aiStyle"
              class="w-full md:w-64 px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
            >
              <option value="professional">专业严谨</option>
              <option value="friendly">友好亲切</option>
              <option value="casual">轻松随意</option>
              <option value="academic">学术风格</option>
            </select>
          </div>
        </div>
      </div>

      <!-- 重置选项 -->
      <div class="bg-red-50 border border-red-200 rounded-lg p-6">
        <div class="flex items-start">
          <div class="w-10 h-10 bg-red-100 rounded-lg flex items-center justify-center mr-4 flex-shrink-0">
            <i class="fas fa-exclamation-triangle text-red-600"></i>
          </div>
          <div class="flex-1">
            <h3 class="font-medium text-red-900 mb-2">重置偏好设置</h3>
            <p class="text-sm text-red-800 mb-4">
              这将清除所有个人偏好设置，恢复到系统默认状态。此操作无法撤销。
            </p>
            <button 
              @click="resetAllPreferences"
              class="px-4 py-2 bg-red-600 text-white rounded-lg hover:bg-red-700 transition-colors text-sm"
            >
              重置所有偏好设置
            </button>
          </div>
        </div>
      </div>

      <!-- 保存按钮 -->
      <div class="flex justify-end space-x-4">
        <button 
          @click="resetToDefault"
          class="px-6 py-2 bg-gray-100 text-gray-700 rounded-lg hover:bg-gray-200 transition-colors"
        >
          恢复默认
        </button>
        <button 
          @click="savePreferences"
          class="px-6 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition-colors"
        >
          保存设置
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { reactive } from 'vue';

// 偏好设置数据
const preferences = reactive({
  // 界面设置
  theme: 'light',
  fontSize: 14,
  sidebarCollapsed: false,
  rememberSidebarState: true,
  
  // 语言和地区
  language: 'zh-CN',
  timezone: 'Asia/Shanghai',
  dateFormat: 'YYYY-MM-DD',
  timeFormat: '24',
  
  // 功能偏好
  animations: true,
  autoSave: true,
  keyboardShortcuts: true,
  soundEffects: false,
  pageSize: '20',
  
  // AI助手偏好
  aiRecommendations: true,
  autoGrading: true,
  aiStyle: 'friendly'
});

// 默认设置
const defaultPreferences = {
  theme: 'light',
  fontSize: 14,
  sidebarCollapsed: false,
  rememberSidebarState: true,
  language: 'zh-CN',
  timezone: 'Asia/Shanghai',
  dateFormat: 'YYYY-MM-DD',
  timeFormat: '24',
  animations: true,
  autoSave: true,
  keyboardShortcuts: true,
  soundEffects: false,
  pageSize: '20',
  aiRecommendations: true,
  autoGrading: true,
  aiStyle: 'friendly'
};

// 方法
const savePreferences = () => {
  console.log('保存偏好设置', preferences);
  // 这里应该调用API保存偏好设置
  localStorage.setItem('userPreferences', JSON.stringify(preferences));
  alert('偏好设置保存成功！');
};

const resetToDefault = () => {
  Object.assign(preferences, defaultPreferences);
  console.log('恢复默认设置');
};

const resetAllPreferences = () => {
  if (confirm('确定要重置所有偏好设置吗？此操作无法撤销。')) {
    Object.assign(preferences, defaultPreferences);
    localStorage.removeItem('userPreferences');
    console.log('重置所有偏好设置');
    alert('所有偏好设置已重置！');
  }
};

// 初始化设置
try {
  const savedPreferences = localStorage.getItem('userPreferences');
  if (savedPreferences) {
    Object.assign(preferences, JSON.parse(savedPreferences));
  }
} catch (error) {
  console.warn('加载偏好设置失败:', error);
}
</script> 