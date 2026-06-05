package com.example.service;

import com.example.dto.CreateOrderRequest;
import com.example.dto.OrderItemRequest;
import com.example.entity.Customer;
import com.example.entity.Order;
import com.example.entity.OrderItem;
import com.example.entity.OrderStatus;
import com.example.entity.Product;
import com.example.repository.CustomerRepository;
import com.example.repository.OrderRepository;
import com.example.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderService {

    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public OrderService(
            CustomerRepository customerRepository,
            ProductRepository productRepository,
            OrderRepository orderRepository) {

        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @Transactional
    public Long createOrder(CreateOrderRequest request) {

        Customer customer = customerRepository.findByEmail(request.customerEmail())
                .orElseThrow(() ->
                        new RuntimeException("Customer not found"));

        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.NEW);

        for (OrderItemRequest itemRequest : request.items()) {

            Product product = productRepository.findByName(itemRequest.productName())
                    .orElseThrow(() ->
                            new RuntimeException(
                                    "Product not found: " + itemRequest.productName()));

            if (product.getStockQuantity() < itemRequest.quantity()) {
                throw new RuntimeException(
                        "Not enough stock for product: " + product.getName());
            }

            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(itemRequest.quantity());
            orderItem.setUnitPrice(product.getPrice());

            product.setStockQuantity(product.getStockQuantity() - itemRequest.quantity());

            order.addItem(orderItem);
        }

        return orderRepository.save(order).getId();
    }
}