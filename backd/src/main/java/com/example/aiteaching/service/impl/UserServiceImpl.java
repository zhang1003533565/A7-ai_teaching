package com.example.aiteaching.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.aiteaching.dto.LoginRequest;
import com.example.aiteaching.dto.LoginResponse;
import com.example.aiteaching.entity.User;
import com.example.aiteaching.service.UserService;
import com.example.aiteaching.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserMapper userMapper;

    @Override
    public LoginResponse login(LoginRequest request) {
        // 1. 根据用户名查询用户
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, request.getUsername());
        User user = userMapper.selectOne(queryWrapper);

        if (user == null) {
            throw new RuntimeException("用户不存在");
        }

        // 2. 密码校验（这里使用MD5加密，实际项目中应该使用更安全的加密方式）
        String encryptedPassword = DigestUtils.md5DigestAsHex(request.getPassword().getBytes());
        if (!user.getPassword().equals(encryptedPassword)) {
            throw new RuntimeException("密码错误");
        }

        // 3. 检查用户状态
        if (user.getStatus() != null && user.getStatus() != 1) {
            throw new RuntimeException("账号已被禁用");
        }

        // 4. 生成token（这里简单实现，实际项目中应该使用JWT等方案）
        String token = DigestUtils.md5DigestAsHex((user.getUsername() + System.currentTimeMillis()).getBytes());

        // 5. 返回登录响应
        return LoginResponse.builder()
                .token(token)
                .username(user.getUsername())
                .realName(user.getRealName())
                .role(user.getRole())
                .avatar(user.getAvatar())
                .build();
    }
} 