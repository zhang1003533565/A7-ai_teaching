package com.example.aiteaching.controller;

import com.example.aiteaching.common.Result;
import com.example.aiteaching.dto.*;
import com.example.aiteaching.entity.User;
import com.example.aiteaching.entity.Permission;
import com.example.aiteaching.service.UserService;
import com.example.aiteaching.service.PermissionService;
import com.example.aiteaching.common.util.UserContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @Mock
    private PermissionService permissionService;

    @InjectMocks
    private UserController userController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void login_Success() throws Exception {
        // 准备测试数据
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("test");
        loginRequest.setPassword("123456");

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken("test-token");
        loginResponse.setId(1L);
        loginResponse.setRole("user");

        List<Permission> permissions = new ArrayList<>();
        Permission permission = new Permission();
        permission.setId(1L);
        permission.setPermissionName("test");
        permissions.add(permission);

        // 模拟服务层行为
        when(userService.login(any(LoginRequest.class))).thenReturn(loginResponse);
        when(permissionService.getUserPermissions(any(Long.class))).thenReturn(permissions);

        // 执行测试并验证结果
        mockMvc.perform(post("/api/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data.token").value("test-token"));
    }

    @Test
    void login_Admin_Success() throws Exception {
        // 准备测试数据
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("admin");
        loginRequest.setPassword("123456");

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken("admin-token");
        loginResponse.setId(1L);
        loginResponse.setRole("admin");

        List<Permission> allPermissions = new ArrayList<>();
        Permission permission1 = new Permission();
        permission1.setId(1L);
        permission1.setPermissionName("admin_permission");
        allPermissions.add(permission1);

        // 模拟服务层行为
        when(userService.login(any(LoginRequest.class))).thenReturn(loginResponse);
        when(permissionService.getAllPermissions()).thenReturn(allPermissions);

        // 执行测试并验证结果
        mockMvc.perform(post("/api/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data.token").value("admin-token"))
                .andExpect(jsonPath("$.data.role").value("admin"));
    }

    @Test
    void getCurrentUserInfo_Success() throws Exception {
        // 准备测试数据
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        user.setUsername("testuser");
        user.setRole("admin");

        // 模拟 UserContext
        try (MockedStatic<UserContext> mockedStatic = mockStatic(UserContext.class)) {
            mockedStatic.when(UserContext::getCurrentUserId).thenReturn(userId);

            // 模拟服务层行为
            when(userService.getUserById(userId)).thenReturn(user);
            when(permissionService.getAllPermissions()).thenReturn(new ArrayList<>());

            // 执行测试并验证结果
            mockMvc.perform(get("/api/user/info"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.code").value(200))
                    .andExpect(jsonPath("$.data.username").value("testuser"))
                    .andExpect(jsonPath("$.data.role").value("admin"));
        }
    }

    @Test
    void getCurrentUserInfo_UserNotLoggedIn() throws Exception {
        // 模拟 UserContext
        try (MockedStatic<UserContext> mockedStatic = mockStatic(UserContext.class)) {
            mockedStatic.when(UserContext::getCurrentUserId).thenReturn(null);

            // 执行测试并验证结果
            mockMvc.perform(get("/api/user/info"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.code").value(500))
                    .andExpect(jsonPath("$.message").value("用户未登录"));
        }
    }

    @Test
    void updateUser_Success() throws Exception {
        // 准备测试数据
        UserRequest request = new UserRequest();
        request.setId(1L);
        request.setUsername("testuser");
        request.setRealName("更新用户");
        request.setEmail("test@example.com");
        request.setPhone("13800138000");
        request.setRole("TEACHER");
        request.setRoleIds(Arrays.asList(2L, 3L));

        User updatedUser = new User();
        updatedUser.setId(1L);
        updatedUser.setUsername("testuser");
        updatedUser.setRealName("更新用户");
        updatedUser.setEmail("test@example.com");
        updatedUser.setPhone("13800138000");
        updatedUser.setRole("TEACHER");

        // 模拟服务层行为
        when(userService.updateUser(any(UserRequest.class))).thenReturn(updatedUser);

        // 执行测试并验证结果
        mockMvc.perform(put("/api/user/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data.username").value("testuser"))
                .andExpect(jsonPath("$.data.realName").value("更新用户"))
                .andExpect(jsonPath("$.data.email").value("test@example.com"))
                .andExpect(jsonPath("$.data.phone").value("13800138000"))
                .andExpect(jsonPath("$.data.role").value("TEACHER"));
    }

    @Test
    void updateUser_ValidationError() throws Exception {
        // 准备测试数据 - 缺少必要字段
        UserRequest request = new UserRequest();
        request.setId(1L);
        // 没有设置用户名和其他必要字段

        // 模拟服务层抛出异常
        when(userService.updateUser(any(UserRequest.class)))
                .thenThrow(new RuntimeException("用户名不能为空"));

        // 执行测试并验证结果
        mockMvc.perform(put("/api/user/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(500))
                .andExpect(jsonPath("$.message").value("用户名不能为空"));
    }

    @Test
    void resetPassword_Success() throws Exception {
        // 准备测试数据
        Long userId = 1L;

        // 模拟服务层行为
        doNothing().when(userService).resetPassword(userId);

        // 执行测试并验证结果
        mockMvc.perform(post("/api/user/reset-password/" + userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data").value(true));

        // 验证服务方法是否被调用
        verify(userService).resetPassword(userId);
    }

    @Test
    void getUserPermissions_Success() throws Exception {
        // 准备测试数据
        Long userId = 1L;
        String userRole = "user";
        List<Permission> permissions = new ArrayList<>();
        Permission permission = new Permission();
        permission.setId(1L);
        permission.setPermissionName("test_permission");
        permissions.add(permission);

        // 模拟 UserContext
        try (MockedStatic<UserContext> mockedStatic = mockStatic(UserContext.class)) {
            mockedStatic.when(UserContext::getCurrentUserId).thenReturn(userId);
            mockedStatic.when(UserContext::getCurrentUserRole).thenReturn(userRole);

            // 模拟服务层行为
            when(permissionService.getUserPermissions(userId)).thenReturn(permissions);

            // 执行测试并验证结果
            mockMvc.perform(get("/api/user/permissions"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.code").value(200))
                    .andExpect(jsonPath("$.data[0].permissionName").value("test_permission"));
        }
    }

    @Test
    void getUserPage_Success() throws Exception {
        // 准备测试数据
        PageRequest pageRequest = new PageRequest();
        pageRequest.setCurrent(1);
        pageRequest.setSize(10);

        PageResponse<User> pageResponse = new PageResponse<>();
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        users.add(user);
        pageResponse.setRecords(users);
        pageResponse.setTotal(1L);

        // 模拟服务层行为
        when(userService.getUserPage(any(PageRequest.class))).thenReturn(pageResponse);

        // 执行测试并验证结果
        mockMvc.perform(get("/api/user/page")
                .param("current", "1")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data.records[0].username").value("testuser"))
                .andExpect(jsonPath("$.data.total").value(1));
    }

    @Test
    void addUser_Success() throws Exception {
        // 准备测试数据
        UserRequest request = new UserRequest();
        request.setUsername("testuser");
        request.setPassword("123456");
        request.setRealName("测试用户");
        request.setRole("TEACHER");
        request.setRoleIds(Arrays.asList(1L, 2L));

        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setRealName("测试用户");
        user.setRole("TEACHER");

        // 模拟服务层行为
        when(userService.createUser(any(UserRequest.class))).thenReturn(user);

        // 执行测试并验证结果
        mockMvc.perform(post("/api/user/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data.username").value("testuser"))
                .andExpect(jsonPath("$.data.realName").value("测试用户"))
                .andExpect(jsonPath("$.data.role").value("TEACHER"));
    }

    @Test
    void deleteUser_Success() throws Exception {
        // 准备测试数据
        Long userId = 1L;

        // 执行测试并验证结果
        mockMvc.perform(delete("/api/user/delete/" + userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data").value(true));
    }

    @Test
    void getUser_Success() throws Exception {
        // 准备测试数据
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        user.setUsername("testuser");
        user.setRealName("测试用户");
        user.setRole("TEACHER");

        // 模拟服务层行为
        when(userService.getUserById(userId)).thenReturn(user);

        // 执行测试并验证结果
        mockMvc.perform(get("/api/user/get/" + userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data.username").value("testuser"))
                .andExpect(jsonPath("$.data.realName").value("测试用户"))
                .andExpect(jsonPath("$.data.role").value("TEACHER"));
    }

    @Test
    void listUsers_Success() throws Exception {
        // 准备测试数据
        User user1 = new User();
        user1.setId(1L);
        user1.setUsername("user1");
        user1.setRole("TEACHER");

        User user2 = new User();
        user2.setId(2L);
        user2.setUsername("user2");
        user2.setRole("STUDENT");

        List<User> users = Arrays.asList(user1, user2);

        // 模拟服务层行为
        when(userService.getAllUsers()).thenReturn(users);

        // 执行测试并验证结果
        mockMvc.perform(get("/api/user/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data[0].username").value("user1"))
                .andExpect(jsonPath("$.data[0].role").value("TEACHER"))
                .andExpect(jsonPath("$.data[1].username").value("user2"))
                .andExpect(jsonPath("$.data[1].role").value("STUDENT"));
    }

    @Test
    void login_Failure() throws Exception {
        // 准备测试数据
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("test");
        loginRequest.setPassword("wrongpassword");

        // 模拟服务层返回null
        when(userService.login(any(LoginRequest.class))).thenReturn(null);

        // 执行测试并验证结果
        mockMvc.perform(post("/api/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(500))
                .andExpect(jsonPath("$.message").value("用户名或密码错误"));
    }

    @Test
    void addUser_Failure_DuplicateUsername() throws Exception {
        // 准备测试数据
        UserRequest request = new UserRequest();
        request.setUsername("existinguser");
        request.setPassword("123456");

        // 模拟服务层抛出异常
        when(userService.createUser(any(UserRequest.class)))
                .thenThrow(new RuntimeException("用户名已存在"));

        // 执行测试并验证结果
        mockMvc.perform(post("/api/user/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(500))
                .andExpect(jsonPath("$.message").value("用户名已存在"));
    }
} 