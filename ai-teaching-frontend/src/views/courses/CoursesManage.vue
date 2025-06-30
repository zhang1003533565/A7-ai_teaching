<!-- 课程管理页面 - 课程的增删改查功能 -->
<template>
  <div class="p-6 bg-gray-50 min-h-screen">
    <!-- 页面标题和操作 -->
    <div class="flex justify-between items-center mb-8">
      <div>
        <h1 class="text-2xl font-bold text-gray-900 mb-2">课程管理</h1>
        <p class="text-gray-600">创建和管理您的课程信息</p>
      </div>
      <button 
        @click="showCreateModal = true"
        class="bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded-lg flex items-center transition-colors"
      >
        <i class="fas fa-plus mr-2"></i>
        创建课程
      </button>
    </div>

    <!-- 搜索和筛选 -->
    <div class="bg-white p-6 rounded-lg shadow-sm border border-gray-200 mb-6">
      <div class="grid grid-cols-1 md:grid-cols-4 gap-4">
        <!-- 搜索框 -->
        <div class="relative">
          <i class="fas fa-search absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400"></i>
          <input
            v-model="searchTerm"
            type="text"
            placeholder="搜索课程名称..."
            class="w-full pl-10 pr-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
          />
        </div>

        <!-- 状态筛选 -->
        <select
          v-model="statusFilter"
          class="px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
        >
          <option value="">全部状态</option>
          <option value="active">进行中</option>
          <option value="finished">已结束</option>
          <option value="draft">草稿</option>
        </select>

        <!-- 学期筛选 -->
        <select
          v-model="semesterFilter"
          class="px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
        >
          <option value="">全部学期</option>
          <option value="2024-1">2024春季</option>
          <option value="2024-2">2024秋季</option>
          <option value="2023-2">2023秋季</option>
        </select>

        <!-- 重置按钮 -->
        <button
          @click="resetFilters"
          class="px-4 py-2 border border-gray-300 rounded-lg hover:bg-gray-50 transition-colors"
        >
          重置筛选
        </button>
      </div>
    </div>

    <!-- 课程列表 -->
    <div class="bg-white rounded-lg shadow-sm border border-gray-200">
      <div class="p-6 border-b border-gray-200">
        <div class="flex justify-between items-center">
          <h2 class="text-lg font-semibold text-gray-900">课程列表</h2>
          <span class="text-sm text-gray-600">共 {{ filteredCourses.length }} 门课程</span>
        </div>
      </div>

      <div class="overflow-x-auto">
        <table class="min-w-full divide-y divide-gray-200">
          <thead class="bg-gray-50">
            <tr>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                课程信息
              </th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                学期
              </th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                学生数
              </th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                状态
              </th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                创建时间
              </th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                操作
              </th>
            </tr>
          </thead>
          <tbody class="bg-white divide-y divide-gray-200">
            <tr v-for="course in paginatedCourses" :key="course.id" class="hover:bg-gray-50">
              <td class="px-6 py-4 whitespace-nowrap">
                <div class="flex items-center">
                  <div class="w-10 h-10 bg-blue-100 rounded-lg flex items-center justify-center">
                    <i class="fas fa-book text-blue-600"></i>
                  </div>
                  <div class="ml-4">
                    <div class="text-sm font-medium text-gray-900">{{ course.name }}</div>
                    <div class="text-sm text-gray-500">{{ course.code }}</div>
                  </div>
                </div>
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
                {{ course.semester }}
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
                {{ course.students }}
              </td>
              <td class="px-6 py-4 whitespace-nowrap">
                <span 
                  class="px-2 py-1 inline-flex text-xs leading-5 font-semibold rounded-full"
                  :class="getStatusClass(course.status)"
                >
                  {{ getStatusText(course.status) }}
                </span>
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
                {{ course.createdAt }}
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm font-medium">
                <div class="flex space-x-2">
                  <button 
                    @click="editCourse(course)"
                    class="text-blue-600 hover:text-blue-900"
                  >
                    编辑
                  </button>
                  <button 
                    @click="duplicateCourse(course)"
                    class="text-green-600 hover:text-green-900"
                  >
                    复制
                  </button>
                  <button 
                    @click="deleteCourse(course)"
                    class="text-red-600 hover:text-red-900"
                  >
                    删除
                  </button>
                </div>
              </td>
            </tr>
          </tbody>
        </table>
      </div>

      <!-- 分页 -->
      <div class="px-6 py-3 flex items-center justify-between border-t border-gray-200">
        <div class="flex-1 flex justify-between sm:hidden">
          <button 
            @click="currentPage > 1 && currentPage--"
            :disabled="currentPage === 1"
            class="relative inline-flex items-center px-4 py-2 border border-gray-300 text-sm font-medium rounded-md text-gray-700 bg-white hover:bg-gray-50 disabled:opacity-50"
          >
            上一页
          </button>
          <button 
            @click="currentPage < totalPages && currentPage++"
            :disabled="currentPage === totalPages"
            class="ml-3 relative inline-flex items-center px-4 py-2 border border-gray-300 text-sm font-medium rounded-md text-gray-700 bg-white hover:bg-gray-50 disabled:opacity-50"
          >
            下一页
          </button>
        </div>
        <div class="hidden sm:flex-1 sm:flex sm:items-center sm:justify-between">
          <div>
            <p class="text-sm text-gray-700">
              显示第 <span class="font-medium">{{ (currentPage - 1) * pageSize + 1 }}</span> 到 
              <span class="font-medium">{{ Math.min(currentPage * pageSize, filteredCourses.length) }}</span> 条，
              共 <span class="font-medium">{{ filteredCourses.length }}</span> 条记录
            </p>
          </div>
          <div class="flex space-x-2">
            <button 
              v-for="page in visiblePages" 
              :key="page"
              @click="currentPage = page"
              class="px-3 py-2 text-sm font-medium rounded-md"
              :class="page === currentPage ? 'bg-blue-600 text-white' : 'text-gray-700 hover:bg-gray-100'"
            >
              {{ page }}
            </button>
          </div>
        </div>
      </div>
    </div>

    <!-- 创建/编辑课程模态框 -->
    <div v-if="showCreateModal || showEditModal" class="fixed inset-0 bg-gray-500 bg-opacity-50 flex items-center justify-center z-50">
      <div class="bg-white rounded-lg p-6 w-full max-w-2xl mx-4">
        <div class="flex justify-between items-center mb-6">
          <h3 class="text-lg font-semibold text-gray-900">
            {{ showCreateModal ? '创建课程' : '编辑课程' }}
          </h3>
          <button 
            @click="closeModal"
            class="text-gray-400 hover:text-gray-600"
          >
            <i class="fas fa-times"></i>
          </button>
        </div>

        <form @submit.prevent="saveCourse" class="space-y-4">
          <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">课程名称</label>
              <input
                v-model="courseForm.name"
                type="text"
                required
                class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
              />
            </div>
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">课程代码</label>
              <input
                v-model="courseForm.code"
                type="text"
                required
                class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
              />
            </div>
          </div>

          <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">学期</label>
              <select
                v-model="courseForm.semester"
                required
                class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
              >
                <option value="">选择学期</option>
                <option value="2024-1">2024春季</option>
                <option value="2024-2">2024秋季</option>
              </select>
            </div>
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">学分</label>
              <input
                v-model="courseForm.credits"
                type="number"
                min="1"
                max="10"
                class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
              />
            </div>
          </div>

          <div>
            <label class="block text-sm font-medium text-gray-700 mb-1">课程描述</label>
            <textarea
              v-model="courseForm.description"
              rows="3"
              class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
            ></textarea>
          </div>

          <div class="flex justify-end space-x-4 pt-4">
            <button
              type="button"
              @click="closeModal"
              class="px-4 py-2 text-gray-700 border border-gray-300 rounded-lg hover:bg-gray-50 transition-colors"
            >
              取消
            </button>
            <button
              type="submit"
              class="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition-colors"
            >
              {{ showCreateModal ? '创建' : '保存' }}
            </button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive, computed, onMounted } from 'vue'

