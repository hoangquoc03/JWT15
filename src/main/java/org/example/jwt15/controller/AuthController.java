package org.example.jwt15.controller;


import lombok.RequiredArgsConstructor;
import org.example.jwt15.dto.AuthResponse;
import org.example.jwt15.dto.LoginRequest;
import org.example.jwt15.dto.RegisterRequest;
import org.example.jwt15.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public String register(
            @RequestBody RegisterRequest request) {

        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(
            @RequestBody LoginRequest request) {

        return authService.login(request);
    }
}