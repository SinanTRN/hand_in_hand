package com.example.hand_in_hand.service;

import com.example.hand_in_hand.dao.CategoryDAOImpl;
import com.example.hand_in_hand.entities.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService implements com.example.hand_in_hand.service.Contracts.CategoryService {
    private CategoryDAOImpl categoryDAO;
    @Autowired
    public CategoryService(CategoryDAOImpl categoryDAO) {
        this.categoryDAO = categoryDAO;
    }
    @Transactional
    @Override
    public Category save(Category entity) {
        return categoryDAO.save(entity);
    }

    @Override
    public List getAll() {
        return categoryDAO.getAll();
    }

    @Override
    public Category getById(int id) {
        return categoryDAO.getById(id);
    }
    @Transactional
    @Override
    public void update(Category entity) {
        categoryDAO.update(entity);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        categoryDAO.deleteById(id);
    }
}
