package com.example.hand_in_hand.auth;

import com.example.hand_in_hand.dao.contracts.UserDAO;
import com.example.hand_in_hand.entities.models.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    private final TokenManager tokenManager;
    private final UserDAO userRepository;

    @Autowired
    public JwtTokenFilter(TokenManager tokenManager, UserDAO userRepository) {
        this.tokenManager = tokenManager;
        this.userRepository = userRepository;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // authHeader "bearer ......" şeklinde gelecek
        final String authHeader = request.getHeader("Authorization");
        String username=null;
        String token=null;
        if(authHeader!=null && authHeader.startsWith("Bearer ")) {
            // Token'ı al
            token = authHeader.substring(7);
            // Token'ı validate et
            if(!tokenManager.tokenValidate(token)) {
                // Token geçerli değilse hata döndür
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            }
            // Token'dan kullanıcı adını al
            try {
                username = tokenManager.getUsernameToken(token);
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }
        if(username != null && token != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            if(tokenManager.tokenValidate(token)) {
                User user = userRepository.findByUsername(username);
                UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
                upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(upat);
            }
        }
        filterChain.doFilter(request, response);
    }
}
