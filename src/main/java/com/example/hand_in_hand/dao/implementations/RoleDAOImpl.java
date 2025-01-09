package com.example.hand_in_hand.dao.implementations;

import com.example.hand_in_hand.dao.contracts.RoleDAO;
import com.example.hand_in_hand.entities.models.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class RoleDAOImpl implements RoleDAO {
    private final EntityManager em;
    @Override
    public Role save(Role entity) {
        return em.merge(entity);
    }

    @Override
    public List<Role> getAll() {
        TypedQuery<Role> roles = em.createQuery("SELECT r FROM Role r", Role.class);
        return roles.getResultList();
    }

    @Override
    public Role getById(int id) {
        return em.find(Role.class, id);
    }

    @Override
    public Role getByName(String name) {
        TypedQuery<Role> query = em.createQuery("SELECT r FROM Role r WHERE r.name = :name", Role.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    @Override
    public boolean checkIfRoleExists(String name) {
        TypedQuery<Role> query = em.createQuery("SELECT r FROM Role r WHERE r.name = :name", Role.class);
        query.setParameter("name", name);
        return !query.getResultList().isEmpty();
    }

    @Override
    public void update(Role entity) {
        em.merge(entity);
    }

    @Override
    public void deleteById(int id) {
        Role role = em.find(Role.class, id);
        em.remove(role);
    }

    @Override
    public List<Role> getByIds(List<Integer> ids) {
        TypedQuery<Role> query = em.createQuery("SELECT r FROM Role r WHERE r.id IN :ids", Role.class);
        query.setParameter("ids", ids);
        return query.getResultList();
    }

}
