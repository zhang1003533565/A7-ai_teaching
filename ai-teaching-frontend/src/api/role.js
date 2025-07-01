import request from './request'

/**
 * 创建角色
 * @param {Object} data - 角色数据
 * @param {string} data.roleName - 角色名称
 * @param {string} data.roleCode - 角色编码
 */
export const createRole = (data) => {
    return request({
        url: '/role',
        method: 'post',
        data
    });
};

/**
 * 更新角色
 * @param {number} id - 角色ID
 * @param {Object} data - 角色数据
 */
export const updateRole = (id, data) => {
    return request({
        url: `/role/${id}`,
        method: 'put',
        data
    });
};

/**
 * 删除角色
 * @param {number} id - 角色ID
 */
export const deleteRole = (id) => {
    return request({
        url: `/role/${id}`,
        method: 'delete'
    });
};

/**
 * 获取角色详情
 * @param {number} id - 角色ID
 */
export const getRole = (id) => {
    return request({
        url: `/role/${id}`,
        method: 'get'
    });
};

/**
 * 分页查询角色列表
 * @param {Object} params - 查询参数
 * @param {number} [params.current=1] - 当前页码
 * @param {number} [params.size=10] - 每页条数
 * @param {string} [params.roleName] - 角色名称（可选）
 * @param {string} [params.roleCode] - 角色编码（可选）
 */
export const getRolePage = (params) => {
    return request({
        url: '/role/page',
        method: 'get',
        params
    });
}; 