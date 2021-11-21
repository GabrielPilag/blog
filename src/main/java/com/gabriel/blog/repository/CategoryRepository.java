package com.gabriel.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabriel.blog.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
