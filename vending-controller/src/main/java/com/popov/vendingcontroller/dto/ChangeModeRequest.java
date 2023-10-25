package com.popov.vendingcontroller.dto;



import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class ChangeModeRequest {

	@NotEmpty( message = "Please provide a list of vending machines.")
	@NotNull( message = "Please provide a list of vending machines.")
	private List<String> vendingMachines;

	@NotEmpty( message = "Please provide either maintenance or operational mode.")
	@NotNull( message = "Please provide either maintenance or operational mode.")
	private String newMode;


	public List<String> getVendingMachines() {
		return vendingMachines;
	}

	public void setVendingMachines(List<String> vendingMachines) {
		this.vendingMachines = vendingMachines;
	}

	public String getNewMode() {
		return newMode;
	}

	public void setNewMode(String newMode) {
		this.newMode = newMode;
	}
}
