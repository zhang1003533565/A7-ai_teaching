<template>
  <div class="p-6 bg-gray-50 min-h-screen">
    <!-- 页面标题 -->
    <div class="mb-8">
      <h1 class="text-2xl font-bold text-gray-900 mb-2">教学材料</h1>
      <p class="text-gray-600">管理和组织您的教学材料资源</p>
    </div>

    <!-- 工具栏 -->
    <div class="bg-white p-4 rounded-lg shadow-sm border border-gray-200 mb-6">
      <div class="flex flex-wrap gap-4 items-center">
        <!-- 上传按钮 -->
        <button 
          @click="showUploadModal = true"
          class="bg-blue-600 text-white px-4 py-2 rounded-lg hover:bg-blue-700 flex items-center"
        >
          <i class="fas fa-upload mr-2"></i>
          上传文件
        </button>

        <!-- 新建文件夹 -->
        <button 
          @click="showNewFolderModal = true"
          class="bg-gray-100 text-gray-700 px-4 py-2 rounded-lg hover:bg-gray-200 flex items-center"
        >
          <i class="fas fa-folder-plus mr-2"></i>
          新建文件夹
        </button>

        <!-- 视图切换 -->
        <div class="flex items-center space-x-2 ml-auto">
          <button 
            @click="viewMode = 'grid'"
            :class="[
              'p-2 rounded-lg',
              viewMode === 'grid' ? 'bg-gray-200' : 'hover:bg-gray-100'
            ]"
          >
            <i class="fas fa-th-large"></i>
          </button>
          <button 
            @click="viewMode = 'list'"
            :class="[
              'p-2 rounded-lg',
              viewMode === 'list' ? 'bg-gray-200' : 'hover:bg-gray-100'
            ]"
          >
            <i class="fas fa-list"></i>
          </button>
        </div>
      </div>
    </div>

    <!-- 面包屑导航 -->
    <div class="bg-white px-4 py-2 rounded-lg shadow-sm border border-gray-200 mb-6">
      <div class="flex items-center space-x-2 text-sm">
        <span 
          class="text-blue-600 hover:text-blue-800 cursor-pointer"
          @click="navigateToRoot"
        >
          根目录
        </span>
        <template v-for="(folder, index) in currentPath" :key="index">
          <i class="fas fa-chevron-right text-gray-400"></i>
          <span 
            :class="[
              'cursor-pointer',
              index === currentPath.length - 1 ? 'text-gray-700' : 'text-blue-600 hover:text-blue-800'
            ]"
            @click="navigateToPath(index)"
          >
            {{ folder }}
          </span>
        </template>
      </div>
    </div>

    <!-- 文件列表 - 网格视图 -->
    <div v-if="viewMode === 'grid'" class="grid grid-cols-1 md:grid-cols-4 lg:grid-cols-6 gap-4">
      <!-- 文件夹 -->
      <div 
        v-for="folder in folders" 
        :key="folder.id"
        class="bg-white p-4 rounded-lg shadow-sm border border-gray-200 hover:shadow-md transition-shadow cursor-pointer"
        @click="openFolder(folder)"
      >
        <div class="flex flex-col items-center text-center">
          <div class="w-16 h-16 bg-yellow-100 rounded-lg flex items-center justify-center mb-2">
            <i class="fas fa-folder text-2xl text-yellow-600"></i>
          </div>
          <span class="text-sm font-medium text-gray-900 truncate w-full">{{ folder.name }}</span>
          <span class="text-xs text-gray-500">{{ folder.itemCount }}个项目</span>
        </div>
      </div>

      <!-- 文件 -->
      <div 
        v-for="file in files" 
        :key="file.id"
        class="bg-white p-4 rounded-lg shadow-sm border border-gray-200 hover:shadow-md transition-shadow"
      >
        <div class="flex flex-col items-center text-center">
          <div class="w-16 h-16 rounded-lg flex items-center justify-center mb-2" :class="getFileIconBg(file.type)">
            <i :class="['text-2xl', getFileIcon(file.type)]"></i>
          </div>
          <span class="text-sm font-medium text-gray-900 truncate w-full">{{ file.name }}</span>
          <span class="text-xs text-gray-500">{{ file.size }}</span>
        </div>
        <div class="mt-2 flex justify-center space-x-2">
          <button 
            @click.stop="downloadFile(file)"
            class="text-gray-600 hover:text-blue-600"
            title="下载"
          >
            <i class="fas fa-download"></i>
          </button>
          <button 
            @click.stop="shareFile(file)"
            class="text-gray-600 hover:text-green-600"
            title="分享"
          >
            <i class="fas fa-share-alt"></i>
          </button>
          <button 
            @click.stop="deleteFile(file)"
            class="text-gray-600 hover:text-red-600"
            title="删除"
          >
            <i class="fas fa-trash-alt"></i>
          </button>
        </div>
      </div>
    </div>

    <!-- 文件列表 - 列表视图 -->
    <div v-else class="bg-white rounded-lg shadow-sm border border-gray-200">
      <div class="overflow-x-auto">
        <table class="min-w-full divide-y divide-gray-200">
          <thead>
            <tr>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">名称</th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">大小</th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">修改日期</th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">操作</th>
            </tr>
          </thead>
          <tbody class="bg-white divide-y divide-gray-200">
            <!-- 文件夹 -->
            <tr 
              v-for="folder in folders" 
              :key="folder.id"
              class="hover:bg-gray-50 cursor-pointer"
              @click="openFolder(folder)"
            >
              <td class="px-6 py-4 whitespace-nowrap">
                <div class="flex items-center">
                  <i class="fas fa-folder text-yellow-600 mr-2"></i>
                  <span class="text-sm font-medium text-gray-900">{{ folder.name }}</span>
                </div>
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                {{ folder.itemCount }}个项目
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                {{ folder.modifiedDate }}
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                <div class="flex space-x-2">
                  <button class="text-gray-600 hover:text-blue-600">
                    <i class="fas fa-pencil-alt"></i>
                  </button>
                  <button class="text-gray-600 hover:text-red-600">
                    <i class="fas fa-trash-alt"></i>
                  </button>
                </div>
              </td>
            </tr>

            <!-- 文件 -->
            <tr 
              v-for="file in files" 
              :key="file.id"
              class="hover:bg-gray-50"
            >
              <td class="px-6 py-4 whitespace-nowrap">
                <div class="flex items-center">
                  <i :class="[getFileIcon(file.type), 'mr-2']"></i>
                  <span class="text-sm font-medium text-gray-900">{{ file.name }}</span>
                </div>
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                {{ file.size }}
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                {{ file.modifiedDate }}
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                <div class="flex space-x-2">
                  <button 
                    @click.stop="downloadFile(file)"
                    class="text-gray-600 hover:text-blue-600"
                    title="下载"
                  >
                    <i class="fas fa-download"></i>
                  </button>
                  <button 
                    @click.stop="shareFile(file)"
                    class="text-gray-600 hover:text-green-600"
                    title="分享"
                  >
                    <i class="fas fa-share-alt"></i>
                  </button>
                  <button 
                    @click.stop="deleteFile(file)"
                    class="text-gray-600 hover:text-red-600"
                    title="删除"
                  >
                    <i class="fas fa-trash-alt"></i>
                  </button>
                </div>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <!-- 上传文件模态框 -->
    <div v-if="showUploadModal" class="fixed inset-0 bg-gray-500 bg-opacity-50 flex items-center justify-center z-50">
      <div class="bg-white rounded-lg p-6 w-full max-w-lg mx-4">
        <div class="flex justify-between items-center mb-4">
          <h3 class="text-lg font-semibold text-gray-900">上传文件</h3>
          <button 
            @click="showUploadModal = false"
            class="text-gray-400 hover:text-gray-600"
          >
            <i class="fas fa-times"></i>
          </button>
        </div>

        <div class="border-2 border-dashed border-gray-300 rounded-lg p-6 text-center">
          <input
            type="file"
            ref="fileInput"
            @change="handleFileSelect"
            class="hidden"
            multiple
          />
          <i class="fas fa-cloud-upload-alt text-4xl text-gray-400 mb-4"></i>
          <p class="text-gray-600 mb-2">拖拽文件到此处或点击选择文件</p>
          <button
            type="button"
            @click="$refs.fileInput.click()"
            class="text-blue-600 hover:text-blue-700 font-medium"
          >
            选择文件
          </button>
        </div>

        <div v-if="selectedFiles.length > 0" class="mt-4 space-y-2">
          <div 
            v-for="(file, index) in selectedFiles" 
            :key="index"
            class="flex items-center justify-between p-2 bg-gray-50 rounded"
          >
            <div class="flex items-center">
              <i class="fas fa-file mr-2 text-gray-500"></i>
              <span class="text-sm">{{ file.name }}</span>
            </div>
            <button
              @click="removeSelectedFile(index)"
              class="text-red-600 hover:text-red-800"
            >
              <i class="fas fa-times"></i>
            </button>
          </div>
        </div>

        <div class="flex justify-end space-x-4 mt-6">
          <button
            @click="showUploadModal = false"
            class="px-4 py-2 text-gray-700 border border-gray-300 rounded-lg hover:bg-gray-50"
          >
            取消
          </button>
          <button
            @click="uploadFiles"
            class="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700"
          >
            上传
          </button>
        </div>
      </div>
    </div>

    <!-- 新建文件夹模态框 -->
    <div v-if="showNewFolderModal" class="fixed inset-0 bg-gray-500 bg-opacity-50 flex items-center justify-center z-50">
      <div class="bg-white rounded-lg p-6 w-full max-w-md mx-4">
        <div class="flex justify-between items-center mb-4">
          <h3 class="text-lg font-semibold text-gray-900">新建文件夹</h3>
          <button 
            @click="showNewFolderModal = false"
            class="text-gray-400 hover:text-gray-600"
          >
            <i class="fas fa-times"></i>
          </button>
        </div>

        <div class="mb-4">
          <label class="block text-sm font-medium text-gray-700 mb-1">文件夹名称</label>
          <input
            v-model="newFolderName"
            type="text"
            class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
            placeholder="输入文件夹名称"
          />
        </div>

        <div class="flex justify-end space-x-4">
          <button
            @click="showNewFolderModal = false"
            class="px-4 py-2 text-gray-700 border border-gray-300 rounded-lg hover:bg-gray-50"
          >
            取消
          </button>
          <button
            @click="createFolder"
            class="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700"
          >
            创建
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'

