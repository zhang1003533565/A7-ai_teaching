<template>
  <div>
    <!-- 面包屑导航 -->
    <div class="mb-6 flex items-center text-sm text-gray-500">
      <span class="cursor-pointer hover:text-blue-600">首页</span>
      <i class="fas fa-chevron-right mx-2 text-xs"></i>
      <span class="cursor-pointer hover:text-blue-600">系统管理</span>
      <i class="fas fa-chevron-right mx-2 text-xs"></i>
      <span class="cursor-pointer hover:text-blue-600">系统设置</span>
      <i class="fas fa-chevron-right mx-2 text-xs"></i>
      <span class="text-gray-700">基本设置</span>
    </div>

    <!-- 页面标题 -->
    <div class="mb-6">
      <h1 class="text-2xl font-bold text-gray-900">基本设置</h1>
      <p class="mt-2 text-gray-600">配置系统的基本参数和全局设置</p>
    </div>

    <!-- 设置表单 -->
    <div class="grid grid-cols-1 lg:grid-cols-2 gap-6">
      <!-- 系统信息设置 -->
      <div class="bg-white rounded-lg shadow-sm p-6">
        <h2 class="text-lg font-semibold text-gray-900 mb-4">
          <i class="fas fa-info-circle text-blue-600 mr-2"></i>系统信息
        </h2>
        <div class="space-y-4">
          <div>
            <label class="block text-sm font-medium text-gray-700 mb-2">系统名称</label>
            <input
              type="text"
              v-model="settings.systemName"
              class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              placeholder="AI智能教学系统"
            />
          </div>
          <div>
            <label class="block text-sm font-medium text-gray-700 mb-2">系统版本</label>
            <input
              type="text"
              v-model="settings.systemVersion"
              class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              placeholder="v1.0.0"
            />
          </div>
          <div>
            <label class="block text-sm font-medium text-gray-700 mb-2">系统描述</label>
            <textarea
              v-model="settings.systemDescription"
              rows="3"
              class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              placeholder="基于AI技术的智能教学平台"
            ></textarea>
          </div>
        </div>
      </div>

      <!-- 用户设置 -->
      <div class="bg-white rounded-lg shadow-sm p-6">
        <h2 class="text-lg font-semibold text-gray-900 mb-4">
          <i class="fas fa-users text-green-600 mr-2"></i>用户设置
        </h2>
        <div class="space-y-4">
          <div>
            <label class="block text-sm font-medium text-gray-700 mb-2">默认用户角色</label>
            <select
              v-model="settings.defaultRole"
              class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
            >
              <option value="student">学生</option>
              <option value="teacher">教师</option>
              <option value="admin">管理员</option>
            </select>
          </div>
          <div>
            <label class="block text-sm font-medium text-gray-700 mb-2">用户注册</label>
            <div class="flex items-center">
              <input
                type="checkbox"
                v-model="settings.allowRegistration"
                class="h-4 w-4 text-blue-600 focus:ring-blue-500 border-gray-300 rounded"
              />
              <span class="ml-2 text-sm text-gray-700">允许用户自主注册</span>
            </div>
          </div>
          <div>
            <label class="block text-sm font-medium text-gray-700 mb-2">密码策略</label>
            <div class="space-y-2">
              <div class="flex items-center">
                <input
                  type="checkbox"
                  v-model="settings.passwordPolicy.requireUppercase"
                  class="h-4 w-4 text-blue-600 focus:ring-blue-500 border-gray-300 rounded"
                />
                <span class="ml-2 text-sm text-gray-700">需要大写字母</span>
              </div>
              <div class="flex items-center">
                <input
                  type="checkbox"
                  v-model="settings.passwordPolicy.requireNumbers"
                  class="h-4 w-4 text-blue-600 focus:ring-blue-500 border-gray-300 rounded"
                />
                <span class="ml-2 text-sm text-gray-700">需要数字</span>
              </div>
              <div class="flex items-center">
                <input
                  type="checkbox"
                  v-model="settings.passwordPolicy.requireSpecialChars"
                  class="h-4 w-4 text-blue-600 focus:ring-blue-500 border-gray-300 rounded"
                />
                <span class="ml-2 text-sm text-gray-700">需要特殊字符</span>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- 系统限制 -->
      <div class="bg-white rounded-lg shadow-sm p-6">
        <h2 class="text-lg font-semibold text-gray-900 mb-4">
          <i class="fas fa-cogs text-orange-600 mr-2"></i>系统限制
        </h2>
        <div class="space-y-4">
          <div>
            <label class="block text-sm font-medium text-gray-700 mb-2">最大在线用户数</label>
            <input
              type="number"
              v-model="settings.maxOnlineUsers"
              class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              placeholder="1000"
            />
          </div>
          <div>
            <label class="block text-sm font-medium text-gray-700 mb-2">单个文件上传限制 (MB)</label>
            <input
              type="number"
              v-model="settings.maxFileSize"
              class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              placeholder="50"
            />
          </div>
          <div>
            <label class="block text-sm font-medium text-gray-700 mb-2">会话超时时间 (分钟)</label>
            <input
              type="number"
              v-model="settings.sessionTimeout"
              class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              placeholder="30"
            />
          </div>
        </div>
      </div>

      <!-- 功能开关 -->
      <div class="bg-white rounded-lg shadow-sm p-6">
        <h2 class="text-lg font-semibold text-gray-900 mb-4">
          <i class="fas fa-toggle-on text-purple-600 mr-2"></i>功能开关
        </h2>
        <div class="space-y-4">
          <div class="flex items-center justify-between">
            <div>
              <span class="text-sm font-medium text-gray-700">AI助手功能</span>
              <p class="text-xs text-gray-500">启用智能对话和辅助功能</p>
            </div>
            <label class="relative inline-flex items-center cursor-pointer">
              <input type="checkbox" v-model="settings.features.aiAssistant" class="sr-only peer">
              <div class="w-11 h-6 bg-gray-200 peer-focus:outline-none peer-focus:ring-4 peer-focus:ring-blue-300 rounded-full peer peer-checked:after:translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:left-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-5 after:w-5 after:transition-all peer-checked:bg-blue-600"></div>
            </label>
          </div>
          <div class="flex items-center justify-between">
            <div>
              <span class="text-sm font-medium text-gray-700">在线考试</span>
              <p class="text-xs text-gray-500">启用在线考试和测评功能</p>
            </div>
            <label class="relative inline-flex items-center cursor-pointer">
              <input type="checkbox" v-model="settings.features.onlineExam" class="sr-only peer">
              <div class="w-11 h-6 bg-gray-200 peer-focus:outline-none peer-focus:ring-4 peer-focus:ring-blue-300 rounded-full peer peer-checked:after:translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:left-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-5 after:w-5 after:transition-all peer-checked:bg-blue-600"></div>
            </label>
          </div>
          <div class="flex items-center justify-between">
            <div>
              <span class="text-sm font-medium text-gray-700">视频直播</span>
              <p class="text-xs text-gray-500">启用在线直播教学功能</p>
            </div>
            <label class="relative inline-flex items-center cursor-pointer">
              <input type="checkbox" v-model="settings.features.liveStreaming" class="sr-only peer">
              <div class="w-11 h-6 bg-gray-200 peer-focus:outline-none peer-focus:ring-4 peer-focus:ring-blue-300 rounded-full peer peer-checked:after:translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:left-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-5 after:w-5 after:transition-all peer-checked:bg-blue-600"></div>
            </label>
          </div>
          <div class="flex items-center justify-between">
            <div>
              <span class="text-sm font-medium text-gray-700">消息通知</span>
              <p class="text-xs text-gray-500">启用系统消息推送功能</p>
            </div>
            <label class="relative inline-flex items-center cursor-pointer">
              <input type="checkbox" v-model="settings.features.notifications" class="sr-only peer">
              <div class="w-11 h-6 bg-gray-200 peer-focus:outline-none peer-focus:ring-4 peer-focus:ring-blue-300 rounded-full peer peer-checked:after:translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:left-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-5 after:w-5 after:transition-all peer-checked:bg-blue-600"></div>
            </label>
          </div>
        </div>
      </div>
    </div>

    <!-- 操作按钮 -->
    <div class="mt-6 flex justify-end space-x-4">
      <button
        @click="resetSettings"
        class="px-4 py-2 text-gray-700 bg-gray-100 border border-gray-300 rounded-md hover:bg-gray-200 transition-colors"
      >
        重置
      </button>
      <button
        @click="saveSettings"
        class="px-4 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700 transition-colors"
      >
        保存设置
      </button>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive } from 'vue'

