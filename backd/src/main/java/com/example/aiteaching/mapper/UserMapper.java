package com.example.aiteaching.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.aiteaching.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Update("UPDATE sys_user SET is_deleted = 1, delete_time = #{deleteTime} WHERE id = #{id}")
    int logicalDelete(@Param("id") Long id, @Param("deleteTime") LocalDateTime deleteTime);
} 