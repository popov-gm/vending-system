package com.popov.vendingmachine.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumer {

	private VendingService vendingService;

	public MessageConsumer(VendingService vendingService) {
		this.vendingService = vendingService;
	}

	@RabbitListener(queues = "${serviceId}Queue")
	public void changeMode(String message) {
		System.out.println(message);
		switch (message) {
			case "operational":
				vendingService.setOperationalMode();
				break;
			case "maintenance":
				vendingService.setMaintenanceMode();
				break;
			default:
				// Do nothing
		}
	}
}
