package com.impacto.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.impacto.dto.CreateCategoryRequest;
import com.impacto.dto.GlobalResponse;
import com.impacto.entity.Category;
import com.impacto.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService service;

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategory() {
        List<Category> categories = service.findAll();
        return ResponseEntity.ok(categories);
    }

    @PostMapping
    public ResponseEntity<GlobalResponse> createCategory(@RequestBody CreateCategoryRequest body) {
        System.out.println(body);
        Category category = service.createCategory(body);
        return ResponseEntity.ok(GlobalResponse.builder()
                .message("ok")
                .build());
    }

}