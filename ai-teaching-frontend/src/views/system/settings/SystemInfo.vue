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
      <span class="text-gray-700">系统信息</span>
    </div>

    <!-- 页面标题 -->
    <div class="mb-6">
      <h1 class="text-2xl font-bold text-gray-900">系统信息</h1>
      <p class="mt-2 text-gray-600">查看系统运行状态和详细信息</p>
    </div>

    <div class="grid grid-cols-1 lg:grid-cols-2 gap-6">
      <!-- 系统概览 -->
      <div class="bg-white rounded-lg shadow-sm p-6">
        <h2 class="text-lg font-semibold text-gray-900 mb-4">
          <i class="fas fa-desktop text-blue-600 mr-2"></i>系统概览
        </h2>
        <div class="space-y-3">
          <div class="flex justify-between">
            <span class="text-sm font-medium text-gray-500">系统名称</span>
            <span class="text-sm text-gray-900">{{ systemInfo.name }}</span>
          </div>
          <div class="flex justify-between">
            <span class="text-sm font-medium text-gray-500">系统版本</span>
            <span class="text-sm text-gray-900">{{ systemInfo.version }}</span>
          </div>
          <div class="flex justify-between">
            <span class="text-sm font-medium text-gray-500">构建时间</span>
            <span class="text-sm text-gray-900">{{ systemInfo.buildTime }}</span>
          </div>
          <div class="flex justify-between">
            <span class="text-sm font-medium text-gray-500">运行时间</span>
            <span class="text-sm text-gray-900">{{ systemInfo.uptime }}</span>
          </div>
          <div class="flex justify-between">
            <span class="text-sm font-medium text-gray-500">启动时间</span>
            <span class="text-sm text-gray-900">{{ systemInfo.startTime }}</span>
          </div>
        </div>
      </div>

      <!-- 服务器信息 -->
      <div class="bg-white rounded-lg shadow-sm p-6">
        <h2 class="text-lg font-semibold text-gray-900 mb-4">
          <i class="fas fa-server text-green-600 mr-2"></i>服务器信息
        </h2>
        <div class="space-y-3">
          <div class="flex justify-between">
            <span class="text-sm font-medium text-gray-500">操作系统</span>
            <span class="text-sm text-gray-900">{{ serverInfo.os }}</span>
          </div>
          <div class="flex justify-between">
            <span class="text-sm font-medium text-gray-500">CPU</span>
            <span class="text-sm text-gray-900">{{ serverInfo.cpu }}</span>
          </div>
          <div class="flex justify-between">
            <span class="text-sm font-medium text-gray-500">内存</span>
            <span class="text-sm text-gray-900">{{ serverInfo.memory }}</span>
          </div>
          <div class="flex justify-between">
            <span class="text-sm font-medium text-gray-500">磁盘</span>
            <span class="text-sm text-gray-900">{{ serverInfo.disk }}</span>
          </div>
          <div class="flex justify-between">
            <span class="text-sm font-medium text-gray-500">IP地址</span>
            <span class="text-sm text-gray-900">{{ serverInfo.ip }}</span>
          </div>
        </div>
      </div>

      <!-- 运行环境 -->
      <div class="bg-white rounded-lg shadow-sm p-6">
        <h2 class="text-lg font-semibold text-gray-900 mb-4">
          <i class="fas fa-code text-purple-600 mr-2"></i>运行环境
        </h2>
        <div class="space-y-3">
          <div class="flex justify-between">
            <span class="text-sm font-medium text-gray-500">Java版本</span>
            <span class="text-sm text-gray-900">{{ environment.javaVersion }}</span>
          </div>
          <div class="flex justify-between">
            <span class="text-sm font-medium text-gray-500">Spring Boot</span>
            <span class="text-sm text-gray-900">{{ environment.springBootVersion }}</span>
          </div>
          <div class="flex justify-between">
            <span class="text-sm font-medium text-gray-500">数据库</span>
            <span class="text-sm text-gray-900">{{ environment.database }}</span>
          </div>
          <div class="flex justify-between">
            <span class="text-sm font-medium text-gray-500">Redis</span>
            <span class="text-sm text-gray-900">{{ environment.redis }}</span>
          </div>
          <div class="flex justify-between">
            <span class="text-sm font-medium text-gray-500">Node.js</span>
            <span class="text-sm text-gray-900">{{ environment.nodeVersion }}</span>
          </div>
        </div>
      </div>

      <!-- 数据统计 -->
      <div class="bg-white rounded-lg shadow-sm p-6">
        <h2 class="text-lg font-semibold text-gray-900 mb-4">
          <i class="fas fa-chart-bar text-orange-600 mr-2"></i>数据统计
        </h2>
        <div class="space-y-3">
          <div class="flex justify-between">
            <span class="text-sm font-medium text-gray-500">总用户数</span>
            <span class="text-sm text-gray-900">{{ statistics.totalUsers }}</span>
          </div>
          <div class="flex justify-between">
            <span class="text-sm font-medium text-gray-500">活跃用户</span>
            <span class="text-sm text-gray-900">{{ statistics.activeUsers }}</span>
          </div>
          <div class="flex justify-between">
            <span class="text-sm font-medium text-gray-500">课程数量</span>
            <span class="text-sm text-gray-900">{{ statistics.totalCourses }}</span>
          </div>
          <div class="flex justify-between">
            <span class="text-sm font-medium text-gray-500">作业数量</span>
            <span class="text-sm text-gray-900">{{ statistics.totalAssignments }}</span>
          </div>
          <div class="flex justify-between">
            <span class="text-sm font-medium text-gray-500">数据库大小</span>
            <span class="text-sm text-gray-900">{{ statistics.databaseSize }}</span>
          </div>
        </div>
      </div>
    </div>

    <!-- 系统状态监控 -->
    <div class="mt-6 bg-white rounded-lg shadow-sm p-6">
      <h2 class="text-lg font-semibold text-gray-900 mb-4">
        <i class="fas fa-heartbeat text-red-600 mr-2"></i>系统状态监控
      </h2>
      <div class="grid grid-cols-1 md:grid-cols-4 gap-4">
        <div class="bg-blue-50 rounded-lg p-4">
          <div class="flex items-center justify-between">
            <div>
              <p class="text-sm font-medium text-gray-500">CPU使用率</p>
              <p class="text-2xl font-bold text-blue-600">{{ performance.cpuUsage }}%</p>
            </div>
            <i class="fas fa-microchip text-2xl text-blue-600"></i>
          </div>
          <div class="mt-2">
            <div class="w-full bg-gray-200 rounded-full h-2">
              <div 
                class="bg-blue-600 h-2 rounded-full transition-all duration-300"
                :style="`width: ${performance.cpuUsage}%`"
              ></div>
            </div>
          </div>
        </div>
        
        <div class="bg-green-50 rounded-lg p-4">
          <div class="flex items-center justify-between">
            <div>
              <p class="text-sm font-medium text-gray-500">内存使用率</p>
              <p class="text-2xl font-bold text-green-600">{{ performance.memoryUsage }}%</p>
            </div>
            <i class="fas fa-memory text-2xl text-green-600"></i>
          </div>
          <div class="mt-2">
            <div class="w-full bg-gray-200 rounded-full h-2">
              <div 
                class="bg-green-600 h-2 rounded-full transition-all duration-300"
                :style="`width: ${performance.memoryUsage}%`"
              ></div>
            </div>
          </div>
        </div>
        
        <div class="bg-yellow-50 rounded-lg p-4">
          <div class="flex items-center justify-between">
            <div>
              <p class="text-sm font-medium text-gray-500">磁盘使用率</p>
              <p class="text-2xl font-bold text-yellow-600">{{ performance.diskUsage }}%</p>
            </div>
            <i class="fas fa-hdd text-2xl text-yellow-600"></i>
          </div>
          <div class="mt-2">
            <div class="w-full bg-gray-200 rounded-full h-2">
              <div 
                class="bg-yellow-600 h-2 rounded-full transition-all duration-300"
                :style="`width: ${performance.diskUsage}%`"
              ></div>
            </div>
          </div>
        </div>
        
        <div class="bg-purple-50 rounded-lg p-4">
          <div class="flex items-center justify-between">
            <div>
              <p class="text-sm font-medium text-gray-500">网络流量</p>
              <p class="text-2xl font-bold text-purple-600">{{ performance.networkSpeed }}</p>
            </div>
            <i class="fas fa-network-wired text-2xl text-purple-600"></i>
          </div>
          <div class="mt-2 text-xs text-gray-500">
            上传: {{ performance.uploadSpeed }} | 下载: {{ performance.downloadSpeed }}
          </div>
        </div>
      </div>
    </div>

    <!-- 许可证信息 -->
    <div class="mt-6 bg-white rounded-lg shadow-sm p-6">
      <h2 class="text-lg font-semibold text-gray-900 mb-4">
        <i class="fas fa-certificate text-indigo-600 mr-2"></i>许可证信息
      </h2>
      <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
        <div class="space-y-3">
          <div class="flex justify-between">
            <span class="text-sm font-medium text-gray-500">许可证类型</span>
            <span class="text-sm text-gray-900">{{ license.type }}</span>
          </div>
          <div class="flex justify-between">
            <span class="text-sm font-medium text-gray-500">授权用户数</span>
            <span class="text-sm text-gray-900">{{ license.maxUsers }}</span>
          </div>
          <div class="flex justify-between">
            <span class="text-sm font-medium text-gray-500">有效期至</span>
            <span class="text-sm text-gray-900">{{ license.expireDate }}</span>
          </div>
        </div>
        <div class="space-y-3">
          <div class="flex justify-between">
            <span class="text-sm font-medium text-gray-500">授权机构</span>
            <span class="text-sm text-gray-900">{{ license.organization }}</span>
          </div>
          <div class="flex justify-between">
            <span class="text-sm font-medium text-gray-500">许可证状态</span>
            <span 
              :class="[
                'text-sm px-2 py-1 rounded-full text-xs font-medium',
                license.status === 'active' 
                  ? 'bg-green-100 text-green-800' 
                  : 'bg-red-100 text-red-800'
              ]"
            >
              {{ license.status === 'active' ? '有效' : '已过期' }}
            </span>
          </div>
          <div class="flex justify-between">
            <span class="text-sm font-medium text-gray-500">剩余天数</span>
            <span class="text-sm text-gray-900">{{ license.remainingDays }} 天</span>
          </div>
        </div>
      </div>
    </div>

    <!-- 刷新按钮 -->
    <div class="mt-6 flex justify-end">
      <button
        @click="refreshSystemInfo"
        class="px-4 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700 transition-colors"
      >
        <i class="fas fa-sync-alt mr-2"></i>刷新信息
      </button>
    </div>
  </div>
