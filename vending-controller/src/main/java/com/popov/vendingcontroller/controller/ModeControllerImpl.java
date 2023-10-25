package com.popov.vendingcontroller.controller;

import com.popov.vendingcontroller.dto.ChangeModeRequest;
import com.popov.vendingcontroller.service.ModeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ModeControllerImpl implements ModeController {

	private ModeService modeService;

	@Autowired
	public ModeControllerImpl(ModeService modeService) {
		this.modeService = modeService;
	}


	public ResponseEntity changeMode(ChangeModeRequest changeModeRequest) {
		for (String machineName: changeModeRequest.getVendingMachines()) {
			modeService.changeVendingMachineMode(machineName, changeModeRequest.getNewMode());
		}

		return ResponseEntity.accepted().build();
	}
}
