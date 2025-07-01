package com.example.aiteaching.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.aiteaching.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {
    
    /**
     * 批量插入用户角色关联
     */
    int insertBatch(@Param("list") List<UserRole> list);
} 