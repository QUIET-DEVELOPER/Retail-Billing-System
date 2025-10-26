package com.quitedev.retail_billing_system.service;

import com.quitedev.retail_billing_system.io.CategoryRequest;
import com.quitedev.retail_billing_system.io.CategoryResponse;
import java.util.List;

public interface CategoryService {
    CategoryResponse add(CategoryRequest categoryRequest);
    List<CategoryResponse> read();
    void deleteCategory(String categoryId);
}
