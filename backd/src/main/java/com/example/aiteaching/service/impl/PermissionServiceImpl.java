package com.example.aiteaching.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.aiteaching.entity.Permission;
import com.example.aiteaching.entity.RolePermission;
import com.example.aiteaching.entity.UserRole;
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
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public List<Permission> getAllPermissions() {
        return this.list(null);
    }

    @Override
    public List<Permission> getUserPermissions(Long userId) {
        return this.getBaseMapper().selectByUserId(userId);
    }

    @Override
    public List<Permission> getMenuPermissionTree() {
        // 获取所有菜单权限
        List<Permission> allMenus = this.getBaseMapper().selectMenuTree();
        
        // 将权限列表转换为树形结构
        return buildTree(allMenus);
    }

    @Override
    public List<Permission> getRoutePermissionList() {
        // 获取所有路由权限
        return this.getBaseMapper().selectRouteList();
    }

    @Override
    public List<Permission> getRoleMenuPermissions(Long roleId) {
        // 获取角色的菜单权限
        List<Permission> menuPermissions = this.getBaseMapper().selectRoleMenuPermissions(roleId);
        
        // 将权限列表转换为树形结构
        return buildTree(menuPermissions);
    }

    @Override
    public List<Permission> getRoleRoutePermissions(Long roleId) {
        // 获取角色的路由权限
        return this.getBaseMapper().selectRoleRoutePermissions(roleId);
    }

    @Override
    public List<Permission> getRolePermissions(Long roleId) {
        List<Long> permissionIds = this.getBaseMapper().selectPermissionIdsByRoleId(roleId);
        if (permissionIds.isEmpty()) {
            return new ArrayList<>();
        }
        return this.getBaseMapper().selectBatchIds(permissionIds);
    }

    @Override
    @Transactional
    public void assignRolePermissions(Long roleId, Long[] permissionIds) {
        // 先删除原有的权限关联
        this.getBaseMapper().deleteByRoleId(roleId);
        
        // 添加新的权限关联
        if (permissionIds != null && permissionIds.length > 0) {
            List<RolePermission> rolePermissions = new ArrayList<>();
            for (Long permissionId : permissionIds) {
                RolePermission rolePermission = new RolePermission();
                rolePermission.setRoleId(roleId);
                rolePermission.setPermissionId(permissionId);
                rolePermissions.add(rolePermission);
            }
            this.getBaseMapper().insertBatch(rolePermissions);
        }
    }

    @Override
    @Transactional
    public void saveRoleMenuPermissions(Long roleId, Long[] permissionIds) {
        // 删除角色原有的菜单权限
        this.getBaseMapper().deleteRoleMenuPermissions(roleId);
        
        // 添加新的菜单权限
        if (permissionIds != null && permissionIds.length > 0) {
            this.getBaseMapper().insertRoleMenuPermissions(roleId, permissionIds);
        }
    }

    @Override
    @Transactional
    public void saveRoleRoutePermissions(Long roleId, Long[] permissionIds) {
        // 删除角色原有的路由权限
        this.getBaseMapper().deleteRoleRoutePermissions(roleId);
        
        // 添加新的路由权限
        if (permissionIds != null && permissionIds.length > 0) {
            this.getBaseMapper().insertRoleRoutePermissions(roleId, permissionIds);
        }
    }

    @Override
    public List<Permission> getUserMenuPermissions(Long userId) {
        // 1. 获取用户的所有角色ID
        List<Long> roleIds = this.getBaseMapper().selectRoleIdsByUserId(userId);
        if (roleIds.isEmpty()) {
            return new ArrayList<>();
        }
        
        // 2. 获取这些角色的所有权限ID
        List<Long> permissionIds = this.getBaseMapper().selectPermissionIdsByRoleIds(roleIds);
        if (permissionIds.isEmpty()) {
            return new ArrayList<>();
        }
        
        // 3. 获取所有菜单类型的权限
        List<Permission> permissions = this.getBaseMapper().selectBatchIds(permissionIds);
        return permissions.stream()
                .filter(p -> p.getPermissionType() == 1) // 只返回菜单类型的权限
                .collect(Collectors.toList());
    }

    @Override
    public List<Permission> getAllMenus() {
        // 获取所有未删除的权限
        List<Permission> allPermissions = this.list(
            new LambdaQueryWrapper<Permission>()
                .eq(Permission::getIsDeleted, 0)
                .orderByAsc(Permission::getSort)
        );

        // 构建树形结构
        return buildTree(allPermissions);
    }

    @Override
    @Transactional
    public Permission addMenu(Permission permission) {
        // 设置默认值
        permission.setIsDeleted(0);
        
        // 如果没有设置排序值，设置为同级最大排序值+1
        if (permission.getSort() == null) {
            Integer maxSort = this.getBaseMapper().selectMaxSortByParentId(permission.getParentId());
            permission.setSort(maxSort != null ? maxSort + 1 : 1);
        }

        // 保存权限
        this.save(permission);
        return permission;
    }

    @Override
    @Transactional
    public Permission updateMenu(Permission permission) {
        // 更新权限
        this.updateById(permission);
        return permission;
    }

    @Override
    @Transactional
    public Boolean deleteMenu(Long id) {
        // 检查是否有子菜单
        Long count = this.count(
            new LambdaQueryWrapper<Permission>()
                .eq(Permission::getParentId, id)
                .eq(Permission::getIsDeleted, 0)
        );
        
        if (count > 0) {
            throw new RuntimeException("该菜单下有子菜单，不能删除");
        }

        // 逻辑删除
        Permission permission = new Permission();
        permission.setId(id);
        permission.setIsDeleted(1);
        return this.updateById(permission);
    }

    @Override
    public Permission getMenuDetail(Long id) {
        return this.getById(id);
    }

    /**
     * 构建树形结构
     */
    private List<Permission> buildTree(List<Permission> permissions) {
        // 按parentId分组
        Map<Long, List<Permission>> parentIdMap = permissions.stream()
            .collect(Collectors.groupingBy(Permission::getParentId));
        
        // 设置子菜单
        permissions.forEach(permission -> {
            List<Permission> children = parentIdMap.get(permission.getId());
            if (children != null) {
                permission.setChildren(children);
            }
        });
        
        // 返回顶级菜单
        return parentIdMap.getOrDefault(0L, new ArrayList<>());
    }
} 