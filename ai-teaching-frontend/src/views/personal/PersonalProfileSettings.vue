<template>
  <div>
    <!-- 面包屑导航 -->
    <div class="mb-6 flex items-center text-sm text-gray-500">
      <span class="cursor-pointer hover:text-blue-600" @click="$router.push('/dashboard')">首页</span>
      <i class="fas fa-chevron-right mx-2 text-xs"></i>
      <span class="cursor-pointer hover:text-blue-600">个人设置</span>
      <i class="fas fa-chevron-right mx-2 text-xs"></i>
      <span class="text-gray-700">基本信息</span>
    </div>

    <!-- 页面标题 -->
    <div class="mb-6">
      <h1 class="text-2xl font-bold text-gray-900">基本信息</h1>
      <p class="text-gray-600 mt-1">管理您的个人资料和基本信息</p>
    </div>

    <div class="grid grid-cols-1 lg:grid-cols-3 gap-6">
      <!-- 头像设置 -->
      <div class="lg:col-span-1">
        <div class="bg-white rounded-lg shadow-sm border border-gray-200 p-6">
          <h2 class="text-lg font-semibold text-gray-900 mb-4">头像设置</h2>
          
          <div class="flex flex-col items-center">
            <div class="relative">
              <div class="w-24 h-24 rounded-full bg-gradient-to-r from-blue-500 to-indigo-600 flex items-center justify-center text-white font-bold text-3xl">
                {{ userInfo.realName ? userInfo.realName.charAt(0) : 'U' }}
              </div>
              <button class="absolute bottom-0 right-0 w-8 h-8 bg-blue-600 rounded-full flex items-center justify-center text-white hover:bg-blue-700 transition-colors">
                <i class="fas fa-camera text-sm"></i>
              </button>
            </div>
            
            <h3 class="mt-4 text-lg font-medium text-gray-900">{{ userInfo.realName || '用户' }}</h3>
            <p class="text-sm text-gray-500">@{{ userInfo.username || 'username' }}</p>
            
            <div class="mt-4 space-y-2 w-full">
              <button class="w-full px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition-colors text-sm">
                上传新头像
              </button>
              <button class="w-full px-4 py-2 bg-gray-100 text-gray-700 rounded-lg hover:bg-gray-200 transition-colors text-sm">
                删除头像
              </button>
            </div>
          </div>
        </div>

        <!-- 快速统计 -->
        <div class="bg-white rounded-lg shadow-sm border border-gray-200 p-6 mt-6">
          <h2 class="text-lg font-semibold text-gray-900 mb-4">账户统计</h2>
          <div class="space-y-3">
            <div class="flex justify-between">
              <span class="text-sm text-gray-600">注册时间</span>
              <span class="text-sm font-medium text-gray-900">{{ userInfo.createTime || '2024-01-01' }}</span>
            </div>
            <div class="flex justify-between">
              <span class="text-sm text-gray-600">最后登录</span>
              <span class="text-sm font-medium text-gray-900">{{ userInfo.lastLoginTime || '今天' }}</span>
            </div>
            <div class="flex justify-between">
              <span class="text-sm text-gray-600">登录次数</span>
              <span class="text-sm font-medium text-gray-900">142 次</span>
            </div>
          </div>
        </div>
      </div>

      <!-- 基本信息表单 -->
      <div class="lg:col-span-2">
        <div class="bg-white rounded-lg shadow-sm border border-gray-200 p-6">
          <h2 class="text-lg font-semibold text-gray-900 mb-6">基本信息</h2>
          
          <form @submit.prevent="updateProfile">
            <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
              <div>
                <label class="block text-sm font-medium text-gray-700 mb-2">真实姓名 *</label>
                <input 
                  v-model="profileForm.realName"
                  type="text" 
                  class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                  required
                />
              </div>
              
              <div>
                <label class="block text-sm font-medium text-gray-700 mb-2">用户名 *</label>
                <input 
                  v-model="profileForm.username"
                  type="text" 
                  class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                  required
                />
              </div>
              
              <div>
                <label class="block text-sm font-medium text-gray-700 mb-2">邮箱地址</label>
                <input 
                  v-model="profileForm.email"
                  type="email" 
                  class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                />
              </div>
              
              <div>
                <label class="block text-sm font-medium text-gray-700 mb-2">手机号码</label>
                <input 
                  v-model="profileForm.phone"
                  type="tel" 
                  class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                />
              </div>
              
              <div>
                <label class="block text-sm font-medium text-gray-700 mb-2">性别</label>
                <select 
                  v-model="profileForm.gender"
                  class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                >
                  <option value="">请选择</option>
                  <option value="male">男</option>
                  <option value="female">女</option>
                  <option value="other">其他</option>
                </select>
              </div>
              
              <div>
                <label class="block text-sm font-medium text-gray-700 mb-2">出生日期</label>
                <input 
                  v-model="profileForm.birthDate"
                  type="date" 
                  class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                />
              </div>
              
              <div>
                <label class="block text-sm font-medium text-gray-700 mb-2">职业</label>
                <input 
                  v-model="profileForm.occupation"
                  type="text" 
                  class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                  placeholder="如：教师、学生、软件工程师"
                />
              </div>
              
              <div>
                <label class="block text-sm font-medium text-gray-700 mb-2">所在地区</label>
                <input 
                  v-model="profileForm.location"
                  type="text" 
                  class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                  placeholder="如：北京市朝阳区"
                />
              </div>
            </div>
            
            <div class="mt-6">
              <label class="block text-sm font-medium text-gray-700 mb-2">个人简介</label>
              <textarea 
                v-model="profileForm.bio"
                rows="4"
                class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                placeholder="简单介绍一下自己..."
              ></textarea>
            </div>
            
            <div class="mt-8 flex justify-end space-x-4">
              <button 
                type="button"
                @click="resetForm"
                class="px-6 py-2 bg-gray-100 text-gray-700 rounded-lg hover:bg-gray-200 transition-colors"
              >
                重置
              </button>
              <button 
                type="submit"
                class="px-6 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition-colors"
              >
                保存更改
              </button>
            </div>
          </form>
        </div>

        <!-- 社交媒体账号 -->
        <div class="bg-white rounded-lg shadow-sm border border-gray-200 p-6 mt-6">
          <h2 class="text-lg font-semibold text-gray-900 mb-6">社交媒体</h2>
          
          <div class="space-y-4">
            <div class="flex items-center space-x-4">
              <div class="w-10 h-10 bg-blue-100 rounded-lg flex items-center justify-center">
                <i class="fab fa-weibo text-blue-600"></i>
              </div>
              <div class="flex-1">
                <input 
                  v-model="profileForm.weibo"
                  type="text" 
                  placeholder="微博账号"
                  class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                />
              </div>
            </div>
            
            <div class="flex items-center space-x-4">
              <div class="w-10 h-10 bg-green-100 rounded-lg flex items-center justify-center">
                <i class="fab fa-weixin text-green-600"></i>
              </div>
              <div class="flex-1">
                <input 
                  v-model="profileForm.wechat"
                  type="text" 
                  placeholder="微信号"
                  class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                />
              </div>
            </div>
            
            <div class="flex items-center space-x-4">
              <div class="w-10 h-10 bg-gray-100 rounded-lg flex items-center justify-center">
                <i class="fab fa-github text-gray-600"></i>
              </div>
              <div class="flex-1">
                <input 
                  v-model="profileForm.github"
                  type="text" 
                  placeholder="GitHub 用户名"
                  class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                />
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue';

// 用户信息
const userInfo = reactive(JSON.parse(localStorage.getItem('userInfo') || '{}'));

// 表单数据
const profileForm = reactive({
  realName: '',
  username: '',
  email: '',
  phone: '',
  gender: '',
  birthDate: '',
  occupation: '',
  location: '',
  bio: '',
  weibo: '',
  wechat: '',
  github: ''
});

// 方法
const updateProfile = () => {
  console.log('更新个人资料', profileForm);
  // 这里应该调用API更新个人资料
  alert('个人资料更新成功！');
};

const resetForm = () => {
  Object.assign(profileForm, {
    realName: userInfo.realName || '',
    username: userInfo.username || '',
    email: userInfo.email || '',
    phone: userInfo.phone || '',
    gender: '',
    birthDate: '',
    occupation: '',
    location: '',
    bio: '',
    weibo: '',
    wechat: '',
    github: ''
  });
};

// 初始化表单数据
onMounted(() => {
  resetForm();
});
</script> 