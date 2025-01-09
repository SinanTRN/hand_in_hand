package com.example.hand_in_hand.service.contracts;

import com.example.hand_in_hand.entities.models.Role;

import java.util.List;

public interface RoleService {
    Role save(Role entity);
    List<Role> getAll();
    Role getById(int id);
    Role getByName(String name);
    void update(Role entity);
    void deleteById(int id);
}
