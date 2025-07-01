<template>
  <div class="p-6 bg-gray-50 min-h-screen">
    <!-- 页面标题 -->
    <div class="mb-8">
      <h1 class="text-2xl font-bold text-gray-900 mb-2">文档资料</h1>
      <p class="text-gray-600">管理和查看教学相关的文档资料</p>
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
          上传文档
        </button>

        <!-- 新建文件夹 -->
        <button 
          @click="showNewFolderModal = true"
          class="bg-gray-100 text-gray-700 px-4 py-2 rounded-lg hover:bg-gray-200 flex items-center"
        >
          <i class="fas fa-folder-plus mr-2"></i>
          新建文件夹
        </button>

        <!-- 筛选 -->
        <div class="flex items-center space-x-4">
          <select 
            v-model="courseFilter"
            class="border border-gray-300 rounded-lg px-3 py-2 text-sm"
          >
            <option value="">所有课程</option>
            <option v-for="course in courses" :key="course" :value="course">{{ course }}</option>
          </select>

          <select 
            v-model="typeFilter"
            class="border border-gray-300 rounded-lg px-3 py-2 text-sm"
          >
            <option value="">所有类型</option>
            <option value="pdf">PDF文档</option>
            <option value="word">Word文档</option>
            <option value="excel">Excel表格</option>
            <option value="ppt">PPT演示文稿</option>
          </select>
        </div>

        <!-- 搜索框 -->
        <div class="relative ml-auto">
          <i class="fas fa-search absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400"></i>
          <input
            v-model="searchTerm"
            type="text"
            placeholder="搜索文档..."
            class="pl-10 pr-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
          />
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

    <!-- 文档列表 -->
    <div class="bg-white rounded-lg shadow-sm border border-gray-200">
      <div class="overflow-x-auto">
        <table class="min-w-full divide-y divide-gray-200">
          <thead>
            <tr>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">文件名</th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">所属课程</th>
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
                {{ folder.course || '-' }}
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                {{ folder.itemCount }}个项目
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                {{ folder.modifiedDate }}
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                <div class="flex space-x-2">
                  <button 
                    @click.stop="renameFolder(folder)"
                    class="text-gray-600 hover:text-blue-600"
                    title="重命名"
                  >
                    <i class="fas fa-pencil-alt"></i>
                  </button>
                  <button 
                    @click.stop="deleteFolder(folder)"
                    class="text-gray-600 hover:text-red-600"
                    title="删除"
                  >
                    <i class="fas fa-trash-alt"></i>
                  </button>
                </div>
              </td>
            </tr>

            <!-- 文件 -->
            <tr 
              v-for="doc in filteredDocuments" 
              :key="doc.id"
              class="hover:bg-gray-50"
            >
              <td class="px-6 py-4 whitespace-nowrap">
                <div class="flex items-center">
                  <i :class="[getFileIcon(doc.type), 'mr-2']"></i>
                  <span class="text-sm font-medium text-gray-900">{{ doc.name }}</span>
                </div>
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                {{ doc.course }}
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                {{ doc.size }}
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                {{ doc.modifiedDate }}
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                <div class="flex space-x-2">
                  <button 
                    @click="previewDocument(doc)"
                    class="text-gray-600 hover:text-blue-600"
                    title="预览"
                  >
                    <i class="fas fa-eye"></i>
                  </button>
                  <button 
                    @click="downloadDocument(doc)"
                    class="text-gray-600 hover:text-blue-600"
                    title="下载"
                  >
                    <i class="fas fa-download"></i>
                  </button>
                  <button 
                    @click="shareDocument(doc)"
                    class="text-gray-600 hover:text-green-600"
                    title="分享"
                  >
                    <i class="fas fa-share-alt"></i>
                  </button>
                  <button 
                    @click="deleteDocument(doc)"
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

    <!-- 上传文档模态框 -->
    <div v-if="showUploadModal" class="fixed inset-0 bg-gray-500 bg-opacity-50 flex items-center justify-center z-50">
      <div class="bg-white rounded-lg p-6 w-full max-w-lg mx-4">
        <div class="flex justify-between items-center mb-4">
          <h3 class="text-lg font-semibold text-gray-900">上传文档</h3>
          <button 
            @click="showUploadModal = false"
            class="text-gray-400 hover:text-gray-600"
          >
            <i class="fas fa-times"></i>
          </button>
        </div>

        <div class="space-y-4">
          <div>
            <label class="block text-sm font-medium text-gray-700 mb-1">所属课程</label>
            <select
              v-model="uploadForm.course"
              class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
            >
              <option value="">选择课程</option>
              <option v-for="course in courses" :key="course" :value="course">{{ course }}</option>
            </select>
          </div>

          <div>
            <label class="block text-sm font-medium text-gray-700 mb-1">文档描述</label>
            <textarea
              v-model="uploadForm.description"
              rows="3"
              class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
              placeholder="输入文档描述"
            ></textarea>
          </div>

          <div>
            <label class="block text-sm font-medium text-gray-700 mb-1">选择文件</label>
            <div class="border-2 border-dashed border-gray-300 rounded-lg p-6 text-center">
              <input
                type="file"
                ref="fileInput"
                @change="handleFileSelect"
                class="hidden"
                accept=".pdf,.doc,.docx,.xls,.xlsx,.ppt,.pptx"
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
        </div>

        <div class="flex justify-end space-x-4 mt-6">
          <button
            @click="showUploadModal = false"
            class="px-4 py-2 text-gray-700 border border-gray-300 rounded-lg hover:bg-gray-50"
          >
            取消
          </button>
          <button
            @click="uploadDocuments"
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

    <!-- 文档预览模态框 -->
    <div v-if="showPreviewModal" class="fixed inset-0 bg-gray-500 bg-opacity-50 flex items-center justify-center z-50">
      <div class="bg-white rounded-lg p-6 w-full max-w-4xl mx-4">
        <div class="flex justify-between items-center mb-4">
          <h3 class="text-lg font-semibold text-gray-900">{{ currentDocument?.name }}</h3>
          <button 
            @click="closePreview"
            class="text-gray-400 hover:text-gray-600"
          >
            <i class="fas fa-times"></i>
          </button>
        </div>

        <div class="h-[600px] bg-gray-100 rounded-lg flex items-center justify-center">
          <iframe
            v-if="currentDocument?.previewUrl"
            :src="currentDocument.previewUrl"
            class="w-full h-full rounded-lg"
            frameborder="0"
          ></iframe>
          <p v-else class="text-gray-500">预览不可用</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'

