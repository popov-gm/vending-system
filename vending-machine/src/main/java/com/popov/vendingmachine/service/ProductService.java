package com.popov.vendingmachine.service;


import com.popov.vendingmachine.dto.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

	Product addProduct(Product code);

	String getProduct(String code);

	Product deleteProduct(String code);

	BigDecimal getPrice(String code);

	void validateProductAvailable(String code);

	Product updateProduct(String code, BigDecimal price);

	List<Product> listAvailableProducts();
}
