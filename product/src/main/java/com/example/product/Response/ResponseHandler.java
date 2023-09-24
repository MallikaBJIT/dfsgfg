package com.example.product.Response;

import java.util.Date;

import com.example.product.dto.ProductDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseHandler {
	private Date timestamp;
	private String message;
	private String details;
}
