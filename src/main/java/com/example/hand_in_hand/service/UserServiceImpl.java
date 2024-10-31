package com.example.hand_in_hand.service;

import com.example.hand_in_hand.dao.UserDAOImpl;
import com.example.hand_in_hand.entities.models.User;
import com.example.hand_in_hand.service.contracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserDAOImpl userDAO;
    @Autowired
    public UserServiceImpl(UserDAOImpl userDAO) {
        this.userDAO = userDAO;
    }

    @Transactional
    @Override
    public User save(User entity) {
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
        userDAO.update(entity);
    }
    @Transactional
    @Override
    public void deleteById(int id) {
        userDAO.deleteById(id);
    }
}
