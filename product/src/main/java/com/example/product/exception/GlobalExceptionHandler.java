package com.example.product.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.example.product.Response.ResponseHandler;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<?> handleResourceNotFoundException(CustomException ex, WebRequest webRequest) {
		ResponseHandler errorDetails = new ResponseHandler(new Date(), ex.getMessage(),
				webRequest.getDescription(false));
		return ResponseEntity.status(ex.getStatus()).body(errorDetails);
	}
}
