package com.example.aiteaching.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.aiteaching.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
} 