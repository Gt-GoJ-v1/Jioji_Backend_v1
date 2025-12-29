package com.spring.jwt.service;
import com.spring.jwt.dto.UserRegistrationRequest;
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
import java.util.Optional;

@Service
public class UserEmployeeService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtConfig jwtConfig;

    // -------------------- Registration --------------------
    public User registerUser(UserRegistrationRequest request) throws Exception {
        Optional<User> existing = userRepository.findByUsername(request.getUsername());
        if (existing.isPresent()) throw new Exception("Username already exists");

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole() != null ? request.getRole() : "USER");

        return userRepository.save(user);
    }

    // -------------------- Login with JWT --------------------
    public String loginUser(LoginRequest request) throws Exception {
        // 1️⃣ User check
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new Exception("User not found"));

        // 2️⃣ Password check
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new Exception("Invalid credentials");
        }

        // 3️⃣ Role-based access

        if (!user.getRole().equalsIgnoreCase("USER") &&
                !user.getRole().equalsIgnoreCase("EMPLOYEE") &&
                !user.getRole().equalsIgnoreCase("ADMIN")) {
            throw new Exception("User role is not authorized");
        }

        // 4️⃣ JWT generate
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





