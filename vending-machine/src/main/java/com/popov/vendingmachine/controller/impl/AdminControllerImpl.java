package com.popov.vendingmachine.controller.impl;

import com.popov.vendingmachine.controller.AdminController;
import com.popov.vendingmachine.dto.MachineState;
import com.popov.vendingmachine.dto.Product;
import com.popov.vendingmachine.dto.UpdateProductPrice;
import com.popov.vendingmachine.service.VendingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminControllerImpl implements AdminController {

	private VendingService vendingService;

	@Autowired
	public AdminControllerImpl(VendingService vendingService) {
		this.vendingService = vendingService;
	}


	@Override
	public ResponseEntity<Product> loadProduct(Product product) {
		return ResponseEntity.ok(vendingService.loadProduct(product));
	}

	@Override
	public ResponseEntity<Product> removeProduct(String code) {
		return ResponseEntity.ok(vendingService.removeProduct(code));
	}

	@Override
	public ResponseEntity<Product> updateProductPrice(String code, UpdateProductPrice updateProduct) {
		return ResponseEntity.ok(vendingService.updateProductPrice(code, updateProduct.getPrice()));

	}

	@Override
	public ResponseEntity<MachineState> getMachineState() {
		return ResponseEntity.ok(vendingService.getMachineState());
	}

}
