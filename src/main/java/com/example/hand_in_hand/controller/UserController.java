package com.example.hand_in_hand.controller;

import com.example.hand_in_hand.entities.models.User;
import com.example.hand_in_hand.service.contracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable int id) {
        User user = userService.getById(id);
        if (user == null) {
            throw new RuntimeException("User not found for the id: " + id);
        }
        return user;
    }

    @PostMapping("/users")
    public User saveUser(@RequestBody User user) {
        user.setId(0);
        User savedUser = userService.save(user);
        return savedUser;
    }

    @PutMapping("/users/{id}")
    public User updateUser(@RequestBody User user) {
        userService.update(user);
        return user;
    }

    @DeleteMapping("/users/{id}")
    public String deleteUserById(@PathVariable int id) {
        User user = userService.getById(id);
        if (user == null) {
            throw new RuntimeException("User not found for the id: " + id);
        }
        userService.deleteById(id);
        return "User deleted with id: " + id;
    }

}
