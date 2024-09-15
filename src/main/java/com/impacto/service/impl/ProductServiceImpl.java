package com.impacto.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.impacto.dto.CreateProductRequest;
import com.impacto.dto.GetDetailProductResponse;
import com.impacto.dto.UpdateProductRequest;
import com.impacto.entity.Category;
import com.impacto.entity.Product;
import com.impacto.exception.NotFoundException;
import com.impacto.repository.CategoryRepository;
import com.impacto.repository.ProductRepository;
import com.impacto.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public List<GetDetailProductResponse> getAllProduct() {
        List<Product> products = productRepository.findAll();
        List<GetDetailProductResponse> response = products.stream()
                .map((product) -> {
                    GetDetailProductResponse detail = new GetDetailProductResponse();

                    BeanUtils.copyProperties(product, detail);

                    detail.setCategoryId(product.getCategory().getId());
                    detail.setCategoryDesc(product.getCategory().getName());

                    return detail;
                })
                .collect(Collectors.toList());

        return response;
    }

    @Override
    public GetDetailProductResponse getProduct(Integer id) throws NotFoundException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("product not found", null));

        GetDetailProductResponse response = new GetDetailProductResponse();

        BeanUtils.copyProperties(product, response);

        response.setCategoryId(product.getCategory().getId());
        response.setCategoryDesc(product.getCategory().getName());

        return response;
    }

    @Override
    public Product createProduct(CreateProductRequest body) {
        Product newProduct = new Product();
        BeanUtils.copyProperties(body, newProduct);

        Product product = productRepository.findLatestCode(body.getCode()).orElse(null);
        if (ObjectUtils.isNotEmpty(product)) {
            String adjustedCode = "";
            String[] component = product.getCode().split("_");
            try {
                Integer latestNumber = Integer.parseInt(component[component.length - 1]);
                latestNumber++;

                adjustedCode = newProduct.getCode() + "_" + String.valueOf(latestNumber);
            } catch (Exception ex) {
                adjustedCode = newProduct.getCode() + "_1";
            }

            newProduct.setCode(adjustedCode);
        }

        Category category = categoryRepository.findById(body.getCategoryId())
                .orElseThrow(() -> new NotFoundException("category not found", null));
        newProduct.setCategory(category);

        newProduct = productRepository.save(newProduct);
        return newProduct;
    }

    @Override
    public void deleteProduct(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("product not found", null));
        productRepository.delete(product);

    }

    @Override
    public void updateProduct(Integer id, UpdateProductRequest body) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("product not found", null));

        if (ObjectUtils.isNotEmpty(body.getCode()) && body.getCode() != product.getCode()) {
            product.setCode(body.getCode());

            Product exisiting = productRepository.findLatestCode(body.getCode()).orElse(null);
            if (ObjectUtils.isNotEmpty(exisiting)) {
                String adjustedCode = "";
                String[] component = exisiting.getCode().split("_");
                try {
                    Integer latestNumber = Integer.parseInt(component[component.length - 1]);
                    latestNumber++;

                    adjustedCode = product.getCode() + "_" + String.valueOf(latestNumber);
                } catch (Exception ex) {
                    adjustedCode = product.getCode() + "_1";
                }

                product.setCode(adjustedCode);
            }
        }

        if (ObjectUtils.isNotEmpty(body.getName()) && body.getName() != product.getName()) {
            product.setName(body.getName());
        }

        if (ObjectUtils.isNotEmpty(body.getPrice()) && body.getPrice() != product.getPrice()) {
            product.setPrice(body.getPrice());
        }

        if (ObjectUtils.isNotEmpty(body.getCategoryId()) && body.getCategoryId() != product.getCategory().getId()) {
            Category category = categoryRepository.findById(body.getCategoryId())
                    .orElseThrow(() -> new NotFoundException("category not found", null));
            product.setCategory(category);
        }

        productRepository.save(product);
    }

}
