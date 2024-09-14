package com.impacto.service;

import java.util.List;

import com.impacto.dto.CreateCategoryRequest;
import com.impacto.entity.Category;

public interface CategoryService {

    List<Category> findAll();

    Category createCategory(CreateCategoryRequest body);

}
