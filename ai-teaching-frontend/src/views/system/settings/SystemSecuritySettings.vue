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
      <span class="text-gray-700">安全策略</span>
    </div>

    <!-- 页面标题 -->
    <div class="mb-6">
      <h1 class="text-2xl font-bold text-gray-900">安全策略</h1>
      <p class="mt-2 text-gray-600">配置系统安全相关策略和防护措施</p>
    </div>

    <div class="grid grid-cols-1 lg:grid-cols-2 gap-6">
      <!-- 登录安全 -->
      <div class="bg-white rounded-lg shadow-sm p-6">
        <h2 class="text-lg font-semibold text-gray-900 mb-4">
          <i class="fas fa-sign-in-alt text-blue-600 mr-2"></i>登录安全
        </h2>
        <div class="space-y-4">
          <div class="flex items-center justify-between">
            <div>
              <span class="text-sm font-medium text-gray-700">启用双因子认证</span>
              <p class="text-xs text-gray-500">要求用户设置两步验证</p>
            </div>
            <label class="relative inline-flex items-center cursor-pointer">
              <input type="checkbox" v-model="securitySettings.twoFactorAuth" class="sr-only peer">
              <div class="w-11 h-6 bg-gray-200 peer-focus:outline-none peer-focus:ring-4 peer-focus:ring-blue-300 rounded-full peer peer-checked:after:translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:left-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-5 after:w-5 after:transition-all peer-checked:bg-blue-600"></div>
            </label>
          </div>
          <div>
            <label class="block text-sm font-medium text-gray-700 mb-2">登录失败限制次数</label>
            <input
              type="number"
              v-model="securitySettings.maxLoginAttempts"
              class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              placeholder="5"
            />
          </div>
          <div>
            <label class="block text-sm font-medium text-gray-700 mb-2">账户锁定时间 (分钟)</label>
            <input
              type="number"
              v-model="securitySettings.lockoutDuration"
              class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              placeholder="30"
            />
          </div>
          <div>
            <label class="block text-sm font-medium text-gray-700 mb-2">会话超时时间 (分钟)</label>
            <input
              type="number"
              v-model="securitySettings.sessionTimeout"
              class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              placeholder="120"
            />
          </div>
        </div>
      </div>

      <!-- 密码策略 -->
      <div class="bg-white rounded-lg shadow-sm p-6">
        <h2 class="text-lg font-semibold text-gray-900 mb-4">
          <i class="fas fa-key text-green-600 mr-2"></i>密码策略
        </h2>
        <div class="space-y-4">
          <div>
            <label class="block text-sm font-medium text-gray-700 mb-2">最小密码长度</label>
            <input
              type="number"
              v-model="passwordPolicy.minLength"
              class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              placeholder="8"
            />
          </div>
          <div class="space-y-2">
            <div class="flex items-center">
              <input
                type="checkbox"
                v-model="passwordPolicy.requireUppercase"
                class="h-4 w-4 text-blue-600 focus:ring-blue-500 border-gray-300 rounded"
              />
              <span class="ml-2 text-sm text-gray-700">需要大写字母</span>
            </div>
            <div class="flex items-center">
              <input
                type="checkbox"
                v-model="passwordPolicy.requireLowercase"
                class="h-4 w-4 text-blue-600 focus:ring-blue-500 border-gray-300 rounded"
              />
              <span class="ml-2 text-sm text-gray-700">需要小写字母</span>
            </div>
            <div class="flex items-center">
              <input
                type="checkbox"
                v-model="passwordPolicy.requireNumbers"
                class="h-4 w-4 text-blue-600 focus:ring-blue-500 border-gray-300 rounded"
              />
              <span class="ml-2 text-sm text-gray-700">需要数字</span>
            </div>
            <div class="flex items-center">
              <input
                type="checkbox"
                v-model="passwordPolicy.requireSpecialChars"
                class="h-4 w-4 text-blue-600 focus:ring-blue-500 border-gray-300 rounded"
              />
              <span class="ml-2 text-sm text-gray-700">需要特殊字符</span>
            </div>
          </div>
          <div>
            <label class="block text-sm font-medium text-gray-700 mb-2">密码有效期 (天)</label>
            <input
              type="number"
              v-model="passwordPolicy.expiryDays"
              class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              placeholder="90"
            />
          </div>
          <div>
            <label class="block text-sm font-medium text-gray-700 mb-2">禁止重复历史密码数量</label>
            <input
              type="number"
              v-model="passwordPolicy.historyCount"
              class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              placeholder="5"
            />
          </div>
        </div>
      </div>

      <!-- 访问控制 -->
      <div class="bg-white rounded-lg shadow-sm p-6">
        <h2 class="text-lg font-semibold text-gray-900 mb-4">
          <i class="fas fa-shield-alt text-orange-600 mr-2"></i>访问控制
        </h2>
        <div class="space-y-4">
          <div class="flex items-center justify-between">
            <div>
              <span class="text-sm font-medium text-gray-700">启用IP白名单</span>
              <p class="text-xs text-gray-500">只允许指定IP访问系统</p>
            </div>
            <label class="relative inline-flex items-center cursor-pointer">
              <input type="checkbox" v-model="accessControl.enableIPWhitelist" class="sr-only peer">
              <div class="w-11 h-6 bg-gray-200 peer-focus:outline-none peer-focus:ring-4 peer-focus:ring-blue-300 rounded-full peer peer-checked:after:translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:left-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-5 after:w-5 after:transition-all peer-checked:bg-blue-600"></div>
            </label>
          </div>
          <div v-if="accessControl.enableIPWhitelist">
            <label class="block text-sm font-medium text-gray-700 mb-2">允许访问的IP地址 (每行一个)</label>
            <textarea
              v-model="accessControl.allowedIPs"
              rows="4"
              class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              placeholder="192.168.1.100&#10;10.0.0.0/8&#10;172.16.0.0/12"
            ></textarea>
          </div>
          <div>
            <label class="block text-sm font-medium text-gray-700 mb-2">允许登录时间段</label>
            <div class="grid grid-cols-2 gap-2">
              <input
                type="time"
                v-model="accessControl.allowedTimeStart"
                class="px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              />
              <input
                type="time"
                v-model="accessControl.allowedTimeEnd"
                class="px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              />
            </div>
          </div>
        </div>
      </div>

      <!-- 审计日志 -->
      <div class="bg-white rounded-lg shadow-sm p-6">
        <h2 class="text-lg font-semibold text-gray-900 mb-4">
          <i class="fas fa-clipboard-list text-purple-600 mr-2"></i>审计日志
        </h2>
        <div class="space-y-4">
          <div class="flex items-center justify-between">
            <div>
              <span class="text-sm font-medium text-gray-700">启用登录日志</span>
              <p class="text-xs text-gray-500">记录用户登录活动</p>
            </div>
            <label class="relative inline-flex items-center cursor-pointer">
              <input type="checkbox" v-model="auditSettings.loginLog" class="sr-only peer">
              <div class="w-11 h-6 bg-gray-200 peer-focus:outline-none peer-focus:ring-4 peer-focus:ring-blue-300 rounded-full peer peer-checked:after:translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:left-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-5 after:w-5 after:transition-all peer-checked:bg-blue-600"></div>
            </label>
          </div>
          <div class="flex items-center justify-between">
            <div>
              <span class="text-sm font-medium text-gray-700">启用操作日志</span>
              <p class="text-xs text-gray-500">记录用户重要操作</p>
            </div>
            <label class="relative inline-flex items-center cursor-pointer">
              <input type="checkbox" v-model="auditSettings.operationLog" class="sr-only peer">
              <div class="w-11 h-6 bg-gray-200 peer-focus:outline-none peer-focus:ring-4 peer-focus:ring-blue-300 rounded-full peer peer-checked:after:translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:left-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-5 after:w-5 after:transition-all peer-checked:bg-blue-600"></div>
            </label>
          </div>
          <div class="flex items-center justify-between">
            <div>
              <span class="text-sm font-medium text-gray-700">启用错误日志</span>
              <p class="text-xs text-gray-500">记录系统错误信息</p>
            </div>
            <label class="relative inline-flex items-center cursor-pointer">
              <input type="checkbox" v-model="auditSettings.errorLog" class="sr-only peer">
              <div class="w-11 h-6 bg-gray-200 peer-focus:outline-none peer-focus:ring-4 peer-focus:ring-blue-300 rounded-full peer peer-checked:after:translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:left-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-5 after:w-5 after:transition-all peer-checked:bg-blue-600"></div>
            </label>
          </div>
          <div>
            <label class="block text-sm font-medium text-gray-700 mb-2">日志保留时间 (天)</label>
            <input
              type="number"
              v-model="auditSettings.retentionDays"
              class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              placeholder="365"
            />
          </div>
        </div>
      </div>
    </div>

    <!-- 安全状态监控 -->
    <div class="mt-6 bg-white rounded-lg shadow-sm p-6">
      <h2 class="text-lg font-semibold text-gray-900 mb-4">
        <i class="fas fa-chart-line text-red-600 mr-2"></i>安全状态监控
      </h2>
      <div class="grid grid-cols-1 md:grid-cols-4 gap-4">
        <div class="bg-blue-50 rounded-lg p-4">
          <div class="text-center">
            <i class="fas fa-shield-alt text-2xl text-blue-600 mb-2"></i>
            <h3 class="text-sm font-medium text-gray-700">防护等级</h3>
            <p class="text-lg font-bold text-blue-600">高</p>
          </div>
        </div>
        <div class="bg-green-50 rounded-lg p-4">
          <div class="text-center">
            <i class="fas fa-lock text-2xl text-green-600 mb-2"></i>
            <h3 class="text-sm font-medium text-gray-700">今日安全事件</h3>
            <p class="text-lg font-bold text-green-600">0</p>
          </div>
        </div>
        <div class="bg-orange-50 rounded-lg p-4">
          <div class="text-center">
            <i class="fas fa-exclamation-triangle text-2xl text-orange-600 mb-2"></i>
            <h3 class="text-sm font-medium text-gray-700">风险提醒</h3>
            <p class="text-lg font-bold text-orange-600">2</p>
          </div>
        </div>
        <div class="bg-purple-50 rounded-lg p-4">
          <div class="text-center">
            <i class="fas fa-clock text-2xl text-purple-600 mb-2"></i>
            <h3 class="text-sm font-medium text-gray-700">最后检查</h3>
            <p class="text-sm font-bold text-purple-600">10分钟前</p>
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
import { reactive } from 'vue'

