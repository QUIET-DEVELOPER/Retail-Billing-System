package com.quitedev.retail_billing_system.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.quitedev.retail_billing_system.io.CategoryRequest;
import com.quitedev.retail_billing_system.io.CategoryResponse;
import com.quitedev.retail_billing_system.service.CategoryService;

import java.util.List;

import org.springframework.http.HttpStatus;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse addCategory(@RequestBody CategoryRequest categoryRequest) {
        return categoryService.add(categoryRequest);
    }

    @GetMapping
    public List<CategoryResponse> fetchCategories() {
        return categoryService.read();
    }

    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable String categoryId) {
        try {
            categoryService.deleteCategory(categoryId);
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found : "+ categoryId);
        }
    }
}
