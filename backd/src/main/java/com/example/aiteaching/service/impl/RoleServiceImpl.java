package com.example.aiteaching.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.aiteaching.dto.RoleRequest;
import com.example.aiteaching.entity.Role;
import com.example.aiteaching.entity.Permission;
import com.example.aiteaching.entity.RolePermission;
import com.example.aiteaching.entity.UserRole;
import com.example.aiteaching.mapper.RoleMapper;
import com.example.aiteaching.mapper.RolePermissionMapper;
import com.example.aiteaching.mapper.UserRoleMapper;
import com.example.aiteaching.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    @Transactional
    public Role createRole(RoleRequest request) {
        // 1. 检查角色编码是否已存在
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Role::getRoleCode, request.getRoleCode())
                .eq(Role::getIsDeleted, 0);
        if (count(queryWrapper) > 0) {
            throw new RuntimeException("角色编码已存在");
        }

        // 2. 创建角色
        Role role = new Role();
        BeanUtils.copyProperties(request, role);
        role.setStatus(request.getStatus() != null ? request.getStatus() : 1);
        role.setSort(request.getSort() != null ? request.getSort() : 0);
        save(role);

        return role;
    }

    @Override
    @Transactional
    public Role updateRole(RoleRequest request) {
        // 1. 检查角色是否存在
        Role role = getById(request.getId());
        if (role == null) {
            throw new RuntimeException("角色不存在");
        }

        // 2. 检查角色编码是否重复
        if (!role.getRoleCode().equals(request.getRoleCode())) {
            LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Role::getRoleCode, request.getRoleCode())
                    .eq(Role::getIsDeleted, 0);
            if (count(queryWrapper) > 0) {
                throw new RuntimeException("角色编码已存在");
            }
        }

        // 3. 更新角色
        BeanUtils.copyProperties(request, role);
        updateById(role);

        return role;
    }

    @Override
    @Transactional
    public boolean deleteRole(Long id) {
        // 1. 检查角色是否存在
        Role role = getById(id);
        if (role == null) {
            throw new RuntimeException("角色不存在");
        }

        // 2. 删除角色（软删除）
        removeById(id);

        // 3. 删除角色关联的权限
        removeRolePermissions(id);

        // 4. 删除用户与该角色的关联
        LambdaQueryWrapper<UserRole> userRoleWrapper = new LambdaQueryWrapper<>();
        userRoleWrapper.eq(UserRole::getRoleId, id);
        userRoleMapper.delete(userRoleWrapper);

        return true;
    }

    @Override
    public Role getRoleById(Long id) {
        Role role = getById(id);
        if (role == null) {
            throw new RuntimeException("角色不存在");
        }
        
        // 获取角色的权限列表
        role.setPermissions(getPermissionsByRoleId(id));
        return role;
    }

    @Override
    public IPage<Role> getRolePage(Page<Role> page, String roleName, String roleCode) {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(roleName), Role::getRoleName, roleName)
                .like(StringUtils.hasText(roleCode), Role::getRoleCode, roleCode)
                .eq(Role::getIsDeleted, 0)
                .orderByAsc(Role::getSort);
        
        return page(page, queryWrapper);
    }

    @Override
    public List<Role> getRolesByUserId(Long userId) {
        return roleMapper.selectRolesByUserId(userId);
    }

    @Override
    public List<Permission> getPermissionsByRoleId(Long roleId) {
        return roleMapper.selectPermissionsByRoleId(roleId);
    }

    @Override
    @Transactional
    public boolean assignPermissions(Long roleId, List<Long> permissionIds) {
        // 先删除原有的权限关联
        rolePermissionMapper.deleteByRoleId(roleId);
        
        // 如果有新的权限，则添加
        if (permissionIds != null && !permissionIds.isEmpty()) {
            Long[] permissionIdArray = permissionIds.toArray(new Long[0]);
            rolePermissionMapper.insertBatch(roleId, permissionIdArray);
        }
        return true;
    }

    @Override
    @Transactional
    public boolean assignRolesToUser(Long userId, List<Long> roleIds) {
        // 1. 删除原有角色
        removeUserRoles(userId);

        // 2. 添加新角色
        if (roleIds != null && !roleIds.isEmpty()) {
            List<UserRole> userRoles = new ArrayList<>();
            for (Long roleId : roleIds) {
                UserRole userRole = new UserRole();
                userRole.setUserId(userId);
                userRole.setRoleId(roleId);
                userRoles.add(userRole);
            }
            userRoleMapper.insertBatch(userRoles);
        }

        return true;
    }

    @Override
    @Transactional
    public boolean removeUserRoles(Long userId) {
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRole::getUserId, userId);
        userRoleMapper.delete(wrapper);
        return true;
    }

    @Override
    @Transactional
    public boolean removeRolePermissions(Long roleId) {
        LambdaQueryWrapper<RolePermission> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RolePermission::getRoleId, roleId);
        rolePermissionMapper.delete(wrapper);
        return true;
    }
} 