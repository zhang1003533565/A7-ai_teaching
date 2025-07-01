package com.example.aiteaching.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.aiteaching.entity.UserRole;
import com.example.aiteaching.entity.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {
    
    /**
     * 批量插入用户角色关联
     */
    @Insert("<script>" +
            "INSERT INTO sys_user_role (user_id, role_id, create_time) " +
            "VALUES " +
            "<foreach collection='list' item='item' separator=','>" +
            "(#{item.userId}, #{item.roleId}, #{item.createTime})" +
            "</foreach>" +
            "</script>")
    int insertBatch(@Param("list") List<UserRole> userRoles);

    @Delete("DELETE FROM sys_user_role WHERE user_id = #{userId}")
    int deleteByUserId(@Param("userId") Long userId);

    @Select("SELECT role_id FROM sys_user_role WHERE user_id = #{userId}")
    List<Long> selectRoleIdsByUserId(Long userId);

    /**
     * 根据用户ID查询角色列表
     */
    List<Role> selectRolesByUserId(Long userId);
} 