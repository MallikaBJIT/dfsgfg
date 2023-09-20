package com.example.order.controller;

import com.example.order.external.service.ProductService;
import com.example.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;

    @GetMapping("/hello")
    public ResponseEntity<?> helloOrder() {
        //return ResponseEntity.ok(orderService.helloOrder());
        return ResponseEntity.ok(productService.helloProduct());
    }
}
