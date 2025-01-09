package com.example.hand_in_hand.dao.contracts;

import com.example.hand_in_hand.entities.models.User;

import java.util.List;

public interface UserDAO {
    User save(User entity);
    List<User> getAll();
    List<User> getAllByRole(String role);
    User getById(int id);
    void update(User entity);
    void deleteById(int id);
    User findByUsername(String username);
    boolean existsByUsername(String username);
}
