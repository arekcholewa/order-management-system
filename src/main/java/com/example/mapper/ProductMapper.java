package com.example.mapper;

import com.example.dto.ProductDTO;
import com.example.entity.Product;
import org.springframework.stereotype.Component;

@Component
public final class ProductMapper {

    public Product toEntity(ProductDTO dto) {
        Product product = new Product();
        product.setName(dto.name());
        product.setPrice(dto.price());
        product.setStockQuantity(dto.stockQuantity());
        return product;
    }

    public ProductDTO toResponse(Product product) {
        return new ProductDTO(product.getName(), product.getPrice(), product.getStockQuantity());
    }
}