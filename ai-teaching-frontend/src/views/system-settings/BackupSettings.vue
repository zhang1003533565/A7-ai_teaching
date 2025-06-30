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
      <span class="text-gray-700">备份恢复</span>
    </div>

    <!-- 页面标题 -->
    <div class="mb-6">
      <h1 class="text-2xl font-bold text-gray-900">备份恢复</h1>
      <p class="mt-2 text-gray-600">管理系统数据备份和恢复操作</p>
    </div>

    <div class="grid grid-cols-1 lg:grid-cols-2 gap-6">
      <!-- 自动备份设置 -->
      <div class="bg-white rounded-lg shadow-sm p-6">
        <h2 class="text-lg font-semibold text-gray-900 mb-4">
          <i class="fas fa-clock text-blue-600 mr-2"></i>自动备份设置
        </h2>
        <div class="space-y-4">
          <div class="flex items-center justify-between">
            <div>
              <span class="text-sm font-medium text-gray-700">启用自动备份</span>
              <p class="text-xs text-gray-500">定期自动备份系统数据</p>
            </div>
            <label class="relative inline-flex items-center cursor-pointer">
              <input type="checkbox" v-model="backupSettings.autoBackup" class="sr-only peer">
              <div class="w-11 h-6 bg-gray-200 peer-focus:outline-none peer-focus:ring-4 peer-focus:ring-blue-300 rounded-full peer peer-checked:after:translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:left-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-5 after:w-5 after:transition-all peer-checked:bg-blue-600"></div>
            </label>
          </div>
          <div>
            <label class="block text-sm font-medium text-gray-700 mb-2">备份频率</label>
            <select
              v-model="backupSettings.frequency"
              class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
            >
              <option value="daily">每日</option>
              <option value="weekly">每周</option>
              <option value="monthly">每月</option>
            </select>
          </div>
          <div>
            <label class="block text-sm font-medium text-gray-700 mb-2">备份时间</label>
            <input
              type="time"
              v-model="backupSettings.time"
              class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
            />
          </div>
          <div>
            <label class="block text-sm font-medium text-gray-700 mb-2">保留份数</label>
            <input
              type="number"
              v-model="backupSettings.retentionCount"
              class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              placeholder="30"
            />
          </div>
        </div>
      </div>

      <!-- 手动备份 -->
      <div class="bg-white rounded-lg shadow-sm p-6">
        <h2 class="text-lg font-semibold text-gray-900 mb-4">
          <i class="fas fa-download text-green-600 mr-2"></i>手动备份
        </h2>
        <div class="space-y-4">
          <div>
            <label class="block text-sm font-medium text-gray-700 mb-2">备份内容</label>
            <div class="space-y-2">
              <div class="flex items-center">
                <input
                  type="checkbox"
                  v-model="manualBackup.includeDatabase"
                  class="h-4 w-4 text-blue-600 focus:ring-blue-500 border-gray-300 rounded"
                />
                <span class="ml-2 text-sm text-gray-700">数据库</span>
              </div>
              <div class="flex items-center">
                <input
                  type="checkbox"
                  v-model="manualBackup.includeFiles"
                  class="h-4 w-4 text-blue-600 focus:ring-blue-500 border-gray-300 rounded"
                />
                <span class="ml-2 text-sm text-gray-700">上传文件</span>
              </div>
              <div class="flex items-center">
                <input
                  type="checkbox"
                  v-model="manualBackup.includeConfig"
                  class="h-4 w-4 text-blue-600 focus:ring-blue-500 border-gray-300 rounded"
                />
                <span class="ml-2 text-sm text-gray-700">系统配置</span>
              </div>
            </div>
          </div>
          <div>
            <label class="block text-sm font-medium text-gray-700 mb-2">备份描述</label>
            <input
              type="text"
              v-model="manualBackup.description"
              class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              placeholder="手动备份 - 系统升级前"
            />
          </div>
          <button
            @click="createBackup"
            class="w-full px-4 py-2 bg-green-600 text-white rounded-md hover:bg-green-700 transition-colors"
          >
            <i class="fas fa-plus mr-2"></i>创建备份
          </button>
        </div>
      </div>
    </div>

    <!-- 备份列表 -->
    <div class="mt-6 bg-white rounded-lg shadow-sm p-6">
      <h2 class="text-lg font-semibold text-gray-900 mb-4">
        <i class="fas fa-list text-purple-600 mr-2"></i>备份历史
      </h2>
      
      <!-- 搜索和筛选 -->
      <div class="mb-4 flex items-center space-x-4">
        <div class="flex-1">
          <input
            type="text"
            v-model="searchTerm"
            class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
            placeholder="搜索备份..."
          />
        </div>
        <select
          v-model="filterType"
          class="px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
        >
          <option value="">全部类型</option>
          <option value="auto">自动备份</option>
          <option value="manual">手动备份</option>
        </select>
      </div>

      <!-- 备份列表 -->
      <div class="overflow-x-auto">
        <table class="min-w-full divide-y divide-gray-200">
          <thead class="bg-gray-50">
            <tr>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                备份信息
              </th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                类型
              </th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                大小
              </th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                状态
              </th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                操作
              </th>
            </tr>
          </thead>
          <tbody class="bg-white divide-y divide-gray-200">
            <tr v-for="backup in filteredBackups" :key="backup.id" class="hover:bg-gray-50">
              <td class="px-6 py-4 whitespace-nowrap">
                <div>
                  <div class="text-sm font-medium text-gray-900">{{ backup.name }}</div>
                  <div class="text-sm text-gray-500">{{ backup.createdAt }}</div>
                  <div class="text-xs text-gray-400">{{ backup.description }}</div>
                </div>
              </td>
              <td class="px-6 py-4 whitespace-nowrap">
                <span 
                  :class="[
                    'px-2 inline-flex text-xs leading-5 font-semibold rounded-full',
                    backup.type === 'auto' 
                      ? 'bg-blue-100 text-blue-800' 
                      : 'bg-green-100 text-green-800'
                  ]"
                >
                  {{ backup.type === 'auto' ? '自动' : '手动' }}
                </span>
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
                {{ backup.size }}
              </td>
              <td class="px-6 py-4 whitespace-nowrap">
                <span 
                  :class="[
                    'px-2 inline-flex text-xs leading-5 font-semibold rounded-full',
                    backup.status === 'success' 
                      ? 'bg-green-100 text-green-800' 
                      : backup.status === 'failed'
                      ? 'bg-red-100 text-red-800'
                      : 'bg-yellow-100 text-yellow-800'
                  ]"
                >
                  {{ 
                    backup.status === 'success' ? '成功' : 
                    backup.status === 'failed' ? '失败' : '进行中'
                  }}
                </span>
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm font-medium space-x-2">
                <button
                  @click="downloadBackup(backup)"
                  class="text-blue-600 hover:text-blue-900"
                  :disabled="backup.status !== 'success'"
                >
                  下载
                </button>
                <button
                  @click="restoreBackup(backup)"
                  class="text-green-600 hover:text-green-900"
                  :disabled="backup.status !== 'success'"
                >
                  恢复
                </button>
                <button
                  @click="deleteBackup(backup)"
                  class="text-red-600 hover:text-red-900"
                >
                  删除
                </button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <!-- 操作按钮 -->
    <div class="mt-6 flex justify-end space-x-4">
      <button
        @click="resetSettings"
        class="px-4 py-2 text-gray-700 bg-gray-100 border border-gray-300 rounded-md hover:bg-gray-200 transition-colors"
      >
        重置设置
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
import { ref, reactive, computed } from 'vue'

