package com.popov.vendingmachine.dto;

import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class UpdateProductPrice {

	@Positive
	private BigDecimal price;

	public UpdateProductPrice(String code, BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
