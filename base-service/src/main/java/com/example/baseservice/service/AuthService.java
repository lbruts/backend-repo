package com.example.baseservice.service;

import com.example.baseservice.model.entity.User;
import com.example.baseservice.repository.UserMapper;
import com.example.common.exception.BusinessException;
import com.example.common.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserMapper userMapper;
    private final JwtUtils jwtUtils;  // 使用 JWT 生成 Token
    private final PasswordEncoder passwordEncoder; // 这里注入 BCryptPasswordEncoder

    @Transactional
    public String login(String username, String password) {
        // 获取用户信息
        User user = userMapper.findByUsername(username);
        if (user == null) {
            throw new BusinessException("用户不存在");
        }
        System.out.println(password+"//"+user.getPassword());
        // 验证密码
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BusinessException("密码错误");
        }

        // 检查用户状态
        if (user.getStatus() != 1) {
            throw new BusinessException("用户已被禁用");
        }

        try {
            // 生成新token
            String token = jwtUtils.generateToken(username);
            System.out.println("token:"+token);
            
            // 更新数据库中的token
            userMapper.updateToken(username, token);
            
            return token;
        } catch (Exception e) {
            throw new BusinessException("生成token失败: " + e.getMessage());
        }
    }

    @Transactional
    public void logout(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new BusinessException("用户名不能为空");
        }
        userMapper.updateToken(username, null);
    }

    public boolean validateToken(String token, String username) {
        if (token == null || username == null) {
            return false;
        }

        try {
            // 验证token是否有效
            if (!jwtUtils.validateToken(token)) {
                return false;
            }

            // 验证token中的用户名是否匹配
            String tokenUsername = jwtUtils.getUsernameFromToken(token);
            if (!username.equals(tokenUsername)) {
                return false;
            }

            // 验证token是否与数据库中的一致
            User user = userMapper.findByUsername(username);
            return user != null && token.equals(user.getToken());
        } catch (Exception e) {
            return false;
        }
    }

    public String getUsernameFromToken(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        return jwtUtils.getUsernameFromToken(token);
    }
}
