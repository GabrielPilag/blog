package com.gabriel.blog.models;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name="appuser")
public class AppUser implements UserDetails{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String email;
	@JsonIgnore
	private String password;

	public AppUser(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//Para simplificar todos los usuarios tienen los mismos Roles.
		Set<SimpleGrantedAuthority> permissions = new HashSet<SimpleGrantedAuthority>();
		permissions.add(new SimpleGrantedAuthority("ROLE_USER"));
		return permissions;
	}
	@Override
	@JsonIgnore
	public String getUsername() {
		return email;
	}
	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		// No se utiliza
		return true;
	}
	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		// No se utiliza
		return true;
	}
	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		// No se utiliza
		return true;
	}
	@Override
	@JsonIgnore
	public boolean isEnabled() {
		//No se utiliza
		return true;
	}
	
	
	
}
