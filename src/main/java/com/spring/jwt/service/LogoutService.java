package com.spring.jwt.service;


import com.spring.jwt.entity.BlacklistedToken;
import com.spring.jwt.repository.BlacklistedTokenRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class LogoutService {

    @Autowired
    private BlacklistedTokenRepository repo;

    private static final String SECRET_KEY =
            "mySecretKeymySecretKeymySecretKey12345";

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public void logout(String token) {

        Date expiry = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();

        BlacklistedToken bt = new BlacklistedToken();
        bt.setToken(token);
        bt.setExpiryTime(
                expiry.toInstant()
                        .atZone(java.time.ZoneId.systemDefault())
                        .toLocalDateTime()
        );

        repo.save(bt);
    }
}
