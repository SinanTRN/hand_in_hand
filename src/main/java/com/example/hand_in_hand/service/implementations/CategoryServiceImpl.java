package com.example.hand_in_hand.service.implementations;

import com.example.hand_in_hand.dao.contracts.CategoryDAO;
import com.example.hand_in_hand.entities.exceptions.ConflictException;
import com.example.hand_in_hand.entities.exceptions.notFoundExceptions.implementations.CategoryNotFoundException;
import com.example.hand_in_hand.entities.models.Category;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@AllArgsConstructor
@Service
public class CategoryServiceImpl implements com.example.hand_in_hand.service.contracts.CategoryService {
    private CategoryDAO categoryDAO;
    @Transactional
    @Override
    public Category save(Category entity) {
        if(categoryDAO.existsByName(entity.getCategoryName())){
            throw new ConflictException("Category already exists");
        }
        entity.setId(0);
        return categoryDAO.save(entity);
    }

    @Override
    public List<Category> getAll() {
        List<Category> categories = categoryDAO.getAll();
        if (categories.isEmpty()) {
            throw new CategoryNotFoundException("No categories found");
        }
        return categoryDAO.getAll();
    }

    @Override
    public Category getById(int id) {
        Category category = categoryDAO.getById(id);
        if (category == null) {
            throw new CategoryNotFoundException("Category not found for the id: " + id);
        }
        return categoryDAO.getById(id);
    }
    @Transactional
    @Override
    public void update(Category entity) {
        Category category = categoryDAO.getById(entity.getId());
        if (category == null) {
            throw new CategoryNotFoundException("Category not found for the id: " + entity.getId());
        }
        categoryDAO.update(entity);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        Category category = categoryDAO.getById(id);
        if (category == null) {
            throw new CategoryNotFoundException("Category not found for the id: " + id);
        }
        categoryDAO.deleteById(id);
    }
}
