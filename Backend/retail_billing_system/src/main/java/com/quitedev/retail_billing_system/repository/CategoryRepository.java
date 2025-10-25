package com.quitedev.retail_billing_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quitedev.retail_billing_system.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>{
    
}
