package com.example.aiteaching.controller;

import com.example.aiteaching.entity.Permission;
import com.example.aiteaching.service.PermissionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class PermissionControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PermissionService permissionService;

    @InjectMocks
    private PermissionController permissionController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(permissionController).build();
    }

    @Test
    void getMenuPermissionTree_ShouldReturnMenuTree() throws Exception {
        // 准备测试数据
        List<Permission> menuTree = Arrays.asList(
            createPermission(1L, "系统管理", "SYSTEM", "menu"),
            createPermission(2L, "用户管理", "USER", "menu")
        );
        
        // Mock服务方法
        when(permissionService.getMenuPermissionTree()).thenReturn(menuTree);

        // 执行请求并验证
        mockMvc.perform(get("/api/permission/menu/tree"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.code").value(200))
               .andExpect(jsonPath("$.data").isArray())
               .andExpect(jsonPath("$.data[0].permissionName").value("系统管理"))
               .andExpect(jsonPath("$.data[1].permissionName").value("用户管理"));
    }

    @Test
    void getRoutePermissionList_ShouldReturnRouteList() throws Exception {
        // 准备测试数据
        List<Permission> routeList = Arrays.asList(
            createPermission(1L, "系统管理路由", "/system", "route"),
            createPermission(2L, "用户管理路由", "/user", "route")
        );
        
        // Mock服务方法
        when(permissionService.getRoutePermissionList()).thenReturn(routeList);

        // 执行请求并验证
        mockMvc.perform(get("/api/permission/route/list"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.code").value(200))
               .andExpect(jsonPath("$.data").isArray())
               .andExpect(jsonPath("$.data[0].permissionName").value("系统管理路由"))
               .andExpect(jsonPath("$.data[1].permissionName").value("用户管理路由"));
    }

    @Test
    void getRoleMenuPermissions_ShouldReturnRoleMenus() throws Exception {
        // 准备测试数据
        Long roleId = 1L;
        List<Permission> roleMenus = Arrays.asList(
            createPermission(1L, "系统管理", "SYSTEM", "menu"),
            createPermission(2L, "用户管理", "USER", "menu")
        );
        
        // Mock服务方法
        when(permissionService.getRoleMenuPermissions(roleId)).thenReturn(roleMenus);

        // 执行请求并验证
        mockMvc.perform(get("/api/permission/role/{roleId}/menu", roleId))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.code").value(200))
               .andExpect(jsonPath("$.data").isArray())
               .andExpect(jsonPath("$.data[0].permissionName").value("系统管理"))
               .andExpect(jsonPath("$.data[1].permissionName").value("用户管理"));
    }

    @Test
    void getRoleRoutePermissions_ShouldReturnRoleRoutes() throws Exception {
        // 准备测试数据
        Long roleId = 1L;
        List<Permission> roleRoutes = Arrays.asList(
            createPermission(1L, "系统管理路由", "/system", "route"),
            createPermission(2L, "用户管理路由", "/user", "route")
        );
        
        // Mock服务方法
        when(permissionService.getRoleRoutePermissions(roleId)).thenReturn(roleRoutes);

        // 执行请求并验证
        mockMvc.perform(get("/api/permission/role/{roleId}/route", roleId))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.code").value(200))
               .andExpect(jsonPath("$.data").isArray())
               .andExpect(jsonPath("$.data[0].permissionName").value("系统管理路由"))
               .andExpect(jsonPath("$.data[1].permissionName").value("用户管理路由"));
    }

    @Test
    void saveRoleMenuPermissions_ShouldSaveSuccessfully() throws Exception {
        // 准备测试数据
        Long roleId = 1L;
        Long[] permissionIds = {1L, 2L};
        
        // Mock服务方法
        doNothing().when(permissionService).saveRoleMenuPermissions(eq(roleId), any(Long[].class));

        // 执行请求并验证
        mockMvc.perform(post("/api/permission/role/{roleId}/menu", roleId)
               .contentType("application/json")
               .content("[1, 2]"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.code").value(200));
        
        // 验证服务方法是否被调用
        verify(permissionService).saveRoleMenuPermissions(eq(roleId), any(Long[].class));
    }

    @Test
    void saveRoleRoutePermissions_ShouldSaveSuccessfully() throws Exception {
        // 准备测试数据
        Long roleId = 1L;
        Long[] permissionIds = {1L, 2L};
        
        // Mock服务方法
        doNothing().when(permissionService).saveRoleRoutePermissions(eq(roleId), any(Long[].class));

        // 执行请求并验证
        mockMvc.perform(post("/api/permission/role/{roleId}/route", roleId)
               .contentType("application/json")
               .content("[1, 2]"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.code").value(200));
        
        // 验证服务方法是否被调用
        verify(permissionService).saveRoleRoutePermissions(eq(roleId), any(Long[].class));
    }

    @Test
    void getUserPermissions_ShouldReturnUserPermissions() throws Exception {
        // 准备测试数据
        Long userId = 1L;
        List<Permission> userPermissions = Arrays.asList(
            createPermission(1L, "系统管理", "SYSTEM", "menu"),
            createPermission(2L, "用户管理路由", "/user", "route")
        );
        
        // Mock服务方法
        when(permissionService.getUserPermissions(userId)).thenReturn(userPermissions);

        // 执行请求并验证
        mockMvc.perform(get("/api/permission/user/{userId}", userId))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.code").value(200))
               .andExpect(jsonPath("$.data").isArray())
               .andExpect(jsonPath("$.data[0].permissionName").value("系统管理"))
               .andExpect(jsonPath("$.data[1].permissionName").value("用户管理路由"));
    }

    // 辅助方法：创建Permission对象
    private Permission createPermission(Long id, String name, String code, String type) {
        Permission permission = new Permission();
        permission.setId(id);
        permission.setPermissionName(name);
        permission.setPermissionCode(code);
        permission.setPermissionType("menu".equals(type) ? 1 : 2);  // 1-菜单，2-按钮
        return permission;
    }
} 