package com.example.aiteaching.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.aiteaching.entity.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 权限Mapper接口
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * 根据用户ID查询权限列表
     */
    List<Permission> selectByUserId(Long userId);

    /**
     * 查询菜单权限树
     */
    List<Permission> selectMenuTree();

    /**
     * 根据父级ID查询子权限
     */
    List<Permission> selectByParentId(Long parentId);

    /**
     * 查询路由权限列表
     */
    List<Permission> selectRouteList();

    /**
     * 查询角色的菜单权限
     */
    List<Permission> selectRoleMenuPermissions(Long roleId);

    /**
     * 查询角色的路由权限
     */
    List<Permission> selectRoleRoutePermissions(Long roleId);

    /**
     * 删除角色的菜单权限
     */
    int deleteRoleMenuPermissions(Long roleId);

    /**
     * 删除角色的路由权限
     */
    int deleteRoleRoutePermissions(Long roleId);

    /**
     * 批量插入角色的菜单权限
     */
    int insertRoleMenuPermissions(@Param("roleId") Long roleId, @Param("permissionIds") Long[] permissionIds);

    /**
     * 批量插入角色的路由权限
     */
    int insertRoleRoutePermissions(@Param("roleId") Long roleId, @Param("permissionIds") Long[] permissionIds);
} 