package com.quitedev.retail_billing_system.service;

import com.quitedev.retail_billing_system.io.CategoryRequest;
import com.quitedev.retail_billing_system.io.CategoryResponse;

public interface CategoryService {
    CategoryResponse add(CategoryRequest categoryRequest);
}
