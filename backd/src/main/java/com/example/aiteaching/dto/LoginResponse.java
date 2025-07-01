package com.example.aiteaching.dto;

import com.example.aiteaching.entity.Permission;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class LoginResponse {
    private Long id;
    private String username;
    private String token;
    private String role;
    private String realName;
    private String avatar;
    private List<Permission> permissions;
} 