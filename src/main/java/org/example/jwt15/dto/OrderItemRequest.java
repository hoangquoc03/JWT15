package org.example.jwt15.dto;

import lombok.Data;

@Data
public class OrderItemRequest {

    private Long productId;

    private Integer quantity;
}