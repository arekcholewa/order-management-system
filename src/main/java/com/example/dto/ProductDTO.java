package com.example.dto;

import com.example.validation.ValidProductName;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record ProductDTO(@NotBlank @ValidProductName String name, BigDecimal price, Integer stockQuantity
) {}