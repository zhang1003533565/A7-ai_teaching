package com.example.aiteaching.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.aiteaching.common.Result;
import com.example.aiteaching.dto.RoleRequest;
import com.example.aiteaching.entity.Role;
import com.example.aiteaching.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/role")
@CrossOrigin
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 创建角色
     */
    @PostMapping
    public Result<Role> createRole(@RequestBody RoleRequest request) {
        try {
            Role role = roleService.createRole(request);
            return Result.success(role);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    /**
     * 更新角色
     */
    @PutMapping("/{id}")
    public Result<Role> updateRole(@PathVariable Long id, @RequestBody RoleRequest request) {
        try {
            request.setId(id);
            Role role = roleService.updateRole(request);
            return Result.success(role);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    /**
     * 删除角色（软删除）
     */
    @DeleteMapping("/{id}")
    public Result<Boolean> deleteRole(@PathVariable Long id) {
        try {
            boolean success = roleService.deleteRole(id);
            return Result.success(success);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    /**
     * 获取角色详情
     */
    @GetMapping("/{id}")
    public Result<Role> getRole(@PathVariable Long id) {
        try {
            Role role = roleService.getRoleById(id);
            return Result.success(role);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    /**
     * 分页查询角色列表
     */
    @GetMapping("/page")
    public Result<IPage<Role>> getRolePage(
            @RequestParam(defaultValue = "1") Integer current,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String roleName,
            @RequestParam(required = false) String roleCode) {
        try {
            Page<Role> page = new Page<>(current, size);
            IPage<Role> rolePage = roleService.getRolePage(page, roleName, roleCode);
            return Result.success(rolePage);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
} 