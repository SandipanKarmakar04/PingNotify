package com.pingnotify.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class WhatsAppService {

	private final RestClient restClient;

	@Value("${whatsapp.api.version}")
	private String apiVersion;

	@Value("${whatsapp.api.phone-number-id}")
	private String phoneNumberId;

	public WhatsAppService(@Value("${whatsapp.api.base-url}") String baseUrl,
			@Value("${whatsapp.api.access-token}") String accessToken) {

		this.restClient = RestClient.builder().baseUrl(baseUrl).defaultHeader("Authorization", "Bearer " + accessToken)
				.defaultHeader("Content-Type", "application/json").build();
	}

	public String sendTemplateMessage(String recipientPhoneNumber, String message) {

		Map<String, Object> requestBody = Map.of("messaging_product", "whatsapp", "to", recipientPhoneNumber, "type",
				"template", "template",
				Map.of("name", "pingnotify_notification", "language", Map.of("code", "en"), "components",
						java.util.List.of(Map.of("type", "body", "parameters",
								java.util.List.of(Map.of("type", "text", "text", message))))));

		return restClient.post().uri("/{version}/{phoneNumberId}/messages", apiVersion, phoneNumberId).body(requestBody)
				.retrieve().body(String.class);
	}

}
