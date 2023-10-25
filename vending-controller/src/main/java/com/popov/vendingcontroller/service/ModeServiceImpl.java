package com.popov.vendingcontroller.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;

@Service
public class ModeServiceImpl implements ModeService {

	public static final String MESSAGE_UNABLE_TO_SEND_REQUEST = "Unable to send request.";
	public static final String MESSAGE_INVALID_MODE = "Invalid mode.";

	@Value("${modes}")
	private String[] modes;

	@Value("${exchanges.vendingMachine.command}")
	private String vendingMachineCommandExchange;

	RabbitTemplate rabbitTemplate;

	@Autowired
	public ModeServiceImpl(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	public void changeVendingMachineMode(String machineName,String mode) {
		validateNewMode(mode);
		try {
			rabbitTemplate.convertAndSend(vendingMachineCommandExchange, machineName, mode);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, MESSAGE_UNABLE_TO_SEND_REQUEST);
		}
	}

	private void validateNewMode(String newMode) {
		if(newMode == null || Arrays.stream(modes).noneMatch(newMode::equals)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MESSAGE_INVALID_MODE);
		}
	}
}
