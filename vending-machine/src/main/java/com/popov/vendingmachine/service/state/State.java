package com.popov.vendingmachine.service.state;

import com.popov.vendingmachine.dto.*;

import java.math.BigDecimal;

public interface State {

	ReturnedCreditResult returnCredit();

	BigDecimal insertCoin(Coin coin);

	OrderResult buyProduct(String code);

	Product loadProduct(Product code);

	MachineState getMachineState();

	void setOperationalMode();

	void setMaintenanceMode();

	Product removeProduct(String code);

	Product updateProductPrice(String code, BigDecimal price);
}
