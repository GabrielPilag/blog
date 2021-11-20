package com.gabriel.blog.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gabriel.blog.models.AppUser;
import com.gabriel.blog.repository.AppUserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService{

	private final AppUserRepository appUserRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Mejorar la excepcion que lanza
		return appUserRepository.findByEmail(username).orElseThrow(()-> new IllegalStateException("username not found"));
	}

	@Transactional
	public String signUpUser(AppUser appUser) {
		
		boolean userExists = appUserRepository.findByEmail(appUser.getEmail()).isPresent();
		if (userExists) {
			throw new IllegalStateException("email already taken");
		}
		String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
		appUser.setPassword(encodedPassword);
		
		appUserRepository.save(appUser);//guardo en mi base de datos mi usuario
		
		return "Registro correcto";
	}
	
	public Optional<AppUser> findUserById(Long id) {
		return appUserRepository.findById(id);
	}
	
	

}