</template>

<script setup>
import { reactive, onMounted } from 'vue'

// 系统信息
const systemInfo = reactive({
  name: 'AI智能教学系统',
  version: 'v1.0.0',
  buildTime: '2024-01-15 10:30:00',
  uptime: '7天 12小时 35分钟',
  startTime: '2024-01-08 08:00:00'
})

// 服务器信息
const serverInfo = reactive({
  os: 'Ubuntu 20.04.3 LTS',
  cpu: 'Intel Core i7-9700K @ 3.60GHz (8核)',
  memory: '16 GB DDR4',
  disk: '500 GB SSD',
  ip: '192.168.1.100'
})

// 运行环境
const environment = reactive({
  javaVersion: 'OpenJDK 11.0.16',
  springBootVersion: '2.7.3',
  database: 'MySQL 8.0.30',
  redis: 'Redis 6.2.7',
  nodeVersion: 'Node.js 16.14.2'
})

// 数据统计
const statistics = reactive({
  totalUsers: '1,245',
  activeUsers: '892',
  totalCourses: '156',
  totalAssignments: '2,341',
  databaseSize: '2.3 GB'
})

// 系统性能
const performance = reactive({
  cpuUsage: 45,
  memoryUsage: 68,
  diskUsage: 32,
  networkSpeed: '125 MB/s',
  uploadSpeed: '45 MB/s',
  downloadSpeed: '80 MB/s'
})

// 许可证信息
const license = reactive({
  type: '企业版',
  maxUsers: '2000',
  expireDate: '2024-12-31',
  organization: 'AI教育科技有限公司',
  status: 'active',
  remainingDays: 320
})

// 刷新系统信息
const refreshSystemInfo = () => {
  console.log('刷新系统信息')
  // 这里应该调用API获取最新的系统信息
  
  // 模拟更新性能数据
  performance.cpuUsage = Math.floor(Math.random() * 100)
  performance.memoryUsage = Math.floor(Math.random() * 100)
  performance.diskUsage = Math.floor(Math.random() * 100)
  
  alert('系统信息已刷新！')
}

// 页面加载时获取系统信息
onMounted(() => {
  // 这里可以调用API获取实际的系统信息
  console.log('获取系统信息')
})
</script> 