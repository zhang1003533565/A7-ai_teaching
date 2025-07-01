package com.example.aiteaching.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.aiteaching.entity.RolePermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RolePermissionMapper extends BaseMapper<RolePermission> {
    
    /**
     * 批量插入角色权限关联
     */
    int insertBatch(@Param("list") List<RolePermission> list);
} 