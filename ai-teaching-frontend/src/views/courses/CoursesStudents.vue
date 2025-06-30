<template>
  <div class="p-6 bg-gray-50 min-h-screen">
    <!-- 页面标题 -->
    <div class="mb-8">
      <h1 class="text-2xl font-bold text-gray-900 mb-2">学生管理</h1>
      <p class="text-gray-600">管理课程学生名单、成绩和学习情况</p>
    </div>

    <!-- 操作栏 -->
    <div class="bg-white p-6 rounded-lg shadow-sm border border-gray-200 mb-6">
      <div class="flex justify-between items-center mb-4">
        <div class="flex items-center space-x-4">
          <select
            v-model="selectedCourse"
            class="px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
          >
            <option value="">选择课程</option>
            <option v-for="course in courses" :key="course.id" :value="course.id">
              {{ course.name }}
            </option>
          </select>
          
          <div class="relative">
            <i class="fas fa-search absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400"></i>
            <input
              v-model="searchTerm"
              type="text"
              placeholder="搜索学生姓名或学号..."
              class="pl-10 pr-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
            />
          </div>
        </div>
        
        <div class="flex items-center space-x-2">
          <button 
            @click="exportStudents"
            class="bg-green-600 hover:bg-green-700 text-white px-4 py-2 rounded-lg flex items-center transition-colors"
          >
            <i class="fas fa-file-export mr-2"></i>
            导出名单
          </button>
          <button 
            @click="showAddModal = true"
            class="bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded-lg flex items-center transition-colors"
          >
            <i class="fas fa-plus mr-2"></i>
            添加学生
          </button>
        </div>
      </div>

      <!-- 统计信息 -->
      <div class="grid grid-cols-1 md:grid-cols-4 gap-4">
        <div class="bg-blue-50 p-4 rounded-lg">
          <div class="flex items-center">
            <i class="fas fa-users text-blue-600 text-xl mr-3"></i>
            <div>
              <p class="text-sm text-blue-600">总学生数</p>
              <p class="text-lg font-semibold text-blue-900">{{ filteredStudents.length }}</p>
            </div>
          </div>
        </div>
        
        <div class="bg-green-50 p-4 rounded-lg">
          <div class="flex items-center">
            <i class="fas fa-check-circle text-green-600 text-xl mr-3"></i>
            <div>
              <p class="text-sm text-green-600">出勤率</p>
              <p class="text-lg font-semibold text-green-900">{{ averageAttendance }}%</p>
            </div>
          </div>
        </div>
        
        <div class="bg-purple-50 p-4 rounded-lg">
          <div class="flex items-center">
            <i class="fas fa-chart-line text-purple-600 text-xl mr-3"></i>
            <div>
              <p class="text-sm text-purple-600">平均成绩</p>
              <p class="text-lg font-semibold text-purple-900">{{ averageGrade }}</p>
            </div>
          </div>
        </div>
        
        <div class="bg-orange-50 p-4 rounded-lg">
          <div class="flex items-center">
            <i class="fas fa-tasks text-orange-600 text-xl mr-3"></i>
            <div>
              <p class="text-sm text-orange-600">作业提交率</p>
              <p class="text-lg font-semibold text-orange-900">{{ homeworkSubmissionRate }}%</p>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- 学生列表 -->
    <div class="bg-white rounded-lg shadow-sm border border-gray-200">
      <div class="p-6 border-b border-gray-200">
        <div class="flex justify-between items-center">
          <h2 class="text-lg font-semibold text-gray-900">学生列表</h2>
          <div class="flex items-center space-x-4">
            <select
              v-model="sortBy"
              class="px-3 py-1 text-sm border border-gray-300 rounded focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
            >
              <option value="name">按姓名排序</option>
              <option value="studentId">按学号排序</option>
              <option value="grade">按成绩排序</option>
              <option value="attendance">按出勤率排序</option>
            </select>
          </div>
        </div>
      </div>

      <div class="overflow-x-auto">
        <table class="min-w-full divide-y divide-gray-200">
          <thead class="bg-gray-50">
            <tr>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                学生信息
              </th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                学号
              </th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                班级
              </th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                出勤率
              </th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                平均成绩
              </th>
              <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                作业完成率
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
            <tr v-for="student in paginatedStudents" :key="student.id" class="hover:bg-gray-50">
              <td class="px-6 py-4 whitespace-nowrap">
                <div class="flex items-center">
                  <div class="w-10 h-10 bg-blue-100 rounded-full flex items-center justify-center">
                    <span class="text-sm font-medium text-blue-600">
                      {{ student.name.charAt(0) }}
                    </span>
                  </div>
                  <div class="ml-4">
                    <div class="text-sm font-medium text-gray-900">{{ student.name }}</div>
                    <div class="text-sm text-gray-500">{{ student.email }}</div>
                  </div>
                </div>
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
                {{ student.studentId }}
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
                {{ student.class }}
              </td>
              <td class="px-6 py-4 whitespace-nowrap">
                <div class="flex items-center">
                  <div class="w-full bg-gray-200 rounded-full h-2 mr-2">
                    <div 
                      class="h-2 rounded-full"
                      :class="getAttendanceColor(student.attendance)"
                      :style="{ width: student.attendance + '%' }"
                    ></div>
                  </div>
                  <span class="text-sm text-gray-600">{{ student.attendance }}%</span>
                </div>
              </td>
              <td class="px-6 py-4 whitespace-nowrap">
                <span 
                  class="px-2 py-1 text-xs font-semibold rounded-full"
                  :class="getGradeColor(student.averageGrade)"
                >
                  {{ student.averageGrade || '暂无' }}
                </span>
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
                {{ student.homeworkCompletion }}%
              </td>
              <td class="px-6 py-4 whitespace-nowrap">
                <span 
                  class="px-2 py-1 inline-flex text-xs leading-5 font-semibold rounded-full"
                  :class="student.status === 'active' ? 'bg-green-100 text-green-800' : 'bg-red-100 text-red-800'"
                >
                  {{ student.status === 'active' ? '正常' : '异常' }}
                </span>
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm font-medium">
                <div class="flex space-x-2">
                  <button 
                    @click="viewStudent(student)"
                    class="text-blue-600 hover:text-blue-900"
                  >
                    详情
                  </button>
                  <button 
                    @click="editGrade(student)"
                    class="text-green-600 hover:text-green-900"
                  >
                    成绩
                  </button>
                  <button 
                    @click="removeStudent(student)"
                    class="text-red-600 hover:text-red-900"
                  >
                    移除
                  </button>
                </div>
              </td>
            </tr>
          </tbody>
        </table>
      </div>

      <!-- 分页 -->
      <div class="px-6 py-3 flex items-center justify-between border-t border-gray-200">
        <div class="text-sm text-gray-700">
          显示第 {{ (currentPage - 1) * pageSize + 1 }} 到 
          {{ Math.min(currentPage * pageSize, filteredStudents.length) }} 条，
          共 {{ filteredStudents.length }} 条记录
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

    <!-- 添加学生模态框 -->
    <div v-if="showAddModal" class="fixed inset-0 bg-gray-500 bg-opacity-50 flex items-center justify-center z-50">
      <div class="bg-white rounded-lg p-6 w-full max-w-2xl mx-4">
        <div class="flex justify-between items-center mb-6">
          <h3 class="text-lg font-semibold text-gray-900">添加学生</h3>
          <button 
            @click="closeAddModal"
            class="text-gray-400 hover:text-gray-600"
          >
            <i class="fas fa-times"></i>
          </button>
        </div>

        <form @submit.prevent="addStudent" class="space-y-4">
          <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">学生姓名</label>
              <input
                v-model="studentForm.name"
                type="text"
                required
                class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
              />
            </div>
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">学号</label>
              <input
                v-model="studentForm.studentId"
                type="text"
                required
                class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
              />
            </div>
          </div>

          <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">邮箱</label>
              <input
                v-model="studentForm.email"
                type="email"
                required
                class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
              />
            </div>
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">班级</label>
              <input
                v-model="studentForm.class"
                type="text"
                required
                class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
              />
            </div>
          </div>

          <div class="flex justify-end space-x-4 pt-4">
            <button
              type="button"
              @click="closeAddModal"
              class="px-4 py-2 text-gray-700 border border-gray-300 rounded-lg hover:bg-gray-50 transition-colors"
            >
              取消
            </button>
            <button
              type="submit"
              class="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition-colors"
            >
              添加
            </button>
          </div>
        </form>
      </div>
    </div>

    <!-- 成绩编辑模态框 -->
    <div v-if="showGradeModal" class="fixed inset-0 bg-gray-500 bg-opacity-50 flex items-center justify-center z-50">
      <div class="bg-white rounded-lg p-6 w-full max-w-lg mx-4">
        <div class="flex justify-between items-center mb-6">
          <h3 class="text-lg font-semibold text-gray-900">编辑成绩</h3>
          <button 
            @click="showGradeModal = false"
            class="text-gray-400 hover:text-gray-600"
          >
            <i class="fas fa-times"></i>
          </button>
        </div>

        <div class="space-y-4">
          <div class="text-sm text-gray-600">
            学生：{{ editingStudent?.name }} ({{ editingStudent?.studentId }})
          </div>
          
          <div>
            <label class="block text-sm font-medium text-gray-700 mb-1">平时成绩</label>
            <input
              v-model="gradeForm.regularGrade"
              type="number"
              min="0"
              max="100"
              class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
            />
          </div>
          
          <div>
            <label class="block text-sm font-medium text-gray-700 mb-1">期中成绩</label>
            <input
              v-model="gradeForm.midtermGrade"
              type="number"
              min="0"
              max="100"
              class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
            />
          </div>
          
          <div>
            <label class="block text-sm font-medium text-gray-700 mb-1">期末成绩</label>
            <input
              v-model="gradeForm.finalGrade"
              type="number"
              min="0"
              max="100"
              class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
            />
          </div>

          <div class="flex justify-end space-x-4 pt-4">
            <button
              @click="showGradeModal = false"
              class="px-4 py-2 text-gray-700 border border-gray-300 rounded-lg hover:bg-gray-50 transition-colors"
            >
              取消
            </button>
            <button
              @click="saveGrade"
              class="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition-colors"
            >
              保存
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive, computed } from 'vue'

