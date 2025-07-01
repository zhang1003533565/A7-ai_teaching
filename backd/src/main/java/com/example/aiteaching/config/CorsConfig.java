package com.example.aiteaching.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        
        // 允许cookies跨域
        config.setAllowCredentials(true);
        
        // 允许特定的源（开发环境）
        config.addAllowedOrigin("http://localhost:5173"); // Vue开发服务器默认端口
        config.addAllowedOrigin("http://localhost:4173"); // Vue预览端口
        
        // 允许访问的头信息
        config.addAllowedHeader("*");
        
        // 预检请求的缓存时间（秒）
        config.setMaxAge(18000L);
        
        // 允许提交请求的方法
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
} 