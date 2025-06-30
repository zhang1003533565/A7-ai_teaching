<template>
  <div>
    <!-- 面包屑导航 -->
    <div class="mb-6 flex items-center text-sm text-gray-500">
      <span class="cursor-pointer hover:text-blue-600" @click="$router.push('/dashboard')">首页</span>
      <i class="fas fa-chevron-right mx-2 text-xs"></i>
      <span class="cursor-pointer hover:text-blue-600">个人设置</span>
      <i class="fas fa-chevron-right mx-2 text-xs"></i>
      <span class="text-gray-700">安全设置</span>
    </div>

    <!-- 页面标题 -->
    <div class="mb-6">
      <h1 class="text-2xl font-bold text-gray-900">安全设置</h1>
      <p class="text-gray-600 mt-1">保护您的账户安全，管理密码和验证方式</p>
    </div>

    <div class="space-y-6">
      <!-- 密码管理 -->
      <div class="bg-white rounded-lg shadow-sm border border-gray-200 p-6">
        <div class="flex items-center justify-between mb-6">
          <div>
            <h2 class="text-lg font-semibold text-gray-900">密码管理</h2>
            <p class="text-sm text-gray-600 mt-1">定期更换密码可以提高账户安全性</p>
          </div>
          <div class="flex items-center">
            <i class="fas fa-key text-blue-600 mr-2"></i>
            <span class="text-sm text-gray-500">上次修改：{{ lastPasswordChange }}</span>
          </div>
        </div>
        
        <form @submit.prevent="changePassword" class="space-y-4">
          <div>
            <label class="block text-sm font-medium text-gray-700 mb-2">当前密码 *</label>
            <div class="relative">
              <input 
                v-model="passwordForm.currentPassword"
                :type="showCurrentPassword ? 'text' : 'password'"
                class="w-full px-3 py-2 pr-10 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                required
              />
              <button 
                type="button"
                @click="showCurrentPassword = !showCurrentPassword"
                class="absolute inset-y-0 right-0 pr-3 flex items-center"
              >
                <i :class="showCurrentPassword ? 'fas fa-eye-slash' : 'fas fa-eye'" class="text-gray-400"></i>
              </button>
            </div>
          </div>
          
          <div>
            <label class="block text-sm font-medium text-gray-700 mb-2">新密码 *</label>
            <div class="relative">
              <input 
                v-model="passwordForm.newPassword"
                :type="showNewPassword ? 'text' : 'password'"
                class="w-full px-3 py-2 pr-10 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                required
                @input="checkPasswordStrength"
              />
              <button 
                type="button"
                @click="showNewPassword = !showNewPassword"
                class="absolute inset-y-0 right-0 pr-3 flex items-center"
              >
                <i :class="showNewPassword ? 'fas fa-eye-slash' : 'fas fa-eye'" class="text-gray-400"></i>
              </button>
            </div>
            <!-- 密码强度指示器 -->
            <div class="mt-2">
              <div class="flex space-x-1">
                <div 
                  v-for="i in 4" 
                  :key="i"
                  class="h-1 flex-1 rounded"
                  :class="getPasswordStrengthColor(i)"
                ></div>
              </div>
              <p class="text-xs mt-1" :class="passwordStrength.color">
                {{ passwordStrength.text }}
              </p>
            </div>
          </div>
          
          <div>
            <label class="block text-sm font-medium text-gray-700 mb-2">确认新密码 *</label>
            <div class="relative">
              <input 
                v-model="passwordForm.confirmPassword"
                :type="showConfirmPassword ? 'text' : 'password'"
                class="w-full px-3 py-2 pr-10 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                required
              />
              <button 
                type="button"
                @click="showConfirmPassword = !showConfirmPassword"
                class="absolute inset-y-0 right-0 pr-3 flex items-center"
              >
                <i :class="showConfirmPassword ? 'fas fa-eye-slash' : 'fas fa-eye'" class="text-gray-400"></i>
              </button>
            </div>
            <p v-if="passwordForm.confirmPassword && passwordForm.newPassword !== passwordForm.confirmPassword" 
               class="text-xs text-red-500 mt-1">
              密码不匹配
            </p>
          </div>
          
          <div class="flex justify-end space-x-4 pt-4">
            <button 
              type="button"
              @click="resetPasswordForm"
              class="px-6 py-2 bg-gray-100 text-gray-700 rounded-lg hover:bg-gray-200 transition-colors"
            >
              重置
            </button>
            <button 
              type="submit"
              :disabled="!isPasswordFormValid"
              class="px-6 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition-colors disabled:opacity-50 disabled:cursor-not-allowed"
            >
              修改密码
            </button>
          </div>
        </form>
      </div>

      <!-- 两步验证 -->
      <div class="bg-white rounded-lg shadow-sm border border-gray-200 p-6">
        <div class="flex items-center justify-between mb-6">
          <div>
            <h2 class="text-lg font-semibold text-gray-900">两步验证</h2>
            <p class="text-sm text-gray-600 mt-1">为您的账户添加额外的安全保护</p>
          </div>
          <div class="flex items-center">
            <span class="px-2 py-1 text-xs rounded-full" 
                  :class="twoFactorEnabled ? 'bg-green-100 text-green-800' : 'bg-gray-100 text-gray-800'">
              {{ twoFactorEnabled ? '已启用' : '未启用' }}
            </span>
          </div>
        </div>

        <div class="space-y-4">
          <!-- 手机验证 -->
          <div class="flex items-center justify-between p-4 border border-gray-200 rounded-lg">
            <div class="flex items-center">
              <div class="w-10 h-10 bg-green-100 rounded-lg flex items-center justify-center mr-4">
                <i class="fas fa-mobile-alt text-green-600"></i>
              </div>
              <div>
                <h3 class="font-medium text-gray-900">短信验证</h3>
                <p class="text-sm text-gray-600">通过手机短信接收验证码</p>
              </div>
            </div>
            <div class="flex items-center space-x-3">
              <span class="text-sm text-gray-500">138****8888</span>
              <button 
                @click="toggleSmsVerification"
                class="px-4 py-2 text-sm rounded-lg transition-colors"
                :class="smsVerificationEnabled ? 'bg-red-100 text-red-700 hover:bg-red-200' : 'bg-blue-100 text-blue-700 hover:bg-blue-200'"
              >
                {{ smsVerificationEnabled ? '禁用' : '启用' }}
              </button>
            </div>
          </div>

          <!-- 邮箱验证 -->
          <div class="flex items-center justify-between p-4 border border-gray-200 rounded-lg">
            <div class="flex items-center">
              <div class="w-10 h-10 bg-blue-100 rounded-lg flex items-center justify-center mr-4">
                <i class="fas fa-envelope text-blue-600"></i>
              </div>
              <div>
                <h3 class="font-medium text-gray-900">邮箱验证</h3>
                <p class="text-sm text-gray-600">通过邮件接收验证链接</p>
              </div>
            </div>
            <div class="flex items-center space-x-3">
              <span class="text-sm text-gray-500">user****@example.com</span>
              <button 
                @click="toggleEmailVerification"
                class="px-4 py-2 text-sm rounded-lg transition-colors"
                :class="emailVerificationEnabled ? 'bg-red-100 text-red-700 hover:bg-red-200' : 'bg-blue-100 text-blue-700 hover:bg-blue-200'"
              >
                {{ emailVerificationEnabled ? '禁用' : '启用' }}
              </button>
            </div>
          </div>

          <!-- 应用验证器 -->
          <div class="flex items-center justify-between p-4 border border-gray-200 rounded-lg">
            <div class="flex items-center">
              <div class="w-10 h-10 bg-purple-100 rounded-lg flex items-center justify-center mr-4">
                <i class="fas fa-qrcode text-purple-600"></i>
              </div>
              <div>
                <h3 class="font-medium text-gray-900">身份验证器应用</h3>
                <p class="text-sm text-gray-600">使用 Google Authenticator 等应用生成验证码</p>
              </div>
            </div>
            <button 
              @click="setupAuthenticator"
              class="px-4 py-2 text-sm bg-purple-100 text-purple-700 rounded-lg hover:bg-purple-200 transition-colors"
            >
              {{ authenticatorEnabled ? '重新设置' : '设置' }}
            </button>
          </div>
        </div>
      </div>

      <!-- 登录历史 -->
      <div class="bg-white rounded-lg shadow-sm border border-gray-200 p-6">
        <div class="flex items-center justify-between mb-6">
          <div>
            <h2 class="text-lg font-semibold text-gray-900">登录历史</h2>
            <p class="text-sm text-gray-600 mt-1">查看最近的登录记录</p>
          </div>
          <button 
            @click="refreshLoginHistory"
            class="px-4 py-2 text-sm bg-gray-100 text-gray-700 rounded-lg hover:bg-gray-200 transition-colors"
          >
            <i class="fas fa-sync-alt mr-2"></i>
            刷新
          </button>
        </div>

        <div class="space-y-3">
          <div 
            v-for="(login, index) in loginHistory" 
            :key="index"
            class="flex items-center justify-between p-4 border border-gray-200 rounded-lg"
          >
            <div class="flex items-center">
              <div class="w-10 h-10 rounded-lg flex items-center justify-center mr-4"
                   :class="login.success ? 'bg-green-100' : 'bg-red-100'">
                <i :class="login.success ? 'fas fa-check text-green-600' : 'fas fa-times text-red-600'"></i>
              </div>
              <div>
                <div class="flex items-center space-x-2">
                  <span class="font-medium text-gray-900">{{ login.location }}</span>
                  <span class="px-2 py-1 text-xs rounded-full"
                        :class="login.success ? 'bg-green-100 text-green-800' : 'bg-red-100 text-red-800'">
                    {{ login.success ? '成功' : '失败' }}
                  </span>
                </div>
                <p class="text-sm text-gray-600">{{ login.device }} • {{ login.ip }}</p>
              </div>
            </div>
            <div class="text-right text-sm text-gray-500">
              <p>{{ login.time }}</p>
              <p>{{ login.date }}</p>
            </div>
          </div>
        </div>
      </div>

      <!-- 安全建议 -->
      <div class="bg-amber-50 border border-amber-200 rounded-lg p-6">
        <div class="flex items-start">
          <div class="w-10 h-10 bg-amber-100 rounded-lg flex items-center justify-center mr-4 flex-shrink-0">
            <i class="fas fa-lightbulb text-amber-600"></i>
          </div>
          <div>
            <h3 class="font-medium text-amber-900 mb-2">安全建议</h3>
            <ul class="text-sm text-amber-800 space-y-1">
              <li>• 使用强密码，包含大小写字母、数字和特殊字符</li>
              <li>• 定期更换密码，避免在多个网站使用相同密码</li>
              <li>• 启用两步验证，提高账户安全性</li>
              <li>• 不要在公共网络或设备上登录重要账户</li>
              <li>• 如发现异常登录，请立即修改密码</li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive, computed } from 'vue';

