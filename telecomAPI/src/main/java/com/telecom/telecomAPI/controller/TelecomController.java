package com.telecom.telecomAPI.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.telecom.telecomAPI.DTOResponse.DTOResponse;

@RestController
@RequestMapping("/telecomoperator")
public class TelecomController {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${internal.db.api.url}")
	private String dbUrl;

	@PostMapping("/smsc")
	public String telecomMessage(@RequestParam Long accountId, @RequestParam long mobile,
			@RequestParam String message) {

		if (String.valueOf(mobile).length() != 10 || message.isEmpty() || accountId == null) {
			return new DTOResponse("REJECTED", "FAILURE", null).toString();
		}

		String uuid = UUID.randomUUID().toString();
		return new DTOResponse("ACCEPTED", "SUCCESS", uuid).toString();
	}

}
