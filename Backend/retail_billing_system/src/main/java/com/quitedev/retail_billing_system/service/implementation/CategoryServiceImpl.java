package com.quitedev.retail_billing_system.service.implementation;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.quitedev.retail_billing_system.entity.CategoryEntity;
import com.quitedev.retail_billing_system.io.CategoryRequest;
import com.quitedev.retail_billing_system.io.CategoryResponse;
import com.quitedev.retail_billing_system.repository.CategoryRepository;
import com.quitedev.retail_billing_system.service.CategoryService;
import com.quitedev.retail_billing_system.service.FileUploadService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final FileUploadService fileUploadService;

    @Override
    public CategoryResponse add(CategoryRequest categoryRequest, MultipartFile file) {
        String imgUrl = fileUploadService.uploadFile(file);
        CategoryEntity newCategory = convertToEntity(categoryRequest);
        newCategory.setImgUrl(imgUrl);
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
        fileUploadService.deleteFile(exsitingCategory.getImgUrl());
        categoryRepository.delete(exsitingCategory);
    }
}