// 视图模式
const viewMode = ref('grid')

// 当前路径
const currentPath = ref(['教学材料'])

// 模态框状态
const showUploadModal = ref(false)
const showNewFolderModal = ref(false)

// 新文件夹名称
const newFolderName = ref('')

// 选中的文件
const selectedFiles = ref([])

// 模拟数据
const folders = ref([
  { id: 1, name: '课件', itemCount: 15, modifiedDate: '2024-03-15' },
  { id: 2, name: '教案', itemCount: 8, modifiedDate: '2024-03-14' },
  { id: 3, name: '习题', itemCount: 12, modifiedDate: '2024-03-13' },
  { id: 4, name: '实验指导', itemCount: 6, modifiedDate: '2024-03-12' }
])

const files = ref([
  { 
    id: 1, 
    name: '高等数学第一章.pptx', 
    type: 'ppt',
    size: '2.5MB',
    modifiedDate: '2024-03-15 14:30'
  },
  { 
    id: 2, 
    name: '线性代数教案.docx', 
    type: 'word',
    size: '1.8MB',
    modifiedDate: '2024-03-14 16:20'
  },
  { 
    id: 3, 
    name: '概率论习题集.pdf', 
    type: 'pdf',
    size: '3.2MB',
    modifiedDate: '2024-03-13 09:45'
  },
  { 
    id: 4, 
    name: '数据结构实验报告模板.docx', 
    type: 'word',
    size: '1.2MB',
    modifiedDate: '2024-03-12 11:15'
  }
])

