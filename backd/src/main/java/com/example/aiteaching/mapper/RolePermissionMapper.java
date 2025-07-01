package com.example.aiteaching.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.aiteaching.entity.RolePermission;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RolePermissionMapper extends BaseMapper<RolePermission> {
    
    @Select("<script>" +
            "SELECT permission_id FROM sys_role_permission WHERE role_id IN " +
            "<foreach collection='roleIds' item='roleId' open='(' separator=',' close=')'>" +
            "#{roleId}" +
            "</foreach>" +
            " AND is_deleted = 0" +
            "</script>")
    List<Long> selectPermissionIdsByRoleIds(@Param("roleIds") List<Long> roleIds);

    @Select("SELECT permission_id FROM sys_role_permission WHERE role_id = #{roleId} AND is_deleted = 0")
    List<Long> selectPermissionIdsByRoleId(@Param("roleId") Long roleId);

    @Update("UPDATE sys_role_permission SET is_deleted = 1 WHERE role_id = #{roleId}")
    int deleteByRoleId(@Param("roleId") Long roleId);

    int insertBatch(@Param("list") List<RolePermission> list);
} 