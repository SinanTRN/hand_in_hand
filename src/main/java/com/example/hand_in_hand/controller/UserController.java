package com.example.hand_in_hand.controller;

import com.example.hand_in_hand.annotations.RoleRequired;
import com.example.hand_in_hand.entities.models.User;
import com.example.hand_in_hand.service.contracts.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("/roles/{role}")
    public List<User> getAllByRole(@PathVariable String role) {
        return userService.getAllByRole(role);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getById(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@Valid @RequestBody User user) {
        userService.update(user);
        return user;
    }
    @RoleRequired(role = "ADMIN")
    @PostMapping
    public User saveUser(@RequestBody User user) {
        return userService.save(user);
    }

    @RoleRequired(role = "ADMIN")
    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable int id) {
        userService.deleteById(id);
        return "User deleted with id: " + id;
    }

}
