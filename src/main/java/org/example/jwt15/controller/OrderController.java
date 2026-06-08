package org.example.jwt15.controller;

import lombok.RequiredArgsConstructor;
import org.example.jwt15.dto.CreateOrderRequest;
import org.example.jwt15.entity.Order;
import org.example.jwt15.service.OrderService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public Order createOrder(
            Authentication authentication,
            @RequestBody CreateOrderRequest request) {

        return orderService.createOrder(
                authentication.getName(),
                request);
    }

    @GetMapping("/my")
    public List<Order> myOrders(
            Authentication authentication) {

        return orderService.myOrders(
                authentication.getName());
    }

    @GetMapping
    public List<Order> getAllOrders() {

        return orderService.getAllOrders();
    }

    @PutMapping("/{id}/status")
    public Order updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        return orderService.updateStatus(id, status);
    }
}