package com.example.common.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String secret;  // 从配置文件加载密钥（必须是 32+ 字符）

    @Value("${jwt.expiration}")
    private Long expiration;  // Token 过期时间（单位：毫秒）

    private Key key;

    @PostConstruct
    public void init() {
        if (secret == null || secret.length() < 32) {
            throw new IllegalArgumentException("JWT 密钥 (`jwt.secret`) 长度必须至少 32 个字符！");
        }
        this.key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secret.getBytes(StandardCharsets.UTF_8)));
    }

    // 生成 Token
    public String generateToken(String username) {
        try {
            Date now = new Date();
            Date expiryDate = new Date(now.getTime() + expiration);

            return Jwts.builder()
                    .setSubject(username) // 设置 Token 主题（存储用户名）
                    .setIssuedAt(now) // 设置 Token 签发时间
                    .setExpiration(expiryDate) // 设置 Token 过期时间
                    .signWith(key, SignatureAlgorithm.HS512) // 用密钥 key 签名
                    .compact(); // 生成 Token
        } catch (Exception e) {
            throw new RuntimeException("生成 Token 失败", e);
        }
    }

    // 解析 Token（获取 Claims）
    public Claims getClaimsFromToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException e) {
            throw new RuntimeException("解析 Token 失败", e);
        }
    }

    // 获取用户名
    public String getUsernameFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    // 验证 Token 是否有效
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}
