package com.example.order.controller;

import com.example.order.dto.OrderRequestDto;
import com.example.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService orderService;

	@PostMapping("/create")
	public ResponseEntity<?> createOrder(@RequestBody OrderRequestDto orderRequestDto) {
		// return ResponseEntity.ok(orderService.helloOrder());
		return ResponseEntity.ok(orderService.createOrder(orderRequestDto));
	}
}