// 系统设置数据
const settings = reactive({
  systemName: 'AI智能教学系统',
  systemVersion: 'v1.0.0',
  systemDescription: '基于AI技术的智能教学平台，为师生提供个性化的教学体验',
  defaultRole: 'student',
  allowRegistration: true,
  passwordPolicy: {
    requireUppercase: true,
    requireNumbers: true,
    requireSpecialChars: false
  },
  maxOnlineUsers: 1000,
  maxFileSize: 50,
  sessionTimeout: 30,
  features: {
    aiAssistant: true,
    onlineExam: true,
    liveStreaming: false,
    notifications: true
  }
})

// 保存设置
const saveSettings = () => {
  console.log('保存设置:', settings)
  // 这里应该调用API保存设置
  alert('设置已保存！')
}

// 重置设置
const resetSettings = () => {
  if (confirm('确定要重置所有设置吗？')) {
    // 重置为默认值
    Object.assign(settings, {
      systemName: 'AI智能教学系统',
      systemVersion: 'v1.0.0',
      systemDescription: '基于AI技术的智能教学平台，为师生提供个性化的教学体验',
      defaultRole: 'student',
      allowRegistration: true,
      passwordPolicy: {
        requireUppercase: true,
        requireNumbers: true,
        requireSpecialChars: false
      },
      maxOnlineUsers: 1000,
      maxFileSize: 50,
      sessionTimeout: 30,
      features: {
        aiAssistant: true,
        onlineExam: true,
        liveStreaming: false,
        notifications: true
      }
    })
  }
}
</script> 