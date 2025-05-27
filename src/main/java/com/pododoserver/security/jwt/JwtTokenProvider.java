package com.pododoserver.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${pododo.jwt.secret}")
    private String secretKey;
    @Value("${pododo.jwt.expiration}")
    private long validityMillis;

    public String createToken(String provider, String providerId, String role) {
        Claims claims = Jwts.claims().setSubject(provider + "|" + providerId);
        claims.put("role", role);
        Date now = new Date();
        Date exp = new Date(now.getTime() + validityMillis);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}
