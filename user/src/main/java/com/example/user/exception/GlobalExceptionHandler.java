package com.example.user.exception;

import org.springframework.web.context.request.WebRequest;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.example.user.response.ErrorDetails;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ErrorDetails> handleResourceNotFoundException(CustomException ex, WebRequest webRequest) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), webRequest.getDescription(false));
		return ResponseEntity.status(ex.getStatus()).body(errorDetails);
	}
}
