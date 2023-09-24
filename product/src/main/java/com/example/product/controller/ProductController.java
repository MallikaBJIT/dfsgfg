package com.example.product.controller;

import com.example.product.dto.OrderItemsDto;
import com.example.product.dto.ProductDto;
import com.example.product.service.ProductService;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductService productService;

	@PostMapping("/create")
	public ResponseEntity<?> createProduct(@RequestBody ProductDto productRequest) {
		productService.createProduct(productRequest);
		return ResponseEntity.ok("product is added");
	}

	@GetMapping("/getAll")
	public ResponseEntity<?> getAllProducts() {
		return ResponseEntity.ok(productService.getAllProducts());
	}

	@PostMapping("/getAll")
	public ResponseEntity<?> getAllProducts(@RequestBody Set<OrderItemsDto> orderItems) {
		productService.checkOrderItems(orderItems);
		return ResponseEntity.ok("All products available");
	}

}
