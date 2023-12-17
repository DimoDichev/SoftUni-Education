package com.softuni.springdataintroexercies.services.impl;

import com.softuni.springdataintroexercies.repositories.CategoryRepository;
import com.softuni.springdataintroexercies.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

}
