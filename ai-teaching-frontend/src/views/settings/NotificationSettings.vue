<template>
  <div>
    <!-- 面包屑导航 -->
    <div class="mb-6 flex items-center text-sm text-gray-500">
      <span class="cursor-pointer hover:text-blue-600" @click="$router.push('/dashboard')">首页</span>
      <i class="fas fa-chevron-right mx-2 text-xs"></i>
      <span class="cursor-pointer hover:text-blue-600">个人设置</span>
      <i class="fas fa-chevron-right mx-2 text-xs"></i>
      <span class="text-gray-700">通知设置</span>
    </div>

    <!-- 页面标题 -->
    <div class="mb-6">
      <h1 class="text-2xl font-bold text-gray-900">通知设置</h1>
      <p class="text-gray-600 mt-1">管理您希望接收的通知类型和频率</p>
    </div>

    <div class="space-y-6">
      <!-- 通知方式设置 -->
      <div class="bg-white rounded-lg shadow-sm border border-gray-200 p-6">
        <h2 class="text-lg font-semibold text-gray-900 mb-6">通知方式</h2>
        
        <div class="grid grid-cols-1 md:grid-cols-3 gap-6">
          <!-- 站内通知 -->
          <div class="text-center p-4 border border-gray-200 rounded-lg">
            <div class="w-12 h-12 bg-blue-100 rounded-lg flex items-center justify-center mx-auto mb-3">
              <i class="fas fa-bell text-blue-600 text-xl"></i>
            </div>
            <h3 class="font-medium text-gray-900 mb-2">站内通知</h3>
            <p class="text-sm text-gray-600 mb-4">在网站内显示消息提醒</p>
            <div class="flex items-center justify-center">
              <label class="relative inline-flex items-center cursor-pointer">
                <input 
                  v-model="notificationMethods.inApp" 
                  type="checkbox" 
                  class="sr-only peer"
                />
                <div class="w-11 h-6 bg-gray-200 peer-focus:outline-none peer-focus:ring-4 peer-focus:ring-blue-300 rounded-full peer peer-checked:after:translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:left-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-5 after:w-5 after:transition-all peer-checked:bg-blue-600"></div>
              </label>
            </div>
          </div>

          <!-- 邮件通知 -->
          <div class="text-center p-4 border border-gray-200 rounded-lg">
            <div class="w-12 h-12 bg-green-100 rounded-lg flex items-center justify-center mx-auto mb-3">
              <i class="fas fa-envelope text-green-600 text-xl"></i>
            </div>
            <h3 class="font-medium text-gray-900 mb-2">邮件通知</h3>
            <p class="text-sm text-gray-600 mb-4">发送通知到您的邮箱</p>
            <div class="flex items-center justify-center">
              <label class="relative inline-flex items-center cursor-pointer">
                <input 
                  v-model="notificationMethods.email" 
                  type="checkbox" 
                  class="sr-only peer"
                />
                <div class="w-11 h-6 bg-gray-200 peer-focus:outline-none peer-focus:ring-4 peer-focus:ring-blue-300 rounded-full peer peer-checked:after:translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:left-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-5 after:w-5 after:transition-all peer-checked:bg-blue-600"></div>
              </label>
            </div>
          </div>

          <!-- 短信通知 -->
          <div class="text-center p-4 border border-gray-200 rounded-lg">
            <div class="w-12 h-12 bg-purple-100 rounded-lg flex items-center justify-center mx-auto mb-3">
              <i class="fas fa-mobile-alt text-purple-600 text-xl"></i>
            </div>
            <h3 class="font-medium text-gray-900 mb-2">短信通知</h3>
            <p class="text-sm text-gray-600 mb-4">发送通知到您的手机</p>
            <div class="flex items-center justify-center">
              <label class="relative inline-flex items-center cursor-pointer">
                <input 
                  v-model="notificationMethods.sms" 
                  type="checkbox" 
                  class="sr-only peer"
                />
                <div class="w-11 h-6 bg-gray-200 peer-focus:outline-none peer-focus:ring-4 peer-focus:ring-blue-300 rounded-full peer peer-checked:after:translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:left-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-5 after:w-5 after:transition-all peer-checked:bg-blue-600"></div>
              </label>
            </div>
          </div>
        </div>
      </div>

      <!-- 通知类型设置 -->
      <div class="bg-white rounded-lg shadow-sm border border-gray-200 p-6">
        <h2 class="text-lg font-semibold text-gray-900 mb-6">通知类型</h2>
        
        <div class="space-y-6">
          <!-- 系统通知 -->
          <div>
            <h3 class="text-base font-medium text-gray-900 mb-4 flex items-center">
              <i class="fas fa-cog text-blue-600 mr-2"></i>
              系统通知
            </h3>
            <div class="space-y-3 ml-6">
              <div class="flex items-center justify-between py-2">
                <div>
                  <p class="font-medium text-gray-900">系统维护</p>
                  <p class="text-sm text-gray-600">系统升级、维护等重要消息</p>
                </div>
                <label class="relative inline-flex items-center cursor-pointer">
                  <input v-model="notificationTypes.systemMaintenance" type="checkbox" class="sr-only peer">
                  <div class="w-11 h-6 bg-gray-200 peer-focus:outline-none peer-focus:ring-4 peer-focus:ring-blue-300 rounded-full peer peer-checked:after:translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:left-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-5 after:w-5 after:transition-all peer-checked:bg-blue-600"></div>
                </label>
              </div>
              
              <div class="flex items-center justify-between py-2">
                <div>
                  <p class="font-medium text-gray-900">功能更新</p>
                  <p class="text-sm text-gray-600">新功能发布、产品更新通知</p>
                </div>
                <label class="relative inline-flex items-center cursor-pointer">
                  <input v-model="notificationTypes.featureUpdates" type="checkbox" class="sr-only peer">
                  <div class="w-11 h-6 bg-gray-200 peer-focus:outline-none peer-focus:ring-4 peer-focus:ring-blue-300 rounded-full peer peer-checked:after:translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:left-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-5 after:w-5 after:transition-all peer-checked:bg-blue-600"></div>
                </label>
              </div>
              
              <div class="flex items-center justify-between py-2">
                <div>
                  <p class="font-medium text-gray-900">安全提醒</p>
                  <p class="text-sm text-gray-600">异常登录、密码修改等安全消息</p>
                </div>
                <label class="relative inline-flex items-center cursor-pointer">
                  <input v-model="notificationTypes.security" type="checkbox" class="sr-only peer">
                  <div class="w-11 h-6 bg-gray-200 peer-focus:outline-none peer-focus:ring-4 peer-focus:ring-blue-300 rounded-full peer peer-checked:after:translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:left-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-5 after:w-5 after:transition-all peer-checked:bg-blue-600"></div>
                </label>
              </div>
            </div>
          </div>

          <!-- 教学通知 -->
          <div>
            <h3 class="text-base font-medium text-gray-900 mb-4 flex items-center">
              <i class="fas fa-graduation-cap text-green-600 mr-2"></i>
              教学通知
            </h3>
            <div class="space-y-3 ml-6">
              <div class="flex items-center justify-between py-2">
                <div>
                  <p class="font-medium text-gray-900">作业提醒</p>
                  <p class="text-sm text-gray-600">作业发布、截止时间提醒</p>
                </div>
                <label class="relative inline-flex items-center cursor-pointer">
                  <input v-model="notificationTypes.homework" type="checkbox" class="sr-only peer">
                  <div class="w-11 h-6 bg-gray-200 peer-focus:outline-none peer-focus:ring-4 peer-focus:ring-blue-300 rounded-full peer peer-checked:after:translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:left-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-5 after:w-5 after:transition-all peer-checked:bg-blue-600"></div>
                </label>
              </div>
              
              <div class="flex items-center justify-between py-2">
                <div>
                  <p class="font-medium text-gray-900">课程更新</p>
                  <p class="text-sm text-gray-600">课程内容更新、新课程发布</p>
                </div>
                <label class="relative inline-flex items-center cursor-pointer">
                  <input v-model="notificationTypes.courseUpdates" type="checkbox" class="sr-only peer">
                  <div class="w-11 h-6 bg-gray-200 peer-focus:outline-none peer-focus:ring-4 peer-focus:ring-blue-300 rounded-full peer peer-checked:after:translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:left-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-5 after:w-5 after:transition-all peer-checked:bg-blue-600"></div>
                </label>
              </div>
              
              <div class="flex items-center justify-between py-2">
                <div>
                  <p class="font-medium text-gray-900">成绩公布</p>
                  <p class="text-sm text-gray-600">考试成绩、作业评分公布</p>
                </div>
                <label class="relative inline-flex items-center cursor-pointer">
                  <input v-model="notificationTypes.grades" type="checkbox" class="sr-only peer">
                  <div class="w-11 h-6 bg-gray-200 peer-focus:outline-none peer-focus:ring-4 peer-focus:ring-blue-300 rounded-full peer peer-checked:after:translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:left-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-5 after:w-5 after:transition-all peer-checked:bg-blue-600"></div>
                </label>
              </div>
              
              <div class="flex items-center justify-between py-2">
                <div>
                  <p class="font-medium text-gray-900">考试通知</p>
                  <p class="text-sm text-gray-600">考试安排、考试时间变更</p>
                </div>
                <label class="relative inline-flex items-center cursor-pointer">
                  <input v-model="notificationTypes.exams" type="checkbox" class="sr-only peer">
                  <div class="w-11 h-6 bg-gray-200 peer-focus:outline-none peer-focus:ring-4 peer-focus:ring-blue-300 rounded-full peer peer-checked:after:translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:left-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-5 after:w-5 after:transition-all peer-checked:bg-blue-600"></div>
                </label>
              </div>
            </div>
          </div>

          <!-- AI助手通知 -->
          <div>
            <h3 class="text-base font-medium text-gray-900 mb-4 flex items-center">
              <i class="fas fa-robot text-purple-600 mr-2"></i>
              AI助手通知
            </h3>
            <div class="space-y-3 ml-6">
              <div class="flex items-center justify-between py-2">
                <div>
                  <p class="font-medium text-gray-900">智能推荐</p>
                  <p class="text-sm text-gray-600">学习计划推荐、课程推荐</p>
                </div>
                <label class="relative inline-flex items-center cursor-pointer">
                  <input v-model="notificationTypes.aiRecommendations" type="checkbox" class="sr-only peer">
                  <div class="w-11 h-6 bg-gray-200 peer-focus:outline-none peer-focus:ring-4 peer-focus:ring-blue-300 rounded-full peer peer-checked:after:translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:left-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-5 after:w-5 after:transition-all peer-checked:bg-blue-600"></div>
                </label>
              </div>
              
              <div class="flex items-center justify-between py-2">
                <div>
                  <p class="font-medium text-gray-900">学习提醒</p>
                  <p class="text-sm text-gray-600">学习进度提醒、复习建议</p>
                </div>
                <label class="relative inline-flex items-center cursor-pointer">
                  <input v-model="notificationTypes.studyReminders" type="checkbox" class="sr-only peer">
                  <div class="w-11 h-6 bg-gray-200 peer-focus:outline-none peer-focus:ring-4 peer-focus:ring-blue-300 rounded-full peer peer-checked:after:translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:left-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-5 after:w-5 after:transition-all peer-checked:bg-blue-600"></div>
                </label>
              </div>
              
              <div class="flex items-center justify-between py-2">
                <div>
                  <p class="font-medium text-gray-900">批改完成</p>
                  <p class="text-sm text-gray-600">AI智能批改完成通知</p>
                </div>
                <label class="relative inline-flex items-center cursor-pointer">
                  <input v-model="notificationTypes.aiGrading" type="checkbox" class="sr-only peer">
                  <div class="w-11 h-6 bg-gray-200 peer-focus:outline-none peer-focus:ring-4 peer-focus:ring-blue-300 rounded-full peer peer-checked:after:translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:left-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-5 after:w-5 after:transition-all peer-checked:bg-blue-600"></div>
                </label>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- 通知时间设置 -->
      <div class="bg-white rounded-lg shadow-sm border border-gray-200 p-6">
        <h2 class="text-lg font-semibold text-gray-900 mb-6">通知时间</h2>
        
        <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
          <div>
            <label class="block text-sm font-medium text-gray-700 mb-2">免打扰时间段</label>
            <div class="flex items-center space-x-2">
              <input 
                v-model="quietHours.start"
                type="time" 
                class="px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
              />
              <span class="text-gray-500">至</span>
              <input 
                v-model="quietHours.end"
                type="time" 
                class="px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
              />
            </div>
            <p class="text-xs text-gray-500 mt-1">在此时间段内不会收到通知</p>
          </div>
          
          <div>
            <label class="block text-sm font-medium text-gray-700 mb-2">通知频率</label>
            <select 
              v-model="notificationFrequency"
              class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
            >
              <option value="immediate">立即通知</option>
              <option value="hourly">每小时汇总</option>
              <option value="daily">每日汇总</option>
              <option value="weekly">每周汇总</option>
            </select>
            <p class="text-xs text-gray-500 mt-1">选择接收通知的频率</p>
          </div>
        </div>

        <div class="mt-6">
          <div class="flex items-center">
            <input 
              v-model="weekendNotifications" 
              type="checkbox" 
              id="weekend-notifications"
              class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 rounded focus:ring-blue-500"
            />
            <label for="weekend-notifications" class="ml-2 text-sm font-medium text-gray-900">
              周末接收通知
            </label>
          </div>
          <p class="text-xs text-gray-500 mt-1 ml-6">在周末时间也接收通知消息</p>
        </div>
      </div>

      <!-- 通知预览 -->
      <div class="bg-white rounded-lg shadow-sm border border-gray-200 p-6">
        <div class="flex items-center justify-between mb-6">
          <div>
            <h2 class="text-lg font-semibold text-gray-900">通知预览</h2>
            <p class="text-sm text-gray-600 mt-1">查看您将收到的通知样式</p>
          </div>
          <button 
            @click="sendTestNotification"
            class="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition-colors text-sm"
          >
            发送测试通知
          </button>
        </div>

        <div class="space-y-3">
          <div class="border border-gray-200 rounded-lg p-4">
            <div class="flex items-start">
              <div class="w-8 h-8 bg-blue-100 rounded-lg flex items-center justify-center mr-3 flex-shrink-0">
                <i class="fas fa-bell text-blue-600 text-sm"></i>
              </div>
              <div class="flex-1">
                <div class="flex items-center justify-between">
                  <h4 class="font-medium text-gray-900">系统通知</h4>
                  <span class="text-xs text-gray-500">2分钟前</span>
                </div>
                <p class="text-sm text-gray-600 mt-1">您有一个新的作业需要完成，截止时间为明天18:00</p>
              </div>
            </div>
          </div>

          <div class="border border-gray-200 rounded-lg p-4">
            <div class="flex items-start">
              <div class="w-8 h-8 bg-green-100 rounded-lg flex items-center justify-center mr-3 flex-shrink-0">
                <i class="fas fa-graduation-cap text-green-600 text-sm"></i>
              </div>
              <div class="flex-1">
                <div class="flex items-center justify-between">
                  <h4 class="font-medium text-gray-900">教学通知</h4>
                  <span class="text-xs text-gray-500">1小时前</span>
                </div>
                <p class="text-sm text-gray-600 mt-1">《高等数学》课程有新的资料更新，请及时查看</p>
              </div>
            </div>
          </div>

          <div class="border border-gray-200 rounded-lg p-4">
            <div class="flex items-start">
              <div class="w-8 h-8 bg-purple-100 rounded-lg flex items-center justify-center mr-3 flex-shrink-0">
                <i class="fas fa-robot text-purple-600 text-sm"></i>
              </div>
              <div class="flex-1">
                <div class="flex items-center justify-between">
                  <h4 class="font-medium text-gray-900">AI助手</h4>
                  <span class="text-xs text-gray-500">3小时前</span>
                </div>
                <p class="text-sm text-gray-600 mt-1">您的作业已完成AI智能批改，点击查看详细反馈</p>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- 保存按钮 -->
      <div class="flex justify-end space-x-4">
        <button 
          @click="resetSettings"
          class="px-6 py-2 bg-gray-100 text-gray-700 rounded-lg hover:bg-gray-200 transition-colors"
        >
          重置
        </button>
        <button 
          @click="saveSettings"
          class="px-6 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition-colors"
        >
          保存设置
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive } from 'vue';

