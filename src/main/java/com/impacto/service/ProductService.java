package com.impacto.service;

import java.util.List;

import com.impacto.dto.CreateProductRequest;
import com.impacto.dto.GetDetailProductResponse;
import com.impacto.dto.UpdateProductRequest;
import com.impacto.entity.Product;

public interface ProductService {

    Product createProduct(CreateProductRequest body);

    List<GetDetailProductResponse> getAllProduct();

    GetDetailProductResponse getProduct(Integer id);

    void deleteProduct(Integer id);

    void updateProduct(Integer id, UpdateProductRequest body);

}
