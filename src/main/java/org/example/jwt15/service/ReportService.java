package org.example.jwt15.service;

import lombok.RequiredArgsConstructor;
import org.example.jwt15.dto.RevenueResponse;
import org.example.jwt15.entity.Order;
import org.example.jwt15.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final OrderRepository orderRepository;

    public RevenueResponse revenue(
            String type) {

        List<Order> orders =
                orderRepository.findAll();

        BigDecimal total =
                orders.stream()
                        .map(Order::getTotalMoney)
                        .reduce(
                                BigDecimal.ZERO,
                                BigDecimal::add);

        return new RevenueResponse(
                type,
                total
        );
    }
}