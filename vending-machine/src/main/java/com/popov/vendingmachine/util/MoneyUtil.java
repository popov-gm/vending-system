package com.popov.vendingmachine.util;

import java.math.BigDecimal;

public class MoneyUtil {

	private final static String LV_FORMAT = "%.2f lv.";

	public static String toLvFormat(BigDecimal value) {

		return String.format(LV_FORMAT, value.doubleValue());
	}
}
