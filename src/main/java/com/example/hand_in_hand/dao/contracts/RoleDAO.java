package com.example.hand_in_hand.dao.contracts;

import com.example.hand_in_hand.entities.models.Role;

import java.util.List;

public interface RoleDAO {
    Role save(Role entity);
    List<Role> getAll();
    Role getById(int id);
    Role getByName(String name);
    boolean checkIfRoleExists(String name);
    void update(Role entity);
    void deleteById(int id);
    List<Role> getByIds(List<Integer> ids);

}
