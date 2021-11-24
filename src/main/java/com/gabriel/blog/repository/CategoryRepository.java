package com.gabriel.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabriel.blog.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
