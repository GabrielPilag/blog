package com.gabriel.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabriel.blog.entities.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long>{

	Optional<AppUser> findByEmail(String email);
	
}
