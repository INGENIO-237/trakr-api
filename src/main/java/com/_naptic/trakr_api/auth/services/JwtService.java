package com._naptic.trakr_api.auth.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Component
public class JwtService {
    @Value("${jwt.secret}")
    private String SECRET;

    private JwtBuilder getJwtBuilder(String username) {
        final long TOKEN_DURATION = 1000 * 60 * 60 * 3; // 3hrs

        return Jwts.builder()
                .signWith(getKey())
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_DURATION));
    }

    public String generateToken(String username) {
        return getJwtBuilder(username).compact();
    }

    public String generateToken(String username, Map<String, Object> claims) {
        return getJwtBuilder(username)
                .addClaims(claims)
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public boolean isTokenValid(String token) {
        boolean isExpired = extractClaims(token)
                .getExpiration()
                .before(new Date());

        return !isExpired;
    }

    private Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);

        return Keys.hmacShaKeyFor(keyBytes);
    }
}
