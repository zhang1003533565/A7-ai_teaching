<!-- The exported code uses Tailwind CSS. Install Tailwind CSS in your dev environment to ensure all styles work. -->
<template>
  <div
    class="h-screen flex flex-col items-center justify-center bg-cover bg-center px-4 sm:px-6 lg:px-8"
    :style="{ backgroundImage: `url(${campusImageUrl})` }"
  >
    <div class="w-full max-w-md backdrop-blur-sm bg-white/80 p-6 rounded-2xl">
      <!-- 顶部区域 -->
      <div class="flex flex-col items-center mb-8">
        <img :src="logoUrl" alt="校园logo" class="h-16 mb-4 object-contain" />
        <h1 class="text-2xl font-bold text-blue-700 mb-2">校园统一身份认证</h1>
        <p class="text-gray-600">欢迎回来，请登录您的账号</p>
      </div>
      <!-- 登录表单区域 -->
      <div class="bg-white shadow-md rounded-lg p-8">
        <!-- 错误提示 -->
        <div
          v-if="errorMessage"
          class="mb-4 p-3 bg-red-100 text-red-700 rounded-md text-sm"
        >
          {{ errorMessage }}
        </div>
        <form @submit.prevent="handleLogin" class="space-y-6">
          <!-- 学号输入框 -->
          <div>
            <label
              for="studentId"
              class="block text-sm font-medium text-gray-700 mb-1"
              >学号</label
            >
            <div class="relative rounded-md shadow-sm">
              <div
                class="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none"
              >
                <i class="fas fa-user text-gray-400"></i>
              </div>
              <input
                id="studentId"
                v-model="studentId"
                type="text"
                placeholder="请输入学号"
                class="form-input"
                :class="{'error': studentIdError}"
                @focus="studentIdError = false"
              />
            </div>
            <p v-if="studentIdError" class="mt-1 text-sm text-red-600">
              请输入有效的学号
            </p>
          </div>
          <!-- 密码输入框 -->
          <div>
            <label
              for="password"
              class="block text-sm font-medium text-gray-700 mb-1"
              >密码</label
            >
            <div class="relative rounded-md shadow-sm">
              <div
                class="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none"
              >
                <i class="fas fa-lock text-gray-400"></i>
              </div>
              <input
                id="password"
                v-model="password"
                :type="showPassword ? 'text' : 'password'"
                placeholder="请输入密码"
                class="form-input"
                :class="{'error': passwordError}"
                @focus="passwordError = false"
              />
              <div class="absolute inset-y-0 right-0 pr-3 flex items-center">
                <button
                  type="button"
                  class="text-gray-400 hover:text-gray-600 focus:outline-none cursor-pointer"
                  @click="togglePasswordVisibility"
                >
                  <i
                    :class="showPassword ? 'fas fa-eye-slash' : 'fas fa-eye'"
                  ></i>
                </button>
              </div>
            </div>
            <p v-if="passwordError" class="mt-1 text-sm text-red-600">
              请输入密码
            </p>
          </div>
          <!-- 记住密码和忘记密码 -->
          <div class="flex items-center justify-between">
            <div class="flex items-center">
              <input
                id="remember-me"
                v-model="rememberMe"
                type="checkbox"
                class="h-4 w-4 text-blue-600 focus:ring-blue-500 border-gray-300 rounded cursor-pointer"
              />
              <label
                for="remember-me"
                class="ml-2 block text-sm text-gray-700 cursor-pointer"
              >
                记住密码
              </label>
            </div>
            <div class="text-sm">
              <a
                href="#"
                class="font-medium text-blue-600 hover:text-blue-500 whitespace-nowrap"
              >
                忘记密码？
              </a>
            </div>
          </div>
          <!-- 登录按钮 -->
          <div>
            <button
              type="submit"
              class="w-full flex justify-center py-3 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-blue-600 hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500 transition duration-150 ease-in-out !rounded-button whitespace-nowrap cursor-pointer"
              :disabled="isLoading"
            >
              <span v-if="isLoading" class="flex items-center">
                <i class="fas fa-circle-notch fa-spin mr-2"></i> 登录中...
              </span>
              <span v-else>登录</span>
            </button>
          </div>
          <!-- 其他登录方式 -->
          <div class="mt-6">
            <div class="relative">
              <div class="absolute inset-0 flex items-center">
                <div class="w-full border-t border-gray-300"></div>
              </div>
              <div class="relative flex justify-center text-sm">
                <span class="px-2 bg-white text-gray-500">其他登录方式</span>
              </div>
            </div>
            <div class="mt-6 grid grid-cols-3 gap-3">
              <a
                href="#"
                class="w-full flex items-center justify-center px-4 py-2 border border-gray-300 rounded-md shadow-sm text-sm font-medium text-gray-700 bg-white hover:bg-gray-50 cursor-pointer"
              >
                <i class="fab fa-weixin text-green-600 text-lg"></i>
              </a>
              <a
                href="#"
                class="w-full flex items-center justify-center px-4 py-2 border border-gray-300 rounded-md shadow-sm text-sm font-medium text-gray-700 bg-white hover:bg-gray-50 cursor-pointer"
              >
                <i class="fab fa-qq text-blue-500 text-lg"></i>
              </a>
              <a
                href="#"
                class="w-full flex items-center justify-center px-4 py-2 border border-gray-300 rounded-md shadow-sm text-sm font-medium text-gray-700 bg-white hover:bg-gray-50 cursor-pointer"
              >
                <i class="fas fa-mobile-alt text-gray-600 text-lg"></i>
              </a>
            </div>
          </div>
        </form>
      </div>
      <!-- 页脚信息 -->
      <div class="mt-4 text-center text-xs text-gray-500">
        <p>© {{ currentYear }} 校园统一身份认证系统 版权所有</p>
        <p class="mt-0.5">
          技术支持：信息化建设与管理办公室 | 联系电话：400-123-4567
        </p>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { ref, computed } from "vue";
