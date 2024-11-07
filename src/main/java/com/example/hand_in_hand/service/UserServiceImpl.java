package com.example.hand_in_hand.service;

import com.example.hand_in_hand.dao.UserDAOImpl;
import com.example.hand_in_hand.entities.models.User;
import com.example.hand_in_hand.service.contracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserDAOImpl userDAO;
    private final BCryptPasswordEncoder bCryptPasswordEncoder ;
    @Autowired
    public UserServiceImpl(UserDAOImpl userDAO, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDAO = userDAO;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Transactional
    @Override
    public User save(User entity) {
        if(entity.getPassword() == null || entity.getPassword().isEmpty()){
            throw new RuntimeException("Password is required");
        }
        entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
        return userDAO.save(entity);
    }

    @Override
    public List<User> getAll() {
        return userDAO.getAll();
    }

    @Override
    public User getById(int id) {
        return userDAO.getById(id);
    }
    @Transactional
    @Override
    public void update(User entity) {
        if (entity.getPassword() != null && !entity.getPassword().isEmpty()) {
            entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
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
        userDAO.deleteById(id);
    }
    @Override
    public boolean authenticate(String username, String password) {
        User user = userDAO.findByUsername(username);
        if (user == null) {
            return false;
        }
        return bCryptPasswordEncoder.matches(password,user.getPassword());
    }

    @Override
    public User findByUsername(String username) {
        return userDAO.findByUsername(username);
    }
}