// 表单状态
const showCurrentPassword = ref(false);
const showNewPassword = ref(false);
const showConfirmPassword = ref(false);

// 密码表单数据
const passwordForm = reactive({
  currentPassword: '',
  newPassword: '',
  confirmPassword: ''
});

// 安全设置状态
const twoFactorEnabled = ref(true);
const smsVerificationEnabled = ref(true);
const emailVerificationEnabled = ref(false);
const authenticatorEnabled = ref(false);
const lastPasswordChange = ref('2024-01-15');

// 密码强度
const passwordStrength = ref({
  level: 0,
  text: '请输入新密码',
  color: 'text-gray-500'
});

// 登录历史
const loginHistory = ref([
  {
    success: true,
    location: '北京市',
    device: 'Chrome 浏览器',
    ip: '192.168.1.100',
    time: '14:30',
    date: '2024-01-20'
  },
  {
    success: true,
    location: '上海市',
    device: 'iPhone Safari',
    ip: '10.0.0.1',
    time: '09:15',
    date: '2024-01-19'
  },
  {
    success: false,
    location: '广州市',
    device: 'Firefox 浏览器',
    ip: '172.16.0.1',
    time: '22:45',
    date: '2024-01-18'
  }
]);

// 计算属性
const isPasswordFormValid = computed(() => {
  return passwordForm.currentPassword && 
         passwordForm.newPassword && 
         passwordForm.confirmPassword &&
         passwordForm.newPassword === passwordForm.confirmPassword &&
         passwordStrength.value.level >= 2;
});

