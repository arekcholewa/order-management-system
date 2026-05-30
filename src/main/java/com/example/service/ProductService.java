package com.example.service;

import com.example.dto.ProductDTO;
import com.example.entity.Product;
import com.example.exception.ProductNotFoundException;
import com.example.mapper.ProductMapper;
import com.example.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    public ProductService(ProductRepository repository, ProductMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public ProductDTO getById(Long id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() ->
                        new ProductNotFoundException(id));
    }

    public Product create(ProductDTO product) {
        return repository.save(mapper.toEntity(product));
    }
}