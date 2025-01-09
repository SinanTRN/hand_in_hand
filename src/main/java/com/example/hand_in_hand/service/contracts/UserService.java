package com.example.hand_in_hand.service.contracts;

import com.example.hand_in_hand.entities.models.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    User getById(int id);
    User save(User entity);
    void update(User entity);
    void deleteById(int id);
    User findByUsername(String username);
    List<User> getAllByRole(String role);
}
