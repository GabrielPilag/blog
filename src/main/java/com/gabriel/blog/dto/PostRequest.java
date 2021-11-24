package com.gabriel.blog.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class PostRequest {
	
	@NotBlank(message = "cannot be blank")
	private String title;
	
	@NotBlank(message = "cannot be blank")
	private String content;
	
	@NotBlank(message = "cannot be blank")
	@URL(message = "must be a valid URL")
	private String imageUrl;
	
	@NotNull(message = "cannot be null")
	private Long idUser;
	
	@NotNull(message = "cannot be null")
	private Long idCategory;
}
