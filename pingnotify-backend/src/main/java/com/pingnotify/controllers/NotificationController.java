package com.pingnotify.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pingnotify.dtos.NotificationRequest;
import com.pingnotify.services.WhatsAppService;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "http://localhost:4200")
public class NotificationController {

	private final WhatsAppService whatsAppService;

	public NotificationController(WhatsAppService whatsAppService) {
		this.whatsAppService = whatsAppService;
	}

	@PostMapping("/send")
	public String sendNotification(@RequestBody NotificationRequest request) {

		return whatsAppService.sendTemplateMessage(request.getRecipientPhoneNumber(), request.getMessage());
	}

}
