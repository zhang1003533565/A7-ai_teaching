<template>
  <div class="min-h-screen bg-gray-50 py-8">
    <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
      <!-- 面包屑导航 -->
      <div class="mb-6 flex items-center text-sm text-gray-500">
        <span class="cursor-pointer hover:text-blue-600" @click="$router.push('/dashboard')">首页</span>
        <i class="fas fa-chevron-right mx-2 text-xs"></i>
        <span class="cursor-pointer hover:text-blue-600">个人设置</span>
        <i class="fas fa-chevron-right mx-2 text-xs"></i>
        <span class="text-gray-700">基本信息</span>
      </div>


      <div class="grid grid-cols-1 lg:grid-cols-3 gap-6">
        <!-- 左侧栏 -->
        <div class="lg:col-span-1 space-y-6">
          <!-- 头像设置 -->
          <div class="bg-white rounded-lg shadow-sm border border-gray-200 p-6">
            <h2 class="text-lg font-semibold text-gray-900 mb-4">头像设置</h2>
            
            <div class="flex flex-col items-center">
              <div class="relative">
                <div v-if="!userInfo.avatar" class="w-24 h-24 rounded-full bg-gradient-to-r from-blue-500 to-indigo-600 flex items-center justify-center text-white font-bold text-3xl">
                  {{ userInfo.realName ? userInfo.realName.charAt(0) : 'U' }}
                </div>
                <img v-else :src="getFullAvatarUrl(userInfo.avatar)" class="w-24 h-24 rounded-full object-cover" :alt="userInfo.realName" />
                <label class="absolute bottom-0 right-0 w-8 h-8 bg-blue-600 rounded-full flex items-center justify-center text-white hover:bg-blue-700 transition-colors cursor-pointer">
                  <input 
                    type="file" 
                    class="hidden" 
                    accept="image/*"
                    ref="fileInput"
                    @change="handleAvatarUpload" 
                  />
                  <i class="fas fa-camera text-sm"></i>
                </label>
              </div>
              
              <h3 class="mt-4 text-lg font-medium text-gray-900">{{ userInfo.realName || '用户' }}</h3>
              <p class="text-sm text-gray-500">@{{ userInfo.username || 'username' }}</p>
              
              <div class="mt-4 space-y-2 w-full" v-if="userInfo.avatar">
                <button 
                  @click="deleteAvatar" 
                  class="w-full px-4 py-2 bg-gray-100 text-gray-700 rounded-lg hover:bg-gray-200 transition-colors text-sm"
                >
                  删除头像
                </button>
              </div>
            </div>
          </div>

          <!-- 账户信息 -->
          <div class="bg-white rounded-lg shadow-sm border border-gray-200 p-6">
            <h2 class="text-lg font-semibold text-gray-900 mb-4">账户信息</h2>
            <div class="space-y-3">
              <div class="flex justify-between">
                <span class="text-sm text-gray-600">账户状态</span>
                <span :class="['text-sm font-medium', userInfo.status === 1 ? 'text-green-600' : 'text-red-600']">
                  {{ userInfo.status === 1 ? '正常' : '禁用' }}
                </span>
              </div>
              <div class="flex justify-between">
                <span class="text-sm text-gray-600">注册时间</span>
                <span class="text-sm font-medium text-gray-900">{{ formatDate(userInfo.createTime) }}</span>
              </div>
              <div class="flex justify-between">
                <span class="text-sm text-gray-600">最后更新</span>
                <span class="text-sm font-medium text-gray-900">{{ formatDate(userInfo.updateTime) }}</span>
              </div>
            </div>
          </div>
        </div>

        <!-- 右侧表单 -->
        <div class="lg:col-span-2">
          <div class="bg-white rounded-lg shadow-sm border border-gray-200 p-6 form-container">
            <!-- 编辑按钮 -->
            <div class="flex justify-end mb-6">
              <template v-if="!isEditing">
                <button 
                  @click="startEdit"
                  class="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition-colors flex items-center"
                >
                  <i class="fas fa-edit mr-2"></i>
                  编辑资料
                </button>
              </template>
              <template v-else>
                <button 
                  @click="cancelEdit"
                  class="px-4 py-2 bg-gray-100 text-gray-700 rounded-lg hover:bg-gray-200 transition-colors mr-2"
                >
                  取消
                </button>
                <button 
                  type="submit"
                  form="profileForm"
                  class="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition-colors"
                >
                  保存更改
                </button>
              </template>
            </div>

            <form id="profileForm" @submit.prevent="updateProfile" class="space-y-8">
              <!-- 基本信息 -->
              <div>
                <h3 class="text-md font-medium text-gray-800 mb-4">基本信息</h3>
                <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                  <div>
                    <label class="block text-sm font-medium text-gray-700 mb-2">用户名</label>
                    <input 
                      v-model="profileForm.username"
                      type="text" 
                      class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 text-gray-900"
                      placeholder="请输入用户名"
                      :disabled="!isEditing"
                    />
                  </div>

                  <div>
                    <label class="block text-sm font-medium text-gray-700 mb-2">真实姓名</label>
                    <input 
                      v-model="profileForm.realName"
                      type="text" 
                      class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 text-gray-900"
                      placeholder="请输入真实姓名"
                      :disabled="!isEditing"
                    />
                  </div>

                  <div>
                    <label class="block text-sm font-medium text-gray-700 mb-2">邮箱</label>
                    <input 
                      v-model="profileForm.email"
                      type="email" 
                      class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 text-gray-900"
                      placeholder="请输入邮箱"
                      :disabled="!isEditing"
                    />
                  </div>

                  <div>
                    <label class="block text-sm font-medium text-gray-700 mb-2">手机号码</label>
                    <input 
                      v-model="profileForm.phone"
                      type="tel" 
                      class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 text-gray-900"
                      placeholder="请输入手机号码"
                      :disabled="!isEditing"
                    />
                  </div>

                  <div>
                    <label class="block text-sm font-medium text-gray-700 mb-2">性别</label>
                    <select 
                      v-model="profileForm.gender"
                      class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 text-gray-900"
                      :disabled="!isEditing"
                    >
                      <option value="">请选择</option>
                      <option value="男">男</option>
                      <option value="女">女</option>
                      <option value="其他">其他</option>
                    </select>
                  </div>

                  <div>
                    <label class="block text-sm font-medium text-gray-700 mb-2">出生日期</label>
                    <input 
                      v-model="profileForm.birthDate"
                      type="date" 
                      class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 text-gray-900"
                      :disabled="!isEditing"
                    />
                  </div>
                </div>
              </div>

              <!-- 学籍信息 -->
              <div>
                <h3 class="text-md font-medium text-gray-800 mb-4">学籍信息</h3>
                <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                  <div>
                    <label class="block text-sm font-medium text-gray-700 mb-2">学校</label>
                    <input 
                      v-model="profileForm.school"
                      type="text" 
                      class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 text-gray-900"
                      placeholder="请输入学校名称"
                      :disabled="!isEditing"
                    />
                  </div>

                  <div>
                    <label class="block text-sm font-medium text-gray-700 mb-2">学院</label>
                    <input 
                      v-model="profileForm.college"
                      type="text" 
                      class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 text-gray-900"
                      placeholder="请输入学院名称"
                      :disabled="!isEditing"
                    />
                  </div>

                  <div>
                    <label class="block text-sm font-medium text-gray-700 mb-2">专业</label>
                    <input 
                      v-model="profileForm.major"
                      type="text" 
                      class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 text-gray-900"
                      placeholder="请输入专业名称"
                      :disabled="!isEditing"
                    />
                  </div>

                  <div>
                    <label class="block text-sm font-medium text-gray-700 mb-2">班级</label>
                    <input 
                      v-model="profileForm.className"
                      type="text" 
                      class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 text-gray-900"
                      placeholder="请输入班级"
                      :disabled="!isEditing"
                    />
                  </div>

                  <div>
                    <label class="block text-sm font-medium text-gray-700 mb-2">学号</label>
                    <input 
                      v-model="profileForm.studentNumber"
                      type="text" 
                      class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 text-gray-900"
                      placeholder="请输入学号"
                      :disabled="!isEditing"
                    />
                  </div>

                  <div>
                    <label class="block text-sm font-medium text-gray-700 mb-2">入学年份</label>
                    <input 
                      v-model="profileForm.entryYear"
                      type="number" 
                      class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 text-gray-900"
                      min="1900"
                      :max="new Date().getFullYear()"
                      placeholder="请输入入学年份"
                      :disabled="!isEditing"
                    />
                  </div>

                  <div>
                    <label class="block text-sm font-medium text-gray-700 mb-2">年级</label>
                    <select 
                      v-model="profileForm.grade"
                      class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 text-gray-900"
                      :disabled="!isEditing"
                    >
                      <option value="">请选择</option>
                      <option value="1">大一</option>
                      <option value="2">大二</option>
                      <option value="3">大三</option>
                      <option value="4">大四</option>
                      <option value="5">研一</option>
                      <option value="6">研二</option>
                      <option value="7">研三</option>
                      <option value="8">博一</option>
                      <option value="9">博二</option>
                      <option value="10">博三</option>
                    </select>
                  </div>
                </div>
              </div>

              <!-- 个人信息 -->
              <div>
                <h3 class="text-md font-medium text-gray-800 mb-4">个人信息</h3>
                <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                  <div>
                    <label class="block text-sm font-medium text-gray-700 mb-2">年龄</label>
                    <input 
                      v-model="profileForm.age"
                      type="number" 
                      class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 text-gray-900"
                      min="0"
                      max="120"
                      placeholder="请输入年龄"
                      :disabled="!isEditing"
                    />
                  </div>

                  <div>
                    <label class="block text-sm font-medium text-gray-700 mb-2">身份证号</label>
                    <input 
                      v-model="profileForm.idCard"
                      type="text" 
                      class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 text-gray-900"
                      placeholder="请输入身份证号"
                      :disabled="!isEditing"
                    />
                  </div>

                  <div>
                    <label class="block text-sm font-medium text-gray-700 mb-2">民族</label>
                    <input 
                      v-model="profileForm.nation"
                      type="text" 
                      class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 text-gray-900"
                      placeholder="请输入民族"
                      :disabled="!isEditing"
                    />
                  </div>

                  <div>
                    <label class="block text-sm font-medium text-gray-700 mb-2">政治面貌</label>
                    <select 
                      v-model="profileForm.politicalStatus"
                      class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 text-gray-900"
                      :disabled="!isEditing"
                    >
                      <option value="">请选择</option>
                      <option value="群众">群众</option>
                      <option value="共青团员">共青团员</option>
                      <option value="中共党员">中共党员</option>
                      <option value="其他">其他</option>
                    </select>
                  </div>

                  <div>
                    <label class="block text-sm font-medium text-gray-700 mb-2">家庭住址</label>
                    <input 
                      v-model="profileForm.address"
                      type="text" 
                      class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 text-gray-900"
                      placeholder="请输入家庭住址"
                      :disabled="!isEditing"
                    />
                  </div>

                  <div>
                    <label class="block text-sm font-medium text-gray-700 mb-2">紧急联系人</label>
                    <input 
                      v-model="profileForm.emergencyContact"
                      type="text" 
                      class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 text-gray-900"
                      placeholder="请输入紧急联系人"
                      :disabled="!isEditing"
                    />
                  </div>

                  <div>
                    <label class="block text-sm font-medium text-gray-700 mb-2">紧急联系人电话</label>
                    <input 
                      v-model="profileForm.emergencyPhone"
                      type="tel" 
                      class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 text-gray-900"
                      placeholder="请输入紧急联系人电话"
                      :disabled="!isEditing"
                    />
                  </div>
                </div>
              </div>

              <div class="mt-6">
                <label class="block text-sm font-medium text-gray-700 mb-2">角色</label>
                <div class="flex flex-wrap gap-2">
                  <span 
                    v-for="role in userInfo.roles" 
                    :key="role.id"
                    class="px-3 py-1 bg-blue-100 text-blue-800 rounded-full text-sm"
                  >
                    {{ role.roleName }}
                  </span>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue';
