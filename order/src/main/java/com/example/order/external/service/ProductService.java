package com.example.order.external.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("PRODUCT-APP")
public interface ProductService {
    @GetMapping("/product/hello")
    public String helloProduct();
}
