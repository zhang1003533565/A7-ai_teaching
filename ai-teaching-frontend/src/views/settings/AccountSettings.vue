<template>
  <div>
    <!-- 面包屑导航 -->
    <div class="mb-6 flex items-center text-sm text-gray-500">
      <span class="cursor-pointer hover:text-blue-600" @click="$router.push('/dashboard')">首页</span>
      <i class="fas fa-chevron-right mx-2 text-xs"></i>
      <span class="cursor-pointer hover:text-blue-600">个人设置</span>
      <i class="fas fa-chevron-right mx-2 text-xs"></i>
      <span class="text-gray-700">账户管理</span>
    </div>

    <!-- 页面标题 -->
    <div class="mb-6">
      <h1 class="text-2xl font-bold text-gray-900">账户管理</h1>
      <p class="text-gray-600 mt-1">管理您的账户信息、第三方绑定和账户安全</p>
    </div>

    <div class="space-y-6">
      <!-- 账户信息 -->
      <div class="bg-white rounded-lg shadow-sm border border-gray-200 p-6">
        <h2 class="text-lg font-semibold text-gray-900 mb-6">账户信息</h2>
        
        <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
          <div class="space-y-4">
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">账户ID</label>
              <div class="flex items-center">
                <span class="text-gray-900 font-mono">{{ accountInfo.id }}</span>
                <button 
                  @click="copyToClipboard(accountInfo.id)"
                  class="ml-2 text-gray-400 hover:text-blue-600 transition-colors"
                  title="复制账户ID"
                >
                  <i class="fas fa-copy"></i>
                </button>
              </div>
            </div>
            
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">注册时间</label>
              <span class="text-gray-900">{{ accountInfo.createTime }}</span>
            </div>
            
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">账户状态</label>
              <span class="px-2 py-1 text-xs rounded-full bg-green-100 text-green-800">
                {{ accountInfo.status }}
              </span>
            </div>
          </div>
          
          <div class="space-y-4">
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">最后登录</label>
              <span class="text-gray-900">{{ accountInfo.lastLogin }}</span>
            </div>
            
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">登录IP</label>
              <span class="text-gray-900 font-mono">{{ accountInfo.lastLoginIp }}</span>
            </div>
            
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">账户等级</label>
              <div class="flex items-center">
                <span class="px-2 py-1 text-xs rounded-full bg-blue-100 text-blue-800 mr-2">
                  {{ accountInfo.level }}
                </span>
                <span class="text-sm text-gray-600">{{ accountInfo.levelDescription }}</span>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- 第三方账户绑定 -->
      <div class="bg-white rounded-lg shadow-sm border border-gray-200 p-6">
        <h2 class="text-lg font-semibold text-gray-900 mb-6">第三方账户绑定</h2>
        
        <div class="space-y-4">
          <!-- 微信绑定 -->
          <div class="flex items-center justify-between p-4 border border-gray-200 rounded-lg">
            <div class="flex items-center">
              <div class="w-10 h-10 bg-green-100 rounded-lg flex items-center justify-center mr-4">
                <i class="fab fa-weixin text-green-600 text-xl"></i>
              </div>
              <div>
                <h3 class="font-medium text-gray-900">微信</h3>
                <p class="text-sm text-gray-600">
                  {{ bindings.wechat ? '已绑定 ' + bindings.wechat : '未绑定' }}
                </p>
              </div>
            </div>
            <button 
              @click="bindings.wechat ? unbindWechat() : bindWechat()"
              class="px-4 py-2 text-sm rounded-lg transition-colors"
              :class="bindings.wechat ? 'bg-red-100 text-red-700 hover:bg-red-200' : 'bg-green-100 text-green-700 hover:bg-green-200'"
            >
              {{ bindings.wechat ? '解除绑定' : '绑定' }}
            </button>
          </div>

          <!-- QQ绑定 -->
          <div class="flex items-center justify-between p-4 border border-gray-200 rounded-lg">
            <div class="flex items-center">
              <div class="w-10 h-10 bg-blue-100 rounded-lg flex items-center justify-center mr-4">
                <i class="fab fa-qq text-blue-600 text-xl"></i>
              </div>
              <div>
                <h3 class="font-medium text-gray-900">QQ</h3>
                <p class="text-sm text-gray-600">
                  {{ bindings.qq ? '已绑定 ' + bindings.qq : '未绑定' }}
                </p>
              </div>
            </div>
            <button 
              @click="bindings.qq ? unbindQQ() : bindQQ()"
              class="px-4 py-2 text-sm rounded-lg transition-colors"
              :class="bindings.qq ? 'bg-red-100 text-red-700 hover:bg-red-200' : 'bg-blue-100 text-blue-700 hover:bg-blue-200'"
            >
              {{ bindings.qq ? '解除绑定' : '绑定' }}
            </button>
          </div>

          <!-- 新浪微博绑定 -->
          <div class="flex items-center justify-between p-4 border border-gray-200 rounded-lg">
            <div class="flex items-center">
              <div class="w-10 h-10 bg-red-100 rounded-lg flex items-center justify-center mr-4">
                <i class="fab fa-weibo text-red-600 text-xl"></i>
              </div>
              <div>
                <h3 class="font-medium text-gray-900">新浪微博</h3>
                <p class="text-sm text-gray-600">
                  {{ bindings.weibo ? '已绑定 @' + bindings.weibo : '未绑定' }}
                </p>
              </div>
            </div>
            <button 
              @click="bindings.weibo ? unbindWeibo() : bindWeibo()"
              class="px-4 py-2 text-sm rounded-lg transition-colors"
              :class="bindings.weibo ? 'bg-red-100 text-red-700 hover:bg-red-200' : 'bg-red-100 text-red-700 hover:bg-red-200'"
            >
              {{ bindings.weibo ? '解除绑定' : '绑定' }}
            </button>
          </div>

          <!-- GitHub绑定 -->
          <div class="flex items-center justify-between p-4 border border-gray-200 rounded-lg">
            <div class="flex items-center">
              <div class="w-10 h-10 bg-gray-100 rounded-lg flex items-center justify-center mr-4">
                <i class="fab fa-github text-gray-600 text-xl"></i>
              </div>
              <div>
                <h3 class="font-medium text-gray-900">GitHub</h3>
                <p class="text-sm text-gray-600">
                  {{ bindings.github ? '已绑定 @' + bindings.github : '未绑定' }}
                </p>
              </div>
            </div>
            <button 
              @click="bindings.github ? unbindGithub() : bindGithub()"
              class="px-4 py-2 text-sm rounded-lg transition-colors"
              :class="bindings.github ? 'bg-red-100 text-red-700 hover:bg-red-200' : 'bg-gray-100 text-gray-700 hover:bg-gray-200'"
            >
              {{ bindings.github ? '解除绑定' : '绑定' }}
            </button>
          </div>
        </div>

        <div class="mt-6 p-4 bg-blue-50 border border-blue-200 rounded-lg">
          <div class="flex items-start">
            <i class="fas fa-info-circle text-blue-600 mr-2 mt-0.5"></i>
            <div class="text-sm">
              <p class="text-blue-900 font-medium mb-1">绑定说明</p>
              <ul class="text-blue-800 space-y-1">
                <li>• 绑定第三方账户后，可以使用对应平台快捷登录</li>
                <li>• 解除绑定前请确保已设置密码，避免无法正常登录</li>
                <li>• 建议至少保留一种登录方式</li>
              </ul>
            </div>
          </div>
        </div>
      </div>

      <!-- 数据和隐私 -->
      <div class="bg-white rounded-lg shadow-sm border border-gray-200 p-6">
        <h2 class="text-lg font-semibold text-gray-900 mb-6">数据和隐私</h2>
        
        <div class="space-y-4">
          <div class="flex items-center justify-between p-4 border border-gray-200 rounded-lg">
            <div class="flex items-center">
              <div class="w-10 h-10 bg-blue-100 rounded-lg flex items-center justify-center mr-4">
                <i class="fas fa-download text-blue-600"></i>
              </div>
              <div>
                <h3 class="font-medium text-gray-900">导出个人数据</h3>
                <p class="text-sm text-gray-600">下载您在平台上的所有个人数据</p>
              </div>
            </div>
            <button 
              @click="exportPersonalData"
              class="px-4 py-2 text-sm bg-blue-100 text-blue-700 rounded-lg hover:bg-blue-200 transition-colors"
            >
              申请导出
            </button>
          </div>

          <div class="flex items-center justify-between p-4 border border-gray-200 rounded-lg">
            <div class="flex items-center">
              <div class="w-10 h-10 bg-green-100 rounded-lg flex items-center justify-center mr-4">
                <i class="fas fa-history text-green-600"></i>
              </div>
              <div>
                <h3 class="font-medium text-gray-900">活动历史</h3>
                <p class="text-sm text-gray-600">查看和管理您的活动记录</p>
              </div>
            </div>
            <button 
              @click="viewActivityHistory"
              class="px-4 py-2 text-sm bg-green-100 text-green-700 rounded-lg hover:bg-green-200 transition-colors"
            >
              查看历史
            </button>
          </div>

          <div class="flex items-center justify-between p-4 border border-gray-200 rounded-lg">
            <div class="flex items-center">
              <div class="w-10 h-10 bg-purple-100 rounded-lg flex items-center justify-center mr-4">
                <i class="fas fa-shield-alt text-purple-600"></i>
              </div>
              <div>
                <h3 class="font-medium text-gray-900">隐私设置</h3>
                <p class="text-sm text-gray-600">管理个人信息的可见性和共享设置</p>
              </div>
            </div>
            <button 
              @click="openPrivacySettings"
              class="px-4 py-2 text-sm bg-purple-100 text-purple-700 rounded-lg hover:bg-purple-200 transition-colors"
            >
              隐私设置
            </button>
          </div>
        </div>
      </div>

      <!-- 账户操作 -->
      <div class="bg-white rounded-lg shadow-sm border border-gray-200 p-6">
        <h2 class="text-lg font-semibold text-gray-900 mb-6">账户操作</h2>
        
        <div class="space-y-4">
          <div class="flex items-center justify-between p-4 border border-yellow-200 bg-yellow-50 rounded-lg">
            <div class="flex items-center">
              <div class="w-10 h-10 bg-yellow-100 rounded-lg flex items-center justify-center mr-4">
                <i class="fas fa-pause text-yellow-600"></i>
              </div>
              <div>
                <h3 class="font-medium text-gray-900">暂停账户</h3>
                <p class="text-sm text-gray-600">临时禁用账户，可随时恢复</p>
              </div>
            </div>
            <button 
              @click="suspendAccount"
              class="px-4 py-2 text-sm bg-yellow-600 text-white rounded-lg hover:bg-yellow-700 transition-colors"
            >
              暂停账户
            </button>
          </div>

          <div class="flex items-center justify-between p-4 border border-red-200 bg-red-50 rounded-lg">
            <div class="flex items-center">
              <div class="w-10 h-10 bg-red-100 rounded-lg flex items-center justify-center mr-4">
                <i class="fas fa-user-times text-red-600"></i>
              </div>
              <div>
                <h3 class="font-medium text-gray-900">注销账户</h3>
                <p class="text-sm text-gray-600">永久删除账户和所有相关数据</p>
              </div>
            </div>
            <button 
              @click="deleteAccount"
              class="px-4 py-2 text-sm bg-red-600 text-white rounded-lg hover:bg-red-700 transition-colors"
            >
              注销账户
            </button>
          </div>
        </div>

        <div class="mt-6 p-4 bg-amber-50 border border-amber-200 rounded-lg">
          <div class="flex items-start">
            <i class="fas fa-exclamation-triangle text-amber-600 mr-2 mt-0.5"></i>
            <div class="text-sm">
              <p class="text-amber-900 font-medium mb-1">重要提醒</p>
              <ul class="text-amber-800 space-y-1">
                <li>• 暂停账户后，您将无法登录系统，但数据会被保留</li>
                <li>• 注销账户是不可逆操作，将永久删除所有个人数据</li>
                <li>• 注销前请确保备份重要数据，或联系客服咨询</li>
              </ul>
            </div>
          </div>
        </div>
      </div>

      <!-- 技术支持 -->
      <div class="bg-white rounded-lg shadow-sm border border-gray-200 p-6">
        <h2 class="text-lg font-semibold text-gray-900 mb-6">技术支持</h2>
        
        <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
          <div class="text-center p-4 border border-gray-200 rounded-lg">
            <div class="w-12 h-12 bg-blue-100 rounded-lg flex items-center justify-center mx-auto mb-3">
              <i class="fas fa-question-circle text-blue-600 text-xl"></i>
            </div>
            <h3 class="font-medium text-gray-900 mb-2">帮助中心</h3>
            <p class="text-sm text-gray-600 mb-4">查看常见问题和使用指南</p>
            <button class="px-4 py-2 text-sm bg-blue-100 text-blue-700 rounded-lg hover:bg-blue-200 transition-colors">
              访问帮助中心
            </button>
          </div>

          <div class="text-center p-4 border border-gray-200 rounded-lg">
            <div class="w-12 h-12 bg-green-100 rounded-lg flex items-center justify-center mx-auto mb-3">
              <i class="fas fa-headset text-green-600 text-xl"></i>
            </div>
            <h3 class="font-medium text-gray-900 mb-2">联系客服</h3>
            <p class="text-sm text-gray-600 mb-4">获取人工客服支持</p>
            <button class="px-4 py-2 text-sm bg-green-100 text-green-700 rounded-lg hover:bg-green-200 transition-colors">
              联系客服
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { reactive } from 'vue';

