package com.popov.vendingmachine.service.state;

import com.popov.vendingmachine.dto.*;
import com.popov.vendingmachine.service.VendingService;
import com.popov.vendingmachine.util.MoneyUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

public class ProcessingOrderState implements State{

	private static final String MESSAGE_NO_COINS_TO_RETURN = "No coins to return";
	private static final String MESSAGE_CANNOT_SUPPLY_PRODUCTS = "You cannot supply products when processing an order.";
	private static final String MESSAGE_CANNOT_REMOVE_PRODUCTS = "You cannot remove products when processing an order.";
	private static final String MESSAGE_CANNOT_CHANGE_PRODUCT_PRICE = "You cannot change product price when processing an order.";
	public static final String MESSAGE_YOU_CANNOT_ACCESS_PRODUCT_INFO = "You cannot access product info when processing an order.";

	private VendingService vendingService;
	private State nextState;

	public ProcessingOrderState(VendingService vendingService, State nextState) {
		this.vendingService = vendingService;
		this.nextState = nextState;
	}

	@Override
	public ReturnedCreditResult returnCredit() {
		BigDecimal credit = vendingService.getCredit();
		if(credit.compareTo(BigDecimal.ZERO) < 0) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, MESSAGE_NO_COINS_TO_RETURN);
		}

		String creditToReturn = MoneyUtil.toLvFormat(credit);
		vendingService.resetCredit();
		this.vendingService.setCurrentState(this.nextState);
		return new ReturnedCreditResult(creditToReturn);
	}

	@Override
	public BigDecimal insertCoin(Coin coin) {
		return vendingService.addCredit(coin.getValue());
	}

	@Override
	public OrderResult buyProduct(String code) {
		vendingService.validateProductAvailable(code);
		BigDecimal price = vendingService.getPrice(code);
		vendingService.verifySufficientCredit(price);

		String product = vendingService.getProduct(code);
		BigDecimal change = vendingService.returnChange(vendingService.getPrice(code));
		vendingService.collectSum(price);

		this.vendingService.setCurrentState(this.nextState);
		return new OrderResult(product, change);
	}

	@Override
	public Product loadProduct(Product product) {
		throw new ResponseStatusException(HttpStatus.FORBIDDEN, MESSAGE_CANNOT_SUPPLY_PRODUCTS);
	}

	@Override
	public MachineState getMachineState() {
		throw new ResponseStatusException(HttpStatus.FORBIDDEN, MESSAGE_YOU_CANNOT_ACCESS_PRODUCT_INFO);
	}

	@Override
	public void setOperationalMode() {
		this.nextState = new OperationalState(vendingService);
	}

	@Override
	public void setMaintenanceMode() {
		this.nextState = new MaintenanceState(vendingService);
	}

	@Override
	public Product removeProduct(String code) {
		throw new ResponseStatusException(HttpStatus.FORBIDDEN, MESSAGE_CANNOT_REMOVE_PRODUCTS);
	}

	@Override
	public Product updateProductPrice(String code, BigDecimal price) {
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MESSAGE_CANNOT_CHANGE_PRODUCT_PRICE);
	}
}
