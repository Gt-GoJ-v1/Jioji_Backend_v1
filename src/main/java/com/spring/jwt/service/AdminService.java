package com.spring.jwt.service;

import com.spring.jwt.dto.AdminLoginRequest;
import com.spring.jwt.dto.AdminLoginResponse;
import com.spring.jwt.entity.User;
import com.spring.jwt.repository.UserRepository;
import com.spring.jwt.jwt.JwtConfig;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtConfig jwtConfig; // JWT config inject

    @Autowired
    private PasswordEncoder passwordEncoder; // PasswordEncoder inject

    public AdminLoginResponse adminLogin(AdminLoginRequest request) {

        // 1️⃣ User check
        User admin = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        // 2️⃣ Password check using passwordEncoder
        if (!passwordEncoder.matches(request.getPassword(), admin.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        // 3️⃣ Role check
        if (!admin.getRole().equalsIgnoreCase("ADMIN")) {
            throw new RuntimeException("User is not an admin");
        }

        // 4️⃣ Generate JWT token
        String token = Jwts.builder()
                .setSubject(admin.getUsername())
                .claim("role", admin.getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getExpiration() * 1000))
                .signWith(SignatureAlgorithm.HS256, jwtConfig.getSecret())
                .compact();

        // 5️⃣ Return AdminLoginResponse with real JWT token
        return new AdminLoginResponse(
                "Admin login successful",
                admin.getUsername(),
                admin.getRole(),
                token
        );
    }
}
