<template>
  <div class="p-6 bg-gray-50 min-h-screen">
    <!-- 页面标题 -->
    <div class="mb-8">
      <h1 class="text-2xl font-bold text-gray-900 mb-2">视频资源</h1>
      <p class="text-gray-600">管理和播放教学视频资源</p>
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
          上传视频
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
            <option value="lecture">课堂录像</option>
            <option value="tutorial">教学辅导</option>
            <option value="experiment">实验演示</option>
          </select>
        </div>

        <!-- 搜索框 -->
        <div class="relative ml-auto">
          <i class="fas fa-search absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400"></i>
          <input
            v-model="searchTerm"
            type="text"
            placeholder="搜索视频..."
            class="pl-10 pr-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
          />
        </div>
      </div>
    </div>

    <!-- 视频列表 -->
    <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-6">
      <div 
        v-for="video in filteredVideos" 
        :key="video.id"
        class="bg-white rounded-lg shadow-sm border border-gray-200 overflow-hidden"
      >
        <!-- 视频缩略图 -->
        <div class="relative aspect-video bg-gray-100">
          <img 
            :src="video.thumbnail" 
            :alt="video.title"
            class="w-full h-full object-cover"
          />
          <div class="absolute bottom-2 right-2 bg-black bg-opacity-70 text-white text-xs px-2 py-1 rounded">
            {{ video.duration }}
          </div>
          <button 
            @click="playVideo(video)"
            class="absolute inset-0 flex items-center justify-center bg-black bg-opacity-0 hover:bg-opacity-30 transition-opacity"
          >
            <i class="fas fa-play-circle text-4xl text-white opacity-0 group-hover:opacity-100"></i>
          </button>
        </div>

        <!-- 视频信息 -->
        <div class="p-4">
          <h3 class="font-medium text-gray-900 mb-1 line-clamp-2">{{ video.title }}</h3>
          <p class="text-sm text-gray-500 mb-2">{{ video.course }}</p>
          <div class="flex items-center justify-between text-sm text-gray-500">
            <span>{{ video.uploadTime }}</span>
            <span>{{ video.views }}次观看</span>
          </div>
        </div>

        <!-- 操作按钮 -->
        <div class="px-4 py-3 border-t border-gray-200 flex justify-end space-x-2">
          <button 
            @click="downloadVideo(video)"
            class="text-gray-600 hover:text-blue-600"
            title="下载"
          >
            <i class="fas fa-download"></i>
          </button>
          <button 
            @click="shareVideo(video)"
            class="text-gray-600 hover:text-green-600"
            title="分享"
          >
            <i class="fas fa-share-alt"></i>
          </button>
          <button 
            @click="deleteVideo(video)"
            class="text-gray-600 hover:text-red-600"
            title="删除"
          >
            <i class="fas fa-trash-alt"></i>
          </button>
        </div>
      </div>
    </div>

    <!-- 视频播放模态框 -->
    <div v-if="showVideoModal" class="fixed inset-0 bg-black bg-opacity-75 flex items-center justify-center z-50">
      <div class="w-full max-w-4xl mx-4">
        <div class="bg-white rounded-lg overflow-hidden">
          <div class="relative aspect-video">
            <video
              ref="videoPlayer"
              :src="currentVideo.url"
              class="w-full h-full"
              controls
              autoplay
            ></video>
          </div>
          <div class="p-4">
            <div class="flex justify-between items-start mb-4">
              <div>
                <h3 class="text-lg font-semibold text-gray-900">{{ currentVideo.title }}</h3>
                <p class="text-sm text-gray-500">{{ currentVideo.course }}</p>
              </div>
              <button 
                @click="closeVideoModal"
                class="text-gray-400 hover:text-gray-600"
              >
                <i class="fas fa-times"></i>
              </button>
            </div>
            <p class="text-sm text-gray-600">{{ currentVideo.description }}</p>
          </div>
        </div>
      </div>
    </div>

    <!-- 上传视频模态框 -->
    <div v-if="showUploadModal" class="fixed inset-0 bg-gray-500 bg-opacity-50 flex items-center justify-center z-50">
      <div class="bg-white rounded-lg p-6 w-full max-w-lg mx-4">
        <div class="flex justify-between items-center mb-4">
          <h3 class="text-lg font-semibold text-gray-900">上传视频</h3>
          <button 
            @click="showUploadModal = false"
            class="text-gray-400 hover:text-gray-600"
          >
            <i class="fas fa-times"></i>
          </button>
        </div>

        <div class="space-y-4">
          <div>
            <label class="block text-sm font-medium text-gray-700 mb-1">视频标题</label>
            <input
              v-model="uploadForm.title"
              type="text"
              class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
              placeholder="输入视频标题"
            />
          </div>

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
            <label class="block text-sm font-medium text-gray-700 mb-1">视频类型</label>
            <select
              v-model="uploadForm.type"
              class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
            >
              <option value="">选择类型</option>
              <option value="lecture">课堂录像</option>
              <option value="tutorial">教学辅导</option>
              <option value="experiment">实验演示</option>
            </select>
          </div>

          <div>
            <label class="block text-sm font-medium text-gray-700 mb-1">视频描述</label>
            <textarea
              v-model="uploadForm.description"
              rows="3"
              class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
              placeholder="输入视频描述"
            ></textarea>
          </div>

          <div>
            <label class="block text-sm font-medium text-gray-700 mb-1">选择视频</label>
            <div class="border-2 border-dashed border-gray-300 rounded-lg p-6 text-center">
              <input
                type="file"
                ref="videoInput"
                @change="handleVideoSelect"
                class="hidden"
                accept="video/*"
              />
              <i class="fas fa-cloud-upload-alt text-4xl text-gray-400 mb-4"></i>
              <p class="text-gray-600 mb-2">拖拽视频到此处或点击选择视频</p>
              <button
                type="button"
                @click="$refs.videoInput.click()"
                class="text-blue-600 hover:text-blue-700 font-medium"
              >
                选择视频
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
            @click="uploadVideo"
            class="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700"
          >
            上传
          </button>
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
const showVideoModal = ref(false)

