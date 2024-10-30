package com.example.hand_in_hand.dao;

import com.example.hand_in_hand.entities.models.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class CategoryDAOImpl implements GenericDAO<Category> {
    private EntityManager em;
    @Autowired
    public CategoryDAOImpl(EntityManager em) {
        this.em = em;
    }
    @Override
    public Category save(Category entity) {
        Category dbCategory = em.merge(entity);
        return dbCategory;
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
}
