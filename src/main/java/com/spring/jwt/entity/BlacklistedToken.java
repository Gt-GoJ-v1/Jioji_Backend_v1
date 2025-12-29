package com.spring.jwt.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class BlacklistedToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500)
    private String token;

    private LocalDateTime expiryTime;

    public void setToken(String token) {
    }

    public void setExpiryTime(LocalDateTime localDateTime) {
    }
}

