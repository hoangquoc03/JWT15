package org.example.jwt15.repository;

import org.example.jwt15.entity.Order;
import org.example.jwt15.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository
        extends JpaRepository<Order, Long> {

    List<Order> findByUser(User user);
}