package com.example.aiteaching.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.aiteaching.common.Result;
import com.example.aiteaching.dto.RoleRequest;
import com.example.aiteaching.entity.Role;
import com.example.aiteaching.service.RoleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class RoleControllerTest {

    private MockMvc mockMvc;

    @Mock
    private RoleService roleService;

    @InjectMocks
    private RoleController roleController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(roleController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void createRole_Success() throws Exception {
        // 准备测试数据
        RoleRequest request = new RoleRequest();
        request.setRoleName("测试角色");
        request.setRoleCode("TEST_ROLE");

        Role role = new Role();
        role.setId(1L);
        role.setRoleName("测试角色");
        role.setRoleCode("TEST_ROLE");

        // 模拟服务层行为
        when(roleService.createRole(any(RoleRequest.class))).thenReturn(role);

        // 执行测试并验证结果
        mockMvc.perform(post("/api/role")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data.roleName").value("测试角色"))
                .andExpect(jsonPath("$.data.roleCode").value("TEST_ROLE"));
    }

    @Test
    void updateRole_Success() throws Exception {
        // 准备测试数据
        Long roleId = 1L;
        RoleRequest request = new RoleRequest();
        request.setRoleName("更新角色");
        request.setRoleCode("UPDATE_ROLE");

        Role updatedRole = new Role();
        updatedRole.setId(roleId);
        updatedRole.setRoleName("更新角色");
        updatedRole.setRoleCode("UPDATE_ROLE");

        // 模拟服务层行为
        when(roleService.updateRole(any(RoleRequest.class))).thenReturn(updatedRole);

        // 执行测试并验证结果
        mockMvc.perform(put("/api/role/" + roleId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data.roleName").value("更新角色"))
                .andExpect(jsonPath("$.data.roleCode").value("UPDATE_ROLE"));
    }

    @Test
    void deleteRole_Success() throws Exception {
        // 准备测试数据
        Long roleId = 1L;

        // 模拟服务层行为
        when(roleService.deleteRole(roleId)).thenReturn(true);

        // 执行测试并验证结果
        mockMvc.perform(delete("/api/role/" + roleId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data").value(true));
    }

    @Test
    void getRole_Success() throws Exception {
        // 准备测试数据
        Long roleId = 1L;
        Role role = new Role();
        role.setId(roleId);
        role.setRoleName("测试角色");
        role.setRoleCode("TEST_ROLE");

        // 模拟服务层行为
        when(roleService.getRoleById(roleId)).thenReturn(role);

        // 执行测试并验证结果
        mockMvc.perform(get("/api/role/" + roleId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data.roleName").value("测试角色"))
                .andExpect(jsonPath("$.data.roleCode").value("TEST_ROLE"));
    }

    @Test
    void getRolePage_Success() throws Exception {
        // 准备测试数据
        String roleName = "测试";
        String roleCode = "TEST";
        Page<Role> page = new Page<>(1, 10);
        
        Role role = new Role();
        role.setId(1L);
        role.setRoleName("测试角色");
        role.setRoleCode("TEST_ROLE");
        page.setRecords(java.util.Collections.singletonList(role));
        page.setTotal(1);

        // 模拟服务层行为
        when(roleService.getRolePage(any(Page.class), eq(roleName), eq(roleCode))).thenReturn(page);

        // 执行测试并验证结果
        mockMvc.perform(get("/api/role/page")
                .param("current", "1")
                .param("size", "10")
                .param("roleName", roleName)
                .param("roleCode", roleCode))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data.records[0].roleName").value("测试角色"))
                .andExpect(jsonPath("$.data.records[0].roleCode").value("TEST_ROLE"))
                .andExpect(jsonPath("$.data.total").value(1));
    }
} 