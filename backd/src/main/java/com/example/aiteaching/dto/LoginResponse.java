package com.example.aiteaching.dto;

import com.example.aiteaching.entity.Permission;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private Long id;
    private String username;
    private String token;
    private String role;
    private String realName;
    private String avatar;
    private List<Permission> permissions;
} 