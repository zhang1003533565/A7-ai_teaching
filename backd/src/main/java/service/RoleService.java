package com.example.aiteaching.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.aiteaching.dto.RoleRequest;
import com.example.aiteaching.entity.Role;
import com.example.aiteaching.entity.Permission;

import java.util.List;

/**
 * 角色服务接口
 */
public interface RoleService extends IService<Role> {

    /**
     * 创建角色
     */
    Role createRole(RoleRequest request);

    /**
     * 更新角色
     */
    Role updateRole(RoleRequest request);

    /**
     * 删除角色（软删除）
     */
    boolean deleteRole(Long id);

    /**
     * 根据ID获取角色
     */
    Role getRoleById(Long id);

    /**
     * 分页查询角色列表
     */
    IPage<Role> getRolePage(Page<Role> page, String roleName, String roleCode);

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