import { ElMessage } from 'element-plus';
import { getUserInfo, updateUser } from '@/api/user';
import { uploadFile, deleteFile } from '@/api/file';
import config from '@/config/env.config';

// 获取基础URL（去掉/api后缀）
const baseUrl = config.baseUrl.replace('/api', '');

// 计算完整的头像URL
const getFullAvatarUrl = (avatar) => {
  if (!avatar) return '';
  if (avatar.startsWith('http')) return avatar;
  return `${baseUrl}${avatar}`;
};

// 用户信息
const userInfo = ref({});

// 文件输入引用
const fileInput = ref(null);

// 表单数据
const profileForm = reactive({
  id: '',
  username: '',
  realName: '',
  email: '',
  phone: '',
  role: '',
  avatar: '',
  
  school: '',
  college: '',
  major: '',
  className: '',
  studentNumber: '',
  age: null,
  birthDate: '',
  gender: '',
  idCard: '',
  nation: '',
  politicalStatus: '',
  entryYear: null,
  grade: null,
  address: '',
  emergencyContact: '',
  emergencyPhone: ''
});

// 编辑状态
const isEditing = ref(false);

// 临时表单数据（用于编辑）
const tempForm = ref({});

// 开始编辑
const startEdit = () => {
  // 保存当前表单数据的副本
  tempForm.value = JSON.parse(JSON.stringify(profileForm));
  isEditing.value = true;
};

