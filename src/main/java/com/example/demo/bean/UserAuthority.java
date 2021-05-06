/////////////////////////////////////////////////////////////////////////////
//
// © 2021 IDTU-CS3332IRFA-21TSP
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.bean;

import org.springframework.security.core.GrantedAuthority;

import com.example.demo.data.UserRole;

/**
 * [OVERVIEW] User Authority.
 *
 * @author: LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2021/04/20      LinhDT             Create new
*/
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
