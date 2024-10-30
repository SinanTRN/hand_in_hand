package com.example.hand_in_hand.controller;

import com.example.hand_in_hand.entities.models.Category;
import com.example.hand_in_hand.service.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {
    private CategoryServiceImpl categoryServiceImpl;
    @Autowired
    public CategoryController(CategoryServiceImpl categoryServiceImpl) {
        this.categoryServiceImpl = categoryServiceImpl;
    }

    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return categoryServiceImpl.getAll();
    }


}
