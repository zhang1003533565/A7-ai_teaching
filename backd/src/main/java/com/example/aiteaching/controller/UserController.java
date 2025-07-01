package com.example.aiteaching.controller;

import com.example.aiteaching.common.Result;
import com.example.aiteaching.dto.*;
import com.example.aiteaching.entity.User;
import com.example.aiteaching.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result<LoginResponse> login(@RequestBody LoginRequest request) {
        try {
            LoginResponse response = userService.login(request);
            return Result.success(response);
        } catch (RuntimeException e) {
            return Result.error(e.getMessage());
        }
    }

    @PostMapping("/add")
    public Result<User> addUser(@RequestBody UserRequest request) {
        try {
            User user = userService.createUser(request);
            return Result.success(user);
        } catch (RuntimeException e) {
            return Result.error(e.getMessage());
        }
    }

    @PutMapping("/update")
    public Result<User> updateUser(@RequestBody UserRequest request) {
        try {
            User user = userService.updateUser(request);
            return Result.success(user);
        } catch (RuntimeException e) {
            return Result.error(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public Result<Boolean> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return Result.success(true);
        } catch (RuntimeException e) {
            return Result.error(e.getMessage());
        }
    }

    @GetMapping("/get/{id}")
    public Result<User> getUser(@PathVariable Long id) {
        try {
            User user = userService.getUserById(id);
            return Result.success(user);
        } catch (RuntimeException e) {
            return Result.error(e.getMessage());
        }
    }

    @GetMapping("/list")
    public Result<List<User>> listUsers() {
        try {
            List<User> users = userService.getAllUsers();
            return Result.success(users);
        } catch (RuntimeException e) {
            return Result.error(e.getMessage());
        }
    }
    
    @GetMapping("/page")
    public Result<PageResponse<User>> getUserPage(PageRequest request) {
        try {
            PageResponse<User> page = userService.getUserPage(request);
            return Result.success(page);
        } catch (RuntimeException e) {
            return Result.error(e.getMessage());
        }
    }

    @PostMapping("/reset-password/{id}")
    public Result<Boolean> resetPassword(@PathVariable Long id) {
        try {
            userService.resetPassword(id);
            return Result.success(true);
        } catch (RuntimeException e) {
            return Result.error(e.getMessage());
        }
    }
} 