// 状态
const selectedCourse = ref('')
const searchTerm = ref('')
const sortBy = ref('name')
const showAddModal = ref(false)
const showGradeModal = ref(false)
const editingStudent = ref(null)
const currentPage = ref(1)
const pageSize = ref(10)

// 表单数据
const studentForm = reactive({
  name: '',
  studentId: '',
  email: '',
  class: ''
})

const gradeForm = reactive({
  regularGrade: 0,
  midtermGrade: 0,
  finalGrade: 0
})

// 模拟数据
const courses = ref([
  { id: 1, name: '高等数学A' },
  { id: 2, name: '线性代数' },
  { id: 3, name: '概率论与数理统计' }
])

const students = ref([
  {
    id: 1,
    name: '张三',
    studentId: '2023001',
    email: 'zhangsan@example.com',
    class: '计算机2023-1班',
    attendance: 95,
    averageGrade: 85,
    homeworkCompletion: 90,
    status: 'active'
  },
  {
    id: 2,
    name: '李四',
    studentId: '2023002',
    email: 'lisi@example.com',
    class: '计算机2023-1班',
    attendance: 88,
    averageGrade: 78,
    homeworkCompletion: 85,
    status: 'active'
  },
  {
    id: 3,
    name: '王五',
    studentId: '2023003',
    email: 'wangwu@example.com',
    class: '计算机2023-2班',
    attendance: 92,
    averageGrade: 82,
    homeworkCompletion: 88,
    status: 'active'
  }
])

