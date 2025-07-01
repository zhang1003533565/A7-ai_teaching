import request from './request'

/**
 * 获取菜单权限树
 */
export const getMenuPermissionTree = () => {
    return request({
        url: '/permission/menu/tree',
        method: 'get'
    });
};

/**
 * 获取路由权限列表
 */
export const getRoutePermissionList = () => {
    return request({
        url: '/permission/route/list',
        method: 'get'
    });
};

/**
 * 获取角色的菜单权限
 * @param {number} roleId - 角色ID
 */
export const getRoleMenuPermissions = (roleId) => {
    return request({
        url: `/permission/role/${roleId}/menu`,
        method: 'get'
    });
};

/**
 * 获取角色的路由权限
 * @param {number} roleId - 角色ID
 */
export const getRoleRoutePermissions = (roleId) => {
    return request({
        url: `/permission/role/${roleId}/route`,
        method: 'get'
    });
};

/**
 * 保存角色的菜单权限
 * @param {number} roleId - 角色ID
 * @param {Array<number>} permissionIds - 权限ID列表
 */
export const saveRoleMenuPermissions = (roleId, permissionIds) => {
    return request({
        url: `/permission/role/${roleId}/menu`,
        method: 'post',
        data: permissionIds
    });
};

/**
 * 保存角色的路由权限
 * @param {number} roleId - 角色ID
 * @param {Array<number>} permissionIds - 权限ID列表
 */
export const saveRoleRoutePermissions = (roleId, permissionIds) => {
    return request({
        url: `/permission/role/${roleId}/route`,
        method: 'post',
        data: permissionIds
    });
};

/**
 * 获取用户权限
 */
export const getUserPermissions = () => {
    return request({
        url: '/user/permissions',
        method: 'get'
    });
}; 