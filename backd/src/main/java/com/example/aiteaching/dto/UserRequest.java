package com.example.aiteaching.dto;

import lombok.Data;
import java.util.List;

@Data
public class UserRequest {
    private Long id;
    private String username;
    private String password;
    private String realName;
    private String email;
    private String phone;
    private String role;
    private String avatar;
    private List<Long> roleIds; // 角色ID列表
} 