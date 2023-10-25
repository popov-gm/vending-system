package com.popov.vendingmachine.service;

import java.math.BigDecimal;

public interface MoneyService {

	BigDecimal addCredit(BigDecimal money);

	BigDecimal returnChange(BigDecimal price);

	BigDecimal getCredit();

	void resetCredit();

	void verifySufficientCredit(BigDecimal price);

	void collectSum(BigDecimal price);

	BigDecimal getTotalMoney();
}
