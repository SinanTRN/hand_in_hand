package com.example.hand_in_hand.service.implementations;

import com.example.hand_in_hand.dao.contracts.RoleDAO;
import com.example.hand_in_hand.dao.contracts.UserDAO;
import com.example.hand_in_hand.entities.dto.RegisterRequest;
import com.example.hand_in_hand.entities.exceptions.AuthorizationException;
import com.example.hand_in_hand.entities.exceptions.ConflictException;
import com.example.hand_in_hand.entities.models.Role;
import com.example.hand_in_hand.entities.models.User;
import com.example.hand_in_hand.security.jwt.JwtUtil;
import com.example.hand_in_hand.security.password.PasswordUtil;
import com.example.hand_in_hand.service.contracts.IAuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class AuthService implements IAuthService {
    private final UserDAO userDAO;
    private final RoleDAO roleDAO;
    private final JwtUtil jwtUtil;

    @Override
    public String checkAuthorization(String username, String password) {
        User user = userDAO.findByUsername(username);
        if (!PasswordUtil.checkPassword(password, user.getPassword())) {
            throw new AuthorizationException("Invalid username or password");
        }
        else{
            return generateToken(user);
        }
    }

    @Override
    public String generateToken(User user) {
        List<String> roles = user.getRoles().stream().map(role -> role.getName()).toList();
        return jwtUtil.generateToken(user.getUsername(), roles, user.getId());
    }

    @Transactional
    @Override
    public User saveUser(RegisterRequest registerRequest) {
        User user = convertToUser(registerRequest);
        if(userDAO.existsByUsername(user.getUsername())){
            throw new ConflictException("Username already exists");
        }
        if(user.getRoles() == null || user.getRoles().isEmpty()){
            Role role= roleDAO.getByName("USER");
            user.setRoles(List.of(role));
        }
        user.setId(0);
        user.setPassword(PasswordUtil.hashPassword(user.getPassword()));
        return userDAO.save(user);
    }

    @Override
    public User convertToUser(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(registerRequest.getPassword());
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setEmail(registerRequest.getEmail());
        user.setRoles(registerRequest.getRoles());
        user.setPhoneNumber(registerRequest.getPhoneNumber());
        return user;
    }
}
