package com.popov.vendingmachine.service.impl;

import com.popov.vendingmachine.dto.Product;
import com.popov.vendingmachine.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

	private final static int MIN_QUANTITY = 1;
	private final static int MAX_QUANTITY = 10;
	public static final String MESSAGE_PRODUCT_ISNT_AVAILABLE = "Product isn't available.";
	public static final String MESSAGE_SLOT_IS_NOT_AVAILABLE = "Slot %s is not available.";
	public static final String MESSAGE_PRODUCT_QUANTITY_MUST_BE_NO_LESS_THAN_1 = "Product quantity must be no less than 1.";
	public static final String MESSAGE_PRODUCT_PRICE_MUST_BE_GREATER_THAN_0 = "Product price must be greater than 0.";


	Map<String, Product> productMap = new HashMap<>();

	@Override
	public Product addProduct(Product product) {
		product = validateProduct(product);
		productMap.put(product.getCode(), product);
		return product;
	}

	@Override
	public String getProduct(String code) {
		decrementProductQuantity(code);
		return productMap.get(code).getName();
	}

	@Override
	public Product deleteProduct(String code) {
		return productMap.remove(code);
	}

	@Override
	public BigDecimal getPrice(String code) {
		return productMap.get(code).getPrice();
	}

	@Override
	public void validateProductAvailable(String code) {
		if (!productMap.containsKey(code) || productMap.get(code).getQuantity() <= 0) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, MESSAGE_PRODUCT_ISNT_AVAILABLE);
		}
	}

	@Override
	public Product updateProduct(String code, BigDecimal price) {
		validateProductAvailable(code);
		Product product = productMap.get(code);
		product.setPrice(price);
		productMap.put(code, product);
		return product;
	}

	@Override
	public List<Product> listAvailableProducts() {
		return productMap.values().stream().toList();
	}

	private void decrementProductQuantity(String code) {
		Product product = productMap.get(code);
		product.decrementQuantity();
		productMap.put(code, product);
	}

	private Product validateProduct(Product product) {
		if(productMap.containsKey(product.getCode()) && productMap.get(product.getCode()).getQuantity() > 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					String.format(MESSAGE_SLOT_IS_NOT_AVAILABLE, product.getCode()));
		}

		if(product.getQuantity() > MAX_QUANTITY) {
			product.setQuantity(MAX_QUANTITY);
		}

		if (product.getQuantity() < MIN_QUANTITY) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MESSAGE_PRODUCT_QUANTITY_MUST_BE_NO_LESS_THAN_1);
		}

		if(product.getPrice().compareTo(BigDecimal.ZERO) < 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MESSAGE_PRODUCT_PRICE_MUST_BE_GREATER_THAN_0);
		}

		return product;
	}
}
