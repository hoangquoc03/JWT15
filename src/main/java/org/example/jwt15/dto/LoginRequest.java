package org.example.jwt15.dto;

import lombok.Data;

@Data
public class LoginRequest {

    private String email;

    private String password;
}