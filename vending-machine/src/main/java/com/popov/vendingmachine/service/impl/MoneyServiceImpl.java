package com.popov.vendingmachine.service.impl;

import com.popov.vendingmachine.service.MoneyService;
import com.popov.vendingmachine.util.MoneyUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@Service
public class MoneyServiceImpl implements MoneyService {

	private static final String MESSAGE_INSUFFICIENT_CREDIT = "Insufficient credit. Please insert additional %s";

	private BigDecimal credit = BigDecimal.ZERO;
	private BigDecimal totalMoney = BigDecimal.ZERO;

	@Override
	public BigDecimal addCredit(BigDecimal value) {
		credit = credit.add(value);
		totalMoney.add(value);
		return credit;
	}

	@Override
	public BigDecimal returnChange(BigDecimal price) {
		BigDecimal change = credit.subtract(price);
		credit = credit.subtract(change);
		return change;
	}

	@Override
	public BigDecimal getCredit() {
        return credit;
    }

	@Override
	public void resetCredit() {
		credit = BigDecimal.ZERO;
	}

	@Override
	public void verifySufficientCredit(BigDecimal price) {
		if(credit.compareTo(price) < 0) {
            BigDecimal shortage = price.subtract(credit);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					String.format(MESSAGE_INSUFFICIENT_CREDIT, MoneyUtil.toLvFormat(shortage)));
        }
	}

	@Override
	public void collectSum(BigDecimal value) {
		credit = credit.subtract(value);
		totalMoney = totalMoney.add(value);
	}

	@Override
	public BigDecimal getTotalMoney() {
		return totalMoney;
	}
}
