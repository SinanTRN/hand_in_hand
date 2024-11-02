package com.example.hand_in_hand.dao.contracts;

import com.example.hand_in_hand.entities.models.User;

import java.util.List;

public interface UserDAO {
    User save(User entity);
    List<User> getAll();
    User getById(int id);
    User getByUserName(String username);
    void update(User entity);
    void deleteById(int id);
}
