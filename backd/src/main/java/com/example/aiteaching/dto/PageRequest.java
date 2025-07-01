package com.example.aiteaching.dto;

import lombok.Data;

@Data
public class PageRequest {
    private Integer current = 1;
    private Integer size = 10;
    private String username;
    private String realName;
    private Integer status;
    private Long roleId;
} 