// 取消编辑
const cancelEdit = () => {
  // 恢复表单数据
  Object.assign(profileForm, tempForm.value);
  isEditing.value = false;
};

// 获取用户信息
const fetchUserInfo = async () => {
  try {
    const response = await getUserInfo();
    if (response.code === 200) {
      userInfo.value = response.data;
      // 更新表单数据
      Object.keys(profileForm).forEach(key => {
        if (response.data[key] !== undefined) {
          // 处理特殊类型的字段
          if (key === 'birthDate' && response.data[key]) {
            // 确保日期格式正确
            profileForm[key] = new Date(response.data[key]).toISOString().split('T')[0];
          } else if (['age', 'entryYear', 'grade'].includes(key)) {
            // 确保数字类型字段为数字
            profileForm[key] = response.data[key] ? parseInt(response.data[key]) : null;
          } else {
            profileForm[key] = response.data[key];
          }
        }
      });
      console.log('获取到的用户信息：', response.data);
      console.log('更新后的表单数据：', profileForm);
    }
  } catch (error) {
    console.error('获取用户信息失败:', error);
    ElMessage.error('获取用户信息失败');
  }
};

// 更新个人资料
const updateProfile = async () => {
  try {
    // 构建要提交的数据
    const submitData = {
      ...profileForm,
      // 确保数字类型字段为数字
      age: profileForm.age ? parseInt(profileForm.age) : null,
      grade: profileForm.grade ? parseInt(profileForm.grade) : null,
      entryYear: profileForm.entryYear ? parseInt(profileForm.entryYear) : null,
      // 处理日期格式
      birthDate: profileForm.birthDate ? new Date(profileForm.birthDate).toISOString().split('T')[0] : null
    };

    console.log('提交的数据：', submitData);

    const response = await updateUser(submitData);
    if (response.code === 200) {
      ElMessage.success('个人资料更新成功');
      await fetchUserInfo();
      isEditing.value = false;
    } else {
      ElMessage.error(response.message || '更新失败');
    }
  } catch (error) {
    console.error('更新个人资料失败:', error);
    ElMessage.error('更新个人资料失败');
  }
};

