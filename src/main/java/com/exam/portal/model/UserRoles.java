package com.exam.portal.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class UserRoles {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long UserRole;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private UserModel user;
	
	@ManyToOne
	private Roles roles;

	public UserRoles() {
		 
	}

	public Long getUserRole() {
		return UserRole;
	}

	public void setUserRole(Long userRole) {
		UserRole = userRole;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public Roles getRole() {
		return roles;
	}

	public void setRole(Roles role) {
		this.roles = role;
	} 
	
}