// 计算属性
const filteredStudents = computed(() => {
  let filtered = students.value
  
  if (searchTerm.value) {
    filtered = filtered.filter(student => 
      student.name.toLowerCase().includes(searchTerm.value.toLowerCase()) ||
      student.studentId.includes(searchTerm.value)
    )
  }
  
  // 排序
  filtered.sort((a, b) => {
    switch (sortBy.value) {
      case 'name':
        return a.name.localeCompare(b.name)
      case 'studentId':
        return a.studentId.localeCompare(b.studentId)
      case 'grade':
        return (b.averageGrade || 0) - (a.averageGrade || 0)
      case 'attendance':
        return b.attendance - a.attendance
      default:
        return 0
    }
  })
  
  return filtered
})

const paginatedStudents = computed(() => {
  const start = (currentPage.value - 1) * pageSize.value
  const end = start + pageSize.value
  return filteredStudents.value.slice(start, end)
})

const totalPages = computed(() => Math.ceil(filteredStudents.value.length / pageSize.value))

const visiblePages = computed(() => {
  const pages = []
  const total = totalPages.value
  const current = currentPage.value
  
  for (let i = Math.max(1, current - 2); i <= Math.min(total, current + 2); i++) {
    pages.push(i)
  }
  
  return pages
})

const averageAttendance = computed(() => {
  if (students.value.length === 0) return 0
  const total = students.value.reduce((sum, student) => sum + student.attendance, 0)
  return Math.round(total / students.value.length)
})

