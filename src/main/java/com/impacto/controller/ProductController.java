package com.impacto.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.impacto.dto.CreateProductRequest;
import com.impacto.dto.GetDetailProductResponse;
import com.impacto.dto.GlobalResponse;
import com.impacto.dto.UpdateProductRequest;
import com.impacto.service.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService service;

    @PostMapping
    public ResponseEntity<GlobalResponse> createProduct(@Valid @RequestBody CreateProductRequest body) {
        service.createProduct(body);
        return ResponseEntity.ok(GlobalResponse.builder().message("ok").build());
    }

    @GetMapping
    public ResponseEntity<List<GetDetailProductResponse>> getAllPrroducts() {
        return ResponseEntity.ok(service.getAllProduct());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetDetailProductResponse> getProduct(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok(service.getProduct(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GlobalResponse> deleteProduct(@PathVariable(name = "id") Integer id) {
        service.deleteProduct(id);
        return ResponseEntity.ok(GlobalResponse.builder().message("ok").build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<GlobalResponse> updateProducEntity(@PathVariable(name = "id") Integer id,
            @Valid @RequestBody UpdateProductRequest body) {
        service.updateProduct(id, body);
        return ResponseEntity.ok(GlobalResponse.builder().message("ok").build());
    }

}
