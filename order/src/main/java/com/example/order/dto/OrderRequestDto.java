package com.example.order.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequestDto {
	private String orderDate;
    private String status;
    private int customerId;
    private Set<OrderItemsDto> orderItemsDtos;
}
