package com.example.hand_in_hand.controller;


import com.example.hand_in_hand.auth.TokenManager;
import com.example.hand_in_hand.entities.dto.LoginRequest;
import com.example.hand_in_hand.service.contracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class authController {
    private final TokenManager tokenManager;
    private final UserService userService;
    @Autowired
    public authController(TokenManager tokenManager, UserService userService, BCryptPasswordEncoder passwordEncoder) {
        this.tokenManager = tokenManager;
        this.userService = userService;
    }
    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        if (userService.authenticate(loginRequest.getUsername(), loginRequest.getPassword())) {
            String token = tokenManager.generateToken(loginRequest.getUsername());
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(401).build();

    }
}
