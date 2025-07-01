import axios from 'axios'
import router from '@/router'
import config from '@/config/env.config'

// 创建axios实例
const service = axios.create({
  baseURL: config.baseUrl,
  timeout: config.timeout,
  headers: {
    'Content-Type': 'application/json;charset=utf-8'
  }
})

// 请求拦截器
service.interceptors.request.use(
  config => {
    // 从localStorage获取token
    const token = localStorage.getItem('token')
    if (token) {
      config.headers['Authorization'] = `Bearer ${token}`
    }
    return config
  },
  error => {
    console.error('请求错误：', error)
    return Promise.reject(error)
  }
)

// 响应拦截器
service.interceptors.response.use(
  response => {
    const res = response.data
    // 这里可以根据后端返回的状态码进行判断
    if (res.code === 200) {
      return res.data // 直接返回数据部分
    } else {
      // 处理其他状态码
      switch (res.code) {
        case 401:
          // token过期或未登录
          localStorage.removeItem('token')
          router.push('/login')
          break
        case 403:
          // 权限不足
          console.error('权限不足')
          break
        default:
          console.error(res.message || '未知错误')
      }
      return Promise.reject(new Error(res.message || '未知错误'))
    }
  },
  error => {
    console.error('响应错误：', error)
    if (error.response) {
      switch (error.response.status) {
        case 401:
          // 未授权，清除token并跳转到登录页面
          localStorage.removeItem('token')
          router.push('/login')
          break
        case 403:
          console.error('权限不足')
          break
        case 404:
          console.error('请求的资源不存在')
          break
        case 500:
          console.error('服务器错误')
          break
        default:
          console.error(`连接错误 ${error.response.status}`)
      }
    } else {
      console.error('网络异常')
    }
    return Promise.reject(error)
  }
)

export default service 