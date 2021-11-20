package com.gabriel.blog.services;

import org.springframework.stereotype.Service;

import com.gabriel.blog.models.AppUser;
import com.gabriel.blog.models.RegistrationRequest;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RegistrationService {
	
	
	private final AppUserService appUserService;
	
	public String register(RegistrationRequest request) {
		
		return appUserService
							.signUpUser(new AppUser(request.getEmail(), request.getPassword()));
	}

	
	
	
	

}