// 账户信息
const accountInfo = reactive({
  id: 'AT2024001234',
  createTime: '2024-01-01 10:30:00',
  status: '正常',
  lastLogin: '2024-01-20 14:30:00',
  lastLoginIp: '192.168.1.100',
  level: 'VIP',
  levelDescription: '高级用户'
});

// 第三方绑定状态
const bindings = reactive({
  wechat: 'user123',
  qq: null,
  weibo: null,
  github: 'developer456'
});

// 方法
const copyToClipboard = (text) => {
  navigator.clipboard.writeText(text).then(() => {
    alert('账户ID已复制到剪贴板');
  }).catch(() => {
    alert('复制失败，请手动复制');
  });
};

// 绑定操作
const bindWechat = () => {
  console.log('绑定微信');
  alert('请扫描二维码完成微信绑定');
};

const unbindWechat = () => {
  if (confirm('确定要解除微信绑定吗？')) {
    bindings.wechat = null;
    console.log('解除微信绑定');
  }
};

const bindQQ = () => {
  console.log('绑定QQ');
  alert('正在跳转到QQ授权页面...');
};

const unbindQQ = () => {
  if (confirm('确定要解除QQ绑定吗？')) {
    bindings.qq = null;
    console.log('解除QQ绑定');
  }
};

