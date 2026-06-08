package org.example.jwt15.controller;

import lombok.RequiredArgsConstructor;
import org.example.jwt15.entity.Product;
import org.example.jwt15.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping
    public Product create(
            @RequestBody Product product) {

        return productService.create(product);
    }

    @PutMapping("/{id}")
    public Product update(
            @PathVariable Long id,
            @RequestBody Product product) {

        return productService.update(id, product);
    }

    @DeleteMapping("/{id}")
    public String delete(
            @PathVariable Long id) {

        productService.delete(id);

        return "Deleted successfully";
    }
}