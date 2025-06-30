<!-- 作业发布页面 - 创建和发布新作业 -->
<template>
  <div class="p-6 bg-gray-50 min-h-screen">
    <!-- 页面标题 -->
    <div class="mb-8">
      <h1 class="text-2xl font-bold text-gray-900 mb-2">作业发布</h1>
      <p class="text-gray-600">创建和发布新的作业任务</p>
    </div>

    <div class="bg-white p-6 rounded-lg shadow-sm border border-gray-200">
      <h2 class="text-lg font-semibold text-gray-900 mb-4">作业发布功能</h2>
      <p class="text-gray-600">在这里创建和发布作业任务。</p>
      
      <div class="mt-6 grid grid-cols-1 md:grid-cols-3 gap-4">
        <div class="p-4 border border-gray-200 rounded-lg">
          <i class="fas fa-plus-circle text-2xl text-blue-600 mb-2"></i>
          <h3 class="font-medium text-gray-900">创建作业</h3>
          <p class="text-sm text-gray-600">创建新的作业任务</p>
        </div>
        
        <div class="p-4 border border-gray-200 rounded-lg">
          <i class="fas fa-clock text-2xl text-green-600 mb-2"></i>
          <h3 class="font-medium text-gray-900">定时发布</h3>
          <p class="text-sm text-gray-600">设置作业发布时间</p>
        </div>
        
        <div class="p-4 border border-gray-200 rounded-lg">
          <i class="fas fa-copy text-2xl text-purple-600 mb-2"></i>
          <h3 class="font-medium text-gray-900">模板管理</h3>
          <p class="text-sm text-gray-600">使用作业模板快速创建</p>
        </div>
      </div>
    </div>

    <div class="grid grid-cols-1 lg:grid-cols-4 gap-8">
      <!-- 主要表单区域 -->
      <div class="lg:col-span-3">
        <form @submit.prevent="publishHomework" class="space-y-6">
          <!-- 基本信息 -->
          <div class="bg-white p-6 rounded-lg shadow-sm border border-gray-200">
            <h2 class="text-lg font-semibold text-gray-900 mb-4">基本信息</h2>
            
            <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
              <div>
                <label class="block text-sm font-medium text-gray-700 mb-2">作业标题 *</label>
                <input
                  v-model="homeworkForm.title"
                  type="text"
                  required
                  class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
                  placeholder="请输入作业标题"
                />
              </div>
              
              <div>
                <label class="block text-sm font-medium text-gray-700 mb-2">所属课程 *</label>
                <select
                  v-model="homeworkForm.courseId"
                  required
                  class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
                >
                  <option value="">请选择课程</option>
                  <option v-for="course in courses" :key="course.id" :value="course.id">
                    {{ course.name }}
                  </option>
                </select>
              </div>

              <div>
                <label class="block text-sm font-medium text-gray-700 mb-2">作业类型 *</label>
                <select
                  v-model="homeworkForm.type"
                  required
                  class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
                >
                  <option value="">请选择类型</option>
                  <option v-for="type in homeworkTypes" :key="type.value" :value="type.value">
                    {{ type.label }}
                  </option>
                </select>
              </div>

              <div>
                <label class="block text-sm font-medium text-gray-700 mb-2">满分 *</label>
                <input
                  v-model="homeworkForm.totalScore"
                  type="number"
                  min="1"
                  max="1000"
                  required
                  class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
                  placeholder="100"
                />
              </div>
            </div>

            <div class="mt-6">
              <label class="block text-sm font-medium text-gray-700 mb-2">作业描述 *</label>
              <textarea
                v-model="homeworkForm.description"
                rows="4"
                required
                class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
                placeholder="请详细描述作业要求和注意事项..."
              ></textarea>
            </div>
          </div>

          <!-- 时间设置 -->
          <div class="bg-white p-6 rounded-lg shadow-sm border border-gray-200">
            <h2 class="text-lg font-semibold text-gray-900 mb-4">时间设置</h2>
            
            <div class="grid grid-cols-1 md:grid-cols-3 gap-6">
              <div>
                <label class="block text-sm font-medium text-gray-700 mb-2">发布时间</label>
                <input
                  v-model="homeworkForm.publishTime"
                  type="datetime-local"
                  class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
                />
                <p class="text-xs text-gray-500 mt-1">留空则立即发布</p>
              </div>

              <div>
                <label class="block text-sm font-medium text-gray-700 mb-2">截止时间 *</label>
                <input
                  v-model="homeworkForm.dueTime"
                  type="datetime-local"
                  required
                  class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
                />
              </div>

              <div>
                <label class="block text-sm font-medium text-gray-700 mb-2">延期截止时间</label>
                <input
                  v-model="homeworkForm.lateDueTime"
                  type="datetime-local"
                  class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
                />
                <p class="text-xs text-gray-500 mt-1">允许延期提交的时间</p>
              </div>
            </div>
          </div>

          <!-- 提交设置 -->
          <div class="bg-white p-6 rounded-lg shadow-sm border border-gray-200">
            <h2 class="text-lg font-semibold text-gray-900 mb-4">提交设置</h2>
            
            <div class="space-y-4">
              <div class="flex items-center">
                <input
                  v-model="homeworkForm.allowLateSubmission"
                  type="checkbox"
                  class="rounded border-gray-300 text-blue-600 focus:ring-blue-500"
                />
                <label class="ml-2 text-sm text-gray-700">允许延期提交</label>
              </div>

              <div class="flex items-center">
                <input
                  v-model="homeworkForm.allowResubmission"
                  type="checkbox"
                  class="rounded border-gray-300 text-blue-600 focus:ring-blue-500"
                />
                <label class="ml-2 text-sm text-gray-700">允许重复提交</label>
              </div>

              <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                <div>
                  <label class="block text-sm font-medium text-gray-700 mb-2">最大提交次数</label>
                  <input
                    v-model="homeworkForm.maxSubmissions"
                    type="number"
                    min="1"
                    class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
                    placeholder="不限制"
                  />
                </div>

                <div>
                  <label class="block text-sm font-medium text-gray-700 mb-2">文件大小限制 (MB)</label>
                  <input
                    v-model="homeworkForm.fileSizeLimit"
                    type="number"
                    min="1"
                    max="100"
                    class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
                    placeholder="10"
                  />
                </div>
              </div>

              <div>
                <label class="block text-sm font-medium text-gray-700 mb-2">允许的文件类型</label>
                <div class="grid grid-cols-2 md:grid-cols-4 gap-3">
                  <div v-for="fileType in fileTypes" :key="fileType.value" class="flex items-center">
                    <input
                      v-model="homeworkForm.allowedFileTypes"
                      :value="fileType.value"
                      type="checkbox"
                      class="rounded border-gray-300 text-blue-600 focus:ring-blue-500"
                    />
                    <label class="ml-2 text-sm text-gray-700">{{ fileType.label }}</label>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <!-- 评分设置 -->
          <div class="bg-white p-6 rounded-lg shadow-sm border border-gray-200">
            <h2 class="text-lg font-semibold text-gray-900 mb-4">评分设置</h2>
            
            <div class="space-y-4">
              <div>
                <label class="block text-sm font-medium text-gray-700 mb-2">评分标准</label>
                <textarea
                  v-model="homeworkForm.gradingCriteria"
                  rows="3"
                  class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
                  placeholder="请输入详细的评分标准..."
                ></textarea>
              </div>

              <div class="flex items-center">
                <input
                  v-model="homeworkForm.enablePeerReview"
                  type="checkbox"
                  class="rounded border-gray-300 text-blue-600 focus:ring-blue-500"
                />
                <label class="ml-2 text-sm text-gray-700">启用同伴评议</label>
              </div>

              <div v-if="homeworkForm.enablePeerReview" class="ml-6 space-y-3">
                <div>
                  <label class="block text-sm font-medium text-gray-700 mb-2">每人需要评议的作业数</label>
                  <input
                    v-model="homeworkForm.peerReviewCount"
                    type="number"
                    min="1"
                    max="10"
                    class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
                    placeholder="3"
                  />
                </div>
              </div>
            </div>
          </div>

          <!-- 附件上传 -->
          <div class="bg-white p-6 rounded-lg shadow-sm border border-gray-200">
            <h2 class="text-lg font-semibold text-gray-900 mb-4">作业附件</h2>
            
            <div class="border-2 border-dashed border-gray-300 rounded-lg p-6 text-center">
              <input
                type="file"
                ref="fileInput"
                @change="handleFileSelect"
                class="hidden"
                multiple
              />
              <i class="fas fa-upload text-4xl text-gray-400 mb-4"></i>
              <p class="text-gray-600 mb-2">拖拽文件到此处或点击选择文件</p>
              <button
                type="button"
                @click="$refs.fileInput.click()"
                class="text-blue-600 hover:text-blue-700 font-medium"
              >
                选择文件
              </button>
            </div>

            <!-- 已选文件列表 -->
            <div v-if="selectedFiles.length > 0" class="mt-4 space-y-2">
              <div v-for="(file, index) in selectedFiles" :key="index" 
                   class="flex items-center justify-between p-3 bg-gray-50 rounded-lg">
                <div class="flex items-center">
                  <i class="fas fa-file mr-2 text-gray-500"></i>
                  <span class="text-sm font-medium">{{ file.name }}</span>
                  <span class="text-xs text-gray-500 ml-2">({{ formatFileSize(file.size) }})</span>
                </div>
                <button
                  type="button"
                  @click="removeFile(index)"
                  class="text-red-600 hover:text-red-800"
                >
                  <i class="fas fa-times"></i>
                </button>
              </div>
            </div>
          </div>

          <!-- 操作按钮 -->
          <div class="flex justify-end space-x-4">
            <button
              type="button"
              @click="saveDraft"
              class="px-6 py-2 text-gray-700 border border-gray-300 rounded-lg hover:bg-gray-50 transition-colors"
            >
              保存草稿
            </button>
            <button
              type="button"
              @click="previewHomework"
              class="px-6 py-2 bg-gray-600 text-white rounded-lg hover:bg-gray-700 transition-colors"
            >
              预览作业
            </button>
            <button
              type="submit"
              class="px-6 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition-colors"
            >
              {{ homeworkForm.publishTime ? '定时发布' : '立即发布' }}
            </button>
          </div>
        </form>
      </div>

      <!-- 右侧信息面板 -->
      <div class="space-y-6">
        <!-- 发布提示 -->
        <div class="bg-blue-50 p-4 rounded-lg border border-blue-200">
          <h3 class="font-medium text-blue-900 mb-2">
            <i class="fas fa-info-circle mr-2"></i>发布提示
          </h3>
          <ul class="text-sm text-blue-800 space-y-1">
            <li>• 发布后学生将收到通知</li>
            <li>• 可设置定时发布功能</li>
            <li>• 建议提前检查作业要求</li>
            <li>• 支持发布后修改基本信息</li>
          </ul>
        </div>

        <!-- 作业统计 -->
        <div class="bg-white p-4 rounded-lg shadow-sm border border-gray-200">
          <h3 class="font-medium text-gray-900 mb-3">作业统计</h3>
          <div class="space-y-2 text-sm">
            <div class="flex justify-between">
              <span class="text-gray-600">本月已发布</span>
              <span class="font-medium">12 个</span>
            </div>
            <div class="flex justify-between">
              <span class="text-gray-600">待批改作业</span>
              <span class="font-medium text-orange-600">8 个</span>
            </div>
            <div class="flex justify-between">
              <span class="text-gray-600">平均完成率</span>
              <span class="font-medium text-green-600">87%</span>
            </div>
          </div>
        </div>

        <!-- 快捷模板 -->
        <div class="bg-white p-4 rounded-lg shadow-sm border border-gray-200">
          <h3 class="font-medium text-gray-900 mb-3">快捷模板</h3>
          <div class="space-y-2">
            <button 
              v-for="template in templates"
              :key="template.id"
              @click="applyTemplate(template)"
              class="w-full text-left p-2 text-sm text-gray-700 hover:bg-gray-100 rounded"
            >
              {{ template.name }}
            </button>
          </div>
        </div>
      </div>
    </div>

    <!-- 预览模态框 -->
    <div v-if="showPreview" class="fixed inset-0 bg-gray-500 bg-opacity-50 flex items-center justify-center z-50">
      <div class="bg-white rounded-lg p-6 w-full max-w-4xl mx-4 max-h-[90vh] overflow-y-auto">
        <div class="flex justify-between items-center mb-6">
          <h3 class="text-lg font-semibold text-gray-900">作业预览</h3>
          <button 
            @click="showPreview = false"
            class="text-gray-400 hover:text-gray-600"
          >
            <i class="fas fa-times"></i>
          </button>
        </div>

        <div class="prose max-w-none">
          <h2>{{ homeworkForm.title }}</h2>
          <div class="bg-gray-50 p-4 rounded-lg mb-4">
            <div class="grid grid-cols-2 gap-4 text-sm">
              <div><strong>课程:</strong> {{ getCourseNameById(homeworkForm.courseId) }}</div>
              <div><strong>类型:</strong> {{ getTypeLabel(homeworkForm.type) }}</div>
              <div><strong>满分:</strong> {{ homeworkForm.totalScore }} 分</div>
              <div><strong>截止时间:</strong> {{ homeworkForm.dueTime }}</div>
            </div>
          </div>
          <div class="whitespace-pre-wrap">{{ homeworkForm.description }}</div>
          
          <div v-if="homeworkForm.gradingCriteria" class="mt-4">
            <h3>评分标准</h3>
            <div class="whitespace-pre-wrap">{{ homeworkForm.gradingCriteria }}</div>
          </div>
        </div>

        <div class="flex justify-end space-x-4 pt-4 border-t">
          <button
            @click="showPreview = false"
            class="px-4 py-2 text-gray-700 border border-gray-300 rounded-lg hover:bg-gray-50"
          >
            关闭预览
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive } from 'vue'

