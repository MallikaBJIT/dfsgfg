package com.example.order.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.order.dao.OrderDao;
import com.example.order.dto.OrderItemsDto;
import com.example.order.dto.OrderRequestDto;
import com.example.order.exception.CustomException;
import com.example.order.feign.UserFeign;
import com.example.order.feign.ProductFeign;
import com.example.order.model.OrderEntity;
import com.example.order.model.OrderItems;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
	private final OrderDao orderDao;
	private final UserFeign userFeign;
	private final ProductFeign productFeign;

	public String createOrder(OrderRequestDto orderRequestDto) {
		try {
			userFeign.getUserById(orderRequestDto.getCustomerId());
		} catch (CustomException ex) {
			throw ex;
		}
		try {
			productFeign.getAllProducts(orderRequestDto.getOrderItemsDtos());
		} catch (CustomException ex) {
			//return ex.toString();
			throw ex;
		}

		OrderEntity order = new OrderEntity();
		order.setCustomerId(orderRequestDto.getCustomerId());
		order.setOrderDate(orderRequestDto.getOrderDate());
		order.setStatus(orderRequestDto.getStatus());

		Set<OrderItems> orderItems = new HashSet<>();
		for (OrderItemsDto orderItemsDto : orderRequestDto.getOrderItemsDtos()) {
			OrderItems orderItem = new OrderItems();
			orderItem.setProductId(orderItemsDto.getProductId());
			orderItem.setQuantity(orderItemsDto.getQuantity());
			orderItem.setOrder(order);
			orderItems.add(orderItem);
		}
		order.setOrderItems(orderItems);

		orderDao.save(order);
		return "successfully save order";
	}

	private OrderItems mapToOderItems(OrderItemsDto orderItemDto) {
		// TODO Auto-generated method stub
		return OrderItems.builder().productId(orderItemDto.getProductId()).quantity(orderItemDto.getQuantity()).build();
	}
}
