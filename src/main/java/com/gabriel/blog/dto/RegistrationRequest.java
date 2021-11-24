package com.gabriel.blog.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {

	@NotBlank(message="cannot be blank")
	private final String password;
	
	@NotBlank(message="cannot be blank")
	@Email(message = "email not valid")
	private final String email;
	
	
}
