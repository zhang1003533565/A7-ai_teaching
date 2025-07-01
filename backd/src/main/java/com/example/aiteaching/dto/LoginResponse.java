package com.example.aiteaching.dto;

import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;

@Data
@Builder
@AllArgsConstructor
public class LoginResponse {
    private String username;
    private String realName;
    private String role;
    private String avatar;
    private String token;
} 