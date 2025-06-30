<template>
  <div class="p-6 bg-gray-50 min-h-screen">
    <!-- 页面标题 -->
    <div class="mb-8">
      <h1 class="text-2xl font-bold text-gray-900 mb-2">课程安排</h1>
      <p class="text-gray-600">管理课程时间表、教室安排和上课计划</p>
    </div>

    <!-- 操作栏 -->
    <div class="bg-white p-6 rounded-lg shadow-sm border border-gray-200 mb-6">
      <div class="flex justify-between items-center">
        <div class="flex items-center space-x-4">
          <select
            v-model="selectedSemester"
            class="px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
          >
            <option value="">选择学期</option>
            <option value="2024-1">2024春季</option>
            <option value="2024-2">2024秋季</option>
          </select>
          
          <select
            v-model="selectedCourse"
            class="px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
          >
            <option value="">选择课程</option>
            <option v-for="course in courses" :key="course.id" :value="course.id">
              {{ course.name }}
            </option>
          </select>
        </div>
        
        <button 
          @click="showScheduleModal = true"
          class="bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded-lg flex items-center transition-colors"
        >
          <i class="fas fa-plus mr-2"></i>
          添加课程安排
        </button>
      </div>
    </div>

    <!-- 课程表 -->
    <div class="bg-white rounded-lg shadow-sm border border-gray-200">
      <div class="p-6 border-b border-gray-200">
        <h2 class="text-lg font-semibold text-gray-900">课程表</h2>
      </div>
      
      <div class="p-6">
        <div class="overflow-x-auto">
          <table class="w-full">
            <thead>
              <tr class="border-b border-gray-200">
                <th class="text-left py-3 px-4 font-medium text-gray-900">时间</th>
                <th v-for="day in weekDays" :key="day" class="text-center py-3 px-4 font-medium text-gray-900">
                  {{ day }}
                </th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="time in timeSlots" :key="time.id" class="border-b border-gray-100">
                <td class="py-4 px-4 font-medium text-gray-700">
                  {{ time.period }}<br>
                  <span class="text-sm text-gray-500">{{ time.time }}</span>
                </td>
                <td v-for="day in weekDays" :key="day" class="py-4 px-4 text-center">
                  <div v-if="getSchedule(day, time.id)" class="relative">
                    <div 
                      class="bg-blue-100 border border-blue-300 rounded-lg p-3 hover:shadow-md transition-shadow cursor-pointer"
                      @click="viewScheduleDetail(getSchedule(day, time.id))"
                    >
                      <div class="font-medium text-blue-900 text-sm">
                        {{ getSchedule(day, time.id).courseName }}
                      </div>
                      <div class="text-xs text-blue-700 mt-1">
                        {{ getSchedule(day, time.id).classroom }}
                      </div>
                    </div>
                  </div>
                  <div v-else class="h-16 border-2 border-dashed border-gray-200 rounded-lg flex items-center justify-center">
                    <button 
                      @click="addSchedule(day, time.id)"
                      class="text-gray-400 hover:text-blue-600 transition-colors"
                    >
                      <i class="fas fa-plus"></i>
                    </button>
                  </div>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>

    <!-- 课程列表 -->
    <div class="mt-6 bg-white rounded-lg shadow-sm border border-gray-200">
      <div class="p-6 border-b border-gray-200">
        <div class="flex justify-between items-center">
          <h2 class="text-lg font-semibold text-gray-900">课程安排列表</h2>
          <span class="text-sm text-gray-600">共 {{ schedules.length }} 个安排</span>
        </div>
      </div>
      
      <div class="p-6">
        <div class="space-y-4">
          <div 
            v-for="schedule in schedules" 
            :key="schedule.id"
            class="flex items-center justify-between p-4 border border-gray-200 rounded-lg hover:shadow-md transition-shadow"
          >
            <div class="flex items-center space-x-4">
              <div class="w-12 h-12 bg-blue-100 rounded-lg flex items-center justify-center">
                <i class="fas fa-calendar text-blue-600"></i>
              </div>
              <div>
                <h3 class="font-medium text-gray-900">{{ schedule.courseName }}</h3>
                <p class="text-sm text-gray-600">
                  {{ schedule.dayOfWeek }} {{ schedule.timeSlot }} | {{ schedule.classroom }}
                </p>
                <p class="text-xs text-gray-500">
                  {{ schedule.startDate }} - {{ schedule.endDate }}
                </p>
              </div>
            </div>
            
            <div class="flex items-center space-x-2">
              <span 
                class="px-2 py-1 text-xs rounded-full"
                :class="schedule.status === 'active' ? 'bg-green-100 text-green-800' : 'bg-gray-100 text-gray-800'"
              >
                {{ schedule.status === 'active' ? '进行中' : '已结束' }}
              </span>
              <button 
                @click="editSchedule(schedule)"
                class="text-blue-600 hover:text-blue-800"
              >
                <i class="fas fa-edit"></i>
              </button>
              <button 
                @click="deleteSchedule(schedule)"
                class="text-red-600 hover:text-red-800"
              >
                <i class="fas fa-trash"></i>
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- 添加/编辑课程安排模态框 -->
    <div v-if="showScheduleModal" class="fixed inset-0 bg-gray-500 bg-opacity-50 flex items-center justify-center z-50">
      <div class="bg-white rounded-lg p-6 w-full max-w-2xl mx-4">
        <div class="flex justify-between items-center mb-6">
          <h3 class="text-lg font-semibold text-gray-900">
            {{ editingSchedule ? '编辑课程安排' : '添加课程安排' }}
          </h3>
          <button 
            @click="closeScheduleModal"
            class="text-gray-400 hover:text-gray-600"
          >
            <i class="fas fa-times"></i>
          </button>
        </div>

        <form @submit.prevent="saveSchedule" class="space-y-4">
          <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">课程</label>
              <select
                v-model="scheduleForm.courseId"
                required
                class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
              >
                <option value="">选择课程</option>
                <option v-for="course in courses" :key="course.id" :value="course.id">
                  {{ course.name }}
                </option>
              </select>
            </div>
            
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">教室</label>
              <input
                v-model="scheduleForm.classroom"
                type="text"
                required
                class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
              />
            </div>
          </div>

          <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">星期</label>
              <select
                v-model="scheduleForm.dayOfWeek"
                required
                class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
              >
                <option value="">选择星期</option>
                <option v-for="day in weekDays" :key="day" :value="day">
                  {{ day }}
                </option>
              </select>
            </div>
            
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">时间段</label>
              <select
                v-model="scheduleForm.timeSlot"
                required
                class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
              >
                <option value="">选择时间段</option>
                <option v-for="time in timeSlots" :key="time.id" :value="time.period">
                  {{ time.period }} ({{ time.time }})
                </option>
              </select>
            </div>
          </div>

          <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">开始日期</label>
              <input
                v-model="scheduleForm.startDate"
                type="date"
                required
                class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
              />
            </div>
            
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">结束日期</label>
              <input
                v-model="scheduleForm.endDate"
                type="date"
                required
                class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
              />
            </div>
          </div>

          <div class="flex justify-end space-x-4 pt-4">
            <button
              type="button"
              @click="closeScheduleModal"
              class="px-4 py-2 text-gray-700 border border-gray-300 rounded-lg hover:bg-gray-50 transition-colors"
            >
              取消
            </button>
            <button
              type="submit"
              class="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition-colors"
            >
              {{ editingSchedule ? '保存' : '添加' }}
            </button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive, computed } from 'vue'

