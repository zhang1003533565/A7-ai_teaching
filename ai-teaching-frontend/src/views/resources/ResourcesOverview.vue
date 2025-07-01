<template>
  <div class="p-6 bg-gray-50 min-h-screen">
    <!-- 页面标题 -->
    <div class="mb-8">
      <h1 class="text-2xl font-bold text-gray-900 mb-2">资源概览</h1>
      <p class="text-gray-600">管理和查看所有教学资源</p>
    </div>

    <!-- 资源统计卡片 -->
    <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6 mb-8">
      <div class="bg-white p-6 rounded-lg shadow-sm border border-gray-200">
        <div class="flex items-center justify-between mb-4">
          <div>
            <p class="text-sm text-gray-600">总资源数</p>
            <p class="text-2xl font-bold text-gray-900">{{ stats.totalResources }}</p>
          </div>
          <div class="w-12 h-12 bg-blue-100 rounded-lg flex items-center justify-center">
            <i class="fas fa-file-alt text-blue-600"></i>
          </div>
        </div>
        <p class="text-sm text-gray-500">本月新增 <span class="text-green-600">+{{ stats.newResources }}</span></p>
      </div>

      <div class="bg-white p-6 rounded-lg shadow-sm border border-gray-200">
        <div class="flex items-center justify-between mb-4">
          <div>
            <p class="text-sm text-gray-600">存储空间</p>
            <p class="text-2xl font-bold text-gray-900">{{ stats.storageUsed }}</p>
          </div>
          <div class="w-12 h-12 bg-green-100 rounded-lg flex items-center justify-center">
            <i class="fas fa-database text-green-600"></i>
          </div>
        </div>
        <p class="text-sm text-gray-500">剩余 <span class="text-blue-600">{{ stats.storageLeft }}</span></p>
      </div>

      <div class="bg-white p-6 rounded-lg shadow-sm border border-gray-200">
        <div class="flex items-center justify-between mb-4">
          <div>
            <p class="text-sm text-gray-600">共享资源</p>
            <p class="text-2xl font-bold text-gray-900">{{ stats.sharedResources }}</p>
          </div>
          <div class="w-12 h-12 bg-purple-100 rounded-lg flex items-center justify-center">
            <i class="fas fa-share-alt text-purple-600"></i>
          </div>
        </div>
        <p class="text-sm text-gray-500">本月分享 <span class="text-green-600">+{{ stats.newShared }}</span></p>
      </div>

      <div class="bg-white p-6 rounded-lg shadow-sm border border-gray-200">
        <div class="flex items-center justify-between mb-4">
          <div>
            <p class="text-sm text-gray-600">下载次数</p>
            <p class="text-2xl font-bold text-gray-900">{{ stats.downloads }}</p>
          </div>
          <div class="w-12 h-12 bg-yellow-100 rounded-lg flex items-center justify-center">
            <i class="fas fa-download text-yellow-600"></i>
          </div>
        </div>
        <p class="text-sm text-gray-500">本月下载 <span class="text-green-600">+{{ stats.newDownloads }}</span></p>
      </div>
    </div>

    <!-- 快捷操作 -->
    <div class="bg-white rounded-lg shadow-sm border border-gray-200 mb-8">
      <div class="p-6">
        <h2 class="text-lg font-semibold text-gray-900 mb-4">快捷操作</h2>
        <div class="grid grid-cols-2 md:grid-cols-4 lg:grid-cols-6 gap-4">
          <div 
            v-for="action in quickActions" 
            :key="action.key"
            class="p-4 border border-gray-200 rounded-lg hover:bg-gray-50 cursor-pointer transition-colors"
            @click="handleQuickAction(action.key)"
          >
            <div class="flex flex-col items-center text-center">
              <div :class="['w-12 h-12 rounded-lg flex items-center justify-center mb-2', action.bgColor]">
                <i :class="[action.icon, action.iconColor]"></i>
              </div>
              <span class="text-sm font-medium text-gray-900">{{ action.name }}</span>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- 最近资源 -->
    <div class="bg-white rounded-lg shadow-sm border border-gray-200 mb-8">
      <div class="p-6">
        <div class="flex justify-between items-center mb-4">
          <h2 class="text-lg font-semibold text-gray-900">最近资源</h2>
          <router-link 
            to="/dashboard/resources/materials" 
            class="text-blue-600 hover:text-blue-800 text-sm font-medium"
          >
            查看全部
          </router-link>
        </div>
        <div class="overflow-x-auto">
          <table class="min-w-full divide-y divide-gray-200">
            <thead>
              <tr>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">资源名称</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">类型</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">大小</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">上传时间</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">状态</th>
              </tr>
            </thead>
            <tbody class="bg-white divide-y divide-gray-200">
              <tr v-for="resource in recentResources" :key="resource.id" class="hover:bg-gray-50">
                <td class="px-6 py-4 whitespace-nowrap">
                  <div class="flex items-center">
                    <i :class="[resource.icon, 'text-gray-400 mr-2']"></i>
                    <span class="text-sm font-medium text-gray-900">{{ resource.name }}</span>
                  </div>
                </td>
                <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                  {{ resource.type }}
                </td>
                <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                  {{ resource.size }}
                </td>
                <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                  {{ resource.uploadTime }}
                </td>
                <td class="px-6 py-4 whitespace-nowrap">
                  <span :class="[
                    'px-2 py-1 text-xs font-medium rounded-full',
                    resource.status === '已分享' ? 'bg-green-100 text-green-800' : 'bg-gray-100 text-gray-800'
                  ]">
                    {{ resource.status }}
                  </span>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>

    <!-- 资源分布 -->
    <div class="grid grid-cols-1 lg:grid-cols-2 gap-8">
      <div class="bg-white rounded-lg shadow-sm border border-gray-200">
        <div class="p-6">
          <h2 class="text-lg font-semibold text-gray-900 mb-4">资源类型分布</h2>
          <div class="h-64 bg-gray-50 rounded-lg flex items-center justify-center">
            <p class="text-gray-500">图表区域 - 资源类型分布</p>
          </div>
        </div>
      </div>

      <div class="bg-white rounded-lg shadow-sm border border-gray-200">
        <div class="p-6">
          <h2 class="text-lg font-semibold text-gray-900 mb-4">资源使用趋势</h2>
          <div class="h-64 bg-gray-50 rounded-lg flex items-center justify-center">
            <p class="text-gray-500">图表区域 - 资源使用趋势</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()

