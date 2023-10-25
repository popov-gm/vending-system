package com.popov.vendingmachine.controller;

import com.popov.vendingmachine.dto.MachineState;
import com.popov.vendingmachine.dto.Product;
import com.popov.vendingmachine.dto.UpdateProductPrice;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/admin")
public interface AdminController {

	@PostMapping("/product")
	ResponseEntity<Product> loadProduct(@Valid @RequestBody Product product);

	@DeleteMapping("/product/{code}")
	ResponseEntity<Product> removeProduct(@PathVariable String code);

	@PatchMapping("/product/{code}")
	ResponseEntity updateProductPrice(@PathVariable String code, @Valid @RequestBody UpdateProductPrice updateProduct);

	@GetMapping("/machine/state")
	ResponseEntity<MachineState> getMachineState();
}