// 通知方式设置
const notificationMethods = reactive({
  inApp: true,
  email: true,
  sms: false
});

// 通知类型设置
const notificationTypes = reactive({
  // 系统通知
  systemMaintenance: true,
  featureUpdates: true,
  security: true,
  
  // 教学通知
  homework: true,
  courseUpdates: true,
  grades: true,
  exams: true,
  
  // AI助手通知
  aiRecommendations: false,
  studyReminders: true,
  aiGrading: true
});

// 时间设置
const quietHours = reactive({
  start: '23:00',
  end: '07:00'
});

const notificationFrequency = ref('immediate');
const weekendNotifications = ref(false);

// 方法
const saveSettings = () => {
  const settings = {
    methods: notificationMethods,
    types: notificationTypes,
    quietHours: quietHours,
    frequency: notificationFrequency.value,
    weekendNotifications: weekendNotifications.value
  };
  
  console.log('保存通知设置', settings);
  // 这里应该调用API保存设置
  alert('通知设置保存成功！');
};

const resetSettings = () => {
  // 重置为默认设置
  Object.assign(notificationMethods, {
    inApp: true,
    email: true,
    sms: false
  });
  
  Object.assign(notificationTypes, {
    systemMaintenance: true,
    featureUpdates: true,
    security: true,
    homework: true,
    courseUpdates: true,
    grades: true,
    exams: true,
    aiRecommendations: false,
    studyReminders: true,
    aiGrading: true
  });
  
  Object.assign(quietHours, {
    start: '23:00',
    end: '07:00'
  });
  
  notificationFrequency.value = 'immediate';
  weekendNotifications.value = false;
  
  console.log('重置通知设置');
};

const sendTestNotification = () => {
  console.log('发送测试通知');
  // 这里应该调用API发送测试通知
  alert('测试通知已发送！请检查您的通知中心');
};
</script> 