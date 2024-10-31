package com.example.hand_in_hand.service.contracts;

import com.example.hand_in_hand.entities.models.User;

import java.util.List;

public interface UserService {
    User save(User entity);
    List<User> getAll();
    User getById(int id);
    void update(User entity);
    void deleteById(int id);
}
