package com.spring.jwt.repository;


import com.spring.jwt.entity.BlacklistedToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlacklistedTokenRepository
        extends JpaRepository<BlacklistedToken, Long> {

    boolean existsByToken(String token);
}
