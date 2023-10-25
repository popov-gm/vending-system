package com.popov.vendingmachine.dto;

import java.util.List;

public class MachineState {

	List<Product> availableProducts;

	String collectedMoney;

	public MachineState(List<Product> availableProducts, String collectedMoney) {
		this.availableProducts = availableProducts;
		this.collectedMoney = collectedMoney;
	}

	public List<Product> getAvailableProducts() {
		return availableProducts;
	}

	public void setAvailableProducts(List<Product> availableProducts) {
		this.availableProducts = availableProducts;
	}

	public String getCollectedMoney() {
		return collectedMoney;
	}

	public void setCollectedMoney(String collectedMoney) {
		this.collectedMoney = collectedMoney;
	}
}
