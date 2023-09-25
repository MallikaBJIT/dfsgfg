package com.example.order.feign;

import java.util.Set;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.order.dto.OrderItemsDto;
import com.example.order.exception.FeignErrorDecoder;

@FeignClient(name = "product-app", configuration = FeignErrorDecoder.class)
public interface ProductFeign {
    @CircuitBreaker(name = "CircuitBreakerService", fallbackMethod = "fallbackGetAllProducts")
    @PostMapping("/product/getAll")
    public ResponseEntity<String> getAllProducts(@RequestBody Set<OrderItemsDto> orderItems);

    default ResponseEntity<String> fallbackGetAllProducts(Set<OrderItemsDto> orderItems, Throwable throwable) {
        System.out.println("product fall back .....");
        return new ResponseEntity<>("product fallback method", HttpStatus.BAD_REQUEST);
    }
}
