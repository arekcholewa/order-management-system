package com.example.controller;

import com.example.dto.CreateOrderRequest;
import com.example.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody CreateOrderRequest request) {

        final Long orderId = orderService.createOrder(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderId);
    }
}
