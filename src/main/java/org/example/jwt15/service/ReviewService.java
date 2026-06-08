package org.example.jwt15.service;

import lombok.RequiredArgsConstructor;
import org.example.jwt15.dto.ReviewRequest;
import org.example.jwt15.entity.*;
import org.example.jwt15.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public Review createReview(
            String email,
            ReviewRequest request) {

        User user = userRepository.findByEmail(email)
                .orElseThrow();

        Product product =
                productRepository.findById(
                                request.getProductId())
                        .orElseThrow();

        long purchased =
                orderRepository.countPurchasedProduct(
                        user,
                        product);

        if (purchased == 0) {
            throw new RuntimeException(
                    "You have not purchased this product");
        }

        Review review = Review.builder()
                .user(user)
                .product(product)
                .rating(request.getRating())
                .comment(request.getComment())
                .createdDate(LocalDateTime.now())
                .build();

        return reviewRepository.save(review);
    }

    public List<Review> getReviews(
            Long productId) {

        Product product =
                productRepository.findById(productId)
                        .orElseThrow();

        return reviewRepository.findByProduct(product);
    }
}