// 表单数据
const homeworkForm = reactive({
  title: '',
  courseId: '',
  type: '',
  totalScore: 100,
  description: '',
  publishTime: '',
  dueTime: '',
  lateDueTime: '',
  allowLateSubmission: false,
  allowResubmission: true,
  maxSubmissions: '',
  fileSizeLimit: 10,
  allowedFileTypes: [],
  gradingCriteria: '',
  enablePeerReview: false,
  peerReviewCount: 3
})

// 状态
const selectedFiles = ref([])
const showPreview = ref(false)

// 基础数据
const courses = ref([
  { id: 1, name: '高等数学A' },
  { id: 2, name: '线性代数' },
  { id: 3, name: '概率论与数理统计' }
])

const homeworkTypes = ref([
  { value: 'exercise', label: '练习题' },
  { value: 'programming', label: '编程作业' },
  { value: 'report', label: '实验报告' },
  { value: 'essay', label: '论文写作' },
  { value: 'project', label: '项目作业' }
])

const fileTypes = ref([
  { value: 'pdf', label: 'PDF' },
  { value: 'doc', label: 'Word' },
  { value: 'txt', label: '文本' },
  { value: 'zip', label: '压缩包' },
  { value: 'jpg', label: '图片' },
  { value: 'code', label: '代码文件' }
])