// 方法
const checkPasswordStrength = () => {
  const password = passwordForm.newPassword;
  let level = 0;
  let text = '';
  let color = '';

  if (password.length === 0) {
    text = '请输入新密码';
    color = 'text-gray-500';
  } else if (password.length < 6) {
    level = 1;
    text = '密码太短';
    color = 'text-red-500';
  } else {
    let checks = 0;
    if (/[a-z]/.test(password)) checks++;
    if (/[A-Z]/.test(password)) checks++;
    if (/\d/.test(password)) checks++;
    if (/[^a-zA-Z\d]/.test(password)) checks++;

    if (checks === 1) {
      level = 1;
      text = '弱密码';
      color = 'text-red-500';
    } else if (checks === 2) {
      level = 2;
      text = '中等强度';
      color = 'text-yellow-500';
    } else if (checks === 3) {
      level = 3;
      text = '强密码';
      color = 'text-green-500';
    } else if (checks === 4) {
      level = 4;
      text = '非常强';
      color = 'text-green-600';
    }
  }

  passwordStrength.value = { level, text, color };
};

const getPasswordStrengthColor = (index) => {
  if (passwordStrength.value.level >= index) {
    if (passwordStrength.value.level === 1) return 'bg-red-500';
    if (passwordStrength.value.level === 2) return 'bg-yellow-500';
    if (passwordStrength.value.level >= 3) return 'bg-green-500';
  }
  return 'bg-gray-200';
};

const changePassword = () => {
  console.log('修改密码', passwordForm);
  // 这里应该调用API
  alert('密码修改成功！');
  resetPasswordForm();
};

const resetPasswordForm = () => {
  Object.assign(passwordForm, {
    currentPassword: '',
    newPassword: '',
    confirmPassword: ''
  });
  passwordStrength.value = {
    level: 0,
    text: '请输入新密码',
    color: 'text-gray-500'
  };
};

const toggleSmsVerification = () => {
  smsVerificationEnabled.value = !smsVerificationEnabled.value;
  console.log('短信验证', smsVerificationEnabled.value ? '启用' : '禁用');
};

const toggleEmailVerification = () => {
  emailVerificationEnabled.value = !emailVerificationEnabled.value;
  console.log('邮箱验证', emailVerificationEnabled.value ? '启用' : '禁用');
};

const setupAuthenticator = () => {
  console.log('设置身份验证器');
  // 这里应该显示二维码等设置步骤
  alert('身份验证器设置功能开发中...');
};

const refreshLoginHistory = () => {
  console.log('刷新登录历史');
  // 这里应该调用API获取最新的登录历史
  alert('登录历史已刷新');
};
</script> 