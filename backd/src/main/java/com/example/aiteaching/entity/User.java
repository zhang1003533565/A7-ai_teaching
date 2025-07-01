package com.example.aiteaching.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    private Integer isDeleted;
    
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime deleteTime;
    
    /**
     * 用户角色列表（非数据库字段）
     */
    @TableField(exist = false)
    private List<Role> roles;
} 