package com.example.dto;

import java.util.List;

public record CreateOrderRequest(String customerEmail, List<OrderItemRequest> items) {
}