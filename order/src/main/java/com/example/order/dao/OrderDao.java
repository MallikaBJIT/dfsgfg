package com.example.order.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.order.model.OrderEntity;

public interface OrderDao extends JpaRepository<OrderEntity, Integer> {

}