// 安全设置
const securitySettings = reactive({
  twoFactorAuth: false,
  maxLoginAttempts: 5,
  lockoutDuration: 30,
  sessionTimeout: 120
})

// 密码策略
const passwordPolicy = reactive({
  minLength: 8,
  requireUppercase: true,
  requireLowercase: true,
  requireNumbers: true,
  requireSpecialChars: false,
  expiryDays: 90,
  historyCount: 5
})

// 访问控制
const accessControl = reactive({
  enableIPWhitelist: false,
  allowedIPs: '',
  allowedTimeStart: '09:00',
  allowedTimeEnd: '18:00'
})

// 审计设置
const auditSettings = reactive({
  loginLog: true,
  operationLog: true,
  errorLog: true,
  retentionDays: 365
})

// 保存设置
const saveSettings = () => {
  console.log('保存安全设置:', {
    securitySettings,
    passwordPolicy,
    accessControl,
    auditSettings
  })
  alert('安全策略已保存！')
}

// 重置设置
const resetSettings = () => {
  if (confirm('确定要重置安全策略吗？')) {
    // 重置为默认值
    Object.assign(securitySettings, {
      twoFactorAuth: false,
      maxLoginAttempts: 5,
      lockoutDuration: 30,
      sessionTimeout: 120
    })
    
    Object.assign(passwordPolicy, {
      minLength: 8,
      requireUppercase: true,
      requireLowercase: true,
      requireNumbers: true,
      requireSpecialChars: false,
      expiryDays: 90,
      historyCount: 5
    })
    
    Object.assign(accessControl, {
      enableIPWhitelist: false,
      allowedIPs: '',
      allowedTimeStart: '09:00',
      allowedTimeEnd: '18:00'
    })
    
    Object.assign(auditSettings, {
      loginLog: true,
      operationLog: true,
      errorLog: true,
      retentionDays: 365
    })
  }
}
</script> 