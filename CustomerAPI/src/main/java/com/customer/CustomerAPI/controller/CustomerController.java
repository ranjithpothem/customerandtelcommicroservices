package com.customer.CustomerAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.customer.CustomerAPI.respose.DTOResponse;
import com.customer.CustomerAPI.service.CustomerService;

@RestController
@RequestMapping("/customerAPI")
public class CustomerController {

	@Autowired
	private CustomerService service;

	@PostMapping("/sendmessage")
	public String sendMessage(@RequestParam String username, 
			@RequestParam String password,
			@RequestParam String mobile,
			@RequestParam String message) {
		DTOResponse respose = service.validatingDetails(username, password, mobile, message);
		return respose.toString();
	}
}
