package org.example.jwt15.controller;

import lombok.RequiredArgsConstructor;
import org.example.jwt15.dto.RevenueResponse;
import org.example.jwt15.service.ReportService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/revenue")
    public RevenueResponse revenue(
            @RequestParam String type) {

        return reportService.revenue(type);
    }
}