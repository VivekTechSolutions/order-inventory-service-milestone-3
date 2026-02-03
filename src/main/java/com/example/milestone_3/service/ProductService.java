package com.example.milestone_3.service;

import java.util.List;

import com.example.milestone_3.dto.request.ProductRequest;
import com.example.milestone_3.dto.response.ProductResponse;


public interface ProductService {

    ProductResponse createProduct(ProductRequest request);

    List<ProductResponse> getAllProducts();

    ProductResponse getProductById(Long id);

    ProductResponse updateProduct(Long id, ProductRequest request);
}
