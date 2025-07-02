package com.example.aiteaching.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;

@Data
@Accessors(chain = true)
@TableName("sys_user")
public class User {
    @TableId(type = IdType.AUTO)
    private Long id;
    
    private String username;
    
    private String password;
    
    @TableField("real_name")
    private String realName;
    
    private String avatar;
    
    private String email;
    
    private String phone;
    
    private String role;
    
    private Integer status;
    
    @TableLogic(value = "0", delval = "1")
    @TableField("is_deleted")
    private Integer isDeleted;
    
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    
    @TableField(value = "delete_time", fill = FieldFill.UPDATE)
    private LocalDateTime deleteTime;
    
    /**
     * 用户角色列表（非数据库字段）
     */
    @TableField(exist = false)
    private List<Role> roles;

    // 扩展的个人信息字段
    private String school;
    
    private String college;
    
    private String major;
    
    @TableField("class_name")
    private String className;
    
    @TableField("student_number")
    private String studentNumber;
    
    private Integer age;
    
    @TableField("birth_date")
    private LocalDate birthDate;
    
    private String gender;
    
    @TableField("id_card")
    private String idCard;
    
    private String nation;
    
    @TableField("political_status")
    private String politicalStatus;
    
    @TableField("entry_year")
    private Integer entryYear;
    
    private Integer grade;
    
    private String address;
    
    @TableField("emergency_contact")
    private String emergencyContact;
    
    @TableField("emergency_phone")
    private String emergencyPhone;
} 