// 方法
const navigateToRoot = () => {
  currentPath.value = ['教学材料']
}

const navigateToPath = (index) => {
  currentPath.value = currentPath.value.slice(0, index + 1)
}

const openFolder = (folder) => {
  currentPath.value.push(folder.name)
}

const getFileIcon = (type) => {
  const icons = {
    pdf: 'fas fa-file-pdf text-red-600',
    word: 'fas fa-file-word text-blue-600',
    ppt: 'fas fa-file-powerpoint text-orange-600',
    excel: 'fas fa-file-excel text-green-600',
    image: 'fas fa-file-image text-purple-600',
    video: 'fas fa-file-video text-pink-600',
    default: 'fas fa-file text-gray-600'
  }
  return icons[type] || icons.default
}

const getFileIconBg = (type) => {
  const backgrounds = {
    pdf: 'bg-red-100',
    word: 'bg-blue-100',
    ppt: 'bg-orange-100',
    excel: 'bg-green-100',
    image: 'bg-purple-100',
    video: 'bg-pink-100',
    default: 'bg-gray-100'
  }
  return backgrounds[type] || backgrounds.default
}

const handleFileSelect = (event) => {
  const files = Array.from(event.target.files)
  selectedFiles.value = [...selectedFiles.value, ...files]
}

const removeSelectedFile = (index) => {
  selectedFiles.value.splice(index, 1)
}

const uploadFiles = () => {
  // 实现文件上传逻辑
  console.log('上传文件:', selectedFiles.value)
  showUploadModal.value = false
  selectedFiles.value = []
}

const createFolder = () => {
  if (newFolderName.value.trim()) {
    folders.value.push({
      id: folders.value.length + 1,
      name: newFolderName.value,
      itemCount: 0,
      modifiedDate: new Date().toISOString().split('T')[0]
    })
    showNewFolderModal.value = false
    newFolderName.value = ''
  }
}

const downloadFile = (file) => {
  console.log('下载文件:', file)
}

const shareFile = (file) => {
  console.log('分享文件:', file)
}

const deleteFile = (file) => {
  console.log('删除文件:', file)
}
</script> 