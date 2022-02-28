package com.rodrigues.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rodrigues.entities.User;
import com.rodrigues.services.UserServices;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	
	@Autowired
	private UserServices service;
	
	@GetMapping(value = "/search")
	public ResponseEntity<User> fingByEmail(@RequestParam String email){
		try {
			User user = service.findByEmail(email);
			return ResponseEntity.ok().body(user);
		}catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
