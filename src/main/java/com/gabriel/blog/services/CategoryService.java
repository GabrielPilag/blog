package com.gabriel.blog.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gabriel.blog.models.Category;
import com.gabriel.blog.repository.CategoryRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryService {
	
	private final CategoryRepository categoryRepository;

	public Optional<Category> findCategoryById(Long id){
		return categoryRepository.findById(id);
	}
	
}
