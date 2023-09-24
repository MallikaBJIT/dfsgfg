package com.example.product.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.product.entity.Product;

public interface ProductDao extends JpaRepository<Product, Integer> {

}
