package com.example.aiteaching.controller;

import com.example.aiteaching.common.Result;
import com.example.aiteaching.dto.*;
import com.example.aiteaching.entity.User;
import com.example.aiteaching.entity.Permission;
import com.example.aiteaching.entity.Role;
import com.example.aiteaching.entity.UserRole;
import com.example.aiteaching.service.UserService;
import com.example.aiteaching.service.PermissionService;
import com.example.aiteaching.service.RoleService;
import com.example.aiteaching.mapper.RoleMapper;
import com.example.aiteaching.mapper.UserRoleMapper;
import com.example.aiteaching.common.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import java.util.List;
import java.util.Collections;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @PostMapping("/login")
    public Result<LoginResponse> login(@RequestBody LoginRequest request) {
        try {
            LoginResponse response = userService.login(request);
            if (response != null) {
                // 如果是管理员，返回所有权限
                if ("admin".equals(response.getRole())) {
                    List<Permission> allPermissions = permissionService.getAllPermissions();
                    response.setPermissions(allPermissions);
                } else {
                    // 获取用户的权限
                    List<Permission> permissions = permissionService.getUserPermissions(response.getId());
                    response.setPermissions(permissions);
                }
                return Result.success(response);
            }
            return Result.error("用户名或密码错误");
        } catch (RuntimeException e) {
            return Result.error(e.getMessage());
        } catch (Exception e) {
            return Result.error("登录失败：" + e.getMessage());
        }
    }

    @PostMapping("/add")
    public Result<User> addUser(@RequestBody UserRequest request) {
        try {
            User user = userService.createUser(request);
            return Result.success(user);
        } catch (RuntimeException e) {
            return Result.error(e.getMessage());
        }
    }

    @PutMapping("/update")
    public Result<User> updateUser(@RequestBody UserRequest request) {
        try {
            User user = userService.updateUser(request);
            return Result.success(user);
        } catch (RuntimeException e) {
            return Result.error(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public Result<Boolean> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return Result.success(true);
        } catch (RuntimeException e) {
            return Result.error(e.getMessage());
        }
    }

    @GetMapping("/get/{id}")
    public Result<User> getUser(@PathVariable Long id) {
        try {
            User user = userService.getUserById(id);
            return Result.success(user);
        } catch (RuntimeException e) {
            return Result.error(e.getMessage());
        }
    }

    @GetMapping("/list")
    public Result<List<User>> listUsers() {
        try {
            List<User> users = userService.getAllUsers();
            return Result.success(users);
        } catch (RuntimeException e) {
            return Result.error(e.getMessage());
        }
    }
    
    @GetMapping("/page")
    public Result<PageResponse<User>> getUserPage(PageRequest request) {
        try {
            PageResponse<User> page = userService.getUserPage(request);
            return Result.success(page);
        } catch (RuntimeException e) {
            return Result.error(e.getMessage());
        }
    }

    @PostMapping("/reset-password/{id}")
    public Result<Boolean> resetPassword(@PathVariable Long id) {
        try {
            userService.resetPassword(id);
            return Result.success(true);
        } catch (RuntimeException e) {
            return Result.error(e.getMessage());
        }
    }

    @GetMapping("/permissions")
    public Result<List<Permission>> getUserPermissions() {
        Long userId = UserContext.getCurrentUserId();
        String userRole = UserContext.getCurrentUserRole();
        
        if ("admin".equals(userRole)) {
            return Result.success(permissionService.getAllPermissions());
        }
        return Result.success(permissionService.getUserPermissions(userId));
    }

    @GetMapping("/info")
    public Result<User> getCurrentUserInfo() {
        try {
            Long userId = UserContext.getCurrentUserId();
            if (userId == null) {
                return Result.error("用户未登录");
            }
            User user = userService.getUserById(userId);
            if (user != null) {
                // 获取用户的角色和权限信息
                if (user.getRoles() == null || user.getRoles().isEmpty()) {
                    // 如果用户没有角色，根据user.role字段分配默认角色
                    LambdaQueryWrapper<Role> roleWrapper = new LambdaQueryWrapper<>();
                    roleWrapper.eq(Role::getRoleCode, user.getRole().toLowerCase())
                              .eq(Role::getIsDeleted, 0);
                    Role defaultRole = roleMapper.selectOne(roleWrapper);
                    
                    if (defaultRole != null) {
                        // 为用户分配默认角色
                        UserRole userRole = new UserRole();
                        userRole.setUserId(userId);
                        userRole.setRoleId(defaultRole.getId());
                        userRoleMapper.insert(userRole);
                        user.setRoles(Collections.singletonList(defaultRole));
                    }
                }
                
                if (user.getRoles() != null && !user.getRoles().isEmpty()) {
                    if ("admin".equals(user.getRole())) {
                        List<Permission> allPermissions = permissionService.getAllPermissions();
                        user.getRoles().get(0).setPermissions(allPermissions);
                    } else {
                        List<Permission> permissions = permissionService.getUserPermissions(userId);
                        user.getRoles().get(0).setPermissions(permissions);
                    }
                }
                return Result.success(user);
            }
            return Result.error("用户不存在");
        } catch (RuntimeException e) {
            return Result.error(e.getMessage());
        }
    }
} 