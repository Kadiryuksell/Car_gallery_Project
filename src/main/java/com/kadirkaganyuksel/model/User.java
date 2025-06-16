package com.kadirkaganyuksel.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseEntity implements UserDetails{

	private String username;
	
	private String password;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return List.of();
	}
	
}
