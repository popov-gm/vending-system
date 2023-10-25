package com.popov.vendingmachine.dto;

import java.math.BigDecimal;

public class OrderResult {

	private String product;
	private BigDecimal change;

	public OrderResult(String product, BigDecimal change) {
		this.product = product;
		this.change = change;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public BigDecimal getChange() {
		return change;
	}

	public void setChange(BigDecimal change) {
		this.change = change;
	}
}