// 状态
const selectedSemester = ref('')
const selectedCourse = ref('')
const showScheduleModal = ref(false)
const editingSchedule = ref(null)

// 表单数据
const scheduleForm = reactive({
  courseId: '',
  classroom: '',
  dayOfWeek: '',
  timeSlot: '',
  startDate: '',
  endDate: ''
})

// 基础数据
const weekDays = ['周一', '周二', '周三', '周四', '周五']
const timeSlots = [
  { id: 1, period: '第1-2节', time: '08:00-09:40' },
  { id: 2, period: '第3-4节', time: '10:00-11:40' },
  { id: 3, period: '第5-6节', time: '14:00-15:40' },
  { id: 4, period: '第7-8节', time: '16:00-17:40' },
  { id: 5, period: '第9-10节', time: '19:00-20:40' }
]

const courses = ref([
  { id: 1, name: '高等数学A' },
  { id: 2, name: '线性代数' },
  { id: 3, name: '概率论与数理统计' }
])

const schedules = ref([
  {
    id: 1,
    courseId: 1,
    courseName: '高等数学A',
    classroom: 'A101',
    dayOfWeek: '周一',
    timeSlot: '第1-2节',
    startDate: '2024-02-26',
    endDate: '2024-06-30',
    status: 'active'
  },
  {
    id: 2,
    courseId: 2,
    courseName: '线性代数',
    classroom: 'A102',
    dayOfWeek: '周三',
    timeSlot: '第3-4节',
    startDate: '2024-02-28',
    endDate: '2024-06-30',
    status: 'active'
  }
])

// 方法
const getSchedule = (day, timeId) => {
  const timeSlot = timeSlots.find(t => t.id === timeId)
  return schedules.value.find(s => s.dayOfWeek === day && s.timeSlot === timeSlot.period)
}

const addSchedule = (day, timeId) => {
  const timeSlot = timeSlots.find(t => t.id === timeId)
  scheduleForm.dayOfWeek = day
  scheduleForm.timeSlot = timeSlot.period
  showScheduleModal.value = true
}

const editSchedule = (schedule) => {
  editingSchedule.value = schedule
  Object.assign(scheduleForm, {
    courseId: schedule.courseId,
    classroom: schedule.classroom,
    dayOfWeek: schedule.dayOfWeek,
    timeSlot: schedule.timeSlot,
    startDate: schedule.startDate,
    endDate: schedule.endDate
  })
  showScheduleModal.value = true
}

const deleteSchedule = (schedule) => {
  if (confirm(`确定要删除"${schedule.courseName}"的课程安排吗？`)) {
    const index = schedules.value.findIndex(s => s.id === schedule.id)
    if (index > -1) {
      schedules.value.splice(index, 1)
    }
  }
}

const closeScheduleModal = () => {
  showScheduleModal.value = false
  editingSchedule.value = null
  resetScheduleForm()
}

const resetScheduleForm = () => {
  Object.assign(scheduleForm, {
    courseId: '',
    classroom: '',
    dayOfWeek: '',
    timeSlot: '',
    startDate: '',
    endDate: ''
  })
}

const saveSchedule = () => {
  const course = courses.value.find(c => c.id === parseInt(scheduleForm.courseId))
  
  if (editingSchedule.value) {
    // 编辑现有安排
    Object.assign(editingSchedule.value, {
      ...scheduleForm,
      courseName: course.name
    })
  } else {
    // 添加新安排
    const newSchedule = {
      id: schedules.value.length + 1,
      ...scheduleForm,
      courseName: course.name,
      status: 'active'
    }
    schedules.value.push(newSchedule)
  }
  
  closeScheduleModal()
}

const viewScheduleDetail = (schedule) => {
  console.log('查看课程安排详情:', schedule)
}
</script> 