// 模态框状态
const showCreateModal = ref(false)
const showEditModal = ref(false)

// 搜索和筛选
const searchTerm = ref('')
const statusFilter = ref('')
const semesterFilter = ref('')

// 分页
const currentPage = ref(1)
const pageSize = ref(10)

// 课程表单
const courseForm = reactive({
  id: null,
  name: '',
  code: '',
  semester: '',
  credits: 3,
  description: ''
})

// 课程数据
const courses = ref([
  {
    id: 1,
    name: '高等数学A',
    code: 'MATH101',
    semester: '2024-1',
    students: 45,
    status: 'active',
    createdAt: '2024-02-15',
    credits: 4,
    description: '高等数学基础课程'
  },
  {
    id: 2,
    name: '线性代数',
    code: 'MATH201',
    semester: '2024-1',
    students: 38,
    status: 'active',
    createdAt: '2024-02-10',
    credits: 3,
    description: '线性代数基础'
  },
  {
    id: 3,
    name: '概率论与数理统计',
    code: 'MATH301',
    semester: '2023-2',
    students: 42,
    status: 'finished',
    createdAt: '2023-09-01',
    credits: 3,
    description: '概率论基础课程'
  }
])

// 计算属性
const filteredCourses = computed(() => {
  return courses.value.filter(course => {
    const matchesSearch = course.name.toLowerCase().includes(searchTerm.value.toLowerCase()) ||
                         course.code.toLowerCase().includes(searchTerm.value.toLowerCase())
    const matchesStatus = !statusFilter.value || course.status === statusFilter.value
    const matchesSemester = !semesterFilter.value || course.semester === semesterFilter.value
    
    return matchesSearch && matchesStatus && matchesSemester
  })
})

