package com.example.baseservice.controller;

import com.example.baseservice.model.dto.LoginRequest;
import com.example.baseservice.service.AuthService;
import com.example.common.response.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public Result<String> login(@RequestBody LoginRequest request) {
        String token = authService.login(request.getUsername(), request.getPassword());
        return Result.success(token);
    }

    @PostMapping("/logout")
    public Result<Void> logout(@RequestHeader("Authorization") String token) {
        String username = authService.getUsernameFromToken(token);
        authService.logout(username);
        return Result.success(null);
    }
}
