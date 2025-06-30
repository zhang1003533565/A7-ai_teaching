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
      <span class="text-gray-700">邮件配置</span>
    </div>

    <!-- 页面标题 -->
    <div class="mb-6">
      <h1 class="text-2xl font-bold text-gray-900">邮件配置</h1>
      <p class="mt-2 text-gray-600">配置系统邮件发送服务和相关参数</p>
    </div>

    <!-- 邮件服务器配置 -->
    <div class="bg-white rounded-lg shadow-sm p-6 mb-6">
      <h2 class="text-lg font-semibold text-gray-900 mb-4">
        <i class="fas fa-server text-blue-600 mr-2"></i>SMTP服务器配置
      </h2>
      <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
        <div>
          <label class="block text-sm font-medium text-gray-700 mb-2">SMTP服务器地址</label>
          <input
            type="text"
            v-model="emailConfig.smtpHost"
            class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
            placeholder="smtp.example.com"
          />
        </div>
        <div>
          <label class="block text-sm font-medium text-gray-700 mb-2">端口号</label>
          <input
            type="number"
            v-model="emailConfig.smtpPort"
            class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
            placeholder="587"
          />
        </div>
        <div>
          <label class="block text-sm font-medium text-gray-700 mb-2">用户名</label>
          <input
            type="text"
            v-model="emailConfig.username"
            class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
            placeholder="your-email@example.com"
          />
        </div>
        <div>
          <label class="block text-sm font-medium text-gray-700 mb-2">密码</label>
          <input
            type="password"
            v-model="emailConfig.password"
            class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
            placeholder="••••••••"
          />
        </div>
        <div>
          <label class="block text-sm font-medium text-gray-700 mb-2">加密方式</label>
          <select
            v-model="emailConfig.encryption"
            class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
          >
            <option value="none">无加密</option>
            <option value="tls">TLS</option>
            <option value="ssl">SSL</option>
          </select>
        </div>
        <div>
          <label class="block text-sm font-medium text-gray-700 mb-2">发件人邮箱</label>
          <input
            type="email"
            v-model="emailConfig.fromEmail"
            class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
            placeholder="noreply@example.com"
          />
        </div>
      </div>
    </div>

    <!-- 邮件模板配置 -->
    <div class="bg-white rounded-lg shadow-sm p-6 mb-6">
      <h2 class="text-lg font-semibold text-gray-900 mb-4">
        <i class="fas fa-envelope-open-text text-green-600 mr-2"></i>邮件模板
      </h2>
      <div class="space-y-6">
        <!-- 注册确认邮件 -->
        <div>
          <h3 class="text-md font-medium text-gray-800 mb-3">注册确认邮件</h3>
          <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-2">邮件主题</label>
              <input
                type="text"
                v-model="emailTemplates.registration.subject"
                class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              />
            </div>
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-2">发件人名称</label>
              <input
                type="text"
                v-model="emailTemplates.registration.fromName"
                class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              />
            </div>
          </div>
          <div class="mt-4">
            <label class="block text-sm font-medium text-gray-700 mb-2">邮件内容</label>
            <textarea
              v-model="emailTemplates.registration.content"
              rows="4"
              class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
            ></textarea>
          </div>
        </div>

        <!-- 密码重置邮件 -->
        <div>
          <h3 class="text-md font-medium text-gray-800 mb-3">密码重置邮件</h3>
          <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-2">邮件主题</label>
              <input
                type="text"
                v-model="emailTemplates.passwordReset.subject"
                class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              />
            </div>
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-2">发件人名称</label>
              <input
                type="text"
                v-model="emailTemplates.passwordReset.fromName"
                class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              />
            </div>
          </div>
          <div class="mt-4">
            <label class="block text-sm font-medium text-gray-700 mb-2">邮件内容</label>
            <textarea
              v-model="emailTemplates.passwordReset.content"
              rows="4"
              class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
            ></textarea>
          </div>
        </div>
      </div>
    </div>

    <!-- 邮件发送设置 -->
    <div class="bg-white rounded-lg shadow-sm p-6 mb-6">
      <h2 class="text-lg font-semibold text-gray-900 mb-4">
        <i class="fas fa-cogs text-orange-600 mr-2"></i>发送设置
      </h2>
      <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
        <div class="space-y-4">
          <div class="flex items-center">
            <input
              type="checkbox"
              v-model="emailSettings.enableEmail"
              class="h-4 w-4 text-blue-600 focus:ring-blue-500 border-gray-300 rounded"
            />
            <span class="ml-2 text-sm text-gray-700">启用邮件发送功能</span>
          </div>
          <div class="flex items-center">
            <input
              type="checkbox"
              v-model="emailSettings.enableQueue"
              class="h-4 w-4 text-blue-600 focus:ring-blue-500 border-gray-300 rounded"
            />
            <span class="ml-2 text-sm text-gray-700">启用邮件队列</span>
          </div>
          <div class="flex items-center">
            <input
              type="checkbox"
              v-model="emailSettings.enableRetry"
              class="h-4 w-4 text-blue-600 focus:ring-blue-500 border-gray-300 rounded"
            />
            <span class="ml-2 text-sm text-gray-700">启用发送失败重试</span>
          </div>
        </div>
        <div class="space-y-4">
          <div>
            <label class="block text-sm font-medium text-gray-700 mb-2">每日发送限制</label>
            <input
              type="number"
              v-model="emailSettings.dailyLimit"
              class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              placeholder="1000"
            />
          </div>
          <div>
            <label class="block text-sm font-medium text-gray-700 mb-2">重试次数</label>
            <input
              type="number"
              v-model="emailSettings.retryCount"
              class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              placeholder="3"
            />
          </div>
          <div>
            <label class="block text-sm font-medium text-gray-700 mb-2">重试间隔 (分钟)</label>
            <input
              type="number"
              v-model="emailSettings.retryInterval"
              class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              placeholder="5"
            />
          </div>
        </div>
      </div>
    </div>

    <!-- 测试邮件 -->
    <div class="bg-white rounded-lg shadow-sm p-6 mb-6">
      <h2 class="text-lg font-semibold text-gray-900 mb-4">
        <i class="fas fa-paper-plane text-purple-600 mr-2"></i>测试邮件
      </h2>
      <div class="flex items-end space-x-4">
        <div class="flex-1">
          <label class="block text-sm font-medium text-gray-700 mb-2">测试邮箱地址</label>
          <input
            type="email"
            v-model="testEmail"
            class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
            placeholder="test@example.com"
          />
        </div>
        <button
          @click="sendTestEmail"
          class="px-4 py-2 bg-purple-600 text-white rounded-md hover:bg-purple-700 transition-colors"
        >
          发送测试邮件
        </button>
      </div>
    </div>

    <!-- 操作按钮 -->
    <div class="flex justify-end space-x-4">
      <button
        @click="resetConfig"
        class="px-4 py-2 text-gray-700 bg-gray-100 border border-gray-300 rounded-md hover:bg-gray-200 transition-colors"
      >
        重置
      </button>
      <button
        @click="saveConfig"
        class="px-4 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700 transition-colors"
      >
        保存配置
      </button>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive } from 'vue'

