// 环境变量配置
export const config = {
  // 开发环境
  development: {
    baseUrl: 'http://localhost:8080/api',
    timeout: 5000
  },
  // 生产环境
  production: {
    baseUrl: '/api', // 生产环境使用相对路径
    timeout: 15000
  }
}

// 获取当前环境
const env = import.meta.env.MODE || 'development'

// 导出当前环境的配置
export default config[env] 