package com.example.demo.bean;

import org.springframework.security.core.GrantedAuthority;

import com.example.demo.data.UserRole;

public class UserAuthority implements GrantedAuthority{

	private UserRole role;
	
	public UserAuthority(UserRole role) {
		this.role = role;
	}
	
	public UserAuthority(Integer roleValue) {
		this.role = UserRole.parse(roleValue);
	}
	
	@Override
	public String getAuthority() {
		return this.role.toString();
	}

}
