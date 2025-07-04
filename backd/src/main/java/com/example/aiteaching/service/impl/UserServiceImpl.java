package com.example.aiteaching.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.aiteaching.dto.*;
import com.example.aiteaching.entity.User;
import com.example.aiteaching.entity.UserRole;
import com.example.aiteaching.entity.Role;
import com.example.aiteaching.mapper.UserMapper;
import com.example.aiteaching.mapper.UserRoleMapper;
import com.example.aiteaching.mapper.RoleMapper;
import com.example.aiteaching.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public LoginResponse login(LoginRequest request) {
        // 1. 根据用户名查询用户
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, request.getUsername())
                   .eq(User::getIsDeleted, 0);  // 添加未删除条件
        User user = userMapper.selectOne(queryWrapper);

        if (user == null) {
            throw new RuntimeException("用户不存在");
        }

        // 2. 密码校验（明文比较）
        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("密码错误");
        }

        // 3. 检查用户状态
        if (user.getStatus() != null && user.getStatus() != 1) {
            throw new RuntimeException("账号已被禁用");
        }

        // 4. 检查用户角色
        List<Role> roles = roleMapper.selectRolesByUserId(user.getId());
        if (roles == null || roles.isEmpty()) {
            // 如果用户没有角色，根据user.role字段分配默认角色
            LambdaQueryWrapper<Role> roleWrapper = new LambdaQueryWrapper<>();
            roleWrapper.eq(Role::getRoleCode, user.getRole().toLowerCase())
                      .eq(Role::getIsDeleted, 0);
            Role defaultRole = roleMapper.selectOne(roleWrapper);
            
            if (defaultRole != null) {
                // 为用户分配默认角色
                UserRole userRole = new UserRole();
                userRole.setUserId(user.getId());
                userRole.setRoleId(defaultRole.getId());
                userRoleMapper.insert(userRole);
                roles = Collections.singletonList(defaultRole);
            }
        }

        // 5. 使用用户表中的role字段
        String role = user.getRole().toLowerCase();

        // 6. 生成token
        String token = Jwts.builder()
                .setSubject(user.getId().toString())
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000)) // 24小时过期
                .signWith(SignatureAlgorithm.HS256, "your-secret-key")
                .compact();

        // 7. 返回登录响应
        return new LoginResponse()
                .setId(user.getId())
                .setToken(token)
                .setUsername(user.getUsername())
                .setRealName(user.getRealName())
                .setRole(role)
                .setAvatar(user.getAvatar());
    }

    @Override
    @Transactional
    public User createUser(UserRequest request) {
        // 验证用户名是否已存在
        Assert.isTrue(checkUsernameUnique(request.getUsername()), "用户名已存在");
        
        // 验证角色
        Assert.isTrue(request.getRoleIds() != null && !request.getRoleIds().isEmpty(), "至少需要选择一个角色");
        
        // 获取第一个角色的信息并设置role字段
        Long firstRoleId = request.getRoleIds().get(0);
        Role role = roleMapper.selectById(firstRoleId);
        Assert.notNull(role, "所选角色不存在");
        
        // 创建用户
        User user = new User();
        BeanUtils.copyProperties(request, user);
        user.setRole(role.getRoleCode().toLowerCase()); // 设置角色代码作为role字段
        userMapper.insert(user);
        
        // 绑定角色
        if (request.getRoleIds() != null && !request.getRoleIds().isEmpty()) {
            bindUserRoles(user.getId(), request.getRoleIds());
        }
        
        return user;
    }

    @Override
    @Transactional
    public User updateUser(UserRequest request) {
        // 验证用户是否存在
        User existingUser = userMapper.selectById(request.getId());
        Assert.notNull(existingUser, "用户不存在");
        
        // 如果修改了用户名，需要验证唯一性
        if (!existingUser.getUsername().equals(request.getUsername())) {
            Assert.isTrue(checkUsernameUnique(request.getUsername()), "用户名已存在");
        }
        
        // 如果更新了角色，需要更新role字段
        if (request.getRoleIds() != null && !request.getRoleIds().isEmpty()) {
            Long firstRoleId = request.getRoleIds().get(0);
            Role role = roleMapper.selectById(firstRoleId);
            Assert.notNull(role, "所选角色不存在");
            request.setRole(role.getRoleCode().toLowerCase());
        }
        
        // 更新用户信息
        BeanUtils.copyProperties(request, existingUser);
        userMapper.updateById(existingUser);
        
        // 更新角色绑定
        if (request.getRoleIds() != null) {
            // 先删除原有角色
            unbindUserRoles(existingUser.getId());
            // 绑定新角色
            if (!request.getRoleIds().isEmpty()) {
                bindUserRoles(existingUser.getId(), request.getRoleIds());
            }
        }
        
        return existingUser;
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        User user = userMapper.selectById(id);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        
        // 执行逻辑删除
        int rows = userMapper.logicalDelete(id, LocalDateTime.now());
        if (rows != 1) {
            throw new RuntimeException("删除用户失败");
        }
        
        // 删除用户角色关联
        userRoleMapper.deleteByUserId(id);
    }

    @Override
    public User getUserById(Long id) {
        User user = userMapper.selectById(id);
        Assert.notNull(user, "用户不存在");
        
        // 加载用户的角色信息
        List<Role> roles = roleMapper.selectRolesByUserId(id);
        user.setRoles(roles);
        
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.selectList(null);
    }

    @Override
    public PageResponse<User> getUserPage(PageRequest request) {
        Page<User> page = new Page<>(request.getCurrent(), request.getSize());
        Page<User> userPage = userMapper.selectPage(page, null);
        return new PageResponse<User>(userPage.getRecords(), userPage.getTotal(), userPage.getSize(), userPage.getCurrent());
    }

    @Override
    public void resetPassword(Long id) {
        User user = userMapper.selectById(id);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        
        // 设置默认密码为123456
        String defaultPassword = "123456";
        user.setPassword(defaultPassword);
        userMapper.updateById(user);
    }

    // 检查用户名是否唯一
    private boolean checkUsernameUnique(String username) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, username)
               .eq(User::getIsDeleted, 0);  // 只检查未删除的用户
        return userMapper.selectCount(wrapper) == 0;
    }

    // 绑定用户角色
    private void bindUserRoles(Long userId, List<Long> roleIds) {
        for (Long roleId : roleIds) {
            UserRole userRole = new UserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);
            userRoleMapper.insert(userRole);
        }
    }

    // 解绑用户角色
    private void unbindUserRoles(Long userId) {
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRole::getUserId, userId);
        userRoleMapper.delete(wrapper);
    }
} 