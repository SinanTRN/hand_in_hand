package com.example.hand_in_hand.service.implementations;


import com.example.hand_in_hand.dao.contracts.RoleDAO;
import com.example.hand_in_hand.dao.contracts.UserDAO;
import com.example.hand_in_hand.entities.exceptions.ConflictException;
import com.example.hand_in_hand.entities.exceptions.notFoundExceptions.implementations.RoleNotFoundExcepiton;
import com.example.hand_in_hand.entities.exceptions.notFoundExceptions.implementations.UserNotFoundException;
import com.example.hand_in_hand.entities.models.Role;
import com.example.hand_in_hand.entities.models.User;
import com.example.hand_in_hand.security.password.PasswordUtil;
import com.example.hand_in_hand.service.contracts.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;
    private final RoleDAO roleDAO;

    @Override
    public List<User> getAll() {
        return userDAO.getAll();
    }

    @Override
    public User getById(int id) {
        User user = userDAO.getById(id);
        if(user == null){
            throw new UserNotFoundException("User not found for this id : " + id);
        }
        return userDAO.getById(id);
    }

    @Override
    public User save(User entity) {
        checkUser(entity);
        entity.setId(0);
        entity.setPassword(PasswordUtil.hashPassword(entity.getPassword()));
        return userDAO.save(entity);
    }

    private void checkUser(User entity) {
        if (userDAO.existsByUsername(entity.getUsername())) {
            throw new ConflictException("Username already exists");
        }
        if(entity.getRoles() == null || entity.getRoles().isEmpty()){
            Role role= roleDAO.getByName("USER");
            entity.setRoles(List.of(role));
        }
    }

    @Transactional
    @Override
    public void update(User entity) {
        if (entity.getPassword() != null && !entity.getPassword().isEmpty()) {
            entity.setPassword(PasswordUtil.hashPassword(entity.getPassword()));
        }
        else{
            User user = userDAO.getById(entity.getId());
            entity.setPassword(user.getPassword());
        }
        userDAO.update(entity);
    }
    @Transactional
    @Override
    public void deleteById(int id) {
        User user = userDAO.getById(id);
        if(user == null){
            throw new UserNotFoundException("User not found for this id : " + id);
        }
        userDAO.deleteById(id);
    }

    @Override
    public User findByUsername(String username) {
        User user = userDAO.findByUsername(username);
        if(user == null){
            throw new UserNotFoundException("User not found for this username : " + username);
        }
        return userDAO.findByUsername(username);
    }

    @Override
    public List<User> getAllByRole(String role) {
        if(roleDAO.getByName(role) == null){
            throw new RoleNotFoundExcepiton("Role not found for this name : " + role);
        }
        return userDAO.getAllByRole(role);
    }
}