const templates = ref([
  { id: 1, name: '数学练习题模板' },
  { id: 2, name: '编程作业模板' },
  { id: 3, name: '实验报告模板' },
  { id: 4, name: '论文写作模板' }
])

// 方法
const handleFileSelect = (event) => {
  const files = Array.from(event.target.files)
  selectedFiles.value = [...selectedFiles.value, ...files]
}

const removeFile = (index) => {
  selectedFiles.value.splice(index, 1)
}

const formatFileSize = (bytes) => {
  if (bytes === 0) return '0 Bytes'
  const k = 1024
  const sizes = ['Bytes', 'KB', 'MB', 'GB']
  const i = Math.floor(Math.log(bytes) / Math.log(k))
  return parseFloat((bytes / Math.pow(k, i)).toFixed(2)) + ' ' + sizes[i]
}

const getCourseNameById = (courseId) => {
  const course = courses.value.find(c => c.id == courseId)
  return course ? course.name : ''
}

const getTypeLabel = (typeValue) => {
  const type = homeworkTypes.value.find(t => t.value === typeValue)
  return type ? type.label : ''
}

const applyTemplate = (template) => {
  console.log('应用模板:', template)
  // 这里可以根据模板填充表单数据
}

const saveDraft = () => {
  console.log('保存草稿')
  // 保存草稿逻辑
}

const previewHomework = () => {
  showPreview.value = true
}

const publishHomework = () => {
  console.log('发布作业:', homeworkForm)
  // 发布作业逻辑
}
</script> 