import { useRouter } from 'vue-router';
import { login } from '../api/user';

const router = useRouter();

// 状态变量
const studentId = ref("");
const password = ref("");
const showPassword = ref(false);
const rememberMe = ref(false);
const isLoading = ref(false);
const errorMessage = ref("");
const studentIdError = ref(false);
const passwordError = ref(false);

// 图片资源
const logoUrl =
  "https://readdy.ai/api/search-image?query=university%20logo%20design%2C%20minimalist%2C%20blue%20and%20gold%20colors%2C%20academic%20emblem%2C%20clean%20background%2C%20professional%20looking%2C%20high%20quality%2C%20vector%20style%20logo%20for%20a%20prestigious%20university&width=300&height=300&seq=1&orientation=squarish";
const campusImageUrl =
  "https://readdy.ai/api/search-image?query=beautiful%20university%20campus%20view%2C%20modern%20architecture%2C%20sunny%20day%2C%20green%20lawns%2C%20students%20walking%2C%20academic%20buildings%2C%20spring%20season%2C%20peaceful%20atmosphere%2C%20high%20resolution%20photography&width=800&height=400&seq=2&orientation=landscape";

// 计算当前年份
const currentYear = computed(() => new Date().getFullYear());

// 切换密码可见性
const togglePasswordVisibility = () => {
  showPassword.value = !showPassword.value;
};

// 登录处理
const handleLogin = async () => {
  // 重置错误状态
  errorMessage.value = "";
  studentIdError.value = false;
  passwordError.value = false;

  // 表单验证
  let isValid = true;
  if (!studentId.value.trim()) {
    studentIdError.value = true;
    isValid = false;
  }
  if (!password.value) {
    passwordError.value = true;
    isValid = false;
  }
  if (!isValid) {
    return;
  }

  // 调用登录接口
  try {
    isLoading.value = true;
    const response = await login({
      username: studentId.value,
      password: password.value
    });

    // 保存token和用户信息
    localStorage.setItem('token', response.token);
    localStorage.setItem('userInfo', JSON.stringify({
      username: response.username,
      realName: response.realName,
      role: response.role,
      avatar: response.avatar
    }));

    // 如果记住密码，保存账号密码
    if (rememberMe.value) {
      localStorage.setItem('rememberedAccount', JSON.stringify({
        studentId: studentId.value,
        password: password.value
      }));
    } else {
      localStorage.removeItem('rememberedAccount');
    }

    console.log('登录成功');
    // 跳转到首页
    router.push('/dashboard');
  } catch (error) {
    errorMessage.value = error.message || '登录失败，请重试';
  } finally {
    isLoading.value = false;
  }
};

// 页面加载时检查是否有记住的账号密码
const checkRememberedAccount = () => {
  const rememberedAccount = localStorage.getItem('rememberedAccount');
  if (rememberedAccount) {
    const { studentId: savedStudentId, password: savedPassword } = JSON.parse(rememberedAccount);
    studentId.value = savedStudentId;
    password.value = savedPassword;
    rememberMe.value = true;
  }
};

// 在组件挂载时检查记住的账号密码
checkRememberedAccount();
</script>

<style scoped>
/* 自定义样式 */
.login__form-input:focus {
  outline: none;
  box-shadow: 0 0 0 2px rgba(59, 130, 246, 0.2);
}

/* 确保输入框有正确的样式 */
.form-input {
  display: block;
  width: 100%;
  padding: 12px 40px 12px 40px;
  border: 1px solid #d1d5db;
  border-radius: 6px;
  background-color: white;
  font-size: 14px;
  color: #374151;
  transition: all 0.15s ease-in-out;
}

.form-input:focus {
  outline: none;
  border-color: #3b82f6;
  box-shadow: 0 0 0 3px rgba(59, 130, 246, 0.1);
}

.form-input.error {
  border-color: #ef4444;
}

.form-input.error:focus {
  border-color: #ef4444;
  box-shadow: 0 0 0 3px rgba(239, 68, 68, 0.1);
}

/* 移除number类型输入框的上下箭头 */
input[type="number"]::-webkit-inner-spin-button,
input[type="number"]::-webkit-outer-spin-button {
  -webkit-appearance: none;
  margin: 0;
}
input[type="number"] {
  -moz-appearance: textfield;
}
</style>
