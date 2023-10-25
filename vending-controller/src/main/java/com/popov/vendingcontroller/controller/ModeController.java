package com.popov.vendingcontroller.controller;

import com.popov.vendingcontroller.dto.ChangeModeRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/mode")
public interface ModeController {

	@PostMapping()
	ResponseEntity changeMode(@Valid @RequestBody ChangeModeRequest changeModeRequest);
}
