package com.example.hand_in_hand.dao.implementations;

import com.example.hand_in_hand.dao.contracts.UserDAO;
import com.example.hand_in_hand.entities.models.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class UserDAOImpl implements UserDAO {

    private EntityManager em;

    @Override
    public User save(User entity) {
        return em.merge(entity);
    }

    @Override
    public List<User> getAll() {
        TypedQuery<User> users = em.createQuery("SELECT u FROM User u", User.class);
        return users.getResultList();
    }

    @Override
    public List<User> getAllByRole(String role) {
        TypedQuery<User> users = em.createQuery("SELECT u FROM User u JOIN u.roles r WHERE r.name= :role", User.class);
        users.setParameter("role", role);
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

    @Override
    public User findByUsername(String username) {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
        query.setParameter("username", username);
        return query.getSingleResult();
    }

    @Override
    public boolean existsByUsername(String username) {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
        query.setParameter("username", username);
        return !query.getResultList().isEmpty();
    }


}
