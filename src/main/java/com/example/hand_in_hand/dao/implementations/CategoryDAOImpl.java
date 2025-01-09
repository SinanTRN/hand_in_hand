package com.example.hand_in_hand.dao.implementations;

import com.example.hand_in_hand.dao.contracts.CategoryDAO;
import com.example.hand_in_hand.entities.models.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
@AllArgsConstructor
@Repository
public class CategoryDAOImpl implements CategoryDAO {
    private EntityManager em;
    @Override
    public Category save(Category entity) {
        return em.merge(entity);
    }

    @Override
    public List<Category> getAll() {
        TypedQuery<Category> query = em.createQuery("SELECT c FROM Category c", Category.class);
        return query.getResultList();
    }

    @Override
    public Category getById(int id) {
        return em.find(Category.class, id);
    }

    @Override
    public void update(Category entity) {
        em.merge(entity);
    }

    @Override
    public void deleteById(int id) {
        Category category = em.find(Category.class, id);
        em.remove(category);
    }

    @Override
    public boolean existsByName(String name) {
        TypedQuery<Category> query = em.createQuery("SELECT c FROM Category c WHERE c.categoryName = :name", Category.class);
        query.setParameter("name", name);
        return !query.getResultList().isEmpty();
    }
}
