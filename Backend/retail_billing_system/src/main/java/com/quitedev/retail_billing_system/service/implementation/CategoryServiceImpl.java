package com.quitedev.retail_billing_system.service.implementation;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.quitedev.retail_billing_system.entity.CategoryEntity;
import com.quitedev.retail_billing_system.io.CategoryRequest;
import com.quitedev.retail_billing_system.io.CategoryResponse;
import com.quitedev.retail_billing_system.repository.CategoryRepository;
import com.quitedev.retail_billing_system.service.CategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponse add(CategoryRequest categoryRequest) {
        CategoryEntity newCategory = convertToEntity(categoryRequest);
        newCategory = categoryRepository.save(newCategory);
        return convertToResponse(newCategory);
    }

    private CategoryResponse convertToResponse(CategoryEntity newCategory) {
        return CategoryResponse.builder()
            .categoryId(newCategory.getCategoryId())
            .name(newCategory.getName())
            .description(newCategory.getDescription())
            .bgColor(newCategory.getBgColor())
            .imgUrl(newCategory.getImgUrl())
            .createdAt(newCategory.getCreatedAt())
            .updatedAt(newCategory.getUpdatedAt())
            .build();
    }

    private CategoryEntity convertToEntity(CategoryRequest categoryRequest) {
        return CategoryEntity.builder()
            .categoryId(UUID.randomUUID().toString())
            .name(categoryRequest.getName())
            .description(categoryRequest.getDescription())
            .bgColor(categoryRequest.getBgColor())
            .build();
        
    }

    public List<CategoryResponse> read() {
        return categoryRepository.findAll()
                        .stream()
                        .map(categoryEntity -> convertToResponse(categoryEntity))
                        .collect(Collectors.toList());
    }
    

    public void deleteCategory(String categoryId) {
        CategoryEntity exsitingCategory = categoryRepository.findByCategoryId(categoryId).orElseThrow(() -> new RuntimeException("Category not found" + categoryId));
        categoryRepository.delete(exsitingCategory);
    }
}
