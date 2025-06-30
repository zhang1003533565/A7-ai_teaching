package com.example.aiteaching.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.aiteaching.entity.Role;
import com.example.aiteaching.entity.Permission;

import java.util.List;

/**
 * 角色服务接口
 */
public interface RoleService extends IService<Role> {

    /**
     * 根据用户ID查询角色列表
     */
    List<Role> getRolesByUserId(Long userId);

    /**
     * 根据角色ID查询权限列表
     */
    List<Permission> getPermissionsByRoleId(Long roleId);

    /**
     * 为角色分配权限
     */
    boolean assignPermissions(Long roleId, List<Long> permissionIds);

    /**
     * 为用户分配角色
     */
    boolean assignRolesToUser(Long userId, List<Long> roleIds);

    /**
     * 移除用户的所有角色
     */
    boolean removeUserRoles(Long userId);

    /**
     * 移除角色的所有权限
     */
    boolean removeRolePermissions(Long roleId);
} 