package com.impacto.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.impacto.entity.Category;
import com.impacto.repository.CategoryRepository;
import com.impacto.service.CategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    @Override
    public List<Category> findAll() {
        return repository.findAll();
    }



}