// 当前播放的视频
const currentVideo = ref(null)

// 上传表单
const uploadForm = ref({
  title: '',
  course: '',
  type: '',
  description: '',
  file: null
})

// 课程列表
const courses = [
  '高等数学',
  '线性代数',
  '概率论',
  '数据结构',
  '计算机网络'
]

// 视频列表
const videos = ref([
  {
    id: 1,
    title: '高等数学第一章：函数与极限',
    course: '高等数学',
    type: 'lecture',
    thumbnail: 'https://picsum.photos/400/225',
    duration: '45:30',
    uploadTime: '2024-03-15',
    views: 128,
    description: '本视频详细讲解了函数与极限的基本概念和性质，包括函数的定义、极限的定义和计算方法等。',
    url: 'https://example.com/video1.mp4'
  },
  {
    id: 2,
    title: '线性代数实验演示：矩阵运算',
    course: '线性代数',
    type: 'experiment',
    thumbnail: 'https://picsum.photos/400/225',
    duration: '32:15',
    uploadTime: '2024-03-14',
    views: 86,
    description: '通过实际演示帮助学生理解矩阵的基本运算，包括矩阵加法、乘法和转置等操作。',
    url: 'https://example.com/video2.mp4'
  },
  {
    id: 3,
    title: '概率论重点难点辅导',
    course: '概率论',
    type: 'tutorial',
    thumbnail: 'https://picsum.photos/400/225',
    duration: '28:45',
    uploadTime: '2024-03-13',
    views: 156,
    description: '针对概率论中的重点难点内容进行详细讲解，帮助学生更好地理解和掌握相关知识点。',
    url: 'https://example.com/video3.mp4'
  },
  {
    id: 4,
    title: '数据结构：红黑树详解',
    course: '数据结构',
    type: 'lecture',
    thumbnail: 'https://picsum.photos/400/225',
    duration: '52:20',
    uploadTime: '2024-03-12',
    views: 245,
    description: '详细讲解红黑树的概念、性质和基本操作，包括插入、删除等算法的实现过程。',
    url: 'https://example.com/video4.mp4'
  }
])

// 过滤后的视频列表
const filteredVideos = computed(() => {
  return videos.value.filter(video => {
    const matchSearch = video.title.toLowerCase().includes(searchTerm.value.toLowerCase())
    const matchCourse = !courseFilter.value || video.course === courseFilter.value
    const matchType = !typeFilter.value || video.type === typeFilter.value
    return matchSearch && matchCourse && matchType
  })
})

// 方法
const playVideo = (video) => {
  currentVideo.value = video
  showVideoModal.value = true
}

const closeVideoModal = () => {
  showVideoModal.value = false
  currentVideo.value = null
}

const handleVideoSelect = (event) => {
  uploadForm.value.file = event.target.files[0]
}

const uploadVideo = () => {
  // 实现视频上传逻辑
  console.log('上传视频:', uploadForm.value)
  showUploadModal.value = false
  // 重置表单
  uploadForm.value = {
    title: '',
    course: '',
    type: '',
    description: '',
    file: null
  }
}

const downloadVideo = (video) => {
  console.log('下载视频:', video)
}

const shareVideo = (video) => {
  console.log('分享视频:', video)
}

const deleteVideo = (video) => {
  console.log('删除视频:', video)
}
</script> 