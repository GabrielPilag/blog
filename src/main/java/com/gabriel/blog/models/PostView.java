package com.gabriel.blog.models;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostView {

	private Long id;
	private String title;
	private String imageUrl;
	private String category;
	private LocalDateTime creationDate;
	
	
}
