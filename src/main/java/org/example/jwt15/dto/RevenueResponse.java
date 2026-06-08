package org.example.jwt15.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class RevenueResponse {

    private String period;

    private BigDecimal revenue;
}