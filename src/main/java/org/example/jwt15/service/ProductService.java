package org.example.jwt15.service;

import lombok.RequiredArgsConstructor;
import org.example.jwt15.entity.Product;
import org.example.jwt15.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product create(Product product) {
        return productRepository.save(product);
    }

    public Product update(Long id, Product product) {

        Product existing = productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));

        existing.setName(product.getName());
        existing.setDescription(product.getDescription());
        existing.setPrice(product.getPrice());
        existing.setSize(product.getSize());
        existing.setToppings(product.getToppings());

        return productRepository.save(existing);
    }

    public void delete(Long id) {

        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found");
        }

        productRepository.deleteById(id);
    }
}