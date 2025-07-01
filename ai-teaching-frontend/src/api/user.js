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

// 添加用户
export const addUser = (data) => {
    return request({
        url: '/user/add',
        method: 'post',
        data
    });
};

// 更新用户
export const updateUser = (data) => {
    return request({
        url: '/user/update',
        method: 'put',
        data
    });
};

// 删除用户
export const deleteUser = (id) => {
    return request({
        url: `/user/delete/${id}`,
        method: 'delete'
    });
};

// 获取单个用户
export const getUser = (id) => {
    return request({
        url: `/user/get/${id}`,
        method: 'get'
    });
};

// 获取用户列表
export const listUsers = () => {
    return request({
        url: '/user/list',
        method: 'get'
    });
};

// 分页获取用户列表
export const getUserPage = (params) => {
    return request({
        url: '/user/page',
        method: 'get',
        params
    });
};

// 重置用户密码
export const resetPassword = (id) => {
    return request({
        url: `/user/reset-password/${id}`,
        method: 'post'
    });
}; 