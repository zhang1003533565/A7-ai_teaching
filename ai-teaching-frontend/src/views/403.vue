<template>
  <div class="min-h-screen flex items-center justify-center bg-gray-100">
    <div class="text-center">
      <div class="mb-8">
        <i class="fas fa-lock text-6xl text-red-500"></i>
      </div>
      <h1 class="text-4xl font-bold text-gray-800 mb-4">403 权限不足</h1>
      <p class="text-gray-600 mb-8">抱歉，您没有权限访问此页面</p>
      <div class="space-x-4">
        <button
          @click="goBack"
          class="px-6 py-2 bg-gray-500 text-white rounded-md hover:bg-gray-600 transition-colors"
        >
          返回上一页
        </button>
        <button
          @click="goHome"
          class="px-6 py-2 bg-blue-500 text-white rounded-md hover:bg-blue-600 transition-colors"
        >
          返回首页
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'

const router = useRouter()

const goBack = () => {
  router.back()
}

const goHome = () => {
  router.push('/')
}
</script> 