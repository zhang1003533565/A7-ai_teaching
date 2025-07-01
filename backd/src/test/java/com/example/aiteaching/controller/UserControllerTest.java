package com.example.aiteaching.controller;

import com.example.aiteaching.common.Result;
import com.example.aiteaching.dto.LoginRequest;
import com.example.aiteaching.dto.LoginResponse;
import com.example.aiteaching.dto.UserRequest;
import com.example.aiteaching.entity.User;
import com.example.aiteaching.service.UserService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

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

        // 模拟服务层行为
        when(userService.login(any(LoginRequest.class))).thenReturn(loginResponse);

        // 执行测试并验证结果
        mockMvc.perform(post("/api/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data.token").value("test-token"));
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
    void updateUser_Success() throws Exception {
        // 准备测试数据
        UserRequest request = new UserRequest();
        request.setId(1L);
        request.setUsername("testuser");
        request.setRealName("更新用户");
        request.setRole("TEACHER");
        request.setRoleIds(Arrays.asList(2L, 3L));

        User updatedUser = new User();
        updatedUser.setId(1L);
        updatedUser.setUsername("testuser");
        updatedUser.setRealName("更新用户");
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

        // 模拟服务层抛出异常
        when(userService.login(any(LoginRequest.class)))
                .thenThrow(new RuntimeException("用户名或密码错误"));

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