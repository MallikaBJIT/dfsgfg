package com.example.order.feign;

import java.util.List;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.order.exception.FeignErrorDecoder;
import com.example.order.dto.UserResponseDto;

@FeignClient(name = "user-app", configuration = FeignErrorDecoder.class)
public interface UserFeign {
    @CircuitBreaker(name = "CircuitBreakerService", fallbackMethod = "fallbackGetUserById")
    @GetMapping("/user/getuserbyid/{userId}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable(name = "userId") int userId);

    default ResponseEntity<UserResponseDto> fallbackGetUserById(int userId, Throwable throwable) {
        System.out.println("user fall back ......");
        return new ResponseEntity<>(new UserResponseDto(), HttpStatus.BAD_REQUEST);
    }
}
