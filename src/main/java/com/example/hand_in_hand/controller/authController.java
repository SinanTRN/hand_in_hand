package com.example.hand_in_hand.controller;


import com.example.hand_in_hand.entities.dto.LoginRequest;
import com.example.hand_in_hand.entities.dto.RegisterRequest;
import com.example.hand_in_hand.entities.models.User;
import com.example.hand_in_hand.service.contracts.IAuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
@Validated
public class authController {
    private final IAuthService authService;

    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginRequest loginRequest) {
        return authService.checkAuthorization(loginRequest.getUsername(), loginRequest.getPassword());
    }

    @PostMapping("/register")
    public User register(@Valid @RequestBody RegisterRequest registerRequest) {
        return authService.saveUser(registerRequest);
    }

}
