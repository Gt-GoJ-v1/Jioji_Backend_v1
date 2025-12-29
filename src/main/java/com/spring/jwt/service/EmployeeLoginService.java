


package com.spring.jwt.service;

import com.spring.jwt.dto.LoginRequest;
import com.spring.jwt.entity.User;
import com.spring.jwt.jwt.JwtConfig;
import com.spring.jwt.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EmployeeLoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtConfig jwtConfig;  // JWT config inject

    // Employee login and JWT generation
    public String loginEmployee(LoginRequest loginRequest) {

        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Password check
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        // Role check
        if (!"EMPLOYEE".equals(user.getRole())) {
            throw new RuntimeException("User is not an employee");
        }

        // JWT generate
        String token = Jwts.builder()
                .setSubject(user.getUsername())
                .claim("role", user.getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getExpiration() * 1000))
                .signWith(SignatureAlgorithm.HS256, jwtConfig.getSecret())
                .compact();

        return token;
    }

}


