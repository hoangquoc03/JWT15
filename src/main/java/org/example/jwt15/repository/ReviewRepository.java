package org.example.jwt15.repository;

import org.example.jwt15.entity.Product;
import org.example.jwt15.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository
        extends JpaRepository<Review, Long> {

    List<Review> findByProduct(Product product);
}