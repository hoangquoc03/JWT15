package org.example.jwt15.service;


import lombok.RequiredArgsConstructor;
import org.example.jwt15.dto.AuthResponse;
import org.example.jwt15.dto.LoginRequest;
import org.example.jwt15.dto.RegisterRequest;
import org.example.jwt15.entity.User;
import org.example.jwt15.repository.UserRepository;
import org.example.jwt15.security.JwtService;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public String register(RegisterRequest request) {

        if(userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = User.builder()
                .phone(request.getPhone())
                .email(request.getEmail())
                .password(
                        passwordEncoder.encode(request.getPassword()))
                .role("ROLE_USER")
                .build();

        userRepository.save(user);

        return "Register success";
    }

    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        String accessToken =
                jwtService.generateAccessToken(
                        request.getEmail());

        String refreshToken =
                jwtService.generateRefreshToken(
                        request.getEmail());

        return new AuthResponse(
                accessToken,
                refreshToken
        );
    }
}