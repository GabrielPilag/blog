package com.gabriel.blog.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel.blog.models.RegistrationRequest;
import com.gabriel.blog.services.RegistrationService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class RegistrationController {
	
	private final RegistrationService registrationService;


	@PostMapping("/register")
	public ResponseEntity<String> register(@Valid @RequestBody RegistrationRequest request) {
		return new ResponseEntity<>(registrationService.register(request),HttpStatus.OK);
	}

}
