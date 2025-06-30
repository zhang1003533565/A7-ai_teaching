package com.example.aiteaching;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.aiteaching.mapper")
public class AITeachingApplication {
    public static void main(String[] args) {
        SpringApplication.run(AITeachingApplication.class, args);
    }
} 