// 备份设置
const backupSettings = reactive({
  autoBackup: true,
  frequency: 'daily',
  time: '02:00',
  retentionCount: 30
})

// 手动备份
const manualBackup = reactive({
  includeDatabase: true,
  includeFiles: true,
  includeConfig: true,
  description: ''
})

// 搜索和筛选
const searchTerm = ref('')
const filterType = ref('')

// 备份列表数据
const backups = reactive([
  {
    id: 1,
    name: 'backup_2024_01_15_02_00_00',
    description: '每日自动备份',
    type: 'auto',
    size: '256.7 MB',
    status: 'success',
    createdAt: '2024-01-15 02:00:00'
  },
  {
    id: 2,
    name: 'backup_manual_2024_01_14_upgrade',
    description: '系统升级前手动备份',
    type: 'manual',
    size: '298.2 MB',
    status: 'success',
    createdAt: '2024-01-14 16:30:00'
  },
  {
    id: 3,
    name: 'backup_2024_01_14_02_00_00',
    description: '每日自动备份',
    type: 'auto',
    size: '245.1 MB',
    status: 'success',
    createdAt: '2024-01-14 02:00:00'
  },
  {
    id: 4,
    name: 'backup_2024_01_13_02_00_00',
    description: '每日自动备份',
    type: 'auto',
    size: '0 MB',
    status: 'failed',
    createdAt: '2024-01-13 02:00:00'
  }
])

// 筛选后的备份列表
const filteredBackups = computed(() => {
  return backups.filter(backup => {
    const matchesSearch = backup.name.toLowerCase().includes(searchTerm.value.toLowerCase()) ||
                         backup.description.toLowerCase().includes(searchTerm.value.toLowerCase())
    const matchesType = !filterType.value || backup.type === filterType.value
    return matchesSearch && matchesType
  })
})

// 创建备份
const createBackup = () => {
  if (!manualBackup.includeDatabase && !manualBackup.includeFiles && !manualBackup.includeConfig) {
    alert('请至少选择一种备份内容！')
    return
  }
  
  console.log('创建手动备份:', manualBackup)
  alert('正在创建备份，请稍候...')
}

// 下载备份
const downloadBackup = (backup) => {
  console.log('下载备份:', backup.name)
  alert(`正在下载备份 ${backup.name}`)
}

// 恢复备份
const restoreBackup = (backup) => {
  if (confirm(`确定要恢复备份 ${backup.name} 吗？此操作将覆盖当前数据！`)) {
    console.log('恢复备份:', backup.name)
    alert(`正在恢复备份 ${backup.name}`)
  }
}

// 删除备份
const deleteBackup = (backup) => {
  if (confirm(`确定要删除备份 ${backup.name} 吗？`)) {
    const index = backups.findIndex(b => b.id === backup.id)
    if (index > -1) {
      backups.splice(index, 1)
    }
  }
}

// 保存设置
const saveSettings = () => {
  console.log('保存备份设置:', backupSettings)
  alert('备份设置已保存！')
}

// 重置设置
const resetSettings = () => {
  if (confirm('确定要重置备份设置吗？')) {
    Object.assign(backupSettings, {
      autoBackup: true,
      frequency: 'daily',
      time: '02:00',
      retentionCount: 30
    })
  }
}
</script> 