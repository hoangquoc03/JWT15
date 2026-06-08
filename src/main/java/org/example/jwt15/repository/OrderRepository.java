package org.example.jwt15.repository;

import org.example.jwt15.entity.Order;
import org.example.jwt15.entity.Product;
import org.example.jwt15.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository
        extends JpaRepository<Order, Long> {

    List<Order> findByUser(User user);
    @Query("""
        SELECT COUNT(o)
        FROM Order o
        JOIN o.items oi
        WHERE o.user = :user
        AND oi.product = :product
    """)
    long countPurchasedProduct(
            @Param("user") User user,
            @Param("product") Product product
    );
}