const totalPages = computed(() => Math.ceil(filteredCourses.value.length / pageSize.value))

const paginatedCourses = computed(() => {
  const start = (currentPage.value - 1) * pageSize.value
  const end = start + pageSize.value
  return filteredCourses.value.slice(start, end)
})

const visiblePages = computed(() => {
  const pages = []
  const total = totalPages.value
  const current = currentPage.value
  
  for (let i = Math.max(1, current - 2); i <= Math.min(total, current + 2); i++) {
    pages.push(i)
  }
  
  return pages
})

// 方法
const getStatusClass = (status) => {
  const classes = {
    'active': 'bg-green-100 text-green-800',
    'finished': 'bg-gray-100 text-gray-800',
    'draft': 'bg-yellow-100 text-yellow-800'
  }
  return classes[status] || 'bg-gray-100 text-gray-800'
}

const getStatusText = (status) => {
  const texts = {
    'active': '进行中',
    'finished': '已结束',
    'draft': '草稿'
  }
  return texts[status] || '未知'
}

const resetFilters = () => {
  searchTerm.value = ''
  statusFilter.value = ''
  semesterFilter.value = ''
  currentPage.value = 1
}

const editCourse = (course) => {
  Object.assign(courseForm, course)
  showEditModal.value = true
}

const duplicateCourse = (course) => {
  const newCourse = {
    ...course,
    id: courses.value.length + 1,
    name: course.name + ' (副本)',
    code: course.code + '_COPY',
    createdAt: new Date().toISOString().split('T')[0]
  }
  courses.value.push(newCourse)
}

const deleteCourse = (course) => {
  if (confirm(`确定要删除课程"${course.name}"吗？`)) {
    const index = courses.value.findIndex(c => c.id === course.id)
    if (index > -1) {
      courses.value.splice(index, 1)
    }
  }
}

const closeModal = () => {
  showCreateModal.value = false
  showEditModal.value = false
  resetForm()
}

const resetForm = () => {
  Object.assign(courseForm, {
    id: null,
    name: '',
    code: '',
    semester: '',
    credits: 3,
    description: ''
  })
}

const saveCourse = () => {
  if (showCreateModal.value) {
    // 创建新课程
    const newCourse = {
      ...courseForm,
      id: courses.value.length + 1,
      students: 0,
      status: 'draft',
      createdAt: new Date().toISOString().split('T')[0]
    }
    courses.value.push(newCourse)
  } else if (showEditModal.value) {
    // 更新现有课程
    const index = courses.value.findIndex(c => c.id === courseForm.id)
    if (index > -1) {
      Object.assign(courses.value[index], courseForm)
    }
  }
  closeModal()
}
</script> 