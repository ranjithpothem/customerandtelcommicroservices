package com.database.InternalDB.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.database.InternalDB.entity.Users;
import com.database.InternalDB.service.DBService;

@RestController
@RequestMapping("/database")
public class DBController {
	
	@Autowired
	private DBService dBService;
	
	@GetMapping("/validate")
	public ResponseEntity<Long> validateUserDetails(
			@RequestParam String username,
			@RequestParam String password) {
		return dBService.validateTheDetails(username, password);
	}

	
	@GetMapping("/validateById")
	public ResponseEntity<List<Users>> validateByID(@RequestParam Iterable<Long> accountId) {
		return dBService.findByAccountId(accountId);
	}
	
	@PostMapping("/sendMessaging")
	public ResponseEntity<Object> sendMessage(
			@RequestParam Long mobile,
			@RequestParam String message,
			@RequestParam Long accountId) {
		return dBService.insertTheMessage(mobile, message, accountId);
	}

}
