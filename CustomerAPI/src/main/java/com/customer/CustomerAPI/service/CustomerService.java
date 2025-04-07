package com.customer.CustomerAPI.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.customer.CustomerAPI.respose.DTOResponse;

@Service
public class CustomerService {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${internal.db.api.url}")
	private String dbUrl;
	
	
	@Value("${telecomoperator.api.url}")
	private String telecomUrl;

	public DTOResponse validatingDetails(String username, String password, String mobile, String message) {

		if (mobile.length() != 10 || message.isEmpty() || message.length() > 160) {
			return new DTOResponse("REJECTED", "FAILURE", null);
		}

		Integer account_id = restTemplate.getForObject(dbUrl + "/validate?username={username}&password={password}",
				Integer.class, username, password);
		if (account_id == null) {
			return new DTOResponse("REJECTED", "FAILURE", null);
		}

//		String uuid = restTemplate.postForObject(
//				dbUrl + "/sendMessaging?mobile={mobile}&message={message}&accountId={accountId}", null, String.class,
//				mobile, message, Long.valueOf(account_id));
		
		String uuid = restTemplate.postForObject(
				telecomUrl + "/sendmsg?mobile={mobile}&message={message}&accountId={accountId}", null, String.class,
				mobile, message, Long.valueOf(account_id));

		System.out.println(new DTOResponse("ACCEPTED", "SUCCESS", uuid));
		return new DTOResponse("ACCEPTED", "SUCCESS", uuid);
	}

}
