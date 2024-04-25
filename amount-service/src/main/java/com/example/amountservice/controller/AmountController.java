package com.example.amountservice.controller;


import com.example.amountservice.dto.AmountResponse;
import com.example.amountservice.service.AmountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/amount")
@RequiredArgsConstructor
public class AmountController {

    private final AmountService amountService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AmountResponse> isInStock(@RequestParam List<String> skuCode){
        return amountService.isInStock(skuCode);
    }
}
