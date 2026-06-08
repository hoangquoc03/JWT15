package org.example.jwt15.controller;

import lombok.RequiredArgsConstructor;
import org.example.jwt15.dto.ReviewRequest;
import org.example.jwt15.entity.Review;
import org.example.jwt15.service.ReviewService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/api/reviews")
    public Review createReview(
            Authentication authentication,
            @RequestBody ReviewRequest request) {

        return reviewService.createReview(
                authentication.getName(),
                request);
    }

    @GetMapping("/api/products/{id}/reviews")
    public List<Review> getReviews(
            @PathVariable Long id) {

        return reviewService.getReviews(id);
    }
}