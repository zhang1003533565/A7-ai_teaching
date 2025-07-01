import axios from 'axios'
import router from '@/router'
import config from '@/config/env.config'

// 创建axios实例
const service = axios.create({
  baseURL: config.baseUrl,
  timeout: config.timeout,
  headers: {
    'Content-Type': 'application/json;charset=utf-8'
  },
  // 允许跨域携带cookie
  withCredentials: true
})

// 请求拦截器
service.interceptors.request.use(
  config => {
    // 从localStorage获取token
    const token = localStorage.getItem('token')
    if (token) {
      config.headers['Authorization'] = `Bearer ${token}`
    }
    console.log('请求配置：', config);
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
    console.log('响应数据：', res);
    // 如果是直接返回的数据，包装一下
    if (!res.hasOwnProperty('code')) {
      return {
        code: 200,
        data: res,
        message: 'success'
      }
    }
    // 如果返回的code不是200，说明有错误
    if (res.code !== 200) {
      return Promise.reject(new Error(res.message || '操作失败'));
    }
    return res;  // 返回完整的响应数据
  },
  error => {
    console.error('响应错误：', error)
    let message = '网络异常'
    if (error.response) {
      switch (error.response.status) {
        case 401:
          message = '未授权，请重新登录'
          localStorage.removeItem('token')
          router.push('/login')
          break
        case 403:
          message = '权限不足'
          break
        case 404:
          message = '请求的资源不存在'
          break
        case 500:
          message = error.response.data?.message || '服务器错误'
          break
        default:
          message = `连接错误 ${error.response.status}`
      }
    }
    return Promise.reject(new Error(message))
  }
)

export default service 