package com.example.hand_in_hand.dao;

import com.example.hand_in_hand.dao.contracts.UserDAO;
import com.example.hand_in_hand.entities.models.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    private EntityManager em;
    @Autowired
    public UserDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public User save(User entity) {
        User dbUser = em.merge(entity);
        return dbUser;
    }

    @Override
    public List<User> getAll() {
        TypedQuery<User> users = em.createQuery("SELECT u FROM User u", User.class);
        return users.getResultList();
    }

    @Override
    public User getById(int id) {
        return em.find(User.class, id);
    }

    @Override
    public void update(User entity) {
        em.merge(entity);
    }

    @Override
    public void deleteById(int id) {
        User user = em.find(User.class, id);
        em.remove(user);
    }



}
