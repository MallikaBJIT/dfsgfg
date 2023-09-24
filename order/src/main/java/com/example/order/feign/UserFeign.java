package com.example.order.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.order.exception.FeignErrorDecoder;
import com.example.order.dto.UserResponseDto;

@FeignClient(name = "user-app", configuration = FeignErrorDecoder.class)
public interface UserFeign {
	@GetMapping("/user/getuserbyid/{userId}")
	public ResponseEntity<UserResponseDto> getUserById(@PathVariable(name = "userId") int userId);
}
