package com.popov.vendingmachine.service.state;

import com.popov.vendingmachine.dto.*;
import com.popov.vendingmachine.service.VendingService;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

public class MaintenanceState implements State {

	private static final String MESSAGE_CANT_RETURN_CREDIT = "You can't return credit in maintenance mode.";
	private static final String MESSAGE_CANT_INSERT_COIN = "You can't insert coin in maintenance mode.";
	private static final String MESSAGE_CANT_BUY_PRODUCTS = "You can't buy products in maintenance mode.";

	private VendingService vendingService;

	public MaintenanceState(VendingService vendingService) {
		this.vendingService = vendingService;
	}

	@Override
	public ReturnedCreditResult returnCredit() {
		throw new ResponseStatusException(HttpStatus.FORBIDDEN, MESSAGE_CANT_RETURN_CREDIT);
	}

	@Override
	public BigDecimal insertCoin(Coin coin) {
		throw new ResponseStatusException(HttpStatus.FORBIDDEN, MESSAGE_CANT_INSERT_COIN);
	}

	@Override
	public OrderResult buyProduct(String code) {
		throw new ResponseStatusException(HttpStatus.FORBIDDEN, MESSAGE_CANT_BUY_PRODUCTS);
	}

	@Override
	public Product loadProduct(Product product) {
		return vendingService.addProduct(product);
	}

	@Override
	public MachineState getMachineState() {
		return vendingService.getMachineInfo();
	}

	@Override
	public void setOperationalMode() {
		vendingService.setCurrentState(new OperationalState(vendingService));

	}

	@Override
	public void setMaintenanceMode() {
		// Already in maintenance mode
	}

	@Override
	public Product removeProduct(String code) {
		return vendingService.deleteProduct(code);
	}

	@Override
	public Product updateProductPrice(String code, BigDecimal price) {
		return vendingService.updateProduct(code, price);
	}
}
