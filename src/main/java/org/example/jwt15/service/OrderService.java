package org.example.jwt15.service;

import lombok.RequiredArgsConstructor;
import org.example.jwt15.dto.CreateOrderRequest;
import org.example.jwt15.dto.OrderItemRequest;
import org.example.jwt15.entity.*;
import org.example.jwt15.repository.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public Order createOrder(
            String email,
            CreateOrderRequest request) {

        User user = userRepository.findByEmail(email)
                .orElseThrow();

        Order order = new Order();

        order.setUser(user);
        order.setCreatedDate(LocalDateTime.now());
        order.setStatus("PENDING");

        List<OrderItem> orderItems = new ArrayList<>();

        BigDecimal total = BigDecimal.ZERO;

        for (OrderItemRequest itemReq : request.getItems()) {

            Product product =
                    productRepository.findById(
                                    itemReq.getProductId())
                            .orElseThrow();

            BigDecimal itemTotal =
                    product.getPrice().multiply(
                            BigDecimal.valueOf(
                                    itemReq.getQuantity()));

            total = total.add(itemTotal);

            OrderItem item = new OrderItem();

            item.setProduct(product);
            item.setQuantity(itemReq.getQuantity());
            item.setPriceBuy(product.getPrice());
            item.setOrder(order);

            orderItems.add(item);
        }

        order.setItems(orderItems);
        order.setTotalMoney(total);

        return orderRepository.save(order);
    }

    public List<Order> myOrders(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow();

        return orderRepository.findByUser(user);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order updateStatus(
            Long orderId,
            String status) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow();

        order.setStatus(status);

        return orderRepository.save(order);
    }
}