// 筛选状态
const searchTerm = ref('')
const courseFilter = ref('')
const typeFilter = ref('')

// 模态框状态
const showUploadModal = ref(false)
const showNewFolderModal = ref(false)
const showPreviewModal = ref(false)

// 当前路径
const currentPath = ref(['文档资料'])

// 当前预览的文档
const currentDocument = ref(null)

// 新文件夹名称
const newFolderName = ref('')

// 上传表单
const uploadForm = ref({
  course: '',
  description: '',
  files: []
})

// 选中的文件
const selectedFiles = ref([])

// 课程列表
const courses = [
  '高等数学',
  '线性代数',
  '概率论',
  '数据结构',
  '计算机网络'
]

// 文件夹列表
const folders = ref([
  { id: 1, name: '教学大纲', itemCount: 5, modifiedDate: '2024-03-15', course: '高等数学' },
  { id: 2, name: '教案', itemCount: 8, modifiedDate: '2024-03-14', course: '线性代数' },
  { id: 3, name: '习题', itemCount: 12, modifiedDate: '2024-03-13', course: '概率论' }
])

// 文档列表
const documents = ref([
  {
    id: 1,
    name: '高等数学教学大纲.pdf',
    type: 'pdf',
    size: '2.5MB',
    course: '高等数学',
    modifiedDate: '2024-03-15 14:30',
    previewUrl: 'https://example.com/preview/1'
  },
  {
    id: 2,
    name: '线性代数第一章教案.docx',
    type: 'word',
    size: '1.8MB',
    course: '线性代数',
    modifiedDate: '2024-03-14 16:20',
    previewUrl: 'https://example.com/preview/2'
  },
  {
    id: 3,
    name: '概率论期末试卷.pdf',
    type: 'pdf',
    size: '3.2MB',
    course: '概率论',
    modifiedDate: '2024-03-13 09:45',
    previewUrl: 'https://example.com/preview/3'
  },
  {
    id: 4,
    name: '数据结构实验报告模板.docx',
    type: 'word',
    size: '1.2MB',
    course: '数据结构',
    modifiedDate: '2024-03-12 11:15',
    previewUrl: 'https://example.com/preview/4'
  }
])

// 过滤后的文档列表
const filteredDocuments = computed(() => {
  return documents.value.filter(doc => {
    const matchSearch = doc.name.toLowerCase().includes(searchTerm.value.toLowerCase())
    const matchCourse = !courseFilter.value || doc.course === courseFilter.value
    const matchType = !typeFilter.value || doc.type === typeFilter.value
    return matchSearch && matchCourse && matchType
  })
})

// 方法
const navigateToRoot = () => {
  currentPath.value = ['文档资料']
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
    excel: 'fas fa-file-excel text-green-600',
    ppt: 'fas fa-file-powerpoint text-orange-600',
    default: 'fas fa-file text-gray-600'
  }
  return icons[type] || icons.default
}

const handleFileSelect = (event) => {
  const files = Array.from(event.target.files)
  selectedFiles.value = [...selectedFiles.value, ...files]
}

const removeSelectedFile = (index) => {
  selectedFiles.value.splice(index, 1)
}

const uploadDocuments = () => {
  // 实现文档上传逻辑
  console.log('上传文档:', {
    ...uploadForm.value,
    files: selectedFiles.value
  })
  showUploadModal.value = false
  selectedFiles.value = []
  uploadForm.value = {
    course: '',
    description: '',
    files: []
  }
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

const previewDocument = (doc) => {
  currentDocument.value = doc
  showPreviewModal.value = true
}

const closePreview = () => {
  showPreviewModal.value = false
  currentDocument.value = null
}

const downloadDocument = (doc) => {
  console.log('下载文档:', doc)
}

const shareDocument = (doc) => {
  console.log('分享文档:', doc)
}

const deleteDocument = (doc) => {
  console.log('删除文档:', doc)
}

const renameFolder = (folder) => {
  console.log('重命名文件夹:', folder)
}

const deleteFolder = (folder) => {
  console.log('删除文件夹:', folder)
}
</script> 