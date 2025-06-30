import axios from 'axios';

// 创建axios实例
const request = axios.create({
    baseURL: 'http://localhost:8080/api', // 后端接口基础地址
    timeout: 5000 // 请求超时时间
});

// 请求拦截器
request.interceptors.request.use(
    config => {
        const token = localStorage.getItem('token');
        if (token) {
            config.headers['Authorization'] = `Bearer ${token}`;
        }
        return config;
    },
    error => {
        return Promise.reject(error);
    }
);

// 响应拦截器
request.interceptors.response.use(
    response => {
        const res = response.data;
        if (res.code === 200) {
            return res.data;
        } else {
            ElMessage.error(res.message || '请求失败');
            return Promise.reject(new Error(res.message || '请求失败'));
        }
    },
    error => {
        ElMessage.error(error.message || '请求失败');
        return Promise.reject(error);
    }
);

// 用户登录
export const login = (data) => {
    return request({
        url: '/user/login',
        method: 'post',
        data
    });
}; 