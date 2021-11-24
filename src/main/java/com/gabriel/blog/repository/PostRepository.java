package com.gabriel.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabriel.blog.entities.Post;

public interface PostRepository extends JpaRepository<Post, Long>{

}
