package com.popov.vendingmachine.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class Product {

	@NotNull
	@NotEmpty
	private String code;

	@NotNull
	@NotEmpty
	private String name;

	@Positive
	private BigDecimal price;

	@Min(1)
	@Max(10)
	private int quantity;

	public Product(String code, String name, BigDecimal price, int quantity) {
		this.code = code;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void decrementQuantity() {
		if (quantity > 0) {
			quantity--;
		}
	}
}
