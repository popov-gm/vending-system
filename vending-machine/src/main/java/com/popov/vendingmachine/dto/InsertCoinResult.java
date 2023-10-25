package com.popov.vendingmachine.dto;

public class InsertCoinResult {

	private String insertedCoin;

	private String totalCredit;

	public InsertCoinResult(String insertedCoin, String totalCredit) {
		this.insertedCoin = insertedCoin;
		this.totalCredit = totalCredit;
	}

	public String getInsertedCoin() {
		return insertedCoin;
	}

	public void setInsertedCoin(String insertedCoin) {
		this.insertedCoin = insertedCoin;
	}

	public String getTotalCredit() {
		return totalCredit;
	}

	public void setTotalCredit(String totalCredit) {
		this.totalCredit = totalCredit;
	}
}
