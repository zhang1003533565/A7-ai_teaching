package com.example.aiteaching.config;

import com.example.aiteaching.common.util.UserContext;
import com.example.aiteaching.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 如果是登录请求，直接放行
        if (request.getRequestURI().equals("/api/user/login")) {
            return true;
        }

        // 从请求头中获取token
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            try {
                // 解析token
                Claims claims = Jwts.parser()
                        .setSigningKey("your-secret-key")
                        .parseClaimsJws(token)
                        .getBody();

                // 从token中获取用户ID和角色
                Long userId = Long.parseLong(claims.getSubject());
                String role = claims.get("role", String.class);

                // 设置到UserContext中
                UserContext.setCurrentUserId(userId);
                UserContext.setCurrentUserRole(role);

                return true;
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }
        }

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // 请求结束后清理ThreadLocal
        UserContext.clear();
    }
} 