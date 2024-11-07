package com.example.hand_in_hand.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class TokenManager {
    private static final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final long validity = 5*60*1000;

    public boolean tokenValidate(String token) {
        if(getUsernameToken(token) != null && isExpired(token)) {
            return true;
        }
        return false;
    }
    public String getUsernameToken(String token) {
        return getClaims(token).getSubject();
    }
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuer("http://localhost:8080")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+validity))
                .signWith(secretKey)
                .compact();
    }
    public boolean isExpired(String token) {
        Claims claims = getClaims(token);
        if (claims.getExpiration().after(new Date(System.currentTimeMillis()))) {
            return true;
        }
        return false;
    }

    private Claims getClaims(String token) {
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        return claims;
    }
}
