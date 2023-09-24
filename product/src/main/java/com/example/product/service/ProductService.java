package com.example.product.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.product.dao.ProductDao;
import com.example.product.dto.OrderItemsDto;
import com.example.product.dto.ProductDto;
import com.example.product.entity.Product;
import com.example.product.exception.CustomException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
	private final ProductDao productDao;

	public void createProduct(ProductDto ProductDto) {
		Product product = Product.builder().name(ProductDto.getName()).quantity(ProductDto.getQuantity()).build();

		productDao.save(product);
	}

	public List<ProductDto> getAllProducts() {
		List<Product> products = productDao.findAll();
		return products.stream().map(this::mapToDto).toList();
	}

	public ProductDto mapToDto(Product product) {
		return ProductDto.builder().name(product.getName()).quantity(product.getQuantity()).build();
	}

	public void checkOrderItems(Set<OrderItemsDto> orderItems) {
		Set<Product> products = orderItems.stream().map(orderItem -> getProductDto(orderItem))
				.collect(Collectors.toSet());

		productDao.saveAll(products);
	}

	private Product getProductDto(OrderItemsDto orderItem) {
		Product product = productDao.findById(orderItem.getProductId())
				.orElseThrow(() -> new CustomException("Product not exist", HttpStatus.NOT_FOUND));
		if (orderItem.getQuantity() > product.getQuantity()) {
			System.out.println("out of stock product");
			throw new CustomException("Product out of stock", HttpStatus.BAD_REQUEST);
		}
		product.setQuantity(product.getQuantity() - orderItem.getQuantity());
		return product;
	}
}
