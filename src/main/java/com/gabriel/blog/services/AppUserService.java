package com.gabriel.blog.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gabriel.blog.entities.AppUser;
import com.gabriel.blog.handlers.NotFoundException;
import com.gabriel.blog.repository.AppUserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService{

	private final AppUserRepository appUserRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return appUserRepository.findByEmail(username).orElseThrow(()-> new NotFoundException("username not found"));
	}

	@Transactional
	public String signUpUser(AppUser appUser) {
		
		boolean userExists = appUserRepository.findByEmail(appUser.getEmail()).isPresent();
		if (userExists) {
			throw new IllegalStateException("email already taken");
		}
		
		String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
		appUser.setPassword(encodedPassword);
		appUserRepository.save(appUser);
		
		return "Registro correcto";
	}
	
	public Optional<AppUser> findUserById(Long id) {
		return appUserRepository.findById(id);
	}
	
	

}
