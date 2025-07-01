package com.example.aiteaching.service;

import com.example.aiteaching.entity.Permission;
import java.util.List;

public interface PermissionService {
    /**
     * 获取所有权限
     */
    List<Permission> getAllPermissions();

    /**
     * 获取用户的所有权限
     */
    List<Permission> getUserPermissions(Long userId);

    /**
     * 获取角色的所有权限
     */
    List<Permission> getRolePermissions(Long roleId);

    /**
     * 为角色分配权限
     */
    void assignRolePermissions(Long roleId, Long[] permissionIds);

    List<Permission> getMenuPermissionTree();
    
    List<Permission> getRoutePermissionList();
    
    List<Permission> getRoleMenuPermissions(Long roleId);
    
    List<Permission> getRoleRoutePermissions(Long roleId);
    
    void saveRoleMenuPermissions(Long roleId, Long[] permissionIds);
    
    void saveRoleRoutePermissions(Long roleId, Long[] permissionIds);

    List<Permission> getUserMenuPermissions(Long userId);
} 