// 处理头像上传
const handleAvatarUpload = async (event) => {
  const file = event.target.files[0];
  if (!file) return;
  
  // 验证文件类型
  const allowedTypes = ['image/jpeg', 'image/png', 'image/gif'];
  if (!allowedTypes.includes(file.type)) {
    ElMessage.error('只能上传 JPG/PNG/GIF 格式的图片');
    return;
  }
  
  // 验证文件大小（限制为 2MB）
  const maxSize = 2 * 1024 * 1024;
  if (file.size > maxSize) {
    ElMessage.error('图片大小不能超过 2MB');
    return;
  }
  
  try {
    const response = await uploadFile(file);
    
    if (response.code === 200 && response.data) {
      // 更新用户头像
      const updateResponse = await updateUser({
        id: userInfo.value.id,
        avatar: response.data.url
      });
      
      if (updateResponse.code === 200) {
        ElMessage.success('头像更新成功');
        await fetchUserInfo();
      } else {
        ElMessage.error(updateResponse.message || '头像更新失败');
      }
    } else {
      ElMessage.error(response.message || '头像上传失败');
    }
  } catch (error) {
    console.error('头像上传失败:', error);
    ElMessage.error('头像上传失败：' + (error.message || '未知错误'));
  } finally {
    // 重置文件输入，允许重新选择相同的文件
    if (fileInput.value) {
      fileInput.value.value = '';
    }
  }
};

// 删除头像
const deleteAvatar = async () => {
  try {
    if (userInfo.value.avatar) {
      // 从URL中提取文件名
      const filename = userInfo.value.avatar.split('/').pop();
      const deleteResponse = await deleteFile(filename);
      
      if (deleteResponse.code === 200) {
        // 更新用户信息，清空头像
        const updateResponse = await updateUser({
          id: userInfo.value.id,
          avatar: ''
        });
        
        if (updateResponse.code === 200) {
          ElMessage.success('头像删除成功');
          await fetchUserInfo();
        } else {
          ElMessage.error(updateResponse.message || '头像信息更新失败');
        }
      } else {
        ElMessage.error(deleteResponse.message || '头像文件删除失败');
      }
    }
  } catch (error) {
    console.error('头像删除失败:', error);
    ElMessage.error('头像删除失败：' + (error.message || '未知错误'));
  }
};

// 格式化日期
const formatDate = (date) => {
  if (!date) return '';
  return new Date(date).toLocaleDateString('zh-CN');
};

// 在组件挂载时获取用户信息
onMounted(() => {
  fetchUserInfo();
});
</script>

<style scoped>
.form-container {
  overflow-y: auto;
  height: calc(100vh - 200px);
}

/* 输入框样式 */
input, select {
  @apply text-gray-900;
}

input::placeholder, select::placeholder {
  @apply text-gray-400;
}

/* 禁用状态的输入框样式 */
input:disabled, select:disabled {
  @apply bg-gray-50 text-gray-500 cursor-not-allowed;
}
</style> 