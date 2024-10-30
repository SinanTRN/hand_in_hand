package com.example.hand_in_hand.controller;

import com.example.hand_in_hand.entities.models.Category;
import com.example.hand_in_hand.service.contracts.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {
    private CategoryService categoryService;
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return categoryService.getAll();
    }

    @GetMapping("/categories/{id}")
    public Category getCategoryById(@PathVariable int id) {
        Category category = categoryService.getById(id);
        if (category == null) {
            throw new RuntimeException("Category not found for the id: " + id);
        }
        return category;
    }

    @PostMapping("/categories")
    public Category saveCategory(@RequestBody Category category) {
        category.setId(0);
        Category savedCategory = categoryService.save(category);
        return savedCategory;
    }

    @PutMapping("/categories/{id}")
    public Category updateCategory(@RequestBody Category category) {
        categoryService.update(category);
        return category;
    }

    @DeleteMapping("/categories/{id}")
    public String deleteCategoryById(@PathVariable int id) {
        Category category = categoryService.getById(id);
        if (category == null) {
            throw new RuntimeException("Category not found for the id: " + id);
        }
        categoryService.deleteById(id);
        return "Category deleted with id: " + id;
    }


}
