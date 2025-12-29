package com.spring.jwt.dto;



import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLoginResponse {
    private String username;
    private String role;
    private String token; // JWT token after login
}

