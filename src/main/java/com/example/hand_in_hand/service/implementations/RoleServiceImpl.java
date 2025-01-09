package com.example.hand_in_hand.service.implementations;

import com.example.hand_in_hand.dao.contracts.RoleDAO;
import com.example.hand_in_hand.entities.exceptions.ConflictException;
import com.example.hand_in_hand.entities.exceptions.notFoundExceptions.implementations.RoleNotFoundExcepiton;
import com.example.hand_in_hand.entities.models.Role;
import com.example.hand_in_hand.service.contracts.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleDAO roleDAO;

    @Transactional
    @Override
    public Role save(Role entity) {
        if(roleDAO.checkIfRoleExists(entity.getName())) {
            throw new ConflictException(entity.getName()+"Role already exists");
        }
        return roleDAO.save(entity);
    }

    @Override
    public List<Role> getAll() {
        return roleDAO.getAll();
    }

    @Override
    public Role getById(int id) {
        Role role = roleDAO.getById(id);
        if (role == null) {
            throw new RoleNotFoundExcepiton("Role not found");
        }
        return roleDAO.getById(id);
    }

    @Override
    public Role getByName(String name) {
        if(roleDAO.checkIfRoleExists(name)){
            throw new RoleNotFoundExcepiton(name+" Role not found");
        }
        return roleDAO.getByName(name);
    }

    @Transactional
    @Override
    public void update(Role entity) {
        if(roleDAO.checkIfRoleExists(entity.getName())) {
            throw new ConflictException(entity.getName()+"Role already exists");
        }
        roleDAO.update(entity);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        Role role = roleDAO.getById(id);
        if (role == null) {
            throw new RoleNotFoundExcepiton("Role not found");
        }
        roleDAO.deleteById(id);
    }
}