const bindWeibo = () => {
  console.log('绑定微博');
  alert('正在跳转到微博授权页面...');
};

const unbindWeibo = () => {
  if (confirm('确定要解除微博绑定吗？')) {
    bindings.weibo = null;
    console.log('解除微博绑定');
  }
};

const bindGithub = () => {
  console.log('绑定GitHub');
  alert('正在跳转到GitHub授权页面...');
};

const unbindGithub = () => {
  if (confirm('确定要解除GitHub绑定吗？')) {
    bindings.github = null;
    console.log('解除GitHub绑定');
  }
};

// 数据和隐私操作
const exportPersonalData = () => {
  console.log('申请导出个人数据');
  alert('数据导出申请已提交，我们将在24小时内处理并发送至您的邮箱');
};

const viewActivityHistory = () => {
  console.log('查看活动历史');
  alert('活动历史功能开发中...');
};

const openPrivacySettings = () => {
  console.log('打开隐私设置');
  alert('隐私设置功能开发中...');
};

// 账户操作
const suspendAccount = () => {
  if (confirm('确定要暂停账户吗？暂停后您将无法登录系统。')) {
    console.log('暂停账户');
    alert('账户已暂停，如需恢复请联系客服');
  }
};

const deleteAccount = () => {
  const confirmText = '删除账户';
  const userInput = prompt(`注销账户是不可逆操作，将永久删除所有数据。\n\n如果您确定要继续，请输入"${confirmText}"：`);
  
  if (userInput === confirmText) {
    console.log('注销账户');
    alert('账户注销申请已提交，我们将在3个工作日内处理');
  } else if (userInput !== null) {
    alert('输入不正确，操作已取消');
  }
};
</script> 