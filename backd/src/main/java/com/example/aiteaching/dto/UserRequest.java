package com.example.aiteaching.dto;

import lombok.Data;
import java.util.List;
import java.time.LocalDate;

@Data
public class UserRequest {
    private Long id;
    private String username;
    private String password;
    private String oldPassword;
    private String realName;
    private String email;
    private String phone;
    private String role;
    private String avatar;
    private List<Long> roleIds; // 角色ID列表
    
    // 扩展的个人信息字段
    private String school;
    private String college;
    private String major;
    private String className;
    private String studentNumber;
    private Integer age;
    private LocalDate birthDate;
    private String gender;
    private String idCard;
    private String nation;
    private String politicalStatus;
    private Integer entryYear;
    private Integer grade;
    private String address;
    private String emergencyContact;
    private String emergencyPhone;
} 