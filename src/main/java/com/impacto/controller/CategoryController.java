package com.impacto.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.impacto.entity.Category;
import com.impacto.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService service;

    @GetMapping("")
    public ResponseEntity<List<Category>> getAllCategory() {
        List<Category> categories = service.findAll();
        return ResponseEntity.ok(categories);
    }



}