// 统计数据
const stats = reactive({
  totalResources: 256,
  newResources: 23,
  storageUsed: '45.8GB',
  storageLeft: '154.2GB',
  sharedResources: 128,
  newShared: 15,
  downloads: 1256,
  newDownloads: 89
})

// 快捷操作
const quickActions = ref([
  {
    key: 'upload',
    name: '上传资源',
    icon: 'fas fa-upload',
    bgColor: 'bg-blue-100',
    iconColor: 'text-blue-600'
  },
  {
    key: 'create-folder',
    name: '新建文件夹',
    icon: 'fas fa-folder-plus',
    bgColor: 'bg-yellow-100',
    iconColor: 'text-yellow-600'
  },
  {
    key: 'share',
    name: '共享资源',
    icon: 'fas fa-share-alt',
    bgColor: 'bg-green-100',
    iconColor: 'text-green-600'
  },
  {
    key: 'template',
    name: '创建模板',
    icon: 'fas fa-copy',
    bgColor: 'bg-purple-100',
    iconColor: 'text-purple-600'
  },
  {
    key: 'organize',
    name: '整理资源',
    icon: 'fas fa-sort',
    bgColor: 'bg-red-100',
    iconColor: 'text-red-600'
  },
  {
    key: 'recycle',
    name: '回收站',
    icon: 'fas fa-trash-alt',
    bgColor: 'bg-gray-100',
    iconColor: 'text-gray-600'
  }
])

// 最近资源
const recentResources = ref([
  {
    id: 1,
    name: '高等数学教案.pdf',
    type: 'PDF文档',
    size: '2.5MB',
    uploadTime: '2小时前',
    status: '已分享',
    icon: 'fas fa-file-pdf'
  },
  {
    id: 2,
    name: '线性代数课件.pptx',
    type: '演示文稿',
    size: '5.8MB',
    uploadTime: '3小时前',
    status: '私有',
    icon: 'fas fa-file-powerpoint'
  },
  {
    id: 3,
    name: '概率论习题集.docx',
    type: 'Word文档',
    size: '1.2MB',
    uploadTime: '5小时前',
    status: '已分享',
    icon: 'fas fa-file-word'
  },
  {
    id: 4,
    name: '数据结构实验视频.mp4',
    type: '视频',
    size: '256MB',
    uploadTime: '1天前',
    status: '私有',
    icon: 'fas fa-file-video'
  },
  {
    id: 5,
    name: '计算机网络实验指导.pdf',
    type: 'PDF文档',
    size: '3.6MB',
    uploadTime: '2天前',
    status: '已分享',
    icon: 'fas fa-file-pdf'
  }
])

// 处理快捷操作
const handleQuickAction = (actionKey) => {
  switch (actionKey) {
    case 'upload':
      router.push('/dashboard/resources/materials')
      break
    case 'create-folder':
      // 实现新建文件夹逻辑
      break
    case 'share':
      router.push('/dashboard/resources/shared')
      break
    case 'template':
      router.push('/dashboard/resources/templates')
      break
    case 'organize':
      router.push('/dashboard/resources/materials')
      break
    case 'recycle':
      // 实现回收站逻辑
      break
  }
}
</script> 