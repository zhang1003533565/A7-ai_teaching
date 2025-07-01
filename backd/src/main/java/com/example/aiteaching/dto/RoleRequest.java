package com.example.aiteaching.dto;

import lombok.Data;

@Data
public class RoleRequest {
    private Long id;
    private String roleName;
    private String roleCode;
    private String description;
    private Integer status;
    private Integer sort;
} 