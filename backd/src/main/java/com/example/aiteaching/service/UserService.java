package com.example.aiteaching.service;

import com.example.aiteaching.dto.LoginRequest;
import com.example.aiteaching.dto.LoginResponse;
import com.example.aiteaching.dto.PageRequest;
import com.example.aiteaching.dto.PageResponse;
import com.example.aiteaching.dto.UserRequest;
import com.example.aiteaching.entity.User;

import java.util.List;

public interface UserService {
    LoginResponse login(LoginRequest request);
    
    User createUser(UserRequest request);
    
    User updateUser(UserRequest request);
    
    void deleteUser(Long id);
    
    User getUserById(Long id);
    
    List<User> getAllUsers();
    
    PageResponse<User> getUserPage(PageRequest request);

    void resetPassword(Long id);
} 