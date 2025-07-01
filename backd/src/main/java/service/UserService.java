package com.example.aiteaching.service;

import com.example.aiteaching.dto.LoginRequest;
import com.example.aiteaching.dto.LoginResponse;

public interface UserService {
    LoginResponse login(LoginRequest request);
} 