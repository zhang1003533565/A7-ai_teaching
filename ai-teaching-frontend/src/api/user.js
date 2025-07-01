import request from './request'

// 用户登录
export const login = (data) => {
    return request({
        url: '/user/login',
        method: 'post',
        data
    });
};

// 用户登出
export const logout = () => {
    return request({
        url: '/user/logout',
        method: 'post'
    });
};

// 获取用户信息
export const getUserInfo = () => {
    return request({
        url: '/user/info',
        method: 'get'
    });
}; 