package org.example.jwt15.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequest {

    private List<OrderItemRequest> items;
}