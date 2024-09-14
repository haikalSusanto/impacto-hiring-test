package com.impacto.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.impacto.dto.CreateCategoryRequest;
import com.impacto.entity.Category;
import com.impacto.repository.CategoryRepository;
import com.impacto.service.CategoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    @Override
    public List<Category> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public Category createCategory(CreateCategoryRequest body) {
        log.info(body.getName());
        Category category = Category.builder()
                .name(body.getName())
                .build();

        category = repository.save(category);
        return category;
    }

}
