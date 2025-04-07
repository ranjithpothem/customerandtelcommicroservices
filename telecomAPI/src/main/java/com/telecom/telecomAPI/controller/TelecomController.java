package com.telecom.telecomAPI.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

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

	private static String uuid =null;
	
	//this is more effective way to create  id in single variable shared in class
	  private final AtomicReference<String> atomicUuid = new AtomicReference<>();


	@PostMapping("/smsc")
	public String telecomMessage(@RequestParam Long accountId, @RequestParam long mobile,
			@RequestParam String message) {

		if (String.valueOf(mobile).length() != 10 || message.isEmpty() || accountId == null) {
			return new DTOResponse("REJECTED", "FAILURE", null).toString();
		}

//		String uuid = UUID.randomUUID().toString();
		String currentUuid= atomicUuid.get();
		return new DTOResponse("ACCEPTED", "SUCCESS", atomicUuid.get()).toString();
	}

	// /sendMessaging?mobile={mobile}&message={message}&accountId={accountId}"

	@PostMapping("/sendmsg")
	public String sendingMessage(@RequestParam("mobile") String mobile, @RequestParam("message") String message,
			@RequestParam("accountId") Long accountId) {

		String response = restTemplate.postForObject(
				dbUrl + "/sendMessaging?mobile={mobile}&message={message}&accountId={accountId}", null, String.class,
				mobile, message, Long.valueOf(accountId));
		if (response != null) {
			//return new DTOResponse("ACCEPTED", "SUCCESS", uuid).toString();
			//uuid= UUID.randomUUID().toString();
			atomicUuid.set(UUID.randomUUID().toString());
			
			return atomicUuid.get();
			//return uuid;
		}

		//return new DTOResponse("REJECTED", "FAILURE", null).toString();
		return null;
	}

}
