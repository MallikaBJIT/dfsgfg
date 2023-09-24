package com.example.order.feign;

import java.util.Set;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.order.dto.OrderItemsDto;
import com.example.order.exception.FeignErrorDecoder;

@FeignClient(name = "product-app", configuration = FeignErrorDecoder.class)
public interface ProductFeign {
	@PostMapping("/product/getAll")
	public ResponseEntity<String> getAllProducts(@RequestBody Set<OrderItemsDto> orderItems);
}
