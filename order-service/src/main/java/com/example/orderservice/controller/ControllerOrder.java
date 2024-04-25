package com.example.orderservice.controller;

import com.example.orderservice.dto.RequestOrder;
import com.example.orderservice.service.ServiceOrder;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class ControllerOrder {

    private final ServiceOrder serviceOrder;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "inventory" , fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "inventory")
    @Retry(name = "inventory")
    public CompletableFuture<String> placeOrder(@RequestBody RequestOrder requestOrder){
        return CompletableFuture.supplyAsync(()->serviceOrder.placeOrder(requestOrder));
    }
   public CompletableFuture<String> fallbackMethod(RequestOrder requestOrder,RuntimeException runtimeException){

        return CompletableFuture.supplyAsync(()->  "Oops! Sometimes went wrong , please order after some time!");

   }


}
