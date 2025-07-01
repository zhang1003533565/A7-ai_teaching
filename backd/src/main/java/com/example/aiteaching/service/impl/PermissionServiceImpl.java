package com.example.aiteaching.service.impl;

import com.example.aiteaching.entity.Permission;
import com.example.aiteaching.entity.RolePermission;
import com.example.aiteaching.mapper.PermissionMapper;
import com.example.aiteaching.mapper.RolePermissionMapper;
import com.example.aiteaching.mapper.UserRoleMapper;
import com.example.aiteaching.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public List<Permission> getAllPermissions() {
        return permissionMapper.selectList(null);
    }

    @Override
    public List<Permission> getUserPermissions(Long userId) {
        return permissionMapper.selectByUserId(userId);
    }

    @Override
    public List<Permission> getMenuPermissionTree() {
        // 获取所有菜单权限
        List<Permission> allMenus = permissionMapper.selectMenuTree();
        
        // 将权限列表转换为树形结构
        return buildPermissionTree(allMenus);
    }

    @Override
    public List<Permission> getRoutePermissionList() {
        // 获取所有路由权限
        return permissionMapper.selectRouteList();
    }

    @Override
    public List<Permission> getRoleMenuPermissions(Long roleId) {
        // 获取角色的菜单权限
        List<Permission> menuPermissions = permissionMapper.selectRoleMenuPermissions(roleId);
        
        // 将权限列表转换为树形结构
        return buildPermissionTree(menuPermissions);
    }

    @Override
    public List<Permission> getRoleRoutePermissions(Long roleId) {
        // 获取角色的路由权限
        return permissionMapper.selectRoleRoutePermissions(roleId);
    }

    @Override
    public List<Permission> getRolePermissions(Long roleId) {
        List<Long> permissionIds = rolePermissionMapper.selectPermissionIdsByRoleId(roleId);
        if (permissionIds.isEmpty()) {
            return new ArrayList<>();
        }
        return permissionMapper.selectBatchIds(permissionIds);
    }

    @Override
    @Transactional
    public void assignRolePermissions(Long roleId, Long[] permissionIds) {
        // 先删除原有的权限关联
        rolePermissionMapper.deleteByRoleId(roleId);
        
        // 添加新的权限关联
        if (permissionIds != null && permissionIds.length > 0) {
            List<RolePermission> rolePermissions = new ArrayList<>();
            for (Long permissionId : permissionIds) {
                RolePermission rolePermission = new RolePermission();
                rolePermission.setRoleId(roleId);
                rolePermission.setPermissionId(permissionId);
                rolePermissions.add(rolePermission);
            }
            rolePermissionMapper.insertBatch(rolePermissions);
        }
    }

    @Override
    @Transactional
    public void saveRoleMenuPermissions(Long roleId, Long[] permissionIds) {
        // 删除角色原有的菜单权限
        permissionMapper.deleteRoleMenuPermissions(roleId);
        
        // 添加新的菜单权限
        if (permissionIds != null && permissionIds.length > 0) {
            permissionMapper.insertRoleMenuPermissions(roleId, permissionIds);
        }
    }

    @Override
    @Transactional
    public void saveRoleRoutePermissions(Long roleId, Long[] permissionIds) {
        // 删除角色原有的路由权限
        permissionMapper.deleteRoleRoutePermissions(roleId);
        
        // 添加新的路由权限
        if (permissionIds != null && permissionIds.length > 0) {
            permissionMapper.insertRoleRoutePermissions(roleId, permissionIds);
        }
    }

    /**
     * 构建权限树
     * @param permissions 权限列表
     * @return 树形结构的权限列表
     */
    private List<Permission> buildPermissionTree(List<Permission> permissions) {
        // 按父ID分组
        Map<Long, List<Permission>> parentIdMap = permissions.stream()
                .collect(Collectors.groupingBy(Permission::getParentId));
        
        // 获取根节点（parentId = 0）
        List<Permission> rootPermissions = parentIdMap.getOrDefault(0L, new ArrayList<>());
        
        // 递归设置子节点
        rootPermissions.forEach(root -> setChildren(root, parentIdMap));
        
        return rootPermissions;
    }

    /**
     * 递归设置子节点
     * @param parent 父节点
     * @param parentIdMap 按父ID分组的权限Map
     */
    private void setChildren(Permission parent, Map<Long, List<Permission>> parentIdMap) {
        List<Permission> children = parentIdMap.getOrDefault(parent.getId(), new ArrayList<>());
        if (!children.isEmpty()) {
            parent.setChildren(children);
            children.forEach(child -> setChildren(child, parentIdMap));
        }
    }
} 