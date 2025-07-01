package com.example.aiteaching.controller;

import com.example.aiteaching.common.Result;
import com.example.aiteaching.service.PermissionService;
import com.example.aiteaching.common.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping("/menu/tree")
    public Result getMenuPermissionTree() {
        return Result.success(permissionService.getMenuPermissionTree());
    }

    @GetMapping("/route/list")
    public Result getRoutePermissionList() {
        return Result.success(permissionService.getRoutePermissionList());
    }

    @GetMapping("/role/{roleId}/menu")
    public Result getRoleMenuPermissions(@PathVariable Long roleId) {
        return Result.success(permissionService.getRoleMenuPermissions(roleId));
    }

    @GetMapping("/role/{roleId}/route")
    public Result getRoleRoutePermissions(@PathVariable Long roleId) {
        return Result.success(permissionService.getRoleRoutePermissions(roleId));
    }

    @PostMapping("/role/{roleId}/menu")
    public Result saveRoleMenuPermissions(@PathVariable Long roleId, @RequestBody List<Long> permissionIds) {
        Long[] permissionIdArray = permissionIds.toArray(new Long[0]);
        permissionService.saveRoleMenuPermissions(roleId, permissionIdArray);
        return Result.success();
    }

    @PostMapping("/role/{roleId}/route")
    public Result saveRoleRoutePermissions(@PathVariable Long roleId, @RequestBody List<Long> permissionIds) {
        Long[] permissionIdArray = permissionIds.toArray(new Long[0]);
        permissionService.saveRoleRoutePermissions(roleId, permissionIdArray);
        return Result.success();
    }

    @GetMapping("/user/{userId}")
    public Result getUserPermissions(@PathVariable Long userId) {
        return Result.success(permissionService.getUserPermissions(userId));
    }

    @GetMapping("/user/menus")
    public Result getUserMenuPermissions() {
        Long userId = UserContext.getCurrentUserId();
        if (userId == null) {
            return Result.fail("用户未登录");
        }
        return Result.success(permissionService.getUserMenuPermissions(userId));
    }
} 