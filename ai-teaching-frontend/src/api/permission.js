import request from './request'

/**
 * 获取菜单权限树
 */
export function getMenuPermissionTree() {
    return request({
        url: '/permissions/menu/tree',
        method: 'get'
    });
}

/**
 * 获取路由权限列表
 */
export function getRoutePermissionList() {
    return request({
        url: '/permissions/route/list',
        method: 'get'
    });
}

/**
 * 获取角色的菜单权限
 * @param {number} roleId - 角色ID
 */
export function getRoleMenuPermissions(roleId) {
    return request({
        url: `/permissions/role/${roleId}/menu`,
        method: 'get'
    });
}

/**
 * 获取角色的路由权限
 * @param {number} roleId - 角色ID
 */
export function getRoleRoutePermissions(roleId) {
    return request({
        url: `/permissions/role/${roleId}/route`,
        method: 'get'
    });
}

/**
 * 保存角色的菜单权限
 * @param {number} roleId - 角色ID
 * @param {Array<number>} permissionIds - 权限ID列表
 */
export function saveRoleMenuPermissions(roleId, permissionIds) {
    return request({
        url: `/permissions/role/${roleId}/menu`,
        method: 'post',
        data: permissionIds
    });
}

/**
 * 保存角色的路由权限
 * @param {number} roleId - 角色ID
 * @param {Array<number>} permissionIds - 权限ID列表
 */
export function saveRoleRoutePermissions(roleId, permissionIds) {
    return request({
        url: `/permissions/role/${roleId}/route`,
        method: 'post',
        data: permissionIds
    });
}

/**
 * 获取用户权限
 */
export function getUserPermissions() {
    return request({
        url: '/user/permissions',
        method: 'get'
    });
}

// 获取用户的菜单权限
export function getUserMenuPermissions() {
    return request({
        url: '/user/permissions',
        method: 'get'
    });
} 