package com.example.hand_in_hand.annotations.aspects;

import com.example.hand_in_hand.annotations.RoleRequired;
import com.example.hand_in_hand.entities.exceptions.AuthorizationException;
import com.example.hand_in_hand.security.jwt.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Aspect
@Component
public class RoleRequiredAspect {
    private final JwtUtil jwtUtil;
    private final HttpServletRequest request;

    public RoleRequiredAspect(JwtUtil jwtUtil, HttpServletRequest request) {
        this.jwtUtil = jwtUtil;
        this.request = request;
    }

    @Before("@annotation(roleRequired)")
    public void checkRole(RoleRequired roleRequired) {
        String requiredRole = roleRequired.role();

        var token = getTokenFromRequest();
        if (Objects.equals(requiredRole, "ANY")) {
            return;
        }
        var roles = jwtUtil.extractClaim(token, claims -> claims.get("roles", List.class));
        if (!roles.contains(requiredRole)) {
            throw new AuthorizationException("You are not authorized to perform this action");
        }

    }

    private String getTokenFromRequest(){
        final String authorizationHeader = request.getHeader("Authorization");

        // Check if the request has a JWT token
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new AuthorizationException("No token provided");
        }

        var jwt = authorizationHeader.substring(7); // Remove "Bearer " from the token

        // Check if the token is valid
        if (!jwtUtil.validateToken(jwt)) {
            throw new AuthorizationException("Token expired please login again");
        }

        return jwt;
    }
}
