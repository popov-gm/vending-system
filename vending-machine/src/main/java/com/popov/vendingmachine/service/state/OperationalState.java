package com.popov.vendingmachine.service.state;

import com.popov.vendingmachine.dto.*;
import com.popov.vendingmachine.service.VendingService;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

public class OperationalState implements State {

	private static final String MESSAGE_INSERT_COIN_FIRST = "Insert coin first.";
	private static final String MESSAGE_CANT_SUPPLY_PRODUCTS = "You cannot supply products in operational mode.";
	private static final String MESSAGE_CANNOT_REMOVE_PRODUCTS = "You cannot remove products in operational mode.";
	private static final String MESSAGE_CANNOT_CHANGE_PRICE = "You cannot change product price in operational mode.";
	private static final String MESSAGE_NO_COINS = "No coins to return";
	private static final String MESSAGE_CANNOT_ACCESS_MACHINE_STATE = "You cannot access machine state in operation mode.";

	private VendingService vendingService;

	public OperationalState(VendingService vendingService) {
		this.vendingService = vendingService;

	}

	@Override
	public ReturnedCreditResult returnCredit() {
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, MESSAGE_NO_COINS);
	}

	@Override
	public BigDecimal insertCoin(Coin coin) {
		BigDecimal totalCredit = vendingService.addCredit(coin.getValue());
		vendingService.setCurrentState(new ProcessingOrderState(vendingService, new OperationalState(vendingService)));
		return totalCredit;
	}

	@Override
	public OrderResult buyProduct(String code) {
		throw new ResponseStatusException(HttpStatus.FORBIDDEN, MESSAGE_INSERT_COIN_FIRST);
	}

	@Override
	public Product loadProduct(Product product) {
		throw new ResponseStatusException(HttpStatus.FORBIDDEN, MESSAGE_CANT_SUPPLY_PRODUCTS);
	}

	@Override
	public void setOperationalMode() {
		// Already in operational mode
	}

	@Override
	public void setMaintenanceMode() {
		vendingService.setCurrentState(new MaintenanceState(vendingService));
	}

	@Override
	public Product removeProduct(String code) {
		throw new ResponseStatusException(HttpStatus.FORBIDDEN, MESSAGE_CANNOT_REMOVE_PRODUCTS);
	}

	@Override
	public Product updateProductPrice(String code, BigDecimal price) {
		throw new ResponseStatusException(HttpStatus.FORBIDDEN, MESSAGE_CANNOT_CHANGE_PRICE);
	}

	@Override
	public MachineState getMachineState() {
		throw new ResponseStatusException(HttpStatus.FORBIDDEN, MESSAGE_CANNOT_ACCESS_MACHINE_STATE);
	}
}
