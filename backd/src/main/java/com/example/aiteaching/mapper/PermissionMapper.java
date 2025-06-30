package com.example.aiteaching.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.aiteaching.entity.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 权限Mapper接口
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * 根据用户ID查询权限列表
     */
    @Select("SELECT DISTINCT p.* FROM sys_permission p " +
            "INNER JOIN sys_role_permission rp ON p.id = rp.permission_id " +
            "INNER JOIN sys_user_role ur ON rp.role_id = ur.role_id " +
            "WHERE ur.user_id = #{userId} AND p.is_deleted = 0 AND p.status = 1 " +
            "ORDER BY p.sort ASC")
    List<Permission> selectPermissionsByUserId(Long userId);

    /**
     * 查询菜单权限树
     */
    @Select("SELECT * FROM sys_permission " +
            "WHERE is_deleted = 0 AND status = 1 AND permission_type = 1 " +
            "ORDER BY sort ASC")
    List<Permission> selectMenuTree();

    /**
     * 根据父级ID查询子权限
     */
    @Select("SELECT * FROM sys_permission " +
            "WHERE parent_id = #{parentId} AND is_deleted = 0 AND status = 1 " +
            "ORDER BY sort ASC")
    List<Permission> selectByParentId(Long parentId);
} 