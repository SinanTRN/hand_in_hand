package com.example.hand_in_hand.dao.contracts;

import com.example.hand_in_hand.entities.models.Category;

import java.util.List;

public interface CategoryDAO {
    Category save(Category entity);
    List<Category> getAll();
    Category getById(int id);
    void update(Category entity);
    void deleteById(int id);
}
