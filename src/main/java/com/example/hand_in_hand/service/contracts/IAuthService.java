package com.example.hand_in_hand.service.contracts;

import com.example.hand_in_hand.entities.dto.RegisterRequest;
import com.example.hand_in_hand.entities.models.User;

public interface IAuthService {
    String checkAuthorization(String username, String password);
    String generateToken(User user);
    User saveUser(RegisterRequest registerRequest);
    User convertToUser(RegisterRequest registerRequest);
}
