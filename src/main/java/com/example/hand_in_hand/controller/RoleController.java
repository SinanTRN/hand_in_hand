package com.example.hand_in_hand.controller;

import com.example.hand_in_hand.annotations.RoleRequired;
import com.example.hand_in_hand.entities.models.Role;
import com.example.hand_in_hand.service.contracts.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/roles")
public class RoleController {
    private final RoleService roleService;

    @RoleRequired(role = "ADMIN")
    @GetMapping
    public List<Role> getRoles() {
        return roleService.getAll();
    }
    @RoleRequired(role = "ADMIN")
    @GetMapping("/{id}")
    public Role getRoleById(@PathVariable int id) {
        return roleService.getById(id);
    }

    @RoleRequired(role = "ADMIN")
    @PostMapping
    public Role saveRole(@RequestBody Role role) {
        return roleService.save(role);
    }

    @RoleRequired(role = "ADMIN")
    @PutMapping("/{id}")
    public String updateRole(@RequestBody Role role) {
        roleService.update(role);
        return "Role updated successfully";
    }

    @RoleRequired(role = "ADMIN")
    @DeleteMapping("/{id}")
    public String deleteRoleById(@PathVariable int id) {
        roleService.deleteById(id);
        return "Role deleted successfully";
    }
}