// 邮件服务器配置
const emailConfig = reactive({
  smtpHost: 'smtp.qq.com',
  smtpPort: 587,
  username: '',
  password: '',
  encryption: 'tls',
  fromEmail: 'noreply@aiteaching.com'
})

// 邮件模板
const emailTemplates = reactive({
  registration: {
    subject: '欢迎注册AI智能教学系统',
    fromName: 'AI智能教学系统',
    content: '欢迎您注册AI智能教学系统！请点击以下链接完成邮箱验证：{verification_link}'
  },
  passwordReset: {
    subject: '密码重置请求',
    fromName: 'AI智能教学系统',
    content: '您好，您请求重置密码。请点击以下链接重置您的密码：{reset_link}'
  }
})

// 邮件发送设置
const emailSettings = reactive({
  enableEmail: true,
  enableQueue: true,
  enableRetry: true,
  dailyLimit: 1000,
  retryCount: 3,
  retryInterval: 5
})

// 测试邮箱
const testEmail = ref('')

// 保存配置
const saveConfig = () => {
  console.log('保存邮件配置:', { emailConfig, emailTemplates, emailSettings })
  alert('邮件配置已保存！')
}

// 重置配置
const resetConfig = () => {
  if (confirm('确定要重置邮件配置吗？')) {
    // 重置为默认值
    Object.assign(emailConfig, {
      smtpHost: 'smtp.qq.com',
      smtpPort: 587,
      username: '',
      password: '',
      encryption: 'tls',
      fromEmail: 'noreply@aiteaching.com'
    })
    
    Object.assign(emailTemplates.registration, {
      subject: '欢迎注册AI智能教学系统',
      fromName: 'AI智能教学系统',
      content: '欢迎您注册AI智能教学系统！请点击以下链接完成邮箱验证：{verification_link}'
    })
    
    Object.assign(emailTemplates.passwordReset, {
      subject: '密码重置请求',
      fromName: 'AI智能教学系统',
      content: '您好，您请求重置密码。请点击以下链接重置您的密码：{reset_link}'
    })
    
    Object.assign(emailSettings, {
      enableEmail: true,
      enableQueue: true,
      enableRetry: true,
      dailyLimit: 1000,
      retryCount: 3,
      retryInterval: 5
    })
  }
}

// 发送测试邮件
const sendTestEmail = () => {
  if (!testEmail.value) {
    alert('请输入测试邮箱地址！')
    return
  }
  
  console.log('发送测试邮件到:', testEmail.value)
  alert(`测试邮件已发送到 ${testEmail.value}！`)
}
</script> 