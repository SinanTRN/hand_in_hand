package com.example.hand_in_hand.controller;

import com.example.hand_in_hand.annotations.RoleRequired;
import com.example.hand_in_hand.entities.models.Category;
import com.example.hand_in_hand.service.contracts.CategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAll();
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable int id) {
        return categoryService.getById(id);
    }

    @RoleRequired(role = "ADMIN")
    @PostMapping
    public ResponseEntity<Category> saveCategory(@Valid @RequestBody Category category) {
        Category savedCategory = categoryService.save(category);
        return new ResponseEntity<>(savedCategory, ResponseEntity.status(201).build().getStatusCode());
    }

    @RoleRequired(role = "ADMIN")
    @PutMapping("/{id}")
    public Category updateCategory(@Valid @RequestBody Category category) {
        categoryService.update(category);
        return category;
    }

    @RoleRequired(role = "ADMIN")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable int id) {
        categoryService.deleteById(id);
        return ResponseEntity.status(204).build();
    }
}