const averageGrade = computed(() => {
  const gradesWithValue = students.value.filter(s => s.averageGrade)
  if (gradesWithValue.length === 0) return '暂无'
  const total = gradesWithValue.reduce((sum, student) => sum + student.averageGrade, 0)
  return Math.round(total / gradesWithValue.length)
})

const homeworkSubmissionRate = computed(() => {
  if (students.value.length === 0) return 0
  const total = students.value.reduce((sum, student) => sum + student.homeworkCompletion, 0)
  return Math.round(total / students.value.length)
})

// 方法
const getAttendanceColor = (attendance) => {
  if (attendance >= 90) return 'bg-green-500'
  if (attendance >= 80) return 'bg-yellow-500'
  return 'bg-red-500'
}

const getGradeColor = (grade) => {
  if (!grade) return 'bg-gray-100 text-gray-800'
  if (grade >= 85) return 'bg-green-100 text-green-800'
  if (grade >= 70) return 'bg-yellow-100 text-yellow-800'
  return 'bg-red-100 text-red-800'
}

const viewStudent = (student) => {
  console.log('查看学生详情:', student)
}

const editGrade = (student) => {
  editingStudent.value = student
  gradeForm.regularGrade = student.regularGrade || 0
  gradeForm.midtermGrade = student.midtermGrade || 0
  gradeForm.finalGrade = student.finalGrade || 0
  showGradeModal.value = true
}

const saveGrade = () => {
  if (editingStudent.value) {
    // 计算平均成绩
    const total = gradeForm.regularGrade + gradeForm.midtermGrade + gradeForm.finalGrade
    editingStudent.value.averageGrade = Math.round(total / 3)
    editingStudent.value.regularGrade = gradeForm.regularGrade
    editingStudent.value.midtermGrade = gradeForm.midtermGrade
    editingStudent.value.finalGrade = gradeForm.finalGrade
  }
  showGradeModal.value = false
}

const removeStudent = (student) => {
  if (confirm(`确定要移除学生"${student.name}"吗？`)) {
    const index = students.value.findIndex(s => s.id === student.id)
    if (index > -1) {
      students.value.splice(index, 1)
    }
  }
}

const closeAddModal = () => {
  showAddModal.value = false
  resetStudentForm()
}

const resetStudentForm = () => {
  Object.assign(studentForm, {
    name: '',
    studentId: '',
    email: '',
    class: ''
  })
}

const addStudent = () => {
  const newStudent = {
    id: students.value.length + 1,
    ...studentForm,
    attendance: 100,
    averageGrade: null,
    homeworkCompletion: 100,
    status: 'active'
  }
  students.value.push(newStudent)
  closeAddModal()
}

const exportStudents = () => {
  console.log('导出学生名单')
}
</script> 