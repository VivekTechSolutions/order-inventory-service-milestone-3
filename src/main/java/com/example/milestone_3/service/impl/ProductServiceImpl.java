package com.example.milestone_3.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.milestone_3.dto.request.ProductRequest;
import com.example.milestone_3.dto.response.ProductResponse;
import com.example.milestone_3.entity.Product;
import com.example.milestone_3.exception.ResourceNotFoundException;
import com.example.milestone_3.exception.ProductAlreadyExistsException;
import com.example.milestone_3.repository.ProductRepository;
import com.example.milestone_3.service.ProductService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // ---------------- Create Product ----------------
    @Override
    public ProductResponse createProduct(ProductRequest request) {
        if (productRepository.existsByName(request.getName())) {
            throw new ProductAlreadyExistsException(
                "Product already exists with name: " + request.getName()
            );
        }

        Product product = new Product();
        product.setName(request.getName());
        product.setStock(request.getStock());
        product.setPrice(request.getPrice());

        Product savedProduct = productRepository.save(product);
        return mapToResponse(savedProduct);
    }

    // ---------------- Get All Products ----------------
    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductResponse> responses = new ArrayList<>();
        for (Product product : products) {
            responses.add(mapToResponse(product));
        }
        return responses;
    }

    // ---------------- Get Product By ID ----------------
    @Override
    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Product not found with id: " + id
                ));
        return mapToResponse(product);
    }

    // ---------------- Update Product ----------------
    @Override
    public ProductResponse updateProduct(Long id, ProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Product not found with id: " + id
                ));

        // Check duplicate name on update
        if (request.getName() != null &&
            !request.getName().equals(product.getName()) &&
            productRepository.existsByName(request.getName())) {
            throw new ProductAlreadyExistsException(
                "Product already exists with name: " + request.getName()
            );
        }

        if (request.getName() != null) product.setName(request.getName());
        if (request.getPrice() != null) product.setPrice(request.getPrice());
        if (request.getStock() != null) product.setStock(request.getStock());

        Product updatedProduct = productRepository.save(product);
        return mapToResponse(updatedProduct);
    }

    // ---------------- Delete Product ----------------
    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Product not found with id: " + id
                ));

        productRepository.delete(product); // Deletes product from DB
    }

    // ---------------- Mapper: Product -> ProductResponse ----------------
    private ProductResponse mapToResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setStock(product.getStock());
        response.setPrice(product.getPrice());
        return response;
    }
}
