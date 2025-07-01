// 环境变量配置
export const config = {
  // 开发环境
  development: {
    baseUrl: 'http://localhost:8080/api',  
    timeout: 30000
  },
  // 生产环境
  production: {
    baseUrl: '',  // 生产环境使用相对路径
    timeout: 30000
  }
}

// 获取当前环境
const env = import.meta.env.MODE || 'development'

// 导出当前环境的配置
export default config[env] 